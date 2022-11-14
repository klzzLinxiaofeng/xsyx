package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【课程考勤】根据条件分页查询考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListLessonRecordsConditionalParam extends OpenApiParam {


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

    public static AttendanceServiceListLessonRecordsConditionalParamBuilder builder(){
        return new AttendanceServiceListLessonRecordsConditionalParamBuilder();
    }

    public static class AttendanceServiceListLessonRecordsConditionalParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListLessonRecordsConditionalParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListLessonRecordsConditionalParam build(){
            AttendanceServiceListLessonRecordsConditionalParam param = new AttendanceServiceListLessonRecordsConditionalParam();
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
         * 日期 yyyy-MM-dd
         */
        private String date;
        /**
         * 场地标识
         */
        private String roomNum;
        /**
         * 场地标识类型：1-希沃场地ID（默认），2-房间编号，3-行政班级ID
         */
        private Integer roomNumType;
        /**
         * 课程节次 1-N
         */
        private Integer lessonIdx;
        /**
         * 从何时开始 HH:mm:ss（只查询该时间及之后的数据）
         */
        private String lastTime;
        /**
         * page
         */
        private Integer page;
        /**
         * pageSize
         */
        private Integer pageSize;

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

        public Integer getLessonIdx() {
            return this.lessonIdx;
        }

        public void setLessonIdx(Integer lessonIdx) {
            this.lessonIdx = lessonIdx;
        }

        public String getLastTime() {
            return this.lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String appId;
            private String schoolUid;
            private String date;
            private String roomNum;
            private Integer roomNumType;
            private Integer lessonIdx;
            private String lastTime;
            private Integer page;
            private Integer pageSize;

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
            public QueryBuilder roomNum(String roomNum){
                this.roomNum = roomNum;
                return this;
            }
            public QueryBuilder roomNumType(Integer roomNumType){
                this.roomNumType = roomNumType;
                return this;
            }
            public QueryBuilder lessonIdx(Integer lessonIdx){
                this.lessonIdx = lessonIdx;
                return this;
            }
            public QueryBuilder lastTime(String lastTime){
                this.lastTime = lastTime;
                return this;
            }
            public QueryBuilder page(Integer page){
                this.page = page;
                return this;
            }
            public QueryBuilder pageSize(Integer pageSize){
                this.pageSize = pageSize;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setAppId(appId);
                param.setSchoolUid(schoolUid);
                param.setDate(date);
                param.setRoomNum(roomNum);
                param.setRoomNumType(roomNumType);
                param.setLessonIdx(lessonIdx);
                param.setLastTime(lastTime);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
