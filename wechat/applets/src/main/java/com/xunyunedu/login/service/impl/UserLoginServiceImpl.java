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
     * 判断账号信息
     *
     * @param name    账号名称/或者手机号码
     * @param pwd     密码
     * @param type
     * @param dataMap
     * @return 1 成功 2 账号或者密码错误
     */
    @Override
    public Integer getAccuontStatus(String name, String pwd, Integer type, Map<String, String> dataMap, UserPojo userParam) {
        // 根据账号查询
        /**
         * Type 对应数据库：yh_usertype,yh_role,yh_user_role
         * 1	教职工
         * 2	管理员
         * 3	家长
         * 4	学生
         */
        // 1.获取用户信息，2.判断用户类型
        // 目前查询登录绑定状态是 以绑定账号的来源类型  0=官方代码, 1=手机号码
        Integer bindType = 0;
        // 目前后端生成的账号为10位数字，可以区分是账号还是手机号码11位登录
        if (StrUtil.trim(name).length() == 11) {
            bindType = 1;
        }
        // 会存在即是教师又是家长，1.手机号码和账号是一样的，2.手机号码一样，但是账号不同
        List<UserPojo> user = userLoginDao.getUserInfoByName(name, bindType);
        if (CollUtil.isEmpty(user)) {
            log.info("小程序登录---未获取到用户信息");
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }
        UserPojo userPojo = null;
        boolean flg = false;
        // 判断用户对应的角色是否符合
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
            log.info("小程序登录---用户角色不符");
            // 提示信息全部改为USER_ACCOUNT_ERROR，避免进行猜测登录
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (StrUtil.isNotEmpty(userPojo.getPassword())) {
            // 对密码加密 按照老系统方式
            String encodePwd = new PwdEncoder().encodePassword(pwd);
            if (userPojo.getPassword().equals(encodePwd)) {
                // 校验老师和学生的状态：老师判断是否在职 `job_state`  11=在职 01=退休 07=离职 状态不为11无法登录小程序
                // `study_state` 学生在读状态:01=在读。03=退学。07=毕业，小程序是给家长使用，所以都是使用的家长账号登录，一个家长下面可以有多个孩子，如果多个孩子状态都不是01，将无法登录
                // 家长
                if (type == 3) {
                    // 查询家长下的孩子信息
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
                log.info("小程序登录---密码错误");
                throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
            }
        }
        return null;
    }

    @Override
    public Integer getAccuontStatusYiHao(String name, String pwd, Map<String, Object> dataMap, UserPojo userParam) {
        Integer bindType = 0;
        // 会存在即是教师又是家长，1.手机号码和账号是一样的，2.手机号码一样，但是账号不同
        List<UserPojo> user = userLoginDao.getUserInfoByName(name, bindType);

        if (CollUtil.isEmpty(user)) {
            log.info("小程序登录---未获取到用户信息");
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }

        UserPojo userPojo = null;
        boolean flg = false;
        // 判断用户对应的角色是否符合
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
            log.info("小程序登录---用户角色不符");
            // 提示信息全部改为USER_ACCOUNT_ERROR，避免进行猜测登录
            throw new BusinessRuntimeException(ResultCode.USER_ACCOUNT_ERROR);
        }

        if (StrUtil.isNotEmpty(userPojo.getPassword())) {
            // 对密码加密 按照老系统方式
            String encodePwd = new PwdEncoder().encodePassword(pwd);
            if (userPojo.getPassword().equals(encodePwd)) {
                // 校验老师和学生的状态：老师判断是否在职 `job_state`  11=在职 01=退休 07=离职 状态不为11无法登录小程序
                // `study_state` 学生在读状态:01=在读。03=退学。07=毕业，小程序是给家长使用，所以都是使用的家长账号登录，一个家长下面可以有多个孩子，如果多个孩子状态都不是01，将无法登录
                    TeacherPojo teacherPojo = new TeacherPojo();
                    teacherPojo.setUserName(userPojo.getUserName());
                    TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
                    if (teacher == null) {
                        throw new BusinessRuntimeException(ResultCode.USER_LEAVE_SCHOOL);
                }
                return 1;
            } else {
                log.info("小程序登录---密码错误");
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
     * 生成token保存用户信息
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
            // 保存用户信息到redis
            jedis = RedisPoolUtil.getConnect();
            jedis.set(TOKEN_PRE + token, JSONObject.toJSONString(dataMap));
            // 将openid保存到yh_user表
            if (pojo != null && pojo.getId() != null && StrUtil.isNotEmpty(openid)) {
                UserPojo userPojo = new UserPojo();
                userPojo.setId(pojo.getId());
                userPojo.setOpenId(openid);
                userPojo.setModifyDate(new Date());
                userLoginDao.update(userPojo);
            } else {
                log.info("绑定用户openid失败，缺少参数信息");
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
            // 保存用户信息到redis
            jedis = RedisPoolUtil.getConnect();
            jedis.set(TOKEN_PRE + token, JSONObject.toJSONString(dataMap));
            // 将openid保存到yh_user表
            return token;
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }

    }
    /**
     * 修改密码
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
            // 删除原来的token
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
     * 修改手机号码
     *
     * @param name
     * @param mobile
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editMobile(String name, String mobile) {
        /**
         * 修改手机号码涉及表：pj_parent pj_teacher pj_student yh_user_binding pj_person
         * 参照后端系统逻辑
         */

        // 判断手机号码是否需要修改、手机号码是否重复

        // 获取当前登录用户的id
        UserPojo byUserName = userLoginDao.findByUserName(name);
        if (byUserName == null) {
            throw new BusinessRuntimeException(ResultCode.USER_NOT_FONUD);
        }
        // 判断当前手机号码是否没有修改
        UserBindingPojo userBindingPojo = new UserBindingPojo();
        userBindingPojo.setUserId(byUserName.getId());
        userBindingPojo.setBindingType("1");
        List<UserBindingPojo> bindingPojo = userLoginDao.findByBindName(userBindingPojo);
        String bindingName = bindingPojo.get(0).getBindingName();
        if (bindingName.equals(mobile)) {
            return;
        }

        // 判断手机号码是否重复
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
        // 修改教师
        if (teacher != null) {
            teacher.setMobile(mobile);
            teacher.setModifyDate(modifyDate);
            teacherHomeDao.update(teacher);
        }
        // 修改家长
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
        // 修改学生
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setUserName(name);
        List<StudentPojo> studentPojoList = studentDao.read(studentPojo);
        for (StudentPojo pojo : studentPojoList) {
            pojo.setMobile(mobile);
            pojo.setModifyDate(modifyDate);
            studentDao.update(pojo);
        }
        // 修改登录绑定账号
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
     * 获取微信公众号文章列表
     *
     * @param offset
     * @param count
     * @return
     */
    @Override
    public String getPublicContentList(Integer offset, Integer count) {
        // 获取微信公众号token
        String accessToken = wechatApiService.getPublicAccessToken();
        return wechatApiService.getContentList(accessToken, offset, count);
    }

    /**
     * 获取公众号文章详细内容
     *
     * @param mediaId
     * @return
     */
    @Override
    public String getMaterial(String mediaId) {
        // 获取微信公众号token
        String accessToken = wechatApiService.getPublicAccessToken();
        return wechatApiService.getMaterial(accessToken, mediaId);
    }


}
