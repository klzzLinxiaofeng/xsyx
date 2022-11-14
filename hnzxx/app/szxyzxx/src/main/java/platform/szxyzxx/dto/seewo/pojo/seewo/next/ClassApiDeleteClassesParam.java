package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量删除班级
 * 批量删除班级
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassApiDeleteClassesParam extends OpenApiParam {


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

    public static ClassApiDeleteClassesParamBuilder builder(){
        return new ClassApiDeleteClassesParamBuilder();
    }

    public static class ClassApiDeleteClassesParamBuilder{
        private JSONRequestBody requestBody;

        public ClassApiDeleteClassesParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassApiDeleteClassesParam build(){
            ClassApiDeleteClassesParam param = new ClassApiDeleteClassesParam();
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
         * 第三方班级id列表（classId和seewoClassId必须有一个）
         */
        private List<String> classIds;
        /**
         * seewo班级id列表（classId和seewoClassId必须有一个）
         */
        private List<String> seewoClassIds;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public List<String> getClassIds() {
            return this.classIds;
        }

        public void setClassIds(List<String> classIds) {
            this.classIds = classIds;
        }

        public List<String> getSeewoClassIds() {
            return this.seewoClassIds;
        }

        public void setSeewoClassIds(List<String> seewoClassIds) {
            this.seewoClassIds = seewoClassIds;
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


        public static MisThirdClassQueryDtoBuilder builder(){
            return new MisThirdClassQueryDtoBuilder();
        }

        public static class MisThirdClassQueryDtoBuilder{
            private List<String> classIds;
            private List<String> seewoClassIds;
            private String schoolUid;
            private String appId;

            public MisThirdClassQueryDtoBuilder classIds(List<String> classIds){
                this.classIds = classIds;
                return this;
            }
            public MisThirdClassQueryDtoBuilder seewoClassIds(List<String> seewoClassIds){
                this.seewoClassIds = seewoClassIds;
                return this;
            }
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
                param.setClassIds(classIds);
                param.setSeewoClassIds(seewoClassIds);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
