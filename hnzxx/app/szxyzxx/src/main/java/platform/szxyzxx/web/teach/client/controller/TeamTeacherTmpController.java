package platform.szxyzxx.web.teach.client.controller;

import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectTeacherTmp;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.SchoolInitService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.SubjectTeacherTmpCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.user.model.Group;
import platform.education.user.service.GroupService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/24.
 */
@Controller
@RequestMapping("/tmp/team/teacher/")
public class TeamTeacherTmpController {
    @Autowired
    @Qualifier("baseRedisCache")
    private BaseRedisCache<Object> baseRedisCache;
    @Autowired
    @Qualifier("schoolInitService")
    private SchoolInitService schoolInitService;
    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;
    @Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;
    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;
    @Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;

    private static final String DIR = "/schoolInit/teamTeacher";

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public Object indexPage(@CurrentUser UserInfo userInfo, Model model) {
        String code = getCode(userInfo.getId());
        Long warnSize = schoolInitService.countSubjectTeacherTmpByCodeAndStatus(code, 1);
        Long errorSize = schoolInitService.countSubjectTeacherTmpByCodeAndStatus(code, 2);
        model.addAttribute("warnSize", warnSize);
        model.addAttribute("errorSize", errorSize);
        return new ModelAndView(DIR + "/tmp_index", model.asMap());
    }

    @RequestMapping(value="/list")
    public Object list(
            @CurrentUser UserInfo userInfo,
            @RequestParam(value="status", defaultValue="0") Integer status,
            @RequestParam(value="index", defaultValue="list") String index,
            @RequestParam(value="gradeId", required = false) Integer gradeId,
            @RequestParam(value="teamId", required = false) Integer teamId,
            @ModelAttribute("page") Page page, Model model) {

        String path = DIR + "/tmp_list";
        List<SubjectTeacherTmp> result = new ArrayList<>();
        String code = getCode(userInfo.getId());
        if (code != null) {
//            result = schoolInitService.findSubjectTeacherByCodeAndStatus(code, status, page, Order.desc("id"));
            SubjectTeacherTmpCondition condition = new SubjectTeacherTmpCondition();
            condition.setCode(code);
            condition.setStatus(status);
            condition.setGradeId(gradeId);
            condition.setTeamId(teamId);
            result = schoolInitService.findSubjectTeacherTmpByCondition(condition, page, Order.desc("id"));
        }
        if (!"index".equals(index)) {
            if (status == 0) {
                path = DIR + "/tmp_ok";
            } else if (status == 1) {
                path = DIR + "/tmp_warn";
            } else if (status == 2) {
                path = DIR + "/tmp_error";
            }
        }

        model.addAttribute("result", result);
        model.addAttribute("status", status);
        return new ModelAndView(path, model.asMap());
    }

