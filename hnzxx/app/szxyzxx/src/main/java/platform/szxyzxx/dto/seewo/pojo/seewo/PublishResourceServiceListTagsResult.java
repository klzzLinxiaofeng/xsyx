package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 查询学校资源栏目
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PublishResourceServiceListTagsResult extends OpenApiResult {

    public PublishResourceServiceListTagsResult(HttpResponse response) {
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
         * tagId
         */
        private String tagId;
        /**
         * tagName
         */
        private String tagName;

        public String getTagId() {
            return this.tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public String getTagName() {
            return this.tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

    }


}

