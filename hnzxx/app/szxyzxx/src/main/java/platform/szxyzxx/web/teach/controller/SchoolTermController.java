package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolTermVo;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.InfoCode;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teach/schoolTerm")
public class SchoolTermController extends BaseController {

	private final static String viewBasePath = "/teach/schoolTerm";

	@RequestMapping(value = "/list")
	public ModelAndView getSchoolTermList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("schoolTermCondition") SchoolTermCondition schoolTermCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath;
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		schoolTermCondition.setSchoolId(user.getSchoolId());
		if(year != null){
			schoolTermCondition.setSchoolYear(year);
		}
		List<SchoolTermVo> schoolTermList = schoolTermService
				.findSchoolTermByConditionMore(schoolTermCondition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/schoolTermList");
		}
		
		//用于查询当前学期
		SchoolTermCurrent schoolTermCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		if(schoolTermCurrent != null){
			model.addAttribute("schoolTermCurrent", schoolTermCurrent);
		}
		
		//用于查询某学年下所属学期
		SchoolYear schoolYear = new SchoolYear();
		SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
		if(year != null){
			schoolYearCondition.setYear(year);
			schoolYearCondition.setSchoolId(user.getSchoolId());
			schoolYear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
			
		}
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("schoolTermList", schoolTermList);
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolTerm> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") SchoolTermCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return schoolTermService.findSchoolTermByCondition(condition, page, null);
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addSchoolTermPage(@CurrentUser UserInfo user,
			Model model) {
		model.addAttribute("schoolId", user.getSchoolId());
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creatorOther", method = RequestMethod.GET)
	public ModelAndView addSchoolTermPageOther(@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			SchoolYearCondition schoolYearCondition,
			Model model) {
		schoolYearCondition.setYear(year);
		schoolYearCondition.setSchoolId(user.getSchoolId());
		SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("schoolId", user.getSchoolId());

		SchoolTermCondition condition = new SchoolTermCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setSchoolYear(year);
		List<SchoolTermVo> schoolTermList = schoolTermService.findSchoolTermByConditionMore(condition, null, Order.asc("begin_date"));
		model.addAttribute("schoolTermList", JSONArray.fromObject(schoolTermList).toString());
		return new ModelAndView(structurePath("/inputOther"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addSchoolTerm(
			@CurrentUser UserInfo user,
			SchoolTerm schoolTerm) {
		SchoolYearCondition condition = new SchoolYearCondition();
		condition.setYear(schoolTerm.getSchoolYear());
		condition.setSchoolId(user.getSchoolId());
		SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(condition);
		if(schoolYear != null) {
			schoolTerm.setSchoolYearId(schoolYear.getId());
		}
		schoolTerm.setSchoolId(user.getSchoolId());
		schoolTerm.setCreateDate(new Date());
		schoolTerm.setModifyDate(new Date());
		
		if(schoolTerm != null) {
			String code = schoolTerm.getCode();
			SchoolTermCondition termCondition = new SchoolTermCondition();
			termCondition.setSchoolId(user.getSchoolId());
			termCondition.setIsDelete(false);
			termCondition.setCode(code);
			List<SchoolTerm> termList = this.schoolTermService.findSchoolTermByCondition(termCondition, null, null);
			if(termList.size() > 0) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		schoolTerm = this.schoolTermService.add(schoolTerm);
		return schoolTerm != null ? new ResponseInfomation(schoolTerm.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifySchoolTerm(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		SchoolTerm schoolTerm = this.schoolTermService.findSchoolTermById(id);
//		SchoolYearCondition condition = new SchoolYearCondition();
//		List<SchoolYear> schoolYearList = schoolYearService
//				.findSchoolYearByCondition(condition, null, null);
//		model.addAttribute("schoolYearList", schoolYearList);
		SchoolTermCondition condition = new SchoolTermCondition();
		condition.setSchoolId(schoolTerm.getSchoolId());
		condition.setSchoolYear(schoolTerm.getSchoolYear());
		List<SchoolTermVo> schoolTermList = schoolTermService.findSchoolTermByConditionMore(condition, null, Order.asc("begin_date"));
		if (schoolTermList != null && schoolTermList.size() > 0) {
			for (SchoolTermVo vo : schoolTermList) {
				if (vo.getId().equals(schoolTerm.getId())) {
					schoolTermList.remove(vo);
					break;
				}
			}
		}
		model.addAttribute("schoolTermList", JSONArray.fromObject(schoolTermList).toString());

		SchoolYear schoolYear = this.schoolYearService.findSchoolYearById(schoolTerm.getSchoolYearId()==null?0:schoolTerm.getSchoolYearId());
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("schoolId", user.getSchoolId());
		model.addAttribute("schoolTerm", schoolTerm);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		return new ModelAndView(structurePath("/inputOther"), model.asMap());
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateSchoolTerm(
			@CurrentUser UserInfo user,
			@PathVariable(value = "id") Integer id, SchoolTerm schoolTerm) {
		schoolTerm.setId(id);
		schoolTerm.setModifyDate(new Date());
		
		if(schoolTerm != null) {
			String code = schoolTerm.getCode();
			SchoolTermCondition termCondition = new SchoolTermCondition();
			termCondition.setSchoolId(user.getSchoolId());
			termCondition.setIsDelete(false);
			termCondition.setCode(code);
			List<SchoolTerm> termList = this.schoolTermService.findSchoolTermByCondition(termCondition, null, null);
			String codeTemp = this.schoolTermService.findSchoolTermById(id).getCode();
			if(!codeTemp.equals(code) && termList.size() > 0) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		
		schoolTerm = this.schoolTermService.modify(schoolTerm);
		return schoolTerm != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteSchoolTerm(@PathVariable(value = "id") Integer id,
			SchoolTerm schoolTerm) {
		if (schoolTerm != null) {
			schoolTerm.setId(id);
		}
		return this.schoolTermService.abandon(schoolTerm);
	}

	// 设置为当前学期
	@RequestMapping(value = "/currentTerm", method = RequestMethod.POST)
	@ResponseBody
	public String setCurrentTerm(
			@RequestParam(value = "id", required = true) Integer id, @CurrentUser UserInfo user, HttpSession session) {
		String result = this.schoolTermCurrentService.setCurrentSchoolTerm(id);
		if (SchoolTermCurrentService.OPERATE_SUCCESS.equals(result)) {
			SchoolTerm schoolTerm = this.schoolTermService.findSchoolTermById(id);
			user.setSchoolYear(schoolTerm.getSchoolYear());
			user.setSchoolTermCode(schoolTerm.getCode());
			session.setAttribute(InfoCode.CURRENT_USER, user);
		}
		return result;
	}

	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		SchoolTermCondition condition = new SchoolTermCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setCode(code);
		if ("code".equals(dxlx)) {
			List<SchoolTerm> list = schoolTermService
					.findSchoolTermByCondition(condition, null, null);
			if (list != null && list.size() > 0) {
				Integer currentId;
				for (SchoolTerm tem : list) {
					currentId = tem.getId();
					if (currentId != null && currentId.equals(id)) {
						isExist = true;
					} else {
						isExist = false;
					}
				}
			} else {
				isExist = true;
			}
		}
		return isExist;
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo userInfo, SchoolTermCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		if (schoolId == null) {
			if (userInfo != null) {
				condition.setSchoolId(userInfo.getSchoolId());
			}
		}
	}
}
