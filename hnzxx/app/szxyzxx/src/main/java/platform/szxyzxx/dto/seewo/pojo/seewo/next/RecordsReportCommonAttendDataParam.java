package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 考勤数据上报
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class RecordsReportCommonAttendDataParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static RecordsReportCommonAttendDataParamBuilder builder(){
        return new RecordsReportCommonAttendDataParamBuilder();
    }

    public static class RecordsReportCommonAttendDataParamBuilder{
        private RequestBody requestBody;

        public RecordsReportCommonAttendDataParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public RecordsReportCommonAttendDataParam build(){
            RecordsReportCommonAttendDataParam param = new RecordsReportCommonAttendDataParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 
         */
        private Query attendData;

        public Query getAttendData() {
            return this.attendData;
        }

        public void setAttendData(Query attendData) {
            this.attendData = attendData;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Query attendData;

            public RequestBodyBuilder attendData(Query attendData){
                this.attendData = attendData;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setAttendData(attendData);
                return param;
            }
        }
    }

    public static class Query {
        /**
         * 数据来源厂商简称，如seewo
         */
        private String company;
        /**
         * 链路追踪UUID，便于调试与问题定位
         */
        private String traceId;
        /**
         * appId
         */
        private String appId;
        /**
         * 希沃学校UID
         */
        private String schoolUid;
        /**
         * 是否通知把班牌。0不通知班牌（默认），1通知用户所在行政班级的班牌，2通知场地roomNum绑定的班牌
         */
        private Integer notifyBp;
        /**
         * 考勤数据，最多20条
         */
        private List<DataItem> data;

        public String getCompany() {
            return this.company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getTraceId() {
            return this.traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public Integer getNotifyBp() {
            return this.notifyBp;
        }

        public void setNotifyBp(Integer notifyBp) {
            this.notifyBp = notifyBp;
        }

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String company;
            private String traceId;
            private String appId;
            private String schoolUid;
            private Integer notifyBp;
            private List<DataItem> data;

            public QueryBuilder company(String company){
                this.company = company;
                return this;
            }
            public QueryBuilder traceId(String traceId){
                this.traceId = traceId;
                return this;
            }
            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder notifyBp(Integer notifyBp){
                this.notifyBp = notifyBp;
                return this;
            }
            public QueryBuilder data(List<DataItem> data){
                this.data = data;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setCompany(company);
                param.setTraceId(traceId);
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setNotifyBp(notifyBp);
                param.setData(data);
                return param;
            }
        }
    }

    public static class DataItem {
        /**
         * 签到时间戳 13位
         */
        private Long signTimestamp;
        /**
         * 用户标识
         */
        private String userCode;
        /**
         * 用户标识类型。 0：学生卡号(默认)，1：16进制卡号，2：学生学号，3：希沃用户UID，4：第三方系统ID
         */
        private Integer userCodeType;
        /**
         * 用户类型，0：学生(默认)，1：老师
         */
        private Integer userType;
        /**
         * 场地标识
         */
        private String roomNum;
        /**
         * 场地标识类型，0：第三方ID（默认），1：希沃场地UID，2：房间编号，3：行政班级ID
         */
        private Integer roomNumType;
        /**
         * 进出类型，0：进入（默认），1：离开
         */
        private Integer inoutType;

        public Long getSignTimestamp() {
            return this.signTimestamp;
        }

        public void setSignTimestamp(Long signTimestamp) {
            this.signTimestamp = signTimestamp;
        }

        public String getUserCode() {
            return this.userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public Integer getUserCodeType() {
            return this.userCodeType;
        }

        public void setUserCodeType(Integer userCodeType) {
            this.userCodeType = userCodeType;
        }

        public Integer getUserType() {
            return this.userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }

        public String getRoomNum() {
            return this.roomNum;
        }

        public void setRoomNum(String roomNum) {
            this.roomNum = roomNum;
        }

        public Integer getRoomNumType() {
            return this.roomNumType;
        }

        public void setRoomNumType(Integer roomNumType) {
            this.roomNumType = roomNumType;
        }

        public Integer getInoutType() {
            return this.inoutType;
        }

        public void setInoutType(Integer inoutType) {
            this.inoutType = inoutType;
        }


        public static DataItemBuilder builder(){
            return new DataItemBuilder();
        }

        public static class DataItemBuilder{
            private Long signTimestamp;
            private String userCode;
            private Integer userCodeType;
            private Integer userType;
            private String roomNum;
            private Integer roomNumType;
            private Integer inoutType;

            public DataItemBuilder signTimestamp(Long signTimestamp){
                this.signTimestamp = signTimestamp;
                return this;
            }
            public DataItemBuilder userCode(String userCode){
                this.userCode = userCode;
                return this;
            }
            public DataItemBuilder userCodeType(Integer userCodeType){
                this.userCodeType = userCodeType;
                return this;
            }
            public DataItemBuilder userType(Integer userType){
                this.userType = userType;
                return this;
            }
            public DataItemBuilder roomNum(String roomNum){
                this.roomNum = roomNum;
                return this;
            }
            public DataItemBuilder roomNumType(Integer roomNumType){
                this.roomNumType = roomNumType;
                return this;
            }
            public DataItemBuilder inoutType(Integer inoutType){
                this.inoutType = inoutType;
                return this;
            }

            public DataItem build(){
                DataItem param = new DataItem();
                param.setSignTimestamp(signTimestamp);
                param.setUserCode(userCode);
                param.setUserCodeType(userCodeType);
                param.setUserType(userType);
                param.setRoomNum(roomNum);
                param.setRoomNumType(roomNumType);
                param.setInoutType(inoutType);
                return param;
            }
        }
    }


}
