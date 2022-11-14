package com.xunyunedu.syllabus.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.syllabus.param.ApprobalParam;
import com.xunyunedu.syllabus.pojo.AdjustClassPojo;


import java.util.Date;
import java.util.List;



/**
 * 调课申请表(OaAdjustClass)表服务接口
 *
 * @author sjz
 * @since 2021-03-30 14:20:29
 */
public interface AdjustClassService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AdjustClassPojo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdjustClassPojo> queryAllByLimit(int offset,int limit);

    /**
     * 新增数据
     *
     * @param oaAdjustClass 实例对象
     * @return 实例对象
     */
    AdjustClassPojo insert(AdjustClassPojo oaAdjustClass);

    /**
     * 修改数据
     *
     * @param oaAdjustClass 实例对象
     * @return 实例对象
     */
    AdjustClassPojo update(AdjustClassPojo oaAdjustClass);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    PageInfo<AdjustClassPojo> selectList(PageCondition<AdjustClassPojo> condition);

    AdjustClassPojo approval(ApprobalParam approbalParam);

    List<AdjustClassPojo> rejectOther(AdjustClassPojo adjustClass);


    List<AdjustClassPojo> listByLesson(Object lesson,String applyDateStr);

    List<AdjustClassPojo> listBySetDateSetLesson(Object lesson,String setDateStr);

    int selectCount(Date applyDate,Object lesson,Integer applicantId);
}
