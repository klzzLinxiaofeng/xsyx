package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 获取学校生效的排课计划
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class TimetablePlanFindEnablePlanInSchoolResult extends OpenApiResult {

    public TimetablePlanFindEnablePlanInSchoolResult(HttpResponse response) {
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
         * 标识
         */
        private String uid;
        /**
         * 学校id
         */
        private String schoolUid;
        /**
         * 学期标题，如 第一学期，冬季学期
         */
        private String planName;
        /**
         * 是否启用
         */
        private Boolean enabled;
        /**
         * 学年，如 2018-2019学年
         */
        private String yearName;
        /**
         * 学期名
         */
        private String termName;
        /**
         * 第几学期
         */
        private Integer termIndex;
        /**
         * 起始天
         */
        private String startDay;
        /**
         * 结束天
         */
        private String endDay;
        /**
         * 起始学年 2017
         */
        private Integer startYear;
        /**
         * 结束学年 2018
         */
        private Integer endYear;
        /**
         * 修改时间
         */
        private Long updateTime;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
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

        public String getYearName() {
            return this.yearName;
        }

        public void setYearName(String yearName) {
            this.yearName = yearName;
        }

        public String getTermName() {
            return this.termName;
        }

        public void setTermName(String termName) {
            this.termName = termName;
        }

        public Integer getTermIndex() {
            return this.termIndex;
        }

        public void setTermIndex(Integer termIndex) {
            this.termIndex = termIndex;
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

        public Integer getStartYear() {
            return this.startYear;
        }

        public void setStartYear(Integer startYear) {
            this.startYear = startYear;
        }

        public Integer getEndYear() {
            return this.endYear;
        }

        public void setEndYear(Integer endYear) {
            this.endYear = endYear;
        }

        public Long getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

    }


}

