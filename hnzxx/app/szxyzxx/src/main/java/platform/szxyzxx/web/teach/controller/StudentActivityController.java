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
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.StudentActivity;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.StudentActivityService;
import platform.education.generalTeachingAffair.vo.StudentActivityCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/teach/studentActivity")
public class StudentActivityController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/studentActivity";
	
	@Autowired
	@Qualifier("studentActivityService")
	private StudentActivityService studentActivityService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") StudentActivityCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<StudentActivity> items = this.studentActivityService.findStudentActivityByCondition(condition, page, order);
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
	public List<StudentActivity> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") StudentActivityCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.studentActivityService.findStudentActivityByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(StudentActivity studentActivity, @CurrentUser UserInfo user) {
		studentActivity.setSchoolId(user.getSchoolId());
		studentActivity.setCreateDate(new Date());
		studentActivity.setModifyDate(new Date());
		//通过id找到name
		String masterId = studentActivity.getMasterId();
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
		studentActivity.setMasterName(masterName.toString());
		//通过教职工的id找到name
		String teachId = studentActivity.getTeachId();
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
		studentActivity.setTeachName(teachName.toString());
		//通过学生的id找到name
		String studentId = studentActivity.getStudentId();
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
		studentActivity.setStudentName(studentName.toString());
		studentActivity = this.studentActivityService.add(studentActivity);
		return studentActivity != null ? new ResponseInfomation(studentActivity.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		StudentActivity studentActivity = this.studentActivityService.findStudentActivityById(id);
		if(studentActivity!=null){
			EntityFile entity = this.entityFileService.findFileByUUID(studentActivity.getFileUuid());
			model.addAttribute("entity", entity);
		}
		model.addAttribute("studentActivity",studentActivity);
		model.addAttribute("isCK",isCK);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, StudentActivity studentActivity) {
		if (studentActivity != null) {
			studentActivity.setId(id);
		}
		return  this.studentActivityService.moveTo(studentActivity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, 
			StudentActivity studentActivity,
			@CurrentUser UserInfo user) {
		studentActivity.setId(id);
		studentActivity.setSchoolId(user.getSchoolId());
		studentActivity.setModifyDate(new Date());
		String masterId = studentActivity.getMasterId();
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
		studentActivity.setMasterName(masterName.toString());
		String teachId = studentActivity.getTeachId();
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
		studentActivity.setTeachName(teachName.toString());
		String studentId = studentActivity.getStudentId();
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
		studentActivity.setStudentName(studentName.toString());
		studentActivity = this.studentActivityService.modify(studentActivity);
		return studentActivity != null ? new ResponseInfomation(studentActivity.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, StudentActivityCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
