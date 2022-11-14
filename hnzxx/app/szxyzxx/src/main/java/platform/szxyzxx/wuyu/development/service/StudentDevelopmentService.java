package platform.szxyzxx.wuyu.development.service;

import framework.generic.dao.Page;
import platform.szxyzxx.wuyu.development.vo.StudentDevelopment;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 17:14
 * @Version 1.0
 */
public interface StudentDevelopmentService {
    List<StudentDevelopment> findByAll(String xn, String xq, Integer nj, Integer bj, Integer zhibiaoId, String stuName, Page page);
}
