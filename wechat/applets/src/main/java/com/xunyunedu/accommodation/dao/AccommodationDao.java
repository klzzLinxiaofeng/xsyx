package com.xunyunedu.accommodation.dao;

import com.xunyunedu.accommodation.pojo.Accommodation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccommodationDao {
    List<Accommodation> findByAll(@Param("userId") Integer userId);
    Accommodation findById(@Param("id") Integer id);
    Integer create(@Param("accommodation") Accommodation accommodation);
}
