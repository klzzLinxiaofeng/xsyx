package platform.szxyzxx.studentworks.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.studentworks.vo.StudentZuoPin;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/10 14:24
 * @Version 1.0
 */
public interface StudentZuoPinDao {
    public List<StudentZuoPin> findByAll(String schoolYear, String schoolTrem, Integer gardeId, Integer teamId, String studentName, Page page);
    public StudentZuoPin findById(Integer id);
}
