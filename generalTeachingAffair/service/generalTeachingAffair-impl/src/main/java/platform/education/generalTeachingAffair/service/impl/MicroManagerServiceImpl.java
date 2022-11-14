package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.*;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.MicroTypePojo;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.MicroManagerService;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.MicroManagerPojo;
import platform.education.generalTeachingAffair.vo.UserCommentsPojo;

import java.util.Date;
import java.util.List;

/**
 * 小程序微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:23
 * @Description:
 */
public class MicroManagerServiceImpl implements MicroManagerService {


    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 开课管理
     */
    private MicroManagerDao microManagerDao;

    /**
     * 评论管理
     */
    private StuCommentsDao stuCommentsDao;

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    private DepartmentTeacherDao departmentTeacherDao;

    public void setDepartmentTeacherDao(DepartmentTeacherDao departmentTeacherDao) {
        this.departmentTeacherDao = departmentTeacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setMicroManagerDao(MicroManagerDao microManagerDao) {
        this.microManagerDao = microManagerDao;
    }

    public void setStuCommentsDao(StuCommentsDao stuCommentsDao) {
        this.stuCommentsDao = stuCommentsDao;
    }

    /**
     * 获取所有微课信息
     *
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @Override
    public List<MicroManagerPojo> findMicroManagerByCondition(MicroManagerPojo condition, Page page, Order order) {
        List<MicroManagerPojo> microManagerList = null;
        try {
            microManagerList = microManagerDao.findMicroManagerByCondition(condition, page, order);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return microManagerList;
    }

    @Override
    public MicroManagerPojo findMicroManagerPojoById(Integer id) {
        try {
            MicroManagerPojo microManagerPojo = new MicroManagerPojo();
            microManagerPojo.setId(id);
            List<MicroManagerPojo> microManagerList = microManagerDao.findMicroManagerByCondition(microManagerPojo, null, null);
            if (microManagerList != null && microManagerList.size() > 0) {
                return microManagerList.get(0);
            }
            return null;
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }


    /**
     * 新增问卷
     * @param publicClass
     * @return
     */
    @Override
    public MicroManagerPojo add(MicroManagerPojo publicClass) {
        if (publicClass == null) {
            return null;
        }
        Date createDate = publicClass.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        Date uploadDate = publicClass.getUploadDate();
        if (uploadDate == null) {
            uploadDate = new Date();
        }
        publicClass.setCreateDate(createDate);
        publicClass.setModifyDate(createDate);
        publicClass.setUploadDate(uploadDate);
        publicClass.setCollect(0);
        publicClass.setThumbs(0);
        MicroManagerPojo microManagerPojo = microManagerDao.create(publicClass);
        Integer id = publicClass.getId();
        if (id == null) {
            return null;
        }
        String gradeIds = publicClass.getGradeIds();
        // 保存课程对应的年级
        if (gradeIds != null && !("").equals(gradeIds)) {
            String[] split = gradeIds.split(",");
            microManagerDao.createGradeRelated(split, id, publicClass.getSchoolId());
        } else {
            throw new NullPointerException();
        }
        // 保存课程对应的类型
        Integer typeId = publicClass.getTypeId();
        if (typeId != null) {
            microManagerDao.createTypeRelated(typeId, id, publicClass.getSchoolId());
        } else {
            throw new NullPointerException();
        }

        return microManagerPojo;
    }

    @Override
    public MicroManagerPojo modify(MicroManagerPojo publicClass) {

        if (publicClass == null) {
            return null;
        }
        Date modify = publicClass.getModifyDate();
        publicClass.setModifyDate(modify != null ? modify : new Date());
        Integer id = publicClass.getId();
        // 删除关联的类别、年级
        microManagerDao.deleteTypeRelated(id, publicClass.getSchoolId());
        microManagerDao.deleteGradeRelated(id, publicClass.getSchoolId());

        String gradeIds = publicClass.getGradeIds();
        // 保存课程对应的年级
        if (gradeIds != null && !("").equals(gradeIds)) {
            String[] split = gradeIds.split(",");
            microManagerDao.createGradeRelated(split, id, publicClass.getSchoolId());
        } else {
            throw new NullPointerException();
        }
        // 保存课程对应的类型
        Integer typeId = publicClass.getTypeId();
        if (typeId != null) {
            microManagerDao.createTypeRelated(typeId, id, publicClass.getSchoolId());
        } else {
            throw new NullPointerException();
        }


        return microManagerDao.update(publicClass);
    }

    @Override
    public String abandon(MicroManagerPojo publicClass) {
        if (publicClass != null) {
            publicClass.setIsDeleted(1);
            Date modify = publicClass.getModifyDate();
            publicClass.setModifyDate(modify != null ? modify : new Date());
            try {
                publicClass = this.microManagerDao.update(publicClass);
                return PublicClassService.OPERATE_SUCCESS;
            } catch (Exception e) {
                log.error("废弃 -> {} 失败，异常信息为 {}", publicClass.getId(), e);
                return PublicClassService.OPERATE_ERROR;
            }
        }
        return PublicClassService.OPERATE_FAIL;
    }


    /**
     * 获取所有评论信息
     *
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @Override
    public List<UserCommentsPojo> findStuCommentsPojoByCondition(MicroManagerPojo condition, Page page, Order order) {
        List<UserCommentsPojo> microManagerList = null;
        microManagerList = stuCommentsDao.findStuCommentsPojoByCondition(condition, page, order);
        for (UserCommentsPojo commentsPojo : microManagerList) {
            Integer userId = commentsPojo.getUserId();
            Integer userType = commentsPojo.getUserType();
            if (userId != null && userType != null){
                // 0学生 1教师
                if (userType == 0) {
                    Student read = studentDao.findById(userId);
                    if (read != null) {
                        commentsPojo.setName(read.getName());
                        commentsPojo.setClassName(read.getTeamName());
                    }
                } else if (userType == 1){
                    Teacher teacher = teacherDao.findById(userId);
                    if (teacher != null) {
                        commentsPojo.setName(teacher.getName());
                        DepartmentTeacherCondition departmentTeacher = new DepartmentTeacherCondition();
                        departmentTeacher.setTeacherId(userId);
                        List<DepartmentTeacher> departmentTeacherByCondition = departmentTeacherDao.findDepartmentTeacherByCondition(departmentTeacher, null, null);
                        if (departmentTeacherByCondition != null && departmentTeacherByCondition.size() > 0) {
                            commentsPojo.setClassName(departmentTeacherByCondition.get(0).getDepartmentName());
                        }
                    }
                }
            }
        }

        return microManagerList;
    }

    /**
     * 删除指定的评论
     *
     * @param userCommentsPojo
     * @return
     */
    @Override
    public String commentAbandon(UserCommentsPojo userCommentsPojo) {
        if (userCommentsPojo != null) {
            userCommentsPojo.setIsDeleted(1);
            Date modify = userCommentsPojo.getModifyDate();
            userCommentsPojo.setModifyDate(modify != null ? modify : new Date());
            try {
                userCommentsPojo = this.stuCommentsDao.update(userCommentsPojo);
                return PublicClassService.OPERATE_SUCCESS;
            } catch (Exception e) {
                log.error(e.toString());
                return PublicClassService.OPERATE_ERROR;
            }
        }
        return PublicClassService.OPERATE_FAIL;
    }

    @Override
    public List<MicroTypePojo> findMicroType(MicroTypePojo microTypePojo) {
        return microManagerDao.findMicroType(microTypePojo);
    }

    @Override
    public String findMicroGradeNameById(Integer id, Integer schoolId) {
        return microManagerDao.findMicroGradeNameById(id, schoolId);
    }

}
