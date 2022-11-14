package platform.szxyzxx.web.teach.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.InitService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.util.PropertiesUtil;
import platform.szxyzxx.web.teach.util.UUIDUtil;

import java.util.List;

/**
 * 获取食堂已经发卡的用户数据
 *
 * @author: yhc
 * @Date: 2020/10/27 15:17
 * @Description:
 */
@Controller
@RequestMapping("/init")
public class InitCanteenUser extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(InitCanteenUser.class);

    @Autowired
    private InitService initService;


    /**
     * 学生
     */
    @Autowired
    @Qualifier("studentService")
    protected StudentService studentService;

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    /**
     * 图书馆接口
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;
    private static String canteenIp;
    private static String userListUrl;


    static {
        String fileName = "System.properties";
        // 图书馆
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");

        // 食堂接口查询卡号信息
        canteenIp = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        userListUrl = PropertiesUtil.getProperty(fileName, "canteen.user.list.url");

    }


    /**
     * ！！！！！！！！！！！！！！！！此功能为一次性方案部署到香市时调用一下（为了解决食堂已经发卡的用户）！！！！！！！！！！！！！！！！！！
     * <p>
     * 项目启动请求食堂接口获取所有已经发卡的用户信息
     */
//    @RequestMapping("/initUser")
//    @ResponseBody
    public String initUser() {
        String fileName = "System.properties";
        Boolean isBind = Boolean.parseBoolean(PropertiesUtil.getProperty(fileName, "bind.user"));
        if (isBind == null || ("").equals(isBind)) {
            return "执行失败";
        }
        if (isBind) {
            // 获取的数据为食堂已经发卡的用户信息
            List<CanteenStudent> canteenUser = initService.findCanteenUser();
            List<CanteenTeacher> canteenTeachers = initService.findCanteenTeacherUser();
            log.info("开始获取学生和教师信息--绑定食堂卡号");
//            Map<String, String> map = new HashMap<>(8);
//            config(map);
            int a = 0;
            int a1 = 0;
            int a2 = 0;
            // 获取食堂已经发卡的用户数据（教师和学生）
            if (!canteenUser.isEmpty()) {
                for (CanteenStudent canteenStudent : canteenUser) {
                    // 截取年级 一年级一班 转为一年级(1)班
                    String grade = grade(canteenStudent.getGradeName(), canteenStudent.getClassNum());
                    // 根据班级和年级查询学生信息
                    List<Student> byTeamNameAndName = initService.findByTeamNameAndName(grade, canteenStudent.getName());
                    if (byTeamNameAndName != null && byTeamNameAndName.size() > 1) {
                        log.info("多个相同姓名学生：{}", canteenStudent.toString());
                        a++;
                    } else if (byTeamNameAndName != null && byTeamNameAndName.size() == 0) {
                        log.info("未获取到相同学生的用户： {}", canteenStudent.toString());
                        a1++;
                    } else if (byTeamNameAndName != null && byTeamNameAndName.size() == 1) {
                        a2++;
                        log.info("已经获取到相同学生的一个用户： {}", byTeamNameAndName.toString());
                        // 修改学生的卡号
                        /**
                         * 将食堂提供的已经发卡的用户信息修改，后面新增的用户，查询的时候以username为准，发送给食堂的emp_code也是username字段
                         *
                         */
                        Student student = new Student();
                        student.setId(byTeamNameAndName.get(0).getId());
                        student.setEmpCard(canteenStudent.getIcNumber());
                        student.setEmpCode(canteenStudent.getEmpCode());
                        initService.update(student);
                    }

                }
            }
            log.info("多个相同: {} , 未获取到：{}", a, a1);
            log.info("一个用户: {}", a2);
            int c = 0;
            int c2 = 0;
            int c3 = 0;
            if (!canteenTeachers.isEmpty()) {
                for (CanteenTeacher canteenTeacher : canteenTeachers) {
                    // 根据教师姓名、性别查询
                    List<Teacher> byTeamNameAndName = initService.findTeacherByNameAndSex(canteenTeacher.getName(), canteenTeacher.getSex());
                    if (byTeamNameAndName != null && byTeamNameAndName.size() > 1) {
                        log.info("多个相同姓名教师：{}", canteenTeacher.toString());
                        c++;
                    } else if (byTeamNameAndName != null && byTeamNameAndName.size() == 0) {
                        log.info("未获取到相同教师的用户： {}", canteenTeacher.toString());
                        c2++;
                    } else if (byTeamNameAndName != null && byTeamNameAndName.size() == 1) {
                        log.info("已经获取到相同教师的一个用户： {}", byTeamNameAndName.toString());
                        c3++;
                        Teacher teacher = new Teacher();
                        teacher.setId(byTeamNameAndName.get(0).getId());
                        teacher.setEmpCard(canteenTeacher.getIcNumber());
                        teacher.setEmpCode(canteenTeacher.getEmpCode());
                        initService.updateTeacher(teacher);
                    }
                }
                log.info("教师多个相同: {} , 未获取到：{}", c, c2);
                log.info("教师一个用户: {}", c3);
            }
        }
        return "处理完成";
    }

    private String grade(String numStr, Integer classNum) {
        StringBuilder stringBuilder = new StringBuilder(numStr);
        stringBuilder.replace(3, 4, "(" + classNum + ")班");
        return stringBuilder.toString();
    }

    /**
     * 处理用户信息发送到图书馆
     *
     * @auth yhc
     */
    @RequestMapping("/library")
    @ResponseBody
    public String userInfo() {
        /**
         * 定时读取学生表和教师表，判断是否有未发送到图书馆的学生信息，有：判断是否已经判定了食堂的卡号(emp_card)，没有：查询食堂接口，
         * 根据emp_code再获取食堂卡号，写入对应的字段，然后获取对应的字段发送到图书馆
         */
        // 获取学生信息
        Student studentVO = new Student();
        studentVO.setIsSendLibrary(0);
        List<Student> studentList = studentService.findStudentByNoSend(studentVO);

        Teacher teacherVo = new Teacher();
        teacherVo.setIsSendLibrary(0);
        List<Teacher> teacherList = teacherService.findTeacherByNoSend(teacherVo);

        for (Teacher teacher : teacherList) {
            // 判断卡号工号
            String empCode = teacher.getEmpCode();
            String empCard = teacher.getEmpCard();
            flg(empCode, empCard, null, teacher, 0);
        }
        for (Student student : studentList) {
            // 判断卡号工号
            String empCode = student.getEmpCode();
            String empCard = student.getEmpCard();
            flg(empCode, empCard, student, null, 1);
        }
        return "处理完成";
    }

    /**
     * 判断卡号信息
     *
     * @param empCode
     * @param empCard
     * @param student
     * @param type    1:学生
     */
    public void flg(String empCode, String empCard, Student student, Teacher teacher, Integer type) {
        if (empCard == null || empCard.equals("0")) {
            if (empCode != null && !empCode.equals("")) {
                // 根据工号查询食堂接口获取卡号，保存到用户中
                String card = httpService.postData(canteenIp + userListUrl + "?emp_code=" + empCode);
                if (card != null && !("").equals(card)) {
                    // 发送给图书馆
                    if (type == 1) {
                        student.setEmpCard(card);
                        studentService.modify(student);
                        libraryData(student.getId(), student.getName(), student.getSex(), student.getMobile(), empCard, type);
                    } else {
                        teacher.setEmpCard(empCard);
                        teacherService.modify(teacher);
                        libraryData(teacher.getId(), teacher.getName(), teacher.getSex(), teacher.getMobile(), empCard, type);
                    }
                }
            } else {
                return;
            }
        } else {
            if (empCode != null && !empCode.equals("")) {
                if (type == 1) {
                    libraryData(student.getId(), student.getName(), student.getSex(), student.getMobile(), empCard, type);
                } else {
                    libraryData(teacher.getId(), teacher.getName(), teacher.getSex(), teacher.getMobile(), empCard, type);
                }
            } else {
                return;
            }
        }
    }

    /**
     * 发送数据
     */
    public void libraryData(Integer id, String name, String sex, String phone, String empCard, Integer type) {
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

        // status: 0: 单条数据 1：批量 2：单条（已获取数据） type 0：老师 1：学生
        httpService.addLibraryData(libraryVo, libraryACnteen, libraryLogin, libraryCreate, null, null, type);
    }

    public Boolean getSexName(String val) {
        if (val != null && !("").equals(val)) {
            return true;
        }
        return false;
    }
}
