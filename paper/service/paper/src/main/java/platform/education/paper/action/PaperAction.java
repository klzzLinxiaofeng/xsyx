/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.paper.action;


import platform.education.paper.vo.SessionTempPaperVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.paper.constants.AnswerConstants;
import platform.education.paper.constants.ClientType;
import platform.education.paper.constants.CollectType;
import platform.education.paper.constants.CorrectType;
import platform.education.paper.constants.PaperType;
import platform.education.paper.constants.QuestionConstants;
import platform.education.paper.constants.QuestionType;
import platform.education.paper.model.PaCollect;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaUserAnswer;
import platform.education.paper.model.PaWrong;
import platform.education.paper.service.PaCollectService;
import platform.education.paper.service.PaPaperQuestionService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaUserAnswerService;
import platform.education.paper.service.PaWrongService;
import platform.education.paper.service.PaperService;
import platform.education.paper.vo.FindByPaperIdAndPosCondition;
import platform.education.paper.vo.FindCollectQuestionListCondition;
import platform.education.paper.vo.FindDistinctAnswerCondition;
import platform.education.paper.vo.FindDistinctAnswerResult;
import platform.education.paper.vo.FindWrongQuestionListCondition;
import platform.education.paper.vo.PaCollectCondition;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.PaPaperQuestionCondition;
import platform.education.paper.vo.PaQuestionCondition;
import platform.education.paper.vo.PaUserAnswerCondition;
import platform.education.paper.vo.PaWrongCondition;
import platform.education.resource.utils.UUIDUtil;

/**
 *
 * @author Administrator
 */
@Controller("paperAction")
@Scope("prototype")
@RequestMapping(value = "/common/paper")
public class PaperAction {

    @Resource
    public PaperService paperService;
    @Resource
    public PaPaperService paPaperService; 
    @Resource
    public PaQuestionService paQuestionService;
    @Resource
    public PaPaperQuestionService paPaperQuestionService;
    @Resource
    public PaCollectService paCollectService;
    @Resource
    public PaWrongService paWrongService;
    @Resource
    public PaUserAnswerService paUserAnswerService;

    private String getClient(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String client = ClientType.PC;
        if (userAgent.matches(".*Android.*") || userAgent.matches(".*ipad.*") || userAgent.matches(".*windows mobile.*") || userAgent.matches(".*iphone os.*")) {
            //移动端处理标记
            client = ClientType.MOBILE;
        }
        return client;
    }

    @RequestMapping(value = "/paperAction_loadPaperJson.action")
    public String loadPaperJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, UnsupportedEncodingException, IOException {
        String client = getClient(request);
        String paperId = request.getParameter("paperId");
        JSONObject paperJson = new JSONObject();
        PaPaper paper = this.paPaperService.findPaPaperByUuid(paperId);
        paperJson = this.paperService.putDataToPaperJson(paperJson, paper, true, null, client);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(paperJson);
        return null;
    }

    @RequestMapping(value = "/paperAction_loadQuestionListJson.action")
    public String loadQuestionListJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String client = getClient(request);
        String subjectCode = request.getParameter("subjectCode");
        String publishCode = request.getParameter("publishCode");
        String gradeCode = request.getParameter("gradeCode");
        String volumeCode = request.getParameter("volumeCode");
        String unitCode = request.getParameter("unitCode");
        String sectionCode = request.getParameter("sectionCode");
        String userId = request.getParameter("userId");
        String questionId = request.getParameter("questionId");
        JSONObject qAct = new JSONObject();
        JSONArray qArray = new JSONArray();
        PaQuestionCondition qc = new PaQuestionCondition();
        if (subjectCode != null && !"".equals(subjectCode)) {
            qc.setSubjectCode(subjectCode);
        }
        if (publishCode != null && !"".equals(publishCode)) {
            qc.setPublishCode(publishCode);
        }
        if (gradeCode != null && !"".equals(gradeCode)) {
            qc.setGradeCode(gradeCode);
        }
        if (volumeCode != null && !"".equals(volumeCode)) {
            qc.setVolumeCode(volumeCode);
        }
        if (unitCode != null && !"".equals(unitCode)) {
            qc.setUnitCode(unitCode);
        }
        if (sectionCode != null && !"".equals(sectionCode)) {
            qc.setSectionCode(sectionCode);
        }
        if (userId != null && !"".equals(userId)) {
            qc.setUserId(userId);
        }
        if (questionId != null && !"".equals(questionId)) {
            qc.setUuid(questionId);
        }
        List<PaQuestion> qList = this.paQuestionService.findPaQuestionByCondition(qc, null, Order.desc("create_date"));
        for (PaQuestion q : qList) {
            JSONObject jAct = new JSONObject();
            jAct = this.paperService.putDataToQuesJson(jAct, q, client);
            qArray.add(jAct);
        }
        qAct.put("questions", qArray);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(qAct);
        return null;
    }

    /*
     * 提供给移动微课堂做保存最近学习记录的接口
     * 
     */
