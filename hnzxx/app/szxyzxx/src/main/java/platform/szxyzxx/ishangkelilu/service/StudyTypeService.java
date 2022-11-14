package platform.szxyzxx.ishangkelilu.service;

import framework.generic.dao.Page;
import platform.szxyzxx.ishangkelilu.pojo.StudyType;

import java.util.List;

public interface StudyTypeService {
    public List<StudyType> findByAll(String name, Page page);
    public StudyType findById(Integer id);
    Integer create(StudyType studyType);
    Integer update(StudyType studyType);
    Integer updateDelete(Integer id);
}
