package com.xunyunedu.huiyi.dao;


import com.xunyunedu.huiyi.pojo.HuiYi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HuiYiDao {
    Integer create(@Param("huiYi")HuiYi huiYi);
    List<HuiYi> findByAll(@Param("id") Integer id, @Param("name") String name);
    Integer updateShenHe(@Param("id")Integer id,@Param("zhuangtai") Integer zhuangtai,@Param("text") String text);
    HuiYi findById(@Param("id") Integer id);
    void createshenhe(@Param("huiYi") HuiYi huiYi);
    List<HuiYi> findByTime(@Param("time") String time);
}
