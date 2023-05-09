package com.exam.taker.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class BatchImportRecordVo {

    private String uuid;

    /**
     * 用户ID
     */
    private String userUuid;

    /**
     * 用户ID
     */
    private String userName;

    /**
     * 附件主键
     */
    private String fileUuid;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型:0源文件，1记录导入成功数据文件，2记录导入失败数据文件
     */
    private String type;

    /**
     * 导入数据总量
     */
    private String allNum;

    /**
     * 导入失败数据总量
     */
    private String fallNum;

    /**
     * 导入分类:0毕业生、1中考考生信息、2录取情况、3学生去向
     */
    private String importType;

    private Date ctime;

    /**
     * 机构主键
     */
    private String orgUuid;
}
