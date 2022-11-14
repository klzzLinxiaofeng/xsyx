
package platform.education.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import platform.education.constant.Constant;
import platform.education.netdisk.common.FrameworkException;
import platform.education.netdisk.common.ResultEnum;

import javax.servlet.http.HttpServletRequest;


/**
 * token 工具类，从上下文中获取token
 *
 * @author hasee
 */
@Component
public class TokenUtils {

    @Autowired
    RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 从request中获取token，并获取用户信息
     *
     * @return
     */
    public String getToken() {

        String strToken = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            strToken = request.getHeader(Constant.TOKEN);
        } catch (Exception e) {
            logger.error("获取token失败");
            e.printStackTrace();
        }
        return strToken;
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public long getUserId() {
        String token = this.getToken();
        if (StringUtil.isEmpty(token)) {
            throw new FrameworkException(ResultEnum.ERROR_301.getCode(), ResultEnum.ERROR_301.getMsg());
        }
        //有效期延长一天
        String userId = redisUtils.get(token, Constant.DEFAULT_EXPIRE);
        if (StringUtil.isEmpty(userId)) {
            throw new FrameworkException(ResultEnum.ERROR_301.getCode(), ResultEnum.ERROR_301.getMsg());
        }
        return Long.parseLong(userId);
    }

    /**
     * 获取在线用户信息
     *
     * @return
     */
//    public OnlineUserEntit getOnlineUser() {
//        String token = this.getToken();
//        if (StringUtil.isEmpty(token)) {
//            return null;
//        }
//        String userId = redisUtils.get(token, Constant.SEVEN_DAY_EXPIRE);
//        String onlineUserKey = RedisKeys.getOnlineUserKey(userId);
//        OnlineUserEntit onlineUserEntity = redisUtils.get(onlineUserKey, OnlineUserEntit.class,
//                Constant.SEVEN_DAY_EXPIRE);
//        return onlineUserEntity;
//    }

    /**
     * 删除用户token
     *
     * @return
     */
    public void deleteUser() {
        String token = this.getToken();
        if (StringUtil.isEmpty(token)) {
            return;
        }
        // 删除用户权限缓存
        String frameworkAccountUrlKey = RedisKeys.getFrameworkAccountUrlKey(this.getUserId() + "");
        String frameworkAccountKey = RedisKeys.getOnlineUserKey(this.getUserId() + "");
        redisUtils.delete(frameworkAccountUrlKey);
        redisUtils.delete(frameworkAccountKey);
        redisUtils.delete(token);
    }
}
