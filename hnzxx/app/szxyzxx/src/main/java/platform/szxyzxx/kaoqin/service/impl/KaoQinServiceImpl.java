package platform.szxyzxx.kaoqin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seewo.open.sdk.DefaultSeewoClient;
import com.seewo.open.sdk.SeewoClient;
import com.seewo.open.sdk.auth.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.GradeDao;
import platform.education.generalTeachingAffair.dao.TeamDao;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.*;
import platform.szxyzxx.kaoqin.service.KaoQinService;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class KaoQinServiceImpl implements KaoQinService {
    private static final String SEEWO_PROPERTY_NAME = "seewo.properties";

    private static final String SCHOOL_CODE = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolCode");

    private static final String SCHOOL_UID= PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolUid");

    private static final String APP_ID = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.appid");

    private static final String SECRET = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.secret");

    private static final String SERVER = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.server");

    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private TeamDao teamDao;

    //根据班级查询学生考勤记录
   public Map<String,Object> findByKaoQin(UserInfo userInfo){
       GradeCondition gradeCondition=new GradeCondition();
       gradeCondition.setSchoolId(userInfo.getSchoolId());
       gradeCondition.setSchoolYear(userInfo.getSchoolYear());
       List<Grade> list=gradeDao.findGradeByCondition(gradeCondition);
       List list1=new ArrayList();
       List quanxiaoKaoqin=new ArrayList<>();
       int zhengchang=0;
       int chidao=0;
       int qingjia=0;
       int queqing=0;
       for(Grade aa:list){
           List<Team> teamList=teamDao.findTeamOfGrade(aa.getId(),userInfo.getSchoolId());
            if(teamList.size()<=0){

            }else{
                int a=0;
                int b=0;
                int c=0;
                int d=0;
                for(Team bb:teamList){
                    //拿到课对应的班级名成进行转换
                    String teamName= findByTeam(bb.getName());
                  //  System.out.println("班级"+teamName);
                    if(teamName!=null){
                  List<Map<String,Object>> mapList=  findSeewoClass();
                  String teamUuid=  findBySwooUUid(mapList,teamName);
                  Map<String,Integer> Sweeoclass=findBySweeoKaoqin(teamUuid);
                  a+=Sweeoclass.get("zhengchang");
                  b+=Sweeoclass.get("chidao");
                  c+=Sweeoclass.get("queqin");
                  d+=Sweeoclass.get("qingjia");
                    }
                }
                Double svg=0.00;
                if(a+b+c+d!=0){
                     svg=(double)a/(double)(a+b+c+d)*100 ;
                }
                list1.add(svg);
                zhengchang+=a;
                chidao+=b;
                qingjia+=d;
                queqing+=c;
            }
       }
       double svgzhengchang=0.00;
       double svgchidao=0.00;
       double svgqingjia=0.00;
       double svgqueqing=0.00;
        if(chidao+qingjia+queqing!=0){
            svgzhengchang=(double) zhengchang/(double)(qingjia+chidao+queqing);
        }
       if(zhengchang+qingjia+queqing!=0){
           svgchidao=(double) chidao/(double)(qingjia+zhengchang+queqing);
       }
       if(chidao+zhengchang+queqing!=0){
           svgqingjia=(double) qingjia/(double)(zhengchang+chidao+queqing);
       }
       if(chidao+qingjia+zhengchang!=0){
           svgqueqing=(double) queqing/(double)(qingjia+chidao+zhengchang);
       }
       quanxiaoKaoqin.add(svgzhengchang);
       quanxiaoKaoqin.add(svgchidao);
       quanxiaoKaoqin.add(svgqueqing);
       quanxiaoKaoqin.add(svgqingjia);
       Map<String,Object> map=new HashMap<>();
       map.put("quanxiaoKaoqin",quanxiaoKaoqin);
       map.put("gradeIdKaoQin",list1);
       return map;
   }


   //从希沃获取对应班级的考勤
   public Map<String, Integer> findBySweeoKaoqin(String teamUuid) {
       //初始化客户端
       SeewoClient seewoClient = new DefaultSeewoClient(new Account(APP_ID, SECRET));
       AttendanceServiceListClassStudentRecordsParam param = new AttendanceServiceListClassStudentRecordsParam();
//响应体，MimeType为 application/json
       AttendanceServiceListClassStudentRecordsParam.RequestBody requestBody = AttendanceServiceListClassStudentRecordsParam.RequestBody.builder()
               .build();
       param.setRequestBody(requestBody);
//query
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();

       AttendanceServiceListClassStudentRecordsParam.Query query = AttendanceServiceListClassStudentRecordsParam.Query.builder()
               .appId(APP_ID)
               .schoolUid(SCHOOL_UID)
               .attendDate(formatter.format(date))
               .attendType(1)
               .classUid(teamUuid)
               .page(1)
               .size(400)
               .build();
       requestBody.setQuery(query);
       param.setRequestBody(requestBody);
       AttendanceServiceListClassStudentRecordsRequest request = new AttendanceServiceListClassStudentRecordsRequest(param);
       AttendanceServiceListClassStudentRecordsResult result = seewoClient.invoke(request);
       System.out.println("出参：" + result);
       JSONObject jsonObject = JSONObject.parseObject(result.getBody());
       JSONObject jsonObject2 = (JSONObject) jsonObject.get("data");
       List<Map<String, Object>> list = new ArrayList<>();
       Map<String, Integer> maps = new HashMap<>();
       //a正常，b迟到，c缺勤，d请假
       int a = 0;
       int b = 0;
       int c = 0;
       int d = 0;
       if (jsonObject.get("code") == "000000") {
           JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(jsonObject2.get("result")));

           for (int i = 0; i < jsonArray.size(); i++) {
               //  Map<String,Object> map=new HashMap<>();
               JSONObject jsonObject3 = (JSONObject) jsonArray.get(i);
               //status 个人考勤结果： 0正常，1迟到，3缺勤，6请假
               if (Integer.parseInt(jsonObject3.get("status").toString()) == 0) {
                   a++;
               }
               if (Integer.parseInt(jsonObject3.get("status").toString()) == 1) {
                   b++;
               }
               if (Integer.parseInt(jsonObject3.get("status").toString()) == 3) {
                   c++;
               }
               if (Integer.parseInt(jsonObject3.get("status").toString()) == 6) {
                   d++;
               }
           }
       }

       maps.put("zhengchang", a);
       maps.put("chidao", b);
       maps.put("queqin", c);
       maps.put("qingjia", d);
       return maps;
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
}
