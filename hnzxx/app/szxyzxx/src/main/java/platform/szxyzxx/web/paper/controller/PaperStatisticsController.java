package platform.szxyzxx.web.paper.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.paper.service.TaskService;
import platform.education.paper.vo.TaskVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/paper")
public class PaperStatisticsController {

    @Resource
    private TeamService teamService;
    @Resource
    private TaskService taskService;
    @Resource
    private SubjectService subjectService;

    private static final String DIR = "/paper";

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return DIR + "/teamTaskIndex";
    }

    /**
     * 当前学校，所有已布置的试卷分页列表
     *
     * @param subjectCode
     * @param title
     * @param teamId
     * @param index
     * @param dm
     * @param page
     * @param order
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    public String teamTask(@RequestParam(required = false, value = "subjectCode") String subjectCode,
                           @RequestParam(required = false, value = "title") String title,
                           @RequestParam(required = false, value = "teamId") Integer teamId,
                           @RequestParam(value = "gradeId", required = false) Integer gradeId,
                           @RequestParam(required = false, value = "index") String index,
                           @RequestParam(value = "dm", required = false) String dm,
                           @ModelAttribute(value = "page") Page page,
                           @ModelAttribute(value = "order") Order order,
                           @CurrentUser UserInfo user,
                           HttpServletRequest request) {

        Integer code = null;
        if (subjectCode != null && !"".equals(subjectCode)) {
            code = Integer.parseInt(subjectCode);
        }
        if (page == null) {
            page = new Page();
        }
        order = new Order();
        order.setProperty("create_date");
        order.setAscending(false);
        List<TaskVo> voList = new ArrayList<TaskVo>();
        voList = taskService.findTaskVo(teamId, code, title, user.getSchoolId(), gradeId, page, null);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (TaskVo vo : voList) {
            Integer userCount = vo.getUserCount();
            Integer finishedCount = vo.getFinishedCount();
            BigDecimal big = new BigDecimal(userCount);
            BigDecimal big2 = new BigDecimal(finishedCount);
            BigDecimal big3 = new BigDecimal(100.0f + "");
            float percent = big2.divide(big, 4, BigDecimal.ROUND_HALF_UP).multiply(big3).floatValue();
            vo.setPercent(percent + "%");
        }
        request.setAttribute("voList", voList);
        request.setAttribute("page", page);
        return DIR + "/teamTaskList";
    }
}
