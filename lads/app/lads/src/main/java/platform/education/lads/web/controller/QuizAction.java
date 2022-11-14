package platform.education.lads.web.controller;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package platform.education.lads.action;
//
//import com.gzxtjy.common.service.HibernateBaseService;
//import com.gzxtjy.common.util.HttpUtils;
//import com.gzxtjy.common.web.action.BaseAction;
//import com.gzxtjy.exam.entities.ExamQuiz;
//import com.gzxtjy.lads.constants.EmbedSystem;
//import com.gzxtjy.lads.entities.LadsQuizQuestionTool;
//import com.gzxtjy.lads.entities.LadsQuizResultTool;
//import com.gzxtjy.lads.entities.LadsQuizTool;
//import com.gzxtjy.lads.service.LadsService;
//import com.gzxtjy.lads.service.QuizToolService;
//import com.gzxtjy.lads.util.LadsCommonUtils;
//import platform.education.lads.vo.LadsUserVo;
//import platform.education.lads.vo.quizToolVo.LadsQuizResultVo;
//import platform.education.lads.vo.quizToolVo.LadsQuizUserResultVo;
//import com.gzxtjy.portal.session.SessionManager;
//import com.gzxtjy.portal.util.CatalogUtil;
//import com.gzxtjy.resources.constants.ResourceTypeName;
//import com.gzxtjy.resources.util.DocPathUtil;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.dom4j.DocumentException;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
///**
// *
// * @author 罗志明
// */
//@Controller("ladsQuizAction")
//@Scope("prototype")
//public class QuizAction extends BaseAction {
//
//    @Resource
//    private HibernateBaseService hibernateBaseServiceImpl;
//    @Resource
//    private QuizToolService quizToolServiceImpl;
//    @Resource
//    private LadsService ladsServiceImpl;
//    @Resource
//    private com.gzxtjy.exam.action.QuizAction quizAction;
//
//    public String loadQuizList() {
//        HttpServletRequest request = super.getRequest();
//        String sysType = request.getParameter("sysType");
//        String userId = request.getParameter("userId");
//        String toolId = request.getParameter("toolId");
//        if (sysType.equals(EmbedSystem.XTJY)) {
//            String keyWord = request.getParameter("keyWord");
//            String shareStauts = request.getParameter("shareStauts");
//            DetachedCriteria criteria = DetachedCriteria.forClass(ExamQuiz.class);
//            criteria.createAlias("dcCenterUser", "centerUser");
//            criteria.add(Restrictions.eq("centerUser.userId", SessionManager
//                    .getUser().getId()));
//            // 教材目录筛选
//            //criteria = CatalogUtil.setDirectoryInCriteria(request, criteria);
//            if (shareStauts != null && !"".equals(shareStauts)
//                    && !"-00".equals(shareStauts)) {
//                criteria.add(Restrictions.eq("shareStatus",
//                        Integer.parseInt(shareStauts)));
//            }
//            if (keyWord != null && !"".equals(keyWord)) {
//                criteria.add(Restrictions.like("title", "%" + keyWord + "%"));
//            }
//            criteria.add(Restrictions.eq("resType", ResourceTypeName.RES_QUIZ));
//            criteria.addOrder(Order.desc("createTime"));
//            List<ExamQuiz> quizList = this.hibernateBaseServiceImpl.findByCriteria(pagination,
//                    criteria);
//            request.setAttribute("urlPara", LadsCommonUtils.createUrlPara(request));
//            request.setAttribute("quizList", quizList);
//        } else {
//            request.setAttribute("msg", "本项目没有可导入的练习");
//        }
//        request.setAttribute("toolId", toolId);
//        return "author_quiz_load_quiz_list";
//    }
//
//    public String chooseQuizXml() throws UnsupportedEncodingException, IOException {
//        HttpServletRequest request = super.getRequest();
//        HttpServletResponse response = super.getResponse();
//        String sysType = request.getParameter("sysType");
//        String quizId = request.getParameter("quizId");
//        PrintWriter pw = this.setAjaxResponse(request, response);
//        if (sysType.equals(EmbedSystem.XTJY)) {
//            ExamQuiz eq = this.hibernateBaseServiceImpl.findById(ExamQuiz.class, quizId);
//            pw.print(DocPathUtil.converHttpUrl(eq.getHttpUrl()));
//        } else {
//        }
//        pw.close();
//        return null;
//    }
//
//    public String saveQuizResult() throws IOException {
//        HttpServletRequest request = super.getRequest();
//        String para = request.getParameter("para");
//        String split[] = para.split("\\|");
//        String userId = split[0];
//        String toolId = split[1];
//        String sysType = split[2];
//        String passRate = request.getParameter("psp");
//        String xmlContent = request.getParameter("dr");
//        String score = request.getParameter("sp");
//        String totalScore = request.getParameter("tp");
//        String title = request.getParameter("qt");
//        LadsQuizTool qt = this.quizToolServiceImpl.getQuizByToolId(toolId);
//        LadsQuizResultTool eqr = new LadsQuizResultTool();
//        eqr.setTitle(qt.getTitle());
//        eqr.setLadsQuizTool(qt);
//        eqr.setTotalScore(totalScore);
//        eqr.setScore(score);
//        //如果userId为空,获取当前系统登录用户的id
//        if(userId==null||"".equals(userId)||"null".equals(userId)){
//            userId = this.ladsServiceImpl.getEmbedUserId(sysType);
//        }
//        eqr.setUserId(userId);
//        eqr.setXmlContent(xmlContent);
//        eqr.setCreateTime(new Date());
//        if (passRate != null && !"".equals(passRate)) {
//            eqr.setPassRate(Double.parseDouble(passRate));
//        }
//        eqr = this.quizToolServiceImpl.saveQuizResult(eqr);
//        return null;
//    }
//
//    public String reportQuizQuestion() throws DocumentException {
//        HttpServletRequest request = super.getRequest();
//        String id = request.getParameter("id");
//        LadsQuizTool qt = this.quizToolServiceImpl.getQuizByToolId(id);
//        List list = this.quizToolServiceImpl.getQuestionVoList(qt);
//        request.setAttribute("questionList", list);
//        request.setAttribute("id", id);
//        return "monitor_quiz_question_report";
//    }
//
//    public String reportQuizQuestionDetail() throws DocumentException, UnsupportedEncodingException {
//        HttpServletRequest request = super.getRequest();
//        String id = request.getParameter("id");
//        String sysType = request.getParameter("sysType");
//        LadsQuizQuestionTool qq = this.hibernateBaseServiceImpl.findById(LadsQuizQuestionTool.class, id);
//        //获取最后提交的那次成绩
//        String sql = "from LadsQuizResultTool rt where rt.ladsQuizTool.id = '" + qq.getLadsQuizTool().getId() + "' and rt.userId is not null and rt.userId <> ''"
//                + " and rt.id in (select max(qt.id) from LadsQuizResultTool qt where qt.ladsQuizTool.id = '" + qq.getLadsQuizTool().getId() + "' group by qt.userId having count(qt.userId)>=1) order by rt.createTime desc";
//        List<LadsQuizResultTool> rList = this.hibernateBaseServiceImpl.findList(sql);
//
//        List<LadsQuizResultVo> voList = assembleResultVo(sysType, rList);
//
//        StringBuilder sb = this.quizToolServiceImpl.questionReportCreater(qq, voList);
//        request.setAttribute("sysType", sysType);
//        request.setAttribute("id", id);
//        request.setAttribute("detail", sb);
//        return "monitor_quiz_question_report_detail";
//    }
//
//    public String personalQuestionDetail() {
//        HttpServletRequest request = super.getRequest();
//        String resultId = request.getParameter("resultId");
//        String sysType = request.getParameter("sysType");
//        String qid = request.getParameter("qid");
//        request.setAttribute("referUrl", "/common/lads/ladsQuizAction_reportQuizResult.action?resultId=" + resultId + "&score=true&qid=" + qid + "&sysType=" + sysType);
//        return "referer";
//    }
//
//    public String reportQuizResult() throws DocumentException {
//        HttpServletRequest request = super.getRequest();
//        String resultId = request.getParameter("resultId");
//        String sysType = request.getParameter("sysType");
//        LadsQuizResultTool eqr = this.hibernateBaseServiceImpl.findById(LadsQuizResultTool.class, resultId);
//        StringBuilder sb = this.quizToolServiceImpl.xmlReportCreater(eqr);
//        LadsQuizResultVo vo = new LadsQuizResultVo();
//        vo.setResult(eqr);
//        LadsUserVo user = this.ladsServiceImpl.getUser(sysType, eqr.getUserId());
//        if (user != null) {
//            vo.setRealName(user.getRealName());
//        } else {
//            vo.setRealName("游客");
//        }
//        request.setAttribute("statistics", sb);
//        request.setAttribute("vo", vo);
//        if (request.getParameter("score") != null && !"".equals(request.getParameter("score"))) {
//            request.setAttribute("score", request.getParameter("score"));
//        }
//        if (request.getParameter("qid") != null && !"".equals(request.getParameter("qid"))) {
//            request.setAttribute("qid", request.getParameter("qid"));
//        }
//        request.setAttribute("sysType", sysType);
//        return "monitor_quiz_report";
//    }
//
//    public String scoreResult() throws DocumentException {
//        HttpServletRequest request = super.getRequest();
//        String resultId = request.getParameter("resultId");
//        String score[] = request.getParameterValues("score");
//        String sysType = request.getParameter("sysType");
//        LadsQuizResultTool eqr = this.hibernateBaseServiceImpl.findById(LadsQuizResultTool.class, resultId);
//        eqr = this.quizToolServiceImpl.saveScore(eqr, score);
//        request.setAttribute("referUrl", "/common/lads/ladsQuizAction_reportQuizResult.action?resultId=" + resultId + "&score=true" + "&sysType=" + sysType);
//        return "referer";
//    }
//
//    public String getUserResultList() {
//        HttpServletRequest request = super.getRequest();
//        String id = request.getParameter("id");
//        String sysType = request.getParameter("sysType");
//        LadsQuizTool quiz = this.quizToolServiceImpl.getQuizByToolId(id);
//        String quizId = quiz.getId();
//        List<String> userList = this.hibernateBaseServiceImpl.findList("select distinct rt.userId from LadsQuizResultTool rt where rt.userId is not null and rt.userId <> '' and rt.ladsQuizTool.id='" + quizId + "' order by rt.createTime");
//        List<LadsQuizUserResultVo> voList = new ArrayList<LadsQuizUserResultVo>();
//        for (String userId : userList) {
//            LadsQuizUserResultVo vo = new LadsQuizUserResultVo();
//            vo.setQuizId(quizId);
//            vo.setQuizTitle(quiz.getTitle());
//            LadsUserVo user = this.ladsServiceImpl.getUser(sysType, userId);
//            if (user != null) {
//                vo.setRealName(user.getRealName());
//            } else {
//                vo.setRealName("游客");
//            }
//            DetachedCriteria rcriteria = DetachedCriteria.forClass(LadsQuizResultTool.class);
//            rcriteria.createAlias("ladsQuizTool", "quiz");
//            rcriteria.add(Restrictions.eq("quiz.id", quizId));
//            rcriteria.add(Restrictions.eq("userId", userId));
//            rcriteria.addOrder(Order.desc("createTime"));
//            List<LadsQuizResultTool> qrl = this.hibernateBaseServiceImpl.findByCriteria(rcriteria);
//            vo.setResultList(qrl);
//            voList.add(vo);
//        }
//        Collections.sort(voList);
//        request.setAttribute("pq", quiz);
//        request.setAttribute("voList", voList);
//        request.setAttribute("sysType", sysType);
//        //request.setAttribute("urlPara", HttpUtils.createUrlPara(request));
//        return "monitor_quiz_user_result_list";
//    }
//
//    public List<LadsQuizResultVo> assembleResultVo(String sysType, List<LadsQuizResultTool> resultList) {
//        List<LadsQuizResultVo> voList = new ArrayList();
//        for (LadsQuizResultTool result : resultList) {
//            String userId = result.getUserId();
//            LadsUserVo user = this.ladsServiceImpl.getUser(sysType, userId);
//            LadsQuizResultVo vo = new LadsQuizResultVo();
//            vo.setResult(result);
//            if (user != null) {
//                vo.setRealName(user.getRealName());
//            } else {
//                vo.setRealName("游客");
//            }
//            voList.add(vo);
//        }
//        return voList;
//    }
//
//    private PrintWriter setAjaxResponse(HttpServletRequest request,
//            HttpServletResponse response) throws UnsupportedEncodingException,
//            IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("utf-8");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", -1);
//        return response.getWriter();
//    }
//}
