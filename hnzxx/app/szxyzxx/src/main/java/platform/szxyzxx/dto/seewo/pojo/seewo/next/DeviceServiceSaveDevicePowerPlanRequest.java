package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 保存并应用设备开关机计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class DeviceServiceSaveDevicePowerPlanRequest extends OpenApiRequest<DeviceServiceSaveDevicePowerPlanParam, DeviceServiceSaveDevicePowerPlanResult> {

    public DeviceServiceSaveDevicePowerPlanRequest(DeviceServiceSaveDevicePowerPlanParam param) {
        this();
        setBizModel(param);
    }

    public DeviceServiceSaveDevicePowerPlanRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/device-service/save-device-power-plan");
        setHttpMethod("POST");
    }

    public Class<DeviceServiceSaveDevicePowerPlanResult> getResponseClass() {
        return DeviceServiceSaveDevicePowerPlanResult.class;
    }

    public Class<DeviceServiceSaveDevicePowerPlanParam> getDomainClass() {
        return DeviceServiceSaveDevicePowerPlanParam.class;
    }
}

