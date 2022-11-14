package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 删除班级荣誉
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AppApiRemoveClassHonourParam extends OpenApiParam {


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

    public static AppApiRemoveClassHonourParamBuilder builder(){
        return new AppApiRemoveClassHonourParamBuilder();
    }

    public static class AppApiRemoveClassHonourParamBuilder{
        private RequestBody requestBody;

        public AppApiRemoveClassHonourParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AppApiRemoveClassHonourParam build(){
            AppApiRemoveClassHonourParam param = new AppApiRemoveClassHonourParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private RemoveClassHonourQuery query;

        public RemoveClassHonourQuery getQuery() {
            return this.query;
        }

        public void setQuery(RemoveClassHonourQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private RemoveClassHonourQuery query;

            public RequestBodyBuilder query(RemoveClassHonourQuery query){
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

    public static class RemoveClassHonourQuery {
        /**
         * appId
         */
        private String appId;
        /**
         * 班级id列表
         */
        private List<String> classUids;
        /**
         * 荣誉名字
         */
        private String honourName;
        /**
         * 学校id
         */
        private String schoolUid;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public List<String> getClassUids() {
            return this.classUids;
        }

        public void setClassUids(List<String> classUids) {
            this.classUids = classUids;
        }

        public String getHonourName() {
            return this.honourName;
        }

        public void setHonourName(String honourName) {
            this.honourName = honourName;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }


        public static RemoveClassHonourQueryBuilder builder(){
            return new RemoveClassHonourQueryBuilder();
        }

        public static class RemoveClassHonourQueryBuilder{
            private String appId;
            private List<String> classUids;
            private String honourName;
            private String schoolUid;

            public RemoveClassHonourQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public RemoveClassHonourQueryBuilder classUids(List<String> classUids){
                this.classUids = classUids;
                return this;
            }
            public RemoveClassHonourQueryBuilder honourName(String honourName){
                this.honourName = honourName;
                return this;
            }
            public RemoveClassHonourQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }

            public RemoveClassHonourQuery build(){
                RemoveClassHonourQuery param = new RemoveClassHonourQuery();
                param.setAppId(appId);
                param.setClassUids(classUids);
                param.setHonourName(honourName);
                param.setSchoolUid(schoolUid);
                return param;
            }
        }
    }


}
