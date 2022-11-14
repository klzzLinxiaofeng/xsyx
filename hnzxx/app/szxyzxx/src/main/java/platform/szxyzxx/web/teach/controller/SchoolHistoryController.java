package platform.szxyzxx.web.teach.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 学校历史沿革
 * @author tangzh
 *
 */
@Controller
@RequestMapping("/school/history")
public class SchoolHistoryController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(StudentCheckAttendanceController.class);
	
	private final static String viewBasePath = "/teach/schoolHistory";
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		ModelAndView mav = new ModelAndView();
		String viewPath = "";
		try{
			SchoolCondition schoolCondition = new SchoolCondition();
			schoolCondition.setId(user.getSchoolId());
			List<School> schoolList = schoolService.findSchoolByCondition(schoolCondition, page, order);
			if(schoolList != null && schoolList.size() > 0){
				if("list".equals(sub)){
//					viewPath = structurePath("/list");
				}else{
					viewPath = structurePath("/index");
				}
				mav.addObject("schoolList", schoolList);
				mav.setViewName(viewPath);
			}
		}catch(Exception e){
			log.info("学校历史查询异常");
		}
		return mav;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@CurrentUser UserInfo user, School school) {
		school.setId(user.getSchoolId());
		school = this.schoolService.modify(school);
		return school != null ? new ResponseInfomation(school.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
}
