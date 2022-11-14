package com.xunyunedu.common.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/1/21 16:05
 * @Description:
 */
public interface CommonDao {

    /**
     * 查询jc_gc_item表，下拉框使用
     *
     * @param code
     * @return
     */
    List<Map<String, Integer>> getValueByCode(@Param("code") String code);

    /**
     * 查询pj_job_control按钮开关
     *
     * @param name
     * @return
     */
    Boolean getJobControlStatus(@Param("name") String name);
}
