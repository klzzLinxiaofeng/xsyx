package platform.szxyzxx.web.micro.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.micro.contants.MicroType;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.model.MicroLessonHot;
import platform.education.micro.service.MicroLessonHotService;
import platform.education.micro.service.MicroLessonService;
import platform.education.micro.vo.MicroLessonCondition;
import platform.education.micro.vo.MicroLessonHotCondition;
import platform.education.micro.vo.MicroLessonHotVo;
import platform.education.micro.vo.MicroLessonVo;
import platform.education.user.model.Group;
import platform.education.user.service.GroupService;
import platform.education.user.service.GroupUserService;
import platform.education.user.service.UserService;
import platform.education.user.vo.GroupCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/wkx/microlessonhot")
public class MicroLessonHotController { 
	
	private final static String viewBasePath = "wkx/hot";
	
	@Resource
	private MicroLessonHotService microLessonHotService;
	
	 //微课
    @Resource
    private MicroLessonService microLessonService;
    
    @Resource
    private TeacherService teacherService;
    
    @Resource
    private GroupService groupService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private GroupUserService groupUserService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MicroLessonHotCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<MicroLessonHot> items = this.microLessonHotService.findMicroLessonHotByCondition(condition, page, order);
		List<MicroLessonHotVo> voList = new ArrayList<MicroLessonHotVo>();
		for(MicroLessonHot mlh:items){
			MicroLessonHotVo vo = new MicroLessonHotVo();
			BeanUtils.copyProperties(mlh, vo);
			Integer microId = mlh.getLessonId();
			if(microId != null){
				MicroLesson ml = microLessonService.findMicroLessonById(microId);
				if(ml != null ){
					String lessonUuid = ml.getUuid();
					vo.setMicroUuid(lessonUuid);
				}
			}
			voList.add(vo);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", voList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<MicroLessonHot> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") MicroLessonHotCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.microLessonHotService.findMicroLessonHotByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/create")
	public ModelAndView create(
			@CurrentUser UserInfo user,
			Model model,
			MicroLessonCondition microLessonCondition,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		microLessonCondition.setType(MicroType.MICRO_COURSE);
		List<MicroLesson> list = microLessonService.findMicroLessonByCondition(microLessonCondition, page, order);
		List<MicroLessonVo> voList = new ArrayList<MicroLessonVo>();
		for(MicroLesson ml : list){
			MicroLessonVo vo = new MicroLessonVo();
			Integer userId = ml.getUserId();
			TeacherCondition teacherCondition = new TeacherCondition(); 
			teacherCondition.setUserId(userId);
			teacherCondition.setSchoolId(user.getSchoolId());
			List<Teacher> teachers = teacherService.findTeacherByCondition(teacherCondition, null, null);
			BeanUtils.copyProperties(ml, vo);
			if(teachers != null && teachers.size() > 0){
				Teacher teacher = teachers.get(0);
				vo.setName(teacher.getName());
			}
			voList.add(vo);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/input_list");
		} else {
			viewPath = structurePath("/input");
		}
		model.addAttribute("list", voList);
		return new ModelAndView(viewPath);
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(MicroLessonHot microLessonHot, @CurrentUser UserInfo user) {
		MicroLesson ml = microLessonService.findMicroLessonById(microLessonHot.getLessonId());
		TeacherCondition teacherCondition = new TeacherCondition(); 
		if(ml != null){
			teacherCondition.setUserId(ml.getUserId());
			microLessonHot.setTitle(ml.getTitle());
			teacherCondition.setSchoolId(user.getSchoolId());
			List<Teacher> teachers = teacherService.findTeacherByCondition(teacherCondition, null, null);
			String lessonAuthor = null;
			if(teachers != null && teachers.size() > 0){
				Teacher teacher = teachers.get(0);
				lessonAuthor = teacher.getName();
			}
			microLessonHot.setLessonAuthor(lessonAuthor);
		}
		microLessonHot.setPushState("0");
		microLessonHot = this.microLessonHotService.add(microLessonHot);
		return microLessonHot != null ? new ResponseInfomation(microLessonHot.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/batchCreate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation batchCreate(@RequestParam(value = "lessonIds")String lessonIds, @CurrentUser UserInfo user) {
		String[] ids = null;
		if(lessonIds.contains(",")){
			ids = lessonIds.split(",");
		}else{
			ids =  new String[]{lessonIds};
		}
		if(ids.length > 0){
			for(String i:ids){
				MicroLessonHot microLessonHot = new MicroLessonHot();
				MicroLesson ml = microLessonService.findMicroLessonById(Integer.parseInt(i));
				TeacherCondition teacherCondition = new TeacherCondition(); 
				if(ml != null){
					teacherCondition.setUserId(ml.getUserId());
					microLessonHot.setTitle(ml.getTitle());
					teacherCondition.setSchoolId(user.getSchoolId());
					List<Teacher> teachers = teacherService.findTeacherByCondition(teacherCondition, null, null);
					String lessonAuthor = null;
					if(teachers != null && teachers.size() > 0){
						Teacher teacher = teachers.get(0);
						lessonAuthor = teacher.getName();
					}
					microLessonHot.setLessonAuthor(lessonAuthor);
					microLessonHot.setLessonId(ml.getId());
				}
				microLessonHot.setPushState("0");
				try {
					microLessonHot = this.microLessonHotService.add(microLessonHot);
				} catch (Exception e) {
					return  new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
				}
			}
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		MicroLessonHot microLessonHot = this.microLessonHotService.findMicroLessonHotById(id);
		MicroLessonCondition microLessonCondition = new MicroLessonCondition();
		microLessonCondition.setType(MicroType.MICRO_COURSE);
		List<MicroLesson> list = microLessonService.findMicroLessonByCondition(microLessonCondition);
		List<MicroLessonVo> voList = new ArrayList<MicroLessonVo>();
		for(MicroLesson ml : list){
			MicroLessonVo vo = new MicroLessonVo();
			Integer userId = ml.getUserId();
			TeacherCondition teacherCondition = new TeacherCondition(); 
			teacherCondition.setUserId(userId);
			teacherCondition.setSchoolId(user.getSchoolId());
			List<Teacher> teachers = teacherService.findTeacherByCondition(teacherCondition, null, null);
			BeanUtils.copyProperties(ml, vo);
			if(teachers != null && teachers.size() > 0){
				Teacher teacher = teachers.get(0);
				vo.setName(teacher.getName());
			}
			voList.add(vo);
		}
		model.addAttribute("list", voList);
		model.addAttribute("microLessonHot", microLessonHot);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		MicroLessonHot microLessonHot = this.microLessonHotService.findMicroLessonHotById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("microLessonHot", microLessonHot);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, MicroLessonHot microLessonHot) {
		if (microLessonHot != null) {
			microLessonHot.setId(id);
		}
		try {
			this.microLessonHotService.remove(microLessonHot);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@CurrentUser UserInfo user,@PathVariable(value = "id") Integer id, MicroLessonHot microLessonHot) {
		MicroLesson ml = microLessonService.findMicroLessonById(microLessonHot.getLessonId());
		TeacherCondition teacherCondition = new TeacherCondition(); 
		if(ml != null){
			teacherCondition.setUserId(ml.getUserId());
			microLessonHot.setTitle(ml.getTitle());
			teacherCondition.setSchoolId(user.getSchoolId());
			List<Teacher> teachers = teacherService.findTeacherByCondition(teacherCondition, null, null);
			String lessonAuthor = null;
			if(teachers != null && teachers.size() > 0){
				Teacher teacher = teachers.get(0);
				lessonAuthor = teacher.getName();
			}
			microLessonHot.setLessonAuthor(lessonAuthor);
		}
		microLessonHot.setId(id);
		microLessonHot = this.microLessonHotService.modify(microLessonHot);
		return microLessonHot != null ? new ResponseInfomation(microLessonHot.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
