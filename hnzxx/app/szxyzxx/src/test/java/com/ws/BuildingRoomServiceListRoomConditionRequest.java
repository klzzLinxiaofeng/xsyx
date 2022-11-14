package com.ws;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 查询指定条件的场地列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class BuildingRoomServiceListRoomConditionRequest extends OpenApiRequest<BuildingRoomServiceListRoomConditionParam, BuildingRoomServiceListRoomConditionResult> {

    public BuildingRoomServiceListRoomConditionRequest(BuildingRoomServiceListRoomConditionParam param) {
        this();
        setBizModel(param);
    }

    public BuildingRoomServiceListRoomConditionRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/seewo-yunban-api/building-room-service/list-room-condition");
        setHttpMethod("POST");
    }

    public Class<BuildingRoomServiceListRoomConditionResult> getResponseClass() {
        return BuildingRoomServiceListRoomConditionResult.class;
    }

    public Class<BuildingRoomServiceListRoomConditionParam> getDomainClass() {
        return BuildingRoomServiceListRoomConditionParam.class;
    }
}

