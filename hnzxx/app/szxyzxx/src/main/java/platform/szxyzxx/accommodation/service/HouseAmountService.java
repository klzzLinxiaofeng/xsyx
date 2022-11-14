package platform.szxyzxx.accommodation.service;

import platform.szxyzxx.accommodation.pojo.HouseAmount;

import java.util.List;

public interface HouseAmountService {
    List<HouseAmount> findByAll();
    Integer create(HouseAmount houseAmount);
    Integer update(HouseAmount houseAmount);
}
