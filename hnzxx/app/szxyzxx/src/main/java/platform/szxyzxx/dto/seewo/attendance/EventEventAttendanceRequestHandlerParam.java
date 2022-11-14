package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 事件考勤api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class EventEventAttendanceRequestHandlerParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private JSONRequestBody requestBody;


    public JSONRequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(JSONRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static EventEventAttendanceRequestHandlerParamBuilder builder(){
        return new EventEventAttendanceRequestHandlerParamBuilder();
    }

    public static class EventEventAttendanceRequestHandlerParamBuilder{
        private JSONRequestBody requestBody;

        public EventEventAttendanceRequestHandlerParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public EventEventAttendanceRequestHandlerParam build(){
            EventEventAttendanceRequestHandlerParam param = new EventEventAttendanceRequestHandlerParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 查询条件
         */
        private AttendanceEventQuery dto;

        public AttendanceEventQuery getDto() {
            return this.dto;
        }

        public void setDto(AttendanceEventQuery dto) {
            this.dto = dto;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private AttendanceEventQuery dto;

            public JSONRequestBodyBuilder dto(AttendanceEventQuery dto){
                this.dto = dto;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setDto(dto);
                return param;
            }
        }
    }

    public static class AttendanceEventQuery {
        /**
         * 学校UID
         */
        private String schoolUid;
        /**
         * 班级ID
         */
        private String classId;
        /**
         * 事件ID
         */
        private String eventId;
        /**
         * 学生ID
         */
        private String userId;
        /**
         * 状态 0正常，6请假
         */
        private Integer status;
        /**
         * 补打卡日期
         */
        private String attendanceDate;
        /**
         * 获取班级考勤结果：getEventClassRecords；补打卡：changeAttendanceStatus
         */
        private String method;
        /**
         * 获取班级考勤记录查询日期
         */
        private String queryDate;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getClassId() {
            return this.classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getEventId() {
            return this.eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getAttendanceDate() {
            return this.attendanceDate;
        }

        public void setAttendanceDate(String attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public String getMethod() {
            return this.method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getQueryDate() {
            return this.queryDate;
        }

        public void setQueryDate(String queryDate) {
            this.queryDate = queryDate;
        }


        public static AttendanceEventQueryBuilder builder(){
            return new AttendanceEventQueryBuilder();
        }

        public static class AttendanceEventQueryBuilder{
            private String schoolUid;
            private String classId;
            private String eventId;
            private String userId;
            private Integer status;
            private String attendanceDate;
            private String method;
            private String queryDate;

            public AttendanceEventQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public AttendanceEventQueryBuilder classId(String classId){
                this.classId = classId;
                return this;
            }
            public AttendanceEventQueryBuilder eventId(String eventId){
                this.eventId = eventId;
                return this;
            }
            public AttendanceEventQueryBuilder userId(String userId){
                this.userId = userId;
                return this;
            }
            public AttendanceEventQueryBuilder status(Integer status){
                this.status = status;
                return this;
            }
            public AttendanceEventQueryBuilder attendanceDate(String attendanceDate){
                this.attendanceDate = attendanceDate;
                return this;
            }
            public AttendanceEventQueryBuilder method(String method){
                this.method = method;
                return this;
            }
            public AttendanceEventQueryBuilder queryDate(String queryDate){
                this.queryDate = queryDate;
                return this;
            }

            public AttendanceEventQuery build(){
                AttendanceEventQuery param = new AttendanceEventQuery();
                param.setSchoolUid(schoolUid);
                param.setClassId(classId);
                param.setEventId(eventId);
                param.setUserId(userId);
                param.setStatus(status);
                param.setAttendanceDate(attendanceDate);
                param.setMethod(method);
                param.setQueryDate(queryDate);
                return param;
            }
        }
    }


}
