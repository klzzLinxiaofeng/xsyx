package platform.szxyzxx.dto.seewo.pojo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 查询指定条件的场地列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class BuildingRoomServiceListRoomConditionParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private JSONRequestBody requestBody;


    public JSONRequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(JSONRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static BuildingRoomServiceListRoomConditionParamBuilder builder(){
        return new BuildingRoomServiceListRoomConditionParamBuilder();
    }

    public static class BuildingRoomServiceListRoomConditionParamBuilder{
        private JSONRequestBody requestBody;

        public BuildingRoomServiceListRoomConditionParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public BuildingRoomServiceListRoomConditionParam build(){
            BuildingRoomServiceListRoomConditionParam param = new BuildingRoomServiceListRoomConditionParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 
         */
        private ThirdListRoomQuery query;

        public ThirdListRoomQuery getQuery() {
            return this.query;
        }

        public void setQuery(ThirdListRoomQuery query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private ThirdListRoomQuery query;

            public JSONRequestBodyBuilder query(ThirdListRoomQuery query){
                this.query = query;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class ThirdListRoomQuery {
        /**
         * 
         */
        private String appId;
        /**
         *  希沃机构uid
         */
        private String unitUid;
        /**
         *  建筑UID
         */
        private String buildingUid;
        /**
         *  场地UID
         */
        private String roomUid;
        /**
         *  楼层号
         */
        private Integer floorNum;
        /**
         *  场地名称
         */
        private String name;
        /**
         *  场地号
         */
        private String roomNum;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
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

        public String getRoomUid() {
            return this.roomUid;
        }

        public void setRoomUid(String roomUid) {
            this.roomUid = roomUid;
        }

        public Integer getFloorNum() {
            return this.floorNum;
        }

        public void setFloorNum(Integer floorNum) {
            this.floorNum = floorNum;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoomNum() {
            return this.roomNum;
        }

        public void setRoomNum(String roomNum) {
            this.roomNum = roomNum;
        }


        public static ThirdListRoomQueryBuilder builder(){
            return new ThirdListRoomQueryBuilder();
        }

        public static class ThirdListRoomQueryBuilder{
            private String appId;
            private String unitUid;
            private String buildingUid;
            private String roomUid;
            private Integer floorNum;
            private String name;
            private String roomNum;

            public ThirdListRoomQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public ThirdListRoomQueryBuilder unitUid(String unitUid){
                this.unitUid = unitUid;
                return this;
            }
            public ThirdListRoomQueryBuilder buildingUid(String buildingUid){
                this.buildingUid = buildingUid;
                return this;
            }
            public ThirdListRoomQueryBuilder roomUid(String roomUid){
                this.roomUid = roomUid;
                return this;
            }
            public ThirdListRoomQueryBuilder floorNum(Integer floorNum){
                this.floorNum = floorNum;
                return this;
            }
            public ThirdListRoomQueryBuilder name(String name){
                this.name = name;
                return this;
            }
            public ThirdListRoomQueryBuilder roomNum(String roomNum){
                this.roomNum = roomNum;
                return this;
            }

            public ThirdListRoomQuery build(){
                ThirdListRoomQuery param = new ThirdListRoomQuery();
                param.setAppId(appId);
                param.setUnitUid(unitUid);
                param.setBuildingUid(buildingUid);
                param.setRoomUid(roomUid);
                param.setFloorNum(floorNum);
                param.setName(name);
                param.setRoomNum(roomNum);
                return param;
            }
        }
    }


}
