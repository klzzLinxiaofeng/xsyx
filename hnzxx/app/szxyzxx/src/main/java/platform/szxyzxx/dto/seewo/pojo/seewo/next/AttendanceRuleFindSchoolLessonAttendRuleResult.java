package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 【课程考勤】查询学校课程考勤规则
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceRuleFindSchoolLessonAttendRuleResult extends OpenApiResult {

    public AttendanceRuleFindSchoolLessonAttendRuleResult(HttpResponse response) {
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
         * 上课前N分钟开始考勤
         */
        private Integer beforeStartMinute;
        /**
         * 上课后N分钟结束考勤
         */
        private Integer afterStartMinute;

        public Integer getBeforeStartMinute() {
            return this.beforeStartMinute;
        }

        public void setBeforeStartMinute(Integer beforeStartMinute) {
            this.beforeStartMinute = beforeStartMinute;
        }

        public Integer getAfterStartMinute() {
            return this.afterStartMinute;
        }

        public void setAfterStartMinute(Integer afterStartMinute) {
            this.afterStartMinute = afterStartMinute;
        }

    }


}

