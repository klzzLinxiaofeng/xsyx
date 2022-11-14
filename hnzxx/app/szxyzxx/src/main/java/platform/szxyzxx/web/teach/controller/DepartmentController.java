package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.vo.TreeVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:DepartmentController.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 * 
 * @Function 功能描述：部门管理控制器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年5月18日
 */
@Controller
@RequestMapping("/teach/dept")
public class DepartmentController extends BaseController {

	private final static String viewBasePath = "/teach/dept";


	@Autowired
	BasicSQLService basicSQLService;


	@RequestMapping(value = "/index")
	public ModelAndView index(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") DepartmentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFiler(condition, user);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<Department> depts = departmentService.findDepartmentByCondition(
				condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("list", depts);
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@CurrentUser UserInfo user,
			@RequestParam("t") String type,
			@ModelAttribute("condition") DepartmentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		conditionFiler(condition, user);
		List<Department> zzjgs = departmentService.findDepartmentByCondition(
				condition, page, order);
		model.addAttribute("list", zzjgs);
		return new ModelAndView(structurePath("/list" + type), model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Department> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") DepartmentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		conditionFiler(condition, user);
		return departmentService.findDepartmentByCondition(condition, page,
				order);
	}

	@RequestMapping(value = "/getBySchoolId", method = RequestMethod.GET)
	@ResponseBody
	public List<Department> getBySchoolId(@CurrentUser UserInfo user,@RequestParam("schoolId") Integer schoolId) {
		return departmentService.findBySchoolId(schoolId);
	}

	@RequestMapping(value = "/getMyDept", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> getBySchoolId(@CurrentUser UserInfo user) {

		return basicSQLService.find("select d.id,d.`name` from pj_department_teacher dt inner join pj_teacher t on t.id=dt.teacher_id inner join pj_department d on d.id=dt.department_id where t.user_id="+user.getId()+" and t.is_delete=0 and dt.is_deleted=0 and d.is_delete=0");

	}


	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(@CurrentUser UserInfo user,
			Department department) {
		Integer schoolId = department.getSchoolId();
		if (schoolId == null) {
			if (user != null) {
				Integer userSchoolId = user.getSchoolId();
				if (userSchoolId == null) {
					return new ResponseInfomation();
				}
				department.setSchoolId(userSchoolId);
			}
		}
		department = this.departmentService.add(department);
		return department != null ? new ResponseInfomation(department.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Department dept = this.departmentService.findDepartmentById(id);
		model.addAttribute("dept", dept);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Department dept) {
		if (dept != null) {
			dept.setId(id);
		}
		dept = departmentService.findDepartmentById(id);
		return this.departmentService.deleteByRecursive(dept);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			Department dept) {
		dept.setId(id);
		try {
			dept = this.departmentService.modify(dept);
		} catch (Exception e) {
			return new ResponseInfomation();
		}
		return dept != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	/**
	 * 拖动修改部门排序
	 * @param ids
	 * @return 成功  失败
	 */
	@RequestMapping(value = "/editOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation editOrder(@RequestParam(value = "ids") String ids) {
		try {
			this.departmentService.modifyOrder(ids);
		} catch (Exception e) {
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	@RequestMapping(value = "/tree/index")
	public String indexTree(
			@RequestParam(value = "dm", required = false) String dm) {
		return structurePath("/tree_index");
	}

	@RequestMapping(value = "/tree/json")
	@ResponseBody
	public List<TreeVo> getTreeJsonData(
			@CurrentUser UserInfo user,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
			@ModelAttribute("condition") DepartmentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		conditionFiler(condition, user);
		List<Department> depts = departmentService.findDepartmentByCondition(
				condition, "1".equals(usePage) ? page : null,
				"1".equals(useOrder) ? order : null);
		return deptToTreeVo(depts, condition.getParentId());
	}

	private List<TreeVo> deptToTreeVo(List<Department> depts, Integer parentId) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		Page page = new Page();
		page.setPageSize(1);
		for (Department dept : depts) {
			TreeVo vo = new TreeVo();
			vo.setId(String.valueOf(dept.getId()));
			vo.setpId(String.valueOf(dept.getParentId()));
			vo.setName(dept.getName());
			List<Department> departments = this.departmentService
					.findByParentId(Integer.parseInt(vo.getId()), page);
			vo.setIsParent(departments != null && departments.size() > 0 ? true
					: false);
			treeVos.add(vo);
		}
		return treeVos;
	}

	/**
	 * 
	 * @Method creator
	 * @Function 功能描述：跳转者创建页面
	 * @param parentId
	 *            父资源ID
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/tree/creator", method = RequestMethod.GET)
	public ModelAndView creatorTree(
			@RequestParam(value = "caller", required = false) String caller,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "level", required = false) Integer level,
			Model model) {
		Department parent = this.departmentService.findDepartmentById(parentId);
		if(parent != null){
			parent.setLevel(level);
		}
		model.addAttribute("parent", parent);
		return new ModelAndView(structurePath("/tree_input"), model.asMap());
	}

	/**
	 * 
	 * @Method editor
	 * @Function 功能描述：跳转者修改页面
	 * @param dm
	 *            菜单ID
	 * @param model
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/tree/editor", method = RequestMethod.GET)
	public ModelAndView editorTree(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Department dept = this.departmentService.findDepartmentById(id);
		model.addAttribute("dept", dept);
		return new ModelAndView(structurePath("/tree_input"), model.asMap());
	}

	@RequestMapping(value = "/teacher/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addTeacherToDept(
			@RequestParam("deptId") Integer deptId,
			@RequestParam("teachIds[]") String teachIds,
			@CurrentUser UserInfo user) {
		String [] teachIdss=teachIds.split(",");

		if (user != null) {
			Integer schoolId = user.getSchoolId();
			String result = this.departmentTeacherService.addBatch(deptId,
					teachIdss, schoolId);
			return DepartmentTeacherService.OPERATE_SUCCESS.equals(result) ? new ResponseInfomation(
					ResponseInfomation.OPERATION_SUC)
					: new ResponseInfomation();
		}
		return new ResponseInfomation();
	}

	@RequestMapping(value = "/teacher/liquidator", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseInfomation removeTeacherFromDept(
			@RequestParam("deptId") Integer deptId,
			@RequestParam("teachId") Integer teachId) {
		String result = this.departmentTeacherService.abandon(teachId, deptId);
		return DepartmentTeacherService.OPERATE_SUCCESS.equals(result) ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/teacher/saveSort", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation saveDepartTeacherSort(
			@RequestParam("deptId") Integer deptId,
			@RequestParam("teachIds") String teachIds,
			@CurrentUser UserInfo user) {
		try{
			String[] teachersId = teachIds.split(",");
			this.departmentTeacherService.updateSort(teachersId, deptId,user.getSchoolId());
		}catch(Exception e){
			return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	private void conditionFiler(DepartmentCondition condition, UserInfo user) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}

}
