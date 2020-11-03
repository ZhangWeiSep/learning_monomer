package indi.zhangweisep.modules.system.vo;

import indi.zhangweisep.modules.system.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 登录用户信息
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/22 15:48
 */
@Data
public class LoginUserVO implements Serializable {

    /**
     * 系统用户信息
     */
    private SysUser sysUser;

    /**
     * 保存登录信息
     */
    private Boolean saveLogin;

    /**
     * 权限集合
     */
    private List<String> permissions;

    /**
     * 角色集合
     */
    private List<String> roles;

}
