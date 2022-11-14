package platform.szxyzxx.dto.seewo.pojo;

import com.seewo.open.sdk.HttpResponse;
import com.seewo.open.sdk.OpenApiResult;

/**
 * seewo-open API: 保存场地课表（新）
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class TimetableServiceSaveRoomTimetableResult extends OpenApiResult {

    public TimetableServiceSaveRoomTimetableResult(HttpResponse response) {
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

    }


}

