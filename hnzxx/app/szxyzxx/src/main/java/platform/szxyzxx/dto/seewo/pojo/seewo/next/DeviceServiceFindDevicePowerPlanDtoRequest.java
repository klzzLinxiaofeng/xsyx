package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 获取设备开关机计划详情
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class DeviceServiceFindDevicePowerPlanDtoRequest extends OpenApiRequest<DeviceServiceFindDevicePowerPlanDtoParam, DeviceServiceFindDevicePowerPlanDtoResult> {

    public DeviceServiceFindDevicePowerPlanDtoRequest(DeviceServiceFindDevicePowerPlanDtoParam param) {
        this();
        setBizModel(param);
    }

    public DeviceServiceFindDevicePowerPlanDtoRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/device-service/find-device-power-plan-dto");
        setHttpMethod("POST");
    }

    public Class<DeviceServiceFindDevicePowerPlanDtoResult> getResponseClass() {
        return DeviceServiceFindDevicePowerPlanDtoResult.class;
    }

    public Class<DeviceServiceFindDevicePowerPlanDtoParam> getDomainClass() {
        return DeviceServiceFindDevicePowerPlanDtoParam.class;
    }
}

