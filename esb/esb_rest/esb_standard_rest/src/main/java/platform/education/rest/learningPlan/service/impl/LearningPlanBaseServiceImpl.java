package platform.education.rest.learningPlan.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.vo.ResTextbookCatalogVo;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.model.LearningPlanInterscore;
import platform.education.learningDesign.model.LpTask;
import platform.education.learningDesign.model.LpTaskUnitUser;
import platform.education.learningDesign.model.LpTaskUser;
import platform.education.learningDesign.model.LpTaskUserActivity;
import platform.education.learningDesign.model.LpUnit;
import platform.education.learningDesign.model.LpUnitFile;
import platform.education.learningDesign.service.LearningPlanInterscoreService;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.learningDesign.service.LpTaskLockService;
import platform.education.learningDesign.service.LpTaskService;
import platform.education.learningDesign.service.LpTaskUnitUserService;
import platform.education.learningDesign.service.LpTaskUserService;
import platform.education.learningDesign.service.LpUnitFileService;
import platform.education.learningDesign.service.LpUnitService;
import platform.education.learningDesign.vo.*;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.service.MicroLessonService;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.UserFile;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaperService;
import platform.education.paper.service.UserFileService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.paper.vo.UserQuestionCondition;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.ResourceHandlerService;
import platform.education.resource.service.ResourceService;
import platform.education.rest.learningPlan.service.LearningPlanBaseService;
import platform.education.user.model.User;
import platform.education.user.service.UserService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

public class LearningPlanBaseServiceImpl implements LearningPlanBaseService{
	
	@Autowired
	@Qualifier("learningPlanService")
	private LearningPlanService learningPlanService;
	
	@Autowired
	@Qualifier("lpTaskUnitUserService")
	private LpTaskUnitUserService lpTaskUnitUserService;
	
	@Autowired
	@Qualifier("lpUnitService")
	private LpUnitService lpUnitService;
	
	@Autowired
	@Qualifier("lpTaskUserService")
	private LpTaskUserService lpTaskUserService;
	
	@Autowired
	@Qualifier("lpTaskService")
	private LpTaskService lpTaskService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
	
	@Autowired
    @Qualifier("resourceHandlerService")
	private ResourceHandlerService resourceHandlerService;
	
	@Autowired
    @Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;
	
	@Autowired
    @Qualifier("catalogResourceService")
	private CatalogResourceService catalogResourceService;
	
	@Autowired
    @Qualifier("resourceService")
	private ResourceService resourceService;
	
	@Autowired
    @Qualifier("lpUnitFileService")
	private LpUnitFileService lpUnitFileService;
	
	@Autowired
    @Qualifier("microLessonService")
	private MicroLessonService microLessonService;
	
	@Autowired
    @Qualifier("userService")
	private UserService userService;
	
	@Autowired
    @Qualifier("personService")
	private PersonService personService;

	@Autowired
    @Qualifier("examService")
	private ExamService examService;
	
	@Autowired
    @Qualifier("papaperService")
	private PaperService paperService;
	
	@Autowired
    @Qualifier("userPaperService")
	private UserPaperService userPaperService;
	
	@Autowired
    @Qualifier("userFileService")
	private UserFileService userFileService;
	
	@Autowired
    @Qualifier("userQuestionService")
	private UserQuestionService userQuestionService;
	
	@Autowired
    @Qualifier("learningPlanInterscoreService")
	private LearningPlanInterscoreService learningPlanInterscoreService;
	
	@Autowired
    @Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;
	
	@Autowired
    @Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	@Autowired
	@Qualifier("lpTaskLockService")
	private LpTaskLockService lpTaskLockService;
	
	
	
	@Override
	public Page getPage(Integer pageSize, Integer pageNumber) {
		Page page = new Page();
		if(pageSize!=null) {
			page.setEnableGetTolaRows(false);
			page.setPageSize(pageSize);
		}
		
		if(pageNumber!=null) {
			page.setEnableGetTolaRows(false);
			page.setCurrentPage(pageNumber);
		}
		return page;
	}
	
