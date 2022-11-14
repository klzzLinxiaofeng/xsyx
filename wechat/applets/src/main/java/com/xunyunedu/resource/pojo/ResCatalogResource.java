package com.xunyunedu.resource.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 目录资源
 */
@Data
public class ResCatalogResource {

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
     * 文件属于哪个APPId
     */
    Integer appId;

    /**
     * 使用学段
     */
    String stageCode;

    /**
     * 适用学科
     */
    String subjectCode;

    /**
     * 年级
     */
    String gradeCode;

    /**
     * 册次，卷
     */
    String volumnCode;

    /**
     * 版本
     */
    String versionCode;

    /**
     * 对应目录
     */
    String catalogCode;

    /**
     * 资源Id
     */
    Integer resourceId;

    /**
     * 资源类型
     */
    Integer resourceType;

    /**
     * 使用学段
     */
    String stageName;

    /**
     * 适用学科
     */
    String subjectName;

    /**
     * 年级
     */
    String gradeName;

    /**
     * 册次，卷
     */
    String volumName;

    /**
     * 版本
     */
    String versionName;

    String objectId;





}
