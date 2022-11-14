package platform.szxyzxx.dto.seewo.pojo;

import com.seewo.open.sdk.OpenApiParam;

import java.util.List;

/**
 * seewo-open API: 保存场地课表（新）
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class TimetableServiceSaveRoomTimetableParam extends OpenApiParam {


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

    public static TimetableServiceSaveRoomTimetableParamBuilder builder(){
        return new TimetableServiceSaveRoomTimetableParamBuilder();
    }

    public static class TimetableServiceSaveRoomTimetableParamBuilder{
        private RequestBody requestBody;

        public TimetableServiceSaveRoomTimetableParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public TimetableServiceSaveRoomTimetableParam build(){
            TimetableServiceSaveRoomTimetableParam param = new TimetableServiceSaveRoomTimetableParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 请求内容
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
         * 场地编号
         */
        private String roomNum;
        /**
         * 课表项列表，不超过200条，每次将覆盖上一次的场地课表
         */
        private List<TimetableItemsItem> timetableItems;
        /**
         * 组织ID、学校ID
         */
        private String unitUid;
        /**
         * 排课计划ID，不传默认当前使用的计划（前提需要存在）
         */
        private String planUid;
        /**
         * 教室场地名，多个建筑下重名的会报错
         */
        private String roomName;

        public String getRoomNum() {
            return this.roomNum;
        }

        public void setRoomNum(String roomNum) {
            this.roomNum = roomNum;
        }

        public List<TimetableItemsItem> getTimetableItems() {
            return this.timetableItems;
        }

        public void setTimetableItems(List<TimetableItemsItem> timetableItems) {
            this.timetableItems = timetableItems;
        }

        public String getUnitUid() {
            return this.unitUid;
        }

        public void setUnitUid(String unitUid) {
            this.unitUid = unitUid;
        }

        public String getPlanUid() {
            return this.planUid;
        }

        public void setPlanUid(String planUid) {
            this.planUid = planUid;
        }

        public String getRoomName() {
            return this.roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String roomNum;
            private List<TimetableItemsItem> timetableItems;
            private String unitUid;
            private String planUid;
            private String roomName;

            public QueryBuilder roomNum(String roomNum){
                this.roomNum = roomNum;
                return this;
            }
            public QueryBuilder timetableItems(List<TimetableItemsItem> timetableItems){
                this.timetableItems = timetableItems;
                return this;
            }
            public QueryBuilder unitUid(String unitUid){
                this.unitUid = unitUid;
                return this;
            }
            public QueryBuilder planUid(String planUid){
                this.planUid = planUid;
                return this;
            }
            public QueryBuilder roomName(String roomName){
                this.roomName = roomName;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setRoomNum(roomNum);
                param.setTimetableItems(timetableItems);
                param.setUnitUid(unitUid);
                param.setPlanUid(planUid);
                param.setRoomName(roomName);
                return param;
            }
        }

        @Override
        public String toString() {
            return "Query{" +
                    "roomNum='" + roomNum + '\'' +
                    ", timetableItems=" + timetableItems +
                    ", unitUid='" + unitUid + '\'' +
                    ", planUid='" + planUid + '\'' +
                    ", roomName='" + roomName + '\'' +
                    '}';
        }
    }

    public static class TimetableItemsItem {

        /**
         * 当天课节顺序：默认从 0 开始
         */
        private Integer sectionIndex;
        /**
         * 星期几 1-7
         */
        private Integer dayIndex;
        /**
         * 教师名称
         */
        private String teacherName;
        /**
         * 教师手机号
         */
        private String teacherPhone;
        /**
         * 课节开始时间 HH:mm
         */
        private String startTime;
        /**
         * 课节结束时间 HH:mm
         */
        private String endTime;
        /**
         * 【ALL】应用到全部周
【ALNT:1】应用到单周
【ALNT:0】应用到双周
         */
        private String strategy;
        /**
         * 课程名称 （支持中文汉字，建议不要带英文字母）
         */
        private String subjectName;

        public Integer getSectionIndex() {
            return this.sectionIndex;
        }

        public void setSectionIndex(Integer sectionIndex) {
            this.sectionIndex = sectionIndex;
        }

        public Integer getDayIndex() {
            return this.dayIndex;
        }

        public void setDayIndex(Integer dayIndex) {
            this.dayIndex = dayIndex;
        }

        public String getTeacherName() {
            return this.teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherPhone() {
            return this.teacherPhone;
        }

        public void setTeacherPhone(String teacherPhone) {
            this.teacherPhone = teacherPhone;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStrategy() {
            return this.strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public String getSubjectName() {
            return this.subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }


        public static TimetableItemsItemBuilder builder(){
            return new TimetableItemsItemBuilder();
        }

        @Override
        public String toString() {
            return "TimetableItemsItem{" +
                    "sectionIndex=" + sectionIndex +
                    ", dayIndex=" + dayIndex +
                    ", teacherName='" + teacherName + '\'' +
                    ", teacherPhone='" + teacherPhone + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", strategy='" + strategy + '\'' +
                    ", subjectName='" + subjectName + '\'' +
                    '}';
        }
        public static class TimetableItemsItemBuilder{
            private Integer sectionIndex;
            private Integer dayIndex;
            private String teacherName;
            private String teacherPhone;
            private String startTime;
            private String endTime;
            private String strategy;
            private String subjectName;

            public TimetableItemsItemBuilder sectionIndex(Integer sectionIndex){
                this.sectionIndex = sectionIndex;
                return this;
            }
            public TimetableItemsItemBuilder dayIndex(Integer dayIndex){
                this.dayIndex = dayIndex;
                return this;
            }
            public TimetableItemsItemBuilder teacherName(String teacherName){
                this.teacherName = teacherName;
                return this;
            }
            public TimetableItemsItemBuilder teacherPhone(String teacherPhone){
                this.teacherPhone = teacherPhone;
                return this;
            }
            public TimetableItemsItemBuilder startTime(String startTime){
                this.startTime = startTime;
                return this;
            }
            public TimetableItemsItemBuilder endTime(String endTime){
                this.endTime = endTime;
                return this;
            }
            public TimetableItemsItemBuilder strategy(String strategy){
                this.strategy = strategy;
                return this;
            }
            public TimetableItemsItemBuilder subjectName(String subjectName){
                this.subjectName = subjectName;
                return this;
            }

            public TimetableItemsItem build(){
                TimetableItemsItem param = new TimetableItemsItem();
                param.setSectionIndex(sectionIndex);
                param.setDayIndex(dayIndex);
                param.setTeacherName(teacherName);
                param.setTeacherPhone(teacherPhone);
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                param.setStrategy(strategy);
                param.setSubjectName(subjectName);
                return param;
            }
        }
    }


}
