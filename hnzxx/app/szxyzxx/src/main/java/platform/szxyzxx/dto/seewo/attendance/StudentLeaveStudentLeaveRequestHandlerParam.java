package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 学生请假申请与查询api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class StudentLeaveStudentLeaveRequestHandlerParam extends OpenApiParam {


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

    public static StudentLeaveStudentLeaveRequestHandlerParamBuilder builder(){
        return new StudentLeaveStudentLeaveRequestHandlerParamBuilder();
    }

    public static class StudentLeaveStudentLeaveRequestHandlerParamBuilder{
        private JSONRequestBody requestBody;

        public StudentLeaveStudentLeaveRequestHandlerParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentLeaveStudentLeaveRequestHandlerParam build(){
            StudentLeaveStudentLeaveRequestHandlerParam param = new StudentLeaveStudentLeaveRequestHandlerParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 请求参数
         */
        private StudentLeaveQuery dto;

        public StudentLeaveQuery getDto() {
            return this.dto;
        }

        public void setDto(StudentLeaveQuery dto) {
            this.dto = dto;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private StudentLeaveQuery dto;

            public JSONRequestBodyBuilder dto(StudentLeaveQuery dto){
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

    public static class StudentLeaveQuery {
        /**
         * 学校UID
         */
        private String schoolUid;
        /**
         * 学生ID
         */
        private String userId;
        /**
         * 开始日期
         */
        private String startDate;
        /**
         * 结束日期
         */
        private String endDate;
        /**
         * 开始时间
         */
        private String startTime;
        /**
         * 结束时间
         */
        private String endTime;
        /**
         * 请假类型：0事假，1病假
         */
        private Integer type;
        /**
         * 请假原因
         */
        private String reason;
        /**
         * 附件列表：url字符串列表
         */
        private List<String> attachments;
        /**
         * 申请请假applyForLeave；获取学校请假记录列表getSchoolPeriodLeaveRecords
         */
        private String method;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

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

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getReason() {
            return this.reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public List<String> getAttachments() {
            return this.attachments;
        }

        public void setAttachments(List<String> attachments) {
            this.attachments = attachments;
        }

        public String getMethod() {
            return this.method;
        }

        public void setMethod(String method) {
            this.method = method;
        }


        public static StudentLeaveQueryBuilder builder(){
            return new StudentLeaveQueryBuilder();
        }

        public static class StudentLeaveQueryBuilder{
            private String schoolUid;
            private String userId;
            private String startDate;
            private String endDate;
            private String startTime;
            private String endTime;
            private Integer type;
            private String reason;
            private List<String> attachments;
            private String method;

            public StudentLeaveQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public StudentLeaveQueryBuilder userId(String userId){
                this.userId = userId;
                return this;
            }
            public StudentLeaveQueryBuilder startDate(String startDate){
                this.startDate = startDate;
                return this;
            }
            public StudentLeaveQueryBuilder endDate(String endDate){
                this.endDate = endDate;
                return this;
            }
            public StudentLeaveQueryBuilder startTime(String startTime){
                this.startTime = startTime;
                return this;
            }
            public StudentLeaveQueryBuilder endTime(String endTime){
                this.endTime = endTime;
                return this;
            }
            public StudentLeaveQueryBuilder type(Integer type){
                this.type = type;
                return this;
            }
            public StudentLeaveQueryBuilder reason(String reason){
                this.reason = reason;
                return this;
            }
            public StudentLeaveQueryBuilder attachments(List<String> attachments){
                this.attachments = attachments;
                return this;
            }
            public StudentLeaveQueryBuilder method(String method){
                this.method = method;
                return this;
            }

            public StudentLeaveQuery build(){
                StudentLeaveQuery param = new StudentLeaveQuery();
                param.setSchoolUid(schoolUid);
                param.setUserId(userId);
                param.setStartDate(startDate);
                param.setEndDate(endDate);
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                param.setType(type);
                param.setReason(reason);
                param.setAttachments(attachments);
                param.setMethod(method);
                return param;
            }
        }
    }


}
