package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 获取学校生效的排课计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TimetablePlanFindEnablePlanInSchoolParam extends OpenApiParam {


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

    public static TimetablePlanFindEnablePlanInSchoolParamBuilder builder(){
        return new TimetablePlanFindEnablePlanInSchoolParamBuilder();
    }

    public static class TimetablePlanFindEnablePlanInSchoolParamBuilder{
        private JSONRequestBody requestBody;

        public TimetablePlanFindEnablePlanInSchoolParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TimetablePlanFindEnablePlanInSchoolParam build(){
            TimetablePlanFindEnablePlanInSchoolParam param = new TimetablePlanFindEnablePlanInSchoolParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private String schoolUid;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private String schoolUid;

            public JSONRequestBodyBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setSchoolUid(schoolUid);
                return param;
            }
        }
    }


}
