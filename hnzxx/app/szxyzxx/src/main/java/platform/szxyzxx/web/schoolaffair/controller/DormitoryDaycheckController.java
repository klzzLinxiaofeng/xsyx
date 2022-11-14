package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
import java.util.List;

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

import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.model.DormitoryDaycheck;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.DormitoryCondition;
import platform.education.school.affair.vo.DormitoryDaycheckCondition;
import platform.education.school.affair.vo.DormitoryDaycheckVo;
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
@RequestMapping("/schoolaffair/dormitorydaycheck")
public class DormitoryDaycheckController extends BaseController{ 
	private static final Logger log = LoggerFactory
			.getLogger(DormitoryDaycheckController.class);
	private final static String viewBasePath = "/schoolaffair/dormitoryManager/dormitoryDaycheck";
	
	/**
	 * 日常检查情况列表页面
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
			@ModelAttribute("condition") DormitoryDaycheckCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		List<DormitoryDaycheckVo> checks = null;
		if ("list".equals(sub)) {
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			// 获取该学校当前学年
			SchoolTermCurrent stc = this.schoolTermCurrentService
					.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			condition.setSchoolYearId(stc.getSchoolYearId());
			checks = this.dormitoryDaycheckService.findDormitoryDaycheckByConditionVo(condition, page, order);
			if(checks.size()>0){
				for(DormitoryDaycheckVo ddv:checks){
					Dormitory d = this.dormitoryService.findDormitoryById(ddv.getDormitoryId());
					if(d!=null){
						Floor f = this.floorService.findFloorByCode(user.getSchoolId(), d.getFloorCode());
						ddv.setFloorName(f==null?"":f.getName());
					}
				}
			}
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("checks", checks);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<DormitoryDaycheck> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") DormitoryDaycheckCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		
		return this.dormitoryDaycheckService.findDormitoryDaycheckByCondition(condition, page, order);
	}
	
	/**
	 * 新增日常检查页面
	 * @param user
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(
			@CurrentUser UserInfo user,
			Model model
			) {
				//宿舍楼号
				DormitoryCondition condition = new DormitoryCondition();
				condition.setSchoolId(user.getSchoolId());
				List<DistinctDormitoryVo> dormitoryList = CommonUtil.uniquetList(floorService, dormitoryService, condition);
				
				
				model.addAttribute("dormitoryList", dormitoryList);
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * 保存日常检查信息
	 * @param dormitoryDaycheck
	 * @param user
	 * @return
	 */
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(DormitoryDaycheckVo dormitoryDaycheckVo, @CurrentUser UserInfo user) {
		
		DormitoryDaycheck dormitoryDaycheck = new DormitoryDaycheck();
		try {
			
			//根据year查找对应的学年id
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(String.valueOf(dormitoryDaycheckVo.getSchoolYearId()));
			SchoolYear sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			Dormitory dormitory = new Dormitory();
			dormitory.setFloorCode(dormitoryDaycheckVo.getFloorCode());
			dormitory.setDormitoryCode(dormitoryDaycheckVo.getDormitoryCode());
			dormitory.setIsDeleted(0);
			dormitory.setSchoolId(user.getSchoolId());
			Dormitory d = this.dormitoryService.findByDormitory(dormitory);
			
			dormitoryDaycheck.setSchoolId(user.getSchoolId());
			dormitoryDaycheck.setDormitoryId(d.getId());
			dormitoryDaycheck.setDormitoryCode(dormitoryDaycheckVo.getDormitoryCode());
			dormitoryDaycheck.setSchoolYearId(sy.getId());
			dormitoryDaycheck.setGradeId(dormitoryDaycheckVo.getGradeId());
			dormitoryDaycheck.setTeamNumber(dormitoryDaycheckVo.getTeamNumber());
			dormitoryDaycheck.setCheckType(dormitoryDaycheckVo.getCheckType());
			dormitoryDaycheck.setCheckResult(dormitoryDaycheckVo.getCheckResult());
			dormitoryDaycheck.setRemark(dormitoryDaycheckVo.getRemark());
			dormitoryDaycheck.setCreateDate(dormitoryDaycheckVo.getCreateDate());
			dormitoryDaycheck.setIsDeleted(0);
			dormitoryDaycheck = this.dormitoryDaycheckService.add(dormitoryDaycheck);
		} catch (Exception e) {
			log.info("新增信息异常..");
			e.printStackTrace();
		}
		return dormitoryDaycheck != null ? new ResponseInfomation(dormitoryDaycheck.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
    /**
     * 修改日常检查页面
     * @param id
     * @param model
     * @return
     */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		
			 	DormitoryDaycheckVo dormitoryDaycheck = new DormitoryDaycheckVo();
		
			    try {
			    	DormitoryDaycheck dormitoryDaychecks = this.dormitoryDaycheckService.findDormitoryDaycheckById(id);
					//根据schoolYearId得到对应的学年名称
					SchoolYear sy = this.schoolYearService.findSchoolYearById(dormitoryDaychecks.getSchoolYearId());
					//宿舍楼号
					DormitoryCondition condition = new DormitoryCondition();
					condition.setSchoolId(dormitoryDaychecks.getSchoolId());
					List<DistinctDormitoryVo> dormitoryList = CommonUtil.uniquetList(floorService, dormitoryService, condition);
					
					Dormitory d = this.dormitoryService.findDormitoryById(dormitoryDaychecks.getDormitoryId());
					
					dormitoryDaycheck.setId(dormitoryDaychecks.getId());
					dormitoryDaycheck.setSchoolId(dormitoryDaychecks.getSchoolId());
					dormitoryDaycheck.setFloorCode(d.getFloorCode());
					dormitoryDaycheck.setDormitoryId(d.getId());
					dormitoryDaycheck.setDormitoryCode(dormitoryDaychecks.getDormitoryCode());
					dormitoryDaycheck.setSchoolYearId(sy.getId());
					dormitoryDaycheck.setGradeId(dormitoryDaychecks.getGradeId());
					dormitoryDaycheck.setTeamNumber(dormitoryDaychecks.getTeamNumber());
					dormitoryDaycheck.setCheckType(dormitoryDaychecks.getCheckType());
					dormitoryDaycheck.setCheckResult(dormitoryDaychecks.getCheckResult());
					dormitoryDaycheck.setRemark(dormitoryDaychecks.getRemark());
					dormitoryDaycheck.setCreateDate(dormitoryDaychecks.getCreateDate());
					dormitoryDaycheck.setIsDeleted(0);
					
					
					
					model.addAttribute("sy", sy);
					model.addAttribute("dormitoryList", dormitoryList);
					model.addAttribute("dormitoryDaycheck", dormitoryDaycheck);
				} catch (Exception e) {
					log.info("====修改日常检查异常===");
					e.printStackTrace();
				}
				
		return new ModelAndView(structurePath("/editor"), model.asMap());
	}
	
	/**
	 * 保存修改日常检查
	 * @param id
	 * @param user
	 * @param dormitoryDaycheck
	 * @return
	 */
	@RequestMapping("/saveEditor")
	@ResponseBody
	public ResponseInfomation edit( 
			@CurrentUser UserInfo user,DormitoryDaycheckVo dormitoryDaycheckVo) {
		
		
			DormitoryDaycheck dormitoryDaycheck = new DormitoryDaycheckVo();
			
		try {
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(String.valueOf(dormitoryDaycheckVo.getSchoolYearId()));
			SchoolYear sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			
			
			Dormitory dormitory = new Dormitory();
			dormitory.setFloorCode(dormitoryDaycheckVo.getFloorCode());
			dormitory.setDormitoryCode(dormitoryDaycheckVo.getDormitoryCode());
			dormitory.setIsDeleted(0);
			dormitory.setSchoolId(user.getSchoolId());
			Dormitory d = this.dormitoryService.findByDormitory(dormitory);
			
			dormitoryDaycheck.setId(dormitoryDaycheckVo.getId());
			dormitoryDaycheck.setSchoolId(user.getSchoolId());
			dormitoryDaycheck.setDormitoryId(d.getId());
			dormitoryDaycheck.setDormitoryCode(dormitoryDaycheckVo.getDormitoryCode());
			dormitoryDaycheck.setSchoolYearId(sy.getId());
			dormitoryDaycheck.setGradeId(dormitoryDaycheckVo.getGradeId());
			dormitoryDaycheck.setTeamNumber(dormitoryDaycheckVo.getTeamNumber());
			dormitoryDaycheck.setCheckType(dormitoryDaycheckVo.getCheckType());
			dormitoryDaycheck.setCheckResult(dormitoryDaycheckVo.getCheckResult());
			dormitoryDaycheck.setRemark(dormitoryDaycheckVo.getRemark());
			dormitoryDaycheck.setCreateDate(dormitoryDaycheckVo.getCreateDate());
			dormitoryDaycheck.setIsDeleted(0);
			
			dormitoryDaycheck = this.dormitoryDaycheckService.modify(dormitoryDaycheck);
		} catch (Exception e) {
			log.info("...更新日常检查异常...");
			e.printStackTrace();
		}
		
		return dormitoryDaycheck != null ? new ResponseInfomation(dormitoryDaycheck.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * 
	 * 查看日常检查
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		DormitoryDaycheck dormitoryDaychecks = this.dormitoryDaycheckService.findDormitoryDaycheckById(id);
		DormitoryDaycheckVo dormitoryDaycheck = new DormitoryDaycheckVo();
		dormitoryDaycheck.setId(id);
		dormitoryDaycheck.setSchoolId(dormitoryDaychecks.getSchoolId());
		dormitoryDaycheck.setDormitoryId(dormitoryDaychecks.getDormitoryId());
		dormitoryDaycheck.setDormitoryCode(dormitoryDaychecks.getDormitoryCode());
		dormitoryDaycheck.setSchoolYearId(dormitoryDaychecks.getSchoolYearId());
		dormitoryDaycheck.setGradeId(dormitoryDaychecks.getGradeId());
		dormitoryDaycheck.setTeamNumber(dormitoryDaychecks.getTeamNumber());
		dormitoryDaycheck.setCheckType(dormitoryDaychecks.getCheckType());
		dormitoryDaycheck.setCheckResult(dormitoryDaychecks.getCheckResult());
		dormitoryDaycheck.setRemark(dormitoryDaychecks.getRemark());
		dormitoryDaycheck.setCreateDate(dormitoryDaychecks.getCreateDate());
		dormitoryDaycheck.setIsDeleted(0);
		
		if(dormitoryDaycheck!=null){
			Dormitory d = this.dormitoryService.findDormitoryById(dormitoryDaycheck.getDormitoryId());
			if(d!=null){
				Floor f = this.floorService.findFloorByCode(d.getSchoolId(), d.getFloorCode());
				dormitoryDaycheck.setFloorName(f==null?"":f.getName());
			}
		}
		
		//根据schoolYearId得到对应的学年名称
		SchoolYear sy = this.schoolYearService.findSchoolYearById(dormitoryDaycheck.getSchoolYearId());
		model.addAttribute("isCK", "disable");
		model.addAttribute("dormitoryDaycheck", dormitoryDaycheck);
		model.addAttribute("sy", sy);
		return new ModelAndView(structurePath("/view"), model.asMap());
	}

	

	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/**
	 * 删除日常检查信息
	 * @param id
	 * @param dormitoryDaycheck
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, DormitoryDaycheck dormitoryDaycheck) {
		if (dormitoryDaycheck != null) {
			dormitoryDaycheck.setId(id);
		}
		try {
			this.dormitoryDaycheckService.abandon(dormitoryDaycheck);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	
	/**
	 * 根据查询的结果导出日常检查的信息列表
	 * @param request
	 * @param response
	 * @param dormitoryDaycheck
	 * @param user
	 * @param page
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/dormitoryDayCheckOut")  
	public void dormitoryDayCheckOut(
			HttpServletRequest request,
			HttpServletResponse response,
			DormitoryDaycheckVo dormitoryDaycheckVo,
			@CurrentUser UserInfo user
			
			)throws Exception{
		// 获取该学校当前学年
//				SchoolYear sy = null;
				if(dormitoryDaycheckVo.getSchoolYearId()!=null){
					//根据year得到学年的id
					SchoolYearCondition syCondition = new SchoolYearCondition();
					syCondition.setSchoolId(user.getSchoolId());
					syCondition.setYear(String.valueOf(dormitoryDaycheckVo.getSchoolYearId()));
//					sy = this.schoolYearService.findSchoolYearByYear(syCondition);
				}
		ParseConfig config = SzxyExcelTookit.getConfig("dormitoryDaycheckVo");
		List<Object> maps = new ArrayList<Object>();
		DormitoryDaycheckCondition ddcCondition = new DormitoryDaycheckCondition();
		ddcCondition.setSchoolId(user.getSchoolId());
		ddcCondition.setGradeId(dormitoryDaycheckVo.getGradeId());
		ddcCondition.setTeamNumber(dormitoryDaycheckVo.getTeamNumber());
		ddcCondition.setCheckType(dormitoryDaycheckVo.getCheckType());
		ddcCondition.setDormitoryCode(dormitoryDaycheckVo.getDormitoryCode());
		ddcCondition.setIsDeleted(0);
		//根据条件查询对应的宿舍日常检查信息列表
		List<DormitoryDaycheckVo>dormitoryDaycheckList = this.dormitoryDaycheckService.findDormitoryDaycheckByConditionVo(ddcCondition, null, null);
		String []titles = {"日期","检查类型","宿舍楼号","寝室编号","检查结果","检查说明"};
		config.setTitles(titles);
		config.setSheetTitle("日常检查表");
		if(dormitoryDaycheckList.size()!=0){
			for(DormitoryDaycheckVo ddcList :dormitoryDaycheckList){
				Dormitory d = this.dormitoryService.findDormitoryById(ddcList.getDormitoryId());
				if(d!=null){
					Floor f = this.floorService.findFloorByCode(d.getSchoolId(), d.getFloorCode());
					ddcList.setFloorName(f==null?"":f.getName());
				}
				
				
				DormitoryDaycheckVo ddCheckVo = new DormitoryDaycheckVo();
				BeanUtils.copyProperties(ddcList, ddCheckVo);
				maps.add(ddCheckVo);
			}
		}
		String filename = "日常检查表.xls";
		SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
	}
	
	
	private void conditionFilter(UserInfo user, DormitoryDaycheckCondition condition) {
		if(user != null) {
			condition.setSchoolId(user.getSchoolId());
		}
		
	}
	
}
