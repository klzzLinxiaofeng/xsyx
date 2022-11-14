package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据发送时间限量获取留言列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceListNotesLimitedParam extends OpenApiParam {


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

    public static HomeSchoolServiceListNotesLimitedParamBuilder builder(){
        return new HomeSchoolServiceListNotesLimitedParamBuilder();
    }

    public static class HomeSchoolServiceListNotesLimitedParamBuilder{
        private RequestBody requestBody;

        public HomeSchoolServiceListNotesLimitedParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public HomeSchoolServiceListNotesLimitedParam build(){
            HomeSchoolServiceListNotesLimitedParam param = new HomeSchoolServiceListNotesLimitedParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 查询条件
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
         * 学校ID
         */
        private String schoolUid;
        /**
         * 学生学号
         */
        private String studentCode;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 起始时间，上一次查询最后一条消息时间，默认当前查询时间，格式yyyy-MM-dd HH:mm:ss
         */
        private String lastSendTime;
        /**
         * 查询条数，默认20
         */
        private Integer size;

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

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLastSendTime() {
            return this.lastSendTime;
        }

        public void setLastSendTime(String lastSendTime) {
            this.lastSendTime = lastSendTime;
        }

        public Integer getSize() {
            return this.size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String studentCode;
            private String phone;
            private String lastSendTime;
            private Integer size;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder studentCode(String studentCode){
                this.studentCode = studentCode;
                return this;
            }
            public QueryBuilder phone(String phone){
                this.phone = phone;
                return this;
            }
            public QueryBuilder lastSendTime(String lastSendTime){
                this.lastSendTime = lastSendTime;
                return this;
            }
            public QueryBuilder size(Integer size){
                this.size = size;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setStudentCode(studentCode);
                param.setPhone(phone);
                param.setLastSendTime(lastSendTime);
                param.setSize(size);
                return param;
            }
        }
    }


}
