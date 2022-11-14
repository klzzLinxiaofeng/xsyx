package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据班级ID列表查询班级信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceFindByIdsParam extends OpenApiParam {


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

    public static ClassServiceFindByIdsParamBuilder builder(){
        return new ClassServiceFindByIdsParamBuilder();
    }

    public static class ClassServiceFindByIdsParamBuilder{
        private RequestBody requestBody;

        public ClassServiceFindByIdsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassServiceFindByIdsParam build(){
            ClassServiceFindByIdsParam param = new ClassServiceFindByIdsParam();
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
         * appId
         */
        private String appId;
        /**
         * 班级ID列表，最大100条
         */
        private List<String> ids;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
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
            private String appId;
            private List<String> ids;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder ids(List<String> ids){
                this.ids = ids;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setIds(ids);
                return param;
            }
        }
    }


}
