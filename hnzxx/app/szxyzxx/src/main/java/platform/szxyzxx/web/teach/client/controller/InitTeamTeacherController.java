package platform.szxyzxx.web.teach.client.controller;

import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.SchoolInitService;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.generalTeachingAffair.vo.TeamVo;
import platform.education.user.contants.SysDefRole;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserRole;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ExcelUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Administrator on 2018/4/18.
 */
@Controller
@RequestMapping("/team/teacher/init")
public class InitTeamTeacherController extends BaseController {
    private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("schoolInitService")
	private SchoolInitService schoolInitService;

    @Autowired
    @Qualifier("baseRedisCache")
    private BaseRedisCache<Object> baseRedisCache;

    private static final String DIR = "/schoolInit/teamTeacher";

    @RequestMapping(value = "/export")
    public void exportExcel(@CurrentUser UserInfo user, HttpServletResponse response, HttpServletRequest request){
        String fileName = "教师任课安排导入模板"+".xls";
        String sheetName = "任课安排";

        Integer schoolId = user.getSchoolId();
        String schoolYear = user.getSchoolYear();
        List<Subject> subjectList = subjectService.findSubjectsOfSchool(schoolId);
        String[] title = new String[subjectList.size() + 3];
        title[0] = "年级";
        title[1] = "班级";
        title[2] = "班主任";
        for (int i = 3; i < subjectList.size() + 3; i++) {
            title[i] = subjectList.get(i-3).getName();
        }

        List<Team> teams = teamService.findCurrentTeamOfSchoolAndYear(schoolId, schoolYear);
        String[][] content = new String [teams.size()][subjectList.size()];

        List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
        List<Team> teamList = null;
        int i = 0;
        if (gradeList != null && gradeList.size() > 0) {
            for (Grade grade : gradeList) {
                teamList = teamService.findTeamOfGrade(grade.getId());
                for (Team team : teamList) {
                    content[i][0] = grade.getName();
                    content[i][1] = String.valueOf(team.getTeamNumber());
                    i++;
                }
            }
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null, "1");
        try {
            setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @RequestMapping(value = "/index")
    public Object indexPage(@CurrentUser UserInfo user, Model model) {
    	boolean initTerm = false;
		SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		if (current != null) {
			initTerm = true;
		}
 
		Integer schoolId = user.getSchoolId();
		boolean initTeacher = isInitTeacher(schoolId, user.getSchoolYear());
		 
		String url = "";
		if(initTerm && initTeacher) {
			url = DIR + "/index";
		} else {
			url = DIR + "/unfinished";
		}
		
		return new ModelAndView(url, model.asMap());
    }
    private boolean isInitTeacher(Integer schoolId, String schoolYear) {
		if(schoolYear==null || "".equals(schoolYear)) {
			return false;
		}
//		Integer status = Integer.parseInt((schoolId+schoolYear));
//		Long size = schoolInitService.countTeacherTmpByCodeAndStatus(null, status);
        List<Teacher> teacherList = teacherService.findTeacherListBySchoolId(schoolId);

        if(teacherList != null && teacherList.size() > 0) {
        	return true;
        } else {
        	return false;
        }
    }

    @RequestMapping(value = "/import")
    public Object importPage(@CurrentUser UserInfo user, Model model) {

        String key = "team_teacher_tmp_" + user.getId();
        Object value = baseRedisCache.getCacheObject(key);

        model.addAttribute("value", value);
        model.addAttribute("userId", user.getId());
        model.addAttribute("schoolId", user.getSchoolId());
        model.addAttribute("schoolYear", user.getSchoolYear());
        return new ModelAndView(DIR + "/import", model.asMap());
    }

    @RequestMapping(value = "/manage")
    public Object managePage(@CurrentUser UserInfo user, Model model){

        List<Grade> gradeList = gradeService.findGradeBySchoolYear(user.getSchoolId(), user.getSchoolYear());
        model.addAttribute("gradeList", gradeList);

        return new ModelAndView(DIR + "/manage", model.asMap());
    }

    @RequestMapping(value = "/manage/list")
    public Object manageListPage(
            @CurrentUser UserInfo user,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            Model model){

        Grade grade = gradeService.findGradeById(gradeId);
        if (grade != null) {
            Integer schoolId = grade.getSchoolId();
            List<Team> teamList = teamService.findTeamOfGradeAndSchool(gradeId, schoolId);
            List<SubjectGrade> subjectList = subjectGradeService.findSubjectGradeByGradeCode(schoolId, grade.getUniGradeCode());

            Integer teamId = null;
            List<Object> classMasterList = new ArrayList<>();
            List<Object> subjectTeacherList = new ArrayList<>();
            List<Object> subjectTeachers = new ArrayList<>();
            for (Team team : teamList) {
                teamId = team.getId();
                //班主任
                List<TeamTeacher> masters = teamTeacherService.findByTeamIdAndType(teamId, 1);
                if (masters != null && masters.size() > 0) {
                    classMasterList.add(masters.get(0));
                } else {
                    classMasterList.add(new TeamTeacher());
                }
                //科任教师
                subjectTeachers = new ArrayList<>();
                for (SubjectGrade subjectGrade : subjectList) {
                    TeamTeacher teamTeacher = teamTeacherService.findTeamTeacherByTeamIdAndSubjectCode(teamId, subjectGrade.getSubjectCode());
                    subjectTeachers.add(teamTeacher);
                }
                subjectTeacherList.add(subjectTeachers);
            }

            model.addAttribute("teamList", teamList);
            model.addAttribute("subjectList", subjectList);
            model.addAttribute("classMasterList", classMasterList);
            model.addAttribute("subjectTeacherList", subjectTeacherList);
        }
        return new ModelAndView(DIR + "/manage_list", model.asMap());
    }

    @RequestMapping(value = "/manage/edit")
    public Object editTeamTeacherPage(
            @CurrentUser UserInfo user,
            @RequestParam(value="id", required = false) Integer id,
            @RequestParam(value="gradeId", required = false) Integer gradeId,
            @RequestParam(value="teamId", required = false) Integer teamId,
            @RequestParam(value="subjectCode", required = false) String subjectCode,
            @RequestParam(value="subjectName", required = false) String subjectName,
            @RequestParam(value="type", required = false) Integer type,
            Model model){

        String path = DIR + "/manage_edit";
        if (id != null) {
            //修改
            TeamTeacher teamTeacher = teamTeacherService.findTeamTeacherById(id);
            if (teamTeacher != null) {
                gradeId = teamTeacher.getGradeId();
                teamId = teamTeacher.getTeamId();
                subjectCode = teamTeacher.getSubjectCode();
                type = teamTeacher.getType();
                subjectName = teamTeacher.getSubjectName();
            }
        }
        if (type.intValue() == 1) {
            subjectName = "班主任";
        }
        Grade grade = gradeService.findGradeById(gradeId);
        Team team = teamService.findTeamById(teamId);
        Map<String, Object> map = new HashMap<>();
        map.put("gradeName", grade.getName());
        map.put("teamNumber", team.getTeamNumber());
        map.put("subjectName", subjectName);
        map.put("gradeId", gradeId);
        map.put("teamId", teamId);
        map.put("subjectCode", subjectCode);
        map.put("type", type);
        map.put("id", id);
        model.addAttribute("map", map);

        return new ModelAndView(path, model.asMap());
    }

    @RequestMapping(value = "/manage/edit/list")
    public Object editTeamTeacherList(
            @CurrentUser UserInfo user,
            @RequestParam(value="name", required = false) String name,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model){
        page.setPageSize(5);
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
        return new ModelAndView(DIR + "/tmp_edit_list", model.asMap());
    }

    @RequestMapping(value = "/manage/delete")
    @ResponseBody
    public Object deleteTeamTeacher(@RequestParam(value="id", required = false) Integer id){

        return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
    }


    @RequestMapping(value = "/subject")
    public Object subjectPage(@CurrentUser UserInfo user, Model model){
        return new ModelAndView(DIR + "/subject", model.asMap());
    }

    @RequestMapping(value = "/subjectGrade")
    public Object subjectGradePage(@CurrentUser UserInfo user, Model model){
        return new ModelAndView(DIR + "/subjectGrade", model.asMap());
    }

    @RequestMapping(value = "/subjectTeacher")
    public Object subjectTeacherPage(@CurrentUser UserInfo user, Model model){
        return new ModelAndView(DIR + "/subjectTeacher", model.asMap());
    }

}
