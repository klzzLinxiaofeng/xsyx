package com.xunyunedu.personinfor.pojo;

public class KeXuanKe {
    /*
    *主键
    * */
    private Integer id;
    /*
     *年级id
     * */
    private Integer gradeId;
    /*
     *是否开启选课0，1
     * */
    private Integer zhuantai;
    /*
     *学年
     * */
    private String schoolYear;
    /*
     *学年
     * */
    private String schoolTrem;
    public KeXuanKe(){}
    public KeXuanKe(Integer id, Integer gradeId, Integer zhuantai, String schoolYear, String schoolTrem) {
        this.id = id;
        this.gradeId = gradeId;
        this.zhuantai = zhuantai;
        this.schoolYear = schoolYear;
        this.schoolTrem = schoolTrem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getZhuantai() {
        return zhuantai;
    }

    public void setZhuantai(Integer zhuantai) {
        this.zhuantai = zhuantai;
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
}
