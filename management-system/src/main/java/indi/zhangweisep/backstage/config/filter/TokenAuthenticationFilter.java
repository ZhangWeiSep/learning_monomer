package indi.zhangweisep.backstage.config.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import indi.zhangweisep.backstage.config.properties.LoginTokenProperties;
import indi.zhangweisep.backstage.config.security.SecurityUserDetail;
import indi.zhangweisep.common.constants.CommonConstant;
import indi.zhangweisep.common.constants.SecurityConstant;
import indi.zhangweisep.modules.system.entity.SysUser;
import indi.zhangweisep.modules.system.vo.LoginUserVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 自定义token过滤器
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/27 14:05
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private StringRedisTemplate redisTemplate;

    private LoginTokenProperties tokenProperties;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager,
                                     LoginTokenProperties tokenProperties, StringRedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenProperties = tokenProperties;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Creates an instance which will authenticate against the supplied
     * {@code AuthenticationManager} and which will ignore failed authentication attempts,
     * allowing the request to proceed down the filter chain.
     *
     * @param authenticationManager the bean to submit authentication requests to
     */
    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 做过滤器内部
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 过滤器链
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader(SecurityConstant.AUTHORIZATION);
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(SecurityConstant.AUTHORIZATION);
        }
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        boolean type = Boolean.parseBoolean(request.getHeader("type"));
        if (type) {
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(token, response);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //将请求转发给过滤器链上下一个对象
        filterChain.doFilter(request, response);
    }

    /**
     * 得到认证
     *
     * @param token    令牌
     * @param response 响应
     * @return {@link UsernamePasswordAuthenticationToken}
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token, HttpServletResponse response) {
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        String v = redisTemplate.opsForValue().get(SecurityConstant.TOKEN_PRE + token);
        if (StrUtil.isBlank(v)) {
            return null;
        }
        LoginUserVO user = JSON.parseObject(v, LoginUserVO.class);
        SysUser sysUser = user.getSysUser();
        sysUser.setPassword("");
        sysUser.setSealFlag(CommonConstant.STATUS_NORMAL == 0);
        //缓存了权限
        for (String ga : user.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(ga));
        }
        if (!user.getSaveLogin()) {
            // 若未保存登录状态重新设置失效时间
            redisTemplate.opsForValue().set(SecurityConstant.USER_TOKEN + sysUser.getUserName(), token, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(SecurityConstant.TOKEN_PRE + token, v, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
        }
        SecurityUserDetail principal = new SecurityUserDetail(sysUser, authorities);
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }


}
