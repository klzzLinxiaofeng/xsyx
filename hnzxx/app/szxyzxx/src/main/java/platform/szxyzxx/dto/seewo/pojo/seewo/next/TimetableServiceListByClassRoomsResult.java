package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据场地id查询场地课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-9-7
 */
public class TimetableServiceListByClassRoomsResult extends OpenApiResult {

    public TimetableServiceListByClassRoomsResult(HttpResponse response) {
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
         * data
         */
        private List<Data> data;
        /**
         * code
         */
        private String code;
        /**
         * message
         */
        private String message;

        public List<Data> getData() {
            return this.data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

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

    }

    public static class Data {
        /**
         * LessonRuleDtos
         */
        private List<LessonRuleDto> lessonRuleDtos;
        /**
         * buildingUid
         */
        private String buildingUid;
        /**
         * buildingName
         */
        private String buildingName;
        /**
         * classroomUid
         */
        private String classroomUid;
        /**
         * classroomName
         */
        private String classroomName;

        public List<LessonRuleDto> getLessonRuleDtos() {
            return this.lessonRuleDtos;
        }

        public void setLessonRuleDtos(List<LessonRuleDto> lessonRuleDtos) {
            this.lessonRuleDtos = lessonRuleDtos;
        }

        public String getBuildingUid() {
            return this.buildingUid;
        }

        public void setBuildingUid(String buildingUid) {
            this.buildingUid = buildingUid;
        }

        public String getBuildingName() {
            return this.buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getClassroomUid() {
            return this.classroomUid;
        }

        public void setClassroomUid(String classroomUid) {
            this.classroomUid = classroomUid;
        }

        public String getClassroomName() {
            return this.classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }

    }

    public static class LessonRuleDto {
        /**
         * uid
         */
        private String uid;
        /**
         * planUid
         */
        private String planUid;
        /**
         * strategy
         */
        private String strategy;
        /**
         * dayIndex
         */
        private Integer dayIndex;
        /**
         * periodUid
         */
        private String periodUid;
        /**
         * periodNickName
         */
        private String periodNickName;
        /**
         * periodType
         */
        private Integer periodType;
        /**
         * sectionIndex
         */
        private Integer sectionIndex;
        /**
         * typeSectionIndex
         */
        private Integer typeSectionIndex;
        /**
         * periodTypeName
         */
        private String periodTypeName;
        /**
         * startTime
         */
        private String startTime;
        /**
         * endTime
         */
        private String endTime;
        /**
         * classroomUid
         */
        private String classroomUid;
        /**
         * classroomName
         */
        private String classroomName;
        /**
         * courseUid
         */
        private String courseUid;
        /**
         * courseName
         */
        private String courseName;
        /**
         * courseType
         */
        private Integer courseType;
        /**
         * classUid
         */
        private String classUid;
        /**
         * teacherUid
         */
        private String teacherUid;
        /**
         * teacherName
         */
        private String teacherName;
        /**
         * subjectUid
         */
        private String subjectUid;
        /**
         * subjectName
         */
        private String subjectName;
        /**
         * className
         */
        private String className;
        /**
         * gradeName
         */
        private String gradeName;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPlanUid() {
            return this.planUid;
        }

        public void setPlanUid(String planUid) {
            this.planUid = planUid;
        }

        public String getStrategy() {
            return this.strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public Integer getDayIndex() {
            return this.dayIndex;
        }

        public void setDayIndex(Integer dayIndex) {
            this.dayIndex = dayIndex;
        }

        public String getPeriodUid() {
            return this.periodUid;
        }

        public void setPeriodUid(String periodUid) {
            this.periodUid = periodUid;
        }

        public String getPeriodNickName() {
            return this.periodNickName;
        }

        public void setPeriodNickName(String periodNickName) {
            this.periodNickName = periodNickName;
        }

        public Integer getPeriodType() {
            return this.periodType;
        }

        public void setPeriodType(Integer periodType) {
            this.periodType = periodType;
        }

        public Integer getSectionIndex() {
            return this.sectionIndex;
        }

        public void setSectionIndex(Integer sectionIndex) {
            this.sectionIndex = sectionIndex;
        }

        public Integer getTypeSectionIndex() {
            return this.typeSectionIndex;
        }

        public void setTypeSectionIndex(Integer typeSectionIndex) {
            this.typeSectionIndex = typeSectionIndex;
        }

        public String getPeriodTypeName() {
            return this.periodTypeName;
        }

        public void setPeriodTypeName(String periodTypeName) {
            this.periodTypeName = periodTypeName;
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

        public String getClassroomUid() {
            return this.classroomUid;
        }

        public void setClassroomUid(String classroomUid) {
            this.classroomUid = classroomUid;
        }

        public String getClassroomName() {
            return this.classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }

        public String getCourseUid() {
            return this.courseUid;
        }

        public void setCourseUid(String courseUid) {
            this.courseUid = courseUid;
        }

        public String getCourseName() {
            return this.courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public Integer getCourseType() {
            return this.courseType;
        }

        public void setCourseType(Integer courseType) {
            this.courseType = courseType;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getTeacherUid() {
            return this.teacherUid;
        }

        public void setTeacherUid(String teacherUid) {
            this.teacherUid = teacherUid;
        }

        public String getTeacherName() {
            return this.teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getSubjectUid() {
            return this.subjectUid;
        }

        public void setSubjectUid(String subjectUid) {
            this.subjectUid = subjectUid;
        }

        public String getSubjectName() {
            return this.subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getClassName() {
            return this.className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getGradeName() {
            return this.gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

    }


}

