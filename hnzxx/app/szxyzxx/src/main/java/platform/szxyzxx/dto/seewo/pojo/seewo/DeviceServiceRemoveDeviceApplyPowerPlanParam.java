package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 清除设备应用的开关机计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class DeviceServiceRemoveDeviceApplyPowerPlanParam extends OpenApiParam {


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

    public static DeviceServiceRemoveDeviceApplyPowerPlanParamBuilder builder(){
        return new DeviceServiceRemoveDeviceApplyPowerPlanParamBuilder();
    }

    public static class DeviceServiceRemoveDeviceApplyPowerPlanParamBuilder{
        private RequestBody requestBody;

        public DeviceServiceRemoveDeviceApplyPowerPlanParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public DeviceServiceRemoveDeviceApplyPowerPlanParam build(){
            DeviceServiceRemoveDeviceApplyPowerPlanParam param = new DeviceServiceRemoveDeviceApplyPowerPlanParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 
         */
        private RemoveDeviceApplyPowerPlanQuery query;

        public RemoveDeviceApplyPowerPlanQuery getQuery() {
            return this.query;
        }

        public void setQuery(RemoveDeviceApplyPowerPlanQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private RemoveDeviceApplyPowerPlanQuery query;

            public RequestBodyBuilder query(RemoveDeviceApplyPowerPlanQuery query){
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

    public static class RemoveDeviceApplyPowerPlanQuery {
        /**
         * appId
         */
        private String appId;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * String
         */
        private List<String> snCodes;

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

        public List<String> getSnCodes() {
            return this.snCodes;
        }

        public void setSnCodes(List<String> snCodes) {
            this.snCodes = snCodes;
        }


        public static RemoveDeviceApplyPowerPlanQueryBuilder builder(){
            return new RemoveDeviceApplyPowerPlanQueryBuilder();
        }

        public static class RemoveDeviceApplyPowerPlanQueryBuilder{
            private String appId;
            private String schoolUid;
            private List<String> snCodes;

            public RemoveDeviceApplyPowerPlanQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public RemoveDeviceApplyPowerPlanQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public RemoveDeviceApplyPowerPlanQueryBuilder snCodes(List<String> snCodes){
                this.snCodes = snCodes;
                return this;
            }

            public RemoveDeviceApplyPowerPlanQuery build(){
                RemoveDeviceApplyPowerPlanQuery param = new RemoveDeviceApplyPowerPlanQuery();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setSnCodes(snCodes);
                return param;
            }
        }
    }


}
