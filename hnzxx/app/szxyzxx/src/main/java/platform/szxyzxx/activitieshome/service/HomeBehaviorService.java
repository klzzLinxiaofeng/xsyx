package platform.szxyzxx.activitieshome.service;

import framework.generic.dao.Page;
import platform.szxyzxx.activitieshome.vo.HomeBehavior;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface HomeBehaviorService {
    List<HomeBehavior> findByAll(QueryPojo queryPojo, Page page);
    List<HomeBehavior> findByAllTongji(UserInfo userInfo,
                                       String schoolYear,
                                       String schoolTrem,
                                       Integer gradeId,
                                       Integer teamId,
                                       String stuName,
                                       Page page);
}
