package com.xunyunedu.character.controller;


import com.xunyunedu.character.pojo.Evaluation;
import com.xunyunedu.character.pojo.Records;
import com.xunyunedu.character.service.EvaluationService;
import com.xunyunedu.character.service.RecordService;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.logger.vo.Loggers;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
*品格养成
*/
@RestController
@RequestMapping(value = "/character")
@Api(value = "/character", description = "品格接口")
public class CharacterController {
    Logger logger = LoggerFactory.getLogger(CharacterController.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;
    @GetMapping("/student")
    @ApiOperation(value = "品格接口学生名字搜索", httpMethod = "GET")
    public List<StudentPojo> findByStudent(@RequestParam
                                             String stuName){
        List<StudentPojo> student=studentService.getIdByUserId(stuName,null);
            return student;
    }

    @GetMapping("/evaver")
    @ApiOperation(value = "品格接口类型列表", httpMethod = "Get")
    public List<Evaluation> findByEvaver(){
        List<Evaluation> evaluation=evaluationService.findByAll();
        return evaluation;
    }

    @GetMapping("/defenqingkuang")
    @ApiOperation(value = "品格养成学生分数", httpMethod = "GET")
    public Integer defenqingkuang(@RequestParam Integer studentId,
                                  @RequestParam Integer evelaId){
        Integer num=evaluationService.defenqingkuang(studentId,evelaId);
        return num;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加", httpMethod = "POST")
    public Map createrecord(@RequestBody Records records) {
        Boolean falg = recordService.create(records);
        Map map = new HashMap();
        if (falg) {
            Loggers logger = new Loggers();
            logger.setCaozuoId(records.getTeacherId());
            logger.setName(records.getTeaName());
            List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+records.getTeacherId());
            logger.setUsername(teacher.get(0).get("user_name").toString());
            logger.setMobile(teacher.get(0).get("mobile").toString());
            logger.setMokuaiName("品格养成");
            logger.setType(1);
            logger.setMessage("新增品格养成记录");
            List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
            logger.setSchoolYear(termInfo.get(0).get("school_year").toString());
            logger.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
            loggerService.create(logger);
            map.put("code", 200);
            map.put("susses", "评价成功");
            return map;
        }
        map.put("code", 400);
        map.put("susses", "评价失败");
        return map;
    }


    @GetMapping("/PaiMing")
    @ApiOperation(value = "品格养成排行榜", httpMethod = "Get")
    public List<Map<String,Object>> findByPaiMing(@RequestParam Integer schoolId){
        List<Map<String,Object>> list=recordService.pinGePaiHang(schoolId);
        return list;
    }
    @GetMapping("/PaiMingTeam")
    @ApiOperation(value = "品格班级排行榜", httpMethod = "Get")
    public List<Map<String,Object>> findByBg( @RequestParam Integer evaluationId,
                                              @RequestParam Integer teamId,
                                              @RequestParam Integer schoolId){
        List<Map<String,Object>> list=recordService.pinGePaiHangBanJi(schoolId,teamId,evaluationId);
        return list;
    }

    @GetMapping("/PaiMingGrade")
    @ApiOperation(value = "品格年级排行榜", httpMethod = "Get")
    public List<Map<String,Object>> findByNg( @RequestParam Integer evaluationId,
                                              @RequestParam Integer gradeId,
                                              @RequestParam Integer schoolId){
        if(evaluationId==null || gradeId==null || schoolId==null){
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return recordService.pinGePaiHangNianJi(schoolId,gradeId,evaluationId);
    }

    @GetMapping("/PaiMingjilu")
    @ApiOperation(value = "查看记录/查看详细记录", httpMethod = "Get")
    public ApiResult findByJiLu(@RequestParam Integer studentId,
                                @RequestParam Integer schoolId,
                                @RequestParam(value = "evaluationId",required = false) Integer evaluationId){
        if(studentId==null || schoolId==null){
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, recordService.pinGeStudentJiLu(schoolId,studentId,evaluationId));
    }

    @GetMapping("/TuBiao")
    @ApiOperation(value = "图表", httpMethod = "Get")
    public List<Map<String,Object>> findByTupian(
                                              @RequestParam Integer studentId,
                                              @RequestParam Integer schoolId,
                                              @RequestParam String schoolYear){
        return recordService.findByTupian(schoolId,studentId,schoolYear);
    }
    @GetMapping("/SaoMa")
    @ApiOperation(value = "扫一扫", httpMethod = "Get")
    public StudentPojo findBySaoMa(
            @RequestParam Integer studentId,
            @RequestParam Integer schoolId){
        return recordService.findBySaoMa(studentId,schoolId);
    }
}


