/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.paper.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.storage.ftp.FtpHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.validation.constraints.Null;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXFormula.TeXIconBuilder;
import org.scilab.forge.jlatexmath.TeXIcon;

import platform.education.paper.constants.ClientType;
import platform.education.paper.constants.CorrectType;
import platform.education.paper.constants.QuestionConstants;
import platform.education.paper.constants.QuestionType;
import platform.education.paper.model.PaCollect;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaUserAnswer;
import platform.education.paper.service.PaCollectService;
import platform.education.paper.service.PaPaperQuestionService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaUserAnswerService;
import platform.education.paper.service.PaUserPaperService;
import platform.education.paper.service.PaWrongService;
import platform.education.paper.service.PaperService;
import platform.education.paper.vo.FindDistinctAnswerCondition;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.PaPaperQuestionCondition;
import platform.education.paper.vo.PaQuestionCondition;
import platform.education.paper.vo.PaUserAnswerCondition;
import platform.education.resource.utils.UUIDUtil;

/**
 *
 * @author Administrator
 */ 
public class PaperServiceImpl implements PaperService {

    private PaCollectService paCollectService;
    private PaPaperService paPaperService;
    private PaPaperQuestionService paPaperQuestionService;
    private PaQuestionService paQuestionService;
    private PaUserAnswerService paUserAnswerService;
    private PaUserPaperService paUserPaperService;
    private PaWrongService paWrongService;

    public void setPaCollectService(PaCollectService paCollectService) {
        this.paCollectService = paCollectService;
    }

    public void setPaPaperService(PaPaperService paPaperService) {
        this.paPaperService = paPaperService;
    }

    public void setPaPaperQuestionService(PaPaperQuestionService paPaperQuestionService) {
        this.paPaperQuestionService = paPaperQuestionService;
    }

    public void setPaQuestionService(PaQuestionService paQuestionService) {
        this.paQuestionService = paQuestionService;
    }

    public void setPaUserAnswerService(PaUserAnswerService paUserAnswerService) {
        this.paUserAnswerService = paUserAnswerService;
    }

    public void setPaUserPaperService(PaUserPaperService paUserPaperService) {
        this.paUserPaperService = paUserPaperService;
    }

    public void setPaWrongService(PaWrongService paWrongService) {
        this.paWrongService = paWrongService;
    }

    public PaPaperQuestion copyQuestion(PaPaperQuestion ppq, PaQuestion pq, PaPaper paper) {
        ppq.setAnswer(pq.getAnswer());
        ppq.setCreateDate(pq.getCreateDate());
        ppq.setModifyDate(pq.getModifyDate());
        ppq.setContent(pq.getContent());
        ppq.setPaper(paper.getUuid());
        ppq.setPos(pq.getPos());
        ppq.setCorrectAnswer(pq.getCorrectAnswer());
        ppq.setDifficulity(pq.getDifficulity());
        ppq.setGroupId(pq.getGroupId());
        ppq.setGroupTitle(pq.getGroupTitle());
        ppq.setExplanation(pq.getExplanation());
        ppq.setExtraContent(pq.getExtraContent());
        ppq.setScore(pq.getScore());
        ppq.setGradeCode(pq.getGradeCode());
        ppq.setSubjectCode(pq.getSubjectCode());
        ppq.setPublishCode(pq.getPublishCode());
        ppq.setQuestionType(pq.getQuestionType());
        ppq.setSuperQuestionId(pq.getUuid());
        ppq.setVolumeCode(pq.getVolumeCode());
        ppq.setUnitCode(pq.getUnitCode());
        ppq.setSectionCode(pq.getSectionCode());
        ppq.setBookId(pq.getBookId());
        ppq.setBookUnitId(pq.getBookUnitId());
        ppq.setBookSectionId(pq.getBookSectionId());
        ppq.setKnowledge(pq.getKnowledge());
        return ppq;
    }

    public List saveComplex(JSONObject ques, PaPaper paper, String source, String userId) {
        JSONArray subQuestions = (JSONArray) ques.get("questions");
        PaPaperQuestion ppq;
        PaQuestion pq;
        //复合题处理子题目返回id
        JSONArray subReturnArray = new JSONArray();
        //保存复合题大题
        List complexList = saveQuestion(ques, paper, source, userId);
        ppq = (PaPaperQuestion) complexList.get(0);
        pq = (PaQuestion) complexList.get(1);
        if (subQuestions != null) {
            for (int i = 0; i < subQuestions.size(); i++) {
                JSONObject sub = (JSONObject) subQuestions.get(i);
                //保存复合题小题
                List subList = saveQuestion(sub, paper, source, userId);
                PaPaperQuestion subPpq = (PaPaperQuestion) subList.get(0);
                PaQuestion subPq = (PaQuestion) subList.get(1);
                //设置父题
                subPpq.setParentQuestion(ppq.getUuid());
                subPq.setParentQuestion(pq.getUuid());
                this.paQuestionService.modify(subPq);
                this.paPaperQuestionService.modify(subPpq);
                JSONObject subReturnJson = new JSONObject();
                subReturnJson.put("pos", subPpq.getPos());
                subReturnJson.put("questionId", subPpq.getUuid());
                subReturnArray.add(subReturnJson);
            }
        }
        complexList.add(subReturnArray);
        return complexList;
    }

