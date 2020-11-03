package indi.zhangweisep.backstage.config.security.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import indi.zhangweisep.backstage.config.properties.LoginTokenProperties;
import indi.zhangweisep.backstage.config.security.SecurityUserDetail;
import indi.zhangweisep.backstage.util.ResponseUtil;
import indi.zhangweisep.common.constants.SecurityConstant;
import indi.zhangweisep.modules.system.entity.SysUser;
import indi.zhangweisep.modules.system.vo.LoginUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录成功处理器
 * SavedRequestAwareAuthenticationSuccessHandler：继承security定义的登录成功处理类，自定义登录成功的操作
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/22 15:40
 */
@Configuration
@RequiredArgsConstructor
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final StringRedisTemplate redisTemplate;

    private final LoginTokenProperties tokenProperties;

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //提取用户信息
        SecurityUserDetail userDetail = ((SecurityUserDetail) authentication.getPrincipal());
        //提取权限信息
        List<String> permissions = userDetail.getAuthorities().stream().map(String::valueOf).collect(Collectors.toList());
        permissions.add("project:save");
        //初始化系统用户
        SysUser user = new SysUser();
        user.setId(userDetail.getUserId());
        user.setUserName(userDetail.getUsername());
        //初始化登录用户
        LoginUserVO loginUser = new LoginUserVO();
        loginUser.setSysUser(user);
        loginUser.setPermissions(permissions);
        loginUser.setSaveLogin(false);
        //判断是否是单点登录
        if (tokenProperties.getSdl()) {
            //清除缓存的token
            Optional.ofNullable(redisTemplate.opsForValue()
                    .get(SecurityConstant.USER_TOKEN + userDetail.getUsername()))
                    .ifPresent(data -> redisTemplate.delete(SecurityConstant.TOKEN_PRE + data));
        }
        //登录成功生成token
        String token = IdUtil.fastSimpleUUID();
        //获取参数中设置的保存token期限规则
        String saveLogin = request.getParameter(SecurityConstant.SAVE_LOGIN);
        //设置保存规则的时间单位
        TimeUnit minutes = StrUtil.equals(saveLogin, Boolean.TRUE.toString()) ? TimeUnit.DAYS
                : TimeUnit.MINUTES;
        //保存token信息到redis中
        redisTemplate.opsForValue().set(SecurityConstant.USER_TOKEN + userDetail.getUsername(), token,
                tokenProperties.getSaveLoginTime(), minutes);
        redisTemplate.opsForValue().set(SecurityConstant.TOKEN_PRE + token,
                JSONObject.toJSONString(loginUser), tokenProperties.getSaveLoginTime(), minutes);
        //发送token信息到前端
        JSONObject obj = new JSONObject();
        obj.put("access_token", token);
        ResponseUtil.out(response, R.ok(obj).setMsg("登录成功"));
    }

}
