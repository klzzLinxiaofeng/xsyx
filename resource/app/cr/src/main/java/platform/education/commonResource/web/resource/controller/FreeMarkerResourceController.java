package platform.education.commonResource.web.resource.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.FreeMarkerUtil;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.OperationWeekcount;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.OperationWeekcountService;
import platform.education.resource.service.ResourceOperationService;
import platform.education.resource.vo.OperationWeekcountCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;

@Controller
@RequestMapping(value = "www")
public class FreeMarkerResourceController extends BaseController {
	
	@Autowired
	@Qualifier("operationWeekcountService")
	private OperationWeekcountService operationWeekcountService;
	
	@Autowired
	@Qualifier("resourceOperationService")
	private ResourceOperationService resourceOperationService;
	
	@RequestMapping
	@ResponseBody
	public void getResource(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("condition") ResourceVoCondition condition, @CurrentUser UserInfo user,
			@ModelAttribute("page") Page page, @ModelAttribute("order") Order order, Model model)
					throws ServletException, IOException {
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		/**
		 * 小学语文科目
		 */
		vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosOneChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosTwoChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosThreeChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosFourChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosFiveChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosSixChinese", vos);
		/**
		 * 初中语文科目
		 */
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosMiddleOneChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_CHINESE, user, request, page, order);
		data.put("vosMiddleTwoChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_CHINESE, user, request, page,
				order);
		data.put("vosMiddleThreeChinese", vos);
		/**
		 * 小学数学科目
		 */
		vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosOneMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosTwoMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosThreeMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosFourMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosFiveMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosSixMath", vos);
		/**
		 * 初中数学科目
		 */
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosMiddleOneMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosMiddleTwoMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_MATH, user, request, page, order);
		data.put("vosMiddleThreeMath", vos);
		/**
		 * 小学英语科目
		 */
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_ENGLISH, user, request, page, order);
		data.put("vosThreeEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_ENGLISH, user, request, page, order);
		data.put("vosFourEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_ENGLISH, user, request, page, order);
		data.put("vosFiveEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_ENGLISH, user, request, page, order);
		data.put("vosSixEnglish", vos);
		/**
		 * 初中英语科目
		 */
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_ENGLISH, user, request, page, order);
		data.put("vosMiddleOneEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_ENGLISH, user, request, page, order);
		data.put("vosMiddleTwoEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_ENGLISH, user, request, page,
				order);
		data.put("vosMiddleThreeEnglish", vos);
		data.put("request", request);
		data.put("userId", user.getUserId());
		data.put("realName", user.getRealName());
		/**
		 * 把res_resource_operation表符合条件的内容复制到res_res_operation_weekcount
		 */
		addToOperationWeekCount();
		
		List<OperationWeekcount> OperationWeekcountVos = new ArrayList<OperationWeekcount>();
		/**
		 * 获取收藏资源榜 click_count
		 */
		OperationWeekcountVos = getResourceByOperation(user, request,OperationType.CLICK);
		data.put("vosClickCount", OperationWeekcountVos);
		/**
		 * 获取点赞资源 like_count
		 */
		OperationWeekcountVos = getResourceByOperation(user, request, OperationType.LIKE);
		data.put("vosLikeCount", OperationWeekcountVos);
		/**
		 * 获取收藏资源榜 fav_count
		 */
		OperationWeekcountVos = getResourceByOperation(user, request,OperationType.FAV);
		data.put("vosFavCount", OperationWeekcountVos);
		/**
		 * 获取收藏资源榜 fav_count
		 */
		OperationWeekcountVos = getResourceByOperation(user, request,OperationType.DOWN);
		data.put("vosDownCount", OperationWeekcountVos);
		
		ServletContext context = request.getServletContext();
		//FreeMarkerUtil.crateHTML(context, data, "/index.ftl", "index.html");
		operationWeekcountService.removeAll();
	}

	private void conditionFilter(UserInfo user, ResourceVoCondition condition) {
		Integer appId = condition.getAppId();
		/*
		 * if (appId == null) { condition.setAppId(SysContants.SYSTEM_APP_ID); }
		 */
		if (condition.getGradeCode() != null && !"".equals(condition.getGradeCode().trim())) {// 对高中资源做特殊处理
			if ("0".equals(condition.getGradeCode())) {
				condition.setGradeCode("");
			}
		} else {

		}
	}

	private List<ResourceVo> getResourceByGradeCode(String gradeCode, String subjectCode, UserInfo user,
			HttpServletRequest request, Page page, Order order) {
		ResourceVoCondition condition = new ResourceVoCondition();
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		if (relateAppId != null) {
			condition.setRelateAppId(relateAppId);
		} else {
			condition.setRelateAppId(appId);
		}
		if (relateSchoolId != null) {
			condition.setRelateSchoolId(relateSchoolId);
		} else {
			condition.setRelateSchoolId(ownerId);
		}
		condition.setGradeCode(gradeCode);
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		condition.setSubjectCode(subjectCode);
		conditionFilter(user, condition);
		order.setAscending(false);
		order.setProperty("create_date");
		page.setPageSize(7);// 页面拿数据几条数据
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		condition.setResType(ResourceType.LEARNING_DESIGN);
		List<ResourceVo> kjvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.HOMEWORK);
		List<ResourceVo> zyvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.EXAM);
		List<ResourceVo> sjvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.TEACHING_PLAN);
		List<ResourceVo> javos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.MATERIAL);
		List<ResourceVo> scvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		if ((kjvos.size() + zyvos.size() + sjvos.size() + javos.size() + scvos.size()) <= 7) {
			vos.addAll(kjvos);
			vos.addAll(zyvos);
			vos.addAll(sjvos);
			vos.addAll(javos);
			vos.addAll(scvos);
		} else {
			for (int i = 0; i < 7; i++) {

				if (kjvos.size() > i) {
					vos.add(kjvos.get(i));
					if (vos.size() == 7) {
						break;
					}

				}
				if (zyvos.size() > i) {
					vos.add(zyvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
				if (sjvos.size() > i) {
					vos.add(sjvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
				if (javos.size() > i) {
					vos.add(javos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
				if (scvos.size() > i) {
					vos.add(scvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
			}
		}
		return vos;
	}

	/*private List<ResourceVo> getResourceByLikeCount(UserInfo user,HttpServletRequest request, Page page, Order order) {
		List<ResourceVo> vos = null;
		ResourceVoCondition condition = new ResourceVoCondition();
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		if (relateAppId != null) {
			condition.setRelateAppId(relateAppId);
		} else {
			condition.setRelateAppId(appId);
		}
		if (relateSchoolId != null) {
			condition.setRelateSchoolId(relateSchoolId);
		} else {
			condition.setRelateSchoolId(ownerId);
		}
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		conditionFilter(user, condition);
		order.setAscending(false);
		order.setProperty("like_count");
		page.setPageSize(8);// 页面拿数据几条数据
		vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		return vos;
	}
	
	private List<ResourceVo> getResourceByFavCount(UserInfo user,HttpServletRequest request, Page page, Order order) {
		List<ResourceVo> vos = null;
		ResourceVoCondition condition = new ResourceVoCondition();
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		if (relateAppId != null) {
			condition.setRelateAppId(relateAppId);
		} else {
			condition.setRelateAppId(appId);
		}
		if (relateSchoolId != null) {
			condition.setRelateSchoolId(relateSchoolId);
		} else {
			condition.setRelateSchoolId(ownerId);
		}
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		conditionFilter(user, condition);
		order.setAscending(false);
		order.setProperty("fav_count");
		page.setPageSize(8);// 页面拿数据几条数据
		vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		return vos;
	}*/
	
	private void addToOperationWeekCount() {
		this.resourceOperationService.createOperationWeekCountByType(OperationType.CLICK);
		this.resourceOperationService.createOperationWeekCountByType(OperationType.LIKE);
		this.resourceOperationService.createOperationWeekCountByType(OperationType.FAV);
		this.resourceOperationService.createOperationWeekCountByType(OperationType.DOWN);
	}
	private List<OperationWeekcount> getResourceByOperation(UserInfo user,HttpServletRequest request,Integer operationType){
		Page page = new Page();
		Order order = new Order();
		Integer relateAppId = getRelateApp(request);
		Integer relateSchoolId = getRelateSchool(request);
		ResourceLibrary resourceLibrary = null;
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();;
		if(relateAppId!=null&&relateSchoolId!=null){
        	resourceLibraryCondition.setAppId(relateAppId);
        	resourceLibraryCondition.setOwerId(relateAppId);
        	List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
        	if(resourceLibraryList != null&&resourceLibraryList.size()>0){//如果存在取对应的uui
        		resourceLibrary = resourceLibraryList.get(0);
        	}
		}
		OperationWeekcountCondition operationWeekcountCondition = new OperationWeekcountCondition();
		if(resourceLibrary!=null){
			operationWeekcountCondition.setLibraryUuid(resourceLibrary.getUuid());
		}
		resourceLibraryCondition.setAppId(SysContants.SYSTEM_APP_ID);
		resourceLibraryCondition.setOwerId(SysContants.SYSTEM_OWNER_ID);
		List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
    	if(resourceLibraryList != null&&resourceLibraryList.size()>0){//如果存在取对应的uui
    		resourceLibrary = resourceLibraryList.get(0);
    	}
    	if(resourceLibrary!=null){
    		operationWeekcountCondition.setLibraryPubUuid(resourceLibrary.getUuid());
    	}
		order.setAscending(false);
		order.setProperty("count");
		page.setPageSize(8);// 页面拿数据几条数据
		List<OperationWeekcount> vos = null;
		operationWeekcountCondition.setOperationType(operationType);
		if(operationWeekcountCondition.getLibraryPubUuid()!=null&&operationWeekcountCondition.getLibraryUuid()!=null){
			vos = this.operationWeekcountService.findOperationWeekcountByMoreCondition(operationWeekcountCondition, page, order);
			return vos;
		}
		return null;
	}
}
