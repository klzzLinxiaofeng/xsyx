/**   
* @Title: PaperAnswerTest.java
* @Package com.pantq.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月24日 上午10:54:22 
* @version V1.0   
*/
package com.pantq.test;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.paper.model.TeamQuestionOptions;
import platform.education.paper.service.PaperHandleService;
import platform.education.paper.service.UserQuestionService;

/** 
* @ClassName: PaperAnswerTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pantq
* @date 2017年2月24日 上午10:54:22 
*  
*/

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:paper_app.xml" })
public class PaperUsedCount {
//	@Resource
//	private PaperHandleService paperHandleService ;
	
	@Resource
	private UserQuestionService userQuestionService ;
	
	@Test
	public void test(){
		
		try {
			
			List<TeamQuestionOptions> list = userQuestionService.getTeamQuestionOptionsByQuestionUuIdAndOwnerId(2366, 735, "d31bae5a-7f13-4577-93a9-1a2871034dbc", null);
			System.out.println("11");
			
			//paperHandleService.paperSplit(123456, "D:\\1.xep", "D:\\test\\", "12", "12", 123l, "123", "123", "123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(paperHandleService.findUserPaperByPaperId(41, 4,1,1));
	}
}
