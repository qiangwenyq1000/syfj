package com.exam.taker.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("行为记录分页查询扩展信息")
public class SysLogDto extends RequestDto{

    /**
     * 记录名称
     */
    @ApiModelProperty(value = "记录名称")
    private String actName;

    /**
     * 记录类型
     */
    @ApiModelProperty(value = "记录类型")
    private String actType;


    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String userUuid;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private Date creatorDate;

    /**
     * 动作状态 1 成功 0 失败
     */
    @ApiModelProperty(value = "动作状态 1 成功 0 失败")
    private String actStatus;

    /**
     * 记录发起值
     */
    @ApiModelProperty(value = "记录发起值")
    private String actPar;

    @ApiModelProperty(value = "记录发起值密文")
    private String actParAse;
}
