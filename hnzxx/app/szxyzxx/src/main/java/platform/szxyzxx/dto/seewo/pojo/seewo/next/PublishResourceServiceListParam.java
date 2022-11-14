package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 条件查询新闻公告图片视频资源列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServiceListParam extends OpenApiParam {


    /**
     * 响应体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static PublishResourceServiceListParamBuilder builder(){
        return new PublishResourceServiceListParamBuilder();
    }

    public static class PublishResourceServiceListParamBuilder{
        private RequestBody requestBody;

        public PublishResourceServiceListParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PublishResourceServiceListParam build(){
            PublishResourceServiceListParam param = new PublishResourceServiceListParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private Query query;

        public Query getQuery() {
            return this.query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Query query;

            public RequestBodyBuilder query(Query query){
                this.query = query;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class Query {
        /**
         * classUid
         */
        private String classUid;
        /**
         * schoolUid
         */
        private String schoolUid;
        /**
         * 资源类型key：news，note，pic，video
         */
        private String key;
        /**
         * 标签id
         */
        private String tagId;
        /**
         * 第几页
         */
        private Integer page;
        /**
         * 分页大小
         */
        private Integer pageSize;

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getTagId() {
            return this.tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String classUid;
            private String schoolUid;
            private String key;
            private String tagId;
            private Integer page;
            private Integer pageSize;

            public QueryBuilder classUid(String classUid){
                this.classUid = classUid;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder key(String key){
                this.key = key;
                return this;
            }
            public QueryBuilder tagId(String tagId){
                this.tagId = tagId;
                return this;
            }
            public QueryBuilder page(Integer page){
                this.page = page;
                return this;
            }
            public QueryBuilder pageSize(Integer pageSize){
                this.pageSize = pageSize;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setClassUid(classUid);
                param.setSchoolUid(schoolUid);
                param.setKey(key);
                param.setTagId(tagId);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
