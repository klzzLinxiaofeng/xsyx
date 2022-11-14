package platform.szxyzxx.web.seewo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.szxyzxx.dto.seewo.service.*;

import java.util.List;

/**
 * 同步希沃数据
 */
@RequestMapping("/seewo/sync")
@RestController
public class SeewoController {

    @Autowired
    private TeamSeewoDataSender teamDataSender;
    @Autowired
    private StudentSeewoDataSender studentSeewoDataSender;

    @Autowired
    private TeacherSeewoDataSender teacherSeewoDataSender;

    @Autowired
    private TeamTeacherSeewoDataSender teamTeacherSeewoDataSender;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherCardSeewoDataOperateService teacherCardSeewoDataOperateService;
    @Autowired
    private StudentCardSeewoDataOperateService studentCardSeewoDataOperateService;

    @Autowired
    private TeamSeewoSyncService teamSeewoSyncService;
    @Autowired
    private TeacherSeewoSyncService teacherSeewoSyncService;

    @Autowired
    private StudentSeewoSyncService studentSeewoSyncService;


    /**
     * 同步班级
     * @return
     */
    @RequestMapping("/team")
    public String testTeam(){
        //teamDataSender.sendNotSynchronizedDataAndUpdate();

        teamSeewoSyncService.syncData();

        return "success";
    }

    /**
     * 同步学生
     * @return
     */
    @RequestMapping("/stu")
    public String testStudent(){
        //studentSeewoDataSender.sendNotSynchronizedDataAndUpdate();
        studentSeewoSyncService.syncData();
        return "success";
    }

    /**
     * 同步老师
     * @return
     */
    @RequestMapping("/teacher")
    public String testTeacher(){
        //teacherSeewoDataSender.sendNotSynchronizedDataAndUpdate();
        teacherSeewoSyncService.syncData();
        return "success";
    }

    /**
     * 同步班级班主任
     * @return
     */
    @RequestMapping("/tt")
    public String tt(){
        teamTeacherSeewoDataSender.sendNotSynchronizedDataAndUpdate();
        return "success";
    }

    /**
     * 更新老师一卡通卡号
     * @return
     */
    @RequestMapping("/updateTeacherCard")
    public String updateTeacherCard(){
        List<Teacher> teacherList=teacherService.findCanSendSeewo();
        teacherCardSeewoDataOperateService.batchAdd(teacherList);
        return "success";
    }

    /**
     * 更新学生一卡通卡号
     * @return
     */
    @RequestMapping("/updateStudentCard")
    public String updateStudentCard(){
        List<Student> students=studentService.findCanSendSeewo();
        studentCardSeewoDataOperateService.batchAdd(students);
        return "success";
    }


}
