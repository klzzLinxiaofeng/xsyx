package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 保存并应用设备开关机计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class DeviceServiceSaveDevicePowerPlanParam extends OpenApiParam {


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

    public static DeviceServiceSaveDevicePowerPlanParamBuilder builder(){
        return new DeviceServiceSaveDevicePowerPlanParamBuilder();
    }

    public static class DeviceServiceSaveDevicePowerPlanParamBuilder{
        private RequestBody requestBody;

        public DeviceServiceSaveDevicePowerPlanParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public DeviceServiceSaveDevicePowerPlanParam build(){
            DeviceServiceSaveDevicePowerPlanParam param = new DeviceServiceSaveDevicePowerPlanParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 
         */
        private SaveDevicePowerPlanQuery query;

        public SaveDevicePowerPlanQuery getQuery() {
            return this.query;
        }

        public void setQuery(SaveDevicePowerPlanQuery query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private SaveDevicePowerPlanQuery query;

            public RequestBodyBuilder query(SaveDevicePowerPlanQuery query){
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

    public static class SaveDevicePowerPlanQuery {
        /**
         * appId
         */
        private String appId;
        /**
         * 计划名
         */
        private String name;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 设置开关机的设备码列表
         */
        private List<String> snCodes;
        /**
         * 开关机时间项列表
         */
        private List<ItemsItem> items;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
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

        public List<ItemsItem> getItems() {
            return this.items;
        }

        public void setItems(List<ItemsItem> items) {
            this.items = items;
        }


        public static SaveDevicePowerPlanQueryBuilder builder(){
            return new SaveDevicePowerPlanQueryBuilder();
        }

        public static class SaveDevicePowerPlanQueryBuilder{
            private String appId;
            private String name;
            private String schoolUid;
            private List<String> snCodes;
            private List<ItemsItem> items;

            public SaveDevicePowerPlanQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public SaveDevicePowerPlanQueryBuilder name(String name){
                this.name = name;
                return this;
            }
            public SaveDevicePowerPlanQueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public SaveDevicePowerPlanQueryBuilder snCodes(List<String> snCodes){
                this.snCodes = snCodes;
                return this;
            }
            public SaveDevicePowerPlanQueryBuilder items(List<ItemsItem> items){
                this.items = items;
                return this;
            }

            public SaveDevicePowerPlanQuery build(){
                SaveDevicePowerPlanQuery param = new SaveDevicePowerPlanQuery();
                param.setAppId(appId);
                param.setName(name);
                param.setSchoolUid(schoolUid);
                param.setSnCodes(snCodes);
                param.setItems(items);
                return param;
            }
        }
    }

    public static class ItemsItem {
        /**
         * 周几。1-7代表周一到周日
         */
        private String day;
        /**
         * 开始时间：使用如下格式：08:00:00
         */
        private String startTime;
        /**
         * 结束时间：使用如下格式：12:00:00
         */
        private String endTime;

        public String getDay() {
            return this.day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }


        public static ItemsItemBuilder builder(){
            return new ItemsItemBuilder();
        }

        public static class ItemsItemBuilder{
            private String day;
            private String startTime;
            private String endTime;

            public ItemsItemBuilder day(String day){
                this.day = day;
                return this;
            }
            public ItemsItemBuilder startTime(String startTime){
                this.startTime = startTime;
                return this;
            }
            public ItemsItemBuilder endTime(String endTime){
                this.endTime = endTime;
                return this;
            }

            public ItemsItem build(){
                ItemsItem param = new ItemsItem();
                param.setDay(day);
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                return param;
            }
        }
    }


}
