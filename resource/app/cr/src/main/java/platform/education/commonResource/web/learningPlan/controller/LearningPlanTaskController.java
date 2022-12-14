package platform.education.commonResource.web.learningPlan.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.commonResource.serivce.StatisticService;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.UserIconUtil;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.generalcode.service.StageService;
import platform.education.learningDesign.model.*;
import platform.education.learningDesign.service.*;
import platform.education.learningDesign.vo.*;
import platform.education.paper.model.PaPaper;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaperHandleService;
import platform.education.paper.service.PaperQuestionService;
import platform.education.paper.service.UserQuestionService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.utils.UUIDUtil;
import platform.education.user.model.User;
import platform.service.storage.vo.FileResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/learningPlan/task")
public class LearningPlanTaskController extends BaseController {
	private static final String DIR = "learningplan";

	@Resource
	private LearningPlanService learningPlanService;

	@Resource
	private TeamStudentService teamStudentService;

	@Resource
	private PaperHandleService paperHandleService;

	@Resource
	private LpUnitService lpUnitService;

	@Resource
	private ResourceService resourceService;

	@Autowired
	@Qualifier("pjGroupStudentService")
	private PjGroupStudentService pjGroupStudentService;

	@Resource
	private LpTaskService lpTaskService;

	@Resource
	private LpUnitFileService lpUnitFileService;

	@Resource
	private LpTaskUserService lpTaskUserService;

	@Resource
	private LpTaskUnitUserService lpTaskUnitUserService;

	@Resource
	private LpCatelogService lpCatelogService;

	@Resource
	private LpTaskUserActivityService lpTaskUserActivityService;

	@Resource
	private LpTaskExamUnitService lpTaskExamUnitService;

	@Autowired
	@Qualifier("paperQuestionService")
	private PaperQuestionService paperQuestionService;

	@Autowired
	@Qualifier("userQuestionService")
	private UserQuestionService userQuestionService;

	@Autowired
	@Qualifier("jcStageService")
	private StageService stageService;

	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;

	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;

	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;

	@Autowired
	@Qualifier("pjExamService")
	private PjExamService pjExamService;

	@Autowired
	@Qualifier("gradeService")
	protected GradeService gradeService;

	@Autowired
	@Qualifier("personService")
	private PersonService personService;

	@Autowired
	@Qualifier("statisticService")
	private StatisticService statisticService;

	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;

	@Autowired
	@Qualifier("lpTaskLockService")
	private LpTaskLockService lpTaskLockService;

	@Autowired
	@Qualifier("taskUserActivityFilesService")
	private TaskUserActivityFilesService taskUserActivityFilesService;

	/**
	 * ??????????????????????????????????????????
	 *
	 * @param request
	 * @param response
	 * @param taskId
	 * @return
	 */

	@RequestMapping(value = "/activity/index")
	public ModelAndView activity(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId") Integer taskId) {
		ModelAndView model = new ModelAndView();
		LpTask task = lpTaskService.findLpTaskById(taskId);
		if (task != null) {
			/**???????????????*/
			LearningPlan lp = learningPlanService.findLearningPlanById(task.getLpId());
			/**?????????????????????*/
			model.addObject("title", lp.getTitle());
			/**?????????????????????????????????*/
			LpUnitCondition lpUnitCondition = new LpUnitCondition();
			lpUnitCondition.setLpId(lp.getId());
			lpUnitCondition.setUnitType(LpUnitType.ACTIVITY);
			List<LpUnit> lpUnitList = lpUnitService.findSpecialUnitByCondition(lpUnitCondition);
			/**????????????????????????*/
			model.addObject("lpUnitList", lpUnitList);
			/**??????taskid*/
			model.addObject("taskId", task.getId());
			model.addObject("unitSize", lpUnitList.size());
		}
		model.setViewName(DIR + "/task/lp_task_activity_index");
		return model;
	}

