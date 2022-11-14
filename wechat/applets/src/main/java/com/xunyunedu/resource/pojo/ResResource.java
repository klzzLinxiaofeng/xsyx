package com.xunyunedu.resource.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author edison
 */
@Data
public class ResResource {

    Integer id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    Date createDate;

    /**
     * 修改时间
     */
    Date modifyDate;

    /**
     * 标题
     */
    String title;

    /**
     * 备注
     */
    String description;

    /**
     * 文件uuid
     */
    String uuid;

    /**
     *  资源类型（2课件 3 作业 4试卷  8教案）
     */
    Integer resType;

    /**
     * 资源大小，精确到M
     */
    String objectId;

    /**
     * 收藏次数
     */
    Integer favCount;

    /**
     * 点赞次数
     */
    Integer likeCount;

    /**
     * 评价次数
     */
    Integer commentCount;

    /**
     * 资源访问路径
     */
    String thumbnailUrl;

    /**
     * 图标类型
     */
    Integer iconType;

    /**
     * 所在资源库
     */
    String libraryId;

    /**
     * 0 校本资源上传成功  2 校本资源共享成功 6 校本资源共享中 5 校本资源共享失败 7 个人资源共享成功 4 个人资源未共享 8 个人资源未共享
     */
    String verify;

    /**
     * 用户id
     */
    Integer userId;

    /**
     * 用户名称
     */
    String userName;

    /**
     * 是否为个人资源
     */
    Boolean isPersonal;

    Boolean isDeleted;

    /**
     * 下载次数
     */
    Integer downloadCount;

    /**
     * 点击次数
     */
    Integer clickCount;

    /**
     * 资源所需积分
     */
    Integer integral;

    /**
     * 资源评分
     */
    Integer score;

    /**
     * 资源评分次数
     */
    Float scoringCount;





}
