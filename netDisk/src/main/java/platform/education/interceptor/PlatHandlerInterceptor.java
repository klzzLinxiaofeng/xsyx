package platform.education.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import platform.education.annotation.AuthIgnore;
import platform.education.constant.Constant;
import platform.education.netdisk.common.FrameworkException;
import platform.education.netdisk.common.ResultEnum;
import platform.education.util.RedisUtils;
import platform.education.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import platform.education.generalTeachingAffair.model.Teacher;

public class PlatHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 认证
        AuthIgnore annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }
        // 如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }
        // 获取用户凭证
        String token = request.getHeader(Constant.TOKEN);
        if (StringUtil.isEmpty(token)) {
            token = request.getParameter(Constant.TOKEN);
        }
        // 凭证为空
        if (StringUtil.isEmpty(token)) {
            throw new FrameworkException(ResultEnum.ERROR_301.getCode(), ResultEnum.ERROR_301.getMsg());
        }
        // 从redis中读取token,同时延长有效时间为一天
        String userId = redisUtils.get(token, Constant.DEFAULT_EXPIRE);
        if (StringUtils.isBlank(userId)) {
            throw new FrameworkException(ResultEnum.ERROR_301.getCode(), ResultEnum.ERROR_301.getMsg());
        }

        /*
        // 鉴权
        PermissionIgnore permissionIgnore;
        if (handler instanceof HandlerMethod) {
            permissionIgnore = ((HandlerMethod) handler).getMethodAnnotation(PermissionIgnore.class);
            // 如果有@PermissionIgnore注解，则不做认证
            if (permissionIgnore != null) {
                return true;
            }
        }

        // 1 根据userId作为key，去redis查询用户的所有权限 ，如果请求路径在结果集中，则放行，否则无权限错误
        Set<String> userPrivilege = frameworkMenuService.getUrlByUserId(Long.parseLong(userId));
        String pathInfo = request.getServletPath();
        if (!userPrivilege.contains(pathInfo)) {
            throw new FrameworkException(ResultEnum.ERROR_401.getCode(), ResultEnum.ERROR_401.getMsg());
        }
         */

//        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);
//        requestWrapper.getParameterMap().put("userId", new String[]{String.valueOf(userId)});
//        requestWrapper.setAttribute();
        request.setAttribute("userId", userId);
/**
        HttpSession session = request.getSession();
        Object teacher = session.getAttribute(userId);
        if (teacher != null) {
            request.setAttribute(userId, teacher);
        }*/

        return true;
    }
}
