package platform.education.paper.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.paper.constants.PaperContans;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.model.PaPaperTree;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaQuestionCatalog;
import platform.education.paper.model.PaQuestionKnoledge;
import platform.education.paper.model.PaUserPaper;
import platform.education.paper.model.PaUserQuestion;
import platform.education.paper.model.PaperResult;
import platform.education.paper.service.PaPaperCatalogService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaPaperTreeService;
import platform.education.paper.service.PaQuestionCatalogService;
import platform.education.paper.service.PaQuestionKnoledgeService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaPaperHandleService;
import platform.education.paper.service.PaUserFileService;
import platform.education.paper.service.PaUserPaperService;
import platform.education.paper.service.PaUserQuestionService;
import platform.education.paper.util.AESHelper;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.util.PaperFileUtil;
import platform.education.paper.util.ReadFileUtil;
import platform.education.paper.util.ZipUtil;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaUserFileVo;
import platform.education.paper.vo.PaUserPaperCondition;
import platform.education.paper.vo.PaUserQuestionCondition;
import platform.education.user.service.ProfileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

/**
 * @ClassName: PaperHandleServiceImpl
 * @Description: 试题处理实现类
 * @author pantq
 * @date 2017年2月23日 下午3:11:36
 * 
 */
public class PaPaperHandleServiceImpl implements PaPaperHandleService {

	@Resource
	private FileService fileService;
	
	@Resource
	private PaQuestionService paQuestionService;
	
	@Resource
	private PaPaperTreeService paPaperTreeService;
	
	@Resource
	private PaPaperService paPaperService;
	
	@Resource
	private PaPaperCatalogService paPaperCatalogService;
	
	@Resource
	private PaQuestionKnoledgeService paQuestionKnoledgeService;
	
   @Resource
   private PaUserQuestionService  paUserQuestionService;
	
	@Resource
	private PaQuestionCatalogService paQuestionCatalogService;
	@Resource 
	private PaUserFileService paUserFileService;
	@Resource
	private PaUserPaperService paUserPaperService;
	@Resource
	private TeamStudentService teamStudentService;
	@Resource
	private ProfileService  profileService;
	@Resource 
	private TeamService teamService;
	@Resource 
	private GradeService gradeService;
	

	
	private Date now;
	

	@Override
	public PaperResult paperSplit(Integer userId, String filePath, String targerPath, String fileUuid,
			String schoolUuid, Integer relId, Integer type, Integer isCover) 
			throws Exception {
		PaperResult paperResult = new PaperResult();

		/**1.获取压缩包描述内容*/
		String comment = ZipUtil.getComment(filePath);

		/**2.根据描述内容计算真实密码*/
		String password = AESHelper.decrypt(comment);

		/**3.拿到真实密码解压*/
		Boolean flag = ZipUtil.explodeZip(filePath, targerPath, password);
		
		/**4.解压成功读取文件解析题库*/
		if (flag) {
			/**5.解析document.json文件 进行试题拆分 (文件名和后缀必须要保持不变)*/
			String path = targerPath + "document.json";
			/**6. 读取document.json文件内容 入库*/
			String content = ReadFileUtil.readString(path);

			paperResult = addPaperAndQuestion(userId, content, targerPath, fileUuid, schoolUuid, relId, type, isCover);

			/**删除临时文件夹*/
			PaperFileUtil.deleteDir(new File(targerPath));
			return paperResult;
		}
		return null;
	}

	/**
	 * 拆题业务逻辑处理(优化后)
	 * 
	 * @author pantq
	 * @param isCover 
	 * @return
	 */
	@Override
	public PaperResult addPaperAndQuestion(Integer userId, String content, String targerPath,
			String fileUuid, String schoolUuid, Integer relId, Integer type, Integer isCover) throws Exception{
		PaperResult result = new PaperResult();
		now = new Date();
			/**pc端传入的json结构*/
			JSONObject pcData = JSONObject.fromObject(content);
			JSONObject pcPaper = pcData.getJSONObject("paper");
			/**试卷的试题结构*/
			JSONObject body = pcData.getJSONObject("body");
			/**替换图片*/
			String paperData = attrImageData(pcData, targerPath);
			
			JSONArray groups = body.getJSONArray("group");

			if (pcPaper != null) {
				/**获取试卷信息*/
				PaPaper paPaper = getPaPaper(pcPaper, paperData, userId,
						fileUuid, schoolUuid, relId, type);
				
				PaPaper paper = paPaperService.findPaPaperByUUid(paPaper.getPaperUuid());
				if(paper!=null) {
					if(paper.getUsedCount()>0 && isCover==0) {
						result.setStatus(1);
						result.setMsg("试卷已经被使用");
						return result;
					} 
					paPaperService.removeAllPaperInfo(paper);
				}
				/**添加试卷*/
				paPaper = paPaperService.add(paPaper);
				
				Integer paperId = paPaper.getId();
				
				/**试卷关联的目录*/
				JSONArray catalogs = pcPaper.getJSONArray("catalogs");
				String[] catalogCodes = getCatalog(catalogs);
				/**试卷关联的科目*/
				JSONArray subjects = pcPaper.getJSONArray("subjects");
				JSONObject sections = pcPaper.getJSONObject("sections");
				
				/**把试卷和目录、科目关联起来*/
				addPaperRelation(paperId, catalogCodes, subjects, sections.getString("code"));
				/** 试卷memo*/
				String memo = pcPaper.getString("memo");
				/**这里添加paper最顶级的结构*/
				Integer parentId = addParentPaperTree(paPaper,memo);
				
				/**这里添加每一个题组*/
				for (int i = 0; i < groups.size(); i++) {
					JSONObject group = groups.getJSONObject(i);
					/**添加题组*/
					Integer paperTreeId = addPaperTree(group, parentId, paperId);
					
					/**试题结构*/
					JSONArray questions = group.getJSONArray("questions");
					/**添加试卷*/
					addQuestions(questions, paperTreeId, 0, paperId, userId, 0, catalogCodes,
							subjects, sections.getString("code"), type, schoolUuid);
				}
			}
			
		result.setStatus(0);
		return result;
	}

