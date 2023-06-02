package com.demo.file.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author df
 * @since 2023-06-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("minio_file")
public class MinioFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    @TableId("id")
    private Long id;

    /**
     * 原始文件名称
     */
    @TableField("original_file_name")
    private String originalFileName;

    /**
     * 文件拓展名
     */
    @TableField("file_ext_name")
    private String fileExtName;

    /**
     * 文件大小(单位：字节）
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 存入minio时的文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件的content-type
     */
    @TableField("mime")
    private String mime;


    /**
     * 是否删除 0 否 1 是
     */
    @TableField("is_delete")
    private Integer isDelete;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
