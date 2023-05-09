package com.exam.taker.vo;

import lombok.Data;

import java.util.List;

@Data
public class DatassInfoVo {


    /**
     * 名称
     */
    private String type;

    /**
     * DatasVo对象
     */
    private List<DatasInfoVo> list;
}
