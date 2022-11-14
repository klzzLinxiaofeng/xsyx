package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 新增或更新资源回调URL
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ResourceHookSaveOrUpdateResourceHookParam extends OpenApiParam {


    /**
     * 请求体
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static ResourceHookSaveOrUpdateResourceHookParamBuilder builder(){
        return new ResourceHookSaveOrUpdateResourceHookParamBuilder();
    }

    public static class ResourceHookSaveOrUpdateResourceHookParamBuilder{
        private RequestBody requestBody;

        public ResourceHookSaveOrUpdateResourceHookParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ResourceHookSaveOrUpdateResourceHookParam build(){
            ResourceHookSaveOrUpdateResourceHookParam param = new ResourceHookSaveOrUpdateResourceHookParam();
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
         * 资源类型，目前支持KID_NEW_NOTE，ATTENDANCE
         */
        private String resType;
        /**
         * appId
         */
        private String appId;
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 注册的URL，为空时表示删除
         */
        private String url;

        public String getResType() {
            return this.resType;
        }

        public void setResType(String resType) {
            this.resType = resType;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String resType;
            private String appId;
            private String schoolUid;
            private String url;

            public QueryBuilder resType(String resType){
                this.resType = resType;
                return this;
            }
            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder url(String url){
                this.url = url;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setResType(resType);
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setUrl(url);
                return param;
            }
        }
    }


}
