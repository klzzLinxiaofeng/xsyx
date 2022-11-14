package com.xunyunedu.student.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-09 15:54
 */
@Data
public class StudentHealthArchivePojo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * pj_school.id
     */
    private Integer schoolId;

    /**
     * pj_team.id
     */
    private Integer teamId;
    /**
     * pj_student.id
     */
    private Integer studentId;
    /**
     * 类别
     */
    private String type;
    /**
     * 描述
     */
    private String remark;
    /**
     * 附件
     */
    private String accessory;
    /**
     * 删除标记
     */
    private Boolean isDelete;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 修改日期
     */
    private java.util.Date modifyDate;
    /**
     * 图片标识uuid
     */
    private String uuid;
    /**
     * uuid集合
     */
    private List<String> uuids;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 图片返回时的真实路径
     */
    private String picUrl;

    private String typeNames;

    /**
     * 传输症状类型  实体表中不存在！
     */
    private List<JcGcItemPojo> healthTypes;

    /**
     * 接收教师的id
     */
    private Integer teacherId;
    /**
     * 接收班级名称
     */
    private String teamName;
    /**
     * 接收学生姓名
     */
    private String stuName;


    public StudentHealthArchivePojo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getUuids() {
        return uuids;
    }

    public void setUuids(List<String> uuids) {
        this.uuids = uuids;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
    }

    public List<JcGcItemPojo> getHealthTypes() {
        return healthTypes;
    }

    public void setHealthTypes(List<JcGcItemPojo> healthTypes) {
        this.healthTypes = healthTypes;
    }
}
