package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据学号查询学生课表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetableServiceListByStudentCodesResult extends OpenApiResult {

    public TimetableServiceListByStudentCodesResult(HttpResponse response) {
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
         * 
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
         * 课节规则列表
         */
        private List<LessonRuleDtos> lessonRuleDtos;
        /**
         * 用户uid
         */
        private String userUid;
        /**
         * 用户名
         */
        private String name;
        /**
         * 学号
         */
        private String studentCode;
        /**
         * 教师手机号
         */
        private String teacherPhone;

        public List<LessonRuleDtos> getLessonRuleDtos() {
            return this.lessonRuleDtos;
        }

        public void setLessonRuleDtos(List<LessonRuleDtos> lessonRuleDtos) {
            this.lessonRuleDtos = lessonRuleDtos;
        }

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getTeacherPhone() {
            return this.teacherPhone;
        }

        public void setTeacherPhone(String teacherPhone) {
            this.teacherPhone = teacherPhone;
        }

    }

    public static class LessonRuleDtos {
        /**
         * 课节id
         */
        private String uid;
        /**
         * 排课计划id
         */
        private String planUid;
        /**
         *     "ALL", "普通课节";    "ALNT", "单双周";    "ALNT:1", "单周课程";    "ALNT:0", "双周课程";
         */
        private String strategy;
        /**
         * 星期几 1-7 表示 星期一到星期天
         */
        private Integer dayIndex;
        /**
         * 作息时间段
         */
        private String periodUid;
        /**
         * 自定义名称
         */
        private String periodNickName;
        /**
         * 时间段类型
         */
        private Integer periodType;
        /**
         * 一天中的第几节 Ordered
         */
        private Integer sectionIndex;
        /**
         * 作息时间段时间段类型课节序号
         */
        private Integer typeSectionIndex;
        /**
         * 时间段名称
         */
        private String periodTypeName;
        /**
         * 起始时间 HH:mm
         */
        private String startTime;
        /**
         * 起始时间 HH:mm
         */
        private String endTime;
        /**
         * 场地id
         */
        private String classroomUid;
        /**
         * 场地名字
         */
        private String classroomName;
        /**
         * 课程id
         */
        private String courseUid;
        /**
         * 课程名字
         */
        private String courseName;
        /**
         * 课程类型
         */
        private Integer courseType;
        /**
         * 班级id
         */
        private String classUid;
        /**
         * 老师id
         */
        private String teacherUid;
        /**
         * 老师名称
         */
        private String teacherName;
        /**
         * 学科id
         */
        private String subjectUid;
        /**
         * 学科名
         */
        private String subjectName;
        /**
         * 班级名称
         */
        private String className;
        /**
         * 年级名称
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

