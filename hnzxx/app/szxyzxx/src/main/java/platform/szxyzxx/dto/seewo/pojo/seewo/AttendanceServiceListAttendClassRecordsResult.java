package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 获取事件（课程+事件）下班级考勤详细记录 
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AttendanceServiceListAttendClassRecordsResult extends OpenApiResult {

    public AttendanceServiceListAttendClassRecordsResult(HttpResponse response) {
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
         * classUid
         */
        private String classUid;
        /**
         * className
         */
        private String className;
        /**
         * records
         */
        private List<RecordsItem> records;

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

        public List<RecordsItem> getRecords() {
            return this.records;
        }

        public void setRecords(List<RecordsItem> records) {
            this.records = records;
        }

    }

    public static class RecordsItem {
        /**
         * userUid
         */
        private String userUid;
        /**
         * userName
         */
        private String userName;
        /**
         * studentCode
         */
        private String studentCode;
        /**
         * attendTime
         */
        private String attendTime;
        /**
         * status
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

