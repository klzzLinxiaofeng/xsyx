package platform.education.commonResource.web.paper.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.commonResource.serivce.StatisticService;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.util.IntToSmallChineseNumber;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.model.Stage;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.service.ResTextbookService;
import platform.education.generalcode.service.StageService;
import platform.education.micro.service.MiMicroPersonGroupService;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.model.PaPaperTree;
import platform.education.paper.model.PaQuestionCatalog;
import platform.education.paper.service.PaPaperCatalogService;
import platform.education.paper.service.PaPaperHandleService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaPaperTreeService;
import platform.education.paper.service.PaQuestionCatalogService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.TaskService;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.AssemblyGroup;
import platform.education.paper.vo.AssemblyGroupJson;
import platform.education.paper.vo.AssemblyPaQuestion;
import platform.education.paper.vo.AssemblyPaperJson;
import platform.education.paper.vo.AssemblyQuestion;
import platform.education.paper.vo.AssemblyQuestionJson;
import platform.education.paper.vo.AssemblySubject;
import platform.education.paper.vo.BasketGroupJson;
import platform.education.paper.vo.NodeType;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.utils.UUIDUtil;
import platform.service.storage.service.FileService;

@Controller
@RequestMapping("/paper/assembly/")
public class PaperAssemblyController {
	private static final Logger log = LoggerFactory.getLogger(PaperAssemblyController.class);
	@Resource
	private PaQuestionService paQuestionService;
	
	@Resource
	private TeamStudentService teamStudentService;
	
	@Resource
	private StatisticService statisticService;
	
	@Resource
	private PjExamService pjExamService;
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private TeamService teamService;
	
	@Resource
	private GradeService gradeService;
	
	@Resource
	private MiMicroPersonGroupService miMicroPersonGroupService;
	
	@Resource
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private TeamTeacherService teamTeacherService;
	
	@Resource
	private SubjectService subjectService;
	
	@Resource
	private PaPaperService paPaperService;
	
	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("jcStageService")
	private StageService stageService;
	
	@Autowired
	@Qualifier("paQuestionCatalogService")
	private PaQuestionCatalogService paQuestionCatalogService;
	
	@Autowired
	@Qualifier("paPaperHandleService")
	private PaPaperHandleService paPaperHandleService;
	
	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;
	
	@Autowired
	@Qualifier("resourceLibraryService")
	private ResourceLibraryService resourceLibraryService;
	
	@Autowired
	@Qualifier("paPaperTreeService")
	private PaPaperTreeService paPaperTreeService;
	
	@Autowired
	@Qualifier("paPaperCatalogService")
	private PaPaperCatalogService paPaperCatalogService;
	
	@Autowired
	@Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;
	
	@Autowired
	@Qualifier("resTextbookService")
	private ResTextbookService resTextbookService;
	
	private static String salt = "c4ff9ca7eb";
	
	private static String DIR="paper/assembly";
	
	/**进入组卷选题*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/basket/index")
	public String basketIndex(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="ownerModel", defaultValue="1")Integer ownerModel
		) throws JsonParseException, JsonMappingException, IOException {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		String assemblyPaperJson = (String) operations.get(salt + user.getUserId() + "assemblyPaperJson");
		
		if(assemblyPaperJson!=null) {
			/**解析json*/
			ObjectMapper mapper = new ObjectMapper();
			BasketGroupJson[] groups = mapper.readValue(assemblyPaperJson, BasketGroupJson[].class);
			BasketGroupJson group = groups[0];
			
