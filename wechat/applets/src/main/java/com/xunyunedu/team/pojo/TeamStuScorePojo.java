package com.xunyunedu.team.pojo;

import java.io.Serializable;

/**
 * 班级学生列表
 *
 * @author: yhc
 * @Date: 2020/12/21 17:07
 * @Description:
 */
public class TeamStuScorePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生成绩
     */
    private String score;

    /**
     * 学生姓名
     */
    private String name;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
