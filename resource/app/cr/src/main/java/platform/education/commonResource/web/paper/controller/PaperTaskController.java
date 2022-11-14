package platform.education.commonResource.web.paper.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import platform.education.commonResource.serivce.StatisticService;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.commonResource.web.paper.contans.PaperContans;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.PjExamCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TreeVo;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaFavorites;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.Task;
import platform.education.paper.model.TaskTeam;
import platform.education.paper.model.TaskUser;
import platform.education.paper.service.PaFavoritesService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.TaskService;
import platform.education.paper.service.TaskTeamService;
import platform.education.paper.vo.PaFavoritesCondition;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaperQuestionTree;
import platform.education.paper.vo.QuestionKnoledgeScoreVo;
import platform.education.paper.vo.TaskTeamCondition;
import platform.education.paper.vo.TaskVo;
import platform.education.resource.utils.UUIDUtil;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/paperTask")
public class PaperTaskController {

    @Resource
    private TeamStudentService teamStudentService;
    @Resource
    private StatisticService statisticService;
    @Resource
    private PjExamService pjExamService;
    @Resource
    private TaskService taskService;
    @Resource
    private TaskTeamService taskTeamService;
    @Resource
    private TeamService teamService;
    @Resource
    private GradeService gradeService;
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
    @Resource
    private PaQuestionService paQuestionService;
    @Resource
    private PaFavoritesService paFavoritesService;
    private static final String DIR = "/paper";

