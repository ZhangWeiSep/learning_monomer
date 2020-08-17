package indi.zhangweisep.common.base.result;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.annotation.JsonInclude;
import indi.zhangweisep.common.enums.ServiceCodeEnum;
import indi.zhangweisep.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 全局请求返回结果实体
 * </p>
 *
 * @author ZhangWei
 * @since 2020/8/17 14:36
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否执行成功
     */
    private boolean success;

    /**
     * code码：请求回执状态码
     */
    private String code;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 请求返回值
     */
    private T data;

    /**
     * 请求成功——无返回值
     *
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> success() {
        return success(ServiceCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 请求成功——自定义消息
     *
     * @param message 消息
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> success(String message) {
        return success(message, null);
    }

    /**
     * 请求成功——返回结果集
     *
     * @param data 数据
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> success(T data) {
        return success(ServiceCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 请求成功——构造返回结果
     *
     * @param message 消息
     * @param data    数据
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> success(String message, T data) {
        return new ResultData<>(Boolean.TRUE,
                ServiceCodeEnum.SUCCESS.getCode(),
                message,
                data);
    }

    /**
     * 请求失败——无返回值
     *
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error() {
        return error(ServiceCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 请求失败——自定义消息
     *
     * @param message 消息
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error(String message) {
        return error(message, null);
    }

    /**
     * 请求失败——返回结果集
     *
     * @param data 数据
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error(T data) {
        return error(ServiceCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 请求失败——构造返回结果
     *
     * @param message 消息
     * @param data    数据
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error(String message, T data) {
        return new ResultData<>(Boolean.FALSE,
                ServiceCodeEnum.SYS_ERROR.getCode(),
                message,
                data);
    }

    /**
     * 请求失败——自定义错误
     *
     * @param error 错误
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error(ServiceCodeEnum error) {
        return error(error.getCode(), error.getMessage());
    }

    /**
     * 请求失败——构造返回结果
     *
     * @param code    代码
     * @param message 消息
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error(String code, String message) {
        return new ResultData<>(Boolean.FALSE,
                code,
                message,
                null);
    }

    /**
     * 请求失败——自定义异常
     *
     * @param e e
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error(ServiceException e) {
        //是否存在自定义异常消息
        if (e.getServiceCodeEnum() == null) {
            return error(e.getCode(), e.getMessage());
        }
        return error(e.getServiceCodeEnum());
    }

    /**
     * 请求失败——系统异常
     *
     * @param e e
     * @return {@link ResultData<T>}
     */
    public static <T> ResultData<T> error(Exception e) {
        //是否是自定义异常
        if (e instanceof ServiceException) {
            return error(e);
        }
        //是否存在异常消息
        if (StrUtil.isNotBlank(e.getMessage())) {
            return error(e.getMessage());
        }
        return error();
    }


}