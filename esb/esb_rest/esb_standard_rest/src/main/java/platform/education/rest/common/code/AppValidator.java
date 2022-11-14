package platform.education.rest.common.code;

public interface AppValidator {

    public boolean validateAppKey(String appKey);
    public boolean validateSercet(String appKey, String sercet);
    public String getSercet(String appKey);

}
