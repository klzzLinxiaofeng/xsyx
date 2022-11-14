package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 考勤数据上报
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class RecordsReportCommonAttendDataResult extends OpenApiResult {

    public RecordsReportCommonAttendDataResult(HttpResponse response) {
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
         * 响应码 000000为成功，其他为失败
         */
        private String code;
        /**
         * message
         */
        private String message;
        /**
         * 若失败则返回失败的数据
         */
        private List<DataItem> data;

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

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }

    }

    public static class DataItem {
        /**
         * 处理失败原因
         */
        private String failReason;
        /**
         * signTimestamp
         */
        private Integer signTimestamp;
        /**
         * userCode
         */
        private String userCode;
        /**
         * roomNum
         */
        private String roomNum;

        public String getFailReason() {
            return this.failReason;
        }

        public void setFailReason(String failReason) {
            this.failReason = failReason;
        }

        public Integer getSignTimestamp() {
            return this.signTimestamp;
        }

        public void setSignTimestamp(Integer signTimestamp) {
            this.signTimestamp = signTimestamp;
        }

        public String getUserCode() {
            return this.userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getRoomNum() {
            return this.roomNum;
        }

        public void setRoomNum(String roomNum) {
            this.roomNum = roomNum;
        }

    }


}

