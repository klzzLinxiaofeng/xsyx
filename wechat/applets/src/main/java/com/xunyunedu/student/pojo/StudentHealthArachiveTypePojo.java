package com.xunyunedu.student.pojo;

import java.io.Serializable;

/**
 * @author Eternityhua
 * @create 2020-12-15 14:42
 */
public class StudentHealthArachiveTypePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer studentHealthId;

    private Integer healthType;

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

    @Override
    public String toString() {
        return "StudentHealthArachiveTypePojo{" +
                "id=" + id +
                ", studentHealthId=" + studentHealthId +
                ", healthType=" + healthType +
                '}';
    }
}
