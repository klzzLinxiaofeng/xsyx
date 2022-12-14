package platform.szxyzxx.web.schoolaffair.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.DormitoryCondition;
import platform.education.school.affair.vo.DormitoryVo;
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
@RequestMapping("/schoolaffair/dormitory")
public class DormitoryController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(DormitoryController.class);
	private final static String viewBasePath = "/schoolaffair/dormitoryManager/dormitory";

	/**
	 * ??????????????????
	 * 
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
	public ModelAndView getDormitoryList(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") DormitoryCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		List<DistinctDormitoryVo>distinctList = null;
		List<DormitoryVo> dormitories = null;
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDeleted(0);
		
		//???????????????????????????????????????????????? 
		distinctList = CommonUtil.distnctList(floorService, dormitoryService, condition);
		//??????????????????
		dormitories = this.dormitoryService.findDormitoryByConditionVo(condition, page, order);
		if ("list".equals(sub)) {
			order.setProperty(order.getProperty() != null ? order.getProperty()
					: "create_date");
			
			
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("dormitories", dormitories);
		model.addAttribute("dormitory", distinctList);
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Dormitory> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") DormitoryCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.dormitoryService.findDormitoryByCondition(condition, page,
				order);
	}

	/**
	 * ????????????????????????
	 * 
	 * @param dormitory
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addDormitoryPage(
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@CurrentUser UserInfo user, Model model) {
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * ??????????????????
	 * 
	 * @param dormitory
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addDormitory(Dormitory dormitory,
			@CurrentUser UserInfo user) {
		dormitory.setSchoolId(user.getSchoolId());
		dormitory.setCreateDate(new Date());
		dormitory.setModifyDate(new Date());
		dormitory.setIsDeleted(0);
		
		Dormitory dormitory1 = this.dormitoryService.findByDormitory(dormitory);
		if(dormitory1==null){
			dormitory = this.dormitoryService.add(dormitory);
			return dormitory != null ? new ResponseInfomation(dormitory.getId(),
					ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
		}else{
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
		
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user, Model model) {
		Dormitory dormitory = this.dormitoryService.findDormitoryById(id);
		
		//????????????id?????????code???????????????????????????
		Floor floor = this.floorService.findFloorByCode(user.getSchoolId(),
				dormitory.getFloorCode());
		model.addAttribute("dormitory", dormitory);
		model.addAttribute("floor", floor);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user, Model model) {
		Dormitory dormitory = this.dormitoryService.findDormitoryById(id);
		
		//????????????id?????????code???????????????????????????
		Floor floor = this.floorService.findFloorByCode(user.getSchoolId(),
				dormitory.getFloorCode());
		model.addAttribute("isCK", "disable");
		model.addAttribute("dormitory", dormitory);
		model.addAttribute("floor", floor);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @param dormitory
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			Dormitory dormitory) {
		if (dormitory != null) {
			dormitory.setId(id);
		}
		try {
			this.dormitoryService.abandon(dormitory);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			Dormitory dormitory) {
		dormitory.setId(id);
		
		Dormitory dormintoryFirst = this.dormitoryService.findDormitoryById(id);
		
		Dormitory dormitoryNext = new Dormitory();
		
		dormitoryNext.setSchoolId(dormintoryFirst.getSchoolId());
		if(dormitory.getFloorCode()==null){
			dormitoryNext.setFloorCode(dormintoryFirst.getFloorCode());
		}else{
			dormitoryNext.setFloorCode(dormitory.getFloorCode());
		}
		
		if(dormitory.getDormitoryCode()==null){
			dormitoryNext.setDormitoryCode(dormintoryFirst.getDormitoryCode());
		}else{
			dormitoryNext.setDormitoryCode(dormitory.getDormitoryCode());
		}
		
		Dormitory dormitory1 = this.dormitoryService.findByDormitory(dormitoryNext);
		if(dormitory1==null){
			dormitory = this.dormitoryService.modify(dormitory);
			return dormitory != null ? new ResponseInfomation(dormitory.getId(),
					ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
		}else if(dormitory1.getSchoolId().equals(dormintoryFirst.getSchoolId())
				&& dormitory1.getFloorCode().equals(dormintoryFirst.getFloorCode())
				&& dormitory1.getDormitoryCode().equals(dormintoryFirst.getDormitoryCode())){
			
			dormitory = this.dormitoryService.modify(dormitory);
			return dormitory != null ? new ResponseInfomation(dormitory.getId(),
					ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
			
		}else{
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, DormitoryCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if (schoolId == null) {
			if (user != null) {
				condition.setSchoolId(user.getSchoolId());
			}
		}
	}

	/**
	 * ???????????????????????????????????????
	 * 
	 * @param request
	 * @param response
	 * @param dormitory
	 * @param user
	 * @param page
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/dormitoryVoCheck")
	public void dormitoryVoCheck(HttpServletRequest request,
			HttpServletResponse response, Dormitory dormitory,
			@CurrentUser UserInfo user

	) throws Exception {
		ParseConfig config = SzxyExcelTookit.getConfig("dormitoryVo");
		List<Object> maps = new ArrayList<Object>();
		Map<Object, Map<Object, Object>> typeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
		// Map<Object, Map<Object, Object>> typeWithValueMaps = new
		// HashMap<Object, Map<Object, Object>>();
		DormitoryCondition dCondition = new DormitoryCondition();
		dCondition.setSchoolId(user.getSchoolId());
		dCondition.setFloorCode(dormitory.getFloorCode());
		dCondition.setSex(dormitory.getSex());
		dCondition.setDormitoryCode(dormitory.getDormitoryCode());
		dCondition.setIsDeleted(0);
		// ?????????????????????????????????????????????
		List<DormitoryVo> dormitoryList = this.dormitoryService
				.findDormitoryByConditionVo(dCondition, null, null);

		//????????????code???????????????????????????
		if(dormitory.getFloorCode()!=null && !"".equals(dormitory.getFloorCode())){
			Floor floor = this.floorService.findFloorByCode(user.getSchoolId(), dormitory.getFloorCode());
			Map<Object,Object>teamWithValueMap = new HashMap<Object, Object>();
			teamWithValueMap.put(dormitory.getFloorCode(), floor.getName());
			typeWithValueMaps.put("name", teamWithValueMap);
			config.setCodeWithValueMaps(typeWithValueMaps);
		}else{
			Map<Object,Object>teamWithValueMap = new HashMap<Object, Object>();
			for(int i=0;i<dormitoryList.size();i++){
				Floor floor1 = this.floorService.findFloorByCode(user.getSchoolId(), dormitoryList.get(i).getFloorCode());
				teamWithValueMap.put(dormitoryList.get(i).getFloorCode(), floor1.getName());
				typeWithValueMaps.put("name", teamWithValueMap);
			}
			config.setCodeWithValueMaps(typeWithValueMaps);
		}
		
		
		String[] titles = { "????????????", "????????????", "????????????", "????????????" };
		config.setTitles(titles);
		config.setSheetTitle("???????????????");
		if (dormitoryList.size() != 0) {
			for (Dormitory dList : dormitoryList) {
				DormitoryVo domitoryVo = new DormitoryVo();
				BeanUtils.copyProperties(dList, domitoryVo);
				maps.add(domitoryVo);
			}
		}
		String filename = "???????????????.xls";
		SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,
				filename);
	}

	/**
	 * ??????????????????????????????
	 */
	@RequestMapping(value = "/downLoadDormitoryPage", method = RequestMethod.GET)
	public ModelAndView downLoadDormitoryPage() {

		return new ModelAndView(structurePath("/downLoadDormitoryPage"));
	}

	@RequestMapping(value = "/downLoadDormitoryInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfomation downLoadDormitory(@CurrentUser UserInfo user,
			DormitoryVo dormitoryVo, HttpServletResponse response,
			HttpServletRequest request, Page page, Order order) {
		ResponseInfomation tesponseInfomation = null;
		dormitoryVo.setSchoolId(user.getSchoolId());
		List<Object> list = new ArrayList<Object>();

		ParseConfig config = SzxyExcelTookit.getConfig("dormitoryVoInsert");
		String filename = "??????????????????" + ".xls";
		try {

			list.add(dormitoryVo);
			SzxyExcelTookit.exportExcelToWEB(list, config, request, response,
					filename);
		} catch (UnsupportedEncodingException e) {
			log.info("????????????????????????????????????..");
			e.printStackTrace();
		}
		tesponseInfomation = new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC);

		return tesponseInfomation;
	}

	/**
	 * ????????????????????????
	 */
	@RequestMapping("/upLoadDormitoryInfoPage")
	public ModelAndView upLoadStudentAwardInfoPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(this.structurePath("/upLoadDormitoryInfoPage"));
		return mav;
	}

	/**
	 * ??????????????????
	 * 
	 * @param fileUpload
	 * @param userInfo
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/upLoadDormitoryInfo")
	@ResponseBody
	public ResponseInfomation upLoadDormitoryInfo(
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@CurrentUser UserInfo userInfo, HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		// ??????????????????
		List<DormitoryVo> errorDormitoryVoList = new ArrayList<DormitoryVo>();
//		String fileName = fileUpload.getOriginalFilename();// ???????????????
		InputStream is = null;

		try {

			is = fileUpload.getInputStream();
			// fileName.lastIndexOf(".") + 1,
			// fileName.length()fileName.substring("xls");
			String suffix = "xls";
			ParseConfig config = SzxyExcelTookit.getConfig("dormitoryVoInsert");

			List<Object> dormitoryVoList = SzxyExcelTookit.excelDataToModels(
					config, is, suffix);
			for (Object object : dormitoryVoList) {
				DormitoryVo dormitoryvo = (DormitoryVo) object;
				dormitoryvo.setSchoolId(userInfo.getSchoolId());
				;
				DormitoryVo dv = new DormitoryVo();
				try {
					dv = this.dormitoryService.addExcel(dormitoryvo);
					if (dv.getErrorInfo() == null
							|| "".equals(dv.getErrorInfo())) {

					} else {
						dormitoryvo.setErrorInfo(dv.getErrorInfo());
						errorDormitoryVoList.add(dormitoryvo);
					}
				} catch (Exception e) {
					dormitoryvo.setErrorInfo(e.getMessage());
					errorDormitoryVoList.add(dormitoryvo);
					e.printStackTrace();
				}

			}
		} catch (Exception e) {

			log.info("????????????????????????????????????..");
			new ResponseInfomation("", ResponseInfomation.OPERATION_FAIL);
			e.printStackTrace();
		}

		// ????????????excle???????????????
		if (errorDormitoryVoList.size() > 0) {
			List<Object> list = new ArrayList<Object>();
			// ????????????
			ParseConfig config = SzxyExcelTookit.getConfig();
			String[] titles = { "????????????", "????????????", "????????????", "????????????", "????????????", "??????",
					"????????????" };
			String[] fieldNames = { "floorName", "floorCode", "dormitoryCode",
					"sex", "capacity", "remark", "rank", "errorInfo" };

			config.setTitles(titles);
			config.setFieldNames(fieldNames);
			config.setSheetTitle("??????????????????????????????");
			String filename = "??????????????????????????????" + ".xls";
			try {
				for (DormitoryVo dormitoryvo : errorDormitoryVoList) {
					int num = 0;
					Map<String, Object> map = new HashMap<String, Object>();
//					map.put(fieldNames[num++], dormitoryvo.getFloorName());
					map.put(fieldNames[num++], dormitoryvo.getFloorCode());
					map.put(fieldNames[num++], dormitoryvo.getDormitoryCode());
					map.put(fieldNames[num++], dormitoryvo.getSex());
					map.put(fieldNames[num++], dormitoryvo.getCapacity());
					map.put(fieldNames[num++], dormitoryvo.getRemark());
					map.put(fieldNames[num++], dormitoryvo.getErrorInfo());
					list.add(map);
				}
				SzxyExcelTookit.exportExcelToWEB(list, config, request,
						response, filename);
			} catch (Exception e) {
				log.error(e.getMessage());

				e.printStackTrace();
			}
		}

		return new ResponseInfomation("", ResponseInfomation.OPERATION_SUC);

	}

}