    public Map<PaPaper,JSONArray> savePaper(String jsonString) {
        List<String> questionIdList = new ArrayList();
        JSONObject paperJson = JSONObject.fromObject(jsonString);
        JSONArray questionsArray = (JSONArray) paperJson.get("questions");
        JSONArray returnQuesArray = new JSONArray();
        String userId = (String) paperJson.get("userId");
        String paperId = (String) paperJson.get("paperId");
        String paperTitle = (String) paperJson.get("paperTitle");
        Integer paperType = (Integer) paperJson.get("paperType");
        Integer difficulity = (Integer) paperJson.get("difficulity");
        String score = (String) paperJson.get("score");
        PaPaper paper;
        if (paperId != null && !"".equals(paperId)) {
            paper = this.paPaperService.findPaPaperByUuid(paperId);
            paper.setModifyDate(new Date());
        } else {
            paper = new PaPaper();
            paper.setUuid(UUIDUtil.getUUID());
            paper.setCreateDate(new Date());
        }
        if (paperType != null) {
            paper.setPaperType(paperType);
        }
        if (difficulity != null) {
            paper.setDifficulity(difficulity);
        }
        if (score != null && !"".equals(score)) {
            paper.setScore(Double.parseDouble(score));
        }
        paper.setUserId(userId);
        paper.setTitle(paperTitle);
        paper.setSubjectCode((String) paperJson.get("subjectCode"));
        paper.setPublishCode((String) paperJson.get("publishCode"));
        paper.setGradeCode((String) paperJson.get("gradeCode"));
        paper.setVolumeCode((String) paperJson.get("volumeCode"));
        paper.setUnitCode((String) paperJson.get("unitCode"));
        paper.setSectionCode((String) paperJson.get("sectionCode"));
        paper.setBookId((String) paperJson.get("bookId"));
        paper.setKnowledge((String) paperJson.get("knowledge"));
        paper.setBookUnitId((String) paperJson.get("bookUnitId"));
        paper.setBookSectionId((String) paperJson.get("bookSectionId"));
        if (paperId != null && !"".equals(paperId)) {
            paper = this.paPaperService.modify(paper);
        } else {
            paper = this.paPaperService.add(paper);
        }
        for (int i = 0; i < questionsArray.size(); i++) {
            JSONObject ques = questionsArray.getJSONObject(i);
            String paQuestionId = (String) ques.get("questionId");
            String originalQuestionId = (String) ques.get("originalQuestionId");
            String type = (String) ques.get("type");
            PaPaperQuestion paperQuestion;
            PaQuestion question;
            JSONObject returnQuesJson = new JSONObject();
            //复合题处理方法
            if (type.equals(QuestionType.COMPLEX)) {
                List complexList = this.saveComplex(ques, paper, paper.getTitle(), userId);
                paperQuestion = (PaPaperQuestion) complexList.get(0);
                question = (PaQuestion) complexList.get(1);
                JSONArray subReturnArray = (JSONArray) complexList.get(2);
                questionIdList.add(paperQuestion.getUuid());
                for (int h = 0; h < subReturnArray.size(); h++) {
                    JSONObject subjson = (JSONObject) subReturnArray.get(h);
                    questionIdList.add((String) subjson.get("questionId"));
                }
                returnQuesJson.put("pos", paperQuestion.getPos());
                returnQuesJson.put("questionId", paperQuestion.getUuid());
                returnQuesJson.put("questions", subReturnArray);
            } else {
                //普通题目处理方法 
                List questionList = this.saveQuestion(ques, paper, paper.getTitle(), userId);
                paperQuestion = (PaPaperQuestion) questionList.get(0);
                question = (PaQuestion) questionList.get(1);
                questionIdList.add(paperQuestion.getUuid());
                returnQuesJson.put("pos", paperQuestion.getPos());
                returnQuesJson.put("questionId", paperQuestion.getUuid());
            }
            returnQuesArray.add(returnQuesJson);
        }
        //删除不在题目列表的题目
        this.deleteNotInUpdateQuestions(paper.getUuid(), questionIdList);
        Map returnMap = new HashMap();
        returnMap.put(paper,returnQuesArray);
        return returnMap;
    }    

