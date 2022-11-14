package platform.education.paper.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import platform.education.paper.model.PaPaperTree;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.AssemblyGroupJson;
import platform.education.paper.vo.AssemblyPaperJson;
import platform.education.paper.vo.AssemblyQuestionJson;
import platform.education.paper.vo.PaFavoritesCondition;
import platform.education.paper.vo.PaPaperTreeCondition;
import platform.education.paper.vo.PaQuestionCatalogCondition;
import platform.education.paper.vo.PaQuestionCondition;
import platform.education.paper.vo.PaQuestionKnoledgeCondition;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaQuetionKnowledgeNodeNameVo;
import platform.education.paper.vo.PaperQuestionTree;
import platform.education.paper.vo.TeamQuestionOptions;
import platform.education.resource.service.UserActionService;
import platform.education.paper.service.PaFavoritesService;
import platform.education.paper.service.PaQuestionCatalogService;
import platform.education.paper.service.PaQuestionKnoledgeService;
import platform.education.paper.service.PaQuestionService;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.paper.constants.PaperContans;
import platform.education.paper.constants.QuestionContans;
import platform.education.paper.dao.PaPaperTreeDao;
import platform.education.paper.dao.PaQuestionDao;
import platform.service.storage.service.FileService;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;

public class PaQuestionServiceImpl implements PaQuestionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaQuestionDao paQuestionDao;

	private PaPaperTreeDao paPaperTreeDao;
	
	private PaQuestionCatalogService paQuestionCatalogService;
	
	private PaQuestionKnoledgeService paQuestionKnoledgeService;
	
	private FileService fileService;
	
	@Autowired
	@Qualifier("userActionService")
	private UserActionService userActionService;

	@Autowired
	@Qualifier("knowledgeNodeService")
	private KnowledgeNodeService knowledgeNodeService;
	
	@Autowired
	@Qualifier("paFavoritesService")
	private PaFavoritesService paFavoritesService;
	
