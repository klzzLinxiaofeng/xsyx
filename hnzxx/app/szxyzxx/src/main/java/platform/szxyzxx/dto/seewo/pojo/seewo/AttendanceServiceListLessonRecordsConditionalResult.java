package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 条件查询课程考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AttendanceServiceListLessonRecordsConditionalResult extends OpenApiResult {

    public AttendanceServiceListLessonRecordsConditionalResult(HttpResponse response) {
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
         * 总记录数
         */
        private Integer totalCount;
        /**
         * 课节信息
         */
        private List<LessonInfosItem> lessonInfos;
        /**
         * records
         */
        private List<RecordsItem> records;
        /**
         * page
         */
        private Integer page;
        /**
         * pageSize
         */
        private Integer pageSize;

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<LessonInfosItem> getLessonInfos() {
            return this.lessonInfos;
        }

        public void setLessonInfos(List<LessonInfosItem> lessonInfos) {
            this.lessonInfos = lessonInfos;
        }

        public List<RecordsItem> getRecords() {
            return this.records;
        }

        public void setRecords(List<RecordsItem> records) {
            this.records = records;
        }

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

    }

    public static class LessonInfosItem {
        /**
         * lessonId
         */
        private String lessonId;
        /**
         * 课程名
         */
        private String name;
        /**
         * 日期yyyy-MM-dd
         */
        private String date;
        /**
         * 课程节次1-N
         */
        private Integer lessonIdx;
        /**
         * 场地标识
         */
        private String roomNum;
        /**
         * 场地名称
         */
        private String roomName;
        /**
         * 授课老师ID
         */
        private String teacherUid;
        /**
         * 授课老师
         */
        private String teacherName;

        public String getLessonId() {
            return this.lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getLessonIdx() {
            return this.lessonIdx;
        }

        public void setLessonIdx(Integer lessonIdx) {
            this.lessonIdx = lessonIdx;
        }

        public String getRoomNum() {
            return this.roomNum;
        }

        public void setRoomNum(String roomNum) {
            this.roomNum = roomNum;
        }

        public String getRoomName() {
            return this.roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getTeacherUid() {
            return this.teacherUid;
        }

        public void setTeacherUid(String teacherUid) {
            this.teacherUid = teacherUid;
        }

        public String getTeacherName() {
            return this.teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

    }

    public static class RecordsItem {
        /**
         * lessonId
         */
        private String lessonId;
        /**
         * 学生ID
         */
        private String userUid;
        /**
         * 学生姓名
         */
        private String userName;
        /**
         * 学号
         */
        private String studentCode;
        /**
         * 签到时间HH:mm:ss
         */
        private String attendTime;
        /**
         * 状态：0:正常 1:迟到 3:缺勤 6请假
         */
        private Integer status;

        public String getLessonId() {
            return this.lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
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

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getAttendTime() {
            return this.attendTime;
        }

        public void setAttendTime(String attendTime) {
            this.attendTime = attendTime;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }


}

