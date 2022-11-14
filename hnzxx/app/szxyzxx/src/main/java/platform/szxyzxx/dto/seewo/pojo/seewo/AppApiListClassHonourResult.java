package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 获取学校下的班级荣誉列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AppApiListClassHonourResult extends OpenApiResult {

    public AppApiListClassHonourResult(HttpResponse response) {
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
         * 业务状态码，000000为成功
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
         * 班级名
         */
        private String className;
        /**
         * 班级uid
         */
        private String classUid;
        /**
         * 荣誉结束时间
         */
        private String endTime;
        /**
         * 业务id
         */
        private String itemId;
        /**
         * 荣誉开始时间
         */
        private String startTime;

        public String getClassName() {
            return this.className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getItemId() {
            return this.itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

    }


}

