package indi.zhangweisep.backstage.config.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * <p>
 * 自定义token
 * 继承自AbstractAuthenticationToken，主要用于存放登录的token信息、用户信息
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/22 14:03
 */
public class CustomToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 主要
     */
    private final Object principal;

    /**
     * 凭证
     */
    private final Object credentials;

    /**
     * 自定义令牌
     *
     * @param principal   主要
     * @param credentials 凭证
     */
    public CustomToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(Boolean.FALSE);
    }

    /**
     * The credentials that prove the principal is correct. This is usually a password,
     * but could be anything relevant to the <code>AuthenticationManager</code>. Callers
     * are expected to populate the credentials.
     *
     * @return the credentials that prove the identity of the <code>Principal</code>
     */
    @Override
    public Object getCredentials() {
        return credentials;
    }

    /**
     * The identity of the principal being authenticated. In the case of an authentication
     * request with username and password, this would be the username. Callers are
     * expected to populate the principal for an authentication request.
     * <p>
     * The <tt>AuthenticationManager</tt> implementation will often return an
     * <tt>Authentication</tt> containing richer information as the principal for use by
     * the application. Many of the authentication providers will create a
     * {@code UserDetails} object as the principal.
     *
     * @return the <code>Principal</code> being authenticated or the authenticated
     * principal after authentication.
     */
    @Override
    public Object getPrincipal() {
        return principal;
    }
}
