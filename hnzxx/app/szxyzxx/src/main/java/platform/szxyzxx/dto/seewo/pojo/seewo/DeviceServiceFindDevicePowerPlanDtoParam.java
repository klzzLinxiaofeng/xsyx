package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 获取设备开关机计划详情
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class DeviceServiceFindDevicePowerPlanDtoParam extends OpenApiParam {


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

    public static DeviceServiceFindDevicePowerPlanDtoParamBuilder builder(){
        return new DeviceServiceFindDevicePowerPlanDtoParamBuilder();
    }

    public static class DeviceServiceFindDevicePowerPlanDtoParamBuilder{
        private RequestBody requestBody;

        public DeviceServiceFindDevicePowerPlanDtoParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public DeviceServiceFindDevicePowerPlanDtoParam build(){
            DeviceServiceFindDevicePowerPlanDtoParam param = new DeviceServiceFindDevicePowerPlanDtoParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 
         */
        private FindDevicePowerPlanDtoQuery query;

        public FindDevicePowerPlanDtoQuery getQuery() {
            return this.query;
        }

        public void setQuery(FindDevicePowerPlanDtoQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private FindDevicePowerPlanDtoQuery query;

            public RequestBodyBuilder query(FindDevicePowerPlanDtoQuery query){
                this.query = query;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class FindDevicePowerPlanDtoQuery {
        /**
         * appId
         */
        private String appId;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 设备码
         */
        private String snCode;

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

        public String getSnCode() {
            return this.snCode;
        }

        public void setSnCode(String snCode) {
            this.snCode = snCode;
        }


        public static FindDevicePowerPlanDtoQueryBuilder builder(){
            return new FindDevicePowerPlanDtoQueryBuilder();
        }

        public static class FindDevicePowerPlanDtoQueryBuilder{
            private String appId;
            private String schoolUid;
            private String snCode;

            public FindDevicePowerPlanDtoQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public FindDevicePowerPlanDtoQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public FindDevicePowerPlanDtoQueryBuilder snCode(String snCode){
                this.snCode = snCode;
                return this;
            }

            public FindDevicePowerPlanDtoQuery build(){
                FindDevicePowerPlanDtoQuery param = new FindDevicePowerPlanDtoQuery();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setSnCode(snCode);
                return param;
            }
        }
    }


}
