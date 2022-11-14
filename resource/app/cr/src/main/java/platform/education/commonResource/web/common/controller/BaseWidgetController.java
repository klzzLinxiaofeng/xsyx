package platform.education.commonResource.web.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.model.Stage;
import platform.education.generalcode.model.Textbook;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.generalcode.service.KnowledgeTreeService;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.service.ResTextbookService;
import platform.education.generalcode.service.StageService;
import platform.education.generalcode.service.TextbookCatalogService;
import platform.education.generalcode.service.TextbookService;
import platform.education.generalcode.service.TextbookVersionService;
import platform.education.generalcode.service.TextbookVolumnService;

/**
 * 导学案和试卷检索的基础组件
 * @author pengshoulian
 *
 */
@Controller
@RequestMapping("/base/widget")
public class BaseWidgetController {
	@Autowired
	@Qualifier("jcSubjectService")
	private platform.education.generalcode.service.SubjectService jcSubjectService;
	
	@Autowired
	@Qualifier("jcStageService")
	private StageService stageService;
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	@Qualifier("jcTextbookVersionService")
	private TextbookVersionService textbookVersionService;

	@Autowired
	@Qualifier("jcTextbookVolumnService")
	private TextbookVolumnService textbookVolumnService;

	@Autowired
	@Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;

	@Autowired
	@Qualifier("jcTextbookCatalogService")
	private TextbookCatalogService textbookCatalogService;

	@Autowired
	@Qualifier("jcTextbookService")
	private TextbookService textbookService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("subjectTeacherService")
	private SubjectTeacherService subjectTeacherService;

	@Autowired
	@Qualifier("knowledgeTreeService")
	private KnowledgeTreeService knowledgeTreeService;

	@Autowired
	@Qualifier("knowledgeNodeService")
	private KnowledgeNodeService knowledgeNodeService;

	@Autowired
	@Qualifier("resTextbookService")
	private ResTextbookService resTextbookService;
    
    @Autowired
   	@Qualifier("subjectService")
    private SubjectService subjectService;
    
    @Autowired
   	@Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;
    
    @Autowired
   	@Qualifier("teamService")
    private TeamService teamService;
    
    @Autowired
   	@Qualifier("subjectGradeService")
    private SubjectGradeService subjectGradeService;

	@Autowired
	@Qualifier("virtualTeamService")
	private VirtualTeamService virtualTeamService;

