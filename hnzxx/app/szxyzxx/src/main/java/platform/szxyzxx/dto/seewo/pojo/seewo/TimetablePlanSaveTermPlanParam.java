package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 根据名称保存或更新学期排课
 * 保存或更新排课计划
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetablePlanSaveTermPlanParam extends OpenApiParam {


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

    public static TimetablePlanSaveTermPlanParamBuilder builder(){
        return new TimetablePlanSaveTermPlanParamBuilder();
    }

    public static class TimetablePlanSaveTermPlanParamBuilder{
        private JSONRequestBody requestBody;

        public TimetablePlanSaveTermPlanParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TimetablePlanSaveTermPlanParam build(){
            TimetablePlanSaveTermPlanParam param = new TimetablePlanSaveTermPlanParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 排课计划入参
         */
        private TermPlanSaveReq saveReq;

        public TermPlanSaveReq getSaveReq() {
            return this.saveReq;
        }

        public void setSaveReq(TermPlanSaveReq saveReq) {
            this.saveReq = saveReq;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private TermPlanSaveReq saveReq;

            public JSONRequestBodyBuilder saveReq(TermPlanSaveReq saveReq){
                this.saveReq = saveReq;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setSaveReq(saveReq);
                return param;
            }
        }
    }

    public static class TermPlanSaveReq {
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 排课计划名，如 第一学期，冬季学期
         */
        private String planName;
        /**
         * 学年，如 2018-2019学年
         */
        private String yearName;
        /**
         * 起始学年 2017
         */
        private Integer startYear;
        /**
         * 结束学年 2018
         */
        private Integer endYear;
        /**
         * 教学周开始日期 如：2019-03-04 必须为周一
         */
        private String startDay;
        /**
         * 教学周总数 如：18
         */
        private Integer weekCount;
        /**
         * 学期名
         */
        private String termName;
        /**
         * 第几学期
         */
        private Integer termIndex;
        /**
         * 每天课节数
         */
        private Integer  dayLessons;
        /**
         * 每周天数
         */
        private Integer weekDays;

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

        public String getYearName() {
            return this.yearName;
        }

        public void setYearName(String yearName) {
            this.yearName = yearName;
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

        public String getStartDay() {
            return this.startDay;
        }

        public void setStartDay(String startDay) {
            this.startDay = startDay;
        }

        public Integer getWeekCount() {
            return this.weekCount;
        }

        public void setWeekCount(Integer weekCount) {
            this.weekCount = weekCount;
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

        public Integer getDayLessons() {
            return this. dayLessons;
        }

        public void setDayLessons(Integer  dayLessons) {
            this. dayLessons =  dayLessons;
        }

        public Integer getWeekDays() {
            return this.weekDays;
        }

        public void setWeekDays(Integer weekDays) {
            this.weekDays = weekDays;
        }


        public static TermPlanSaveReqBuilder builder(){
            return new TermPlanSaveReqBuilder();
        }

        public static class TermPlanSaveReqBuilder{
            private String schoolUid;
            private String planName;
            private String yearName;
            private Integer startYear;
            private Integer endYear;
            private String startDay;
            private Integer weekCount;
            private String termName;
            private Integer termIndex;
            private Integer  dayLessons;
            private Integer weekDays;

            public TermPlanSaveReqBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public TermPlanSaveReqBuilder planName(String planName){
                this.planName = planName;
                return this;
            }
            public TermPlanSaveReqBuilder yearName(String yearName){
                this.yearName = yearName;
                return this;
            }
            public TermPlanSaveReqBuilder startYear(Integer startYear){
                this.startYear = startYear;
                return this;
            }
            public TermPlanSaveReqBuilder endYear(Integer endYear){
                this.endYear = endYear;
                return this;
            }
            public TermPlanSaveReqBuilder startDay(String startDay){
                this.startDay = startDay;
                return this;
            }
            public TermPlanSaveReqBuilder weekCount(Integer weekCount){
                this.weekCount = weekCount;
                return this;
            }
            public TermPlanSaveReqBuilder termName(String termName){
                this.termName = termName;
                return this;
            }
            public TermPlanSaveReqBuilder termIndex(Integer termIndex){
                this.termIndex = termIndex;
                return this;
            }
            public TermPlanSaveReqBuilder  dayLessons(Integer  dayLessons){
                this. dayLessons =  dayLessons;
                return this;
            }
            public TermPlanSaveReqBuilder weekDays(Integer weekDays){
                this.weekDays = weekDays;
                return this;
            }

            public TermPlanSaveReq build(){
                TermPlanSaveReq param = new TermPlanSaveReq();
                param.setSchoolUid(schoolUid);
                param.setPlanName(planName);
                param.setYearName(yearName);
                param.setStartYear(startYear);
                param.setEndYear(endYear);
                param.setStartDay(startDay);
                param.setWeekCount(weekCount);
                param.setTermName(termName);
                param.setTermIndex(termIndex);
                param.setDayLessons( dayLessons);
                param.setWeekDays(weekDays);
                return param;
            }
        }
    }


}
