package com.xunyunedu.notice.pojo;

import com.google.zxing.aztec.detector.Detector;
import lombok.Data;

import java.util.Date;

@Data
public class PubNoticeFile {

    Integer id;

    /**
     * 通知id
     */
    Integer noticeId;

    /**
     * 文件UUID
     */
    String fileUuid;

    /**
     * 文件Url
     */
    String fileUrl;

    /**
     * 文件名称
     */
    String fileName;


    /**
     * 文件大小
     */
    String fileSize;

    /**
     * 創建時間
     */
    Date createDate;

    /**
     * 修改時間
     */
    Date modifyDate;

    /**
     * 是否刪除
     */
    Boolean isDeleted;

}
