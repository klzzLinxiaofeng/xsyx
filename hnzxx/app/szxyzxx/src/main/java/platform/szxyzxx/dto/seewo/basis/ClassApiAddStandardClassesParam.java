package platform.szxyzxx.dto.seewo.basis;


import java.util.*;

import com.seewo.open.sdk.OpenApiParam;

/**
 * seewo-open API: 批量新增标准班级
 * 批量新增标准班级，若存在重复则忽略重复
 *
 * @author auto create
 * @since 2.0.1 2020-12-3
 */
public class ClassApiAddStandardClassesParam extends OpenApiParam {


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

    public static ClassApiAddStandardClassesParamBuilder builder() {
        return new ClassApiAddStandardClassesParamBuilder();
    }

    public static class ClassApiAddStandardClassesParamBuilder {
        private JSONRequestBody requestBody;

        public ClassApiAddStandardClassesParamBuilder requestBody(JSONRequestBody requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public ClassApiAddStandardClassesParam build() {
            ClassApiAddStandardClassesParam param = new ClassApiAddStandardClassesParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 班级信息
         */
        private List<ClassSaveDto> classAddDtoList;
        /**
         * 学校id 和list学校id保持一致
         */
        private String unitId;

        public List<ClassSaveDto> getClassAddDtoList() {
            return this.classAddDtoList;
        }

        public void setClassAddDtoList(List<ClassSaveDto> classAddDtoList) {
            this.classAddDtoList = classAddDtoList;
        }

        public String getUnitId() {
            return this.unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }


        public static JSONRequestBodyBuilder builder() {
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder {
            private List<ClassSaveDto> classAddDtoList;
            private String unitId;

            public JSONRequestBodyBuilder classAddDtoList(List<ClassSaveDto> classAddDtoList) {
                this.classAddDtoList = classAddDtoList;
                return this;
            }

            public JSONRequestBodyBuilder unitId(String unitId) {
                this.unitId = unitId;
                return this;
            }

            public JSONRequestBody build() {
                JSONRequestBody param = new JSONRequestBody();
                param.setClassAddDtoList(classAddDtoList);
                param.setUnitId(unitId);
                return param;
            }
        }
    }

    public static class ClassSaveDto {
        /**
         * 业务主键 更新时传入
         */
        private String id;
        /**
         * 学校id，必填 和外层保持一致
         */
        private String unitId;
        /**
         * 班级类型，1 普通班， 2 走班
         */
        private Integer type;
        /**
         * 学段信息，必填
         */
        private String stageCode;
        /**
         * 年级，如7年级,必填，范围为1-99
         */
        private Integer grade;
        /**
         * 班级的届数，例如2017届，必填，为2000至2117
         */
        private Integer gradeYear;
        /**
         * 班级，必填，大于0
         */
        private Integer clazz;
        /**
         * 班级别名
         */
        private String nickName;
        /**
         * 创建人uid，必填
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


        public static ClassSaveDtoBuilder builder() {
            return new ClassSaveDtoBuilder();
        }

        public static class ClassSaveDtoBuilder {
            private String id;
            private String unitId;
            private Integer type;
            private String stageCode;
            private Integer grade;
            private Integer gradeYear;
            private Integer clazz;
            private String nickName;
            private String creatorId;
            private Long createtime;

            public ClassSaveDtoBuilder id(String id) {
                this.id = id;
                return this;
            }

            public ClassSaveDtoBuilder unitId(String unitId) {
                this.unitId = unitId;
                return this;
            }

            public ClassSaveDtoBuilder type(Integer type) {
                this.type = type;
                return this;
            }

            public ClassSaveDtoBuilder stageCode(String stageCode) {
                this.stageCode = stageCode;
                return this;
            }

            public ClassSaveDtoBuilder grade(Integer grade) {
                this.grade = grade;
                return this;
            }

            public ClassSaveDtoBuilder gradeYear(Integer gradeYear) {
                this.gradeYear = gradeYear;
                return this;
            }

            public ClassSaveDtoBuilder clazz(Integer clazz) {
                this.clazz = clazz;
                return this;
            }

            public ClassSaveDtoBuilder nickName(String nickName) {
                this.nickName = nickName;
                return this;
            }

            public ClassSaveDtoBuilder creatorId(String creatorId) {
                this.creatorId = creatorId;
                return this;
            }

            public ClassSaveDtoBuilder createtime(Long createtime) {
                this.createtime = createtime;
                return this;
            }

            public ClassSaveDto build() {
                ClassSaveDto param = new ClassSaveDto();
                param.setId(id);
                param.setUnitId(unitId);
                param.setType(type);
                param.setStageCode(stageCode);
                param.setGrade(grade);
                param.setGradeYear(gradeYear);
                param.setClazz(clazz);
                param.setNickName(nickName);
                param.setCreatorId(creatorId);
                param.setCreatetime(createtime);
                return param;
            }
        }
    }


}
