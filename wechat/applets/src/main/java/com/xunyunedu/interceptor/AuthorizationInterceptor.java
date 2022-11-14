package com.xunyunedu.interceptor;

import cn.hutool.core.util.StrUtil;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.util.redis.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author: yhc
 * @Date: 2020/9/19 11:52
 * @Description: 对请求进行拦截判断token
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    private static final String TOKEN_PRE = "wxtoken:";



    /**
     * @author: yhc
     * @Date: 2020/9/19 13:01
     * @Description: 对指定注解方法进行拦截，登录不进行拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.addHeader("Access-Control-Max-Age", "3600");
        }

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果注明了@authorization，需要进行验证,进行验证返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            // 获取token进行验证

            String token = request.getHeader("token");
            if (StrUtil.hasEmpty(token)) {
                logger.error("未获取到token请重新登录绑定账号！");
                String str = "{'errorCode':801,'message':'缺少token，无法验证，请重新登录绑定账号','data':null}";
                dealErrorReturn(request, response, str);
                return false;
            } else {
                // 根据token去查询是否存在
                logger.debug("判断token状态");
                if (getToken(token)) {
                    logger.debug("登录成功！");
                    return true;
                }
            }
        }
        return true;
    }


    /**
     * @author: yhc
     * @Date: 2020/9/19 12:56
     * @Description:检测到没有token，直接返回不验证
     */
    public void dealErrorReturn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj) {
        String json = (String) obj;
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            writer.print(json);
        } catch (IOException ex) {
            logger.error("response error", ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    /**
     * 查询token
     *
     * @param token
     * @return boolean
     */
    public boolean getToken(String token) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolUtil.getConnect();
            String value = jedis.get(TOKEN_PRE + token);
            if (StrUtil.hasEmpty(value)) {
                logger.error("登录错误，未获取到token值！");
                throw new BusinessRuntimeException(ResultCode.ERROR_TOKEN);
            }
            return true;
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
    }

}
