package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 更新课程考勤用户考勤状态
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class AttendanceServiceUpdateLessonRecordStatusParam extends OpenApiParam {


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

    public static AttendanceServiceUpdateLessonRecordStatusParamBuilder builder(){
        return new AttendanceServiceUpdateLessonRecordStatusParamBuilder();
    }

    public static class AttendanceServiceUpdateLessonRecordStatusParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceUpdateLessonRecordStatusParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceUpdateLessonRecordStatusParam build(){
            AttendanceServiceUpdateLessonRecordStatusParam param = new AttendanceServiceUpdateLessonRecordStatusParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private Query query;

        public Query getQuery() {
            return this.query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Query query;

            public RequestBodyBuilder query(Query query){
                this.query = query;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class Query {
        /**
         * appId
         */
        private String appId;
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 日期yyyy-MM-dd
         */
        private String date;
        /**
         * 课程节次1-N
         */
        private Integer lessonIdx;
        /**
         * 场地标识
         */
        private String roomNum;
        /**
         * 场地标识类型；0：第三方ID，1：希沃场地ID（默认），2：房间编号，3：行政班级ID
         */
        private Integer roomNumType;
        /**
         * 用户类型：student, teacher
         */
        private String userType;
        /**
         * userStatus
         */
        private List<UserStatusItem> userStatus;

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

        public String getDate() {
            return this.date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getLessonIdx() {
            return this.lessonIdx;
        }

        public void setLessonIdx(Integer lessonIdx) {
            this.lessonIdx = lessonIdx;
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

        public String getUserType() {
            return this.userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public List<UserStatusItem> getUserStatus() {
            return this.userStatus;
        }

        public void setUserStatus(List<UserStatusItem> userStatus) {
            this.userStatus = userStatus;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String date;
            private Integer lessonIdx;
            private String roomNum;
            private Integer roomNumType;
            private String userType;
            private List<UserStatusItem> userStatus;

            public QueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder date(String date){
                this.date = date;
                return this;
            }
            public QueryBuilder lessonIdx(Integer lessonIdx){
                this.lessonIdx = lessonIdx;
                return this;
            }
            public QueryBuilder roomNum(String roomNum){
                this.roomNum = roomNum;
                return this;
            }
            public QueryBuilder roomNumType(Integer roomNumType){
                this.roomNumType = roomNumType;
                return this;
            }
            public QueryBuilder userType(String userType){
                this.userType = userType;
                return this;
            }
            public QueryBuilder userStatus(List<UserStatusItem> userStatus){
                this.userStatus = userStatus;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setDate(date);
                param.setLessonIdx(lessonIdx);
                param.setRoomNum(roomNum);
                param.setRoomNumType(roomNumType);
                param.setUserType(userType);
                param.setUserStatus(userStatus);
                return param;
            }
        }
    }

    public static class UserStatusItem {
        /**
         * 用户标识 
         */
        private String userCode;
        /**
         * 用户标识类型：0卡号，1十六进制卡号，2学号，3希沃用户ID
         */
        private Integer userCodeType;
        /**
         * 考勤状态 0正常，1迟到，3缺卡，6请假
         */
        private Integer status;

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

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }


        public static UserStatusItemBuilder builder(){
            return new UserStatusItemBuilder();
        }

        public static class UserStatusItemBuilder{
            private String userCode;
            private Integer userCodeType;
            private Integer status;

            public UserStatusItemBuilder userCode(String userCode){
                this.userCode = userCode;
                return this;
            }
            public UserStatusItemBuilder userCodeType(Integer userCodeType){
                this.userCodeType = userCodeType;
                return this;
            }
            public UserStatusItemBuilder status(Integer status){
                this.status = status;
                return this;
            }

            public UserStatusItem build(){
                UserStatusItem param = new UserStatusItem();
                param.setUserCode(userCode);
                param.setUserCodeType(userCodeType);
                param.setStatus(status);
                return param;
            }
        }
    }


}
