package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 发送家长到校留言给家长指定学校所有孩子
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceSendNoteToChildrenResult extends OpenApiResult {

    public HomeSchoolServiceSendNoteToChildrenResult(HttpResponse response) {
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
         * 操作结果信息
         */
        private String message;
        /**
         * 操作状态码，000000成功
         */
        private String code;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }


}

