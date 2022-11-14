package com.xunyunedu.student.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 微课类型
 *
 *  @author: yhc
 *  @Date: 2020/12/12 9:57
 *  @Description:
 */
public class MicroTypePojo implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 对应的学校id
     */
    private Integer schoolId;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 提交时间
     */
    private Date createdAt;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

