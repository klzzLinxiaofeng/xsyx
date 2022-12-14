package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.model.DormitoryAttendance;
import platform.education.school.affair.model.DormitoryPerson;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.DormitoryAttendanceCondition;
import platform.education.school.affair.vo.DormitoryAttendanceVo;
import platform.education.school.affair.vo.DormitoryCondition;
import platform.education.school.affair.vo.DormitoryPersonCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;


  

@Controller
@RequestMapping("/schoolaffair/dormitoryAttendance")
public class DormitoryAttendanceController extends BaseController { 
	
	private static final Logger log = LoggerFactory
			.getLogger(DormitoryAttendanceController.class);
	private final static String viewBasePath = "/schoolaffair/dormitoryManager/dormitoryAttendance";
	
	
	/**
	 * ??????????????????
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") DormitoryAttendanceCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		List<DormitoryAttendanceVo> dAttendanceVo = null;
		if ("list".equals(sub)) {
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			
			//???????????????????????????
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			condition.setSchoolYearId(stc.getSchoolYearId());
			condition.setIsDeleted(0);
			dAttendanceVo = this.dormitoryAttendanceService.findDormitoryAttendanceByConditionVo(condition, page, order);
			if(dAttendanceVo.size()>0){
				for(DormitoryAttendanceVo dav : dAttendanceVo){
					Dormitory d = this.dormitoryService.findDormitoryById(dav.getDormitoryId());
					if(d!=null){
						dav.setDormitoryCode(d.getDormitoryCode());
						Floor f = this.floorService.findFloorByCode(user.getSchoolId(), d.getFloorCode());
						if(f!=null){
							dav.setFloorName(f.getName());
						}
						
					}
				}
			}
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("dAttendanceVo", dAttendanceVo);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<DormitoryAttendance> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") DormitoryAttendanceCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.dormitoryAttendanceService.findDormitoryAttendanceByCondition(condition, page, order);
	}
	
	/**
	 * ??????????????????????????????
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(
			@CurrentUser UserInfo user,
			Model model
			) {
		
		// ???????????????????????????
		SchoolTermCurrent stc = this.schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		//????????????id???????????????????????????????????????????????????????????????
		DormitoryPersonCondition dpCondition = new DormitoryPersonCondition();
		dpCondition.setSchoolId(user.getSchoolId());
		dpCondition.setSchoolYearId(stc.getSchoolYearId());
		List<DormitoryPerson>dpList = this.dormitoryPersonService.findDormitoryPersonByCondition(dpCondition,null,null);
		List<Grade>gradeList = new ArrayList<Grade>();
		if(dpList.size()>0){
			for(DormitoryPerson person : dpList){
				Grade grades = new Grade();
				Grade grade = this.gradeService.findGradeById(person.getGradeId());
				grades.setId(grade.getId());
				grades.setName(grade.getName());
				gradeList.add(grades);
			}
			
		}
		
		//????????????
		DormitoryCondition condition = new DormitoryCondition();
		condition.setSchoolId(user.getSchoolId());
		List<Dormitory> dormitoryList = this.dormitoryService.findDormitoryUnique(condition);
		
		model.addAttribute("stc", stc);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("dormitoryList", dormitoryList);
		return new ModelAndView(structurePath("/input"));
	}
   
	/**
	 * ?????????????????? ????????????
	 * @param dormitoryAttendance
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(DormitoryAttendance dormitoryAttendance, @CurrentUser UserInfo user) {
		try {
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(String.valueOf(dormitoryAttendance.getSchoolYearId()));
			SchoolYear sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			Student s = this.studentService.findStudentById(dormitoryAttendance.getStudentId());
			dormitoryAttendance.setSchoolYearId(sy.getId());
			dormitoryAttendance.setStudentName(s.getName());
			dormitoryAttendance.setStudentNumber(s.getStudentNumber()==null?"":s.getStudentNumber());
			dormitoryAttendance.setSchoolId(user.getSchoolId());
			dormitoryAttendance.setIsDeleted(0);
			dormitoryAttendance = this.dormitoryAttendanceService.add(dormitoryAttendance);
		} catch (Exception e) {
			log.info("??????????????????..");
			e.printStackTrace();
		}
		
		return dormitoryAttendance != null ? new ResponseInfomation(dormitoryAttendance.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	/**
	 * ??????????????????????????????
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			Model model) {
		try {
			DormitoryAttendance dormitoryAttendance = this.dormitoryAttendanceService.findDormitoryAttendanceById(id);
			//??????schoolYearId???????????????????????????
			SchoolYear sy = this.schoolYearService.findSchoolYearById(dormitoryAttendance.getSchoolYearId());
			
			// ???????????????????????????
			SchoolTermCurrent stc = this.schoolTermCurrentService
					.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			//????????????id???????????????????????????????????????????????????????????????
			DormitoryPersonCondition dpCondition = new DormitoryPersonCondition();
			dpCondition.setSchoolId(user.getSchoolId());
			dpCondition.setSchoolYearId(stc.getSchoolYearId());
			List<DormitoryPerson>dpList = this.dormitoryPersonService.findDormitoryPersonByCondition(dpCondition,null,null);
			List<Grade>gradeList = new ArrayList<Grade>();
			if(dpList.size()>0){
				for(DormitoryPerson person : dpList){
					Grade grades = new Grade();
					Grade grade = this.gradeService.findGradeById(person.getGradeId());
					grades.setId(grade.getId());
					grades.setName(grade.getName());
					gradeList.add(grades);
				}
			}
			//????????????
			DormitoryCondition condition = new DormitoryCondition();
			condition.setSchoolId(dormitoryAttendance.getSchoolId());
			List<Dormitory> dormitoryList = this.dormitoryService.findDormitoryUnique(condition);
			model.addAttribute("sy", sy);
			model.addAttribute("gradeList", gradeList);
			model.addAttribute("dormitoryList", dormitoryList);
			model.addAttribute("dormitoryAttendance", dormitoryAttendance);
		} catch (Exception e) {
			log.info("====????????????????????????===");
			e.printStackTrace();
		}
		
		return new ModelAndView(structurePath("/editor"), model.asMap());
	}
	
	/**
	 * ??????????????????????????????
	 * @param id
	 * @param dormitoryAttendance
	 * @return
	 */
	@RequestMapping("/saveEditor")
	@ResponseBody
	public ResponseInfomation edit(
			@CurrentUser UserInfo user,DormitoryAttendance dormitoryAttendance) {
		try {
			
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(String.valueOf(dormitoryAttendance.getSchoolYearId()));
			SchoolYear sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			
			Student s = this.studentService.findStudentById(dormitoryAttendance.getStudentId());
			dormitoryAttendance.setStudentNumber(s.getStudentNumber()==null?"":s.getStudentNumber());
			dormitoryAttendance.setStudentName(s.getName());
			dormitoryAttendance.setSchoolYearId(sy.getId());
			dormitoryAttendance = this.dormitoryAttendanceService.modify(dormitoryAttendance);
		} catch (Exception e) {
			log.info("...????????????????????????...");
			e.printStackTrace();
		}
		return dormitoryAttendance != null ? new ResponseInfomation(dormitoryAttendance.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * ????????????????????????
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		DormitoryAttendanceVo dormitoryAttendance = this.dormitoryAttendanceService.findDormitoryAttendanceById(id);
		if(dormitoryAttendance!=null){
			Dormitory d = this.dormitoryService.findDormitoryById(dormitoryAttendance.getDormitoryId());
			if(d!=null){
				Floor floor = this.floorService.findFloorByCode(dormitoryAttendance.getSchoolId(), d.getFloorCode());
				if(floor != null){
					dormitoryAttendance.setFloorName(floor.getName());
				}
			}
		}
		
		
		
				//??????schoolYearId???????????????????????????
				SchoolYear sy = this.schoolYearService.findSchoolYearById(dormitoryAttendance.getSchoolYearId());
				model.addAttribute("isCK", "disable");
				model.addAttribute("sy", sy);
				model.addAttribute("dormitoryAttendance", dormitoryAttendance);
		return new ModelAndView(structurePath("/view"), model.asMap());
	}

	/**
	 * ????????????????????????
	 * @param id
	 * @param dormitoryAttendance
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, DormitoryAttendance dormitoryAttendance) {
		if (dormitoryAttendance != null) {
			dormitoryAttendance.setId(id);
		}
		try {
			this.dormitoryAttendanceService.abandon(dormitoryAttendance);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	/**
	 * ????????????????????????
	 * @param request
	 * @param response
	 * @param dormitoryAttendance
	 * @param user
	 * @param page
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/dormitoryAttendanceCheck")  
	public void dormitoryAttendanceCheck(
			HttpServletRequest request,
			HttpServletResponse response,
			DormitoryAttendance dormitoryAttendance,
			@CurrentUser UserInfo user
			
			)throws Exception{
		/*SchoolYear sy = new SchoolYear();
		if(dormitoryAttendance.getSchoolYearId()!=null){
			//??????year???????????????id
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(String.valueOf(dormitoryAttendance.getSchoolYearId()));
			sy = this.schoolYearService.findSchoolYearByYear(syCondition);
		}*/
		ParseConfig config = SzxyExcelTookit.getConfig("dormitoryAttendanceVo");
		List<Object> maps = new ArrayList<Object>();
		Map<Object, Map<Object, Object>> typeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
//		Map<Object, Object> typeWithValueMap = new HashMap<Object, Object>();
		
		DormitoryAttendanceCondition daCondition = new DormitoryAttendanceCondition();
		// ???????????????????????????
		SchoolTermCurrent stc = this.schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		daCondition.setSchoolYearId(stc.getSchoolYearId());
		daCondition.setSchoolId(user.getSchoolId());
		daCondition.setGradeId(dormitoryAttendance.getGradeId());
		daCondition.setTeamNumber(dormitoryAttendance.getTeamNumber());
		daCondition.setAttendanceType(dormitoryAttendance.getAttendanceType());
		daCondition.setStudentName(dormitoryAttendance.getStudentName());
		daCondition.setIsDeleted(0);
		//???????????????????????????????????????????????????
		List<DormitoryAttendanceVo>dormitoryAttendanceVoList = this.dormitoryAttendanceService.findDormitoryAttendanceByConditionVo(daCondition, null, null);
		
		//?????????teamNumber???????????????????????????
		if(dormitoryAttendance.getTeamNumber()!=null){
			Team t  = this.teamService.findTeamById(dormitoryAttendance.getTeamNumber());
			Map<Object,Object>teamWithValueMap = new HashMap<Object, Object>();
			teamWithValueMap.put(dormitoryAttendance.getTeamNumber(), t.getName());
			typeWithValueMaps.put("teamName", teamWithValueMap);
			config.setCodeWithValueMaps(typeWithValueMaps);
		}else{
			Map<Object,Object>teamWithValueMap = new HashMap<Object, Object>();
			for(int i=0;i<dormitoryAttendanceVoList.size();i++){
				Team t1  = this.teamService.findTeamById(dormitoryAttendanceVoList.get(i).getTeamNumber());
				teamWithValueMap.put(dormitoryAttendanceVoList.get(i).getTeamNumber(), t1.getName());
				typeWithValueMaps.put("teamName", teamWithValueMap);
			}
			config.setCodeWithValueMaps(typeWithValueMaps);
		}
		
		String []titles = {"??????","????????????","????????????","????????????","??????","??????","?????????"};
		config.setTitles(titles);
		config.setSheetTitle("???????????????");
		if(dormitoryAttendanceVoList.size()!=0){
			for(DormitoryAttendanceVo daList :dormitoryAttendanceVoList){
				Dormitory d = this.dormitoryService.findDormitoryById(daList.getDormitoryId());
				if(d!=null){
					Floor f = this.floorService.findFloorByCode(d.getSchoolId(), d.getFloorCode());
					daList.setFloorName(f==null?"":f.getName());
				}
				
				
				DormitoryAttendanceVo daVo = new DormitoryAttendanceVo();
				BeanUtils.copyProperties(daList, daVo);
				maps.add(daVo);
			}
		}
		String filename = "???????????????.xls";
		SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
	}
	
	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, DormitoryAttendanceCondition condition) {
		
		if(user != null ) {
			condition.setSchoolId(user.getSchoolId());
		}
		
	}
	
	
	
	
}
