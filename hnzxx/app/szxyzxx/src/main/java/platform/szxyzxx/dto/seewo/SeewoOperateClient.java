package platform.szxyzxx.dto.seewo;

import com.seewo.open.sdk.DefaultSeewoClient;
import com.seewo.open.sdk.OpenApiRequest;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.SeewoClient;
import com.seewo.open.sdk.auth.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;
import platform.szxyzxx.web.common.util.JSONUtil;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.io.UnsupportedEncodingException;

public class SeewoOperateClient {

    private static final Logger logger = LoggerFactory.getLogger(SeewoOperateClient.class);

    private static final String SEEWO_PROPERTY_NAME = "seewo.properties";

    private static final String SCHOOL_CODE = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolCode");

    private static final String SCHOOL_UID= PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolUid");

    private static final String APP_ID = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.appid");

    private static final String SECRET = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.secret");

    private static final String SERVER = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.server");

    private static final SeewoClient seewoClient;
    static{
        seewoClient=new DefaultSeewoClient(new Account(APP_ID, SECRET));
    }

    public static   String getSchoolUid(){
        return SCHOOL_UID;
    }
    public static  String getAppId(){
        return APP_ID;
    }

    public static String getSchoolCode() {
        return SCHOOL_CODE;
    }

    public static String getSecret() {
        return SECRET;
    }

    public static String getServer() {
        return SERVER;
    }


    public static BasicResponseResult invoke(OpenApiRequest request){
        request.setServerUrl(getServer());
        logger.trace("seewo request:{}",request.toString());
        OpenApiResult openApiResult=getClient().invoke(request);
        logger.trace("seewo response:{}",openApiResult.toString());
        String responseBody=openApiResult.getBody();
        BasicResponseResult result= JSONUtil.parseJson(responseBody, BasicResponseResult.class);
        if(result.getCode()!=null && !result.getCode().equals("000000")){
            try {
                logger.error("希沃请求处理失败，请求URL:"+request.getServerUrl()+request.getPath()+",请求体[{}]，响应体：[{}]",new String(request.getBody(),"UTF-8"),responseBody);
            } catch (UnsupportedEncodingException e) {
               logger.error("希沃请求体编码有误");
            }
        }
        result.setOriginJson(responseBody);
        result.setStatusCode(openApiResult.getStatusCode());
        return result;
    }

    public static SeewoClient getClient(){
        return seewoClient;
    }

}


