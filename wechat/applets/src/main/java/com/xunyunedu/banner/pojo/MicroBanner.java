package com.xunyunedu.banner.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class MicroBanner {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String url;
    private String description;
    private String entityId;
    private String pushState;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;
    private Date modifyDate;
    /**
     * 图片地址
     */
    private String httUrl;


    public MicroBanner() {
    }

    public String getHttUrl() {
        return httUrl;
    }

    public void setHttUrl(String httUrl) {
        this.httUrl = httUrl;
    }

    public MicroBanner(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPushState() {
        return this.pushState;
    }

    public void setPushState(String pushState) {
        this.pushState = pushState;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
