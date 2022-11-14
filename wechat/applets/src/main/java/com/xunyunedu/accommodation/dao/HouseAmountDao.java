package com.xunyunedu.accommodation.dao;


import com.xunyunedu.accommodation.pojo.HouseAmount;
import com.xunyunedu.accommodation.pojo.SettlementAmount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseAmountDao {
  List<HouseAmount> findByAll();
  Integer createSettlement(@Param("settlementAmount") SettlementAmount settlementAmount);

}
