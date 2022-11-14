package com.xunyunedu.teacher.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.pay.dao.PayOrderDao;
import com.xunyunedu.pay.pojo.StudentPojo;
import com.xunyunedu.student.dao.StudentAskingDao;
import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.teacher.condition.TeacherSearchCondition;
import com.xunyunedu.teacher.dao.PersonDao;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.DepartmentTeacherPojo;
import com.xunyunedu.teacher.pojo.PersonPojo;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.teacher.service.TeacherHomeService;
import com.xunyunedu.util.ftp.FtpUtils;
import com.xunyunedu.workAttendance.dao.PjTeamTeacherDao;
import com.xunyunedu.workAttendance.model.PjTeamTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2020/11/2 9:44
 * @Description:
 */
@Service
public class TeacherHomeServiceImpl implements TeacherHomeService {
    @Autowired
    private TeacherHomeDao teacherHomeDao;

    @Autowired
    private StudentAskingDao studentAskingDao;

    @Autowired
    private PayOrderDao payOrderDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;
    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    private PjTeamTeacherDao pjTeamTeacherDao;


    @Override
    public TeacherPojo getTeacherById(Integer teacherId) {
        return teacherHomeDao.selectById(teacherId);
    }

    @Override
    public void modifyIndiaSatus(StudentAskingPojo studentAskingPojo) {
        Date date = new Date();
        studentAskingPojo.setReviewDate(date);
        studentAskingPojo.setModifyDate(date);
        teacherHomeDao.modifyIndiaSatus(studentAskingPojo);
    }

