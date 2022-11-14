package com.xunyunedu.homeWorkZy.controller;


import com.xunyunedu.PublishAndAcceptJob.pojo.StudentHomeWoke;
import com.xunyunedu.PublishAndAcceptJob.service.TeamTeacherService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.homeWorkZy.pojo.HomeWokePojo;
import com.xunyunedu.homeWorkZy.service.HomeWokeService;
import com.xunyunedu.util.ftp.FtpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/home/woke")
@Api(value = "/home/woke", description = "作业检查")
public class HomeWokeController {
    @Autowired
    private HomeWokeService homeworkService;
    @Autowired
    private TeamTeacherService teamTeacherService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;
  /*-------------------------教师端----------------------------------*/
    @RequestMapping("/homeWorkAll")
    @ApiOperation(value = "作业检查列表", httpMethod = "GET")
    public Map<String,Object> findByAll(@RequestParam Integer schoolId,
                                        @RequestParam Integer teacherId){
        Map<String,Object> map =new HashMap<>();
        if(schoolId!=null && teacherId!=null){
            List<HomeWokePojo> homeWokePojos=homeworkService.findByAll(schoolId,teacherId);
            if(homeWokePojos.size()>0){
                map.put("message","success");
                map.put("stats",true);
                map.put("data",homeWokePojos);
                return map;
            }else{
                map.put("message","暂无数据");
                map.put("stats",true);
                map.put("data",homeWokePojos);
                return map;
            }
        }else{
            map.put("message","参数为空");
            map.put("stats",false);
            map.put("data",null);
             return map;
        }
    }


    @RequestMapping("/homeWorkStudentAll")
    @ApiOperation(value = "作业检查详情学生列表", httpMethod = "GET")
    public Map<String,Object> findByAll(@RequestParam Integer id,
                                        @RequestParam(value = "studentName",required = false)String studentName){
        Map<String,Object> map =new HashMap<>();
        if(id!=null){
            List<StudentHomeWoke> studentHomeWokes=homeworkService.findByStudentHomeWokeAll(id,studentName);
            for(StudentHomeWoke aa:studentHomeWokes){
                if(aa.getZhuantai()==0){
                    aa.setZhuangzhongwen("待提交");
                }else if(aa.getZhuantai()==1){
                    aa.setZhuangzhongwen("已交");
                }else if(aa.getZhuantai()==2){
                    aa.setZhuangzhongwen("缺交");
                }else if(aa.getZhuantai()==3){
                    aa.setZhuangzhongwen("补交");
                }
            }

            if(studentHomeWokes.size()>0){
                map.put("message","success");
                map.put("stats",true);
                map.put("data",studentHomeWokes);
                return map;
            }else{
                map.put("message","暂无数据");
                map.put("stats",true);
                map.put("data",studentHomeWokes);
                return map;
            }
        }else{
            map.put("message","参数为空");
            map.put("stats",false);
            map.put("data",null);
            return map;
        }
    }


    @RequestMapping("/homeWorkPingjia")
    @ApiOperation(value = "作业评价", httpMethod = "POST")
    public Map<String,Object> findByPingjia(@RequestBody StudentHomeWoke studentHomeWoke){
        Map<String,Object> map =new HashMap<>();
        if( studentHomeWoke.getId()!=null &&studentHomeWoke.getFenzhi()!=null){
            int num=homeworkService.updateStudentHomeWokePing(studentHomeWoke);
            if(num>0){
                map.put("message","success");
                map.put("stats",true);
                return map;
            }else{
                map.put("message","评价失败");
                map.put("stats",false);
                return map;
            }
        }else{
            map.put("message","参数为空");
            map.put("stats",false);
            map.put("data",studentHomeWoke.toString());
            return map;
        }
    }
     /*---------------------------------家校通-------------------------------------------*/
     @RequestMapping("/studentHomeWorkAll")
     @ApiOperation(value = "学生作业列表", httpMethod = "GET")
     public Map<String,Object> findByAllStudentId(@RequestParam Integer schoolId,
                                                  @RequestParam Integer studentId,
                                                  @RequestParam(value ="subjectId",required = false) Integer subjectId){
         Map<String,Object> map =new HashMap<>();
         if(schoolId!=null && studentId!=null){
             List<HomeWokePojo> pojoList=homeworkService.findByAllStudentId(schoolId,studentId,subjectId);
             if(pojoList.size()>0){
                 map.put("message","success");
                 map.put("stats",true);
                 map.put("data",pojoList);
                 return map;
             }else{
                 map.put("message","暂无数据");
                 map.put("stats",true);
                 map.put("data",pojoList);
                 return map;
             }
         }else{
             map.put("message","参数为空");
             map.put("stats",false);
             map.put("data",null);
             return map;
         }
     }

