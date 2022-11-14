package platform.szxyzxx.oa.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.szxyzxx.oa.vo.WeiXiuDaoChu;
import platform.szxyzxx.oa.vo.WeiXiuGong;
import platform.szxyzxx.oa.vo.WeiXiuType;

import java.util.List;

public interface WeiXiuTypeDao {
    Integer create(WeiXiuType weiXiuType);
    Integer update(WeiXiuType weiXiuType);
    Integer updateDelete(Integer id);
    Integer updateDeleteWeiXiuGong(Integer atId,Integer id);
    WeiXiuType findById(Integer id);
    List<WeiXiuType> findByAll();
    List<WeiXiuGong> findByWeiXiuGongAll(Integer atId);
    Integer createWeiXiuGongDelete(WeiXiuGong weiXiuGong);
    List<WeiXiuDaoChu> findByDaoChu(String faburen, Integer typeId,String startTime,String endTime);
    List<Teacher> findByjiaoshiliebiao(String name, Page page);
    WeiXiuGong findByIds(WeiXiuGong weiXiuGong);
}