    public List saveQuestion(JSONObject ques, PaPaper paper, String source, String userId) {
        String questionId = (String) ques.get("questionId");
        List list = new ArrayList();
        PaPaperQuestion ppq;
        PaQuestion pq;
        if (questionId != null && !"".equals(questionId)) {
            ppq = this.paPaperQuestionService.findPaPaperQuestionByUuid(questionId);
            if (ppq != null) {
                pq = this.paQuestionService.findPaQuestionByUuid(ppq.getSuperQuestionId());
                if (pq != null) {
                    //判断更改该题目的用户是不是原题目的创建者,如果是,则有权把原题目修改,如果不是则只改动组卷后的题目
                    if (pq.getUserId().equals(userId)) {
                        pq = this.setQuestionValue(pq, ques, source, userId);
                        this.paQuestionService.modify(pq);
                    }
                } else {
                    pq = new PaQuestion();
                    pq.setCreateDate(new Date());
                    pq.setUuid(UUIDUtil.getUUID());
                    pq = this.setQuestionValue(pq, ques, source, userId);
                    this.paQuestionService.add(pq);
                }
                ppq = this.setPaperQuestionValue(ppq, ques, paper, userId);
                this.paPaperQuestionService.modify(ppq);
            } else {
                pq = new PaQuestion();
                pq.setCreateDate(new Date());
                pq.setUuid(UUIDUtil.getUUID());
                pq = this.setQuestionValue(pq, ques, source, userId);
                this.paQuestionService.add(pq);
                ppq = new PaPaperQuestion();
                ppq = this.copyQuestion(ppq, pq, paper);
                this.paPaperQuestionService.add(ppq);
            }
        } else {
            pq = new PaQuestion();
            pq.setCreateDate(new Date());
            pq.setUuid(UUIDUtil.getUUID());
            pq = this.setQuestionValue(pq, ques, source, userId);
            this.paQuestionService.add(pq);
            ppq = new PaPaperQuestion();
            ppq = this.copyQuestion(ppq, pq, paper);
            this.paPaperQuestionService.add(ppq);
        }
        list.add(ppq);
        list.add(pq);
        return list;
    }

    public PaQuestion setQuestionValue(PaQuestion question, JSONObject ques, String quesSource, String userId) {
        String answer = confirmAnswerType(ques.get("answer"));
        question.setAnswer(answer);
        question.setContent((String) ques.get("content"));
        question.setExtraContent((String) ques.get("extraContent"));
        String correctAnswer = confirmAnswerType(ques.get("correctAnswer"));
        question.setCorrectAnswer(correctAnswer);
        question.setQuestionType((String) ques.get("type"));
        question.setSubjectCode((String) ques.get("subjectCode"));
        question.setPublishCode((String) ques.get("publishCode"));
        question.setGradeCode((String) ques.get("gradeCode"));
        question.setVolumeCode((String) ques.get("volumeCode"));
        question.setUnitCode((String) ques.get("unitCode"));
        question.setSectionCode((String) ques.get("sectionCode"));
        question.setBookId((String) ques.get("bookId"));
        question.setBookUnitId((String) ques.get("bookUnitId"));
        question.setBookSectionId((String) ques.get("bookSectionId"));
        question.setKnowledge((String) ques.get("knowledge"));
        question.setPos((Integer) ques.get("pos"));
        question.setGroupTitle((String) ques.get("groupTitle"));
        question.setGroupId((String) ques.get("groupId"));
        Integer difficulity = (Integer) ques.get("difficulity");
        if (difficulity != null) {
            question.setDifficulity(difficulity);
        }
        String score = (String) ques.get("score");
        if (score != null && !"".equals(score)) {
            question.setScore(Double.parseDouble(score));
        }
        String explanation = (String) ques.get("explanation");
        if (explanation != null && !(QuestionConstants.NOT_INLOAD_FLAG.equals(explanation))) {
            question.setExplanation(explanation);
        }
        String extraContent = (String) ques.get("extraContent");
        if (extraContent != null) {
            question.setExtraContent(extraContent);
        }
        if (userId != null && !"".equals(userId)) {
            //题目的创建用户
            question.setUserId(userId);
        }
        if (quesSource != null && !"".equals(quesSource)) {
            //题目来源
            question.setSource(quesSource);
        }
        return question;
    }

    public PaPaperQuestion setPaperQuestionValue(PaPaperQuestion question, JSONObject ques, PaPaper paper, String userId) {
        String answer = confirmAnswerType(ques.get("answer"));
        question.setAnswer(answer);
        question.setContent((String) ques.get("content"));
        question.setExtraContent((String) ques.get("extraContent"));
        String correctAnswer = confirmAnswerType(ques.get("correctAnswer"));
        question.setCorrectAnswer(correctAnswer);
        question.setQuestionType((String) ques.get("type"));
        question.setSubjectCode((String) ques.get("subjectCode"));
        question.setPublishCode((String) ques.get("publishCode"));
        question.setGradeCode((String) ques.get("gradeCode"));
        question.setVolumeCode((String) ques.get("volumeCode"));
        question.setUnitCode((String) ques.get("unitCode"));
        question.setSectionCode((String) ques.get("sectionCode"));
        question.setBookId((String) ques.get("bookId"));
        question.setBookUnitId((String) ques.get("bookUnitId"));
        question.setBookSectionId((String) ques.get("bookSectionId"));
        question.setKnowledge((String) ques.get("knowledge"));
        question.setPos((Integer) ques.get("pos"));
        question.setGroupTitle((String) ques.get("groupTitle"));
        question.setGroupId((String) ques.get("groupId"));
        question.setPaper(paper.getUuid());
        String originalQuestionId = (String) ques.get("originalQuestionId");
        if (originalQuestionId != null && !"".equals(originalQuestionId)) {
            question.setSuperQuestionId(originalQuestionId);
        }
        Integer difficulity = (Integer) ques.get("difficulity");
        if (difficulity != null) {
            question.setDifficulity(difficulity);
        }
        String score = (String) ques.get("score");
        if (score != null && !"".equals(score)) {
            question.setScore(Double.parseDouble(score));
        }
        String explanation = (String) ques.get("explanation");
        if (explanation != null && !(QuestionConstants.NOT_INLOAD_FLAG.equals(explanation))) {
            question.setExplanation(explanation);
        }
        return question;
    }

