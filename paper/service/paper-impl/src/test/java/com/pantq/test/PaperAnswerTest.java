/**   
* @Title: PaperAnswerTest.java
* @Package com.pantq.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月24日 上午10:54:22 
* @version V1.0   
*/
package com.pantq.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.paper.service.PaperHandleService;
import platform.education.paper.util.ReadFileUtil;

/** 
* @ClassName: PaperAnswerTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pantq
* @date 2017年2月24日 上午10:54:22 
*  
*/

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:paper_app.xml" })
public class PaperAnswerTest {
	@Resource
	private PaperHandleService paperHandleService ;
	
	@Test
	public void test(){
		try {
			
			 String startDate = "2016-11-10 22:00:00";
			 String endDate = "2016-11-10 23:30:00";
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
			 Date sDate = sdf.parse(startDate);
			 Date eDate = sdf.parse(endDate);
			 startDate = sdf1.format(sDate);
			 
			 //2. 判断用户提交答案是否在允许范围内（考虑网络原因 所以延迟一分钟）
			 eDate.setTime(eDate.getTime() + 1*60*1000);
			 endDate = sdf1.format(eDate);
			 String dateScope = startDate + "-" +endDate;
			
			Date systemDate = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			String strDate = simpleDateFormat.format(systemDate);
			/**
			 * 判断学生提交的时间是否在考试允许时间范围内
			 */
			String answers = ReadFileUtil.readFile("C:\\Users\\quan\\Desktop\\提交答案格式.txt");
			
			
			//if (DateUtil.isInTime(dateScope, strDate)) {
			//	paperHandleService.uploadPaperAnswer("1","d6242702-15f8-4d80-86ba-bfe42feb69c4",12,0d, 234, answers,4,1);
			//};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
