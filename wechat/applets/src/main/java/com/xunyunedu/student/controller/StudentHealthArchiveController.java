package com.xunyunedu.student.controller;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.student.pojo.JcGcItemPojo;
import com.xunyunedu.student.pojo.StudentHealthArchivePojo;
import com.xunyunedu.student.pojo.StudentHealthArchiveTypePojo;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.JcGcItemService;
import com.xunyunedu.student.service.StudentHealthArchiveService;
import com.xunyunedu.student.service.StudentHealthArchiveTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生健康管理
 * @author Eternityhua
 * @create 2020-12-09 15:58
 */
@RestController
@RequestMapping("/stuhealth")
public class StudentHealthArchiveController {


    Logger logger = LoggerFactory.getLogger(StudentAskingController.class);

    @Autowired
    private StudentHealthArchiveService studentHealthArchiveService;

    @Autowired
    private StudentHealthArchiveTypeService studentHealthArchiveTypeService;

    @Autowired
    private JcGcItemService jcGcItemService;

    /**
     * 查看学生健康记录详细信息
     * @param id 学生id
     * @return
     */
    @GetMapping("/queryOne")
    @Authorization
    public ApiResult getStuMessage(@RequestParam Integer id) {

        StudentHealthArchivePojo entity = studentHealthArchiveService.findStudentHealthArchiveById(id);
        return new ApiResult(ResultCode.SUCCESS, entity);
    }




    /**
     *学生健康信息的删除
     * @param ids
     * @return
     */
    @DeleteMapping("/delMessage")
    @Authorization
    public ApiResult delStudent(@RequestParam Integer ids){

        studentHealthArchiveService.delete(ids);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 学生健康信息逻辑删除
     * @param id
     * @return
     */
    @PutMapping("/delStuHealth")
    @Authorization
    public ApiResult delStuHealth(@RequestParam Integer id){

        studentHealthArchiveService.del(id);
        return new ApiResult(ResultCode.SUCCESS);
    }









    /**
     *
     * 条件下某个学生健康状况查询
     * @param id
     * @return
     */
    @GetMapping("/getOneStudentCondition")
    @Authorization
    public ApiResult getOneStudentCondition(@RequestParam("id") Integer id){

        StudentHealthArchivePojo healthArchivePojo = studentHealthArchiveService.queryById(id);
        return new ApiResult(ResultCode.SUCCESS, healthArchivePojo);

    }


    /**
     * 根据学生id查询学生所有健康记录信息
     * @param stuId
     * @return
     */
    @GetMapping("/getConditionByStuId")
    @Authorization
    public ApiResult getConditionByStuId(@RequestParam("stuId") Integer stuId){

        List<StudentHealthArchivePojo> pojos = studentHealthArchiveService.queryByStuId(stuId);
        return new ApiResult(ResultCode.SUCCESS, pojos);

    }






    /**
     * 条件下学生所有健康信息
     * @param
     * @return
     */
    @GetMapping("/queryAll")
    @Authorization
    public ApiResult getOneStudentCondition(){

        List<StudentHealthArchivePojo> list = studentHealthArchiveService.queryAll();
        return new ApiResult(ResultCode.SUCCESS, list);

    }


    /**
     *学生健康信息的发布
     * @param studentHealthArchivePojo
     * @return
     */
    @PostMapping("/addMessage")
    @Authorization
    public ApiResult addStuMessage(@RequestBody StudentHealthArchivePojo studentHealthArchivePojo){

        studentHealthArchiveService.save(studentHealthArchivePojo);
        return new ApiResult(ResultCode.SUCCESS);
    }


    /**
     * 修改学生健康信息
     * @param studentHealthArchivePojo
     * @return
     */
    @PostMapping("/updateStuMessage")
    @Authorization
    public ApiResult updateStuMessage(@RequestBody StudentHealthArchivePojo studentHealthArchivePojo){
        if (studentHealthArchivePojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        studentHealthArchiveService.update(studentHealthArchivePojo);
        return new ApiResult(ResultCode.SUCCESS);

    }



    /**
     * 条件下学生所有健康类型信息
     * @param
     * @return
     */
    @GetMapping("/queryAllType")
    @Authorization
    public ApiResult getAllHealthType(){

        List<StudentHealthArchiveTypePojo> list = studentHealthArchiveTypeService.findAllStudentHealthTypeArchive();
        return new ApiResult(ResultCode.SUCCESS, list);

    }


    /**
     * 查询所有的健康类型
     * @return
     */
    @GetMapping("/queryAllHealthType")
    @Authorization
    public ApiResult getAllHealthTypes(){

        List<JcGcItemPojo> types = jcGcItemService.queryAllHealthType();
        return new ApiResult(ResultCode.SUCCESS,types);

    }


    /**
     * 根据班级id查询学生所有健康记录信息
     * @param teamId
     * @return
     */
    @GetMapping("/getByTeamId")
    @Authorization
    public ApiResult getByTeamId(@RequestParam("teamId") Integer teamId,
                                          @RequestParam("schoolId") Integer schoolId){
        List<StudentPojo> students = studentHealthArchiveTypeService.getByTeamId(teamId,schoolId);
        return new ApiResult(ResultCode.SUCCESS, students);

    }


    /**
     * 根据班级id查询学生所有健康记录信息
     * @param teamId
     * @return
     */
    @GetMapping("/getConditionByTeamId")
    @Authorization
    public ApiResult getConditionByTeamId(@RequestParam("teamId") Integer teamId){

        List<StudentHealthArchivePojo> pojos = studentHealthArchiveService.queryByTeamId(teamId);
        return new ApiResult(ResultCode.SUCCESS, pojos);

    }






}
