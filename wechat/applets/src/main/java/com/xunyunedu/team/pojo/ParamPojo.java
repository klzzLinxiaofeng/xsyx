package com.xunyunedu.team.pojo;

import java.util.List;

/**
 * 请求参数
 *
 * @author: yhc
 * @Date: 2021/1/13 10:01
 * @Description:
 */
public class ParamPojo {
    /**
     * 1：统考 0：平时
     */
    private String type;


    private List<String> types;


    /**
     * 科目code 为空就是查询所有
     */
    private String code;

    /**
     * 班级id
     */
    private Integer teamId;

    private String termCode;


    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学生id
     */
    private Integer stuId;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
