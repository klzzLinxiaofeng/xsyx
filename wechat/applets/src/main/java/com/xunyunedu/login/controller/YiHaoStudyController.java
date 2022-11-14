package com.xunyunedu.login.controller;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.ishangkelilu.pojo.StudyHabits;
import com.xunyunedu.ishangkelilu.service.StudyHabitsService;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.login.pojo.ZuoWeiHangLie;
import com.xunyunedu.notice.param.PubNoticeInsertParam;
import com.xunyunedu.notice.pojo.PubNotice;
import com.xunyunedu.notice.service.PubNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.StudentService;
import com.xunyunedu.team.pojo.TeamPojo;
import com.xunyunedu.team.service.TeamService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import com.xunyunedu.wechat.template.WechatMessageTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/zykjStudy")
@Api(value = "/zykjStudy", description = "接口对接")
public class YiHaoStudyController {
    Logger logger = LoggerFactory.getLogger(YiHaoStudyController.class);
    @Autowired
    private StudyHabitsService studyHabitsService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private LoggerService loggerService;

  @Autowired
  private BasicSQLService basicSQLService;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    @Autowired
    private PubNoticeService service;
     /* 获取所有学生*/
    @RequestMapping(value ="/TeamstudentAll")
    @ApiOperation(value = "获取该班级所有学生", httpMethod = "GET")
    public List<StudentPojo> findByStudentAll(@RequestParam Integer schoolId,
                                           @RequestParam Integer gradeId,
                                           @RequestParam String schoolYear,
                                           @RequestParam  Integer teamId) {
        List<StudentPojo> list = studentService.findStudentVoByTeam(schoolId,schoolYear, gradeId,teamId);
        return list;
    }

    @RequestMapping(value ="/studentAll")
    @ApiOperation(value = "获取该班级座位表所有学生", httpMethod = "GET")
    public Map<String,Object> findByStudent(@RequestParam Integer schoolId,
                                            @RequestParam Integer gradeId,
                                            @RequestParam String schoolYear,
                                            @RequestParam  Integer teamId) {
        String sql="select z.*,ps.name as stuName from zuowei z inner join pj_student ps on ps.id=z.student_id where  ps.is_delete=0 and z.is_delete=0 and z.team_id="+teamId;
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        Map<String,Object> map=new HashMap<>();
        if(mapList.size()>0){

            map.put("message","success");
            map.put("stats",true);
            map.put("studentList",mapList);
            return map;
        }
        map.put("message","该班级暂未设置座位");
        map.put("stats",false);
        map.put("studentList",mapList);
        return map;
    }
    /*
     * 根据年级id获取班级
    */
    @RequestMapping(value ="/teamAll")
    @ApiOperation(value = "根据年级id获取班级", httpMethod = "GET")
    public  Map<String,Object> findByTeam(@RequestParam Integer schoolId,
                                       @RequestParam Integer gradeId) {
        Map<String,Object> map=new HashMap<>();
            if(gradeId==null){
                List list=null;
                map.put("stats",false);
                map.put("message","参数为空");
                map.put("data",list);
                return map;
            }else{
                List<TeamPojo> list = teamService.findTeamOfGradeAndSchool(schoolId,gradeId);
                if(list.size()<1){
                    map.put("stats",false);
                    map.put("message","该年级无班级信息");
                    map.put("data",list);
                    return map;
                }
                map.put("stats",true);
                map.put("message","success");
                map.put("data",list);
                return map;
            }

    }

