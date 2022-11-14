package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据班级id获取班级数据
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassApiQueryClassByClassIdParam extends OpenApiParam {


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

    public static ClassApiQueryClassByClassIdParamBuilder builder(){
        return new ClassApiQueryClassByClassIdParamBuilder();
    }

    public static class ClassApiQueryClassByClassIdParamBuilder{
        private JSONRequestBody requestBody;

        public ClassApiQueryClassByClassIdParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassApiQueryClassByClassIdParam build(){
            ClassApiQueryClassByClassIdParam param = new ClassApiQueryClassByClassIdParam();
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
         * 第三方班级id
         */
        private String classId;
        /**
         * seewo班级id
         */
        private String seewoClassId;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;

        public String getClassId() {
            return this.classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getSeewoClassId() {
            return this.seewoClassId;
        }

        public void setSeewoClassId(String seewoClassId) {
            this.seewoClassId = seewoClassId;
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
            private String classId;
            private String seewoClassId;
            private String schoolUid;
            private String appId;

            public MisThirdClassQueryDtoBuilder classId(String classId){
                this.classId = classId;
                return this;
            }
            public MisThirdClassQueryDtoBuilder seewoClassId(String seewoClassId){
                this.seewoClassId = seewoClassId;
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
                param.setClassId(classId);
                param.setSeewoClassId(seewoClassId);
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                return param;
            }
        }
    }


}
