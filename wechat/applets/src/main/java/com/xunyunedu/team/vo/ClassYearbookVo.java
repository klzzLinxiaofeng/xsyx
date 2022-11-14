package com.xunyunedu.team.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 班级纪念册
 *
 * @author: yhc
 * @Date: 2020/12/16 11:59
 * @Description:
 */
public class ClassYearbookVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级Id
     */
    private Integer teamId;

    /**
     * 学期
     */
    private String termCode;
    /**
     * 相册薄id
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
     * 教师id
     */
    private Integer teacherId;

    /**
     * 封面uuid
     */
    private String uuid;

    /**
     * 当前相册的照片
     */
    private List<String> uuids;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getUuids() {
        return uuids;
    }

    public void setUuids(List<String> uuids) {
        this.uuids = uuids;
    }
}

