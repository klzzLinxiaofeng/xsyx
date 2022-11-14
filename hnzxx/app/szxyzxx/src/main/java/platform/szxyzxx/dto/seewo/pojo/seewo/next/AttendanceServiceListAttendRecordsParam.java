package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【事件|课程】根据规则与日期条件分页查询考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListAttendRecordsParam extends OpenApiParam {


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

    public static AttendanceServiceListAttendRecordsParamBuilder builder(){
        return new AttendanceServiceListAttendRecordsParamBuilder();
    }

    public static class AttendanceServiceListAttendRecordsParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListAttendRecordsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListAttendRecordsParam build(){
            AttendanceServiceListAttendRecordsParam param = new AttendanceServiceListAttendRecordsParam();
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
         * 考勤类型
1-事件（默认），2-课程
         */
        private Integer attendType;
        /**
         * 考勤规则ID
         */
        private String eventId;
        /**
         * 班级uid
         */
        private String classUid;
        /**
         * 年级序号
年级序号+班级序号 与 班级uid只取一个，优先取班级uid
         */
        private Integer grade;
        /**
         * 班级序号
年级序号+班级序号 与 班级uid只取一个，优先取班级uid
         */
        private Integer clazz;
        /**
         * 用户类型
student - 学生（默认）
teacher - 老师
老师和班级没有绑定关系，因此查询老师考勤信息时不要携带班级信息
         */
        private String userType;
        /**
         * 页号，默认1
         */
        private Integer page;
        /**
         * 每页大小，默认20
         */
        private Integer pageSize;

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

        public Integer getAttendType() {
            return this.attendType;
        }

        public void setAttendType(Integer attendType) {
            this.attendType = attendType;
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

        public String getUserType() {
            return this.userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String attendDate;
            private Integer attendType;
            private String eventId;
            private String classUid;
            private Integer grade;
            private Integer clazz;
            private String userType;
            private Integer page;
            private Integer pageSize;

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
            public QueryBuilder attendType(Integer attendType){
                this.attendType = attendType;
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
            public QueryBuilder userType(String userType){
                this.userType = userType;
                return this;
            }
            public QueryBuilder page(Integer page){
                this.page = page;
                return this;
            }
            public QueryBuilder pageSize(Integer pageSize){
                this.pageSize = pageSize;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setAttendDate(attendDate);
                param.setAttendType(attendType);
                param.setEventId(eventId);
                param.setClassUid(classUid);
                param.setGrade(grade);
                param.setClazz(clazz);
                param.setUserType(userType);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
