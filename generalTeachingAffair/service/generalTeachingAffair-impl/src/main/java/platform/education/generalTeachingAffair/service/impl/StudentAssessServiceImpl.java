package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import platform.education.generalTeachingAffair.dao.StudentAssessDao;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.dao.StudentShowDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.education.generalTeachingAffair.service.StudentAssessService;
import platform.education.generalTeachingAffair.service.StudentShowService;
import platform.education.generalTeachingAffair.vo.PerformancePojo;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/13 15:54
 * @Description:
 */
public class StudentAssessServiceImpl implements StudentAssessService {
    private Logger log = LoggerFactory.getLogger(getClass());


    private StudentAssessDao studentAssessDao;

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setStudentAssessDao(StudentAssessDao studentAssessDao) {
        this.studentAssessDao = studentAssessDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     */
    @Override
    public String delete(Integer id) {
        if (id != null) {
            studentAssessDao.deletePerformance(id);
            studentAssessDao.deletePerformanceStu(id);
        } else {
            return PublicClassService.OPERATE_FAIL;
        }
        return PublicClassService.OPERATE_SUCCESS;
    }


    /**
     * @param performancePojo
     * @return
     */
    @Override
    public PerformancePojo add(PerformancePojo performancePojo) {
        if (performancePojo == null) {
            return null;
        }
        Date createDate = performancePojo.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        performancePojo.setCreateDate(createDate);
        studentAssessDao.create(performancePojo);

        Integer id = performancePojo.getId();
        if (id == null) {
            throw new NullPointerException();
        }
        String students = performancePojo.getStudents();
        if (students != null && !("").equals(students)) {
            String[] studentIds = students.split(",");
            // 新增学生和表现关系表
            for (String stuId : studentIds) {
                Student student = studentDao.findById(Integer.parseInt(stuId));
                if (student != null && student.getTeamId() != null) {
                    studentAssessDao.createStuShow(stuId, student.getTeamId(), id);
                } else {
                    throw new NullPointerException();
                }
            }
        }

        return performancePojo;
    }

    /**
     * @param performancePojo
     * @param page
     * @return
     */
    @Override
    public List<PerformancePojo> findSutBusByGroupCondition(PerformancePojo performancePojo, Page page) {
        List<PerformancePojo> list = studentAssessDao.findSutBusByGroupCondition(performancePojo, page);
        if (!CollectionUtils.isEmpty(list)) {
            for (PerformancePojo pojo : list) {
                String students = pojo.getStudents();
                if (!org.springframework.util.StringUtils.isEmpty(students)) {
                    String[] split = students.split(",");
                    List<String> stuNames = studentDao.findNameById(split);
                    pojo.setStuName(StringUtils.join(stuNames, ","));
                }
                Integer teacherId = pojo.getTeacherId();
                Teacher teacher = teacherDao.findById(teacherId);
                if (teacher != null) {
                    pojo.setTeacherName(teacher.getName());
                }
            }
        }
        return list;
    }

}
