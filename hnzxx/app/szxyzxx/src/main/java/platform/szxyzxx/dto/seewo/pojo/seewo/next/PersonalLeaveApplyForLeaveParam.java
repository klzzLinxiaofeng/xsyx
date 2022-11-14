package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 申请请假
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PersonalLeaveApplyForLeaveParam extends OpenApiParam {


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

    public static PersonalLeaveApplyForLeaveParamBuilder builder(){
        return new PersonalLeaveApplyForLeaveParamBuilder();
    }

    public static class PersonalLeaveApplyForLeaveParamBuilder{
        private RequestBody requestBody;

        public PersonalLeaveApplyForLeaveParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PersonalLeaveApplyForLeaveParam build(){
            PersonalLeaveApplyForLeaveParam param = new PersonalLeaveApplyForLeaveParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 
         */
        private Object query;

        public Object getQuery() {
            return this.query;
        }

        public void setQuery(Object query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Object query;

            public RequestBodyBuilder query(Object query){
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

    public static class Object {
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 请假人ID
         */
        private String userUid;
        /**
         * 开始时间 yyyy-MM-dd HH:mm
         */
        private String startTime;
        /**
         * 结束时间 yyyy-MM-dd HH:mm
         */
        private String endTime;
        /**
         * 请假类型 0事假，1病假
         */
        private Integer type;
        /**
         * 请假原因，非必填
         */
        private String reason;
        /**
         * 附件链接，最多5个，非必填
         */
        private List<String> attachments;
        /**
         * 发起人ID
         */
        private String applyUserUid;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
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

        public String getApplyUserUid() {
            return this.applyUserUid;
        }

        public void setApplyUserUid(String applyUserUid) {
            this.applyUserUid = applyUserUid;
        }


        public static ObjectBuilder builder(){
            return new ObjectBuilder();
        }

        public static class ObjectBuilder{
            private String schoolUid;
            private String userUid;
            private String startTime;
            private String endTime;
            private Integer type;
            private String reason;
            private List<String> attachments;
            private String applyUserUid;

            public ObjectBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public ObjectBuilder userUid(String userUid){
                this.userUid = userUid;
                return this;
            }
            public ObjectBuilder startTime(String startTime){
                this.startTime = startTime;
                return this;
            }
            public ObjectBuilder endTime(String endTime){
                this.endTime = endTime;
                return this;
            }
            public ObjectBuilder type(Integer type){
                this.type = type;
                return this;
            }
            public ObjectBuilder reason(String reason){
                this.reason = reason;
                return this;
            }
            public ObjectBuilder attachments(List<String> attachments){
                this.attachments = attachments;
                return this;
            }
            public ObjectBuilder applyUserUid(String applyUserUid){
                this.applyUserUid = applyUserUid;
                return this;
            }

            public Object build(){
                Object param = new Object();
                param.setSchoolUid(schoolUid);
                param.setUserUid(userUid);
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                param.setType(type);
                param.setReason(reason);
                param.setAttachments(attachments);
                param.setApplyUserUid(applyUserUid);
                return param;
            }
        }
    }


}
