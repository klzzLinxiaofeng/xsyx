package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 保存班级荣誉
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AppApiSaveClassHonoursParam extends OpenApiParam {


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

    public static AppApiSaveClassHonoursParamBuilder builder(){
        return new AppApiSaveClassHonoursParamBuilder();
    }

    public static class AppApiSaveClassHonoursParamBuilder{
        private RequestBody requestBody;

        public AppApiSaveClassHonoursParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AppApiSaveClassHonoursParam build(){
            AppApiSaveClassHonoursParam param = new AppApiSaveClassHonoursParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private SaveClassHonourQuery query;

        public SaveClassHonourQuery getQuery() {
            return this.query;
        }

        public void setQuery(SaveClassHonourQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private SaveClassHonourQuery query;

            public RequestBodyBuilder query(SaveClassHonourQuery query){
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

    public static class SaveClassHonourQuery {
        /**
         * appId
         */
        private String appId;
        /**
         * 班级id列表
         */
        private List<String> classUids;
        /**
         * 荣誉结束时间，格式为yyyy-MM-dd
         */
        private String endTime;
        /**
         * 荣誉名字
         */
        private String honourName;
        /**
         * 颁发者id
         */
        private String issuerUid;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 荣誉开始时间，格式为yyyy-MM-dd
         */
        private String startTime;

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

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getHonourName() {
            return this.honourName;
        }

        public void setHonourName(String honourName) {
            this.honourName = honourName;
        }

        public String getIssuerUid() {
            return this.issuerUid;
        }

        public void setIssuerUid(String issuerUid) {
            this.issuerUid = issuerUid;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }


        public static SaveClassHonourQueryBuilder builder(){
            return new SaveClassHonourQueryBuilder();
        }

        public static class SaveClassHonourQueryBuilder{
            private String appId;
            private List<String> classUids;
            private String endTime;
            private String honourName;
            private String issuerUid;
            private String schoolUid;
            private String startTime;

            public SaveClassHonourQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public SaveClassHonourQueryBuilder classUids(List<String> classUids){
                this.classUids = classUids;
                return this;
            }
            public SaveClassHonourQueryBuilder endTime(String endTime){
                this.endTime = endTime;
                return this;
            }
            public SaveClassHonourQueryBuilder honourName(String honourName){
                this.honourName = honourName;
                return this;
            }
            public SaveClassHonourQueryBuilder issuerUid(String issuerUid){
                this.issuerUid = issuerUid;
                return this;
            }
            public SaveClassHonourQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public SaveClassHonourQueryBuilder startTime(String startTime){
                this.startTime = startTime;
                return this;
            }

            public SaveClassHonourQuery build(){
                SaveClassHonourQuery param = new SaveClassHonourQuery();
                param.setAppId(appId);
                param.setClassUids(classUids);
                param.setEndTime(endTime);
                param.setHonourName(honourName);
                param.setIssuerUid(issuerUid);
                param.setSchoolUid(schoolUid);
                param.setStartTime(startTime);
                return param;
            }
        }
    }


}
