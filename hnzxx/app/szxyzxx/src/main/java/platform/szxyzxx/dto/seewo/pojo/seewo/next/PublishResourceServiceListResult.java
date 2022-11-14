package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 条件查询新闻公告图片视频资源列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServiceListResult extends OpenApiResult {

    public PublishResourceServiceListResult(HttpResponse response) {
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
         * 分页大小
         */
        private Integer pageSize;
        /**
         * 当前页
         */
        private Integer page;
        /**
         * 总数
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
         * 资源ID
         */
        private String id;
        /**
         * 新闻公告标题
         */
        private String title;
        /**
         * 新闻公告封面图路径
         */
        private String coverImageUrl;
        /**
         * 新闻公告内容
         */
        private String content;
        /**
         * 图片相关参数--描述
         */
        private String description;
        /**
         * 视频名字
         */
        private String name;
        /**
         * 视频时长，单位秒
         */
        private Integer len;
        /**
         * 视频大小，单位byte
         */
        private Integer size;
        /**
         * 视频封面url
         */
        private String headImageUrl;
        /**
         * 视频分辨率
         */
        private String resolution;
        /**
         * 视频或者图片路径
         */
        private String url;
        /**
         * 资源阅读量
         */
        private Integer viewCount;
        /**
         * 资源审核状态，1通过，2不通过
         */
        private Integer checkStatus;
        /**
         * 发布时间
         */
        private String publishTime;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverImageUrl() {
            return this.coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getLen() {
            return this.len;
        }

        public void setLen(Integer len) {
            this.len = len;
        }

        public Integer getSize() {
            return this.size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public String getHeadImageUrl() {
            return this.headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public String getResolution() {
            return this.resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getViewCount() {
            return this.viewCount;
        }

        public void setViewCount(Integer viewCount) {
            this.viewCount = viewCount;
        }

        public Integer getCheckStatus() {
            return this.checkStatus;
        }

        public void setCheckStatus(Integer checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getPublishTime() {
            return this.publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

    }


}

