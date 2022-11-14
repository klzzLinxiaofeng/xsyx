package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.CanteenStudent;
import platform.education.generalTeachingAffair.model.CanteenTeacher;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;

import java.util.List;

/**
 * 初始化学生和教师用户的食堂卡号
 *
 * @author: yhc
 * @Date: 2020/10/28 14:19
 * @Description:
 */
public interface InitService {
    List<CanteenStudent> findCanteenUser();

    List<Student> findByTeamNameAndName(String teamName, String name);

    void update(Student student);

    List<CanteenTeacher> findCanteenTeacherUser();

    List<Teacher> findTeacherByNameAndSex(String name, Integer sex);

    void updateTeacher(Teacher teacher);
}
