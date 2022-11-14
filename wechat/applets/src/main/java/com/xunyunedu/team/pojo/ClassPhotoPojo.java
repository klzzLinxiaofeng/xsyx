package com.xunyunedu.team.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级相册图片
 *
 * @author: yhc
 * @Date: 2020/12/16 12:04
 * @Description:
 */
public class ClassPhotoPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;

    /**
     * 班级纪念册id
     */
    private Integer classYearbookId;

    /**
     * 图片uuid res_entity_file
     */
    private String uuid;

    /**
     *
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 图片路径
     */
    private String httpUrl;

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassYearbookId() {
        return classYearbookId;
    }

    public void setClassYearbookId(Integer classYearbookId) {
        this.classYearbookId = classYearbookId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

