package platform.szxyzxx.web.im.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.im.model.PushObject;
import platform.education.im.model.PushSubscription;
import platform.education.im.service.PushObjectService;
import platform.education.im.service.PushSubscriptionService;
import platform.education.im.vo.AppAuthorizationVo;
import platform.education.im.vo.PushSubscriptionCondition;
import platform.education.im.vo.PushSubscriptionVo;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;




@Controller
@RequestMapping("/im/pushSubscription")
public class PushSubscriptionController { 
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final static String viewBasePath = "/im/pushSubscription";
	
	@Autowired
	@Qualifier("pushSubscriptionService")
	private PushSubscriptionService pushSubscriptionService;
	
	@Autowired
	@Qualifier("pushObjectService")
	private PushObjectService pushObjectService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PushSubscriptionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<PushSubscription> psList = this.pushSubscriptionService.findPushSubscriptionByCondition(condition, page, order);
		List<PushSubscriptionVo> items = new ArrayList<PushSubscriptionVo>();
		PushSubscriptionVo vo = null;
		for(PushSubscription ps : psList){
			vo = new PushSubscriptionVo();
			BeanUtils.copyProperties(ps, vo);
			PushObject po = this.pushObjectService.findByCode(ps.getObjectCode());
			AppEdition app = this.appEditionService.findByAppKey(ps.getAppKey());
			if(po!=null){
				vo.setPushObjectName(po.getName());
			}
			if(app!=null){
				vo.setAppKey(app.getAppKey());
			}
			items.add(vo);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<PushSubscription> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") PushSubscriptionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.pushSubscriptionService.findPushSubscriptionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(Model model) {
		List<PushObject> pushList = this.pushObjectService.findPushObjectByCondition(null);
		model.addAttribute("pushList",pushList);
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public String creator(
			@RequestParam(value = "appKey", required = true) String appKey,
			@CurrentUser UserInfo user,
			@RequestParam(value = "objectCodes", required = true) String objectCodes) {
		try {
			if(objectCodes!= null && !"".equals(objectCodes)){
				PushSubscription pushSubscription = null;
				JSONArray jsonArray = JSONArray.fromObject(objectCodes);
				for(int i=0; i<jsonArray.size(); i++){
					String objectCode = (String)jsonArray.get(i);
					pushSubscription = new PushSubscription();
					pushSubscription.setAppKey(appKey);
					pushSubscription.setObjectCode(objectCode);
					this.pushSubscriptionService.add(pushSubscription);
				}
			}
			return ResponseInfomation.OPERATION_SUC;
		} catch (Exception e) {
			log.info("{推送订阅错误}", e);
			return ResponseInfomation.OPERATION_ERROR;
		}
		
		
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		PushSubscription pushSubscription = this.pushSubscriptionService.findPushSubscriptionById(id);
		model.addAttribute("pushSubscription", pushSubscription);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		PushSubscription pushSubscription = this.pushSubscriptionService.findPushSubscriptionById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("pushSubscription", pushSubscription);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Boolean check(
			@ModelAttribute("condition") PushSubscriptionCondition condition,
			Model model) {
		List<PushSubscription> psList = this.pushSubscriptionService.findPushSubscriptionByCondition(condition, null, null);
		if(psList!=null && psList.size()>0){
			return false;
		}
		return true;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, PushSubscription pushSubscription) {
		if (pushSubscription != null) {
			pushSubscription.setId(id);
		}
		try {
			this.pushSubscriptionService.remove(pushSubscription);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, PushSubscription pushSubscription) {
		pushSubscription.setId(id);
		pushSubscription = this.pushSubscriptionService.modify(pushSubscription);
		return pushSubscription != null ? new ResponseInfomation(pushSubscription.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, PushSubscriptionCondition condition) {
	}
}
