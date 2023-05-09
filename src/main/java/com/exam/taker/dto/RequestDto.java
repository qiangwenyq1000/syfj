package com.exam.taker.dto;

import lombok.Data;

@Data
public class RequestDto {

    private int pageNum=1;

    private int pageSize=20;

    private Boolean isPage=false;

    private String keywords;

    private String sortColumn;
//
    private String sortRule;
}
