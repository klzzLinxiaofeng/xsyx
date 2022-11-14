package com.pantq.test;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.paper.model.WrongPaper;
import platform.education.paper.model.WrongPaperJson;
import platform.education.paper.service.UserWrongService;


/**
 * 试卷测试用例
 * @author quan
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:paper_app.xml" })
public class PaperWrongTest {
	
	@Resource
	private UserWrongService userWrongService ;
	
	
	@Test
	public void paperHandle(){
		ObjectMapper mapper = new ObjectMapper();
			/*try{
				
				Page page = new Page();
				page.setCurrentPage(pageIndex == null ? 1:pageIndex);
				page.setPageSize(pageSize== null ? 10:pageSize);
				
				*//**时间格式化*//*
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
				List<WrongPaper> wrongPaperList = paperHandleService.findUserWrongList(21466, null, page, null);
				if(wrongPaperList != null && wrongPaperList.size() >0){
					
					for(WrongPaper wrongPaper:wrongPaperList){
						
						String dateStr = format.format(wrongPaper.getCreateDate());
						if(userTaskList.containsKey(dateStr)){
							userTaskList.get(dateStr).add(wrongPaper);
						}else{
							
							List<WrongPaper> list = new ArrayList<WrongPaper>();
							list.add(wrongPaper);
							userTaskList.put(dateStr, list);
						}
					}
					System.out.println(mapper.writeValueAsString(userTaskList));
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				
				
			}*/
			
			//List list = paperQuestionService.findPaperQuestionByPaperId(175, "994bd5e2-d9e8-4abf-86c1-b5f976d9eea2");
			
			//List list = paperHandleService.findUserWrongList(20974, null, null, null);
			
			
			//System.out.println(userQuestionService.findSumScore(218, 1176, "44b965dd-587a-4c0f-9c88-f46e176054be"));
			
			
			/*UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
			userQuestionCondition.setAnswer("[\"B\"]");
			userQuestionCondition.setOwnerId(166);
			userQuestionCondition.setTeamId(1176);
			userQuestionCondition.setQuestionUuid("f7df79fd-5a02-4683-a834-1033023026e2");
			//Long list =
			TeamQuestionOptions op = userQuestionService.getTeamQuestionOptionsByQuestionUuIdAndOwnerId(1176, 166, "f7df79fd-5a02-4683-a834-1033023026e2");
			//System.out.println(mapper.writeValueAsString(paperHandleService.findUserWrongList(null, null,null, null)));
			//String answer = "[{\"answer\":[\"B\"],\"uuid\":\"9a69d97d-4185-46fa-8fcf-a6caa7791ffd\",\"isCorrect\":1}]";
*/			//System.out.println(mapper.writeValueAsString(paperHandleService.findUserWrongList(20794, "22", page, null)));
			
	/*	List<Map<String, Object>> taskInfoList = new ArrayList<Map<String, Object>>();
		
		*//**时间格式化*//*
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		*//**上一个任务*//*
		String preTime = "";
		*//**保存用户任务信息列表*//*
		List<WrongPaper> userTaskList =new ArrayList<WrongPaper>();
		*//**保存任务时间和用户信息列表*//*
		Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();
		Page page = new Page();
		page.setCurrentPage(pageIndex == null ? 1:pageIndex);
		page.setPageSize(pageSize== null ? 10:pageSize);*/
		
		List<WrongPaper> wrongPaperList = userWrongService.findUserWrongListByUserId(24317, null, null, null);
		
		List<WrongPaperJson> wrongPaperJsonList = new ArrayList<WrongPaperJson>();
		for(WrongPaper wrongPaper:wrongPaperList){
			WrongPaperJson wrongPaperJson = new WrongPaperJson();
			try {
				BeanUtils.copyProperties(wrongPaperJson, wrongPaper);
				wrongPaperJsonList.add(wrongPaperJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			System.out.println(mapper.writeValueAsString(wrongPaperJsonList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		if(wrongPaperList != null && wrongPaperList.size() > 0){
			
			for (int i = 0; i < wrongPaperList.size(); i++) {
				WrongPaper wrongPaper = wrongPaperList.get(i);
				if(i==0) {
					*//**初始上一个任务时间*//*
					preTime = format.format(wrongPaper.getCreateDate());
				}
				
				*//**上一个任务和当前任务是否为同一天的任务*//*
				if(preTime.equals(format.format(wrongPaper.getCreateDate()))) {
					*//**如果为同一天把信息添加用户任务列表*//*
					userTaskList.add(wrongPaper);
					*//**如果为最后一个并且是同一天任务*//*
					if(i==wrongPaperList.size()-1) {
						*//**把时间保存到时间和任务的list中*//*
						timeAndUserTaskMap.put("time",preTime);
						timeAndUserTaskMap.put("question",userTaskList);
						*//**添加到任务信息列表中*//*
						taskInfoList.add(timeAndUserTaskMap);
					}
				} else {
					*//**如果不为同一天*//*
					timeAndUserTaskMap.put("time",preTime);
					timeAndUserTaskMap.put("question",userTaskList);
					*//**把上一天的任务列表添加到任务信息列表中*//*
					taskInfoList.add(timeAndUserTaskMap);
					
					*//**初始化时间和任务map,用于存储当天的时间和任务*//*
					timeAndUserTaskMap = new HashMap<String, Object>();
					*//**初始化用户任务列表,用于存储当天任务*//*
					userTaskList = new ArrayList<WrongPaper>();
					userTaskList.add(wrongPaper);
					
					*//**当天的时间变成上一天时间*//*
					preTime=format.format(wrongPaper.getCreateDate());
					
					*//**如果是最后一个任务*//*
					if(i==wrongPaperList.size()-1) {
						*//**最后一个任务为这天的任务总数*//*
						timeAndUserTaskMap.put("time",preTime);
						timeAndUserTaskMap.put("question",userTaskList);
						taskInfoList.add(timeAndUserTaskMap);
					} 
				}
				
			}
			//for(int i=0;)
			
			
			
			
			try {
				System.out.println(mapper.writeValueAsString(taskInfoList));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}*/
	
	
	}	

}
