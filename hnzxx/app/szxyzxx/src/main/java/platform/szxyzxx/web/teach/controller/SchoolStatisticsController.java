package platform.szxyzxx.web.teach.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.generalcode.model.Region;
import platform.education.generalcode.model.Stage;
import platform.education.generalcode.vo.RegionCondition;
import platform.education.generalcode.vo.StageVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
/**
 * 学校统计管理
 * @author zhoujin
 * 2015-6-5
 */
@Controller
@RequestMapping("/teach/schoolStatistics")
public class SchoolStatisticsController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(SchoolStatisticsController.class);
	
	@RequestMapping("/getSchoolStatistics")
	public ModelAndView getSchoolStatistics(
			HttpServletRequest request,
			@CurrentUser UserInfo user){
		
		ModelAndView mav = null;
		String viewPath ="";
		String arerTemp = "";
		String xiaoxueTemp = "";
		String chouzhongTemp = "";
		String gaozhongTemp = "";
		String zongsuTemp = "";
		
		try{
			mav = new ModelAndView();
			String regionCode = user.getRegionCode();
			String level = user.getLevel();
			/*
			 *根据不同的用户类型计算
			 *小学+初中   多少所 
			 *初中+高中  多少所
			 *小学+初中+高中  多少所
			 */
			Integer xiaoxue_chuzhong = 0;
			Integer chuzhong_gaozhong = 0;
			Integer xiaoxue_chuzhong_gaozhong = 0;
			
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
			//String province = request.getParameter("province");
			//String city = request.getParameter("city");
			//String district = request.getParameter("district");
			List<Region> regionList = this.jcRegionService.findRegionByCondition(regionCondition, null, null);
			for(Region region : regionList){
				SchoolCondition schoolCondition = new SchoolCondition();
				if((level=="2" || "2".equals(level)) || (level=="3" || "3".equals(level))){
					schoolCondition.setDistrict(region.getCode());
				}else{
					schoolCondition.setCity(region.getCode());
				}
				List<School> schoolList = this.schoolService.findSchoolByCondition(schoolCondition, null, null);
				
				for(School school:schoolList){
					String stageScope = school.getStageScope();
					String[] stage_scope = stageScope.split(",");
					String stage_scope1 = "";
					for(int i=0;i<stage_scope.length;i++){
						if(stage_scope[i] == "2" || "2".equals(stage_scope[i])){
							stage_scope1+=stage_scope[i]+",";
						}else if(stage_scope[i] == "3" || "3".equals(stage_scope[i])){
							stage_scope1+=stage_scope[i]+",";
						}else if(stage_scope[i] == "4" || "4".equals(stage_scope[i])){
							stage_scope1+=stage_scope[i]+",";
						}
					}
					
					String stage_scope2 = stage_scope1.substring(0, stage_scope1.length()-1);
				   if(stage_scope2.equals("2,3")){
					   xiaoxue_chuzhong++;
					}else if(stage_scope2.equals("3,4")){
						chuzhong_gaozhong++;
					}else if(stage_scope2.equals("2,3,4")){
						xiaoxue_chuzhong_gaozhong++;
					}
					
				}
				StageVo stageVo = getStageVo(schoolList);
				arerTemp +="'"+ region.getName()+"',";
				xiaoxueTemp += stageVo.getXiaoXueNum()+",";
				chouzhongTemp += stageVo.getChouZhongNum()+",";
				gaozhongTemp += stageVo.getGaoZhongNum()+",";
				zongsuTemp +=stageVo.getZongSuNum()+",";
			}
			
			String areaTemp_ = "["+deleteEndSingle(arerTemp)+"]";
			String xiaoxueTemp_= "["+deleteEndSingle(xiaoxueTemp)+"]";
			String chouzhongTemp_= "["+deleteEndSingle(chouzhongTemp)+"]";
			String gaozhongTemp_= "["+deleteEndSingle(gaozhongTemp)+"]";
			String zongsuTemp_ = "["+deleteEndSingle(zongsuTemp)+"]";
			viewPath ="/teach/schoolStatistics/getSchoolStatistics";
			mav.addObject("xiaoxueTemp_", xiaoxueTemp_);
			mav.addObject("chouzhongTemp_", chouzhongTemp_);
			mav.addObject("gaozhongTemp_", gaozhongTemp_);
			mav.addObject("areaTemp_", areaTemp_);
			mav.addObject("zongsuTemp_", zongsuTemp_);
			
			mav.addObject("districtTemp", districtTemp);
			mav.addObject("cityTemp", cityTemp);
			mav.addObject("privinceTemp", privinceTemp);
			mav.addObject("cityName", regionName.getName());
			
			mav.addObject("level", level);
			
			mav.addObject("xiaoxue_chuzhong", xiaoxue_chuzhong);
			mav.addObject("chuzhong_gaozhong", chuzhong_gaozhong);
			mav.addObject("xiaoxue_chuzhong_gaozhong", xiaoxue_chuzhong_gaozhong);
			
			
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("学校统计异常");
			e.printStackTrace();
		}
		return mav;
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
	
	public StageVo getStageVo(List<School> schoolList){
		StageVo stageVo = new StageVo();
		//小学
		int xiaoXueNum = 0;
		//初中
		int chouZhongNum = 0;
		//高中
		int gaoZhongNum = 0;
		//总数
		int countNum = 0;
		
		
		for(School school : schoolList){
			//stageVo = new StageVo();
			String stageScope = school.getStageScope();
			
			String[] stage_scope = stageScope.split(",");
			
			String stage_scope1 = "";
			
			for(int i=0;i<stage_scope.length;i++){
				Stage stage = this.jcStageService.findStageByCode(stage_scope[i]);
				if(stage_scope[i] == "2" || "2".equals(stage_scope[i])){
					xiaoXueNum++;
					stage_scope1+=stage_scope[i]+",";
					/*countNum++;*/
					stageVo.setName(stage.getName());
				}else if(stage_scope[i] == "3" || "3".equals(stage_scope[i])){
					chouZhongNum++;
					stage_scope1+=stage_scope[i]+",";
					/*countNum++;*/
					stageVo.setName(stage.getName());
				}else if(stage_scope[i] == "4" || "4".equals(stage_scope[i])){
					gaoZhongNum++;
					stage_scope1+=stage_scope[i]+",";
					/*countNum++;*/
					stageVo.setName(stage.getName());
				}
			}
			
			String stage_scope2 = stage_scope1.substring(0, stage_scope1.length()-1);
			if(stage_scope2.equals("2")){
				countNum++;
			}else if(stage_scope2.equals("3")){
				countNum++;
			}else if(stage_scope2.equals("4")){
				countNum++;
			}else if(stage_scope2.equals("2,3")){
				countNum++;
			}else if(stage_scope2.equals("2,4")){
				countNum++;
			}else if(stage_scope2.equals("3,4")){
				countNum++;
			}else if(stage_scope2.equals("2,3,4")){
				countNum++;
			}
		}
		stageVo.setZongSuNum(String.valueOf(countNum));
		stageVo.setXiaoXueNum(String.valueOf(xiaoXueNum));
		stageVo.setChouZhongNum(String.valueOf(chouZhongNum));
		stageVo.setGaoZhongNum(String.valueOf(gaoZhongNum));
		return stageVo;
	}
	
	
	
}
