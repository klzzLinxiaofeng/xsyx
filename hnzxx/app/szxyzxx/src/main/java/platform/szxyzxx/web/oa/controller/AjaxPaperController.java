package platform.szxyzxx.web.oa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.oa.vo.PaperUserReadCondition;
import platform.education.oa.vo.PaperUserReadVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping(value = "/office/ajax/paper")
public class AjaxPaperController extends BaseController{
	private final static String BASE_PATH = "oa/paper/";

	/**
	 * 对阅读人员的阅读状态进行分页显示
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param paperId
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/yuedu_index")
	public ModelAndView yuedu_index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PaperUserReadCondition condition,
			@ModelAttribute("page") Page page,
			@RequestParam(value = "paperId", required = false) String paperId,
			@ModelAttribute("order") Order order, Model model) {

		String viewPath = null;
		page.setPageSize(30);
		if ("yuedu_list".equals(sub)) {
			viewPath = structurePath("/yuedu_list");
		} else {
			viewPath = structurePath("/yuedu_index");
		}
		condition.setOwnerId(user.getSchoolId());
		condition.setOwnerType(user.getGroupType());
		condition.setPaperId(Integer.parseInt(paperId));
		if(type!=null || !type.equals("")){
			if(type.equals("weiyuedu")){
				condition.setReadStatus(false);
			}else if(type.equals("yiyuedu")){
				condition.setReadStatus(true);
			}
		}
		
		List<PaperUserReadVo> puLists1 = this.paperUserReadService
				.findPaperUserReadByConditionVo(condition,page,order);
		if(puLists1!=null){
			for(int i=0;i<puLists1.size();i++){
				Teacher t = this.teacherService.findOfUser(user.getSchoolId(), puLists1.get(i).getUserId());
				 if(t!=null){
					 puLists1.get(i).setRealName(t.getName());
					 puLists1.get(i).setTelphone(t.getMobile());
				 }
			}
		}
		
		model.addAttribute("paperId", paperId);
		model.addAttribute("puLists1", puLists1);
		model.addAttribute("type", type);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	
	
	
	
	
	private String structurePath(String subPath) {
		return BASE_PATH + subPath;
	}
}
