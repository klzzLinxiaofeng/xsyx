package com.xunyunedu.student.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:20
 * @Description:
 */
public class MicroManagerPojo implements Serializable {
    private Integer id;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面uuid res_entity_file
     */
    private String coverUuid;

    /**
     * 封面完整url
     */
    private String coverUrl;

    /**
     * 视频uuid res_entity_file
     */
    private String videoUuid;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 课件uuid res_entity_file
     */
    private String classUuid;


    /**
     * 课件url
     */
    private String classUrl;


    /**
     * 简介
     */
    private String directory;

//    /**
//     * 主讲老师
//     */
//    private Integer teacherId;
//
//    /**
//     * 老师名称
//     */
//    private String teacherName;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date uploadDate;

    /**
     * 收藏数量
     */
    private Integer collect;

    /**
     * 点赞数量
     */
    private Integer thumbs;

    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 课件名称
     */
    private String coursewareName;

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
     * 获取学校id
     *
     * @return school_id - 学校id
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * 设置学校id
     *
     * @param schoolId 学校id
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取封面uuid res_entity_file
     *
     * @return cover_uuid - 封面uuid res_entity_file
     */
    public String getCoverUuid() {
        return coverUuid;
    }

    /**
     * 设置封面uuid res_entity_file
     *
     * @param coverUuid 封面uuid res_entity_file
     */
    public void setCoverUuid(String coverUuid) {
        this.coverUuid = coverUuid;
    }

    /**
     * 获取视频uuid res_entity_file
     *
     * @return video_uuid - 视频uuid res_entity_file
     */
    public String getVideoUuid() {
        return videoUuid;
    }

    /**
     * 设置视频uuid res_entity_file
     *
     * @param videoUuid 视频uuid res_entity_file
     */
    public void setVideoUuid(String videoUuid) {
        this.videoUuid = videoUuid;
    }

    /**
     * 获取课件uuid res_entity_file
     *
     * @return class_uuid - 课件uuid res_entity_file
     */
    public String getClassUuid() {
        return classUuid;
    }

    /**
     * 设置课件uuid res_entity_file
     *
     * @param classUuid 课件uuid res_entity_file
     */
    public void setClassUuid(String classUuid) {
        this.classUuid = classUuid;
    }

    /**
     * 获取简介
     *
     * @return directory - 简介
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * 设置简介
     *
     * @param directory 简介
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * 获取发布时间
     *
     * @return upload_date - 发布时间
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * 设置发布时间
     *
     * @param uploadDate 发布时间
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * 获取收藏数量
     *
     * @return collect - 收藏数量
     */
    public Integer getCollect() {
        return collect;
    }

    /**
     * 设置收藏数量
     *
     * @param collect 收藏数量
     */
    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    /**
     * 获取点赞数量
     *
     * @return thumbs - 点赞数量
     */
    public Integer getThumbs() {
        return thumbs;
    }

    /**
     * 设置点赞数量
     *
     * @param thumbs 点赞数量
     */
    public void setThumbs(Integer thumbs) {
        this.thumbs = thumbs;
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
     * 获取修改时间
     *
     * @return modify_date - 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getClassUrl() {
        return classUrl;
    }

    public void setClassUrl(String classUrl) {
        this.classUrl = classUrl;
    }

}