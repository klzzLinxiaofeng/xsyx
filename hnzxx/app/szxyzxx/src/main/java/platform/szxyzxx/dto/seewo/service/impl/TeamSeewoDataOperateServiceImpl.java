package platform.szxyzxx.dto.seewo.service.impl;

import com.seewo.open.sdk.OpenApiRequest;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.*;
import platform.szxyzxx.dto.seewo.service.AbsGeneralSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeamSeewoDataOperateService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author jiaxin
 */
@Service
public class TeamSeewoDataOperateServiceImpl extends AbsGeneralSeewoDataOperateService<Map<String,Object>> implements TeamSeewoDataOperateService {
    @Override
    protected OpenApiRequest createDeleteRequest(List<Map<String, Object>> delList) {
        List<String> delIds=new ArrayList<>(delList.size());
        for (Map<String, Object> map : delList) {
            delIds.add(map.get("uid").toString());
        }
        ClassApiDeleteClassesParam param = new ClassApiDeleteClassesParam();
        ClassApiDeleteClassesParam.JSONRequestBody requestBody = ClassApiDeleteClassesParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ClassApiDeleteClassesParam.MisThirdClassQueryDto query = ClassApiDeleteClassesParam.MisThirdClassQueryDto.builder()
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .seewoClassIds(delIds)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return new ClassApiDeleteClassesRequest(param);
    }

    @Override
    protected OpenApiRequest createUpdateRequest(List<Map<String, Object>> updateList) {
        ClassServiceBatchUpdateClassParam param = new ClassServiceBatchUpdateClassParam();
        ClassServiceBatchUpdateClassParam.RequestBody requestBody = ClassServiceBatchUpdateClassParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ClassServiceBatchUpdateClassParam.Query query = ClassServiceBatchUpdateClassParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);

        List<ClassServiceBatchUpdateClassParam.ClassListItem> upParamList=new ArrayList<>(updateList.size());
        for (Map<String, Object> map : updateList) {
            ClassServiceBatchUpdateClassParam.ClassListItem clssParam = ClassServiceBatchUpdateClassParam.ClassListItem.builder()
                    .classUid((String) map.get("uid"))
                    .stageCode("CodeSchoolStage_0")
                    .grade((Integer) map.get("grade"))
                    .gradeYear((Integer) map.get("gradeYear"))
                    .clazz((Integer) map.get("clazz"))
                    .nickName((String) map.get("nickName"))
                    .build();
            upParamList.add(clssParam);
        }
        query.setClassList(upParamList);
        param.setRequestBody(requestBody);
        return new ClassServiceBatchUpdateClassRequest(param);
    }



    @Override
    public OpenApiRequest createAddAllRequest(List<Map<String, Object>> list) {
        ClassServiceBatchSaveClassParam param = new ClassServiceBatchSaveClassParam();
        ClassServiceBatchSaveClassParam.RequestBody requestBody = ClassServiceBatchSaveClassParam.RequestBody.builder().build();
        param.setRequestBody(requestBody);
        ClassServiceBatchSaveClassParam.Query query = ClassServiceBatchSaveClassParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        List<ClassServiceBatchSaveClassParam.ClassListItem> paramList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            ClassServiceBatchSaveClassParam.ClassListItem classListParam = ClassServiceBatchSaveClassParam.ClassListItem.builder()
                    .type(1)
                    .stageCode("CodeSchoolStage_0")
                    .clazz(Integer.valueOf(map.get("teamNumber").toString()))//班级数字
                    .grade(Integer.valueOf(map.get("gradeNumber").toString())) //年级数字
                    .gradeYear(Integer.valueOf(map.get("schoolYear").toString()))//学年
                    .nickName((String) map.get("teamName"))
                    .build();
            paramList.add(classListParam);
        }
        query.setClassList(paramList);
        param.setRequestBody(requestBody);
        return new ClassServiceBatchSaveClassRequest(param);

    }

    @Override
    public OpenApiRequest createQueryAllRequest() {
        ClassApiQueryClassDetailBySchoolUidParam param = new ClassApiQueryClassDetailBySchoolUidParam();
        ClassApiQueryClassDetailBySchoolUidParam.JSONRequestBody requestBody = ClassApiQueryClassDetailBySchoolUidParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ClassApiQueryClassDetailBySchoolUidParam.MisThirdClassQueryDto query = ClassApiQueryClassDetailBySchoolUidParam.MisThirdClassQueryDto.builder()
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return  new ClassApiQueryClassDetailBySchoolUidRequest(param);
    }


    @Override
    public void removeMasterRelation(String seewoClassId) {
        ClassApiUnsetClassMasterParam param = new ClassApiUnsetClassMasterParam();
        ClassApiUnsetClassMasterParam.JSONRequestBody requestBody = ClassApiUnsetClassMasterParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ClassApiUnsetClassMasterParam.MisThirdClassQueryDto query = ClassApiUnsetClassMasterParam.MisThirdClassQueryDto.builder()
                //.classId(teamId)
                .seewoClassId(seewoClassId)
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        invoke( new ClassApiUnsetClassMasterRequest(param));
    }

    @Override
    public void setMaster(String seewoTeamId,String mobile) {
        TeacherServiceBatchSetClassMastersParam param = new TeacherServiceBatchSetClassMastersParam();
        TeacherServiceBatchSetClassMastersParam.RequestBody requestBody = TeacherServiceBatchSetClassMastersParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        TeacherServiceBatchSetClassMastersParam.Query query = TeacherServiceBatchSetClassMastersParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .classUid(seewoTeamId)
                .userPhones(Collections.singletonList(mobile))
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        invoke(new TeacherServiceBatchSetClassMastersRequest(param));
    }
}
