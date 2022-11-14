package com.xunyunedu.wareHousing.dao;

import com.xunyunedu.wareHousing.vo.ShenHe;
import com.xunyunedu.wareHousing.vo.WareHousing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WareHousingDao {
     List<WareHousing> findByAll(@Param("userId") Integer userId);
     WareHousing findById(@Param("id") Integer id);
     Integer create(@Param("wareHousing") WareHousing wareHousing);
     Integer createshenhe(@Param("shenHe") ShenHe shenHe);
     Integer update(@Param("wareHousing") WareHousing wareHousing);
}