	/**
	 * 添加试卷结构记录
	 */
	@Override
	public Integer addPaperTree(JSONObject group, Integer parentId, Integer paperId) {
		PaPaperTree paperTree = new PaPaperTree();
		Integer pos = Integer.parseInt(group.getString("pos"));
		paperTree.setCreateDate(now);
		paperTree.setIsDeleted(0);
		paperTree.setMemo(group.getString("memo"));
		paperTree.setModifyDate(new Date());
		paperTree.setNodeOrder(pos);
		paperTree.setNodeType(1);
		paperTree.setPaperId(paperId);
		paperTree.setParentId(parentId);
		paperTree.setPos(pos);
		paperTree.setScore((float) group.getDouble("score"));
		paperTree.setTitle(group.getString("name"));
		paperTree = paPaperTreeService.add(paperTree);
		return paperTree.getId();
	}

	/**
	 * 添加最顶级的paper结构
	 */
	@Override
	public Integer addParentPaperTree(PaPaper paPaper,String memo) {
		PaPaperTree paperTree = new PaPaperTree();
		Date nowDate = new Date();
		paperTree.setPaperId(paPaper.getId());
		paperTree.setMemo(memo);
		paperTree.setParentId(0);
		paperTree.setCreateDate(now);
		paperTree.setIsDeleted(0);
		paperTree.setModifyDate(nowDate);
		paperTree.setNodeOrder(0);
		paperTree.setNodeType(0);
		paperTree.setScore(paPaper.getScore());
		paperTree.setTitle(paPaper.getTitle());
		paperTree = paPaperTreeService.add(paperTree);
		return paperTree.getId();
	}

	/**
	 * 添加paper和科目、目录的信息
	 * @param code 
	 */
	@Override
	public void addPaperRelation(Integer paperId, String[] catalogCodes, JSONArray subjects, String stageCode) {
		for (int i = 0; i < subjects.size(); i++) {
			for (int j = 0; j < catalogCodes.length; j++) {
				if(i==j) {
					JSONObject subject = subjects.getJSONObject(i);
					String subjectCode = subject.getString("code");
					String catalogCode = catalogCodes[j];
					/**这里把paper和subject和catalog关联起来*/
					PaPaperCatalog paPaperCatalog = new PaPaperCatalog();
					paPaperCatalog.setPaperId(paperId);
					paPaperCatalog.setSubjectCode(subjectCode);
					paPaperCatalog.setCatalogCode(catalogCode);
					paPaperCatalog.setStageCode(stageCode);
					paPaperCatalogService.add(paPaperCatalog);
				}
			}
		}
	}
	
	/**
	 * 添加question和科目、目录的信息
	 * @param stageCode 
	 * @param code 
	 */
	private void addQuestionRelation(Integer questionId, String[] catalogCodes, JSONArray subjects, String stageCode, String code) {
		for (int i = 0; i < subjects.size(); i++) {
			JSONObject subject = subjects.getJSONObject(i);
			String subjectCode = subject.getString("code");
			if(subjectCode!=null && code!=null && subjectCode.equals(code)) {
				String catalogCode = catalogCodes[i];
				/**这里把paper和subject和catalog关联起来*/
				PaQuestionCatalog paQuestionCatalog = new PaQuestionCatalog();
				paQuestionCatalog.setQuestionId(questionId);
				paQuestionCatalog.setSubjectCode(subjectCode);
				paQuestionCatalog.setCatalogCode(catalogCode);
				paQuestionCatalog.setStageCode(stageCode);
				paQuestionCatalogService.add(paQuestionCatalog);
			}
		}
	}

