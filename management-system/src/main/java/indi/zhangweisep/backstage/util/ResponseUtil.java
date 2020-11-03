package indi.zhangweisep.backstage.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 输出JSON结果
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/27 10:45
 */
@Slf4j
public class ResponseUtil {

    /**
     * 使用response输出JSON
     */
    public static void out(HttpServletResponse response, R r) {
        out(response, r, 200);
    }

    public static void out(HttpServletResponse response, R r, Integer status) {
        ServletOutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);
            out = response.getOutputStream();
            out.write(JSON.toJSONString(r).getBytes());
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
