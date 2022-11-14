package com.xunyunedu.resource.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收费资源
 * @author edison
 */
@Data
public class ResPaidResource {

    Integer id;

    /**
     * 标题
     */
    String title;

    /**
     * 备注
     */
    String description;

    /**
     * 文件关联的Id
     */
    String entityId;

    /**
     * 价格
     */
    BigDecimal price;

    /**
     * 做关联的Id
     */
    String uuid;

    /**
     * 上传者id
     */
    Integer userId;

    /**
     * 创建时间
     */
    Date createDate;


}
