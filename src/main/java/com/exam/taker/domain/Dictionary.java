package com.exam.taker.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 名称
     */
    @TableField("DIC_VALUE")
    private String dicValue;

    /**
     * 编码值
     */
    @TableField("DIC_KEY")
    private String dicKey;

    /**
     * 上级标识
     */
    @TableField("PARENT_CODE")
    private String parentCode;

    /**
     * 分组标识
     */
    @TableField("GROUP_CODE")
    private String groupCode;

    /**
     * 排序序号，默认值0
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * 状态:1启用；0停用，默认值1
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 数据含义
     */
    @TableField("REMARK")
    private String remark;


}
