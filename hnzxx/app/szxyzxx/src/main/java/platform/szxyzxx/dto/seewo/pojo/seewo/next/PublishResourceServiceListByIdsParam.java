package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据资源id批量查找资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServiceListByIdsParam extends OpenApiParam {


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

    public static PublishResourceServiceListByIdsParamBuilder builder(){
        return new PublishResourceServiceListByIdsParamBuilder();
    }

    public static class PublishResourceServiceListByIdsParamBuilder{
        private RequestBody requestBody;

        public PublishResourceServiceListByIdsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PublishResourceServiceListByIdsParam build(){
            PublishResourceServiceListByIdsParam param = new PublishResourceServiceListByIdsParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * ids
         */
        private List<String> ids;

        public List<String> getIds() {
            return this.ids;
        }

        public void setIds(List<String> ids) {
            this.ids = ids;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private List<String> ids;

            public RequestBodyBuilder ids(List<String> ids){
                this.ids = ids;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setIds(ids);
                return param;
            }
        }
    }


}