	/**
	 * 获取试卷的信息
	 * @param relId 
	 * @param schoolUuid 
	 * @param type 
	 * @param fileUuid2 
	 */
	private PaPaper getPaPaper(JSONObject pcPaper, String paperData, Integer userId,
			String fileUuid, String schoolUuid, Integer relId, Integer type) {
		String paperUuid = pcPaper.getString("id");
		String title = pcPaper.getString("title");
		Integer questioncount = pcPaper.getInt("questionCount");
		double score = pcPaper.getDouble("score");
		
		
		PaPaper paPaper = new PaPaper();
		if(pcPaper.containsKey("create_date")) {
			Long time = pcPaper.getLong("create_date");
			paPaper.setCreateDate(new Date(time));
		} else {
			paPaper.setCreateDate(now);
		}
		paPaper.setExtraData(null);
		paPaper.setFavCount(0);
		paPaper.setUsedCount(0);
		paPaper.setFinishedCount(0);
		paPaper.setIsDeleted(false);
		paPaper.setModifyDate(now);
		paPaper.setPaperData(paperData);
		paPaper.setPaperType(1);
		paPaper.setPaperUuid(paperUuid);
		paPaper.setQuestionCount(questioncount);
		paPaper.setOwnerMode(type);
		paPaper.setOwnerUid(schoolUuid);
		paPaper.setResourceLibId(relId);
		paPaper.setScore((float) score);
		paPaper.setTitle(title);
		paPaper.setUserId(userId);
		paPaper.setXepFileId(fileUuid);
		return paPaper;
	}

	/**
	 * 添加题目
	 * @param targerPath 
	 * @param subjects 
	 * @param catalogCodes 
	 * @param stageCode 
	 * @param schoolUuid 
	 * @param type 
	 */
	@Override
	public void addQuestions(JSONArray questions, Integer paperTreeParentId, Integer questionParentId, Integer paperId,
			Integer userId, Integer level, String[] catalogCodes, JSONArray subjects, String stageCode, Integer type, String schoolUuid) {
		for (int i = 0; i < questions.size(); i++) {
			JSONObject jsonObject = questions.getJSONObject(i);
			
			String code = "";
			JSONObject questionSubject = jsonObject.getJSONObject("subject");
			if(questionSubject!=null) {
				code = questionSubject.getString("code");
			}
			
			PaQuestion paQuestion = getPaQuestion(jsonObject, type, schoolUuid);
			paQuestion.setParentId(questionParentId);
			paQuestion.setUserId(userId);
			paQuestion =  paQuestionService.add(paQuestion);
			Integer paQuestionId = paQuestion.getId();
			
			addQuestionRelation(paQuestionId, catalogCodes, subjects, stageCode, code);
			
			PaPaperTree paperTree = getPaperTree(jsonObject);
			paperTree.setQuestionId(paQuestion.getId());
			paperTree.setParentId(paperTreeParentId);
			paperTree.setPaperId(paperId);
			paperTree.setNodeOrder(i);
			if(level+2==2) {
				paperTree.setNodeType(2);
			} else {
				paperTree.setNodeType(3);
			}
			paPaperTreeService.add(paperTree);
			
			/**知识点*/
			JSONArray knowledge = jsonObject.getJSONArray("knowledges");
			/**科目*/
			JSONObject subject = jsonObject.getJSONObject("subject");
			String subjectCode = subject.getString("code");
			
			if(knowledge.size()>0 && subjectCode!=null && !"".equals(subjectCode)) {
				/**题目关联知识点和科目*/
				addKnowledgeRelation(paQuestionId, knowledge, subjectCode);
			}
			
			/**排除没有小题的情况, 防止pc端传入的小题是null，而不是[], 导致下面解析失败报错*/
			Object obj = jsonObject.get("questions");
			if(obj==null || "null".equals(obj.toString())) {
				continue;
			}
			
			/**小题目*/
			JSONArray childrenQuestion = jsonObject.getJSONArray("questions");
			if(childrenQuestion!=null && childrenQuestion.size()>0) {
				/**递归添加小题目*/
				addQuestions(childrenQuestion, paperTree.getId(), paQuestionId, paperId, userId, ++level,
						catalogCodes, subjects,stageCode, type, schoolUuid);
				level = 0;
			}
		}
	}

	@Override
	public void addKnowledgeRelation(Integer paQuestionId, JSONArray knowledges, String subjectCode) {
		for (Object object : knowledges) {
			JSONObject knowledge = (JSONObject) object;
			String knowledgeCode = knowledge.getString("code");
			PaQuestionKnoledge paQuestionKnoledge = new PaQuestionKnoledge();
			paQuestionKnoledge.setKnowledgeCode(knowledgeCode);
			paQuestionKnoledge.setQuestionId(paQuestionId);
			paQuestionKnoledge.setSubjectCode(subjectCode);
			paQuestionKnoledgeService.add(paQuestionKnoledge);
		}
	}

