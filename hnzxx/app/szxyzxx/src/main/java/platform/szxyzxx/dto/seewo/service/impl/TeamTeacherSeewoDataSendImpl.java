package platform.szxyzxx.dto.seewo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.szxyzxx.dto.seewo.service.AbsBasicSeewoMapDataSender;
import platform.szxyzxx.dto.seewo.service.TeamTeacherSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeamTeacherSeewoDataSender;
import platform.szxyzxx.dto.seewo.BatchSeewoDataOperateService;

import java.util.List;
import java.util.Map;

/**
 * @author jiaxin
 */
@Service
public class TeamTeacherSeewoDataSendImpl extends AbsBasicSeewoMapDataSender implements TeamTeacherSeewoDataSender {
    @Autowired
    TeamTeacherService teamTeacherService;
    @Autowired
    private TeamTeacherSeewoDataOperateService operateService;

    @Override
    protected String getDataName() {
        return "教师班级数据";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }
    @Override
    protected BatchSeewoDataOperateService<Map<String,Object>> getDataOperator() {
        return operateService;
    }

    @Override
    protected List<Map<String,Object>> querySendData() {
        return teamTeacherService.findNotSendSeewo();
    }

    @Override
    protected void updateSendSuccessIdsStatus(Integer[] successfulIdArr) {
        teamTeacherService.updateSeewoStatus(successfulIdArr);
    }
}
