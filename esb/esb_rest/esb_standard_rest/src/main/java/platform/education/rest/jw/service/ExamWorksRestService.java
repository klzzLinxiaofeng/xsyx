package platform.education.rest.jw.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Administrator on 2018/1/27.
 */
@Path("/school/exam/")
public interface ExamWorksRestService {

    /**
     * 获取学校指定学期的统考记录
     */
    @POST
    @Path("works/major/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getMajorExamWorks(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("schoolYear") String schoolYear,
            @FormParam("termCode") String termCode,
            @FormParam("examType") String examType
    ) ;

    /**
     * 获取年级试科目设置
     * @return
     */
    @POST
    @Path("works/context")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getExamWorksContext(
            @FormParam("examWorksId") Integer examWorksId,
            @FormParam("subjectCode") String subjectCode,
            @FormParam("gradeId") Integer gradeId
    );

    /**
     * 获取一次统考的所有年级班级及其考试科目设置
     * @return
     */
    @POST
    @Path("works/context/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getExamWorksContextList(
            @FormParam("examWorksId") Integer examWorksId,
            @FormParam("userId") Integer userId,
            @FormParam("type") String type
    );

    /**
     * 获取教师所教的班级小测
     */
    @POST
    @Path("works/class/list")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getClassExamWorks(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("schoolYear") String schoolYear,
            @FormParam("termCode") String termCode,
            @FormParam("userId") Integer userId
    ) ;


    @POST
    @Path("score/team/import")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object importTeamStudentScore(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("schoolYear") String schoolYear,
            @FormParam("termCode") String termCode,
            @FormParam("examWorksId") Integer examWorksId,
            @FormParam("subjectCode") String subjectCode,
            @FormParam("gradeId") Integer gradeId,
            @FormParam("teamId") Integer teamId,
            @FormParam("examTime") String examTime,
            @FormParam("dataJson") String dataJson,
            @FormParam("fullScore") Float fullScore,
            @FormParam("highScore") Float highScore,
            @FormParam("lowScore") Float lowScore,
            @FormParam("passScore") Float passScore,@FormParam("examType") Integer examType,
            @FormParam("userId") Integer userId
    );

}
