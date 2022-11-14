package com.xunyunedu.wareHousing.service;

import com.xunyunedu.wareHousing.vo.ShenHe;
import com.xunyunedu.wareHousing.vo.WareHousing;

import java.util.List;

public interface WareHousingService {
    List<WareHousing> findByAll(Integer userId);
    WareHousing findById(Integer id);
    Integer create(WareHousing wareHousing);
    Integer update(WareHousing wareHousing);
    Integer createShenhe(ShenHe shenHe);
}
