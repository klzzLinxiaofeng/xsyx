package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 发送家长到校留言给家长指定学校所有孩子
 * 留言信息：XX学生， 你的家长已到校
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ThirdKidnoteServiceSendNoteToChildrenResult extends OpenApiResult {

    public ThirdKidnoteServiceSendNoteToChildrenResult(HttpResponse response) {
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
         * 操作状态码，200为成功
         */
        private Integer statusCode;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatusCode() {
            return this.statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

    }


}

