package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 获取学校下的班级荣誉列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AppApiListClassHonourParam extends OpenApiParam {


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

    public static AppApiListClassHonourParamBuilder builder(){
        return new AppApiListClassHonourParamBuilder();
    }

    public static class AppApiListClassHonourParamBuilder{
        private RequestBody requestBody;

        public AppApiListClassHonourParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AppApiListClassHonourParam build(){
            AppApiListClassHonourParam param = new AppApiListClassHonourParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private ListClassHonourQuery query;

        public ListClassHonourQuery getQuery() {
            return this.query;
        }

        public void setQuery(ListClassHonourQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private ListClassHonourQuery query;

            public RequestBodyBuilder query(ListClassHonourQuery query){
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

    public static class ListClassHonourQuery {
        /**
         * appId
         */
        private String appId;
        /**
         * 荣誉名字
         */
        private String honourName;
        /**
         * schoolUid
         */
        private String schoolUid;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
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


        public static ListClassHonourQueryBuilder builder(){
            return new ListClassHonourQueryBuilder();
        }

        public static class ListClassHonourQueryBuilder{
            private String appId;
            private String honourName;
            private String schoolUid;

            public ListClassHonourQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public ListClassHonourQueryBuilder honourName(String honourName){
                this.honourName = honourName;
                return this;
            }
            public ListClassHonourQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }

            public ListClassHonourQuery build(){
                ListClassHonourQuery param = new ListClassHonourQuery();
                param.setAppId(appId);
                param.setHonourName(honourName);
                param.setSchoolUid(schoolUid);
                return param;
            }
        }
    }


}
