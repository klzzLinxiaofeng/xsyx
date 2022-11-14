package com.xunyunedu.PublishAndAcceptJob.pojo;

import java.io.Serializable;

/**
 * 老师发布作业
 * 班级实体类
 * @author lee
 */
public class TeamPojo implements Serializable {

    /**
     * 班级id
     */
    private Integer id;

    /**
     * 班级名称
     */
    private String name;

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
}