    /*
     * 根据老师id获取年级
     */
    @RequestMapping(value ="/gradeAll")
        @ApiOperation(value = "根据老师id获取年级", httpMethod = "GET")
    public Map<String,Object> findByGade(@RequestParam Integer teacherId,
                                         @RequestParam String schoolYear ,
                                         @RequestParam Integer schoolId) {
        Map<String,Object> map=new HashMap<>();

        List<Map<String,Object>> list=new ArrayList<>();

        if(teacherId==null){
            map.put("stats",false);
            map.put("message","该账号非教师账号");
            return map;
        }else{
            String sql="select pg.* from pj_team_teacher ptt inner join pj_grade pg on pg.id=ptt.grade_id where pg.school_year="+schoolYear+" and ptt.teacher_id="+teacherId+"  and  pg.school_id="+schoolId+"  and ptt.is_delete=0";
            list=basicSQLService.find(sql);
            if(list.size()<1){
                map.put("stats",false);
                map.put("message","该账号无对应年级信息");
                map.put("data",list);
                return map;
            }
            map.put("stats",true);
            map.put("message","success");
            map.put("data",list);
            return map;
        }
    }
    /*
     * 获取课堂评分记录
     */
    @RequestMapping(value ="/ScoreAll")
    @ApiOperation(value = "获取课堂评分记录", httpMethod = "GET")
    public Map<String,Object> findByScoreAll(@RequestParam Integer teacherId,
                                             @RequestParam Integer teamId) {
        Map<String,Object> map=new HashMap<>();
        if(teamId==null) {
            List list = null;
            map.put("stats", false);
            map.put("message", "参数为空");
            map.put("data", list);
            return map;
        }else{
            List<StudyHabits> list = studyHabitsService.findByShangke(teamId, teacherId);
            if(list.size()<1){
                map.put("stats",false);
                map.put("message","该账号无对应年级信息");
                map.put("data",list);
                return map;
            }
            map.put("stats",true);
            map.put("message","success");
            map.put("data",list);
            return map;
        }

    }
    /*
     * 课堂评分
     */
    @RequestMapping(value ="/classScore")
    @ApiOperation(value = "课堂评分", httpMethod = "POST")
    public Map<String,Object> findByScoreClassPingfen(@RequestBody StudyHabits studyHabits) {
        if(studyHabits!=null){}
        //当前 学年,学期
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        studyHabits.setSchoolYear(mapList.get(0).get("school_year").toString());
        studyHabits.setSchoolTrem(mapList.get(0).get("school_term_code").toString());
        //当前班级所在年级
        List<Map<String,Object>> mapList2=basicSQLService.find("SELECT * FROM pj_team WHERE school_id = 215 and id="+studyHabits.getTeamId()+"  ORDER BY id DESC");
       studyHabits.setGradeId(Integer.parseInt(mapList2.get(0).get("grade_id").toString()));
        String str=studyHabitsService.createOrupdate(studyHabits);
        Map<String,Object> map=new HashMap<>();
        if(str=="success"){



            map.put("stats",true);
            map.put("message","评分成功");
        }else{
            map.put("stats",false);
            map.put("message","评分失败");
        }
           return map;
    }

