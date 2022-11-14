package platform.szxyzxx.web.oauth2.controller;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.oauth2.service.OAuthService;
import platform.education.user.model.Group;
import platform.education.user.service.GroupUserService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserRoleService;
import platform.szxyzxx.web.bbx.util.CommonUtil;
import platform.szxyzxx.web.common.contants.CasConfig;
import platform.szxyzxx.web.common.controller.LoginController;
import platform.szxyzxx.web.common.shiro.core.utils.SaveRequest;
import platform.szxyzxx.web.common.util.MultiDomainResolver;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.oauth2.contants.OAuth2Config;
import platform.szxyzxx.web.oauth2.util.encoderUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2018/1/9.
 */
@Controller
public class OAuth2Controller {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private OAuth2Config oAuth2Config;

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private UserBindingService userBindingService;

    @Autowired
    private GroupUserService groupUserService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ParentService parentService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private ProfileService profileService;

    /**
     *  用于session失效后的uri重定向
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/oauth2/login")
    public String oauth2Login(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {

//        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
//        String saveRequestURI = savedRequest.getRequestURI();
//        StringBuffer url = request.getRequestURL();

    	SaveRequest savedRequest = null;
        if (session != null) {
            savedRequest = (SaveRequest) session.getAttribute("oauth2SavedRequest");
        }

//        String serverName = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).toString();
//        try {
//            saveRequestURI = URLEncoder.encode(serverName + saveRequestURI, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        String xbullRedirectUrl = oAuth2Config.getXbullRedirectUrl();
        String xbullAppId = oAuth2Config.getXbullAppId();
        String reponseType = oAuth2Config.getXbullCodeParam();
        String scope = oAuth2Config.getXbullScope();

//        String redirectUri = "http://ps.10niu.cn/PrimarySchool-develop/Wechat/oauth2/authorize?xbull_appid=59f2cd6e823cs&response_type=code&scope=api_userinfo&state=10001&redirect_uri=" + saveRequestURI;
        String redirectUri = xbullRedirectUrl + "?xbull_appid=" + xbullAppId + "&response_type=" + reponseType
                + "&scope=" + scope + "&state=10001&redirect_uri=" + URLEncoder.encode(savedRequest.getRequestUrl(), "utf-8");
        session.removeAttribute("oauth2SavedRequest");
        return "redirect:" + redirectUri;
    }


    /**
     *  获取授权码
     */
    @RequestMapping(value = "/oauth2/authorize")
    public Object getAuthorizationCode(HttpServletRequest request, Model model) throws URISyntaxException, OAuthSystemException{

        try {
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            String responseType = oauthRequest.getResponseType();
            String clientId = oauthRequest.getClientId();
            String redirectURI = oauthRequest.getRedirectURI();
            String state = oauthRequest.getState();
            String ssoType = oauthRequest.getParam("ssoType");
            boolean toCas = false;
//            Set<String> scopes = oauthRequest.getScopes();

            //判断传入的客户端id是否正确
            if (!oAuthService.checkClientId(clientId)) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                        .setErrorDescription("错误的client_id")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }

            Subject subject = SecurityUtils.getSubject();
            //如果用户没有登录，跳转到登录页面
            if(!subject.isAuthenticated()) {
                //TODO 登录失败时跳转到登录页面
                if(!login(subject, request)) {
                    model.addAttribute("appName", oAuthService.getClientName(clientId));
                    return new ModelAndView("/common/oauth2login", model.asMap());
                }
                toCas = true;
            }

            String username = (String)subject.getPrincipal();
            String authorizationCode = null;
            //response_type对应的的值必须是code，另一个值token对应的是直接生成access_token
            if (ResponseType.CODE.toString().equals(responseType)) {
                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oauthIssuerImpl.authorizationCode();
                //往缓存中存入授权码， key=code,value=username+redirectURI
//                oAuthService.addIntoCache(authorizationCode, username + "###" + redirectURI);
                oAuthService.addCode(authorizationCode, username + "###" + redirectURI, Long.parseLong(oAuth2Config.getCodeExpireTime()));
            }

            if (toCas && subject.isAuthenticated() && "jasigCAS".equals(ssoType)) {
                String casBasePath = MultiDomainResolver.resolver(request.getServerName(), CasConfig.CAS_BASE_PATH);
                return "redirect:"+ casBasePath + "/MqtCookieGenerator?ssoType=oauth&username=" + username + "&password=" + request.getParameter("password") + "&serviceUrl=" + redirectURI + "?code=" + authorizationCode;
            }
//            进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
            //设置授权码，state的值自动返回，无需设置
            builder.setCode(authorizationCode);

            //TODO 关于scopes授权内容的判定
            //构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
//
            //根据OAuthResponse返回ResponseEntity响应
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));


        } catch (OAuthProblemException e) {
            e.printStackTrace();

            //告诉客户端没有传入redirectURI直接报错
            String redirectURI = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectURI)) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError("redirect_uri_mismatch")
                        .setErrorDescription("错误的redirect_uri")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }

            OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .setError(e.getError()).setErrorDescription(e.getDescription()).buildJSONMessage();
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));

        }

    }

    private boolean login(Subject subject, HttpServletRequest request) {
        if("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = encoderUtil.encoder(password);

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return true;
        } catch (Exception e) {
            request.setAttribute("error", "登录失败:" + e.getClass().getName());
            return false;
        }
    }


    /**
     * 获取access_token
     */
    @RequestMapping(value = "/oauth2/accessToken", method = RequestMethod.POST)
    public Object getAccessToken(HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
        try {
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
            String clientId = oauthRequest.getClientId();
            String clientSecret = oauthRequest.getClientSecret();
            String code = oauthRequest.getCode();
            String grantType = oauthRequest.getGrantType();
            String redirectURI = oauthRequest.getRedirectURI();

            //判断client_id是否正确
            if (!oAuthService.checkClientId(clientId)) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription("错误的client_id")
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }

            //判断client_secret是否正确
            if (!oAuthService.checkClientSecret(clientId, clientSecret)) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                                .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                                .setErrorDescription("错误的client_secret")
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }


            //判断类型，获取token的只验证AUTHORIZATION_CODE
            if (GrantType.AUTHORIZATION_CODE.toString().equals(grantType)) {
                //验证缓存中的code，K-V : code - username，时效10分钟，只能使用1次
//                if (!oAuthService.checkAuthCode(code)) {
                if (!oAuthService.checkCode(code)) {
                    OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .setError(OAuthError.TokenResponse.INVALID_GRANT)
                            .setErrorDescription("错误的授权码")
                            .buildJSONMessage();
                    return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
                }
            }

            //验证redirectURI是否一致
