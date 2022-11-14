package platform.education.rest.open.service;

import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.TokenType;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Administrator on 2017/11/10.
 */
@Path("/open/school/")
public interface BasicRestService {
    /**
     * 获取所有学校信息
     * @param version
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getSchoolList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version);

    /**
     * 获取学校所有用户账号信息
     * @param version
     * @param schoolId
     * @param type
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("account/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getAccountList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId, @FormParam("type") String type);

    /**
     * 获取学校所有教师个人信息
     * @param version
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("teacher/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getTeacherList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId);

    /**
     * 获取学校所有学生个人信息
     * @param version
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("student/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getStudentList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId);

    /**
     * 获取学校所有家长个人信息
     * @param version
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("parent/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getParentList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId);

    /**
     * 获取学校所有年级信息
     * @param version
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("grade/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getGradeList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId);

    /**
     * 获取学校所有班级信息
     * @param version
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("team/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getTeamList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId);

    /**
     * 获取学校所有部门信息
     * @param version
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("department/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getDepartmentList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId);

    /**
     * 获取学校毕业年级及班级信息
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("graduation/team/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getGraduationTeamList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("schoolId") Integer schoolId);

    /**
     * 获取学校所有科目信息
     * @param version
     * @param schoolId
     * @param subjectClass 课程级别 :1=公共课程; 2=地方课程; 3=校本课程. 如同时获取多个级别的课程,如"1,2"
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("subject/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getSubjectList(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("version") String version, @FormParam("schoolId") Integer schoolId, @FormParam("subjectClass") String subjectClass);


    /**
     * 获取学校的学年学期
     * @param schoolId
     * @return
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("year/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getYearAndTermList(@FormParam("version") String version, @FormParam("schoolId") Integer schoolId);

}
