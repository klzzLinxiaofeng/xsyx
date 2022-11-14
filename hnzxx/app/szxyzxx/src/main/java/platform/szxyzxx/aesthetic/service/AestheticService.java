package platform.szxyzxx.aesthetic.service;

import framework.generic.dao.Page;
import platform.szxyzxx.aesthetic.pojo.AestheticPojo;
import platform.szxyzxx.aesthetic.pojo.ErWerMa;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface AestheticService {

    List<AestheticPojo> findByAestheticPojo(UserInfo userInfo, Integer schoolId, String schoolYear, String schoolTrem, Page page);
    AestheticPojo findByAestheticPojoId(Integer id);
    String createOrupdate(UserInfo userInfo,AestheticPojo aestheticPojo);
    void createErweima(ErWerMa erWerMa);
    ErWerMa findByEeWeiMa(Integer studentId,String schoolYear);
}
