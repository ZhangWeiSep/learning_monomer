package indi.zhangweisep.modules.system.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 用户登录信息
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/22 14:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailVO {

    /**
     * 登录名/用户名
     */
    private String userName;

    /**
     * 用户名/昵称（自定义显示名称）
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

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
     * 权限集合
     */
    private List<String> permissions;

}
