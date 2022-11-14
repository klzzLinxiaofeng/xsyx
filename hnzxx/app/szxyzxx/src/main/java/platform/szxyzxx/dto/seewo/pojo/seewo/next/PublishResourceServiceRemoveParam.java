package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量删除资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServiceRemoveParam extends OpenApiParam {


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

    public static PublishResourceServiceRemoveParamBuilder builder(){
        return new PublishResourceServiceRemoveParamBuilder();
    }

    public static class PublishResourceServiceRemoveParamBuilder{
        private RequestBody requestBody;

        public PublishResourceServiceRemoveParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PublishResourceServiceRemoveParam build(){
            PublishResourceServiceRemoveParam param = new PublishResourceServiceRemoveParam();
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
         * 学校ID
         */
        private String schoolUid;
        /**
         * 操作者用户ID
         */
        private String userUid;
        /**
         * 删除的资源id列表
         */
        private List<String> ids;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
        }

        public List<String> getIds() {
            return this.ids;
        }

        public void setIds(List<String> ids) {
            this.ids = ids;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String schoolUid;
            private String userUid;
            private List<String> ids;

            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder userUid(String userUid){
                this.userUid = userUid;
                return this;
            }
            public QueryBuilder ids(List<String> ids){
                this.ids = ids;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setSchoolUid(schoolUid);
                param.setUserUid(userUid);
                param.setIds(ids);
                return param;
            }
        }
    }


}
