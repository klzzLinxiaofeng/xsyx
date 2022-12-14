package platform.szxyzxx.web.apts.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.PjAptsTaskItemSummary;
import platform.education.generalTeachingAffair.model.PjAptsTaskJudge;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.ArabicToChineseUtils;
import platform.education.generalTeachingAffair.utils.DateUtil;
import platform.education.generalTeachingAffair.vo.*;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/assessment/teacher")
public class AptsController {

    @Autowired
    @Qualifier("pjAptsTaskUserService")
    protected PjAptsTaskUserService pjAptsTaskUserService;

    @Autowired
    @Qualifier("teamTeacherService")
    protected TeamTeacherService teamTeacherService;

    @Autowired
    @Qualifier("pjAptsTaskItemSummaryService")
    protected PjAptsTaskItemSummaryService pjAptsTaskItemSummaryService;

    @Autowired
    @Qualifier("pjAptsTaskScoreService")
    protected PjAptsTaskScoreService pjAptsTaskScoreService;

    @Autowired
    @Qualifier("pjAptsTaskJudgeService")
    protected PjAptsTaskJudgeService pjAptsTaskJudgeService;

    @Autowired
    @Qualifier("studentService")
    protected StudentService studentService;

    @Autowired
    @Qualifier("schoolTermService")
    protected SchoolTermService schoolTermService;

    private final static String viewBasePath = "/assessment";

    private final static String[] nums = {"???", "???", "???", "???", "???", "???", "???"};

    @RequestMapping("/menu")
    public String index() {
        return structurePath("/menu");
    }

