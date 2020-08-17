package indi.zhangweisep.common.base.entity;

import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 实体类的抽象<br/>
 * 所有实体类都继承这个类<br/>
 * 声明公共的实体属性
 * </p>
 *
 * @author ZhangWei
 * @since 2020/8/14 10:37
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String modifyUser;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标识
     */
    @TableLogic
    private Boolean delFlag;

}