			List<Integer> quesitonId = group.getQuestionId();
			/**如果groupName为空和group下没有题目，也算是没有记录*/
			if(group.getGroupName()=="" && quesitonId.size()==0) {
				model.addAttribute("hasRecord", false);
			} else {
				model.addAttribute("hasRecord", true);
				if(quesitonId.size()>0) {
					Integer questionId = group.getQuestionId().get(0);
					/**目录信息*/
					PaQuestionCatalog paQuestionCatalog = paQuestionCatalogService.findPaQuestionCatalogByQuestionId(questionId);
					/**正常情况*/
					if(paQuestionCatalog!=null) {
						/**学段信息*/
						String stageCode = paQuestionCatalog.getStageCode();
						model.addAttribute("stageCode", stageCode);
					} else {
						/**试卷编辑情况*/
						AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
						model.addAttribute("stageCode", assemblyPaper.getStageCode());
					}
				}
				
				/**直接返回对象用el进行解析*/
				model.addAttribute("assemblyPaperJson", assemblyPaperJson);
				/**返回json对象供js进行解析*/
				model.addAttribute("assemblyPaper", JSONArray.fromObject(assemblyPaperJson));
			}
		} else {
			model.addAttribute("hasRecord", false);
		}
		model.addAttribute("ownerModel", ownerModel);
		
		return DIR + "/paper_basket_index";
	}
	
	/**加入试题蓝*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/basket/add")
	@ResponseBody
	public String basketAdd(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="data", required=false)String data,
			@RequestParam(value="questionId", required=true)Integer questionId) {
		try {
			ValueOperations  operations =  stringRedisTemplate.opsForValue();

			Integer userId = user.getUserId();
			/**同步信息*/
			asyncInfo(userId, operations);
			
			/**获取试题详细信息*/
			PaQuestionVo paQuestionVo = paQuestionService.findPaQuestionAllInfoById(questionId);
			if(paQuestionVo != null) {
				/**把试题存储到redis*/
				operations.set(salt + userId + questionId, paQuestionVo);
				/**把组卷的试卷结构存储到redis*/
				operations.set(salt + userId + "assemblyPaperJson", data);
				
				/**获取子题目*/
				List<PaQuestionVo> children = paQuestionService.findPaperQuestionByParentId(paQuestionVo.getId());
				for (PaQuestionVo child : children) {
					/**存储子题目*/
					operations.set(salt + userId + "pid" + questionId +child.getId(), child);
				}
				asyncInfo(userId, operations);
			} else {
				log.error("试题已经被删除");
				return "fail";
			}
			
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**移出试题篮*/
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/basket/remove")
	@ResponseBody
	public String basketRemove(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="data", required=true)String data,
			@RequestParam(value="questionId", required=true)Integer questionId) {
		try {
			Integer userId = user.getUserId();
			/**把试题从redis中移除*/
			stringRedisTemplate.delete(salt + userId + questionId);
			/**获取子题目*/
			List<PaQuestionVo> children = getQuesitonChildren(userId, questionId);
			/**如果存在子题目*/
			if(children.size()>0) {
				/**移除子题目*/
				stringRedisTemplate.delete(salt + userId + "pid" + questionId + "*");
			}
			ValueOperations operations = stringRedisTemplate.opsForValue();
			/**存储新的组卷结构*/
			operations.set(salt + userId + "assemblyPaperJson", data);
			/**同步组卷信息*/
			asyncInfo(userId, operations);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**存储分组信息(也就是组卷结构)*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/group/save")
	@ResponseBody
	public String groupAdd(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="data", required=false)String data) {
		try {
			Integer userId = user.getUserId();
			ValueOperations operations = stringRedisTemplate.opsForValue();
			/**存储组卷结构*/
			operations.set(salt + user.getUserId() + "assemblyPaperJson", data);
			/**同步组卷信息*/
			asyncInfo(userId, operations);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**获取分组信息(也就是组卷结构)*/
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/group/get")
	@ResponseBody
	public Object getGroup(@CurrentUser UserInfo user, Model model) {
		try {
			Integer userId = user.getUserId();
			ValueOperations operations = stringRedisTemplate.opsForValue();
			/**获取组卷结构*/
			String assemblyPaperJson = (String) operations.get(salt + userId + "assemblyPaperJson");
			return assemblyPaperJson;
		} catch (Exception e) {
			return "fail";
		}
	}
	
	/**组卷中心*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/index")
	public String index(@CurrentUser UserInfo user, Model model
			) throws IllegalAccessException, InvocationTargetException, JsonParseException, JsonMappingException, IOException {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		/**组卷中心自己的结构*/
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		if(assemblyPaper==null) {
			assemblyPaper = asyncInfo(user.getUserId(), operations);
			model.addAttribute("assemblyPaper", assemblyPaper);
			return DIR + "/paper_assembly_index";
		}
		/**获取组卷的结构（使用加入试题篮时的结构作转换）*/
		String data = changeBasketJsonToCenterJson(user.getUserId());
		/**处理组卷参数*/
		List<AssemblyPaQuestion> result = handlerParameter(data, assemblyPaper, user.getUserId(), user.getSchoolId());
	
		operations.set(salt+user.getUserId()+"assemblyPaper", assemblyPaper);
		
		handleSubjectsAndCatalogsRelation(assemblyPaper, user.getUserId());
		
		model.addAttribute("assemblyPaper", assemblyPaper);
		model.addAttribute("result", result);

		return DIR + "/paper_assembly_index";
	}
	
	
	@SuppressWarnings({"rawtypes"})
	@RequestMapping(value = "/question/list")
	@ResponseBody
	public String questionList(@CurrentUser UserInfo user, Model model) throws IllegalAccessException, InvocationTargetException {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		List<AssemblyPaQuestion> result = handlerAssemblyPaQuestion(assemblyPaper, user.getUserId());
		handleResult(assemblyPaper, result);
		model.addAttribute("assemblyPaper", assemblyPaper);
		model.addAttribute("result", result);
		
		return DIR + "paper_assembly_list";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/question/remove")
	@ResponseBody
	public String removeQuestion(@CurrentUser UserInfo user,
			@RequestParam(value="uuid", required=false)String uuid,
			@RequestParam(value="data", required=false)String data) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		/**组卷中心结构转对象*/
		JSONObject object = JSONObject.fromObject(data);
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) JSONObject.toBean(object, AssemblyPaperJson.class);
		/**设置更新时间*/
		assemblyPaper.setModifyDate(new Date());
		operations.set(salt+user.getUserId()+"assemblyPaper", assemblyPaper);
		stringRedisTemplate.delete(salt+user.getUserId()+uuid);
		
		changeCenterJsonToBasketJson(user.getUserId());
		
		return "success";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveDraft")
	@ResponseBody
	public String saveDraft(@CurrentUser UserInfo user,
			@RequestParam(value="data", required=false)String data) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		JSONObject object = JSONObject.fromObject(data);
		/**新的组卷中心的结构*/
		AssemblyPaperJson newAssemblyPaper = (AssemblyPaperJson) JSONObject.toBean(object, AssemblyPaperJson.class);
		/**旧的组卷中心的结构*/
		AssemblyPaperJson ordAssemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		
		newAssemblyPaper.setCreateDate(new Date());
		newAssemblyPaper.setModifyDate(new Date());
		
		/**设置试卷的学段属性*/
		if(ordAssemblyPaper!=null) {
			newAssemblyPaper.setStageCode(ordAssemblyPaper.getStageCode());
			newAssemblyPaper.setStageName(ordAssemblyPaper.getStageName());
			newAssemblyPaper.setCreateDate(ordAssemblyPaper.getCreateDate());
			/**重新处理subjects和catalogs的关系(进行了属性设置后把试题移出了组卷中心会产生科目不一致，所以要重新组织这个关系)*/
			handleSubjectsAndCatalogsRelation(newAssemblyPaper, user.getUserId());
		}
		
		operations.set(salt+user.getUserId()+"assemblyPaper", newAssemblyPaper);
		/**同步试题篮和组卷中心的结构*/
		changeCenterJsonToBasketJson(user.getUserId());
		
		return "success";
	}
	
	@RequestMapping(value = "/remove")
	@ResponseBody
	public String removeAllKey(@CurrentUser UserInfo user) {
		boolean flag = removeFromRedisByUser(user.getUserId());
		if(flag) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/my/edit")
	@ResponseBody
	public Object myEdit(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="paperId", required=true)Integer paperId) throws JsonParseException, JsonMappingException, IllegalAccessException, InvocationTargetException, IOException  {
		PaPaper paper = paPaperService.findPaPaperById(paperId);
		if(paper==null) {
			log.error("该试卷已经不存在, 无法进行编辑操作");
			return "noExit";
		}
		
		boolean hasTask = taskService.getHasTaskStateByPaperId(paperId);
		if(hasTask) {
			log.error("该试卷已经被布置过，无法进行编辑操作");
			return "hasTask";
		}
		/**获取组卷的参数结构*/
		String param = getAssemblyParam(paperId, user.getUserId());
		
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		AssemblyPaperJson assemblyPaper = new AssemblyPaperJson();
		Date now = new Date();
		assemblyPaper.setCreateDate(paper.getCreateDate());
		assemblyPaper.setModifyDate(now);
		/**处理组卷的参数*/
		List<AssemblyPaQuestion> result = handlerParameter(param, assemblyPaper, user.getUserId(), user.getSchoolId());
		/**新建试卷的标题保持和久的一致*/
		assemblyPaper.setTitle(paper.getTitle());
		
		
		AssemblySubject[] subjects = assemblyPaper.getSubject();
		
		JSONArray catalogs = getOldCatalogs(paperId, subjects);
		JSONArray subjectArray = getOldSubjects(subjects);
		
		operations.set(salt+user.getUserId()+"catalogs", catalogs.toString());
		operations.set(salt+user.getUserId()+"subjects", subjectArray.toString());
		operations.set(salt + user.getUserId() + "type", paper.getOwnerMode());
		assemblyPaper.setSubject(subjects);
		/**把组卷信息存储的redis*/
		operations.set(salt+user.getUserId()+"assemblyPaper", assemblyPaper);
		
		/**删除原有的试卷*/
		paPaperService.removeAllPaperInfo(paper);
		
		model.addAttribute("assemblyPaper", assemblyPaper);
		model.addAttribute("result", result);
		
		return "success";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void handleSubjectsAndCatalogsRelation(AssemblyPaperJson newAssemblyPaper, Integer userId) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		AssemblyPaperJson ordAssemblyPaper = (AssemblyPaperJson) operations.get(salt + userId + "assemblyPaper");
		AssemblySubject[] oldAssemblySubject = ordAssemblyPaper.getSubject();
		
		/**如果原有科目有空的话，不作任何操作*/
		if(oldAssemblySubject==null || oldAssemblySubject.length==0) {
			return;
		}
		
		/**新的组卷信息*/
		AssemblySubject[] newAssemblySubject = newAssemblyPaper.getSubject();
		String catalogs = (String) operations.get(salt + userId + "catalogs");
		
		/**如果新的组卷信息的科目为空，则把试卷原有试卷的属性删除*/
		if(newAssemblySubject==null || newAssemblySubject.length==0) {
			stringRedisTemplate.delete(salt + userId + "subjects");
			stringRedisTemplate.delete(salt + userId + "catalogs");
			return;
		}
		
		JSONArray array = new JSONArray();
		for (AssemblySubject assemblySubject : newAssemblySubject) {
			JSONObject subject = new JSONObject();
			subject.accumulate("code", assemblySubject.getSubjectCode());
			subject.accumulate("name", assemblySubject.getSubjectName());
			array.add(subject);
		}
		
		if(array.size()>0) {
			operations.set(salt + userId + "subjects", JSONArray.fromObject(array).toString());
		}
		
		/**如果目录为空，则不进行继续的处理*/
		if(catalogs==null || "".equals(catalogs)) {
			return;
		}
		
		JSONArray catalogArray = JSONArray.fromObject(catalogs);
		JSONArray newCatalogArray = new JSONArray();
		
		/**处理科目和目录的关系*/
		for (int j = 0; j < oldAssemblySubject.length; j++) {
			AssemblySubject oldSubject = oldAssemblySubject[j];
			for (int i = 0; i < newAssemblySubject.length; i++) {
				AssemblySubject newSubject = newAssemblySubject[i];
				if(newSubject.getSubjectCode().equals(oldSubject.getSubjectCode())) {
					if(catalogArray.size()>=(j+1)) {
						newCatalogArray.add(catalogArray.getJSONObject(j));
					}
				}
			}
		}
		
		if(newCatalogArray.size()>0) {
			catalogs = newCatalogArray.toString();
			operations.set(salt + userId + "catalogs", catalogs);
		}
	}
	
	private JSONArray getOldSubjects(AssemblySubject[] subjects) {
		JSONArray subjectArray = new JSONArray();
		for (int i = 0; i < subjects.length; i++) {
			AssemblySubject subject = subjects[i];
			JSONObject entity = new JSONObject();
			entity.accumulate("name", subject.getSubjectName());
			entity.accumulate("code", subject.getSubjectCode());
			subjectArray.add(entity);
		}
		return subjectArray;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/my/create")
	@ResponseBody
	public Object myCreate(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="paperId", required=true)Integer paperId,
			@RequestParam(value="type", defaultValue="0")Integer type) throws JsonParseException, JsonMappingException, IllegalAccessException, InvocationTargetException, IOException  {
		PaPaper paper = paPaperService.findPaPaperById(paperId);
		if(paper==null) {
			log.error("该试卷已经不存在, 无法进行重新创建操作");
			return "noExit";
		}
		
		/**获取组卷的参数结构*/
		String param = getAssemblyParam(paperId, user.getUserId());
		
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		AssemblyPaperJson assemblyPaper = new AssemblyPaperJson();
		Date now = new Date();
		assemblyPaper.setCreateDate(now);
		assemblyPaper.setModifyDate(now);
		
		/**处理组卷的参数*/
		List<AssemblyPaQuestion> result = handlerParameter(param, assemblyPaper, user.getUserId(), user.getSchoolId());
		/**新建试卷的标题保持和久的一致*/
		assemblyPaper.setTitle(paper.getTitle());
		/**把组卷信息存储的redis*/
		operations.set(salt+user.getUserId()+"assemblyPaper", assemblyPaper);
		
		AssemblySubject[] subjects = assemblyPaper.getSubject();
		
		JSONArray catalogs = getOldCatalogs(paperId, subjects);
		JSONArray subjectArray = getOldSubjects(subjects);
		
		if(type-0==0) {
			operations.set(salt+user.getUserId()+"catalogs", catalogs.toString());
			operations.set(salt+user.getUserId()+"subjects", subjectArray.toString());
			operations.set(salt + user.getUserId() + "type", paper.getOwnerMode());
		}
		
		model.addAttribute("assemblyPaper", assemblyPaper);
		model.addAttribute("result", result);
		/**同步试题篮和组卷中心的结构*/
		changeCenterJsonToBasketJson(user.getUserId());
		
		return "success";
	}
	
	private JSONArray getOldCatalogs(Integer paperId, AssemblySubject[] subjects) {
		JSONArray catalogs = new JSONArray();
		for (int i = 0; i < subjects.length; i++) {
			AssemblySubject subject = subjects[i];
			String subjectCode = subject.getSubjectCode();
			PaPaperCatalog paPaperCatalog = paPaperCatalogService.findPaPaperCatalogByPaperIdAndSubjectCode(paperId, subjectCode);
			ResTextbookCatalog catalog = resTextbookCatalogService.findResTextbookCatalogByCode(paPaperCatalog.getCatalogCode());
			if(catalog==null) {
				continue;
			}
			
			JSONObject catalogJson = getCatalogParentById(catalog.getId(), catalog.getLevel());
			
			ResTextbook resTextbook = resTextbookService.findResTextbookById(catalog.getTestBookId());
			JSONObject item = new JSONObject();
			item.accumulate("id", resTextbook.getId());
			item.accumulate("name", resTextbook.getName());
			
			catalogJson.accumulate("book", item);
			catalogJson.accumulate("volumn", item);
			if(catalog.getLevel()-1==0) {
				catalogJson.accumulate("bookItem", getItem(null));		
			}
			if(catalog.getLevel()-0==0) {
				catalogJson.accumulate("bookItem", getItem(null));
				catalogJson.accumulate("bookSction", getItem(null));
			}
			catalogs.add(catalogJson);
		}
		return catalogs;
	}

	private JSONObject getCatalogParentById(Integer parentId, Integer level) {
		ResTextbookCatalog catalog = resTextbookCatalogService.findResTextbookCatalogById(parentId);
		if(level==2) {
			JSONObject bookItem = getItem(catalog);
			JSONObject catalogJson = getCatalogParentById(catalog.getParentId(), --level);
			catalogJson.accumulate("bookItem", bookItem);
			return catalogJson;
		} else if(level==1) {
			JSONObject bookSction = getItem(catalog);
			JSONObject catalogJson = getCatalogParentById(catalog.getParentId(), --level);
			catalogJson.accumulate("bookSction", bookSction);
			return catalogJson;
		} else if(level==0) {
			JSONObject bookUnit = getItem(catalog);
			JSONObject catalogJson = new JSONObject();
			return catalogJson.accumulate("bookUnit", bookUnit);
		}
		return new JSONObject();
	}
	
	private JSONObject getItem(ResTextbookCatalog catalog) {
		JSONObject item = new JSONObject();
		
		if(catalog!=null) {
			item.accumulate("name", catalog.getName());
			item.accumulate("code", catalog.getCode());
		} else {
			item.accumulate("name", "");
			item.accumulate("code", "");
		}
		return item;
	}

	/**
	 * 获取组卷的参数结构
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getAssemblyParam(Integer paperId, Integer userId) throws JsonParseException, JsonMappingException, IOException {
		ValueOperations operations =  stringRedisTemplate.opsForValue();
		List<PaPaperTree> paPaperTreeList = paPaperTreeService.findPaPaperTreeByPaperIdAndType(paperId, null);
		JSONArray array = new JSONArray();
		for (PaPaperTree paPaperTree : paPaperTreeList) {
			if(NodeType.GROUP-paPaperTree.getNodeType()==0) {
				JSONArray questionId = new JSONArray();
				for (PaPaperTree question : paPaperTreeList) {
					if(NodeType.QUESTION-question.getNodeType()!=0) {
						continue;
					}
					if(question.getParentId()-paPaperTree.getId()!=0) {
						continue;
					}
					questionId.add(question.getQuestionId());
				}
				JSONObject entity = new JSONObject();
				entity.accumulate("groupName", paPaperTree.getTitle());
				entity.accumulate("pos", paPaperTree.getPos());
				entity.accumulate("questionId", questionId);
				array.add(entity);
			}
		}
		
		/**试题篮的组卷结构*/
		operations.set(salt + userId + "assemblyPaperJson", array.toString());
		
		return changeBasketJsonToCenterJson(userId);
	}
	
	private boolean removeFromRedisByUser(Integer userId) {
		try {
			Set<String> keys = stringRedisTemplate.keys(salt + userId + "*");
			stringRedisTemplate.delete(keys);
			Iterator<String> its = keys.iterator();
			while (its.hasNext()) {
				String key = its.next();
				log.info("从redis移除： " + key);
			}
			return true;
		} catch(Exception e) {
			log.error("从redis移除userId=[" + userId + "]的组卷记录失败");
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/question/batchSetting")
	public String batchSetting(@CurrentUser UserInfo user,Model model,
			@RequestParam(value="type", defaultValue="center")String type) throws IllegalAccessException, InvocationTargetException, JsonParseException, JsonMappingException, IOException {
		
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		/**组卷中心自己的结构*/
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		
		/**获取组卷的结构（使用加入试题篮时的结构作转换）*/
		String data = changeBasketJsonToCenterJson(user.getUserId());
		/**处理组卷参数*/
		List<AssemblyPaQuestion> result = handlerParameter(data, assemblyPaper, user.getUserId(), user.getSchoolId());
		operations.set(salt+user.getUserId()+"assemblyPaper", assemblyPaper);
		
		handleSubjectsAndCatalogsRelation(assemblyPaper, user.getUserId());
		
		model.addAttribute("assemblyPaper", assemblyPaper);
		model.addAttribute("result", result);
		model.addAttribute("type", type);
		
		return DIR + "/paper_batch_setting";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/properties/setting")
	public String propertiesSetting(@CurrentUser UserInfo user, Model model){
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		/**组卷内容详细信息*/
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		/**组卷已经选择的属性信息(目录)*/
		String catalogs = (String) operations.get(salt + user.getUserId() + "catalogs");
		Integer type = (Integer) operations.get(salt + user.getUserId() + "type");
		if(type==null) {
			type=1;
		}
		if(catalogs!=null && !"".equals(catalogs)) {
			model.addAttribute("catalogs", JSONArray.fromObject(catalogs));
		} else {
			model.addAttribute("catalogs", new JSONArray());
		}
		model.addAttribute("assemblyPaper", assemblyPaper);
		model.addAttribute("type", type);
		return DIR + "/paper_assembly_properties";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/properties/check")
	@ResponseBody
	public Object propertiesCheck(@CurrentUser UserInfo user, Model model) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		/**组卷已经选择的属性信息(科目)*/
		String subjects = (String) operations.get(salt + user.getUserId() + "subjects");
		/**组卷已经选择的属性信息(目录)*/
		String catalogs = (String) operations.get(salt + user.getUserId() + "catalogs");
		
		boolean flag = check(subjects, catalogs);
		
		return flag;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/my/record/count")
	@ResponseBody
	public Object myRecord(@CurrentUser UserInfo user, Model model) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		if(assemblyPaper!=null) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/properties/save")
	@ResponseBody
	public Object propertiesSave(@CurrentUser UserInfo user, @RequestParam("catalogs")String catalogs,
			@RequestParam("subjects")String subjects,
			@RequestParam("type")Integer type){
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		/**组卷的属性信息(目录)*/
		operations.set(salt + user.getUserId() + "catalogs", catalogs);
		operations.set(salt + user.getUserId() + "subjects", subjects);
		operations.set(salt + user.getUserId() + "type", type);
		
		return "success";
	}
	
	@RequestMapping(value = "/my/index")
	public Object myIndex(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="index", defaultValue="index") String index,
			@RequestParam(value="type", defaultValue="record")String type
		) throws IllegalAccessException, InvocationTargetException{
		@SuppressWarnings("rawtypes")
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		
		model.addAttribute("assemblyPaper", assemblyPaper);
		
		/**获取我上传的试卷*/
		PaPaperCondition paPaperCondition = new PaPaperCondition();
		paPaperCondition.setUserId(user.getUserId());
		List<PaPaper> paperList = paPaperService.findPaPaperByCondition(paPaperCondition, new Page(), Order.desc("id"));
		
		/**循环判断试卷有没有被发布过*/
		List<PaPaperVo> result = new ArrayList<PaPaperVo>(paperList.size());
		for (PaPaper paPaper : paperList) {
			PaPaperVo vo = new PaPaperVo();
			/**属性拷贝*/
			BeanUtils.copyProperties(vo, paPaper);
			/**使用次数大于0*/
			if(paPaper.getUsedCount()>0) {
				vo.setHasTask(true);
			}
			result.add(vo);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("size", result.size());
		/**返回试题篮*/
		if("basket".equals(type)) {
			return DIR + "/paper_basket";
		}
		/**返回组卷记录*/
		if("list".equals(index)) {
			return DIR + "/paper_my_list";
		} else {
			return DIR + "/paper_my_index";
		}
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@CurrentUser UserInfo user, 
			@RequestParam(value="paperId", required=true)Integer paperId){
		PaPaper paper = paPaperService.findPaPaperById(paperId);
		if(paper==null) {
			log.error("试卷id=[" + paperId + "]在数据库的记录在空,无法进行删除操作");
			return "fail";
		}
		if(paper.getUsedCount()==0) {
			paPaperService.removeAllPaperInfo(paper);
		} else {
			log.error("试卷已经被使用过，无法删除");
			return "fail";
		}
		return "success";
	}
	
	private boolean check(String subjects, String catalogs) {
		if(subjects==null || subjects.trim().equals("null")) {
			return false;
		}
		if(catalogs==null || catalogs.trim().equals("null")) {
			return false;
		}
		JSONArray subjectArray = JSONArray.fromObject(subjects);
		JSONArray catalogArray = JSONArray.fromObject(catalogs);
		
		if(subjectArray.size()==0 || catalogArray.size()==0) {
			return false;
		}
		for (int i = 0; i < subjectArray.size(); i++) {
			JSONObject catalog = catalogArray.getJSONObject(i);
			if(catalog==null ){
				return false;
			} else {
				JSONObject bookUnit = catalog.getJSONObject("bookUnit");
				String name = bookUnit.getString("name");
				if(name==null || "".equals(name) || "无".equals(name)) {
					return false;
				}
				String code = bookUnit.getString("code");
				if(code==null || "".equals(code) || "0".equals(code)) {
					return false;
				}
			}
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/question/batchModify")
	@ResponseBody
	public String batchModify(@CurrentUser UserInfo user, Model model,
			@RequestParam("questions")String questions) throws IllegalAccessException, InvocationTargetException {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		JSONArray questionArray = JSONArray.fromObject(questions);
		
		for (int i = 0; i < questionArray.size(); i++) {
			JSONObject question = questionArray.getJSONObject(i);
			int questionId = question.getInt("id");
			/**redis的key*/
			String key = salt + user.getUserId() + questionId;
			PaQuestionVo vo = (PaQuestionVo) operations.get(salt + user.getUserId() + questionId);
			if(vo==null) {
				/**小题的情况*/
				Set<String> keys = stringRedisTemplate.keys(salt + user.getUserId() + "pid*" + questionId);
				Iterator<String> its = keys.iterator();
				while(its.hasNext()) {
					key = its.next();
					vo = (PaQuestionVo) operations.get(key);
				}
			}
			double score = question.getDouble("score");
			float  changeScore = (float)score;
			String questionType = question.getString("type");
			vo.setScore(changeScore);
			vo.setQuestionType(questionType);
			operations.set(key, vo);
		}
		
		/**复合题更新小题分数时同时更新题干分数*/
		updateScore(user.getUserId());
		
		return "success";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updateScore(Integer userId) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + userId + "assemblyPaper");
		AssemblyGroupJson[] assemblyGroupJsons = assemblyPaper.getGroups();
		
		for (AssemblyGroupJson assemblyGroupJson : assemblyGroupJsons) {
			AssemblyQuestionJson[] questionList = assemblyGroupJson.getQuestion();
			for (AssemblyQuestionJson assemblyQuestionJson : questionList) {
				PaQuestionVo paQuestionVo = (PaQuestionVo) operations.get(salt+ + userId + assemblyQuestionJson.getQuestionId());
				
				/**获取子题目*/
				List<PaQuestionVo> children =  getQuesitonChildren(userId, paQuestionVo.getId());
				if(children.size()>0) {
					Float score = 0.0F;
					for (PaQuestionVo child : children) {
						score += child.getScore();
					}
					/**更新题干的分组*/
					paQuestionVo.setScore(score);
					operations.set(salt+ + userId + paQuestionVo.getId(), paQuestionVo);
				}
			} 
		}
		operations.set(salt+ + userId + "assemblyPaper", assemblyPaper);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/finish")
	@ResponseBody
	public Object finish(@CurrentUser UserInfo user, Model model) throws IllegalAccessException, InvocationTargetException {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + user.getUserId() + "assemblyPaper");
		/**组卷已经选择的属性信息(科目)*/
		String subjects = (String) operations.get(salt + user.getUserId() + "subjects");
		/**组卷已经选择的属性信息(目录)*/
		String catalogs = (String) operations.get(salt + user.getUserId() + "catalogs");
		Integer type = (Integer) operations.get(salt + user.getUserId() + "type");
		/**设置试卷的属性*/
		Map<String, Object> paper = getPaperInfo(assemblyPaper, subjects, catalogs);
		/**试卷的分组和试题信息*/
		Map<String, Object> groupMap = new HashMap<String, Object>();
		
		if(assemblyPaper!=null) {
			List<AssemblyGroup> group = getGroupList(assemblyPaper, user.getUserId(), operations);
			groupMap.put("group", group);
		}
		/**整份试卷的信息*/
		Map<String, Object> document = new HashMap<String, Object>();
		document.put("paper", paper);
		document.put("body", groupMap);
		
		String schoolUuid = "";
		Integer relId = 0;
		/**用户所在学校信息*/
		School school = schoolService.findSchoolByUserId(user.getUserId());
		if(school!=null) {
			ResourceLibrary resourceLibrary = this.resourceLibraryService.findByAppIdAndOwnerId(SysContants.SYSTEM_SZXY_APP_ID, school.getId());
			schoolUuid = school.getUuid();
			relId = resourceLibrary.getId();
		}
		
		String content = JSONObject.fromObject(document).toString();
		
		try {
			paPaperHandleService.addPaperAndQuestion(user.getUserId(), content, null, "", schoolUuid, relId, type, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		updateUsedCount(assemblyPaper);
		
		removeFromRedisByUser(user.getUserId());
		
		return "success";
	}
	
	private void updateUsedCount(AssemblyPaperJson assemblyPaper) {
		if(assemblyPaper==null) {
			return;
		}
		List<Integer> questionList = new ArrayList<Integer>(); 
		AssemblyGroupJson[] assemblyGroupJsons = assemblyPaper.getGroups();
		for (AssemblyGroupJson assemblyGroupJson : assemblyGroupJsons) {
			AssemblyQuestionJson[] questions = assemblyGroupJson.getQuestion();
			for (AssemblyQuestionJson assemblyQuestionJson : questions) {
				questionList.add(assemblyQuestionJson.getQuestionId());
			}
		}
		
		paQuestionService.updateUsedByquestionIdList(questionList);
	}

	private Map<String, Object> getPaperInfo(AssemblyPaperJson assemblyPaper, String subjects, String catalogs) {
		Map<String, Object> paper = new HashMap<String, Object>();
		paper.put("id", UUIDUtil.getUUID());
		paper.put("title", assemblyPaper.getTitle());
		paper.put("type", assemblyPaper.getType());
		Map<String, Object> sections = new HashMap<String, Object>();
		sections.put("name", assemblyPaper.getStageName());
		sections.put("code", assemblyPaper.getStageCode());
		paper.put("sections", sections);
		paper.put("subjects", JSONArray.fromObject(subjects));
		JSONArray catalogsArray = JSONArray.fromObject(catalogs);
		for (int i = 0; i < catalogsArray.size(); i++) {
			catalogsArray.getJSONObject(i);
		}
		paper.put("catalogs", JSONArray.fromObject(catalogs));
		paper.put("knowledges", null);
		paper.put("memo", "");
		AssemblyGroupJson[] gruop = assemblyPaper.getGroups();
		paper.put("groupCount", gruop.length);
		paper.put("questionCount", assemblyPaper.getQuestionSize());
		paper.put("score", assemblyPaper.getTotalScore());
		paper.put("create_date", assemblyPaper.getCreateDate().getTime());
		
		if(gruop.length>0) {
			paper.put("hasgroup", true);
		} else {
			paper.put("hasgroup", false);
		}
		return paper;
	}
	
	@SuppressWarnings("rawtypes")
	private List<AssemblyGroup> getGroupList(AssemblyPaperJson assemblyPaper, Integer userId, ValueOperations operations) {
		AssemblyGroupJson[] assemblyGroupJsons = assemblyPaper.getGroups();
		List<AssemblyGroup> groups = new ArrayList<AssemblyGroup>(assemblyGroupJsons.length);
		
		for (AssemblyGroupJson assemblyGroup : assemblyGroupJsons) {
			AssemblyGroup group = getGroup(assemblyGroup);
			
			AssemblyQuestionJson[] assemblyQuestionJsons = assemblyGroup.getQuestion();
			List<AssemblyQuestion> questions = new ArrayList<AssemblyQuestion>(assemblyQuestionJsons.length);
			for (AssemblyQuestionJson assemblyQuestionJson : assemblyQuestionJsons) {
				Integer questionId = assemblyQuestionJson.getQuestionId();
				PaQuestionVo paQuestionVo = (PaQuestionVo) operations.get(salt+ + userId + questionId);
				if(paQuestionVo==null) {
					log.error("试题id[" + questionId + "]在数据库的记录不存在！");
					continue;
				}
				/**获取试题题目结构(和pc的结构一致)*/
				AssemblyQuestion assemblyQuestion = getQuestion(paQuestionVo, assemblyPaper.getSubject(), userId);
				questions.add(assemblyQuestion);
			}
			group.setQuestions(questions);
			groups.add(group);
		}
		/**设置题目序号*/
		int pos = 1;
		int num = 1;
		for (AssemblyGroup assemblyGroup : groups) {
			if ("group".equals(assemblyPaper.getModel())) {
				num = 1;
			}
			List<AssemblyQuestion> list = assemblyGroup.getQuestions();
			for (AssemblyQuestion assemblyQuestion : list) {
				List<AssemblyQuestion> children = assemblyQuestion.getQuestions();
				int size = children.size();
				if(size>0) {
					assemblyQuestion.setPos(0);
					assemblyQuestion.setNum(0);
					for (AssemblyQuestion child : children) {
						child.setPos(pos);
						child.setNum(num);
						num++;
						pos++;
					}
				} else {
					assemblyQuestion.setPos(pos);
					assemblyQuestion.setNum(num);
					pos++;
					num++;
				}
			}
		}
		
		return groups;
	}

	/**获取题目的结构*/
	private AssemblyQuestion getQuestion(PaQuestionVo paQuestionVo, AssemblySubject[] assemblySubjects, int userId) {
		/**试题详细信息设置*/
		AssemblyQuestion assemblyQuestion = new AssemblyQuestion();
		assemblyQuestion.setAnswer(stringToArray(paQuestionVo.getAnswer()));
		assemblyQuestion.setCognition(paQuestionVo.getCognition());
		assemblyQuestion.setContent(paQuestionVo.getContent());
		assemblyQuestion.setCorrectAnswer(stringToArray(paQuestionVo.getCorrectAnswer()));
		assemblyQuestion.setDifficulty(paQuestionVo.getDifficulity());
		assemblyQuestion.setExplanation(paQuestionVo.getExplanation());
		assemblyQuestion.setId(paQuestionVo.getUuid());
		assemblyQuestion.setIsSubjective(paQuestionVo.getQuestionProperty()+"");
		assemblyQuestion.setKnowledges(new String[0]);
		assemblyQuestion.setMemo(paQuestionVo.getMemo());
		assemblyQuestion.setNum(paQuestionVo.getNum());
		assemblyQuestion.setId(UUIDUtil.getUUID());
		
		/**获取子题目*/
		List<PaQuestionVo> children = getQuesitonChildren(userId, paQuestionVo.getId());
	
		/**子题目的结构*/
		List<AssemblyQuestion> questions = new ArrayList<AssemblyQuestion>(children.size());
		for (PaQuestionVo child : children) {
			 /**子题目是否还有子题目(递归)*/
			 AssemblyQuestion assemblyChild = getQuestion(child, assemblySubjects, userId);
			 questions.add(assemblyChild);
		}
		assemblyQuestion.setQuestions(questions);
		assemblyQuestion.setScore(paQuestionVo.getScore());
		assemblyQuestion.setType(paQuestionVo.getQuestionType());
		
		/**试题科目信息*/
		Map<String, Object> subject = new HashMap<String, Object>();
		String subjectCode = paQuestionVo.getSubjectCode();
		if(subjectCode==null || "".equals(subjectCode)) {
			subject.put("code", "");
			subject.put("name", "");
		} else {
			/**设置科目的name*/
			for (AssemblySubject assemblySubject : assemblySubjects) {
				if(assemblySubject.getSubjectCode().equals(subjectCode)) {
					subject.put("code", assemblySubject.getSubjectCode());
					subject.put("name", assemblySubject.getSubjectName());
				}
			}
		}
		/**设置试题科目信息*/
		assemblyQuestion.setSubject(subject);
		return assemblyQuestion;
	}

	/**获取分组的信息*/
	private AssemblyGroup getGroup(AssemblyGroupJson assemblyGroup) {
		AssemblyGroup group = new AssemblyGroup();
		group.setMemo("");
		group.setName(assemblyGroup.getGroupName());
		group.setPos(assemblyGroup.getPos());
		group.setScore(0f);
		return group;
	}
	
	@SuppressWarnings("rawtypes")
	private List<AssemblyPaQuestion> handlerAssemblyPaQuestion(AssemblyPaperJson assemblyPaper, Integer userId) throws IllegalAccessException, InvocationTargetException{
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		/**旧的组卷记录*/
		List<AssemblyPaQuestion> result = new ArrayList<AssemblyPaQuestion>();
		AssemblyGroupJson[] assemblyGroupJsons = assemblyPaper.getGroups();
		
		for (AssemblyGroupJson assemblyGroupJson : assemblyGroupJsons) {
			AssemblyPaQuestion entity = new AssemblyPaQuestion();
			entity.setGroupName(assemblyGroupJson.getGroupName());
			entity.setStrPos(assemblyGroupJson.getStrPos());
			
			Float score = 0.0f;
			AssemblyQuestionJson[] assemblyQuestionJsons = assemblyGroupJson.getQuestion();
			for (AssemblyQuestionJson assemblyQuestionJson : assemblyQuestionJsons) {
				PaQuestionVo paQuestionVo = (PaQuestionVo) operations.get(salt+ + userId + assemblyQuestionJson.getQuestionId());
				/**处理题目信息: 包括图片替换, 答案转数组 */
				handlerQuestion(paQuestionVo);
				
				/**获取子题目*/
				Set<String> keys = stringRedisTemplate.keys(salt + userId + "pid" + paQuestionVo.getId() + "*");
				Iterator<String> its = keys.iterator();
				while (its.hasNext()) {
					String key = its.next();
					PaQuestionVo child = (PaQuestionVo) operations.get(key);
					/**处理题目信息: 包括图片替换, 答案转数组 */
					handlerQuestion(child);
					paQuestionVo.getChildrenQuestionVo().add(child);
				}
				entity.getQuestionList().add(paQuestionVo);
				score+=paQuestionVo.getScore();
			}
			entity.setScore(score);
			result.add(entity);
			
		}
		return result;
	}
	
	private void handlePicture(PaQuestionVo paQuestionVo) {
		String correntAnswer = paQuestionVo.getCorrectAnswer();
		String answer = paQuestionVo.getAnswer();;
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
	
	private String handlerDifficulity(Float difficulity) {
		String difficulityStr = "未知";
		if(difficulity==null) {
			return difficulityStr;
		}
		if(difficulity<=0.2) {
			difficulityStr = "困难";
		}
		if(0.3<=difficulity && difficulity<=0.5) {
			difficulityStr = "中等";
		}
		if(difficulity>=0.6) {
			difficulityStr = "简单";
		}
		return difficulityStr;
	}
	
	private String[] stringToArray(String answer) {
		JSONArray array = JSONArray.fromObject(answer);
		String[] answers = new String[array.size()];
		
		for (int i = 0; i < array.size(); i++) {
			String str = array.getString(i);
			answers[i] = str;
		}
		return answers;
	}

	private String[] answerToArray(String answer) {
		if(answer==null || "".equals(answer)) {
			log.error("answer为空");
			return new String[]{""};
		}
		JSONArray array = JSONArray.fromObject(answer);
		String[] answers = new String[array.size()];
		
		for (int i = 0; i < array.size(); i++) {
			String str = array.getString(i);
			answers[i] = str;
		}
		return answers;
	}

	private String handlerContent(String content) {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		String[] contents = MqtPaperUtil.StringToArray(content, domain);
		
		StringBuffer anwserBuf = new StringBuffer();
		
		for (int i = 0; i < contents.length; i++) {
			String str = contents[i];
			anwserBuf.append(str);
		}
		
		return anwserBuf.toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String changeCenterJsonToBasketJson(Integer userId) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		/**组卷中心自己的结构*/
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + userId + "assemblyPaper");
		AssemblyGroupJson[] groupJson = assemblyPaper.getGroups();
		/**试题篮的试卷结构*/
		BasketGroupJson[] groups = new BasketGroupJson[groupJson.length];
		
		/**结构转换*/
		for (int i = 0; i < groupJson.length; i++) {
			AssemblyGroupJson assemblyGroupJson = groupJson[i];
			/**试题篮题组*/
			BasketGroupJson group = new BasketGroupJson();
			group.setGroupName(assemblyGroupJson.getGroupName());
			group.setPos(i);
			/**题组的题目总数*/
			Long questionSize = 0L;
			/**题目集合*/
			AssemblyQuestionJson[] assemblyQuestionJsons = assemblyGroupJson.getQuestion();
			List<Integer> questionIds = new ArrayList<Integer>();
			for (int j = 0; j < assemblyQuestionJsons.length; j++) {
				AssemblyQuestionJson assemblyQuestionJson = assemblyQuestionJsons[j];
				/**把题目入到试题篮的结构中*/
				questionIds.add(assemblyQuestionJson.getQuestionId());
				
				List<PaQuestionVo> children = getQuesitonChildren(userId, assemblyQuestionJson.getQuestionId());
				/**计算题目总数*/
				if(children!=null) {
					if(children.size()==0) {
						questionSize++;
					} else {
						questionSize+=children.size();
					}
				} else {
					questionSize++;
				}
			}
			group.setQuestionId(questionIds);
			group.setQuestionSize(questionSize);
			groups[i] = group;
		}
		/**把对象转json字符串*/
		String assemblyPaperJson = JSONArray.fromObject(groups).toString();
		operations.set(salt + userId + "assemblyPaperJson", assemblyPaperJson);
		return assemblyPaperJson;
	}
	
	@SuppressWarnings("rawtypes")
	private String changeBasketJsonToCenterJson(Integer userId) throws JsonParseException, JsonMappingException, IOException {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		/**试题篮的组卷结构*/
		String assemblyPaperJson = (String) operations.get(salt + userId + "assemblyPaperJson");
		ObjectMapper mapper = new ObjectMapper();
		/**反序列化成对象*/
		BasketGroupJson[] groups = mapper.readValue(assemblyPaperJson, BasketGroupJson[].class);
		/**组卷中心的试题结构*/
		AssemblyGroupJson[] groupJson = new AssemblyGroupJson[groups.length];
		/**中文排序*/
		/**结构转换*/
		for (int i = 0; i < groups.length; i++) {
			BasketGroupJson basketGroupJson = groups[i];
			/**题组信息*/
			AssemblyGroupJson group = new AssemblyGroupJson();
			group.setGroupName(basketGroupJson.getGroupName());
			group.setPos(i);
			group.setStrPos(IntToSmallChineseNumber.toCH(i));
			
			/**题目集合*/
			List<Integer> questionIds = basketGroupJson.getQuestionId();
			/**题目信息*/
			AssemblyQuestionJson[] assemblyQuestionJsons = new AssemblyQuestionJson[questionIds.size()];
			for (int j = 0; j < questionIds.size(); j++) {
				AssemblyQuestionJson assemblyQuestionJson = new AssemblyQuestionJson();
				assemblyQuestionJson.setChildSize(0);
				assemblyQuestionJson.setPos(j);
				assemblyQuestionJson.setQuestionId(questionIds.get(j));
				assemblyQuestionJson.setStrPos(IntToSmallChineseNumber.toCH(i));
				
				assemblyQuestionJsons[j] = assemblyQuestionJson;
			}
			group.setQuestion(assemblyQuestionJsons);
			groupJson[i] = group;
		}
		
		String paperJson = JSONArray.fromObject(groupJson).toString();
		
		return paperJson;
	}
	
	private void handleResult(AssemblyPaperJson assemblyPaper, List<AssemblyPaQuestion> result) {
		Float totalScore = 0.0f;
		Integer questionSize = 0;
		
		for (AssemblyPaQuestion assemblyPaQuestion : result) {
			totalScore+=assemblyPaQuestion.getScore();
			List<PaQuestionVo> paQuestionVoList = assemblyPaQuestion.getQuestionList();
			for (PaQuestionVo paQuestionVo : paQuestionVoList) {
				List<PaQuestionVo> list = paQuestionVo.getChildrenQuestionVo();
				if(list.size()==0) {
					questionSize++;
				} else {
					questionSize+=list.size();
				}
			}
			
		}
		assemblyPaper.setQuestionSize(questionSize);
		assemblyPaper.setTotalScore(totalScore);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<AssemblyPaQuestion> handlerParameter(String data, AssemblyPaperJson assemblyPaper, 
			Integer userId, Integer schoolId) throws IllegalAccessException, InvocationTargetException, JsonParseException, JsonMappingException, IOException {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		
		/**需要组卷的题目的集合*/
		List<AssemblyPaQuestion> result = new ArrayList<AssemblyPaQuestion>();
		ObjectMapper mapper = new ObjectMapper();
		
		/**总分*/
		Float totalScore = 0.0f;
		/**题目总数*/
		Integer questionSize = 0;
		/**学段*/
		String stageCode = null;
		/**解析参数*/
		AssemblyGroupJson[] groupJsons = mapper.readValue(data, AssemblyGroupJson[].class);
		/**获取学校的科目集合*/
		Map<String, String> subjectMap = subjectService.getSubjectMap(schoolId);
		/**组卷的科目及科目部分集合*/
		Map<String, AssemblySubject> assemblySubjectMap = new HashMap<String, AssemblySubject>();
		
		for (int i = 0; i < groupJsons.length; i++) {
			AssemblyGroupJson groupJson = groupJsons[i];
			groupJson.setStrPos(IntToSmallChineseNumber.toCH(i));
			/**试题的id集合*/
			AssemblyQuestionJson[] assemblyQuestionJsons = groupJson.getQuestion();
			/**结果集*/
			List<PaQuestionVo> resultList = new ArrayList<PaQuestionVo>();
			/**题组的总分*/
			Float score = 0.0f;
			
			for (AssemblyQuestionJson assemblyQuestionJson : assemblyQuestionJsons) {
				Integer questionId = assemblyQuestionJson.getQuestionId();
				/**题目的状态*/
				boolean changeState = false;
				/**先去redis中查询（重新选题的情况）*/
				PaQuestionVo paQuestionVo = (PaQuestionVo) operations.get(salt + userId + questionId);
				if(paQuestionVo==null) {
					changeState = true;
					/**如果redis不存在(原因，返回选题时把题目删除或者新加入试题篮的试题)，再到数据库查*/
					paQuestionVo = paQuestionService.findPaQuestionAllInfoById(questionId);
					if(paQuestionVo==null) {
						log.error("不存在此题目, 请检查重试");
						continue;
					}
				}
				/**将题目存储到redis*/
				operations.set(salt + userId + questionId, paQuestionVo);
				
				List<PaQuestionVo> children =  new ArrayList<PaQuestionVo>();
				/**试题信息已经改变(原因，返回选题时把题目删除或者新加入试题篮的试题)，子题目也相应改变*/
				if(changeState) {
					children = paQuestionService.findPaperQuestionByParentId(paQuestionVo.getId());
				} else {
					children = getQuesitonChildren(userId, questionId);
				}
				for (PaQuestionVo child : children) {
					operations.set(salt + userId + "pid" + questionId +child.getId(), child);
					/**处理题目信息: 包括图片替换, 答案转数组 */
					handlerQuestion(child);
					
				}
				paQuestionVo.setChildrenQuestionVo(children);
				
				/**子题目总数*/
				assemblyQuestionJson.setChildSize(children.size());
				/**获取组卷中心试卷结构*/
				AssemblyQuestionJson[] assemblyQuestionChilds = getAssemblyQuestionJson(children); 
				assemblyQuestionJson.setChildren(assemblyQuestionChilds);
				/**计算总分*/
				score+=paQuestionVo.getScore();
				/**questionId*/
				assemblyQuestionJson.setQuestionId(questionId);
				/**试卷的总分*/
				totalScore+=paQuestionVo.getScore();
				/**处理题目信息: 包括图片替换, 答案转数组 */
				handlerQuestion(paQuestionVo);
				
				/**计算题目数量*/
				if(children.size()==0) {
					questionSize++;
				} else {
					questionSize+=children.size();
				}
				/**处理科目信息(计算试卷的科目及科目的分数)*/
				String temp = handleAssemblySubjectMap(paQuestionVo, assemblySubjectMap, subjectMap, assemblyQuestionJson, userId);
				if(stageCode==null && !"".equals(temp)) {
					stageCode = temp;
				}
				
				resultList.add(paQuestionVo);
			}
			/**题组的model*/
			AssemblyPaQuestion entity = new AssemblyPaQuestion();
			entity.setScore(score);
			entity.setGroupName(groupJson.getGroupName());
			entity.setStrPos(IntToSmallChineseNumber.toCH(i));
			entity.setPos(i);
			entity.getQuestionList().addAll(resultList);
			result.add(entity);
		}
		/**组卷中心的试题详细信息*/
		assemblyPaper.setGroups(groupJsons);
		assemblyPaper.setQuestionSize(questionSize);
		assemblyPaper.setTotalScore(totalScore);
		assemblyPaper.setQuestionSize(questionSize);
		
		AssemblySubject[] assemblySubjectArray = new AssemblySubject[assemblySubjectMap.size()];
		Iterator<Entry<String, AssemblySubject>> its = assemblySubjectMap.entrySet().iterator();
		int index = 0;
		/**遍历设置科目信息*/
		while(its.hasNext()) {
			Entry<String, AssemblySubject> entry = its.next();
			AssemblySubject assemblySubject = entry.getValue();
			assemblySubjectArray[index] = assemblySubject;
			index++;
		}
		if(assemblySubjectArray.length>0) {
			assemblyPaper.setSubject(assemblySubjectArray);
		}
		
		/**设置学段信息*/
		if(stageCode!=null) {
			Stage stage = stageService.findStageByCode(stageCode);
			assemblyPaper.setStageCode(stageCode);
			assemblyPaper.setStageName(stage.getName());
		}
		
		/**是否有分组*/
		if("".equals(assemblyPaper.getGroups()[0].getGroupName())) {
			assemblyPaper.setHasGroup(0);
		} else {
			assemblyPaper.setHasGroup(1);
		}
		
		checkSubject(assemblySubjectArray, userId);
		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private List<PaQuestionVo> getQuesitonChildren(Integer userId, Integer parentId) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		List<PaQuestionVo> children =  new ArrayList<PaQuestionVo>();
		/**获取子题目*/
		Set<String> keys = stringRedisTemplate.keys(salt + userId + "pid" + parentId + "*");
		/**有序的集合*/
		HashSet<String> sortKey = new HashSet<String>(keys.size());
		Iterator<String> its = keys.iterator();
		/**把无序集合变有序*/
		while (its.hasNext()) {
			String key = its.next();
			sortKey.add(key);
		}
		/**遍历有序的key集合用于获取试题*/
		its = sortKey.iterator();
		while (its.hasNext()) {
			String key = its.next();
			PaQuestionVo child = (PaQuestionVo) operations.get(key);
			children.add(child);
		}
		return children;
	}

	private void handlerQuestion(PaQuestionVo paQuestionVo) {
		/**字符串转数组*/
		paQuestionVo.setAnswers(answerToArray(paQuestionVo.getAnswer()));
		/**字符串转数组*/
		paQuestionVo.setCorrectAnswers(answerToArray(paQuestionVo.getCorrectAnswer()));
		/**计算难易度*/
		paQuestionVo.setDifficulityString(handlerDifficulity(paQuestionVo.getDifficulity()));
		/**处理图片*/
		handlePicture(paQuestionVo);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void checkSubject(AssemblySubject[] assemblySubjectArray, Integer userId) {
		ValueOperations  operations =  stringRedisTemplate.opsForValue();
		String subjectsStr = (String) operations.get(salt + userId + "subjects");
		if(subjectsStr==null || ""==subjectsStr) {
			return;
		}
		
		String catalogsStr = (String) operations.get(salt + userId + "catalogs");
		
		JSONArray catalogs = null;
		JSONArray newCatalogs = new JSONArray();
		if(catalogsStr!=null && !"".equals(catalogsStr)) {
			catalogs = JSONArray.fromObject(catalogsStr);
		}
		
		JSONArray subjects = JSONArray.fromObject(subjectsStr);
		JSONArray newSubjects = new JSONArray();
		for (int i = 0; i < assemblySubjectArray.length; i++) {
			boolean needCatalog = true;
			boolean needSubject = true;
			AssemblySubject assemblySubject = assemblySubjectArray[i];
			String subjectCode = assemblySubject.getSubjectCode();
			for (int j = 0; j < subjects.size(); j++) {
				JSONObject subject = subjects.getJSONObject(j);
				String code = subject.getString("code");
				if(code.equals(subjectCode)) {
					needSubject = false;
					newSubjects.add(subject);
					if(catalogs == null || (j+1)>catalogs.size()) {
						continue;
					}
					if(catalogs!=null) {
						needCatalog = false;
						newCatalogs.add(catalogs.getJSONObject(j));
					}
				}
			}
			if(needSubject) {
				JSONObject subject = new JSONObject();
				subject.accumulate("code", subjectCode);
				subject.accumulate("name", assemblySubject.getSubjectName());
				newSubjects.add(subject);
			}
			if(needCatalog) {
				JSONObject catalog = new JSONObject();
				JSONObject book = new JSONObject();
				book.accumulate("id", "");
				book.accumulate("name", "");
				catalog.accumulate("book", book);
				catalog.accumulate("volumn", book);
				JSONObject item = getItem(null);
				catalog.accumulate("bookUnit", item);
				catalog.accumulate("bookSction", item);
				catalog.accumulate("bookItem", item);
				newCatalogs.add(catalog);
			}
		}
		
		System.err.println("subjects: " + newSubjects.toString());
		System.err.println("catalogs: " + newCatalogs.toString());
		
		if(newSubjects.size()>0) {
			operations.set(salt + userId + "subjects", newSubjects.toString());
		}
		if(newCatalogs.size()>0) {
			operations.set(salt + userId + "catalogs", newCatalogs.toString());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String handleAssemblySubjectMap(PaQuestionVo paQuestionVo, Map<String, AssemblySubject> assemblySubjectMap,
			Map<String, String> subjectMap, AssemblyQuestionJson assemblyQuestionJson, Integer userId) {
		ValueOperations options = stringRedisTemplate.opsForValue();
		String stageCode = "";
		
		/**题目和目录的关系*/
		PaQuestionCatalog paQuestionCatalog = (PaQuestionCatalog) options.get(salt + userId + "catalog" + paQuestionVo.getId());
		if(paQuestionCatalog==null) {
			paQuestionCatalog = paQuestionCatalogService.findPaQuestionCatalogByQuestionId(paQuestionVo.getId());
			if(paQuestionCatalog==null) {
				return "";
			} else {
				options.set(salt + userId + "catalog" + paQuestionVo.getId(), paQuestionCatalog);
			}
		}
		
		assemblyQuestionJson.setSubjectCode(paQuestionCatalog.getSubjectCode());
		/**试题科目信息*/
		stageCode = paQuestionCatalog.getStageCode();
		if(paQuestionCatalog.getQuestionId()-paQuestionVo.getId()==0) {
			String subjectCode = paQuestionCatalog.getSubjectCode();
			AssemblySubject assemblySubject = assemblySubjectMap.get(paQuestionCatalog.getSubjectCode());
			if(assemblySubject==null) {
				assemblySubject = new AssemblySubject();
				assemblySubject.setQuestionCount(1);
				assemblySubject.setScore(paQuestionVo.getScore());
				assemblySubject.setSubjectCode(subjectCode);
				assemblySubject.setSubjectName(subjectMap.get(subjectCode));
				assemblySubjectMap.put(subjectCode, assemblySubject);
			} else {
				assemblySubject.setQuestionCount(assemblySubject.getQuestionCount()+1);
				assemblySubject.setScore(paQuestionVo.getScore()+assemblySubject.getScore());
			}
		}
		return stageCode;
	}

	private AssemblyQuestionJson[] getAssemblyQuestionJson(List<PaQuestionVo> children) {
		AssemblyQuestionJson[] assemblyQuestionChilds = new AssemblyQuestionJson[children.size()];
		int index = 0;
		for (PaQuestionVo child : children) {
			AssemblyQuestionJson assemblyQuestionChild = new AssemblyQuestionJson();
			assemblyQuestionChild.setQuestionId(child.getId());
			assemblyQuestionChild.setPos(index);
			assemblyQuestionChild.setChildSize(0);
			assemblyQuestionChild.setStrPos(IntToSmallChineseNumber.toCH(index));
			assemblyQuestionChilds[index] = assemblyQuestionChild;
			index++;
		}
		return assemblyQuestionChilds;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private AssemblyPaperJson asyncInfo(Integer userId, ValueOperations operations) throws JsonParseException, JsonMappingException, IOException {
		/**组卷内容详细信息*/
		AssemblyPaperJson assemblyPaper = (AssemblyPaperJson) operations.get(salt + userId + "assemblyPaper");
		
		Date now = new Date();
		/**如果groupName为空和group下没有题目，也算是没有记录*/
		if(assemblyPaper==null) {
			assemblyPaper = new AssemblyPaperJson();
			assemblyPaper.setModel("global");
			assemblyPaper.setTitle("试卷一");
			assemblyPaper.setCreateDate(now);
			assemblyPaper.setModifyDate(now);
		} else {
			assemblyPaper.setModifyDate(now);
		}
		operations.set(salt + userId + "assemblyPaper", assemblyPaper);
		return assemblyPaper;
	}
	
}