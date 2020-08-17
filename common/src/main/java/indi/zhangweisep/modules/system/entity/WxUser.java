package indi.zhangweisep.modules.system.entity;

import indi.zhangweisep.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 微信用户表——实体类
 * </p>
 *
 * @author Zhang Wei
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(callSuper = true)
public class WxUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统登陆用户
     */
    private String sysUserName;

    /**
     * 微信用户的openid
     */
    private String openid;

    /**
     * 联系电话（接受验证码用的）
     */
    private String phone;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 关注时间
     */
    private String focusTime;

    /**
     * 微信用户状态标志
     */
    private Boolean mark;


}
