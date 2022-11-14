package platform.szxyzxx.ishangkelilu.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.ishangkelilu.pojo.StudyType;

import java.util.List;

public interface StudyTypeDao {
    List<StudyType> findByAll(String name, Page page);
    Integer create(StudyType studyType);
    Integer update(StudyType studyType);
    Integer updateShanChu(Integer id);
    StudyType findById(Integer id);
}
