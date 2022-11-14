package com.xunyunedu.htsfeedback.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-09 0:48
 */
public class HtsFeedbackPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 家长姓名
     */
    private String parentName;

    /**
     * 家长手机号
     */
    private String phone;

    /**
     * 反馈内容
     */
    private String content;
    /**
     * 回复
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 返回完整的图片说明url
     */
    private String picUrl;

    public List getUuids() {
        return uuids;
    }

    public void setUuids(List uuids) {
        this.uuids = uuids;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    /**
     * 图片说明uuid
     */
    private List uuids;

    private String uuid;

    /**
     * 实际图片的地址，不存在于表中
     */
    private String coverUrl;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 请求类型 0 回复   1  详情
     */
    private Integer type;

    /*
     * 回复标记
     *
     * 0 未回复   1 回复
     *
     * */
    private Integer isReply;


    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    /**
     * 接收传入的学生id
     */
    private Integer stuId;

    private Integer parentId;

    public Integer getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    /**
     * 接收传入的父母的id
     */
    private Integer parentUserId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }





    private Integer schoolId;
    /**
     * 创建日期
     */
    @JSONField(format = "yyyy/MM/dd")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }




    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 作废标记
     */
    private Integer isDelete;


    @Override
    public String toString() {
        return "HtsFeedbackPojo{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", uuids=" + uuids +
                ", uuid='" + uuid + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", type=" + type +
                ", isReply=" + isReply +
                ", stuId=" + stuId +
                ", parentId=" + parentId +
                ", parentUserId=" + parentUserId +
                ", schoolId=" + schoolId +
                ", createDate=" + createDate +
                ", isDelete=" + isDelete +
                '}';
    }
}
