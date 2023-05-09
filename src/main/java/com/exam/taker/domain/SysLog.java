package com.exam.taker.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("t_sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uuid", type = IdType.UUID)
    private String uuid;

    /**
     * 记录类型
     */
    private String actType;

    /**
     * 记录名称
     */
    private String actName;

    /**
     * 记录描述
     */
    private String actDescription;

    @TableField("USER_UUID")
    private String userUuid;

    /**
     * 时间
     */
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    private Date creatorDate;

    /**
     * 动作状态 1 成功 0 失败
     */
    @TableField("ACT_STATUS")
    private String actStatus;

    /**
     * 记录发起值
     */
    @TableField("ACT_PAR")
    private String actPar;


    /**
     * 记录发起值
     */
    @TableField("ACT_PAR_ASE")
    private String actParAse;
}
