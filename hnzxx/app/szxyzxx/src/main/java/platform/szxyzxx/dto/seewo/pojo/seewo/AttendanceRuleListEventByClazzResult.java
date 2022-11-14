package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 查询班级的考勤规则（事件+课程）列表  
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AttendanceRuleListEventByClazzResult extends OpenApiResult {

    public AttendanceRuleListEventByClazzResult(HttpResponse response) {
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
         * classType
         */
        private Integer classType;
        /**
         * events
         */
        private List<EventsItem> events;

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

        public List<EventsItem> getEvents() {
            return this.events;
        }

        public void setEvents(List<EventsItem> events) {
            this.events = events;
        }

    }

    public static class EventsItem {
        /**
         * ruleId
         */
        private String ruleId;
        /**
         * name
         */
        private String name;
        /**
         * attendType
         */
        private Integer attendType;
        /**
         * attendStartTime
         */
        private String attendStartTime;
        /**
         * attendLateTime
         */
        private String attendLateTime;
        /**
         * attendEndTime
         */
        private String attendEndTime;

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

    }


}

