package com.xunyunedu.personinfor.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 问卷调查
 *
 * @author: yhc
 * @Date: 2020/10/12 16:18
 * @Description: 问卷调查
 */
public class QuestionnairePojo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * 问卷名称
     */
    private String name;
    /**
     * 问卷对象 1: 教师 2:学生/家长
     */
    private Integer object;
    /**
     * 问卷星url
     */
    private String url;

    /**
     * 图片完整url
     */
    private String httpUrl;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 截止有效日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;

    private Integer schoolId;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 作废标记
     */
    private Integer isDelete;

    private String uuid;


    public QuestionnairePojo() {
    }

    public QuestionnairePojo(Integer id) {
        this.id = id;
    }


    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getObject() {
        return object;
    }

    public void setObject(Integer object) {
        this.object = object;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "QuestionnaireVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", object='" + object + '\'' +
                ", url='" + url + '\'' +
                ", expiryDate=" + expiryDate +
                ", schoolId=" + schoolId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDelete=" + isDelete +
                '}';
    }
}

