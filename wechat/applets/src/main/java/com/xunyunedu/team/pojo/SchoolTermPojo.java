package com.xunyunedu.team.pojo;

import java.io.Serializable;

/**
 * 学期
 *
 * @author lee
 */
public class SchoolTermPojo implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 主健ID
     */
    private Integer id;
    /**
     * 唯一识别码
     */
    private String code;
    /**
     * 所属学年
     */
    private Integer schoolYearId;
    /**
     * 所在学校
     */
    private Integer schoolId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学年的开始年份。用于取代ID做关联。如果2013-2014则记录2013
     */
    private String schoolYear;
    /**
     * 开班时间
     */
    private java.util.Date beginDate;
    /**
     * 结束时间
     */
    private java.util.Date finishDate;
    /**
     * 删除标记
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private java.util.Date createDate;
    /**
     * 修改时间
     */
    private java.util.Date modifyDate;

    /**
     * 国标学期代码 1=上/秋季。2=下/春季。3=夏季。5=寒假。6=暑假
     */
    private String gbCode;

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
    }


    /**
     * 获取主健ID
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置主健ID
     *
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学期代码：1=上/秋季。2=下/春季。3=夏季。5=寒假。6=暑假
     *
     * @return java.lang.String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置学期代码：1=上/秋季。2=下/春季。3=夏季。5=寒假。6=暑假
     *
     * @type java.lang.String
     */
    public void setCode(String code) {
        this.code = code;
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

    /**
     * 获取姓名
     *
     * @return java.lang.String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置姓名
     *
     * @type java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取学年的开始年份。用于取代ID做关联。如果2013-2014则记录2013
     *
     * @return java.lang.String
     */
    public String getSchoolYear() {
        return this.schoolYear;
    }

    /**
     * 设置学年的开始年份。用于取代ID做关联。如果2013-2014则记录2013
     *
     * @type java.lang.String
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    /**
     * 获取开班时间
     *
     * @return java.util.Date
     */
    public java.util.Date getBeginDate() {
        return this.beginDate;
    }

    /**
     * 设置开班时间
     *
     * @type java.util.Date
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束时间
     *
     * @return java.util.Date
     */
    public java.util.Date getFinishDate() {
        return this.finishDate;
    }

    /**
     * 设置结束时间
     *
     * @type java.util.Date
     */
    public void setFinishDate(java.util.Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 获取删除标记
     *
     * @return java.lang.Boolean
     */
    public Boolean getIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置删除标记
     *
     * @type java.lang.Boolean
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建时间
     *
     * @return java.util.Date
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置创建时间
     *
     * @type java.util.Date
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     *
     * @return java.util.Date
     */
    public java.util.Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @type java.util.Date
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
