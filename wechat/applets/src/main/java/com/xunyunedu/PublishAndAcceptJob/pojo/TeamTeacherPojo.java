package com.xunyunedu.PublishAndAcceptJob.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 老师发布作业
 * 查询学科实体类
 * @author lee
 */
@Data
public class TeamTeacherPojo implements Serializable {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 老师id
     */
    private Integer teacherId;

    /**
     * 所在班级id
     */
    private String teamId;

    /**
     * 老师名称
     */
    private String name;

    /**
     * 学科名称
     */
    private String subjectName;

    /**
     * 删除标识
     */
    private Double isDelete;

    /**
     * 创建日期
     */


    private Date createDate;
    /**
     * 更新时间
     */

    private Date modifyDate;

    /**
     * 科目代号
     */
    private String subjectCode;

    /**
     * 班级id
     */
    private Integer tId;

    /**
     * 封装班级信息
     */
    private TeamPojo teamPojo;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public TeamPojo getTeamPojo() {
        return teamPojo;
    }

    public void setTeamPojo(TeamPojo teamPojo) {
        this.teamPojo = teamPojo;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Double isDelete) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
