package com.xunyunedu.seewo.dto;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据用户查询指定日期的考勤结果
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-16
 */
public class AttendanceServiceListEventRecordsByUserDateParam extends OpenApiParam {


    /**
     * 响应体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static AttendanceServiceListEventRecordsByUserDateParamBuilder builder(){
        return new AttendanceServiceListEventRecordsByUserDateParamBuilder();
    }

    public static class AttendanceServiceListEventRecordsByUserDateParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListEventRecordsByUserDateParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListEventRecordsByUserDateParam build(){
            AttendanceServiceListEventRecordsByUserDateParam param = new AttendanceServiceListEventRecordsByUserDateParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {

        public Map<String,Object> query = new HashMap<>();

        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{

            public Map<String,Object> query = new HashMap<>();

            public RequestBodyBuilder appId(String appId){
                query.put("appId",appId);
                return this;
            }
            public RequestBodyBuilder schoolUid(String schoolUid){
                query.put("schoolUid",schoolUid);
                return this;
            }
            public RequestBodyBuilder attendDate(String attendDate){
                query.put("attendDate",attendDate);
                return this;
            }
            public RequestBodyBuilder userCode(String userCode){
                query.put("userCode",userCode);
                return this;
            }
            public RequestBodyBuilder userCodeType(Integer userCodeType){
                query.put("userCodeType",userCodeType);
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.query = query;
                return param;
            }
        }
    }


}
