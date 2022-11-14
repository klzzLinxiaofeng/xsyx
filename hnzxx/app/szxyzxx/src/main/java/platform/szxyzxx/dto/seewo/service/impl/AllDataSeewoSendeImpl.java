package platform.szxyzxx.dto.seewo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dto.seewo.SeewoDataSender;
import platform.szxyzxx.dto.seewo.service.*;


/**
 * 全部学生、老师、班级数据的seewo发送者
 */
@Service
public class AllDataSeewoSendeImpl implements AllDataSeewoSender {

    @Autowired
    private TeamSeewoDataSender teamDataSender;
    @Autowired
    private StudentSeewoDataSender studentSeewoDataSender;

    @Autowired
    private TeacherSeewoDataSender teacherSeewoDataSender;

    @Autowired
    private TeamTeacherSeewoDataSender teamTeacherSeewoDataSender;

    @Override
    public void sendNotSynchronizedDataAndUpdate() {
        teamDataSender.sendNotSynchronizedDataAndUpdate();
        studentSeewoDataSender.sendNotSynchronizedDataAndUpdate();
        teacherSeewoDataSender.sendNotSynchronizedDataAndUpdate();
        teamTeacherSeewoDataSender.sendNotSynchronizedDataAndUpdate();
    }
}