//            String[] values = oAuthService.getCacheValue(code).split("###");
//            System.out.println("***********  code : " + oAuthService.getCode(code));
            String[] values = oAuthService.getValueByCode(code).split("###");
            String userName = values[0];
//            if (!values[1].equals(redirectURI)) {
//                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
//                        .setError(OAuthError.OAUTH_ERROR_URI)
    //                        .setErrorDescription("错误的redirect_uri")
//                        .buildJSONMessage();
//                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
//            }

            //生成access_token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oauthIssuerImpl.accessToken();
            String refreshToken = oauthIssuerImpl.refreshToken();

            //往缓存中存入access_token, refresh_token
            //记录数据须包含 username、access_token、refresh_token、createTime、消亡时间（根据生命周期定）
//            oAuthService.addIntoCache(accessToken, userName);
//            oAuthService.addIntoCache(refreshToken, userName + "###" + accessToken);
            oAuthService.addAccessToken(accessToken, userName, Long.parseLong(oAuth2Config.getAccessTokenExpireTime().trim()));
            oAuthService.addRefreshAccessToken(refreshToken, accessToken + "###" + userName, Long.parseLong(oAuth2Config.getRefreshTokenExpireTime().trim()));

            //清除授权码，确保一个code只能使用一次
            oAuthService.removeCode(code);

            //生成OAuth响应
            //TODO expires_in令牌的生命周期需通过配置文件设置
            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setExpiresIn(oAuth2Config.getAccessTokenExpireTime())
                    .buildJSONMessage();

            //根据OAuthResponse生成ResponseEntity
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));

        } catch (OAuthProblemException e) {
            e.printStackTrace();
            //构建错误响应
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e).buildJSONMessage();
            return new ResponseEntity(res.getBody(), HttpStatus.valueOf(res.getResponseStatus()));
        }
    }


    /**
     * 刷新access_token
     */
    @RequestMapping(value = "/oauth2/refreshToken", method = RequestMethod.POST)
    public Object refreshToken(HttpServletRequest request) throws URISyntaxException, OAuthSystemException{
        try {
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
            String grantType = oauthRequest.getGrantType();
            String refreshToken = oauthRequest.getRefreshToken();
            String clientId = oauthRequest.getClientId();
            String clientSecret = oauthRequest.getClientSecret();

            //验证client_id和client_secret
            if (!oAuthService.checkClientId(clientId)) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                        .setErrorDescription("错误的client_id")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }
            if (!oAuthService.checkClientSecret(clientId, clientSecret)) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                        .setErrorDescription("错误的client_secret")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }


            //验证refresh_token是否存在
//            if (!oAuthService.checkAuthCode(refreshToken)) {
            if (!oAuthService.checkRefreshAccessToken(refreshToken)) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                        .setErrorDescription("错误的refresh_token")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }

            //获取新的access_token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oauthIssuerImpl.accessToken();

            //往缓存中存入access_token, refresh_token，K-V : access_token - username，时效7200秒；refresh_token - username, 时效30天
