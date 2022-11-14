package platform.szxyzxx.web.teach.controller;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.education.generalcode.model.Region;
import platform.education.generalcode.vo.RegionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 学生统计管理
 * @author admin
 *
 */
@Controller
@RequestMapping("/teach/studentStatistics")
public class StudentStatisticsController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(StudentStatisticsController.class);

	@RequestMapping("/getStudentStatistics")
	public ModelAndView getStudentStatistics(
			@CurrentUser UserInfo user,
			HttpServletRequest request){
		String arerTemp = "";
		String manNumTemp ="";
		String womenNumTemp ="";
		String sumNumTemp = "";
		ModelAndView mav = new ModelAndView();
		String viewPath ="/teach/studentStatistics/getStudentStatistics";
		try{
			String regionCode = user.getRegionCode();
			String level = user.getLevel();
			//1=省
			//2=市 
			//3=区
			String privinceTemp = "";
			String cityTemp ="";
			String districtTemp="";
			RegionCondition regionCondition = new RegionCondition();
			if(level=="1" || "1".equals(level)){
				regionCondition.setParent(Integer.parseInt(regionCode));
				privinceTemp = regionCode;
			}else if(level=="2" || "2".equals(level)){
				regionCondition.setParent(Integer.parseInt(regionCode));
				cityTemp = regionCode;
			}else if(level=="3" || "3".equals(level)){
				regionCondition.setCode(regionCode);
				districtTemp = regionCode;
			}
			Region regionName =  getCityName(regionCode);
			//String city = "440100";
			//String district = request.getParameter("district");
			//RegionCondition regionCondition = new RegionCondition();
			//regionCondition.setParent(Integer.parseInt(city));
			//regionCondition.setCode(district);
			List<Region> regionList = this.jcRegionService.findRegionByCondition(regionCondition, null, null);
			for(Region region : regionList){
				SchoolCondition schoolCondition = new SchoolCondition();
				if((level=="2" || "2".equals(level)) || (level=="3" || "3".equals(level))){
					schoolCondition.setDistrict(region.getCode());
				}else{
					schoolCondition.setCity(region.getCode());
				}
				List<School> schoolList = this.schoolService.findSchoolByCondition(schoolCondition, null, null);
				StudentVo getStudentVo = getStudentVo(schoolList);
				arerTemp +="'"+ region.getName()+"',";
				manNumTemp += getStudentVo.getManNum()+",";
				womenNumTemp += getStudentVo.getWomanNum()+",";
				
				sumNumTemp += getStudentVo.getSumNum()+",";
			}
			
			String arerTemp_ = "["+deleteEndSingle(arerTemp)+"]";
			String manNumTemp_ = "["+deleteEndSingle(manNumTemp)+"]";
			String womenNumTemp_ = "["+deleteEndSingle(womenNumTemp)+"]";
			String sumNumTemp_ = "["+deleteEndSingle(sumNumTemp)+"]";
			String manNumStr = "0";
			String womanNumStr = "0";
			String sumNumStr = "0";
			if(deleteEndSingle(manNumTemp)==null||"".equals(deleteEndSingle(manNumTemp))){
				
			}else{
				manNumStr = deleteEndSingle(manNumTemp);
			}
			
			if(deleteEndSingle(womenNumTemp)==null||"".equals(deleteEndSingle(womenNumTemp))){
				
			}else{
				womanNumStr = deleteEndSingle(womenNumTemp);
			}
			
			if(deleteEndSingle(sumNumTemp)==null||"".equals(deleteEndSingle(sumNumTemp))){
				
			}else{
				sumNumStr = deleteEndSingle(sumNumTemp);
			}
			BigDecimal manNumTempBig = new BigDecimal(addBigDec(manNumStr).toString());
			BigDecimal womenNumTempBig = new BigDecimal(addBigDec(womanNumStr).toString());
			BigDecimal sumNumTempBig = new BigDecimal(addBigDec(sumNumStr).toString());
			BigDecimal weiZhiBig = sumNumTempBig.subtract(womenNumTempBig).subtract(manNumTempBig);
			String weiZhi = "["+weiZhiBig.toString()+"]";
			mav.addObject("arerTemp_", arerTemp_);
			mav.addObject("manNumTemp_", manNumTemp_);
			mav.addObject("womenNumTemp_", womenNumTemp_);
			mav.addObject("sumNumTemp_", sumNumTemp_);
			mav.addObject("weiZhi", weiZhi);
			
			
			mav.addObject("districtTemp", districtTemp);
			mav.addObject("cityTemp", cityTemp);
			mav.addObject("privinceTemp", privinceTemp);
			mav.addObject("cityName", regionName.getName());
			mav.addObject("level", level);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("学生统计异常！");
			e.printStackTrace();
		}
		return mav;
	}
	
	public  BigDecimal addBigDec(String numStr) {
		
		String[] arrayNum = numStr.split(",");
		BigDecimal sumTemp = new BigDecimal("0");
		for (String num : arrayNum) {
			sumTemp = sumTemp.add(new BigDecimal(num));
		}
		
		System.out.println(sumTemp.toString());
		
		return sumTemp;
		
}
	
	public Region getCityName(String code){
		RegionCondition regionCondition = new RegionCondition();
		regionCondition.setCode(code);
		List<Region>  regionList = this.jcRegionService.findRegionByCondition(regionCondition, null, null);
		Region region = regionList.get(0);
		return region;
	}
	
	/***
	 * 去掉字符串最后一个逗号
	 * @param stringTemp
	 * @return
	 */
	public String deleteEndSingle(String stringTemp){
		String strTemp = "";
		if(stringTemp != null && !"".equals(stringTemp)){
			strTemp = stringTemp.substring(0,stringTemp.length()-1);
		}
		return strTemp;
	}
	
	public StudentVo getStudentVo(List<School> schoolList){
		StudentVo studentVo = new StudentVo();
		int manNum = 0;
		int womanNum = 0;
		int sumNum = 0;
		for(School school : schoolList){
			int schoolId = school.getId();
			StudentCondition studnetCondition1 = new StudentCondition();
			studnetCondition1.setSchoolId(schoolId);
			studnetCondition1.setSex("1");//男
			List<Student> studentList1 =this.studentService.findStudentByCondition(studnetCondition1, null, null);
			
			manNum += studentList1.size();
			StudentCondition studentCondition0 = new StudentCondition();
			studentCondition0.setSchoolId(schoolId);
			studentCondition0.setSex("2");//女
			List<Student> studentList0 = this.studentService.findStudentByCondition(studentCondition0, null, null);
			womanNum +=studentList0.size();
			sumNum = manNum+womanNum;
		}
		studentVo.setManNum(String.valueOf(manNum));
		studentVo.setWomanNum(String.valueOf(womanNum));
		studentVo.setSumNum(String.valueOf(sumNum));
		return studentVo;
	}
	
}
