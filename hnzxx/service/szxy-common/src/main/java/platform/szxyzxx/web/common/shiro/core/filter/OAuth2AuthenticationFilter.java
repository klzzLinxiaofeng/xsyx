package platform.szxyzxx.web.common.shiro.core.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import platform.szxyzxx.web.common.shiro.core.utils.SaveRequest;
import platform.szxyzxx.web.common.shiro.token.OAuth2Token;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/8.
 */
public class OAuth2AuthenticationFilter extends AuthenticatingFilter {

    //oauth2 authc code参数名
    private String codeParam = "code";
    //oauth2服务器响应类型
    private String responseType = "code";
    //客户端id
    private String clientId;
    //服务器端登录成功/失败后重定向到的客户端地址
    private String redirectUrl;

    private String failureUrl;

    public void setCodeParam(String codeParam) {
        this.codeParam = codeParam;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
    	
    	
    	
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String code = httpRequest.getParameter(codeParam);
        return new OAuth2Token(code);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	System.out.println("进入方法----------------------------------------------------------------------------------------------------------------------------------------------------");
        String error = request.getParameter("error");
        String errorDescription = request.getParameter("error_description");
        if(!StringUtils.isEmpty(error)) {//如果服务端返回了错误
            WebUtils.issueRedirect(request, response, failureUrl + "?error=" + error + "error_description=" + errorDescription);
            return false;
        }

        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated()) {
//            WebUtils.saveRequest(request);
//            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            SaveRequest savedRequest = new SaveRequest(httpRequest);
            session.setAttribute("oauth2SavedRequest", savedRequest);
            if(StringUtils.isEmpty(request.getParameter(codeParam))) {
                saveRequestAndRedirectToLogin(request, response);
                return false;
            }
            return executeLogin(request, response);
        }

        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
    	System.out.println("oauth2 redirect url:进入方法----------------------------------------------------------------------------------------------------------------------------------------------------");
        String successUrl = null;
        boolean contextRelative = true;
        SaveRequest savedRequest = null;
        Session session = subject.getSession(false);
        if (session != null) {
            savedRequest = (SaveRequest) session.getAttribute("oauth2SavedRequest");
        }
        if (savedRequest != null) {
            session.removeAttribute("oauth2SavedRequest");
        }
        if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
        	System.out.println("oauth2 redirect url:" + savedRequest.getRequestUrl() + "----------------------------------------------------------------------------------------------------------------------------------------------------");
            successUrl = savedRequest.getRequestUrl();
            contextRelative = false;
        }

        if (successUrl == null) {
            throw new IllegalStateException("Success URL not available via saved request or via the " +
                    "successUrlFallback method parameter. One of these must be non-null for " +
                    "issueSuccessRedirect() to work.");
        }
        WebUtils.issueRedirect(request, response, successUrl, null, contextRelative);




        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            try {
                issueSuccessRedirect(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                WebUtils.issueRedirect(request, response, failureUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
