package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("bp/common/")
public interface BpCommonRestService {

	@POST
	@Path("getTeamInfoList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeamInfoList(@FormParam("schoolId") Integer schoolId);
	
	@POST
	@Path("getSchoolTeamInfoListByUserId")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSchoolTeamInfoListByUserId(@FormParam("userId") Integer userId);
	
	@POST
	@Path("getTopIndexInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTopIndexInfo(@FormParam("schoolId") Integer schoolId, @FormParam("teamId") Integer teamId,
                           @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("getIndexContentInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getIndexContentInfo(@FormParam("teamId") Integer teamId,
                               @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("getBreakContentInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getBreakContentInfo(@FormParam("teamId") Integer teamId,
                               @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	//课间模式
	@POST
	@Path("getNewBreakContentInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getNewBreakContentInfo(@FormParam("teamId") Integer teamId,
                                  @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("getRoomListInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getRoomListInfo(@FormParam("schoolId") Integer schoolId,
                           @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	@POST
	@Path("app/release/getCurrent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getCurrent(@FormParam("appKey") String appKey);

	@POST
	@Path("im/account/getByApp")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object imAccount(@FormParam("appKey") String appKey,
                     @FormParam(value = "id") String id,
                     @FormParam(value = "type") String type);


	@POST
	@Path("getIndexInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getIndexInfo(@FormParam("teamId") Integer teamId,
                        @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	@POST
	@Path("updatePushFlag")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object updatePushFlag(
			@FormParam("roomId") Integer roomId,
			@FormParam("teamId") Integer teamId,
			@FormParam("type") String type,
			@FormParam("pushType") String pushType,
			@FormParam("objectId") Integer objectId,
			@FormParam("version") String version,
			@FormParam("appKey") String appKey,@FormParam("signage") String signage);

	/**
	 * 获取一卡通信息
	 * @param cardId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getCardInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getCardInfo(
            @FormParam("cardId") String cardId,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
