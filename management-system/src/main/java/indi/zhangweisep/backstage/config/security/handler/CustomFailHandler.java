package indi.zhangweisep.backstage.config.security.handler;

import com.baomidou.mybatisplus.extension.api.R;
import indi.zhangweisep.backstage.config.exception.LoginFailException;
import indi.zhangweisep.backstage.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 失败处理器
 * SimpleUrlAuthenticationFailureHandler：继承security定义的登录失败处理类，自定义登录失败通知
 * SimpleUrlAuthenticationFailureHandler继承AuthenticationFailureHandler接口
 * AuthenticationFailureHandler：security定义的登录失败处理接口
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/27 13:19
 */
@Slf4j
@Component
public class CustomFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        log.error(e.getMessage());
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            ResponseUtil.out(response, R.failed("用户名或密码错误"));
        } else if (e instanceof LockedException) {
            ResponseUtil.out(response, R.failed("账户被禁用，请联系管理员"));
        } else if (e instanceof DisabledException) {
            ResponseUtil.out(response, R.failed("账户被禁用，请联系管理员"));
        } else if (e instanceof LoginFailException) {
            ResponseUtil.out(response, R.failed(e.getMessage()));
        } else {
            ResponseUtil.out(response, R.failed("登录失败，其他内部错误").setCode(500), 500);
            e.printStackTrace();
        }
    }

}
