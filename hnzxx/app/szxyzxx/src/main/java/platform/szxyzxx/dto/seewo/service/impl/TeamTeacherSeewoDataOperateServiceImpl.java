package platform.szxyzxx.dto.seewo.service.impl;

import com.seewo.open.sdk.OpenApiRequest;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dto.seewo.service.AbsSingleAndBatchSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeamTeacherSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.pojo.seewo.ClassApiSetClassMasterParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.ClassApiSetClassMasterRequest;

import java.util.Map;

/**
 * @author jiaxin
 */
@Service
public class TeamTeacherSeewoDataOperateServiceImpl extends AbsSingleAndBatchSeewoDataOperateService<Map<String,Object>> implements TeamTeacherSeewoDataOperateService {
    @Override
    protected OpenApiRequest createSingleAddRequest(Map<String, Object> data) {
        ClassApiSetClassMasterParam param = new ClassApiSetClassMasterParam();
        ClassApiSetClassMasterParam.JSONRequestBody requestBody = ClassApiSetClassMasterParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ClassApiSetClassMasterParam.MisThirdClassQueryDto query = ClassApiSetClassMasterParam.MisThirdClassQueryDto.builder()
                .classId(data.get("team_id").toString())
                .phone(data.get("teacher_mobile").toString())
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return new ClassApiSetClassMasterRequest(param);
    }
}
