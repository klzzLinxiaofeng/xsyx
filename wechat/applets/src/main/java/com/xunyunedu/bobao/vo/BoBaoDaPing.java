package com.xunyunedu.bobao.vo;

import java.util.Date;

public class BoBaoDaPing {
    private Integer id;
    private String gradeIds;
    private String gradeNames;
    private String boBaoHao;
    private String name;
    private Date createTime;
    private Date modiyTime;
    private String schoolYear;
    private String schoolTrem;
    private String schoolId;


    public BoBaoDaPing(){}

    public BoBaoDaPing(Integer id, String gradeIds, String gradeNames, String boBaoHao, String name, Date createTime, Date modiyTime, String schoolYear, String schoolTrem, String schoolId) {
        this.id = id;
        this.gradeIds = gradeIds;
        this.gradeNames = gradeNames;
        this.boBaoHao = boBaoHao;
        this.name = name;
        this.createTime = createTime;
        this.modiyTime = modiyTime;
        this.schoolYear = schoolYear;
        this.schoolTrem = schoolTrem;
        this.schoolId = schoolId;
    }

    public String getBoBaoHao() {
        return boBaoHao;
    }

    public void setBoBaoHao(String boBaoHao) {
        this.boBaoHao = boBaoHao;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGradeIds() {
        return gradeIds;
    }

    public void setGradeIds(String gradeIds) {
        this.gradeIds = gradeIds;
    }

    public String getGradeNames() {
        return gradeNames;
    }

    public void setGradeNames(String gradeNames) {
        this.gradeNames = gradeNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }
}

