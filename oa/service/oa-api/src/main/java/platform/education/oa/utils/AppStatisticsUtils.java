package platform.education.oa.utils;

import platform.education.oa.model.AppStatistics;
import platform.education.oa.model.AppSumStatistics;
import platform.education.oa.service.AppStatisticsService;
import platform.education.oa.service.AppSumStatisticsService;

public class AppStatisticsUtils {
	/**
	 * 保存总的操作统计
	 * @param appSumStatisticsService 
	 * @param type 功能的类型代码：会议：meeting 日程：schedule 通讯录：addressbook公告：notice
	 */
	public static void sumStatisticsCreator(AppSumStatisticsService appSumStatisticsService,String type ){
		 AppSumStatistics sumStatistics=appSumStatisticsService.findAppSumStatisticsToType(type);
         if(sumStatistics==null){
      	   sumStatistics=new AppSumStatistics();
      	   sumStatistics.setStatistics(1);
      	   sumStatistics.setType(type);
      	   appSumStatisticsService.add(sumStatistics);
      	  
         }else{
      	   sumStatistics.setStatistics(sumStatistics.getStatistics()+1);
      	    appSumStatisticsService.modify(sumStatistics);
         }
	}
	/**
	 * 保存每一用户每一次的操作记录
	 * @param appStatisticsService
	 * @param ownerId  单位或学校ID
	 * @param ownerType 单位或学校类型
	 * @param posterId 发布者ID
	 * @param type  功能的类型代码：  会议：meeting 日程：schedule 通讯录：addressbook公告：notice
	 */
	
	public static void statisticsCreator(AppStatisticsService appStatisticsService,String ownerId,String ownerType,String posterId,String type ){
		AppStatistics statistics=new AppStatistics();
        statistics.setOwnerId(Integer.valueOf(ownerId));
        statistics.setOwnerType(ownerType);
        statistics.setUserId(Integer.valueOf(posterId));
        statistics.setType(type);
         appStatisticsService.add(statistics);
	}

}