    @RequestMapping(value="/edit")
    public Object editPage(
            @CurrentUser UserInfo user,
            @RequestParam(value="id", required = false) Integer id,
            @RequestParam(value="sub", required = false) String sub,
            @RequestParam(value="name", required = false) String name,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model){

        page.setPageSize(5);
        String path = DIR + "/tmp_edit";
        SubjectTeacherTmp tmp = schoolInitService.findSubjectTeacherTmpById(id);
        if ("list".equals(sub)) {
            path = DIR + "/tmp_edit_list";
            name = name != null ? name.trim() : "";
            TeacherCondition condition = new TeacherCondition();
            condition.setSchoolId(user.getSchoolId());
            condition.setName(name);
            List<TeacherVo> teacherList = this.teacherService.findTeacherVoByCondition(condition, page, order);
            List<TeacherVo> teachers = this.teacherService.findTeacherVoByCondition(condition, null, null);
            int count = 0;
            if (teachers != null) {
                count = teachers.size();
            }
            model.addAttribute("count", count);
            model.addAttribute("teacherList", teacherList);
        }
        model.addAttribute("tmp", tmp);
        if (tmp != null && tmp.getSubjectTeacherId() != null) {
            //导入成功的数据
            TeamTeacher teamTeacher = teamTeacherService.findTeamTeacherById(tmp.getSubjectTeacherId());
            if (teamTeacher != null) {
                model.addAttribute("type", teamTeacher.getType());
                model.addAttribute("subjectCode", teamTeacher.getSubjectCode());
            }
        } else if (tmp != null){
            //导入失败的数据，没有teamTeacherId
            String subjectName = tmp.getSubjectName();
            List<Subject> subjectList = subjectService.findSubjectByName(subjectName, user.getSchoolId(), null, null);
            if (subjectList != null && subjectList.size() > 0) {
                model.addAttribute("subjectCode", subjectList.get(0).getCode());
            }
            int type = 2;
            if ("班主任".equals(subjectName)) {
                type = 1;
            }
            model.addAttribute("type", type);
        }
        return new ModelAndView(path, model.asMap());
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public String updateTmp(
            @RequestParam(value="id", required = false) Integer id,
            @RequestParam(value="subjectTeacherId", required = false) Integer subjectTeacherId,
            @RequestParam(value="gradeId", required = false) Integer gradeId,
            @RequestParam(value="teamId", required = false) Integer teamId,
            @RequestParam(value="type", required = false) Integer type,
            @RequestParam(value="subjectCode", required = false) String subjectCode,
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value="mobile", required = false) String mobile){

        SubjectTeacherTmp tmp = null;
        if (id != null) {
            tmp = schoolInitService.findSubjectTeacherTmpById(id);
        } else {
            SubjectTeacherTmpCondition condition = new SubjectTeacherTmpCondition();
            condition.setGradeId(gradeId);
            condition.setTeamId(teamId);
            condition.setSubjectTeacherId(subjectTeacherId);
            List<SubjectTeacherTmp> tmpList = schoolInitService.findSubjectTeacherTmpByCondition(condition, null, null);
            if (tmpList != null && tmpList.size() > 0) {
                tmp = tmpList.get(0);
            }
        }

        if (tmp != null) {
            TeamTeacher teamTeacher = null;
            if (type == 1) {
                List<TeamTeacher> teacherList = teamTeacherService.findByTeamIdAndType(teamId, type);
                if (teacherList != null && teacherList.size() > 0) {
                    teamTeacher = teacherList.get(0);
                }
            } else if (type == 2) {
                teamTeacher = teamTeacherService.findTeamTeacherByTeamIdAndSubjectCode(teamId, subjectCode);
            }

            tmp.setTeacherName(name);
            tmp.setPhone(mobile);
            if (teamTeacher != null) {
                tmp.setSubjectTeacherId(teamTeacher.getId());
            }
            //针对错误信息
            if (tmp.getStatus() != 0) {
                tmp.setStatus(0);
                tmp.setErrorFiled("");
                tmp.setErrorInfo("");
            }
            schoolInitService.modifySubjectTeacherTmp(tmp, 1);

            return ResponseInfomation.OPERATION_SUC;
        }
        return ResponseInfomation.OPERATION_FAIL;
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public String deleteTmp(
            @CurrentUser UserInfo user,
            @RequestParam(value="id", required = false) Integer id,
            @RequestParam(value="teamTeacherId", required = false) Integer teamTeacherId){
        Integer schoolId = user.getSchoolId();
        Group group = groupService.findUnique(schoolId, "1");
        TeamTeacher teamTeacher = teamTeacherService.findTeamTeacherById(teamTeacherId);

        String status = ResponseInfomation.OPERATION_SUC;
        if (teamTeacher != null) {
            Integer type = teamTeacher.getType();
            if (type.intValue() == 1) {
                status = teamTeacherService.removeMasterFromTeam(teamTeacher, 1, group.getId(), schoolId);
            } else if (type.intValue() == 2) {
                status = teamTeacherService.removeClassTeacherFromTeam(teamTeacher, 1, group.getId(), schoolId);
            }
        }
        if (id != null) {
            schoolInitService.deleteSubjectTeacherTmpById(id);
        } else {
            if (teamTeacher != null) {
                SubjectTeacherTmpCondition condition = new SubjectTeacherTmpCondition();
                condition.setGradeId(teamTeacher.getGradeId());
                condition.setTeamId(teamTeacher.getTeamId());
                condition.setSubjectTeacherId(teamTeacherId);
                List<SubjectTeacherTmp> tmpList = schoolInitService.findSubjectTeacherTmpByCondition(condition, null, null);
                for (SubjectTeacherTmp tmp : tmpList) {
                    schoolInitService.deleteSubjectTeacherTmpById(tmp.getId());
                }
            }
        }

        return status;
    }



    private String getCode(Integer userId) {
        String key = "team_teacher_tmp_" + userId;
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
        if(value!=null && !"".equals(value)) {
            String code = (String) value.get("code");
            return code;
        }
        return null;
    }
}
