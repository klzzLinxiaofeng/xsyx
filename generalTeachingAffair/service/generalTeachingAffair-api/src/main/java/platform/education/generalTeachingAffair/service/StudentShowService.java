package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.PerformancePojo;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/4/13 15:54
 *  @Description: 学生表现
 */
public interface StudentShowService {


    String delete(Integer id);

    PerformancePojo add(PerformancePojo publicClass);

    List<PerformancePojo> findSutBusByGroupCondition(PerformancePojo performancePojo, Page page);
}
