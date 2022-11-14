package platform.szxyzxx.web.oa.termial.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.user.model.Profile;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.oa.termial.vo.PhoneDepVo;
import platform.szxyzxx.web.oa.termial.vo.PhoneDepartmentVo;
import platform.szxyzxx.web.oa.termial.vo.PhoneUserInfoVo;
import platform.szxyzxx.web.oa.utils.JsonWriteUtils;
import platform.szxyzxx.web.oa.utils.StringUtils;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 通讯录控制类
 * 
 * @author sky
 * @version 1.0 2015-6-10
 */

@Controller
@RequestMapping(value = "/termial/oa/department")
public class PhoneDepController extends BaseController {

	/**
	 * 查询发送给学校全体教工的通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public void getDepartment(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {

		JSONObject json_return = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String ownerId = request.getParameter("ownerId");
			String ownerType = request.getParameter("ownerType");
			DepartmentCondition conition = new DepartmentCondition();

			conition.setSchoolId(Integer.valueOf(ownerId));
			conition.setIsDelete(false);
			page.setPageSize(Integer.MAX_VALUE);
			List<Department> list = this.departmentService
					.findDepartmentByCondition(conition, page, order);

			DepartmentTeacherCondition departmentTeacherCondition = null;
			List<DepartmentTeacher> dts = null;
			Teacher teacher = null;
			Profile profile = null;
			PhoneDepartmentVo deps = null;
			JSONObject jsonObjects = null;
			for (Department dep : list) {
				departmentTeacherCondition = new DepartmentTeacherCondition();
				departmentTeacherCondition.setDepartmentId(dep.getId());
				departmentTeacherCondition.setIsDeleted(false);
				dts = this.departmentTeacherService
						.findDepartmentTeacherByConditionMore(departmentTeacherCondition);
				List<PhoneUserInfoVo> users = new ArrayList<PhoneUserInfoVo>();
				for (DepartmentTeacher t : dts) {
					String imgurl = "";
					if (t != null) {
						teacher = this.teacherService.findTeacherById(t
								.getTeacherId());
						if (teacher != null) {
							profile = this.profileService
									.findByUserId(teacher.getUserId());
							if (profile != null) {
								String icon = profile.getIcon();

								if (StringUtils.isNotEmpty(icon)) {
									imgurl = this.fileService
											.relativePath2HttpUrlByUUID(icon);
								}
							}
							PhoneUserInfoVo user = new PhoneUserInfoVo(teacher,
									imgurl);
							users.add(user);
						}
					}
				}
				deps = new PhoneDepartmentVo(dep, users);
				jsonObjects = JSONObject.fromObject(deps);
				array.add(jsonObjects);
			}
			//后面增加的没有部门人员的数据
			PhoneDepartmentVo noDeps = getNoDepartmentTeacher(Integer.valueOf(ownerId));
			JSONObject jsonObjectsOfNoDep = JSONObject.fromObject(noDeps);
			array.add(jsonObjectsOfNoDep);

			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", array);
			JsonWriteUtils.setJson(json_return, response);
		} catch (Exception e) {
			e.printStackTrace();
			json_return
					.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		} finally {
			if (!array.isEmpty()) {
				array.clear();
			}
			if (json_return != null) {
				json_return.clear();
			}

		}

	}
	/**
	 * 没有部门 的教师
	 * @return
	 */
	public PhoneDepartmentVo getNoDepartmentTeacher(Integer schoolId){
		List<PhoneUserInfoVo> users = new ArrayList<PhoneUserInfoVo>();
		String imgurl = null;
		Department dep = new Department();
		TeacherCondition tCondition = new TeacherCondition();
//		tCondition.setDelete(false);
		tCondition.setIsDelete(false);
		tCondition.setSchoolId(schoolId);
		List<TeacherVo> list = this.teacherService.findTeacherVoByCondition(tCondition, null, null);
		if(list.size() > 0){
			for(TeacherVo vo : list){
				if(vo.getDepartmentId()==null || "".equals(vo.getDepartmentId())){
					dep.setId(-1);
					dep.setName("其他");
					Profile profile = this.profileService
							.findByUserId(vo.getUserId());
					if (profile != null) {
						String icon = profile.getIcon();
						if (StringUtils.isNotEmpty(icon)) {
							imgurl = this.fileService
									.relativePath2HttpUrlByUUID(icon);
						}
					}
					PhoneUserInfoVo puf = new PhoneUserInfoVo(vo, imgurl);
					users.add(puf);
				}
			}
		}
		PhoneDepartmentVo phoneDepartmentVo = new PhoneDepartmentVo(dep, users);
		return phoneDepartmentVo;
	}
	
