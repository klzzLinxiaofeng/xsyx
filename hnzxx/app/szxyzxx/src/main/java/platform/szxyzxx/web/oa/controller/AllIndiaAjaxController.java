package platform.szxyzxx.web.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.oa.model.India;
import platform.education.oa.service.IndiaService;
import platform.education.oa.vo.IndiaCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping(value = "/office/ajax/allIndia")
public class AllIndiaAjaxController extends BaseController{

	private final static String viewBasePath = "/oa/india";
	@Autowired
	@Qualifier("indiaService")
	private IndiaService indiaService;
	
	@RequestMapping(value = "/allIndia_index")
	public ModelAndView allIndia_index(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") IndiaCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		if ("allIndia_list".equals(sub)) {
			viewPath = structurePath("/allIndia_list");
		} else {
			viewPath = structurePath("/allIndia_index");
		}
		condition.setSchoolId(user.getSchoolId());
		List<India> items = this.indiaService.findIndiaByCondition(condition,
				page, order);
		List<String> departmentName = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			if (items.size() != 0) {
				Department d = this.departmentService.findDepartmentById(items
						.get(i).getDepartmentId());
				departmentName.add(i, d.getName());
			}
		}

		// 根据用户id user_id查找对应的教师Id teacherId
		Integer teacherId = this.teacherService.findUnqiueTeacherId(
				user.getId(), user.getSchoolId());
		// 根据teacherId查询对应的教师信息
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		List<EntityFile> upload = new ArrayList<EntityFile>();
		for (int i = 0; i < items.size(); i++) {
			if (items.size() != 0) {
				EntityFile entity = this.entityFileService.findFileByUUID(items.get(i)
						.getUploadFile());
				upload.add(i, entity);
			}
		}
		model.addAttribute("upload", upload);
		model.addAttribute("departmentName", departmentName);
		model.addAttribute("teacher", teacher);
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
		
	}
	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
}
