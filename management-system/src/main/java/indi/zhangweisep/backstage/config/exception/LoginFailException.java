package indi.zhangweisep.backstage.config.exception;

import lombok.Getter;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * <p>
 * 登录失败异常
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/27 13:30
 */
public class LoginFailException extends InternalAuthenticationServiceException {

    /**
     * 消息
     */
    @Getter
    private String message;

    public LoginFailException(String message) {
        super(message);
        this.message = message;
    }

}
