package platform.szxyzxx.dto.seewo.pojo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 查询指定条件的场地列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class BuildingRoomServiceListRoomConditionResult extends OpenApiResult {

    public BuildingRoomServiceListRoomConditionResult(HttpResponse response) {
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
        private List<List<BaseResponseThirdRoomDto>> result;

        public List<List<BaseResponseThirdRoomDto>> getResult() {
            return this.result;
        }

        public void setResult(List<List<BaseResponseThirdRoomDto>> result) {
            this.result = result;
        }

    }

    public static class BaseResponseThirdRoomDto {
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
        private List<ThirdRoomDto> data;

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

        public List<ThirdRoomDto> getData() {
            return this.data;
        }

        public void setData(List<ThirdRoomDto> data) {
            this.data = data;
        }

    }

    public static class ThirdRoomDto {
        /**
         *  场地uid
         */
        private String roomUid;
        /**
         *  场地名称
         */
        private String roomName;
        /**
         *  场地编号
         */
        private String roomNum;
        /**
         *  座位数
         */
        private Integer seatCount;
        /**
         *  建筑uid
         */
        private String buildingUid;
        /**
         *  建筑名称
         */
        private String buildingName;
        /**
         *  学校uid
         */
        private String schoolUid;
        /**
         *  楼层数
         */
        private Integer floorNum;
        /**
         *  场地类型
         */
        private String roomType;
        /**
         *  场地面积
         */
        private Integer roomArea;
        /**
         *  场地管理员uid列表
         */
        private List<String> administratorUids;

        public String getRoomUid() {
            return this.roomUid;
        }

        public void setRoomUid(String roomUid) {
            this.roomUid = roomUid;
        }

        public String getRoomName() {
            return this.roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getRoomNum() {
            return this.roomNum;
        }

        public void setRoomNum(String roomNum) {
            this.roomNum = roomNum;
        }

        public Integer getSeatCount() {
            return this.seatCount;
        }

        public void setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
        }

        public String getBuildingUid() {
            return this.buildingUid;
        }

        public void setBuildingUid(String buildingUid) {
            this.buildingUid = buildingUid;
        }

        public String getBuildingName() {
            return this.buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public Integer getFloorNum() {
            return this.floorNum;
        }

        public void setFloorNum(Integer floorNum) {
            this.floorNum = floorNum;
        }

        public String getRoomType() {
            return this.roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public Integer getRoomArea() {
            return this.roomArea;
        }

        public void setRoomArea(Integer roomArea) {
            this.roomArea = roomArea;
        }

        public List<String> getAdministratorUids() {
            return this.administratorUids;
        }

        public void setAdministratorUids(List<String> administratorUids) {
            this.administratorUids = administratorUids;
        }

    }


}