	/**
	 * @param request
	 * @return departmentList
	 * 根据学校查找部门
	 */
	@RequestMapping(value = "getDepartmentBySchoolId")
	@ResponseBody
	public void getDepartmentBySchoolId(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		JSONObject json_return = new JSONObject();
		String ownerId = request.getParameter("ownerId");
		String ownerType = request.getParameter("ownerType");
		JSONArray array = new JSONArray();
		try {
			DepartmentCondition departmentCondition = new DepartmentCondition();
			departmentCondition.setSchoolId(Integer.valueOf(ownerId));
			departmentCondition.setIsDelete(false);
			List<Department> departmentList = departmentService.findDepartmentByCondition(departmentCondition, null,null);
			for(Department dt : departmentList){
				PhoneDepVo pv = new PhoneDepVo(dt);
				JSONObject jsonObjectsOfDep = JSONObject.fromObject(pv);
				array.add(jsonObjectsOfDep);
			}
			
			//增加其他  用于存放没有部门的教师========
			Department noDep = new Department();
			noDep.setId(-1);
			noDep.setName("其他");
			PhoneDepVo noDpv = new PhoneDepVo(noDep);
			JSONObject jsonObjectsOfDep = JSONObject.fromObject(noDpv);
			array.add(jsonObjectsOfDep);
			//增加其他  用于存放没有部门的教师========
			
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", array);
			JsonWriteUtils.setJson(json_return, response);
		} catch (Exception e) {
			json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		}finally {
			if (!array.isEmpty()) {
				array.clear();
			}
			if (json_return != null) {
				json_return.clear();
			}
		}
	}
	
	/**
	 * @param request
	 * @return departmentList
	 * 根据部门ID查找教师
	 * 目前客户端还没做分页，将page设置为null，分页做好后改为page即可 有分页效果
	 */
	@RequestMapping(value = "getTeacherByDepartmentId")
	@ResponseBody
	public void getTeacherByDepartmentId(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		String ownerId = request.getParameter("ownerId");
		String ownerType = request.getParameter("ownerType");
		String departmentId = request.getParameter("departmentId");
		String new_or_old = request.getParameter("new_or_old");
		String baselineDate = request.getParameter("baselineDate");
		String page_num = request.getParameter("page_num");
		Profile profile = null;
		int num = 20;
		if (StringUtils.isNotEmpty(page_num)) {
			try {
				num = Integer.parseInt(page_num);
			} catch (Exception ex) {
				// TODO: handle exception
			}
		}
		JSONObject json_return = new JSONObject();
		List<PhoneUserInfoVo> users = new ArrayList<PhoneUserInfoVo>();
		try {
			DepartmentTeacherCondition dtc = new DepartmentTeacherCondition();
			dtc.setDepartmentId(Integer.valueOf(departmentId));
			dtc.setIsDeleted(false);
			dtc.setSchoolId(Integer.valueOf(ownerId));
			page.setPageSize(num);
			order.setProperty(order.getProperty() != null ? order.getProperty()
					: "create_date");
			if(departmentId.equals("-1")){  //用户点击“其他”选项，获取没有部门的教师
				TeacherCondition tCondition = new TeacherCondition();
//				tCondition.setDelete(false);
				tCondition.setIsDelete(false);
				tCondition.setSchoolId(Integer.valueOf(ownerId));
				tCondition.setBaselineDate(baselineDate);
				tCondition.setNew_or_old(new_or_old);
				List<TeacherVo> list = this.teacherService.findNoDepartmentTeacherVoByCondition(tCondition, null, order);
				if(list.size() > 0){
					for(TeacherVo vo : list){
						String imgurl = null;
						if(vo.getDepartmentId()==null || "".equals(vo.getDepartmentId())){
							profile = this.profileService
									.findByUserId(vo.getUserId());
							if (profile != null) {
								String icon = profile.getIcon();
								if (StringUtils.isNotEmpty(icon)) {
									imgurl = this.fileService
											.relativePath2HttpUrlByUUID(icon);
								}
							}
							PhoneUserInfoVo puf = new PhoneUserInfoVo(vo, imgurl);
							users.add(puf);
						}
					}
				}
			}else{          //获取部门下的教师
				List<DepartmentTeacher> dtList = departmentTeacherService.findDepartmentTeacherByCondition(dtc, null, order);
				if(dtList.size() > 0){
					for(DepartmentTeacher dt : dtList){
						String imgurl = null;
						Teacher teacher = teacherService.findTeacherById(dt.getTeacherId());
						//获取头像
						if (teacher != null) {
							profile = this.profileService
									.findByUserId(teacher.getUserId());
							if (profile != null) {
								String icon = profile.getIcon();
								
								if (StringUtils.isNotEmpty(icon)) {
									imgurl = this.fileService
											.relativePath2HttpUrlByUUID(icon);
								}
							}
	 						PhoneUserInfoVo puf = new PhoneUserInfoVo(teacher, imgurl);
							users.add(puf);
						}
					}
				}
			}
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", users);
			JsonWriteUtils.setJson(json_return, response);
		} catch (Exception e) {
			json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		}finally {
			if (json_return != null) {
				json_return.clear();
			}
		}
	}
	
}
