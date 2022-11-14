package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 获取设备信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class DeviceServiceListDeviceInfoParam extends OpenApiParam {


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

    public static DeviceServiceListDeviceInfoParamBuilder builder(){
        return new DeviceServiceListDeviceInfoParamBuilder();
    }

    public static class DeviceServiceListDeviceInfoParamBuilder{
        private RequestBody requestBody;

        public DeviceServiceListDeviceInfoParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public DeviceServiceListDeviceInfoParam build(){
            DeviceServiceListDeviceInfoParam param = new DeviceServiceListDeviceInfoParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private ListDeviceInfoQuery query;

        public ListDeviceInfoQuery getQuery() {
            return this.query;
        }

        public void setQuery(ListDeviceInfoQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private ListDeviceInfoQuery query;

            public RequestBodyBuilder query(ListDeviceInfoQuery query){
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

    public static class ListDeviceInfoQuery {
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 系统code，此处传入 yun-ban 即可
         */
        private String systemCode;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getSystemCode() {
            return this.systemCode;
        }

        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
        }


        public static ListDeviceInfoQueryBuilder builder(){
            return new ListDeviceInfoQueryBuilder();
        }

        public static class ListDeviceInfoQueryBuilder{
            private String schoolUid;
            private String systemCode;

            public ListDeviceInfoQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public ListDeviceInfoQueryBuilder systemCode(String systemCode){
                this.systemCode = systemCode;
                return this;
            }

            public ListDeviceInfoQuery build(){
                ListDeviceInfoQuery param = new ListDeviceInfoQuery();
                param.setSchoolUid(schoolUid);
                param.setSystemCode(systemCode);
                return param;
            }
        }
    }


}
