package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 清除设备应用的开关机计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class DeviceServiceRemoveDeviceApplyPowerPlanRequest extends OpenApiRequest<DeviceServiceRemoveDeviceApplyPowerPlanParam, DeviceServiceRemoveDeviceApplyPowerPlanResult> {

    public DeviceServiceRemoveDeviceApplyPowerPlanRequest(DeviceServiceRemoveDeviceApplyPowerPlanParam param) {
        this();
        setBizModel(param);
    }

    public DeviceServiceRemoveDeviceApplyPowerPlanRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/device-service/remove-device-apply-power-plan");
        setHttpMethod("POST");
    }

    public Class<DeviceServiceRemoveDeviceApplyPowerPlanResult> getResponseClass() {
        return DeviceServiceRemoveDeviceApplyPowerPlanResult.class;
    }

    public Class<DeviceServiceRemoveDeviceApplyPowerPlanParam> getDomainClass() {
        return DeviceServiceRemoveDeviceApplyPowerPlanParam.class;
    }
}