//            String[] values = oAuthService.getCode(refreshToken).split("###");
//            String userName = values[0];
//            oAuthService.addCode(accessToken, userName, 3600L);
//            oAuthService.addCode(refreshToken, userName + "###" + accessToken, 864000L);
            String value = oAuthService.getValueByRefreshAccessToken(refreshToken);

            String[] values = value.split("###");

            String oldAccessToken = values[0];
            String userName = values[1];
            oAuthService.removeAccessToken(oldAccessToken);
            oAuthService.addAccessToken(accessToken, userName, Long.parseLong(oAuth2Config.getAccessTokenExpireTime().trim()));
            oAuthService.addRefreshAccessToken(refreshToken, accessToken + "###" + userName, oAuthService.getRefreshAcessTokenOfExpire(refreshToken));
            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setExpiresIn(oAuth2Config.getAccessTokenExpireTime().trim())
                    .buildJSONMessage();

            //根据OAuthResponse生成ResponseEntity
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));

        } catch (OAuthProblemException e) {
            e.printStackTrace();
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e).buildJSONMessage();
            return new ResponseEntity(res.getBody(), HttpStatus.valueOf(res.getResponseStatus()));
        }

    }

    @RequestMapping(value = "oauth2/userInfo")
    public Object getUserInfo(HttpServletRequest request) throws OAuthSystemException {
        try {
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
            String accessToken = oauthRequest.getAccessToken();

            if (!oAuthService.checkAccessToken(accessToken)) {
                OAuthResponse response = OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                        .setErrorDescription("错误的access_token")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }

            String username = oAuthService.getValueByAccessToken(accessToken);
            return new ResponseEntity(getUserInformation(username, request), HttpStatus.OK);

        } catch (OAuthProblemException e) {
            e.printStackTrace();
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e).buildJSONMessage();
            return new ResponseEntity(res.getBody(), HttpStatus.valueOf(res.getResponseStatus()));
        }

    }


    private Object getUserInformation(String userName, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        Integer userId = userBindingService.findUserId(userName);
        List<Group> groups = groupUserService.findGroups(userId, "1");

        if (groups != null && groups.size() > 0) {
            Group group = groups.get(0);
            Integer schoolId = group.getOwnerId();
            School school = schoolService.findSchoolById(schoolId);
            if (school != null) {
                map.put("schoolName", school.getName());
            }
            List<String> types = userRoleService.findRoleTypesByUserId(userId, 1);
            String role = "";
            String name = "";
            String sex = "";
            if (types != null && types.size() > 0) {
                for (String type : types) {
                    //1=教师 2=管理员 3=家长 4=学生
                    if ("1".equals(type)) {
                        Teacher teacher = teacherService.findOfUser(schoolId, userId);
                        if (teacher != null) {
                            map.put("teacherId", teacher.getId());
                            if (name == null || "".equals(name)) {
                                name = teacher.getName();
                            }
                            if (sex == null || "".equals(sex)) {
                                sex = teacher.getSex();
                            }
                            role += "TEACHER，";
                        }
                    }
                    if ("2".equals(type)) {
                        role += "ADMIN，";
                    }
                    if ("3".equals(type)) {
                        Parent parent = parentService.findUniqueByUserId(userId);
                        if (parent != null) {
                            map.put("parentId", parent.getId());
                            if (name == null || "".equals(name)) {
                                name = parent.getName();
                            }
                            role += "PARENT，";
                        }
                    }
                    if ("4".equals(type)) {
                        Student student = studentService.findStudentByUserId(userId);
                        if (student != null) {
                            map.put("studentId", student.getId());
                            if (name == null || "".equals(name)) {
                                name = student.getName();
                            }
                            if (sex == null || "".equals(sex)) {
                                sex = student.getSex();
                            }
                            role += "STUDENT，";
                        }
                    }
                }
                if (role.length() > 0) {
                    role = role.substring(0, role.length() - 1);
                }
                map.put("role", role);
            }
            map.put("schoolId", schoolId);
            map.put("name", name);
            map.put("sex", sex);
        }
        map.put("userName", userName);
        map.put("userId", userId);
        map.put("iconUrl", CommonUtil.getImgSrc(userId, request, profileService));
        return map;
    }


    @RequestMapping("oauth2/logout")
    public String logout(@RequestParam(value = "redirect_url", required = true) String redirectUrl) {
        Subject currentUser = SecurityUtils.getSubject();
        String result = ResponseInfomation.OPERATION_SUC;
        try {
            currentUser.logout();
        } catch (Exception e) {
            result = ResponseInfomation.OPERATION_FAIL;
        }
        return "redirect:" + redirectUrl;
    }


//    private boolean checkClientId(String clientId){
//        //TODO 从数据库中查询
//        if ("mqt".equals(clientId)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private boolean checkClientSecret(String clientId, String clientSecret){
//        //TODO 从数据库中查询对比
//        if ("education".equals(clientSecret)) {
//            return true;
//        } else {
//            return false;
//        }
//    }


}
