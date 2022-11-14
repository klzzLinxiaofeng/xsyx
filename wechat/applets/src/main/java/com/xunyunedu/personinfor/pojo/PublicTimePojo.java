package com.xunyunedu.personinfor.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 选课时间
 *
 * @author: yhc
 * @Date: 2020/11/18 20:53
 * @Description:
 */
public class PublicTimePojo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 课程ID
     */
    private Integer id;
    /**
     * 上课时间
     */
    private String classTime;

    private Integer schoolId;

    /**
     * 作废标记
     */
    private Integer isDelete;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 课程id
     */
    private Integer cid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
}