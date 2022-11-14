package platform.szxyzxx.web.common.shiro.core.utils;

import org.apache.shiro.web.util.SavedRequest;

import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.MultiDomainResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/3/5.
 */
public class SaveRequest {

    //TODO - complete JavaDoc

    private String method;
    private String queryString;
    private String requestURI;
    private StringBuffer requestUrl;
    
    private String serverName;

    /**
     * Constructs a new instance from the given HTTP request.
     *
     * @param request the current request to save.
     */
    public SaveRequest(HttpServletRequest request) {
        this.method = request.getMethod();
        this.queryString = request.getQueryString();
        this.requestURI = request.getRequestURI();
        this.requestUrl = request.getRequestURL();
        this.serverName = request.getServerName();
    }

    public String getMethod() {
        return method;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getRequestUrl() {
        String basePath = SysContants.SZXY_BASE_PATH;
        basePath = MultiDomainResolver.resolver(this.serverName, basePath);
        StringBuilder requestUrl = new StringBuilder(basePath);
        requestUrl = requestUrl.append(this.requestURI);
        if (getQueryString() != null) {
        	requestUrl.append("?").append(getQueryString());
        }
System.out.println("oauth url" + requestUrl + "------------------------------------------------------------");
        return requestUrl.toString();
    }
}
