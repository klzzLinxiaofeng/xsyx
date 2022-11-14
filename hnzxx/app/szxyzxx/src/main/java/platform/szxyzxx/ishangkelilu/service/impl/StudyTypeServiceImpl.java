package platform.szxyzxx.ishangkelilu.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.ishangkelilu.dao.StudyTypeDao;
import platform.szxyzxx.ishangkelilu.pojo.StudyType;
import platform.szxyzxx.ishangkelilu.service.StudyTypeService;

import java.util.List;


@Service
public class StudyTypeServiceImpl implements StudyTypeService {
    @Autowired
    private StudyTypeDao studyTypeDao;
    @Override
    public List<StudyType> findByAll(String name, Page page) {
        return studyTypeDao.findByAll(name,page);
    }

    @Override
    public StudyType findById(Integer id) {
        return studyTypeDao.findById(id);
    }

    @Override
    public Integer create(StudyType studyType) {
        return studyTypeDao.create(studyType);
    }

    @Override
    public Integer update(StudyType studyType) {
        return studyTypeDao.update(studyType);
    }

    @Override
    public Integer updateDelete(Integer id) {
        return studyTypeDao.updateShanChu(id);
    }
}
