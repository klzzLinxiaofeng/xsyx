package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 通过课程类型查询课程列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class CourseListByNameOrTypeResult extends OpenApiResult {

    public CourseListByNameOrTypeResult(HttpResponse response) {
        super(response);
    }


    /**
     * 
     */
    
    private ResponseBody ResponseBody;


    public ResponseBody getResponseBody() {
        return this.ResponseBody;
    }

    public void setResponseBody(ResponseBody ResponseBody) {
        this.ResponseBody = ResponseBody;
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
         * 
         */
        private List<Data> data;

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

        public List<Data> getData() {
            return this.data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

    }

    public static class Data {
        /**
         * 总个数 
         */
        private Integer total;
        /**
         * 第几页
         */
        private Integer pageNo;
        /**
         * 每页个数
         */
        private Integer pageSize;
        /**
         * 总页数
         */
        private Integer totalPage;
        /**
         * 
         */
        private List<DataItem> data;

        public Integer getTotal() {
            return this.total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getPageNo() {
            return this.pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getTotalPage() {
            return this.totalPage;
        }

        public void setTotalPage(Integer totalPage) {
            this.totalPage = totalPage;
        }

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }

    }

    public static class DataItem {
        /**
         * 课程编码
         */
        private String courseCode;
        /**
         * 课程名字 
         */
        private String courseName;
        /**
         * 老师uid 
         */
        private String teacherUid;
        /**
         * 老师名称 
         */
        private String teacherName;
        /**
         * 类型 : 1: 行政班 2：教学班
         */
        private Integer type;
        /**
         * 班级uid 
         */
        private String classUid;

        public String getCourseCode() {
            return this.courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getCourseName() {
            return this.courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
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

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

    }


}

