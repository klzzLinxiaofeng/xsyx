package com.xunyunedu.resource.param;

import lombok.Data;

@Data
public class ResourceCommentParam {
    /**
     * 评论用户id
     */
    private Integer userId;
    /**
     * 评论用户名
     */
    private String userName;
    /**
     * 资源id
     */
    private Integer resId;
    /**
     * 评论内容
     */
    private String commentContent;

}
