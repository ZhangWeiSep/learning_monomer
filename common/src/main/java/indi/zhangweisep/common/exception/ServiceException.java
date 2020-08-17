package indi.zhangweisep.common.exception;

import indi.zhangweisep.common.enums.ServiceCodeEnum;
import lombok.Getter;

/**
 * <p>
 * 自定义运行时异常
 * </p>
 *
 * @author ZhangWei
 * @since 2020/8/17 16:03
 */
public class ServiceException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    /**
     * 错误码枚举
     */
    private ServiceCodeEnum serviceCodeEnum;

    /**
     * 状态码
     */
    @Getter
    private String code;

    /**
     * 服务异常
     *
     * @param serviceCodeEnum 服务代码枚举
     */
    public ServiceException(ServiceCodeEnum serviceCodeEnum) {
        super(serviceCodeEnum.getMessage());
        this.code = serviceCodeEnum.getCode();
        this.serviceCodeEnum = serviceCodeEnum;
    }

    /**
     * 服务异常
     *
     * @param code    代码
     * @param message 消息
     */
    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 服务异常
     *
     * @param message 消息
     */
    public ServiceException(String message) {
        super(message);
        this.code = ServiceCodeEnum.SYS_ERROR.getCode();
    }

    /**
     * 服务异常
     *
     * @param message 消息
     * @param e       e
     */
    public ServiceException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 服务异常
     *
     * @param e e
     */
    public ServiceException(Exception e) {
        super(e);
        this.code = ServiceCodeEnum.SYS_ERROR.getCode();
    }

    /**
     * 得到服务代码枚举
     *
     * @return {@link ServiceCodeEnum}
     */
    public ServiceCodeEnum getServiceCodeEnum() {
        return serviceCodeEnum;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     * @return {@link ServiceException}
     */
    public ServiceException setCode(String code) {
        this.code = code;
        return this;
    }

}