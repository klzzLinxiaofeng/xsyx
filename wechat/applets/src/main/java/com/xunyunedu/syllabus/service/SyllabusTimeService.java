package com.xunyunedu.syllabus.service;

import com.xunyunedu.syllabus.pojo.SyllabusTime;

import java.util.List;


/**
 * 学校作息时间表(SyllabusTime)表服务接口
 *
 * @author sjz
 * @since 2021-04-07 11:13:24
 */
public interface SyllabusTimeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SyllabusTime queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SyllabusTime> queryAllByLimit(int offset,int limit);

    /**
     * 新增数据
     *
     * @param syllabusTime 实例对象
     * @return 实例对象
     */
    SyllabusTime insert(SyllabusTime syllabusTime);

    /**
     * 修改数据
     *
     * @param syllabusTime 实例对象
     * @return 实例对象
     */
    SyllabusTime update(SyllabusTime syllabusTime);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    SyllabusTime getBySchoolId(Integer schoolId,String termCode);
}
