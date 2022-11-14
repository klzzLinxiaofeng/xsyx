package com.xunyunedu.teacher.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 教师端审批记录
 *
 * @author: yhc
 * @Date: 2020/11/2 16:26
 * @Description:
 */
public class ApprovePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审批记录id
     */
    private Integer id;

    /**
     * 0请假、1报销、2外勤、3休假、3采购、5用章
     */
    private Integer type;
    /**
     * 是否为学生 1:是 0否
     */
    private Integer isStu;

    /**
     * 申请事由
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    /**
     * 申请人姓名
     */
    private String name;

    /**
     * 审核状态（1：审核中 2：已通过 3：已驳回）
     */
    private Integer indiaStatus;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 学校id
     */
    private Integer schoolId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsStu() {
        return isStu;
    }

    public void setIsStu(Integer isStu) {
        this.isStu = isStu;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndiaStatus() {
        return indiaStatus;
    }

    public void setIndiaStatus(Integer indiaStatus) {
        this.indiaStatus = indiaStatus;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "ApprovePojo{" +
                "id=" + id +
                ", type=" + type +
                ", isStu=" + isStu +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", name='" + name + '\'' +
                ", indiaStatus=" + indiaStatus +
                ", teacherId=" + teacherId +
                ", schoolId=" + schoolId +
                '}';
    }
}
