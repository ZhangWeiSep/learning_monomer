package indi.zhangweisep.backstage.config.security;

import indi.zhangweisep.modules.system.entity.Role;
import indi.zhangweisep.modules.system.entity.SysUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * security用户信息
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/22 16:17
 */
public class SecurityUserDetail extends User {

    /**
     * 用户id
     */
    @Getter
    private final Long userId;

    @Getter
    private final List<Role> roleList;

    /**
     * 初始化security用户信息
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     *
     * @param sysUser     系统用户
     * @param authorities 当局
     */
    public SecurityUserDetail(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUserName(), sysUser.getPassword(), !sysUser.getSealFlag(),
                Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, authorities);
        this.userId = sysUser.getId();
        this.roleList = sysUser.getRoleList();
    }

}
