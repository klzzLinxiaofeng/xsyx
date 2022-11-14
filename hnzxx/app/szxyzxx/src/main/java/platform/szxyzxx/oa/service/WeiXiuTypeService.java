package platform.szxyzxx.oa.service;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.szxyzxx.oa.vo.WeiXiuDaoChu;
import platform.szxyzxx.oa.vo.WeiXiuGong;
import platform.szxyzxx.oa.vo.WeiXiuType;

import java.util.List;

public interface WeiXiuTypeService {
    String create(WeiXiuType weiXiuType);
    String update(WeiXiuType weiXiuType);
    String updateDelete(Integer id);
    List<WeiXiuType> findByAll();
    WeiXiuType findById(Integer id);
    List<WeiXiuGong> findByWeiXiuGongAll(Integer atId);
    String createWeiXiuGong(WeiXiuGong weiXiuGong);
    String updateWeiXiuGongDelete(Integer id);
   List<WeiXiuDaoChu> findByDaoChu(String weixiuren,Integer typeId,String startTime,String endTime);
   List<Teacher> findByjiaoshiliebiao(String name , Page page);
}
