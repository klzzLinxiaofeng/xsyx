package platform.education.commonResource.web.common.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import platform.education.commonResource.web.common.util.MultiDomainResolver;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CrFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {


        String loginUrl = MultiDomainResolver.resolver(request.getServerName(),getLoginUrl());

        WebUtils.issueRedirect(request, response, loginUrl);



    }
}
