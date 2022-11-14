package platform.education.generalTeachingAffair.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.LibraryVo;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.LibraryService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.utils.UUIDUtil;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/2 11:53
 * @Description: 修改和新增是同一个接口 只要no 和cardNo不相同即可当做修改接口
 */
public class LibraryServiceImpl implements LibraryService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    @Autowired
    @Qualifier("studentService")
    protected StudentService studentService;

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    /**
     * 获取学生数据
     *
     * @param libraryACnteen
     * @param libraryLogin
     * @param libraryCreate
     */
    @Override
    public void studentSend(String libraryACnteen, String libraryLogin, String libraryCreate) {
        try {
            /**
             * 图书馆为单条发送接口
             */
            // 获取学生信息
            Student studentVO = new Student();
            studentVO.setIsSendLibrary(0);
            List<Student> studentList = studentService.findStudentByNoSend(studentVO);
            // 图书馆为单条新增接口
            for (Student student : studentList) {
                // 判断卡号工号
                String empCode = student.getEmpCode();
                String empCard = student.getEmpCard();
                if (empCode != null && !empCode.equals("") && empCard != null && !("0").equals(empCard)) {
                    // 单条修改
                    libraryData(student.getId(), student.getName(), student.getSex(), student.getMobile(), empCard, 1, libraryACnteen, libraryLogin, libraryCreate);
                }
            }
        } catch (Exception e) {
            log.error("获取学生信息到图书馆失败, {}", e);
        }
    }

    /**
     * 获取老师数据
     *
     * @param libraryACnteen
     * @param libraryLogin
     * @param libraryCreate
     */
    @Override
    public void teacherSend(String libraryACnteen, String libraryLogin, String libraryCreate) {
        try {
            Teacher teacherVo = new Teacher();
            teacherVo.setIsSendLibrary(0);
            List<Teacher> teacherList = teacherService.findTeacherByNoSend(teacherVo);
            // 图书馆为单条新增接口
            for (Teacher teacher : teacherList) {
                // 判断卡号工号
                String empCode = teacher.getEmpCode();
                String empCard = teacher.getEmpCard();
                if (empCode != null && !empCode.equals("") && empCard != null && !("0").equals(empCard)) {
                    libraryData(teacher.getId(), teacher.getName(), teacher.getSex(), teacher.getMobile(), empCard, 0, libraryACnteen, libraryLogin, libraryCreate);
                }
            }
        } catch (Exception e) {
            log.error("获取教师信息到图书馆失败, {}", e);
        }
    }

    /**
     * 发送数据到图书馆 图书馆菜单单条发送
     *
     * @param id
     * @param name
     * @param sex
     * @param phone
     * @param empCard
     * @param type
     */
    public void libraryData(Integer id, String name, String sex, String phone, String empCard, Integer type, String libraryACnteen, String libraryLogin, String libraryCreate) {
        LibraryVo libraryVo = new LibraryVo();
        //图书馆接口数据
        libraryVo.setId(id);
        libraryVo.setNo(UUIDUtil.getUUID());
        libraryVo.setName(name);
        libraryVo.setSex(getSexName(sex).toString());
        libraryVo.setEnabled("true");
        libraryVo.setPhone(phone);
        libraryVo.setCardNo(empCard);
        libraryVo.setMoney("0");
        if (type == 0) {
            libraryVo.setRoleName("教职工");
            libraryVo.setRoleId("1");
        } else {
            libraryVo.setRoleName("学生");
            libraryVo.setRoleId("2");
        }
        // type 0：老师 1：学生
        httpService.addLibraryData(libraryVo, libraryACnteen, libraryLogin, libraryCreate, null, null, type);
    }

    public Boolean getSexName(String val) {
        if (val != null && !("").equals(val)) {
            return true;
        }
        return false;
    }
}
