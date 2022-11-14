package platform.szxyzxx.lifebehavior.service;

import framework.generic.dao.Page;
import platform.szxyzxx.lifebehavior.vo.LifeBehavior;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface LifeBehaviorService {
    List<LifeBehavior> findByAll(QueryPojo queryPojo, Page page);
    List<LifeBehavior> findByAllTongji(UserInfo userInfo,
                                       String schoolYear,
                                       String schoolTrem,
                                       Integer gradeId,
                                       Integer teamId,
                                       String stuName,
                                       Page page);
}
