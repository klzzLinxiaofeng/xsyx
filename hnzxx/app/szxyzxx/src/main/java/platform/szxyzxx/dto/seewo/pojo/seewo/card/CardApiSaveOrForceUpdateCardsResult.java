package platform.szxyzxx.dto.seewo.pojo.seewo.card;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 添加或强制更新卡信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-1-27
 */
public class CardApiSaveOrForceUpdateCardsResult extends OpenApiResult {

    public CardApiSaveOrForceUpdateCardsResult(HttpResponse response) {
        super(response);
    }


    /**
     * 返回
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
        private List<MisThirdCardDto> data;

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

        public List<MisThirdCardDto> getData() {
            return this.data;
        }

        public void setData(List<MisThirdCardDto> data) {
            this.data = data;
        }

    }

    public static class MisThirdCardDto {
        /**
         * 
         */
        private String name;
        /**
         * 
         */
        private String cardId;
        /**
         * 
         */
        private String number;
        /**
         * 异常信息
         */
        private String exception;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCardId() {
            return this.cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getException() {
            return this.exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

    }


}

