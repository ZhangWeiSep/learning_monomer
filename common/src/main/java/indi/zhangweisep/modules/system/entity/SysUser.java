package indi.zhangweisep.modules.system.entity;

import indi.zhangweisep.common.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统用户表——实体类
 * </p>
 *
 * @author Zhang Wei
 * @since 2020-08-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(callSuper = true)
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名（自定义显示名称）
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String citizenIdNumber;

    /**
     * 性别：0：女，1：男
     */
    private Boolean sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户头像地址
     */
    private String phoneAddress;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 在线状态
     */
    private String onlineState;

    /**
     * 封号标识
     */
    private Boolean sealFlag;

    /**
     * 微信号
     */
    private String wxOpenid;

    /**
     * 绑定的微信昵称
     */
    private String wxNickname;

    /**
     * 绑定的微信unionid
     */
    private String wxUnionId;

}
