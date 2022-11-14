package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取设备信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class DeviceServiceListDeviceInfoRequest extends OpenApiRequest<DeviceServiceListDeviceInfoParam, DeviceServiceListDeviceInfoResult> {

    public DeviceServiceListDeviceInfoRequest(DeviceServiceListDeviceInfoParam param) {
        this();
        setBizModel(param);
    }

    public DeviceServiceListDeviceInfoRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/device-service/list-device-info");
        setHttpMethod("POST");
    }

    public Class<DeviceServiceListDeviceInfoResult> getResponseClass() {
        return DeviceServiceListDeviceInfoResult.class;
    }

    public Class<DeviceServiceListDeviceInfoParam> getDomainClass() {
        return DeviceServiceListDeviceInfoParam.class;
    }
}

