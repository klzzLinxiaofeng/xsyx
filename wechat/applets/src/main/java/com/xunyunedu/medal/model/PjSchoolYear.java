package com.xunyunedu.medal.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
* @Description: 学期类
* @Param: * @param null
* @return:
* @Author: cmb
* @Date: 2020/10/15
*/
public class PjSchoolYear implements Serializable {
    /**
     * 主健ID
     */
    private Integer id;

    /**
     * 所在学校
     */
    @NotNull(message = "学校id不能为空")
    private Integer schoolId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 学年的年份：用于取代ID做表关联，如果2013-2014则记录2013
     */
    private String year;

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

    private Date createDate;

    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "PjSchoolYear{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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