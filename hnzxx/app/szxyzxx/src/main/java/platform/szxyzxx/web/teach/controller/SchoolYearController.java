package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teach/schoolYear")
public class SchoolYearController extends BaseController {

	private final static String viewBasePath = "/teach/schoolYear";

	@Autowired
	private BasicSQLService basicSQLService;

	@RequestMapping(value = "/list")
	public ModelAndView getSchoolYearList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("schoolYearCondition") SchoolYearCondition schoolYearCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath;
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		schoolYearCondition.setSchoolId(user.getSchoolId());
		List<SchoolYear> schoolYearList = schoolYearService
				.findSchoolYearByCondition(schoolYearCondition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/schoolYearList");
		}
		model.addAttribute("schoolYearList", schoolYearList);
		return new ModelAndView(viewPath, model.asMap());
	}


	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifySchoolYear(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		SchoolYear schoolYear = schoolYearService.findSchoolYearById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		model.addAttribute("schoolYear", schoolYear);
		SchoolYearCondition condition = new SchoolYearCondition();
		condition.setSchoolId(schoolYear.getSchoolId());
		List<SchoolYear> yearList = schoolYearService.findSchoolYearByCondition(condition, null, Order.asc("begin_date"));
		for (SchoolYear year : yearList) {
			if (year.getId().equals(schoolYear.getId()))  {
				yearList.remove(year);
				break;
			}
		}
		model.addAttribute("yearList", JSONArray.fromObject(yearList).toString());
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateSchoolYear(
			@CurrentUser UserInfo user,
			@PathVariable(value = "id") Integer id, SchoolYear schoolYear) {
		schoolYear.setId(id);
		schoolYear.setModifyDate(new Date());
		if(schoolYear != null) {
			SchoolYearCondition condition = new SchoolYearCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setYear(schoolYear.getYear());
			SchoolYear year = this.schoolYearService.findSchoolYearByYear(condition);
			String YearTemp = schoolYearService.findSchoolYearById(id).getYear();
			if(!YearTemp.equals(schoolYear.getYear()) && year != null){
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		schoolYear = this.schoolYearService.modify(schoolYear);
		
		//2016-5-30 修复修改学年时 将学期表中的冗余数据修改==========开始
		if(schoolYear != null && schoolYear.getId() != null){
			schoolTermService.modifyBySchoolYear(schoolYear);
		}
		//2016-5-30 修复修改学年时 将学期表中的冗余数据修改==========结束
		
		return schoolYear != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteSchoolYear(
			@CurrentUser UserInfo user,
			@PathVariable(value = "id") Integer id,
			SchoolYear schoolYear) {
		String year = null;
		int msg = 0;
		if (schoolYear != null) {
			schoolYear = this.schoolYearService.findSchoolYearById(id);
			year = schoolYear.getYear();
		}
		SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
		schoolTermCondition.setSchoolYear(year);
		schoolTermCondition.setSchoolId(user.getSchoolId());
		List<SchoolTerm> list = this.schoolTermService
				.findSchoolTermByCondition(schoolTermCondition, null, null);
		if (list.isEmpty()) {
			String message = schoolYearService.abandon(schoolYear);
			if(message == "success"){
				msg = 0;
			}else if(message == "fail" || message == "error"){
				msg = 1;
			}
		} else {
			msg = -1;
		}
		return msg;
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addSchoolYearPage(@CurrentUser UserInfo user,Model model) {
		SchoolYearCondition condition = new SchoolYearCondition();
		condition.setSchoolId(user.getSchoolId());
		List<SchoolYear> yearList = schoolYearService.findSchoolYearByCondition(condition, null, Order.asc("begin_date"));
		model.addAttribute("yearList", JSONArray.fromObject(yearList).toString());
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addSchoolYear(@CurrentUser UserInfo user,
			SchoolYear schoolYear) {
		schoolYear.setSchoolId(user.getSchoolId());
		schoolYear.setCreateDate(new Date());
		schoolYear.setModifyDate(new Date());
		if(schoolYear != null) {
			SchoolYearCondition condition = new SchoolYearCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setYear(schoolYear.getYear());
			SchoolYear year = this.schoolYearService.findSchoolYearByYear(condition);
			if(year != null){
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		schoolYear = this.schoolYearService.add(schoolYear);
		return schoolYear != null ? new ResponseInfomation(schoolYear.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();

	}

	@RequestMapping(value = "/checker", method = RequestMethod.GET)
//	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		SchoolYearCondition condition = new SchoolYearCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setYear(year);
		if ("year".equals(dxlx)) {
			List<SchoolYear> list = schoolYearService
					.findSchoolYearByCondition(condition, null, null);
			if (list != null && list.size() > 0) {
				Integer currentId;
				for (SchoolYear tem : list) {
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
	
	/**
	 * @Method jsonList
	 * @Function 功能描述：获取学年JSON数据
	 * @param user
	 * @param condition
	 * @param page
	 * @param order
	 * @param usePage
	 * @return
	 * @Date 2015年5月18日
	 */
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SchoolYearCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
//		conditionFilter(user, condition);
//		page = usePage ? page : null;
		//order.setProperty("year");
//		return schoolYearService.findSchoolYearByCondition(condition, page, order);


		return basicSQLService.find("SELECT y.* FROM pj_school_year y left join pj_school_term_current c on c.school_year_id=y.id WHERE y.is_delete = 0 AND y.school_id = 215 order by c.id desc,y.year desc");
	}
	
	private void conditionFilter(UserInfo user, SchoolYearCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if(user != null && schoolId == null) {
			condition.setSchoolId(user.getSchoolId());
		}
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
}
