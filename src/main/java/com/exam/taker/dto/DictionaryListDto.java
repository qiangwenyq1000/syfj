package com.exam.taker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("字典列表查询扩展信息")
public class DictionaryListDto {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String dicValue;

    /**
     * 编码值
     */
    @ApiModelProperty(value = "编码值")
    private String dicKey;

    /**
     * 上级标识
     */
    @ApiModelProperty(value = "上级标识")
    private String parentCode;

    /**
     * 分组标识
     */
    @ApiModelProperty(value = "分组标识")
    private String groupCode;



    /**
     * 状态:1启用；0停用，默认值1
     */
    @ApiModelProperty(value = "状态:1启用；0停用，默认值1")
    private Integer status;
}
