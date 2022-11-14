package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据学校uid查询排课计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TimetableServiceListPlanResult extends OpenApiResult {

    public TimetableServiceListPlanResult(HttpResponse response) {
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
         * 业务状态码 
         */
        private String code;
        /**
         * data
         */
        private Data data;
        /**
         * message
         */
        private String message;

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Data getData() {
            return this.data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static class Data {
        /**
         * result
         */
        private List<ResultItem> result;
        /**
         * 每页个数
         */
        private Integer pageSize;
        /**
         * 当前页 
         */
        private Integer page;
        /**
         * 总数量
         */
        private Integer totalCount;

        public List<ResultItem> getResult() {
            return this.result;
        }

        public void setResult(List<ResultItem> result) {
            this.result = result;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPage() {
            return this.page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

    }

    public static class ResultItem {
        /**
         * 排课计划uid
         */
        private String uid;
        /**
         * 开始时间
         */
        private String startDay;
        /**
         * 结束时间
         */
        private String endDay;
        /**
         * 排课计划名称
         */
        private String planName;
        /**
         * 是否启用（true 启用 false 未启用）
         */
        private Boolean enabled;
        /**
         * 学校uid
         */
        private String schoolUid;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getStartDay() {
            return this.startDay;
        }

        public void setStartDay(String startDay) {
            this.startDay = startDay;
        }

        public String getEndDay() {
            return this.endDay;
        }

        public void setEndDay(String endDay) {
            this.endDay = endDay;
        }

        public String getPlanName() {
            return this.planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public Boolean getEnabled() {
            return this.enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

    }


}

