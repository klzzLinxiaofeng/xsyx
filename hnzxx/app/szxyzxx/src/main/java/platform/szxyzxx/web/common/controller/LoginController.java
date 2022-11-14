package platform.szxyzxx.web.common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.user.model.UserBinding;
import platform.education.user.model.UserLoginLog;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserLoginLogService;
import platform.education.user.utils.holder.PwdEncoderHolder;
import platform.education.user.vo.UserLoginLogCondition;
import platform.service.ucenter.client.UcenterClient;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.CasConfig;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.contants.WarnCode;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.shiro.exception.InactiveAccountException;
import platform.szxyzxx.web.common.shiro.exception.OverdueAccountException;
import platform.szxyzxx.web.common.util.MultiDomainResolver;
import platform.szxyzxx.web.common.util.holder.SessionInfoManager;
import platform.szxyzxx.web.common.vo.PcUserInfo;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Title:UserController.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 *
 * @Function 功能描述：登录控制器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月25日
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger log = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    @Qualifier("sessionInfoManager")
    private SessionInfoManager sessionInfoManager;

    @Autowired
    @Qualifier("userLoginLogService")
    private UserLoginLogService userLoginLogService;

    @Autowired
    @Qualifier("userBindingService")
    private UserBindingService userBindingService;

    @Resource
    private UcenterClient ucenterClient;

    /**
     * @param username
     * @param password
     * @param isRememberMe
     * @return
     * @Method loginPerformer
     * @Function 功能描述：
     * @Date 2014年10月27日
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginPerformer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "isRememberMe", defaultValue = "false") boolean isRememberMe) {
        Subject currentUser = SecurityUtils.getSubject();

        UserBinding userBinding = userBindingService.findByBindingName(username);
        if (userBinding != null) {
            System.out.println("开始记录登录日志。。。。。。。。。");
            UserLoginLogCondition userLoginLogCondition = new UserLoginLogCondition();
            userLoginLogCondition.setUserId(userBinding.getUserId());
            List<UserLoginLog> userLoginLogList = userLoginLogService.findUserLoginLogByCondition(userLoginLogCondition);
            if (userLoginLogList != null && userLoginLogList.size() > 0) {
                InetAddress addr;
                try {
                    addr = InetAddress.getLocalHost();
                    String curIp = addr.getHostAddress();
                    UserLoginLog userLoginLog = userLoginLogList.get(0);
                    userLoginLog.setLastIp(curIp);
                    userLoginLog.setCurLoginTime(new Date());
                    userLoginLogService.modify(userLoginLog);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    Date nowDate = new Date();
                    InetAddress addr = InetAddress.getLocalHost();
                    String curIp = addr.getHostAddress();
                    UserLoginLog userLoginLog = new UserLoginLog();
                    userLoginLog.setUserId(userBinding.getUserId());
                    userLoginLog.setCurLoginTime(nowDate);
                    userLoginLog.setLastLoginTime(nowDate);
                    userLoginLog.setCreateDate(nowDate);
                    userLoginLog.setCurIp(curIp);
                    userLoginLog.setLastIp(curIp);
                    userLoginLog.setModifyDate(nowDate);
                    userLoginLogService.add(userLoginLog);
                } catch (UnknownHostException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return excuteLogin(username, password, isRememberMe,
                currentUser.getSession());
    }

    @RequestMapping(value = "/cas/login/transfer")
    public String loginTransfer(HttpServletRequest request) {
        String basePath = MultiDomainResolver.resolver(request.getServerName(), SysContants.SZXY_BASE_PATH);
        String casBasePath = MultiDomainResolver.resolver(request.getServerName(), CasConfig.CAS_BASE_PATH);
        return "redirect:" + casBasePath + "/login?service=" + basePath + "/sso";
    }

//	@RequestMapping(value = "/cas/logout/transfer")
//	public String logoutTransfer(HttpServletRequest request, @RequestParam(value = "service", required = false) String service) {
//
//		if (service != null && !service.equals("")) {
//
//			service = MultiDomainResolver.resolver(request.getServerName(), service);
//
//			return "redirect:" + CasConfig.CAS_BASE_PATH + "/logout?service=" + service;
//		}
//		return "redirect:" + CasConfig.CAS_BASE_PATH + "/logout?service=" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//	}

    @ResponseBody
    @RequestMapping(value = "/pc/login")
    public PcUserInfo pc_loginPerformer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "isRememberMe", required = false, defaultValue = "false") boolean isRememberMe,
            HttpServletRequest request) {
        String result = verification(username, password, isRememberMe);
        PcUserInfo pcUserInfo = new PcUserInfo();
        System.out.println("登录是否成功"+result);
        pcUserInfo.setStatus(result);
        if (WarnCode.L$ACCEPT.equals(result)) {
            UserInfo userInfo = sessionInfoManager.getUserInfo(username);
            String userTypes = userInfo.getUserTypes();
            if (!userTypes.contains(SysContants.USER_TYPE_ADMIN)) {
                pcUserInfo.setStatus(WarnCode.L$403);
                return pcUserInfo;
            }
            String icon = userInfo.getIcon();
            String schoolLogo = userInfo.getSchoolLogo();
            if (icon != null) {
                icon = this.fileService.relativePath2HttpUrlByUUID(icon);
                userInfo.setIcon(!"".equals(icon) ? icon : null);
            } else {
                userInfo.setIcon(null);
            }

            if (schoolLogo != null) {
                schoolLogo = this.fileService
                        .relativePath2HttpUrlByUUID(schoolLogo);
                userInfo.setSchoolLogo(!"".equals(schoolLogo) ? schoolLogo
                        : null);
            } else {
                userInfo.setSchoolLogo(null);
            }
            BeanUtils.copyProperties(userInfo, pcUserInfo);
            return pcUserInfo;
        }

        return pcUserInfo;
    }

    private String excuteLogin(String username, String password,
                               boolean isRememberMe, Session session) {
        String md5Password = PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(password);
        String result = verification(username, md5Password, isRememberMe);
        if (WarnCode.L$ACCEPT.equals(result)) {
            sessionInfoManager.setUserInfoToSession(username, password, session);
        }
        System.out.println("登陆是否成功"+result);
        return result;
    }

    private String verification(String username, String password,
                                boolean isRememberMe) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password.toCharArray());
        token.setRememberMe(isRememberMe);
        try {
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                log.info("{} 登录成功.", username);
                return WarnCode.L$ACCEPT;
            }
        } catch (UnknownAccountException e) {
            log.info("{} 无存在此账号", username);
            return WarnCode.L$ACCOUNT;
        } catch (InactiveAccountException e) {
            log.info("{} 此账号并无激活", username);
            return WarnCode.ACCOUNT_INACTIVE;
        } catch (OverdueAccountException e) {
            log.info("{} 此账号失效", username);
            return WarnCode.ACCOUNT_OVERDUE;
        } catch (DisabledAccountException e) {
            log.info("{} 此账号被禁用", username);
            return WarnCode.ACCOUNT_DISABLE;
        } catch (IncorrectCredentialsException e) {
            log.info("{} 密码  {} 匹配错误", username, password);
            return WarnCode.L$PWD;
        } catch (Exception e) {
            log.info("登录失败");
            return WarnCode.L$EXE;
        }
        return WarnCode.L$PWD;
    }

    @RequestMapping(value = {"/login", "/login/index"}, method = RequestMethod.GET)
    public String loginAccessor(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/cr/paper/assembly/index";
        }
        return "/common/login";
    }

    @RequestMapping(value = "/login/dispatcher", method = RequestMethod.GET)
    public String dispatcher() {
        return "/common/loginer";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         @CurrentUser UserInfo user, HttpSession session,
                         Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
            if (user != null) {
                model.addAttribute("currentSystemId", user.getCurrentSystemId());
            }
            if (SysContants.SNS_ENABLE) {
                model.addAttribute("logout_msg", this.ucenterClient.synlogout());
            }
        } catch (AuthenticationException e) {
            log.info("登出失败", e);
        }
//		return "redirect:" + CasConfig.CAS_LOGOUT_REDIRECT_URL;
        return "/common/logout_suc";
    }


    @RequestMapping("/change/logout")
    @ResponseBody
    public String changeLogout(HttpServletRequest request,
                               @CurrentUser UserInfo user, HttpSession session,
                               Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
            if (user != null) {
                model.addAttribute("currentSystemId", user.getCurrentSystemId());
            }
            if (SysContants.SNS_ENABLE) {
                model.addAttribute("logout_msg", this.ucenterClient.synlogout());
            }
        } catch (AuthenticationException e) {
            log.info("登出失败", e);
        }
//		return "redirect:" + CasConfig.CAS_LOGOUT_REDIRECT_URL;
        return "1";
    }

    public void setUcenterClient(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    /**
     * @param username
     * @param password
     * @param isRememberMe
     * @return
     * @Method loginBbx
     * @Function 功能描述：
     * @Date 2016年01月11日
     */
    @RequestMapping(value = "/bbx/loginbbx", method = RequestMethod.POST)
    @ResponseBody
    public String loginBbx(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "isRememberMe", defaultValue = "false") boolean isRememberMe) {
        Subject currentUser = SecurityUtils.getSubject();
        return excuteLogin(username, password, isRememberMe,
                currentUser.getSession());
    }


    /**
     * 汉教云H5登录页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/bp/login", method = RequestMethod.GET)
    public String loginBpAccessor(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/bp/h5/home";
        }
        return "/bpH5/login";
    }

}
