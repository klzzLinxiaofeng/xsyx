package com.xunyunedu.seewo.client;

import cn.hutool.json.JSONUtil;
import com.seewo.open.sdk.DefaultSeewoClient;
import com.seewo.open.sdk.OpenApiRequest;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.SeewoClient;
import com.seewo.open.sdk.auth.Account;
import com.xunyunedu.seewo.pojo.SeewoResponseResult;
import com.xunyunedu.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static  String getSchoolUid(){
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


    public static SeewoResponseResult invoke(OpenApiRequest request){
        request.setServerUrl(getServer());
        logger.debug("seewo request:{}",request.toString());
        try {
            logger.debug("request body:{}",new String(request.getBody(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }
        OpenApiResult openApiResult=getClient().invoke(request);
        String responseBody=openApiResult.getBody();

        SeewoResponseResult result= JSONUtil.toBean(responseBody,SeewoResponseResult.class);
        result.setOriginJson(responseBody);
        result.setStatusCode(openApiResult.getStatusCode());
        return result;
    }

    public static SeewoClient getClient(){
        return seewoClient;
    }

}