	/**
	 *获取题目信息 
	 * @param schoolUuid 
	 * @param type 
	 * @param targerPath 
	 */
	private PaQuestion getPaQuestion(JSONObject questionJSON, Integer type, String schoolUuid) {
		PaQuestion paQuestion = new PaQuestion();
		JSONArray answer = questionJSON.getJSONArray("answer");
		JSONArray correctAnswer = questionJSON.getJSONArray("correctAnswer");
		Object obj = questionJSON.get("isSubjective");
		if(!"null".equals(obj.toString())) {
			paQuestion.setQuestionProperty(questionJSON.getInt("isSubjective"));
		}
		String content = questionJSON.getString("content");
		paQuestion.setContent(content);
		String explain = questionJSON.getString("explanation");
		paQuestion.setExplanation(explain);
		paQuestion.setCorrectAnswer(correctAnswer.toString());
		paQuestion.setAnswer(answer.toString());
		paQuestion.setAnswerCount(0);
		paQuestion.setAverageTime(null);
		paQuestion.setCognition(questionJSON.getString("cognition"));
		paQuestion.setCreateDate(now);
		paQuestion.setDifficulity((float) questionJSON.getDouble("difficulty"));
		paQuestion.setExtraData(null);
		paQuestion.setIsDeleted(0);
		paQuestion.setModifyDate(new Date());
		paQuestion.setRightAnswerCount(0);
		paQuestion.setSourceType("2");
		paQuestion.setTotalTime(null);
		paQuestion.setTotalTimeCount(0);
		paQuestion.setUsedCount(0);
		paQuestion.setFavCount(0);
		paQuestion.setUuid(questionJSON.getString("id"));
		paQuestion.setOwnerMode(type);
		paQuestion.setOwnerUid(schoolUuid);
		paQuestion.setQuestionType(questionJSON.getString("type"));
		
		return paQuestion;
	}