    @ResponseBody
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String question(HttpServletRequest request,
                           @RequestParam(value = "data", required = false) String data,
                           @CurrentUser final UserInfo user, Model model) {

        try {
            final String uuid = UUIDUtil.getUUID();
            Date date = new Date();
            ObjectMapper objMapper = new ObjectMapper();
//		    	data = data.replace("&quot;", "\"");
            JSONObject obj = JSONObject.fromObject(data);

            JSONArray teams = (JSONArray) obj.get("teamIds");
            String taskString = obj.getString("task");
            if (teams.size() == 0) {
                return "fail";
            }
            Integer[] teamID = new Integer[teams.size()];
            final List<Integer> teamIds = new ArrayList<Integer>();
            for (int i = 0; i < teams.size(); i++) {
                teamID[i] = teams.getInt(i);
                teamIds.add(teams.getInt(i));
            }
            List<TeamStudent> tsList = teamStudentService.findByTeamIds(teamID);
            if (tsList.size() == 0) {
                return "empty";
            }
            List<TaskTeam> ttList = new ArrayList<TaskTeam>();

            final Task task = objMapper.readValue(taskString, Task.class);
            PaPaper pa = paPaperService.findPaPaperById(task.getPaperId());
            task.setTitle(pa.getTitle());
            task.setCreateDate(date);
            task.setModifyDate(date);
            task.setFinishedCount(0);
            task.setUserCount(tsList.size());
            task.setPublisherId(user.getUserId());
            task.setPublisherName(user.getRealName());
            task.setUuid(uuid);
            for (Integer integer : teamID) {
                TaskTeam tt = new TaskTeam();
                tt.setCreateDate(date);
                tt.setModifyDate(date);
                tt.setTeamId(integer);
//		    		tt.setPjExamId(teamMap.get(teamID[i]));
                tt.setModifyDate(new Date());
                ttList.add(tt);
            }
            List<TaskUser> tuList = new ArrayList<TaskUser>();
            for (TeamStudent ts : tsList) {
                TaskUser tu = new TaskUser();
                tu.setCreateDate(date);
                tu.setFinishedFlag(2);
                tu.setName(ts.getName());
                tu.setStudentId(ts.getStudentId());
                tu.setTeamId(ts.getTeamId());
                tu.setUserId(ts.getUserId());
                tu.setModifyDate(new Date());
                tuList.add(tu);
            }
            if (task.getStartTime() == null || task.getFinishTime() == null) {
                return "timeout";
            }
            taskService.addPaperRelate(ttList, tuList, task);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    statisticService.initBatchExamStatistics(teamIds, user.getSchoolId(), user.getTeacherId(), PaperContans.PAPER_CODE, task.getPaperId(), task.getUuid());
                    PjExamCondition pjc = new PjExamCondition();
                    List<PjExam> pjList = pjExamService.findPjExamByCondition(pjc);
                    pjc.setJointExamCode(uuid);
                    pjc.setExamType(PaperContans.PAPER_CODE);
                    Map<Integer, Integer> teamMap = new HashMap<Integer, Integer>();
                    for (PjExam pj : pjList) {
                        teamMap.put(pj.getTeamId(), pj.getId());
                    }
                    Task task = taskService.findTaskByUuid(uuid);
                    TaskTeamCondition tc = new TaskTeamCondition();
                    tc.setTaskId(task.getId());
                    List<TaskTeam> list = taskTeamService.findTaskTeamByCondition(tc);
                    for (TaskTeam tt : list) {
                        tt.setPjExamId(teamMap.get(tt.getTeamId()));
                    }
                    taskService.modifyBatchTaskPjExamId(list);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/update/task", method = RequestMethod.POST)
    public String update(@RequestParam(value = "data", required = false) String data) {
        try {
            ObjectMapper objMapper = new ObjectMapper();
            JSONObject obj = JSONObject.fromObject(data);
            String taskString = obj.getString("task");
            Task nTask = objMapper.readValue(taskString, Task.class);
            Task oTask = taskService.findTaskById(nTask.getId());
            oTask.setTitle(nTask.getTitle());
            oTask.setStartTime(nTask.getStartTime());
            oTask.setFinishTime(nTask.getFinishTime());
            oTask.setIsCheck(nTask.getIsCheck());
            taskService.modify(oTask);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/choose/team", method = RequestMethod.GET)
    public String team(HttpServletRequest request,
                       @RequestParam(value = "paperId", required = false) Integer paperId,
                       @RequestParam(value = "dm", required = false) String dm,
                       @RequestParam(value = "isPC", required = false) String isPC,
                       @CurrentUser UserInfo user, Model model) {

        Map<String, Object> classGradeMap = getClassGradeMap(user, false, false, true);
        Map allClassMap = findAllClassByUser(user.getSchoolId());
        List<Integer> teamIds = taskService.findPaperStatus(paperId);
        request.setAttribute("classGradeMap", classGradeMap);
        request.setAttribute("allClassMap", allClassMap);
        request.setAttribute("teamIds", teamIds);

        if (isPC != null) {
            request.setAttribute("isPC", isPC);
        }

        return DIR + "/chooseTeam";
    }

    @RequestMapping(value = "/edit/task", method = RequestMethod.GET)
    public String teamEdit(HttpServletRequest request,
                           @RequestParam(value = "taskId", required = false) Integer taskId,
                           @RequestParam(value = "dm", required = false) String dm,
                           @CurrentUser UserInfo user, Model model) {

        Task task = taskService.findTaskById(taskId);
        TaskTeamCondition condition = new TaskTeamCondition();
        condition.setTaskId(taskId);
        condition.setIsDeleted(false);
        List<TaskTeam> ttList = taskTeamService.findTaskTeamByCondition(condition);
        StringBuilder name = new StringBuilder();
        List<Team> teamList = new ArrayList<Team>();
        if (ttList != null && ttList.size() > 0) {
            Integer[] teamIds = new Integer[ttList.size()];
            int i = 0;
            for (TaskTeam tt : ttList) {
                teamIds[i] = tt.getTeamId();
                i++;
            }
            Order order = new Order();
            order.setProperty("team_number");
            order.setAscending(true);
            teamList = teamService.findByIdsOfOrder(teamIds, order);
        }
        for (Team t : teamList) {
            name.append(t.getName()).append(",");
        }
//        if (name.length() > 0) {
//            name = new StringBuilder(name.substring(0, name.length()));
//        }
        request.setAttribute("name", name.toString());
        request.setAttribute("task", task);
        return DIR + "/edit";
    }

    @RequestMapping(value = "/isDelete")
    public String isDelete(HttpServletRequest request,
                           @RequestParam(value = "taskId", required = false) Integer taskId,
                           Model model) {

        Task task = taskService.findTaskById(taskId);
        TaskTeamCondition condition = new TaskTeamCondition();
        condition.setTaskId(taskId);
        condition.setIsDeleted(false);
        List<TaskTeam> ttList = taskTeamService.findTaskTeamByCondition(condition);
        StringBuilder name = new StringBuilder();
        List<Team> teamList = new ArrayList<Team>();
        if (ttList != null && ttList.size() > 0) {
            Integer[] teamIds = new Integer[ttList.size()];
            int i = 0;
            for (TaskTeam tt : ttList) {
                teamIds[i] = tt.getTeamId();
                i++;
            }
            Order order = new Order();
            order.setProperty("team_number");
            order.setAscending(true);
            teamList = teamService.findByIdsOfOrder(teamIds, order);
        }
        for (Team t : teamList) {
            name.append(t.getName()).append(",");
        }
//        if (name.length() > 0) {
//            name = new StringBuilder(name.substring(0, name.length()));
//        }
        request.setAttribute("team", name.toString());
        request.setAttribute("title", task.getTitle());
        return DIR + "/isDelete";
    }

    @ResponseBody
    @RequestMapping(value = "/del/task")
    public String paper(HttpServletRequest request,
                        @RequestParam(value = "taskId", required = false) Integer taskId,
                        @RequestParam(value = "teamId", required = false) Integer teamId,
                        @CurrentUser UserInfo user, Model model) {

        Task task = taskService.findTaskById(taskId);
        if (task == null) {
            return "fail";
        }
        if (user.getUserId().intValue() != task.getPublisherId().intValue()) {
            return "promise";
        }
        try {
            PjExamCondition condition = new PjExamCondition();
            condition.setJointExamCode(task.getUuid());
            condition.setExamType(PaperType.PAPER_CODE);
            condition.setTeamId(teamId);
            condition.setIsDelete(false);
            List<PjExam> peList = pjExamService.findPjExamByCondition(condition);
            if (peList == null) {
                return "fail";
            }
            if (peList.size() == 1) {
                PjExam pj = peList.get(0);
                pj.setIsDelete(false);
                pjExamService.modify(pj);
            } else {
                pjExamService.modifyDelectByJointExamCode(task.getUuid(), PaperType.PAPER_CODE);
            }
            taskService.deleteTaskRelate(taskId, teamId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/team/task")
    public String teamTask(@RequestParam(required = false, value = "subjectCode") String subjectCode,
                           @RequestParam(required = false, value = "title") String title,
                           @RequestParam(required = false, value = "teamId") Integer teamId,
                           @RequestParam(required = false, value = "index") String index,
                           @RequestParam(value = "dm", required = false) String dm,
                           @ModelAttribute(value = "page") Page page,
                           @ModelAttribute(value = "order") Order order,
                           @CurrentUser UserInfo user,
                           HttpServletRequest request) {

        PaPaperVo vo = new PaPaperVo();
        vo.setSubjectCode(subjectCode);
        vo.setTitle(title);
        if (page == null) {
            page = new Page();
        }
        order = new Order();
        order.setProperty("create_date");
        order.setAscending(false);
        List<TaskVo> voList = new ArrayList<TaskVo>();
        Team team = new Team();
        if (teamId != null) {
            team = teamService.findTeamById(teamId);
            voList = taskService.findTaskVoByTeamId(vo, teamId, page, order);
        }
        Date date = new Date();
        Map<String, String> sMap = subjectService.findAllSubjectNameMap();
        for (TaskVo v : voList) {
            if (v.getSubjectCode().equals("0")) {
                v.setSubjectName("多科目");
            } else {
                v.setSubjectName(sMap.get(v.getSubjectCode()));
            }
            int a = 0;
            if (date.after(v.getStartTime()) && date.before(v.getFinishTime())) {
                a = 1;
            } else if (date.after(v.getFinishTime())) {
                a = 2;
            }
            v.setState(a);
        }
        request.setAttribute("voList", voList);
        request.setAttribute("page", page);
        if (index != null && index.equals("list")) {
            return DIR + "/teamTaskList";
        }
        List<TreeVo> allList = teamService.findAllTeamTreeBySchoolId(user.getSchoolId());
        List<TreeVo> classList = teamService.findClassTeamTreeBySchoolId(user.getSchoolId(), user.getUserId());
        List<Subject> sList = subjectService.findSubjectsOfSchool(user.getSchoolId());
        request.setAttribute("team", team);
        request.setAttribute("allList", allList);
        request.setAttribute("classList", classList);
        request.setAttribute("sList", sList);
        return DIR + "/teamTaskIndex";
    }


    @RequestMapping(value = "/my/task")
    public String myTask(@RequestParam(required = false, value = "subjectCode") String subjectCode,
                         @RequestParam(required = false, value = "title") String title,
                         @RequestParam(required = false, value = "index") String index,
                         @RequestParam(value = "dm", required = false) String dm,
                         @ModelAttribute(value = "page") Page page,
                         @ModelAttribute(value = "order") Order order,
                         HttpServletRequest request,
                         @CurrentUser UserInfo user) {

        PaPaperVo vo = new PaPaperVo();
        vo.setTitle(title);
        vo.setSubjectCode(subjectCode);
        String path = "/myIndex";
        if (page == null) {
            page = new Page();
        }
        order = new Order();
        order.setProperty("create_date");
        order.setAscending(false);
        request.setAttribute("tit", "title");
        List<TaskVo> voList = taskService.findTaskVoByPaperVo(vo, user.getUserId(), page, order);
        request.setAttribute("volist", voList);
        request.setAttribute("page", page);
        if (index != null && index.equals("list")) {
            path = "/myList";
        }
        return DIR + path;
    }

    @RequestMapping(value = "/paper/viewer")
    public String getPaper(@RequestParam(required = false, value = "isBasket") Integer isBasket,
                           @RequestParam(required = false, value = "paperId") Integer paperId,
                           @RequestParam(required = false, value = "order") Order order,
                           @RequestParam(required = false, value = "property") String property,
                           @RequestParam(required = false, value = "asc") Boolean asc,
                           @CurrentUser UserInfo user,
                           HttpServletRequest request) {

        if (order == null) {
            order = new Order();
        }
        if (asc == null) {
            asc = true;
        }
        if (property == null || property.equals("")) {
            property = "nodeOrder";
        }
        order.setAscending(asc);
        order.setProperty(property);
        PaPaper paper = paPaperService.findPaPaperById(paperId);
        List<Subject> sList = subjectService.findAllSubjectName();
        Map<String, String> sMap = new HashMap<String, String>();
        for (Subject s : sList) {
            sMap.put(s.getCode(), s.getName());
        }
        List<QuestionKnoledgeScoreVo> knList = paPaperService.findQuestionKnoledgeScoreByPaperId(paperId);
        StringBuilder name = new StringBuilder("暂无科目");
        if (knList != null && knList.size() > 0) {
            name = new StringBuilder();
            for (QuestionKnoledgeScoreVo qs : knList) {
                name.append(sMap.get(qs.getSubjectCode())).append("(").append(qs.getScore()).append(")").append(",");
            }
            name = new StringBuilder(name.substring(0, name.length() - 1));
        }
        List<PaperQuestionTree> list = paQuestionService.findPaperQuestionTreeByPaperId(paperId, order, 0);
        PaFavoritesCondition pfCondition = new PaFavoritesCondition();
        pfCondition.setObjectType(PaperContans.PAPER);
        pfCondition.setObjectId(paperId);
        List<PaFavorites> pfList = paFavoritesService.findPaFavoritesByCondition(pfCondition);
        boolean isFav = false;
        if (pfList != null && pfList.size() > 0) {
            isFav = true;
        }

        // 组卷中心页面
        if (isBasket != null) {
            request.setAttribute("isBasket", true);
            //加入学段
            String stageCode = paPaperService.findStageCodeByPaperId(paperId);
            request.setAttribute("stageCode", stageCode);
            // 每道题的收藏与加入试题蓝状态
            if (list != null && !list.isEmpty()) {
                for (PaperQuestionTree item : list) {
                    if (item.getChildrens() != null && !item.getChildrens().isEmpty()) {
                        for (PaperQuestionTree q : item.getChildrens()) {
                            PaQuestionVo question = q.getObj();
                            question.setIsFav(paFavoritesService.isFav(question.getId(), user.getUserId(), PaperContans.QUESTION));
//							question.setStatusForBasket(0);
                        }
                    }
                }
            }
        }
        request.setAttribute("list", list);
        request.setAttribute("subject", name.toString());
        request.setAttribute("paper", paper);
        request.setAttribute("asc", asc);
        request.setAttribute("property", property);
        request.setAttribute("isFav", isFav);
        String path = "/getPaper";
        return DIR + path;

    }

    /**
     * 找到所有的班级
     */
    private Map<String, Object> getClassGradeMap(UserInfo user, boolean includeSubject, boolean includeSameClass, boolean includeType) {
        Map<String, Object> classGradeMap = new LinkedHashMap<String, Object>();
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
        Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), user.getUserId());
        if (teacher != null && schoolTermCurrent != null) {
            TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            teamTeacherCondition.setTeacherId(teacher.getId());
            // 1 班主任 2 任课教师
            teamTeacherCondition.setType(2);
            teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
            List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, Order.asc("team_id"));
            for (TeamTeacher tt : teamTeacherList) {
                Grade grade = this.gradeService.findGradeById(tt.getGradeId());
                if (grade != null) {
                    List<Map<String, Object>> classList = new ArrayList();
                    String viewName = grade.getName() + "&&"
                            + grade.getUniGradeCode();
                    if (classGradeMap.containsKey(viewName)) {
                        classList = (List<Map<String, Object>>) classGradeMap.get(viewName);
                    }
                    Team team = this.teamService.findTeamById(tt.getTeamId());
                    if (team != null) {
                        Map map = new HashMap();
                        String classSubjectName = team.getName();
                        if (includeSubject) {
                            classSubjectName = classSubjectName + "   [" + tt.getSubjectName() + "]";
                        }
                        if (includeType) {
                            classSubjectName = classSubjectName + "&&" + "01";
                        }
                        map.put(team.getId(), classSubjectName);
                        if (includeSameClass) {
                            classList.add(map);
                        } else if (!classList.contains(map)) {
                            classList.add(map);
                        }
                    }
                    classGradeMap.put(viewName, classList);
                }
            }
        }
        TreeMap<String, Object> hm = new TreeMap<String, Object>(new Comparator() {
            public int compare(Object o1, Object o2) {
                int a1 = Integer.parseInt(String.valueOf(o1).split("&&")[1]);
                int b1 = Integer.parseInt(String.valueOf(o2).split("&&")[1]);
                if (a1 > b1) {
                    return 1;
                }
                return -1;
            }
        });
        hm.putAll(classGradeMap);
        return hm;
    }

    /**
     * 找到任教班级
     */
    private Map findAllClassByUser(Integer schoolId) {
        Map classGradeMap = new LinkedHashMap();
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
        List<Grade> grades = this.gradeService.findGradeBySchoolYear(schoolId, schoolTermCurrent.getSchoolYear());
        for (Grade g : grades) {
            List<Map> classList = new ArrayList();
            String viewName = g.getName() + "&&" + g.getUniGradeCode();
            if (classGradeMap.containsKey(viewName)) {
                classList = (List<Map>) classGradeMap.get(viewName);
            }
            List<Team> teams = this.teamService.findTeamOfGrade(g.getId());
            for (Team team : teams) {
                Map map = new HashMap();
                map.put(team.getId(), team.getName());
                classList.add(map);
            }
            if (classList.size() > 0) {
                classGradeMap.put(viewName, classList);
            }
        }
        return classGradeMap;
    }

}
