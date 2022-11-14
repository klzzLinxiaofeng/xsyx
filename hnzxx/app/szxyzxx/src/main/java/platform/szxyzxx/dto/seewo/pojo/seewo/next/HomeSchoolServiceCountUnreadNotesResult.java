package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 获取孩子与家长的当前未读消息数
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class HomeSchoolServiceCountUnreadNotesResult extends OpenApiResult {

    public HomeSchoolServiceCountUnreadNotesResult(HttpResponse response) {
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
         * 响应码
         */
        private String code;
        /**
         * 响应消息
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
         * 学校ID
         */
        private String schoolUid;
        /**
         * 孩子未读数
         */
        private Integer childUnreadNum;
        /**
         * 家长未读数
         */
        private Integer parentUnreadNum;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public Integer getChildUnreadNum() {
            return this.childUnreadNum;
        }

        public void setChildUnreadNum(Integer childUnreadNum) {
            this.childUnreadNum = childUnreadNum;
        }

        public Integer getParentUnreadNum() {
            return this.parentUnreadNum;
        }

        public void setParentUnreadNum(Integer parentUnreadNum) {
            this.parentUnreadNum = parentUnreadNum;
        }

    }


}

