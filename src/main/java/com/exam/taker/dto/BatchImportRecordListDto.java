package com.exam.taker.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("文件导入列表查询扩展信息")
public class BatchImportRecordListDto {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userUuid;

    /**
     * 附件主键
     */
    @ApiModelProperty(value = "附件主键")
    private String fileUuid;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;


    /**
     * 文件类型:0源文件，1记录导入成功数据文件，2记录导入失败数据文件
     */
    @ApiModelProperty(value = "文件类型:0源文件，1记录导入成功数据文件，2记录导入失败数据文件")
    private String type;


    /**
     * 导入分类:0毕业生、1中考考生信息、2录取情况、3学生去向
     */
    @ApiModelProperty(value = "导入分类:0毕业生、1中考考生信息、2录取情况、3学生去向")
    private String importType;


    /**
     * 机构主键
     */
    @ApiModelProperty(value = "机构主键")
    private String orgUuid;
}
