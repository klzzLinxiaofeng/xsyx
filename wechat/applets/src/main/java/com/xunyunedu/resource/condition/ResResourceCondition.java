package com.xunyunedu.resource.condition;

import lombok.Data;

/**
 * 资源查询条件类
 * @author edison
 */
@Data
public class ResResourceCondition {


    /**
     * 模糊搜索名称
     */
    String title;

    /**
     * 资源/文件夹类型,只有isPersonal为false才生效（2：课件，3：作业，4：试卷，8：付费）
     */
    Integer resType;

    /**
     * 是否个人资源（true:查询个人资源，false:查询共享（校本资源））
     */
    Boolean isPersonal;
    /**
     * 是否查询已收藏,isPersonal为false生效（true：查询已收藏，空/false：不做筛选）
     */
    Boolean isFav;
    /**
     * 当前用户id，如果isPersonal/isFav为true，则此参数必填
     */
    Integer userId;


}
