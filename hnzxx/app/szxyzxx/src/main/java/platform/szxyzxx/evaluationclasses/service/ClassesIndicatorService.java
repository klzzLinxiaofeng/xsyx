package platform.szxyzxx.evaluationclasses.service;

import framework.generic.dao.Page;
import platform.szxyzxx.evaluationclasses.vo.ClassesIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
public interface ClassesIndicatorService {
    Integer create(ClassesIndicators classesIndicators);
    List<ClassesIndicators> findByAll(UserInfo userInfo, Page page);
    ClassesIndicators findById(Integer id);
    Integer update(ClassesIndicators classesIndicators);
    String updateDelete(String ids);
}