	private String[] getStringArray(JSONArray array) {
		String[] strArray = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			strArray[i] = array.getString(i);
		}
		return strArray;
	}
	
	/**
	 *获取试卷结构 
	 */
	private PaPaperTree getPaperTree(JSONObject paperTreeJSON) {
		PaPaperTree paperTree = new PaPaperTree();
		paperTree.setCreateDate(now);
		paperTree.setIsDeleted(0);
		paperTree.setMemo(paperTreeJSON.getString("memo"));
		paperTree.setNum(paperTreeJSON.getString("num"));
		paperTree.setPos(paperTreeJSON.getInt("pos"));
		paperTree.setQuestionType(paperTreeJSON.getString("type"));
		paperTree.setScore((float) paperTreeJSON.getDouble("score"));
		paperTree.setTitle("");
		paperTree.setModifyDate(new Date());
		return paperTree;
	}

	/**
	 * 获取目录的code值
	 */
	private String[] getCatalog(JSONArray catalogs) {
		String[] codes = new String[catalogs.size()];
		
		for (int i = 0; i < catalogs.size(); i++) {
			String code = null;
			JSONObject catalog = catalogs.getJSONObject(i);
			JSONObject bookItem = catalog.getJSONObject("bookItem");
			code = bookItem.getString("code");
			if(!isNull(code)) {
				codes[i] = code;
				continue;
			}
			JSONObject booksSction = catalog.getJSONObject("bookSction");
			code = booksSction.getString("code");
			if(!isNull(code)) {
				codes[i] =  code;
				continue;
			}
			JSONObject bookUnit = catalog.getJSONObject("bookUnit");
			code = bookUnit.getString("code");
			if(!isNull(code)) {
				codes[i] =  code;
				continue;
			}
			JSONObject volumn = catalog.getJSONObject("volumn");
			code = volumn.getString("code");
			if(!isNull(code)) {
				codes[i] =  code;
				continue;
			}
		}
		return codes;
	}
	
	/**判断code值是否非空*/
	private boolean isNull(String code) {
		if(code != null && !"".equals(code) && !"null".equals(code)) {
			return false;
		}
		return true;
	}
	
	private String attrImageData(JSONObject pcData, String targerPath) throws Exception {
		
		JSONObject body = pcData.getJSONObject("body");
		JSONArray groups = body.getJSONArray("group");
		for (int i = 0; i < groups.size(); i++) {
			JSONObject group =  groups.getJSONObject(i);
			JSONArray questions = group.getJSONArray("questions");
			handlerQuestions(questions, targerPath);
		}
		return pcData.toString();
	}

	private void handlerQuestions(JSONArray questions, String targerPath) throws Exception {
		for (int i = 0; i < questions.size(); i++) {
			JSONObject question =  questions.getJSONObject(i);
			JSONArray children = question.getJSONArray("questions");
			
			String content = question.getString("content");
			String explain = question.getString("explanation");
			JSONArray correctAnswer = question.getJSONArray("correctAnswer");
			JSONArray answer = question.getJSONArray("answer");
			question.put("content", attrImage(content, targerPath));
			question.put("explanation", attrImage(explain, targerPath));
			question.put("correctAnswer", isJson(getStringArray(correctAnswer), targerPath));
			question.put("answer", isJson(getStringArray(answer), targerPath));
			
			if(children.size()>0) {
				handlerQuestions(children, targerPath);
			}
		}
	}

	private String isJson(String[] source, String targerPath) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
			List<String> newList = new ArrayList<String>();
			if (source != null && source.length > 0) {
				for (String s : source) {
					newList.add(attrImage(s, targerPath));
				}
			}
			result = mapper.writeValueAsString(newList);

		return result;
	}


	/**
	 * 上传图片处理
	 */
	private String attrImage(String source, String targerPath) throws Exception {
		if(targerPath==null || "".equals(targerPath)) {
			return source;
		}
		if (source == null || source.trim().length() == 0)
			return "";

		StringBuilder sb = new StringBuilder();
		String st = "src=\"";
		String et = "\"";

		int start = source.indexOf(st);

		if (start < 0)
			return source;

		String[] array = source.split(st);
		if (array.length < 2)
			return source;

		sb.append(array[0]);
		for (int index = 1; index < array.length; index++) {
			if (array[index].trim().length() == 0)
				continue;

			int next = array[index].indexOf(et);

			if (next < 0)
				continue;

			String srcText = array[index].substring(0, next);
			File file = new File(targerPath + File.separator + "images" + File.separator + srcText);
			FileResult fileResult = fileService.upload(file, "", "paper");
			if (fileResult != null) {
				String fileSrc = "";
				fileSrc = fileResult.getEntityFile().getRelativePath();
				/**原图片路径*/
				if (srcText.trim().length() == 0)
					continue;
				sb.append(st);
				sb.append(fileSrc + et + array[index].substring(next + 1, array[index].length()));
			}

		}
		return sb.toString();
	}

	@Override
	public com.alibaba.fastjson.JSONObject findPaperJsonByPaperId(Integer paperId,Integer taskId,Integer userId,Integer uintId) {
		String jsonString="";
		com.alibaba.fastjson.JSONObject  json = null;
		com.alibaba.fastjson.JSONObject jSONObject = null;
		JsonConfig cfg=new JsonConfig();
//		cfg.setJsonPropertyFilter(new IgnoreFieldProcessorImpl(true,new String[]{"correctAnswer"}));
		cfg.setExcludes(new String[]{"correctAnswer"});
		 try {
		List<PaQuestionVo>pqlist=paQuestionService.findPaQuestionVoByPaperId(paperId,1,null); 
		if(pqlist==null){
			return  jSONObject;
		}
		Map<String,Object> questionMap=new HashMap<String, Object>();
		for(PaQuestionVo vo:pqlist){
			questionMap.put(vo.getUuid(), vo);
		}
		PaPaper paper=paPaperService.findPaPaperById(paperId);
		if(paper==null){
			return jSONObject;
		}
		Map<String,Object> imgListMap=new HashMap<String, Object>();
		Map<String,String> uqtoqMap=new HashMap<String, String>();
			PaUserQuestionCondition  condition=new PaUserQuestionCondition();
			condition.setUserId(userId);
			condition.setOwnerId(taskId);
			condition.setObjectId(uintId);
			if(uintId==null){
				condition.setType(PaperContans.PAPERTYPE);
			}else{
				condition.setType(PaperContans.LEARNNINGPLAYTYPE);
			}
			List<PaUserQuestion> uqlist=new ArrayList<PaUserQuestion>();
			uqlist=paUserQuestionService.findPaUserQuestionByCondition(condition);
			if(uqlist!=null&&uqlist.size()>0){
				int i=0;
				String[] uqUuids=new String[uqlist.size()];
				for(PaUserQuestion uq:uqlist){
					uqUuids[i]=uq.getUuid();
					i++;
					uqtoqMap.put(uq.getQuestionUuid(), uq.getUuid());
				}
			   List<PaUserFileVo> pufVo=paUserFileService.findPaUserFilesByUuids(uqUuids);
			   if(pufVo!=null&&pufVo.size()>0){
				   for(PaUserFileVo vo:pufVo){
					   List<PaUserFileVo> pfList=new ArrayList<PaUserFileVo>();
					   pfList=(List<PaUserFileVo>) imgListMap.get(vo.getRealUserQuestionUuid());
					   if(pfList==null){
						   pfList=new ArrayList<PaUserFileVo>();
					   }
					   pfList.add(vo);
					   imgListMap.put(vo.getRealUserQuestionUuid(), pfList);
				   }
			   }
			}
//		 jsonString=paPaperService.findPaperJsonByDb(paperId);
		 jsonString=paper.getPaperData();
//		 System.out.println(jsonString);
		 String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		 jsonString = jsonString.replace("src=\\\"", "src=\\\""+domain+"");
//		 json = JSONObject.fromObject(jsonString,cfg); 
		   json=com.alibaba.fastjson.JSONObject.parseObject(jsonString);
	      com.alibaba.fastjson.JSONObject  body =  json.getJSONObject("body");
	      com.alibaba.fastjson.JSONArray objlist= body.getJSONArray("group");
		 for(int i=0;i<objlist.size();i++){
			  com.alibaba.fastjson.JSONObject obj1= objlist.getJSONObject(i);
			  com.alibaba.fastjson.JSONArray objlist1=obj1.getJSONArray("questions");
			 for(int j=0;j<objlist1.size();j++){
				  com.alibaba.fastjson.JSONObject obj2=objlist1.getJSONObject(j);
				 String questionUuid=obj2.getString("id");
				 PaQuestionVo vo=(PaQuestionVo) questionMap.get(questionUuid);
				 obj2.put("averageTime", vo.getAverageTime()==null?0:vo.getAverageTime());
				 if(vo.getAnswerCount()==null||vo.getAnswerCount()==0||vo.getRightAnswerCount()==null||vo.getRightAnswerCount()==0){
					 obj2.put("rightRate", 0.0f);
				 }else{
					 BigDecimal big1=new BigDecimal(vo.getAnswerCount());
					 BigDecimal big2=new BigDecimal(vo.getRightAnswerCount());
					 float a=big2.divide(big1,2,BigDecimal.ROUND_HALF_DOWN).floatValue();
					 obj2.put("rightRate", a);
				 }
				 obj2.put("difficulty", vo.getDifficulity());
				if(imgListMap.size()>0){
						String uqUuid=uqtoqMap.get(questionUuid);
						List<String> img=new ArrayList<String>();
						List<PaUserFileVo>volist=(List<PaUserFileVo>) imgListMap.get(uqUuid);
						if(volist!=null&&volist.size()>0){
							for(PaUserFileVo v:volist){
								String url=fileService.relativePath2HttpUrlByUUID(v.getSourceFileUuid());
								img.add(url);
							}
						}
						obj2.put("imgList", img);
				}else{
					obj2.put("imgList", new  ArrayList<String>());
				}
				com.alibaba.fastjson.JSONArray objlist2=obj2.getJSONArray("questions");
				for(int z=0;z<objlist2.size();z++){
					  com.alibaba.fastjson.JSONObject obj3=objlist2.getJSONObject(z);
					 String questionUuid2=obj3.getString("id");
					 PaQuestionVo vo2=(PaQuestionVo) questionMap.get(questionUuid2);
					 obj3.put("averageTime", vo2.getAverageTime()==null?0:vo2.getAverageTime());
					 if(vo2.getAnswerCount()==null||vo2.getAnswerCount()==0||vo2.getRightAnswerCount()==null||vo2.getRightAnswerCount()==0){
						 obj3.put("rightRate", 0.0f);
					 }else{
						 BigDecimal big1=new BigDecimal(vo2.getAnswerCount());
						 BigDecimal big2=new BigDecimal(vo2.getRightAnswerCount());
						 float a=big2.divide(big1,2,BigDecimal.ROUND_HALF_DOWN).floatValue();
						 obj3.put("rightRate", a);
					 }
					 obj3.put("difficulty", vo2.getDifficulity());
					 if(imgListMap.size()>0){
							String uqUuid=uqtoqMap.get(questionUuid2);
							List<String> img=new ArrayList<String>();
							List<PaUserFileVo>volist=(List<PaUserFileVo>) imgListMap.get(uqUuid);
							if(volist!=null&&volist.size()>0){
								for(PaUserFileVo v:volist){
									String url=fileService.relativePath2HttpUrlByUUID(v.getSourceFileUuid());
									img.add(url);
								}
							}
							obj3.put("imgList", img);
					}else{
						obj3.put("imgList", new  ArrayList<String>());
					}
				}
			 }
		 }
		// ObjectMapper objectMapper=new ObjectMapper();
			//jsonString=objectMapper.writeValueAsString(json);
		//	System.out.println(jsonString);
//			jsonString = StringEscapeUtils.unescapeJava(jsonString);
//			System.out.println(jsonString);
		}catch (Exception e){
			e.printStackTrace();
			return jSONObject;
		}
		return json;
	}

	@Override
	public com.alibaba.fastjson.JSONObject findFullPaperJsonByPaperId(
			Integer paperId, Integer taskId, Integer userId, Integer uintId) {
		String jsonString="";
		com.alibaba.fastjson.JSONObject  json = null;
		com.alibaba.fastjson.JSONObject jSONObject = null;
		JsonConfig cfg=new JsonConfig();
//		cfg.setJsonPropertyFilter(new IgnoreFieldProcessorImpl(true,new String[]{"correctAnswer"}));
		cfg.setExcludes(new String[]{"correctAnswer"});
		 try {
		String teamName="";
		String studentName="";
		Double studentScore =0.0d;
		String icon="http://cdn.test.studyo.cn:8082/develop/test/2016-11-2/18b971bf8bb9f27bbdec77ddcf9e3286.jpg";
		
		PaUserPaperCondition pupCondition=new PaUserPaperCondition();
		pupCondition.setOwnerId(taskId);
		pupCondition.setObjectId(uintId);
		pupCondition.setUserId(userId);
		List<PaUserPaper> puList=paUserPaperService.findPaUserPaperByCondition(pupCondition);
		if(puList.size()>0){
			PaUserPaper pup=puList.get(0);
			studentScore=pup.getScore();
			TeamStudentCondition tsCondition=new TeamStudentCondition();
			tsCondition.setTeamId(pup.getTeamId());
			tsCondition.setUserId(userId);
			tsCondition.setIsDelete(false);
			List<TeamStudent> tsList=teamStudentService.findTeamStudentByCondition(tsCondition, null, null);
			if(tsList.size()>0){
				TeamStudent ts=tsList.get(0);
				studentName=ts.getName();
			}
			Team team=teamService.findTeamById(pup.getTeamId());
			if(team!=null){
				Grade g=gradeService.findGradeById(team.getGradeId());
				teamName=g.getName()+"("+team.getTeamNumber()+")班";
			}
			
		}
	
		Map<String,PaUserQuestion> puMap=new HashMap<String, PaUserQuestion>();
		List<PaQuestionVo>pqlist=paQuestionService.findPaQuestionVoByPaperId(paperId,1,null); 
		if(pqlist==null){
			return  jSONObject;
		}
		Map<String,Object> questionMap=new HashMap<String, Object>();
		for(PaQuestionVo vo:pqlist){
			questionMap.put(vo.getUuid(), vo);
		}
		PaPaper paper=paPaperService.findPaPaperById(paperId);
		if(paper==null){
			return jSONObject;
		}
		Map<String,Object> imgListMap=new HashMap<String, Object>();
		Map<String,String> uqtoqMap=new HashMap<String, String>();
			PaUserQuestionCondition  condition=new PaUserQuestionCondition();
			condition.setUserId(userId);
			condition.setOwnerId(taskId);
			condition.setObjectId(uintId);
			if(uintId==null){
				condition.setType(PaperContans.PAPERTYPE);
			}else{
				condition.setType(PaperContans.LEARNNINGPLAYTYPE);
			}
			List<PaUserQuestion> uqlist=new ArrayList<PaUserQuestion>();
			uqlist=paUserQuestionService.findPaUserQuestionByCondition(condition);
			Integer sumTime=0;
			for(PaUserQuestion pq:uqlist){
				puMap.put(pq.getQuestionUuid(), pq);
				sumTime+=pq.getAnswerTime();
			}
			if(uqlist!=null&&uqlist.size()>0){
				int i=0;
				String[] uqUuids=new String[uqlist.size()];
				for(PaUserQuestion uq:uqlist){
					uqUuids[i]=uq.getUuid();
					i++;
					uqtoqMap.put(uq.getQuestionUuid(), uq.getUuid());
				}
			   List<PaUserFileVo> pufVo=paUserFileService.findPaUserFilesByUuids(uqUuids);
			   if(pufVo!=null&&pufVo.size()>0){
				   for(PaUserFileVo vo:pufVo){
					   List<PaUserFileVo> pfList=new ArrayList<PaUserFileVo>();
					   pfList=(List<PaUserFileVo>) imgListMap.get(vo.getRealUserQuestionUuid());
					   if(pfList==null){
						   pfList=new ArrayList<PaUserFileVo>();
					   }
					   pfList.add(vo);
					   imgListMap.put(vo.getRealUserQuestionUuid(), pfList);
				   }
			   }
			}
//		 jsonString=paPaperService.findPaperJsonByDb(paperId);
		 jsonString=paper.getPaperData(); 
//		 System.out.println(jsonString);
		 String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		 jsonString = jsonString.replace("src=\\\"", "src=\\\""+domain+"");
//		 json = JSONObject.fromObject(jsonString,cfg); 
		   json=com.alibaba.fastjson.JSONObject.parseObject(jsonString);
		  com.alibaba.fastjson.JSONObject studentInfo=new com.alibaba.fastjson.JSONObject();
		  studentInfo.put("teamName", teamName);
		  studentInfo.put("studentName", studentName);
		  studentInfo.put("studentScore", studentScore);
//		  studentInfo.put("userId", userId);
		  studentInfo.put("icon", icon);
		  studentInfo.put("sumTime", sumTime);
		  json.put("studentInfo", studentInfo);
	      com.alibaba.fastjson.JSONObject  body =  json.getJSONObject("body");
	      com.alibaba.fastjson.JSONArray objlist= body.getJSONArray("group");
		 for(int i=0;i<objlist.size();i++){
			  com.alibaba.fastjson.JSONObject obj1= objlist.getJSONObject(i);
			  com.alibaba.fastjson.JSONArray objlist1=obj1.getJSONArray("questions");
			 for(int j=0;j<objlist1.size();j++){
				  com.alibaba.fastjson.JSONObject obj2=objlist1.getJSONObject(j);
				 String questionUuid=obj2.getString("id");
				 float a=0.0f;
				 PaQuestionVo vo=(PaQuestionVo) questionMap.get(questionUuid);
				 if(vo.getAnswerCount()==null||vo.getAnswerCount()==0||vo.getRightAnswerCount()==null||vo.getRightAnswerCount()==0){
					
				 }else{
					 BigDecimal big1=new BigDecimal(vo.getAnswerCount());
					 BigDecimal big2=new BigDecimal(vo.getRightAnswerCount());
					 a=big2.divide(big1,2,BigDecimal.ROUND_HALF_DOWN).floatValue();
				 }
				
				 float avgTime= vo.getAverageTime()==null?0:vo.getAverageTime();
				 Float diff=0.0f;
				 diff=vo.getDifficulity();
				 String[] userAnwer={};
				 Double score=0.0d;
				 PaUserQuestion uq=puMap.get(questionUuid);
				 Integer is_correct=0;
				 int time=0;
				 if(uq!=null){
					 String aws=uq.getAnswer();
					 userAnwer=MqtPaperUtil.parseAnswer(aws, domain);
					 score=uq.getScore();
					 if(uq.getIsCorrect()){
						  is_correct=1;
					 }
					 time=uq.getAnswerTime();
				 }
				 obj2.put("isCorrect", is_correct);
				if(imgListMap.size()>0){
						String uqUuid=uqtoqMap.get(questionUuid);
						List<String> img=new ArrayList<String>();
						List<PaUserFileVo>volist=(List<PaUserFileVo>) imgListMap.get(uqUuid);
						if(volist!=null&&volist.size()>0){
							for(PaUserFileVo v:volist){
								String pufUuid2=v.getSourceFileUuid();
								if(v.getMarkedFileUuid()!=null&&!v.getMarkedFileUuid().equals("")){
									pufUuid2=v.getMarkedFileUuid();
								}
								String url=fileService.relativePath2HttpUrlByUUID(pufUuid2);
								img.add(url);
							}
						}
						obj2.put("imgList", img);
				}else{
					obj2.put("imgList", new  ArrayList<String>());
				}
				com.alibaba.fastjson.JSONArray objlist2=obj2.getJSONArray("questions");
				if(objlist2.size()>0){
					diff=0.0f;
				}
				for(int z=0;z<objlist2.size();z++){
					  com.alibaba.fastjson.JSONObject obj3=objlist2.getJSONObject(z);
					 String questionUuid2=obj3.getString("id");
					 PaQuestionVo vo2=(PaQuestionVo) questionMap.get(questionUuid2);
					 obj3.put("averageTime", vo2.getAverageTime()==null?0:vo2.getAverageTime());
					 avgTime+=vo2.getAverageTime()==null?0:vo2.getAverageTime();
					 if(vo2.getAnswerCount()==null||vo2.getAnswerCount()==0||vo2.getRightAnswerCount()==null||vo2.getRightAnswerCount()==0){
						 obj3.put("rightRate", 0.0f);
					 }else{
						 BigDecimal big1=new BigDecimal(vo2.getAnswerCount());
						 BigDecimal big2=new BigDecimal(vo2.getRightAnswerCount());
						 float a1=big2.divide(big1,2,BigDecimal.ROUND_HALF_DOWN).floatValue();
						 BigDecimal ab=new BigDecimal(a+"");
						 BigDecimal a1b=new BigDecimal(a1+"");
						 a=ab.add(a1b).floatValue();
						 obj3.put("rightRate", a1);
					 }
					 String[] userAnwer2={};
					 Double score2=0.0d;
					 PaUserQuestion uq2=puMap.get(questionUuid2);
					 Integer is_correct1=0;
					 Integer time1=0;
					 if(uq2!=null){
						 String aws2=uq2.getAnswer();
						 userAnwer2=MqtPaperUtil.parseAnswer(aws2, domain);
						 score2=uq2.getScore();
						 if(uq2.getIsCorrect()){
							 is_correct1=1;
						 }
						 time1=uq2.getAnswerTime();
					 }
					 time+=time1;
					 obj3.put("time", time1);
					 obj3.put("isCorrect", is_correct1);
					 obj3.put("studentScore", score2);
					 obj3.put("userAnwer", userAnwer2);
					 obj3.put("difficulty", vo2.getDifficulity());
					 BigDecimal diffB=new BigDecimal(diff+"");
					 BigDecimal diff2B=new BigDecimal(vo2.getDifficulity()+"");
					 diff=diffB.add(diff2B).floatValue();
					 BigDecimal scoreB=new BigDecimal(score+"");
					 BigDecimal score2B=new BigDecimal(score2+"");
					 
					 score=scoreB.add(score2B).doubleValue();
					 if(imgListMap.size()>0){
							String uqUuid=uqtoqMap.get(questionUuid2);
							List<String> img=new ArrayList<String>();
							List<PaUserFileVo>volist=(List<PaUserFileVo>) imgListMap.get(uqUuid);
							if(volist!=null&&volist.size()>0){
								for(PaUserFileVo v:volist){
									String pufUuid3=v.getSourceFileUuid();
									if(v.getMarkedFileUuid()!=null&&!v.getMarkedFileUuid().equals("")){
										pufUuid3=v.getMarkedFileUuid();
									}
									String url=fileService.relativePath2HttpUrlByUUID(pufUuid3);
									img.add(url);
								}
							}
							obj3.put("imgList", img);
					}else{
						obj3.put("imgList", new  ArrayList<String>());
					}
				}
				if(objlist2.size()!=0){
					 BigDecimal big1=new BigDecimal(objlist2.size()+"");
					 BigDecimal big2=new BigDecimal(a+"");
					 a=big2.divide(big1,2,BigDecimal.ROUND_HALF_DOWN).floatValue();
					 big2=new BigDecimal(avgTime);
//					 avgTime=big2.divide(big1,2,BigDecimal.ROUND_HALF_DOWN).floatValue();
					 big2=new BigDecimal(diff+"");
					 diff=big2.divide(big1,1,BigDecimal.ROUND_HALF_DOWN).floatValue();
				}
				 obj2.put("userAnwer", userAnwer);
				 obj2.put("time", time);
				 obj2.put("studentScore", score);
				 obj2.put("difficulty", diff);
				 obj2.put("averageTime",avgTime);
				 obj2.put("rightRate", a);
			 }
		 }
		// ObjectMapper objectMapper=new ObjectMapper();
			//jsonString=objectMapper.writeValueAsString(json);
		//	System.out.println(jsonString);
//			jsonString = StringEscapeUtils.unescapeJava(jsonString);
//			System.out.println(jsonString);
		}catch (Exception e){
			e.printStackTrace();
			return jSONObject;
		}
		return json;
	}
	
}