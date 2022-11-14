package platform.szxyzxx.socialresponsibility.service;

import framework.generic.dao.Page;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.socialresponsibility.vo.SocialBehavior;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface SocialBehaviorService {
    List<SocialBehavior> findByAll(QueryPojo queryPojo, Page page);
    List<SocialBehavior> findByAllTongji(UserInfo userInfo,
                                         String schoolYear,
                                         String schoolTrem,
                                         Integer gradeId,
                                         Integer teamId,
                                         String stuName,
                                         Page page);
}
