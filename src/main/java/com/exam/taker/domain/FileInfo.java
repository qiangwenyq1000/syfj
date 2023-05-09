package com.exam.taker.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@TableName("t_file_info")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UUID", type = IdType.UUID)
    private String uuid;

    /**
     * 云存储文件路径
     */
    @TableField("FILE_SAVE_PATH")
    private String fileSavePath;

    /**
     * 文件上传名
     */
    @TableField("FILE_UPLOAD_NAME")
    private String fileUploadName;

    /**
     * 文件大小，文件真实大小以byte为单位
     */
    @TableField("FILE_SIZE")
    private Integer fileSize;

    /**
     * 文件原始类型
     */
    @TableField("FILE_ORIGINAL_TYPE")
    private String fileOriginalType;


}
