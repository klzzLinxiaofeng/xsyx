package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 查询学校资源栏目
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class PublishResourceServiceListTagsParam extends OpenApiParam {


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

    public static PublishResourceServiceListTagsParamBuilder builder(){
        return new PublishResourceServiceListTagsParamBuilder();
    }

    public static class PublishResourceServiceListTagsParamBuilder{
        private RequestBody requestBody;

        public PublishResourceServiceListTagsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PublishResourceServiceListTagsParam build(){
            PublishResourceServiceListTagsParam param = new PublishResourceServiceListTagsParam();
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
         * 学校uid
         */
        private String schoolUid;
        /**
         * 资源类型key：pic
         */
        private String key;

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


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String schoolUid;
            private String key;

            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder key(String key){
                this.key = key;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setSchoolUid(schoolUid);
                param.setKey(key);
                return param;
            }
        }
    }


}
