package platform.szxyzxx.dto.seewo.basis;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 批量新增标准班级
 * 批量新增标准班级，若存在重复则忽略重复
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class ClassApiAddStandardClassesResult extends OpenApiResult {

    public ClassApiAddStandardClassesResult(HttpResponse response) {
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
         * 业务主键
         */
        private String id;
        /**
         * 组织id
         */
        private String unitId;
        /**
         * 班级类型，1 普通班， 2 走班
         */
        private Integer type;
        /**
         * 学段信息
         */
        private String stageCode;
        /**
         * 年级，如7年级
         */
        private Integer grade;
        /**
         * 年级的届数，例如2017届
         */
        private Integer gradeYear;
        /**
         * 班级
         */
        private Integer clazz;
        /**
         * 班级别名
         */
        private String nickName;
        /**
         * 创建人uid
         */
        private String creatorId;
        /**
         * 创建时间
         */
        private Long createtime;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUnitId() {
            return this.unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
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

        public String getCreatorId() {
            return this.creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public Long getCreatetime() {
            return this.createtime;
        }

        public void setCreatetime(Long createtime) {
            this.createtime = createtime;
        }

    }


}

