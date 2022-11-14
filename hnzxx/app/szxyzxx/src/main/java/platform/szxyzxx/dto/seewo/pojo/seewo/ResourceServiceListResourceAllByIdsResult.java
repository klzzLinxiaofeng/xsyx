package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据Id列表带有文本信息的资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ResourceServiceListResourceAllByIdsResult extends OpenApiResult {

    public ResourceServiceListResourceAllByIdsResult(HttpResponse response) {
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
         * 资源id
         */
        private Long id;
        /**
         * 类型typeKey
         */
        private String typeKey;
        /**
         * 资源阅读量
         */
        private Integer viewCount;
        /**
         * 新闻标题，json串
         */
        private String content;
        /**
         * 新闻内容
         */
        private String extraContent;

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTypeKey() {
            return this.typeKey;
        }

        public void setTypeKey(String typeKey) {
            this.typeKey = typeKey;
        }

        public Integer getViewCount() {
            return this.viewCount;
        }

        public void setViewCount(Integer viewCount) {
            this.viewCount = viewCount;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getExtraContent() {
            return this.extraContent;
        }

        public void setExtraContent(String extraContent) {
            this.extraContent = extraContent;
        }

    }


}

