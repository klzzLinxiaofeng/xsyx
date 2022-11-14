package platform.szxyzxx.web.schoolaffair.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import platform.education.school.affair.model.Classroom;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.ClassroomCondition;
import platform.education.school.affair.vo.ClassroomVo;
import platform.education.school.affair.vo.FloorCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/schoolaffair/classroom")
public class ClassroomController extends BaseController {

	private final static String viewBasePath = "/schoolaffair/logistics/classroom";

	@RequestMapping(value = "/list")
	public ModelAndView getClassroomList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("xwHqClassroomCondition") ClassroomCondition classroomCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, classroomCondition);
		List<Classroom> classroomList = classroomService.findClassroomByCondition(classroomCondition, page, order);
		List<ClassroomVo> classroomVoList = new ArrayList<ClassroomVo>();
		if(classroomList.size() > 0) {
			for(Classroom classroom : classroomList) {
				ClassroomVo vo = new ClassroomVo();
				BeanUtils.copyProperties(classroom, vo);
				Floor floor = this.floorService.findFloorById(classroom.getFloorId());
				String floorName = "0";
				if(floor != null) {
					if(floor.getIsDelete() == true) {
						
					}else {
						floorName = floor.getName();
					}
				}
				vo.setFloorName(floorName);
				classroomVoList.add(vo);
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/classroomList");
		}
		model.addAttribute("classroomVoList", classroomVoList);
		return new ModelAndView(viewPath, model.asMap());

	}
	
		//查询数据
		@RequestMapping(value = "/list/json", method = RequestMethod.GET)
		@ResponseBody
		public List<Classroom> jsonList(
				@CurrentUser UserInfo user, 
				@ModelAttribute("condition") ClassroomCondition condition,
				@ModelAttribute("page") Page page,
				@ModelAttribute("order") Order order,
				@RequestParam(value = "usePage", required = false) boolean usePage) {
			conditionFilter(user, condition);
			page = usePage ? page : null;
			return this.classroomService.findClassroomByCondition(condition, page, order);
		}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addClassroomPage(@CurrentUser UserInfo user, Model model) {
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<Floor> floorList = this.floorService.findFloorByCondition(condition);
		List<Floor> list = new ArrayList<Floor>();
		if(floorList != null) {
			for(Floor floor : floorList) {
				if("4".equals(floor.getState())) {
					
					}else {
						list.add(floor);
					}
				}
		}
		model.addAttribute("floorList", list);
		return new ModelAndView(structurePath("/input"));

	}
	
	@RequestMapping(value = "/findStorey")
	@ResponseBody
	public ResponseInfomation storey(@RequestParam(value = "id", required = true) Integer id){
		Floor floor = this.floorService.findFloorById(id);
		return floor != null ? new ResponseInfomation(floor.getLayerNumber(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
		
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addClassroom(@CurrentUser UserInfo user,
			Classroom classroom) {
		classroom.setSchoolId(user.getSchoolId());
		classroom.setCreateDate(new Date());
		classroom.setModifyDate(new Date());
		classroom = this.classroomService.add(classroom);
		return classroom != null ? new ResponseInfomation(classroom.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 修改页面
	 * 
	 * @param id
	 * @param isCK
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifyClassrooom(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		Classroom classroom = this.classroomService.findClassroomById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
//		condition.setIsDelete(false);
		List<Floor> floorList = this.floorService.findFloorByCondition(condition);
//		List<Floor> list = new ArrayList<Floor>();
//		if(floorList != null) {
//			for(Floor floor : floorList) {
//				if("4".equals(floor.getState())) {
//					
//				}else {
//					list.add(floor);
//				}
//			}
//		}
		model.addAttribute("floorList", floorList);
		model.addAttribute("classroom", classroom);
		return new ModelAndView(structurePath("/input"), model.asMap());

	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateClassroom(
			@PathVariable(value = "id") Integer id, Classroom classroom) {
		classroom.setId(id);
		classroom.setModifyDate(new Date());
		classroom = this.classroomService.modify(classroom);
		return classroom != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteClassroom(@PathVariable(value = "id") Integer id,
			Classroom classroom) {
		if (classroom != null) {
			classroom.setId(id);
		}
		return  this.classroomService.abandon(classroom);
	}
	
	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		ClassroomCondition condition = new ClassroomCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setCode(code);
		condition.setIsDelete(false);
		if ("code".equals(dxlx)) {
			List<Classroom> list = classroomService.findClassroomByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(Classroom tem : list) {
					currentId = tem.getId();
					if(currentId != null && currentId.equals(id)) {
						isExist = true;
					}else {
						isExist = false;
					}
				}
			}else{
				isExist = true;
			}
		}
		return isExist;
	}
	
	@RequestMapping(value = "/classNameChecker", method = RequestMethod.POST)
	@ResponseBody
	public boolean classNameChecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		ClassroomCondition condition = new ClassroomCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setName(name);
		condition.setIsDelete(false);
		if ("name".equals(dxlx)) {
			List<Classroom> list = classroomService.findClassroomNameByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(Classroom tem : list) {
					currentId = tem.getId();
					if(currentId != null && currentId.equals(id)) {
						isExist = true;
					}else {
						isExist = false;
					}
				}
			}else{
				isExist = true;
			}
		}
		return isExist;
	}
	
	@RequestMapping(value = "/downLoadClassroom")
	public void downLoadClassroom(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "name", required = false) String name,
			@ModelAttribute("classroom") Classroom classroom,
			@CurrentUser UserInfo user) {
		ParseConfig config = SzxyExcelTookit.getConfig("downLoadClassroom");
		ClassroomCondition condition = new ClassroomCondition();
		conditionFilter(user, condition);
//		try {
//			name = new String((request.getParameter("name")).getBytes("iso-8859-1"), "utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		condition.setName(name);
		List<Object> list = new ArrayList<Object>();
		List<Classroom> classroomList = classroomService.findClassroomByCondition(condition);
		List<ClassroomVo> classroomVoList = new ArrayList<ClassroomVo>();
		if(classroomList.size() > 0) {
			for(Classroom classroomTemp : classroomList) {
				ClassroomVo vo = new ClassroomVo();
				BeanUtils.copyProperties(classroomTemp, vo);
				String state = "";
				if("0".equals(classroomTemp.getState())) {
					state = "可用";
				}else if("1".equals(classroomTemp.getState())) {
					state = "不可用";
				}
				vo.setState(state);
				Floor floor = this.floorService.findFloorById(classroomTemp.getFloorId());
				String floorName = "0";
				if(floor != null) {
					if(floor.getIsDelete() == true) {
						floorName = "该大楼已废弃";
					}else {
						floorName = floor.getName();
					}
				}
				vo.setFloorName(floorName);
				classroomVoList.add(vo);
			}
		}
		String[] titles = {"教室名称", "编号", "类型", "状态", "大楼名称", "所在楼层", "创建时间"};
		config.setTitles(titles);
		config.setSheetTitle("教室信息表");
		String fileName = "教室信息表.xls";
		try {
			for(ClassroomVo vo : classroomVoList) {
				list.add(vo);
			}
			SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, ClassroomCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
