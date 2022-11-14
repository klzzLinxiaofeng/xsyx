package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("bp/info/")
public interface BpBwInfoRestService {
	
	/**
	 * 查看欢迎词(新)
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getWelcomeText(@FormParam("teamId") Integer teamId,
                          @FormParam("currentTime") Long currentTime,
                          @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 查看欢迎词(旧)
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getWelcomeText")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getWelcomeText(@FormParam("teamId") Integer teamId,
                          @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 发布欢迎词
	 * @param teamId
	 * @param welcomeText
	 * @param welcomeTextTime
	 * @param welcomeTextEndTime
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createWelcomeText")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createWelcomeText(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("gradeId") Integer gradeId,
            @FormParam("teamId") Integer teamId,
            @FormParam("welcomeText") String welcomeText,
            @FormParam("welcomeTextTime") Long welcomeTextTime,
            @FormParam("welcomeTextEndTime") Long welcomeTextEndTime,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 删除欢迎词
	 * @param infoId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("deleteWelcomeText")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteWelcomeText(
            @FormParam("infoId") Integer infoId,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 查看欢迎词
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getWelcomeTextInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getWelcomeTextInfo(@FormParam("teamId") Integer teamId,
                              @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 发布欢迎词(选多个班)
	 * @param teamId
	 * @param welcomeText
	 * @param welcomeTextTime
	 * @param welcomeTextEndTime
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createWelcomeTexts")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createWelcomeTexts(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("gradeId") Integer gradeId,
            @FormParam("teamIds") String teamIds,
            @FormParam("welcomeText") String welcomeText,
            @FormParam("welcomeTextTime") Long welcomeTextTime,
            @FormParam("welcomeTextEndTime") Long welcomeTextEndTime,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
