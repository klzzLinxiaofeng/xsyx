package platform.szxyzxx.web.teach.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Practice;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.PracticeService;
import platform.education.generalTeachingAffair.vo.PracticeCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/teach/practice")
public class PracticeController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/practice";
	
	@Autowired
	@Qualifier("practiceService")
	private PracticeService practiceService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PracticeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Practice> items = this.practiceService.findPracticeByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Practice> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") PracticeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.practiceService.findPracticeByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addPractice(Practice practice,
			@CurrentUser UserInfo user) {
		practice.setSchoolId(user.getSchoolId());
		practice.setCreateDate(new Date());
		practice.setModifyDate(new Date());
		//通过id找到name
		String masterId = practice.getMasterId();
		StringBuffer masterName = new StringBuffer();
		String[] masterArr = masterId.split(";");
		for(int i=1;i<masterArr.length;i++){
			Teacher teacher = this.teacherService.findTeacherById(Integer.valueOf(masterArr[i]));
			if(teacher!=null){
				if(i==1){
					masterName.append(teacher.getName());
				}else{
					masterName.append(";"+teacher.getName());
				}
			}
		}
		practice.setMasterName(masterName.toString());
		//通过教职工的id找到name
		String teachId = practice.getTeachId();
		StringBuffer teachName = new StringBuffer();
		String[] teachArr = teachId.split(";");
		for(int i=1;i<teachArr.length;i++){
			Teacher teacher = this.teacherService.findTeacherById(Integer.valueOf(teachArr[i]));
			if(teacher!=null){
				if(i==1){
					teachName.append(teacher.getName());
				}else{
					teachName.append(";"+teacher.getName());
				}
			}
		}
		practice.setTeachName(teachName.toString());
		//通过学生的id找到name
		String studentId = practice.getStudentId();
		StringBuffer studentName = new StringBuffer();
		String[] studentArr = studentId.split(";");
		for(int i=1;i<studentArr.length;i++){
			Student student = this.studentService.findStudentById(Integer.valueOf(studentArr[i]));
			if(student!=null){
				if(i==1){
					studentName.append(student.getName());
				}else{
					studentName.append(";"+student.getName());
				}
			}
		}
		practice.setStudentName(studentName.toString());
		practice = this.practiceService.add(practice);
		return practice != null ? new ResponseInfomation(practice.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		Practice practice = this.practiceService.findPracticeById(id);
		if(practice!=null){
			EntityFile entity = this.entityFileService.findFileByUUID(practice.getFileUuid());
			model.addAttribute("entity", entity);
		}
		model.addAttribute("practice",practice);
		model.addAttribute("isCK",isCK);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Practice practice) {
		if (practice != null) {
			practice.setId(id);
		}
		return  this.practiceService.moveTo(practice);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, 
			Practice practice,
			@CurrentUser UserInfo user) {
		practice.setId(id);
		practice.setSchoolId(user.getSchoolId());
		practice.setModifyDate(new Date());
		String masterId = practice.getMasterId();
		StringBuffer masterName = new StringBuffer();
		String[] masterArr = masterId.split(";");
		for(int i=1;i<masterArr.length;i++){
			Teacher teacher = this.teacherService.findTeacherById(Integer.valueOf(masterArr[i]));
			if(teacher!=null){
				if(i==1){
					masterName.append(teacher.getName());
				}else{
					masterName.append(";"+teacher.getName());
				}
			}
		}
		practice.setMasterName(masterName.toString());
		String teachId = practice.getTeachId();
		StringBuffer teachName = new StringBuffer();
		String[] teachArr = teachId.split(";");
		for(int i=1;i<teachArr.length;i++){
			Teacher teacher = this.teacherService.findTeacherById(Integer.valueOf(teachArr[i]));
			if(teacher!=null){
				if(i==1){
					teachName.append(teacher.getName());
				}else{
					teachName.append(";"+teacher.getName());
				}
			}
		}
		practice.setTeachName(teachName.toString());
		String studentId = practice.getStudentId();
		StringBuffer studentName = new StringBuffer();
		String[] studentArr = studentId.split(";");
		for(int i=1;i<studentArr.length;i++){
			Student student = this.studentService.findStudentById(Integer.valueOf(studentArr[i]));
			if(student!=null){
				if(i==1){
					studentName.append(student.getName());
				}else{
					studentName.append(";"+student.getName());
				}
			}
		}
		practice.setStudentName(studentName.toString());
		practice = this.practiceService.modify(practice);
		return practice != null ? new ResponseInfomation(practice.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, PracticeCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