	@Override
	public int[] randomArray(int min,int max,int n){
		int len = max-min+1;
		
		if(max < min || n > len){
			return null;
		}
		
		/**初始化给定范围的待选数组*/
		int[] source = new int[len];
        for (int i = min; i < min+len; i++){
        	source[i-min] = i;
        }
        
        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
        	/**待选数组0到(len-2)随机一个下标*/
            index = Math.abs(rd.nextInt() % len);
            while( i == source[index]){
           	 index = Math.abs(rd.nextInt() % len);
           	 if(source[len-1]==max&&source[0]==max){
           		 return randomArray(min,max,n);
           	 }
           }
            len--;
            /**将随机到的数放入结果集*/
            result[i] = source[index];
            /**将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换*/
            source[index] = source[len];
        }
        return result;
	}
	
	@Override
	public Map<String, Object> getCatalogMap(ResTextbookCatalogVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", vo.getId());
		map.put("testBookId", vo.getTestBookId());
		map.put("name", vo.getName());
		map.put("level", vo.getLevel());
		map.put("code", vo.getCode());
		map.put("listOrder", vo.getListOrder());
		return map;
	}
	
	@Override
	public void initExamInterscore(Integer taskId, int taskExamUnitId,Integer unitId,Integer teamId) {
		LearningPlanInterscoreCondition learningPlanInterscoreCondition = new LearningPlanInterscoreCondition();
		learningPlanInterscoreCondition.setTeamId(teamId);
		learningPlanInterscoreCondition.setTaskExamUnitId(taskExamUnitId);
		learningPlanInterscoreCondition.setIsDeleted(false);
		List<LearningPlanInterscore> learningPlanInterscoreList = learningPlanInterscoreService.findLearningPlanInterscoreByCondition(learningPlanInterscoreCondition);
		
		if(learningPlanInterscoreList==null || learningPlanInterscoreList.size()==0){
			UserPaperCondition userPaperCondition = new UserPaperCondition();
			userPaperCondition.setOwnerId(taskId);
			userPaperCondition.setObjectId(unitId);
			
			List<UserPaper> userPaperList=this.userPaperService.findUserPaperByCondition(userPaperCondition);
			if(userPaperList!=null && userPaperList.size()>1){
				Date date=new Date();
				int[] a=randomArray(0, userPaperList.size()-1, userPaperList.size());
				LearningPlanInterscore[] learningPlanInterscoreArray = new LearningPlanInterscore[userPaperList.size()];
				LearningPlanInterscore learningPlanInterscore = new LearningPlanInterscore();
				int i=0;
				for(UserPaper userPaper : userPaperList){
					learningPlanInterscore = new LearningPlanInterscore();
					learningPlanInterscore.setCreateDate(date);
					learningPlanInterscore.setModifyDate(date);
					learningPlanInterscore.setIsDeleted(false);
					learningPlanInterscore.setTaskExamUnitId(taskExamUnitId);
					learningPlanInterscore.setScoredPaperId(userPaper.getId());
					learningPlanInterscore.setScoredUserId(userPaper.getUserId());
					learningPlanInterscore.setScoringUserId(userPaperList.get(a[i]).getUserId());
					learningPlanInterscore.setTeamId(teamId);
					learningPlanInterscoreArray[i] = learningPlanInterscore;
					i++;
				}
				this.learningPlanInterscoreService.createBatch(learningPlanInterscoreArray);
			}
		}
	}
	
	@Override
	public List<Map<String, Object>> getLearningPlan(Integer catalogId, Integer userId, Integer schoolId) {
		ResTextbookCatalog resTextbookCatalog = resTextbookCatalogService.findResTextbookCatalogById(catalogId);
		if(resTextbookCatalog==null) {
			return null;
		}
		/**当前目录和它子目录列表*/
		List<ResTextbookCatalog> list = new ArrayList<ResTextbookCatalog>();
		list.add(resTextbookCatalog);
		if(resTextbookCatalog.getLevel()-1>0) {
			/**获取目录下所有子目录*/
			resTextbookCatalogService.findChildren(resTextbookCatalog.getId(), list);
		}
		
		String codes[]  = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			codes[i] = list.get(i).getCode();
		}
		/**返回值*/
		List<Map<String, Object>> lpList = new ArrayList<Map<String, Object>>();
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<LearningPlan> result = learningPlanService.findLearningPlanByCatalogCodes(codes, null, null);
		for (LearningPlan learningPlan : result) {
			LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
			lpTaskUserCondition.setUserId(userId);
			lpTaskUserCondition.setLpId(learningPlan.getId());
			/**查找用户的列表*/
			Order order = new Order();
			order.setProperty("create_date");
			order.setAscending(false);
			List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByMoreCondition(lpTaskUserCondition,null,order);
			
			String catalogName = resTextbookCatalogService.getFullNameByCode(learningPlan.getCatalogCode(), " ");
			
			for (LpTaskUser lpTaskUser : lpTaskUserList) {
				/**导学案任务信息*/
				Map<String, Object> taskMap = new HashMap<String, Object>();
				/**设置相应的返回值信息*/
				taskMap.put("taskId", lpTaskUser.getTaskId());
				taskMap.put("learningPlanId", lpTaskUser.getLpId());
				taskMap.put("name", learningPlan.getTitle());
				taskMap.put("catalogName", catalogName);
				/**判断导学案学习状态*/
				taskMap.put("state", lpTaskUser.getState());
				/**获取相应的任务信息*/
				LpTask lpt = lpTaskService.findLpTaskById(lpTaskUser.getTaskId());
				taskMap.put("startTime", formatH.format(lpt.getCreateDate()));
				/**添加返回值信息*/
				lpList.add(taskMap);
			}
		}
		return lpList;
	}
	 
	
	/**获取学校科目*/
	@Override
	public Map<String, String> getSubjectMap(Integer schoolId) {
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setSchoolId(schoolId);
		List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
		Map<String, String> subjectMap = new HashMap<String, String>(subjectList.size());
		for (Subject subject : subjectList) {
			subjectMap.put(subject.getCode()+"", subject.getName());
		}
		return subjectMap;
	}


	@Override
	public List<Map<String, Object>> getUnitList(Integer learningPlanID, Integer catalogId, Integer userId,Integer taskId) {
		/**获取导学案单元列表*/
		LpUnitCondition lpUnitCondition = new LpUnitCondition();
		lpUnitCondition.setLpId(learningPlanID);
		lpUnitCondition.setIsDeleted(false);
		lpUnitCondition.setCatalogId(catalogId);
		List<LpUnit> lpUnitList = lpUnitService.findLpUnitByCondition(lpUnitCondition,null,Order.asc("list_order"));
		
		if(lpUnitList.size()==0) {
			return null;
		}
		
		/**返回值列表*/
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (LpUnit lpUnit : lpUnitList) {
			if(catalogId == 0){
				LpTaskUnitUserCondition condition = new LpTaskUnitUserCondition();
				condition.setTaskId(taskId);
				condition.setUnitId(lpUnit.getId());
				if(lpTaskUnitUserService.count(condition) <= 0) continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", lpUnit.getId());
			map.put("title", lpUnit.getTitle());
			map.put("listOrder", lpUnit.getListOrder());
			map.put("unitType", lpUnit.getUnitType());
			/**获取用户单元完成信息*/ //教师的不传userId
			if(userId!=null && taskId != null) {
				boolean hasFinish = lpUnitService.findUnitFinishState(lpUnit.getId(), taskId, userId);
				map.put("hasFinish", hasFinish);
			}
			
			//单元是否已加锁
			LpTaskLockCondition lpTaskLockCondition=new LpTaskLockCondition();
			lpTaskLockCondition.setUnitId(lpUnit.getId());
			lpTaskLockCondition.setTaskId(taskId);
			lpTaskLockCondition.setIsLocked(true);
			lpTaskLockCondition.setIsDeleted(false);
			map.put("isLock", lpTaskLockService.count(lpTaskLockCondition) > 0 ? 1 : 0);
			
			list.add(map);
		}
		return list;
	}
	
	
	/**学生答题记录*/
	@Override
	public List<String> findUserQuestionFileList(Integer taskId, Integer unitId, Integer userId) {
		UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
		userQuestionCondition.setOwnerId(taskId);
		userQuestionCondition.setObjectId(unitId);
		userQuestionCondition.setUserId(userId);
		
		List<UserQuestion> userQuestionList = userQuestionService.findUserQuestionByCondition(userQuestionCondition);
		String[] userQuestionUuIds = new String[userQuestionList.size()];
		
		for (int i=0; i<userQuestionList.size(); i++) {
			userQuestionUuIds[i] = userQuestionList.get(i).getUuid();
		}
    	
    	List<String> userFileList = new ArrayList<String>();
    	
    	/**如果该学生有答题记录*/
    	if(userQuestionList.size()>0) {
    		List<UserFile> userFiles = userFileService.findUserFileByUserQuestionUuIds(userQuestionUuIds);
    		/**去重*/
    		for (int i = 0; i < userFiles.size(); i++)  {
                for (int j = userFiles.size() - 1 ; j > i; j--) {
                    if (userFiles.get(i).getSourceFileUuid().equals(userFiles.get(j).getSourceFileUuid())){
                    	userFiles.remove(j);
                    }
                }
            }
	    	/**遍历*/
	    	for (UserFile userFile : userFiles) {
	    		if(userFile.getMarkedFileUuid()!=null) {
	    			/**如果存在批注后的图片，刚返回批注的图片*/
	    			FileResult result = fileService.findFileByUUID(userFile.getMarkedFileUuid());
	    			userFileList.add(result.getHttpUrl());
	    		} else {
	    			/**如果不存在批注后的图片，刚返回源图片*/
	    			FileResult result = fileService.findFileByUUID(userFile.getSourceFileUuid());
	    			userFileList.add(result.getHttpUrl());
	    		}
			}
    	}
		return userFileList;
	}

	@Override
	public String getMicroIconUrl(String objectId) {
		/**返回资源的详情*/
		MicroLesson micro = microLessonService.findMicroLessonByUuid(objectId);
		if(micro!=null) {
			/**缩略图*/
			String iconUrl = fileService.thumb2HttpUrlByUUID(micro.getEntityId());
			return iconUrl;
		}
		return "";
	}

	@Override
	public Map<String, FileResult> getFileResult(List<LpTaskUserActivity> lpTaskUserActivityList) {
		List<String> uuidList = new ArrayList<String>(lpTaskUserActivityList.size());
		
		for (LpTaskUserActivity lpTaskUserActivity : lpTaskUserActivityList) {
			String files = lpTaskUserActivity.getFiles();
			String[] filesArray = null;
			if(files != null && !"".equals(files)) {
				filesArray = files.split(",");
			}
			if(filesArray != null && filesArray.length > 0) {
				for (String uuid : filesArray) {
					uuidList.add(uuid);
				}
			}
		}
		String[] filesArray = new String[uuidList.size()];
		if(uuidList != null && uuidList.size() >0) {
			
			for (int i = 0; i < uuidList.size(); i++) {
				filesArray[i] = uuidList.get(i);
			}
		}
		
		Map<String, FileResult> fileResultMap = new HashMap<String, FileResult>();
		
		if(filesArray!=null && filesArray.length>0) {
			fileResultMap = fileService.findFileByUUIDs(filesArray);
		}
		
		return fileResultMap;
	}
	
	@Override
	public Map<Integer, Person> getPersonMapByLpTaskUserList(List<?> list) {
		/**用户id的数组*/
		Integer[] userIds = new Integer[list.size()];
		
		/**添加用户的id*/
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			if(object instanceof LpTaskUnitUser) {
				userIds[i] = ((LpTaskUnitUser) object).getUserId();
			} else if(object instanceof LpTaskUser) {
				userIds[i] = ((LpTaskUser) object).getUserId();
			} else if(object instanceof User) {
				userIds[i] = ((User) object).getId();
			}
		}
		
		if(userIds.length==0) {
			return new HashMap<Integer, Person>();
		}
		
		/**获取用户的集合*/
		List<User> userList = userService.findUserByIds(userIds);
		/**获取Person的集合(用于获取用户的name) map的id为userId, value为person*/
		Map<Integer, Person> personMap = getPersons(userList);
		
		return personMap;
	}

	/**
	 * 获取Person的集合， 以useId为id, Person为value的map集合
	 * @param userList
	 * @return
	 */
	@Override
	public Map<Integer, Person> getPersons(List<User> userList) {
		
		Integer[] personIds = new Integer[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			personIds[i] = userList.get(i).getPersonId();
		}
		
		List<Person> personList = personService.findbyIds(personIds);
		
		Map<Integer, Person> map = new HashMap<Integer, Person>(personList.size());
		for (User user : userList) {
			for (Person person : personList) {
				if(user.getPersonId()!=null){
					if(person.getId()-user.getPersonId()==0) {
						map.put(user.getId(), person);
					}
				}
			}
		}
		
		return map;
	}
	
	@Override
	public String parseFiles(String files) {
		String fileString = "";
		JSONArray array = JSONArray.fromObject(files);
		for (int i = 0; i < array.size(); i++) {
			/**最后的数据项不加,*/
			if(i==array.size()-1) {
				fileString+=array.getString(i);
			} else {
				/**以逗号隔开*/
				fileString+=array.getString(i)+",";
			}
		}
		return fileString;
	}
	
	@Override
	public List<Map<String, Object>> getExamUnitList(Integer taskId, Integer lpId, String subjectCode) {
		/**获取试卷单元列表*/
		List<LpUnit> lpUnitList = lpUnitService.findUnitListByLpIdAndUnitType(lpId, Integer.parseInt(LpUnitType.EXAM));
		
		/**获取试卷单元关联的文件列表*/
		List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByUnitIds(lpUnitList);
		Integer[] paperIds = new Integer[lpUnitFileList.size()];
		
		Map<Integer, Integer> unitIdPaperIdMap = new HashMap<Integer, Integer>(lpUnitList.size());
		for (int i = 0; i < lpUnitFileList.size(); i++) {
			LpUnitFile lpUnitFile = lpUnitFileList.get(i);
			PaPaper paper = paPaperService.findPaPaperByUUid(lpUnitFile.getObjectUuid());
			paperIds[i] = paper.getId();
			unitIdPaperIdMap.put(lpUnitFile.getLpUnitId(), paper.getId());
		}
		
		/**根据试卷的id集合获取一批试卷的题目列表*/
		List<PaQuestionVo> questionList = paQuestionService.findPaperQuestionByPaperIds(paperIds);
		
		/**构建单元和题目一对多数据结构*/
		List<Map<String, Object>> unitMapList = new ArrayList<Map<String, Object>>(lpUnitList.size());
		for (LpUnit lpUnit : lpUnitList) {
			/**根据单元id获取试卷的id*/
			Integer paperId = unitIdPaperIdMap.get(lpUnit.getId());
			if(paperId==null) {
				continue;
			}
			
			/**构建返回参数的结构*/
			Map<String, Object> unitMap = new HashMap<String, Object>();
			unitMap.put("unitId", lpUnit.getId());
			unitMap.put("unitName", lpUnit.getTitle());
			
			List<Map<String, Object>> questionMapList = new ArrayList<Map<String, Object>>(questionList.size()/3);
			/**遍历题目信息*/
			for (PaQuestionVo question : questionList) {
				/**如果和该题目是当前试卷的，把它加到questionMapList中*/
				if(question!=null && paperId-question.getPaperId()==0) {
					if(question.getPos()-0==0) {
						continue;
					}
					Map<String, Object> questionMap = new HashMap<String, Object>();
					/**题目的序号*/
					questionMap.put("pos", question.getPos());
					/**题目的uuid*/
					questionMap.put("questionUuid", question.getUuid());
					/**题目的类型*/
					questionMap.put("questionType", question.getQuestionType());
					questionMapList.add(questionMap);
				}
			}
			/**一个试卷单元所对应的题目*/
			unitMap.put("questions", questionMapList);
			unitMapList.add(unitMap);
		}
		
		return unitMapList;
	}
	
	public Map<String, Object> getCatalogInfo(ResTextbookCatalog catalog) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", catalog.getId());
		map.put("name", catalog.getName());
		map.put("level", catalog.getLevel());
		map.put("code", catalog.getCode());
		map.put("listOrder", catalog.getListOrder());
		return map;
	}


	@Override
	public String handlerImg(String content) {
		String st = "src=\"";
		String[] contents = content.split(st);
		if(contents.length-1==0) {
			st = "src=\\\"";
			contents = content.split(st);
		}
		if(contents.length-1==0) {
			return content;
		}
		
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		StringBuffer anwserBuf = new StringBuffer(content.length());
		anwserBuf.append(contents[0]);
		
		for (int i = 1; i < contents.length; i++) {
			String str = contents[i];
			anwserBuf.append(st);
			anwserBuf.append(domain);
			anwserBuf.append(str);
		}
		return anwserBuf.toString();
	}
}
