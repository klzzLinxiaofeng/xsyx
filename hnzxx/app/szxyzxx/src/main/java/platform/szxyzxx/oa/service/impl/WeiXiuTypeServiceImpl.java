package platform.szxyzxx.oa.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.szxyzxx.oa.dao.WeiXiuTypeDao;
import platform.szxyzxx.oa.service.WeiXiuTypeService;
import platform.szxyzxx.oa.vo.WeiXiuDaoChu;
import platform.szxyzxx.oa.vo.WeiXiuGong;
import platform.szxyzxx.oa.vo.WeiXiuType;

import java.util.List;

@Service
public class WeiXiuTypeServiceImpl implements WeiXiuTypeService {
    @Autowired
    private WeiXiuTypeDao weiXiuTypeDao;


    @Override
    public String create(WeiXiuType weiXiuType) {
        int num= weiXiuTypeDao.create(weiXiuType);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public String update(WeiXiuType weiXiuType) {
        int num= weiXiuTypeDao.update(weiXiuType);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public String updateDelete(Integer id) {
        int num= weiXiuTypeDao.updateDelete(id);
        if(num>0){
            weiXiuTypeDao.updateDeleteWeiXiuGong(id,null);
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public List<WeiXiuType> findByAll() {
        return weiXiuTypeDao.findByAll();
    }

    @Override
    public WeiXiuType findById(Integer id) {
        return weiXiuTypeDao.findById(id);
    }

    @Override
    public List<WeiXiuGong> findByWeiXiuGongAll(Integer atId) {
        return weiXiuTypeDao.findByWeiXiuGongAll(atId);
    }

    @Override
    public String createWeiXiuGong(WeiXiuGong weiXiuGong) {
        WeiXiuGong weiXiuGong1=weiXiuTypeDao.findByIds(weiXiuGong);
        if(weiXiuGong1!=null){
            return "success";
        }
        int num= weiXiuTypeDao.createWeiXiuGongDelete(weiXiuGong);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public String updateWeiXiuGongDelete(Integer id) {
        int num= weiXiuTypeDao.updateDeleteWeiXiuGong(null,id);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public List<WeiXiuDaoChu> findByDaoChu(String weixiuren, Integer typeId,String startTime,String endTime) {
        return weiXiuTypeDao.findByDaoChu(weixiuren,typeId, startTime,endTime);
    }

    @Override
    public List<Teacher> findByjiaoshiliebiao(String name, Page page) {
        return weiXiuTypeDao.findByjiaoshiliebiao(name,page);
    }
}
