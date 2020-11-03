package indi.zhangweisep.backstage.util;

import cn.hutool.core.util.StrUtil;
import indi.zhangweisep.backstage.config.security.SecurityUserDetail;
import indi.zhangweisep.common.constants.SecurityConstant;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 安全工具类
 * </p>
 *
 * @author ZhangWei
 * @since 2020/11/3 16:05
 */
@UtilityClass
public class SecurityUtil {

    /**
     * 获取当前登录用户名
     */
    public String getCurrUsername() {
        return getUserDetails().getUsername();
    }

    /**
     * 获取当前登录用户ID
     */
    public String getCurrUserId() {
        return getUserDetails().getUserId().toString();
    }

    /**
     * 获取Authentication
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取Authentication
     */
    private SecurityUserDetail getUserDetails() {
        return (SecurityUserDetail) getAuthentication().getPrincipal();
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<String> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstant.ROLE))
                .forEach(granted -> roleIds.add(StrUtil.removePrefix(granted.getAuthority(), SecurityConstant.ROLE)));
        return roleIds;
    }

    /**
     * 获取用户权限信息
     *
     * @return 权限集合
     */
    public List<String> getPermissions() {
        Collection<? extends GrantedAuthority> authorities = getAuthentication().getAuthorities();

        List<String> permissions = new ArrayList<>();
        authorities.stream()
                .filter(granted -> !StrUtil.startWith(granted.getAuthority(), SecurityConstant.ROLE))
                .forEach(granted -> permissions.add(granted.getAuthority()));
        return permissions;
    }

}
