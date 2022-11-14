/**   
* @Title: PaperHandleServiceImpl.java
* @Package platform.education.paper.service.impl 
* @Description: 试题处理实现类
* @author pantq   
* @date 2017年2月23日 下午3:11:36 
* @version V1.0   
*/
package platform.education.paper.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.paper.constants.Constants;
import platform.education.paper.constants.PaperType;
import platform.education.paper.constants.QuestionType;
import platform.education.paper.constants.SourceType;
import platform.education.paper.model.AnswerSituationResult;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaQuestionKnoledge;
import platform.education.paper.model.Paper;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.PaperResult;
import platform.education.paper.model.Question;
import platform.education.paper.model.UserFile;
import platform.education.paper.model.UserFileQuestion;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.model.UserRank;
import platform.education.paper.model.UserWrong;
import platform.education.paper.model.WrongPaper;
import platform.education.paper.model.mobile.AnswersJson;
import platform.education.paper.model.pc.PcPaper;
import platform.education.paper.model.pc.PcQuestion;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionKnoledgeService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaperHandleService;
import platform.education.paper.service.PaperQuestionService;
import platform.education.paper.service.PaperService;
import platform.education.paper.service.QuestionService;
import platform.education.paper.service.UserFileQuestionService;
import platform.education.paper.service.UserFileService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.service.UserWrongService;
import platform.education.paper.util.AESHelper;
import platform.education.paper.util.JDBCHandle;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.util.PaperFileUtil;
import platform.education.paper.util.ReadFileUtil;
import platform.education.paper.util.ZipUtil;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaperQuestionCondition;
import platform.education.paper.vo.UserQuestionCondition;
import platform.education.paper.vo.UserWrongCondition;
import platform.education.resource.model.ActionCode;
import platform.education.resource.model.UserAction;
import platform.education.resource.model.UserKnowledgeSummary;
import platform.education.resource.service.ActionCodeService;
import platform.education.resource.service.UserActionService;
import platform.education.resource.service.UserKnowledgeSummaryService;
import platform.education.resource.vo.UserActionCondition;
import platform.education.resource.vo.UserKnowledgeSummaryCondition;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

/**
 * @ClassName: PaperHandleServiceImpl
 * @Description: 试题处理实现类
 * @author pantq
 * @date 2017年2月23日 下午3:11:36
 * 
 */
public class PaperHandleServiceImpl implements PaperHandleService {


	private static String salt = "c4ff9ca7ebPaper";

	@Resource
	private PaperService papaperService;

	@Resource
	private QuestionService questionService;

	@Resource
	private PaperQuestionService paperQuestionService;

	@Resource
	private UserPaperService userPaperService;

	@Resource
	private UserQuestionService userQuestionService;

	@Resource
	private UserWrongService userWrongService;

	@Resource
	private FileService fileService;

	@Resource
	private ActionCodeService actionCodeService;

	@Resource
	private UserActionService userActionService;

	@Resource
	private UserKnowledgeSummaryService userKnowledgeSummaryService;

	@Resource
	private KnowledgeNodeService knowledgeNodeService;

	@Resource
	private UserFileService userFileService;

	@Resource
	private SubjectService subjectService;

	@Resource
	private StudentService studentService;

	@Resource
	private PaPaperService paPaperService;

	private ObjectMapper mapper = new ObjectMapper();

	@Resource(name = "uploadAnswer_taskExecutor")
	private ThreadPoolTaskExecutor uploadAnswerTaskExecutor;

	@Resource
	private UserFileQuestionService userFileQuestionService;
	
	@Resource
	private PaQuestionService paQuestionService;

	@Resource
	private PaQuestionKnoledgeService paQuestionKnoledgeService;

	// 模拟redis先干缓存。

