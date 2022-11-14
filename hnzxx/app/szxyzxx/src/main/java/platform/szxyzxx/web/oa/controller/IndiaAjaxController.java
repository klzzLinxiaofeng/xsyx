package platform.szxyzxx.web.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.oa.model.India;
import platform.education.oa.service.IndiaService;
import platform.education.oa.vo.IndiaCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 
 * 文印ajax请求
 * 
 * @author huangyanchun
 * @version 1.0 2014-4-17
 */
@Controller
@RequestMapping(value = "/office/ajax/india")
public class IndiaAjaxController extends BaseController {

	private final static String BASE_PATH = "oa/india/";

	@Autowired
	@Qualifier("indiaService")
	private IndiaService indiaService;

	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherServiceteacherService;

	/**
	 * 我申请的文印 列表
	 * 
	 * @param dm
	 * @param sub
	 * @param page
	 * @param order
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		IndiaCondition condition = new IndiaCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setUserId(user.getId());
		List<India> items = this.indiaService.findIndiaByCondition(condition,
				page, order);
		String path = "myApplay_list";
		if ("list".equals(sub)) {

			path = "list";
		}

		List<String> departmentName = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			if (items.size() != 0) {
				Department d = this.departmentService.findDepartmentById(items
						.get(i).getDepartmentId());
				departmentName.add(i, d.getName());
			}
		}

		// 根据用户id user_id查找对应的教师Id teacherId
		Integer teacherId = this.teacherService.findUnqiueTeacherId(
				user.getId(), user.getSchoolId());
		// 根据teacherId查询对应的教师信息
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		List<EntityFile> upload = new ArrayList<EntityFile>();
		for (int i = 0; i < items.size(); i++) {
			if (items.size() != 0) {
				EntityFile entity = this.entityFileService.findFileByUUID(items.get(i)
						.getUploadFile());
				upload.add(i, entity);
			}
		}
		model.addAttribute("upload", upload);
		model.addAttribute("departmentName", departmentName);
		model.addAttribute("teacher", teacher);
		model.addAttribute("items", items);
		return BASE_PATH + path;
	}

	
	/**
	 * 下载状态的改变
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/download")
	@ResponseBody
	public ResponseInfomation download(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id) {
		India india = this.indiaService.findIndiaById(id);
		india.setIndiaStatus("1");
		india = this.indiaService.modify(india);
		return india != null ? new ResponseInfomation(india.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	/**
	 * 下载打印状态的改变
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/downloadPrint")
	@ResponseBody
	public ResponseInfomation downloadPrint(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id) {
		India india = this.indiaService.findIndiaById(id);
		india.setIndiaStatus("2");
		india = this.indiaService.modify(india);
		return india != null ? new ResponseInfomation(india.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

}
