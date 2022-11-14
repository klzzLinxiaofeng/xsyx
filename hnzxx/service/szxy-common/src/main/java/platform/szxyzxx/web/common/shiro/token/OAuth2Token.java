package platform.szxyzxx.web.common.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by Administrator on 2018/1/8.
 */
public class OAuth2Token implements AuthenticationToken {

    private String code;

    private String principal;

    public OAuth2Token(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return code;
    }
}
