package com.xunyunedu.wareHousing.service.impl;

import com.xunyunedu.wareHousing.dao.WareHousingDao;
import com.xunyunedu.wareHousing.service.WareHousingService;
import com.xunyunedu.wareHousing.vo.ShenHe;
import com.xunyunedu.wareHousing.vo.WareHousing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareHousingServiceImpl implements WareHousingService {
    @Autowired
    private WareHousingDao wareHousingDao;
    @Override
    public List<WareHousing> findByAll(Integer userId) {
        return wareHousingDao.findByAll(userId);
    }
    @Override
    public WareHousing findById(Integer id){
        return wareHousingDao.findById(id);
    }

    @Override
    public Integer create(WareHousing wareHousing) {
      Integer num=wareHousingDao.create(wareHousing);
        if(num>0){
            ShenHe shenHe=new ShenHe();
            shenHe.setTitle(wareHousing.getShenqingName()+"的物资申请");
            shenHe.setReceiverId(wareHousing.getShenheId());
            shenHe.setApplicantName(wareHousing.getShenqingName());
            shenHe.setApplyExplain(wareHousing.getBeizhu());
            shenHe.setDataId(wareHousing.getId());
            shenHe.setDataIdType("wzsq");
            return wareHousingDao.createshenhe(shenHe);
        } else{
             return num;
        }
    }

    @Override
    public Integer update(WareHousing wareHousing) {
        return wareHousingDao.update(wareHousing);
    }

    @Override
    public Integer createShenhe(ShenHe shenHe) {
        return  wareHousingDao.createshenhe(shenHe);
    }
}
