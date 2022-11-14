package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/bpSignageAuto/")
public interface BpSignageAutoRestService {
	
	/**
	 * 获取某班班牌自动开关机时间
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getBpSignageAutoList2")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getBpSignageAutoList2(
            @FormParam("teamId") Integer teamId,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 获取某教室班牌自动开关机时间
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getBpSignageAutoListByRoomId")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getBpSignageAutoListByRoomId(
            @FormParam("roomId") Integer roomId,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 批量设置班级定时开关机
	 * @param id
	 * @param teamIds
	 * @param schoolId
	 * @param content
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createOrModifyBpSignageAutos")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createOrModifyBpSignageAutos(
            @FormParam("id") Integer id,
            @FormParam("teamIds") String teamIds,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("content") String content,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);
	/**
	 * 批量设置教室定时开关机
	 * @param id
	 * @param roomIds
	 * @param schoolId
	 * @param content
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createOrModifyBpSignageAutosByRooms")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createOrModifyBpSignageAutosByRooms(
            @FormParam("id") Integer id,
            @FormParam("roomIds") String roomIds,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("content") String content,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 保存教室定时开关机(转时区)
	 * @param id
	 * @param roomId
	 * @param schoolId
	 * @param content
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createOrModifyBpSignageAutoByRoom1")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createOrModifyBpSignageAutoByRoom1(
            @FormParam("id") Integer id,
            @FormParam("roomId") Integer roomId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("content") String content,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 添加或修改自动开关机记录(转时区)
	 * 注:添加时必须带有schooId参数,
	 *    roomId != null时为某一个房室添加或修改记录,
	 * @param id
	 * @param roomId
	 * @param roomTypeCode
	 * @param schoolId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createOrModifyBpSignageAuto")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createOrModifyBpSignageAuto(
            @FormParam("id") Integer id,
            @FormParam("teamId") Integer teamId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("content") String content,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 保存教室定时开关机
	 * @param id
	 * @param roomId
	 * @param schoolId
	 * @param content
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createOrModifyBpSignageAutoByRoom2")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createOrModifyBpSignageAutoByRoom2(
            @FormParam("id") Integer id,
            @FormParam("roomId") Integer roomId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("content") String content,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 保存班级定时开关机
	 * @param id
	 * @param teamId
	 * @param schoolId
	 * @param content
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createOrModifyBpSignageAuto2")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createOrModifyBpSignageAuto2(
            @FormParam("id") Integer id,
            @FormParam("teamId") Integer teamId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("content") String content,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);
	
}
