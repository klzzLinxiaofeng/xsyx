package platform.szxyzxx.web.common.shiro.realm;

import net.sf.json.JSONObject;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import platform.education.user.model.User;
import platform.education.user.service.UserService;
import platform.szxyzxx.web.common.shiro.token.OAuth2Token;
import platform.szxyzxx.web.common.util.holder.SessionInfoManager;
import platform.szxyzxx.web.common.vo.XbullUserInfo;

/**
 * Created by Administrator on 2018/1/8.
 */
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private SessionInfoManager sessionInfoManager;

    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;
    private String userInfoUrl;
    private String redirectUrl;
    private String grantType = "client_credential";

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;//表示此Realm只支持OAuth2Token类型
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        OAuth2Token oAuth2Token = (OAuth2Token) token;
        String code = oAuth2Token.getCode();
        String username = extractUsername(code);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, code, getName());
        return authenticationInfo;
    }

    private String extractUsername(String code) {

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            //通过授权码获取access_token
            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(accessTokenUrl)
                    .setParameter("grant_type", grantType)
                    .setParameter("xbull_appid", clientId)
                    .setParameter("xbull_app_secret", clientSecret)
                    .setCode(code)
                    .buildQueryMessage();

            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.GET);
            String accessToken = oAuthResponse.getAccessToken();
            System.out.println("accessToken : " + accessToken);

            //通过access_token获取用户信息
            OAuthClientRequest userInfoRequest = OAuthClientRequest
                    .tokenLocation(userInfoUrl)
                    .setParameter("xbull_appid", clientId)
                    .setParameter("access_token", accessToken)
                    .setParameter("lang", "zh_CN")
                    .buildQueryMessage();

            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            System.out.println(resourceResponse.getBody());
            if (200 == resourceResponse.getResponseCode()) {
                String body = resourceResponse.getBody();
                System.out.println("***************十牛用户：" + body + "*********************************************");
                JSONObject jsonObject= JSONObject.fromObject(body);
                XbullUserInfo xbullUserInfo = (XbullUserInfo) JSONObject.toBean(jsonObject, XbullUserInfo.class);
                System.out.println("*************************用户："+xbullUserInfo.getSchool_id()+" / "+xbullUserInfo.getParent_id()+"/"+xbullUserInfo.getParent_app_code()+" / "+xbullUserInfo.getParent_name()+" / "+xbullUserInfo.getChild_id()+" / "+xbullUserInfo.getChild_app_code()+" / "+xbullUserInfo.getChild_name()+" / "+xbullUserInfo.getTeacher_id()+" / "+xbullUserInfo.getTeacher_app_code()+" / "+xbullUserInfo.getTeacher_name());
                System.out.println("getParent_app_code:"+xbullUserInfo.getParent_app_code());
                System.out.println("getTeacher_app_code:"+xbullUserInfo.getTeacher_app_code());
                String role = xbullUserInfo.getRole();
                Integer userId = null;
//                if (xbullUserInfo.getParent_app_code() != null) userId = xbullUserInfo.getParent_app_code();
//                if (xbullUserInfo.getTeacher_app_code() != null) userId = xbullUserInfo.getTeacher_app_code();
                if (xbullUserInfo.getParent_app_code() != null) {
                    userId = xbullUserInfo.getParent_app_code();
                    System.out.println("家长：" + userId);
                }
                if (xbullUserInfo.getTeacher_app_code() != null) {
                    userId = xbullUserInfo.getTeacher_app_code();
                    System.out.println("老师：" + userId);
                }
                System.out.println("id：" + userId);
                User user = userService.findUserById(userId);
                System.out.println(user);
                if (user != null) {
                    return user.getUserName();
                }
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException(e);
        }
    }

}
