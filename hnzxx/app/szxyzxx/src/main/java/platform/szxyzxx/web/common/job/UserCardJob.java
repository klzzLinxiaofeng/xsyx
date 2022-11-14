package platform.szxyzxx.web.common.job;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.LibraryService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

/**
 * 1.定时读取任务，获取用户信息发布到食堂
 * 2.获取没有发卡的用户，去食堂查询卡号
 * 3.获取用户信息到图书馆
 *
 * @author: yhc
 * @Date: 2020/11/4 19:18
 * @Description:
 */
public class UserCardJob {
    private static final Logger log = LoggerFactory.getLogger(UserCardJob.class);


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

    @Autowired
    @Qualifier("libraryService")
    private LibraryService libraryService;


    private static String canteenIp;
    private static String userListUrl;
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;

    private static String address;
    private static String canteen;

    static {
        String fileName = "System.properties";
        // 图书馆
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");

        // 食堂接口查询卡号信息
        canteenIp = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        userListUrl = PropertiesUtil.getProperty(fileName, "canteen.user.list.url");
        address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");
    }

    /**
     * 1.定时读取任务，获取用户信息发布到食堂
     * 2.获取没有发卡的用户，去食堂查询卡号
     * 3.获取用户信息到图书馆
     * 4.获取用户信息到seewo
     */
    public void jobMethod() {
        //有了实时推送，就不需要定时推送了
        //syncSeendCanteen();
        //getCard();
        syncSendLibrary();
    }

    /**
     * 1.定时读取任务，获取新增/新增之后没有发送到食堂的用户信息发布到食堂
     * 弃用
     */
  /*  public void syncSeendCanteen() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<List<DetailVo>> postStuData = new ArrayList<>();
            List<List<DetailVo>> postTeacherData = new ArrayList<>();
            Student studentVO = new Student();
            studentVO.setIsSendCanteen(0);
            List<Student> studentList = studentService.findStudentByNoSendCanteen(studentVO);

            Teacher teacherVo = new Teacher();
            teacherVo.setIsSendCanteen(0);
            List<Teacher> teacherList = teacherService.findTeacherByNoSendCanteen(teacherVo);
            log.info("定时获取卡号任务：没有发送用户信息到的食堂教师有：{}，学生有：{}", CollUtil.isNotEmpty(teacherList) ? teacherList.size() : 0, CollUtil.isNotEmpty(studentList) ? studentList.size() : 0);
            for (Student student : studentList) {
                List<DetailVo> list = new ArrayList<>();
                // 学生数据
                list.add(new DetailVo("emp_name", student.getName(), "String"));
                list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
                // 学号
                list.add(new DetailVo("emp_code", student.getEmpCode(), "String"));
                // 食堂卡号 要求10位可以字母数字，目前以登录账号，必须要传，但是食堂那边不会保存这个卡号，他们自己发卡，所以查询食堂的卡号，根据emp_code查询
                list.add(new DetailVo("emp_card", student.getEmpCode(), "String"));
                list.add(new DetailVo("dept_code", "000002", "String"));
                // 获取学生的班级信息
                String teamName = student.getDeptName();
                if (teamName != null && !("").equals(teamName)) {
                    list.add(new DetailVo("dept_name", teamName, "String"));
                } else {
                    list.add(new DetailVo("dept_name", "香市小学班级", "String"));
                }
                postStuData.add(list);
            }

            for (Teacher teacher : teacherList) {
                List<DetailVo> list = new ArrayList<>();
                list.add(new DetailVo("dept_code", "000001", "String"));
                list.add(new DetailVo("emp_name", teacher.getName(), "String"));
                list.add(new DetailVo("emp_workdate", simpleDateFormat.format(new Date()), "String"));
                // 工号 只需要传递唯一值, 调用的时候可以查询出对应的用户数据
                list.add(new DetailVo("emp_code", teacher.getEmpCode(), "String"));
                // 食堂卡号 要求10位可以字母数字，目前以登录账号，必须要传，但是食堂那边不会保存这个卡号，他们自己发卡，所以查询食堂的卡号，根据emp_code查询
                list.add(new DetailVo("emp_card", teacher.getEmpCode(), "String"));
                String teamName = teacher.getDeptNames();
                if (teamName != null && !("").equals(teamName)) {
                    list.add(new DetailVo("dept_name", teamName, "String"));
                } else {
                    list.add(new DetailVo("dept_name", "香市一小", "String"));
                }
                postTeacherData.add(list);
            }
            // 判断学生信息是否添加成功 调用远程接口发送数据
            if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {
                // 发送学生数据到食堂接口
                if (postStuData != null && postStuData.size() > 0) {
                    // 批量添加数据 status : 0: 单条数据 1：批量 2：单条（已获取数据） type     数据类型 0：老师 1：学生
                    httpService.addEmploye(null, address + canteen, null, 1, postStuData, 1, null);
                }
                if (postStuData != null && postStuData.size() > 0) {
                    // 批量添加数据 status : 0: 单条数据 1：批量 2：单条（已获取数据） type     数据类型 0：老师 1：学生
                    httpService.addEmploye(null, address + canteen, null, 1, postTeacherData, 0, null);
                }

            } else {
                log.error("调用远程接口失败，请检查配置接口信息是否正确！");
            }
        } catch (Exception e) {
            log.error("发送数据到食堂失败, {}", e);
        }


    }*/


