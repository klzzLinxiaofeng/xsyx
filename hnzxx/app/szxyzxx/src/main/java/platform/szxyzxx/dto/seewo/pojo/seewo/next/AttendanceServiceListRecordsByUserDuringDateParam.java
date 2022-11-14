package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 【事件|课程】根据用户查询指定日期范围的考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class AttendanceServiceListRecordsByUserDuringDateParam extends OpenApiParam {


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

    public static AttendanceServiceListRecordsByUserDuringDateParamBuilder builder(){
        return new AttendanceServiceListRecordsByUserDuringDateParamBuilder();
    }

    public static class AttendanceServiceListRecordsByUserDuringDateParamBuilder{
        private RequestBody requestBody;

        public AttendanceServiceListRecordsByUserDuringDateParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public AttendanceServiceListRecordsByUserDuringDateParam build(){
            AttendanceServiceListRecordsByUserDuringDateParam param = new AttendanceServiceListRecordsByUserDuringDateParam();
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
         * 用户身份标识
         */
        private String userCode;
        /**
         * 用户标识类型
0-10进制卡号（默认），1-16进制卡号，2-学生学号，3-希沃用户ID，4-第三方同步的ID
         */
        private Integer userCodeType;
        /**
         * 考勤类型
1-事件 2-课程
         */
        private Integer attendType;
        /**
         * 开始日期
格式: yyyy-MM-dd
注意: 日期范围不得超过30天
         */
        private String startDate;
        /**
         * 结束如期 
格式: yyyy-MM-dd
注意: 日期范围不得超过30天
         */
        private String endDate;
        /**
         * 页号 默认1
         */
        private Integer page;
        /**
         * 每页大小 默认20
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

        public Integer getAttendType() {
            return this.attendType;
        }

        public void setAttendType(Integer attendType) {
            this.attendType = attendType;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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
            private String userCode;
            private Integer userCodeType;
            private Integer attendType;
            private String startDate;
            private String endDate;
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
            public QueryBuilder userCode(String userCode){
                this.userCode = userCode;
                return this;
            }
            public QueryBuilder userCodeType(Integer userCodeType){
                this.userCodeType = userCodeType;
                return this;
            }
            public QueryBuilder attendType(Integer attendType){
                this.attendType = attendType;
                return this;
            }
            public QueryBuilder startDate(String startDate){
                this.startDate = startDate;
                return this;
            }
            public QueryBuilder endDate(String endDate){
                this.endDate = endDate;
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
                param.setUserCode(userCode);
                param.setUserCodeType(userCodeType);
                param.setAttendType(attendType);
                param.setStartDate(startDate);
                param.setEndDate(endDate);
                param.setPage(page);
                param.setPageSize(pageSize);
                return param;
            }
        }
    }


}
