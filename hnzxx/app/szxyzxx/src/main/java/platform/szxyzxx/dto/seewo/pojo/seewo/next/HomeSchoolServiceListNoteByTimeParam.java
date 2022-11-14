package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 获取限定时间内学校或用户留言列表，最多返回500条留言
 * 按留言修改时间增量排序，最多返回500条
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class HomeSchoolServiceListNoteByTimeParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static HomeSchoolServiceListNoteByTimeParamBuilder builder(){
        return new HomeSchoolServiceListNoteByTimeParamBuilder();
    }

    public static class HomeSchoolServiceListNoteByTimeParamBuilder{
        private RequestBody requestBody;

        public HomeSchoolServiceListNoteByTimeParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public HomeSchoolServiceListNoteByTimeParam build(){
            HomeSchoolServiceListNoteByTimeParam param = new HomeSchoolServiceListNoteByTimeParam();
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
         * 开始时间，必需，格式为：yyyy-MM-dd HH:mm:ss
         */
        private String startTime;
        /**
         * 结束时间，必需，格式为：yyyy-MM-dd HH:mm:ss
         */
        private String endTime;
        /**
         * 学校uid，必需，最大32位
         */
        private String schoolUid;
        /**
         * 学号，可选，如果传入，则获取对应学生留言列表。如果不传，则获取学校留言列表。如果parentPhone，studentCode都没传，则获取学校留言列表
         */
        private String studentCode;
        /**
         * 家长手机号，可选，如果传人，则获取对应家长留言列表。如果parentPhone，studentCode都没传，则获取学校留言列表
         */
        private String parentPhone;
        /**
         * 应用id，必需
         */
        private String appId;

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
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

        public String getParentPhone() {
            return this.parentPhone;
        }

        public void setParentPhone(String parentPhone) {
            this.parentPhone = parentPhone;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String startTime;
            private String endTime;
            private String schoolUid;
            private String studentCode;
            private String parentPhone;
            private String appId;

            public QueryBuilder startTime(String startTime){
                this.startTime = startTime;
                return this;
            }
            public QueryBuilder endTime(String endTime){
                this.endTime = endTime;
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
            public QueryBuilder parentPhone(String parentPhone){
                this.parentPhone = parentPhone;
                return this;
            }
            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                param.setSchoolUid(schoolUid);
                param.setStudentCode(studentCode);
                param.setParentPhone(parentPhone);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
