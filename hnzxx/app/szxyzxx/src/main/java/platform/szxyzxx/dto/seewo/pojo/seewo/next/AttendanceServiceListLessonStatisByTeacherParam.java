package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【课程考勤】根据老师查询指定日期的课程考勤统计数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListLessonStatisByTeacherParam extends OpenApiParam {


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

    public static AttendanceServiceListLessonStatisByTeacherParamBuilder builder(){
        return new AttendanceServiceListLessonStatisByTeacherParamBuilder();
    }

    public static class AttendanceServiceListLessonStatisByTeacherParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListLessonStatisByTeacherParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListLessonStatisByTeacherParam build(){
            AttendanceServiceListLessonStatisByTeacherParam param = new AttendanceServiceListLessonStatisByTeacherParam();
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


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String attendDate;
            private String userCode;
            private Integer userCodeType;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder attendDate(String attendDate){
                this.attendDate = attendDate;
                return this;
            }
            public QueryBuilder userCode(String userCode){
                this.userCode = userCode;
                return this;
            }
            public QueryBuilder userCodeType(Integer userCodeType){
                this.userCodeType = userCodeType;
                return this;
            }

            public Query build(){
                Query param = new Query();
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
