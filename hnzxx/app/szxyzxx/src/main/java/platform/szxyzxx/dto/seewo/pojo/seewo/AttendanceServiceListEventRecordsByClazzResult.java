package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据班级查询指定日期考勤结果
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AttendanceServiceListEventRecordsByClazzResult extends OpenApiResult {

    public AttendanceServiceListEventRecordsByClazzResult(HttpResponse response) {
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
        private Object data;

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

        public Object getData() {
            return this.data;
        }

        public void setData(Object data) {
            this.data = data;
        }

    }

    public static class Object {
        /**
         * 班级uid
         */
        private String classUid;
        /**
         * 班级名称
         */
        private String className;
        /**
         * 班级类型
         */
        private Integer classType;
        /**
         * 考勤汇总信息
         */
        private Array attendStatistic;

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getClassName() {
            return this.className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Integer getClassType() {
            return this.classType;
        }

        public void setClassType(Integer classType) {
            this.classType = classType;
        }

        public Array getAttendStatistic() {
            return this.attendStatistic;
        }

        public void setAttendStatistic(Array attendStatistic) {
            this.attendStatistic = attendStatistic;
        }

    }

    public static class Array {
        /**
         * 考勤规则id
         */
        private String ruleId;
        /**
         * 考勤名称
         */
        private String name;
        /**
         * 考勤类型 1 - 事件考勤  2 - 课程考勤
         */
        private Integer attendType;
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

