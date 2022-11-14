package com.xunyunedu.learning.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 学校课件
 * @author edison
 */
@Data
public class LdLearningDesign {

    Integer id;

    /**
     * 创建时间
     */
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
     * 描述
     */
    String description;

    /**
     * 做关联的uuid
     */
    String uuid;

    /**
     * 类型
     */
    String type;

    /**
     * 上传者id
     */
    Integer userId;

    /**
     * 微课属于哪个app
     */
    Integer appId;

    /**
     * 关联文件实体
     */
    String entityId;
}
