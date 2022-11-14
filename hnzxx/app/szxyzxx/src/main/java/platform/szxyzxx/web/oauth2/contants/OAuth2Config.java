package platform.szxyzxx.web.oauth2.contants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/1/10.
 */
@Component("oAuth2Config")
public class OAuth2Config {

    @Value("${xbull.app.id}")
    private String xbullAppId;

    @Value("${xbull.app.sercer}")
    private String xbullAppSercer;

    @Value("${xbull.code.param}")
    private String xbullCodeParam;

    @Value("${xbull.scope}")
    private String xbullScope;

    @Value("${xbull.redirect.url}")
    private String xbullRedirectUrl;

    @Value("${code.expire.time}")
    private String codeExpireTime;

    @Value("${access.token.expire.time}")
    private String accessTokenExpireTime;

    @Value("${refresh.token.expire.time}")
    private String refreshTokenExpireTime;


    public String getXbullAppId() {
        return xbullAppId;
    }

    public String getXbullAppSercer() {
        return xbullAppSercer;
    }

    public String getXbullCodeParam() {
        return xbullCodeParam;
    }

    public String getXbullScope() {
        return xbullScope;
    }

    public String getXbullRedirectUrl() {
        return xbullRedirectUrl;
    }

    public String getCodeExpireTime() {
        return codeExpireTime;
    }

    public String getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public String getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }
}
