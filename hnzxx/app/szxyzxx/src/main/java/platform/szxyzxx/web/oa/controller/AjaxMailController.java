package platform.szxyzxx.web.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherDetailInfo;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/office/ajax/ajaxMail")
public class AjaxMailController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(AjaxMailController.class);

	private final static String viewBasePath = "/oa/mail";

	@RequestMapping(value = "/ajaxMail_index")
	public ModelAndView ajaxMail_index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "teacherName", required = false) String teacherName,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("teacherCondition") TeacherCondition teacherCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		try {
			page.setPageSize(8);
			if ("ajaxMail_list".equals(sub)) {
				viewPath = structurePath("/ajaxMail_list");
			} else {
				viewPath = structurePath("/ajaxMail_index");
			}
			List<TeacherDetailInfo> tdInfoList = new ArrayList<TeacherDetailInfo>();
			
			// 根据部门id查询部门教师表，得到对应的教师id
			DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
				dtCondition.setSchoolId(user.getSchoolId());
				dtCondition.setDepartmentId(Integer.parseInt(departmentId));
			if(teacherName!=""|| teacherName!=null){
				dtCondition.setTeacherName(teacherName);
			}
			List<DepartmentTeacher> dtList = this.departmentTeacherService
					.findDepartmentTeacherByCondition1(dtCondition, page, order);
			
			List<DepartmentTeacher> dtLists = this.departmentTeacherService
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
			
			Integer allSum = 0; 
			if(teacherName==""|| teacherName==null){
				allSum=dtLists.size();
			}else{
				allSum = page.getTotalRows();
			}
			
			model.addAttribute("departmentId", departmentId);
			model.addAttribute("allSum", allSum);
			model.addAttribute("tdInfoList", tdInfoList);

		} catch (Exception e) {

			log.info("查询教师通讯录列表异常...");
			e.printStackTrace();
		}

		return new ModelAndView(viewPath, model.asMap());
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

}
