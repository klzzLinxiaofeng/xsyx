package com.xunyunedu.notice.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author edison
 */
@Data
public class PubNotice {

    Integer id;

    /**
     * 通知的uuid
     */
    String uuid;

    /**
     * yh_app_edition.key
     */
    String appKey;

    /**
     * 通知标题
     */
    String title;

    /**
     * 发送者ID
     */
    Integer posterId;

    /**
     * 发送者
     */
    String posterName;

    /**
     * 定时发送的时间
     */
    Date postTime;

    /**
     * 接收类型
     */
    String receiverType;

    /**
     * 接收者姓名
     */
    String receiverName;

    /**
     * 通知内容
     */
    String content;

    /**
     * 发送的总人数
     */
    Integer userCount;

    /**
     * 通知触发时间
     */
    Date startTime;

    /**
     * 通知结束时间
     */
    Date finishTime;

    /**
     * 阅读人数
     */
    Integer readCount;

    /**
     * 创建时间
     */
    Date createDate;

    /**
     * 修改时间
     */
    Date modifyDate;

    /**
     * 删除标志
     */
    Boolean isDeleted;

    /**
     * 是否需要回复
     */
    Boolean isReply;

    /**
     * 是否已读
     */
    Boolean read;

    private String textContent;

    List<PubNoticeFile> noticeFiles;
}
