package com.xunyunedu.login.controller;


import cn.hutool.core.util.StrUtil;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.login.pojo.UserPojo;
import com.xunyunedu.login.service.UserLoginService;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
* 亿豪登录
*/
@RequestMapping("/ZyLogin")
@RestController
@Api(value = "/ZyLogin", description = "亿豪授权登录")
public class LoginYiHaoController {
    Logger logger = LoggerFactory.getLogger(LoginAppletsController.class);
    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private BasicSQLService basicSQLService;

    @PostMapping("/bindUserYiHao")
    @ApiOperation(value = "一号登录", httpMethod = "POST")
    public ApiResult bindUser(@RequestParam String name,
                              @RequestParam String pwd) {
        if (StrUtil.hasEmpty(name, pwd) ) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 代表学生端/教师端，你先查询账号是否属于对应的端，如果用户学生端输入了教师端的账号，直接返回错误码比如2004给我
        Map<String,Object> dataMap = new HashMap<>(3);
        UserPojo pojo = new UserPojo();
        String token = "";
        //对密码进行加密和数据库中的密码进行比较
        Integer flg = userLoginService.getAccuontStatusYiHao(name, pwd, dataMap, pojo);
        if (flg == 1) {
            //比较成功 生成token
            token = userLoginService.createTokenYiHao(name, pwd, pojo);
            TeacherPojo teacherPojo=userLoginService.getTeacher(name);
            Integer userId=teacherPojo.getUserId();
            List<Map<String,Object>> mapList2=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id="+userId);
            int type=1;
            for(Map<String,Object> bb:mapList2){
                if(bb.get("name").toString().equals("班主任") || bb.get("code").toString().equals("CLASS_MASTER")){
                    type=0;
                }
            }
            if(type==0){

                List<Map<String,Object>> mapList=basicSQLService.find("select * from pj_school_term_current  where 1=1  and school_id= 215");
                List<Map<String,Object>> mapList3=basicSQLService.find("select ptt.*,pt.name as teamName from pj_team_teacher ptt inner join pj_team pt on pt.id=ptt.team_id where ptt.teacher_id="+teacherPojo.getId()+"  and ptt.is_delete=0 and ptt.type=1  and ptt.school_year="+mapList.get(0).get("school_year").toString());
                dataMap.put("token", token);
                teacherPojo.setSchoolYear(mapList.get(0).get("school_year").toString());
                teacherPojo.setTermCode(mapList.get(0).get("school_term_code").toString());
                teacherPojo.setTeamId(mapList3.get(0).get("team_id").toString());
                teacherPojo.setTeamName(mapList3.get(0).get("teamName").toString());
                teacherPojo.setGradeId(Integer.parseInt(mapList3.get(0).get("grade_id").toString()));
                dataMap.put("teacher",teacherPojo);
            }else{
                throw new BusinessRuntimeException(ResultCode.USER_ROLE_ERROR);
            }
        } else {
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return new ApiResult(ResultCode.SUCCESS, dataMap);
    }
}