    @RequestMapping("/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "week", required = false) String week,
            @ModelAttribute("condition") PjAptsTaskUserCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) throws Exception {
        String viewPath = null;

        if ("list".equals(sub)) {

            condition.setName(condition.getName().trim());
            //???pj_team_teacher??????????????????????????? ???????????????????????????????????????
            if (condition.getName() != null & !("").equals(condition.getName())) {
                TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
                teamTeacherCondition.setName(condition.getName().trim());
                teamTeacherCondition.setNameIsLike(true);
                teamTeacherCondition.setGradeId(condition.getGradeId());
                teamTeacherCondition.setTeamId(condition.getTeamId());
                teamTeacherCondition.setSubjectCode(condition.getSubjectCode());
                teamTeacherCondition.setSchoolId(user.getSchoolId());
                List<TeamTeacher> teachers = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
                if (teachers == null || teachers.isEmpty()) {
                    model.addAttribute("isNoTeacher", true);
                }
            }

            //?????????????????????????????????
            if (week != null && !"".equals(week)) {
                String[] weeks = week.split("\\(")[1].split("\\)")[0].split("~");
                condition.setWeekStartDate(weeks[0]);
                condition.setWeekFinishDate(weeks[1]);
            }
            condition.setSchoolId(user.getSchoolId());
            List<PjAptsTaskUserVo> vos = pjAptsTaskUserService.findPjAptsTaskUserVoByCondition(condition, page, null);
            if (vos != null && !vos.isEmpty()) {
                for (PjAptsTaskUserVo vo : vos) {
                    //?????????
                    vo.setWeekDay(nums[DateUtil.dayForWeek(vo.getStartDate()) - 1]);
                }
            }
            model.addAttribute("vos", vos);
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("sub", sub);
        model.addAttribute("evType", condition.getEvType());
        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping("/export")
    public void export(
            @CurrentUser UserInfo user,
            @RequestParam(value = "week", required = false) String week,
            @ModelAttribute("condition") PjAptsTaskUserCondition condition, HttpServletResponse response, HttpServletRequest request) throws Exception {

        if (week != null && !"".equals(week)) {
            String[] weeks = week.split("\\(")[1].split("\\)")[0].split("~");
            condition.setWeekStartDate(weeks[0]);
            condition.setWeekFinishDate(weeks[1]);
        }
        condition.setSchoolId(user.getSchoolId());
        condition.setName(condition.getName().trim());

        List<PjAptsTaskUserVo> vos = pjAptsTaskUserService.findPjAptsTaskUserVoByCondition(condition, null, null);
        if (vos == null || vos.isEmpty()) return;

        String fileName = condition.getEvType() == 1 ? "???????????????" : "??????????????????";
        fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-" + fileName;

        String userAgent = request.getHeader("User-Agent");
        //??????IE?????????IE????????????????????????
        if(userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")){
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }else{
            //???IE?????????????????????
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
        }

        OutputStream os = null;
        WritableWorkbook wwb = null;
        try {
            os = response.getOutputStream();// ???????????????
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename="
                    + fileName + ".xls");// ?????????????????????
            response.setContentType("application/msexcel");// ??????????????????

            wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("sheet1", 1);
            ws.getSettings().setShowGridLines(true);
            WritableFont wf_title = new WritableFont(WritableFont.ARIAL,
                    10, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE); // ???????????? ?????? ????????? ?????? ??????

            WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // ???????????????
            wcf_title.setWrap(true);
            wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // ??????????????????
            wcf_title
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // ??????
            ws.setRowView(0, 500);
            int c = 0;
            ws.addCell(new Label(c++, 0, "??????"));
            ws.addCell(new Label(c++, 0, "??????"));
            ws.addCell(new Label(c++, 0, "??????"));
            if (condition.getEvType() == 1) {
                ws.addCell(new Label(c++, 0, "?????????"));
                ws.addCell(new Label(c++, 0, "????????????"));
                ws.addCell(new Label(c++, 0, "???????????? ???"));
            } else {
                ws.addCell(new Label(c++, 0, "??????"));
                ws.addCell(new Label(c++, 0, "??????"));
                ws.addCell(new Label(c++, 0, "????????????"));
                ws.addCell(new Label(c++, 0, "???????????? ???"));
                ws.addCell(new Label(c++, 0, "????????????"));
            }
            int r = 0;
            SimpleDateFormat sf = new SimpleDateFormat("MM???dd???");
            for (PjAptsTaskUserVo vo : vos) {
                int column = 0;
                r++;
                ws.addCell(new Label(column++, r, vo.getGradeName()));
                ws.addCell(new Label(column++, r, vo.getTeamName()));
                ws.setColumnView(column - 1, 11);
                if (condition.getEvType() == 1) {
                    ws.addCell(new Label(column++, r, sf.format(vo.getStartDate()) + "-" + sf.format(vo.getFinishDate())));
                    ws.setColumnView(column - 1, 20);
                    ws.addCell(new Label(column++, r, vo.getTeacherName()));
                    ws.addCell(new Label(column++, r, "" + vo.getScoredUserCount() + "/" + vo.getTotalUserCount()));
                    ws.addCell(new Label(column++, r, vo.getScore().toString()));
                } else {
                    ws.addCell(new Label(column++, r, sf.format(vo.getStartDate()) + " ??????"+nums[DateUtil.dayForWeek(vo.getStartDate()) - 1]));
                    ws.setColumnView(column - 1, 20);
                    ws.addCell(new Label(column++, r, vo.getSubjectName()));
                    ws.setColumnView(column - 1, 16);
                    ws.addCell(new Label(column++, r, vo.getTeacherName()));
                    ws.addCell(new Label(column++, r, "" + vo.getScoredUserCount() + "/" + vo.getTotalUserCount()));
                    ws.addCell(new Label(column++, r, vo.getScore().toString()));
                    ws.addCell(new Label(column++, r, vo.getIsValid() ? "??????" : "??????"));
                }
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

    @RequestMapping("/detail")
    public ModelAndView detail(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
//          @RequestParam(value = "startDate", required = false) String startDate,
//          @RequestParam(value = "finishDate", required = false) String finishDate,
            @ModelAttribute("condition") PjAptsTaskUserCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {

        PjAptsTaskItemSummaryCondition itemSummaryCondition = new PjAptsTaskItemSummaryCondition();
        itemSummaryCondition.setAptsTaskUserId(condition.getId());
        //?????????
        List<PjAptsTaskItemSummary> itemSummaryList = pjAptsTaskItemSummaryService.findPjAptsTaskItemSummaryByCondition(itemSummaryCondition, null, Order.asc("id"));

        PjAptsTaskJudgeCondition judgeCondition = new PjAptsTaskJudgeCondition();
        judgeCondition.setAptsTaskUserId(condition.getId());
        //????????????
        List<PjAptsTaskJudge> judgeList = pjAptsTaskJudgeService.findPjAptsTaskJudgeByCondition(judgeCondition, page, Order.desc("modify_date"));
        List<PjAptsTaskJudgeVo> judgeVos = new ArrayList<>();
        if (judgeList != null && !judgeList.isEmpty()) {
            Integer num = (page.getCurrentPage() - 1) * page.getPageSize();
            for (PjAptsTaskJudge judge : judgeList) {
                PjAptsTaskJudgeVo judgeVo = new PjAptsTaskJudgeVo();
                BeanUtils.copyProperties(judge, judgeVo);
                //????????????
                judgeVo.setName(studentService.findStudentById(judgeVo.getJudgeId()).getName());
                PjAptsTaskScoreCondition scoreCondition = new PjAptsTaskScoreCondition();
                scoreCondition.setAptsTaskJudgeId(judge.getId());
                //?????????????????????
                judgeVo.setScores(pjAptsTaskScoreService.findPjAptsTaskScoreByCondition(scoreCondition, null, Order.asc("summary_id")));
                judgeVo.setNum(++num);
                judgeVo.setDescription(judgeVo.getDescription().replaceAll(" ","&nbsp"));
                judgeVo.setDescriptionWithNewline(judgeVo.getDescription().replaceAll("\\n","</br>"));
                judgeVos.add(judgeVo);
            }
        }

        String viewPath = null;
        if ("list".equals(sub)) {
            viewPath = structurePath("/detail_list");
        } else {
            model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            viewPath = structurePath("/detail_index");
        }

        model.addAttribute("titles", itemSummaryList);
        model.addAttribute("vos", judgeVos);
        model.addAttribute("condition", condition);
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping("/detail/h5")
    public ModelAndView detailH5(
//            @CurrentUser UserInfo user,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("page") Page page,
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        String viewPath = null;

        PjAptsTaskItemSummaryCondition itemSummaryCondition = new PjAptsTaskItemSummaryCondition();
        itemSummaryCondition.setAptsTaskUserId(id);
        //?????????
        List<PjAptsTaskItemSummary> itemSummaryList = pjAptsTaskItemSummaryService.findPjAptsTaskItemSummaryByCondition(itemSummaryCondition, null, Order.asc("id"));
        model.addAttribute("items", itemSummaryList);

        PjAptsTaskUserCondition condition = new PjAptsTaskUserCondition();
        condition.setId(id);
        List<PjAptsTaskUserVo> vos = pjAptsTaskUserService.findPjAptsTaskUserVoByCondition(condition, null, null);
        if(vos==null||vos.isEmpty()){
            return null;
        }
        PjAptsTaskUserVo vo=vos.get(0);

        if (!"list".equals(sub)) {

            SchoolTermCondition termCondition = new SchoolTermCondition();
            termCondition.setCode(vo.getTermCode());
            SchoolTerm term = schoolTermService.findSchoolTermByCondition(termCondition, null, null).get(0);
            try {
                //????????? ??? ??????
                int weekNum = DateUtil.findWeekCount(term.getBeginDate(), term.getFinishDate(), vo.getStartDate());
                vo.setWeekNum(String.valueOf(weekNum));
                vo.setWeekNumChinese(ArabicToChineseUtils.foematInteger(weekNum));
                vo.setWeekDay(nums[DateUtil.dayForWeek(vo.getStartDate()) - 1]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //??????????????????
            vo.setAvgScores(pjAptsTaskScoreService.findAvgScore(id));
//            Map<String,Float> itemScores=new LinkedHashMap<String,Float>(vo.getAvgScores().size());
//            for(Float score:vo.getAvgScores()){
//                itemScores.put()
//            }

            viewPath = structurePath("/detail_h5_index");

        } else {
            PjAptsTaskJudgeCondition judgeCondition = new PjAptsTaskJudgeCondition();
            judgeCondition.setAptsTaskUserId(id);
            //????????????
            List<PjAptsTaskJudge> judgeList = pjAptsTaskJudgeService.findPjAptsTaskJudgeByCondition(judgeCondition, page, Order.desc("modify_date"));
            List<PjAptsTaskJudgeVo> judgeVos = new ArrayList<>();
            if (judgeList != null && !judgeList.isEmpty()) {
                Integer num = (page.getCurrentPage() - 1) * page.getPageSize();
                for (PjAptsTaskJudge judge : judgeList) {
                    PjAptsTaskJudgeVo judgeVo = new PjAptsTaskJudgeVo();
                    BeanUtils.copyProperties(judge, judgeVo);
                    PjAptsTaskScoreCondition scoreCondition = new PjAptsTaskScoreCondition();
                    scoreCondition.setAptsTaskJudgeId(judge.getId());
                    //????????????
                    judgeVo.setScores(pjAptsTaskScoreService.findPjAptsTaskScoreByCondition(scoreCondition, null, Order.asc("summary_id")));
                    judgeVo.setDescriptionWithNewline(judgeVo.getDescription().replaceAll("\\n","</br>"));
                    judgeVo.setNum(++num);
                    judgeVos.add(judgeVo);
                }
            }
            viewPath = structurePath("/detail_h5_list");
            model.addAttribute("judges", judgeVos);
        }
        model.addAttribute("vo",vo);
        return new ModelAndView(viewPath, model.asMap());
    }


    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}