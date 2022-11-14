package com.xunyunedu.team.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 班级纪念册
 *
 * @author: yhc
 * @Date: 2020/12/16 11:59
 * @Description:
 */
public class ClassYearbookPojo implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 名称
     */
    private String name;

    /**
     * 班级id
     */
    private Integer teamId;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 封面uuid res_entity_file
     */
    private String uuid;
    /**
     * 封面路径
     */
    private String httpUrl;

    /**
     *
     */
    private Integer isDelete;
    /**
     * 是否归档: 0:未归档 1:已归档
     */
    private Integer isArchiving;

    /**
     * 相册数量
     */
    private Integer total;

    /**
     * 学期id pj_school_term
     */
    private Integer termId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    /**
     * 当前相册的照片
     */
    private List<ClassPhotoPojo> classPhotoPojos;

    /**
     * 当前相册的照片
     */
    private List<String> uuids;

    public List<String> getUuids() {
        return uuids;
    }

    public void setUuids(List<String> uuids) {
        this.uuids = uuids;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public Integer getIsArchiving() {
        return isArchiving;
    }

    public void setIsArchiving(Integer isArchiving) {
        this.isArchiving = isArchiving;
    }

    public List<ClassPhotoPojo> getClassPhotoPojos() {
        return classPhotoPojos;
    }

    public void setClassPhotoPojos(List<ClassPhotoPojo> classPhotoPojos) {
        this.classPhotoPojos = classPhotoPojos;
    }

    private static final long serialVersionUID = 1L;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

