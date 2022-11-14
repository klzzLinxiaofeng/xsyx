package platform.szxyzxx.evaluationclasses.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.evaluationclasses.vo.ClassesIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 12:43
 * @Version 1.0
 */
public interface ClassesIndicatorsDao {
    Integer create(ClassesIndicators classesIndicators);
    List<ClassesIndicators> findByAll(UserInfo userInfo, Page page);
    ClassesIndicators findById(Integer id);
    Integer update(ClassesIndicators classesIndicators);
    Integer updateDelete(Integer id);
}
