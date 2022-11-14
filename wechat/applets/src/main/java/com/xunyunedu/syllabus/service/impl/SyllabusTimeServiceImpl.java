package com.xunyunedu.syllabus.service.impl;


import com.xunyunedu.syllabus.dao.SyllabusTimeDao;
import com.xunyunedu.syllabus.pojo.SyllabusTime;
import com.xunyunedu.syllabus.service.SyllabusTimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 学校作息时间表(SyllabusTime)表服务实现类
 *
 * @author sjz
 * @since 2021-04-07 11:13:24
 */
@Service("syllabusTimeService")
public class SyllabusTimeServiceImpl implements SyllabusTimeService {
    @Resource
    private SyllabusTimeDao syllabusTimeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SyllabusTime queryById(Integer id) {
        return this.syllabusTimeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SyllabusTime> queryAllByLimit(int offset,int limit) {
        return this.syllabusTimeDao.queryAllByLimit(offset,limit);
    }

    /**
     * 新增数据
     *
     * @param syllabusTime 实例对象
     * @return 实例对象
     */
    @Override
    public SyllabusTime insert(SyllabusTime syllabusTime) {
        this.syllabusTimeDao.insert(syllabusTime);
        return syllabusTime;
    }

    /**
     * 修改数据
     *
     * @param syllabusTime 实例对象
     * @return 实例对象
     */
    @Override
    public SyllabusTime update(SyllabusTime syllabusTime) {
        this.syllabusTimeDao.update(syllabusTime);
        return this.queryById(syllabusTime.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.syllabusTimeDao.deleteById(id) > 0;
    }

    @Override
    public SyllabusTime getBySchoolId(Integer schoolId,String termCode) {
        return this.syllabusTimeDao.getBySchoolId(schoolId,termCode);
    }
}
