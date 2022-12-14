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
		
		/**????????????????????????????????????*/
		int[] source = new int[len];
        for (int i = min; i < min+len; i++){
        	source[i-min] = i;
        }
        
        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
        	/**????????????0???(len-2)??????????????????*/
            index = Math.abs(rd.nextInt() % len);
            while( i == source[index]){
           	 index = Math.abs(rd.nextInt() % len);
           	 if(source[len-1]==max&&source[0]==max){
           		 return randomArray(min,max,n);
           	 }
           }
            len--;
            /**?????????????????????????????????*/
            result[i] = source[index];
            /**??????????????????????????????????????????????????????(len-1)????????????????????????*/
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
		/**?????????????????????????????????*/
		List<ResTextbookCatalog> list = new ArrayList<ResTextbookCatalog>();
		list.add(resTextbookCatalog);
		if(resTextbookCatalog.getLevel()-1>0) {
			/**??????????????????????????????*/
			resTextbookCatalogService.findChildren(resTextbookCatalog.getId(), list);
		}
		
		String codes[]  = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			codes[i] = list.get(i).getCode();
		}
		/**?????????*/
		List<Map<String, Object>> lpList = new ArrayList<Map<String, Object>>();
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<LearningPlan> result = learningPlanService.findLearningPlanByCatalogCodes(codes, null, null);
		for (LearningPlan learningPlan : result) {
			LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
			lpTaskUserCondition.setUserId(userId);
			lpTaskUserCondition.setLpId(learningPlan.getId());
			/**?????????????????????*/
			Order order = new Order();
			order.setProperty("create_date");
			order.setAscending(false);
			List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByMoreCondition(lpTaskUserCondition,null,order);
			
			String catalogName = resTextbookCatalogService.getFullNameByCode(learningPlan.getCatalogCode(), " ");
			
			for (LpTaskUser lpTaskUser : lpTaskUserList) {
				/**?????????????????????*/
				Map<String, Object> taskMap = new HashMap<String, Object>();
				/**??????????????????????????????*/
				taskMap.put("taskId", lpTaskUser.getTaskId());
				taskMap.put("learningPlanId", lpTaskUser.getLpId());
				taskMap.put("name", learningPlan.getTitle());
				taskMap.put("catalogName", catalogName);
				/**???????????????????????????*/
				taskMap.put("state", lpTaskUser.getState());
				/**???????????????????????????*/
				LpTask lpt = lpTaskService.findLpTaskById(lpTaskUser.getTaskId());
				taskMap.put("startTime", formatH.format(lpt.getCreateDate()));
				/**?????????????????????*/
				lpList.add(taskMap);
			}
		}
		return lpList;
	}
	 
	
	/**??????????????????*/
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
		/**???????????????????????????*/
		LpUnitCondition lpUnitCondition = new LpUnitCondition();
		lpUnitCondition.setLpId(learningPlanID);
		lpUnitCondition.setIsDeleted(false);
		lpUnitCondition.setCatalogId(catalogId);
		List<LpUnit> lpUnitList = lpUnitService.findLpUnitByCondition(lpUnitCondition,null,Order.asc("list_order"));
		
		if(lpUnitList.size()==0) {
			return null;
		}
		
		/**???????????????*/
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
			/**??????????????????????????????*/ //???????????????userId
			if(userId!=null && taskId != null) {
				boolean hasFinish = lpUnitService.findUnitFinishState(lpUnit.getId(), taskId, userId);
				map.put("hasFinish", hasFinish);
			}
			
			//?????????????????????
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
	
	
	/**??????????????????*/
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
    	
    	/**??????????????????????????????*/
    	if(userQuestionList.size()>0) {
    		List<UserFile> userFiles = userFileService.findUserFileByUserQuestionUuIds(userQuestionUuIds);
    		/**??????*/
    		for (int i = 0; i < userFiles.size(); i++)  {
                for (int j = userFiles.size() - 1 ; j > i; j--) {
                    if (userFiles.get(i).getSourceFileUuid().equals(userFiles.get(j).getSourceFileUuid())){
                    	userFiles.remove(j);
                    }
                }
            }
	    	/**??????*/
	    	for (UserFile userFile : userFiles) {
	    		if(userFile.getMarkedFileUuid()!=null) {
	    			/**?????????????????????????????????????????????????????????*/
	    			FileResult result = fileService.findFileByUUID(userFile.getMarkedFileUuid());
	    			userFileList.add(result.getHttpUrl());
	    		} else {
	    			/**??????????????????????????????????????????????????????*/
	    			FileResult result = fileService.findFileByUUID(userFile.getSourceFileUuid());
	    			userFileList.add(result.getHttpUrl());
	    		}
			}
    	}
		return userFileList;
	}

	@Override
	public String getMicroIconUrl(String objectId) {
		/**?????????????????????*/
		MicroLesson micro = microLessonService.findMicroLessonByUuid(objectId);
		if(micro!=null) {
			/**?????????*/
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
		/**??????id?????????*/
		Integer[] userIds = new Integer[list.size()];
		
		/**???????????????id*/
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
		
		/**?????????????????????*/
		List<User> userList = userService.findUserByIds(userIds);
		/**??????Person?????????(?????????????????????name) map???id???userId, value???person*/
		Map<Integer, Person> personMap = getPersons(userList);
		
		return personMap;
	}

	/**
	 * ??????Person???????????? ???useId???id, Person???value???map??????
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
			/**????????????????????????,*/
			if(i==array.size()-1) {
				fileString+=array.getString(i);
			} else {
				/**???????????????*/
				fileString+=array.getString(i)+",";
			}
		}
		return fileString;
	}
	
	@Override
	public List<Map<String, Object>> getExamUnitList(Integer taskId, Integer lpId, String subjectCode) {
		/**????????????????????????*/
		List<LpUnit> lpUnitList = lpUnitService.findUnitListByLpIdAndUnitType(lpId, Integer.parseInt(LpUnitType.EXAM));
		
		/**???????????????????????????????????????*/
		List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByUnitIds(lpUnitList);
		Integer[] paperIds = new Integer[lpUnitFileList.size()];
		
		Map<Integer, Integer> unitIdPaperIdMap = new HashMap<Integer, Integer>(lpUnitList.size());
		for (int i = 0; i < lpUnitFileList.size(); i++) {
			LpUnitFile lpUnitFile = lpUnitFileList.get(i);
			PaPaper paper = paPaperService.findPaPaperByUUid(lpUnitFile.getObjectUuid());
			paperIds[i] = paper.getId();
			unitIdPaperIdMap.put(lpUnitFile.getLpUnitId(), paper.getId());
		}
		
		/**???????????????id???????????????????????????????????????*/
		List<PaQuestionVo> questionList = paQuestionService.findPaperQuestionByPaperIds(paperIds);
		
		/**??????????????????????????????????????????*/
		List<Map<String, Object>> unitMapList = new ArrayList<Map<String, Object>>(lpUnitList.size());
		for (LpUnit lpUnit : lpUnitList) {
			/**????????????id???????????????id*/
			Integer paperId = unitIdPaperIdMap.get(lpUnit.getId());
			if(paperId==null) {
				continue;
			}
			
			/**???????????????????????????*/
			Map<String, Object> unitMap = new HashMap<String, Object>();
			unitMap.put("unitId", lpUnit.getId());
			unitMap.put("unitName", lpUnit.getTitle());
			
			List<Map<String, Object>> questionMapList = new ArrayList<Map<String, Object>>(questionList.size()/3);
			/**??????????????????*/
			for (PaQuestionVo question : questionList) {
				/**???????????????????????????????????????????????????questionMapList???*/
				if(question!=null && paperId-question.getPaperId()==0) {
					if(question.getPos()-0==0) {
						continue;
					}
					Map<String, Object> questionMap = new HashMap<String, Object>();
					/**???????????????*/
					questionMap.put("pos", question.getPos());
					/**?????????uuid*/
					questionMap.put("questionUuid", question.getUuid());
					/**???????????????*/
					questionMap.put("questionType", question.getQuestionType());
					questionMapList.add(questionMap);
				}
			}
			/**????????????????????????????????????*/
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