    /**
     * 获取教师信息
     *
     * @param teacherPojo
     * @return
     */
    @Override
    public TeacherPojo getTeacherByCondition(TeacherPojo teacherPojo) {
        TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
        if (teacher != null && teacher.getId() != null) {

            teacher.setRoleList(basicSQLService.find("select r.id,r.`name` from yh_user_role ur left join yh_role r on ur.role_id=r.id where ur.user_id="+teacher.getUserId()+" and ur.is_deleted=0"));

            Integer teacherId = teacher.getId();

            List<PjTeamTeacher> teamTeacherList = pjTeamTeacherDao.findTeacherByTeacherId(teacherId);
            // 是否为班主任
            if (CollUtil.isNotEmpty(teamTeacherList)) {
                teacher.setIsClassTeacher(1);
            } else {
                teacher.setIsClassTeacher(0);
            }
            // 获取教师部门
            List<DepartmentTeacherPojo> departmentTeacherPojoList = teacherHomeDao.getTeacherDeparment(teacherId);
            if (CollUtil.isNotEmpty(departmentTeacherPojoList)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < departmentTeacherPojoList.size(); i++) {
                    String departmentName = departmentTeacherPojoList.get(i).getDepartmentName();
                    stringBuilder.append(departmentName);
                    if (i != departmentTeacherPojoList.size() - 1) {
                        stringBuilder.append(",");
                    }
                }
                teacher.setDepartmentName(stringBuilder.toString());
            }
            // 获取头像信息
            if (teacher.getPersionId() != null) {
                PersonPojo personPojo = new PersonPojo();
                personPojo.setId(teacher.getPersionId());
                List<PersonPojo> personPojoList = personDao.findPersonByparam(personPojo);
                if (CollUtil.isNotEmpty(personPojoList)) {
                    String photoUuid = personPojoList.get(0).getPhotoUuid();
                    if (StrUtil.isNotEmpty(photoUuid)) {
                        // 获取图片绝对路径
                        // 根据uuid查询封面的url
                        EntityFile file = uploaderDao.findFileByUUID(photoUuid);
                        if (file != null) {
                            teacher.setPicUrl(ftpUtils.relativePath2HttpUrl(file));
                        }
                    }
                }
            }
        }
        return teacher;
    }

    /**
     * 修改教师信息
     *
     * @param teacherPojo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTeacherInfor(TeacherPojo teacherPojo) {
        TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
        if (teacher.getPersionId() != null) {
            PersonPojo personPojo = new PersonPojo();
            personPojo.setId(teacher.getPersionId());
            // 获取档案信息
            List<PersonPojo> personPojoList = personDao.findPersonByparam(personPojo);
            if (CollUtil.isNotEmpty(personPojoList)) {
                // 修改档案
                if (StrUtil.isNotEmpty(personPojoList.get(0).getPhotoUuid()) && personPojoList.get(0).getPhotoUuid().equals(teacherPojo.getEntityId())) {
                    return;
                }
                personPojo.setPhotoUuid(teacherPojo.getEntityId());
                personDao.update(personPojo);
            } else {
                addPerson(teacherPojo.getEntityId(), teacher);
            }
        } else {
            addPerson(teacherPojo.getEntityId(), teacher);
        }
    }


    public void addPerson(String entityId, TeacherPojo teacher) {
        PersonPojo personPojo = new PersonPojo();
        personPojo.setCreateDate(new Date());
        personPojo.setPhotoUuid(entityId);
        personPojo.setName(teacher.getName());
        // 新增档案
        personDao.insert(personPojo);
        // 修改教师绑定档案
        teacher.setPersionId(personPojo.getId());
        teacherHomeDao.update(teacher);
    }

//    /**
//     * 获取审批列表（已审批、未审批）（学生、教师）
//     *
//     * @param approvePojo
//     * @param pageNum
//     * @param pageSize
//     * @param indiaType
//     * @return
//     */
////    @Override
////    public <T> T getAgentInfo(ApprovePojo approvePojo, Integer pageNum, Integer pageSize, Integer indiaType) {
////        // 审核状态（1：审核中 2：已通过 3：已驳回） 审核中不需要分页
////        // 获取学生请假
////        if(approvePojo.getIndiaStatus() != null && approvePojo.getIndiaStatus() == 1){
////            List<StudentAskingPojo> stuAskingList = getStudent(approvePojo, indiaType);
////            return (T) stuAskingList;
////        }
////        PageHelper.startPage(pageNum, pageSize);
////        List<StudentAskingPojo> stuAskingList = getStudent(studentAskingPojo, indiaType);
////        PageInfo<StudentAskingPojo> objectPageInfo = new PageInfo<>(stuAskingList);
////        return (T) objectPageInfo;
////    }

    public List<StudentAskingPojo> getStudent(StudentAskingPojo studentAskingPojo) {
        List<StudentAskingPojo> stuAskingList = studentAskingDao.getStuAskingList(studentAskingPojo);
        for (int i = 0; i < stuAskingList.size(); i++) {
            StudentPojo studentPojo = new StudentPojo();
            studentPojo.setSchoolId(stuAskingList.get(i).getSchoolId());
            studentPojo.setId(stuAskingList.get(i).getStudentId());
            StudentPojo student = payOrderDao.getStuInfo(studentPojo);
            stuAskingList.get(i).setStuName(student.getName());
        }
        return stuAskingList;
    }

    @Override
    public List<TeacherPojo> getBySchoolId(Integer schoolId) {
        return teacherHomeDao.selectBySchoolId(schoolId);
    }

    @Override
    public PageInfo pagingSelectTeacherCoreInfo(PageCondition<TeacherSearchCondition> condition) {
        Page page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List list = teacherHomeDao.selectTeacherCoreInfo(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public TeacherPojo getByUserId(Integer userId) {
        TeacherPojo teacherPojo = new TeacherPojo();
        teacherPojo.setUserId(userId);
        return teacherHomeDao.getTeacherByCondition(teacherPojo);
    }

    @Override
    public List<Integer> getTeacherTeamList(Integer userId, Integer type) {
        String sql = "SELECT tt.team_id FROM pj_teacher t LEFT JOIN pj_team_teacher tt ON t.id = tt.teacher_id WHERE t.user_id =" + userId + " and t.is_delete=0 and tt.is_delete=0";
        if (type != null) {
            sql += " and tt.`type`=" + type;
        }
        List<Map<String, Object>> list = basicSQLService.find(sql);
        if (list == null) {
            return null;
        }
        List<Integer> teamIds = new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            teamIds.add(Integer.valueOf(map.get("team_id").toString()));
        }
        return teamIds;
    }

    @Override
    public Integer getManagedTeamId(Integer userId) {
        List<Integer> list = getTeacherTeamList(userId, 1);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 修改教师信息
     *
     * @param teacherPojo
     */
    @Override
    public void updateTeacher(TeacherPojo teacherPojo) {
        teacherPojo.setModifyDate(new Date());
        teacherHomeDao.update(teacherPojo);
    }
}
