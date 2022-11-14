package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 批量修改卡状态（启用/停用）
 * 根据学校uid查询班级详细信息
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiUpdateCardsStatusResult extends OpenApiResult {

    public CardApiUpdateCardsStatusResult(HttpResponse response) {
        super(response);
    }


    /**
     * 
     */
    
    private Object responseBody;


    public Object getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public static class Object {
        /**
         * 状态码
         */
        private String code;
        /**
         * 消息
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

