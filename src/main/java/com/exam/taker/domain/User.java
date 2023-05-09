package com.exam.taker.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.exam.taker.util.customize.MaskingField;
import com.exam.taker.util.customize.MaskingTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("uc_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UUID", type = IdType.UUID)
    private String uuid;

    @TableField("PHONE")
    private String phone;

    @TableField("REAL_NAME")
    private String realName;

    @TableField("POSITION")
    private String position;

    @TableField("certificates")
    @MaskingField(type = MaskingTypeEnum.ID_CARD)
    private String certificates;

    @TableField("password")
    private String password;

    private Date creatorDate;
}
