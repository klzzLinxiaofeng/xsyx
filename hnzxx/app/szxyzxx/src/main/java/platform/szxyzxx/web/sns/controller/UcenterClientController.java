/**
 * 
 */
package platform.szxyzxx.web.sns.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.service.ucenter.client.UcenterClient;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 
 * @author Jacken(chenjacken@gmail.com)
 *
 */
@Controller
@RequestMapping("/api")
public class UcenterClientController {

	private static final String API_RETURN_SUCCEED = "1";
	private static final String API_RETURN_FAILED = "-1";
	private static final String API_RETURN_FORBIDDEN = "-2";
	
	
	
	private final static boolean API_DELETEUSER = true;		//note 用户删除 API 接口开关
	private final static boolean API_RENAMEUSER = true;		//note 用户改名 API 接口开关
	private final static boolean API_GETTAG = true;		//note 获取标签 API 接口开关
	private final static boolean API_SYNLOGIN = true;		//note 同步登录 API 接口开关
	private final static boolean API_SYNLOGOUT = true;		//note 同步登出 API 接口开关
	private final static boolean API_UPDATEPW = true;		//note 更改用户密码 开关
	private final static boolean API_UPDATEBADWORDS = true;	//note 更新关键字列表 开关
	private final static boolean API_UPDATEHOSTS = true;		//note 更新域名解析缓存 开关
	private final static boolean API_UPDATEAPPS = true;		//note 更新应用列表 开关
	private final static boolean API_UPDATECLIENT = true;		//note 更新客户端缓存 开关
	private final static boolean API_UPDATECREDIT = true;		//note 更新用户积分 开关
	private final static boolean API_GETCREDITSETTINGS = true;	//note 向 UCenter 提供积分设置 开关
	private final static boolean API_GETCREDIT = true;		//note 获取用户的某项积分 开关
	private final static boolean API_UPDATECREDITSETTINGS = true;	//note 更新应用积分设置 开关
	
	

	@Resource
	private UcenterClient ucenterClient;

	public void setUcenterClient(UcenterClient ucenterClient) {
		this.ucenterClient = ucenterClient;
	}

	@RequestMapping(value = "/uc.php", method = RequestMethod.GET)
	@ResponseBody
	public String uc(@RequestParam(value = "code", required = false) String code, HttpServletResponse response, @CurrentUser UserInfo user) {
		if (null == code) {
			return API_RETURN_FAILED;
		}
		code = ucenterClient.authcode(code, "DECODE");
		Map<String, String> paramMap = parseCode(code);
		if (paramMap.isEmpty()) {
			return "Invalid Request";
		}
		if(time() - tolong(paramMap.get("time")) > 3600) {
			return "Authracation has expiried";
		}
		String action = paramMap.get("action");
		if (action == null) {
			return API_RETURN_FAILED;
		}
		if ("test".equals(action)) {
			return API_RETURN_SUCCEED;
		} else if ("deleteuser".equals(action)) {
			//删除用户
			return API_RETURN_SUCCEED;
		} else if ("renameuser".equals(action)) {
			//重命名用户
			return API_RETURN_SUCCEED;
		} else if ("gettag".equals(action)) {
			if(!API_GETTAG) {
				return API_RETURN_FORBIDDEN;
			}
			//获取标签
			return API_RETURN_SUCCEED;
			
		} else if ("synlogin".equals(action)) {
			if(!API_SYNLOGIN ) {
				return (API_RETURN_FORBIDDEN);
			}
			//note 同步登录 API 接口
			//obclean();
//			response.addHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
//			int $cookietime = 31536000;
//			Cookie user = new Cookie("loginuser", paramMap.get("username"));
//			user.setMaxAge($cookietime);
//			response.addCookie(user);
		} else if("synlogout".equals(action)) {
			if(!API_SYNLOGOUT ) {
				return (API_RETURN_FORBIDDEN);
			}
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} else if("updateclient".equals(action)) {
			if(!API_UPDATECLIENT ) {
				return API_RETURN_FORBIDDEN;
			}
			//同步代码
			return API_RETURN_SUCCEED;
		} else if("updatepw".equals(action)) {
			if(!API_UPDATEPW) {
				return API_RETURN_FORBIDDEN;
				
			}
			//同步代码
			return API_RETURN_SUCCEED;
		} else if("updatebadwords".equals(action)) {
			if(!API_UPDATEBADWORDS) {
				return API_RETURN_FORBIDDEN;
			}
			//同步代码
			return API_RETURN_SUCCEED;
		} else if("updatehosts".equals(action)) {
			if(!API_UPDATEHOSTS ) {
				return API_RETURN_FORBIDDEN;
			}
			return API_RETURN_SUCCEED;
		} else if("updateapps".equals(action)) {
			if(!API_UPDATEAPPS ) {
				return API_RETURN_FORBIDDEN;
			}
			return API_RETURN_SUCCEED;
		} else if("updatecredit".equals(action)) {
			if(!API_UPDATECREDIT ) {
				return API_RETURN_FORBIDDEN;
			}
			return API_RETURN_SUCCEED;
		} else if("getcreditsettings".equals(action)) {
			if(!API_GETCREDITSETTINGS ) {
				return API_RETURN_FORBIDDEN;
			}
			return "";//返回积分值
		} else if("updatecreditsettings".equals(action)) {
			if(!API_UPDATECREDITSETTINGS) {
				return API_RETURN_FORBIDDEN;
			}
			//同步代码
			return API_RETURN_SUCCEED;
		} else {
			return (API_RETURN_FORBIDDEN);
		}
		return "";
	}

	private Map<String, String> parseCode(String code) {
		Map<String, String> map = new HashMap<String, String>();
		String[] ps = StringUtils.split(code, "&");
		for (String p : ps) {
			String[] items = StringUtils.split(p, "=");
			for (String i : items) {
				if (items.length == 2) {
					map.put(items[0], items[1]);
				} else if (items.length == 1) {
					map.put(items[0], "");
				}
			}
		}
		return map;
	}
	
	private long time(){
		return System.currentTimeMillis()/1000;
	}
	
	private static long tolong(Object s){
        if(s!=null){
            String ss = s.toString().trim();
            if(ss.length()==0){
                return 0L;
            }else{
                return Long.parseLong(ss);
            }
        }else{
            return 0L;
        }
    }
}
