package platform.szxyzxx.evaluationclasses.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.evaluationclasses.dao.ClassesIndicatorsDao;
import platform.szxyzxx.evaluationclasses.service.ClassesIndicatorService;
import platform.szxyzxx.evaluationclasses.vo.ClassesIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
@Service
public class ClassesIndicatorServiceImpl implements ClassesIndicatorService {
    @Autowired
    private ClassesIndicatorsDao classesIndicatorsDao;

    @Override
    public Integer create(ClassesIndicators classesIndicators) {
        return classesIndicatorsDao.create(classesIndicators);
    }

    @Override
    public List<ClassesIndicators> findByAll(UserInfo userInfo, Page page) {
        return classesIndicatorsDao.findByAll(userInfo,page);
    }

    @Override
    public ClassesIndicators findById(Integer id) {
        return classesIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(ClassesIndicators classesIndicators) {
        return classesIndicatorsDao.update(classesIndicators);
    }

    @Override
    public String updateDelete(String ids) {

        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                classesIndicatorsDao.updateDelete(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }
}
