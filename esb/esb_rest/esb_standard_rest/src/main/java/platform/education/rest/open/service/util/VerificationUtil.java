package platform.education.rest.open.service.util;

import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.util.encoderUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/11.
 */
public class VerificationUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 公共参数的验证
     * @param sign          MD5(AppKey + AppSecret + Timestamp)
     * @param appKey
     * @param timeStamp     {yyyy-MM-dd HH:mm:ss}
     * @return 返回null，验证通过；验证有误，返回错误信息
     */
    public static Object verifySign(String sign, String appKey, String timeStamp){
        try {
            //1.判断Sign、AppKey是否存在
            if (sign == null || "".equals(sign)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("缺少Sign参数");
                info.setParam("Sign");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (appKey == null || "".equals(appKey)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("缺少AppKey参数");
                info.setParam("AppKey");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }

            //2.验证AppKey是否正确
            if (!SysContants.EDUCLOUD_APP_KEY.equals(appKey)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("AppKey值无效");
                info.setParam("AppKey");
                return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
            }

            //3.通过AppKey获取AppSecret
            String appSecret = SysContants.EDUCLOUD_APP_SECRET;

            //4.用AppKey + AppSecret + TimeStamp生成MD5签名
            String encoder = encoderUtil.encoder(appKey + appSecret + timeStamp);

            //5.生成的签名和sign进行比较
            if (!encoder.equals(sign)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("Sign解析失败");
                info.setParam("Sign");
                return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);
            }

            //6.获取服务器时间，判断时间是否超时
            Date time = null;
            if (timeStamp.contains(":") && timeStamp.contains("-")) {
                time = sdf.parse(timeStamp);
            } else {
                time = new Date(Long.parseLong(timeStamp));
            }
            Date now = new Date();
            if (Math.abs(now.getTime() - time.getTime()) > SysContants.REQUEST_ERROR_TIME) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("操作失败");
                info.setDetail("请求时间已超时");
                info.setParam("Timestamp");
                return new ResponseError(CommonCode.S$TIME_OUT, info);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setMsg("参数格式错误");
            info.setDetail("Timestamp的格式不正确，不符合{yyyy-MM-dd HH:mm:ss}标准");
            info.setParam("Timestamp");
            return new ResponseError(CommonCode.D$DATA_NOT_EXIST, info);
        }
        return null;
    }

    /**
     * 判断参数是否为空
     * @param object 参数
     * @param param  参数名
     * @param detail 解释
     */
    public static Object judgeNull(Object object, String param, String detail) {
        if (object == null || "".equals(object)) {
            ResponseInfo info = new ResponseInfo();
            info.setMsg("参数为空");
            info.setDetail(detail + "不能为空");
            info.setParam(param);
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        return null;
    }
}
