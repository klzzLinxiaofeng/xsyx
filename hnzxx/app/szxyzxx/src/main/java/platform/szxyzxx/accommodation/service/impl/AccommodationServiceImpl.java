package platform.szxyzxx.accommodation.service.impl;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.accommodation.dao.AccommodationDao;
import platform.szxyzxx.accommodation.dao.HouseAmountDao;
import platform.szxyzxx.accommodation.pojo.Accommodation;
import platform.szxyzxx.accommodation.pojo.HouseAmount;
import platform.szxyzxx.accommodation.pojo.SettlementAmount;
import platform.szxyzxx.accommodation.service.AccommodationService;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    @Autowired
    private AccommodationDao accommodationDao;
    @Autowired
    private HouseAmountDao houseAmountDao;
    @Override
    public List<Accommodation> findByAll(String teacherName, String fangshihao, String startDate, String endDate, Page page) {
        return accommodationDao.findByAll(teacherName,fangshihao,startDate,endDate,page);
    }

    @Override
    public Accommodation findById(Integer id) {
        return accommodationDao.findById(id);
    }

    @Override
    public Integer create(Accommodation accommodation) {
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
                if(accommodation.getHotWater()!=null && !accommodation.getHotWater().equals("")){
                    Double dd=accommodation.getHotWater()*Double.parseDouble(aa.getJine());
                    settlementAmount.setHotShuiJine(Double.parseDouble(String.format("%.2f", dd)));
                }

            }
            if(aa.getId()==1){
                if(accommodation.getColdWater()!=null && !accommodation.getColdWater().equals("")){
                    Double dd=accommodation.getColdWater()*Double.parseDouble(aa.getJine());
                    settlementAmount.setLenShuiJine(Double.parseDouble(String.format("%.2f", dd)));
                }
            }
            if(aa.getId()==3){
                if(accommodation.getElectricity()!=null && !accommodation.getElectricity().equals("")) {
                    Double dd = accommodation.getElectricity() * Double.parseDouble(aa.getJine());
                    settlementAmount.setDianJine(Double.parseDouble(String.format("%.2f", dd)));
                }
            }
        }
            houseAmountDao.createSettlement(settlementAmount);
        return num;
    }

    @Override
    public Integer update(Accommodation accommodation) {
        Integer num= accommodationDao.update(accommodation);
        List<HouseAmount> houseAmounts=houseAmountDao.findByAll();
        SettlementAmount settlementAmount=new SettlementAmount();
        settlementAmount.setAccommodationId(accommodation.getId());
        for(HouseAmount aa:houseAmounts){
            if(aa.getId()==2){
                if(accommodation.getArea()!=null && !accommodation.getArea().equals("")) {
                    settlementAmount.setZhufangJine(Double.parseDouble(accommodation.getArea()) * Double.parseDouble(aa.getJine()));

                }
            }
            if(aa.getId()==4){
                if(accommodation.getHotWater()!=null && !accommodation.getHotWater().equals("")) {
                    settlementAmount.setHotShuiJine(accommodation.getHotWater() * Double.parseDouble(aa.getJine()));

                }
            }
            if(aa.getId()==1){
                if(accommodation.getColdWater()!=null && !accommodation.getColdWater().equals("")) {
                    settlementAmount.setLenShuiJine(accommodation.getColdWater() * Double.parseDouble(aa.getJine()));
                }
            }
            if(aa.getId()==3){
                if(accommodation.getElectricity()!=null && !accommodation.getElectricity().equals("")) {

                    settlementAmount.setDianJine(accommodation.getElectricity() * Double.parseDouble(aa.getJine()));
                }
            }
        }
        houseAmountDao.updateSettlement(settlementAmount);
        return num;
    }

    @Override
    public Integer updateId(Integer id) {
        houseAmountDao.updatedelete(id);
        return accommodationDao.updateId(id);
    }
}
