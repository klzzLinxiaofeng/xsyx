package com.xunyunedu.file.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息
 *
 * @author edison
 */
@Data
public class ResEntityFile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * 文件的唯一md5码
     */
    private String md5;

    /**
     * 做关联的uuid
     */
    private String uuid;

    /**
     * 文件扩展名不包括点(.)
     */
    private String extension;

    /**
     * 文件头类型
     */
    private String contentType;

    /**
     * 真实文件名
     */
    private String fileName;

    /**
     * 系统磁盘上的文件名称
     */
    private String diskFileName;

    /**
     * 文件对应缩略图的相对地址
     */
    private String thumbnailUrl;

    /**
     * 文件的相对路径
     */
    private String relativePath;

    /**
     * 创建时间
     */
    private Date createDate;


}
