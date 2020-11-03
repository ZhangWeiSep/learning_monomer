package indi.zhangweisep.backstage.config.security;

import indi.zhangweisep.backstage.config.security.token.CustomToken;
import indi.zhangweisep.common.exception.ServiceException;
import indi.zhangweisep.modules.system.entity.SysUser;
import indi.zhangweisep.modules.system.service.SysUserService;
import indi.zhangweisep.modules.system.vo.UserDetailVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * <p>
 * 自定义用户身份验证
 * </p>
 *
 * @author ZhangWei
 * @date 2020/10/22
 * @since 2020/10/22 13:31
 */
@Configuration
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final SysUserService sysUserService;


    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //用户名
        String userName = Optional.ofNullable(authentication.getName())
                .orElseThrow(() -> new ServiceException("用户名不能为空"));
        //密码
        String password = (String) Optional.ofNullable(authentication.getCredentials())
                .orElseThrow(() -> new ServiceException("密码不能为空"));
        //查询用户信息
        SysUser user = sysUserService.lambdaQuery()
                .eq(SysUser::getUserName, userName)
                .oneOpt()
                .orElseThrow(() -> new ServiceException("用户不存在"));
        if (user.getSealFlag()) {
            throw new ServiceException("该用户账号已被封禁，请联系客服处理");
        }
        if (ENCODER.matches(password, user.getPassword())) {
            throw new ServiceException("密码错误");
        }
        //创建自定义token对象
        CustomToken customToken = new CustomToken(UserDetailVO.builder()
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .realName(user.getRealName())
                .sex(user.getSex())
                .mobile(user.getMobile())
                .phoneAddress(user.getPhoneAddress())
                .email(user.getEmail())
                .sign(user.getSign())
                .build(), null);
        //赋值身份验证信息
        customToken.setDetails(authentication.getDetails());
        return customToken;
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {

        return false;
    }
}
