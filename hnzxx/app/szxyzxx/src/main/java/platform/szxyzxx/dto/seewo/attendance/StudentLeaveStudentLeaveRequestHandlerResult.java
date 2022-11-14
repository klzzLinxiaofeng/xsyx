package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 学生请假申请与查询api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class StudentLeaveStudentLeaveRequestHandlerResult extends OpenApiResult {

    public StudentLeaveStudentLeaveRequestHandlerResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private JSONResponseBody responseBody;


    public JSONResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(JSONResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class JSONResponseBody {
        /**
         * 返回码
         */
        private String code;
        /**
         * 错误信息
         */
        private String message;
        /**
         * 业务数据
         */
        private List<StudentLeaveRecordResult> data;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<StudentLeaveRecordResult> getData() {
            return this.data;
        }

        public void setData(List<StudentLeaveRecordResult> data) {
            this.data = data;
        }

    }

    public static class StudentLeaveRecordResult {
        /**
         * 用户ID
         */
        private String userUid;
        /**
         * 学生姓名
         */
        private String userName;
        /**
         * 请假类型：0事假，1病假
         */
        private Integer type;
        /**
         * 请假原因
         */
        private String reason;
        /**
         * 开始时间
         */
        private String startTime;
        /**
         * 结束时间
         */
        private String endTime;
        /**
         * 附件列表
         */
        private List<String> attachments;
        /**
         * 请假申请时间
         */
        private String createTime;

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public List<String> getAttachments() {
            return this.attachments;
        }

        public void setAttachments(List<String> attachments) {
            this.attachments = attachments;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

    }


}