    @RequestMapping("/studentHomeWorkXiangQing")
    @ApiOperation(value = "学生作业详情列表", httpMethod = "GET")
    public Map<String,Object> findByAll(@RequestParam Integer id,
                                        @RequestParam Integer studentId,
                                        @RequestParam Integer schoolId
                                        ){
        Map<String,Object> map =new HashMap<>();
        if(schoolId!=null && studentId!=null){
           StudentHomeWoke studentHomeWoke=homeworkService.findById(id, studentId, schoolId);
            // 根据PctreId查询优秀作品的url
            if(studentHomeWoke.getPicter_uuid()!=null) {
                EntityFile file = uploaderDao.findFileByUUID(studentHomeWoke.getPicter_uuid());
                if (file != null) {
                    studentHomeWoke.setPicterUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
                map.put("message","success");
                map.put("stats",true);
                map.put("data",studentHomeWoke);
                return map;
        }else{
            map.put("message","参数为空");
            map.put("stats",false);
            map.put("data",null);
            return map;
        }
     }

    /*
     * 获取当前教师任教班级
     */
    @RequestMapping("/getTeamAll")
    public List<Map<String, Object>> findByTeamAll(@RequestParam Integer userId,
                                              @RequestParam String schoolYear) {
        return teamTeacherService.getTeacherTeachTeam(userId, schoolYear);
    }
    /*
     * 获取当前教师任教科目
     */
    @RequestMapping("/getTeamSubject")
    public List<Map<String, Object>> findBySubjectStudent(
                                                 @RequestParam Integer teamId,
                                                 @RequestParam Integer studentId,
                                                 @RequestParam Integer schoolId) {
        List<Map<String, Object>> map=basicSQLService.find("select pg.* from pj_team_student pts inner join pj_grade pg where pts.is_delete=0 and  pts.team_id="+teamId+" and pts.student_id="+studentId);
        List<Map<String, Object>> map2=new ArrayList<>();
        if(map.size()>0) {

            String gradecode = map.get(0).get("uni_grade_code").toString();
            map2 = basicSQLService.find("select * from pj_subject_grade psg inner join pj_subject ps on ps.code=psg.subject_code where psg.is_deleted=0 and  psg.grade_code=" + gradecode + " and psg.is_deleted=0  group by ps.id");
            return map2;
        }
      return  map2;
    }


    /*
    * 获取学生班级科目
    */
    @RequestMapping("/getSubjectAll")
    public List<Map<String, Object>> findBySubjectAll(
            @RequestParam String schoolYear,
            @RequestParam Integer userId,
            @RequestParam Integer teamId,
            @RequestParam Integer schoolId) {
        List<Map<String, Object>> map= teamTeacherService.getTeacherTeachSubject(userId,schoolYear,teamId);

        for(Map<String, Object> key :map){
            List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_subject WHERE school_id ="+schoolId+" AND is_delete = 0 AND code ="+key.get("subjectCode"));
            key.put("subjectId",mapList.get(0).get("id"));
        }
        return map;
    }

    /*
     * 获取所有年级
     */
    @RequestMapping("/getByGrade")
    public List<Map<String, Object>> getByGrade(
            @RequestParam Integer schoolId,
            @RequestParam String schoolYear) {
       String sql="SELECT * FROM pj_grade WHERE 1=1 and is_deleted=0 AND school_id="+schoolId+" AND school_year ='"+schoolYear+"' group by id  order by grade_number asc";
        List<Map<String, Object>> map=basicSQLService.find(sql);
        return map;
    }
    /*
     * 获取所有班级
     */
    @RequestMapping("/getByTeam")
    public List<Map<String, Object>> getByTeam(
            @RequestParam String schoolYear,
            @RequestParam Integer gradeId,
            @RequestParam Integer schoolId) {
        String sql="SELECT * FROM pj_team where 1=1 and is_delete= 0 AND school_id ="+schoolId+" AND grade_id ="+gradeId+" AND school_year ='"+schoolYear+"'  group by id";
        List<Map<String, Object>> map=basicSQLService.find(sql);
        return map;
    }
    /*
     * 获取所有科目
     */
    @RequestMapping("/getBySubject")
    public List<Map<String, Object>> getBySubject(@RequestParam Integer gradeId) {
        return basicSQLService.find("select ps.id as subjectId, psg.subject_code as code,psg.subject_name as subjectName from pj_subject_grade  psg inner join pj_grade pg on pg.uni_grade_code=psg.grade_code" +
                " inner join pj_subject ps on ps.code=psg.subject_code where  pg.id="+gradeId+" and psg.is_deleted=0 group by ps.id");
    }

}
