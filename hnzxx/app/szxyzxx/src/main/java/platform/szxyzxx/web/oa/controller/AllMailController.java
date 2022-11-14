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
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherDetailInfo;
import platform.education.oa.vo.IndiaCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/office/ajax/allMail")
public class AllMailController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(AllMailController.class);

	private final static String viewBasePath = "/oa/mail";

	@RequestMapping(value = "/allMail_index")
	public ModelAndView allMail_index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "name", required = false) String name,
			@ModelAttribute("teacherCondition") TeacherCondition teacherCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		try {
			page.setPageSize(8);
			if ("allMail_list".equals(sub)) {
				viewPath = structurePath("/allMail_list");
			} else {
				viewPath = structurePath("/allMail_index");
			}

			List<TeacherDetailInfo> tdInfoList = new ArrayList<TeacherDetailInfo>();
			// 教师信息列表
			teacherCondition.setSchoolId(user.getSchoolId());
			teacherCondition.setIsDelete(false);
			List<Teacher> teacherList = teacherService.findTeacherByCondition(
					teacherCondition, page, order);


			List<Teacher> teacherLists = teacherService.findTeacherByCondition(
					teacherCondition, null, order);
			// 教师所对应的人员的具体信息
			for (Teacher teacher : teacherList) {
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
			
			Integer allSum = 0; 
			if(name==""|| name==null){
				allSum=teacherLists.size();
			}else{
				allSum = page.getTotalRows();
			}
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
