package com.xunyunedu.accommodation.service.impl;


import com.xunyunedu.accommodation.dao.AccommodationDao;
import com.xunyunedu.accommodation.dao.HouseAmountDao;
import com.xunyunedu.accommodation.pojo.Accommodation;
import com.xunyunedu.accommodation.pojo.HouseAmount;
import com.xunyunedu.accommodation.pojo.SettlementAmount;
import com.xunyunedu.accommodation.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    @Autowired
    private AccommodationDao accommodationDao;
    @Autowired
    private HouseAmountDao houseAmountDao;
    @Override
    public List<Accommodation> findByAll(Integer userId) {
        return accommodationDao.findByAll(userId);
    }

    @Override
    public Accommodation findById(Integer id) {
        return accommodationDao.findById(id);
    }

    @Override
    public Integer create(Accommodation accommodation) {
        //价格
        List<HouseAmount> houseAmounts=houseAmountDao.findByAll();
        int num=accommodationDao.create(accommodation);
        SettlementAmount settlementAmount=new SettlementAmount();
        settlementAmount.setAccommodationId(accommodation.getId());
        for(HouseAmount aa:houseAmounts){
            if(aa.getId()==2){
                if(accommodation.getArea()!=null && !accommodation.getArea().equals("")) {
                    Double dd = Double.parseDouble(accommodation.getArea()) * Double.parseDouble(aa.getJine());
                    settlementAmount.setZhufangJine(Double.parseDouble(String.format("%.2f", dd)));
                }
            }
            if(aa.getId()==4){
                if(accommodation.getHotWater()!=null && !accommodation.getHotWater().equals("")) {
                    Double dd = accommodation.getHotWater() * Double.parseDouble(aa.getJine());
                    settlementAmount.setHotShuiJine(Double.parseDouble(String.format("%.2f", dd)));
                }
            }
            if(aa.getId()==1){
                if(accommodation.getColdWater()!=null && !accommodation.getColdWater().equals("")) {
                    Double dd = accommodation.getColdWater() * Double.parseDouble(aa.getJine());
                    settlementAmount.setLenShuiJine(Double.parseDouble(String.format("%.2f", dd)));
                }
            }
            if(aa.getId()==3){
                if( accommodation.getElectricity()!=null &&  !accommodation.getElectricity().equals("")) {
                    Double dd = accommodation.getElectricity() * Double.parseDouble(aa.getJine());
                    settlementAmount.setDianJine(Double.parseDouble(String.format("%.2f", dd)));
                }
            }
        }
            houseAmountDao.createSettlement(settlementAmount);
        return num;
    }
}
