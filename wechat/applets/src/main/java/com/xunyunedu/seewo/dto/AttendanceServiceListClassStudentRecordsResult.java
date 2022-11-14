package com.xunyunedu.seewo.dto;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 【事件|课程】根据班级查询学生考勤记录
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-2-19
 */
public class AttendanceServiceListClassStudentRecordsResult extends OpenApiResult {

    public AttendanceServiceListClassStudentRecordsResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private ResponseBody responseBody;


    public ResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class ResponseBody {
        /**
         * code
         */
        private String code;
        /**
         * message
         */
        private String message;
        /**
         * data
         */
        private Data data;

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

        public Data getData() {
            return this.data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }

    public static class Data {
        /**
         * 页号
         */
        private Integer page;
        /**
         * 每页大小
         */
        private Integer pageSize;
        /**
         * 数据总量
         */
        private Integer totalCount;
        /**
         * 查询考勤结果集
         */
        private List<ResultItem> result;

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

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<ResultItem> getResult() {
            return this.result;
        }

        public void setResult(List<ResultItem> result) {
            this.result = result;
        }

    }

    public static class ResultItem {
        /**
         * 规则id
         */
        private String eventId;
        /**
         * 用户uid
         */
        private String userUid;
        /**
         * 用户名
         */
        private String userName;
        /**
         * 学生学号
         */
        private String studentCode;
        /**
         * 签到时间
         */
        private String attendTime;
        /**
         * 个人考勤结果： 0正常，1迟到，3缺勤，6请假
         */
        private Integer status;

        public String getEventId() {
            return this.eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getAttendTime() {
            return this.attendTime;
        }

        public void setAttendTime(String attendTime) {
            this.attendTime = attendTime;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }


}

