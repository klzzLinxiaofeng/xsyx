package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 【事件|课程】根据事件查询指定日期的考勤统计数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventStatisByEventIdResult extends OpenApiResult {

    public AttendanceServiceListEventStatisByEventIdResult(HttpResponse response) {
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
         * attendStatistic
         */
        private AttendStatistic attendStatistic;
        /**
         * 考勤类型 1- 事件 2 - 课程
         */
        private Integer attendType;
        /**
         * 考勤事假id
         */
        private String eventId;
        /**
         * 考勤名称
         */
        private String eventName;

        public AttendStatistic getAttendStatistic() {
            return this.attendStatistic;
        }

        public void setAttendStatistic(AttendStatistic attendStatistic) {
            this.attendStatistic = attendStatistic;
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

        public String getEventName() {
            return this.eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

    }

    public static class AttendStatistic {
        /**
         * 考勤规则id
         */
        private String ruleId;
        /**
         * 考勤名称
         */
        private String name;
        /**
         * 考勤类型  1 - 事件  2 - 课程
         */
        private Integer attendType;
        /**
         * 考勤日期
         */
        private String attendDate;
        /**
         * 烤漆结束时间
         */
        private String attendEndTime;
        /**
         * 考勤迟到时间
         */
        private String attendLateTime;
        /**
         * 考勤开始时间
         */
        private String attendStartTime;
        /**
         * 考勤总人数
         */
        private Integer totalNum;
        /**
         * 考勤准时人数
         */
        private Integer presentNum;
        /**
         * 考勤迟到人数
         */
        private Integer lateNum;
        /**
         * 考勤请假人数
         */
        private Integer leaveNum;
        /**
         * 考勤缺卡人数
         */
        private Integer absenceNum;

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

        public Integer getAttendType() {
            return this.attendType;
        }

        public void setAttendType(Integer attendType) {
            this.attendType = attendType;
        }

        public String getAttendDate() {
            return this.attendDate;
        }

        public void setAttendDate(String attendDate) {
            this.attendDate = attendDate;
        }

        public String getAttendEndTime() {
            return this.attendEndTime;
        }

        public void setAttendEndTime(String attendEndTime) {
            this.attendEndTime = attendEndTime;
        }

        public String getAttendLateTime() {
            return this.attendLateTime;
        }

        public void setAttendLateTime(String attendLateTime) {
            this.attendLateTime = attendLateTime;
        }

        public String getAttendStartTime() {
            return this.attendStartTime;
        }

        public void setAttendStartTime(String attendStartTime) {
            this.attendStartTime = attendStartTime;
        }

        public Integer getTotalNum() {
            return this.totalNum;
        }

        public void setTotalNum(Integer totalNum) {
            this.totalNum = totalNum;
        }

        public Integer getPresentNum() {
            return this.presentNum;
        }

        public void setPresentNum(Integer presentNum) {
            this.presentNum = presentNum;
        }

        public Integer getLateNum() {
            return this.lateNum;
        }

        public void setLateNum(Integer lateNum) {
            this.lateNum = lateNum;
        }

        public Integer getLeaveNum() {
            return this.leaveNum;
        }

        public void setLeaveNum(Integer leaveNum) {
            this.leaveNum = leaveNum;
        }

        public Integer getAbsenceNum() {
            return this.absenceNum;
        }

        public void setAbsenceNum(Integer absenceNum) {
            this.absenceNum = absenceNum;
        }

    }


}

