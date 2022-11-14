package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据学校uid查询班级详细信息
 * 根据学校uid查询班级详细信息
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassApiQueryClassDetailBySchoolUidParam extends OpenApiParam {


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

    public static ClassApiQueryClassDetailBySchoolUidParamBuilder builder(){
        return new ClassApiQueryClassDetailBySchoolUidParamBuilder();
    }

    public static class ClassApiQueryClassDetailBySchoolUidParamBuilder{
        private JSONRequestBody requestBody;

        public ClassApiQueryClassDetailBySchoolUidParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassApiQueryClassDetailBySchoolUidParam build(){
            ClassApiQueryClassDetailBySchoolUidParam param = new ClassApiQueryClassDetailBySchoolUidParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private MisThirdClassQueryDto query;

        public MisThirdClassQueryDto getQuery() {
            return this.query;
        }

        public void setQuery(MisThirdClassQueryDto query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private MisThirdClassQueryDto query;

            public JSONRequestBodyBuilder query(MisThirdClassQueryDto query){
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

    public static class MisThirdClassQueryDto {
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

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


        public static MisThirdClassQueryDtoBuilder builder(){
            return new MisThirdClassQueryDtoBuilder();
        }

        public static class MisThirdClassQueryDtoBuilder{
            private String schoolUid;
            private String appId;

            public MisThirdClassQueryDtoBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisThirdClassQueryDtoBuilder appId(String appId){
                this.appId = appId;
                return this;
            }

            public MisThirdClassQueryDto build(){
                MisThirdClassQueryDto param = new MisThirdClassQueryDto();
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
