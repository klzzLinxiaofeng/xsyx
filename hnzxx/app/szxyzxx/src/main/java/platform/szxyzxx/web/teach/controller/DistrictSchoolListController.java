package platform.szxyzxx.web.teach.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.generalcode.model.Region;
import platform.education.generalcode.vo.RegionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping("/teach/districtSchoolList")
public class DistrictSchoolListController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(DistrictSchoolListController.class);
	private final static String BASE_PATH = "/teach/districtSchoolList";

	/**
	 * 区域学校列表
	 * @param sub
	 * @param dm
	 * @param province
	 * @param city
	 * @param district
	 * @param schoolCondition
	 * @param page
	 * @param order
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("schoolCondition") SchoolCondition schoolCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,Model model,
			@CurrentUser UserInfo user){
		String viewPath = null;
		//得到当前用户所管辖的区域
		String regionCode = user.getRegionCode();
		//得到当前用户所管辖的区域类型
		String level = user.getLevel();
		
		/*因为学校下拉框选中发送过来的是学校的id
		 * 所以要把学校id作为条件设置进去
		 */
		if(schoolCondition.getName()!=null){
			School school = this.schoolService.findSchoolById(Integer.parseInt(schoolCondition.getName()));
			schoolCondition.setId(school.getId());
			schoolCondition.setName(school.getName());
			
		}
			
		
		
		if("list".equals(sub)){
			viewPath = structurePath("/list");
		}else{
			viewPath = structurePath("/index");
		}
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<School>schoolList = null;
		List<Region>cityList  = null;
		Region region = null;
		Region  region1 = null;
		Region  region2 = null;
		RegionCondition regionCondition = new RegionCondition();
		try {
			//根据条件查找学校
			if(level=="1"||level.equals("1")){
				//当前用户所管辖的区域类型为省级
				schoolCondition.setProvince(regionCode);
				schoolList = this.schoolService.findSchoolByCondition(schoolCondition, page, order);
				/*则查询条件中的省下拉框是不可选状态，市区校级的下拉框是可选状态
				 * 首先根据regionCode查找到对应的省
				 * 再根据用户所管辖的省查找对应的市列表
				 */
				region = this.jcRegionService.findRegionById(Integer.parseInt(regionCode));
				regionCondition.setLevel(2);
				regionCondition.setParent(Integer.parseInt(regionCode));
				cityList = this.jcRegionService.findRegionByCondition(regionCondition, null, null);
				
				
			}else if(level=="2" || level.equals("2")){
				//当前用户所管辖的区域类型为市级
				/*则查询条件中的省、市下拉框是不可选状态，区校级的下拉框是可选状态
				 * 根据市级的regionCode查找对应的相关信息
				 * 再根据parent查找对应的省份
				 * 再根据用户所管辖的省市查找对应的区列表
				 */
				region1 = this.jcRegionService.findRegionById(Integer.parseInt(regionCode));
				region = this.jcRegionService.findRegionById(region1.getParent());
				schoolCondition.setProvince(String.valueOf(region.getId()));
				schoolCondition.setCity(String.valueOf(region1.getId()));
				schoolList = this.schoolService.findSchoolByCondition(schoolCondition, page, order);
				model.addAttribute("region1", region1);
			}else if(level=="3" || level.equals("3")){
				//当前用户所管辖的区域类型为区级
				/*则查询条件中的省、市、区下拉框是不可选状态，校级的下拉框是可选状态
				 * 根据区级的regionCode查找对应的相关信息
				 * 再根据parent查找对应的市
				 * 再根据parent查找对应的区
				 * 再根据用户所管辖的省市区查找对应的学校列表
				 */
				region2 = this.jcRegionService.findRegionById(Integer.parseInt(regionCode));
				region1 = this.jcRegionService.findRegionById(region2.getParent());
				region = this.jcRegionService.findRegionById(region1.getParent());
				schoolCondition.setProvince(String.valueOf(region.getId()));
				schoolCondition.setCity(String.valueOf(region1.getId()));
				schoolCondition.setDistrict(String.valueOf(region2.getId()));
				schoolList = this.schoolService.findSchoolByCondition(schoolCondition, page, order);
				model.addAttribute("region1", region1);
				model.addAttribute("region2", region2);
				
				
			}
		} catch (Exception e) {
			log.info("查询学校列表异常...");
			e.printStackTrace();
			
		}
		
		model.addAttribute("region", region);
		model.addAttribute("regionCode", regionCode);
		model.addAttribute("level", level);
		model.addAttribute("cityList", cityList);
		model.addAttribute("schoolList", schoolList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	private String structurePath(String subPath) {
		return BASE_PATH + subPath;
	}
	
	
	
	//根据省份、市以及区下异步获取学校下拉框的数据
	@RequestMapping(value = "/getSchoolList", method = RequestMethod.POST)
	@ResponseBody
	public List<School>getSchoolList(
			@RequestParam(value = "district", required = true) String district){
		SchoolCondition schoolCondition = new SchoolCondition();
		schoolCondition.setDistrict(district);
		List<School>schoolList = this.schoolService.findSchoolByCondition(schoolCondition, null, null);
		return schoolList;
	}
	
	
}
