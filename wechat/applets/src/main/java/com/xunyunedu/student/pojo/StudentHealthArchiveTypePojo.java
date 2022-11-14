package com.xunyunedu.student.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentHealthArchiveTypePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer studentHealthId;

    private Integer healthType;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentHealthArchiveTypePojo() {
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

    public Integer getStudentHealthId() {
        return studentHealthId;
    }

    public void setStudentHealthId(Integer studentHealthId) {
        this.studentHealthId = studentHealthId;
    }

    public Integer getHealthType() {
        return healthType;
    }

    public void setHealthType(Integer healthType) {
        this.healthType = healthType;
    }
}
