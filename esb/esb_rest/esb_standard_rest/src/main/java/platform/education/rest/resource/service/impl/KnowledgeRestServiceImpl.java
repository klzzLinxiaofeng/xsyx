package platform.education.rest.resource.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalcode.model.KnowledgeNode;
import platform.education.generalcode.model.KnowledgeTree;
import platform.education.generalcode.service.JcCacheService;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.generalcode.service.KnowledgeTreeService;
import platform.education.generalcode.vo.KnowledgeNodeCondition;
import platform.education.generalcode.vo.KnowledgeTreeCondition;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.service.MicroLessonService;
import platform.education.paper.model.Question;
import platform.education.paper.model.QuestionJson;
import platform.education.paper.service.PaperQuestionService;
import platform.education.paper.service.PaperService;
import platform.education.paper.service.QuestionService;
import platform.education.paper.util.HtmlUtils;
import platform.education.paper.vo.QuestionCondition;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.CatalogResource;
import platform.education.resource.model.KnowledgeResourceSummary;
import platform.education.resource.model.Resource;
import platform.education.resource.model.UserAction;
import platform.education.resource.model.UserKnowledgeSummary;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.KnowledgeResourceSummaryService;
import platform.education.resource.service.ResKnowledgeResourceService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.service.UserActionService;
import platform.education.resource.service.UserKnowledgeSummaryService;
import platform.education.resource.vo.CatalogResourceCondition;
import platform.education.resource.vo.ResourceKnowledgeVo;
import platform.education.resource.vo.UserKnowledgeSummaryCondition;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.resource.service.KnowledgeRestService;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;

public class KnowledgeRestServiceImpl implements KnowledgeRestService {
    @Autowired
    @Qualifier("userKnowledgeSummaryService")
    private UserKnowledgeSummaryService userKnowledgeSummaryService;
    @Autowired
    @Qualifier("catalogResourceService")
    private CatalogResourceService catalogResourceService;
    @Autowired
    @Qualifier("microLessonService")
    private MicroLessonService microLessonService;
    @Autowired
    @Qualifier("entityFileService")
    private EntityFileService entityFileService;
    @Autowired
    @Qualifier("fileService")
    private FileService fileService;
    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;
    @Autowired
    @Qualifier("learningDesignService")
    private LearningDesignService learningDesignService;
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;
    @Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
    @Autowired
    @Qualifier("resourceService")
    private ResourceService resourceService;
    @Autowired
    @Qualifier("teamStudentService")
    private TeamStudentService teamStudentService;
    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
    @Autowired
    @Qualifier("knowledgeTreeService")
    private KnowledgeTreeService knowledgeTreeService;
    @Autowired
    @Qualifier("knowledgeNodeService")
    private KnowledgeNodeService knowledgeNodeService;
    @Autowired
    @Qualifier("resKnowledgeResourceService")
    private ResKnowledgeResourceService resKnowledgeResourceService;
    @Autowired
    @Qualifier("jcCacheService")
    private JcCacheService jcCacheService;
    @Autowired
    @Qualifier("userActionService")
    private UserActionService userActionService;
    @Autowired
    @Qualifier("schoolTermCurrentService")
    private SchoolTermCurrentService schoolTermCurrentService;
    @Autowired
    @Qualifier("knowledgeResourceSummaryService")
    private KnowledgeResourceSummaryService knowledgeResourceSummaryService;
    @Autowired
    @Qualifier("appEditionService")
    private AppEditionService appEditionService;
    @Autowired
    @Qualifier("paperQuestionService")
    private PaperQuestionService paperQuestionService;
    @Autowired
    @Qualifier("papaperService")
    private PaperService papaperService;

