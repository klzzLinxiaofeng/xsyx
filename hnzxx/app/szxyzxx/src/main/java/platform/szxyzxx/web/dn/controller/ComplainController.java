package platform.szxyzxx.web.dn.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Complain;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.ComplainService;
import platform.education.generalTeachingAffair.vo.ComplainVo;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/dn/complain")
public class ComplainController extends BaseController {
	
	private final static String viewBasePath = "/dn/complain";
	
	@Autowired
	@Qualifier("complainService")
	private ComplainService complainService;

	//客户投诉主页
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "departId", required = false) Integer departId,
			@RequestParam(value = "description", required = false) String description,
			@ModelAttribute("page") Page page,
			Model model) {
		String viewPath = null;
		List<ComplainVo> complainList = null;
		List<ComplainVo> all = null;
		page.setPageSize(3);
		if ("list".equals(sub)) {
			if (description != null && !"".equals(description)){
				description = description.trim();
			}
			if ("all".equals(type)) {
				complainList = complainService.findAllComplain(user.getSchoolId(), departId, description, null, page);
				all = complainService.findAllComplain(user.getSchoolId(), departId, description, null, null);
			} else {
				complainList = complainService.findByComplainant(user.getSchoolId(), user.getId(), description, page);
				all = complainService.findByComplainant(user.getSchoolId(), user.getId(), description, null);
			}
			model.addAttribute("complainList", complainList);
			if (all != null && all.size() > 0) {
				model.addAttribute("size", all.size());
			}
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("type", type);
		return new ModelAndView(viewPath, model.asMap());
	}

	//投诉处理主页
	@RequestMapping(value = "/dispose/index")
	public ModelAndView disposeIndex(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "isDispose", required = false) boolean isDispose,
			@RequestParam(value = "departId", required = false) Integer departId,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "isStatPage", required = false, defaultValue = "false") boolean isStatPage,
			@RequestParam(value = "beginDate", required = false) Date beginDate,
			@RequestParam(value = "endDate", required = false) Date endDate,
			@ModelAttribute("page") Page page,
			Model model ) {
		String viewPath = null;
		List<ComplainVo> complainList = null;
		page.setPageSize(3);
		if ("list".equals(sub)) {
			if (description != null && !"".equals(description)){
				description = description.trim();
			}
			if (isStatPage) {
				//处理统计
				complainList = complainService.findByCreateDate(user.getSchoolId(), departId, description, beginDate, endDate);
				//获取教师部门
				setDepartAndName(departId, complainList);
				viewPath = structurePath("/stat_list");
			} else {
				viewPath = structurePath("/dispose_list");	//已处理
//				if (isDispose) {
//				} else {
//					viewPath = structurePath("/dispose_list1");	//未处理
//				}
				complainList = complainService.findAllComplain(user.getSchoolId(), departId, description, isDispose, page);
			}

			model.addAttribute("complainList", complainList);
		} else {
			viewPath = structurePath("/dispose_index");
		}
		model.addAttribute("isDispose", isDispose);
		model.addAttribute("isStatPage", isStatPage);
		return new ModelAndView(viewPath, model.asMap());
	}

	private void setDepartAndName(Integer departId, List<ComplainVo> complainList){
		Department department = null;
		Teacher teacher = null;
		if (departId != null) {
			department = departmentService.findDepartmentById(departId);
			if (department != null) {
				for (ComplainVo complainVo : complainList) {
					complainVo.setDepartName(department.getName());
				}
			}
		} else {
			for (ComplainVo complainVo : complainList) {
				department = departmentService.findDepartmentById(complainVo.getDepartId());
				if (department != null) {
					complainVo.setDepartName(department.getName());
				}
			}
		}
		for (ComplainVo complainVo : complainList) {
			teacher = teacherService.findOfUser(complainVo.getSchoolId(), complainVo.getComplainantId());
			if (teacher != null) {
				complainVo.setComplainName(teacher.getName());
			}
		}
	}

	//投诉页面
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	//新建投诉
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			@ModelAttribute("complain") Complain complain,
			@RequestParam(value = "fileUUIDs", required = false) String fileUUIDs,
			@CurrentUser UserInfo user) {
		//新建投诉时，图片的uuid由另一个字段单独传入
		complain.setComplainantId(user.getId());
		complain.setSchoolId(user.getSchoolId());
		complain = this.complainService.addComplain(complain, fileUUIDs);
		return complain != null ? new ResponseInfomation(complain.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	//编辑editor、评价evaluate、处理dispose页面
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "type", required = false) String type,
			Model model) {
		String viewPath = null;
		ComplainVo complainVo = this.complainService.findComplainVoById(id);
		if ("editor".equals(type)) {
			viewPath = structurePath("/input");
		} else if ("evaluate".equals(type)) {
			viewPath = structurePath("/evaluate");
		} else if ("dispose".equals(type)){
			viewPath = structurePath("/dispose");
		}
		model.addAttribute("complainVo", complainVo);
		return new ModelAndView(viewPath, model.asMap());
	}

	//编辑、评价、处理
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(
			@CurrentUser UserInfo user,
			@PathVariable(value = "id") Integer id,
			@RequestParam(value = "isChange", required = false, defaultValue = "false") boolean isChange,
			@RequestParam(value = "fileUUIDs", required = false) String fileUUIDs,
			@ModelAttribute("complain") Complain complain) {
		//评价is_dispose 、处理 is_evaluate 的状态由页面传值
		//编辑时，图片是否有变动由isChange状态传值，在页面判断
		complain.setId(id);
		if (complain.getIsDispose() != null && complain.getIsDispose()) {
			complain.setDisposeId(user.getId());
		}
		complain = this.complainService.modifyComplain(complain, fileUUIDs, isChange);
		return complain != null ? new ResponseInfomation(complain.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	//删除投诉
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Complain complain) {
		if (complain != null) {
			complain.setId(id);
		}
		try {
			this.complainService.remove(complain);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/dept/get", method = RequestMethod.POST)
	@ResponseBody
	public List<DepartmentTeacher> getDeptOfTeacher(@CurrentUser UserInfo user){
		List<DepartmentTeacher> dtList = null;
		Teacher teacher = teacherService.findOfUser(user.getSchoolId(), user.getId());
		if(teacher != null){
			DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setTeacherId(teacher.getId());
			dtList = departmentTeacherService.findDepartmentTeacherByConditionMore(condition);
		}
		return dtList;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

}
