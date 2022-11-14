package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 保存|更新多个学生的家长信息
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class StudentApiSaveStudentParentsParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static StudentApiSaveStudentParentsParamBuilder builder(){
        return new StudentApiSaveStudentParentsParamBuilder();
    }

    public static class StudentApiSaveStudentParentsParamBuilder{
        private RequestBody requestBody;

        public StudentApiSaveStudentParentsParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public StudentApiSaveStudentParentsParam build(){
            StudentApiSaveStudentParentsParam param = new StudentApiSaveStudentParentsParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * 
         */
        private MisParentQueryDto query;

        public MisParentQueryDto getQuery() {
            return this.query;
        }

        public void setQuery(MisParentQueryDto query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private MisParentQueryDto query;

            public RequestBodyBuilder query(MisParentQueryDto query){
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

    public static class MisParentQueryDto {
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 应用key
         */
        private String appId;
        /**
         * 家长信息
         */
        private List<MisParentDetailDto> detailDtos;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public List<MisParentDetailDto> getDetailDtos() {
            return this.detailDtos;
        }

        public void setDetailDtos(List<MisParentDetailDto> detailDtos) {
            this.detailDtos = detailDtos;
        }


        public static MisParentQueryDtoBuilder builder(){
            return new MisParentQueryDtoBuilder();
        }

        public static class MisParentQueryDtoBuilder{
            private String schoolUid;
            private String appId;
            private List<MisParentDetailDto> detailDtos;

            public MisParentQueryDtoBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public MisParentQueryDtoBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public MisParentQueryDtoBuilder detailDtos(List<MisParentDetailDto> detailDtos){
                this.detailDtos = detailDtos;
                return this;
            }

            public MisParentQueryDto build(){
                MisParentQueryDto param = new MisParentQueryDto();
                param.setSchoolUid(schoolUid);
                param.setAppId(appId);
                param.setDetailDtos(detailDtos);
                return param;
            }
        }
    }

    public static class MisParentDetailDto {
        /**
         * 学生uid，不存在添加，存在更新
         */
        private String studentUid;
        /**
         * 家长信息，最多四个
         */
        private List<ParentInfoQuery> parentInfoQueries;

        public String getStudentUid() {
            return this.studentUid;
        }

        public void setStudentUid(String studentUid) {
            this.studentUid = studentUid;
        }

        public List<ParentInfoQuery> getParentInfoQueries() {
            return this.parentInfoQueries;
        }

        public void setParentInfoQueries(List<ParentInfoQuery> parentInfoQueries) {
            this.parentInfoQueries = parentInfoQueries;
        }


        public static MisParentDetailDtoBuilder builder(){
            return new MisParentDetailDtoBuilder();
        }

        public static class MisParentDetailDtoBuilder{
            private String studentUid;
            private List<ParentInfoQuery> parentInfoQueries;

            public MisParentDetailDtoBuilder studentUid(String studentUid){
                this.studentUid = studentUid;
                return this;
            }
            public MisParentDetailDtoBuilder parentInfoQueries(List<ParentInfoQuery> parentInfoQueries){
                this.parentInfoQueries = parentInfoQueries;
                return this;
            }

            public MisParentDetailDto build(){
                MisParentDetailDto param = new MisParentDetailDto();
                param.setStudentUid(studentUid);
                param.setParentInfoQueries(parentInfoQueries);
                return param;
            }
        }
    }

    public static class ParentInfoQuery {
        /**
         * 家长顺序
         */
        private Integer showIndex;
        /**
         * 家长姓名
         */
        private String parentName;
        /**
         * 家长电话
         */
        private String parentPhone;

        public Integer getShowIndex() {
            return this.showIndex;
        }

        public void setShowIndex(Integer showIndex) {
            this.showIndex = showIndex;
        }

        public String getParentName() {
            return this.parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public String getParentPhone() {
            return this.parentPhone;
        }

        public void setParentPhone(String parentPhone) {
            this.parentPhone = parentPhone;
        }


        public static ParentInfoQueryBuilder builder(){
            return new ParentInfoQueryBuilder();
        }

        public static class ParentInfoQueryBuilder{
            private Integer showIndex;
            private String parentName;
            private String parentPhone;

            public ParentInfoQueryBuilder showIndex(Integer showIndex){
                this.showIndex = showIndex;
                return this;
            }
            public ParentInfoQueryBuilder parentName(String parentName){
                this.parentName = parentName;
                return this;
            }
            public ParentInfoQueryBuilder parentPhone(String parentPhone){
                this.parentPhone = parentPhone;
                return this;
            }

            public ParentInfoQuery build(){
                ParentInfoQuery param = new ParentInfoQuery();
                param.setShowIndex(showIndex);
                param.setParentName(parentName);
                param.setParentPhone(parentPhone);
                return param;
            }
        }
    }


}
