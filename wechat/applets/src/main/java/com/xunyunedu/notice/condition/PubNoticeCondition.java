package com.xunyunedu.notice.condition;

import lombok.Data;

import java.util.List;

/**
 * @author edison
 */
@Data
public class PubNoticeCondition {
    /**
     * 通知类型列表（"学校"：school,班级:team,部门:dept,个人:person）
     */
    private String receiverType;
    /**
     * 类容类型（0：与我相关的，1：我发布的）,默认0
     */
    private Integer contentType=0;
    /**
     * 当前用户id
     */
    private Integer userId;
    /**
     * 通知标题
     */
    private String title;
    /**
     * 是否已读（true:已读，false:未读），contentType为0生效
     */
    private Boolean read;

    private Integer id;

}