//    @RequestMapping(value = "/paperAction_saveUserLog.action")
//    public String saveUserLog(HttpServletRequest request, HttpServletResponse response) {
//        String paperId = request.getParameter("paperId");
//        String userId = request.getParameter("userId");
//        String subjectCode = request.getParameter("subjectCode");
//        String publishCode = request.getParameter("publishCode");
//        String gradeCode = request.getParameter("gradeCode");
//        String volumeCode = request.getParameter("volumeCode");
//        UserLog ul = (UserLog) this.hibernateBaseServiceImpl.findObject("from UserLog ul where ul.paPaper.id = '" + paperId + "' and ul.userId = '" + userId + "'");
//        if (ul != null) {
//            ul.setCreateTime(new Date());
//            this.hibernateBaseServiceImpl.update(ul);
//        } else {
//            ul = new UserLog();
//            ul.setUserId(userId);
//            ul.setPaPaper(this.hibernateBaseServiceImpl.findById(PaPaper.class, paperId));
//            ul.setCreateTime(new Date());
//            ul.setSubjectCode(subjectCode);
//            ul.setGradeCode(gradeCode);
//            ul.setVolumeCode(volumeCode);
//            ul.setPublishCode(publishCode);
//            this.hibernateBaseServiceImpl.save(ul);
//        }
//        return null;
//    }

    @RequestMapping(value = "/paperAction_loadPaperListJson.action")
    public String loadPaperListJson(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page) throws UnsupportedEncodingException, IOException {
        String client = getClient(request);
        String subjectCode = request.getParameter("subjectCode");
        String publishCode = request.getParameter("publishCode");
        String gradeCode = request.getParameter("gradeCode");
        String phaseCode = request.getParameter("phaseCode");
        String volumeCode = request.getParameter("volumeCode");
        String unitCode = request.getParameter("unitCode");
        String sectionCode = request.getParameter("sectionCode");
        String bookId = request.getParameter("bookId");
        String bookUnitId = request.getParameter("bookUnitId");
        String bookSectionId = request.getParameter("bookSectionId");
        String paperType = request.getParameter("paperType");
        String userId = request.getParameter("userId");
        String title = request.getParameter("title");
        JSONObject qAct = new JSONObject();
        JSONArray qArray = new JSONArray();
        long current = page.getCurrentPage();
        List<PaPaper> qList = this.paperService.paperSearchImpl((paperType != null && !"".equals(paperType)) ? Integer.parseInt(paperType) : null, title, userId, null, null,
                page, subjectCode, publishCode, gradeCode, phaseCode, volumeCode, unitCode, sectionCode, bookId, bookUnitId, bookSectionId, null);
        if (current > page.getPageSize()) {
            qList = new ArrayList<PaPaper>();
        }
        for (PaPaper q : qList) {
            JSONObject jAct = new JSONObject();
            jAct = this.paperService.putDataToPaperJson(jAct, q, false, null, client);
            qArray.add(jAct);
        }
        qAct.put("papers", qArray);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(qAct);
        return null;
    }

    @RequestMapping(value = "/paperAction_collect.action")
    public String collect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String relateId = request.getParameter("relateId");
        String userId = request.getParameter("userId");
        String operate = request.getParameter("operate");
        int collectType = Integer.parseInt(request.getParameter("collectType"));
        String printFlag = null; 
        if (operate.equals("delete")) {
            PaCollectCondition cc = new PaCollectCondition();
            cc.setUserId(userId);
            cc.setRelateId(relateId);
            cc.setCollectType(collectType);
            this.paCollectService.remove(cc);
            //this.hibernateBaseServiceImpl.delete("delete from PaCollect pc where pc.userId='" + userId + "' and relateId = '" + relateId + "' and collectType = " + collectType + "");
            printFlag = "success";
        } else if (operate.equals("add")) {
            List relateList = new ArrayList();
            relateList.add(relateId);
            List collectList = this.paperService.confirmCollect(userId, relateList, collectType);
            if (collectList.size() <= 0) {
                PaCollect collect = new PaCollect();
                collect.setCreateDate(new Date());
                collect.setUuid(UUIDUtil.getUUID());
                collect.setUserId(userId);
                collect.setRelateId(relateId);
                collect.setCollectType(collectType);
                this.paCollectService.add(collect);
                //this.hibernateBaseServiceImpl.save(collect);
                printFlag = "success";
            } else {
                printFlag = "fail";
            }
        }
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(printFlag);
        return null;
    }

    @RequestMapping(value = "/paperAction_redoSuccessFromWrongList.action")
    public String redoSuccessFromWrongList(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String relateId = request.getParameter("relateId");
        int wrongType = Integer.parseInt(request.getParameter("wrongType"));
        PaWrongCondition wc = new PaWrongCondition();
        wc.setRelateId(relateId);
        wc.setWrongType(wrongType);
        wc.setUserId(userId);
        List<PaWrong> pwList = this.paWrongService.findPaWrongByCondition(wc, null, Order.desc("create_date"));
        if (pwList.size() > 0) {
            PaWrong wrong = pwList.get(0);
            wrong.setReDoSuccess("redo_success");
            this.paWrongService.modify(wrong);
        }
        return null;
    }

    @RequestMapping(value = "/paperAction_loadWrongListJson.action")
    public String loadWrongListJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String client = getClient(request);
        List<String> complexIds = new ArrayList();
        String userId = request.getParameter("userId");
        int wrongType = Integer.parseInt(request.getParameter("wrongType"));
        String subjectCode = request.getParameter("subjectCode");
        JSONObject qAct = new JSONObject();
        JSONArray qArray = new JSONArray();
        FindWrongQuestionListCondition wqlc = new FindWrongQuestionListCondition();
        wqlc.setSubjectCode(subjectCode);
        wqlc.setUserId(userId);
        List<PaPaperQuestion> pqList = this.paPaperQuestionService.findWrongQuestionList(wqlc,null,null);
        for (PaPaperQuestion ppq : pqList) {
            PaWrongCondition wc = new PaWrongCondition();
            wc.setRelateId(ppq.getUuid());
            wc.setWrongType(wrongType);
            wc.setUserId(userId);
            List<PaWrong> pwList = this.paWrongService.findPaWrongByCondition(wc, null,Order.desc("create_date"));
            if (pwList.size()<=0) {
                PaWrong pw = new PaWrong();
                JSONObject jAct = new JSONObject(); 
                PaPaperQuestion parent = this.paPaperQuestionService.getParentPaperQuestion(ppq);
                if (parent != null) {
                    //复合题处理
                    if (!complexIds.contains(parent.getUuid())) {
                        complexIds.add(parent.getUuid());
                        pw.setRelateId(parent.getUuid());
                        jAct = this.paperService.putDataToQuesJson(jAct, parent, client);
                        pw.setWrongType(wrongType);
                        pw.setUserId(userId);
                        pw.setCreateDate(new Date());
                        pw.setUuid(UUIDUtil.getUUID());
                        paWrongService.add(pw);
                        qArray.add(jAct);
                    }
                } else {
                    pw.setRelateId(ppq.getUuid());
                    jAct = this.paperService.putDataToQuesJson(jAct, ppq, client);
                    pw.setWrongType(wrongType);
                    pw.setUserId(userId);
                    pw.setCreateDate(new Date());
                    pw.setUuid(UUIDUtil.getUUID());
                    paWrongService.add(pw);
                    qArray.add(jAct);
                }
            } else {
                //判断重做正确标记是否存在，重做并正确的题目不再出现在错题本里
                PaWrong pw = pwList.get(0);
                if (pw.getReDoSuccess() == null || "".equals(pw.getReDoSuccess())) {
                    JSONObject jAct = new JSONObject();
                    jAct = this.paperService.putDataToQuesJson(jAct, ppq, client);
                    qArray.add(jAct);
                }
            }
        }
        qAct.put("questions", qArray);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(qAct);
        return null;
    }

    @RequestMapping(value = "/paperAction_loadCollectListJson.action")
    public String loadCollectListJson(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("page") Page page) throws IOException {
        String client = getClient(request);
        String userId = request.getParameter("userId");
        int collectType = Integer.parseInt(request.getParameter("collectType"));
        String subjectCode = request.getParameter("subjectCode");
        JSONObject qAct = new JSONObject();
        JSONArray qArray = new JSONArray();
        FindCollectQuestionListCondition cqlc = new FindCollectQuestionListCondition();
        cqlc.setUserId(userId);
        cqlc.setCollectType(collectType);
        cqlc.setSubjectCode(subjectCode);
        List<PaPaperQuestion> qList = this.paPaperQuestionService.findCollectQuestionList(cqlc, page, Order.desc("pc.create_date"));
        for (PaPaperQuestion q : qList) {
            JSONObject jAct = new JSONObject();
            jAct = this.paperService.putDataToQuesJson(jAct, q, client);
            jAct.put("collect", "collected");
            qArray.add(jAct);
        }
        qAct.put("questions", qArray);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(qAct);
        return null;
    }

    @RequestMapping(value = "/paperAction_loadPaperQuestionListJson.action")
    public String loadPaperQuestionListJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String client = getClient(request);
        String paperId = request.getParameter("paperId");
        String userId = request.getParameter("userId");
        String micoCourse = request.getParameter("micoCourse");
        String pos = request.getParameter("pos");
        JSONObject qAct = new JSONObject();
        JSONArray qArray = new JSONArray();
        List<PaPaperQuestion> qList;
        if (pos != null && !"".equals(pos)) {
            String[] split = pos.split(",");
            FindByPaperIdAndPosCondition pac = new FindByPaperIdAndPosCondition();
            pac.setPaperId(paperId);
            pac.setPos(split);
            qList = this.paPaperQuestionService.findByPaperIdAndPos(pac, null,Order.asc("pos"));
        }else{
            qList = this.paPaperQuestionService.findByPaperId(paperId, null, Order.asc("pos"));
        }
        StringBuilder qIds = new StringBuilder();
        List qIdList = new ArrayList();
        for (int i = 0; i < qList.size(); i++) {
            PaPaperQuestion q = qList.get(i);
            qIdList.add(q.getId());
            qIds.append("'" + q.getId() + "'");
            if (i < qList.size() - 1) {
                qIds.append(",");
            }
        }
        
        FindDistinctAnswerCondition ac = new FindDistinctAnswerCondition();
        ac.setUserId(userId);
        ac.setqIds(qIdList);
        List<FindDistinctAnswerResult> obj = this.paUserAnswerService.findDistinctAnswer(ac, null, Order.desc("create_date"));
        List<PaCollect> collectList = this.paperService.confirmCollect(userId, qIdList, CollectType.QUESTION);
        for (PaPaperQuestion q : qList) {
            String type = q.getQuestionType();
            JSONObject jAct = new JSONObject();
            jAct = this.paperService.putDataToQuesJson(jAct, q, client);
            if (micoCourse != null && !"".equals(micoCourse)) {
                String knowledge = (String) jAct.get("knowledge");
                if (knowledge != null && !"".equals(knowledge)) {
                    String[] sts = knowledge.split("@");
                    String[] kn = null;
                    if (sts.length > 1) {
                        kn = sts[sts.length - 1].split("_");
                        if (kn.length > 1) {
                            jAct.put("knowledge", kn[0]);
                        }
                    }
                }
            }
            for (FindDistinctAnswerResult o : obj) {
                String userAnswer = o.getAnswer();
                String qId = o.getPaperQuestion();
                if (qId.equals(q.getId())) {
                    jAct.put("userAnswer", userAnswer);
                    break;
                }
            }
            for (PaCollect c : collectList) {
                if (c.getRelateId().equals(q.getId())) {
                    jAct.put("collect", "collected");
                    break;
                }
            }
            if (jAct.get("collect") == null) {
                jAct.put("collect", "");
            }
            qArray.add(jAct);
        }
        qAct.put("questions", qArray);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(qAct);
        return null;
    }

    @RequestMapping(value = "/paperAction_closeCreatePaper.action")
    public String closeCreatePaper(HttpServletRequest request, HttpServletResponse response) {
        //把session临时组题的数据清空
        request.getSession().removeAttribute(QuestionConstants.SESSION_PAPER);
        return null;
    }

    @RequestMapping(value = "/paperAction_buildUpQuestion.action")
    public String buildUpQuestion(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String client = getClient(request);
        SessionTempPaperVo paperVo = (SessionTempPaperVo) request.getSession().getAttribute(QuestionConstants.SESSION_PAPER);
        String pos = request.getParameter("pos");
        JSONObject qAct = new JSONObject();
        JSONArray preArray = new JSONArray();
        JSONArray tempArray = new JSONArray();
        if (paperVo != null) {
            if (!"".equals(paperVo.getQuestionsJson())) {
                preArray = JSONArray.fromObject(paperVo.getQuestionsJson());
            }
            if (!"".equals(paperVo.getQuestionIds())) {
                for (int h = 0; h < preArray.size(); h++) {
                    JSONObject obj = (JSONObject) preArray.get(h);
                    String originalQuestionId = (String) obj.get("originalQuestionId");
                    if (originalQuestionId != null && paperVo.getQuestionIds().indexOf(originalQuestionId) == -1) {
                        preArray.remove(h);
                    }
                }
                JSONArray quesArray = JSONArray.fromObject(paperVo.getQuestionIds());
                for (int i = 0; i < quesArray.size(); i++) {
                    String quesId = (String) quesArray.get(i);
                    boolean loadFlag = true;
                    for (int v = 0; v < preArray.size(); v++) {
                        JSONObject obj = (JSONObject) preArray.get(v);
                        String originalQuestionId = (String) obj.get("originalQuestionId");
                        if (originalQuestionId != null && quesId.equals(originalQuestionId)) {
                            loadFlag = false;
                            break;
                        }
                    }
                    if (loadFlag) {
                        PaQuestion pq = this.paQuestionService.findPaQuestionByUuid(quesId);
                        if (pq != null) {
                            JSONObject jAct = new JSONObject();
                            jAct = this.paperService.putDataToQuesJson(jAct, pq, client);
                            //导入的题目要把id的键标记为原题库的id键
                            jAct.put("originalQuestionId", jAct.get("questionId"));
                            //导入的题目需要把id的键值去掉
                            jAct.remove("questionId");
                            tempArray.add(jAct);
                        }
                    }
                }
            }
        }
        preArray.addAll(tempArray);
        qAct.put("questionSize", preArray.size());
        if (pos != null && !"".equals(pos)) {
            String[] split = pos.split(",");
            List<Integer> notIn = new ArrayList();
            List<JSONObject> notInJson = new ArrayList();
            for (int y = 0; y < preArray.size(); y++) {
                JSONObject obj = (JSONObject) preArray.get(y);
                String explanation = (String) obj.get("explanation");
                if (explanation != null && QuestionConstants.NOT_INLOAD_FLAG.equals(explanation)) {
                    String questionId = (String) obj.get("questionId");
                    PaPaperQuestion ppq = this.paPaperQuestionService.findPaPaperQuestionByUuid(questionId);
                    String ex = ppq.getExplanation();
                    obj.put("explanation", ex);
                    preArray.add(y, obj);
                }
                boolean notInFlag = false;
                for (int x = 0; x < split.length; x++) {
                    if ((y + 1) != Integer.parseInt(split[x])) {
                        notInFlag = true;
                    }
                }
                if (notInFlag) {
                    notIn.add(y);
                }
            }
            for (Integer not : notIn) {
                JSONObject notObj = (JSONObject) preArray.get(not);
                notInJson.add(notObj);
            }
            for (JSONObject obj : notInJson) {
                preArray.remove(obj);
            }
        }
        qAct.put("questions", preArray);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(qAct);
        return null;
    }

    @RequestMapping(value = "/paperAction_saveTempPaper.action")
    public String saveTempPaper(HttpServletRequest request, HttpServletResponse response) {
        String tempJson = request.getParameter("tempJson");
        JSONObject temp = JSONObject.fromObject(tempJson);
        JSONArray questionIds = (JSONArray) temp.get("questionIds");
        JSONArray questionsJson = (JSONArray) temp.get("questionsJson");
        String paperId = (String) temp.get("paperId");
        String paperTitle = (String) temp.get("paperTitle");
        String paperType = (String) temp.get("paperType");
        String difficulity = (String) temp.get("difficulity");
        String subjectCode = (String) temp.get("subjectCode");
        String publishCode = (String) temp.get("publishCode");
        String gradeCode = (String) temp.get("gradeCode");
        String volumeCode = (String) temp.get("volumeCode");
        String unitCode = (String) temp.get("unitCode");
        String sectionCode = (String) temp.get("sectionCode");
        SessionTempPaperVo vo = (SessionTempPaperVo) request.getSession().getAttribute(QuestionConstants.SESSION_PAPER);
        if (vo == null) {
            vo = new SessionTempPaperVo();
        }
        if (questionIds != null) {
            vo.setQuestionIds(questionIds.toString());
        }
        if (questionsJson != null) {
            vo.setQuestionsJson(questionsJson.toString());
        }
        if (paperTitle != null) {
            vo.setTitle(paperTitle);
        }
        if (paperType != null && !"".equals(paperType)) {
            vo.setPaperType(paperType);
        }
        if (paperId != null && !"".equals(paperId)) {
            vo.setPaperId(paperId);
        }
        if (difficulity != null && !"".equals(difficulity)) {
            vo.setDifficulity(difficulity);
        }
        if (subjectCode != null && !"".equals(subjectCode)) {
            vo.setSubjectCode(subjectCode);
        }
        if (gradeCode != null && !"".equals(gradeCode)) {
            vo.setGradeCode(gradeCode);
        }
        if (publishCode != null && !"".equals(publishCode)) {
            vo.setPublishCode(publishCode);
        }
        if (volumeCode != null && !"".equals(volumeCode)) {
            vo.setVolumeCode(volumeCode);
        }
        if (unitCode != null && !"".equals(unitCode)) {
            vo.setUnitCode(unitCode);
        }
        if (sectionCode != null && !"".equals(sectionCode)) {
            vo.setSectionCode(sectionCode);
        }
        request.getSession().setAttribute(QuestionConstants.SESSION_PAPER, vo);
        return null;
    }

    @RequestMapping(value = "/paperAction_saveResult.action")
    public String saveResult(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String jsonString = request.getParameter("resultJson");
        JSONObject resultJson = JSONObject.fromObject(jsonString);
        JSONArray qArray = (JSONArray) resultJson.get("questions");
        String userId = (String) resultJson.get("userId");
        List list = this.paperService.saveUserAnswer(qArray, userId);
        PrintWriter pw = this.setAjaxResponse(request, response);
        if (list.size() > 0) {
            pw.print("success");
        } else {
            pw.print("fail");
        }
        return null;
    }

    @RequestMapping(value = "/paperAction_createPaper.action")
    public String createPaper(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String paperType = request.getParameter("paperType");
        String paperId = request.getParameter("paperId");
        if (paperType == null || "".equals(paperType)) {
            paperType = (String) request.getAttribute("paperType");
        }
        if (paperType == null || "".equals(paperType)) {
            paperType = "";
        }
        request.setAttribute("paperType", paperType);
        if (paperId == null || "".equals(paperId)) {
            //获取默认目录
            //this.getDirectory(request, false);
        } else {
            PaPaper pa = this.paPaperService.findPaPaperByUuid(paperId);
            if (pa == null) {
                request.setAttribute("failMsg", "paperNotExist");
            }
        }

        //电子书包参数
        String schoolBag = request.getParameter("schoolBag");
        //移动微课堂参数
        String micoCourse = request.getParameter("micoCourse");
        String lessonId = request.getParameter("lessonId");
        if (schoolBag == null || "".equals(schoolBag)) {
            schoolBag = (String) request.getAttribute("schoolBag");
        }
        if (schoolBag == null || "".equals(schoolBag)) {
            schoolBag = "";
        }
        if (lessonId == null || "".equals(lessonId)) {
            lessonId = (String) request.getAttribute("lessonId");
        }
        if (lessonId == null || "".equals(lessonId)) {
            lessonId = "";
        }
        request.setAttribute("schoolBag", schoolBag);
        request.setAttribute("micoCourse", micoCourse);
        request.setAttribute("lessonId", lessonId);
        return "paper_creater";
    }

    @RequestMapping(value = "/paperAction_savePaper.action")
    public String savePaper(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String jsonString = request.getParameter("paperJson");
        JSONObject returnJson = new JSONObject();
        Map returnMap = this.paperService.savePaper(jsonString);
        PaPaper paper = (PaPaper) returnMap.keySet().iterator().next();
        JSONArray returnQuesArray = (JSONArray) returnMap.get(0);
        //把session临时组题的数据清空
        request.getSession().removeAttribute(QuestionConstants.SESSION_PAPER);
        PrintWriter pw = this.setAjaxResponse(request, response);
        returnJson.put("paperId", paper.getId());
        returnJson.put("questions", returnQuesArray);
        pw.print(returnJson);
        return null;
    }

    @RequestMapping(value = "/paperAction_deletePaper.action")
    public String deletePaper(HttpServletRequest request, HttpServletResponse response) {
        String paperId = request.getParameter("paperId");
        //删除paperQuestion,先删子题目
        this.paPaperQuestionService.deleteChildren(paperId);
        //删除paperQuestion,删父题目
        PaPaperQuestionCondition qc = new PaPaperQuestionCondition();
        qc.setPaper(paperId);
        this.paPaperQuestionService.remove(qc);
        //删除paper
        PaPaperCondition pc = new PaPaperCondition();
        pc.setUuid(paperId);
        this.paPaperService.remove(pc);
//        String psql = "delete from PaPaper p where p.id = '" + paperId + "'";
//        String qqsql = "delete from PaPaperQuestion p where p.paPaper.id = '" + paperId + "' and p.paPaperQuestion is not null";
//        String qsql = "delete from PaPaperQuestion p where p.paPaper.id = '" + paperId + "'";
//        this.hibernateBaseServiceImpl.delete(qqsql);
//        this.hibernateBaseServiceImpl.delete(qsql);
//        this.hibernateBaseServiceImpl.delete(psql);
        return "referer";
    }

    @RequestMapping(value = "/paperAction_personalPaperList.action")
    public String personalPaperList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page) {
        int paperType = Integer.parseInt(request.getParameter("paperType"));
        String title = request.getParameter("title");
        String userId = request.getParameter("userId");
        String score = request.getParameter("score");
        List<PaPaper> list = this.paperService.paperSearchImpl(paperType, title, userId, score, null, page, null, null, null, null, null, null, null, null, null, null, null);
        request.setAttribute("paperList", list);
        //request.setAttribute("urlPara", HttpUtils.createUrlPara(request));
        if (paperType == PaperType.HOMEWORD) {
            return "personal_homework_list";
        } else if (paperType == PaperType.EXERCISE) {
            return "personal_exercise_list";
        } else if (paperType == PaperType.EXAM) {
            return "personal_exam_list";
        }
        return null;
    }

    @RequestMapping(value = "/paperAction_managerPaperList.action")
    public String managerPaperList(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("page") Page page) {
        String paperType = request.getParameter("paperType");
        String subjectCode = request.getParameter("subjectCode");
        String publishCode = request.getParameter("publishCode");
        String gradeCode = request.getParameter("gradeCode");
        String phaseCode = request.getParameter("phaseCode");
        String volumeCode = request.getParameter("volumeCode");
        String unitCode = request.getParameter("unitCode");
        String sectionCode = request.getParameter("sectionCode");
        String bookId = request.getParameter("bookId");
        String bookUnitId = request.getParameter("bookUnitId");
        String bookSectionId = request.getParameter("bookSectionId");
        String title = request.getParameter("title");
        //这个是管理员账号id,暂以1为id,以后会改
        final String SYS_ADMIN = "1";
        List<PaPaper> list = this.paperService.paperSearchImpl((paperType != null && !"".equals(paperType)) ? Integer.parseInt(paperType) : null, title, SYS_ADMIN, null, null, page, subjectCode, publishCode, gradeCode, phaseCode, volumeCode, unitCode, sectionCode, bookId, bookUnitId, bookSectionId, null);
        request.setAttribute("paperList", list);
        //request.setAttribute("urlPara", HttpUtils.createUrlPara(request));
        return "manager_paper_list";
    }

    @RequestMapping(value = "/paperAction_studyPaper.action")
    public String studyPaper(HttpServletRequest request, HttpServletResponse response) {
        String paperId = request.getParameter("paperId");
        String preview = request.getParameter("preview");
        String info = request.getParameter("info");
        String buildUp = request.getParameter("buildUp");
        String questionId = request.getParameter("questionId");
        String pos = request.getParameter("pos");
        if (preview != null && !"".equals(preview)) {
            SessionTempPaperVo paperVo = (SessionTempPaperVo) request.getSession().getAttribute(QuestionConstants.SESSION_PAPER);
            //request.setAttribute("questionSize", questionSize.intValue());
            request.setAttribute("preview", "true");
        } else if (info != null && !"".equals(info)) {
            request.setAttribute("info", "true");
            PaPaper paper = this.paPaperService.findPaPaperByUuid(paperId);
            if (paper != null) {
                Long questionSize = this.paPaperQuestionService.countByPaperId(paperId);
                request.setAttribute("questionSize", questionSize.intValue());
                request.setAttribute("paper", paper);
            } else {
                request.setAttribute("failMsg", "paperNotExist");
            }
        } else if (buildUp != null && !"".equals(buildUp)) {
            request.setAttribute("questionId", questionId);
            request.setAttribute("buildUp", "true");
        } else {
            PaPaper paper = this.paPaperService.findPaPaperByUuid(paperId);
            if (paper != null) {
                Long questionSize = this.paPaperQuestionService.countByPaperId(paperId);
                request.setAttribute("questionSize", questionSize.intValue());
                request.setAttribute("paper", paper);
            } else {
                request.setAttribute("failMsg", "paperNotExist");
            }
        }
        request.setAttribute("pos", pos);
        return "study_paper";
    }

    @RequestMapping(value = "/paperAction_paperLib.action")
    public String paperLib(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String paperId = request.getParameter("paperId");
        String paperType = request.getParameter("paperType");
        String quesLength = request.getParameter("quesLength");
        request.setAttribute("paperId", paperId);
        request.setAttribute("paperType", paperType);
        request.setAttribute("quesLength", quesLength);
        //getDirectory(request, true);
        return "paper_lib";
    }

    @RequestMapping(value = "/paperAction_questionLib.action")
    public String questionLib(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String paperId = request.getParameter("paperId");
        String paperType = request.getParameter("paperType");
        String quesLength = request.getParameter("quesLength");
        request.setAttribute("paperId", paperId);
        request.setAttribute("paperType", paperType);
        request.setAttribute("quesLength", quesLength);
        //getDirectory(request, true);
        return "question_lib";
    }

    //添加进移动微课堂用户记录
