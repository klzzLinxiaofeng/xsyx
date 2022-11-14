package platform.szxyzxx.web.teach.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import platform.szxyzxx.web.common.controller.base.BaseController;

@Controller
@RequestMapping("/pub/membership")
public class MembershipControllerClient extends BaseController{
	
	/**
	 * 获取所有下级组织机构
	 * @param appKey
	 * @param ownerId
	 * @param ownerType
	 * @return
	 */
	@RequestMapping(value = "/listChildren")
	public Object listChildren(
			@RequestParam(value = "appKey", required = true) String appKey,
			@RequestParam(value = "ownerId", required = true) String ownerId,
			@RequestParam(value = "ownerType", required = false) String ownerType
			){
		
		
		
		
		
		return null;
	}

}
