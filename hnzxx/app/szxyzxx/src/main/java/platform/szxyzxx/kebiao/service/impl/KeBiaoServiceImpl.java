package platform.szxyzxx.kebiao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seewo.open.sdk.DefaultSeewoClient;
import com.seewo.open.sdk.SeewoClient;
import com.seewo.open.sdk.auth.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.SyllabusDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.dao.TeamDao;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.vo.SyllabusVo;
import platform.szxyzxx.dto.seewo.pojo.*;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.ClassServiceListByConditionalParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.ClassServiceListByConditionalRequest;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.ClassServiceListByConditionalResult;
import platform.szxyzxx.kebiao.service.KeBiaoService;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeBiaoServiceImpl implements KeBiaoService {

    private static final String SEEWO_PROPERTY_NAME = "seewo.properties";

    private static final String SCHOOL_CODE = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolCode");

    private static final String SCHOOL_UID= PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolUid");

    private static final String APP_ID = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.appid");

    private static final String SECRET = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.secret");

    private static final String SERVER = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.server");
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private SyllabusDao syllabusDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeamDao teamDao;

    @Override
    public String findBySeeoween(UserInfo userInfo,Integer teamId,Integer gradeId,String schoolYear,String teamCode) {
        //根据年级id查出上课时间
        if(gradeId==null || teamId==null ||schoolYear==null || teamCode==null){
            //参数为空
            return "0";
        }
        //每周上课那几天
        List<Map<String,Object>> mapList1=basicSQLService.find("select days_plan from pj_syllabus where school_year ='"+schoolYear +"' and term_code ='"+teamCode+"' and school_id="+userInfo.getSchoolId()+"  and team_id="+teamId);
        //每天上课时间+节数
        List<SyllabusVo> syllabusVoList=syllabusDao.findByAllKeBiao(userInfo.getSchoolId(),teamId,schoolYear,teamCode);
        String [] array=mapList1.get(0).get("days_plan").toString().split(",");
        System.out.println("上课周数"+array.length);
        if(syllabusVoList.size()<=0){
            //该班级暂未设置课表
            return "2";
        }
        JSONArray jsonArray=JSONArray.parseArray(syllabusVoList.get(0).getLessonTimes());
        int zongjieshu=syllabusVoList.get(0).getLessonOfMorning()+syllabusVoList.get(0).getLessonOfAfternoon()+syllabusVoList.get(0).getLessonOfEvening();
        System.out.println("id"+syllabusVoList.get(0).getId());
        List<TimetableServiceSaveRoomTimetableParam.TimetableItemsItem> list=new ArrayList();
        for(int zhoushu=0;zhoushu<array.length;zhoushu++){
            System.out.println("周数"+Integer.parseInt(array[zhoushu]));
            for(int jieshu=0;jieshu<zongjieshu;jieshu++) {
                Integer zhoushuNumber = Integer.parseInt(array[zhoushu]);
                JSONObject jsonObject3 = (JSONObject) jsonArray.get(jieshu);
                if (zhoushuNumber == 0) {
                    zhoushuNumber = 7;
                }
                //拿到周数和节数去查询对应的课程详情
                System.out.println("节数" + jieshu + 1);
                SyllabusVo xiangqing = syllabusDao.findByXiangQing(syllabusVoList.get(0).getId(), jieshu + 1, Integer.parseInt(array[zhoushu]));
                //课表项列表，不超过200条，每次将覆盖上一次的场地课表
                if (xiangqing != null) {
                    System.out.println("详情id" + xiangqing.getTeacherId());
                    Teacher teacher = teacherDao.findById(xiangqing.getTeacherId());
                    TimetableServiceSaveRoomTimetableParam.TimetableItemsItem timetableItems = TimetableServiceSaveRoomTimetableParam.TimetableItemsItem.builder()
                            .sectionIndex(jieshu)
                            .dayIndex(zhoushuNumber)
                            .startTime(jsonObject3.get("startTime").toString())
                            .endTime(jsonObject3.get("endTime").toString())
                            .strategy("ALL")
                            .subjectName(xiangqing.getSubjectName())
                            .build();
                    if (teacher != null) {
                        timetableItems.setTeacherName(teacher.getName());
                    }
                    list.add(timetableItems);
                }else{
                    return "课程表不完整,请设置完整";
                }
            }
        }
        List<Map<String,Object>> sweeoTeamlist=findSeewoClass();
        //拿到课表对应的班级名成进行转换
        Team  name=teamDao.findById(teamId);
        String teamName= findByTeam(name.getName());
        if(teamName==null){
            return "班级转换失败";
        }
        //获取希沃对应的班级uid
        String teamUid=findBySwooUUid(sweeoTeamlist,teamName);
        if(teamUid==null){
            return "未在希沃找到对应的班级";
        }
        //场地uuid
       String roomUuid= findByChangDiId(teamUid);
        List<Map<String,Object>> sweeoChangDi=findSeewoChangDiXingXi(roomUuid);
        String sweeoAdd=AddSweooWo(list,sweeoChangDi);

        return sweeoAdd;
    }


 //添加课表到希沃
 public String AddSweooWo(List<TimetableServiceSaveRoomTimetableParam.TimetableItemsItem> list, List<Map<String,Object>> sweeoChangDi){
    //初始化客户端
    SeewoClient seewoClient = new DefaultSeewoClient(new Account(APP_ID, SERVER));
    TimetableServiceSaveRoomTimetableParam param = new TimetableServiceSaveRoomTimetableParam();
     //响应体，MimeType为 application/json
    TimetableServiceSaveRoomTimetableParam.RequestBody requestBody = TimetableServiceSaveRoomTimetableParam.RequestBody.builder()
            .build();
    param.setRequestBody(requestBody);
    //请求内容
    TimetableServiceSaveRoomTimetableParam.Query query = TimetableServiceSaveRoomTimetableParam.Query.builder()
            .roomNum(sweeoChangDi.get(0).get("roomNum").toString())
            .unitUid(SCHOOL_UID)
            .roomName(sweeoChangDi.get(0).get("roomName").toString())
            .build();
     query.setTimetableItems(list);
     for(TimetableServiceSaveRoomTimetableParam.TimetableItemsItem aa:list){
         System.out.println(aa.toString());
     }
    requestBody.setQuery(query);
    param.setRequestBody(requestBody);
    TimetableServiceSaveRoomTimetableRequest request = new TimetableServiceSaveRoomTimetableRequest(param);
    System.out.println("入参：" +request.toString());
    TimetableServiceSaveRoomTimetableResult result = seewoClient.invoke(request);
    System.out.println("出参：" +result);
    return "success";
}


//根据场地id获取场地信息
public List<Map<String,Object>> findSeewoChangDiXingXi(String uuid) {
    //初始化客户端
    SeewoClient seewoClient = new DefaultSeewoClient(new Account(APP_ID, SERVER));
    BuildingRoomServiceListRoomConditionParam param = new BuildingRoomServiceListRoomConditionParam();
//请求体，MimeType为 application/json
    BuildingRoomServiceListRoomConditionParam.JSONRequestBody requestBody = BuildingRoomServiceListRoomConditionParam.JSONRequestBody.builder()
            .build();
    param.setRequestBody(requestBody);
//
    BuildingRoomServiceListRoomConditionParam.ThirdListRoomQuery query = BuildingRoomServiceListRoomConditionParam.ThirdListRoomQuery.builder()
            .appId(APP_ID)
            .unitUid(SCHOOL_UID)
            .roomUid(uuid)
            .build();
    requestBody.setQuery(query);
    param.setRequestBody(requestBody);
    BuildingRoomServiceListRoomConditionRequest request = new BuildingRoomServiceListRoomConditionRequest(param);

//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
    BuildingRoomServiceListRoomConditionResult result = seewoClient.invoke(request);
    JSONObject jsonObject=JSONObject.parseObject(result.getBody());
    JSONArray jsonArray=JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("data")));
    List<Map<String,Object>> list=new ArrayList<>();
    for(int i=0;i<jsonArray.size();i++){
        // System.out.println(jsonArray.get(i));
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject3= (JSONObject) jsonArray.get(i);
        map.put("roomNum",jsonObject3.get("roomNum"));
        map.put("roomUid",jsonObject3.get("roomUid"));
        map.put("roomName",jsonObject3.get("roomName"));
        list.add(map);
    }
    return list;
}

    //获取希沃所有的班级
    public List<Map<String,Object>> findSeewoClass()  {
        //初始化客户端
        SeewoClient seewoClient = new DefaultSeewoClient(new Account(APP_ID, SECRET));
        ClassServiceListByConditionalParam param = new ClassServiceListByConditionalParam();
//响应体，MimeType为 application/json
        ClassServiceListByConditionalParam.RequestBody requestBody = ClassServiceListByConditionalParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//query
        ClassServiceListByConditionalParam.Query query = ClassServiceListByConditionalParam.Query.builder()
                .appId(APP_ID)
                .schoolUid(SCHOOL_UID)
                .pageSize(100)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        ClassServiceListByConditionalRequest request = new ClassServiceListByConditionalRequest(param);
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com")
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
        ClassServiceListByConditionalResult result = seewoClient.invoke(request);
        JSONObject jsonObject = JSONObject.parseObject(result.getBody());
        JSONObject jsonObject2 = (JSONObject) jsonObject.get("data");
        List<Map<String, Object>> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(jsonObject2.get("result")));
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String,Object> map=new HashMap<>();
            JSONObject jsonObject3= (JSONObject) jsonArray.get(i);
          map.put("nickName",jsonObject3.get("nickName"));
            map.put("uid",jsonObject3.get("uid"));
            list.add(map);
        }
        return list;
    }
    //获取希沃所有的场地，并通过班级id匹配
    public String findByChangDiId(String uuid){
        List<Map<String,Object>> list=findSeewoChangDi();
        for(Map<String,Object> aa:list){
            if(aa.get("classUid").toString() .equals(uuid)){
                return aa.get("buildingRoomUid").toString();
            }
        }
        return null;
    }

    //获取希沃所有的场地
    public List<Map<String,Object>> findSeewoChangDi(){
        SeewoClient seewoClient = new DefaultSeewoClient(new Account(APP_ID, SECRET));
        platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalParam param = new platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalParam();
        //请求体，MimeType为 application/json
        platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalParam.JSONRequestBody requestBody = platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//入参条件
        platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalParam.ThirdClassRoomRelQuery query = ClassRoomServiceListClassRoomRelConditionalParam.ThirdClassRoomRelQuery.builder()
                .appId(APP_ID)
                .unitUid(SCHOOL_UID)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalRequest request = new ClassRoomServiceListClassRoomRelConditionalRequest(param);
        ClassRoomServiceListClassRoomRelConditionalResult result = seewoClient.invoke(request);
        JSONObject jsonObject=JSONObject.parseObject(result.getBody());
        List<Map<String,Object>> list=new ArrayList<>();
        JSONArray jsonArray=JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("data")));
        for(int i=0;i<jsonArray.size();i++){
            Map<String,Object> map=new HashMap<>();
            JSONObject jsonObject3= (JSONObject) jsonArray.get(i);
            map.put("buildingRoomUid",jsonObject3.get("buildingRoomUid"));
            map.put("classUid",jsonObject3.get("classUid"));
            list.add(map);
        }
        return list;
    }
    // 转换班级信息
    public String findByTeam(String teamName){
        if(teamName.equals("一年级(1)班")){
            return "一年级1班";
        }if(teamName.equals("一年级(2)班")){
            return "一年级2班";
        }if(teamName.equals("一年级(3)班")){
            return "一年级3班";
        }if(teamName.equals("一年级(4)班")){
            return "一年级4班";
        }if(teamName.equals("一年级(5)班")){
            return "一年级5班";
        }if(teamName.equals("一年级(6)班")){
            return "一年级6班";
        }if(teamName.equals("一年级(7)班")){
            return "一年级7班";
        }if(teamName.equals("一年级(8)班")){
            return "一年级8班";
        }if(teamName.equals("一年级(9)班")){
            return "一年级9班";
        }if(teamName.equals("一年级(10)班")){
            return "一年级10班";
        }if(teamName.equals("二年级(1)班")){
            return "二年级1班";
        }if(teamName.equals("二年级(2)班")){
            return "二年级2班";
        }if(teamName.equals("二年级(3)班")){
            return "二年级3班";
        }if(teamName.equals("二年级(4)班")){
            return "二年级4班";
        }if(teamName.equals("二年级(5)班")){
            return "二年级5班";
        }if(teamName.equals("二年级(6)班")){
            return "二年级6班";
        }if(teamName.equals("二年级(7)班")){
            return "二年级7班";
        }if(teamName.equals("二年级(8)班")){
            return "二年级8班";
        }if(teamName.equals("二年级(9)班")){
            return "二年级9班";
        }if(teamName.equals("二年级(10)班")){
            return "二年级10班";
        }if(teamName.equals("三年级(1)班")){
            return "三年级1班";
        }if(teamName.equals("三年级(2)班")){
            return "三年级2班";
        }if(teamName.equals("三年级(3)班")){
            return "三年级3班";
        }if(teamName.equals("三年级(4)班")){
            return "三年级4班";
        }if(teamName.equals("三年级(5)班")){
            return "三年级5班";
        }if(teamName.equals("三年级(6)班")){
            return "三年级6班";
        }if(teamName.equals("三年级(7)班")){
            return "三年级7班";
        }if(teamName.equals("三年级(8)班")){
            return "三年级8班";
        }if(teamName.equals("三年级(9)班")){
            return "三年级9班";
        }if(teamName.equals("三年级(10)班")){
            return "三年级10班";
        }if(teamName.equals("四年级(1)班")){
            return "四年级1班";
        }if(teamName.equals("四年级(2)班")){
            return "四年级2班";
        }if(teamName.equals("四年级(3)班")){
            return "四年级3班";
        }if(teamName.equals("四年级(4)班")){
            return "四年级4班";
        }if(teamName.equals("四年级(5)班")){
            return "四年级5班";
        }if(teamName.equals("四年级(6)班")){
            return "四年级6班";
        }if(teamName.equals("四年级(7)班")){
            return "四年级7班";
        }if(teamName.equals("四年级(8)班")){
            return "四年级8班";
        }if(teamName.equals("四年级(9)班")){
            return "四年级9班";
        }if(teamName.equals("四年级(10)班")){
            return "四年级10班";
        }if(teamName.equals("五年级(1)班")){
            return "五年级1班";
        }if(teamName.equals("五年级(2)班")){
            return "五年级2班";
        }if(teamName.equals("五年级(3)班")){
            return "五年级3班";
        }if(teamName.equals("五年级(4)班")){
            return "五年级4班";
        }if(teamName.equals("五年级(5)班")){
            return "五年级5班";
        }if(teamName.equals("五年级(6)班")){
            return "五年级6班";
        }if(teamName.equals("五年级(7)班")){
            return "五年级7班";
        }if(teamName.equals("五年级(8)班")){
            return "五年级8班";
        }if(teamName.equals("五年级(9)班")){
            return "五年级9班";
        }if(teamName.equals("五年级(10)班")){
            return "五年级10班";
        }if(teamName.equals("六年级(1)班")){
            return "六年级1班";
        }if(teamName.equals("六年级(2)班")){
            return "六年级2班";
        }if(teamName.equals("六年级(3)班")){
            return "六年级3班";
        }if(teamName.equals("六年级(4)班")){
            return "六年级4班";
        }if(teamName.equals("六年级(5)班")){
            return "六年级5班";
        }if(teamName.equals("六年级(6)班")){
            return "六年级6班";
        }if(teamName.equals("六年级(7)班")){
            return "六年级7班";
        }if(teamName.equals("六年级(8)班")){
            return "六年级8班";
        }if(teamName.equals("六年级(9)班")){
            return "六年级9班";
        }if(teamName.equals("六年级(10)班")){
            return "六年级10班";
        }
        return null;
    }
    //用智慧的班级名称与希沃的班名做比较，获取到对应的希沃的班级uuid
    //转换班级信息
    public String findBySwooUUid(List<Map<String,Object>> list,String teamName){
      for(Map<String,Object> aa:list){
          if(aa.get("nickName").equals(teamName)){
              return aa.get("uid").toString();
          }
      }
      return null;
    }


}
