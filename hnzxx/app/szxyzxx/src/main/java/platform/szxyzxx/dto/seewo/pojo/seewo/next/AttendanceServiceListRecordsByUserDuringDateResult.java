package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 【事件|课程】根据用户查询指定日期范围的考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListRecordsByUserDuringDateResult extends OpenApiResult {

    public AttendanceServiceListRecordsByUserDuringDateResult(HttpResponse response) {
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
         * 页号
         */
        private Integer page;
        /**
         * 每页大小
         */
        private Integer pageSize;
        /**
         * 数据总量
         */
        private Integer totalCount;
        /**
         * result
         */
        private List<ResultItem> result;

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

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<ResultItem> getResult() {
            return this.result;
        }

        public void setResult(List<ResultItem> result) {
            this.result = result;
        }

    }

    public static class ResultItem {
        /**
         * 考勤规则id
         */
        private String ruleId;
        /**
         * 考勤名称（课程考勤为课程名称）
         */
        private String name;
        /**
         * 考勤日期
         */
        private String attendDate;
        /**
         * 考勤开始时间
         */
        private String attendStartTime;
        /**
         * 考勤迟到时间
         */
        private String attendLateTime;
        /**
         * 考勤结束时间
         */
        private String attendEndTime;
        /**
         * 考勤结果：0正常，1迟到，3未打卡，4未开始，6请假
         */
        private Integer status;
        /**
         * 签到时间
         */
        private String attendTime;

        public String getRuleId() {
            return this.ruleId;
        }

        public void setRuleId(String ruleId) {
            this.ruleId = ruleId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttendDate() {
            return this.attendDate;
        }

        public void setAttendDate(String attendDate) {
            this.attendDate = attendDate;
        }

        public String getAttendStartTime() {
            return this.attendStartTime;
        }

        public void setAttendStartTime(String attendStartTime) {
            this.attendStartTime = attendStartTime;
        }

        public String getAttendLateTime() {
            return this.attendLateTime;
        }

        public void setAttendLateTime(String attendLateTime) {
            this.attendLateTime = attendLateTime;
        }

        public String getAttendEndTime() {
            return this.attendEndTime;
        }

        public void setAttendEndTime(String attendEndTime) {
            this.attendEndTime = attendEndTime;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getAttendTime() {
            return this.attendTime;
        }

        public void setAttendTime(String attendTime) {
            this.attendTime = attendTime;
        }

    }


}

