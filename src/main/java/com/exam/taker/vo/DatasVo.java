package com.exam.taker.vo;

import lombok.Data;

import java.util.List;

@Data
public class DatasVo {

    /**
     * 名称
     */
    private String type;

    /**
     * DataVo对象
     */
    private List<DataVo> list;
}
