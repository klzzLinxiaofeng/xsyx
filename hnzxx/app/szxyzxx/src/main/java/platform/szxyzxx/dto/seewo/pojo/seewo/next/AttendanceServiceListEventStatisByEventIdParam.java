package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【事件|课程】根据事件查询指定日期的考勤统计数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventStatisByEventIdParam extends OpenApiParam {


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

    public static AttendanceServiceListEventStatisByEventIdParamBuilder builder(){
        return new AttendanceServiceListEventStatisByEventIdParamBuilder();
    }

    public static class AttendanceServiceListEventStatisByEventIdParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListEventStatisByEventIdParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListEventStatisByEventIdParam build(){
            AttendanceServiceListEventStatisByEventIdParam param = new AttendanceServiceListEventStatisByEventIdParam();
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
1 -  事件（默认）
2 - 课程
         */
        private Integer attendType;
        /**
         * 事件Id
         */
        private String eventId;

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


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String attendDate;
            private Integer attendType;
            private String eventId;

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

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setAttendDate(attendDate);
                param.setAttendType(attendType);
                param.setEventId(eventId);
                return param;
            }
        }
    }


}
