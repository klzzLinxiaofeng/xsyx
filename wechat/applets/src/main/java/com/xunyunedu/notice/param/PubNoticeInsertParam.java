package com.xunyunedu.notice.param;

import lombok.Data;

import java.util.List;

@Data
public class PubNoticeInsertParam {

    /**
     * 通知标题
     */
    private String title;
    /**
     * 通知内容
     */
    private String content;
    /**
     * 通知附件uuid列表
     */
    private List<String> fileUuidList;
    /**
     * 通知类型（班级通知:team,部门通知:dept,个人通知:person）
     */
    private String receiverType;


    /**
     * 通知发送对象列表（部门：pj.dept,个人：pj.person，班级：pj.team）
     */
    private List<String> targetTypeList;
    /**
     * 发起部门名称，targetTypeList包含dept时必填
     */
    private String appKey;

    /**
     * 部门id列表，targetTypeList包含“pj.dept”时必填
     */
    private List<Integer> deptIdList;
    /**
     * 班级id
     */
    private Integer teamId;
    /**
     * 老师id列表
     */
    private List<Integer> teacherIdList;

    /**
     * 当前用户id
     */
    private Integer posterId;
    /**
     * 当前用户姓名
     */
    private String posterName;

    /**
     * 是否需要回复
     */
    private Boolean isReply;

}
