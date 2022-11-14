package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 批量删除班级
 * 批量删除班级
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassApiDeleteClassesResult extends OpenApiResult {

    public ClassApiDeleteClassesResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private JSONResponseBody responseBody;


    public JSONResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(JSONResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class JSONResponseBody {
        /**
         * 
         */
        private String code;
        /**
         * 
         */
        private String message;
        /**
         * 
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

    }


}

