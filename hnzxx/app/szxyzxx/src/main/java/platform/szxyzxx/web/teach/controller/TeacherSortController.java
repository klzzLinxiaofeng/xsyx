package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import platform.education.generalTeachingAffair.model.TeacherSort;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeacherSortService;
import platform.education.generalTeachingAffair.vo.TeacherSortCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.contants.TeacherSortTypeContants;
import framework.generic.dao.Order;
import framework.generic.dao.Page;



/**
 * 教师自定义排序
 * @author 陈业强
 *
 */
@Controller
@RequestMapping("/teacher/sort")
public class TeacherSortController { 
	
	private final static String viewBasePath = "/teach/teacherSort";
	
	@Resource
	private TeacherSortService teacherSortService;
	
	@Resource
	private TeacherService teacherService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TeacherSortCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		condition.setIsDelete(false);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		//用于条件查询
		List<TeacherSort> itemsOfSeach = this.teacherSortService.findTeacherSortByCondition(condition, null, order);
		
		//2016-6-13 =============================
		//用于同步新的数据
		List<TeacherSort> itemsOfAdd = new ArrayList<TeacherSort>();
		
		//用于查询出所有
		condition = new TeacherSortCondition();
		List<TeacherSort> itemsOfAll = this.teacherSortService.findTeacherSortByCondition(condition, null, order);
		Map<Integer,TeacherSort> map = new HashMap<Integer, TeacherSort>();
		for(TeacherSort ts : itemsOfAll){
			map.put(ts.getTeacherId(), ts);
		}
		
		//=======================================
		
		Integer sort = itemsOfAll.size()+1;
		List<Teacher> teachers = this.teacherService.findTeacherListBySchoolId(user.getSchoolId());
		if(itemsOfAll.size() != teachers.size()){
			if(user.getSchoolId() != null){
				if(teachers.size() > 0){
					for(Teacher teacher : teachers){
						TeacherSort teacherSort = map.get(teacher.getId());
						if(teacherSort == null){
							TeacherSort ts = new TeacherSort();
							ts.setPersionId(teacher.getPersionId());
							ts.setSchoolId(user.getSchoolId());
							ts.setTeacherId(teacher.getId());
							ts.setTeacherName(teacher.getName());
							ts.setType(TeacherSortTypeContants.OPT);
							ts.setUserId(teacher.getUserId());
							ts.setSort(sort);
							sort++;
							itemsOfAdd.add(ts);
						}
					}
					itemsOfAdd = this.teacherSortService.batchAdd(itemsOfAdd);
				}
			}
		}
		
		
		
		
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		
		if(itemsOfSeach.size()>0){
			for(TeacherSort teacherSort:itemsOfSeach){
				teacherSort.setTeacherName(this.teacherService.findTeacherById(teacherSort.getTeacherId()).getName());
			}
		}
		
		model.addAttribute("items", itemsOfSeach);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<TeacherSort> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TeacherSortCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.teacherSortService.findTeacherSortByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TeacherSort teacherSort, @CurrentUser UserInfo user) {
		teacherSort = this.teacherSortService.add(teacherSort);
		return teacherSort != null ? new ResponseInfomation(teacherSort.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		TeacherSort teacherSort = this.teacherSortService.findTeacherSortById(id);
		model.addAttribute("teacherSort", teacherSort);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		TeacherSort teacherSort = this.teacherSortService.findTeacherSortById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("teacherSort", teacherSort);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, TeacherSort teacherSort) {
		if (teacherSort != null) {
			teacherSort.setId(id);
		}
		try {
			this.teacherSortService.remove(teacherSort);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TeacherSort teacherSort) {
		teacherSort.setId(id);
		teacherSort = this.teacherSortService.modify(teacherSort);
		return teacherSort != null ? new ResponseInfomation(teacherSort.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, TeacherSortCondition condition) {
		Integer schoolId = user.getSchoolId();
		if(schoolId != null){
			condition.setSchoolId(schoolId);
		}
		condition.setIsDelete(false);
	}
}
