package platform.szxyzxx.web.apts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.generalTeachingAffair.contants.AssessmentCantants;
import platform.education.generalTeachingAffair.model.PjAptsTask;
import platform.education.generalTeachingAffair.model.PjAptsTaskItem;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.PjAptsTaskItemService;
import platform.education.generalTeachingAffair.service.PjAptsTaskService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/assessment/statistics")
public class AptsStatisticsController {
    private final static String viewBasePath = "/assessment/statistics/";
    @Resource
    private PjAptsTaskService pjAptsTaskService;
    @Resource
    private SchoolTermService schoolTermService;
    @Resource
    private SubjectService subjectService;
    @Resource
    private PjAptsTaskItemService pjAptsTaskItemService;

    @RequestMapping("/index")
    public String index(
            @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
            HttpServletRequest requets
    ) {
        String name = "班主任评价";
        if (type == AssessmentCantants.SUBJECT) {
            name = "学科教师评价";
        }
        requets.setAttribute("type", type);
        requets.setAttribute("name", name);
        return viewBasePath + "/tj";
    }

    @RequestMapping("/aptsData")
    @ResponseBody
    public LinkedHashMap<String, Object> apts(
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "termCode", required = true) String termCode,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
            @RequestParam(value = "subjectCode", required = false) String subjectCode
    ) {
        try {
            PjAptsTaskUserCondition condition = new PjAptsTaskUserCondition();
            condition.setGradeId(gradeId);
            condition.setTeamId(teamId);
            condition.setTeacherName(title);
            if (subjectCode != null && !subjectCode.equals("-1") && !subjectCode.equals("")) {
                condition.setSubjectCode(subjectCode);
            }
            condition.setEvType(type);
            String[] str = termCode.split("-");
            SchoolTerm schoolTerm = schoolTermService.findSchoolTermByCode(Integer.valueOf(str[0]), termCode);
            return pjAptsTaskService.findStatisticsByCondition(condition, schoolTerm.getBeginDate(), schoolTerm.getFinishDate());
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedHashMap<String, Object>();
        }
    }

    @RequestMapping("/subject")
    @ResponseBody
    public List<Subject> findSubject(
            @RequestParam(value = "year", required = false) String year,
            @CurrentUser UserInfo user
    ) {
        List<Subject> sblist = subjectService.findSubjectsOfSchool(user.getSchoolId());
        Subject s = new Subject();
        s.setName("全部科目");
        s.setCode("-1");
        sblist.add(0, s);
        return sblist;
    }

    @RequestMapping("/editBorad")
    public String editBorad(
            @CurrentUser UserInfo user,
            @RequestParam(value = "type", required = false, defaultValue = "1") Integer type,
            HttpServletRequest request) {
        PjAptsTask pt = pjAptsTaskService.initDefaultTask(user.getSchoolId(), type);
        request.setAttribute("task", pt);
        request.setAttribute("name", type == 1 ? "班主任评价" : "学科教师评价");
        Integer person = 0;
        person = (int) (pt.getValidPercent() * 100);
        request.setAttribute("person", person);
        return viewBasePath + "borad";
    }

    @RequestMapping("/findItem")
    @ResponseBody
    public List<PjAptsTaskItem> findItem(
            @RequestParam(value = "taskId", required = false) Integer taskId
    ) {
        PjAptsTaskItemCondition condition = new PjAptsTaskItemCondition();
        condition.setAptsTaskId(taskId);
        condition.setIsDelete(false);
        List<PjAptsTaskItem> itemList = pjAptsTaskItemService.findPjAptsTaskItemByCondition(condition);
        return itemList;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(
            @RequestParam(value = "taskId", required = false) Integer taskId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "number", required = false) String number,
            @RequestParam(value = "percent", required = true) Integer percent
    ) {
        PjAptsTask pt = pjAptsTaskService.findPjAptsTaskById(taskId);
        name = name.substring(0, name.length() - 6);
        number = number.substring(0, number.length() - 6);
        String[] names = name.split("------");
        String[] numbers = number.split("------");
        List<String> nameList = new ArrayList<String>();
        List<Integer> numberList = new ArrayList<Integer>();
        for (int i = 0; i < names.length; i++) {
            nameList.add(names[i]);
            numberList.add(Integer.valueOf(numbers[i]));
        }
        Double d = percent * 1.0d / 100;
        pt.setValidPercent(d);
        pjAptsTaskService.modify(pt);
        pjAptsTaskService.modifyTaskItem(nameList, numberList, taskId);
        return "success";
    }
}
