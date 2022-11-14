package platform.education.rest.oa.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/oa")
public interface OaRestService {
	/**
	 * @function 获取全校通知
	 * @param schoolId
	 * @param beginDate
	 * @param endDate
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/school/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSchoolNotice(@FormParam("userId") Integer userId,
			@FormParam("schoolId") String schoolId,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@DefaultValue("create_date") @FormParam("sortItem") String sortItem,
			@DefaultValue("1") @FormParam("sortType") Integer sortType);
	
	/**
	 * @function 获取部门通知
	 * @param departmentId
	 * @param beginDate
	 * @param endDate
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/department/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getDepartmentNotice(@FormParam("userId") Integer userId,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@DefaultValue("create_date") @FormParam("sortItem") String sortItem,
			@DefaultValue("1") @FormParam("sortType") Integer sortType);
	
	/**
	 * @function 获取个人通知
	 * @param userId
	 * @param beginDate
	 * @param endDate
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/person/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getPersonNotice(@FormParam("userId") Integer userId,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@DefaultValue("create_date") @FormParam("sortItem") String sortItem,
			@DefaultValue("1") @FormParam("sortType") Integer sortType);
	
	
	/**
	 * @function 获取与自己相关的通知
	 * @param userId
	 * @param beginDate
	 * @param endDate
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/user/listAll")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getUserNotice(@FormParam("schoolId") String schoolId,
			@FormParam("userId") Integer userId,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@DefaultValue("create_date") @FormParam("sortItem") String sortItem,
			@DefaultValue("1") @FormParam("sortType") Integer sortType);
	
	/**
	 * @function 发送全校通知
	 * @param posterId
	 * @param schoolIDs
	 * @param beginDate
	 * @param endDate
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/school/send")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object sendSchoolNotice(@FormParam("schoolIds") String schoolIds,
			@FormParam("posterId") Integer posterId,
			@FormParam("posterName") String posterName,
			@FormParam("appKey") String appKey,
			@FormParam("title") String title,
			@FormParam("content") String content,
			@FormParam("postTime") String postTime,
			@FormParam("files") String files);
	
	/**
	 * @function 发送部门通知
	 * @param posterId
	 * @param departmentIDs
	 * @param beginDate
	 * @param endDate
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/department/send")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object sendDepartmentNotice(@FormParam("departmentIds") String departmentIds,
			@FormParam("posterId") Integer posterId,
			@FormParam("posterName") String posterName,
			@FormParam("appKey") String appKey,
			@FormParam("title") String title,
			@FormParam("content") String content,
			@FormParam("postTime") String postTime,
			@FormParam("files") String files);
	
	/**
	 * @function 发送个人通知
	 * @param posterId
	 * @param userIDs
	 * @param beginDate
	 * @param endDate
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/person/send")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object sendPersonNotice(@FormParam("userIds") String userIds,
			@FormParam("posterId") Integer posterId,
			@FormParam("posterName") String posterName,
			@FormParam("appKey") String appKey,
			@FormParam("title") String title,
			@FormParam("content") String content,
			@FormParam("postTime") String postTime,
			@FormParam("files") String files);
	
	/**
	 * @function 获取通知详细信息
	 * @param id
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getNotice(@FormParam("id") Integer id);
	
	/**
	 * @function 获取通知详细信息
	 * @param id
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/user/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getNoticeList(@FormParam("id") Integer id);
	
	
	/**
	 * @function 获取通知的附件
	 * @param id
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/file/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getFileList(@FormParam("noticeId") Integer noticeId);
	
	
	/**
	 * @function 阅读通知
	 * @param id
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/read")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object Read(@FormParam("noticeId") Integer noticeId,
			@FormParam("userId") Integer userId);
	
	/**
	 * @function 删除通知
	 * @param id
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object Delete(@FormParam("noticeId") Integer noticeId,
			@FormParam("userId") Integer userId);
	
	/**
	 * @function 我发表的通知
	 * @param id
	 * @date 2016-8-4
	 * @return
	 */
	@POST
	@Path("notice/outbox/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object MyPostedNotice(@FormParam("userId") Integer userId,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@DefaultValue("create_date") @FormParam("sortItem") String sortItem,
			@DefaultValue("1") @FormParam("sortType") Integer sortType);

	/**
	 * @function 通知全部设置为已读
	 * @param type	通知类型：school\dept\person, 不填默认全部
	 * @param userId
	 * @date  2017-8-25
	 * @return
	 */
	@POST
	@Path("notice/batchRead")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object BatchRead(@FormParam("type") String type, @FormParam("schoolId") Integer schoolId,
				@FormParam("userId") Integer userId);
}
