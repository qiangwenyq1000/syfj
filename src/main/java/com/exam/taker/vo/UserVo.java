package com.exam.taker.vo;

import com.exam.taker.util.customize.MaskingField;
import com.exam.taker.util.customize.MaskingTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private String uuid;

    @MaskingField(type = MaskingTypeEnum.MOBILE_PHONE)
    private String phone;

    private String realName;

    @MaskingField(type = MaskingTypeEnum.ID_CARD)
    private String certificates;

    private String position;

    private String orgName;

    private String provinceName;

    private String cityName;

    private Date creatorDate;

    private String objectType;
}
