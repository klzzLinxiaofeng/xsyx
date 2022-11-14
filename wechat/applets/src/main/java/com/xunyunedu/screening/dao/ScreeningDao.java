package com.xunyunedu.screening.dao;


import com.xunyunedu.screening.vo.Screening;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScreeningDao {
    List<Screening> findByAll(@Param("id") Integer id);
    Integer create(@Param("screening") Screening screening);

    Screening findById(@Param("id") Integer id);
}
