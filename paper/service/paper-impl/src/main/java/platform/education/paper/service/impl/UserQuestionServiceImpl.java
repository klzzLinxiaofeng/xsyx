package platform.education.paper.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.paper.constants.PaperType;
import platform.education.paper.dao.PaQuestionDao;
import platform.education.paper.dao.UserQuestionDao;
import platform.education.paper.dao.UserWrongDao;
import platform.education.paper.model.*;
import platform.education.paper.service.PaQuestionCatalogService;
import platform.education.paper.service.PaQuestionKnoledgeService;
import platform.education.paper.service.UserFileService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.vo.CognitionCountVo;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.PaQuestionCatalogCondition;
import platform.education.paper.vo.UserQuestionCondition;
import platform.education.resource.dao.ActionCodeDao;
import platform.education.resource.dao.UserActionDao;
import platform.education.resource.dao.UserKnowledgeSummaryDao;
import platform.education.resource.model.ActionCode;
import platform.education.resource.model.UserAction;
import platform.education.resource.model.UserKnowledgeSummary;
import platform.education.resource.vo.ActionCodeCondition;

import javax.annotation.Resource;
import java.util.*;

public class UserQuestionServiceImpl implements UserQuestionService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private UserQuestionDao userQuestionDao;

    private PaQuestionDao paQuestionDao;

    private UserActionDao userActionDao;

    private ActionCodeDao actionCodeDao;

    private UserKnowledgeSummaryDao userKnowledgeSummaryDao;

    private UserWrongDao userWrongDao;
    @Resource
    private UserFileService userFileService;

    private PaQuestionCatalogService paQuestionCatalogService;

    private PaQuestionKnoledgeService paQuestionKnoledgeService;

    private KnowledgeNodeService knowledgeNodeService;
    @Resource
    private TeamStudentService teamStudentService;


    public void setUserActionDao(UserActionDao userActionDao) {
        this.userActionDao = userActionDao;
    }

    public void setUserKnowledgeSummaryDao(UserKnowledgeSummaryDao userKnowledgeSummaryDao) {
        this.userKnowledgeSummaryDao = userKnowledgeSummaryDao;
    }

    public void setUserWrongDao(UserWrongDao userWrongDao) {
        this.userWrongDao = userWrongDao;
    }


    public void setUserQuestionDao(UserQuestionDao userQuestionDao) {
        this.userQuestionDao = userQuestionDao;
    }

    @Override
    public UserQuestion findUserQuestionById(Integer id) {
        try {
            return userQuestionDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public UserQuestion add(UserQuestion userQuestion) {
        if (userQuestion == null) {
            return null;
        }
        Date createDate = userQuestion.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        userQuestion.setCreateDate(createDate);
        userQuestion.setModifyDate(createDate);
        return userQuestionDao.create(userQuestion);
    }

    @Override
    public UserQuestion modify(UserQuestion userQuestion) {
        if (userQuestion == null) {
            return null;
        }
        Date modify = userQuestion.getModifyDate();
        userQuestion.setModifyDate(modify != null ? modify : new Date());
        return userQuestionDao.update(userQuestion);
    }

    @Override
    public void remove(UserQuestion userQuestion) {
        try {
            userQuestionDao.delete(userQuestion);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", userQuestion.getId(), e);
            }
        }
    }

    @Override
    public List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition, Page page, Order order) {
        return userQuestionDao.findUserQuestionByCondition(userQuestionCondition, page, order);
    }

    @Override
    public List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition) {
        return userQuestionDao.findUserQuestionByCondition(userQuestionCondition, null, null);
    }

    @Override
    public List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition, Page page) {
        return userQuestionDao.findUserQuestionByCondition(userQuestionCondition, page, null);
    }

    @Override
    public List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition, Order order) {
        return userQuestionDao.findUserQuestionByCondition(userQuestionCondition, null, order);
    }

    @Override
    public Long count() {
        return this.userQuestionDao.count(null);
    }

    @Override
    public Long count(UserQuestionCondition userQuestionCondition) {
        return this.userQuestionDao.count(userQuestionCondition);
    }

    /* (非 Javadoc)
     * <p>Title: findUserQuestionByUserIdAndPaperUuId</p>
     * <p>Description: </p>
     * @param userId
     * @param paperUuId
     * @param ownerId
     * @return
     * @see platform.education.paper.service.UserQuestionService#findUserQuestionByUserIdAndPaperUuId(java.lang.Integer, java.lang.String, java.lang.Integer)
     */
    @Override
    public List<AnswerSituationResult> findUserQuestionByUserIdAndPaperUuId(Integer userId, String paperUuId,
                                                                            Integer ownerId) {

        return userQuestionDao.findUserQuestionByUserIdAndPaperUuId(userId, paperUuId, ownerId);
    }

    /* (非 Javadoc)
     * <p>Title: findUserQuestionByOwnerId</p>
     * <p>Description: </p>
     * @param ownerId
     * @return
     * @see platform.education.paper.service.UserQuestionService#findUserQuestionByOwnerId(java.lang.Integer)
     */
    @Override
    public List<UserQuestionResult> findUserQuestionByOwnerId(Integer ownerId, Integer objectId, Integer type) {
        List<UserQuestionResult> userQuestionResultList = this.userQuestionDao.findUserQuestionByOwnerId(ownerId, objectId, type);

        return userQuestionResultList;
    }

    /* (非 Javadoc)
     * <p>Title: getTeamQuestionOptionsByQuestionUuIdAndOwnerId</p>
     * <p>Description: </p>
     * @param teamId
     * @param ownerId
     * @param questionUuId
     * @return
     * @see platform.education.paper.service.UserQuestionService#getTeamQuestionOptionsByQuestionUuIdAndOwnerId(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public List<TeamQuestionOptions> getTeamQuestionOptionsByQuestionUuIdAndOwnerId(Integer teamId, Integer ownerId,
                                                                                    String questionUuId, Integer unitId) {
        List<TeamQuestionOptions> tqoList = new ArrayList<TeamQuestionOptions>();
        TeamStudentCondition tsCondition = new TeamStudentCondition();
        tsCondition.setTeamId(teamId);
        List<TeamStudent> tsList = new ArrayList<TeamStudent>();
        tsList = teamStudentService.findTeamStudentByCondition(tsCondition, null, null);
        Map<Integer, String> userMap = new HashMap<Integer, String>();
        for (TeamStudent ts : tsList) {
            userMap.put(ts.getUserId(), ts.getName() == null ? "匿名" : ts.getName());
        }
        //通过题目UUID查找该题目有几个选项进行拆分
        String[] questionOptions = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        //判断题选项
        PaQuestion question = paQuestionDao.findPaperQuestionByUuid(questionUuId);
        UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
        userQuestionCondition.setTeamId(teamId);
        userQuestionCondition.setOwnerId(ownerId);
        userQuestionCondition.setQuestionUuid(questionUuId);
        userQuestionCondition.setObjectId(unitId);
        if (unitId == null || unitId == -1) {
            userQuestionCondition.setType(PaperType.EXAM);
        }
        List<UserQuestion> uqlist = userQuestionDao.findUserQuestionByCondition(userQuestionCondition, null, null);
        String[] uuids = new String[uqlist.size()];
        for (int i = 0; i < uqlist.size(); i++) {
            UserQuestion uq = uqlist.get(i);
            uuids[i] = uq.getUuid();
        }
        /**根据答题id数组获取提交的图片答案*/
        List<UserFile> userFileList = userFileService.findUserFileByUserQuestionUuIds(uuids);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (UserFile uf : userFileList) {
            map.put(uf.getUserQuestionUuid(), 1);
        }
        LinkedHashMap<String, List<Integer>> opMap = new LinkedHashMap<String, List<Integer>>();
        if (question != null) {
            if (question.getQuestionType().equals("radio") || question.getQuestionType().equals("checkbox") || question.getQuestionType().equals("multichoise")) {
                String questionAnswer = question.getAnswer();
                if (questionAnswer != null && !"".equals(questionAnswer)) {
                    JSONArray jsonArray = JSONArray.fromObject(questionAnswer);
                    int quesitonOptionSize = jsonArray.size();
                    for (int i = 0; i < quesitonOptionSize; i++) {
                        opMap.put(questionOptions[i], new ArrayList<Integer>());
                    }
                }
            } else if (question.getQuestionType().equals("trueOrFalse")) {
                ObjectMapper mapper = new ObjectMapper();
                String anwer = question.getAnswer();
                String[] sourceArray = {};
                try {
                    sourceArray = mapper.readValue(anwer, String[].class);
                } catch (Exception e) {

                }
                for (int i = 0; i < sourceArray.length; i++) {
                    opMap.put(sourceArray[i], new ArrayList<Integer>());
                }
            } else {
                opMap.put("正确", new ArrayList<Integer>());
                opMap.put("错误", new ArrayList<Integer>());
            }
            opMap.put("无作答", new ArrayList<Integer>());
            if (!question.getQuestionType().equals("blank")) {
                for (UserQuestion uq : uqlist) {
                    List<Integer> name = new ArrayList<Integer>();
                    if ((uq.getAnswer().contains("[]") || uq.getAnswer().contains("[\"\"]")) && !map.containsKey(uq.getUuid())) {
                        if (opMap.get("无作答") != null) {
                            name = opMap.get("无作答");
                        }
                        name.add(uq.getUserId());
                        opMap.put("无作答", name);
                    }
                }
            } else {
                for (UserQuestion uq : uqlist) {
                    String anwser = uq.getAnswer();
                    List<Integer> name = new ArrayList<Integer>();
                    if (!isAnwserBlank(anwser)) {
                        if (opMap.get("无作答") != null) {
                            name = opMap.get("无作答");
                        }
                        name.add(uq.getUserId());
                        opMap.put("无作答", name);
                    }
                }
            }
            if (question.getQuestionType().equals("radio") || question.getQuestionType().equals("checkbox") || question.getQuestionType().equals("multichoise")) {
                String questionAnswer = question.getAnswer();
                if (questionAnswer != null && !"".equals(questionAnswer)) {
                    JSONArray jsonArray = JSONArray.fromObject(questionAnswer);
                    int quesitonOptionSize = jsonArray.size();
                    for (int i = 0; i < quesitonOptionSize; i++) {
                        for (UserQuestion uq : uqlist) {
                            if (uq.getAnswer().contains(questionOptions[i])) {
                                List<Integer> name = new ArrayList<Integer>();
                                if (opMap.get(questionOptions[i]) != null) {
                                    name = opMap.get(questionOptions[i]);
                                }
                                name.add(uq.getUserId());
                                opMap.put(questionOptions[i], name);
                            }
                        }
                    }
                }
            } else if (question.getQuestionType().equals("trueOrFalse")) {
                ObjectMapper mapper = new ObjectMapper();
                String anwer = question.getAnswer();
                String[] sourceArray = {};
                try {
                    sourceArray = mapper.readValue(anwer, String[].class);
                } catch (Exception e) {

                }
                for (int i = 0; i < sourceArray.length; i++) {
                    for (UserQuestion uq : uqlist) {
                        if (uq.getAnswer().contains(sourceArray[i])) {
                            List<Integer> name = new ArrayList<Integer>();
                            if (opMap.get(sourceArray[i]) != null) {
                                name = opMap.get(sourceArray[i]);
                            }

                            name.add(uq.getUserId());
                            opMap.put(sourceArray[i], name);
                        }
                    }
                }
            } else if (question.getQuestionType().equals("blank")) {
                for (UserQuestion uq : uqlist) {
                    List<Integer> name = new ArrayList<Integer>();
                    String anwser = uq.getAnswer();
                    if (!isAnwserBlank(anwser)) {

                    } else {
                        if (uq.getIsCorrect()) {
                            if (opMap.get("正确") != null) {
                                name = opMap.get("正确");
                            }
                        } else {
                            if (opMap.get("错误") != null) {
                                name = opMap.get("错误");
                            }
                        }
                        name.add(uq.getUserId());
                        if (uq.getIsCorrect()) {
                            opMap.put("正确", name);
                        } else {
                            opMap.put("错误", name);
                        }
                    }
                }

            } else {
                for (UserQuestion uq : uqlist) {
                    List<Integer> name = new ArrayList<Integer>();
                    if ((uq.getAnswer().contains("[]") || uq.getAnswer().contains("[\"\"]")) && !map.containsKey(uq.getUuid())) {

                    } else {
                        if (uq.getIsCorrect()) {
                            if (opMap.get("正确") != null) {
                                name = opMap.get("正确");
                            }
                        } else {
                            if (opMap.get("错误") != null) {
                                name = opMap.get("错误");
                            }
                        }
                        name.add(uq.getUserId());
                        if (uq.getIsCorrect()) {
                            opMap.put("正确", name);
                        } else {
                            opMap.put("错误", name);
                        }
                    }
                }
            }
        }
        for (java.util.Map.Entry<String, List<Integer>> e : opMap.entrySet()) {
            TeamQuestionOptions teamQuestionOptions = new TeamQuestionOptions();
            teamQuestionOptions.setQuestionOption(e.getKey());
            teamQuestionOptions.setQuestionOptionCount(Long.valueOf(e.getValue().size()));
            teamQuestionOptions.setUserIds(e.getValue());
            tqoList.add(teamQuestionOptions);
        }
        return tqoList;
    }


    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public PaQuestionDao getPaQuestionDao() {
        return paQuestionDao;
    }

    public void setPaQuestionDao(PaQuestionDao paQuestionDao) {
        this.paQuestionDao = paQuestionDao;
    }

    public PaQuestionCatalogService getPaQuestionCatalogService() {
        return paQuestionCatalogService;
    }

    public void setPaQuestionCatalogService(
            PaQuestionCatalogService paQuestionCatalogService) {
        this.paQuestionCatalogService = paQuestionCatalogService;
    }

    public PaQuestionKnoledgeService getPaQuestionKnoledgeService() {
        return paQuestionKnoledgeService;
    }

    public void setPaQuestionKnoledgeService(
            PaQuestionKnoledgeService paQuestionKnoledgeService) {
        this.paQuestionKnoledgeService = paQuestionKnoledgeService;
    }

    public KnowledgeNodeService getKnowledgeNodeService() {
        return knowledgeNodeService;
    }

    public void setKnowledgeNodeService(KnowledgeNodeService knowledgeNodeService) {
        this.knowledgeNodeService = knowledgeNodeService;
    }

    public UserQuestionDao getUserQuestionDao() {
        return userQuestionDao;
    }

    public UserActionDao getUserActionDao() {
        return userActionDao;
    }

    public ActionCodeDao getActionCodeDao() {
        return actionCodeDao;
    }

    public UserKnowledgeSummaryDao getUserKnowledgeSummaryDao() {
        return userKnowledgeSummaryDao;
    }

    public UserWrongDao getUserWrongDao() {
        return userWrongDao;
    }

    /* (非 Javadoc)
     * <p>Title: sumScore</p>
     * <p>Description: </p>
     * @param ownerId
     * @param teamId
     * @param questionUuid
     * @return
     * @see platform.education.paper.service.UserQuestionService#sumScore(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public Long findSumScore(Integer ownerId, Integer teamId, String questionUuid) {
        return this.userQuestionDao.findSumScore(ownerId, teamId, questionUuid);
    }

    @Override
    public List<UserQuestionResult> findUserQuestionByPaperId(Integer paperId) {
        return this.userQuestionDao.findUserQuestionByPaperId(paperId);
    }

    @Override
    public void processPaperAdd(List<UserQuestion> userQuestionList, List<UserAction> userActionList,
                                List<UserKnowledgeSummary> userKnowledgeSummaryList, List<UserWrong> userWrongList) {

        for (UserQuestion userQuestion : userQuestionList) {
            if (userQuestion.getId() != null) {
                userQuestionDao.update(userQuestion);

            } else {
                userQuestionDao.create(userQuestion);

            }

        }
        for (UserAction userAction : userActionList) {
            userActionDao.create(userAction);
        }

        for (UserKnowledgeSummary userKnowledgeSummary : userKnowledgeSummaryList) {

            if (userKnowledgeSummary.getId() != null) {
                userKnowledgeSummaryDao.update(userKnowledgeSummary);

            } else {
                userKnowledgeSummaryDao.create(userKnowledgeSummary);

            }

        }

        for (UserWrong userWrong : userWrongList) {
            if (userWrong.getId() != null) {
                userWrongDao.update(userWrong);

            } else {
                userWrongDao.create(userWrong);
            }
        }

    }

    @Override
    public List<Map> findUserQuestionByTeamIdAndOwnerId(Integer teamId, Integer ownerId, Integer type) {

        return this.userQuestionDao.findUserQuestionByTeamIdAndOwnerId(teamId, ownerId, type);
    }
	
	
	/*public TeamQuestionOptions getTeamQuestionOptionsByQuestionUuIdAndOwnerId(Integer teamId, Integer ownerId, String questionUuId)
	  {
	    TeamQuestionOptions tqo = new TeamQuestionOptions();

	    UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
	    userQuestionCondition.setTeamId(teamId);
	    userQuestionCondition.setOwnerId(ownerId);
	    userQuestionCondition.setQuestionUuid(questionUuId);

	    userQuestionCondition.setAnswer("[\"A\"]");

	    Long aCount = this.userQuestionDao.count(userQuestionCondition);

	    userQuestionCondition.setAnswer("[\"B\"]");

	    Long bCount = this.userQuestionDao.count(userQuestionCondition);

	    userQuestionCondition.setAnswer("[\"C\"]");

	    Long cCount = this.userQuestionDao.count(userQuestionCondition);

	    userQuestionCondition.setAnswer("[\"D\"]");

	    Long dCount = this.userQuestionDao.count(userQuestionCondition);

	    userQuestionCondition.setAnswer("[]");

	    Long eCount = this.userQuestionDao.count(userQuestionCondition);

	    tqo.setaCount(aCount);
	    tqo.setbCount(bCount);
	    tqo.setcCount(cCount);
	    tqo.setdCount(dCount);
	    tqo.seteCount(eCount);
	    return tqo;
	  }*/

    /**
     * @param paperUuid
     * @param knoledgeId
     * @param userId
     * @return
     * @function 添加根据试卷 知识点ID 计算某学生的知识点得分
     */
    @Override
    public List<KnowledgeCountVo> findStudentAllScoreByKnoledgeId(String paperUuid, Integer[] knoledgeId, Integer userId, Integer ownerId, Integer type, Integer objId) {
        return userQuestionDao.findStudentAllScoreByKnoledgeId(paperUuid, knoledgeId, userId, ownerId, type, objId);
    }

    @Override
    public List<CognitionCountVo> findUserCognitionCount(Integer userId, String paperUuid, Integer ownerId, Integer objId, Integer type) {
        return userQuestionDao.findUserCognitionCount(userId, paperUuid, ownerId, objId, type);
    }

    @Override
    public List<PaperAnswerTime> findUserQuestionAnswerTimeByUserIdAndExamId(Integer userId, Integer teamId, Integer type, Integer ownerId, Integer objId) {

        return userQuestionDao.findUserQuestionAnswerTimeByUserIdAndOwnerId(userId, teamId, ownerId, type, objId);
    }


    @Override
    public void processUserAction(JsonNode answersList, Integer ownerId, Integer userId, String schoolYear, String termCode,
                                  Integer resourceType) {

        boolean flag = false;
        for (JsonNode answer : answersList) {
            String sub_answer = answer.path("answer").toString();
            String questionUuid = answer.path("uuid").getTextValue();
            Integer isCorrect = answer.path("isCorrect").getValueAsInt();
            //Integer answerTime = answer.path("answerTime").getValueAsInt();
            //统计用户行为,如果为空 就不统计了。
            if (!"[]".equals(sub_answer)) {
                //1.更新题库题目的答题统计
                if (isCorrect == 0) {
                    flag = true;
                }
                int answerCount = 1;
                PaQuestion question = paQuestionDao.findPaperQuestionByUuid(questionUuid);
                if (question != null) {
                    PaQuestionCatalogCondition pc = new PaQuestionCatalogCondition();
                    pc.setQuestionId(question.getId());
                    List<PaQuestionCatalog> pclist = paQuestionCatalogService.findPaQuestionCatalogByCondition(pc);

                    String subjectCode = pclist.get(0).getSubjectCode();
                    if (question.getAnswerCount() != null) {
                        answerCount = answerCount + 1;
                    }
                    question.setAnswerCount(answerCount);

                    //2. 如果答案正确更新题目正答数目

                    if (flag) {
                        int rightAnswerCount = 1;
                        if (question.getRightAnswerCount() != null) {
                            rightAnswerCount = rightAnswerCount + 1;
                        }
                        question.setRightAnswerCount(rightAnswerCount);
                    }

                    //3. 更新题目的真实难度
                    float difficulity = 0.0f;
						/*if(question.getAnswerCount() != null && question.getAnswerCount() != 0){
						//	difficulity = question.getRightAnswerCount() / question.getAnswerCount();
						}*/
                    question.setDifficulity(difficulity);

                    //4. 如果题目有答题时间更新题库每题题目的答题时间统计
                    int totalTime = 0;
                    int totalTimeCount = 0;
                    float averageTime = 0;
                    if (question.getTotalTime() != null) {
                        UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
                        userQuestionCondition.setQuestionUuid(questionUuid);
                        userQuestionCondition.setOwnerId(ownerId);
                        List<UserQuestion> userQuestions = this.userQuestionDao.findUserQuestionByCondition(userQuestionCondition, null, null);
                        if (userQuestions != null && userQuestions.size() > 0) {
                            UserQuestion uQuestion = userQuestions.get(0);
                            totalTime = totalTime + uQuestion.getAnswerTime();
                            question.setTotalTime(totalTime);
                            if (question.getTotalTimeCount() != null) {
                                totalTimeCount = question.getTotalTimeCount() + 1;
                                question.setTotalTimeCount(totalTimeCount);
                            }

                        }
                        averageTime = totalTime / totalTimeCount;
                        question.setAverageTime(averageTime);
                    }
                    paQuestionDao.update(question);
                    // 5.生成用户答题行为记录
                    ActionCodeCondition actionCodeCondition = new ActionCodeCondition();
                    actionCodeCondition.setName("答题");
                    List<ActionCode> actionCodeList = this.actionCodeDao.findActionCodeByCondition(actionCodeCondition, null, null);
                    if (actionCodeList != null && actionCodeList.size() > 0) {
                        ActionCode actionCode = actionCodeList.get(0);
                        UserAction userAction = new UserAction();
                        userAction.setUserId(userId);
                        userAction.setResourceType(resourceType);

                        //改为 pa_question表的uuid
                        userAction.setResourceUuid(questionUuid);
                        userAction.setActionId(actionCode.getId());
                        userAction.setActionScore(actionCode.getScore());
                        userAction.setSubjectCode(subjectCode);

//							List<PaQuestionKnoledge> pklist=paQuestionKnoledgeService.findByQuestionId(question.getId());
//							if(pklist!=null&&pklist.size()>0){
//								String[]codes=new String[pklist.size()];
//								int i=0;
//								for(PaQuestionKnoledge pk:pklist){
//									codes[i]=pk.getKnowledgeCode();
//									i++;
//								}
//								Map<String,Integer>map=new HashMap<String, Integer>();
//								List<KnowledgeNode>knlist=	knowledgeNodeService.findKnowledgeNodeByCodes(codes);
//								for(KnowledgeNode kn:knlist){
//									map.put(kn.getCode(), kn.getId());
//								}
//								for(PaQuestionKnoledge pk:pklist){
//									Integer knowledgeId = map.get(pk.getKnowledgeCode());
//									userAction.setKnowledgeId(knowledgeId);
//									userAction.setSchoolYear(schoolYear);
//									userAction.setTermCode(termCode);
//									userAction.setIsDeleted(false);
//									userAction.setModifyDate(new Date());
//									if(isCorrect == 1){
//										userAction.setSucceeded(true);
//									}else{
//										userAction.setSucceeded(false);
//									}
//									userAction.setCreateDate(new Date());
//									this.userActionDao.create(userAction);
//								}
//							}
//							
//							float answerQuestionRatio = 0;
//							UserKnowledgeSummaryCondition userKnowledgeSummaryCondition = new UserKnowledgeSummaryCondition(); 
//							userKnowledgeSummaryCondition.setUserId(userId);
//							userKnowledgeSummaryCondition.setKnowledgeId(knowledgeId);
//							userKnowledgeSummaryCondition.setSubjectCode(subjectCode);
//							List<UserKnowledgeSummary> uKnowledgeSummaryList = this.userKnowledgeSummaryDao.findUserKnowledgeSummaryByCondition(userKnowledgeSummaryCondition,null,null);
//							UserKnowledgeSummary userKnowledgeSummary = null; 
//							if(uKnowledgeSummaryList != null && uKnowledgeSummaryList.size() > 0){
//								
//								UserActionCondition userActionCondition = new UserActionCondition();
//								userActionCondition.setKnowledgeId(knowledgeId);
//								userActionCondition.setSucceeded(true);
//								Long succeededCount = this.userActionDao.count(userActionCondition);
//								
//								UserActionCondition userActionCondition1 = new UserActionCondition();
//								userActionCondition1.setKnowledgeId(knowledgeId);
//								//userActionCondition.setSucceeded(false);
//								Long notSucceededCount = this.userActionDao.count(userActionCondition1);
//								userKnowledgeSummary = uKnowledgeSummaryList.get(0);
//								if(notSucceededCount != 0){
//									answerQuestionRatio =(float) succeededCount / notSucceededCount;
//									userKnowledgeSummary.setAnswerQuestionRatio(answerQuestionRatio);
//								}
//								this.userKnowledgeSummaryDao.update(userKnowledgeSummary);
//							}else{
//								userKnowledgeSummary = new UserKnowledgeSummary();
//								userKnowledgeSummary.setUserId(userId);
//								userKnowledgeSummary.setKnowledgeId(knowledgeId);
//								userKnowledgeSummary.setModifyDate(new Date());
//								userKnowledgeSummary.setSubjectCode(subjectCode);
//								userKnowledgeSummary.setAnswerQuestionRatio(answerQuestionRatio);
//								userKnowledgeSummary.setIsDeleted(false);
//								userKnowledgeSummary.setCreateDate(new Date());
//								this.userKnowledgeSummaryDao.create(userKnowledgeSummary);
//							}
//							
                    }
                }
            }
        }

    }

    public void setActionCodeDao(ActionCodeDao actionCodeDao) {
        this.actionCodeDao = actionCodeDao;
    }

    @Override
    public List<Map<String, Object>> findUserQuestionAnswerCount(Integer teamId, Integer ownerId, Integer type, Boolean isCorrect,
                                                                 String answer) {

        return userQuestionDao.findUserQuestionAnswerCount(teamId, ownerId, type, isCorrect, answer);
    }

    @Override
    public Long findUserQuestionScoreCount(Integer teamId, Integer ownerId, Integer type) {

        return userQuestionDao.findUserQuestionScoreCount(teamId, ownerId, type);
    }

    @Override
    public List<Map<String, Object>> findUserQuestionAnswerTime(Integer teamId, Integer ownerId, Integer type) {
        return userQuestionDao.findUserQuestionAnswerTime(teamId, ownerId, type);
    }

    @Override
    public List<Map<String, Object>> findUserQuestionUserAnswerCount(String paperUuid, Integer ownerId, Integer type,
                                                                     Boolean isCorrect, Integer teamId) {

        return userQuestionDao.findUserQuestionUserAnswerCount(paperUuid, ownerId, type, isCorrect, teamId);
    }

    @Override
    public void batchAnswer(List<UserQuestion> userQuestionsList, List<UserWrong> userWrongList) {

        if (userQuestionsList != null && userQuestionsList.size() > 0) {

            userQuestionDao.batchUserQuestion(userQuestionsList.toArray());
        }

        if (userWrongList != null && userWrongList.size() > 0) {

            userWrongDao.batchUserWrong(userWrongList.toArray());
        }

    }

    @Override
    public Boolean batchAddBigData(List<UserAction> userActionList, List<UserKnowledgeSummary> userKnowledgeSummaryList) {
        boolean flag = false;
        try {

            if (userActionList != null && userActionList.size() > 0) {

                this.userActionDao.batchAddUserAction(userActionList);
            }

            if (userKnowledgeSummaryList != null && userKnowledgeSummaryList.size() > 0) {

                this.userKnowledgeSummaryDao.batchAdduserKnowledgeSummary(userKnowledgeSummaryList);
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    @Override
    public void batchModifyBigData(List<UserKnowledgeSummary> userKnowledgeSummaryList) {

        if (userKnowledgeSummaryList != null && userKnowledgeSummaryList.size() > 0) {

            for (UserKnowledgeSummary userKnowledgeSummary : userKnowledgeSummaryList) {

                this.userKnowledgeSummaryDao.update(userKnowledgeSummary);
            }
        }

    }

    @Override
    public Map<String, UserQuestion> findMapByOwnerIdAndUserId(Integer ownerId, Integer userId, Integer objectId) {
        Map<String, UserQuestion> userQuestionMap = new HashMap<String, UserQuestion>();
        List<UserQuestion> userQuestions = this.userQuestionDao.findByOwnerIdByUserId(ownerId, userId, objectId);
        for (UserQuestion userQuestion : userQuestions) {
            userQuestionMap.put(userQuestion.getQuestionUuid(), userQuestion);
        }
        return userQuestionMap;
    }

    @Override
    public Map<String, UserQuestion> findMapByOwnerIdAndUserId(Integer ownerId, Integer objectId) {
        Map<String, UserQuestion> userQuestionMap = new HashMap<String, UserQuestion>();
        List<UserQuestion> userQuestions = this.userQuestionDao.findByOwnerIdByUserId(ownerId, null, objectId);
        if (userQuestions.size() == 0) {
            userQuestions = this.userQuestionDao.findByOwnerIdByUserId(ownerId, null, null);
        }
        for (UserQuestion userQuestion : userQuestions) {
            userQuestionMap.put(userQuestion.getUserId() + userQuestion.getQuestionUuid(), userQuestion);
        }
        return userQuestionMap;
    }

    @Override
    public Integer batchUpdateScoreAndIsCorrect(List<UserQuestion> userQuestions) {
        if (userQuestions != null && userQuestions.size() > 0) {
            return this.userQuestionDao.batchUpdateScoreAndIsCorrect(userQuestions);
        }
        return 0;
    }

    @Override
    public Map<String, UserQuestion> findQuestionListByUserQuestionUuidsAndOnwerId(Object[] list,
                                                                                   Integer ownerId, Integer type) {
        Map<String, UserQuestion> map = new HashMap<String, UserQuestion>();
        ;
        List<UserQuestion> userQuestionList = userQuestionDao.findUserQuestionListByQuestionUuids(list, ownerId, type);
        if (userQuestionList != null && userQuestionList.size() > 0) {
            for (UserQuestion userQuestion : userQuestionList) {

                map.put(userQuestion.getQuestionUuid(), userQuestion);
            }
        }
        return map;
    }

    @Override
    public Map<String, UserQuestion> findUserQuestionListByOwnerIdAndQuestionIds(List questionIds, Integer ownerId,
                                                                                 Integer userId, Integer unitId) {
        Map<String, UserQuestion> map = null;
        if (questionIds != null && questionIds.size() > 0) {

            List<UserQuestion> userQuestionList = this.userQuestionDao.findUserQuestionListByOwnerIdAndQuestionIds(questionIds.toArray(), ownerId, userId, unitId);
            if (userQuestionList != null && userQuestionList.size() > 0) {
                map = new HashMap<String, UserQuestion>();
                for (UserQuestion userQuestion : userQuestionList) {
                    map.put(userQuestion.getQuestionUuid(), userQuestion);
                }
            }

        }

        return map;
    }


    @Override
    public List<UserQuestion> findUserQuestionByOwnerIds(Integer[] ownerIds, Integer objectId, Integer type) {
        return userQuestionDao.findUserQuestionByOwnerIds(ownerIds, objectId, type);
    }

    @Override
    public void deleteByOwnerIdAndType(Integer taskId, Integer type) {
        userQuestionDao.deleteByOwnerIdAndType(taskId, type);

    }

    @Override
    public List<UserQuestionResult> findUserQuestionByPaperIdDemo(Integer paperId) {
        return userQuestionDao.findUserQuestionByPaperIdDemo(paperId);
    }

    @Override
    public Map<String, UserQuestion> findQuestionAnswerList(Integer taskId, Integer unitId, String questionUuid) {
        Map<String, UserQuestion> userQuestionMap = new HashMap<String, UserQuestion>();
        List<UserQuestion> userQuestions = this.userQuestionDao.findQuestionAnswerList(taskId, unitId, questionUuid);
        for (UserQuestion userQuestion : userQuestions) {
            userQuestionMap.put(userQuestion.getUserId() + userQuestion.getQuestionUuid(), userQuestion);
        }
        return userQuestionMap;
    }

    @Override
    public void deleteByNotInQuesitonUuids(String[] questionUuids) {

        userQuestionDao.deleteByNotInQuesitonUuids(questionUuids);

    }

    private boolean isAnwserBlank(String answer) {
        Boolean b = false;
        try {
            if (!answer.equals("[]")) {
                JSONArray ja = JSONArray.fromObject(answer);
                for (int i = 0; i < ja.size(); i++) {
                    JSONObject ob = (JSONObject) ja.get(i);
                    if (ob.get("answer").equals("[\"\"]") || ob.get("answer").equals("[]")) {

                    } else {
                        b = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {

        }

        return b;
    }

}