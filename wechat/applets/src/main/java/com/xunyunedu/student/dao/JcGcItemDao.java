package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.JcGcItemPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JcGcItemDao {

    List<JcGcItemPojo> selectByIds(@Param("ids") List<Integer> ids);

    List<JcGcItemPojo> getAllHealthType();
}
