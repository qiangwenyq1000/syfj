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
 * 字典分类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dictionary_group")
public class DictionaryGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @TableField("GROUP_NAME")
    private String groupName;

    @TableField("GROUP_CODE")
    private String groupCode;

    @TableField("REMARK")
    private String remark;

    @TableField("SORT")
    private Integer sort;

    @TableField("STATUS")
    private Integer status;


}
