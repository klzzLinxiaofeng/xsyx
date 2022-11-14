package platform.education.rest.open.service.impl;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.oauth2.service.OAuthService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.open.service.TokenRestService;
import platform.education.user.model.User;
import platform.education.user.service.UserService;

import javax.servlet.http.HttpServletResponse;

public class TokenRestServiceImpl implements TokenRestService {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("oAuthService")
    private OAuthService oAuthService;

    private long accessTokenExpireTime = 7200l;

    private long refreshTokenExpireTime = 864000l;

    public void setAccessTokenExpireTime(long accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

    public void setRefreshTokenExpireTime(long refreshTokenExpireTime) {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    @Override
    public Object getToken(String sign, String appKey, String timeStamp, Integer userId) throws OAuthSystemException {
        ResponseInfo info = new ResponseInfo();
        if (userId == null || "".equals(userId)) {
            info.setMsg("参数错误");
            info.setDetail("缺少userId参数");
            info.setParam("userId");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        try {
            User user = this.userService.findUserById(userId);
            if (user != null) {
                String userName = user.getUserName();
                OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                String accessToken = oauthIssuerImpl.accessToken();
                String refreshToken = oauthIssuerImpl.refreshToken();
                oAuthService.addSingleSignOnAccessToken(accessToken, userName, accessTokenExpireTime);
                oAuthService.addRefreshSingleSignOnAccessToken(refreshToken, accessToken + "###" + userName, refreshTokenExpireTime);
                OAuthResponse response = OAuthASResponse
                        .tokenResponse(HttpServletResponse.SC_OK)
                        .setAccessToken(accessToken)
                        .setRefreshToken(refreshToken)
                        .setExpiresIn(String.valueOf(accessTokenExpireTime))
                        .buildJSONMessage();
                return response.getBody();
            }
            info.setMsg("数据无效");
            info.setDetail("数据无效，userId为"+ userId + "的用户不存在");
            info.setParam("userId");
            return new ResponseError(CommonCode.S$INVALID_DATA, info);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            info.setMsg("系统异常");
            info.setDetail("系统异常");
            return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
        }


    }

}