//	@Autowired
//	@Qualifier("stringRedisTemplate")
//	private StringRedisTemplate stringRedisTemplate;
	
	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public PaPaperTreeDao getPaPaperTreeDao() {
		return paPaperTreeDao;
	}

	public void setPaPaperTreeDao(PaPaperTreeDao paPaperTreeDao) {
		this.paPaperTreeDao = paPaperTreeDao;
	}

	public PaQuestionDao getPaQuestionDao() {
		return paQuestionDao;
	}

	public void setPaQuestionDao(PaQuestionDao paQuestionDao) {
		this.paQuestionDao = paQuestionDao;
	}
	
	@Override
	public PaQuestion findPaQuestionById(Integer id) {
		try {
			return paQuestionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {}", id);
		}
		return null;
	}
	
	@Override
	public PaQuestion add(PaQuestion paQuestion) {
		if(paQuestion == null) {
    		return null;
    	}
    	Date createDate = paQuestion.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paQuestion.setCreateDate(createDate);
    	paQuestion.setModifyDate(createDate);
		return paQuestionDao.create(paQuestion);
	}

	@Override
	public PaQuestion modify(PaQuestion paQuestion) {
		if(paQuestion == null) {
    		return null;
    	}
    	Date modify = paQuestion.getModifyDate();
    	paQuestion.setModifyDate(modify != null ? modify : new Date());
		return paQuestionDao.update(paQuestion);
	}
	
	@Override
	public PaQuestion modifyNotWithModifyDate(PaQuestion paQuestion) {
		if(paQuestion == null) {
			return null;
		}
		return paQuestionDao.update(paQuestion);
	}
	
	@Override
	public void remove(PaQuestion paQuestion) {
		try {
			paQuestionDao.delete(paQuestion);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paQuestion.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page, Order order) {
		return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, page, order);
	}
	
	@Override
	public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition) {
		return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, null, null);
	}
	
	@Override
	public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Page page) {
		return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, page, null);
	}
	
	@Override
	public List<PaQuestion> findPaQuestionByCondition(PaQuestionCondition paQuestionCondition, Order order) {
		return paQuestionDao.findPaQuestionByCondition(paQuestionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paQuestionDao.count(null);
	}

	@Override
	public Long count(PaQuestionCondition paQuestionCondition) {
		return this.paQuestionDao.count(paQuestionCondition);
	}

	@Override
	public List<PaQuestionVo> findPaperQuestionByPaperIds(Integer[] paperIds) {
		return paQuestionDao.findPaperQuestionByPaperIds(paperIds);
	}

	@Override
	public List<PaQuestion> findPaQuestionsByTypeCode(
			PaQuestionCondition condition, Integer type, String code,
			Page page, Order order) {
		List<PaQuestion> qlist= new ArrayList<PaQuestion>();
		if(type.intValue()==QuestionContans.CATALLOGTYPE){
			qlist=paQuestionDao.findQuestionsByCatalogCode(condition, code, page, order);
		}else{
			qlist=paQuestionDao.findQuestionsByKnoWledgeCode(condition, code, page, order);
		}
		return qlist;
	}

	@Override
	public PaQuestionVo findPaQuestionsVoById(Integer id) {
		PaQuestion pq=new PaQuestion();
		pq=paQuestionDao.findById(id);
		PaQuestionVo vo=new PaQuestionVo();
		 try {
			BeanUtils.copyProperties(vo,pq);
			Integer rate=0;
			if(vo.getTotalTimeCount()!=null&&vo.getRightAnswerCount()!=null&&vo.getRightAnswerCount()!=0&&vo.getTotalTimeCount()!=0){
				rate=vo.getRightAnswerCount()/vo.getTotalTimeCount()*100;
			}
			vo.setQuestionType(questionTypeString(vo.getQuestionType()));
		} catch (Exception e) {
			return new PaQuestionVo();
		} 
		return vo;
	}
	
	 private String questionTypeString(String questionType) {
         if (questionType == null) {
              return "无";
         }
         switch (questionType) {
         case "radio":
              questionType = "单选题";
              break;
         case "checkbox":
              questionType = "多选题";
              break;
         case "multichoise":
              questionType = "不定项选择题";
              break;
         case "trueOrFalse":
              questionType = "判断题";
              break;
         case "blank":
              questionType = "填空题";
              break;
         case "word":
              questionType = "简答题";
              break;
         case "cloze":
              questionType = "完形填空";
              break;
         case "complex":
              questionType = "复合题";
              break;
         default:
              questionType = "未知题型";
              break;
         }
         return questionType;
   }
   
   private String difficulityString(float difficulity) {
         String difficulityString = "";
         if (0 <= difficulity && difficulity <= 0.2)
              difficulityString = "困难";
         else if (0.2 < difficulity && difficulity <= 0.5)
              difficulityString = "一般";
         else if (0.5 < difficulity && difficulity <= 1.0)
              difficulityString = "简单";
         return difficulityString;
   }


	@Override
	public List<PaQuestionVo> findPaQuestionVoByPaperId(Integer paper,Integer isComplex,Order order) {
		return paQuestionDao.findPaQuestionVoByPaperId(paper,isComplex,order);
	}

	@Override
	public void removeByPaperId(Integer paperId) {
		PaPaperTreeCondition paPaperTreeCondition = new PaPaperTreeCondition();
		paPaperTreeCondition.setPaperId(paperId);
		List<PaPaperTree> paPaperTreeList = paPaperTreeDao.findPaPaperTreeByCondition(paPaperTreeCondition, null, null);
		
		Integer[] questionIds = new Integer[paPaperTreeList.size()];
		
		int index = 0;
		
		for (PaPaperTree paPaperTree : paPaperTreeList) {
			if(paPaperTree.getQuestionId()!=null) {
				questionIds[index] = paPaperTree.getQuestionId();
				index++;
			}
		}
		
		paQuestionCatalogService.removeByQuestionIds(questionIds);;
		
		paQuestionKnoledgeService.removeByQuestionIds(questionIds);
		
		if(questionIds.length>0) {
			paQuestionDao.deleteByIds(questionIds);
		}
		
	}

	public PaQuestionCatalogService getPaQuestionCatalogService() {
		return paQuestionCatalogService;
	}

	public void setPaQuestionCatalogService(PaQuestionCatalogService paQuestionCatalogService) {
		this.paQuestionCatalogService = paQuestionCatalogService;
	}

	public PaQuestionKnoledgeService getPaQuestionKnoledgeService() {
		return paQuestionKnoledgeService;
	}

	public void setPaQuestionKnoledgeService(PaQuestionKnoledgeService paQuestionKnoledgeService) {
		this.paQuestionKnoledgeService = paQuestionKnoledgeService;
	}

	@Override
	public List<PaQuestionVo> findPaQuestionByUUIDs(String[] questionUuids,Integer paperId) {
		return paQuestionDao.findPaQuestionByUUIDs(questionUuids,paperId);
	}

	@Override
	public List<String> getJsonDataFromPaQuestionByUUIDs(String[] questionUuids, Integer paperId) {
		List<PaQuestionVo> paQuestionDataList = findPaQuestionByUUIDss(questionUuids,paperId);
		ObjectMapper mapper = new ObjectMapper();
		List<String> paQuestionJsonList = new ArrayList<>();
		String data = null;
		for (PaQuestionVo paQuestionData : paQuestionDataList) {
			try {
				data = mapper.writeValueAsString(paQuestionData);
				paQuestionJsonList.add(data);
			} catch (IOException e) {
				return new ArrayList<>();
			}
		}
		return paQuestionJsonList;
	}
	
	public List<PaQuestionVo> findPaQuestionByUUIDss(String[] questionUuids,Integer paperId) {
		return paQuestionDao.findPaQuestionByUUIDss(questionUuids,paperId);
	}

	@Override
	public String getJsonDataFromPaQuestionById(Integer questionId) {
		PaQuestionVo pqv = findPaQuestionsVoById(questionId);
		ObjectMapper mapper = new ObjectMapper();
		String pqJson = null;
		try {
			pqJson = mapper.writeValueAsString(pqv);
		} catch (IOException e) {
			return null;
		}
		return pqJson;
	}

	@Override
	public List<PaQuestionVo> findPaQuestionListbyPaPaperId(Integer paperId) {
		return paQuestionDao.findPaQuestionListbyPaPaperId(paperId);
	}

	@Override
	public PaQuestionVo findPaQuestionById(Integer paperId, Integer questionId) {
		return paQuestionDao.findPaQuestionById(paperId,questionId);
	}

	@Override
	public List<PaQuestionVo> addQuestionToPaQuestionList(List<PaQuestionVo> paQuestionList, Integer paperId,
			Integer questionId) {
		if(paQuestionList == null ){
			paQuestionList = new ArrayList<PaQuestionVo>();
		}
		PaQuestionVo pQVo = findPaQuestionById(paperId,questionId);
		paQuestionList.add(pQVo);
		return paQuestionList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperQuestionTree> findPaperQuestionTreeByPaperId(
			Integer paperId,Order order,Integer function) {
		List<PaperQuestionTree> list=new ArrayList<PaperQuestionTree>();
		PaPaperTreeCondition pptCondition=new PaPaperTreeCondition();
		pptCondition.setPaperId(paperId);
		pptCondition.setNodeType(1);
		List<PaPaperTree> pptlist=new ArrayList<PaPaperTree>();
		Order order2=new Order();
		order2.setProperty("node_order");
		order2.setAscending(true);
		pptlist=paPaperTreeDao.findPaPaperTreeByCondition(pptCondition, null, order2);
		if(pptlist==null||pptlist.size()==0){
			return  list;
		}
		List<PaQuestionVo> pqvoList=new ArrayList<PaQuestionVo>();
		pqvoList=findPaQuestionVoByPaperId(paperId,1,order);
		if(pqvoList==null||pqvoList.size()==0){
			return  list;
		}
		Map<Integer,List<PaQuestionVo>>map=new HashMap<Integer, List<PaQuestionVo>>();
		for(PaQuestionVo ppt :pqvoList){
			if(ppt.getOrderType().intValue()==3){
				List<PaQuestionVo> p3=new ArrayList<PaQuestionVo>();
				 p3=map.get(ppt.getParentId());
				if(p3==null){
					p3=new ArrayList<PaQuestionVo>();
				}
				p3.add(ppt);
				map.put(ppt.getParentId(), p3);
			}
		}
		for(PaPaperTree ppt :pptlist){
			PaperQuestionTree pqt=new PaperQuestionTree();
			pqt.setName(ppt.getTitle());
			pqt.setType(pqt.getType());
			pqt.setParentId(ppt.getId());
			pqt.setTreeId(ppt.getId());
			PaQuestionVo vo=new PaQuestionVo();
			vo.setMemo(ppt.getMemo());
			vo.setContent(ppt.getTitle());
			vo.setPos(ppt.getPos());
			vo.setScore(ppt.getScore());
			pqt.setObj(vo);
			list.add(pqt);
		}
		for(PaQuestionVo ppt :pqvoList){
			for(PaperQuestionTree pqt:list){
				if(ppt.getTreeParentId()==pqt.getTreeId().intValue()){
					List<PaperQuestionTree> pqtlist=pqt.getChildrens(); 
					if(pqtlist==null){
						pqtlist=new ArrayList<PaperQuestionTree>();
					}
					PaperQuestionTree pqt2=new PaperQuestionTree();
					pqt2.setParentId(ppt.getParentId());
					pqt2.setTreeId(ppt.getId());
					if(function==0){
						ppt=toQuestion(ppt);
					}
					pqt2.setObj(ppt);
					List<PaQuestionVo> volist3=map.get(ppt.getId());
					List<PaperQuestionTree> treelist=new ArrayList<PaperQuestionTree>();
					List<String> anwerList=new ArrayList<String>();
					List<String[]> anwerLists=new ArrayList<String[]>();
					List<String> exp=new ArrayList<String>();
					Integer count=1;
					if(volist3==null){
						pqt2.setChildrens(treelist);
					}else{
						for(PaQuestionVo vo:volist3){
							String ra="";
							if(function==0){
								vo=toQuestion(vo);
							}
							PaperQuestionTree pt3=new PaperQuestionTree();
							pt3.setParentId(vo.getTreeParentId());
							pt3.setObj(vo);
							pt3.setNodeOrder(vo.getNodeOrder());
							if(function==0){
								if(vo.getQuestionType().equals("blank")){
									String[] str=vo.getCorrectAnswers();
									for(int i=0;i<str.length;i++){
										if(i==str.length-1){
											ra+=str[i];
										}else{
											ra+=str[i]+"<br>";
										}
									}
								}else{
									ra=vo.getCorrectAnswer();
								}
	                            anwerList.add(ra);
	                            anwerLists.add(vo.getCorrectAnswers());
	                            exp.add(vo.getExplanation());
							}
							treelist.add(pt3);
							sortNode(treelist);
						}
						count=volist3.size();
					}
					pqt2.setAnwerList(anwerList);
					pqt2.setChildrens(treelist);
					pqt2.setExp(exp);
					pqt2.setCount(count);
					pqtlist.add(pqt2);
					pqt.setChildrens(pqtlist);
				}
			}
		}
		
		return list;
	}
	private PaQuestionVo toQuestion(PaQuestionVo paperQuestionResult){
				String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
				String questionAnswer = paperQuestionResult.getAnswer();
				String correctAnswer = paperQuestionResult.getCorrectAnswer();
				String explanation = paperQuestionResult.getExplanation() ;
				String content =  paperQuestionResult.getContent();
				/**图片替换*/
				paperQuestionResult.setAnswer(handlerContent(questionAnswer));
				/**图片替换*/
				paperQuestionResult.setCorrectAnswer(handlerContent(correctAnswer));
				String[] correctAnswers = MqtPaperUtil.StringToArray(correctAnswer, domain);
				String[] answers = MqtPaperUtil.StringToArray(questionAnswer, domain);
				paperQuestionResult.setAnswers(answers);
				paperQuestionResult.setCorrectAnswers(correctAnswers);
				
				//题目选项
				List<TeamQuestionOptions> teamQuestionOptionsList = null;
				String [] questionOptions = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
						teamQuestionOptionsList = new ArrayList<TeamQuestionOptions>();
						for(int i=0; i<answers.length; i++){
							TeamQuestionOptions teamQuestionOptions = new TeamQuestionOptions();
							teamQuestionOptions.setQuestionOption(questionOptions[i]);
							teamQuestionOptions.setQuestionOptionContent(answers[i].toString());
							teamQuestionOptionsList.add(teamQuestionOptions);
						}
					
				paperQuestionResult.setExplanation(handlerContent(explanation));
				paperQuestionResult.setContent(MqtPaperUtil.replaceDomain(content, domain));
				paperQuestionResult.setQuestionAnswerList(teamQuestionOptionsList);
		
		return paperQuestionResult;
	}

	@Override
	public List<PaQuestionVo> findPaperQuestionByUUIDs(Object[] questionUUIDs) {
		if(questionUUIDs.length==0) {
			return new ArrayList<PaQuestionVo>();
		}
		return paQuestionDao.findPaperQuestionByUUIDs(questionUUIDs);
	}

	@Override
	public List<PaQuestionVo> findPaQuestionVoByPaperId(Integer paperId) {
		return paQuestionDao.findPaQuestionInfoByPaperId(paperId);
	}

	@Override
	public List<PaQuestion> findPaQuestionByIds(Integer[] ids) {
      if(ids==null||ids.length==0){
    	  return new ArrayList<PaQuestion>();
      }
		return paQuestionDao.findPaQuestionByIds(ids);
	}
	
	// 试题详情
	@Override
	public PaQuestionVo findQuestionDetail(Integer id) {
		PaQuestionVo question=new PaQuestionVo();
		PaQuestionCondition paQuestionCondition=new PaQuestionCondition();
		paQuestionCondition.setId(id);
		List<PaQuestionVo> list=paQuestionDao.findPaQuestionVoByCondition(paQuestionCondition);
		if (list != null && !list.isEmpty()) {
			question = list.get(0);
			// 如果是复合题或者完型填空 需要查它的子题目
			if ("cloze".equals(question.getQuestionType()) || "complex".equals(question.getQuestionType())) {
				paQuestionCondition.setId(null);
				paQuestionCondition.setParentId(id);
				List<PaQuestionVo> childrenQuestions = paQuestionDao.findPaQuestionVoByCondition(paQuestionCondition);
				if(childrenQuestions!=null&&!childrenQuestions.isEmpty()) {
					for(PaQuestionVo pq:childrenQuestions) {
						handleQuestion(pq);
					}
				}
				question.setChildrenQuestionVo(childrenQuestions);
			}
			// 正答率
			Integer rate = 0;
			if (question.getAnswerCount() != null && question.getAnswerCount() != 0) {
				try {
					DecimalFormat df = new DecimalFormat("0");
					rate = Integer.valueOf(df.format((float) question.getRightAnswerCount() / (float) question.getAnswerCount() * 100));
				} catch (Exception e) {
					log.debug("rightRate " + e.getMessage());
				}
			}
			question.setRightRate(rate);
			// 难度 题型转成中文
			question.setQuestionTypeString(questionTypeString(question.getQuestionType()));
			question.setDifficulityString(difficulityString(question.getDifficulity()));
			// 查出题目的知识点list
			question.setKnowledge(knowledgeNodeService.findKnowledgeNodeNameByQuestionID(id));
			handleQuestion(question);

			log.debug("*****************question detail service********************888");
			ObjectMapper mapper = new ObjectMapper();
			try {
				log.debug("*****question:" + mapper.writeValueAsString(question));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return question;
	}

	// 知识点相关试题推荐
	@Override
	public List<PaQuestionVo> relatedQuestion(Integer questionId, int number) {
		List<PaQuestionVo> relatedQuestionList = new ArrayList<PaQuestionVo>();
		List<PaQuestionVo> questionList = paQuestionDao.findRelatedQuestionById(questionId);
		if (questionList != null && !questionList.isEmpty()) {
			//如果不够条数，随便拿几条凑够
			if (number > questionList.size()) {
				questionList = paQuestionDao.findByRandom(questionId);
			}
		} else {
			//如果没有，随便拿几条
			questionList = paQuestionDao.findByRandom(questionId);
		}
		if (questionList != null && !questionList.isEmpty()) {
			Random rand = new Random();
			for (int i = 0; i < number; i++) {
				// 随机选出一个
				PaQuestionVo paQuestionVo = questionList.remove(rand.nextInt(questionList.size()));
				// 题型转为中文
				paQuestionVo.setQuestionTypeString(questionTypeString(paQuestionVo.getQuestionType()));
				String content = paQuestionVo.getContent();
				content = content.replaceAll("<grid class=.*?</grid>", "");
				content = content.replaceAll("<img.*?</img>", "");
				content = content.replaceAll("<input id=.*?>", "__");
				paQuestionVo.setContent(content);
				relatedQuestionList.add(paQuestionVo);
			}
		}
		return relatedQuestionList;
	}

	// 做过这道题的人还做过
	@Override
	public List<PaQuestionVo> historyQuestion(String uuid, int number) {
		List<PaQuestionVo> historyList = new ArrayList<PaQuestionVo>();
		List<PaQuestionVo> questionList = paQuestionDao.findHistoryQuestionById(uuid);
		if (questionList != null && !questionList.isEmpty()) {
			Random rand = new Random();
			// 最多选取集合所有元素
			number = number > questionList.size() ? questionList.size() : number;
			for (int i = 0; i < number; i++) {
				// 随机选出一个
				PaQuestionVo paQuestionVo = questionList.remove(rand.nextInt(questionList.size()));
				// 题型转为中文
				paQuestionVo.setQuestionTypeString(questionTypeString(paQuestionVo.getQuestionType()));
				String content=paQuestionVo.getContent();
				content=content.replaceAll("<grid class=.*?</grid>", "图片");
				content=content.replaceAll("<img.*?</img>", "图片");
				content=content.replaceAll("<input id=.*?>", "__");
				paQuestionVo.setContent(content);
				historyList.add(paQuestionVo);
			}
		}
		return historyList;
	}

	@Override
	public List<PaQuestionVo> findPaQuestionList(Integer userId, String schooUUID, String libType, String difficulity,
			String questionType, String stageCode, String subjectCode, String type, String code, String textbookId,
			Page page, Order order) {
		List<PaQuestionVo> questionList=new ArrayList<PaQuestionVo>();
		
		// 默认按更新时间降序
		if (order.getProperty() == null) {
			order.setProperty("modify_date");
		}

		PaQuestionCondition questionCondition = new PaQuestionCondition();
		questionCondition.setUserId(userId);
		// set难度
		if (difficulity != null && !"0".equals(difficulity)) {
			switch (difficulity) {
			case "simple":
				questionCondition.setDifficulityLowerBound((float) 0.6);
				questionCondition.setDifficulityUpperBound((float) 1);
				break;
			case "general":
				questionCondition.setDifficulityLowerBound((float) 0.3);
				questionCondition.setDifficulityUpperBound((float) 0.5);
				break;
			case "difficult":
				questionCondition.setDifficulityLowerBound((float) 0.1);
				questionCondition.setDifficulityUpperBound((float) 0.2);
				break;
			}
		}
		// set题型
		if (questionType != null && !"0".equals(questionType)) {
			questionCondition.setQuestionType(questionType);
		}
		// set学段科目
		PaQuestionCatalogCondition questionCatalogCondition = new PaQuestionCatalogCondition();
		if (stageCode != null && !"0".equals(stageCode)) {
			questionCatalogCondition.setStageCode(stageCode);
		}
		if (subjectCode != null && !"0".equals(subjectCode)) {
			questionCatalogCondition.setSubjectCode(subjectCode);
		}

		// 收藏夹
		if (PaperContans.FAV.equals(libType)) {
			PaFavoritesCondition pfCondition = new PaFavoritesCondition();
			pfCondition.setPosterId(userId);
			pfCondition.setObjectType(PaperContans.QUESTION);
			questionList = paQuestionDao.findPaQuestionOnFavorites(questionCondition, questionCatalogCondition,
					pfCondition,page,order);
		} else {
			Integer ownerMode = 1;
			if (PaperContans.PUBLIC.equals(libType)) {
				ownerMode = 0;
			} else if (PaperContans.PERSIONAL.equals(libType)) {
				ownerMode = 2;
			}
			questionCondition.setOwnerMode(ownerMode);
			questionCondition.setOwnerUid(schooUUID);

			// 学段科目
			if (type == null) {
				questionList = paQuestionDao.findPaQuestionByStageSubject(questionCatalogCondition,
						questionCondition,page,order);
			// code
			} else {
				PaQuestionKnoledgeCondition questionKnoledgeCondition = new PaQuestionKnoledgeCondition();
				questionCatalogCondition.setType(type);
				questionKnoledgeCondition.setType(type);
				if (code != null && !code.equals("0")) {
					questionCatalogCondition.setCode(code);
					questionKnoledgeCondition.setCode(code);
				}
				if (textbookId != null && !textbookId.equals("0")) {
					questionCatalogCondition.setTextbookId(Integer.valueOf(textbookId));
					questionKnoledgeCondition.setTextbookId(Integer.valueOf(textbookId));
				}
				questionList = paQuestionDao.findPaQuestionByCode(questionKnoledgeCondition, questionCatalogCondition,
						questionCondition, page,order);
			}
		}
		if(!questionList.isEmpty()) {
			for(PaQuestionVo question:questionList) {
				// 难度 题型转成中文
				question.setQuestionTypeString(questionTypeString(question.getQuestionType()));
				question.setDifficulityString(difficulityString(question.getDifficulity()));
				// 是否已收藏
				question.setIsFav(paFavoritesService.isFav(question.getId(), userId, PaperContans.QUESTION));
				handleQuestion(question);
				 //复合题 完型填空
				if ("cloze".equals(question.getQuestionType()) || "complex".equals(question.getQuestionType())) {
					PaQuestionCondition condition=new PaQuestionCondition();
					condition.setParentId(question.getId());
					List<PaQuestionVo> childrenQuestions = paQuestionDao.findPaQuestionVoByCondition(condition);
					if(childrenQuestions!=null&&!childrenQuestions.isEmpty()) {
						for(PaQuestionVo pq:childrenQuestions) {
							handleQuestion(pq);
						}
					}
					question.setChildrenQuestionVo(childrenQuestions);
				}
			}
		}
		return questionList;

	}

	private void handleQuestion(PaQuestionVo paQuestionVo) {
		String correntAnswer = paQuestionVo.getCorrectAnswer();
		String answer = paQuestionVo.getAnswer();
		correntAnswer=correntAnswer.replaceAll("<input id=.*?>", "_______");
		/**图片替换*/
		paQuestionVo.setAnswer(handlerContent(paQuestionVo.getAnswer()));
		/**图片替换*/
		paQuestionVo.setCorrectAnswer(handlerContent(paQuestionVo.getCorrectAnswer()));
		
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		String[] correctAnswers = MqtPaperUtil.StringToArray(correntAnswer, domain);
		String[] answers = MqtPaperUtil.StringToArray(answer, domain);
		paQuestionVo.setAnswers(answers);
		paQuestionVo.setCorrectAnswers(correctAnswers);
		
		/**处理content的图片*/
		paQuestionVo.setContent(handlerContent(paQuestionVo.getContent()));
		
		/**图片替换*/
		paQuestionVo.setExplanation(handlerContent(paQuestionVo.getExplanation()));
	}
	
//	处理图片等
	private String handlerContent(String content) {
		//输入框变下划线
		content=content.replaceAll("<input id=.*?>", "_______");
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		String[] contents = MqtPaperUtil.StringToArray(content, domain);
		
		StringBuffer anwserBuf = new StringBuffer();
		
		for (int i = 0; i < contents.length; i++) {
			String str = contents[i];
			anwserBuf.append(str);
		}
		
		return anwserBuf.toString();
	}
	
	@Override
	public List<PaQuestionVo> findPaperQuestionByParentId(Integer parentId) throws IllegalAccessException, InvocationTargetException {
		PaQuestionCondition paQuestionCondition = new PaQuestionCondition();
		paQuestionCondition.setParentId(parentId);
		List<PaQuestion> result = this.findPaQuestionByCondition(paQuestionCondition);
		
		if(result.size()==0) {
			return new ArrayList<PaQuestionVo>();
		}
		
		Integer[] ids = new Integer[result.size()];
		
		for (int i = 0; i < result.size(); i++) {
			ids[i] = result.get(i).getId();
		}
		
		return this.findPaperQuestionByIds(ids);
	}

	@Override
	public List<PaQuestionVo> findPaperQuestionByIds(Integer[] ids) {
		if(ids.length==0) {
			return new ArrayList<PaQuestionVo>();
		}
		return paQuestionDao.findPaperQuestionByIds(ids, Order.asc("pos"));
	}

	@Override
	public PaQuestion findPaperQuestionByUuid(String uuid) {
		
		return paQuestionDao.findPaperQuestionByUuid(uuid);
	}

	@Override
	public PaQuestionVo findPosQuestionDetail(Integer paperId, String uuid) {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		PaQuestionVo vo=new PaQuestionVo();
		vo=paQuestionDao.findPosQuestionDetail(paperId, uuid);
	    vo=toQuestion(vo);
		vo.setContent(vo.getContent().replaceAll("<input id=.*?>", "_________"));
		vo.setEcContent(vo.getEcContent().replaceAll("<input id=.*?>", "_________"));
		vo.setEcContent(MqtPaperUtil.replaceDomain(vo.getEcContent(),domain));
		return vo;
	}

	//个人中心 试题
	@Override
	public List<PaQuestionVo> findMyQuestion(Integer userId, String libType, String stageCode, String subjectCode,
			Page page, Order order) {
		
		List<PaQuestionVo> questionList=new ArrayList<PaQuestionVo>();
		
		// 默认按更新时间降序
		if (order.getProperty() == null) {
			order.setProperty("modify_date");
		}

		PaQuestionCondition questionCondition = new PaQuestionCondition();
		questionCondition.setUserId(userId);

		// set学段科目
		PaQuestionCatalogCondition questionCatalogCondition = new PaQuestionCatalogCondition();
		if (stageCode != null && !"0".equals(stageCode)) {
			questionCatalogCondition.setStageCode(stageCode);
		}
		if (subjectCode != null && !"0".equals(subjectCode)) {
			questionCatalogCondition.setSubjectCode(subjectCode);
		}
		
		//收藏夹
		if (PaperContans.FAV.equals(libType)) {
			PaFavoritesCondition pfCondition = new PaFavoritesCondition();
			pfCondition.setPosterId(userId);
			pfCondition.setObjectType(PaperContans.QUESTION);
			questionList = paQuestionDao.findPaQuestionOnFavorites(questionCondition, questionCatalogCondition,
					pfCondition,page,order);
		// 我创建的
		} else {
			questionList = paQuestionDao.findMyUploadQuestion(questionCatalogCondition,userId,page,order);
		}
	
		if(!questionList.isEmpty()) {
			for(PaQuestionVo question:questionList) {
				// 难度 题型转成中文
				question.setQuestionTypeString(questionTypeString(question.getQuestionType()));
				question.setDifficulityString(difficulityString(question.getDifficulity()));
				// 是否已收藏
				question.setIsFav(paFavoritesService.isFav(question.getId(), userId, PaperContans.QUESTION));
				handleQuestion(question);
				 //复合题 完型填空
				if ("cloze".equals(question.getQuestionType()) || "complex".equals(question.getQuestionType())) {
					PaQuestionCondition condition=new PaQuestionCondition();
					condition.setParentId(question.getId());
					List<PaQuestionVo> childrenQuestions = paQuestionDao.findPaQuestionVoByCondition(condition);
					if(childrenQuestions!=null&&!childrenQuestions.isEmpty()) {
						for(PaQuestionVo pq:childrenQuestions) {
							handleQuestion(pq);
						}
					}
					question.setChildrenQuestionVo(childrenQuestions);
				}
			}
		}
		return questionList;
	}

	/***
	 * 获取试题的详细信息，包括存储在paper_tree的信息
	 */
	@Override
	public PaQuestionVo findPaQuestionAllInfoById(Integer questionId) {
		if(questionId==null) {
			return null;
		}
		List<PaQuestionVo> result = this.findPaperQuestionByIds(new Integer[]{questionId});
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}

	public void sortNode(List<PaperQuestionTree> nodeList){
		Collections.sort(nodeList, new Comparator<PaperQuestionTree>(){  
			public int compare(PaperQuestionTree node1, PaperQuestionTree node2) {  
				Integer n1 = node1.getNodeOrder();
				Integer n2 = node2.getNodeOrder();
				if(n1 > n2){  
					return 1;  
				}  
				if(n1 == n2){  
					return 0;  
				}  
				return -1;  
			}
		});
	}

	@Override
	public Map<Integer, Object> findPaQuetionKnowledgeNodeNameByIds(
			Integer[] ids) {
		 Map<Integer, List<String>> map=new HashMap<Integer, List<String>>();
		 Map<Integer, Object> map1=new HashMap<Integer, Object>();
		List<PaQuetionKnowledgeNodeNameVo> volist=new ArrayList<PaQuetionKnowledgeNodeNameVo>();
		volist=paQuestionDao.findPaQuetionKnowledgeNodeNameVo(ids);
		if(volist!=null&&volist.size()>0){
			List<String> list=new ArrayList<String>();
			for(PaQuetionKnowledgeNodeNameVo vo : volist){
				list=new ArrayList<String>();
				if(map.get(vo.getQuestionId())!=null){
					list=map.get(vo.getQuestionId());
				}
				list.add(vo.getNodeName());
				map.put(vo.getQuestionId(), list);
			}
			for(Entry<Integer,List<String>> obj:map.entrySet()){
				String[] titleTwo = (String[])obj.getValue().toArray(new String[obj.getValue().size()]);
//				obj.getValue().toArray(titleTwo);
				map1.put(obj.getKey(),titleTwo);
			}
		}
		return map1;
	}

	@Override
	public void updateUsedByquestionIdList(List<Integer> questionIdList) {
		if(questionIdList!=null && questionIdList.size()>0) {
			Integer[] questionIds = new Integer[questionIdList.size()];
			for (int i = 0; i < questionIdList.size(); i++) {
				questionIds[i] = questionIdList.get(i);
			}
			paQuestionDao.updateUsedByquestionIdList(questionIds);
		}
	}

	@Override
	public void modifyRightAnswerCountBatch(List<PaQuestionVo> paQuestionVos) {
		if(paQuestionVos!=null && paQuestionVos.size()>0) {
			paQuestionDao.updateRightAnswerCountBatch(paQuestionVos);
		}
		
	}
}
