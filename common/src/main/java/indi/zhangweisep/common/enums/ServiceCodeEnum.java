package indi.zhangweisep.common.enums;

import lombok.Getter;
import lombok.ToString;

import java.text.MessageFormat;

/**
 * <p>
 * 全局请求回执状态码
 * </p>
 *
 * @author ZhangWei
 * @since 2020/8/17 14:50
 */
@ToString
public enum ServiceCodeEnum {
    /**
     * 系统异常
     */
    SUCCESS("0", "执行成功"),
    SYS_ERROR("500", "未知异常，请联系管理员"),

    LOGIN_TIME_OUT("401", "登录超时，请重新登录"),

    PARAM_ERROR("400", "参数异常"),

    API_USER_NOT_FOND("1404", "用户不存在，请先注册"),

    ;

    /**
     * 错误码
     */
    @Getter
    private final String code;

    /**
     * 错误信息
     */
    @Getter
    private final String message;

    /**
     * 重写构造方法
     *
     * @param code    状态码
     * @param message 消息
     */
    ServiceCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取消息
     *
     * @param args args
     * @return {@link String}
     */
    /*public String getMessage(String... args) {
        if (args != null && args.length > 0) {
            return new MessageFormat(message).format(args);
        }
        return message;
    }*/

}
