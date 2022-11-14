package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 查询指定资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServiceFindParam extends OpenApiParam {


    /**
     * 请求体内容
     */
    
    private Object requestBody;


    public Object getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public static PublishResourceServiceFindParamBuilder builder(){
        return new PublishResourceServiceFindParamBuilder();
    }

    public static class PublishResourceServiceFindParamBuilder{
        private Object requestBody;

        public PublishResourceServiceFindParamBuilder requestBody(Object requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PublishResourceServiceFindParam build(){
            PublishResourceServiceFindParam param = new PublishResourceServiceFindParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class Object {
        /**
         * 资源ID
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public static ObjectBuilder builder(){
            return new ObjectBuilder();
        }

        public static class ObjectBuilder{
            private String id;

            public ObjectBuilder id(String id){
                this.id = id;
                return this;
            }

            public Object build(){
                Object param = new Object();
                param.setId(id);
                return param;
            }
        }
    }


}
