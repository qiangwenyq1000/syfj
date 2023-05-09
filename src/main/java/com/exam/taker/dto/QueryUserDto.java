package com.exam.taker.dto;

import lombok.Data;

@Data
public class QueryUserDto extends RequestDto{
    private String orgUuid;
    private String provinceName;
    private String cityName;
}
