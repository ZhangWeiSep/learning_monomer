package indi.zhangweisep.backstage.config.security.handler;

import com.baomidou.mybatisplus.extension.api.R;
import indi.zhangweisep.backstage.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 自定义权限拒绝处理类
 * AccessDeniedHandler: 继承security权限拒绝接口，自定义授权失败处理机制
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/27 13:43
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("授权失败，禁止访问{}", request.getRequestURI());
        ResponseUtil.out(response, R.failed("您没有权限").setCode(403), 403);
    }
}
