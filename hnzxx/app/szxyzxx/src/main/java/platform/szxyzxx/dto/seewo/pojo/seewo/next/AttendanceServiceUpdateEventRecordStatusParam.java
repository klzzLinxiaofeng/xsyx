package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【事件考勤】更新用户考勤状态
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceUpdateEventRecordStatusParam extends OpenApiParam {


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

    public static AttendanceServiceUpdateEventRecordStatusParamBuilder builder(){
        return new AttendanceServiceUpdateEventRecordStatusParamBuilder();
    }

    public static class AttendanceServiceUpdateEventRecordStatusParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceUpdateEventRecordStatusParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceUpdateEventRecordStatusParam build(){
            AttendanceServiceUpdateEventRecordStatusParam param = new AttendanceServiceUpdateEventRecordStatusParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private Query query;

        public Query getQuery() {
            return this.query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Query query;

            public RequestBodyBuilder query(Query query){
                this.query = query;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class Query {
        /**
         * appId
         */
        private String appId;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 考勤日期
格式: yyyy-MM-dd
         */
        private String date;
        /**
         * 考勤规则id
         */
        private String ruleId;
        /**
         * 
         */
        private List<UserStatusItem> userStatus;

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

        public String getDate() {
            return this.date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getRuleId() {
            return this.ruleId;
        }

        public void setRuleId(String ruleId) {
            this.ruleId = ruleId;
        }

        public List<UserStatusItem> getUserStatus() {
            return this.userStatus;
        }

        public void setUserStatus(List<UserStatusItem> userStatus) {
            this.userStatus = userStatus;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String date;
            private String ruleId;
            private List<UserStatusItem> userStatus;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder date(String date){
                this.date = date;
                return this;
            }
            public QueryBuilder ruleId(String ruleId){
                this.ruleId = ruleId;
                return this;
            }
            public QueryBuilder userStatus(List<UserStatusItem> userStatus){
                this.userStatus = userStatus;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setDate(date);
                param.setRuleId(ruleId);
                param.setUserStatus(userStatus);
                return param;
            }
        }
    }

    public static class UserStatusItem {
        /**
         * 用户标识
         */
        private String userCode;
        /**
         * 用户标识类型（默认3）
0 - 10进制卡号，1 - 16进制反码卡号，2 - 学号，3 - 用户UID，4 - 第三方映射ID
         */
        private Integer userCodeType;
        /**
         * 用户考勤状态
0正常，1迟到，3未打卡，6请假
         */
        private Integer status;

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

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }


        public static UserStatusItemBuilder builder(){
            return new UserStatusItemBuilder();
        }

        public static class UserStatusItemBuilder{
            private String userCode;
            private Integer userCodeType;
            private Integer status;

            public UserStatusItemBuilder userCode(String userCode){
                this.userCode = userCode;
                return this;
            }
            public UserStatusItemBuilder userCodeType(Integer userCodeType){
                this.userCodeType = userCodeType;
                return this;
            }
            public UserStatusItemBuilder status(Integer status){
                this.status = status;
                return this;
            }

            public UserStatusItem build(){
                UserStatusItem param = new UserStatusItem();
                param.setUserCode(userCode);
                param.setUserCodeType(userCodeType);
                param.setStatus(status);
                return param;
            }
        }
    }


}
