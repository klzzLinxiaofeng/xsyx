package com.xunyunedu.resource.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 学校资源库
 * @author edison
 */
@Data
public class ResResourceLibrary {

    Integer id;

    String uuid;

    Integer appId;

    /**
     * 所属学校机构和单位
     */
    Integer owerId;

    /**
     * 如果有地区属性，此为行政区划码
     */
    String regionCode;

    /**
     * 资源库名称
     */
    String name;

    /**
     * 创建时间
     */
    Date createDate;

    /**
     * 修改时间
     */
    Date modifyDate;

    String ownerUid;

    String libType;

    Boolean allowShareIn;

    Boolean allowShareOut;

    Boolean noAuditing;

    Boolean allowSelfAuditing;

    Boolean allowAutoAuditing;



}
