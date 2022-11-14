package com.pantq.test;


import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.paper.model.AnswerSituationResult;
import platform.education.paper.model.Question;
import platform.education.paper.service.PaperHandleService;
import platform.education.paper.service.PaperQuestionService;
import platform.education.paper.service.QuestionService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.vo.UserPaperCondition;


/**
 * 试卷测试用例
 * @author quan
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = {"classpath:paper_app.xml"})
public class PaperTestCase {

	@Resource
	private PaperHandleService paperHandleService ;
	
	@Resource
	private UserPaperService userPaperService ;
	
	@Resource
	private QuestionService questionService ;
	
	@Resource
	private UserQuestionService userQuestionService ;
	
	@Resource
	private PaperQuestionService paperQuestionService ;
	
	@Test
	public void paperHandle(){
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			List list = paperQuestionService.findPaperQuestionByPaperId(1, "1fe7e4bd-f31c-4a42-829a-320ce7c95135");
			System.out.println(mapper.writeValueAsString(list));
			//List<AnswerSituationResult> list = userQuestionService.findUserQuestionByUserIdAndPaperUuId(null, null, null);
			//System.out.println(mapper.writeValueAsString(list));
		//	Question q =questionService.findQuestionById(2570); 
		//	System.out.println(q.getAnswer());
			//System.out.println(mapper.writeValueAsString(questionService.findQuestionById(2570)));
			/*UserPaperCondition userPaperCondition = new UserPaperCondition();
			userPaperCondition.setType(4);
			userPaperCondition.setOwnerId(148);
			userPaperCondition.setPaperUuid("60398719-972a-4f66-b8d3-60cc039708c8");
			userPaperCondition.setUserId(20974);
			System.out.println(mapper.writeValueAsString(userPaperService.findUserPaperByCondition(userPaperCondition)));*/
		//	System.out.println(paperHandleService.paperSplit(123,"D://2017//20170321//高三英语试卷及答案.xep", "D://2017//20170321//", "111", "32323", 100L,"","",""));
			
			//System.out.println(mapper.writeValueAsString(paperHandleService.findUserPaperByPaperId(1)));
			
			//System.out.println("1111");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
