package com.ws;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 条件查询班级场地绑定列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class ClassRoomServiceListClassRoomRelConditionalResult extends OpenApiResult {

    public ClassRoomServiceListClassRoomRelConditionalResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private JSONResponseBody responseBody;


    public JSONResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(JSONResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class JSONResponseBody {
        /**
         * 
         */
        private List<BaseResponseThirdClassRoomRelDto> result;

        public List<BaseResponseThirdClassRoomRelDto> getResult() {
            return this.result;
        }

        public void setResult(List<BaseResponseThirdClassRoomRelDto> result) {
            this.result = result;
        }

    }

    public static class BaseResponseThirdClassRoomRelDto {
        /**
         * 
         */
        private String code;
        /**
         * 
         */
        private String message;
        /**
         * 
         */
        private List<ThirdClassRoomRelDto> data;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ThirdClassRoomRelDto> getData() {
            return this.data;
        }

        public void setData(List<ThirdClassRoomRelDto> data) {
            this.data = data;
        }

    }

    public static class ThirdClassRoomRelDto {
        /**
         *  关系Uid
         */
        private String uid;
        /**
         * 希沃学校Uid
         */
        private String unitUid;
        /**
         *  建筑Uid
         */
        private String buildingUid;
        /**
         *  班级Uid
         */
        private String classUid;
        /**
         *  场地Uid
         */
        private String buildingRoomUid;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUnitUid() {
            return this.unitUid;
        }

        public void setUnitUid(String unitUid) {
            this.unitUid = unitUid;
        }

        public String getBuildingUid() {
            return this.buildingUid;
        }

        public void setBuildingUid(String buildingUid) {
            this.buildingUid = buildingUid;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getBuildingRoomUid() {
            return this.buildingRoomUid;
        }

        public void setBuildingRoomUid(String buildingRoomUid) {
            this.buildingRoomUid = buildingRoomUid;
        }

    }


}