    @Override
    public Object studyLoad(Integer userId, String appKey,
                            HttpServletRequest request) {
        AppEdition app = this.appEditionService.findByAppKey(appKey);
        if (app == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("appkey不存在,请确认");
            info.setMsg("不存在该appKey");
            info.setParam("appKey");
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        }
        if (userId == null || appKey == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("userId,appKey不能为空,请确认");
            info.setMsg("userId,appKey不能为空");
            info.setParam("userId,appKey");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        /*
		 层次结构  subjectCode
		           知识点第一级
		        List<KnowledgeNode>(层次是根据level)
		 */
        Student s = this.studentService.findStudentByUserId(userId);
        Integer schoolId = s.getSchoolId();
        //拿到他学校的科目
        List<Subject> sblist = this.subjectService
                .findSubjectsOfSchool(schoolId);
        String stageCode = findStageCodeByUserId(userId);
        List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        List<Object> studyLoadList = new ArrayList<Object>();
        Map<String, Object> nodemap = new HashMap<String, Object>();
        Map<String, Object> knowmap = new HashMap<String, Object>();
        Map<String, Object> subject = new HashMap<String, Object>();

        Integer microCount = 0;
        Integer desginCount = 0;
        Integer questionCount = 0;
        if (sblist != null && sblist.size() > 0) {
            for (Subject sb : sblist) {
                //第一层  map.put(subjectName,treeList)
                treeList = new ArrayList<Map<String, Object>>();
                subject = new HashMap<String, Object>();
                KnowledgeTreeCondition tCondition = new KnowledgeTreeCondition();
                tCondition.setStageCode(stageCode);
                tCondition.setSubjectCode(sb.getCode());
                List<KnowledgeTree> treelist = this.knowledgeTreeService
                        .findKnowledgeTreeByCondition(tCondition);
                if (treelist != null && treelist.size() > 0) {
                    nodeList = new ArrayList<Map<String, Object>>();
                    //确定唯一
                    KnowledgeTree kn = treelist.get(0);
                    KnowledgeNodeCondition knCondition = new KnowledgeNodeCondition();
                    knCondition.setTreeId(kn.getId());
                    knCondition.setLevel(1);
                    List<KnowledgeNode> knlist = this.knowledgeNodeService.findKnowledgeNodeByCondition(knCondition);
                    if (knlist != null && knlist.size() > 0) {
                        for (KnowledgeNode n : knlist) {
                            //第二层  map.put("top",nodename)
                            //     map.put("knowledgeNode", nodeList);
                            nodeList = new ArrayList<Map<String, Object>>();
                            nodemap = new HashMap<String, Object>();
                            nodemap.put("top", n.getName());
                            List<KnowledgeNode> two = new ArrayList<KnowledgeNode>();
                            two = this.knowledgeNodeService.findKnowledgeNodeChiledByNodeId(n.getId(), false);
                            if (two != null && two.size() > 0) {
                                for (KnowledgeNode n1 : two) {
                                    knowmap = new HashMap<String, Object>();
                                    knowmap.put("title", n1.getName());
                                    knowmap.put("level", n1.getLevel());
                                    UserKnowledgeSummaryCondition ukCondition = new UserKnowledgeSummaryCondition();
                                    ukCondition.setKnowledgeId(n1.getId());
                                    ukCondition.setUserId(userId);
                                    Float rate = -1.00f;
                                    List<UserKnowledgeSummary> uslist = userKnowledgeSummaryService.findUserKnowledgeSummaryByCondition(ukCondition);
                                    if (uslist != null && uslist.size() > 0) {
                                        rate = uslist.get(0).getAnswerQuestionRatio();
                                    }
                                    KnowledgeResourceSummary rs = knowledgeResourceSummaryService.findKnowledgeResourceSummaryByNodeId(n1.getId());
                                    //获取某个知识点下面资源的数量，这张表不是时时更新的
                                    if (rs != null) {
                                        microCount = rs.getT1Count();
                                        //questionCount = rs.getT9Count();
                                        desginCount = rs.getT2Count();
                                    }
                                    List<Question> qlist = questionService.findQuestionByKnowledgeId(n1.getId());
                                    questionCount = qlist.size();
                                    
                                    knowmap.put("microCount", microCount);
                                    knowmap.put("desginCount", desginCount);
                                    knowmap.put("questionCount", questionCount);
                                    knowmap.put("nodeId", n1.getId());
                                    knowmap.put("rate", rate);
                                    nodeList.add(knowmap);
                                }
                            }
                            nodemap.put("knowledgeNode", nodeList);
                            treeList.add(nodemap);
                        }
                    }
                }
                subject.put("name", sb.getName());
                subject.put("tree", treeList);
                studyLoadList.add(subject);
            }
        }
        return new ResponseVo<Object>("0", studyLoadList);
    }

    private String findStageCodeByUserId(Integer userId) {
        Student s = this.studentService.findStudentByUserId(userId);
        TeamStudent st = this.teamStudentService.findUnique(s.getTeamId(),
                s.getId());
        Grade g = this.gradeService.findGradeById(st.getGradeId());
        return g.getStageCode();

    }

    private List<Integer> RandomOfNum(int size) {
        Random random = new Random();
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        Collections.shuffle(result);
        return result;
    }

    @Override
    public Object list(Integer userId, String subjectCode, String appKey, Integer maxSize, HttpServletRequest request) throws Exception {
        if (userId == null || appKey == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("userId,appKey不能为空,请确认");
            info.setMsg("userId,appKey不能为空");
            info.setParam("userId,appKey");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        if (maxSize <= 0) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("maxSize要大于0");
            info.setMsg("maxSize要大于0");
            info.setParam("maxSize");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        AppEdition app = this.appEditionService.findByAppKey(appKey);
        if (app == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("appkey不存在,请确认");
            info.setMsg("不存在该appKey");
            info.setParam("appKey");
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        }

        List<Map<String, Object>> microlist = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> designlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> ids = new ArrayList<String>();
        //通过userId找到学生的基本信息
        Student s = this.studentService.findStudentByUserId(userId);
        Integer schoolId = s.getSchoolId();
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
                .findSchoolTermCurrentBySchoolId(schoolId);
        TeamStudent ts = this.teamStudentService.findUnique(s.getTeamId(),
                s.getId());
        Grade g = this.gradeService.findGradeById(ts.getGradeId());

        UserKnowledgeSummaryCondition condition = new UserKnowledgeSummaryCondition();
//		UserActionCondition condition = new UserActionCondition();
        condition.setUserId(userId);
        condition.setSubjectCode(subjectCode);
//		condition.setSchoolYear(schoolTermCurrent.getSchoolYear());
//		condition.setTermCode(schoolTermCurrent.getSchoolTermCode());


        Order order = new Order();
        order.setProperty("create_date");
        order.setAscending(true);
        //找到学生对用户行为有关的知识点

        List<UserKnowledgeSummary> ukss = this.userKnowledgeSummaryService.findUserKnowledgeSummaryByCondition(condition, null, order);
        if (ukss != null && ukss.size() > 0) {
            for (UserKnowledgeSummary uks : ukss) {
                if (uks.getKnowledgeId() != null) {
                    ids.add(String.valueOf(uks.getKnowledgeId()));
                }
            }

        }

        String[] idstring = ids.toArray(new String[ids.size()]);
        microlist = findReCommendResourceByKnowledge(s.getSchoolId(), ResourceType.MICRO, idstring, g.getUniGradeCode(), subjectCode, maxSize);
        designlist = findReCommendResourceByKnowledge(s.getSchoolId(), ResourceType.LEARNING_DESIGN, idstring, g.getUniGradeCode(), subjectCode, maxSize);
        map = new HashMap<String, Object>();
        map.put("microLesson", microlist);
        map.put("desgin", designlist);
        return new ResponseVo<Object>("0", map);
    }

    @Override
    public Object questionList(Integer userId, String subjectCode, String appKey, Integer maxSize, HttpServletRequest request) {
        if (userId == null || appKey == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("userId,appKey不能为空,请确认");
            info.setMsg("userId,appKey不能为空");
            info.setParam("userId,appKey");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        AppEdition app = this.appEditionService.findByAppKey(appKey);
        if (app == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("appkey不存在,请确认");
            info.setMsg("不存在该appKey");
            info.setParam("appKey");
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        }
        if (maxSize <= 0) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("maxSize要大于0");
            info.setMsg("maxSize要大于0");
            info.setParam("maxSize");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        List<Object> examlist = new ArrayList<Object>();
        List<Question> qlist = new ArrayList<Question>();
        List<Question> qulist = new ArrayList<Question>();

        Student s = this.studentService.findStudentByUserId(userId);
       // Integer schoolId = s.getSchoolId();
       // SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);

        UserKnowledgeSummaryCondition condition = new UserKnowledgeSummaryCondition();
        condition.setUserId(userId);
        condition.setSubjectCode(subjectCode);
//		condition.setSchoolYear(schoolTermCurrent.getSchoolYear());
//		condition.setTermCode(schoolTermCurrent.getSchoolTermCode());
        Order order = new Order();
        order.setProperty("create_date");
        order.setAscending(true);
        List<UserKnowledgeSummary> ukss = this.userKnowledgeSummaryService.findUserKnowledgeSummaryByCondition(condition, null, order);
        Page page = null;
        QuestionCondition qCondition = null;
        //根据知识点去找题目
        if (ukss != null && ukss.size() > 0) {
            for (UserKnowledgeSummary uks : ukss) {
                qCondition = new QuestionCondition();
                qCondition.setKnowledgeId(uks.getKnowledgeId());
                qCondition.setSubjectCode(subjectCode);
                qulist = new ArrayList<Question>();
                page = new Page();
                page.setPageSize(maxSize);
                qulist = this.questionService.findQuestionByCondition(qCondition, page);
                if (qulist != null && qulist.size() > 0) {
                    for (Question question : qulist) {
                        if (qlist.size() < maxSize) {
                            qlist.add(question);
                        } else {
                            break;
                        }
                    }

                }
            }
        }
        List<Question> newlist = new ArrayList<Question>();
        //如果对应知识点没题目，根据科目随机拿题目给学生。
        if (qlist == null || qlist.size() <= 0) {
            QuestionCondition qc = new QuestionCondition();
            qc.setSubjectCode(subjectCode);
            qlist = this.questionService.findQuestionByCondition(qc);
        }
        //封装maxSize的题目，如果大于maxSize就随机推荐maxSize条，否则直接返回
        if (qlist != null && qlist.size() > maxSize) {
            List<Integer> randomlist = new ArrayList<Integer>();
            randomlist = RandomOfNum(qlist.size());
            for (int i = 0; i < maxSize; i++) {
                Question q = qlist.get(randomlist.get(i));
                newlist.add(q);
            }
        }else{
        	newlist = qlist;
        }
        if (newlist != null) {
            examlist = copyQuestion(newlist, s.getUserId());
        }
        return new ResponseVo<Object>("0", examlist);
    }

    @Override
    public Object KnowledgeOfType(Integer knowledgeId, Integer type,
                                  String appKey, Integer pageSize, Integer pageNumber,
                                  HttpServletRequest request) {
        AppEdition app = this.appEditionService.findByAppKey(appKey);
        if (app == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("appkey不存在,请确认");
            info.setMsg("不存在该appKey");
            info.setParam("appKey");
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        }
        if (knowledgeId == null || type == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("knowledgeId,type不能为空,请确认");
            info.setMsg("knowledgeId,type不能为空");
            info.setParam("knowledgeId,type");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        List<ResourceKnowledgeVo> list = new ArrayList<ResourceKnowledgeVo>();
        List<Object> knowlist = new ArrayList<Object>();
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrentPage(pageNumber);
        String[] idstring = new String[1];
        idstring[0] = String.valueOf(knowledgeId);
        list = this.resourceService.findResourceByKnowledge(type, idstring, page, null);
        if (list != null && list.size() > 0) {
            for (ResourceKnowledgeVo r : list) {
                String subjectName = "";
                Map<String, Object> map = new HashMap<String, Object>();
                Object object = jcCacheService.findUniqueByParam("jc_subject", "code", r.getSubjectCode(), "name");
                if (object != null) {
                    subjectName = object.toString();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
                map.put("title", r.getTitle());
                map.put("publishName", r.getUserName());
                map.put("subjectName", subjectName);
                map.put("createDate", sdf.format(r.getCreateDate()));

                if (type.intValue() == ResourceType.LEARNING_DESIGN.intValue()) {
                    LearningDesign ml = learningDesignService
                            .findLearningDesignByUuid(r.getObjectId());
                    if (ml != null) {
                        if (ml.getEntityId() != null) {
                            map.put("url", fileService.relativePath2HttpUrlByUUID(ml
                                    .getEntityId()));
                            map.put("iconUrl", "");
                            knowlist.add(map);
                        }
                    }
                } else {
                    MicroLesson ml = microLessonService.findMicroLessonByUuid(r
                            .getObjectId());
                    if (ml != null) {
                        String iconUrl = fileService.thumb2HttpUrlByUUID(ml
                                .getEntityId());
                        map.put("url", fileService.relativePath2HttpUrlByUUID(ml
                                .getEntityId()));
                        map.put("iconUrl", iconUrl);
                        knowlist.add(map);

                    }
                }
            }
        }
        return new ResponseVo<Object>("0", knowlist);
    }

    @Override
    public Object KnowledgeOfQuestion(Integer knowledgeId, String appKey,
                                      Integer pageSize, Integer pageNumber, HttpServletRequest request) {
        AppEdition app = this.appEditionService.findByAppKey(appKey);
        if (app == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("appkey不存在,请确认");
            info.setMsg("不存在该appKey");
            info.setParam("appKey");
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        }
        if (knowledgeId == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("knowledgeId不能为空,请确认");
            info.setMsg("knowledgeId不能为空");
            info.setParam("knowledgeId");
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrentPage(pageNumber);
//        QuestionCondition qc = new QuestionCondition();
//        qc.setKnowledgeId(knowledgeId);
//        List<Question> qlist = new ArrayList<Question>();
//        qlist = this.questionService.findQuestionByCondition(qc, page, null);
        List<Question> qlist = this.questionService.findQuestionByKnowledgeId(knowledgeId);
        List<Object> examlist = new ArrayList<Object>();
        if (qlist != null) {
            examlist = copyQuestion(qlist, null);
        }

        return new ResponseVo<Object>("0", examlist);
    }

    /**
     * 封装数据
     *
     * @param r
     * @param subjectCode
     * @return
     */
    private ResourceKnowledgeVo copyVo(Resource r, String subjectCode) {

        ResourceKnowledgeVo vo = new ResourceKnowledgeVo();
        vo.setCreateDate(r.getCreateDate());
        vo.setTitle(r.getTitle());
        vo.setUserId(r.getUserId());
        if (r.getUserName() != null) {
            vo.setUserName(r.getUserName());
        } else {
            vo.setUserName("");
        }
        vo.setSubjectCode(subjectCode);
        vo.setObjectId(r.getObjectId());
        return vo;
    }

    private List<Object> copyQuestion(List<Question> qlist, Integer userId) {
        String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
        List<Object> list = new ArrayList<Object>();
        Student s = studentService.findStudentByUserId(userId);
        for (Question q : qlist) {
        	String content = q.getContent();
        	if(content != null && !"".equals(content)){
        		String subjectName = "";
        		String errorRate = "0%";
        		Map<String, Object> map = new HashMap<String, Object>();
        		if (s != null && q.getSubjectCode() != null && !q.getSubjectCode().equals("")) {
        			Subject object = subjectService.findUnique(s.getSchoolId(), q.getSubjectCode());
        			if (object != null) {
        				subjectName = object.getName();
        			}
        		}
        		QuestionJson questionJson = new QuestionJson();
        		String paperAnswer = q.getAnswer();
				String correctAnswer = q.getCorrectAnswer();
        		paperAnswer = StringEscapeUtils.unescapeJava(paperAnswer);
				
			
				if(paperAnswer != null && !"".equals(paperAnswer)){
					paperAnswer = paperAnswer.trim();
					paperAnswer = paperAnswer.replaceAll("", "");
					paperAnswer = paperAnswer.replaceAll("&quot;","");
					paperAnswer = paperAnswer.replace("[", "");
					paperAnswer = paperAnswer.replace("]", "");
					//paperAnswer = StringEscapeUtils.unescapeJava(paperAnswer);
					
					/*if(!paperAnswer.contains("<img")){
						paperAnswer = paperAnswer.replace("\"", "");
					}*/
					correctAnswer = replaceDomain(correctAnswer,domain);
					paperAnswer = replaceDomain(paperAnswer,domain);
					String questionType = q.getQuestionType();
					try {
						BeanUtils.copyProperties(questionJson, q);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					if("radio".equals(questionType) || "trueOrFalse".equals(questionType) || "checkbox".equals(questionType)){
						String[] a = null;
						if(paperAnswer.contains("(")){
							a = paperAnswer.split("\",");
						}else{
							a = paperAnswer.split(",");
						}
						
						for(int i=0;i<a.length;i++){
							if(!a[i].contains("<img")){
							//paperAnswer = paperAnswer.replace("\"", "");
							a[i]= a[i].replace("\"", "");
							}
						}
						
						questionJson.setAnswer(a);
					}
				}
        		map.put("paperAnwser", questionJson.getAnswer());
        		map.put("correctAnswer",questionJson.getCorrectAnswer());
        		if (q.getGroupId() != null && !"".equals(q.getGroupId())) {
        			map.put("isComplex", 1);
        		} else {
        			map.put("isComplex", 0);
        		}
        		map.put("complexTitle", replaceDomain(q.getExtraContent(), domain));
        		if (q.getGroupTitle() != null) {
        			map.put("questionType", q.getQuestionType());
        		} else {
        			map.put("questionType", q.getQuestionType());
        		}
        		if (q.getExplanation() != null) {
        			map.put("explanation", replaceDomain(q.getExplanation(), domain));
        		} else {
        			map.put("explanation", "");
        		}
        		if (q.getKnowledge() != null) {
        			map.put("knowledge", q.getKnowledge());
        		} else {
        			map.put("knowledge", "");
        		}
        		if (q.getKnowledgeId() != null && userId != null) {
        			UserKnowledgeSummaryCondition ukc = new UserKnowledgeSummaryCondition();
        			ukc.setUserId(userId);
        			ukc.setKnowledgeId(q.getKnowledgeId());
        			List<UserKnowledgeSummary> uklist = userKnowledgeSummaryService.findUserKnowledgeSummaryByCondition(ukc);
        			if (uklist != null && uklist.size() > 0) {
        				errorRate = (1 - uklist.get(0).getAnswerQuestionRatio()) * 100 + "%";
        			}
        		}
        		map.put("qestionUuid", q.getQuestionUuid());
        		map.put("subjectName", subjectName);
        		map.put("content", replaceDomain(q.getContent(), domain));
        		map.put("difficulty", q.getDifficulity());
        		map.put("knowledge", q.getKnowledge());
        		map.put("errorRate", errorRate);
        		list.add(map);
        	}
        }
        return list;
    }

    /**
     * 根据年级码去拿资源
     *
     * @param GradeCode
     * @param type
     * @return
     */
    private List<ResourceKnowledgeVo> findResourceByGradeCode(String GradeCode, String subjectCode, Integer type, Page page) {
        List<ResourceKnowledgeVo> list = new ArrayList<ResourceKnowledgeVo>();
        CatalogResourceCondition crc = new CatalogResourceCondition();
        crc.setGradeCode(GradeCode);
        crc.setSubjectCode(subjectCode);
        crc.setResourceType(type);
        List<CatalogResource> crlist = this.catalogResourceService.findCatalogResourceByCondition(crc, page);
        if (crlist != null && crlist.size() > 0) {
            for (CatalogResource cr : crlist) {
                Resource r = new Resource();
                r = this.resourceService.findResourceById(cr.getResourceId());
                if (r != null) {
                    ResourceKnowledgeVo rvo = new ResourceKnowledgeVo();
                    rvo = copyVo(r, cr.getSubjectCode());
                    list.add(rvo);
                }
            }

        }
        return list;

    }

    /**
     * 根据知识点拿出num资源数据，如果知识点无资源，从资源库通过UniGradeCode去推荐。
     *
     * @param type
     * @param idstring
     * @param UniGradeCode
     * @return
     */
    private List<Map<String, Object>> findReCommendResourceByKnowledge(Integer schoolId, Integer type, String[] idstring, String UniGradeCode, String subjectCode, Integer maxSize) {
        boolean isFindCommonResource = false;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> objList = new ArrayList<Map<String, Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        //根据知识点的一个字符串组数去找资源
        Page page = new Page();
        page.setPageSize(maxSize);
        List<ResourceKnowledgeVo> rlist = null;
        if (idstring != null && idstring.length > 0) {
            rlist = this.resourceService.findResourceByKnowledge(type, idstring, page, null);
            if (rlist == null || rlist.size() <= 0) {
                isFindCommonResource = true;
            }
        } else {
            isFindCommonResource = true;
        }

        if (isFindCommonResource) {
            //根据年级码
            page = new Page();
            page.setPageSize(maxSize);
            rlist = findResourceByGradeCode(UniGradeCode, subjectCode, type, page);
        }
        //从集合随机拿出maxSize条记录,如果找到list小于maxSize，直接返回
        //------------------------------------
        List<ResourceKnowledgeVo> numList = new ArrayList<ResourceKnowledgeVo>();

        if (rlist != null && rlist.size() > 0) {
            List<Integer> randomlist = new ArrayList<Integer>();
            randomlist = RandomOfNum(rlist.size());
            for (int i = 0; i < rlist.size(); i++) {
                ResourceKnowledgeVo r = rlist.get(randomlist.get(i));
                numList.add(r);
            }
        }
        //------------------------------------

        //封装数据
        if (numList != null && numList.size() > 0) {
            for (ResourceKnowledgeVo r : numList) {
                String subjectName = "";
                map = new HashMap<String, Object>();
                map.put("title", r.getTitle());
                String entityId = "";
                //根据resource res_type和object_id确定下面的实体表找到entityId
                if (type.intValue() == ResourceType.MICRO) {
                    MicroLesson ml = microLessonService.findMicroLessonByUuid(r.getObjectId());
                    if (ml != null) {
                        entityId = ml.getEntityId();
                    }
                } else {
                    LearningDesign ml = learningDesignService.findLearningDesignByUuid(r.getObjectId());
                    if (ml != null) {
                        entityId = ml.getEntityId();
                    }
                }
                if (entityId != null && !"".equals(entityId)) {
                    if (schoolId != null && r.getSubjectCode() != null) {
                        Subject object = subjectService.findUnique(schoolId, r.getSubjectCode());
                        if (object != null) {
                            subjectName = object.getName();
                        }
                    }
                    String iconUrl = "";
                    iconUrl = fileService.thumb2HttpUrlByUUID(entityId);
                    map.put("url", fileService.relativePath2HttpUrlByUUID(entityId));
                    map.put("iconUrl", iconUrl);
                    map.put("subjectName", subjectName);
                    map.put("createDate", sdf.format(r.getCreateDate()));
                    map.put("publishName", r.getUserName());
                    objList.add(map);
                }
            }
        }
        return objList;
    }

    /**
     * @param content
     * @param domain
     * @return String    返回类型
     * @throws
     * @Title: replaceDomain
     * @author pantq
     * @Description: 如有图片转换为http图片格式
     */
    private String replaceDomain(String content, String domain) {
        if (content != null && !"".equals(content)) {
            if (content.contains("<img")) {
                //替换
                content = content.replace("${@}$", "");
                content = HtmlUtils.replaceHtmlTag(content, "img", "src", "src=\"" + domain + "", "\"");
            }

        }

        return content;
    }
}
