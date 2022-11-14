package platform.szxyzxx.dto.seewo.service.impl;

import com.seewo.open.sdk.OpenApiRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsRequest;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.*;
import platform.szxyzxx.dto.seewo.service.AbsGeneralSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeacherSeewoDataOperateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author jiaxin
 */
@Service
public class TeacherSeewoDataOperateServiceImpl extends AbsGeneralSeewoDataOperateService<Map<String,Object>> implements TeacherSeewoDataOperateService {

    @Override
    protected OpenApiRequest createQueryAllRequest() {
        TeacherApiListBySchoolUidParam param = new TeacherApiListBySchoolUidParam();
        TeacherApiListBySchoolUidParam.JSONRequestBody requestBody = TeacherApiListBySchoolUidParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        TeacherApiListBySchoolUidParam.MisThirdTeacherQueryDto query = TeacherApiListBySchoolUidParam.MisThirdTeacherQueryDto.builder()
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return new TeacherApiListBySchoolUidRequest(param);
    }




    @Override
    public BasicResponseResult addAll(List<Map<String,Object>> list) {
        if(list.size()>100){
            List<Map<String,Object>> oneBatch=new ArrayList<>(100);
            for (int i = 1; i <= list.size(); i++) {
                oneBatch.add(list.get(i-1));
                if(i%100==0){
                    simpleAddAll(oneBatch);
                    oneBatch=new ArrayList<>(100);
                }
            }
            if(oneBatch.size()>0){
                simpleAddAll(oneBatch);
            }
        }else{
            simpleAddAll(list);
        }

        return null;
    }

    @Override
    protected OpenApiRequest createDeleteRequest(List<Map<String, Object>> delList) {

        List<String> mobiles=new ArrayList<>(delList.size());
        for (Map<String, Object> map : delList) {
            mobiles.add(map.get("phone").toString());
        }

        TeacherApiDeleteTeachersParam param = new TeacherApiDeleteTeachersParam();
        TeacherApiDeleteTeachersParam.JSONRequestBody requestBody = TeacherApiDeleteTeachersParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        TeacherApiDeleteTeachersParam.MisThirdTeacherQueryDto query = TeacherApiDeleteTeachersParam.MisThirdTeacherQueryDto.builder()
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .phones(mobiles)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return new TeacherApiDeleteTeachersRequest(param);
    }

    @Override
    public void updateAllCarNo(List<Map<String,Object>> list) {
        CardApiSaveOrForceUpdateCardsParam param = new CardApiSaveOrForceUpdateCardsParam();
        CardApiSaveOrForceUpdateCardsParam.RequestBody requestBody = CardApiSaveOrForceUpdateCardsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        CardApiSaveOrForceUpdateCardsParam.Object query = CardApiSaveOrForceUpdateCardsParam.Object.builder()
                .appId(getAppId())
                .userType("1")
                .type("0")
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        List<CardApiSaveOrForceUpdateCardsParam.CardsItem> cardsItems=new ArrayList<>(list.size());
        for (Map<String,Object> map : list) {
            String empCard=(String) map.get("emp_card");
            if(StringUtils.isEmpty(empCard) || empCard.equals("0")){
                continue;
            }
            CardApiSaveOrForceUpdateCardsParam.CardsItem cards = CardApiSaveOrForceUpdateCardsParam.CardsItem.builder()
                    .number(map.get("mobile").toString())
                    .cardId(empCard)
                    .name(map.get("name").toString())
                    .build();
            cardsItems.add(cards);
        }
        query.setCards(cardsItems);
        param.setRequestBody(requestBody);
        invoke(new CardApiSaveOrForceUpdateCardsRequest(param));
    }

    private BasicResponseResult simpleAddAll(List<Map<String, Object>> list) {
        TeacherServiceBatchSaveOrUpdateTeacherParam param = new TeacherServiceBatchSaveOrUpdateTeacherParam();
        TeacherServiceBatchSaveOrUpdateTeacherParam.RequestBody requestBody = TeacherServiceBatchSaveOrUpdateTeacherParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        TeacherServiceBatchSaveOrUpdateTeacherParam.Query query = TeacherServiceBatchSaveOrUpdateTeacherParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        List<TeacherServiceBatchSaveOrUpdateTeacherParam.Teachers> teachersList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            TeacherServiceBatchSaveOrUpdateTeacherParam.Teachers teachers = TeacherServiceBatchSaveOrUpdateTeacherParam.Teachers.builder()
                    .account(map.get("mobile").toString())
                    .name(map.get("name").toString())
                    .accountType("phone")
                    .build();
            teachersList.add(teachers);
        }
        query.setTeachers(teachersList);
        param.setRequestBody(requestBody);
        return invoke(new TeacherServiceBatchSaveOrUpdateTeacherRequest(param));
    }




//    @Override
//    public List<Map<String, Object>> queryAll() {
//        int pageSize=200;
//        BasicResponseResult result=pagingQuery(1,pageSize);
//        List<Map<String, Object>> list=result.getPagingResult();
//        int totalPageCount=result.getPagingTotalCount();
//        if(totalPageCount>1){
//            for(int i=2;i<=totalPageCount;i++){
//                list.addAll(pagingQuery(i,pageSize).getPagingResult());
//            }
//        }
//        return list;
//    }


//    private BasicResponseResult pagingQuery(int pageNum, int pageSize){
//        TeacherServiceListSchoolTeacherConditionalParam param = new TeacherServiceListSchoolTeacherConditionalParam();
//        TeacherServiceListSchoolTeacherConditionalParam.RequestBody requestBody = TeacherServiceListSchoolTeacherConditionalParam.RequestBody.builder()
//                .build();
//        param.setRequestBody(requestBody);
//        TeacherServiceListSchoolTeacherConditionalParam.Query query = TeacherServiceListSchoolTeacherConditionalParam.Query.builder()
//                .appId("")
//                .schoolUid("")
//                .page(pageNum)
//                .pageSize(pageSize)
//                .build();
//        requestBody.setQuery(query);
//        param.setRequestBody(requestBody);
//        return invoke( new TeacherServiceListSchoolTeacherConditionalRequest(param));
//    }

    @Override
    protected OpenApiRequest createAddAllRequest(List<Map<String, Object>> list) {return null; }
    @Override
    protected OpenApiRequest createUpdateRequest(List<Map<String, Object>> updateList) {return null; }
}
