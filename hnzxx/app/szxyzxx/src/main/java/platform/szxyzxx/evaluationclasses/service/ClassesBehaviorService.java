package platform.szxyzxx.evaluationclasses.service;

import framework.generic.dao.Page;
import platform.szxyzxx.evaluationclasses.vo.ClassesBehavior;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface ClassesBehaviorService {
    List<ClassesBehavior> findByAll(QueryPojo queryPojo, Page page);
    List<ClassesBehavior> findByAllTongji(UserInfo userInfo,
                                         String schoolYear,
                                         String schoolTrem,
                                         Integer gradeId,
                                         Integer teamId,
                                         String stuName,
                                         Page page);
}
