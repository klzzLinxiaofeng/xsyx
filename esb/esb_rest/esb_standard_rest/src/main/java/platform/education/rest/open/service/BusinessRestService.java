package platform.education.rest.open.service;

import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.TokenType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;

/**
 * Created by Administrator on 2017/12/4.
 */
@Path("/open/school/")
public interface BusinessRestService {

    /**
     * 添加家长
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("parent/add")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object addParent(
            @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
            @FormParam("schoolId") Integer schoolId, @FormParam("data") String data);

    /**
     *  修改家长信息（切换主副号，修改关系）
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("parent/relation/change")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object modifyParent(
            @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
            @FormParam("schoolId") Integer schoolId, @FormParam("studentUserId") Integer studentUserId, @FormParam("parentUserId") Integer parentUserId,
            @FormParam("rank") String rank, @FormParam("relation") String relation);


    /**
     * 解除家长和学生的关系
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("parent/unbind")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object unbindParent(
            @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
            @FormParam("schoolId") Integer schoolId, @FormParam("parentUserId") Integer parentUserId, @FormParam("data") String data);

    /**
     * 上传用户头像
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("user/icon/change")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object uploadIcon(
            @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
            @FormParam("userId") Integer userId, @FormParam("icon") File icon);

    /**
     * 修改密码
     */
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @POST
    @Path("user/password/change")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object changePassword(
            @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
            @FormParam("userName") String userName,@FormParam("oldPass") String oldPass,@FormParam("newPass") String newPass);
}
