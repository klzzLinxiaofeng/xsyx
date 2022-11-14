package platform.education.oauth2.service;

/**
 * Created by Administrator on 2018/1/18.
 */
public interface OAuthService {

    void addCode(String code, String value, Long timeout);

    String getValueByCode(String code);

    void removeCode(String code);

    boolean checkCode(String code);



    void addAccessToken(String token, String value, Long timeout);

    String getValueByAccessToken(String token);

    void removeAccessToken(String token);

    boolean checkAccessToken(String token);



    void addRefreshAccessToken(String token, String value, Long timeout);

    String getValueByRefreshAccessToken(String token);

    boolean checkRefreshAccessToken(String token);

    long getRefreshAcessTokenOfExpire(String token);

    void addSingleSignOnAccessToken(String token, String value, Long timeout);

    String getValueBySingleSignOnAccessToken(String token);

    void removeSingleSignOnAccessToken(String token);

    boolean checkSingleSignOnAccessToken(String token);


    void addRefreshSingleSignOnAccessToken(String token, String value, Long timeout);

    String getValueByRefreshSingleSignOnAccessToken(String token);

    boolean checkRefreshSingleSignOnAccessToken(String token);

    long getRefreshSingleSignOnAcessTokenOfExpire(String token);

    boolean checkClientId(String clientId);

    boolean checkClientSecret(String clientId, String clientSecret);

    String getClientName(String clientId);

}
