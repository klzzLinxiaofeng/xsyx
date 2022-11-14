package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量删除学校管理员
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class SchoolApiRemoveManagersParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private JSONRequestBody requestBody;


    public JSONRequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(JSONRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static SchoolApiRemoveManagersParamBuilder builder(){
        return new SchoolApiRemoveManagersParamBuilder();
    }

    public static class SchoolApiRemoveManagersParamBuilder{
        private JSONRequestBody requestBody;

        public SchoolApiRemoveManagersParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public SchoolApiRemoveManagersParam build(){
            SchoolApiRemoveManagersParam param = new SchoolApiRemoveManagersParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private MisThirdUserQueryDto query;

        public MisThirdUserQueryDto getQuery() {
            return this.query;
        }

        public void setQuery(MisThirdUserQueryDto query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private MisThirdUserQueryDto query;

            public JSONRequestBodyBuilder query(MisThirdUserQueryDto query){
                this.query = query;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class MisThirdUserQueryDto {
        /**
         * 管理员手机号列表，不能是高级管理员
         */
        private List<String> userPhones;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用appId
         */
        private String appId;

        public List<String> getUserPhones() {
            return this.userPhones;
        }

        public void setUserPhones(List<String> userPhones) {
            this.userPhones = userPhones;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }


        public static MisThirdUserQueryDtoBuilder builder(){
            return new MisThirdUserQueryDtoBuilder();
        }

        public static class MisThirdUserQueryDtoBuilder{
            private List<String> userPhones;
            private String schoolUid;
            private String appId;

            public MisThirdUserQueryDtoBuilder userPhones(List<String> userPhones){
                this.userPhones = userPhones;
                return this;
            }
            public MisThirdUserQueryDtoBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisThirdUserQueryDtoBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public MisThirdUserQueryDto build(){
                MisThirdUserQueryDto param = new MisThirdUserQueryDto();
                param.setUserPhones(userPhones);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
