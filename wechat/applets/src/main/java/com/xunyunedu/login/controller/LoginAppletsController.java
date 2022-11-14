package com.xunyunedu.login.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.login.pojo.UserPojo;
import com.xunyunedu.login.service.UserLoginService;
import com.xunyunedu.wechat.service.WechatApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小程序授权登录
 *
 * @author: yhc
 * @Date: 2020/9/18 15:07
 * @Description: 小程序授权登录
 */
@RequestMapping("/login")
@RestController
@Api(value = "/login", description = "小程序授权登录")
public class LoginAppletsController {
    Logger logger = LoggerFactory.getLogger(LoginAppletsController.class);


    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private WechatApiService wechatApiService;

    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping("/getBg")
    @ResponseBody
    public String getBg(){
        try {
            return basicSQLService.findUnique("select bg_path from sys_login_page where id=1").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 微信用户授权
     *
     * @param code 临时登录凭证code
     * @return
     */
    @PostMapping("/authorization")
    @ApiOperation(value = "用户授权", httpMethod = "POST")
    public ApiResult authorization(@ApiParam(required = true, value = "临时登录凭证code", name = "code") @RequestParam String code) {
        if (StrUtil.hasEmpty(code)) {
            logger.error("获取code失败");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 微信小程序秘钥
        String openid = "";
        // 发送请求，返回Json字符串

        // 根据小程序的code向这个url发送请求
        String url = wechatApiService.getWechatUrl(code);

        String str = wechatApiService.httpRequest(url, "GET", null);
        logger.error("获取用户信息{}", str);

        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str);
        Integer errorcode = jsonObject.getInteger("errcode");
        // 我们需要的openid，在一个小程序中，openid是唯一的
        openid = (String) jsonObject.get("openid");
        if (errorcode != null) {
            logger.error("用户授权失败");
            return new ApiResult(ResultCode.Authorization);
        }
        if (StrUtil.isEmpty(openid)) {
            logger.error("获取code");
            return new ApiResult(ResultCode.ERROR_WX_OPENID);
        }

        // 获取用户信息成功，将信息返回前端
        return new ApiResult(ResultCode.SUCCESS, jsonObject);
    }

    /**
     * 绑定用户账号登录返回token（支持手机号码登录）
     * 用户验证及绑定微信，返回token 和用户账户 参数，前端持久化储存，每次在请求头中携带
     *
     * @param name   姓名/手机号码
     * @param pwd    密码
     * @param openid
     * @param type   用户所属类型：3家长1教师
     * @return
     */
    @PostMapping("/bindUser")
    @ApiOperation(value = "", httpMethod = "POST")
    public ApiResult bindUser(@RequestParam String name,
                              @RequestParam String pwd,
                              @RequestParam String openid,
                              @RequestParam Integer type) {
        if (StrUtil.hasEmpty(name, pwd, openid) || type == null) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 代表学生端/教师端，你先查询账号是否属于对应的端，如果用户学生端输入了教师端的账号，直接返回错误码比如2004给我
        Map<String, String> dataMap = new HashMap<>(2);
        UserPojo pojo = new UserPojo();
        String token = "";
        //对密码进行加密和数据库中的密码进行比较
        Integer flg = userLoginService.getAccuontStatus(name, pwd, type, dataMap, pojo);
        if (flg == 1) {
            //比较成功 生成token
            token = userLoginService.createToken(name, pwd, openid, pojo);
            dataMap.put("token", token);
        } else {
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }

        return new ApiResult(ResultCode.SUCCESS, dataMap);
    }


    /**
     * 修改密码
     * 修改完成密码后，会清空token，需要重新登录
     *
     * @param newPwd
     * @param oldPwd
     * @param userName
     * @return
     */
    @RequestMapping(value = "/editorPassword", method = RequestMethod.POST)
    @Authorization
    public ApiResult editPassword(@RequestParam("newPwd") String newPwd, @RequestParam("oldPwd") String oldPwd, @RequestParam String userName, HttpServletRequest request) {
        if (StrUtil.hasEmpty(newPwd, oldPwd, userName)) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String token = request.getHeader("token");
        userLoginService.modifyUserPassword(userName, newPwd, oldPwd, token);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 修改手机号码 ---修改完成后，会清空token，需要重新登录
     * 修改手机号，会同时修改登录绑定表，学生 教师 家长,pj_person
     *
     * @param name   用户名（手机号登录也是会返回登录账号的用户名）
     * @param mobile 需要修改手机号码
     * @return
     */
    @RequestMapping(value = "/editMobile", method = RequestMethod.POST)
    @Authorization
    public ApiResult editMobile(@RequestParam("name") String name, @RequestParam("mobile") String mobile) {
        if (StrUtil.hasEmpty(name, mobile)) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        userLoginService.editMobile(name, mobile);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 获取微信公众号文章列表
     * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Get_materials_list.html
     *
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     * @return
     */
    @GetMapping(value = "/publicContentList")
    public ApiResult getPublicContentList(@RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset, @RequestParam(value = "count", defaultValue = "3", required = false) Integer count) {
        String contentList = userLoginService.getPublicContentList(offset, count);
        return new ApiResult(ResultCode.SUCCESS, contentList);
    }


    /**
     * 获取公众号文章详细内容
     * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/Getting_Permanent_Assets.html
     *
     * @param mediaId 文章id
     * @return
     */
    @GetMapping(value = "/getMaterial")
    public ApiResult getMaterial(String mediaId) {
        String contentList = userLoginService.getMaterial(mediaId);
        return new ApiResult(ResultCode.SUCCESS, contentList);
    }
    /*
    *判断当前登录用户状态
     */
    @GetMapping(value = "/findByZhuantai")
    public ApiResult findByZhuantai(@RequestParam Integer id,
                                    @RequestParam Integer type) {
        if (id==null || type==null) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        if(type==0){
            List<Map<String,Object>> map=basicSQLService.find("select * from pj_student where id="+id);
                if(map.get(0).get("study_state").toString().equals("01")){
                    return new ApiResult(ResultCode.SUCCESS);
                }else{
                    return new ApiResult(ResultCode.USER_LEAVE_SCHOOL);
                }
        }else{
            List<Map<String,Object>> map=basicSQLService.find("select * from pj_teacher where id="+id);
            if(map.get(0).get("job_state").toString().equals("11")){
                return new ApiResult(ResultCode.SUCCESS);
            }else{
                return new ApiResult(ResultCode.USER_LEAVE_SCHOOL);
            }
        }

    }

}
