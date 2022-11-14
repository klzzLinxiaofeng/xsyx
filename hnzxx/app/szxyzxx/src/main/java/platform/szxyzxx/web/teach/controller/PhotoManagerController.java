package platform.szxyzxx.web.teach.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherVo;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalcode.cache.Order;
import platform.education.generalcode.cache.Page;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
/**
 * @function 主要用于教务模块的照片上传功能
 * @author hmzhang
 * @date 2016年02月18日
 */

@Controller
@RequestMapping("/teach/photoManager")
public class PhotoManagerController extends BaseController{
	private final static String viewBasePath = "/teach/photoManager";
	

	
	/**
	 * @function 教师图片管理的首页
	 * @param model
	 * @return
	 * @date 2016年02月23日
	 */
	@RequestMapping(value = "/teacherIndex")
	public ModelAndView teacherIndex(Model model) {
		String viewPath = "/photo_teacher";
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	/**
	 * @function 学生图片管理页面
	 * @param model
	 * @return
	 * @date 2016年02月26日
	 */
	@RequestMapping(value = "/studentIndex")
	public ModelAndView studentIndex(Model model) {
		String viewPath = "/photo_student";
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	/**
	 * @function 获取部门列表
	 * @param model
	 * @param user
	 * @return
	 * @date 2016年02月19日
	 */
	@RequestMapping(value = "/list/dept")
	@ResponseBody
	public List<Department> deptList(Model model,@CurrentUser UserInfo user) {
		return this.departmentService.findDepartmentBySchoolId(user.getSchoolId(), null, null);
	}
	
	/**
	 * @function 获取部门下的老师
	 * @param model
	 * @return
	 * @date 2016年02月19日
	 */
	@RequestMapping(value = "/teacherList")
	public ModelAndView teacherList(Model model,@RequestParam(value = "departmentId", required = true) Integer departmentId,@CurrentUser UserInfo user) {
		
		
//		List<DepartmentTeacher> deptTeacherList  = this.departmentTeacherService.findByDepartmentId(departmentId);
		
		//选择“请选择”查询  时查询出所有的教师照片  测试人员提出的
		List<DepartmentTeacher> deptTeacherList = new ArrayList<DepartmentTeacher>();
		
		if(departmentId==null){
			DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
			condition.setIsDeleted(false);
			condition.setSchoolId(user.getSchoolId());
			deptTeacherList = this.departmentTeacherService.findDepartmentTeacherByCondition(condition, null, null);
			
		}else{
			deptTeacherList = this.departmentTeacherService.findByDepartmentId(departmentId);
			
		}
		List<DepartmentTeacherVo> voList = new ArrayList<DepartmentTeacherVo>();
		DepartmentTeacherVo vo = null;
		
		for(DepartmentTeacher dtt : deptTeacherList){
			Teacher teacher = this.teacherService.findTeacherById(dtt.getTeacherId());
			if(teacher!=null){
				vo = new DepartmentTeacherVo();
				BeanUtils.copyProperties(dtt, vo);
				//获取教师的userId，方便头像的显示
				vo.setTeacherUserId(teacher.getUserId());
				voList.add(vo);
			}
		}
		model.addAttribute("deptTeacherList",voList);
		String viewPath = "/teacher_list";
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	/**
	 * @function 点击修改教师图片
	 * @param model
	 * @param teacherId
	 * @param request
	 * @return
	 * @date 2016年02月26日
	 */
	@RequestMapping(value = "/updatePhoto")
	public ModelAndView updatePhoto(Model model,
			@RequestParam(value = "teacherId", required = true) Integer teacherId,
			HttpServletRequest request) {
		String viewPath = "/photo_import";
		Boolean isExist = false;
		Teacher teacher = null;
		teacher = this.teacherService.findTeacherById(teacherId);
		if(teacher!=null){
			Profile profile = this.profileService.findByUserId(teacher.getUserId());
			if(profile!=null){
				if(profile.getIcon()!=null&&profile.getIcon()!=""){
					isExist = true;
					model.addAttribute("icon",profile.getIcon());
				}
			}
		}
		model.addAttribute("isExist",isExist);
		model.addAttribute("user",teacher);
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	/**
	 * @function 头像的修改
	 * @param profile
	 * @param user
	 * @return
	 * @date 2016年02月23日
	 */
	@RequestMapping(value="/iconCreator")
	@ResponseBody
	public ResponseInfomation iconCreator(Profile profile,
			@CurrentUser UserInfo user) {
		User userTemp = this.userService.findUserById(profile.getUserId());
		if(userTemp!=null){
			profile.setUserName(userTemp.getUserName());
			profile.setCreateDate(new Date());
			profile.setModifyDate(new Date());
			profile.setIsDeleted(false);
		}
		Profile profileTemp = null;
		profileTemp = this.profileService.findByUserId(profile.getUserId());
		if(profileTemp!=null){
			profile = this.profileService.modify(profile);
		}else{
			profile = this.profileService.add(profile);
		}
		return profile != null ? new ResponseInfomation(profile.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * @function 批量导入
	 * @param model
	 * @return
	 * @date 2016年02月23日
	 */
	@RequestMapping(value = "/allPhoto")
	public ModelAndView allPhoto(Model model) {
		String viewPath = "/photo_teacher_production";
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	
	
	/**
	 * @function 批量添加照片
	 * @param deptId
	 * @param imgs
	 * @return
	 * @date 2016年02月26日
	 */
	@RequestMapping(value="/sendImgs")
	@ResponseBody
	public ResponseInfomation sendImgs(@RequestParam(value = "deptId", required = true) Integer deptId,
			@RequestParam(value = "imgs", required = true) String imgs) {
		
		List<DepartmentTeacher> deptTeacherList = this.departmentTeacherService.findByDepartmentId(deptId);
		try {
			Profile profile = null;
			List<Teacher>teacherList = new ArrayList<Teacher>();
			Integer imgsCount = 0;
			
			if(imgs != null && !"".equals(imgs)){
				JSONArray jsonArray = JSONArray.fromObject(imgs); 
				imgsCount= jsonArray.size();
				for(int i=0; i<jsonArray.size() ;i ++){
					JSONObject json = (JSONObject) jsonArray.get(i);
					String base64 = json.getString("base64");
					String fileName = json.getString("fileName");
					String normal64 = base64.substring(base64.indexOf(",")+1);
					byte[] bytes = Base64.decodeBase64(normal64);   //将字符串转换为byte数组
		            InputStream in = new ByteArrayInputStream(bytes);
		            FileResult result = this.fileService.upload(in, fileName.substring(fileName.lastIndexOf(".")+1), "", fileName, String.valueOf(SysContants.SYSTEM_APP_ID));
					String name = fileName .substring(0,fileName .lastIndexOf("."));//图片名称
					
					for(DepartmentTeacher dtt:deptTeacherList){
						if(dtt.getTeacherName().trim().equals(name.trim())){
							Teacher teacher = this.teacherService.findTeacherById(dtt.getTeacherId());
							if(teacher!=null){
								profile = this.profileService.findByUserId(teacher.getUserId());
								User userTemp = this.userService.findUserById(teacher.getUserId());
								if(userTemp!=null){
									if(profile!=null){
										profile.setUserName(userTemp.getUserName());
										profile.setCreateDate(new Date());
										profile.setModifyDate(new Date());
										profile.setIsDeleted(false);
										if(result!=null){
											profile.setIcon(result.getEntityFile().getUuid());
										}
										profile = this.profileService.modify(profile);
									}else{
										profile = new Profile();
										profile.setUserId(teacher.getUserId());
										profile.setUserName(userTemp.getUserName());
										profile.setCreateDate(new Date());
										profile.setModifyDate(new Date());
										profile.setIsDeleted(false);
										if(result!=null){
											profile.setIcon(result.getEntityFile().getUuid());
										}
										profile = this.profileService.add(profile);
									}
									
									teacherList.add(teacher);
								}
							}else{
								throw new Exception();
							}
						}
					}
				}
			}
			
			if (teacherList.size() == imgsCount) {

				return profile != null ? new ResponseInfomation(profile.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(); 

			} else {

				return new ResponseInfomation(null,
						ResponseInfomation.OUT_OF_ACCORD);
			}
			
			
		} catch (Exception e) {
			return new ResponseInfomation(null, ResponseInfomation.OPERATION_FAIL);
		}
	}
	
	/**
	 * @function 加载学生的列表
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @date 2016年02月29日
	 */
	@RequestMapping(value = "/studentList")
	@ResponseBody
	public ModelAndView getStudentList(TeamCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model){
		List<Student> studentList = this.studentService.findStudentByTeamId(condition.getId());
		model.addAttribute("studentList",studentList);
		String viewPath = "/student_list";
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	/**
	 * @function 用于修改学生照片
	 * @param model
	 * @param userId
	 * @param request
	 * @return
	 * @date 2016年02月29日
	 */
	@RequestMapping(value = "/updateStudentPhoto")
	public ModelAndView updateStudentPhoto(Model model,
			@RequestParam(value = "userId", required = true) Integer userId,
			HttpServletRequest request) {
		String viewPath = "/photo_import";
		Boolean isExist = false;
		Student student = this.studentService.findStudentByUserId(userId);
		Profile profile = this.profileService.findByUserId(userId);
		if(profile!=null){
			if(profile.getIcon()!=null&&profile.getIcon()!=""){
				isExist = true;
				model.addAttribute("icon",profile.getIcon());
			}
		}
		model.addAttribute("isExist",isExist);
		model.addAttribute("user",student);
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	/**
	 * @function 批量导入
	 * @param model
	 * @return
	 * @date 2016年02月29日  
	 */
	@RequestMapping(value = "/allStudentPhoto")
	public ModelAndView allStudentPhoto(Model model) {
		String viewPath = "/photo_student_production";
		return new ModelAndView(viewBasePath+viewPath, model.asMap());
	}
	
	@RequestMapping(value="/sendStudentImgs")
	@ResponseBody
	public ResponseInfomation sendStudentImgs(@RequestParam(value = "teamId", required = true) Integer teamId,
			@RequestParam(value = "imgs", required = true) String imgs) {
		List<Student> studentList = this.studentService.findStudentByTeamId(teamId);
		try {
			Profile profile = null;
			List<Student>stuList = new ArrayList<Student>();
			Integer imgsCount = 0;
			if(imgs != null && !"".equals(imgs)){
				JSONArray jsonArray = JSONArray.fromObject(imgs); 
				imgsCount = jsonArray.size();
				
				for(int i=0; i<jsonArray.size() ;i ++){
					JSONObject json = (JSONObject) jsonArray.get(i);
					String base64 = json.getString("base64");
					String fileName = json.getString("fileName");
					String normal64 = base64.substring(base64.indexOf(",")+1);
					byte[] bytes = Base64.decodeBase64(normal64);   //将字符串转换为byte数组
		            InputStream in = new ByteArrayInputStream(bytes);
		            FileResult result = this.fileService.upload(in, fileName.substring(fileName.lastIndexOf(".")+1), "", fileName, String.valueOf(SysContants.SYSTEM_APP_ID));
					String name = fileName .substring(0,fileName .lastIndexOf("."));//图片名称
					
					for(Student stu:studentList){
						if(stu.getName().trim().equals(name.trim())){
							profile = this.profileService.findByUserId(stu.getUserId());
							User userTemp = this.userService.findUserById(stu.getUserId());
							if(userTemp!=null){
								if(profile!=null){
									profile.setUserName(userTemp.getUserName());
									profile.setCreateDate(new Date());
									profile.setModifyDate(new Date());
									profile.setIsDeleted(false);
									if(result!=null){
										profile.setIcon(result.getEntityFile().getUuid());
									}
									profile = this.profileService.modify(profile);
								}else{
									profile = new Profile();
									profile.setUserId(stu.getUserId());
									profile.setUserName(userTemp.getUserName());
									profile.setCreateDate(new Date());
									profile.setModifyDate(new Date());
									profile.setIsDeleted(false);
									if(result!=null){
										profile.setIcon(result.getEntityFile().getUuid());
									}
									profile = this.profileService.add(profile);
								}
								
								stuList.add(stu);
								
							}else{
								throw new Exception();
							}
						}
					}
				}
				
			}
			
			if(stuList.size()==imgsCount){
				
				return profile != null ? new ResponseInfomation(profile.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(); 
				
			}else{
				
				return new ResponseInfomation(null, ResponseInfomation.OUT_OF_ACCORD);
			}
			
		} catch (Exception e) {
			return new ResponseInfomation(null, ResponseInfomation.OPERATION_FAIL);
		}
	}
	
}
