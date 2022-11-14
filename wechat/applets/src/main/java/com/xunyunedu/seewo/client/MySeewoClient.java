package com.xunyunedu.seewo.client;

import com.alibaba.fastjson.JSON;
import com.seewo.open.sdk.*;
import com.seewo.open.sdk.auth.Account;
import com.xunyunedu.seewo.dto.AttendanceServiceListEventRecordsByUserDateParam;
import com.xunyunedu.seewo.dto.AttendanceServiceListEventRecordsByUserDateRequest;
import com.xunyunedu.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author edison
 */
public class MySeewoClient {

    public static final Logger logger = LoggerFactory.getLogger(MySeewoClient.class);

    public static String SEEWO_PROPERTY_NAME = "seewo.properties";

    public static final String SCHOOL_CODE = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolCode");

    public static final String SCHOOL_UID= PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolUid");

    public static final String APPID = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.test.appid");

    public static final String SECRET = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.test.secret");

    private final static SeewoClient client;

    static {

        Account account = new Account(APPID,SECRET);
        client = new DefaultSeewoClient(account);
    }




    OpenApiRequest request;


    private MySeewoClient(){

    }
    public static <T extends OpenApiRequest> MySeewoClient request(Class<T> c){

        MySeewoClient client = new MySeewoClient();

        try {
            Constructor<T> constructor = c.getConstructor();
            client.request = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        client.request.setServerUrl("https://openapi.test.seewo.com");
        return client;
    }

    public <P extends OpenApiParam>MySeewoClient param(P p){

        request.setBizModel(p);
        logger.info("seewo request:{}",JSON.toJSONString(request,true));
        return this;
    }


    public <R extends OpenApiResult> R invoke(){
        R result = (R) client.invoke(request);
        logger.info("seewo result:{}", JSON.toJSONString(result,true));
        return result;
    }


    public static void main(String[] args) {

        AttendanceServiceListEventRecordsByUserDateParam param = new AttendanceServiceListEventRecordsByUserDateParam();
        //响应体，MimeType为 application/json
        AttendanceServiceListEventRecordsByUserDateParam.RequestBody requestBody = AttendanceServiceListEventRecordsByUserDateParam.RequestBody.builder()
                .appId(MySeewoClient.APPID)
                .schoolUid(MySeewoClient.SCHOOL_UID)
                .attendDate("2020-08-29")
                .userCode("111")
                .userCodeType(2)
                .build();

        param.setRequestBody(requestBody);

        MySeewoClient.request(AttendanceServiceListEventRecordsByUserDateRequest.class)
                .param(param).invoke();

    }




}
