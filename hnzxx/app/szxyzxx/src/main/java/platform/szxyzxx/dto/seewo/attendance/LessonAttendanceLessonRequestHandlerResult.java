package platform.szxyzxx.dto.seewo.attendance;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 课程考勤api
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class LessonAttendanceLessonRequestHandlerResult extends OpenApiResult {

    public LessonAttendanceLessonRequestHandlerResult(HttpResponse response) {
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
         * 返回信息
         */
        private String message;
        /**
         * 业务数据列表
         */
        private List<AttendanceLessonRecordResult> data;

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

        public List<AttendanceLessonRecordResult> getData() {
            return this.data;
        }

        public void setData(List<AttendanceLessonRecordResult> data) {
            this.data = data;
        }

    }

    public static class AttendanceLessonRecordResult {
        /**
         * 课节事件ID
         */
        private String eventId;
        /**
         * 课程名称
         */
        private String courseName;
        /**
         * 课程ID
         */
        private String courseId;
        /**
         * 课节ID
         */
        private String lessonId;
        /**
         * 考勤开始时间HH:mm:ss
         */
        private String attendanceStartTime;
        /**
         * 上课时间HH:mm:ss
         */
        private String startTime;
        /**
         * 考勤结束时间HH:mm:ss
         */
        private String attendanceEndTime;
        /**
         * 下课时间HH:mm:ss
         */
        private String endTime;
        /**
         * 考勤日期yyyy-MM-dd
         */
        private String attendanceDate;
        /**
         * 考勤记录列表
         */
        private List<AttendanceRecordDto> attendanceRecords;

        public String getEventId() {
            return this.eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getCourseName() {
            return this.courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseId() {
            return this.courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getLessonId() {
            return this.lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getAttendanceStartTime() {
            return this.attendanceStartTime;
        }

        public void setAttendanceStartTime(String attendanceStartTime) {
            this.attendanceStartTime = attendanceStartTime;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getAttendanceEndTime() {
            return this.attendanceEndTime;
        }

        public void setAttendanceEndTime(String attendanceEndTime) {
            this.attendanceEndTime = attendanceEndTime;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getAttendanceDate() {
            return this.attendanceDate;
        }

        public void setAttendanceDate(String attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public List<AttendanceRecordDto> getAttendanceRecords() {
            return this.attendanceRecords;
        }

        public void setAttendanceRecords(List<AttendanceRecordDto> attendanceRecords) {
            this.attendanceRecords = attendanceRecords;
        }

    }

    public static class AttendanceRecordDto {
        /**
         * 用户ID
         */
        private String userUid;
        /**
         * 姓名
         */
        private String userName;
        /**
         * 签到打卡时间HH:mm:ss
         */
        private String signTime;
        /**
         * 考勤结果状态：0正常，1迟到，2早退，3未打卡，4未开始，5无需考勤，6请假
         */
        private Integer status;

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

        public String getSignTime() {
            return this.signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }


}