    /**获取当前用户所在学校的学段*/
	@RequestMapping(value = "/getStage")
	@ResponseBody
	public Object getStage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="key", required=false) String key,
			@CurrentUser UserInfo user) {
		/**获取用户的学段*/
		String[] stages = user.getStageCodes();
		List<Map<String, Object>> stageList = new ArrayList<Map<String, Object>>(stages.length);

		/**获取当前用户任教的学段(如果redis中有存储的记录，优先拿redis中存储的)*/
		String stageCode = getStageCode(user.getUserId(), user.getSchoolId(), key);

		for (int i = 0; i < stages.length; i++) {
			Map<String, Object> stageMap = new HashMap<String, Object>();
			/**获取学段信息*/
			Stage stage = stageService.findStageById(Integer.parseInt(stages[i]));
			stageMap.put("code", stage.getCode());
			/**设置学段的选择状态(用于展示默认的任教学段或者回显上一次的记录)*/
			if (stageCode != null && stageCode.equals(stage.getCode())) {
				stageMap.put("selected", true);
			} else {
				stageMap.put("selected", false);
			}
			/**如果教师没有任教学段和redis中没有记录，默认选择第一个*/
			if(stageCode==null && i == 0) {
				stageMap.put("selected", true);
			}
			stageMap.put("name", stage.getName());
			stageList.add(stageMap);
		}
		return stageList;
	}

	/**
	 * 根据学段获取学校的科目
	 * @param stageCode 学段的code
	 * @param key 自定义的key(用于redis存储和展示时回显上一次的浏览记录)
	 * @return
	 */
	@RequestMapping(value = "/getResSubject")
	@ResponseBody
	public Object getResSubject(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stageCode") Integer stageCode, @CurrentUser UserInfo user,
			@RequestParam(value="key", required=false) String key) {
		/**根据学段获取学校的科目列表*/
		List<Subject> subjectList = getSubjectListBySchoolAndStageCode(user.getSchoolId(), "" + stageCode);
		/**获取当前用户任教的科目(如果redis中有存储的记录，优先拿redis中存储的)*/
		String subjectCode = getSubjectCode(user.getUserId(), user.getSchoolId(), key);

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(subjectList.size());
		Integer index = 0;
		for (Subject subject : subjectList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", subject.getCode());
			map.put("name", subject.getName());
			/**设置科目的选择状态(用于展示默认的任教科目或者回显上一次的记录)*/
			if (subjectCode != null && subjectCode.equals(subject.getCode())) {
				map.put("selected", true);
			} else {
				map.put("selected", false);
			}
			/**如果教师没有任教科目和redis中没有记录，默认选择第一个*/
			if(subjectCode==null && index == 0) {
				map.put("selected", true);
			}
			index++;
			result.add(map);
		}
		return result;
	}

	/**
	 * 根据学段获取学校(公共)的科目(具体业务和getResSubject一样，这里不再次注释)
	 * @param stageCode
	 * @param user
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/getSubject")
	@ResponseBody
	public Object getSubject(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stageCode") Integer stageCode, @CurrentUser UserInfo user,
			@RequestParam(value="key", required=false) String key) {
		String stage = null;
		if(stageCode-0!=0) {
			stage = stageCode + "";
		}
		List<platform.education.generalcode.model.Subject> subjectList = jcSubjectService
				.findByStageCode(stage);
		String subjectCode = getSubjectCode(user.getUserId(), user.getSchoolId(), key);

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(subjectList.size());
		for (platform.education.generalcode.model.Subject subject : subjectList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", subject.getCode());
			map.put("name", subject.getName());
			if (subjectCode != null && subjectCode.equals("" + subject.getCode())) {
				map.put("selected", true);
			} else {
				map.put("selected", false);
			}
			result.add(map);
		}
		return result;
	}

	/**
	 * 根据学段和科目获取学校书籍
	 * @param stageCode 学段的code
	 * @param subjectCode 科目的code
	 * @return
	 */
	@RequestMapping(value = "/getResVersionAndVolumn")
	@ResponseBody
	public Object getResVersionAndVolumn(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stageCode") String stageCode, @RequestParam("subjectCode") String subjectCode,
			@CurrentUser UserInfo user) {
		/**根据学段和科目获取学校的书籍列表*/
		List<ResTextbook> resTextBooklist = resTextbookService.findResTextbookByStageAndSubject(stageCode, subjectCode, user.getSchoolId());

		/**年级集合*/
		Map<String, String> gradeMap = getGradeMap(user.getSchoolId(), stageCode);
		/**版本集合*/
		Map<String, String> versionMap = textbookVersionService.findAllVersionNameAsMap();
		/**册次集合*/
		Map<String, String> volumnMap = textbookVolumnService.findAllVolumnAsMap();

		/**result*/
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(resTextBooklist.size());
		/**get result*/
		for (ResTextbook resTextbook : resTextBooklist) {
			StringBuffer info = new StringBuffer();
			Map<String, Object> map = new HashMap<String, Object>(1);
			/**textbookId*/
			map.put("id", resTextbook.getId());
			/**获取学段名称*/
			String gradeName = gradeMap.get(resTextbook.getGradeCode());
			/**获取版本名称*/
			String versionName = versionMap.get(resTextbook.getVersion());
			/**获取册次名称*/
			String volumnName = volumnMap.get(resTextbook.getVolumn());
			/**非空判断并做数据拼装*/
			if (versionName != null) {
				info.append(versionName);
			}
			if (gradeName != null) {
				info.append(gradeName);
			}
			if (volumnName != null) {
				info.append(volumnName);
			}
			/**拼装后的版本年级册次*/
			map.put("info", info.toString());
			/**书名*/
			map.put("textbookName", resTextbook.getName());
			result.add(map);
		}

		return result;
	}

	/**
	 * 根据学段和科目获取学校(公共)的书籍(具体业务和getResVersionAndVolumn一样，这里不再次注释)
	 * @param stageCode
	 * @param subjectCode
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getVersionAndVolumn")
	@ResponseBody
	public Object getVersionAndVolumn(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stageCode") String stageCode, @RequestParam("subjectCode") String subjectCode,
			@CurrentUser UserInfo user) {
		List<Textbook> textBooklist = textbookService.findTextbookByStageAndSubject(stageCode, subjectCode);

		Map<String, String> gradeMap = getGradeMap(user.getSchoolId(), stageCode);
		Map<String, String> versionMap = textbookVersionService.findAllVersionNameAsMap();
		Map<String, String> volumnMap = textbookVolumnService.findAllVolumnAsMap();

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(textBooklist.size());
		for (Textbook textbook : textBooklist) {
			StringBuffer info = new StringBuffer();
			Map<String, Object> map = new HashMap<String, Object>(1);
			map.put("id", textbook.getId());
			String gradeName = gradeMap.get(textbook.getGradeCode());
			String versionName = versionMap.get(textbook.getVersion());
			String volumnName = volumnMap.get(textbook.getVolumn());
			if (versionName != null) {
				info.append(versionName);
			}
			if (gradeName != null) {
				info.append(gradeName);
			}
			if (volumnName != null) {
				info.append(volumnName);
			}
			map.put("info", info.toString());
			map.put("textbookName", textbook.getName());
			result.add(map);
		}

		return result;
	}

	@RequestMapping(value = "/getResCatalogTree")
	@ResponseBody
	public Object getResCatalogTree(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("textbookId") Integer textbookId, @CurrentUser UserInfo user) {
		Map<String, Object> result = resTextbookCatalogService.findResCatalogTreeByTextbookId(textbookId);
		return result;
	}
	
	@RequestMapping(value = "/getResCatalogByParentId")
	@ResponseBody
	public Object getResCatalogByParentId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("textbookId") Integer textbookId, @RequestParam("parentId") Integer parentId) {
		List<ResTextbookCatalog> catalogList = resTextbookCatalogService.findCatalogListByParentId(textbookId, parentId);
		return catalogList;
	}

	@RequestMapping(value = "/getKnowledgeTree")
	@ResponseBody
	public Object getKnowledgeTree(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("stageCode") String stageCode, @RequestParam("subjectCode") String subjectCode) {
		Map<String, Object> tree = knowledgeNodeService.findTreeByStageAndSubject(subjectCode, stageCode);

		return tree;
	}

	@RequestMapping(value = "/getCatalogTree")
	@ResponseBody
	public Object getCatalogTree(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("textbookId") Integer textbookId, @CurrentUser UserInfo user) {
		Map<String, Object> result = textbookCatalogService.findCatalogTreeByTextbookId(textbookId);
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveTrail")
	@ResponseBody
	public Object saveTrail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="stageCode",required=false) String stageCode,
			@RequestParam(value="subjectCode",required=false) String subjectCode,
			@RequestParam(value="key",required=false) String key,
			@CurrentUser UserInfo user) {
		/**存储到redis的学段的key*/
		String stageKey = "stage_code_" + user.getUserId();
		/**存储到redis的科目的key*/
		String subjectKey = "subject_code_" + user.getUserId();
		/**自定义部分的key， 如果不为空，则加上自定义的key*/
		if(key!=null && !"".equals(key)) {
			stageKey += key;
			subjectKey += key;
		}
		ValueOperations options = stringRedisTemplate.opsForValue();
		/**存储到redis*/
		options.set(stageKey, stageCode);
		options.set(subjectKey, subjectCode);
		return "success";
	}

	/**
	 *  获取教师任教 年级 班级 科目
	 * @param request
	 * @param response
	 * @param type
	 * @param gradeId
	 * @param teamId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getTeacherTeachByType")
	@ResponseBody
	public Object getTeacherTeach(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="gradeId",required=false) Integer gradeId,
			@RequestParam(value="teamId",required=false) Integer teamId,
			@CurrentUser UserInfo user) {
		if("grade".equals(type)) {
			//获取教师任教年级
			return teamTeacherService.getTeacherTeachGrade(user.getUserId(), user.getSchoolYear(), user.getSchoolId());
		} else if("team".equals(type)) {
			//获取教师任教班级
			return teamTeacherService.getTeacherTeachTeam(user.getUserId(), user.getSchoolYear(), gradeId);
		} else if("subject".equals(type)) {
			//获取教师任教科目
			return teamTeacherService.getTeacherTeachSubject(user.getUserId(), user.getSchoolYear(), gradeId, teamId);
		}
		return "success";
	}
	
	@RequestMapping(value = "/getSchoolGrade")
	@ResponseBody
	public Object getSchoolGrade(HttpServletRequest request, HttpServletResponse response,
			@CurrentUser UserInfo user) {
		List<Grade> gradeList = gradeService.findGradeBySchoolYear(user.getSchoolId(), user.getSchoolYear());
		Map<Object, Integer> gradeMap = new LinkedHashMap<Object, Integer>();
		
		for (Grade grade : gradeList) {
			 List<Team> list = teamService.findTeamOfGradeAndSchool(grade.getId(), user.getSchoolId());
			 if(list.size()==0){
				 continue;
			 }
			gradeMap.put(grade.getName(), grade.getId());
		}
	
		return gradeMap;
	}

	/**
	 *  通过年级ID获取班级信息
	 *  2019-11-29 增加虚拟走班数据
	 * @param request
	 * @param response
	 * @param gradeId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getTeamByGradeId")
	@ResponseBody
	public Object getTeamByGradeId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="gradeId",required=false) Integer gradeId,
			@CurrentUser UserInfo user) {
		List<Team> teamList = null;
		if(gradeId-0==0) {
			gradeId = null;
			teamList = teamService.findCurrentTeamOfSchoolAndYearNotExistCurrentClass(user.getSchoolId(), user.getSchoolYear(), null);
		} else {
			teamList = teamService.findTeamOfGradeAndSchool(gradeId, user.getSchoolId());
		}

		Map<Object, Integer> teamMap = new LinkedHashMap<Object, Integer>();
		
		for (Team team : teamList) {
			teamMap.put(team.getName(), team.getId());
		}
		return teamMap;
	}

	/**
	 *  通过年级ID获取班级信息
	 *  2019-11-29 增加虚拟走班数据
	 * @param request
	 * @param response
	 * @param gradeId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getTeamAndVTeamByGradeId")
	@ResponseBody
	public Object getTeamAndVTeamByGradeId(HttpServletRequest request, HttpServletResponse response,
								   @RequestParam(value="gradeId",required=false) Integer gradeId,
								   @CurrentUser UserInfo user) {
		List<Team> teamList = null;
		if(gradeId-0==0) {
			gradeId = null;
			teamList = teamService.findCurrentTeamOfSchoolAndYearNotExistCurrentClass(user.getSchoolId(), user.getSchoolYear(), null);
		} else {
			teamList = teamService.findTeamOfGradeAndSchool(gradeId, user.getSchoolId());
		}

		List<VirtualTeam> virtualTeams = virtualTeamService.findByGradeId(gradeId);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("teamMap",teamList);
		map.put("virtualMap",virtualTeams);
		return map;
	}

	/**
	 *  获取教师任教 年级 班级 科目
	 * @param request
	 * @param response
	 * @param type
	 * @param gradeId
	 * @param teamId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getTeacherTeachByTypeNew")
	@ResponseBody
	public Object getTeacherTeachNew(HttpServletRequest request, HttpServletResponse response,
								  @RequestParam(value="type",required=false) String type,
								  @RequestParam(value="gradeId",required=false) Integer gradeId,
								  @RequestParam(value="teamId",required=false) Integer teamId,
								  @CurrentUser UserInfo user) {
		//获取教师任教班级
		Map<Integer, Object> teacherTeachTeam = teamTeacherService.getTeacherTeachTeam(user.getUserId(), user.getSchoolYear(), gradeId);

		VirtualTeamCondition virtualTeamCondition = new VirtualTeamCondition();
		virtualTeamCondition.setGradeId(gradeId);
		virtualTeamCondition.setIsDeleted(false);
		virtualTeamCondition.setSchoolId(user.getSchoolId());
		List<VirtualTeam> virtualTeams = virtualTeamService.findVirtualTeamByCondition(virtualTeamCondition);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("teamMap",teacherTeachTeam);
		map.put("virtualMap",virtualTeams);
		return map;
	}
	
	@RequestMapping(value = "/getSubjectByGradeId")
	@ResponseBody
	public Object getSubjectByGradeId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="gradeId",required=false) String gradeId,
			@CurrentUser UserInfo user) {
		if("0".equals(gradeId)) {
			gradeId = null;
		}
		List<Subject> list = subjectService.findSubjectsOfSchool(user.getSchoolId());
		Map<String, Object> subjectGradeMap = new HashMap<String, Object>();
		
		for (Subject subject : list) {
			subjectGradeMap.put(subject.getCode(), subject.getName());
		}
		return subjectGradeMap;
	}
	
	/**
	 * 获取当前用户任教的学段(如果redis中有存储的记录，优先拿redis中存储的)
	 * @param userId 用户的id
	 * @param schoolId 学校的id
	 * @param key 自定义的key
	 * @return
	 */
	private String getStageCode(Integer userId, Integer schoolId, String key) {
		ValueOperations<?, ?> options = stringRedisTemplate.opsForValue();
		/**获取reids中存储的学段*/
		String stageCode = (String) options.get("stage_code_" + userId + key );
		/**如果redis中的学段为空*/
		if (stageCode == null) {
			TeamTeacherVo subjectTeacher = getTeamTeacherVoByUserIdAndSchoolId(userId, schoolId);
			if (subjectTeacher != null) {
				/**下面是获取学段信息*/
				Grade garde = gradeService.findGradeById(subjectTeacher.getGradeId());
				String gradeCode = garde.getUniGradeCode();
				String subjectCode = subjectTeacher.getSubjectCode();
				SubjectGrade subjectGrade = subjectGradeService.findSubjectGradeByGradeCodeAndSubjectCode(gradeCode, subjectCode, schoolId);
				if(subjectGrade!=null) {
					return subjectGrade.getStageCode();
				}
			}
		}
		return stageCode;
	}

	/**
	 * 获取老师的任教科目或者上一次点击的科目(优先获取上一次点击的科目)
	 * @param userId 用户的id
	 * @param schoolId 学校的id
	 * @param key 自定义的key
	 * @return
	 */
	private String getSubjectCode(Integer userId, Integer schoolId, String key) {
		ValueOperations<?, ?> options = stringRedisTemplate.opsForValue();
		/**从reids中获取上一次点击的科目*/
		String subjectCode = (String) options.get("subject_code_" + userId + key);
		/**如果上一次点击的科目为空，则获取老师任教科目*/
		if (subjectCode == null) {
			/**获取老师任教科目*/
			TeamTeacherVo teamTeacherVo = getTeamTeacherVoByUserIdAndSchoolId(userId, schoolId);
			if (teamTeacherVo != null) {
				subjectCode = teamTeacherVo.getSubjectCode();
			}
		}
		return subjectCode;
	}

	private TeamTeacherVo getTeamTeacherVoByUserIdAndSchoolId(Integer userId, Integer schoolId) {
		Integer teacherId = teacherService.findUnqiueTeacherId(userId, schoolId);
		
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setTeacherId(teacherId);
		teamTeacherCondition.setSchoolId(schoolId);
		teamTeacherCondition.setType(2);
		List<TeamTeacherVo> teamTeacherVoList = teamTeacherService.findTeamTeacherVoByCondition(teamTeacherCondition);

		if (teamTeacherVoList.size() > 0) {
			return teamTeacherVoList.get(0);
		}
		return null;
	}

	private List<Grade> getGradeListBySchoolAndStageCode(Integer schoolId, String stageCode) {
		GradeCondition gradeCondition = new GradeCondition();
		gradeCondition.setSchoolId(schoolId);
		gradeCondition.setStageCode(stageCode);
		List<Grade> subjectList = gradeService.findGradeByCondition(gradeCondition, null, null);
		return subjectList;
	}

	private Map<String, String> getGradeMap(Integer schoolId, String stageCode) {
		List<Grade> gradeList = getGradeListBySchoolAndStageCode(schoolId, stageCode);
		Map<String, String> map = new HashMap<String, String>(gradeList.size());

		for (Grade grade : gradeList) {
			map.put(grade.getUniGradeCode(), grade.getName());
		}
		return map;
	}

	/**
	 * 根据学段获取学校的科目列表
	 * @param schoolId 学校的id
	 * @param stageCode 学校的id
	 * @return
	 */
	private List<Subject> getSubjectListBySchoolAndStageCode(Integer schoolId, String stageCode) {
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setSchoolId(schoolId);
		if (!"0".equals(stageCode)) {
			subjectCondition.setStageCode(stageCode);
		}
		List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
		return subjectList;
	}
}
