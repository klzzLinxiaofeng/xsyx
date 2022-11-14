package com.ws;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ceshilianjie")
public class CeshiLianjieController {
    @RequestMapping("/awasd")

    @Test
public void findByasda(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://10.191.112.30:1433;DatabaseName=xiaoxue";
        String userName="User_1";            //sqlserver用户名
        String userPwd="Yihao888";    //sqlserver用户密码
        try{
            Class.forName(driverName);   //加载sqlserver的驱动类
            System.out.println("加载SQLServer驱动类成功!");
        }
        catch(ClassNotFoundException a){
            System.out.println("加载SQLServer驱动失败!");
            a.printStackTrace();
        }
        Connection dbcon=null;           //处理与特定数据库的连接
        try{
            dbcon= DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("数据库连接成功!");
            dbcon.close();
        }
        catch(SQLException e){
            System.out.println("数据库连接失败!");
            e.printStackTrace();
        }
    }

    @Test
    public void sweewo() {
        Sweeo sweeo=new Sweeo();
        sweeo.setSimpleCourseName(false);
        sweeo.setClassroomUid("classroomUid");
        sweeo.setPlanUid("planUid");
        List list=new ArrayList();
        Map map =new HashMap();
        map.put("teacherUid","35");
        map.put("teacherName","刘老师");
        map.put("periodNickName","第一节课");
        map.put("startTime","9:00");
        map.put("endTime","9:50");
        map.put("type",0);
        map.put("strategy","haah");
        map.put("subjectName","语文");
        map.put("sectionIndex",4);
        map.put("dayIndex",1);
        list.add(map);
        sweeo.setTimetableItems(list);
         sweeo.setRoomName("一年级一班");
        JSONObject param = new JSONObject();
        param.put("x-sw-app-id", "a6a36a0c3ab24d83abd8e95579f62420");
        //param.put("tran_code","emp_add");
        param.put("x-sw-sign", "KUNhgSppmvV9qlD1PtXhwR1ft5ENidSS");
        param.put("x-sw-req-path", "/seewo-yunban-api/timetable-service/save-room-timetable");
        param.put("x-sw-version",2);
        param.put("query", sweeo);
        //canteenThreadPoolExecutor.submit
        HttpRequestResult httpRequestResult = null;
        HttpRequestConfig config = HttpRequestConfig.create().url("http(s)://openapi.test.seewo.com")
                .addHeader("content-type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        config.json(param.toString());
        try {
            httpRequestResult = HttpClientUtils.post(config);
            if (200 == httpRequestResult.getCode()) {
                String responseText = httpRequestResult.getResponseText();
                        JSONObject jsonObject = JSONObject.parseObject(responseText);
                //String result = jsonObject.getString("x-sw-message");
                //System.out.println("情况"+result);
                System.out.println("返回"+jsonObject.toJSONString());
                System.out.println("返回2"+responseText);
            }else{
                System.out.println("shibai");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//request.setServerUrl("https://openapi.test.seewo.com");
//执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
    }

    }
