package platform.szxyzxx.warehousing.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.warehousing.dao.WareHousingDao;
import platform.szxyzxx.warehousing.service.WareHousingService;
import platform.szxyzxx.warehousing.vo.ShenHe;
import platform.szxyzxx.warehousing.vo.WareHousing;

import java.util.List;

@Service
public class WareHousingServiceImpl implements WareHousingService {
    @Autowired
    private WareHousingDao wareHousingDao;
    @Override
    public List<WareHousing> findByAll(String shenqingrenName, Integer type, String name, Integer zhuangtai, String startTime, String endTime, Page page) {
        return wareHousingDao.findByAll(shenqingrenName,type,name,zhuangtai,startTime,endTime,page);
    }

    @Override
    public List<WareHousing> findByDaoAll(String shenqingrenName, Integer type, String name, Integer zhuangtai, String startTime, String endTime) {
        List<WareHousing> wareHousingList=wareHousingDao.findByDaoAll(shenqingrenName,type,name,zhuangtai,startTime,endTime);
        for(WareHousing aa:wareHousingList){
            if(aa.getType()==1){
                aa.setTypeName("办公用品");
            }
            if(aa.getType()==2){
                aa.setTypeName("书籍");
            }
            if(aa.getType()==3){
                aa.setTypeName("防疫物资");
            }
            if(aa.getType()==4){
                aa.setTypeName("其他");
            }
            if(aa.getZhuangtai()==1){
                if(aa.getIsGuihuan()==0){
                    aa.setZhuangTaiName("待归还");
                }else{
                    aa.setZhuangTaiName("无需归还");
                }
            }if(aa.getZhuangtai()==4){
                aa.setZhuangTaiName("已归还");
            }if(aa.getZhuangtai()==3){
                aa.setZhuangTaiName("归还待审批");
            }
        }
        return wareHousingList;
    }

    @Override
    public Integer create(WareHousing wareHousing) {
        Integer num=wareHousingDao.create(wareHousing);;
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
    public WareHousing findById(Integer id) {
        return wareHousingDao.findById(id);
    }

    @Override
    public Integer createShenhe(ShenHe shenHe) {
        return wareHousingDao.createshenhe(shenHe);
    }
}
