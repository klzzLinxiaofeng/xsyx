package platform.education.paper.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.dao.PaperQuestionDao;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.PaperQuestionResult;
import platform.education.paper.model.TeamQuestionOptions;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.PaperQuestionService;
import platform.education.paper.util.JsonValidator;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.PaperQuestionCondition;
import platform.service.storage.service.FileService;

public class PaperQuestionServiceImpl implements PaperQuestionService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private PaperQuestionDao paperQuestionDao;

	@Resource
	private FileService fileService;


	public void setPaperQuestionDao(PaperQuestionDao paperQuestionDao) {
		this.paperQuestionDao = paperQuestionDao;
	}

	@Override
	public PaperQuestion findPaperQuestionById(Integer id) {
		try {
			return paperQuestionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public PaperQuestion add(PaperQuestion paperQuestion) {
		if(paperQuestion == null) {
			return null;
		}
		Date createDate = paperQuestion.getCreateDate();
		if(createDate == null) {
			createDate = new Date();
		}
		paperQuestion.setCreateDate(createDate);
		paperQuestion.setModifyDate(createDate);
		return paperQuestionDao.create(paperQuestion);
	}

	@Override
	public PaperQuestion modify(PaperQuestion paperQuestion) {
		if(paperQuestion == null) {
			return null;
		}
		Date modify = paperQuestion.getModifyDate();
		paperQuestion.setModifyDate(modify != null ? modify : new Date());
		return paperQuestionDao.update(paperQuestion);
	}

	@Override
	public void remove(PaperQuestion paperQuestion) {
		try {
			paperQuestionDao.delete(paperQuestion);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paperQuestion.getId(), e);
			}
		}
	}

	@Override
	public PaperQuestion findPaperQuestionByUuid(String questionUuid) {
		return paperQuestionDao.findPaperQuestionByUuid(questionUuid);
	}
	
	@Override
	public List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition, Page page, Order order) {
		return paperQuestionDao.findPaperQuestionByCondition(paperQuestionCondition, page, order);
	}

	@Override
	public List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition) {
		return paperQuestionDao.findPaperQuestionByCondition(paperQuestionCondition, null, null);
	}

	@Override
	public List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition, Page page) {
		return paperQuestionDao.findPaperQuestionByCondition(paperQuestionCondition, page, null);
	}

	@Override
	public List<PaperQuestion> findPaperQuestionByCondition(PaperQuestionCondition paperQuestionCondition, Order order) {
		return paperQuestionDao.findPaperQuestionByCondition(paperQuestionCondition, null, order);
	}

	@Override
	public Long count() {
		return this.paperQuestionDao.count(null);
	}

	@Override
	public Long count(PaperQuestionCondition paperQuestionCondition) {
		return this.paperQuestionDao.count(paperQuestionCondition);
	}

	/* (非 Javadoc) 
	* <p>Title: findPaperQuestionByPaperUuId</p> 
	* <p>Description: </p> 
	* @param paperUuid
	* @return 
	* @see platform.education.paper.service.PaperQuestionService#findPaperQuestionByPaperUuId(java.lang.String) 
	
	@Override
	public List<PaperQuestionResult> findPaperQuestionByPaperId(Integer paperId, String questionUuId) {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		List<PaperQuestionResult> paperQuestionResultList = this.paperQuestionDao.findPaperQuestionByPaperId(paperId,questionUuId);
		if(paperQuestionResultList != null && paperQuestionResultList.size()> 0){
			for(PaperQuestionResult paperQuestionResult:paperQuestionResultList){
				if(paperQuestionResult.getGroupId() != null && !"".equals(paperQuestionResult.getGroupId())){
					paperQuestionResult.setIsComplex(true);
				}
				String questionAnswer = paperQuestionResult.getQuestionAnswer() ;
				String correctAnswer = paperQuestionResult.getCorrectAnswer();
				String explanation = paperQuestionResult.getExplanation() ;
				String complexTitle = paperQuestionResult.getComplexTitle();
				String content =  paperQuestionResult.getContent();
				//转换图片
				complexTitle = MqtPaperUtil.replaceDomain(complexTitle,domain);
				content = MqtPaperUtil.replaceDomain(content,domain);
				
				questionAnswer = MqtPaperUtil.isJson(questionAnswer,domain);
				correctAnswer = MqtPaperUtil.isJson(correctAnswer,domain);
				explanation = MqtPaperUtil.replaceDomain(explanation,domain);
				
				//题目选项
				List<TeamQuestionOptions> teamQuestionOptionsList = null;
				String [] questionOptions = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
				
				//拆数组
				if(correctAnswer != null && !correctAnswer.contains("<img")){
					//JSONArray jsonArrayCorrectAnswer = JSONArray.fromObject(correctAnswer);
					String []strsCorrectAnswer = MqtPaperUtil.StringToArray(correctAnswer, domain);
					//Object [] strsCorrectAnswer = jsonArrayCorrectAnswer.toArray(); //json转为数组
					StringBuffer stringBuffer = new StringBuffer();
					if(strsCorrectAnswer != null && strsCorrectAnswer.length>0){
						for(int i=0;i<strsCorrectAnswer.length;i++){
							stringBuffer.append(strsCorrectAnswer[i]).append(",");
						}
						if(stringBuffer.toString() != null && !stringBuffer.toString().isEmpty()){
							correctAnswer = stringBuffer.toString().substring(0,stringBuffer.toString().lastIndexOf(","));
						}
					}
					String[] strs = null;
					if(questionAnswer != null && !"".equals(questionAnswer) && !questionAnswer.contains("<img")){
						if(questionAnswer != null){
							strs = MqtPaperUtil.StringToArray(questionAnswer, domain);
							//String a = objectMapper.writeValueAsString(paperAnswer);
							//wrongPaper.setPaperAnser(a);
						}
						//JSONArray jsonArray = JSONArray.fromObject(questionAnswer);
						StringBuffer sb = new StringBuffer();
						//Object [] strs = jsonArray.toArray(); //json转为数组
						teamQuestionOptionsList = new ArrayList<TeamQuestionOptions>();
						for(int i=0; i<strs.length; i++){
							TeamQuestionOptions teamQuestionOptions = new TeamQuestionOptions();
							teamQuestionOptions.setQuestionOption(questionOptions[i]);
							teamQuestionOptions.setQuestionOptionContent(strs[i].toString());
							teamQuestionOptionsList.add(teamQuestionOptions);
						}
						for(Object str:strs){
							sb.append(str).append(",");
						}
						if(sb.toString() != null && !sb.toString().isEmpty()){
							questionAnswer = sb.toString().substring(0,sb.toString().lastIndexOf(","));
							questionAnswer = questionAnswer.replaceAll("\"", "");
						}
						
						
					}else{
						questionAnswer = questionAnswer.replaceAll("&quot;","");
					}
					
				}else if(correctAnswer != null && !"".equals(correctAnswer)){
					
					//correctAnswer = paperQuestionResult.getCorrectAnswer();
					correctAnswer = correctAnswer.replaceAll("\"", "");
					correctAnswer = correctAnswer.substring(1, correctAnswer.length()-1);
					correctAnswer = correctAnswer.replaceAll("&quot;","");
					correctAnswer = StringEscapeUtils.unescapeJava(correctAnswer);
					//paperQuestionResult.setCorrectAnswer(correctAnswer);
						
				}
				
				//处理数据库保存“”的数据
				if(explanation != null && explanation.contains("\"\"")){
					explanation = explanation.substring(1, explanation.length()-1);
					explanation = explanation.replaceAll("&quot;","");
					explanation = StringEscapeUtils.unescapeJava(explanation);
				};
				
				questionAnswer = questionAnswer.replaceAll("\"", "");
				paperQuestionResult.setComplexTitle(complexTitle);
				paperQuestionResult.setExplanation(explanation);
				paperQuestionResult.setContent(content);
				paperQuestionResult.setCorrectAnswer(correctAnswer);
				paperQuestionResult.setQuestionAnswer(questionAnswer);
				paperQuestionResult.setQuestionAnswerList(teamQuestionOptionsList);
				
			}

		}
		
		return paperQuestionResultList;
	}*/
	
	
	
	/* (非 Javadoc) 
	* <p>Title: findPaperQuestionByPaperUuId</p> 
	* <p>Description: </p> 
	* @param paperUuid
	* @return 
	* @see platform.education.paper.service.PaperQuestionService#findPaperQuestionByPaperUuId(java.lang.String) 
	*/
	@Override
	public List<PaperQuestionResult> findPaperQuestionByPaperId(Integer paperId, String questionUuId) {
		ObjectMapper mapper = new ObjectMapper();
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		List<PaperQuestionResult> paperQuestionResultList = this.paperQuestionDao.findPaperQuestionByPaperId(paperId,questionUuId);
		if(paperQuestionResultList != null && paperQuestionResultList.size()> 0){
			for(PaperQuestionResult paperQuestionResult:paperQuestionResultList){
				if(paperQuestionResult.getGroupId() != null && !"".equals(paperQuestionResult.getGroupId())){
					paperQuestionResult.setIsComplex(true);
				}
				String questionAnswer = paperQuestionResult.getQuestionAnswer() ;
				String correctAnswer = paperQuestionResult.getCorrectAnswer();
				String explanation = paperQuestionResult.getExplanation() ;
				String complexTitle = paperQuestionResult.getComplexTitle();
				String content =  paperQuestionResult.getContent();
				//转换图片
				complexTitle = MqtPaperUtil.replaceDomain(complexTitle,domain);
				content = MqtPaperUtil.replaceDomain(content,domain);
				
				questionAnswer = MqtPaperUtil.isJson(questionAnswer,domain);
				correctAnswer = MqtPaperUtil.isJson(correctAnswer,domain);
				explanation = MqtPaperUtil.replaceDomain(explanation,domain);
				
				//题目选项
				List<TeamQuestionOptions> teamQuestionOptionsList = null;
				String [] questionOptions = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
				
				//拆数组
				//if(correctAnswer != null && !correctAnswer.contains("<img")){
					//JSONArray jsonArrayCorrectAnswer = JSONArray.fromObject(correctAnswer);
					String[] strsCorrectAnswer = null;
					String[] strsQuestionAnswer = null;
					try {
						
						if(JsonValidator.validate(correctAnswer)){
							strsCorrectAnswer = mapper.readValue(correctAnswer, String[].class);
							paperQuestionResult.setWebCorrectAnswer(strsCorrectAnswer);
						}
						
						
						if(JsonValidator.validate(questionAnswer)){
							strsQuestionAnswer = mapper.readValue(questionAnswer, String[].class);
							paperQuestionResult.setWebQuestionAnswer(strsQuestionAnswer);
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					/*String[] strs = null;
					if(questionAnswer != null && !"".equals(questionAnswer) && !questionAnswer.contains("<img")){
						if(questionAnswer != null){
							strs = MqtPaperUtil.StringToArray(questionAnswer, domain);
							
						}
						StringBuffer sb = new StringBuffer();
						teamQuestionOptionsList = new ArrayList<TeamQuestionOptions>();
						for(int i=0; i<strs.length; i++){
							TeamQuestionOptions teamQuestionOptions = new TeamQuestionOptions();
							teamQuestionOptions.setQuestionOption(questionOptions[i]);
							teamQuestionOptions.setQuestionOptionContent(strs[i].toString());
							teamQuestionOptionsList.add(teamQuestionOptions);
						}
						for(Object str:strs){
							sb.append(str).append(",");
						}
						if(sb.toString() != null && !sb.toString().isEmpty()){
							questionAnswer = sb.toString().substring(0,sb.toString().lastIndexOf(","));
							questionAnswer = questionAnswer.replaceAll("\"", "");
						}
						
						
					}else{
						questionAnswer = questionAnswer.replaceAll("&quot;","");
					}*/
					
				//处理数据库保存“”的数据
				if(explanation != null && explanation.contains("\"\"")){
					explanation = explanation.substring(1, explanation.length()-1);
					explanation = explanation.replaceAll("&quot;","");
					explanation = StringEscapeUtils.unescapeJava(explanation);
				};
				
				//questionAnswer = questionAnswer.replaceAll("\"", "");
				paperQuestionResult.setComplexTitle(complexTitle);
				paperQuestionResult.setExplanation(explanation);
				paperQuestionResult.setContent(content);
				//paperQuestionResult.setCorrectAnswer(correctAnswer);
			//	paperQuestionResult.setQuestionAnswer(questionAnswer);
				paperQuestionResult.setQuestionAnswerList(teamQuestionOptionsList);
				
			}

		}
		
		return paperQuestionResultList;
	}

	@Override
	public Map<String, PaperQuestion> findPaperQuestionMapByUUIDs(String[] questionUUIDs) {
		Map<String, PaperQuestion> questionMap = new HashMap<String, PaperQuestion>();
		List<PaperQuestion> questions = this.paperQuestionDao.findPaperQuestionByUUIDs(questionUUIDs);
		for (PaperQuestion question : questions) {
			questionMap.put(question.getPaperUuid(), question);
		}
		return questionMap;
	}

	@Override
	public List<PaperQuestion> findPaperQuestionByUUIDs(String[] questionUUIDs) {
		return this.paperQuestionDao.findPaperQuestionByUUIDs(questionUUIDs);
	}

}
