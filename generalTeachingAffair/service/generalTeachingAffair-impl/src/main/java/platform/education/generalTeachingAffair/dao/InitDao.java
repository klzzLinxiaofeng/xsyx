package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.CanteenStudent;
import platform.education.generalTeachingAffair.model.CanteenTeacher;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2020/10/28 19:51
 *  @Description:
 */
public interface InitDao extends GenericDao<Student, Integer> {
    List<CanteenStudent> findCanteenUser();

    List<Student> findByTeamNameAndName(String teamName, String name);

    List<CanteenTeacher> findCanteenTeacherUser();

    List<Teacher> findTeacherByNameAndSex(String name, Integer sex);
}