    /**
     * 2.获取没有发卡的用户，去食堂查询卡号
     * 弃用
     */
  /*  public void getCard() {
        try {
            // 获取教师和学生信息生成后没有发卡的用户
            Student studentVO = new Student();
            studentVO.setIsSendCanteen(1);
            studentVO.setEmpCard("0");
            List<Student> studentList = studentService.findStudentByNoSend(studentVO);

            Teacher teacherVo = new Teacher();
            teacherVo.setIsSendCanteen(1);
            teacherVo.setEmpCard("0");
            List<Teacher> teacherList = teacherService.findTeacherByNoSend(teacherVo);
            log.info("定时获取卡号任务：没有食堂卡号教师有：{}，学生有：{}", CollUtil.isNotEmpty(teacherList) ? teacherList.size() : 0, CollUtil.isNotEmpty(studentList) ? studentList.size() : 0);
            for (Teacher teacher : teacherList) {
                // 判断卡号工号
                String empCode = teacher.getEmpCode();
                String empCard = teacher.getEmpCard();
                getUserCard(empCode, empCard, null, teacher, 0);
            }
            for (Student student : studentList) {
                // 判断卡号工号
                String empCode = student.getEmpCode();
                String empCard = student.getEmpCard();
                getUserCard(empCode, empCard, student, null, 1);
            }
        } catch (Exception e) {
            log.error("食堂查询卡号失败, {}", e);
        }
    }*/


    /**
     * 获取用户卡号，根据工号（工号：用户账号名称）
     *
     * @param empCode
     * @param empCard
     * @param student
     * @param type    1:学生
     */
   /* public void getUserCard(String empCode, String empCard, Student student, Teacher teacher, Integer type) {
        if (empCode != null && !empCode.equals("")) {
            // 根据工号查询食堂接口获取卡号，保存到用户中
            String card = httpService.postData(canteenIp + userListUrl + "?emp_code=" + empCode);

            log.info("emp_code："+empCode+",card:"+card);

            if (card != null && !("").equals(card)) {
                if (type == 1) {
                    student.setEmpCard(card);
                    studentService.modify(student);
                } else {
                    teacher.setEmpCard(card);
                    teacherService.modify(teacher);
                }
            }
        }
    }
*/
    /**
     * 3.获取用户信息到图书馆
     * 修改卡号和新增是同一个接口
     */
    public void syncSendLibrary() {
        if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
            // 发送用户信息到图书馆（修改卡号也是同一个接口，直接新增一个用户）
            libraryService.teacherSend(libraryACnteen, libraryLogin, libraryCreate);
            libraryService.studentSend(libraryACnteen, libraryLogin, libraryCreate);
        } else {
            log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
        }
    }





}
