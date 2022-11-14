package platform.szxyzxx.web.personnel.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.personnel.model.TeacherTitle;
import platform.education.personnel.vo.TeacherTitleCondition;
import platform.education.personnel.vo.TeacherTitleVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/personnel/title")
public class TeacherTitleController extends BaseController { 
	
	private final static String viewBasePath = "/personnel/teachertitle";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TeacherTitleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<TeacherTitle> items = this.teacherTitleService.findTeacherTitleByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", toVos(items));
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<TeacherTitle> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TeacherTitleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.teacherTitleService.findTeacherTitleByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TeacherTitle teacherTitle, @CurrentUser UserInfo user) {
		Integer schoolId = teacherTitle.getSchoolId();
		if(schoolId == null) {
			teacherTitle.setSchoolId(user.getSchoolId());
		}
		teacherTitle = this.teacherTitleService.add(teacherTitle);
		return teacherTitle != null ? new ResponseInfomation(teacherTitle.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		TeacherTitle teacherTitle = this.teacherTitleService.findTeacherTitleById(id);
		if(teacherTitle != null) {
			model.addAttribute("teacherTitle", toVo(teacherTitle));
		}
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		TeacherTitle teacherTitle = this.teacherTitleService.findTeacherTitleById(id);
		model.addAttribute("isCK", "disable");
		if(teacherTitle != null) {
			model.addAttribute("teacherTitle", toVo(teacherTitle));
		}
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, TeacherTitle teacherTitle) {
		if (teacherTitle != null) {
			teacherTitle.setId(id);
		}
		try {
			this.teacherTitleService.remove(teacherTitle);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TeacherTitle teacherTitle) {
		teacherTitle.setId(id);
		teacherTitle = this.teacherTitleService.modify(teacherTitle);
		return teacherTitle != null ? new ResponseInfomation(teacherTitle.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, TeacherTitleCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if(user != null && schoolId == null) {
			condition.setSchoolId(user.getSchoolId());
		}
	}
	
	private List<TeacherTitleVo> toVos(List<TeacherTitle> titles) {
		List<TeacherTitleVo> vos = new ArrayList<TeacherTitleVo>();
		for(TeacherTitle title : titles) {
			vos.add(toVo(title));
		}
		return vos;
	}
	
	private TeacherTitleVo toVo(TeacherTitle teacherTitle) {
		TeacherTitleVo vo = new TeacherTitleVo();
		BeanUtils.copyProperties(teacherTitle, vo);
		Teacher teacher = this.teacherService.findTeacherById(teacherTitle.getTeacherId());
		if(teacher != null) {
			vo.setTeacherName(teacher.getName());
			vo.setJobNumber(teacher.getJobNumber());
		}
		return vo;
	}
}
