package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【事件|课程】根据规则指定日期与班级查询考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListAttendClassRecordsParam extends OpenApiParam {


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

    public static AttendanceServiceListAttendClassRecordsParamBuilder builder(){
        return new AttendanceServiceListAttendClassRecordsParamBuilder();
    }

    public static class AttendanceServiceListAttendClassRecordsParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListAttendClassRecordsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListAttendClassRecordsParam build(){
            AttendanceServiceListAttendClassRecordsParam param = new AttendanceServiceListAttendClassRecordsParam();
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
         * 考勤事件ID
         */
        private String eventId;
        /**
         * 考勤日期
         */
        private String attendDate;
        /**
         * appId
         */
        private String appId;
        /**
         * 年级序号
         */
        private Integer grade;
        /**
         * 班级uid
班级uid与年级序号、班级序号二选一确定具体的班级，如果都填以classUid为准
         */
        private String classUid;
        /**
         * 班级序号
         */
        private Integer clazz;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 考勤类型
1 - 事件考勤（默认）
2 - 课程考勤
         */
        private Integer attendType;

        public String getEventId() {
            return this.eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getAttendDate() {
            return this.attendDate;
        }

        public void setAttendDate(String attendDate) {
            this.attendDate = attendDate;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public Integer getAttendType() {
            return this.attendType;
        }

        public void setAttendType(Integer attendType) {
            this.attendType = attendType;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String eventId;
            private String attendDate;
            private String appId;
            private Integer grade;
            private String classUid;
            private Integer clazz;
            private String schoolUid;
            private Integer attendType;

            public QueryBuilder eventId(String eventId){
                this.eventId = eventId;
                return this;
            }
            public QueryBuilder attendDate(String attendDate){
                this.attendDate = attendDate;
                return this;
            }
            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder grade(Integer grade){
                this.grade = grade;
                return this;
            }
            public QueryBuilder classUid(String classUid){
                this.classUid = classUid;
                return this;
            }
            public QueryBuilder clazz(Integer clazz){
                this.clazz = clazz;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder attendType(Integer attendType){
                this.attendType = attendType;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setEventId(eventId);
                param.setAttendDate(attendDate);
                param.setAppId(appId);
                param.setGrade(grade);
                param.setClassUid(classUid);
                param.setClazz(clazz);
                param.setSchoolUid(schoolUid);
                param.setAttendType(attendType);
                return param;
            }
        }
    }


}
