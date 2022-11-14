package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: （已废弃）获取事件下班级考勤详细记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventClassRecordsParam extends OpenApiParam {


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

    public static AttendanceServiceListEventClassRecordsParamBuilder builder(){
        return new AttendanceServiceListEventClassRecordsParamBuilder();
    }

    public static class AttendanceServiceListEventClassRecordsParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListEventClassRecordsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListEventClassRecordsParam build(){
            AttendanceServiceListEventClassRecordsParam param = new AttendanceServiceListEventClassRecordsParam();
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
         */
        private String attendDate;
        /**
         * 考勤事件ID
         */
        private String eventId;
        /**
         * 班级uid
         */
        private String classUid;
        /**
         * 年级序号：
1-12 小学1年级~高三年级，30 幼儿园。 99 其他自定义
与classUid二选一，如果传了classUid，则grade和clazz不需要传，否则grade和clazz必传
如果都传了，以classUid为准
         */
        private Integer grade;
        /**
         * 班级序号，与classUid二选一
如果传了classUid，则grade和clazz不需要传，否则grade和clazz必传
如果都传了，以classUid为准
         */
        private Integer clazz;

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

        public String getEventId() {
            return this.eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String attendDate;
            private String eventId;
            private String classUid;
            private Integer grade;
            private Integer clazz;

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
            public QueryBuilder eventId(String eventId){
                this.eventId = eventId;
                return this;
            }
            public QueryBuilder classUid(String classUid){
                this.classUid = classUid;
                return this;
            }
            public QueryBuilder grade(Integer grade){
                this.grade = grade;
                return this;
            }
            public QueryBuilder clazz(Integer clazz){
                this.clazz = clazz;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setAttendDate(attendDate);
                param.setEventId(eventId);
                param.setClassUid(classUid);
                param.setGrade(grade);
                param.setClazz(clazz);
                return param;
            }
        }
    }


}
