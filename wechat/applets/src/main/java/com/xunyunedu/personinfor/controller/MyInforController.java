package com.xunyunedu.personinfor.controller;

import cn.hutool.core.util.StrUtil;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.personinfor.pojo.StudentPojo;
import com.xunyunedu.personinfor.service.MyinforService;
import com.xunyunedu.student.pojo.ParentPojo;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.teacher.service.TeacherHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 我的界面
 *
 * @author: yhc
 * @Date: 2020/9/18 15:07
 * @Description: 小程序授权登录
 */

@RequestMapping("/information")
@RestController
@Api(value = "/information", description = "我的 信息界面接口")
public class MyInforController {
    Logger logger = LoggerFactory.getLogger(MyInforController.class);

    @Autowired
    private MyinforService myinforService;

    @Autowired
    private TeacherHomeService teacherHomeService;
    @Autowired
    private BasicSQLService basicSQLService;


    /**
     * 获取家长账户下的所有孩子信息
     *
     * @param name 登录的家长账号name
     * @return
     */
    @GetMapping("/childrenInfor")
    @ApiOperation(value = "获取家长账户下的所有孩子信息和选课信息", httpMethod = "GET")
    @Authorization
    public ApiResult getChildren(@ApiParam(required = true, value = "登录的家长账号name", name = "name") @RequestParam String name) {
        if (StrUtil.hasEmpty(name)) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 根据家长的账号查询名下的孩子信息
        Map<String, List<StudentPojo>> list = myinforService.getInformation(name);

        return new ApiResult(ResultCode.SUCCESS, list);
    }

    /**
     * 切换绑定：
     * 用户的openid不变，需要将前端持久化的 “name账号名称” 字段清空 ，后端也将清空redis中的token下次重新登录，无需微信再次授权
     *
     * @param token
     * @return
     */
    @DeleteMapping("/switchBindUser")
    @ApiOperation(value = "切换绑定：用户的openid不变，需要将前端持久化的 “name账号名称” 字段清空 ，后端也将清空token下次重新登录，无需授权", httpMethod = "POST")
    public ApiResult switchBindUser(@ApiParam(required = true, value = "token", name = "token") @RequestParam String token) {
        myinforService.removeLoginStaus(token);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 获取教师信息
     * 包含头像、性别、头像
     *
     * @param teacherPojo 登录的账号name
     * @return
     */
    @GetMapping("/getTeacherInfo")
    @Authorization
    public ApiResult getTeacherInfo(TeacherPojo teacherPojo) {
        if (teacherPojo == null || StrUtil.isEmpty(teacherPojo.getUserName())) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }

        // 根据教师信息
        TeacherPojo teacher = teacherHomeService.getTeacherByCondition(teacherPojo);
        List<Map<String,Object>> mapList=basicSQLService.find("select * from pj_school_term_current  where 1=1  and school_id= 215");
        teacher.setSchoolYear(mapList.get(0).get("school_year").toString());
        teacher.setTermCode(mapList.get(0).get("school_term_code").toString());
        List<Map<String,Object>> mapList2=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id=(select id from yh_user where user_name="+teacherPojo.getUserName()+")");
        for(Map<String,Object> bb:mapList2){
            if( bb.get("code").toString().equals("SCHOOL_MASTER")){
               teacher.setXiaoZhang(true);
                return new ApiResult(ResultCode.SUCCESS, teacher);
            }
        }
        teacher.setXiaoZhang(false);
        return new ApiResult(ResultCode.SUCCESS, teacher);
    }


    /**
     * 获取车牌
     *
     * @param userName 登录的账号name
     * @return
     */
    @GetMapping("/getLicensePlate")
    @Authorization
    public ApiResult getlicensePlate(@RequestParam String userName) {
        if (StrUtil.isEmpty(userName)) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        ParentPojo parentPojo = myinforService.getParentInfo(userName);
        if(StringUtils.isNotEmpty(parentPojo.getLicensePlate())){
            parentPojo.setLicensePlates(Arrays.asList(parentPojo.getLicensePlate().split(",")));
        }
        return new ApiResult(ResultCode.SUCCESS, parentPojo);
    }


    /**
     * 修改车牌信息
     *
     * @param parentPojo
     * @return
     */
    @PostMapping("/modifyLicensePlate")
    public ApiResult afterSchool(@RequestBody ParentPojo parentPojo) {
        if (parentPojo == null || StrUtil.isEmpty(parentPojo.getUserName())) {
            ApiResult r=new ApiResult();
            r.setCode(400);
            r.setMsg("参数不可为空");
            return r;
        }

        if(parentPojo.getLicensePlates()!=null && parentPojo.getLicensePlates().size()>0){
            StringBuilder s=new StringBuilder();
            for (String licensePlate : parentPojo.getLicensePlates()) {
                if(s.length()!=0){
                    s.append(",");
                }
                s.append(licensePlate);
            }
            parentPojo.setLicensePlate(s.toString());
        }

        String e=myinforService.modifyParentInfo(parentPojo);
        if(e!=null){
            ApiResult r=new ApiResult();
            r.setCode(400);
            r.setMsg(e);
            return r;
        }
        return new ApiResult(ResultCode.SUCCESS);
    }

}
