package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据用户查询指定日期的考勤结果
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
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
        /**
         * appId
         */
        private String appId;
        /**
         * schoolUid
         */
        private String schoolUid;
        /**
         * attendDate
         */
        private String attendDate;
        /**
         * userCode
         */
        private String userCode;
        /**
         * userCodeType
         */
        private Integer userCodeType;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getAttendDate() {
            return this.attendDate;
        }

        public void setAttendDate(String attendDate) {
            this.attendDate = attendDate;
        }

        public String getUserCode() {
            return this.userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public Integer getUserCodeType() {
            return this.userCodeType;
        }

        public void setUserCodeType(Integer userCodeType) {
            this.userCodeType = userCodeType;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private String appId;
            private String schoolUid;
            private String attendDate;
            private String userCode;
            private Integer userCodeType;

            public RequestBodyBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public RequestBodyBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public RequestBodyBuilder attendDate(String attendDate){
                this.attendDate = attendDate;
                return this;
            }
            public RequestBodyBuilder userCode(String userCode){
                this.userCode = userCode;
                return this;
            }
            public RequestBodyBuilder userCodeType(Integer userCodeType){
                this.userCodeType = userCodeType;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setAttendDate(attendDate);
                param.setUserCode(userCode);
                param.setUserCodeType(userCodeType);
                return param;
            }
        }
    }


}
