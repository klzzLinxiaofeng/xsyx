package com.xunyunedu.schoolbus.pojo;

import java.io.Serializable;

/**
 * bus_parent_pick
 * @author 
 */
public class BusParentPick implements Serializable {
    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 接送日期
     */
    private String pickDate;

    /**
     * 方向：0：上学，1：放学
     */
    private Integer direction;

    private String place;

    private String stuEmpCard;

    private Integer createType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getPickDate() {
        return pickDate;
    }

    public void setPickDate(String pickDate) {
        this.pickDate = pickDate;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStuEmpCard() {
        return stuEmpCard;
    }

    public void setStuEmpCard(String stuEmpCard) {
        this.stuEmpCard = stuEmpCard;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }
}