	/**
	 * ????????????????????????
	 *
	 * @param request
	 * @param response
	 * @param taskId
	 * @param unitId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/activity/list")
	public ModelAndView taskActivity(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam("taskId") String taskId, @RequestParam("unitId") String unitId,
									 @RequestParam(value = "userId", required = false) Integer userId,
									 @ModelAttribute("page") Page page) {
		ModelAndView model = new ModelAndView();
		LpTask lpTask = lpTaskService.findLpTaskById(Integer.parseInt(taskId));

		if (lpTask != null) {
			/**??????????????????????????????*/
			LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
			lpTaskUserActivityCondition.setTaskId(Integer.parseInt(taskId));
			lpTaskUserActivityCondition.setUserId(userId);
			lpTaskUserActivityCondition.setUnitId(Integer.parseInt(unitId));
			List<LpTaskUserActivity> lpTaskUserActivityList = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition, page, Order.desc("create_date"));

			model.addObject("userActivitySize", lpTaskUserActivityList.size());

			/**userId??????*/
			Integer[] userIds = new Integer[lpTaskUserActivityList.size()];

			/**??????userId*/
			for (int i = 0; i < lpTaskUserActivityList.size(); i++) {
				userIds[i] = lpTaskUserActivityList.get(i).getUserId();
			}

			/**??????????????????*/
			List<User> userList = new ArrayList<User>(userIds.length);

			if (userIds.length > 0) {
				userList = userService.findUserByIds(userIds);
			}

			/**??????person??????, key???userId??? value???person???map*/
			Map<Integer, Person> personMap = getPersons(userList);

			/**?????????????????????????????????????????????*/
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(lpTaskUserActivityList.size());
			for (LpTaskUserActivity lpTaskUserActivity : lpTaskUserActivityList) {
				Map<String, Object> student = new HashMap<String, Object>();

//				/**?????????????????????????????? files??????,???????????????????????????????????????????????????*/
//				String files = lpTaskUserActivity.getFiles();
//				List<String> fileList = new ArrayList<String>();
//				
//				if (files != null && !files.isEmpty()) {
//					String[] filesArray = files.split(",");
//
//					/** ???????????????????????????????????? */
//					for (String file : filesArray) {
//						FileResult result = fileService.findFileByUUID(file);
//						if (result.getHttpUrl() != null && !"".equals(result.getHttpUrl())) {
//							fileList.add(result.getHttpUrl());
//						}
//					}
//				}

				//??????????????????  ???TaskUserActivityFiles
				List<String> fileList = new ArrayList<String>();
				TaskUserActivityFilesCondition taskUserActivityFilesCondition = new TaskUserActivityFilesCondition();
				taskUserActivityFilesCondition.setIsDeleted(0);
				taskUserActivityFilesCondition.setTaskUserActivityId(lpTaskUserActivity.getId());
				List<TaskUserActivityFiles> taskUserActivityFilesList = this.taskUserActivityFilesService.findTaskUserActivityFilesByCondition(taskUserActivityFilesCondition);
				if (taskUserActivityFilesList != null && taskUserActivityFilesList.size() > 0) {
					for (TaskUserActivityFiles taskUserActivityFiles : taskUserActivityFilesList) {
						//??????
						String sourceFileUrl = null;
						FileResult sourceFileUuidResult = fileService.findFileByUUID(taskUserActivityFiles.getSourceFileUuid());
						if (sourceFileUuidResult.getHttpUrl() != null && !"".equals(sourceFileUuidResult.getHttpUrl())) {
							sourceFileUrl = sourceFileUuidResult.getHttpUrl();
						}

						String markedFileUrl = null;
						//??????????????????
						FileResult markedFileUuidResult = fileService.findFileByUUID(taskUserActivityFiles.getMarkedFileUuid());
						if (markedFileUuidResult.getHttpUrl() != null && !"".equals(markedFileUuidResult.getHttpUrl())) {
							markedFileUrl = markedFileUuidResult.getHttpUrl();
						}
						fileList.add(markedFileUrl == null ? sourceFileUrl : markedFileUrl);
					}
				}


				/**?????????????????????*/
				String imgUrl = UserIconUtil.getImgSrc(lpTaskUserActivity.getUserId(), request, profileService);
				Person person = personMap.get(lpTaskUserActivity.getUserId());

				/**???????????????????????????*/
				student.put("studentName", person.getName());
				student.put("userId", lpTaskUserActivity.getUserId());
				student.put("iconUrl", imgUrl);
				student.put("content", lpTaskUserActivity.getContent());
				student.put("files", fileList);
				student.put("createTime", lpTaskUserActivity.getCreateDate());
				student.put("id", lpTaskUserActivity.getId());

				list.add(student);
			}

			model.addObject("lpTaskUserActivityList", list);

		}
		LpUnit lpUnit = lpUnitService.findLpUnitById(Integer.parseInt(unitId));
		if (lpUnit != null) {
			model.addObject("content", lpUnit.getContent());
		}

		model.addObject("userId", userId);
		model.addObject("taskId", taskId);
		model.addObject("unitId", unitId);
		model.setViewName(DIR + "/task/lp_task_activity_list");
		return model;
	}

	/**
	 * ?????????????????????????????????????????????????????????
	 *
	 * @param request
	 * @param response
	 * @param unitId
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/unit/get")
	@ResponseBody
	public Object unitGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("unitId") Integer unitId, @RequestParam("taskId") Integer taskId) {
		LpUnit lpUnit = lpUnitService.findLpUnitById(unitId);
		LpTask lpTask = lpTaskService.findLpTaskById(taskId);

		Map<String, Object> map = new HashMap<String, Object>();
		if (lpUnit != null || lpTask != null) {
			/**???????????????????????????????????????*/
			LpTaskUnitUserCondition lpTaskUnitUserCondition = new LpTaskUnitUserCondition();
			lpTaskUnitUserCondition.setTaskId(taskId);
			lpTaskUnitUserCondition.setUnitId(unitId);
			List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(lpTaskUnitUserCondition);

			/**????????????*/
			Integer finishedCount = 0;
			for (int i = 0; i < lpTaskUnitUserList.size(); i++) {
				LpTaskUnitUser lpTaskUnitUser = lpTaskUnitUserList.get(i);
				/**????????????*/
				if (lpTaskUnitUser.getHasFinished()) {
					/**??????????????????*/
					finishedCount++;
				}
			}
			/**????????????*/
			map.put("finishedCount", finishedCount);
			/**???????????????*/
			map.put("unfinishCount", lpTask.getUserCount() - finishedCount);
			map.put("usedCount", lpTask.getUserCount());
			/**?????????id*/
			map.put("id", lpUnit.getId());
			/**????????????*/
			if (lpUnit.getContent() == null) {
				map.put("unitContent", "");
			} else {
				map.put("unitContent", lpUnit.getContent());
			}
		}
		return map;
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return DIR + "/task/lp_task_index";
	}

	/**
	 * ??????????????????????????????????????????????????????
	 *
	 * @param request
	 * @param response
	 * @param page
	 * @param order
	 * @param user
	 * @param teamId
	 * @param subjectCode
	 * @param gradeId
	 * @param title
	 * @param dm
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView findTaskVo(HttpServletRequest request, HttpServletResponse response,
								   @ModelAttribute("page") Page page, @ModelAttribute("order") Order order,
								   @CurrentUser UserInfo user, @RequestParam(value = "teamId", required = false) Integer teamId,
								   @RequestParam(value = "subjectCode", required = false) String subjectCode,
								   @RequestParam(value = "gradeId", required = false) Integer gradeId,
								   @RequestParam(value = "title", required = false) String title,
								   @RequestParam(value = "dm", required = false) String dm) {
		ModelAndView model = new ModelAndView();

		// ?????????????????????????????????
		List<TaskVo> lists = new ArrayList<TaskVo>();
		Integer code = null;
		if (subjectCode != null && !"".equals(subjectCode)) {
			code = Integer.parseInt(subjectCode);
		}

		List<TaskVo> list = new ArrayList<TaskVo>();
		if (gradeId != 0 && teamId == null) {
			List<Team> teamList = teamService.findTeamOfGradeAndSchool(gradeId, user.getSchoolId());
			Integer[] teams = new Integer[teamList.size()];
			for (int i = 0; i < teamList.size(); i++) {
				teams[i] = teamList.get(i).getId();
			}
			list = this.lpTaskService.findLpTaskVoByTeamIds(teams, code, title, user.getSchoolId(), page, null);
			int length = this.lpTaskService.findLpTaskVoByTeamIds(teams, code, title, user.getSchoolId(), null, null).size();
			page.init(length, page.getPageSize(), page.getCurrentPage());
		} else {
			list = this.lpTaskService.findLpTaskVo(teamId, code, title, user.getSchoolId(), page, null);
		}

		/**??????????????????*/
		Integer[] teamIds = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			teamIds[i] = list.get(i).getTeamId();
		}
		List<Team> teamList = teamService.findByIds(teamIds);
		/**??????????????????*/
		List<Subject> subjectList = subjectService.findSubjectsOfSchool(user.getSchoolId());

		for (TaskVo vo : list) {
			LpUnitCondition lpUnitCondition = new LpUnitCondition();
			lpUnitCondition.setLpId(vo.getLpId());
			lpUnitCondition.setUnitType(LpUnitType.ACTIVITY);
			Long unitSize = lpUnitService.count(lpUnitCondition);

			if (unitSize > 0) {
				vo.setHasActivity(true);
			} else {
				vo.setHasActivity(false);
			}

			/**????????????*/
			for (Team team : teamList) {
				if (team.getId() - vo.getTeamId() == 0) {
					vo.setTeamName(team.getName());
				}
			}

			User u = userService.findUserById(vo.getUserId());
			Person person = personService.findPersonById(u.getPersonId());
			vo.setUserName(person == null ? "" : person.getName());

			/**????????????????????? */
			for (Subject subject : subjectList) {
				if (subject.getCode().equals("" + vo.getSubjectCode())) {
					vo.setSubjectName(subject.getName());
				}
			}
			lists.add(vo);
		}
		model.addObject("list", lists);

		model.addObject("taskSize", lists.size());
		/**??????????????????*/
		model.setViewName(DIR + "/task/lp_task_list");
		model.addObject("userId", user.getUserId());
		model.addObject("teamId", teamId);
		model.addObject("subjectCode", subjectCode);
		model.addObject("gradeId", gradeId);
		model.addObject("dm", dm);

		return model;
	}

	@RequestMapping("/my/index")
	public String myIndex(HttpServletRequest request, HttpServletResponse response) {
		return DIR + "/publish/my_publish_index";
	}

	/**
	 * ?????????????????????
	 *
	 * @param request
	 * @param response
	 * @param page
	 * @param user
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/my/list")
	public ModelAndView mypublish(HttpServletRequest request, HttpServletResponse response,
								  @ModelAttribute("page") Page page,
								  @CurrentUser UserInfo user, @RequestParam(value = "title", required = false) String title,
								  @RequestParam(value = "subjectCode", required = false) String subjectCode) {

		ModelAndView model = new ModelAndView();
		/**?????????????????????????????????*/
		LpTaskCondition lpTaskCondition = new LpTaskCondition();
		lpTaskCondition.setUserId(user.getUserId());
		if (!"0".equals(subjectCode)) {
			lpTaskCondition.setSubjectCode(subjectCode);
		}
		lpTaskCondition.setTitle(title);
		List<TaskVo> voList = lpTaskService.findMyTaskByCondition(lpTaskCondition, page, Order.desc("create_date"));

		model.addObject("taskSize", voList.size());
		model.addObject("list", voList);
		model.addObject("subjectCode", subjectCode);

		model.setViewName(DIR + "/publish/my_publish_list");

		return model;
	}

	/***
	 * ?????????task????????????????????????
	 * @param request
	 * @param response
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/modify/view")
	public ModelAndView taskModifyView(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId") Integer taskId) {
		ModelAndView model = new ModelAndView();
		/**?????????????????????????????????*/
		LpTask task = lpTaskService.findLpTaskById(taskId);
		LpTaskCondition lpTaskCondition = new LpTaskCondition();
		lpTaskCondition.setUuid(task.getUuid());
		List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);

		StringBuffer buffer = new StringBuffer();

		/**???????????????????????????????????????*/
		for (LpTask lpTask : taskList) {
			/**??????????????????*/
			Team team = teamService.findTeamById(lpTask.getObjectId());
			/**???????????????????????????*/
			if (buffer == null || "".equals(buffer.toString())) {
				buffer.append(team.getName());
			} else {
				/**??????????????????????????????*/
				buffer.append("???" + team.getName());
			}
			model.addObject("title", lpTask.getTitle());
		}
		model.addObject("taskId", task.getId());
		model.addObject("teamNames", buffer.toString());
		model.setViewName(DIR + "/task/lp_task_modify");
		return model;
	}

	/***
	 * ??????????????????
	 * @param request
	 * @param response
	 * @param taskId
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/modify")
	@ResponseBody
	public Object taskModify(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId") Integer taskId, @RequestParam("title") String title) {
		/**???????????????????????????*/
		LpTask task = lpTaskService.findLpTaskById(taskId);
		LpTaskCondition lpTaskCondition = new LpTaskCondition();
		lpTaskCondition.setUuid(task.getUuid());
		List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);

		/**???????????????????????????????????????*/
		for (LpTask lpTask : taskList) {
			lpTask.setTitle(title);
			lpTaskService.modify(lpTask);
		}
		return "success";
	}

	/***
	 * ??????????????????????????????????????????
	 * @param request
	 * @param response
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/delete/view")
	public ModelAndView taskDeleteView(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId") Integer taskId) {
		ModelAndView model = new ModelAndView();
		/**?????????????????????????????????*/
		LpTask task = lpTaskService.findLpTaskById(taskId);
		LpTaskCondition lpTaskCondition = new LpTaskCondition();
		lpTaskCondition.setUuid(task.getUuid());
		List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);

		StringBuffer buffer = new StringBuffer();

		/**???????????????????????????????????????*/
		for (LpTask lpTask : taskList) {
			Team team = teamService.findTeamById(lpTask.getObjectId());
			/**???????????????????????????*/
			if (buffer == null || "".equals(buffer.toString())) {
				buffer.append(team.getName());
			} else {
				/**??????????????????????????????*/
				buffer.append("???" + team.getName());
			}
		}
		model.addObject("title", task.getTitle());
		model.addObject("taskId", task.getId());
		model.addObject("teamNames", buffer.toString());
		model.setViewName(DIR + "/task/lp_task_delete");
		return model;
	}

	/***
	 * ?????????????????????????????????
	 * @param request
	 * @param response
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object taskDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId") Integer taskId) {
		/**?????????????????????????????????*/
		LpTask task = lpTaskService.findLpTaskById(taskId);
		LpTaskCondition lpTaskCondition = new LpTaskCondition();
		lpTaskCondition.setUuid(task.getUuid());
		List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);

		/**????????????????????????????????????????????????????????????????????????????????????*/
		for (LpTask lpTask : taskList) {
			/**?????????????????????????????????*/
			lpTaskUnitUserService.removeByTaskId(lpTask.getId());
			/**???????????????????????????*/
			lpTaskUserService.removeByTaskId(lpTask.getId());
			/**????????????*/
			lpTaskService.remove(lpTask);
		}
		return "success";
	}

	/**
	 * ?????????????????????(??????)
	 *
	 * @param request
	 * @param response
	 * @param user
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user, @RequestParam("id") Integer id) {
		LpTask lpTask = lpTaskService.findLpTaskById(id);
		if (lpTask != null) {
			/**????????????????????????*/
			lpTaskUnitUserService.removeByTaskId(lpTask.getId());
			/**??????????????????*/
			lpTaskUserService.removeByTaskId(lpTask.getId());
			/**????????????*/
			lpTaskService.remove(lpTask);
		}

		return "success";
	}

	/**
	 * ????????????????????????????????????????????????
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/team/exit")
	@ResponseBody
	public Object exitTeams(HttpServletRequest request, HttpServletResponse response) {

		String teamIds = request.getParameter("teamIds");
		String lpId = request.getParameter("lpId");
		JSONArray teamArray = JSONArray.fromObject(teamIds);
		/**??????????????????????????????*/
		List<Integer> teamIdList = lpTaskService.findIsExitInTeams(Integer.parseInt(lpId), teamArray);
		/**????????????????????? ?????????????????????????????????*/
		StringBuffer buffer = new StringBuffer();
		for (Integer teamId : teamIdList) {
			Team team = teamService.findTeamById(teamId);
			/**???????????????????????????????????????????????????????????????????????????*/
			if (team != null && !buffer.toString().contains(team.getName())) {
				/**???????????????????????????*/
				if (buffer == null || "".equals(buffer.toString())) {
					buffer.append(team.getName());
				} else {
					/**??????????????????????????????*/
					buffer.append("???" + team.getName());
				}
			}
		}

		/**
		 * zhenxinghui 2018-11-12
		 * ????????????????????????
		 */
		String unitType = request.getParameter("unitType");
		String u = unitType.substring(1, unitType.length() - 1);
		String[] types = u.split(",");


		/**
		 * wuhongtao 2019-03-07
		 * ??????if????????????????????????????????????????????????
		 */
		if (types.length > 0 && !"".equals(types[0])) {
			LpCatelog catelog = new LpCatelog();
			LpCatelogCondition lpCatelogCondition = new LpCatelogCondition();
//		lpCatelogCondition.setLpId(Integer.parseInt(lpId));
			lpCatelogCondition.setLpId(0);
			lpCatelogCondition.setTitle("?????????");
			List<LpCatelog> catelogList = lpCatelogService.findLpCatelogByCondition(lpCatelogCondition);
			if (catelogList.size() == 0) {
				catelog.setLpId(0);
				catelog.setTitle("?????????");
				catelog = lpCatelogService.add(catelog);
			} else {
				catelog = catelogList.get(0);
			}

			for (int n = 0; n < types.length; n++) {
				if ("".equals(types[n])) {
					continue;
				}
				List<LpUnit> unitList = lpUnitService.findUnitListByLpIdAndUnitType(Integer.parseInt(lpId), Integer.parseInt(types[n]));
				if (unitList.size() == 0) {
					LpUnit lpUnit = new LpUnit();
					lpUnit.setLpId(0);
					lpUnit.setCatalogId(catelog.getId());
					if (LpUnitType.GROUP_ACTIVITY.equals(types[n])) {
						lpUnit.setTitle("????????????");
					} else if (LpUnitType.CONVERSATION.equals(types[n])) {
						lpUnit.setTitle("????????????");
					} else {
						lpUnit.setTitle(learningPlanService.findLearningPlanById(Integer.parseInt(lpId)).getTitle());
					}
					lpUnit.setListOrder(n);
					lpUnit.setUnitType(types[n]);
					lpUnitService.add(lpUnit);
				}
			}
		}
		/**???????????????*/
		Map<String, String> teamNames = new HashMap<String, String>();
		/**???????????????????????????????????????????????????????????????*/
		teamNames.put("size", buffer.length() + "");
		/**??????????????????????????????*/
		teamNames.put("teamNames", buffer.toString());
		return teamNames;
	}

	@RequestMapping(value = "/prepare")
	public String prepareLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user,
								@RequestParam(value = "jumpfrom", required = false, defaultValue = "other") String jumpfrom) {
		String lpId = request.getParameter("lpId");
		/**???????????????????????????(????????????)*/
		List<Map<String, Object>> myTeachList = getMyTeachTeam(user);
		/**??????????????????(???????????????)*/
		List<Map<String, Object>> allList = getAllTeam(user);

		request.setAttribute("allList", allList);
		request.setAttribute("myTeachList", myTeachList);
		request.setAttribute("lpId", lpId);
		request.setAttribute("jumpfrom", jumpfrom);

		// ????????????????????????
		List<LpCatelogVo> catelogs = lpCatelogService.findLpCatelogUnitByLpId(Integer.parseInt(lpId));
		request.setAttribute("catelogs", catelogs);

		//????????????????????? zhenxinghui 2018-11-10
		LearningPlan lp = learningPlanService.findLearningPlanById(Integer.parseInt(lpId));
		request.setAttribute("title", lp.getTitle());

		return DIR + "/publish/lp_publish";
	}

	@RequestMapping(value = "/publish")
	@ResponseBody
	public Object publishLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws ParseException, IOException {
		String data = request.getParameter("teamIds");
		/**????????????????????????*/
		JSONArray classList = JSONArray.fromObject(data);
		String lpId = request.getParameter("lpId");

		String uuid = UUIDUtil.getUUID();

		LearningPlan lp = learningPlanService.findLearningPlanById(Integer.parseInt(lpId));

		Map<String, String> map = null;

		List<LpUnit> lpUnitList = lpUnitService.findUnitListByLpIdAndUnitType(lp.getId(), Integer.parseInt(LpUnitType.EXAM));

		if (lpUnitList.size() > 0) {
			List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByUnitIds(lpUnitList);
			map = new HashMap<String, String>(lpUnitFileList.size());
			for (LpUnitFile lpUnitFile : lpUnitFileList) {
				PaPaper paPaper = paPaperService.findPaPaperByUUid(lpUnitFile.getObjectUuid());
				map.put(lpUnitFile.getLpUnitId() + "," + paPaper.getId(), UUIDUtil.getUUID());
			}
		}
		List<Integer> teamIds = new ArrayList<Integer>();
		/**???????????????userId???userName???list*/
		String taskUuid = "";
		for (int i = 0; i < classList.size(); i++) {
			Integer teamId = classList.getInt(i);
			teamIds.add(teamId);
			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			/**??????????????????????????????*/
			List<TeamStudentVo> studentList = teamStudentService.findTeamStudentByConditionStudent(teamStudentCondition, null, null);
			List<PjGroupStudentVo> studentVoList = pjGroupStudentService.findPjGroupStudentVoByTeamId(teamId, null);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(studentList.size());
//			for (TeamStudentVo teamStudentVo : studentList) {
			if (studentVoList.size() > 0) {
				for (PjGroupStudentVo groupStudentVo : studentVoList) {
					Map<String, Object> studentMap = new HashMap<String, Object>();
					studentMap.put("userId", groupStudentVo.getUserId() + "");
					studentMap.put("userName", groupStudentVo.getStudentName());
					studentMap.put("groupNumber", groupStudentVo.getGroupNumber());
					studentMap.put("isLeader", (groupStudentVo.getIsLeader() == null || !groupStudentVo.getIsLeader()) ? 0 : 1);
					list.add(studentMap);
				}
			}
			/**???????????????*/
			String unitType = request.getParameter("unitType");
			String[] types = null;
			if (unitType != null && unitType.length() > 2) {
				String u = unitType.substring(1, unitType.length() - 1);
				types = u.split(",");
			}
			LpTask task = learningPlanService.publish(lp, list, user.getUserId(), teamId, uuid, types);

			taskUuid = task.getUuid();

			// ?????????????????????
			String unitIds = request.getParameter("unitIds");
			if (unitIds != null && !unitIds.equals("")) {
				JSONArray unitList = JSONArray.fromObject(unitIds);
				LpTaskLock lpTaskLock = new LpTaskLock();
				for (int i1 = 0; i1 < unitList.size(); i1++) {
					Integer unitId = unitList.getInt(i1);
					//?????????null????????????????????????
					lpTaskLock.setId(null);
					lpTaskLock.setTaskId(task.getId());
					lpTaskLock.setUnitId(unitId);
					lpTaskLock.setIsLocked(true);
					lpTaskLock.setIsDeleted(false);
					lpTaskLockService.add(lpTaskLock);
				}
			}
		}
		if (map != null && map.size() > 0) {
			batchInitExamStatistics(teamIds, user.getSchoolId(), user.getTeacherId(), map, taskUuid);
		}
		/**????????????????????????*/
		if (lp.getUsedCount() != null) {
			lp.setUsedCount(lp.getUsedCount() + 1);
		} else {
			lp.setUsedCount(1);
		}

		learningPlanService.modify(lp);
		return "success";
	}

	/***
	 * ??????????????????????????????????????????
	 * @param request
	 * @param response
	 * @param taskId
	 * @param unitId
	 * @return
	 */
	@RequestMapping(value = "/activity/detail")
	public ModelAndView activityFinishedDetail(HttpServletRequest request, HttpServletResponse response,
											   @RequestParam("taskId") Integer taskId, @RequestParam("unitId") Integer unitId) {
		ModelAndView model = new ModelAndView();

		/**????????????????????????????????????????????????*/
		LpTaskUnitUserCondition lpTaskUnitUserCondition = new LpTaskUnitUserCondition();
		lpTaskUnitUserCondition.setTaskId(taskId);
		lpTaskUnitUserCondition.setUnitId(unitId);
		List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(lpTaskUnitUserCondition);

		/**????????????????????????*/
		LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
		lpTaskUserCondition.setTaskId(taskId);
		List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByCondition(lpTaskUserCondition);

		if (lpTaskUserList.size() > 0) {
			/**?????????????????????user*/
			Map<Integer, Boolean> finishedMap = new HashMap<Integer, Boolean>();
			for (int i = 0; i < lpTaskUnitUserList.size(); i++) {
				LpTaskUnitUser lpTaskUnitUser = lpTaskUnitUserList.get(i);
				if (lpTaskUnitUser.getHasFinished()) {
					finishedMap.put(lpTaskUnitUser.getUserId(), lpTaskUnitUser.getHasFinished());
				}
			}
			/**??????id??????*/
			Integer[] userIds = new Integer[lpTaskUserList.size()];

			/**????????????id*/
			for (int i = 0; i < lpTaskUserList.size(); i++) {
				userIds[i] = lpTaskUserList.get(i).getUserId();
			}
			/**??????????????????*/
			List<User> userList = userService.findUserByIds(userIds);
			/**??????person??????, ???userId???key??? person???value???map*/
			Map<Integer, Person> personMap = getPersons(userList);

			/**??????????????????*/
			List<Map<String, Object>> finishedStudents = new ArrayList<Map<String, Object>>(20);
			/**??????????????????*/
			List<Map<String, Object>> unfinishStudents = new ArrayList<Map<String, Object>>(20);

			int finishedCount = 0;
			int unFinishedCount = 0;

			for (LpTaskUser lpTaskUser : lpTaskUserList) {
				Map<String, Object> lpTaskUserMap = new HashMap<String, Object>();
				/**??????person*/
				Person person = personMap.get(lpTaskUser.getUserId());
				if (person != null) {
					lpTaskUserMap.put("userId", lpTaskUser.getUserId());
					lpTaskUserMap.put("studentName", person.getName());
				}
				/**???????????????????????????????????????????????????list*/
				if (finishedMap.get(lpTaskUser.getUserId()) != null) {
					finishedCount++;
					finishedStudents.add(lpTaskUserMap);
				} else {
					/**???????????????*/
					unFinishedCount++;
					unfinishStudents.add(lpTaskUserMap);
				}
			}

			model.addObject("finishedCount", finishedCount);
			model.addObject("unFinishedCount", unFinishedCount);
			model.addObject("finishedStudents", finishedStudents);
			model.addObject("unfinishStudents", unfinishStudents);
		}

		model.addObject("unitId", unitId);
		model.addObject("taskId", taskId);
		model.setViewName(DIR + "/task/lp_task_activity_detail");

		return model;
	}

	/***
	 * ????????????????????????
	 * @param user
	 * @return
	 */
	private List<Map<String, Object>> getMyTeachTeam(UserInfo user) {
		/**????????????????????????*/
		TeamTeacherCondition TeamTeacherByCondition = new TeamTeacherCondition();
		TeamTeacherByCondition.setUserId(user.getUserId());
		TeamTeacherByCondition.setSchoolYear(user.getSchoolYear());
		List<TeamTeacher> list = teamTeacherService.findTeamTeacherByCondition(TeamTeacherByCondition, null, Order.asc("grade_id"));

		/**?????????????????????id*/
		Set<Integer> gradeIds = new LinkedHashSet<Integer>();
		for (TeamTeacher teamTeacher : list) {
			gradeIds.add(teamTeacher.getGradeId());
		}

		List<Map<String, Object>> gradeList = new ArrayList<Map<String, Object>>();

		for (Integer gradeId : gradeIds) {
			/**??????????????????  */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gradeId", gradeId);
			map.put("gradeName", gradeService.findGradeById(gradeId).getName());
			List<Map<String, Object>> teamList = new ArrayList<Map<String, Object>>();
			for (TeamTeacher teamTeacher : list) {
				if (teamTeacher.getGradeId() - gradeId == 0 && teamTeacher.getSubjectCode() != null) {
					Map<String, Object> teamMap = new HashMap<String, Object>();
					teamMap.put("teamId", teamTeacher.getTeamId());
					String teamName = teamService.findTeamById(teamTeacher.getTeamId()).getName();
					teamMap.put("teamName", teamName + "[" + teamTeacher.getSubjectName() + "]");
					teamList.add(teamMap);
				}
			}
			map.put("teamList", teamList);
			gradeList.add(map);
		}
		return gradeList;
	}

	/***
	 * ??????????????????
	 * @param user
	 * @return
	 */
	private List<Map<String, Object>> getAllTeam(UserInfo user) {
		List<Team> teamList = teamService.findCurrentTeamOfSchoolAndYear(user.getSchoolId(), user.getSchoolYear());
		Set<Integer> gradeSet = new LinkedHashSet<Integer>();

		for (Team team : teamList) {
			gradeSet.add(team.getGradeId());
		}

		List<Map<String, Object>> gradeList = new ArrayList<Map<String, Object>>();

		for (Integer gradeId : gradeSet) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gradeId", gradeId);
			map.put("gradeName", gradeService.findGradeById(gradeId).getName());

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (Team team : teamList) {
				if (team.getGradeId() - gradeId == 0) {
					Map<String, Object> teamMap = new HashMap<String, Object>();
					teamMap.put("teamId", team.getId());
					teamMap.put("teamName", team.getName());
					list.add(teamMap);
				}
			}
			map.put("teamList", list);
			gradeList.add(map);
		}

		return gradeList;
	}

	private void batchInitExamStatistics(List<Integer> teamIds, Integer schoolId, Integer teacherId, Map<String, String> map, String taskUuid) {
		LpTaskCondition lc = new LpTaskCondition();
		// lp_task ??? uuid
		lc.setUuid(taskUuid);
		List<LpTask> tasks = lpTaskService.findLpTaskByCondition(lc);
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String paperId = key.split(",")[1];
			statisticService.initBatchExamStatistics(teamIds, schoolId, teacherId, "12", Integer.parseInt(paperId), entry.getValue());
		}

		List<LpTaskExamUnit> ltelist = new ArrayList<LpTaskExamUnit>();
		Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
		while (iterator1.hasNext()) {
			Map.Entry<String, String> entry = iterator1.next();
			String key = entry.getKey();
			Integer lpUnitId = Integer.parseInt(key.split(",")[0]);
			PjExamCondition pjc = new PjExamCondition();
			List<PjExam> pjList = new ArrayList<PjExam>();
			pjc.setJointExamCode(entry.getValue());
			pjc.setExamType("12");
			pjList = pjExamService.findPjExamByCondition(pjc);
			if (pjList != null && pjList.size() > 0) {
				Map<Integer, Integer> teamMap = new HashMap<Integer, Integer>();
				for (PjExam pj : pjList) {
					teamMap.put(pj.getTeamId(), pj.getId());
				}
				for (LpTask lt : tasks) {
					LpTaskExamUnit lpTaskExamUnit = new LpTaskExamUnit();
					lpTaskExamUnit.setJoinExamCode(entry.getValue());
					lpTaskExamUnit.setCreateDate(new Date());
					lpTaskExamUnit.setModifyDate(new Date());
					lpTaskExamUnit.setDeleted(false);
					lpTaskExamUnit.setExamId(teamMap.get(lt.getObjectId()));
					lpTaskExamUnit.setTaskId(lt.getId());
					lpTaskExamUnit.setUnitId(lpUnitId);
					ltelist.add(lpTaskExamUnit);
				}
			}
		}
		lpTaskExamUnitService.createBatch(ltelist);
	}

	private Map<Integer, Person> getPersons(List<User> userList) {

		Integer[] personIds = new Integer[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			personIds[i] = userList.get(i).getPersonId();
		}

		List<Person> personList = personService.findbyIds(personIds);

		Map<Integer, Person> map = new HashMap<Integer, Person>();
		for (User user : userList) {
			for (Person person : personList) {
				if (person.getId() - user.getPersonId() == 0) {
					map.put(user.getId(), person);
				}
			}
		}

		return map;
	}

}
