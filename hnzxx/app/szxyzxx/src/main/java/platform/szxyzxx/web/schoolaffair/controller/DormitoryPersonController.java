package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.school.affair.model.DormitoryPerson;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.DormitoryCondition;
import platform.education.school.affair.vo.DormitoryPersonCondition;
import platform.education.school.affair.vo.DormitoryPersonVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolaffair.util.CommonUtil;
import platform.szxyzxx.web.schoolaffair.vo.DistinctDormitoryVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;




@Controller
@RequestMapping("/schoolaffair/dormitoryPerson")
public class DormitoryPersonController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/dormitoryManager/dormitoryPerson";
	
	/**
	 * 宿舍人员列表
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
			@ModelAttribute("condition") DormitoryPersonCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		List<DormitoryPersonVo> dormitoryPersonvo = null;
		if ("list".equals(sub)) {
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			condition.setSchoolId(user.getSchoolId());
			// 获取该学校当前学年
			SchoolTermCurrent stc = this.schoolTermCurrentService
					.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			condition.setSchoolYearId(stc.getSchoolYearId());
			condition.setIsDeleted(0);
			
			//宿舍人员列表
			dormitoryPersonvo = this.dormitoryPersonService.findDormitoryPersonByConditionVo(condition, page, order);
			
			/*List<DormitoryPerson> dormitoryPerson = this.dormitoryPersonService.findDormitoryPersonByCondition(condition,null,null);*/
			
			if(dormitoryPersonvo.size()>0){
				for(DormitoryPersonVo dpv : dormitoryPersonvo){
					String floorCode = this.dormitoryService.findDormitoryById(dpv.getDormitoryId()).getFloorCode();
					Floor f = this.floorService.findFloorByCode(user.getSchoolId(), floorCode==null?"-1":floorCode);
					dpv.setFloorName(f==null?"":f.getName());
					dpv.setDormitoryCode(this.dormitoryService.findDormitoryById(dpv.getDormitoryId()).getDormitoryCode());
					
					DormitoryPersonCondition dpCondition = new DormitoryPersonCondition();
					dpCondition.setSchoolYearId(stc.getSchoolYearId());
					dpCondition.setIsDeleted(0);
					dpCondition.setDormitoryId(dpv.getDormitoryId());
					dpCondition.setDormitoryCode(dpv.getDormitoryCode());
					List<DormitoryPerson> dormitoryPerson = this.dormitoryPersonService.findDormitoryPersonByCondition(dpCondition,null,null);
					String studentNames = "";
					if(dormitoryPerson.size()>0){
						for(DormitoryPerson dp :dormitoryPerson){
							studentNames += this.studentService.findStudentById(dp.getStudentId()).getName()+"， ";
						}
					}
					dpv.setStudentNames("".equals(studentNames)?"":studentNames.substring(0, studentNames.length()-2));
				}
			}
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("dormitoryPersonvo", dormitoryPersonvo);
//		model.addAttribute("dormitoryPerson", dormitoryPerson);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<DormitoryPerson> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") DormitoryPersonCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.dormitoryPersonService.findDormitoryPersonByCondition(condition, page, order);
	}
	
	/**
	 * 批量分配页面
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = true) String year, 
			Model model
			) {
		
		//班级列表
		School school = this.schoolService.findSchoolById(user.getSchoolId());
		SchoolTermCurrent schoolTermCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		List<Team> teamList = this.teamService.findCurrentTeamOfSchoolAndYear(user.getSchoolId(), schoolTermCurrent.getSchoolYear());
		//宿舍楼号
		DormitoryCondition  dormitoryCondition = new DormitoryCondition();
		dormitoryCondition.setSchoolId(user.getSchoolId());
		dormitoryCondition.setIsDeleted(0);
		List<DistinctDormitoryVo>floorCodeList = CommonUtil.distnctList(floorService, dormitoryService, dormitoryCondition);
		model.addAttribute("floorCodeList", floorCodeList);
		model.addAttribute("teamList", teamList);
		model.addAttribute("school", school);
		return new ModelAndView(structurePath("/inputPage"));
	}

	

	/**
	 * 修改宿舍人员信息页面
	 * @param id
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user,
			Model model) {
		DormitoryPerson dormitoryPerson = this.dormitoryPersonService.findDormitoryPersonById(id);
		DormitoryPersonCondition condition = new DormitoryPersonCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setSchoolYearId(dormitoryPerson.getSchoolYearId());
		condition.setDormitoryId(dormitoryPerson.getDormitoryId());
		
		
		List<DormitoryPersonVo>dormitoryPersonvo = this.dormitoryPersonService.findDormitoryPersonByConditionVo1(condition, null, null);
		if(dormitoryPersonvo.size()>0){
			for(DormitoryPersonVo dpv : dormitoryPersonvo){
				dpv.setStudentName(this.studentService.findStudentById(dpv.getStudentId()).getName());
			}
		}
		model.addAttribute("dormitoryPersonvo", dormitoryPersonvo);
		return new ModelAndView(structurePath("/editor"), model.asMap());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, DormitoryPerson dormitoryPerson) {
		dormitoryPerson.setId(id);
		dormitoryPerson = this.dormitoryPersonService.modify(dormitoryPerson);
		return dormitoryPerson != null ? new ResponseInfomation(dormitoryPerson.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * 查看宿舍人员信息页面 
	 * @param user
	 * @param condition
	 * @param floorCode
	 * @param dormitoryCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") DormitoryPersonCondition condition,
			@RequestParam(value = "dormitoryId", required = true) Integer dormitoryId,
			Model model) {
		conditionFilter(user, condition);
		condition.setSchoolId(user.getSchoolId());
			// 获取该学校当前学年
		SchoolTermCurrent stc = this.schoolTermCurrentService
					.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		condition.setSchoolYearId(stc.getSchoolYearId());
		condition.setSchoolId(user.getSchoolId());
		condition.setDormitoryId(dormitoryId);
		List<DormitoryPersonVo> dormitoryPersonVo = this.dormitoryPersonService.findDormitoryPersonByConditionVo1(condition, null,null);
		
		if(dormitoryPersonVo.size()>0){
			for(DormitoryPersonVo dpv : dormitoryPersonVo){
				dpv.setStudentName(this.studentService.findStudentById(dpv.getStudentId()).getName());
			}
		}
		
		
		model.addAttribute("dormitoryPersonVo", dormitoryPersonVo);
		model.addAttribute("isCK", "disable");
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	/**
	 * 批量调换页面
	 * @return
	 */
	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public ModelAndView placePage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = true) String year, 
			Model model) {
		List<Team> teamList = this.teamService.findCurrentTeamOfSchoolAndYear(user.getSchoolId(), year);
		//宿舍楼号
		DormitoryCondition condition = new DormitoryCondition();
		condition.setSchoolId(user.getSchoolId());
		/*List<Dormitory> dormitoryList = this.dormitoryService.findDormitoryUnique(condition);*/
		List<DistinctDormitoryVo>dormitoryList = CommonUtil.distnctList(floorService, dormitoryService, condition);
		
		DormitoryCondition  dormitoryCondition = new DormitoryCondition();
		dormitoryCondition.setSchoolId(user.getSchoolId());
		/*List<Dormitory>floorCodeList = this.dormitoryService.findDormitoryUnique(dormitoryCondition);*/
		List<DistinctDormitoryVo>floorCodeList = CommonUtil.distnctList(floorService, dormitoryService, dormitoryCondition);
		model.addAttribute("floorCodeList", floorCodeList);
		model.addAttribute("dormitoryList", dormitoryList);
		model.addAttribute("teamList", teamList);
		model.addAttribute("year", year);
		return new ModelAndView(structurePath("/placePage"), model.asMap());
	}
	
	
	
	/**
	 * 清除宿舍人员信息
	 * @param id
	 * @param dormitoryPerson
	 * @return
	 */
	@RequestMapping("/deleteForDormitoryCode")
	@ResponseBody
	public String deleteForDormitoryCode( @CurrentUser UserInfo user,
			@RequestParam(value = "schoolYearId", required = true) String schoolYearId,
			@RequestParam(value = "dormitoryId", required = true) Integer dormitoryId
			) {
		try {
			DormitoryPerson person = new DormitoryPerson();
			person.setSchoolId(user.getSchoolId());
			person.setSchoolYearId(Integer.parseInt(schoolYearId));
			person.setDormitoryId(dormitoryId);
			person.setModifyDate(new Date());
			
			this.dormitoryPersonService.updateByDormitoryCondition(person);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	/**
	 * 删除某个宿舍成员信息
	 * @param id
	 * @param dormitoryPerson
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, DormitoryPerson dormitoryPerson) {
		if (dormitoryPerson != null) {
			dormitoryPerson.setId(id);
		}
		try {
			dormitoryPerson.setModifyDate(new Date());
			this.dormitoryPersonService.abandon(dormitoryPerson);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 导出宿舍人员信息表
	 * @param request
	 * @param response
	 * @param dormitoryPerson
	 * @param user
	 * @param page
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/dormitoryPersonCheck")  
	public void dormitoryPersonCheck(
			HttpServletRequest request,
			HttpServletResponse response,
			DormitoryPerson dormitoryPerson,
			@CurrentUser UserInfo user
			
			)throws Exception{
		// 获取该学校当前学年
//		SchoolYear sy = new SchoolYear();
		if(dormitoryPerson.getSchoolYearId()!=null){
			//根据year得到学年的id
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(String.valueOf(dormitoryPerson.getSchoolYearId()));
//			sy = this.schoolYearService.findSchoolYearByYear(syCondition);
		}
		ParseConfig config = SzxyExcelTookit.getConfig("dormitoryPersonVo");
		List<Object> maps = new ArrayList<Object>();
		
		DormitoryPersonCondition dpCondition = new DormitoryPersonCondition();
		dpCondition.setSchoolId(user.getSchoolId());
		dpCondition.setGradeId(dormitoryPerson.getGradeId());
		dpCondition.setTeamNumber(dormitoryPerson.getTeamNumber());
		dpCondition.setSex(dormitoryPerson.getSex());
		dpCondition.setDormitoryId(dormitoryPerson.getDormitoryId());
		dpCondition.setDormitoryCode(dormitoryPerson.getDormitoryCode());
		dpCondition.setIsDeleted(0);
		//根据条件查询对应的宿舍人员信息列表
		List<DormitoryPersonVo> dormitoryPersonvo = this.dormitoryPersonService.findDormitoryPersonByConditionVo(dpCondition, null,null);
		
		
		if(dormitoryPersonvo.size()>0){
			for(DormitoryPersonVo dpv : dormitoryPersonvo){
				String floorCode = this.dormitoryService.findDormitoryById(dpv.getDormitoryId()).getFloorCode();
				Floor f = this.floorService.findFloorByCode(user.getSchoolId(), floorCode==null?"-1":floorCode);
				dpv.setFloorName(f==null?"":f.getName());
				dpv.setDormitoryCode(this.dormitoryService.findDormitoryById(dpv.getDormitoryId()).getDormitoryCode());
				List<DormitoryPerson>dormitorPersonList = this.dormitoryPersonService.findDormitoryPersonByCondition(dpCondition, null, null);
				String studentNames = "";
				if(dormitorPersonList.size()>0){
					for(DormitoryPerson dp :dormitorPersonList){
						if(dp.getDormitoryId().equals(dpv.getDormitoryId())){
							studentNames += this.studentService.findStudentById(dp.getStudentId()).getName()+"， ";
							
						}
					}
				}
				
				dpv.setStudentNames("".equals(studentNames)?"":studentNames.substring(0, studentNames.length()-2));
				
			}
			
			
		}
		
		
		String []titles = {"宿舍楼号","寝室编号","入住性别","可住人数","入住人员"};
		config.setTitles(titles);
		config.setSheetTitle("宿舍人员表");
		if(dormitoryPersonvo.size()!=0){
			for(DormitoryPerson dpList:dormitoryPersonvo){
				DormitoryPersonVo dpv = new DormitoryPersonVo();
				BeanUtils.copyProperties(dpList, dpv);
				maps.add(dpv);
			}
		}
		String filename = "宿舍人员表.xls";
		SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
	}
	
	
	
	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, DormitoryPersonCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if (schoolId == null) {
			if (user != null) {
				condition.setSchoolId(user.getSchoolId());
			}
		}
	}
	
	
	
}
	

