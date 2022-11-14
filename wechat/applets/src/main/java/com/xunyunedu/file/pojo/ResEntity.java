package com.xunyunedu.file.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author edison
 */
@Data
public class ResEntity {

    Integer id;

    /**
     * 创建时间
     */
    Date createDate;

    /**
     * 修改时间
     */
    Date modifyDate;

    /**
     * 文件大小 单位mb
     */
    Double size;

    /**
     * 文件md5码
     */
    String md5Code;

    /**
     * 做关联的uuid
     */
    String uuid;

    /**
     * 文件后缀
     */
    String suffix;

    /**
     * 文件属于哪个app
     */
    Integer appId;

    /**
     * ftp服务器代码
     */
    String ftpCode;

    /**
     * 文件上传者的id
     */
    Integer userId;

    /**
     * 文件缩略图地址
     */
    String thumbnailUrl;

    /**
     * 文件存放路径地址
     */
    String url;

    /**
     * 上传后修改生成的文件名
     */
    String fileName;

    /**
     * 上传时的真实文件名
     */
    String realFileName;

    /**
     * 是否上传完的标志  1 已上传完  2 未上传完
     */
    Integer finishedFlag;

}
