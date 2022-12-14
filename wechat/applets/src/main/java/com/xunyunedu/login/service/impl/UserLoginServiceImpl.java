package com.xunyunedu.login.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.login.dao.UserLoginDao;
import com.xunyunedu.login.pojo.RolePojo;
import com.xunyunedu.login.pojo.UserBindingPojo;
import com.xunyunedu.login.pojo.UserPojo;
import com.xunyunedu.login.service.UserLoginService;
import com.xunyunedu.personinfor.dao.MyInforDao;
import com.xunyunedu.student.dao.ParentDao;
import com.xunyunedu.student.dao.StudentDao;
import com.xunyunedu.student.pojo.ParentPojo;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.util.pwdutil.PwdEncoder;
import com.xunyunedu.util.redis.RedisPoolUtil;
import com.xunyunedu.wechat.service.WechatApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2020/9/19 14:07
 * @Description:
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private TeacherHomeDao teacherHomeDao;

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private WechatApiService wechatApiService;

    @Autowired
    private MyInforDao myInforDao;

    private static final String TOKEN_PRE = "wxtoken:";

    /**
     * ??????????????????
     *
     * @param name    ????????????/??????????????????
     * @param pwd     ??????
     * @param type
     * @param dataMap
     * @return 1 ?????? 2 ????????????????????????
     */
    @Override
    public Integer getAccuontStatus(String name, String pwd, Integer type, Map<String, String> dataMap, UserPojo userParam) {
        // ??????????????????
        /**
         * Type ??????????????????yh_usertype,yh_role,yh_user_role
         * 1	?????????
         * 2	?????????
         * 3	??????
         * 4	??????
         */
        // 1.?????????????????????2.??????????????????
        // ????????????????????????????????? ??????????????????????????????  0=????????????, 1=????????????
        Integer bindType = 0;
        // ??????????????????????????????10???????????????????????????????????????????????????11?????????
        if (StrUtil.trim(name).length() == 11) {
            bindType = 1;
        }
        // ????????????????????????????????????1.????????????????????????????????????2.???????????????????????????????????????
        List<UserPojo> user = userLoginDao.getUserInfoByName(name, bindType);
        if (CollUtil.isEmpty(user)) {
            log.info("???????????????---????????????????????????");
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }
        UserPojo userPojo = null;
        boolean flg = false;
        // ???????????????????????????????????????
        for (UserPojo pojo : user) {
            List<RolePojo> rolePojoList = userLoginDao.getRoleType(pojo.getId(), type);
            if (CollUtil.isNotEmpty(rolePojoList)) {
                dataMap.put("name", pojo.getUserName());
                userPojo = pojo;
                userParam.setId(pojo.getId());
                flg = true;
                break;
            }
        }
        if (!flg) {
            log.info("???????????????---??????????????????");
            // ????????????????????????USER_ACCOUNT_ERROR???????????????????????????
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (StrUtil.isNotEmpty(userPojo.getPassword())) {
            // ??????????????? ?????????????????????
            String encodePwd = new PwdEncoder().encodePassword(pwd);
            if (userPojo.getPassword().equals(encodePwd)) {
                // ????????????????????????????????????????????????????????? `job_state`  11=?????? 01=?????? 07=?????? ????????????11?????????????????????
                // `study_state` ??????????????????:01=?????????03=?????????07=????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????01??????????????????
                // ??????
                if (type == 3) {
                    // ??????????????????????????????
                    List<com.xunyunedu.personinfor.pojo.StudentPojo> stuList = myInforDao.getUserInfoByName(userPojo.getUserName(),null);
                    if (CollUtil.isEmpty(stuList)) {
                        throw new BusinessRuntimeException(ResultCode.USER_LEAVE_SCHOOL);
                    }
                } else if (type == 1) {
                    TeacherPojo teacherPojo = new TeacherPojo();
                    teacherPojo.setUserName(userPojo.getUserName());
                    TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
                    if (teacher == null) {
                        throw new BusinessRuntimeException(ResultCode.USER_LEAVE_SCHOOL);
                    }
                }
                return 1;
            } else {
                log.info("???????????????---????????????");
                throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
            }
        }
        return null;
    }

    @Override
    public Integer getAccuontStatusYiHao(String name, String pwd, Map<String, Object> dataMap, UserPojo userParam) {
        Integer bindType = 0;
        // ????????????????????????????????????1.????????????????????????????????????2.???????????????????????????????????????
        List<UserPojo> user = userLoginDao.getUserInfoByName(name, bindType);

        if (CollUtil.isEmpty(user)) {
            log.info("???????????????---????????????????????????");
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }

        UserPojo userPojo = null;
        boolean flg = false;
        // ???????????????????????????????????????
        for (UserPojo pojo : user) {
            List<RolePojo> rolePojoList = userLoginDao.getRoleType(pojo.getId(), 1);
            if (CollUtil.isNotEmpty(rolePojoList)) {
                dataMap.put("name", pojo.getUserName());
                userPojo = pojo;
                userParam.setId(pojo.getId());
                flg = true;
                break;
            }
        }
        if (!flg) {
            log.info("???????????????---??????????????????");
            // ????????????????????????USER_ACCOUNT_ERROR???????????????????????????
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }

        if (StrUtil.isNotEmpty(userPojo.getPassword())) {
            // ??????????????? ?????????????????????
            String encodePwd = new PwdEncoder().encodePassword(pwd);
            if (userPojo.getPassword().equals(encodePwd)) {
                // ????????????????????????????????????????????????????????? `job_state`  11=?????? 01=?????? 07=?????? ????????????11?????????????????????
                // `study_state` ??????????????????:01=?????????03=?????????07=????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????01??????????????????
                    TeacherPojo teacherPojo = new TeacherPojo();
                    teacherPojo.setUserName(userPojo.getUserName());
                    TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
                    if (teacher == null) {
                        throw new BusinessRuntimeException(ResultCode.USER_LEAVE_SCHOOL);
                }
                return 1;
            } else {
                log.info("???????????????---????????????");
                throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
            }
        }
        return null;
    }

    @Override
    public TeacherPojo getTeacher(String userName) {
        TeacherPojo teacherPojo = new TeacherPojo();
        teacherPojo.setUserName(userName);
        TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
        if (teacher == null) {
            throw new BusinessRuntimeException(ResultCode.USER_LEAVE_SCHOOL);
        }
        return teacher;
    }


    /**
     * ??????token??????????????????
     *
     * @param name
     * @param pwd
     * @param openid
     * @return
     */
    @Override
    public String createToken(String name, String pwd, String openid, UserPojo pojo) {

        String token = IdUtil.simpleUUID();
        Map<String, String> dataMap = new HashMap<>(3);
        dataMap.put("user_name", name);
//        dataMap.put("pwd", pwd);
        dataMap.put("openid", openid);
        Jedis jedis = null;
        try {
            // ?????????????????????redis
            jedis = RedisPoolUtil.getConnect();
            jedis.set(TOKEN_PRE + token, JSONObject.toJSONString(dataMap));
            // ???openid?????????yh_user???
            if (pojo != null && pojo.getId() != null && StrUtil.isNotEmpty(openid)) {
                UserPojo userPojo = new UserPojo();
                userPojo.setId(pojo.getId());
                userPojo.setOpenId(openid);
                userPojo.setModifyDate(new Date());
                userLoginDao.update(userPojo);
            } else {
                log.info("????????????openid???????????????????????????");
                throw new BusinessRuntimeException(ResultCode.ERROR_WX_OPENID);
            }
            return token;
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }

    }
    @Override
    public String createTokenYiHao(String name, String pwd, UserPojo pojo) {

        String token = IdUtil.simpleUUID();
        Map<String, String> dataMap = new HashMap<>(3);
        dataMap.put("user_name", name);
//        dataMap.put("pwd", pwd);
        Jedis jedis = null;
        try {
            // ?????????????????????redis
            jedis = RedisPoolUtil.getConnect();
            jedis.set(TOKEN_PRE + token, JSONObject.toJSONString(dataMap));
            // ???openid?????????yh_user???
            return token;
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }

    }
    /**
     * ????????????
     *
     * @param userName
     * @param newPwd
     * @param oldPwd
     * @param oldToken
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyUserPassword(String userName, String newPwd, String oldPwd, String oldToken) {
        oldPwd = new PwdEncoder().encodePassword(oldPwd);

        UserPojo user = userLoginDao.findByUserName(userName);
        if (user != null) {
            String password = user.getPassword();
            if (!oldPwd.equals(password)) {
                throw new BusinessRuntimeException(ResultCode.PASSWORD_ERROR);
            }
            user.setPassword(new PwdEncoder().encodePassword(newPwd));
            userLoginDao.update(user);
            // ???????????????token
            if (StrUtil.isNotEmpty(oldToken)) {
                Jedis jedis = null;
                try {
                    jedis = RedisPoolUtil.getConnect();
                    jedis.del(TOKEN_PRE + oldToken);
                } finally {
                    if (jedis != null) {
                        RedisPoolUtil.closeConnect(jedis);
                    }
                }
            }
        } else {
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }

    }

    /**
     * ??????????????????
     *
     * @param name
     * @param mobile
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editMobile(String name, String mobile) {
        /**
         * ??????????????????????????????pj_parent pj_teacher pj_student yh_user_binding pj_person
         * ????????????????????????
         */

        // ???????????????????????????????????????????????????????????????

        // ???????????????????????????id
        UserPojo byUserName = userLoginDao.findByUserName(name);
        if (byUserName == null) {
            throw new BusinessRuntimeException(ResultCode.USER_NOT_FONUD);
        }
        // ??????????????????????????????????????????
        UserBindingPojo userBindingPojo = new UserBindingPojo();
        userBindingPojo.setUserId(byUserName.getId());
        userBindingPojo.setBindingType("1");
        List<UserBindingPojo> bindingPojo = userLoginDao.findByBindName(userBindingPojo);
        String bindingName = bindingPojo.get(0).getBindingName();
        if (bindingName.equals(mobile)) {
            return;
        }

        // ??????????????????????????????
        userBindingPojo.setUserId(null);
        userBindingPojo.setBindingName(mobile);
        List<UserBindingPojo> bindingPojos = userLoginDao.findByBindName(userBindingPojo);
        if (CollUtil.isNotEmpty(bindingPojos)) {
            throw new BusinessRuntimeException(ResultCode.DATA_EXITS);
        }
        TeacherPojo teacherPojo = new TeacherPojo();
        teacherPojo.setUserName(name);
        TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
        Date modifyDate = new Date();
        // ????????????
        if (teacher != null) {
            teacher.setMobile(mobile);
            teacher.setModifyDate(modifyDate);
            teacherHomeDao.update(teacher);
        }
        // ????????????
        ParentPojo parentPojo = new ParentPojo();
        parentPojo.setUserName(name);
        List<ParentPojo> parentPojoList = parentDao.read(parentPojo);
        for (ParentPojo pojo : parentPojoList) {
            pojo.setMobile(mobile);
            pojo.setModifyDate(modifyDate);
            pojo.setName(null);
            pojo.setLicensePlate(null);
            parentDao.update(pojo);
        }
        // ????????????
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setUserName(name);
        List<StudentPojo> studentPojoList = studentDao.read(studentPojo);
        for (StudentPojo pojo : studentPojoList) {
            pojo.setMobile(mobile);
            pojo.setModifyDate(modifyDate);
            studentDao.update(pojo);
        }
        // ????????????????????????
        UserBindingPojo userBinding = new UserBindingPojo();
        userBinding.setUserId(byUserName.getId());
        userBinding.setBindingName(mobile);
        userBinding.setBindingType("1");
        userBinding.setEnabled(true);
        userBinding.setIsDeleted(false);
        userBinding.setModifyDate(modifyDate);
        userLoginDao.updateBindMobile(userBinding);


    }

    /**
     * ?????????????????????????????????
     *
     * @param offset
     * @param count
     * @return
     */
    @Override
    public String getPublicContentList(Integer offset, Integer count) {
        // ?????????????????????token
        String accessToken = wechatApiService.getPublicAccessToken();
        return wechatApiService.getContentList(accessToken, offset, count);
    }

    /**
     * ?????????????????????????????????
     *
     * @param mediaId
     * @return
     */
    @Override
    public String getMaterial(String mediaId) {
        // ?????????????????????token
        String accessToken = wechatApiService.getPublicAccessToken();
        return wechatApiService.getMaterial(accessToken, mediaId);
    }


}