//    @RequestMapping(value = "/paperAction_addToVideoPaper.action")
//    public String addToVideoPaper(HttpServletRequest request, HttpServletResponse response) {
//        String paperId = request.getParameter("paperId");
//        String bookUnitId = request.getParameter("bookUnitId");
//        String bookSectionId = request.getParameter("bookSectionId");
//        String title = request.getParameter("title");
//        VideoAndPaper vap = (VideoAndPaper) this.hibernateBaseServiceImpl.findObject("from VideoAndPaper v where v.linkId = '" + paperId + "' and v.type = 'paper'");
//        if (vap == null) {
//            vap = new VideoAndPaper();
//            vap.setCreateTime(new Date());
//        }
//        vap.setTitle(title);
//        vap.setType("paper");
//        vap.setLinkId(paperId);
//        vap.setBookUnitId(bookUnitId);
//        vap.setBookSectionId(bookSectionId);
//        this.hibernateBaseServiceImpl.saveOrUpdate(vap);
//        return null;
//    }

    //把所有试题数据添加进videoPaper表
//    @RequestMapping(value = "/paperAction_addAllPaperToVideoPaper.action")
//    public String addAllPaperToVideoPaper(HttpServletRequest request, HttpServletResponse response) {
//        List<PaPaper> list = this.paperServiceImpl.paperSearchImpl(PaperType.EXAM, null, SessionConstants.SYS_ADMIN_ID, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null);
//        for (PaPaper paper : list) {
//            VideoAndPaper vap = new VideoAndPaper();
//            vap.setCreateTime(new Date());
//            vap.setTitle(paper.getTitle());
//            vap.setType("paper");
//            vap.setLinkId(paper.getId());
//            vap.setBookUnitId(paper.getBookUnitId());
//            vap.setBookSectionId(paper.getBookSectionId());
//            this.hibernateBaseServiceImpl.saveOrUpdate(vap);
//        }
//        System.out.println("已全部完成导入");
//        return null;
//    }

    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }
}