	/*
	 * (非 Javadoc) <p>Title: paperSplit</p> <p>Description: </p>
	 * 
	 * @param fileUuid
	 * 
	 * @param fileMd5
	 * 
	 * @param fileSize
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#paperSplit(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public PaperResult paperSplit(Integer userId, String filePath, String targerPath, String fileUuid, String fileMd5,
			Long fileSize, String versionCode, String stageCode, String categoryCode) throws Exception {
		PaperResult paperResult = new PaperResult();

		// 1. 获取压缩包描述内容
		String comment = ZipUtil.getComment(filePath);

		// 2. 根据描述内容计算真实密码
		String password = AESHelper.decrypt(comment);

		// 3. 拿到真实密码解压
		Boolean flag = ZipUtil.explodeZip(filePath, targerPath, password);

		// 4. 解压成功读取文件解析题库
		if (flag) {
			// 5. 解析document.json文件 进行试题拆分 (文件名和后缀必须要保持不变)
			// File file = new File(targerPath + "document.json");
			String path = targerPath + "document.json";
			// 6. 读取document.json文件内容 入库
			String content = ReadFileUtil.readString(path);

			// 返回paperid 结束
			paperResult = spitQuestion(userId + "", content, targerPath, fileUuid, fileMd5, fileSize, versionCode,
					stageCode, categoryCode);

			// 删除临时文件夹
			PaperFileUtil.deleteDir(new File(targerPath));
			// FileUtil.deleteDir(new File(targerPath));
			// FileUtil.deleteFile(new File(targerPath));
			return paperResult;
		}
		return null;
	}

	/**
	 * 拆题业务逻辑处理(优化后)
	 * 
	 * @author pantq
	 * @return
	 */
	private PaperResult spitQuestion(String userId, String content, String targerPath, String fileUuid, String fileMd5,
			Long fileSize, String versionCode, String stageCode, String categoryCode) {
		PaperResult result = new PaperResult();
		Paper paPaper = null;
		String paperUuid = null;
		Integer paPaperId = null;
		ObjectMapper mapper = new ObjectMapper();
		try {

			// 1. 获取试卷内容 开始拆题 烦序列化
			PcPaper pcPaper = mapper.readValue(content, PcPaper.class);

			if (pcPaper != null) {
				paPaper = new Paper();
				paPaper.setFileUuid(fileUuid);
				paPaper.setFileSize(fileSize);
				paPaper.setFileMd5(fileMd5);
				paPaper.setUserId(userId);
				paPaper.setVersionCode(versionCode); // 新加字段，PC那边还没有改
				paPaper.setCategoryCode(categoryCode); // 新加字段，PC那边还没有改

				// 插入试卷返回实体
				paPaper = addPaper(pcPaper, paPaper);
				paperUuid = pcPaper.getPaperId();
				paPaperId = paPaper.getId();
				String subjectCode = pcPaper.getSubjectCode();
				// 2. 开始题目拆分
				List<PcQuestion> pcQuestionList = pcPaper.getQuestions();
				if (pcQuestionList != null && pcQuestionList.size() > 0) {

					for (PcQuestion pcQuestion : pcQuestionList) {
						// 获取题目类型
						String type = pcQuestion.getType();
						if (type.equals(QuestionType.MEMO)) { // 当遇到类型为：MEMO 跳过此次循环
							continue;
						}
						// 题目处理
						appendQuesiton(pcQuestion, userId, paperUuid, subjectCode, targerPath, mapper);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setPaperId(paPaperId);
		result.setPaperUuid(paperUuid);
		return result;
	}

	/**
	 * 添加试卷处理
	 * 
	 * @author pantq
	 * @param pcPaper
	 * @param paPaper
	 * @return
	 */
	private Paper addPaper(PcPaper pcPaper, Paper paPaper) {

		paPaper.setPaperUuid(pcPaper.getPaperId());
		paPaper.setUsedCount(0);
		paPaper.setTitle(pcPaper.getPaperTitle());
		paPaper.setPaperType(pcPaper.getPaperType());
		paPaper.setScore(pcPaper.getScore());
		paPaper.setSubjectCode(pcPaper.getSubjectCode());
		paPaper.setGradeCode(pcPaper.getGradeCode());
		paPaper.setVolumeCode(pcPaper.getVolumeCode());
		paPaper.setDifficulity(pcPaper.getDifficulty()); // 由于PC端那边 首字母大写
		paPaper.setKnowledge(paPaper.getKnowledge());

		paPaper.setCreateDate(new Date());
		paPaper = papaperService.add(paPaper);
		return paPaper;
	}

	/**
	 * 题目拆分处理
	 * 
	 * @param demoQuestion
	 */
	private void appendQuesiton(PcQuestion pcQuestion, String userId, String paperUuid, String subjectCode,
			String targerPath, ObjectMapper mapper) {
		Question paQuestion = null;
		String type = pcQuestion.getType();
		if (QuestionType.COMPLEX.equals(type)) {// 符合题
			List<PcQuestion> complexList = pcQuestion.getQuestions();
			if (complexList != null && complexList.size() > 0) {
				// 符合题分组
				String groupId = UUID.randomUUID().toString();
				for (PcQuestion qd : complexList) {
					String questionUuid = qd.getQuestionId();
					Double score = qd.getScore();
					Integer pos = qd.getPos();
					String questionSubjectCode = subjectCodeSelection(subjectCode, qd.getSubjectCode());
					String content = qd.getContent();
					String correctAnswer = isJson(qd.getCorrectAnswer(), targerPath);
					;
					String answer = isJson(qd.getAnswer(), targerPath);

					// 图片上传处理
					content = attrImage(content, targerPath);
					String extraContent = qd.getExtraContent();
					extraContent = attrImage(extraContent, targerPath);
					String explanation = qd.getExplanation();
					explanation = attrImage(explanation, targerPath);

					paQuestion = new Question();
					paQuestion.setUsedCount(0);
					paQuestion.setKnowledgeId(qd.getKnowledgeId());
					paQuestion.setKnowledge(qd.getKnowledge());
					paQuestion.setQuestionType(qd.getType());
					paQuestion.setQuestionUuid(questionUuid);
					paQuestion.setSourceType(SourceType.LEARNINGPLANQUESTIONS);
					paQuestion.setContent(content);
					paQuestion.setExtraContent(extraContent);
					paQuestion.setAnswer(answer);
					paQuestion.setCorrectAnswer(correctAnswer);
					paQuestion.setExplanation(explanation);
					paQuestion.setUserId(userId);
					paQuestion.setCognition(qd.getCognition());
					paQuestion.setDifficulity(qd.getDifficulty());
					paQuestion.setSubjectCode(questionSubjectCode);
					paQuestion.setGradeCode(qd.getGradeCode());
					paQuestion.setVolumeCode(qd.getVolumeCode());
					paQuestion.setScore(score);
					paQuestion.setPos(pos);
					paQuestion.setGroupId(groupId);
					paQuestion.setGroupTitle(qd.getGroupTitle());

					// 添加
					questionService.add(paQuestion);
					// 题目写入试卷试题表 pa_paper_question 表
					PaperQuestion paPaperQuestion = new PaperQuestion();
					paPaperQuestion.setQuestionUuid(questionUuid);
					paPaperQuestion.setPaperUuid(paperUuid);
					paPaperQuestion.setScore(score);
					paPaperQuestion.setCreateDate(new Date());
					paPaperQuestion.setPos(pos);
					paperQuestionService.add(paPaperQuestion);

				}
			}

		} else { // 菲复合套处理
			String questionSubjectCode = subjectCodeSelection(subjectCode, pcQuestion.getSubjectCode());
			String questionUuid = pcQuestion.getQuestionId();
			Double score = pcQuestion.getScore();
			Integer pos = pcQuestion.getPos();

			// 图片上传处理
			String content = pcQuestion.getContent();
			content = attrImage(content, targerPath);
			String extraContent = pcQuestion.getExtraContent();
			extraContent = attrImage(extraContent, targerPath);
			// String answer = Arrays.toString(pcQuestion.getAnswer());
			String correctAnswer = isJson(pcQuestion.getCorrectAnswer(), targerPath);
			String answer = isJson(pcQuestion.getAnswer(), targerPath);

			String explanation = pcQuestion.getExplanation();
			explanation = attrImage(explanation, targerPath);

			paQuestion = new Question();
			paQuestion.setUsedCount(0);
			paQuestion.setKnowledgeId(pcQuestion.getKnowledgeId());
			paQuestion.setKnowledge(pcQuestion.getKnowledge());
			paQuestion.setQuestionType(pcQuestion.getType());
			paQuestion.setQuestionUuid(questionUuid);
			paQuestion.setSourceType(SourceType.LEARNINGPLANQUESTIONS);
			paQuestion.setContent(content);
			paQuestion.setExtraContent(extraContent);
			paQuestion.setAnswer(answer);
			paQuestion.setCorrectAnswer(correctAnswer);
			paQuestion.setExplanation(explanation);
			paQuestion.setUserId(userId);
			paQuestion.setCognition(pcQuestion.getCognition());
			paQuestion.setDifficulity(pcQuestion.getDifficulty());
			paQuestion.setSubjectCode(questionSubjectCode);
			paQuestion.setGradeCode(pcQuestion.getGradeCode());
			paQuestion.setVolumeCode(pcQuestion.getVolumeCode());
			paQuestion.setScore(score);
			paQuestion.setPos(pos);

			// 添加
			questionService.add(paQuestion);
			// 题目写入试卷试题表 pa_paper_question 表
			PaperQuestion paPaperQuestion = new PaperQuestion();
			paPaperQuestion.setQuestionUuid(questionUuid);
			paPaperQuestion.setPaperUuid(paperUuid);
			paPaperQuestion.setScore(score);
			paPaperQuestion.setCreateDate(new Date());
			paPaperQuestion.setPos(pos);
			paperQuestionService.add(paPaperQuestion);

		}

		// 大数据统计
	}

	private String isJson(String[] source, String targerPath) {
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try {
			List<String> newList = new ArrayList<String>();
			// List<String> list = mapper.readValue(source, List.class);
			if (source != null && source.length > 0) {
				for (String s : source) {
					// sb.append(attrImage(s,targerPath));
					newList.add(attrImage(s, targerPath));
				}

			}

			result = mapper.writeValueAsString(newList);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 判断题目是否有科目，如没有就跟试卷的科目CODE 如有就按题目的科目CODE
	 * 
	 * @param paperSubjectCode
	 * @param questionSubjectCode
	 * @return
	 */

	private String subjectCodeSelection(String paperSubjectCode, String questionSubjectCode) {

		if (questionSubjectCode != null && !"".equals(questionSubjectCode)) {
			return questionSubjectCode;
		}

		return paperSubjectCode;
	}

	/**
	 * 上传图片处理
	 * 
	 * @author pantq
	 * @param pamas
	 *            HTML字符串
	 * @param targerPath
	 *            图片在服务器路径
	 * @return
	 */
	private String attrImage(String source, String targerPath) {

		// check
		if (source == null || source.trim().length() == 0)
			return "";

		// init rs
		// int rs = 0;

		// new text
		StringBuilder sb = new StringBuilder();

		// start text
		String st = "src=\"";
		String et = "\"";

		// handle
		int start = source.indexOf(st);

		// check start
		if (start < 0)
			return source;

		// handle_1
		String[] array = source.split(st);
		if (array.length < 2)
			return source;

		// handle_2
		sb.append(array[0]);
		for (int index = 1; index < array.length; index++) {
			// check array
			if (array[index].trim().length() == 0)
				continue;

			// init next
			int next = array[index].indexOf(et);

			// check next
			if (next < 0)
				continue;

			// handle src
			String srcText = array[index].substring(0, next);
			File file = new File(targerPath + File.separator + "images" + File.separator + srcText);
			FileResult fileResult = fileService.upload(file, "", "paper");
			if (fileResult != null && fileResult.getEntityFile() != null) {
				String fileSrc = fileResult.getEntityFile().getRelativePath();
				// 原图片路径
				// System.out.println("srcText : " + srcText);
				if (srcText.trim().length() == 0)
					continue;
				// append st
				sb.append(st);
				sb.append(fileSrc + et + array[index].substring(next + 1, array[index].length()));
			}

		}
		// return
		return sb.toString();
	}

	/**
	 * 题目绑定题目答案上传接口。
	 */
	@Override
	public PaperResult uploadPaperAnswer(String paperUuId, Integer teamId, Double score, final Integer userId,
			String answers, Integer type, final Integer ownerId, final Integer resourceType, final String schoolYear,
			final String termCode, Integer unitId) throws Exception {
		//long startTime = System.currentTimeMillis();
		//初始化数据
		double totalSocre = 0d;
		PaperResult presult = new PaperResult();
		final List<UserFileQuestion> userFileQuestionList = new ArrayList<UserFileQuestion>();
		
		// 类型值为空默认为导学案
		if (type == null) {
			type = PaperType.LEARNING_PLAN;
		}

		// 2. 解析用户提交答案JSON
		final AnswersJson[] answerJsonList = mapper.readValue(answers, AnswersJson[].class);
		final List<String> questionUuids = new ArrayList<String>(answerJsonList.length);
		
		// 5. 解析用户提交过来的答案并做响应处理
		final List<UserQuestion> userQuestionsList = new ArrayList<UserQuestion>(answerJsonList.length);
		final List<UserWrong> userWrongList = new ArrayList<UserWrong>(answerJsonList.length / 7);
		final List<UserFile> userFileList = new ArrayList<UserFile>();
		List<PaQuestionVo> paQuestionVoList = null;
		PaPaper paPaper = paPaperService.findPaPaperByUUid(paperUuId);
		if(paPaper != null) {
			paQuestionVoList = paQuestionService.findPaQuestionVoByPaperId(paPaper.getId());
			Map<String, PaQuestionVo> paperQuestionMap = new HashMap<String, PaQuestionVo>(paQuestionVoList.size());
			Map<String, String> uuidMap = new HashMap<String, String>(paQuestionVoList.size());
			for (PaQuestionVo paQuestionVo : paQuestionVoList) {
				String questionUuid = paQuestionVo.getUuid();
				paperQuestionMap.put(questionUuid, paQuestionVo);

			}
			final Map<String, Integer> questionRightAnswerCountMap = new HashMap<String, Integer>();
			final Map<String, Float> questionDifficulityMap = new HashMap<String, Float>();
			final Map<String, Integer> questionTotalTimeMap = new HashMap<String, Integer>();
			final Map<String, Integer> questionTotalTimeCountMap = new HashMap<String, Integer>();
			final Map<String, Float> questionAverageTimeMap = new HashMap<String, Float>();
			final Map<String, Integer> questionIsAnswerMap = new HashMap<String, Integer>();
			final Map<String, Integer> questionAnswerCountMap = new HashMap<String, Integer>();
			final Map<String, Integer> questionIsCorrectMap = new HashMap<String, Integer>();

			// 循环小题
			for (AnswersJson answer : answerJsonList) {
				// 用户答案
				String sub_answer = null;
				try {
					sub_answer = mapper.writeValueAsString(answer.getAnswer());
				} catch (IOException e) {
					e.printStackTrace();
				}

				// 题目uuid （每道题目唯一）
				String questionUuid = answer.getQuestionUuid();
				questionUuids.add(questionUuid);
				Integer size = 0;
				if(!"[]".equals(sub_answer)) {
					size = 1;
				}
				questionIsAnswerMap.put(questionUuid, size);

				// 答案是否正确
				Integer isCorrect = answer.getIsCorrect();
				// 作答时间
				Integer answerTime = answer.getAnswerTime();
				PaQuestionVo vo = paperQuestionMap.get(questionUuid);
				String uuid = UUID.randomUUID().toString();
				uuidMap.put(questionUuid, uuid);
				Integer rightAnswerCount = 0;
				Integer answerCount = 0;
				Float difficulity = 0f;
				Integer paperQuestionId = 0;
				// 题目分数
				Float questionScore = 0f;
				Integer totalTime = 0;
				Integer totalTimeCount = 0;
				Float averageTime = 0f;
				Date nowDate = new Date();

				if (vo != null) {
					rightAnswerCount = vo.getRightAnswerCount();
					answerCount = vo.getAnswerCount();
					if(answerCount != null) {
						answerCount = answerCount + 1;
						questionAnswerCountMap.put(questionUuid,answerCount);
					}
					totalTime = vo.getTotalTime();
					totalTimeCount = vo.getTotalTimeCount();
					averageTime = vo.getAverageTime();
					// 判断分数是否为null 初始化得分
					if (vo.getScore() == null) {
						vo.setScore(0f);
					} else {
						questionScore = vo.getScore();
					}
					paperQuestionId = vo.getId();
				}

				// 插入用户作答表
				UserQuestion userQuestion = new UserQuestion();
				userQuestion.setPaperUuid(paperUuId);
				userQuestion.setType(type);
				if(answerTime != null) {
					userQuestion.setAnswerTime(answerTime);
					if(totalTime == null) {
						totalTime = 0;
					}
					totalTime = totalTime + answerTime;
					questionTotalTimeMap.put(questionUuid, totalTime);
					totalTimeCount = totalTimeCount+1;
					questionTotalTimeCountMap.put(questionUuid, totalTimeCount);
					if(totalTimeCount != 0) {
						averageTime = (float)totalTime / totalTimeCount;
						questionAverageTimeMap.put(questionUuid, averageTime);
					}

				}else {
					userQuestion.setAnswerTime(0);
				}
				userQuestion.setOwnerId(ownerId);
				userQuestion.setTeamId(teamId);
				userQuestion.setPaperQuestionId(paperQuestionId);
				userQuestion.setQuestionUuid(questionUuid);
				userQuestion.setUserId(userId);
				userQuestion.setObjectId(unitId);
				userQuestion.setAnswer(sub_answer);
				userQuestion.setUuid(uuid);
				userQuestion.setAnswerTime(Integer.parseInt(""+answer.getAnswerTime()));

				if (isCorrect == 1) { // 正确得相应分数
					if(rightAnswerCount != null) {
						rightAnswerCount = rightAnswerCount + 1;
					}
					double tempScore = questionScore;
					userQuestion.setScore(tempScore);
					userQuestion.setIsCorrect(true);
					userQuestion.setCorrectInt(1);

					totalSocre += questionScore;
				} else {
					userQuestion.setScore(0d);
					if(vo.getQuestionType().equals("checkbox")||vo.getQuestionType().equals("multichoise")){
						if(!sub_answer.equals("[]")){
							JSONArray ja=JSONArray.fromObject(sub_answer);
							if(ja.size()>0){
								JSONObject ob=(JSONObject) ja.get(0);
								System.out.println(ob.get("answer"));
								List<String> as=(List<String>) ob.get("answer");
//								List<String> as1=new ArrayList<String>(as.size());
								String[] uas=new String[as.size()];
								int i=0;
								for(String s:as){
									uas[i]=s;
									i++;
								}
								if(uas.length>0){
									String[] a1=mapper.readValue(vo.getCorrectAnswer(), String[].class);
//									String[] a2=mapper.readValue(as, String[].class);
									List<String> l1 = new ArrayList<String>(a1.length);
									Collections.addAll(l1, a1);
									List<String> l2 = new ArrayList<String>();
									Collections.addAll(l2, uas);   
									if(l1.containsAll(l2)){
										BigDecimal b1=new BigDecimal(questionScore+"");
										BigDecimal b2=new BigDecimal(2+"");
										Double tscore=b1.divide(b2,1,BigDecimal.ROUND_HALF_UP).doubleValue();
										totalSocre += tscore;
										userQuestion.setScore(tscore);
									}
								}
							}
						}
					}
					// 错误得0分
					userQuestion.setIsCorrect(false);
					userQuestion.setCorrectInt(0);
					// 插入用户错题库
					UserWrong userWrong = new UserWrong();
					userWrong.setPaperQuestionId(paperQuestionId);
					userWrong.setUserId(userId);
					userWrong.setPaperUuid(paperUuId);
					userWrong.setQuestionUuid(questionUuid);
					userWrong.setAnswer(sub_answer);
					userWrong.setCreateDate(nowDate);
					userWrong.setModifyDate(nowDate);
					userWrong.setUserQuestionUuid(uuid);
					userWrongList.add(userWrong);
				}
				questionIsCorrectMap.put(questionUuid, isCorrect);
				userQuestion.setCreateDate(nowDate);
				userQuestion.setModifyDate(nowDate);
				userQuestionsList.add(userQuestion);
				questionRightAnswerCountMap.put(questionUuid, rightAnswerCount);
				if(answerCount != 0) {
					difficulity = rightAnswerCount /(1.0f*answerCount);
					questionDifficulityMap.put(questionUuid, difficulity);
				}
				// 大数据处理
				// appendBigData(sub_answer, isCorrect, questionUuid, userId, resourceType,
				// ownerId, schoolYear,
				// termCode,userActionList,userKnowledgeSummaryList,userKnowledgeSummaryModifyList);

			}

			// 6. 插入userPaper表
			Date newDate = new Date();
			UserPaper userPaper = new UserPaper();
			userPaper.setPaperUuid(paperUuId);
			userPaper.setTeamId(teamId);
			userPaper.setUserId(userId);
			userPaper.setType(type);
			userPaper.setOwnerId(ownerId);
			userPaper.setScore(totalSocre);
			userPaper.setFinishedTime(newDate);
			userPaper.setCreateDate(newDate);
			userPaper.setModifyDate(newDate);
			userPaper.setObjectId(unitId);
			UserPaper uPaper = userPaperService.add(userPaper);
			Integer uPaperId = uPaper.getId();
			
			Date nowDate = new Date();
			// 图片和题目挂钩
			for (AnswersJson answer : answerJsonList) {
				String[] pictures = answer.getPicture();
				if(pictures != null && pictures.length > 0) {
					for (String uid : pictures) {
						UserFile userFile = new UserFile();
						userFile.setUserId(userId);
						userFile.setUserPaperId(uPaperId);
						userFile.setSourceFileUuid(uid);
						userFile.setCreateDate(nowDate);
						userFile.setIsDeleted(false);
						userFile = this.userFileService.add(userFile);
						Integer userFileId = userFile.getId();

						UserFileQuestion userFileQuestion = new UserFileQuestion();
						userFileQuestion.setUserFileId(userFileId);
						userFileQuestion.setUserQuestionUuid(uuidMap.get(answer.getQuestionUuid()));
						userFileQuestion.setCreateDate(nowDate);
						userFileQuestion.setModifyDate(nowDate);
						userFileQuestionList.add(userFileQuestion);
					}
				}
			}

			/*ValueOperations operations = stringRedisTemplate.opsForValue();
			operations.set("","");*/
			uploadAnswerTaskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					JDBCHandle.jdbcInsertUserQuestion(userQuestionsList);
					JDBCHandle.jdbcInsertUserWrong(userWrongList);
					JDBCHandle.jdbcInsertUserFileQuestion(userFileQuestionList);
					bathUserFile(userFileList);
				try {
					appendBigData(questionUuids, ownerId, resourceType, userId, schoolYear, termCode,questionRightAnswerCountMap,questionDifficulityMap,questionTotalTimeMap,questionTotalTimeCountMap,questionAverageTimeMap,questionIsAnswerMap,questionAnswerCountMap,questionIsCorrectMap);
				} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			});
		}
		

		return presult;
	}
	
	private void appendBigData(List<String> questionUuids, Integer ownerId,
			Integer resourceType, Integer userId, String schoolYear, String termCode, Map<String,Integer> rightAnswerCountMap,Map<String,Float> questionDifficulityMap,Map<String,Integer> questionTotalTimeMap,Map<String,Integer> questionTotalTimeCountMap,Map<String,Float> questionAverageTimeMap,Map<String,Integer> questionIsAnswerMap,Map<String,Integer> questionAnswerCountMap,Map<String,Integer> questionIsCorrectMap) throws IllegalAccessException, InvocationTargetException {

		// 根据questionUuid数组获取所有question对象
		List<PaQuestionVo>  paQuestionVoList = paQuestionService.findPaperQuestionByUUIDs(questionUuids.toArray());

		Map<String, PaQuestionVo> questionList = new HashMap<String, PaQuestionVo>();;
		for(PaQuestionVo paQuestionVo :paQuestionVoList){
			questionList.put(paQuestionVo.getUuid(), paQuestionVo);
		}

		ActionCode actionCode = this.actionCodeService.findActionCodeByName("答题");
		Integer actionCodeId = actionCode.getId();
		Float actionCodeScore = actionCode.getScore();
		for (String questionUuid :questionUuids) {
			Integer size = questionIsAnswerMap.get(questionUuid);
			PaQuestionVo question = questionList.get(questionUuid);
			if (question != null) {
				String subjectCode = question.getSubjectCode();
				Integer answerCount = questionAnswerCountMap.get(questionUuid);
				question.setAnswerCount(answerCount);
				Integer rightAnswerCount = rightAnswerCountMap.get(questionUuid);
				question.setRightAnswerCount(rightAnswerCount);
				// 3. 更新题目的真实难度
				Float difficulity= questionDifficulityMap.get(questionUuid);
				question.setDifficulity(difficulity);
				Integer totalTime = questionTotalTimeMap.get(questionUuid);
				question.setTotalTime(totalTime);
				Integer totalTimeCount = questionTotalTimeCountMap.get(questionUuid);
				question.setTotalTimeCount(totalTimeCount);
				Float averageTime = questionAverageTimeMap.get(questionUuid);
				question.setAverageTime(averageTime);
				PaQuestion paQuestion = new PaQuestion();
				BeanUtils.copyProperties(paQuestion, question);
				paQuestionService.modify(paQuestion);

			// 题目分数
			// 统计用户行为,如果为空 就不统计了。
			if (size>0) {
				// 1.更新题库题目的答题统计
					// 5.生成用户答题行为记录
					List<PaQuestionKnoledge> paQuestionKnoledgeList =  paQuestionKnoledgeService.findByQuestionId(question.getId());
					for (PaQuestionKnoledge paQuestionKnoledge : paQuestionKnoledgeList) {
						UserAction userAction = new UserAction();
						userAction.setUserId(userId);
						userAction.setResourceType(resourceType);

						// 改为 pa_question表的uuid
						userAction.setResourceUuid(questionUuid);
						userAction.setActionId(actionCodeId);
						userAction.setActionScore(actionCodeScore);
						userAction.setSubjectCode(subjectCode);
						Integer knowledgeId = Integer.parseInt(paQuestionKnoledge.getKnowledgeCode());
						userAction.setKnowledgeId(knowledgeId);
						userAction.setSchoolYear(schoolYear);
						userAction.setTermCode(termCode);
						userAction.setIsDeleted(false);
						userAction.setModifyDate(new Date());
						Integer isCorrect = questionIsCorrectMap.get(questionUuid);
						if (isCorrect == 1) {
							userAction.setSucceeded(true);
						} else {
							userAction.setSucceeded(false);
						}
						userAction.setCreateDate(new Date());
						this.userActionService.add(userAction);
						float answerQuestionRatio = 0;
						UserKnowledgeSummaryCondition userKnowledgeSummaryCondition = new UserKnowledgeSummaryCondition();
						userKnowledgeSummaryCondition.setUserId(userId);
						userKnowledgeSummaryCondition.setKnowledgeId(knowledgeId);
						userKnowledgeSummaryCondition.setSubjectCode(subjectCode);
						List<UserKnowledgeSummary> uKnowledgeSummaryList = this.userKnowledgeSummaryService
								.findUserKnowledgeSummaryByCondition(userKnowledgeSummaryCondition, null, null);
						UserKnowledgeSummary userKnowledgeSummary = null;
						if (uKnowledgeSummaryList != null && uKnowledgeSummaryList.size() > 0) {

							UserActionCondition userActionCondition = new UserActionCondition();
							userActionCondition.setKnowledgeId(knowledgeId);
							userActionCondition.setSucceeded(true);
							Long succeededCount = this.userActionService.count(userActionCondition);

							UserActionCondition userActionCondition1 = new UserActionCondition();
							userActionCondition1.setKnowledgeId(knowledgeId);
							// userActionCondition.setSucceeded(false);
							Long notSucceededCount = this.userActionService.count(userActionCondition1);
							userKnowledgeSummary = uKnowledgeSummaryList.get(0);
							if (notSucceededCount != 0) {
								answerQuestionRatio = (float) succeededCount / notSucceededCount;
								userKnowledgeSummary.setAnswerQuestionRatio(answerQuestionRatio);
							}
							// this.userKnowledgeSummaryModifyList.add(userKnowledgeSummary);
							this.userKnowledgeSummaryService.modify(userKnowledgeSummary);
						} else {
							userKnowledgeSummary = new UserKnowledgeSummary();
							userKnowledgeSummary.setUserId(userId);
							userKnowledgeSummary.setKnowledgeId(knowledgeId);
							userKnowledgeSummary.setModifyDate(new Date());
							userKnowledgeSummary.setSubjectCode(subjectCode);
							userKnowledgeSummary.setAnswerQuestionRatio(answerQuestionRatio);
							userKnowledgeSummary.setIsDeleted(false);
							userKnowledgeSummary.setCreateDate(new Date());
							// this.userKnowledgeSummaryList.add(userKnowledgeSummary);
							this.userKnowledgeSummaryService.add(userKnowledgeSummary);
						}
					}
				}
			}
		}
	}

	private void bathUserFile(List<UserFile> userFileList) {
		userFileService.processUserFileAndOrUpdate(userFileList);
	}

	/*
	 * (非 Javadoc) <p>Title: isExistFile</p> <p>Description: </p>
	 * 
	 * @param fileMd5
	 * 
	 * @param paperUuid
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#isExistFile(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public PaperResult isExistFile(String fileMd5, String paperUuid) throws Exception {

		PaperResult paperResult = new PaperResult();

		// 1. 判断文件MD5 是否存在服务器
		FileResult fileResult = this.fileService.findFileByMD5(fileMd5);
		EntityFile entityFile = fileResult.getEntityFile();
		if (entityFile != null) { // 文件存在
			// 2. 判断试卷是否被使用过
			Paper paper = papaperService.findPaperByUuid(paperUuid);

			if (paper != null && paper.getUsedCount() == 0) { // 文件存在并没有被使用过
				paperResult.setStatus(Constants.FILEEXISTNOTUSED);
				paperResult.setMsg(Constants.FILEEXISTNOTUSEDMSG);
			} else if (paper != null && paper.getUsedCount() > 0) { // 已存在并且试卷被使用过
				paperResult.setStatus(Constants.FILEEXISTUSED);
				paperResult.setMsg(Constants.FILEEXISTUSEDMSG);
			}

		} else { // 文件不存在
			paperResult.setStatus(Constants.FILENOTEXIST);
			paperResult.setMsg(Constants.FILENOTEXISTMSG);
		}

		return paperResult;
	}

	/*
	 * (非 Javadoc) <p>Title: findUserPaperByPaperUuId</p> <p>Description: </p>
	 * 
	 * @param paperUuid
	 * 
	 * @return
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#findUserPaperByPaperUuId(
	 * java.lang.String)
	 */
	@Override
	public List<UserRank> findUserPaperByPaperId(Integer paperId, Integer type, Integer ownerId, Integer teamId) {
		List<UserRank> userRankList = null;
		Paper paper = this.papaperService.findPaperById(paperId);
		if (paper != null) {
			if (type == null) { // 没有传类型默认统计导学案类型
				type = PaperType.LEARNING_PLAN;
			}
			userRankList = userPaperService.findUserPaperByPaperUuId(paper.getPaperUuid(), type, ownerId, teamId);
		}
		return userRankList;
	}

	/*
	 * (非 Javadoc) <p>Title: findPaperQuestionCorrectRateByPaperUuId</p>
	 * <p>Description: </p>
	 * 
	 * @param paperUuid
	 * 
	 * @return
	 * 
	 * @see platform.education.paper.service.PaperHandleService#
	 * findPaperQuestionCorrectRateByPaperUuId(java.lang.String)
	 */
	@Override
	public List<UserRank> findPaperQuestionCorrectRateByPaperId(Integer paperId, Integer type, Integer ownerId,
			Integer teamId) {
		List<UserRank> userRankList = null;
		Paper paper = this.papaperService.findPaperById(paperId);
		if (paper != null) {
			if (type == null) { // 没有传类型默认统计导学案类型
				type = PaperType.LEARNING_PLAN;
			}

			userRankList = userPaperService.findPaperQuestionCorrectRateByPaperUuId(paper.getPaperUuid(), type, ownerId,
					teamId);
		}
		return userRankList;
	}

	/*
	 * (非 Javadoc) <p>Title: updatePaperUsedCount</p> <p>Description: </p>
	 * 
	 * @param paperId
	 * 
	 * @return
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#updatePaperUsedCount(java
	 * .lang.Integer)
	 */
	@Override
	public Boolean updatePaperUsedCount(Integer paperId) {
		Boolean flag = false;
		// 1. 根据paperId查询pa_paper表的记录更新usedCount的值。
		Integer paperUsedCount = 0;
		Integer questionUsedCount = 0;
		try {
			Paper paper = this.papaperService.findPaperById(paperId);
			if (paper != null) {
				if (paper.getUsedCount() != null) {
					paperUsedCount = paper.getUsedCount() + 1;
				}
				paper.setUsedCount(paperUsedCount);
				papaperService.modify(paper);
				// 2. 根据paperUuId在pa_paper_question 找到该份试卷所有题目 将usedCount的值更新。
				PaperQuestionCondition paperQuestionCondition = new PaperQuestionCondition();
				paperQuestionCondition.setPaperUuid(paper.getPaperUuid());
				List<PaperQuestion> paperQuestionList = this.paperQuestionService
						.findPaperQuestionByCondition(paperQuestionCondition);
				if (paperQuestionList != null && paperQuestionList.size() > 0) {
					for (PaperQuestion paperQuestion : paperQuestionList) {
						// 3. 根据questionUuId 去pa_question表查询所有记录 并把usedCount的值更新。
						Question question = this.questionService.findQuestionByUuid(paperQuestion.getQuestionUuid());
						if (question != null) {
							if (question.getUsedCount() != null) {
								questionUsedCount = question.getUsedCount() + 1;
							}
							question.setUsedCount(questionUsedCount);
							questionService.modify(question);
						}
					}
				}
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/*
	 * (非 Javadoc) <p>Title: findUserWrongList</p> <p>Description: </p>
	 * 
	 * @param userId
	 * 
	 * @param subjectCode
	 * 
	 * @param page
	 * 
	 * @param order
	 * 
	 * @return
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#findUserWrongList(java.
	 * lang.Integer, java.lang.String, framework.generic.dao.Page,
	 * framework.generic.dao.Order)
	 * 
	 * @Override public List<Map<String, Object>> findUserWrongList(Integer userId,
	 * String subjectCode, Page page, Order order) { String domain =
	 * this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
	 * List<WrongPaper> wrongPaperList =
	 * userWrongService.findUserWrongListByUserId(userId, subjectCode, page, order);
	 * List<WrongPaperJson> wrongPaperJsonList = new ArrayList<WrongPaperJson>();
	 * if(wrongPaperList!=null &&wrongPaperList.size() > 0){ for(WrongPaper
	 * wrongPaper:wrongPaperList){ try { if(wrongPaper != null){ WrongPaperJson
	 * wrongPaperJson = new WrongPaperJson(); String complexTitle =
	 * wrongPaper.getComplexTitle(); String content = wrongPaper.getContent();
	 * String explanation = wrongPaper.getExplanation(); String groupId =
	 * wrongPaper.getGroupId(); if(groupId != null && !"".equals(groupId)){
	 * wrongPaper.setIsComplex(true); }
	 * 
	 * if(explanation != null && !"".equals(explanation)){ explanation =
	 * explanation.replace("&nbsp;", " "); explanation =
	 * explanation.replaceAll("&plusmn;", "±");
	 * wrongPaper.setExplanation(replaceDomain(explanation,domain)); } if(content !=
	 * null && !"".equals(content)){ content = content.replace("&nbsp;", " ");
	 * content = content.replace("\n", "");
	 * wrongPaper.setContent(replaceDomain(content,domain)); }
	 * 
	 * if(complexTitle != null && !"".equals(complexTitle)){
	 * 
	 * complexTitle = complexTitle.replace("&nbsp;", " ");
	 * wrongPaper.setComplexTitle(replaceDomain(complexTitle,domain)); }
	 * 
	 * wrongPaper.setCorrectAnswer(replaceDomain(wrongPaper.getCorrectAnswer(),
	 * domain));
	 * wrongPaper.setUserAnswer(replaceDomain(wrongPaper.getUserAnswer(),domain));
	 * String paperAnswer = wrongPaper.getPaperAnser(); String correctAnswer =
	 * wrongPaper.getCorrectAnswer(); //paperAnswer =
	 * paperAnswer.substring(1,paperAnswer.length()-2); if(paperAnswer != null &&
	 * !"".equals(paperAnswer)){ paperAnswer = paperAnswer.trim(); paperAnswer =
	 * paperAnswer.replaceAll("", ""); paperAnswer =
	 * paperAnswer.replaceAll("&quot;",""); paperAnswer = paperAnswer.replace("[",
	 * ""); paperAnswer = paperAnswer.replace("]", ""); paperAnswer =
	 * StringEscapeUtils.unescapeJava(paperAnswer);
	 * 
	 * correctAnswer = correctAnswer.trim(); correctAnswer =
	 * correctAnswer.replaceAll("", ""); correctAnswer =
	 * correctAnswer.replaceAll("&quot;",""); correctAnswer =
	 * correctAnswer.replace("[", ""); correctAnswer = correctAnswer.replace("]",
	 * "");
	 * 
	 * correctAnswer = StringEscapeUtils.unescapeJava(correctAnswer); //paperAnswer
	 * = StringEscapeUtils.unescapeJava(paperAnswer);
	 * if(!paperAnswer.contains("<img")){ paperAnswer = paperAnswer.replace("\"",
	 * ""); } paperAnswer = replaceDomain(paperAnswer,domain); //String questionType
	 * = wrongPaper.getQuestionType(); BeanUtils.copyProperties(wrongPaperJson,
	 * wrongPaper); //if("radio".equals(questionType) ||
	 * "trueOrFalse".equals(questionType) || "checkbox".equals(questionType)){
	 * String[] a = null; String [] b = null; if(paperAnswer.contains("(")){ a =
	 * paperAnswer.split("\","); }else{ a = paperAnswer.split(","); }
	 * 
	 * if(correctAnswer.contains("(")){ b = correctAnswer.split("\","); }else{ b =
	 * correctAnswer.split(","); }
	 * 
	 * for(int i=0;i<a.length;i++){ if(!a[i].contains("<img")){ //paperAnswer =
	 * paperAnswer.replace("\"", ""); a[i]= a[i].replace("\"", ""); } }
	 * 
	 * 
	 * for(int i=0;i<b.length;i++){ if(!b[i].contains("<img")){ //paperAnswer =
	 * paperAnswer.replace("\"", ""); b[i]= b[i].replace("\"", ""); } }
	 * 
	 * wrongPaperJson.setCorrectAnswer(b); wrongPaperJson.setPaperAnser(a); } //}
	 * wrongPaperJsonList.add(wrongPaperJson); } }catch (Exception e) {
	 * e.printStackTrace(); } } }
	 * 
	 * 
	 * 
	 * List<Map<String, Object>> taskInfoList = new ArrayList<Map<String,
	 * Object>>();
	 * 
	 *//** 时间格式化 */
	/*
	 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 *//** 上一个任务 */
	/*
	 * String preTime = "";
	 *//** 保存用户任务信息列表 */
	/*
	 * List<WrongPaperJson> userTaskList =new ArrayList<WrongPaperJson>();
	 *//** 保存任务时间和用户信息列表 */
	/*
	 * Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();
	 * 
	 * //获取域名 //String domain = fileService.getHttpPrefix() +"/" +
	 * fileService.getSpaceName();
	 * 
	 * if(wrongPaperJsonList != null && wrongPaperJsonList.size() > 0){
	 * 
	 * for (int i = 0; i < wrongPaperJsonList.size(); i++) { WrongPaperJson
	 * wrongPaperJson = wrongPaperJsonList.get(i); if(wrongPaperJson.getContent() !=
	 * null){ String subjectcode = wrongPaperJson.getSubjectCode();
	 * 
	 * Student student = studentService.findStudentByUserId(userId); if(student !=
	 * null){ Integer schoolId = student.getSchoolId(); Subject object =
	 * subjectService.findUnique(schoolId,subjectcode); if (object != null) {
	 * wrongPaperJson.setSubjectName(object.getName()); } }
	 * 
	 * if(i==0) {
	 *//** 初始上一个任务时间 */
	/*
	 * preTime = format.format(wrongPaperJson.getCreateDate()); }
	 * 
	 *//** 上一个任务和当前任务是否为同一天的任务 */
	/*
	 * if(preTime.equals(format.format(wrongPaperJson.getCreateDate()))) {
	 * userTaskList.add(wrongPaperJson); timeAndUserTaskMap.put("time",preTime);
	 * timeAndUserTaskMap.put("question",userTaskList);
	 *//** 如果为最后一个并且是同一天任务 */
	/*
	 * if(i==wrongPaperJsonList.size()-1) {
	 *//** 把时间保存到时间和任务的list中 */
	/*
	 * timeAndUserTaskMap.put("time",preTime);
	 * timeAndUserTaskMap.put("question",userTaskList);
	 *//** 添加到任务信息列表中 */
	/*
	 * taskInfoList.add(timeAndUserTaskMap); } //
	 * taskInfoList.add(timeAndUserTaskMap); } else {
	 *//** 如果不为同一天 */
	/*
	 * timeAndUserTaskMap.put("time",preTime);
	 * timeAndUserTaskMap.put("question",userTaskList);
	 *//** 把上一天的任务列表添加到任务信息列表中 */
	/*
	 * taskInfoList.add(timeAndUserTaskMap);
	 * 
	 *//** 初始化时间和任务map,用于存储当天的时间和任务 */
	/*
	 * timeAndUserTaskMap = new HashMap<String, Object>();
	 * 
	 *//** 当天的时间变成上一天时间 */
	/*
	 * preTime=format.format(wrongPaperJson.getCreateDate()); userTaskList = new
	 * ArrayList<WrongPaperJson>();
	 *//** 如果是最后一个任务 */

	/*
	 * if(i==wrongPaperJsonList.size()-1) {
	 *//** 最后一个任务为这天的任务总数 *//*
							 * timeAndUserTaskMap.put("time",preTime);
							 * timeAndUserTaskMap.put("question",userTaskList);
							 * taskInfoList.add(timeAndUserTaskMap); } } } } } return taskInfoList;
							 * 
							 * }
							 */

	/*
	 * (非 Javadoc) <p>Title: findUserWrongList</p> <p>Description: </p>
	 * 
	 * @param userId
	 * 
	 * @param subjectCode
	 * 
	 * @param page
	 * 
	 * @param order
	 * 
	 * @return
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#findUserWrongList(java.
	 * lang.Integer, java.lang.String, framework.generic.dao.Page,
	 * framework.generic.dao.Order)
	 */
	@Override
	public List<Map<String, Object>> findUserWrongList(Integer userId, String subjectCode, Page page, Order order) {
		//获取域名
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		List<WrongPaper> wrongPaperList = userWrongService.findUserWrongListByUserId(userId, subjectCode, page, order);
		if (wrongPaperList != null && wrongPaperList.size() > 0) {
			for (WrongPaper wrongPaper : wrongPaperList) {
				try {
					if (wrongPaper != null) {

						String complexTitle = wrongPaper.getComplexTitle();
						String content = wrongPaper.getContent();
						String explanation = wrongPaper.getExplanation();
						String groupId = wrongPaper.getGroupId();
						if (groupId != null && !"".equals(groupId)) {
							wrongPaper.setIsComplex(true);
						}
						if (explanation != null && !"".equals(explanation)) {

							wrongPaper.setExplanation(MqtPaperUtil.replaceDomain(explanation, domain));
						} else {
							wrongPaper.setExplanation("暂无解析!");
						}
						wrongPaper.setContent(MqtPaperUtil.replaceDomain(content, domain));
						wrongPaper.setComplexTitle(MqtPaperUtil.replaceDomain(complexTitle, domain));

						String paperAnswer = wrongPaper.getDbPaperAnser();
						String correctAnswer = wrongPaper.getDbCorrectAnswer();
						String userAnswer = wrongPaper.getDbUserAnswer();
						wrongPaper.setPaperAnser(MqtPaperUtil.StringToArray(paperAnswer, domain));
						wrongPaper.setCorrectAnswer(MqtPaperUtil.StringToArray(correctAnswer, domain));
						wrongPaper.setUserAnswer(MqtPaperUtil.StringToArray(userAnswer, domain));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		List<Map<String, Object>> taskInfoList = new ArrayList<Map<String, Object>>();

		/** 时间格式化 */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		/** 上一个任务 */
		String preTime = "";
		/** 保存用户任务信息列表 */
		List<WrongPaper> userTaskList = new ArrayList<WrongPaper>();
		/** 保存任务时间和用户信息列表 */
		Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();

		if (wrongPaperList != null && wrongPaperList.size() > 0) {

			for (int i = 0; i < wrongPaperList.size(); i++) {
				WrongPaper wrongPaper = wrongPaperList.get(i);
				if (wrongPaper.getContent() != null) {
					String subjectcode = wrongPaper.getSubjectCode();

					Student student = studentService.findStudentByUserId(userId);
					if (student != null) {
						Integer schoolId = student.getSchoolId();
						Subject object = subjectService.findUnique(schoolId, subjectcode);
						if (object != null) {
							wrongPaper.setSubjectName(object.getName());
						}
					}

					if (i == 0) {
						/** 初始上一个任务时间 */
						preTime = format.format(wrongPaper.getCreateDate());
					}

					/** 上一个任务和当前任务是否为同一天的任务 */
					if (preTime.equals(format.format(wrongPaper.getCreateDate()))) {
						userTaskList.add(wrongPaper);
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("question", userTaskList);
						/** 如果为最后一个并且是同一天任务 */
						if (i == wrongPaperList.size() - 1) {
							/** 把时间保存到时间和任务的list中 */
							timeAndUserTaskMap.put("time", preTime);
							timeAndUserTaskMap.put("question", userTaskList);
							/** 添加到任务信息列表中 */
							taskInfoList.add(timeAndUserTaskMap);
						}
						// taskInfoList.add(timeAndUserTaskMap);
					} else {
						/** 如果不为同一天 */
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("question", userTaskList);
						/** 把上一天的任务列表添加到任务信息列表中 */
						taskInfoList.add(timeAndUserTaskMap);

						/** 初始化时间和任务map,用于存储当天的时间和任务 */
						timeAndUserTaskMap = new HashMap<String, Object>();

						/** 当天的时间变成上一天时间 */
						preTime = format.format(wrongPaper.getCreateDate());
						userTaskList = new ArrayList<WrongPaper>();
						/** 如果是最后一个任务 */
						if (i == wrongPaperList.size() - 1) {
							/** 最后一个任务为这天的任务总数 */
							timeAndUserTaskMap.put("time", preTime);
							timeAndUserTaskMap.put("question", userTaskList);
							taskInfoList.add(timeAndUserTaskMap);
						}
					}
				}
			}
		}
		return taskInfoList;

	}

	/*
	 * (非 Javadoc) <p>Title: redo</p> <p>Description: </p>
	 * 
	 * @param appKey
	 * 
	 * @param userWrongId
	 * 
	 * @param answers
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#redo(java.lang.String,
	 * java.lang.Integer, java.lang.String)
	 */
	@Override
	public Boolean redo(Integer userWrongId, String answers) throws Exception {

		Boolean flag = false;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode answerList = mapper.readValue(answers, JsonNode.class);

		// 根据userWrongId查询现有记录
		UserWrong userWrong = userWrongService.findUserWrongById(userWrongId);

		for (JsonNode answer : answerList) {
			String lastAnswer = answer.path("answer").getTextValue();
			Integer isCorrect = null;
			if (answer.path("isCorrect") != null) {
				isCorrect = Integer.parseInt(answer.path("isCorrect").toString());
			}
			// 判断答案对错
			if (isCorrect == 1) { // 如正确则在正答数加1
				userWrong.setRightCount(((userWrong.getRightCount()) == null ? 0 : userWrong.getRightCount()) + 1);
				userWrong.setIsCorrect(true);
			} else {// 如正确则在错答数加1
				userWrong.setWrongCount(((userWrong.getWrongCount()) == null ? 0 : userWrong.getWrongCount()) + 1);
				userWrong.setIsCorrect(false);
			}

			userWrong.setLastTime(new Date());
			userWrong.setLastAnswer(lastAnswer);
			userWrongService.modify(userWrong);
			flag = true;
		}

		return flag;
	}

	/*
	 * (非 Javadoc) <p>Title: answerSituation</p> <p>Description: </p>
	 * 
	 * @param userId
	 * 
	 * @param paperUuId
	 * 
	 * @param ownerId
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#answerSituation(java.lang
	 * .Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<AnswerSituationResult> answerSituation(Integer userId, String paperUuId, Integer ownerId)
			throws Exception {
		Integer status = 0;
		List<AnswerSituationResult> answerSituationResultList = this.userQuestionService
				.findUserQuestionByUserIdAndPaperUuId(userId, paperUuId, ownerId);

		for (AnswerSituationResult answerSituationResult : answerSituationResultList) {

			if (!"[]".equals(answerSituationResult.getUserAnswer())) { // 判断是否有答案

				if (answerSituationResult.getIsCorrect()) { // 正确答案
					status = 1;
				}

			} else { // 未作答
				status = 99;
			}

			// 如有图片 转换图片http格式
			if (!"".equals(answerSituationResult.getFileId())) {
				FileResult fileResult = fileService.findFileByUUID(answerSituationResult.getFileId());
				if (fileResult != null) {
					answerSituationResult.setFileUrl(fileService.relativePath2HttpUrl(fileResult.getEntityFile()));
				}
			}

			answerSituationResult.setStatus(status);

		}

		return answerSituationResultList;
	}

	/*
	 * (非 Javadoc) <p>Title: wrongQuestioDelete</p> <p>Description: </p>
	 * 
	 * @param userWrongId
	 * 
	 * @param answers
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#wrongQuestioDelete(java.
	 * lang.Integer, java.lang.String)
	 */
	@Override
	public Boolean wrongQuestioDelete(Integer userWrongId) throws Exception {
		boolean flag = false;
		UserWrong userWrong = userWrongService.findUserWrongById(userWrongId);
		if (userWrong != null) {
			userWrong.setIsDeleted(true);
			userWrongService.modify(userWrong);
			flag = true;
		}
		return flag;
	}

	/*
	 * (非 Javadoc) <p>Title: deletePaperInfo</p> <p>Description: </p>
	 * 
	 * @param ownerId
	 * 
	 * @return
	 * 
	 * @see
	 * platform.education.paper.service.PaperHandleService#deletePaperInfo(java.lang
	 * .Integer)
	 */
	@Override
	public Boolean deletePaperInfo(Integer ownerId, Integer type) {

		// 修改标志
		Boolean flag = false;
		try {

			// 查询用户作答
			UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
			userQuestionCondition.setOwnerId(ownerId);
			userQuestionCondition.setType(type);
			List<UserQuestion> userQuestionList = this.userQuestionService
					.findUserQuestionByCondition(userQuestionCondition);

			// 查询用户总得分
			/*UserPaperCondition userPaperCondition = new UserPaperCondition();
			userPaperCondition.setOwnerId(ownerId);
			userPaperCondition.setType(type);

			List<UserPaper> userPaperList = this.userPaperService
					.findUserPaperAnswerCountByCondition(userPaperCondition);*/

			// 删除pa_user_question表记录
			for (UserQuestion userQuestion : userQuestionList) {
				UserWrongCondition userWrongCondition = new UserWrongCondition();
				userWrongCondition.setQuestionUuid(userQuestion.getQuestionUuid());
				userWrongCondition.setPaperUuid(userQuestion.getPaperUuid());
				List<UserWrong> userWrongList = this.userWrongService.findUserWrongByCondition(userWrongCondition);
				if (userWrongList != null && userWrongList.size() > 0) {
					UserWrong userWrong = userWrongList.get(0);
					this.userWrongService.remove(userWrong);
				}

//				this.userQuestionService.remove(userQuestion);
			}
			userQuestionService.deleteByOwnerIdAndType(ownerId, type);
			userPaperService.deleteByOwnerIdAndType(ownerId, type);

//			// 删除pa_user_paper表记录
//			for (UserPaper userPaper : userPaperList) {
//				this.userPaperService.remove(userPaper);
//			}

			flag = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return flag;
	}
}