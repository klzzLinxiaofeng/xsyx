package com.xunyunedu.syllabus.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.syllabus.dao.AdjustClassDao;
import com.xunyunedu.syllabus.dao.SyllabusDao;
import com.xunyunedu.syllabus.dao.SyllabusLessonDao;
import com.xunyunedu.syllabus.param.ApprobalParam;
import com.xunyunedu.syllabus.pojo.AdjustClassPojo;
import com.xunyunedu.syllabus.pojo.SyllabusLessonPojo;
import com.xunyunedu.syllabus.service.AdjustClassService;
import com.xunyunedu.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 调课申请表(OaAdjustClass)表服务实现类
 *
 * @author sjz
 * @since 2021-03-30 14:20:30
 */
@Service("adjustClassService")
public class AdjustClassServiceImpl implements AdjustClassService {

    @Resource
    private AdjustClassDao adjustClassDao;

    @Resource
    private SyllabusDao syllabusDao;

    @Resource
    private SyllabusLessonDao syllabusLessonDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AdjustClassPojo queryById(Integer id) {
        return this.adjustClassDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AdjustClassPojo> queryAllByLimit(int offset,int limit) {
        return this.adjustClassDao.queryAllByLimit(offset,limit);
    }

    /**
     * 新增数据
     *
     * @param adjustClass 实例对象
     * @return 实例对象
     */
    @Override
    public AdjustClassPojo insert(AdjustClassPojo adjustClass) {
        this.adjustClassDao.insert(adjustClass);
        return adjustClass;
    }

    /**
     * 修改数据
     *
     * @param oaAdjustClass 实例对象
     * @return 实例对象
     */
    @Override
    public AdjustClassPojo update(AdjustClassPojo oaAdjustClass) {
        this.adjustClassDao.update(oaAdjustClass);
        return this.queryById(oaAdjustClass.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.adjustClassDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<AdjustClassPojo> selectList(PageCondition<AdjustClassPojo> condition) {
        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = adjustClassDao.selectBy(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AdjustClassPojo approval(ApprobalParam approbalParam) {
        //根据id查询要审批的调课申请
        AdjustClassPojo adjustClassPojo = adjustClassDao.queryById(approbalParam.getId());
        if (adjustClassPojo != null){
            //审批结果更新到申请表
            adjustClassPojo.setStatus(approbalParam.getStatus());
            if (approbalParam.getStatus() == 2){
                adjustClassPojo.setRejectionReason(approbalParam.getRejectionReason());
            }
            adjustClassPojo.setApproveDate(new Date());
            adjustClassDao.update(adjustClassPojo);
            // 同意调课申请
            if (approbalParam.getStatus().equals(1)){
                //复制个人课表
                this.adjustment(adjustClassPojo);
            }
        }
        return adjustClassPojo;
    }

    /**
     * 驳回其他相关的调课申请
     *
     * @param adjustClassPojo
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AdjustClassPojo> rejectOther(AdjustClassPojo adjustClassPojo) {
        String applyLesson = adjustClassPojo.getApplyLesson().substring(2,3);
        String setLesson = adjustClassPojo.getSetLesson().substring(2,3);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String applyDateStr = df.format(adjustClassPojo.getApplyDate());
        String setDateStr = df.format(adjustClassPojo.getSetDate());
        return adjustClassDao.findAdjustClassByDate(applyLesson,setLesson,applyDateStr,setDateStr);
    }

    @Override
    public List<AdjustClassPojo> listByLesson(Object lesson,String applyDateStr) {
        return adjustClassDao.listByLesson(lesson,applyDateStr);
    }

    @Override
    public List<AdjustClassPojo> listBySetDateSetLesson(Object lesson,String setDateStr) {
        return adjustClassDao.listBySetDateSetLesson(lesson,setDateStr);
    }

    @Override
    public int selectCount(Date applyDate,Object lesson,Integer applicantId) {
        return adjustClassDao.selectCount(applyDate,lesson,applicantId);
    }

    /**
     * 拷贝个人课表
     *
     * @param adjustClass
     * @param teacherId
     * @param flag
     */
    private void personalTimetable(AdjustClassPojo adjustClass,Integer teacherId,boolean flag) {
        String applyWeek = DateUtil.getWeekOfDate(adjustClass.getApplyDate()).toString();
        String setWeek = DateUtil.getWeekOfDate(adjustClass.getSetDate()).toString();
        // 调课日期的周一日期
        Date applyFirstDay = DateUtil.getFirstDayOfWeek(adjustClass.getApplyDate());
        // 调课日期的周日日期
        Date applyLastDay = DateUtil.getLastDayOfWeek(adjustClass.getApplyDate());
        String[] applyLessons = adjustClass.getApplyLesson().substring(1,adjustClass.getApplyLesson().length() - 1).split(",");
        String[] setLessons = adjustClass.getSetLesson().substring(1,adjustClass.getSetLesson().length() - 1).split(",");
        int i1 = Integer.parseInt(applyLessons[0].substring(1,2));
        int i2 = Integer.parseInt(setLessons[0].substring(1,2));
        List<SyllabusLessonPojo> syllabusLessonList = syllabusLessonDao.listBySyllabusLesson(applyFirstDay,teacherId,null,1);
        if (syllabusLessonList.size() <= 0){
            syllabusLessonList = syllabusLessonDao.listBySyllabusLesson(null,teacherId,adjustClass.getApplyDate(),0);
        } else {
            // 删除该周的个人课表
            syllabusLessonDao.deleteByTeacher(teacherId,applyFirstDay);
        }
        if (applyLessons.length > 1){
            // 连课调课
            int i3 = Integer.parseInt(setLessons[1].substring(1,2));
            int i4 = Integer.parseInt(applyLessons[1].substring(1,2));
            for (SyllabusLessonPojo syllabusLesson : syllabusLessonList) {
                syllabusLesson.setCreateDate(new Date());
                syllabusLesson.setStartDate(applyFirstDay);
                syllabusLesson.setAdjustFlag(0);
                syllabusLesson.setDefaultFlag(1);
                syllabusLesson.setEndDate(applyLastDay);
                syllabusLesson.setId(null);
                if (flag){
                    // 复制申请人的个人课表
                    if (syllabusLesson.getLesson().equals(i1) && syllabusLesson.getDayOfWeek().equals(applyWeek)){
                        syllabusLesson.setLesson(i2);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(setWeek);
                    } else if (syllabusLesson.getLesson().equals(i4) && syllabusLesson.getDayOfWeek().equals(applyWeek)){
                        syllabusLesson.setLesson(i3);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(setWeek);
                    }
                } else {
                    // 复制审批人的个人课表
                    if (syllabusLesson.getLesson().equals(i2) && syllabusLesson.getDayOfWeek().equals(setWeek)){
                        syllabusLesson.setLesson(i1);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(applyWeek);
                    } else if (syllabusLesson.getLesson().equals(i3) && syllabusLesson.getDayOfWeek().equals(setWeek)){
                        syllabusLesson.setLesson(i4);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(applyWeek);
                    }
                }
                syllabusLessonDao.insert(syllabusLesson);
            }
        } else {
            // 单课调课
            for (SyllabusLessonPojo syllabusLesson : syllabusLessonList) {
                syllabusLesson.setCreateDate(new Date());
                syllabusLesson.setId(null);
                syllabusLesson.setStartDate(applyFirstDay);
                syllabusLesson.setEndDate(applyLastDay);
                syllabusLesson.setAdjustFlag(0);
                syllabusLesson.setDefaultFlag(1);
                if (flag){
                    // 复制申请人的个人课表
                    if (syllabusLesson.getLesson().equals(i1) && syllabusLesson.getDayOfWeek().equals(applyWeek)){
                        syllabusLesson.setLesson(i2);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(setWeek);
                    }
                } else {
                    // 复制审批人的个人课表
                    if (syllabusLesson.getLesson().equals(i2) && syllabusLesson.getDayOfWeek().equals(setWeek)){
                        syllabusLesson.setLesson(i1);
                        syllabusLesson.setAdjustFlag(1);
                        syllabusLesson.setDayOfWeek(applyWeek);
                    }
                }
                syllabusLessonDao.insert(syllabusLesson);
            }
        }
    }

    private void adjustment(AdjustClassPojo adjustClassPojo) {
        //申请时间和被申请时间
        Date applyDate = adjustClassPojo.getApplyDate();
        Date setDate = adjustClassPojo.getSetDate();
        String appDate = cn.hutool.core.date.DateUtil.format(applyDate,"yyyy-MM-dd");
        String sDate = cn.hutool.core.date.DateUtil.format(setDate,"yyyy-MM-dd");
        //申请时间和被申请时间转为星期
        String week = DateUtil.getWeekOfDate(applyDate).toString();
        String setWeek = DateUtil.getWeekOfDate(setDate).toString();
        //申请时间的每周开始和结束时间
        Date firstDayOfWeek = DateUtil.getFirstDayOfWeek(applyDate);
        Date lastDayOfWeek = DateUtil.getLastDayOfWeek(applyDate);
        //被申请时间的每周开始和结束时间
        Date firstDayOfWeek1 = DateUtil.getFirstDayOfWeek(setDate);
        Date lastDayOfWeek1 = DateUtil.getLastDayOfWeek(setDate);
        //申请调课的节次
        String applyLesson = adjustClassPojo.getApplyLesson();
        JSONArray applyLessons = JSON.parseArray(applyLesson);
        //被申请调课的节次
        String setLesson = adjustClassPojo.getSetLesson();
        JSONArray setLessons = JSON.parseArray(setLesson);
        //申请人和被申请人id
        Integer applicantId = adjustClassPojo.getApplicantId();
        Integer approverId = adjustClassPojo.getApproverId();
        //判断申请调课的时间下的课程是否为默认的课程
        int count = syllabusLessonDao.selectCount(applicantId,sDate);
        if (count > 0){
            //查询申请人申请调课时间的那周课表
            List<SyllabusLessonPojo> syllabusLessonPojos = syllabusLessonDao.listByTeacherId(applicantId,sDate,1);
            if (syllabusLessonPojos != null && syllabusLessonPojos.size() > 0){
                for (SyllabusLessonPojo syllabusLessonPojo : syllabusLessonPojos) {
                    //删除本周不是默认的课程
                    syllabusLessonDao.deleteById(syllabusLessonPojo.getId());
                    for (int i = 0; i < applyLessons.size(); i++) {
                        int i1 = Integer.parseInt(applyLessons.get(i).toString());
                        if (syllabusLessonPojo.getLesson().equals(i1) && week.equals(syllabusLessonPojo.getDayOfWeek())){
                            for (int y = 0; y < setLessons.size(); y++) {
                                int parseInt = Integer.parseInt(setLessons.get(y).toString());
                                if (i == y){
                                    syllabusLessonPojo.setLesson(parseInt);
                                    syllabusLessonPojo.setDayOfWeek(setWeek);
                                    syllabusLessonPojo.setAdjustFlag(1);
                                }
                            }
                        }
                    }
                    syllabusLessonPojo.setId(null);
                    syllabusLessonPojo.setStartDate(firstDayOfWeek1);
                    syllabusLessonPojo.setEndDate(lastDayOfWeek1);
                    syllabusLessonPojo.setCreateDate(new Date());
                    syllabusLessonPojo.setDefaultFlag(1);
                    syllabusLessonDao.insert(syllabusLessonPojo);

                }
            }
        } else {
            List<SyllabusLessonPojo> syllabusLessonPojos = syllabusLessonDao.listByTeacherId(applicantId,sDate,0);
            if (syllabusLessonPojos != null && syllabusLessonPojos.size() > 0){
                for (SyllabusLessonPojo syllabusLessonPojo : syllabusLessonPojos) {
                    for (int i = 0; i < applyLessons.size(); i++) {
                        int i1 = Integer.parseInt(applyLessons.get(i).toString());
                        if (syllabusLessonPojo.getLesson().equals(i1) && week.equals(syllabusLessonPojo.getDayOfWeek())){
                            for (int y = 0; y < setLessons.size(); y++) {
                                int parseInt = Integer.parseInt(setLessons.get(y).toString());
                                if (i == y){
                                    syllabusLessonPojo.setLesson(parseInt);
                                    syllabusLessonPojo.setAdjustFlag(1);
                                    syllabusLessonPojo.setDayOfWeek(setWeek);
                                }
                            }
                        }
                    }
                    syllabusLessonPojo.setId(null);
                    syllabusLessonPojo.setStartDate(firstDayOfWeek1);
                    syllabusLessonPojo.setEndDate(lastDayOfWeek1);
                    syllabusLessonPojo.setCreateDate(new Date());
                    syllabusLessonPojo.setDefaultFlag(1);
                    syllabusLessonDao.insert(syllabusLessonPojo);
                }
            }
        }
        //查询审批人在申请时间的那个星期的课程
        int selectCount = syllabusLessonDao.selectCount(approverId,appDate);
        //如果有不是默认课程，就把不是默认的课程查回来
        if (selectCount > 0){
            List<SyllabusLessonPojo> syllabusLessonPojos = syllabusLessonDao.listByTeacherId(approverId,appDate,1);
            if (syllabusLessonPojos != null && syllabusLessonPojos.size() > 0){
                for (SyllabusLessonPojo syllabusLessonPojo : syllabusLessonPojos) {
                    //删除本周不是默认的课程
                    syllabusLessonDao.deleteById(syllabusLessonPojo.getId());
                    for (int i = 0; i < setLessons.size(); i++) {
                        int i1 = Integer.parseInt(setLessons.get(i).toString());
                        if (syllabusLessonPojo.getDayOfWeek().equals(setWeek) && syllabusLessonPojo.getLesson() == i1){
                            for (int y = 0; y < applyLessons.size(); y++) {
                                int i2 = Integer.parseInt(applyLessons.get(y).toString());
                                if (i == y){
                                    syllabusLessonPojo.setLesson(i2);
                                    syllabusLessonPojo.setAdjustFlag(1);
                                    syllabusLessonPojo.setDayOfWeek(week);
                                }
                            }
                        }
                    }
                    syllabusLessonPojo.setId(null);
                    syllabusLessonPojo.setStartDate(firstDayOfWeek);
                    syllabusLessonPojo.setEndDate(lastDayOfWeek);
                    syllabusLessonPojo.setCreateDate(new Date());
                    syllabusLessonPojo.setDefaultFlag(1);
                    syllabusLessonDao.insert(syllabusLessonPojo);
                }
            }
        } else {
            List<SyllabusLessonPojo> syllabusLessonPojos = syllabusLessonDao.listByTeacherId(approverId,appDate,0);
            if (syllabusLessonPojos != null && syllabusLessonPojos.size() > 0){
                for (SyllabusLessonPojo syllabusLessonPojo : syllabusLessonPojos) {
                    for (int i = 0; i < setLessons.size(); i++) {
                        int i1 = Integer.parseInt(setLessons.get(i).toString());
                        if (syllabusLessonPojo.getDayOfWeek().equals(setWeek) && syllabusLessonPojo.getLesson() == i1){
                            for (int y = 0; y < applyLessons.size(); y++) {
                                int i2 = Integer.parseInt(applyLessons.get(y).toString());
                                if (i == y){
                                    syllabusLessonPojo.setLesson(i2);
                                    syllabusLessonPojo.setDayOfWeek(week);
                                    syllabusLessonPojo.setAdjustFlag(1);
                                }
                            }
                        }
                    }
                    syllabusLessonPojo.setId(null);
                    syllabusLessonPojo.setStartDate(firstDayOfWeek);
                    syllabusLessonPojo.setEndDate(lastDayOfWeek);
                    syllabusLessonPojo.setCreateDate(new Date());
                    syllabusLessonPojo.setDefaultFlag(1);
                    syllabusLessonDao.insert(syllabusLessonPojo);
                }
            }
        }
    }
}


