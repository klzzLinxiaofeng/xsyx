package com.xunyunedu.resource.dao;

import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.resource.condition.ResResourceCondition;
import com.xunyunedu.resource.pojo.ResResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author edison
 */
public interface ResResourceDao {

    List<ResResource> all();

    List<ResResource> selectByCondition(ResResourceCondition condition);

    ResResource selectById(Integer id);

    Integer insert(ResResource resResource);

    long selectUniqueResultSql(@Param("sql") String sql);

    int updateBySql(@Param("sql") String sql);

    List<Map<String,Object>> selectComment( Integer resId);

}
