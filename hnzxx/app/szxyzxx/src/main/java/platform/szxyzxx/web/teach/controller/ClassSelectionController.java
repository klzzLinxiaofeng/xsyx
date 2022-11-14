package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.Date;
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

import platform.education.generalTeachingAffair.model.ClassSelection;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.PublicClass;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.ClassSelectionCondition;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.PublicClassCondition;
import platform.education.generalTeachingAffair.vo.PublicClassVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/classSelection")
public class ClassSelectionController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/classSelection";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PublicClassCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		publicClassconditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		//判断当前用户是否是一名学生
		if(user.getStudentId() != null) {
			List<PublicClass> items = this.publicClassService.findPublicClassByCondition(condition, page, order);
			model.addAttribute("items", toVos(items, user));
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/*
	 * 家长报名部分--开始
	 */
	@RequestMapping(value = "/parentIndex")
	public ModelAndView parentIndex(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PublicClassCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		publicClassconditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		
		List<ParentStudent> psList = new ArrayList<ParentStudent>();
		String userTypes = user.getUserTypes();
		if(userTypes.contains("3")) {
			ParentStudentCondition psCondition = new ParentStudentCondition();
			psCondition.setParentUserId(user.getId());
			psCondition.setIsDelete(false);
			psList = this.parentStudentService.findParentStudentByCondition(psCondition);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/parent_list");
		} else {
			viewPath = structurePath("/parent_index");
		}
		
		model.addAttribute("psList", psList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/getPublicClass", method = RequestMethod.POST)
	public ModelAndView getPublicClass(
			@RequestParam(value = "studentUserId", required = true) Integer studentUserId,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, 
			@CurrentUser UserInfo user,
			Model model) {
		String viewPath = null;
		PublicClassCondition condition = new PublicClassCondition();
//		publicClassconditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
//			Student student = this.studentService.findOfUser(user.getSchoolId(), studentUserId);
			Student student = this.studentService.findStudentByUserId(studentUserId);
			if(student != null) {
//				Integer studentId = student.getId();
				condition.setIsDelete(false);
				condition.setSchoolId(student.getSchoolId());
				List<PublicClass> items = this.publicClassService.findPublicClassByCondition(condition, page, order);
				model.addAttribute("items", toVoOthers(items, student));
			}
			viewPath = structurePath("/parent_list");
			return new ModelAndView(viewPath, model.asMap());
	}
	
	/*
	 * 根据studentUserId获得相应的学生信息
	 */
	public Student studentUserInfo(Integer studentUserId) {
		Student student = this.studentService.findStudentByUserId(studentUserId);
		return student;
	}
	
	private List<PublicClassVo> toVoOthers(List<PublicClass> publicClassList, Student student) {
		List<PublicClassVo> voList = new ArrayList<PublicClassVo>();
		for(PublicClass publicClass : publicClassList) {
			voList.add(toVoOther(publicClass, student));
		}
		return voList;
	}
	
	private PublicClassVo toVoOther(PublicClass publicClass, Student student) {
		PublicClassVo vo = new PublicClassVo();
		BeanUtils.copyProperties(publicClass, vo);
		//获取当前系统时间判断报名时间是否已经截止 after当前时间在报名截止时间之后 
		Date date = new Date();
		date.setDate(date.getDate() - 1);
		Date expiryDate= publicClass.getExpiryDate() ;
		Boolean checkDate = date.before(expiryDate);
		
		Integer enrollNumber = publicClass.getEnrollNumber();  //已报名人数
		Integer maxMember = publicClass.getMaxMember();       //报名人数上限
		Boolean checkNumber = enrollNumber.equals(maxMember);
		
		//是否可以报名  "0"--不可以报名   "1"--可以报名
		if(checkDate) {  //当前时间在报名时间截止之前且报名人数未达到上限时可报名
			vo.setIsEnroll("1"); 
		}else if(!checkDate){   //当前时间在报名时间截止之后不可报名
			vo.setIsEnroll("0");
		}
//		Teacher teacher = this.teacherService.findTeacherById(publicClass.getTeacherId());
//		if(teacher != null) {
//			vo.setTeacherName(teacher.getName());
//		}
		
		//学生报名状态
		String enrollState = enrollStateOther(publicClass.getId(), student);
		vo.setEnrollState(enrollState);
		
		//如果报名人数已达到上限，未报名的学生不可进行报名，已报名的学生可取消报名
		if(checkNumber && "0".equals(enrollState)) {
			enrollState = "2";
			vo.setEnrollState(enrollState);
		}
		return vo;
	} 
	
	/**
	 * 学生报名状态
	 * @param publicClassId
	 * @return
	 */
	public String enrollStateOther(Integer publicClassId, Student student) {
		String enrollState = "";
		ClassSelection classSelection = new ClassSelection();
		ClassSelectionCondition condition = new ClassSelectionCondition();
		condition.setSchoolId(student.getSchoolId());
		condition.setPublicClassId(publicClassId);
		condition.setStudentId(student.getId());
		condition.setIsDelete(false);
		classSelection = this.publicClassService.findClassSelectionByPartCondition(condition);
		//enrollState "0"--未报名  "1"--已报名
		if(classSelection != null) {
			enrollState = "1";
		}else {
			enrollState = "0";
		}
		return enrollState;
	}
	
	/**
	 * 学生报名
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/enrollOther", method = RequestMethod.POST)
	@ResponseBody
	public String enrollOther(
			@RequestParam(value = "classId", required = true) Integer classId,
			@RequestParam(value = "studentUserId", required = true) Integer studentUserId) {
		Boolean checkNumber = checkNumber(classId);
		if(!checkNumber) {
			Student student = this.studentService.findStudentByUserId(studentUserId);
			if(student != null) {
				ClassSelection classSelection = new ClassSelection();
				classSelection.setSchoolId(student.getSchoolId());
				classSelection.setPublicClassId(classId);
				classSelection.setStudentId(student.getId());
//				Student student = this.studentService.findStudentById(user.getStudentId());
				Integer teamId = student.getTeamId();
				if(teamId == null) {
					return "forbid";
				}else{
					classSelection.setTeamId(teamId);
				}
				
				classSelection = this.publicClassService.add(classSelection);
				
				//学生报名成功， 公开课的报名人数加1
				if(classSelection != null) {
					PublicClass publicClass = this.publicClassService.findPublicClassById(classId);
					PublicClass temp = new PublicClass();
					if(publicClass != null) {
						Integer enrollNumber = publicClass.getEnrollNumber();
						enrollNumber = enrollNumber + 1;
						temp.setId(publicClass.getId());
						temp.setEnrollNumber(enrollNumber);
						temp = this.publicClassService.modify(temp);
					}
					return "success";
				}
			}
		}
		return "fail";
	}
	
	/**
	 * 学生取消报名
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/cancelEnrollOther", method = RequestMethod.POST)
	@ResponseBody
	public String cancelEnrollOther(@RequestParam(value = "classId", required = true) Integer classId,
			@RequestParam(value = "studentUserId", required = true) Integer studentUserId) {
		String msg = "";
		Student student = this.studentService.findStudentByUserId(studentUserId);
		if(student != null) {
			ClassSelection classSelection = new ClassSelection();
			ClassSelectionCondition condition = new ClassSelectionCondition();
			condition.setSchoolId(student.getSchoolId());
			condition.setPublicClassId(classId);
			condition.setStudentId(student.getId());
			condition.setIsDelete(false);
			classSelection = this.publicClassService.findClassSelectionByPartCondition(condition);
			if(classSelection != null) {
				msg = this.publicClassService.abandon(classSelection);
				//学生取消报名成功， 公开课的报名人数减1
				PublicClass publicClass = this.publicClassService.findPublicClassById(classId);
				PublicClass temp = new PublicClass();
				if(publicClass != null) {
					Integer enrollNumber = publicClass.getEnrollNumber();
					enrollNumber = enrollNumber - 1;
					temp.setId(publicClass.getId());
					temp.setEnrollNumber(enrollNumber);
					temp = this.publicClassService.modify(temp);
				}
			}
		}
		return msg;
	}
	/*
	 * 家长报名部分--结束
	 */
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<ClassSelection> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ClassSelectionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.publicClassService.findClassSelectionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(ClassSelection classSelection, @CurrentUser UserInfo user) {
		classSelection = this.publicClassService.add(classSelection);
		return classSelection != null ? new ResponseInfomation(classSelection.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		ClassSelection classSelection = this.publicClassService.findClassSelectionById(id);
		model.addAttribute("classSelection", classSelection);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			Model model) {
		PublicClass publicClass = this.publicClassService.findPublicClassById(id);
		model.addAttribute("isCK", "disable");
		if(publicClass != null) {
			model.addAttribute("publicClass", toVo(publicClass, user));
		}
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ClassSelection classSelection) {
		if (classSelection != null) {
			classSelection.setId(id);
		}
		try {
			this.publicClassService.remove(classSelection);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, ClassSelection classSelection) {
		classSelection.setId(id);
		classSelection = this.publicClassService.modify(classSelection);
		return classSelection != null ? new ResponseInfomation(classSelection.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ClassSelectionCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
	
	private void publicClassconditionFilter(UserInfo user, PublicClassCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
	
	private List<PublicClassVo> toVos(List<PublicClass> publicClassList, UserInfo user) {
		List<PublicClassVo> voList = new ArrayList<PublicClassVo>();
		for(PublicClass publicClass : publicClassList) {
			voList.add(toVo(publicClass, user));
		}
		return voList;
	}
	
	private PublicClassVo toVo(PublicClass publicClass, UserInfo user) {
		PublicClassVo vo = new PublicClassVo();
		BeanUtils.copyProperties(publicClass, vo);
		//获取当前系统时间判断报名时间是否已经截止 after当前时间在报名截止时间之后 
		Date date = new Date();
		date.setDate(date.getDate() - 1);
 		Date expiryDate= publicClass.getExpiryDate();
		Boolean checkDate = date.before(expiryDate);
		
		Integer enrollNumber = publicClass.getEnrollNumber();  //已报名人数
		Integer maxMember = publicClass.getMaxMember();       //报名人数上限
		Boolean checkNumber = enrollNumber.equals(maxMember);
		
		//是否可以报名  "0"--不可以报名   "1"--可以报名
		if(checkDate) {  //当前时间在报名时间截止之前且报名人数未达到上限时可报名
			vo.setIsEnroll("1"); 
		}else if(!checkDate){   //当前时间在报名时间截止之后不可报名
			vo.setIsEnroll("0");
		}
//		Teacher teacher = this.teacherService.findTeacherById(publicClass.getTeacherId());
//		if(teacher != null) {
//			vo.setTeacherName(teacher.getName());
//		}
		
		//学生报名状态
		String enrollState = enrollState(publicClass.getId(), user);
		vo.setEnrollState(enrollState);
		
		//如果报名人数已达到上限，未报名的学生不可进行报名，已报名的学生可取消报名
		if(checkNumber && "0".equals(enrollState)) {
			enrollState = "2";
			vo.setEnrollState(enrollState);
		}
		
		return vo;
	} 
	
	/**
	 * 学生报名状态
	 * @param publicClassId
	 * @param user
	 * @return
	 */
	public String enrollState(Integer publicClassId, UserInfo user) {
		String enrollState = "";
		ClassSelection classSelection = new ClassSelection();
		ClassSelectionCondition condition = new ClassSelectionCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setPublicClassId(publicClassId);
		condition.setStudentId(user.getStudentId());
		condition.setIsDelete(false);
		classSelection = this.publicClassService.findClassSelectionByPartCondition(condition);
		//enrollState "0"--未报名  "1"--已报名
		if(classSelection != null) {
			enrollState = "1";
		}else {
			enrollState = "0";
		}
		return enrollState;
	}
	
	//查看是否已达到人数上限
	private Boolean checkNumber(Integer publicClassId) {
		PublicClass publicClass = this.publicClassService.findPublicClassById(publicClassId);
		Boolean checkNumber = null;
		if(publicClass != null) {
			Integer maxMenber = publicClass.getMaxMember();
			Integer enrollNumber = publicClass.getEnrollNumber();
			 checkNumber = enrollNumber.equals(maxMenber);
		}
		return checkNumber;
	}
	
	/**
	 * 学生报名
	 * @param classId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	@ResponseBody
	public String enroll(@RequestParam(value = "classId", required = true) Integer classId,
			@CurrentUser UserInfo user) {
		Boolean checkNumber = checkNumber(classId);
		if(!checkNumber) {
			ClassSelection classSelection = new ClassSelection();
			classSelection.setSchoolId(user.getSchoolId());
			classSelection.setPublicClassId(classId);
			classSelection.setStudentId(user.getStudentId());
			Student student = this.studentService.findStudentById(user.getStudentId());
			if(student != null) {
				Integer teamId = student.getTeamId();
				if(teamId == null) {
					return "forbid";
				}else{
					classSelection.setTeamId(teamId);
				}
			}
			classSelection = this.publicClassService.add(classSelection);
			
			//学生报名成功， 公开课的报名人数加1
			if(classSelection != null) {
				PublicClass publicClass = this.publicClassService.findPublicClassById(classId);
				PublicClass temp = new PublicClass();
				if(publicClass != null) {
					Integer enrollNumber = publicClass.getEnrollNumber();
					enrollNumber = enrollNumber + 1;
					temp.setId(publicClass.getId());
					temp.setEnrollNumber(enrollNumber);
					temp = this.publicClassService.modify(temp);
				}
				return "success";
			}
		}
		return "fail";
	}
	
	/**
	 * 学生取消报名
	 * @param classId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/cancelEnroll", method = RequestMethod.POST)
	@ResponseBody
	public String cancelEnroll(@RequestParam(value = "classId", required = true) Integer classId,
			@CurrentUser UserInfo user) {
		String msg = "";
		ClassSelection classSelection = new ClassSelection();
		ClassSelectionCondition condition = new ClassSelectionCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setPublicClassId(classId);
		condition.setStudentId(user.getStudentId());
		condition.setIsDelete(false);
		classSelection = this.publicClassService.findClassSelectionByPartCondition(condition);
		if(classSelection != null) {
			msg = this.publicClassService.abandon(classSelection);
			//学生取消报名成功， 公开课的报名人数减1
			PublicClass publicClass = this.publicClassService.findPublicClassById(classId);
			PublicClass temp = new PublicClass();
			if(publicClass != null) {
				Integer enrollNumber = publicClass.getEnrollNumber();
				enrollNumber = enrollNumber - 1;
				temp.setId(publicClass.getId());
				temp.setEnrollNumber(enrollNumber);
				temp = this.publicClassService.modify(temp);
			}
		}
		return msg;
	}
	
}
