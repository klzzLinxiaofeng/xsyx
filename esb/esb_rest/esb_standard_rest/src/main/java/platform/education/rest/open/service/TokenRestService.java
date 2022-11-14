package platform.education.rest.open.service;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.RequireParam;
import platform.education.rest.common.annotation.TokenType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
@Path("/token/")
public interface TokenRestService {

    /**
     * 获取教材目录
     * 根据学校ID、学段Code、学科Code、版本Code、册次Code获取教材相关的教材目录详细信息
     * @param userId 用户ID
     * @return
     */
    @POST
    @OpenApi(token = TokenType.NONUSE_TOKEN)
    @Path("singleSignOn/apply")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public Object getToken(
            @RequireParam(true)@FormParam("Sign") String sign,
            @RequireParam(true)@FormParam("AppKey") String appKey,
            @RequireParam(true)@FormParam("Timestamp") String timeStamp,
            @RequireParam(true)@FormParam("userId")Integer userId) throws OAuthSystemException;

}
