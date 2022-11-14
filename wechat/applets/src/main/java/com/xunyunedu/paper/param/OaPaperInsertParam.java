package com.xunyunedu.paper.param;

import lombok.Data;

import java.util.List;

@Data
public class OaPaperInsertParam {

    /**
     * 学校Id
     */
    private Integer schoolId;

    /**
     * 组类型
     */
    private String groupType;

    /**
     * 发布者userId
     */
    private Integer posterId;

    /**
     * 发布者姓名
     */
    private String posterName;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String remark;
    /**
     * 正文
     */
    private String content;

    /**
     * 附件文件ID
     */
    private String attachmentUuid;

    /**
     * 发送对象（0：全员 1：部门 2：个人）
     */
    private Integer receiverType;

    /**
     * 发送对象为个人时的老师userId列表
     */
    private List<Integer> teacherUserIds;
    /**
     * 发送对象为个人时的老师姓名列表
     */
    private List<String> teacherNames;
    /**
     * 发送对象为部门时的部门id列表
     */
    private List<Integer> departIds;


}
