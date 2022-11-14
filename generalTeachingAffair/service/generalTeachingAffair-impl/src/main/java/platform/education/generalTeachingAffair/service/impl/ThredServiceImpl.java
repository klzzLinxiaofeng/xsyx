package platform.education.generalTeachingAffair.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import platform.education.generalTeachingAffair.dao.SchoolBusDao;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.model.SchoolBusMangerVo;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.ThredService;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/12/30 14:11
 * @Description:
 */
public class ThredServiceImpl implements ThredService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private StudentDao studentDao;

    private TeacherDao teacherDao;

    private SchoolBusDao schoolBusDao;

    public void setSchoolBusDao(SchoolBusDao schoolBusDao) {
        this.schoolBusDao = schoolBusDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }


    @Override
    public void updateTeacher(String userName) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        PlatformTransactionManager txManager = ContextLoader.getCurrentWebApplicationContext().getBean(PlatformTransactionManager.class);
        TransactionStatus status = txManager.getTransaction(def);
        try {
            Teacher teacher = new Teacher();
            teacher.setEmpCode(userName);
            teacher.setIsSendCanteen(1);
            teacherDao.updateTeacherSendCanteen(teacher);
            txManager.commit(status);

        } catch (Exception e) {
            // 回滚事务
            txManager.rollback(status);
            log.error("修改失败：", e);
        }
    }


    @Override
    public void updateStu(String userName) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        PlatformTransactionManager txManager = ContextLoader.getCurrentWebApplicationContext().getBean(PlatformTransactionManager.class);
        TransactionStatus status = txManager.getTransaction(def);
        try {
            Student student = new Student();
            student.setEmpCode(userName);
            student.setIsSendCanteen(1);
            studentDao.updateStuSendCanteen(student);
            txManager.commit(status);
        } catch (Exception e) {
            // 回滚事务
            txManager.rollback(status);
            log.error("修改失败：", e);
        }
    }

}
