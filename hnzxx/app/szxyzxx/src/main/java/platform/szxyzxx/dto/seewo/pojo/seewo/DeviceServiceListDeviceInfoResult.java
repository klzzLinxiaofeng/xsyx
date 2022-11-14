package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 获取设备信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class DeviceServiceListDeviceInfoResult extends OpenApiResult {

    public DeviceServiceListDeviceInfoResult(HttpResponse response) {
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
        private List<DataItem> data;
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

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static class DataItem {
        /**
         * 建筑物名
         */
        private String buildingName;
        /**
         * 建筑物ID
         */
        private String buildingUid;
        /**
         * 班级名
         */
        private String className;
        /**
         * 班级id
         */
        private String classUid;
        /**
         * 设备名
         */
        private String deviceName;
        /**
         * 硬件版本
         */
        private String hardwareVersion;
        /**
         * 教室名
         */
        private String roomName;
        /**
         * 教室id
         */
        private String roomUid;
        /**
         * 学校名
         */
        private String schoolName;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 屏幕模式
         */
        private Integer screenPattern;
        /**
         * 设备码
         */
        private String snCode;
        /**
         * 在线状态
         */
        private Boolean status;
        /**
         * 设备类型
         */
        private Integer type;
        /**
         * 软件版本号
         */
        private String version;
        /**
         * 有线网络ip
         */
        private String wireIp;
        /**
         * 无线网络ip
         */
        private String wirelessIp;

        public String getBuildingName() {
            return this.buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getBuildingUid() {
            return this.buildingUid;
        }

        public void setBuildingUid(String buildingUid) {
            this.buildingUid = buildingUid;
        }

        public String getClassName() {
            return this.className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getDeviceName() {
            return this.deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getHardwareVersion() {
            return this.hardwareVersion;
        }

        public void setHardwareVersion(String hardwareVersion) {
            this.hardwareVersion = hardwareVersion;
        }

        public String getRoomName() {
            return this.roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getRoomUid() {
            return this.roomUid;
        }

        public void setRoomUid(String roomUid) {
            this.roomUid = roomUid;
        }

        public String getSchoolName() {
            return this.schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public Integer getScreenPattern() {
            return this.screenPattern;
        }

        public void setScreenPattern(Integer screenPattern) {
            this.screenPattern = screenPattern;
        }

        public String getSnCode() {
            return this.snCode;
        }

        public void setSnCode(String snCode) {
            this.snCode = snCode;
        }

        public Boolean getStatus() {
            return this.status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getWireIp() {
            return this.wireIp;
        }

        public void setWireIp(String wireIp) {
            this.wireIp = wireIp;
        }

        public String getWirelessIp() {
            return this.wirelessIp;
        }

        public void setWirelessIp(String wirelessIp) {
            this.wirelessIp = wirelessIp;
        }

    }


}

