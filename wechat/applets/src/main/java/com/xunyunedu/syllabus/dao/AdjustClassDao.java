package com.xunyunedu.syllabus.dao;

import com.xunyunedu.syllabus.pojo.AdjustClassPojo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * 调课申请表(OaAdjustClass)表数据库访问层
 *
 * @author sjz
 * @since 2021-03-30 14:20:29
 */
public interface AdjustClassDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AdjustClassPojo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdjustClassPojo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oaAdjustClass 实例对象
     * @return 对象列表
     */
    List<AdjustClassPojo> queryAll(AdjustClassPojo oaAdjustClass);

    /**
     * 新增数据
     *
     * @param oaAdjustClass 实例对象
     * @return 影响行数
     */
    int insert(AdjustClassPojo oaAdjustClass);

    /**
     * 修改数据
     *
     * @param adjustClass 实例对象
     * @return 影响行数
     */
    int update(AdjustClassPojo adjustClass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<AdjustClassPojo> selectBy(AdjustClassPojo condition);

    List<AdjustClassPojo> findAdjustClassByDate(@Param("applyLesson") String applyLesson, @Param("setLesson") String setLesson, @Param("applyDateStr") String applyDateStr, @Param("setDateStr") String setDateStr);

    List<AdjustClassPojo> listByLesson(@Param("lesson") Object lesson,@Param("applyDateStr") String applyDateStr);

    List<AdjustClassPojo> listBySetDateSetLesson(@Param("lesson")Object lesson,@Param("setDateStr") String setDateStr);

    int selectCount(@Param("applyDate") Date applyDate,@Param("lesson") Object lesson,@Param("applicantId") Integer applicantId);
}
