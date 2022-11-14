package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据Id列表带有文本信息的资源（已废弃）
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ResourceServiceListResourceAllByIdsParam extends OpenApiParam {


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

    public static ResourceServiceListResourceAllByIdsParamBuilder builder(){
        return new ResourceServiceListResourceAllByIdsParamBuilder();
    }

    public static class ResourceServiceListResourceAllByIdsParamBuilder{
        private RequestBody requestBody;

        public ResourceServiceListResourceAllByIdsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ResourceServiceListResourceAllByIdsParam build(){
            ResourceServiceListResourceAllByIdsParam param = new ResourceServiceListResourceAllByIdsParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * ids
         */
        private List<Long> ids;

        public List<Long> getIds() {
            return this.ids;
        }

        public void setIds(List<Long> ids) {
            this.ids = ids;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private List<Long> ids;

            public RequestBodyBuilder ids(List<Long> ids){
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
