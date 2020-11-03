package indi.zhangweisep.modules.system.entity;

import indi.zhangweisep.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author ZhangWei
 * @since 2020/11/2 17:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(callSuper = true)
public class Role extends BaseEntity {

    /**
     * 角色名
     */
    private String roleName;

}
