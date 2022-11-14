package platform.szxyzxx.dto.seewo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.bo.StudentParentInfo;
import platform.szxyzxx.dto.seewo.SeewoOperateClient;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsRequest;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.*;
import platform.szxyzxx.dto.seewo.service.NewStudentSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeamSeewoDataOperateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class NewStudentSeewoDataOperateServiceImpl  extends SeewoOperateClient implements NewStudentSeewoDataOperateService {

    @Autowired
    private TeamSeewoDataOperateService teamSeewoDataOperateService;


    @Override
    public List<Map<String, Object>> queryByClassUid(String classUid){
        StudentServiceListStudentByClassParam param = new StudentServiceListStudentByClassParam();
        StudentServiceListStudentByClassParam.RequestBody requestBody = StudentServiceListStudentByClassParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        StudentServiceListStudentByClassParam.Query query = StudentServiceListStudentByClassParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .classUid(classUid)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return (List<Map<String, Object>>)invoke(new StudentServiceListStudentByClassRequest(param)).getData();
    }



    public List<Map<String,Object>> queryAll() {
        List<Map<String,Object>> teamList=teamSeewoDataOperateService.queryAll();
        List<Map<String,Object>> stuList=new ArrayList<>(1200);
        for (Map<String, Object> classMap : teamList) {
            List<Map<String,Object>> classStuList=queryByClassUid(classMap.get("uid").toString());
            if(classStuList.size()>0) {
                setStuClassInfo(classMap, classStuList);
                stuList.addAll(classStuList);
            }
        }
        return null;
    }

    @Override
    public BasicResponseResult addAll(List<StudentInfo> list, String classUid) {
        StudentServiceBatchSaveClassStudentsParam param = new StudentServiceBatchSaveClassStudentsParam();
        StudentServiceBatchSaveClassStudentsParam.RequestBody requestBody = StudentServiceBatchSaveClassStudentsParam.RequestBody.builder().build();
        param.setRequestBody(requestBody);
        StudentServiceBatchSaveClassStudentsParam.Query query = StudentServiceBatchSaveClassStudentsParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .classUid(classUid)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        List<StudentServiceBatchSaveClassStudentsParam.StudentsItem> students=new ArrayList<>();
        for(int i=1;i<=list.size();i++){
            StudentInfo  stu=list.get(i-1);
            String empCar=stu.getCardCode();
            StudentServiceBatchSaveClassStudentsParam.StudentsItem.StudentsItemBuilder builder=StudentServiceBatchSaveClassStudentsParam.StudentsItem.builder()
                    .studentName(stu.getStudentName())
                    .studentCode(stu.getStudentCode().toString());
            if(StringUtils.isNotEmpty(empCar) && !empCar.equals("0") ){
                builder.cardNo(empCar);
            }
            //学生列表，最大100条
            StudentServiceBatchSaveClassStudentsParam.StudentsItem student =builder.build();
            students.add(student);
            if(i%100==0){
                query.setStudents(students);
                invoke(new StudentServiceBatchSaveClassStudentsRequest(param));
                students=new ArrayList<>();
            }
        }
        if(students.size()>0){
            query.setStudents(students);
            invoke(new StudentServiceBatchSaveClassStudentsRequest(param));
        }


        //添加家长信息
        addParents(list);


        return null;
    }




    @Override
    public BasicResponseResult deleteAll(List<Map<String, Object>> seewoQueryList,String classUid) {
        StudentApiUnbindStudentToClassParam param = new StudentApiUnbindStudentToClassParam();
        StudentApiUnbindStudentToClassParam.JSONRequestBody requestBody = StudentApiUnbindStudentToClassParam.JSONRequestBody.builder().build();
        param.setRequestBody(requestBody);
        List<String> stuCodes=getAllStringVal("studentCode",seewoQueryList);
        StudentApiUnbindStudentToClassParam.MisThirdStudentQueryDto query = StudentApiUnbindStudentToClassParam.MisThirdStudentQueryDto.builder()
                //.classId("classId")
                .seewoClassId(classUid)
                .studentCodes(stuCodes)
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        return invoke(new StudentApiUnbindStudentToClassRequest(param));
    }

    @Override
    public BasicResponseResult updateCarNo(List<Map<String,Object>> seewoQueryList) {

        CardApiSaveOrForceUpdateCardsParam param = new CardApiSaveOrForceUpdateCardsParam();
        CardApiSaveOrForceUpdateCardsParam.RequestBody requestBody = CardApiSaveOrForceUpdateCardsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        CardApiSaveOrForceUpdateCardsParam.Object query = CardApiSaveOrForceUpdateCardsParam.Object.builder()
                .appId(getAppId())
                .userType("0")
                .type("0")
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        List<CardApiSaveOrForceUpdateCardsParam.CardsItem> cards=new ArrayList<>(seewoQueryList.size());
        for (Map<String, Object> map : seewoQueryList) {
            CardApiSaveOrForceUpdateCardsParam.CardsItem card = CardApiSaveOrForceUpdateCardsParam.CardsItem.builder()
                    .number(map.get("studentCode").toString())
                    .cardId(map.get("updateCarId").toString())
                    .name(map.get("userName").toString())
                    .build();
            cards.add(card);
        }

        query.setCards(cards);
        param.setRequestBody(requestBody);
         return invoke(new CardApiSaveOrForceUpdateCardsRequest(param));
    }

    private void addParents(List<StudentInfo> list){

        ParentServiceBatchSaveOrUpdateParentsParam param = new ParentServiceBatchSaveOrUpdateParentsParam();
        ParentServiceBatchSaveOrUpdateParentsParam.RequestBody requestBody = ParentServiceBatchSaveOrUpdateParentsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ParentServiceBatchSaveOrUpdateParentsParam.Query query = ParentServiceBatchSaveOrUpdateParentsParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        //学生与家长列表，每次最大100条
        List<ParentServiceBatchSaveOrUpdateParentsParam.StudentParentsItem> studentParents=new ArrayList<>(50);

        for(StudentInfo stu:list){
            List<StudentParentInfo> parentList=stu.getParentList();
            if(parentList==null ||parentList.size()==0){
                continue;
            }
            ParentServiceBatchSaveOrUpdateParentsParam.StudentParentsItem studentParent = ParentServiceBatchSaveOrUpdateParentsParam.StudentParentsItem.builder()
                    .studentCode(stu.getStudentCode().toString())
                    .build();


            int parentEndIndex=parentList.size();
            if(parentEndIndex>4){
                parentEndIndex=4;
            }
            List<ParentServiceBatchSaveOrUpdateParentsParam.ParentsItem> parents=new ArrayList<>(parentEndIndex);
            //家长列表，最多4个
            for(int j=0;j<parentEndIndex;j++){
                StudentParentInfo parentInfo=parentList.get(j);
                ParentServiceBatchSaveOrUpdateParentsParam.ParentsItem parent = ParentServiceBatchSaveOrUpdateParentsParam.ParentsItem.builder()
                        .name(parentInfo.getParentName())
                        .phone(parentInfo.getParentPhone())
                        .index(j)
                        .build();
                parents.add(parent);
            }
            studentParent.setParents(parents);
            studentParents.add(studentParent);
            if(studentParents.size()>=100){
                query.setStudentParents(studentParents);
                invoke(new ParentServiceBatchSaveOrUpdateParentsRequest(param));
                studentParents=new ArrayList<>();
            }
        }

        if(studentParents.size()>0){
            query.setStudentParents(studentParents);
            invoke(new ParentServiceBatchSaveOrUpdateParentsRequest(param));
        }

    }


    //设置学生班级信息
    private void setStuClassInfo(Map<String,Object> classMap,List<Map<String,Object>> stuList){
        for (Map<String, Object> stuMap : stuList) {
            //班级uid
            stuMap.put("classUid",classMap.get("uid").toString());
            //学年
            stuMap.put("gradeYear",classMap.get("gradeYear").toString());
            //年级数
            stuMap.put("grade",classMap.get("grade").toString());
            //班级数
            stuMap.put("clazz",classMap.get("clazz").toString());
        }
    }



    private List<String> getAllStringVal(String key,List<Map<String,Object>> list){
        List<String> valList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            valList.add(map.get(key).toString());
        }
        return valList;
    }

}
