package com.xunyunedu.development.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.development.service.StudentDevelopmentService;
import com.xunyunedu.development.vo.DevelopmentIndicators;
import com.xunyunedu.development.vo.StudentDevelopment;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.logger.vo.Loggers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangyong
 * @Date 2022/11/14 9:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/developmentBehavior")
@Api(value = "品德发展接口", description = "品德发展接口")
public class StudentDevelopmentController {
    Logger logger = LoggerFactory.getLogger(StudentDevelopmentController.class);
    @Autowired
    private StudentDevelopmentService studentDevelopmentService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;

    @GetMapping("/lfindByEvaver")
    @ApiOperation(value = "品德发展类型列表接口", httpMethod = "GET")
    public List<DevelopmentIndicators> findByEvaver(@RequestParam Integer type,
                                                    @RequestParam Integer id){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        //0家长端，1 教师端
        List<DevelopmentIndicators> laborIndicators =new ArrayList<>();
        if(type==0){
             laborIndicators=studentDevelopmentService.findByAll(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString(),"PARENT");
        }else{
            List<Map<String,Object>> role=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id where is_deleted=0 and user_id="+id);
            String str=null;
            List <DevelopmentIndicators> all=studentDevelopmentService.findByAll(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString(),null);
                for(DevelopmentIndicators aa:all){
                    String [] array=aa.getCode().split(",");
                    for(int a=0;a<array.length;a++){
                        System.out.println(array[a]);
                        for(Map<String,Object> bb:role){
                            System.out.println(bb.get("code").toString());
                            if(array[a].equals(bb.get("code").toString())){
                                //laborIndicators.add(aa);
                                int num=0;
                                for(DevelopmentIndicators developmentIndicators:laborIndicators){
                                    if(developmentIndicators.getId().equals(aa.getId())){
                                        num+=1;
                                    }
                                }
                                if(num<=0){
                                    laborIndicators.add(aa);
                                }
                            }
                        }
                    }
                }

        }
         return laborIndicators;
    }

   /* @GetMapping("/defenqingkuang")
    @ApiOperation(value = "品德发展学生分数", httpMethod = "GET")
    public Integer defenqingkuang(@RequestParam Integer studentId,
                                  @RequestParam Integer evelaId){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        Integer num=laborBehaviorService.findByFenShu(studentId,evelaId,mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
        return num;
    }*/

    @PostMapping("/add")
    @ApiOperation(value = "品德发展添加", httpMethod = "POST")
    public Map createrecord(@RequestBody StudentDevelopment studentDevelopment,
                            @RequestParam Integer type) {
        List<Map<String,Object>> termInfo= basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        studentDevelopment.setSchoolYear(termInfo.get(0).get("school_year").toString());
        studentDevelopment.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
        Map map = new HashMap();
            Loggers logger = new Loggers();
            if(studentDevelopment.getPingjiaId()!=null){
                //教师端添加
                Integer falg = studentDevelopmentService.create(studentDevelopment);
                if(falg>0){
                List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+studentDevelopment.getPingjiaId());
                logger.setUsername(teacher.get(0).get("user_name").toString());
                logger.setMobile(teacher.get(0).get("mobile").toString());
                logger.setCaozuoId(studentDevelopment.getPingjiaId());
                logger.setName(studentDevelopment.getPingjiaName());
                logger.setMessage("教师新增"+studentDevelopment.getStudentName()+"品德发展记录");
                }else{
                    map.put("code", 400);
                    map.put("susses", "评价失败");
                    return map;
                }
            }else{
                List<Map<String,Object>> teacher=basicSQLService.find("select pp.id,  pp.name,yu.user_name from pj_parent_student  pps " +
                        " inner join pj_student ps on ps.user_id=pps.student_user_id " +
                        " inner join pj_parent pp on pp.user_id=pps.parent_user_id " +
                        " inner join yh_user yu on yu.id=pps.parent_user_id " +
                        " where ps.id="+studentDevelopment.getStudentId());
                studentDevelopment.setPingjiaId(Integer.parseInt(teacher.get(0).get("id").toString()));
                studentDevelopment.setPingjiaName(teacher.get(0).get("name").toString());
                Integer falg = studentDevelopmentService.create(studentDevelopment);
                if(falg>0){
                    logger.setUsername(teacher.get(0).get("user_name").toString());
                    //家长姓名
                    logger.setName(teacher.get(0).get("name").toString());
                    logger.setMobile(null);
                    logger.setMessage("家长新增"+studentDevelopment.getStudentName()+"品德发展记录");
                    logger.setCaozuoId(null);
                }else{
                    map.put("code", 400);
                    map.put("susses", "评价失败");
                    return map;
                }
            }
            logger.setMokuaiName("品德发展");
            logger.setType(1);
            logger.setSchoolYear(termInfo.get(0).get("school_year").toString());
            logger.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
            loggerService.create(logger);
            map.put("code", 200);
            map.put("susses", "评价成功");
            return map;
    }


    @GetMapping("/studentDevelopMentAll")
    @ApiOperation(value = "品德发展评价记录", httpMethod = "GET")
    public List<StudentDevelopment> findBystudentAll(@RequestParam Integer type,
                                                    @RequestParam Integer id){
        List<StudentDevelopment> studentDevelopments =new ArrayList<>();
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        if(type==0){
            List<Map<String,Object>> teacher=basicSQLService.find("select pp.id,  pp.name,yu.user_name from pj_parent_student  pps " +
                    " inner join pj_student ps on ps.user_id=pps.student_user_id " +
                    " inner join pj_parent pp on pp.user_id=pps.parent_user_id " +
                    " inner join yh_user yu on yu.id=pps.parent_user_id " +
                    " where ps.id="+id);
            studentDevelopments=studentDevelopmentService.findByStudentAll(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString(),Integer.parseInt(teacher.get(0).get("id").toString()));
        }else{
            studentDevelopments=studentDevelopmentService.findByStudentAll(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString(),id);
        }
        return studentDevelopments;
    }

    @GetMapping("/studentDevelopmentId")
    @ApiOperation(value = "品德发展评价详情", httpMethod = "GET")
    public StudentDevelopment findBystudentAll(@RequestParam Integer id){
            return studentDevelopmentService.findByStudentId(id);
    }
    }


