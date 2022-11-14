package com.xunyunedu.medal.model;

import java.io.Serializable;
import java.util.Date;


/**
 * pj_school_term
 * @author cmb
 */

public class PjSchoolTerm implements Serializable {
    /**
     * 主健ID
     */
    private Integer id;

    /**
     * pj_school_year.id
     */
    private Integer schoolYearId;

    /**
     * 所在学校（冗余）
     */
    private Integer schoolId;

    /**
     * 名称
     */
    private String name;

    /**
     * 国标学期代码（P53），值5和值6是增补，学期代码：1=上/秋季。2=下/春季。3=夏季。5=寒假。6=暑假
     */
    private String gbCode;

    /**
     * 唯一识别代码，全表唯一由：学校ID+学年+国标学期码（48734-2015-1）
     */
    private String code;

    /**
     * 学年的开始年份（冗余）
     */
    private String schoolYear;

    /**
     * 开班时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date finishDate;

    /**
     * 删除标记
     */
    private Boolean isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "PjSchoolTerm{" +
                "id=" + id +
                ", schoolYearId=" + schoolYearId +
                ", schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", gbCode='" + gbCode + '\'' +
                ", code='" + code + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", beginDate=" + beginDate +
                ", finishDate=" + finishDate +
                ", isDelete=" + isDelete +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(Integer schoolYearId) {
        this.schoolYearId = schoolYearId;
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

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}