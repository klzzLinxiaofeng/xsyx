package com.xunyunedu.student.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/12 16:52
 * @Description: 班级学生信息
 */
public class TeamDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer teamId;

    private String teamName;

    private List<StudentPojo> pojoList;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<StudentPojo> getPojoList() {
        return pojoList;
    }

    public void setPojoList(List<StudentPojo> pojoList) {
        this.pojoList = pojoList;
    }
}
