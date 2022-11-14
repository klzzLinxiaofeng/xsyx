package platform.szxyzxx.accommodation.dao;

import platform.szxyzxx.accommodation.pojo.HouseAmount;
import platform.szxyzxx.accommodation.pojo.SettlementAmount;

import java.util.List;

public interface HouseAmountDao {
  List<HouseAmount> findByAll();
  Integer create(HouseAmount houseAmount);
  Integer update(HouseAmount houseAmount);

  Integer createSettlement(SettlementAmount settlementAmount);
  Integer updateSettlement(SettlementAmount settlementAmount);
  Integer updatedelete(Integer id);

}
