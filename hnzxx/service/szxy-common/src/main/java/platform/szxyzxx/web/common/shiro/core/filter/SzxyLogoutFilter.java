package platform.szxyzxx.web.common.shiro.core.filter;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import platform.szxyzxx.web.common.util.MultiDomainResolver;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SzxyLogoutFilter extends LogoutFilter {

    @Override
    protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
        if (redirectUrl.contains(";")) {
            redirectUrl =  MultiDomainResolver.resolver(request.getServerName(), redirectUrl);
        }
        WebUtils.issueRedirect(request, response, redirectUrl);
    }

}



