package com.xunyunedu.student.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 小程序微课评论管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:21
 * @Description:
 */
public class UserCommentsPojo implements Serializable {
    private Integer id;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 微课管理id pj_micro_manager id
     */
    private Integer microId;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private String name;

    /**
     * 评论内容
     */
    private String comments;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date commentsDate;

    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    /**
     * 0学生 1教师
     */
    private Integer userType;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取微课管理id pj_micro_manager id
     *
     * @return micro_id - 微课管理id pj_micro_manager id
     */
    public Integer getMicroId() {
        return microId;
    }

    /**
     * 设置微课管理id pj_micro_manager id
     *
     * @param microId 微课管理id pj_micro_manager id
     */
    public void setMicroId(Integer microId) {
        this.microId = microId;
    }

    /**
     * 获取评论内容
     *
     * @return comments - 评论内容
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置评论内容
     *
     * @param comments 评论内容
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 获取评论时间
     *
     * @return comments_date - 评论时间
     */
    public Date getCommentsDate() {
        return commentsDate;
    }

    /**
     * 设置评论时间
     *
     * @param commentsDate 评论时间
     */
    public void setCommentsDate(Date commentsDate) {
        this.commentsDate = commentsDate;
    }

    /**
     * @return is_deleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改日期
     *
     * @return modify_date - 修改日期
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改日期
     *
     * @param modifyDate 修改日期
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

}