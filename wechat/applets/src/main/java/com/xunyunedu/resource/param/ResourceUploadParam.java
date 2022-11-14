package com.xunyunedu.resource.param;

import lombok.Data;

/**
 * 资源上传所需参数
 */
@Data
public class ResourceUploadParam {
    /**
     * 是否个人资源
     */
    private Boolean isPersonal;
    /**
     * 文件uuid
     */
    private String fileUuid;
    /**
     * 当前用户id
     */
    private Integer userId;
    /**
     * 当前用户名
     */
    private String userName;
    /**
     * 当为非个人资源时，需要传资源类型（2课件 3 作业 4试卷  8付费）
     */
    private Integer resType;


}
