package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.model.TextbookManager;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TextbookManagerCondition;
import platform.education.generalcode.model.Textbook;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.vo.GradeCondition;
import platform.education.generalcode.vo.TextbookCondition;
import platform.education.generalcode.vo.TextbookVolumnCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/textbookManager")
public class TextbookManagerController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/textbookManager";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TextbookManagerCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		order.setAscending(false);
		condition.setSchoolId(user.getSchoolId());
		List<TextbookManager> items = this.textbookManagerService.findTextbookManagerByCondition(condition, page, order);
		for (TextbookManager textbookManager : items) {
			
			Teacher teacher = this.teacherService.findTeacherById(textbookManager.getTeacherId());
			if(teacher!= null&&teacher.getId()>0 ){
				textbookManager.setTeacherName(teacher.getName());
			}
			
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		
		

		mav.addObject("items", items);
		mav.setViewName(viewPath);
		return mav;
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<TextbookManager> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TextbookManagerCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.textbookManagerService.findTextbookManagerByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user) {
		
		ModelAndView mav = new ModelAndView();
		String initSelect = "stageCode";
//		String[] stageCodes = null;
//		if(user != null&&user.getStageCodes().length>0){
//			stageCodes = user.getStageCodes();
//		}
		mav.addObject(initSelect + "Map",
				this.findTextBookMap(user,null, null, null, null, initSelect));
		mav.setViewName(structurePath("/add"));
		return mav;
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TextbookManager textbookManager, 
			@CurrentUser UserInfo user) {
		textbookManager.setSchoolId(user.getSchoolId());
		String gradeCode = textbookManager.getGradeCode();
		String volumn = textbookManager.getVolumn();
		if(gradeCode != null && !"".equals(gradeCode)){
			//List<Grade> gradeList = this.gradeService.findGradeByCode(gradeCode);
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setCode(gradeCode);
			List<platform.education.generalcode.model.Grade> gradeList =  this.jcGradeService.findGradeByCondition(gradeCondition, null, null);
			if(gradeList != null&&gradeList.size()>0){
				textbookManager.setGradeCodeName(gradeList.get(0).getName());
			}else{
				textbookManager.setGradeCodeName("");
			}
		}else{
			if(volumn != null && !"".equals(volumn)){
				textbookManager.setGradeCodeName("高中");
			}
		}
		
		if(volumn != null && !"".equals(volumn)){
			TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
			textbookVolumnCondition.setCode(volumn);
			List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
			if(volumnList != null&&volumnList.size()>0){
				textbookManager.setVolumnName(volumnList.get(0).getName());
			}else{
				textbookManager.setVolumnName("");
			}
		}
		textbookManager = this.textbookManagerService.add(textbookManager);
		return textbookManager != null ? new ResponseInfomation(textbookManager.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		TextbookManager textbookManager = this.textbookManagerService.findTextbookManagerById(id);
		model.addAttribute("textbookManager", textbookManager);
		return new ModelAndView(structurePath("/modify"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		TextbookManager textbookManager = this.textbookManagerService.findTextbookManagerById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("textbookManager", textbookManager);
		return new ModelAndView(structurePath("/view"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, TextbookManager textbookManager) {
		if (textbookManager != null) {
			textbookManager.setId(id);
		}
		try {
			this.textbookManagerService.remove(textbookManager);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TextbookManager textbookManager) {
		textbookManager.setId(id);
		textbookManager = this.textbookManagerService.modify(textbookManager);
		return textbookManager != null ? new ResponseInfomation(textbookManager.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	
	@RequestMapping(value = "/textBookMap")
	@ResponseBody
	public Map<String, String> findTextBookMap(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type) {
		Map<String, String> map = new HashMap<String, String>();
		if(!"name".equals(type)){
			String[] stageCodes = null;
			if(user != null&&user.getStageCodes().length>0){
				stageCodes = user.getStageCodes();
			}
			map= this.jcTextbookMasterService.findTextBook(stageCodes,stageCode,
					subjectCode, gradeCodeVolumn, version, type);
		}else{
			TextbookCondition condition = new TextbookCondition();
			condition.setStageCode(stageCode);
			condition.setSubjectCode(subjectCode);
			//condition.setGradeCode(gradeCode);
			//condition.setVolumn(volumn);
			condition.setVersion(version);
			
			List<Textbook> list = this.jcTextbookMasterService.findTextbookByCondition(condition);
			for (Textbook textbook : list) {
				map.put("请选择", "");
				map.put(textbook.getName(), textbook.getName());
			}
			 
		}
		
		return map;
	}
	
	@RequestMapping(value = "/findStudentNumFromTeam")
	@ResponseBody
	public String  findStudentNumFromTeam(
			
			@RequestParam(value = "teamId", required = true) Integer teamId,
			@RequestParam(value = "gradeId", required = true) Integer gradeId) {
		TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
		teamStudentCondition.setGradeId(gradeId);
		teamStudentCondition.setTeamId(teamId);
		String num = "0";
		List<TeamStudent> list = this.teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);
		if(list != null&&list.size()>0){
			num = list.size()+"";
		}
		
		return num;
	}
	
	@RequestMapping(value = "/findTeacherFromTeam")
	@ResponseBody
	public List<TeamTeacher>  findTeacherFromTeam(
			
			@RequestParam(value = "teamId", required = true) Integer teamId,
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			@RequestParam(value = "schoolYear", required = true) String schoolYear,
			@CurrentUser UserInfo user
			) {
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setSchoolId(user.getSchoolId());
		teamTeacherCondition.setSchoolYear(schoolYear);
		teamTeacherCondition.setGradeId(gradeId);
		teamTeacherCondition.setTeamId(teamId);
		teamTeacherCondition.setDelete(false);
		List<TeamTeacher> listResult = new ArrayList<TeamTeacher>();
		List<TeamTeacher> list =this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
		//List<TeamTeacher> list = this.teamTeacherService.findClassTeacherByGradeIdAndTeamId(gradeId, teamId);
		if(list != null&&list.size()>0){
			Map<Integer, TeamTeacher> map = new HashMap<Integer, TeamTeacher>();
			
			for (TeamTeacher teamTeacher : list) {
				map.put(teamTeacher.getTeacherId(), teamTeacher);
			}
			
			Set<Integer> set = map.keySet();
			for (Integer key : set) {
				listResult.add(map.get(key));
			}
			
		}else{
			listResult = new ArrayList<TeamTeacher>();
		}
		
		return listResult;
	}
	
	@RequestMapping(value = "/textBook")
	@ResponseBody
	public  Textbook  findTextBook(
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type) {
		Map<String, Textbook> map = new HashMap<String, Textbook>();
			Textbook textbook = new Textbook();
			TextbookCondition condition = new TextbookCondition();
			condition.setStageCode(stageCode);
			condition.setSubjectCode(subjectCode);
			String gradeCode = null;
			String volumn = null;
			if(gradeCodeVolumn != null&&!"".equals(gradeCodeVolumn.trim())){
				String[] array = gradeCodeVolumn.split("-");
				if(array != null&&array.length == 2){
					gradeCode = array[0];
					volumn = array[1];
				}
			}
			
			condition.setGradeCode(gradeCode);
			condition.setVolumn(volumn);
			condition.setVersion(version);
			
			List<Textbook> list = this.jcTextbookMasterService.findTextbookByCondition(condition);
			if(list != null&&list.size() == 1){
				textbook = list.get(0);
				if(textbook.getName() == null||"".equals(textbook.getName())){
					textbook.setName("书籍还未确定名称");
				}
				if(textbook.getIsbn() == null||"".equals(textbook.getIsbn())){
					textbook.setIsbn("0");
				}
				if(textbook.getType() == null||"".equals(textbook.getType())){
					textbook.setType("0");
				}
				if(textbook.getPrice() == null||"".equals(textbook.getPrice())){
					textbook.setPrice("0");
				}
			}else{
				textbook.setName("不存在的书籍名称");
				textbook.setIsbn("0");
				textbook.setPrice("0");
				textbook.setType("0");
			}
			//ModelAndView mav = new ModelAndView();
			map.put("textbook", textbook);
			
			
			return textbook;
	}

}
