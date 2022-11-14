package platform.education.generalTeachingAffair.service.impl;

import platform.education.generalTeachingAffair.dao.InitDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.model.CanteenStudent;
import platform.education.generalTeachingAffair.model.CanteenTeacher;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.InitService;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/10/28 15:11
 * @Description:
 */
public class InitServiceImpl implements InitService {

    /**
     * 学生dao
     */
    private InitDao initDao;

    private TeacherDao teacherDao;

    public void setInitDao(InitDao initDao) {
        this.initDao = initDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<CanteenStudent> findCanteenUser() {
        return initDao.findCanteenUser();
    }

    @Override
    public List<Student> findByTeamNameAndName(String teamName, String name) {
        return initDao.findByTeamNameAndName(teamName, name);
    }

    @Override
    public void update(Student student) {
        initDao.update(student);
    }

    /**
     * 获取已经发卡教师
     *
     * @return
     */
    @Override
    public List<CanteenTeacher> findCanteenTeacherUser() {
        return initDao.findCanteenTeacherUser();
    }

    /**
     * 获取教师数据
     *
     * @param name
     * @param sex
     * @return
     */
    @Override
    public List<Teacher> findTeacherByNameAndSex(String name, Integer sex) {
        return initDao.findTeacherByNameAndSex(name, sex);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherDao.update(teacher);
    }
}
