package platform.szxyzxx.web.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherDetailInfo;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/office/mail")
public class MailController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(MailController.class);
	private final static String BASE_PATH = "/oa/mail";
	/**
	 * 教师通讯录列表 
	 */
	
	@RequestMapping(value = "/index1")
	public ModelAndView index(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "name", required = false) String name,
			@ModelAttribute("teacherCondition") TeacherCondition teacherCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		page.setPageSize(8);
		String viewPath = null;
		Integer allSum = 0; 
//		List<Teacher> teacherLists = null;
		List<TeacherVo> teacherVoLists = null;
		List<DepartmentTeacher> dtLists = null;
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index1");
		}
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		try {
			
			//部门信息列表
			List<Department>departmentList = this.departmentService.findDepartmentBySchoolId(user.getSchoolId(), null, order);
			
			List<TeacherDetailInfo> tdInfoList = new ArrayList<TeacherDetailInfo>();
			
			if(type.equals("all")){
				// 教师信息列表
				teacherCondition.setSchoolId(user.getSchoolId());
				teacherCondition.setIsDelete(false);
				teacherCondition.setName(name);
//				List<Teacher> teacherList = teacherService.findTeacherByCondition(
//						teacherCondition, page, order);
//
//				 teacherLists = teacherService.findTeacherByCondition(
//						teacherCondition, null, order);
				List<TeacherVo> teacherVoList = teacherService.findTeacherVoByConditionMore(teacherCondition, page, order);
				teacherVoLists = teacherService.findTeacherVoByConditionMore(teacherCondition, null, order);
				// 教师所对应的人员的具体信息
//				for (Teacher teacher : teacherList) {
				for(TeacherVo teacher: teacherVoList){
					TeacherDetailInfo teahcherDetailInfo = this.teacherService.findTeacherDetailInfoById(teacher.getId());
					String tdepartmentName="";
					DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
					dtCondition.setSchoolId(teacher.getSchoolId());
					dtCondition.setTeacherId(teacher.getId());
					dtCondition.setIsDeleted(false);
					List<DepartmentTeacher> dtList = departmentTeacherService.findDepartmentTeacherByConditionMore(dtCondition);
					if(dtList.size() > 0){
						for(int i=0;i<dtList.size();i++){
							tdepartmentName += dtList.get(i).getDepartmentName() +",";
						}
						teahcherDetailInfo.setDepartmentName(tdepartmentName.substring(0, tdepartmentName.length()-1));
					}else{
						teahcherDetailInfo.setDepartmentName("");
					}
					
					tdInfoList.add(teahcherDetailInfo);
				}
				
				
			}else{
				// 根据部门id查询部门教师表，得到对应的教师id
				DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
					dtCondition.setSchoolId(user.getSchoolId());
					dtCondition.setDepartmentId(Integer.parseInt(type));
					dtCondition.setTeacherName(name);
				List<DepartmentTeacher> dtList = this.departmentTeacherService
						.findDepartmentTeacherByCondition1(dtCondition, page, order);
				
				 dtLists = this.departmentTeacherService
						.findDepartmentTeacherByCondition1(dtCondition, null, order);
				
				if(dtList.size()>0){
					for(DepartmentTeacher dteacher:dtList){
						TeacherDetailInfo  teahcherDetailInfo = this.teacherService.findTeacherDetailInfoById(dteacher.getTeacherId());
						String tdepartmentName="";
						Department d = this.departmentService.findDepartmentById(dteacher.getDepartmentId());
						if(d.getName()!=null || d.getName()!=""){
							tdepartmentName=d.getName();
							teahcherDetailInfo.setDepartmentName(tdepartmentName);
						}else{
							teahcherDetailInfo.setDepartmentName("");
						}
						tdInfoList.add(teahcherDetailInfo);
					}
				}
				
			}
			
			if(name==""|| name==null){
				if(type.equals("all")){
					allSum=teacherVoLists.size();
				}else{
					allSum=dtLists.size();
				}
			}else{
				allSum = page.getTotalRows();
			}
			
			model.addAttribute("allSum", allSum);
			model.addAttribute("type",type);
			model.addAttribute("sub", sub);
			model.addAttribute("tdInfoList", tdInfoList);
			model.addAttribute("departmentList", departmentList);
		} catch (Exception e) {
			log.info("查询部门列表异常...");
			e.printStackTrace();
		}
		
		return new ModelAndView(viewPath, model.asMap());
     }
	
	
		private String structurePath(String subPath) {
			return BASE_PATH + subPath;
		}
        }



