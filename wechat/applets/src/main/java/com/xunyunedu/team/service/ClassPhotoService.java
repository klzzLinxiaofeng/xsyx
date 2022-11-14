package com.xunyunedu.team.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.team.pojo.ClassPhotoPojo;
import com.xunyunedu.team.pojo.ClassYearbookPojo;
import com.xunyunedu.team.vo.ClassYearbookVo;

import java.util.List;
import java.util.Map;

/**
 * 班级相册
 *
 * @author: yhc
 * @Date: 2020/12/16 14:11
 * @Description:
 */
public interface ClassPhotoService {

    void create(ClassYearbookVo classYearbookVo);

    void update(ClassYearbookVo classYearBookPojo);

    PageInfo<ClassYearbookPojo> findClassYearBook(ClassYearbookPojo classYearbookPojo, Integer pageNum, Integer pageSize);

    ClassYearbookPojo findClassYearBookById(ClassYearbookPojo classYearbookPojo);

    PageInfo<ClassPhotoPojo> getClassPhotoList(Integer id, Integer pageNum, Integer pageSize);

    void removeClassYearBook(ClassYearbookVo classYearBookPojo);

    PageInfo<ClassYearbookPojo> findStuClassYearBook(Integer stuId, Integer schoolId, Integer pageNum, Integer pageSize);
}
