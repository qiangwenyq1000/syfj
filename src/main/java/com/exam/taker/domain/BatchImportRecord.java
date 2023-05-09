package com.exam.taker.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
@TableName("t_batch_import_record")
public class BatchImportRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uuid", type = IdType.UUID)
    private String uuid;

    /**
     * 用户ID
     */
    @TableField("USER_UUID")
    private String userUuid;

    /**
     * 附件主键
     */
    @TableField("FILE_UUID")
    private String fileUuid;

    /**
     * 文件名
     */
    @TableField("FILE_NAME")
    private String fileName;

    /**
     * 文件路径
     */
    @TableField("FILE_PATH")
    private String filePath;

    /**
     * 文件类型:0源文件，1记录导入成功数据文件，2记录导入失败数据文件
     */
    @TableField("TYPE")
    private String type;

    /**
     * 导入数据总量
     */
    @TableField("ALL_NUM")
    private String allNum;

    /**
     * 导入失败数据总量
     */
    @TableField("FALL_NUM")
    private String fallNum;

    /**
     * 导入分类:0毕业生、1中考考生信息、2录取情况、3学生去向
     */
    @TableField("IMPORT_TYPE")
    private String importType;

    @TableField("CTIME")
    private Date ctime;

    /**
     * 机构主键
     */
    @TableField("ORG_UUID")
    private String orgUuid;


}
