package platform.szxyzxx.web.learningPlan.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.learningDesign.service.LpTaskService;
import platform.education.learningDesign.service.LpUnitService;
import platform.education.learningDesign.vo.LpUnitCondition;
import platform.education.learningDesign.vo.LpUnitType;
import platform.education.learningDesign.vo.LpUnitVo;
import platform.education.learningDesign.vo.TaskVo;
import platform.education.user.model.User;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/learningPlan")
public class LearningPlanStatisticsController extends BaseController {

    private static final String DIR = "learningPlan";

    @Resource
    private LpTaskService lpTaskService;

    @Resource
    private LpUnitService lpUnitService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return DIR + "/lp_task_index";
    }

    /**
     * 当前学校，所有已布置的导学案分页列表
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

        // 查询已经布置导学案列表
        Integer code = null;
        if (subjectCode != null && !"".equals(subjectCode)) {
            code = Integer.parseInt(subjectCode);
        }

        List<TaskVo> list = new ArrayList<TaskVo>();
        list = this.lpTaskService.findLpTaskVoGroupByLpId(teamId, code, title, user.getSchoolId(), gradeId, page, null);
        for (TaskVo taskVo : list) {
            // 放入导学案下的单元信息
            List<LpUnitVo> unitVoByLpId = lpUnitService.findUnitVoByLpId(taskVo.getLpId());
            taskVo.setUnitVoList(unitVoByLpId);
            // 放入教师
            User u = userService.findUserById(taskVo.getUserId());
            Person person = personService.findPersonById(u.getPersonId());
            taskVo.setUserName(person == null ? "" : person.getName());

            //单元小结
            LpUnitCondition lpUnitCondition = new LpUnitCondition();
            lpUnitCondition.setLpId(taskVo.getLpId());
            lpUnitCondition.setUnitType(LpUnitType.ACTIVITY);
            Long unitSize = lpUnitService.count(lpUnitCondition);

            if (unitSize > 0) {
                taskVo.setHasActivity(true);
            } else {
                taskVo.setHasActivity(false);
            }
        }
        model.addObject("list", list);

        model.addObject("taskSize", list.size());
        model.setViewName(DIR + "/lp_task_list");
        // 返回分页要使用的参数
        model.addObject("userId", user.getId());
        model.addObject("teamId", teamId);
        model.addObject("subjectCode", subjectCode);
        model.addObject("gradeId", gradeId);
        model.addObject("title", title);
        model.addObject("dm", dm);

        return model;
    }

    /**
     * 导出导学案，限制为前100条
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
     * @throws Exception
     */
    @RequestMapping("/export")
    public void export(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("page") Page page, @ModelAttribute("order") Order order,
            @CurrentUser UserInfo user, @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "title", required = false) String title) throws Exception {

        // 查询已经布置导学案列表
        Integer code = null;
        if (subjectCode != null && !"".equals(subjectCode)) {
            code = Integer.parseInt(subjectCode);
        }

        List<TaskVo> list = new ArrayList<TaskVo>();
        list = this.lpTaskService.findLpTaskVoGroupByLpId(teamId, code, title, user.getSchoolId(), gradeId);
        for (TaskVo taskVo : list) {
            // 放入导学案下的单元信息
            List<LpUnitVo> unitVoByLpId = lpUnitService.findUnitVoByLpId(taskVo.getLpId());
            taskVo.setUnitVoList(unitVoByLpId);
            // 放入教师
            User u = userService.findUserById(taskVo.getUserId());
            Person person = personService.findPersonById(u.getPersonId());
            taskVo.setUserName(person == null ? "" : person.getName());

            //单元小结
            LpUnitCondition lpUnitCondition = new LpUnitCondition();
            lpUnitCondition.setLpId(taskVo.getLpId());
            lpUnitCondition.setUnitType(LpUnitType.ACTIVITY);
            Long unitSize = lpUnitService.count(lpUnitCondition);

            if (unitSize > 0) {
                taskVo.setHasActivity(true);
            } else {
                taskVo.setHasActivity(false);
            }
        }

        String fileName = "导学案统计" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-";
        //
        String userAgent = request.getHeader("User-Agent");
        //针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
        }

        OutputStream os = null;
        WritableWorkbook wwb = null;
        try {
            os = response.getOutputStream();// 取得输出流
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename="
                    + fileName + ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型

            wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("sheet1", 1);
            ws.getSettings().setShowGridLines(true);
            WritableFont wf_title = new WritableFont(WritableFont.ARIAL,
                    10, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE); // 定义格式 字体 下划线 斜体 粗体

            WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
            wcf_title.setWrap(true);
            wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
            wcf_title
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            ws.setRowView(0, 500);
            int c = 0;
            ws.addCell(new Label(c++, 0, "序号"));
            ws.addCell(new Label(c++, 0, "导学案标题"));
            ws.addCell(new Label(c++, 0, "完成人数/总人数"));
            ws.addCell(new Label(c++, 0, "完成率"));
            ws.addCell(new Label(c++, 0, "发布教师"));
            ws.addCell(new Label(c++, 0, "科目"));
            ws.addCell(new Label(c++, 0, "发布时间"));
            int r = 0;
            for (TaskVo vo : list) {
                int column = 0;
                r++;
                ws.addCell(new Label(column++, r, String.valueOf(r)));
                ws.addCell(new Label(column++, r, vo.getTitle()));
                ws.addCell(new Label(column++, r, vo.getFinishCount() + "/" + vo.getStudentCount()));
                ws.addCell(new Label(column++, r, vo.getPercent() + "%"));
                ws.addCell(new Label(column++, r, vo.getUserName()));
                ws.addCell(new Label(column++, r, vo.getSubjectName()));
                ws.addCell(new Label(column++, r, vo.getCreateDate().toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wwb != null) {
                wwb.write();
                wwb.close();
            }
            if (os != null) {
                os.close();
            }
        }

    }
}
