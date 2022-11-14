package com.xunyunedu.sqcomment.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 评论实体
 * @author edison
 */
@Data
public class SqComment {

    Integer id;

    /**
     * 宿主APP
     */
    Integer appId;

    /**
     * 被评论的记录ID
     */
    String objectId;

    /**
     * 评论类型
     */
    String objectType;

    /**
     * 回复的评论id
     */
    Integer parentId;

    /**
     * 发表者用户id
     */
    Integer posterId;

    /**
     * 发表人的姓名或昵称
     */
    String postName;

    /**
     * 发表时间
     */
    Date postTime;

    /**
     * 赞同数
     */
    Integer agrees;

    /**
     * 反对数
     */
    Integer disagrees;

    /**
     * 评论内容
     */
    String content;

    /**
     * 创建时间
     */
    Date createDate;

    /**
     * 修改时间
     */
    Long modifyDate;

    /**
     * 资源Id
     */
    Integer resourceId;
}
