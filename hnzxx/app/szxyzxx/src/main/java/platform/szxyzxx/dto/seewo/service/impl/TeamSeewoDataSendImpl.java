package platform.szxyzxx.dto.seewo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.szxyzxx.dto.seewo.service.AbsBasicSeewoMapDataSender;
import platform.szxyzxx.dto.seewo.service.TeamSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeamSeewoDataSender;
import platform.szxyzxx.dto.seewo.BatchSeewoDataOperateService;

import java.util.List;
import java.util.Map;

/**
 * @author jiaxin
 */
@Service
public class TeamSeewoDataSendImpl extends AbsBasicSeewoMapDataSender implements TeamSeewoDataSender {
    @Autowired
    TeamService teamService;
    @Autowired
    private TeamSeewoDataOperateService operateService;

    @Override
    protected String getDataName() {
        return "班级数据";
    }
    @Override
    protected BatchSeewoDataOperateService<Map<String, Object>> getDataOperator() {
        //return operateService;
        return null;
    }

    @Override
    protected List<Map<String, Object>> querySendData() {
        return teamService.getNotSendSendSeewo();
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

    @Override
    protected void updateSendSuccessIdsStatus(Integer[] successfulIdArr) {
        teamService.updateAsSendSeewoByIds(successfulIdArr);
    }
}
