package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【事件|课程】分页查询学校考勤规则信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceRuleListSchoolAttendRuleParam extends OpenApiParam {


    /**
     * 响应体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static AttendanceRuleListSchoolAttendRuleParamBuilder builder(){
        return new AttendanceRuleListSchoolAttendRuleParamBuilder();
    }

    public static class AttendanceRuleListSchoolAttendRuleParamBuilder{
        private RequestBody requestBody;

        public AttendanceRuleListSchoolAttendRuleParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceRuleListSchoolAttendRuleParam build(){
            AttendanceRuleListSchoolAttendRuleParam param = new AttendanceRuleListSchoolAttendRuleParam();
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
         * 学校uid
         */
        private String schoolUid;
        /**
         * 考勤类型
1-事件（默认），2-课程
         */
        private Integer attendType;
        /**
         * 班级Uid
         */
        private String classUid;
        /**
         * 年级序号
年级序号+班级序号唯一确定班级信息（几年级几班）
年级序号+班级序号 与 班级Uid只取一个，以班级Uid为准
         */
        private Integer grade;
        /**
         * 班级序号
年级序号+班级序号唯一确定班级信息（几年级几班）
年级序号+班级序号 与 班级Uid只取一个，以班级Uid为准
         */
        private Integer clazz;
        /**
         * 场地标识
         */
        private String roomNum;
        /**
         * 场地标识类型
0 - 第三方ID，1 - 希沃场地ID（默认），2 - 房间编号，3 - 行政班ID
         */
        private Integer roomNumType;
        /**
         * 页号，默认1
         */
        private Integer page;
        /**
         * 每页大小，默认20
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

        public Integer getAttendType() {
            return this.attendType;
        }

        public void setAttendType(Integer attendType) {
            this.attendType = attendType;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
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
            private Integer attendType;
            private String classUid;
            private Integer grade;
            private Integer clazz;
            private String roomNum;
            private Integer roomNumType;
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
            public QueryBuilder attendType(Integer attendType){
                this.attendType = attendType;
                return this;
            }
            public QueryBuilder classUid(String classUid){
                this.classUid = classUid;
                return this;
            }
            public QueryBuilder grade(Integer grade){
                this.grade = grade;
                return this;
            }
            public QueryBuilder clazz(Integer clazz){
                this.clazz = clazz;
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
                param.setAttendType(attendType);
                param.setClassUid(classUid);
                param.setGrade(grade);
                param.setClazz(clazz);
                param.setRoomNum(roomNum);
                param.setRoomNumType(roomNumType);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
