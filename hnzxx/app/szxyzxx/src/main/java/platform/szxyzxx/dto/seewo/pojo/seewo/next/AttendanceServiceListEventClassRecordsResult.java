package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: （已废弃）获取事件下班级考勤详细记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListEventClassRecordsResult extends OpenApiResult {

    public AttendanceServiceListEventClassRecordsResult(HttpResponse response) {
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
         * 响应码，成功 000000
         */
        private String code;
        /**
         * 响应消息，成功 success
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
         * 考勤记录
         */
        private Object records;

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

        public Object getRecords() {
            return this.records;
        }

        public void setRecords(Object records) {
            this.records = records;
        }

    }

}

