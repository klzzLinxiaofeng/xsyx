package com.xunyunedu.syllabus.dao;

import com.xunyunedu.syllabus.pojo.SyllabusTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 学校作息时间表(SyllabusTime)表数据库访问层
 *
 * @author sjz
 * @since 2021-04-07 11:13:24
 */
public interface SyllabusTimeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SyllabusTime  queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SyllabusTime> queryAllByLimit(@Param("offset") int offset,@Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param syllabusTime 实例对象
     * @return 对象列表
     */
    List<SyllabusTime> queryAll(SyllabusTime syllabusTime);

    /**
     * 新增数据
     *
     * @param syllabusTime 实例对象
     * @return 影响行数
     */
    int insert(SyllabusTime syllabusTime);

    /**
     * 修改数据
     *
     * @param syllabusTime 实例对象
     * @return 影响行数
     */
    int update(SyllabusTime syllabusTime);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

    SyllabusTime getBySchoolId(@Param("schoolId") Integer schoolId,@Param("termCode") String termCode);
}
