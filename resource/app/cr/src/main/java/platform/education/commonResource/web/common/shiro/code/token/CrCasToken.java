package platform.education.commonResource.web.common.shiro.code.token;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class CrCasToken implements RememberMeAuthenticationToken {

    private static final long serialVersionUID = 8587329689973009598L;
    private String ticket = null;
    private String userId = null;
    private String serverName = null;
    private boolean isRememberMe = false;

    public CrCasToken(String ticket) {
        this.ticket = ticket;
    }

    public CrCasToken(String ticket, String serverName) {
        this.ticket = ticket;
        this.serverName = serverName;
    }

    public Object getPrincipal() {
        return this.userId;
    }

    public Object getCredentials() {
        return this.ticket;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isRememberMe() {
        return this.isRememberMe;
    }

    public void setRememberMe(boolean isRememberMe) {
        this.isRememberMe = isRememberMe;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
