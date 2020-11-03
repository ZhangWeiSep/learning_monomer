package indi.zhangweisep.backstage.config.security;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import indi.zhangweisep.backstage.config.exception.LoginFailException;
import indi.zhangweisep.modules.system.entity.SysUser;
import indi.zhangweisep.modules.system.service.SysUserService;
import indi.zhangweisep.modules.system.vo.LoginUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * 自定义userDetailsService实现认证
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/27 15:48
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.isBlank(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        //根据用户名查询用户信息
        SysUser user = sysUserService.lambdaQuery()
                .eq(SysUser::getUserName, username)
                .oneOpt()
                .orElseThrow(() -> new LoginFailException("用户不存在"));
        if (user.getSealFlag()) {
            throw new LoginFailException("该用户账号已被封禁，请联系客服处理");
        }
        //查询登录用户信息
        LoginUserVO loginUser = sysUserService.findLoginUser(user);
        //保存角色资源信息
        Set<String> authsSet = Sets.newHashSet();
        //获取角色
        //获取资源

        //设置权限
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authsSet.toArray(new String[0]));
        //初始化自定义security用户信息
        return new SecurityUserDetail(user, authorities);
    }
}
