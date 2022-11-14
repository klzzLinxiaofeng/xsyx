package platform.szxyzxx.wuyu.development.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.wuyu.development.dao.StudentDevelopmentDao;
import platform.szxyzxx.wuyu.development.service.StudentDevelopmentService;
import platform.szxyzxx.wuyu.development.vo.StudentDevelopment;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 17:14
 * @Version 1.0
 */
@Service
public class StudentDevelopmentServiceImpl implements StudentDevelopmentService {
    @Autowired
    private StudentDevelopmentDao studentDevelopmentDao;
    @Override
    public List<StudentDevelopment> findByAll(String xn, String xq, Integer nj, Integer bj, Integer zhibiaoId, String stuName, Page page) {
        return studentDevelopmentDao.findByAll(xn, xq, nj, bj, zhibiaoId, stuName, page);
    }
}
