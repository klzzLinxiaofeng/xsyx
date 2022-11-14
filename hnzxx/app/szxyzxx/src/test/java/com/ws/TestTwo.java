package com.ws;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seewo.open.sdk.DefaultSeewoClient;
import com.seewo.open.sdk.SeewoClient;
import com.seewo.open.sdk.auth.Account;
import org.junit.Test;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.EmployeeList;
import platform.education.generalTeachingAffair.vo.ShiTangDate;
import platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalParam;
import platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalRequest;
import platform.szxyzxx.dto.seewo.pojo.ClassRoomServiceListClassRoomRelConditionalResult;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.*;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestTwo {
    //食堂新增接口测试
    public static void main(String[] args) {
        //测试地址
        String ip = PropertiesUtil.getProperty("System.properties", "canteen.server.address");
        //获取食堂用户信息
        String addres = PropertiesUtil.getProperty("System.properties", "canteen.user.list.url");
        String add = "/api/mobile/VipUser/UserEmployeeAdd";
        String update = "/api/mobile/VipUser/UserEmployeeUpdate";
        String url = ip + addres;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ShiTangDate shiTangDate = new ShiTangDate();
        List<EmployeeList> list = new ArrayList<>();
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmp_pycode("7868686");
        // employeeList.setEmp_address("广州市番禺区景观大道");
        employeeList.setEmp_card("2113249768");
        employeeList.setEmp_code("1241241");
        employeeList.setEmp_name("测试以3");
        employeeList.setEmp_sex("女");
        employeeList.setEmp_birthday("2021-11-12");
        employeeList.setEmp_idcard("124311212");
        employeeList.setEmp_workdate(simpleDateFormat.format(new Date()));
        // employeeList.setDept_code("000001");
        employeeList.setDept_name("QA/QC部");
        // employeeList.setJob_name("教师");
        employeeList.setEmp_tel("13545676998");
        // employeeList.setTitle_name("2");
        // employeeList.setEmp_mealtype("外出就餐");
        list.add(employeeList);
       /* shiTangDate.setSign_name("kksss");
        shiTangDate.setTran_code("emp_add");
        shiTangDate.setEmployeeList(list);*/
        Object object = JSONObject.toJSON(list);
        JSONObject param = new JSONObject();
        param.put("sign_name", "kksss");
        //param.put("tran_code","emp_add");
        param.put("tran_code", "emp_add");
        param.put("employeeList", object);

        //canteenThreadPoolExecutor.submit
        HttpRequestResult httpRequestResult = null;
        HttpRequestConfig config = HttpRequestConfig.create().url("http://139.159.242.158:8090" + add)
                .addHeader("content-type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        //config.json(shiTangDate.toString());
        config.json(param.toString());
        try {
            httpRequestResult = HttpClientUtils.post(config);

            if (httpRequestResult == null) {
                System.out.println("为空");
            }
            if (200 == httpRequestResult.getCode()) {
                String responseText = httpRequestResult.getResponseText();
                if (("").equals(responseText) || responseText == null) {
                    System.out.println("responseText为空");
                }
                JSONObject jsonObject = JSONObject.parseObject(responseText);
                String statusCode = jsonObject.getString("statusCode");
                String result = jsonObject.getString("result");
                if (("").equals(statusCode) || statusCode == null && ("").equals(result) || result == null) {
                    System.out.println("statusCode为空");
                }
                if ("200".equals(statusCode) && "true".equals(result)) {
                    List<Map<String, Object>> list2 = (List<Map<String, Object>>) jsonObject.get("data");
                    if (list2.size() < 0) {
                        System.out.println("返回数据为空");
                    }
                    for (Map<String, Object> faa : list2) {
                        System.out.println(faa.get("emp_pycode"));
                        System.out.println(faa.get("deal_msg"));
                    }
                    /*食堂返回信息
                     * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                     */
                } else {
                    System.out.println("食堂接口--添加用户信息到远程接口失败--食堂接口成功 错误信息 {}" + httpRequestResult);
                }
            }


            System.out.println("食堂接口--食堂添加接口添加状态{}，  返回信息: {}----" + httpRequestResult);
        } catch (IOException e) {
            System.out.println("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}" + e);
        }
    }

    @Test
    public void asdas() {
///api/mobile/VipUser/list
        //测试地址
        String ip = PropertiesUtil.getProperty("System.properties", "canteen.server.address");
        HttpRequestResult httpRequestResult = null;
        HttpRequestConfig config = HttpRequestConfig.create().url(ip + "api/mobile/VipUser/list?emp_code=1241241")
                .addHeader("content-type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        //config.json(shiTangDate.toString());
        try {
            httpRequestResult = HttpClientUtils.get(config);
            if (httpRequestResult == null) {
                System.out.println("为空");
            }
            if (200 == httpRequestResult.getCode()) {
                String responseText = httpRequestResult.getResponseText();
                if (("").equals(responseText) || responseText == null) {
                    System.out.println("responseText为空");
                }
                JSONObject jsonObject = JSONObject.parseObject(responseText);
                String statusCode = jsonObject.getString("statusCode");
                String result = jsonObject.getString("result");
                if (("").equals(statusCode) || statusCode == null && ("").equals(result) || result == null) {
                    System.out.println("statusCode为空");
                }
                if ("200".equals(statusCode) && "true".equals(result)) {
                    List<Map<String, Object>> list2 = (List<Map<String, Object>>) jsonObject.get("data");
                    if (list2.size() < 0) {
                        System.out.println("返回数据为空");
                    }
                    for (Map<String, Object> faa : list2) {
                        System.out.println(faa.get("emp_pycode"));
                        System.out.println(faa.get("dept_name"));
                    }
                    /*食堂返回信息
                     * "data":[{"emp_pycode":"202111115","emp_code":"00013015","emp_name":"测试11,15","emp_card":"202111115","emp_idcard":"222222225","deal_code":"0","deal_msg":"success"}
                     */
                } else {
                    System.out.println("食堂接口--查询用户信息到远程接口失败--食堂接口成功 错误信息 {}" + httpRequestResult);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /* @Test
    public void fie(){
        //初始化客户端
        SeewoClient seewoClient = new DefaultSeewoClient(new Account("a6a36a0c3ab24d83abd8e95579f62420", "KUNhgSppmvV9qlD1PtXhwR1ft5ENidSS"));
        TimetableSaveRoomTimetableParam param = new TimetableSaveRoomTimetableParam();
//请求体，MimeType为 application/json
        TimetableSaveRoomTimetableParam.JSONRequestBody requestBody = TimetableSaveRoomTimetableParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//场地课表入参
        TimetableSaveRoomTimetableParam.RoomTimetableSaveReq saveReq = TimetableSaveRoomTimetableParam.RoomTimetableSaveReq.builder()
                .planUid("planUid")
                .classroomUid("classroomUid")
                .simpleCourseName(false)
                .build();
        requestBody.setSaveReq(saveReq);
//班级课表列表
        TimetableSaveRoomTimetableParam.TimetableItem timetableItems = TimetableSaveRoomTimetableParam.TimetableItem.builder()
                .periodNickName("periodNickName")
                .startTime("startTime")
                .endTime("endTime")
                .type(0)
                .dayIndex(1)
                .subjectName("subjectName")
                .teacherName("teacherName")
                .teacherUid("teacherUid")
                .strategy("strategy")
                .sectionIndex(2)
                .build();
        saveReq.setTimetableItems(java.util.Collections.singletonList(timetableItems));
        param.setRequestBody(requestBody);
        TimetableSaveRoomTimetableRequest request = new TimetableSaveRoomTimetableRequest(param);

//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
  request.setServerUrl("https://openapi.test.seewo.com");
        System.out.println("入参：" +request);
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
        TimetableSaveRoomTimetableResult result = seewoClient.invoke(request);
        System.out.println("出参：" +result);
    }*/


    @Test
    public void findSeewo(){
        //初始化客户端
        SeewoClient seewoClient = new DefaultSeewoClient(new Account("36e31c5188f24b448a63dda58b300d5c", "v2ZXXq4yLovrbkbL1gHK6q6qI9QCFitu"));
        ClassServiceListByConditionalParam param = new ClassServiceListByConditionalParam();
//响应体，MimeType为 application/json
        ClassServiceListByConditionalParam.RequestBody requestBody = ClassServiceListByConditionalParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//query
        ClassServiceListByConditionalParam.Query query = ClassServiceListByConditionalParam.Query.builder()
                .appId("36e31c5188f24b448a63dda58b300d5c")
                .schoolUid("db9fce22d2324330a81755df55a18179")
                .pageSize(100)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        ClassServiceListByConditionalRequest request = new ClassServiceListByConditionalRequest(param);
        System.out.println("入参：" +request);
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com")
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
        ClassServiceListByConditionalResult result = seewoClient.invoke(request);

        JSONObject jsonObject=JSONObject.parseObject(result.getBody());

        JSONObject jsonObject2= (JSONObject) jsonObject.get("data");
        System.out.println(jsonObject2.get("result"));
        List<Map<String,Object>> list=new ArrayList<>();
        JSONArray jsonArray=JSONArray.parseArray(JSONObject.toJSONString(jsonObject2.get("result")));
        for(int i=0;i<jsonArray.size();i++){
            System.out.println(jsonArray.get(i));
            Map<String,Object> map=new HashMap<>();
            JSONObject jsonObject3= (JSONObject) jsonArray.get(i);
            map.put("nickName",jsonObject3.get("nickName"));
            map.put("uid",jsonObject3.get("uid"));
            list.add(map);
        }
        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }
    };
    @Test
    public  void text5(){
        SeewoClient seewoClient = new DefaultSeewoClient(new Account("36e31c5188f24b448a63dda58b300d5c", "v2ZXXq4yLovrbkbL1gHK6q6qI9QCFitu"));
        ClassRoomServiceListClassRoomRelConditionalParam param = new ClassRoomServiceListClassRoomRelConditionalParam();
//请求体，MimeType为 application/json
        ClassRoomServiceListClassRoomRelConditionalParam.JSONRequestBody requestBody = ClassRoomServiceListClassRoomRelConditionalParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//入参条件
       ClassRoomServiceListClassRoomRelConditionalParam.ThirdClassRoomRelQuery query = ClassRoomServiceListClassRoomRelConditionalParam.ThirdClassRoomRelQuery.builder()
                .appId("36e31c5188f24b448a63dda58b300d5c")
                .unitUid("db9fce22d2324330a81755df55a18179")
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        ClassRoomServiceListClassRoomRelConditionalRequest request = new ClassRoomServiceListClassRoomRelConditionalRequest(param);
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com")
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法 ClassRoomServiceListClassRoomRelConditionalResult result = seewoClient.invoke(request);
ClassRoomServiceListClassRoomRelConditionalResult result = seewoClient.invoke(request);
        JSONObject jsonObject=JSONObject.parseObject(result.getBody());

        // jsonObject.get("data");
         System.out.println("出参：" +jsonObject.get("data"));
        List<Map<String,Object>> list=new ArrayList<>();
        JSONArray jsonArray=JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("data")));
        for(int i=0;i<jsonArray.size();i++){
           // System.out.println(jsonArray.get(i));
            Map<String,Object> map=new HashMap<>();
            JSONObject jsonObject3= (JSONObject) jsonArray.get(i);
            map.put("buildingRoomUid",jsonObject3.get("buildingRoomUid"));
            map.put("classUid",jsonObject3.get("classUid"));
            list.add(map);
        }
        for(int i=0;i<list.size();i++) {
          //  System.out.println(list.get(i));
        }

    }
    @Test
    public void fjfh(){
        //初始化客户端
        SeewoClient seewoClient = new DefaultSeewoClient(new Account("开放平台创建的APP-ID", "APP-ID 对应的Secret"));
        TimetableServiceSaveRoomTimetableParam param = new TimetableServiceSaveRoomTimetableParam();
//响应体，MimeType为 application/json
        TimetableServiceSaveRoomTimetableParam.RequestBody requestBody = TimetableServiceSaveRoomTimetableParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//请求内容
        TimetableServiceSaveRoomTimetableParam.Query query = TimetableServiceSaveRoomTimetableParam.Query.builder()
                .roomNum("A101")
                .unitUid("03e0b3ec5ebb46c38")
                .planUid("")
                .roomName("A栋-6-01")
                .build();
        requestBody.setQuery(query);
//课表项列表，不超过200条，每次将覆盖上一次的场地课表
        TimetableServiceSaveRoomTimetableParam.TimetableItemsItem timetableItems = TimetableServiceSaveRoomTimetableParam.TimetableItemsItem.builder()
                .sectionIndex(1)
                .dayIndex(1)
                .teacherName("")
                .teacherPhone("13712341234")
                .startTime("07:30")
                .endTime("08:00")
                .strategy("ALL")
                .subjectName("语文")
                .build();
        List list= new ArrayList();
        query.setTimetableItems(list);
        param.setRequestBody(requestBody);
        TimetableServiceSaveRoomTimetableRequest request = new TimetableServiceSaveRoomTimetableRequest(param);
        System.out.println("入参：" +request);
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com")
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
        TimetableServiceSaveRoomTimetableResult result = seewoClient.invoke(request);
        System.out.println("出参：" +result);
    }
    @Test
    public void weft(){
        //初始化客户端
        SeewoClient seewoClient = new DefaultSeewoClient(new Account("36e31c5188f24b448a63dda58b300d5c", "v2ZXXq4yLovrbkbL1gHK6q6qI9QCFitu"));
        BuildingRoomServiceListRoomConditionParam param = new BuildingRoomServiceListRoomConditionParam();
//请求体，MimeType为 application/json
        BuildingRoomServiceListRoomConditionParam.JSONRequestBody requestBody = BuildingRoomServiceListRoomConditionParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//
        BuildingRoomServiceListRoomConditionParam.ThirdListRoomQuery query = BuildingRoomServiceListRoomConditionParam.ThirdListRoomQuery.builder()
                .appId("36e31c5188f24b448a63dda58b300d5c")
                .unitUid("db9fce22d2324330a81755df55a18179")
                .roomUid("de5b2f14426a4171a918ff6f0db9b4fc")
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        BuildingRoomServiceListRoomConditionRequest request = new BuildingRoomServiceListRoomConditionRequest(param);
        System.out.println("入参：" +request);
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com")
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
        BuildingRoomServiceListRoomConditionResult result = seewoClient.invoke(request);
        JSONObject jsonObject=JSONObject.parseObject(result.getBody());
       // System.out.println("出参：" +jsonObject);
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
        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void qiwj(){
//初始化客户端
        SeewoClient seewoClient = new DefaultSeewoClient(new Account("36e31c5188f24b448a63dda58b300d5c", "v2ZXXq4yLovrbkbL1gHK6q6qI9QCFitu"));
        AttendanceRuleListEventByClazzParam param = new AttendanceRuleListEventByClazzParam();
//响应体，MimeType为 application/json
        AttendanceRuleListEventByClazzParam.RequestBody requestBody = AttendanceRuleListEventByClazzParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//query
        AttendanceRuleListEventByClazzParam.Query query = AttendanceRuleListEventByClazzParam.Query.builder()
                .appId("36e31c5188f24b448a63dda58b300d5c")
                .schoolUid("db9fce22d2324330a81755df55a18179")
                .date("2021-12-31")
                .classUid("6c72b59cbc2145f68f86530a33d5109f")
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        AttendanceRuleListEventByClazzRequest request = new AttendanceRuleListEventByClazzRequest(param);
        System.out.println("入参：" +request);
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com")
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
        AttendanceRuleListEventByClazzResult result = seewoClient.invoke(request);
        System.out.println("出参：" +result);

    }
    @Test
    public void levnt(){
        //初始化客户端
        SeewoClient seewoClient = new DefaultSeewoClient(new Account("36e31c5188f24b448a63dda58b300d5c", "v2ZXXq4yLovrbkbL1gHK6q6qI9QCFitu"));
        AttendanceServiceListClassStudentRecordsParam param = new AttendanceServiceListClassStudentRecordsParam();
//响应体，MimeType为 application/json
        AttendanceServiceListClassStudentRecordsParam.RequestBody requestBody = AttendanceServiceListClassStudentRecordsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
//query
        AttendanceServiceListClassStudentRecordsParam.Query query = AttendanceServiceListClassStudentRecordsParam.Query.builder()
                .appId("36e31c5188f24b448a63dda58b300d5c")
                .schoolUid("db9fce22d2324330a81755df55a18179")
                .attendDate("2020-10-10")
                .attendType(0)
                .classUid("6c72b59cbc2145f68f86530a33d5109f")
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        AttendanceServiceListClassStudentRecordsRequest request = new AttendanceServiceListClassStudentRecordsRequest(param);
        System.out.println("入参：" +request);
        AttendanceServiceListClassStudentRecordsResult result = seewoClient.invoke(request);
        System.out.println("出参：" +result);
    }
}