    public String confirmAnswerType(Object obj) {
        String answer = null;
        if (obj instanceof JSONArray) {
            answer = ((JSONArray) obj).toString();
        } else if (obj instanceof String) {
            answer = (String) obj;
        }
        return answer;
    }

    public Object confirmStudyAnswerType(String answer, String client) {
        if (answer != null && !"".equals(answer)) {
            answer = filterByClient(answer, client);
            if (answer.startsWith("[") && answer.endsWith("]")) {
                //选择题答案区做去除部分html标签处理,防止页面答案区域变形
                answer = answer.replaceAll("<p>", "");
                answer = answer.replaceAll("</p>", "");
                answer = answer.replaceAll("<\\/p>", "");
                answer = answer.replaceAll("<br>", "");
                answer = answer.replaceAll("</br>", "");
                answer = answer.replaceAll("<br/>", "");
                return JSONArray.fromObject(answer);
            } else {
                return answer;
            }
        } else {
            return "";
        }
    }

    public void deleteNotInUpdateQuestions(String paId, List<String> ids) {
        //删除不在题目列表的题目
        this.paPaperQuestionService.deleteNotInUpdateQuestions(paId, ids);
    }

    public List<PaUserAnswer> saveUserAnswer(JSONArray qArray, String userId) {
        List<PaUserAnswer> answerList = new ArrayList();
        for (int i = 0; i < qArray.size(); i++) {
            JSONObject q = (JSONObject) qArray.get(i);
            String questionId = (String) q.get("questionId");
            PaUserAnswer answer = new PaUserAnswer();
            answer.setCreateDate(new Date());
            answer.setUuid(UUIDUtil.getUUID());
            if (q.get("userAnswer") != null) {
                String userAnswer = confirmAnswerType(q.get("userAnswer"));
                answer.setAnswer(userAnswer);
            }
            if (q.get("correct") != null) {
                answer.setCorrect((Integer) q.get("correct"));
            }
            answer.setPaperQuestion(questionId);
            answer.setUserId(userId);
            answer.setFinalAnswer((Integer) q.get("finalAnswer"));
            this.paUserAnswerService.add(answer);
            answerList.add(answer);
        }
        return answerList;
    }

    public String trimHtml2Txt(String html, String[] filterTags) {
        //什么都没实现
        return null;
    }

