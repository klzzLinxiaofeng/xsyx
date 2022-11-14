package platform.szxyzxx.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class SqlInjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //目前系统有传json字符串的地方，暂时注释
//        Enumeration<String> names = httpServletRequest.getParameterNames();
//        while(names.hasMoreElements()){
//            String name = names.nextElement();
//            String[] values = httpServletRequest.getParameterValues(name);
//            for(String value: values){
//                //sql注入直接拦截
//                if(judgeSQLInject(value.toLowerCase())){
//                    httpServletResponse.setContentType("text/html;charset=UTF-8");
//                    httpServletResponse.getWriter().print("参数非法");
//                    return false;
//                }
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }



    /**
     * 判断参数是否含有攻击串
     * @param value
     * @return
     */
    public boolean judgeSQLInject(String value){
        if(value == null || "".equals(value)){
            return false;
        }
        String xssStr = "insert|where|alter|table|from|create|and|or|select|update|delete|drop|truncate|%20|=|--|;|'|%|#|+|,|//|/| |\\|!=|(|)|script|alert|eval";
        String[] xssArr = xssStr.split("\\|");
        for(int i=0;i<xssArr.length;i++){
            if(value.indexOf(xssArr[i])>-1){
                return true;
            }
        }
        return false;
    }

}
