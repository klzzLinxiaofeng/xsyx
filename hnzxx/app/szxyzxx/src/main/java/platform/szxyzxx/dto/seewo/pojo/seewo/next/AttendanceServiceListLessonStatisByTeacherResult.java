package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 【课程考勤】根据老师查询指定日期的课程考勤统计数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListLessonStatisByTeacherResult extends OpenApiResult {

    public AttendanceServiceListLessonStatisByTeacherResult(HttpResponse response) {
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
         * data
         */
        private List<DataItem> data;
        /**
         * message
         */
        private String message;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static class DataItem {
        /**
         * attendStatistic
         */
        private AttendStatistic attendStatistic;
        /**
         * attendType
         */
        private Integer attendType;
        /**
         * courseType
         */
        private Integer courseType;
        /**
         * eventId
         */
        private String eventId;
        /**
         * eventName
         */
        private String eventName;
        /**
         * lessonIdx
         */
        private Integer lessonIdx;
        /**
         * roomUid
         */
        private String roomUid;
        /**
         * teacherName
         */
        private String teacherName;
        /**
         * teacherUid
         */
        private String teacherUid;

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

        public Integer getCourseType() {
            return this.courseType;
        }

        public void setCourseType(Integer courseType) {
            this.courseType = courseType;
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

        public Integer getLessonIdx() {
            return this.lessonIdx;
        }

        public void setLessonIdx(Integer lessonIdx) {
            this.lessonIdx = lessonIdx;
        }

        public String getRoomUid() {
            return this.roomUid;
        }

        public void setRoomUid(String roomUid) {
            this.roomUid = roomUid;
        }

        public String getTeacherName() {
            return this.teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherUid() {
            return this.teacherUid;
        }

        public void setTeacherUid(String teacherUid) {
            this.teacherUid = teacherUid;
        }

    }

    public static class AttendStatistic {
        /**
         * name
         */
        private String name;
        /**
         * attendDate
         */
        private String attendDate;
        /**
         * attendEndTime
         */
        private String attendEndTime;
        /**
         * attendLateTime
         */
        private String attendLateTime;
        /**
         * attendStartTime
         */
        private String attendStartTime;
        /**
         * attendType
         */
        private Integer attendType;
        /**
         * lateNum
         */
        private Integer lateNum;
        /**
         * leaveNum
         */
        private Integer leaveNum;
        /**
         * presentNum
         */
        private Integer presentNum;
        /**
         * bsenceNum
         */
        private Integer absenceNum;
        /**
         * ruleId
         */
        private String ruleId;
        /**
         * totalNum
         */
        private Integer totalNum;

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

        public Integer getAttendType() {
            return this.attendType;
        }

        public void setAttendType(Integer attendType) {
            this.attendType = attendType;
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

        public Integer getPresentNum() {
            return this.presentNum;
        }

        public void setPresentNum(Integer presentNum) {
            this.presentNum = presentNum;
        }

        public Integer getAbsenceNum() {
            return this.absenceNum;
        }

        public void setAbsenceNum(Integer absenceNum) {
            this.absenceNum = absenceNum;
        }

        public String getRuleId() {
            return this.ruleId;
        }

        public void setRuleId(String ruleId) {
            this.ruleId = ruleId;
        }

        public Integer getTotalNum() {
            return this.totalNum;
        }

        public void setTotalNum(Integer totalNum) {
            this.totalNum = totalNum;
        }

    }


}

