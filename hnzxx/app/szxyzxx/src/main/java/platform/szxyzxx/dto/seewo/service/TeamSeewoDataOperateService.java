package platform.szxyzxx.dto.seewo.service;

import platform.szxyzxx.dto.seewo.GeneralSeewoDataOerateService;

import java.util.Map;

/**
 * 希沃班级数据操作接口
 */
public interface TeamSeewoDataOperateService extends GeneralSeewoDataOerateService<Map<String,Object>> {
    void removeMasterRelation(String seewoTeamId);
    void setMaster(String seewoTeamId,String mobile);
}