    public static void writeFile(File file, String sets) {
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            PrintWriter out = new PrintWriter(fw);
            out.write(sets);
            out.println();
            fw.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(PaperServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String ReadFile(File file) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return laststr;
    }

    public String latexToImage(String latex) {
        latex = latex.trim();
        FtpHandler ftp = new FtpHandler();
        boolean existFlag = false;
        String returnString = null;
        String uploadUrl = ftp.getPrefixPath() + "paper/editor/math/";
        File latexJsonFile = new File(ftp.getAbsolutePrefix() + uploadUrl + "latex.json");
        if (latexJsonFile.exists()) {
            String jsonFileString = ReadFile(latexJsonFile);
            JSONObject ljo = JSONObject.fromObject(jsonFileString);
            JSONArray lja = ljo.getJSONArray("latexArray");
            for (int i = 0; i < lja.size(); i++) {
                JSONObject sljo = (JSONObject) lja.get(i);
                String latexValue = (String) sljo.get("latex");
                String imageName = (String) sljo.get("imageName");
                String height = (String) sljo.get("height");
                String width = (String) sljo.get("width");
                if (latex.equals(latexValue)) {
                    existFlag = true;
                    returnString = width + "|" + height + "|" + ftp.getHttpPrefix() + uploadUrl + imageName + ".png";
                    break;
                }
            }
        } else {
            try {
                new File(ftp.getAbsolutePrefix() + uploadUrl).mkdirs();
                latexJsonFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PaperServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject root = new JSONObject();
            JSONArray array = new JSONArray();
            root.put("latexArray", array);
            writeFile(latexJsonFile, root.toString());
        }

        if (!existFlag) {
            //Latex公式对象
            TeXFormula fotmula = new TeXFormula(latex);
            //Latex icon对象
            //setSize 设置字体大小
            TeXIcon icon = fotmula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(32).build();
            icon.setInsets(new Insets(5, 5, 5, 5));
            //生成图片
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            //设置背景颜色
            g2.setColor(new Color(22, 2, 2, 0));
            g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
            JLabel jl = new JLabel();
            //设置字体颜色
            jl.setForeground(Color.BLACK);
            icon.paintIcon(jl, g2, 0, 0);
            String imageName = FtpHandler.getRandomNameByTime();
            File mathFile = new File(ftp.getAbsolutePrefix() + uploadUrl + imageName + ".png");
            try {
                ImageIO.write(image, "png", mathFile);
                String jsonFileString = ReadFile(latexJsonFile);
                JSONObject root = JSONObject.fromObject(jsonFileString);
                JSONArray latexArray = (JSONArray) root.get("latexArray");
                JSONObject latexObj = new JSONObject();
                latexObj.put("latex", latex);
                latexObj.put("imageName", imageName);
                latexObj.put("width", String.valueOf(icon.getIconWidth()));
                latexObj.put("height", String.valueOf(icon.getIconHeight()));
                latexArray.add(latexObj);
                root.put("latexArray", latexArray);
                writeFile(latexJsonFile, root.toString());
                returnString = icon.getIconWidth() + "|" + icon.getIconHeight() + "|" + ftp.getHttpPrefix() + uploadUrl + imageName + ".png";
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("公式绘图错误!位置为:" + mathFile.getAbsolutePath());
                returnString = "公式绘图错误!";
            }
        }
        return returnString;
    }

    public String filterByClient(String content, String client) {
        if (content != null && !"".equals(content)) {
            if (client.equals(ClientType.PC)) {
            } else if (client.equals(ClientType.MOBILE)) {
                try {
                    //content = trimHtml2Txt(content, new String[]{"img", "b"});
                    Parser parser = new Parser();
                    parser.setInputHTML(content);
                    NodeFilter mathFilter = new AndFilter(new TagNameFilter("span"), new HasAttributeFilter("class", "mathquill-rendered-math"));
                    NodeList mathList = parser.parse(mathFilter);
                    for (int i = 0; i < mathList.size(); i++) {
                        Parser mathParser = new Parser();
                        Node math = mathList.elementAt(i);
                        String mathHtml = math.toHtml();
                        mathParser.setInputHTML(mathHtml);
                        NodeFilter latexFilter = new AndFilter(new TagNameFilter("span"), new HasAttributeFilter("class", "latex"));
                        NodeList latexList = mathParser.parse(latexFilter);
                        //判断公式html区是否有latex码,有则做图片转换处理,没有则不处理
                        if (latexList.size() > 0) {
                            String latexHtml = latexList.elementAt(0).toHtml();
                            String latex = latexHtml.substring(latexHtml.indexOf(">") + 1, latexHtml.lastIndexOf("<"));
                            String imagePath = latexToImage(latex);
                            String[] splitImage = imagePath.split("\\|");
                            String texHtml = "<img width='" + splitImage[0] + "px' height='" + splitImage[1] + "px' src='" + splitImage[2] + "' class='latex' />";
                            content = content.replace(mathHtml, texHtml);
                        }
                    }
                } catch (ParserException ex) {
                    Logger.getLogger(PaperServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return content;
    }

    public JSONObject putDataToQuesJson(JSONObject jAct, PaQuestion q, String client) {
        jAct.put("answer", this.confirmStudyAnswerType(q.getAnswer(), client));
        jAct.put("correctAnswer", this.confirmStudyAnswerType(q.getCorrectAnswer(), client));
        jAct.put("type", q.getQuestionType());
        jAct.put("content", q.getContent());
        jAct.put("questionId", q.getUuid());
        jAct.put("extraContent", q.getExtraContent());
        jAct.put("difficulity", q.getDifficulity());
        jAct.put("groupId", q.getGroupId());
        jAct.put("groupTitle", q.getGroupTitle());
        jAct.put("score", q.getScore());
        jAct.put("explanation", q.getExplanation());
        jAct.put("subjectCode", q.getSubjectCode());
        jAct.put("publishCode", q.getPublishCode());
        jAct.put("gradeCode", q.getGradeCode());
        jAct.put("volumeCode", q.getVolumeCode());
        jAct.put("unitCode", q.getUnitCode());
        jAct.put("sectionCode", q.getSectionCode());
        jAct.put("bookId", q.getBookId());
        jAct.put("bookUnitId", q.getBookUnitId());
        jAct.put("bookSectionId", q.getBookSectionId());
        jAct.put("knowledge", q.getKnowledge());
        jAct.put("createTime", q.getCreateDate()!= null ? new SimpleDateFormat("yyyy-MM-dd").format(q.getCreateDate()).toString() : "");
        //复合题
        if (q.getQuestionType().equals(QuestionType.COMPLEX)) {
            PaQuestionCondition qc = new PaQuestionCondition();
            qc.setParentQuestion(q.getUuid());
            List<PaQuestion> subList = this.paQuestionService.findPaQuestionByCondition(qc, null, Order.asc("pos"));
            //List<PaQuestion> subList = this.hibernateBaseDao.find("from PaQuestion pq where pq.paQuestion.id = '" + q.getId() + "' order by pq.pos");
            JSONArray subArray = new JSONArray();
            for (PaQuestion sppq : subList) {
                JSONObject sub = new JSONObject();
                subArray.add(putDataToQuesJson(sub, sppq, client));
            }
            jAct.put("questions", subArray);
        }
        return jAct;
    }

    public JSONObject putDataToQuesJson(JSONObject jAct, PaPaperQuestion q, String client) {
        jAct.put("answer", this.confirmStudyAnswerType(q.getAnswer(), client));
        jAct.put("correctAnswer", this.confirmStudyAnswerType(q.getCorrectAnswer(), client));
        jAct.put("type", q.getQuestionType());
        jAct.put("content", this.filterByClient(q.getContent(), client));
        jAct.put("questionId", q.getUuid());
        jAct.put("pos", q.getPos());
        jAct.put("extraContent", this.filterByClient(q.getExtraContent(), client));
        jAct.put("difficulity", q.getDifficulity());
        jAct.put("groupId", q.getGroupId());
        jAct.put("groupTitle", q.getGroupTitle());
        jAct.put("score", q.getScore());
        jAct.put("explanation", this.filterByClient(q.getExplanation(), client));
        jAct.put("subjectCode", q.getSubjectCode());
        jAct.put("publishCode", q.getPublishCode());
        jAct.put("gradeCode", q.getGradeCode());
        jAct.put("volumeCode", q.getVolumeCode());
        jAct.put("unitCode", q.getUnitCode());
        jAct.put("sectionCode", q.getSectionCode());
        jAct.put("bookId", q.getBookId());
        jAct.put("bookUnitId", q.getBookUnitId());
        jAct.put("bookSectionId", q.getBookSectionId());
        jAct.put("knowledge", q.getKnowledge());
        jAct.put("createTime", q.getCreateDate()!= null ? new SimpleDateFormat("yyyy-MM-dd").format(q.getCreateDate()).toString() : "");
        //复合题
        if (q.getQuestionType().equals(QuestionType.COMPLEX)) {
            PaPaperQuestionCondition qc = new PaPaperQuestionCondition();
            qc.setParentQuestion(q.getUuid());
            List<PaPaperQuestion> subList = this.paPaperQuestionService.findPaPaperQuestionByCondition(qc, null, Order.asc("pos"));
            //List<PaPaperQuestion> subList = this.hibernateBaseDao.find("from PaPaperQuestion pq where pq.paPaperQuestion.id = '" + q.getId() + "' order by pq.pos");
            JSONArray subArray = new JSONArray();
            for (PaPaperQuestion sppq : subList) {
                JSONObject sub = new JSONObject();
                subArray.add(putDataToQuesJson(sub, sppq, client));
            }
            jAct.put("questions", subArray);
        }
        return jAct;
    }

    public JSONObject putDataToPaperJson(JSONObject jAct, PaPaper p, boolean relateQuestions, String currentUserId, String client) {
        if (relateQuestions) {
            //paper带完整题目的json
            JSONArray questions = new JSONArray();
            List<PaPaperQuestion> qList = this.paPaperQuestionService.findByPaperId(p.getUuid(), null, Order.asc("pos"));
            //List<PaPaperQuestion> qList = this.hibernateBaseDao.find("from PaPaperQuestion q where q.paPaper.id='" + p.getId() + "' and q.paPaperQuestion is null order by q.pos");
            for (PaPaperQuestion ques : qList) {
                JSONObject qAct = new JSONObject();
                questions.add(putDataToQuesJson(qAct, ques, client));
            }
            jAct.put("questions", questions);
        } else {
            //paper只带题目id的json
            List qids = this.paPaperQuestionService.findIdByPaperId(p.getUuid(), null, Order.asc("pos"));
            //List qids = this.hibernateBaseDao.find("select q.id from PaPaperQuestion q where q.paPaper.id='" + p.getId() + "' and q.paPaperQuestion is null order by q.pos");
            JSONArray array = JSONArray.fromObject(qids);
            jAct.put("questionIds", array);
            if (currentUserId != null && !"".equals(currentUserId)) {
                //去除重复做过的题目记录
                FindDistinctAnswerCondition ac = new FindDistinctAnswerCondition();
                ac.setUserId(currentUserId);
                ac.setqIds(qids);
                List uaIds = this.paUserAnswerService.findDistinctQuestion(ac, null, null);
                JSONArray uaArray = JSONArray.fromObject(uaIds);
                jAct.put("finishedQuestionIds", uaArray);
            }
        }
        jAct.put("paperId", p.getUuid());
        jAct.put("paperTitle", p.getTitle());
        jAct.put("paperType", p.getPaperType());
        jAct.put("subjectCode", p.getSubjectCode());
        jAct.put("publishCode", p.getPublishCode());
        jAct.put("gradeCode", p.getGradeCode());
        jAct.put("volumeCode", p.getVolumeCode());
        jAct.put("unitCode", p.getUnitCode());
        jAct.put("sectionCode", p.getSectionCode());
        jAct.put("bookId", p.getBookId());
        jAct.put("bookUnitId", p.getBookUnitId());
        jAct.put("bookSectionId", p.getBookSectionId());
        jAct.put("knowledge", p.getKnowledge());
//        jAct.put("bookName", this.getBookName(p.getBookId()));
//        jAct.put("bookUnitName", this.getBookUnit(p.getBookUnitId()));
//        jAct.put("bookSectionName", this.getBookSection(p.getBookSectionId()));
        jAct.put("score", p.getScore());
        jAct.put("difficulity", p.getDifficulity());
        jAct.put("createTime", p.getCreateDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()).toString() : "");
        return jAct;
    }

    public JSONObject getUserAnswerStatistics(String userId) {
        int totalCorrectCount = 0;
        Map subjectMap = new HashMap();
        Map subjectCorrectMap = new HashMap();
        JSONObject jAct = new JSONObject();
        JSONArray subjectArray = new JSONArray();
        PaUserAnswerCondition uac = new PaUserAnswerCondition();
        uac.setUserId(userId);
        List<PaUserAnswer> uaList = this.paUserAnswerService.findPaUserAnswerByCondition(uac, null, null);
        //List<PaUserAnswer> uaList = this.hibernateBaseDao.find("from PaUserAnswer ua where ua.userId='" + userId + "'");
        jAct.put("totalQuestions", uaList.size());
        for (PaUserAnswer ua : uaList) {
            PaPaperQuestion ppq = this.paUserAnswerService.getPaperQuestion(ua);
            //PaPaperQuestion ppq = ua.getPaPaperQuestion();
            Integer correct = ua.getCorrect();
            Integer subjectQuesCount;
            if (ppq.getSubjectCode() != null && !"".equals(ppq.getSubjectCode())) {
                if (!subjectMap.containsKey(ppq.getSubjectCode())) {
                    subjectQuesCount = 0;
                    subjectQuesCount++;
                    subjectMap.put(ppq.getSubjectCode(), subjectQuesCount);
                    if (correct != null && correct == CorrectType.CORRECT) {
                        Integer subjectCorrectCount = 0;
                        subjectCorrectCount++;
                        subjectCorrectMap.put(ppq.getSubjectCode(), subjectCorrectCount);
                    }
                } else {
                    subjectQuesCount = (Integer) subjectMap.get(ppq.getSubjectCode());
                    subjectQuesCount++;
                    subjectMap.put(ppq.getSubjectCode(), subjectQuesCount);
                    if (correct != null && correct == CorrectType.CORRECT) {
                        Integer subjectCorrectCount;
                        if (!subjectCorrectMap.containsKey(ppq.getSubjectCode())) {
                            subjectCorrectCount = 0;
                        } else {
                            subjectCorrectCount = (Integer) subjectCorrectMap.get(ppq.getSubjectCode());
                        }
                        subjectCorrectCount++;
                        subjectCorrectMap.put(ppq.getSubjectCode(), subjectCorrectCount);
                    }
                }
            } else {
                //没有科目的按其他科目处理
                if (!subjectMap.containsKey("032")) {
                    subjectQuesCount = 0;
                    subjectQuesCount++;
                    subjectMap.put("032", subjectQuesCount);
                    if (correct != null && correct == CorrectType.CORRECT) {
                        Integer subjectCorrectCount = 0;
                        subjectCorrectCount++;
                        subjectCorrectMap.put("032", subjectCorrectCount);
                    }
                } else {
                    subjectQuesCount = (Integer) subjectMap.get("032");
                    subjectQuesCount++;
                    subjectMap.put("032", subjectQuesCount);
                    if (correct != null && correct == CorrectType.CORRECT) {
                        Integer subjectCorrectCount;
                        if (!subjectCorrectMap.containsKey("032")) {
                            subjectCorrectCount = 0;
                        } else {
                            subjectCorrectCount = (Integer) subjectCorrectMap.get("032");
                        }
                        subjectCorrectCount++;
                        subjectCorrectMap.put("032", subjectCorrectCount);
                    }
                }
            }
            if (correct != null && correct == CorrectType.CORRECT) {
                totalCorrectCount++;
            }
        }
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(0);
        if (uaList.size() > 0) {
            jAct.put("totalCorrectPercent", nt.format(((double) totalCorrectCount / uaList.size())));
        } else {
            jAct.put("totalCorrectPercent", "无");
        }
        for (Object key : subjectMap.keySet()) {
            String subjectCode = (String) key;
            //String subjectName = CatalogDicUtil.getSubject(subjectCode);
            int subjectQues = (Integer) subjectMap.get(key);
            JSONObject sub = new JSONObject();
            String subjectCorrectPercent;
            if (subjectQues > 0 && subjectCorrectMap.get(subjectCode) != null) {
                System.out.println("subjectQues=" + subjectQues);
                System.out.println("subjectCorrectMap=" + subjectCorrectMap);
                System.out.println("subjectCorrectMap.get(subjectCode)=" + subjectCorrectMap.get(subjectCode));
                subjectCorrectPercent = nt.format((double) ((Integer) subjectCorrectMap.get(subjectCode)) / subjectQues);
            } else {
                subjectCorrectPercent = "0%";
            }
            //sub.put("subjectName", subjectName);
            sub.put("subjectQuestions", subjectQues);
            sub.put("subjectCorrectPercent", subjectCorrectPercent);
            subjectArray.add(sub);
        }
        jAct.put("subjectArray", subjectArray);
        return jAct;
    }

    @Override
    public List<PaCollect> confirmCollect(String userId, Collection relateId, int collectType) {
        List<PaCollect> collectList = this.paCollectService.confirmCollect(userId, relateId, collectType);
        return collectList;
    }

    public List<PaPaper> paperSearchImpl(Integer paperType, String title, String userId, String score, String publish, Page page, String subjectCode, String publishCode, String gradeCode, String phaseCode, String volumeCode, String unitCode, String sectionCode, String bookId, String bookUnitId, String bookSectionId, String knowledge) {
        List list;
        PaPaperCondition ppc = new PaPaperCondition();
        ppc.setPaperType(paperType);
        ppc.setTitle(title);
        ppc.setUserId(userId);
        ppc.setScore((score != null && !"".equals(score)) ? Double.parseDouble(score) : null);
        ppc.setPublish(publish);
        if (page != null) {
            list = this.paPaperService.findPaPaperByCondition(ppc, page, Order.desc("create_date"));
        } else {
            list = this.paPaperService.findPaPaperByCondition(ppc, null, Order.desc("create_date"));
        }
        return list;
//        DetachedCriteria criteria = DetachedCriteria.forClass(PaPaper.class);
//        if (paperType != null) {
//            criteria.add(Restrictions.eq("paperType", paperType));
//        }
//        if (title != null && !"".equals(title)) {
//            criteria.add(Restrictions.like("title", "%" + title + "%"));
//        }
//        if (userId != null && !"".equals(userId)) {
//            criteria.add(Restrictions.eq("userId", userId));
//        }
//        if (publish != null && !"".equals(publish)) {
//            criteria.add(Restrictions.eq("publish", publish));
//        }
//        if (subjectCode != null && !"".equals(subjectCode)) {
//            criteria.add(Restrictions.eq("subjectCode", subjectCode));
//        }
//        if (publishCode != null && !"".equals(publishCode)) {
//            criteria.add(Restrictions.eq("publishCode", publishCode));
//        }
//        if (gradeCode != null && !"".equals(gradeCode)) {
//            criteria.add(Restrictions.eq("gradeCode", gradeCode));
//        }
//        if (phaseCode != null && !"".equals(phaseCode)) {
//            if (phaseCode.equals("01")) {
//                criteria.add(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.eq("gradeCode", "01"), Restrictions.eq("gradeCode", "02")),
//                        Restrictions.or(Restrictions.eq("gradeCode", "03"), Restrictions.eq("gradeCode", "04"))),
//                        Restrictions.or(Restrictions.eq("gradeCode", "05"), Restrictions.eq("gradeCode", "06"))));
//            } else if (phaseCode.equals("02")) {
//                criteria.add(Restrictions.or(Restrictions.or(Restrictions.eq("gradeCode", "07"), Restrictions.eq("gradeCode", "08")),
//                        Restrictions.eq("gradeCode", "09")));
//            } else if (phaseCode.equals("03")) {
//                criteria.add(Restrictions.or(Restrictions.or(Restrictions.eq("gradeCode", "10"), Restrictions.eq("gradeCode", "11")),
//                        Restrictions.eq("gradeCode", "12")));
//            }
//        }
//        if (volumeCode != null && !"".equals(volumeCode)) {
//            criteria.add(Restrictions.eq("volumeCode", volumeCode));
//        }
//        if (unitCode != null && !"".equals(unitCode)) {
//            criteria.add(Restrictions.eq("unitCode", unitCode));
//        }
//        if (sectionCode != null && !"".equals(sectionCode)) {
//            criteria.add(Restrictions.eq("sectionCode", sectionCode));
//        }
//        if (bookId != null && !"".equals(bookId)) {
//            criteria.add(Restrictions.eq("bookId", bookId));
//        }
//        if (bookUnitId != null && !"".equals(bookUnitId)) {
//            criteria.add(Restrictions.eq("bookUnitId", bookUnitId));
//        }
//        if (bookSectionId != null && !"".equals(bookSectionId)) {
//            criteria.add(Restrictions.eq("bookSectionId", bookSectionId));
//        }
//        if (knowledge != null && !"".equals(knowledge)) {
//            criteria.add(Restrictions.like("knowledge", "%" + knowledge + "%"));
//        }
//        criteria.addOrder(Order.desc("createTime"));
//        if (rowCount) {
//            ProjectionList proList = Projections.projectionList();//设置投影集合   
//            proList.add(Projections.groupProperty("id"));
//            criteria.setProjection(proList);
//            criteria.setResultTransformer(CriteriaSpecification.PROJECTION);
//        }
//        List list;
//        if (page != null) {
//            list = this.hibernateBaseDao.pagedQuery(criteria, page);
//        } else {
//            list = this.hibernateBaseDao.getHibernateTemplate().findByCriteria(criteria);
//        }
//        return list;
    }
//    public String getBookName(String bookId) {
//        List bookNames = this.hibernateBaseDao.find("select eb.resource.title from EBook eb where eb.id='" + bookId + "'");
//        if (bookNames.size() > 0) {
//            return (String) bookNames.get(0);
//        } else {
//            return "";
//        }
//    }
//
//    public String getBookUnit(String bookUnitId) {
//        List bookUnits = this.hibernateBaseDao.find("select eb.menuName from EBookMenu eb where eb.id='" + bookUnitId + "'");
//        if (bookUnits.size() > 0) {
//            return (String) bookUnits.get(0);
//        } else {
//            return "";
//        }
//    }
//
//    public String getBookSection(String bookSectionId) {
//        List bookSections = this.hibernateBaseDao.find("select eb.menuName from EBookMenu eb where eb.id='" + bookSectionId + "'");
//        if (bookSections.size() > 0) {
//            return (String) bookSections.get(0);
//        } else {
//            return "";
//        }
//    }
}
