package platform.szxyzxx.dto.seewo.service;

import platform.szxyzxx.dto.seewo.GeneralSeewoDataOerateService;

import java.util.List;
import java.util.Map;

/**
 * 希沃老师信息操作接口
 */
public interface TeacherSeewoDataOperateService extends GeneralSeewoDataOerateService<Map<String,Object>> {

    void updateAllCarNo(List<Map<String,Object>> list);

}
