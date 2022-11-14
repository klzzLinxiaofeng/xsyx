package com.xunyunedu.paper.pojo;

import lombok.Data;

import java.util.Map;

/**
 * @author edison
 */
@Data
public class OaPaper {

    /**
     * 主键
     */
    private Integer id;
    /**
     * UUID
     */
    private String uuid;
    /**
     * appid
     */
    private Integer appId;
    /**
     * 公文所属的单位，学校
     */
    private Integer ownerId;
    /**
     * 组的类型，1：学校
     */
    private String ownerType;
    /**
     * 标题
     */
    private String title;
    /**
     * 发文单位
     */
    private String author;
    /**
     * 发布者ID
     */
    private Integer posterId;
    /**
     * 发布者姓名
     */
    private String posterName;
    /**
     * 公文种类
     */
    private String documentType;
    /**
     * 公文紧急等级
     */
    private String emergencyLevel;
    /**
     * 公文机密等级
     */
    private String secretLevel;
    /**
     * 接收者类型
     */
    private Integer receiverType;
    /**
     * 接收者实际人数
     */
    private Integer receiverCount;
    /**
     * 已阅人数
     */
    private Integer readCount;


    /**
     * 接收者名字
     */
    private String receiver;
    /**
     * 正文内容
     */
    private String content;
    /**
     * 附件文件ID
     */
    private String attachmentUuid;
    /**
     * 是否删除
     */
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    private java.util.Date createDate;
    /**
     * 修改时间
     */
    private java.util.Date modifyDate;

    /**
     * 发布日期
     */
    private String publishDate;

    /**
     * 摘要
     */
    private String remark;

    private Map<String,Object> params;




}
