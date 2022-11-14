package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据条件分页查询学校班级列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class ClassServiceListByConditionalResult extends OpenApiResult {

    public ClassServiceListByConditionalResult(HttpResponse response) {
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
         * 返回数据
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
         * pageSize
         */
        private Integer pageSize;
        /**
         * page
         */
        private Integer page;
        /**
         * totalCount
         */
        private Integer totalCount;
        /**
         * result
         */
        private List<ResultItem> result;

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

        public List<ResultItem> getResult() {
            return this.result;
        }

        public void setResult(List<ResultItem> result) {
            this.result = result;
        }

    }

    public static class ResultItem {
        /**
         * 班级ID
         */
        private String uid;
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 班级类型，1行政班，2走班
         */
        private Integer type;
        /**
         * 学段
         */
        private String stageCode;
        /**
         * 学段名：小学，初中，高中，幼儿园
         */
        private String stageName;
        /**
         * 年级序号
         */
        private Integer grade;
        /**
         * 学年
         */
        private Integer gradeYear;
        /**
         * 班级序号
         */
        private Integer clazz;
        /**
         * 班级名称
         */
        private String nickName;
        /**
         * 是否为已毕业班
         */
        private Boolean isGraduate;
        /**
         * 学院ID，高教版特有
         */
        private String departmentId;
        /**
         * 专业ID，高教版特有
         */
        private String majorId;
        /**
         * 年级ID，高教版特有
         */
        private String gradeId;

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

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getStageCode() {
            return this.stageCode;
        }

        public void setStageCode(String stageCode) {
            this.stageCode = stageCode;
        }

        public String getStageName() {
            return this.stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        public Integer getGradeYear() {
            return this.gradeYear;
        }

        public void setGradeYear(Integer gradeYear) {
            this.gradeYear = gradeYear;
        }

        public Integer getClazz() {
            return this.clazz;
        }

        public void setClazz(Integer clazz) {
            this.clazz = clazz;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Boolean getIsGraduate() {
            return this.isGraduate;
        }

        public void setIsGraduate(Boolean isGraduate) {
            this.isGraduate = isGraduate;
        }

        public String getDepartmentId() {
            return this.departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getMajorId() {
            return this.majorId;
        }

        public void setMajorId(String majorId) {
            this.majorId = majorId;
        }

        public String getGradeId() {
            return this.gradeId;
        }

        public void setGradeId(String gradeId) {
            this.gradeId = gradeId;
        }

    }


}

