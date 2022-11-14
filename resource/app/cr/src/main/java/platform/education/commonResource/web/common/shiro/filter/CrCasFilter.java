package platform.education.commonResource.web.common.shiro.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.commonResource.web.common.shiro.code.token.CrCasToken;
import platform.education.commonResource.web.common.shiro.utils.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by clouds on 2017/5/10.
 */
public class CrCasFilter extends AuthenticatingFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthenticatingFilter.class);
    private static final String TICKET_PARAMETER = "ticket";
    private String failureUrl;

    public CrCasFilter() {
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ticket = httpRequest.getParameter("ticket");
        String serverName = request.getServerName();
        return new CrCasToken(ticket, serverName);
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return this.executeLogin(request, response);
    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
        return false;
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request, ServletResponse response) {
        Subject subject = this.getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            try {
                WebUtils.issueRedirect(request, response, this.failureUrl);
            } catch (IOException var7) {
                logger.error("Cannot redirect to failure url : {}", this.failureUrl, var7);
            }
        } else {
            try {
                this.issueSuccessRedirect(request, response);
            } catch (Exception var8) {
                logger.error("Cannot redirect to the default success url", var8);
            }
        }

        return false;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

}