    /*
     * 获取课堂评分撤回
     */
    @RequestMapping(value ="/ScoreWithdraw")
    @ApiOperation(value = "获取课堂评分撤回", httpMethod = "Get")
    public Map<String,Object> findByScoreWithdraw(@RequestParam Integer id) {
        Boolean falg = studyHabitsService.findBychehui(id);
        Map<String,Object> map=new HashMap<>();
        if(falg){
            map.put("message","撤回成功");
            map.put("status",true);
        }else{
            map.put("message","撤回失败");
            map.put("status",false);
        }
        return map;
    }
    /*
     * 下课后提交所有上课记录
     */
    @RequestMapping(value ="/submitScore")
    @ApiOperation(value = "下课后提交所有上课记录", httpMethod = "GET")
    public Map<String,Object> findBySubmitScore(@RequestParam Integer teacherId,
                                                @RequestParam Integer teamId) {
        Map<String,Object> map=new HashMap<>();

        String str=studyHabitsService.findByXiake(teacherId, teamId);
        if(str=="success"){
            map.put("stats",true);
            map.put("message","提交成功");
        }else{
            map.put("stats",false);
            map.put("message","提交失败");
        }
        return map;
    }
    /*
     * 获取课程表
     */
    @RequestMapping(value ="/kechengAll")
    @ApiOperation(value = "获取课程表", httpMethod = "GET")
    public Map<String,Object> findByZuoWeiHang(@RequestParam Integer teamId,
                                               @RequestParam String schoolYear,
                                               @RequestParam String  termCode,
                                               @RequestParam Integer schoolId) {
        Map<String,Object> map=new HashMap<>();

        List<Map<String,Object>> list=new ArrayList<>();
            String sql="select * from pj_syllabus where is_deleted=0 and team_id="+teamId+" and school_id="+schoolId+" and school_year='"+schoolYear+"' and term_code='"+termCode+"'";
            list=basicSQLService.find(sql);
            if(list.size()<=0){
                map.put("stats",false);
                map.put("message","暂无该班级课程");
                map.put("data",list);
                return map;
            }
            //获取上课天数
            int num=Integer.parseInt(list.get(0).get("days").toString());
            //获取上课周数数组
            String [] array=list.get(0).get("days_plan").toString().split(",");
            int subbleId=Integer.parseInt(list.get(0).get("id").toString());
            List lis1=new ArrayList();
            for(int a=0;a<num;a++){
                //将上课时间转换成对象数组
                JSONArray jsonArray=JSONArray.parseArray(list.get(0).get("lesson_times").toString());
                int jieshu=Integer.parseInt(list.get(0).get("lesson_of_morning").toString())+Integer.parseInt(list.get(0).get("lesson_of_afternoon").toString())+Integer.parseInt(list.get(0).get("lesson_of_evening").toString());
                Map<String,Object> lisss=new HashMap<>();
                List asf=new ArrayList();
                //循环节数，没有课程设直为空
                for(int b=0;b<jieshu;b++){
                    int cxz=b+1;
                    JSONObject object= (JSONObject) jsonArray.get(b);
                    String sql2="select pls.*,ps.id as subjectId from  pj_syllabus_lesson pls  inner join pj_subject ps on ps.code=pls.subject_code" +
                            " where pls.lesson="+cxz+" and  pls.syllabus_id="+subbleId+" and pls.day_of_week="+array[a]+" and pls.is_deleted=0  order by lesson asc";
                    List<Map<String,Object>> mapList=basicSQLService.find(sql2);
                    if(mapList.size()>0){
                        mapList.get(0).put("startLessionTime",object.get("startTime"));
                        mapList.get(0).put("endLessionTime",object.get("endTime"));
                        mapList.get(0).put("jieshu",b+1);
                        asf.add(mapList);
                    }else{
                        Map<String,Object> stringObjectMap=new HashMap<>();
                        stringObjectMap.put("jieshu",b+1);
                        stringObjectMap.put("startLessionTime",object.get("startTime"));
                        stringObjectMap.put("endLessionTime",object.get("endTime"));
                        mapList.add(stringObjectMap);
                        asf.add(mapList);
                    }
                }
                //lesson_of_morning 上午几节课 ，lesson_of_afternoon 下午几节课 ，lesson_of_evening 晚上几节课
                lisss.put("week",array[a]);
                lisss.put("lesson_of_morning",Integer.parseInt(list.get(0).get("lesson_of_morning").toString()));
                lisss.put("lesson_of_afternoon",Integer.parseInt(list.get(0).get("lesson_of_afternoon").toString()));
                lisss.put("lesson_of_evening",Integer.parseInt(list.get(0).get("lesson_of_evening").toString()));
                lisss.put("mapList",asf);
                lis1.add(lisss);
            }
            map.put("data",lis1);
            map.put("stats",true);
            map.put("message","success");
            return map;
        }
    /*
     * 获取座位表
     */
    @RequestMapping(value ="/ZuoWeiAll")
    @ApiOperation(value = "获取座位", httpMethod = "GET")
    public Map<String,Object> findByZuoWeiHang(@RequestParam Integer teamId){
        ZuoWeiHangLie zuoWeiHangLie= studyHabitsService.findByzuoweiHangfLie(teamId);
        Map<String,Object> lisss=new HashMap<>();
        lisss.put("data",zuoWeiHangLie);
        lisss.put("stats",true);
        lisss.put("message","success");
        return lisss;
    }


