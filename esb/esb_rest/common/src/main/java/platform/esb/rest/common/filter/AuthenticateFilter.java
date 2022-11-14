package platform.esb.rest.common.filter;

/*import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;*/

import platform.basis.esb.service.common.contants.EsbConfig;
import platform.esb.rest.common.util.SignEncoder;
import platform.esb.rest.common.vo.ResponseInfo;
import platform.esb.rest.common.vo.ResponseVo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;

/**
 * Created by clouds on 16/10/26.
 */
public class AuthenticateFilter implements ContainerRequestFilter {

   /* private Logger logger = LoggerFactory.getLogger(AuthenticateFilter.class);*/

    @Context
    HttpServletRequest request;

   /* @Override*/
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String sign = request.getParameter("sign");
        UriInfo uriInfo = requestContext.getUriInfo();
        URI uri = uriInfo.getRequestUri();
        String path = uri.getPath();
        String method = request.getMethod();
        String dateStr = request.getParameter("date");

        String perSign = path + method + dateStr;

        Long dateTimeStamp = 0l;
        try {
            dateTimeStamp = Long.parseLong(dateStr);
        } catch (Exception e) {
           //* logger.info("时间戳转换失败, 原因为非整型数字字符串");*//*
            Response response = Response.ok(new ResponseVo<ResponseInfo>("400", new ResponseInfo("400", "400", "Bad Request"))).status(401).type(MediaType.APPLICATION_JSON).build();
            throw new WebApplicationException(response);
        }

        SignEncoder signEncoder = new SignEncoder(EsbConfig.getInstance().getSalt(), true, true);
        perSign = signEncoder.encodeSign(perSign);

        Long currentTime = System.currentTimeMillis();

        Long threshold = Long.parseLong(EsbConfig.getInstance().getThresholdVal());

        if((currentTime - dateTimeStamp) > threshold) {
            Response response = Response.ok(new ResponseVo<ResponseInfo>("400", new ResponseInfo("400", "400", "Bad Request"))).status(401).type(MediaType.APPLICATION_JSON).build();
            throw new WebApplicationException(response);
        }

        if (!perSign.equals(sign)) {
            Response response = Response.ok(new ResponseVo<ResponseInfo>("400", new ResponseInfo("401", "401", "UnAuthorized"))).status(401).type(MediaType.APPLICATION_JSON).build();
            throw new WebApplicationException(response);
        }
        return;
    }

}
