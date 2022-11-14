package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据条件获取资源（已废弃）
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ResourceServiceListResourceByConditionPageParam extends OpenApiParam {


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

    public static ResourceServiceListResourceByConditionPageParamBuilder builder(){
        return new ResourceServiceListResourceByConditionPageParamBuilder();
    }

    public static class ResourceServiceListResourceByConditionPageParamBuilder{
        private RequestBody requestBody;

        public ResourceServiceListResourceByConditionPageParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ResourceServiceListResourceByConditionPageParam build(){
            ResourceServiceListResourceByConditionPageParam param = new ResourceServiceListResourceByConditionPageParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * queryDto
         */
        private QueryDto queryDto;

        public QueryDto getQueryDto() {
            return this.queryDto;
        }

        public void setQueryDto(QueryDto queryDto) {
            this.queryDto = queryDto;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private QueryDto queryDto;

            public RequestBodyBuilder queryDto(QueryDto queryDto){
                this.queryDto = queryDto;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQueryDto(queryDto);
                return param;
            }
        }
    }

    public static class QueryDto {
        /**
         * 调用凭证
         */
        private String appKey;
        /**
         * 资源类型
         */
        private List<String> typeKeys;
        /**
         * 学校uid
         */
        private String orgUid;
        /**
         * 默认填写1
         */
        private Integer checkStatus;
        /**
         * 起始页
         */
        private Integer start;
        /**
         * 页大小
         */
        private Integer pageSize;

        public String getAppKey() {
            return this.appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public List<String> getTypeKeys() {
            return this.typeKeys;
        }

        public void setTypeKeys(List<String> typeKeys) {
            this.typeKeys = typeKeys;
        }

        public String getOrgUid() {
            return this.orgUid;
        }

        public void setOrgUid(String orgUid) {
            this.orgUid = orgUid;
        }

        public Integer getCheckStatus() {
            return this.checkStatus;
        }

        public void setCheckStatus(Integer checkStatus) {
            this.checkStatus = checkStatus;
        }

        public Integer getStart() {
            return this.start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }


        public static QueryDtoBuilder builder(){
            return new QueryDtoBuilder();
        }

        public static class QueryDtoBuilder{
            private String appKey;
            private List<String> typeKeys;
            private String orgUid;
            private Integer checkStatus;
            private Integer start;
            private Integer pageSize;

            public QueryDtoBuilder appKey(String appKey){
                this.appKey = appKey;
                return this;
            }
            public QueryDtoBuilder typeKeys(List<String> typeKeys){
                this.typeKeys = typeKeys;
                return this;
            }
            public QueryDtoBuilder orgUid(String orgUid){
                this.orgUid = orgUid;
                return this;
            }
            public QueryDtoBuilder checkStatus(Integer checkStatus){
                this.checkStatus = checkStatus;
                return this;
            }
            public QueryDtoBuilder start(Integer start){
                this.start = start;
                return this;
            }
            public QueryDtoBuilder pageSize(Integer pageSize){
                this.pageSize = pageSize;
                return this;
            }

            public QueryDto build(){
                QueryDto param = new QueryDto();
                param.setAppKey(appKey);
                param.setTypeKeys(typeKeys);
                param.setOrgUid(orgUid);
                param.setCheckStatus(checkStatus);
                param.setStart(start);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
