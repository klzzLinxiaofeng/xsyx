package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据条件获取资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class ResourceServiceListResourceByConditionPageResult extends OpenApiResult {

    public ResourceServiceListResourceByConditionPageResult(HttpResponse response) {
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
         * pageSize
         */
        private Integer pageSize;
        /**
         * page
         */
        private Integer page;
        /**
         * 资源总数
         */
        private Integer totalCount;
        /**
         * result
         */
        private List<ResultItem> result;

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<ResultItem> getResult() {
            return this.result;
        }

        public void setResult(List<ResultItem> result) {
            this.result = result;
        }

    }

    public static class ResultItem {
        /**
         * 资源id
         */
        private Long id;
        /**
         * 上传者uid
         */
        private String uploaderUid;
        /**
         * 上传者名字
         */
        private String uploaderName;
        /**
         * viewCount
         */
        private Integer viewCount;

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUploaderUid() {
            return this.uploaderUid;
        }

        public void setUploaderUid(String uploaderUid) {
            this.uploaderUid = uploaderUid;
        }

        public String getUploaderName() {
            return this.uploaderName;
        }

        public void setUploaderName(String uploaderName) {
            this.uploaderName = uploaderName;
        }

        public Integer getViewCount() {
            return this.viewCount;
        }

        public void setViewCount(Integer viewCount) {
            this.viewCount = viewCount;
        }

    }


}

