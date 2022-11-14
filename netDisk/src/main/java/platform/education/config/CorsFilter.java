package platform.education.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局跨域处理
 */
@Component
public class CorsFilter implements Filter {

    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);



    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin",reqs.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", reqs.getHeader("Access-Control-Request-Headers"));
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init");
    }

//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse)resp;
//        HttpServletRequest request = (HttpServletRequest) req;
//        System.out.println(request.getHeader("Access-Control-Request-Method"));
//        if( request.getHeader("Access-Control-Request-Method") != null && request.getHeader("Access-Control-Request-Headers") != null ){
//            response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
//            response.setHeader("Access-Control-Allow-Methods",request.getHeader("Access-Control-Request-Method"));
//            response.setHeader("Access-Control-Allow-Headers",request.getHeader("Access-Control-Request-Headers"));
//            response.setHeader("Access-Control-Max-Age","300");
//            response.setHeader("Access-Control-Allow-Credentials","true");
//        }
//        filterChain.doFilter(request,response);
//    }
//    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}