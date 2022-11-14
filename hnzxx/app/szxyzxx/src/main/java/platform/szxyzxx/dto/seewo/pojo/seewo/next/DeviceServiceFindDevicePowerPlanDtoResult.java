package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 获取设备开关机计划详情
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class DeviceServiceFindDevicePowerPlanDtoResult extends OpenApiResult {

    public DeviceServiceFindDevicePowerPlanDtoResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private ResponseBody responseBody;


    public ResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class ResponseBody {
        /**
         * 业务状态码
         */
        private String code;
        /**
         * data
         */
        private Data data;
        /**
         * message
         */
        private String message;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Data getData() {
            return this.data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static class Data {
        /**
         * 开关机时间段列表
         */
        private List<ItemsItem> items;
        /**
         * 计划名
         */
        private String name;
        /**
         * 计划id
         */
        private String planUid;
        /**
         * 学校id
         */
        private String schoolUid;

        public List<ItemsItem> getItems() {
            return this.items;
        }

        public void setItems(List<ItemsItem> items) {
            this.items = items;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlanUid() {
            return this.planUid;
        }

        public void setPlanUid(String planUid) {
            this.planUid = planUid;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

    }

    public static class ItemsItem {
        /**
         * 周几。1-7对应星期一到星期日
         */
        private String day;
        /**
         * 结束时间，格式为yyyy-MM-dd
         */
        private String endTime;
        /**
         * 开关机计划id
         */
        private String planUid;
        /**
         * 开始时间，格式为yyyy-MM-dd
         */
        private String startTime;
        /**
         * 时间段id
         */
        private String uid;

        public String getDay() {
            return this.day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getPlanUid() {
            return this.planUid;
        }

        public void setPlanUid(String planUid) {
            this.planUid = planUid;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

    }


}

