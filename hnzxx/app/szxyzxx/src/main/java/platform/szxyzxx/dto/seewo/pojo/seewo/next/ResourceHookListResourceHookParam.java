package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 查询已注册的URL
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ResourceHookListResourceHookParam extends OpenApiParam {


    /**
     * requestBody
     */
    
    private Object requestBody;


    public Object getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public static ResourceHookListResourceHookParamBuilder builder(){
        return new ResourceHookListResourceHookParamBuilder();
    }

    public static class ResourceHookListResourceHookParamBuilder{
        private Object requestBody;

        public ResourceHookListResourceHookParamBuilder requestBody(Object requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ResourceHookListResourceHookParam build(){
            ResourceHookListResourceHookParam param = new ResourceHookListResourceHookParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class Object {
        /**
         * appId
         */
        private String appId;
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 资源类型
         */
        private String resType;

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

        public String getResType() {
            return this.resType;
        }

        public void setResType(String resType) {
            this.resType = resType;
        }


        public static ObjectBuilder builder(){
            return new ObjectBuilder();
        }

        public static class ObjectBuilder{
            private String appId;
            private String schoolUid;
            private String resType;

            public ObjectBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public ObjectBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public ObjectBuilder resType(String resType){
                this.resType = resType;
                return this;
            }

            public Object build(){
                Object param = new Object();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setResType(resType);
                return param;
            }
        }
    }


}