    //---------------------2022,0228新增课堂行为密码设置-------------
    /*
    * 密码验证
    */
    @PostMapping(value ="/teamClassPassword")
    @ApiOperation(value = "密码验证", httpMethod = "POST")
    public ApiResult findByScoreClassPingfen(@RequestParam String teamId,
                                             @RequestParam String pwd) {
        if (StrUtil.hasEmpty(teamId, pwd) ) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<Map<String,Object>> dataMap = basicSQLService.find("select * from zy_team_password where team_id="+teamId+" and password="+pwd);
        if(dataMap.size()>0){
            return new ApiResult(ResultCode.SUCCESS, dataMap);
        }else{
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }
    }

    /*
     * 获取所有课堂行为评价类型
     */
    @RequestMapping(value ="/studyTypeALL")
    @ApiOperation(value = "获取所有课堂行为评价类型", httpMethod = "GET")
    public ApiResult findByScoreClassPingfen() {
        List<Map<String,Object>> dataMap = basicSQLService.find("select * from zy_studyleixing where is_delete=0");
        if(dataMap.size()>0){
            return new ApiResult(ResultCode.SUCCESS, dataMap);
        }else{
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }
    }

    /*
    * 提供给希沃的通知接口
    */
    @PostMapping(value ="/pushNotification")
    @ApiOperation(value = "推送通知", httpMethod = "POST")
    public ApiResult findByNotice(@RequestParam String teamName,
                                  @RequestParam String subjectName) {
        List<Map<String,Object>> dataMap = basicSQLService.find("select pt.user_id,pt.name,pt.id from zy_jizu zj inner join pj_teacher pt on pt.id=zj.fuze_id where zj.fuze_id is not null and zj.is_delete=0 group by zj.fuze_id;");
        List<Map<String,Object>> dept=basicSQLService.find("select * from pj_department where school_id=215 and is_delete=0");
        PubNoticeInsertParam param =new PubNoticeInsertParam();
        List<String> str=new ArrayList<>();
        str.add("pj.dept");
        str.add("pj.person");
        //发送对象
        param.setTargetTypeList(str);
        param.setTitle("上课未打卡通知");
        param.setContent(teamName+subjectName+"无教师打卡上课");
        param.setFileUuidList(null);
        param.setIsReply(false);
        param.setReceiverType("person");
        param.setPosterId(114904);
        param.setPosterName("郝洁");
        //部门list
        List<Integer> deptIdList = new ArrayList<>();
        //教师id
         List<Integer> teacherIdList=new ArrayList<>();
        for(Map<String,Object>  bb:dept){
            deptIdList.add(Integer.parseInt(bb.get("id").toString()));
        }

        for(Map<String,Object>  aa:dataMap){
            teacherIdList.add(Integer.parseInt(aa.get("id").toString()));
            sendWechatNotice(Integer.parseInt(aa.get("user_id").toString()),teamName,subjectName);
        }
        //汤俊
        teacherIdList.add(114907);
        //黄美园
        teacherIdList.add(114908);
        //谢文欣
        teacherIdList.add(114977);
        param.setDeptIdList(deptIdList);
        param.setTeacherIdList(teacherIdList);
        PubNotice pubNotice= service.add(param);
        sendWechatNotice(114907,teamName,subjectName);
        sendWechatNotice(114908,teamName,subjectName);
        sendWechatNotice(114977,teamName,subjectName);
        return new ApiResult(ResultCode.SUCCESS, pubNotice);
    }

    private void sendWechatNotice(Integer id,String teamName,String subjectName){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+"  and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("上课未打卡通知",teamName+subjectName+"无教师打卡上课");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }

}
