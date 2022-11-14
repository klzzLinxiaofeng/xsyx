package com.pantq.test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;

import com.pantq.test.model.DemoPaper;
import com.pantq.test.model.DemoQuestion;

import platform.education.paper.constants.QuestionType;
import platform.education.paper.constants.SourceType;
import platform.education.paper.model.Paper;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.Question;
import platform.education.paper.util.ReadFileUtil;

public class SpitQuestion {

	public static void main(String[] args) {
		try{
			
			ObjectMapper mapper = new ObjectMapper();
			//1. 获取试卷内容
			String filePath = "D:/document.json";
			String content = ReadFileUtil.readString(filePath);
			DemoPaper paper = mapper.readValue(content, DemoPaper.class);
			
			Paper paPaper = new Paper();
			paPaper.setPaperUuid(paper.getPaperId());
			paPaper.setUsedCount(0);
			paPaper.setTitle(paper.getPaperTitle());
			paPaper.setPaperType(paper.getPaperType());
			paPaper.setScore(paper.getScore());
			//paPaper.setUserId(paper.getu);
			paPaper.setSubjectCode(paper.getSubjectCode());
			//paPaper.setVersionCode(paper.getVolumeCode()); // 新加字段，PC那边还没有改
			paPaper.setGradeCode(paper.getGradeCode());
			paPaper.setVolumeCode(paper.getVolumeCode());
		//	paPaper.setCategoryCode(categoryCode); // 新加字段，PC那边还没有改
		//	paPaper.setDifficulity(difficulty);
			paPaper.setKnowledge(paper.getKnowledge());
			//paPaper.setFileUuid(fileUuid);
			//paPaper.setFileSize(fileSize);
			//paPaper.setFileMd5(fileMd5);
			paPaper.setCreateDate(new Date());
			
			//入库
			
			List<DemoQuestion> demoQuestionList = paper.getQuestions();
			if(demoQuestionList != null && demoQuestionList.size() > 0){
				for(DemoQuestion demoQuestion : demoQuestionList){
					String type = demoQuestion.getType();
					if(type.equals(QuestionType.MEMO)){ //当遇到类型为：MEMO 跳过此次循环
						continue;
					}
					
					appendQuesiton(demoQuestion);
					
				}
			}
			
			
			//System.out.println(paper.getScore());
			
			//System.out.println(paper.getQuestions().size());
			
			//System.out.println(paper.getPaperId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static Question appendQuesiton(DemoQuestion demoQuestion){
		Question paQuestion = null;
		if (demoQuestion.getType().equals(QuestionType.COMPLEX)) {//符合题
			List<DemoQuestion> complexList = demoQuestion.getQuestions();
			if(complexList != null && complexList.size() > 0){
				String groupId = UUID.randomUUID().toString();
				for(DemoQuestion qd : complexList){
					
					paQuestion = new Question();
					paQuestion.setUsedCount(0);
					paQuestion.setKnowledgeId(qd.getKnowledgeId());
					paQuestion.setKnowledge(qd.getKnowledge());
					paQuestion.setQuestionType(qd.getType());
					paQuestion.setQuestionUuid(qd.getQuestionId());
					paQuestion.setSourceType(SourceType.LEARNINGPLANQUESTIONS);
					paQuestion.setContent(qd.getContent());
					paQuestion.setExtraContent(qd.getExtraContent());
					paQuestion.setAnswer(qd.getAnswer().toString());
					paQuestion.setCorrectAnswer(qd.getCorrectAnswer().toString());
					paQuestion.setExplanation(qd.getExplanation());
					//paQuestion.setUserId(demoQuestion.getu);
					paQuestion.setCognition(qd.getCognition());
					paQuestion.setDifficulity(qd.getDifficulty());
					paQuestion.setSubjectCode(qd.getSubjectCode());
					//paQuestion.setVersionCode(demoQuestion.getVolumeCode());
					//paQuestion.setCategoryCode(categoryCode);
					paQuestion.setGradeCode(qd.getGradeCode());
					paQuestion.setVolumeCode(qd.getVolumeCode());
					paQuestion.setScore(qd.getScore());
					paQuestion.setPos(qd.getPos());
					
					paQuestion.setGroupId(groupId);
					paQuestion.setGroupTitle(qd.getGroupTitle());
				}
			}
			
		}else{ //菲复合套处理
			System.out.println(demoQuestion.getAnswer());
			paQuestion = new Question();
			paQuestion.setUsedCount(0);
			paQuestion.setKnowledgeId(demoQuestion.getKnowledgeId());
			paQuestion.setKnowledge(demoQuestion.getKnowledge());
			paQuestion.setQuestionType(demoQuestion.getType());
			paQuestion.setQuestionUuid(demoQuestion.getQuestionId());
			paQuestion.setSourceType(SourceType.LEARNINGPLANQUESTIONS);
			paQuestion.setContent(demoQuestion.getContent());
			paQuestion.setExtraContent(demoQuestion.getExtraContent());
			paQuestion.setAnswer(demoQuestion.getAnswer().toString());
			paQuestion.setCorrectAnswer(demoQuestion.getCorrectAnswer().toString());
			paQuestion.setExplanation(demoQuestion.getExplanation());
			//paQuestion.setUserId(demoQuestion.getu);
			paQuestion.setCognition(demoQuestion.getCognition());
			paQuestion.setDifficulity(demoQuestion.getDifficulty());
			paQuestion.setSubjectCode(demoQuestion.getSubjectCode());
			//paQuestion.setVersionCode(demoQuestion.getVolumeCode());
			//paQuestion.setCategoryCode(categoryCode);
			paQuestion.setGradeCode(demoQuestion.getGradeCode());
			paQuestion.setVolumeCode(demoQuestion.getVolumeCode());
			paQuestion.setScore(demoQuestion.getScore());
			paQuestion.setPos(demoQuestion.getPos());
			
		}
		
		
		// 题目写入试卷试题表 pa_paper_question 表
		PaperQuestion paPaperQuestion = new PaperQuestion();
		//paPaperQuestion.setQuestionUuid(questionId);
		//paPaperQuestion.setPaperUuid();
		//paPaperQuestion.setScore(sub_score);
		paPaperQuestion.setCreateDate(new Date());
		//paPaperQuestion.setPos(pos);
		//paperQuestionService.add(paPaperQuestion);
		
		paQuestion.setCreateDate(new Date());
		
		return paQuestion;
	}
	
}
