package com.xunyunedu.student.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 小程序微课收藏和点赞管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:21
 * @Description:
 */
public class CollectionCommentsPojo implements Serializable {
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
     * 0学生 1教师
     */
    private Integer userType;


    /**
     * 收藏: 0:（已收藏/收藏） 1:（未收藏/取消收藏）
     */
    private Integer collectionStatus;

    /**
     * 点赞: 0:(已点赞/点赞) 1:(未点赞/取消点赞)
     */
    private Integer likeStatus;

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
     * 收藏数量
     */
    private Integer collect;

    /**
     * 点赞数量
     */
    private Integer thumbs;

    public Integer getThumbs() {
        return thumbs;
    }

    public void setThumbs(Integer thumbs) {
        this.thumbs = thumbs;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Integer getMicroId() {
        return microId;
    }


    public void setMicroId(Integer microId) {
        this.microId = microId;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getCollectionStatus() {
        return collectionStatus;
    }


    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }


    public Integer getLikeStatus() {
        return likeStatus;
    }


    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
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