package platform.education.netdisk.entity;

import com.alibaba.fastjson.JSON;
import platform.education.constant.LoginConstant;
import platform.education.util.JsEncryptionKey;

import java.io.Serializable;

public class LoginEntity implements Serializable {

    private String appKey;

    private String secret;

    private Integer userId;

    private String validation;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean valid(){
        if(LoginConstant.appKey.equals(this.appKey) && LoginConstant.secret.equals(this.secret)){
            return true;
        }

        return false;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }
}
