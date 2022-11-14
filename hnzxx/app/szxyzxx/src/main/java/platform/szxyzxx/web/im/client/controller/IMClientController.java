package platform.szxyzxx.web.im.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.im.dao.ImAccountDao;
import platform.education.im.dao.ImProviderDao;
import platform.education.im.model.AppAuthorization;
import platform.education.im.model.ImAccount;
import platform.education.im.model.ImProvider;
import platform.education.im.service.AppAuthorizationService;
import platform.education.im.service.IMService;
import platform.education.im.service.ImAccountService;
import platform.education.im.service.ImProviderService;
import platform.education.im.vo.ImAccountCondition;
import platform.education.user.model.User;
import platform.szxyzxx.web.bbx.client.vo.ResponseVo;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.im.client.vo.AppAccount;
import platform.szxyzxx.web.im.client.vo.AppAccountVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;

@Controller
@RequestMapping("/im")
public class IMClientController extends BaseController{
	private Logger log = LoggerFactory.getLogger(IMClientController.class);
	
	@Resource
	private IMService imService;
	
	@Resource
	private ImProviderService imProviderService;
	
	@Resource
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("appAuthorizationService")
	private AppAuthorizationService appAuthorizationService;
	
	/**
	 * App用户登录IM服务器，必须先获得IM账号和密码。本接口根据请求的AppUser返回其IM账号。
	 * 用户指的是具有UserId的个人用户或者是具有OwnerId的。
	 * IM登录前App必须使用用户名和密码进行平台登录，成功后才进行IM登录。
	 * 
	 * @param appKey
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/account/getByApp")
	@ResponseBody
	public Object getByApp(@RequestParam(value="appKey")String appKey,
			@RequestParam(value="id")String id,
			@RequestParam(value="type")String type) {
		try {
			ImAccountCondition condition = new ImAccountCondition();
			ImProvider ip = this.imProviderService.findDefaultProvider();
			ImAccount imAccount = null;
			AppAuthorization appAuth = this.appAuthorizationService.findByAppKey(appKey);
			//个人账户
			if("1".equals(type)){
				condition.setAppKey(appAuth != null ? appAuth.getImAccountApp() : appKey);
				condition.setUserId(Integer.parseInt(id));
				condition.setImType(ip!=null?ip.getImType():"1");
				List<ImAccount> list = this.imAccountService.findImAccountByCondition(condition, null, null);
				//无账号
				if(list.size()<1){
					User user = this.userService.findUserById(Integer.parseInt(id));
					if(user != null){
						imAccount = this.imService.createIMAccountForUser(Integer.parseInt(id), "", appAuth != null ? appAuth.getImAccountApp() : appKey);
						if(imAccount!=null){
							if(("error").equals(imAccount.getImAccountStatus()) || ("fail").equals(imAccount.getImAccountStatus())){
								ResponseInfo info = new ResponseInfo();
								info.setDetail("环信账号不存在...");
								info.setMsg("账号不存在");
								info.setParam("");
								return new ResponseError("000007", info);
							}
						}
					} else {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("不存在该user...");
						info.setMsg("参数出错");
						info.setParam("id");
						return new ResponseError("511001", info);
					}
				} else {
					imAccount = list.get(0);
				}
			}
			if("2".equals(type)){
				condition.setAppKey(appAuth != null ? appAuth.getImAccountApp() : appKey);
				condition.setOwnerId(id);
				condition.setImType(ip!=null?ip.getImType():"1");
				List<ImAccount> list = this.imAccountService.findImAccountByCondition(condition, null, null);
				//无账号
				if(list.size()<1){
					imAccount = this.imService.createIMAccount(id, "", appAuth != null ? appAuth.getImAccountApp() : appKey);
					if(imAccount!=null){
						if(("error").equals(imAccount.getImAccountStatus()) || ("fail").equals(imAccount.getImAccountStatus())){
							ResponseInfo info = new ResponseInfo();
							info.setDetail("环信账号不存在...");
							info.setMsg("账号不存在");
							info.setParam("");
							return new ResponseError("000007", info);
						}
					}
				} else {
					imAccount = list.get(0);
				}
			}
			AppAccount appAccount = new AppAccount();
			BeanUtils.copyProperties(imAccount, appAccount);
			return new ResponseVo<AppAccount>("0",appAccount);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数解析异常...");
			info.setMsg("参数出错");
			info.setParam("");
			return new ResponseError("060113", info);
		}
	}
	
	/**
	 * 获取用户所有的App-IM账号，即用户在不同App的IM账号。
	 * 本方法只处理个人用户的情况。
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/account/listByUser")
	@ResponseBody
	public Object listByUser(@RequestParam(value="userId")String userId) {
		try {
			List<ImAccount> iaList = this.imAccountService.findByUser(Integer.parseInt(userId));
			List<AppAccountVo> voList = new ArrayList<AppAccountVo>();
			AppAccountVo vo = null;
			for(ImAccount ia : iaList){
				vo = new AppAccountVo();
				BeanUtils.copyProperties(ia, vo);
				voList.add(vo);
			}
			return new ResponseVo<List<AppAccountVo>>("0",voList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数解析异常...");
			info.setMsg("格式出错");
			info.setParam("userId");
			return new ResponseError("060113", info);
		}
	}
	
	
}
