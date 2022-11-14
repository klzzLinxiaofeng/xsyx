package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 查询学校阶段内的请假记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PersonalLeaveListSchoolPeriodRecordsResult extends OpenApiResult {

    public PersonalLeaveListSchoolPeriodRecordsResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private ResponseBody responseBody;


    public ResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class ResponseBody {
        /**
         * code
         */
        private String code;
        /**
         * message
         */
        private String message;
        /**
         * data
         */
        private Data data;

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

        public Data getData() {
            return this.data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }

    public static class Data {
        /**
         * 分页条数
         */
        private Integer pageSize;
        /**
         * 当前页
         */
        private Integer page;
        /**
         * 总条数
         */
        private Integer totalCount;
        /**
         * result
         */
        private List<Object> result;

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<Object> getResult() {
            return this.result;
        }

        public void setResult(List<Object> result) {
            this.result = result;
        }

    }

    public static class Object {
        /**
         * 请假记录的唯一ID
         */
        private String uid;
        /**
         * 请假人ID
         */
        private String userUid;
        /**
         * 请假人姓名
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
         * 开始时间 yyyy-MM-dd HH:mm:ss
         */
        private String startTime;
        /**
         * 结束时间 yyyy-MM-dd HH:mm:ss
         */
        private String endTime;
        /**
         * 附件列表，最多5个
         */
        private List<String> attachments;
        /**
         * 发请人ID
         */
        private String applyUserUid;
        /**
         * 发请人名字
         */
        private String applyUserName;
        /**
         * 发请人手机号
         */
        private String applyUserPhone;
        /**
         * 发起时间 yyyy-MM-dd HH:mm:ss
         */
        private String createTime;
        /**
         * 请假审核状态：1未审核，2未通过，3已通过，4已撤销
         */
        private Integer leaveStatus;
        /**
         * 请假审核被拒绝理由
         */
        private String refuseReason;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

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

        public String getApplyUserUid() {
            return this.applyUserUid;
        }

        public void setApplyUserUid(String applyUserUid) {
            this.applyUserUid = applyUserUid;
        }

        public String getApplyUserName() {
            return this.applyUserName;
        }

        public void setApplyUserName(String applyUserName) {
            this.applyUserName = applyUserName;
        }

        public String getApplyUserPhone() {
            return this.applyUserPhone;
        }

        public void setApplyUserPhone(String applyUserPhone) {
            this.applyUserPhone = applyUserPhone;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Integer getLeaveStatus() {
            return this.leaveStatus;
        }

        public void setLeaveStatus(Integer leaveStatus) {
            this.leaveStatus = leaveStatus;
        }

        public String getRefuseReason() {
            return this.refuseReason;
        }

        public void setRefuseReason(String refuseReason) {
            this.refuseReason = refuseReason;
        }

    }


}

