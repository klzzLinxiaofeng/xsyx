package platform.education.lads.service.impl;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.service.impl;
//
//import com.gzxtjy.common.dao.hibernate.HibernateBaseDao;
//import com.gzxtjy.common.util.FtpUtils;
//import com.gzxtjy.common.util.StrUtils;
//import com.gzxtjy.exam.constants.QuizQuestionName;
//import com.gzxtjy.lads.constants.FinishedStatus;
//import com.gzxtjy.lads.constants.ToolName;
//import com.gzxtjy.lads.constants.quizToolCons.QuizXml;
//import com.gzxtjy.lads.entities.LadsQuizQuestionTool;
//import com.gzxtjy.lads.entities.LadsQuizResultTool;
//import com.gzxtjy.lads.entities.LadsQuizTool;
//import com.gzxtjy.lads.entities.LadsToolLibrary;
//import com.gzxtjy.lads.service.QuizToolService;
//import com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil;
//import com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil;
//import com.gzxtjy.lads.vo.quizToolVo.LadsQuizResultVo;
//import java.io.BufferedWriter;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.Resource;
//import org.dom4j.Attribute;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Administrator
// */
//@Service("quizToolServiceImpl")
//@Transactional(rollbackFor = {Exception.class})
//public class QuizToolServiceImpl implements QuizToolService {
//
//    @Resource
//    private HibernateBaseDao hibernateBaseDao;
//
//    @Override
//    public String toHtml() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public String toXml() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public LadsQuizTool save(String toolId, String title, String xmlContent, String uploadPath) {
//        LadsQuizTool quiz = getQuizByToolId(toolId);
//        if (quiz == null) {
//            quiz = new LadsQuizTool();
//            quiz.setCreateTime(new Date());
//            quiz.setLadsToolLibrary(this.hibernateBaseDao.findUniqueBy(LadsToolLibrary.class, "toolName", ToolName.QUIZ_TOOL));
//        }
//        quiz.setToolId(toolId);
//        quiz.setTitle(title);
//        if (xmlContent != null && !"".equals(xmlContent)) {
//            //处理提示文字缺失问题
//            if (xmlContent.contains("<messages/>")) {
//                xmlContent = xmlContent.replace("<messages/>", QuizXml.MESSAGE_XML);
//            }
//            quiz.setXmlContent(xmlContent);
//            String path[] = this.uploadQuizXml(xmlContent, uploadPath);
//            quiz.setHttpUrl(path[0]);
//            quiz.setAbsolutePath(path[1]);
//        }
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(quiz);
//        return quiz;
//    }
//
//    @Override
//    public String[] uploadQuizXml(String xmlContent, String uploadPath) {
//        String[] path = new String[2];
//        FtpUtils ftp = new FtpUtils();
//        List allowExt = new ArrayList();
//        allowExt.add(".xml");
//        ftp.setAllowExt(allowExt);
//        InputStream in;
//        try {
//            in = new ByteArrayInputStream(xmlContent.getBytes("UTF-8"));
//            try {
//                if (ftp.uploadFile(uploadPath, FtpUtils.getRandomNameByTime() + ".xml", in)) {
//                    path[0] = ftp.getFtpCode() + "|" + ftp.getUploadPath();
//                    path[1] = ftp.getAbsolutePath();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(QuizToolServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(QuizToolServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return path;
//    }
//
//    @Override
//    public LadsQuizTool getQuizByToolId(String toolId) {
//        List list = this.hibernateBaseDao.find("from LadsQuizTool et where et.toolId = '" + toolId + "' order by et.createTime desc");
//        if (list.size() > 0) {
//            return (LadsQuizTool) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    public StringBuilder xmlReportCreater(LadsQuizResultTool eqr) throws DocumentException {
//        StringBuilder sb = new StringBuilder();
//        SAXReader saxReader = new SAXReader();
//        Document document = saxReader.read(new File(eqr.getAbsolutePath()));
//        Element root = document.getRootElement();
//        List nodes = root.elements("questions");
//        for (Iterator it = nodes.iterator(); it.hasNext();) {
//            Element elm = (Element) it.next();
//            for (Iterator eit = elm.elementIterator(); eit.hasNext();) {
//                Element questionEle = (Element) eit.next();
//                String qn = questionEle.getName();
//                if (qn.equals(QuizQuestionName.TRUE_OR_FALSE)) {
//                    sb.append(LadsQuizReportUtil.chooseQuestionDiv(questionEle, QuizQuestionName.TRUE_OR_FALSE));
//                } else if (qn.equals(QuizQuestionName.FILL_IN_BLANK)) {
//                    sb.append(LadsQuizReportUtil.fillInBlankQuestionDiv(questionEle));
//                } else if (qn.equals(QuizQuestionName.NUMERIC)) {
//                    sb.append(LadsQuizReportUtil.numericQuestionDiv(questionEle));
//                } else if (qn.equals(QuizQuestionName.WORD_BANK)) {
//                    sb.append(LadsQuizReportUtil.fillInBlankExQuestionDiv(questionEle, QuizQuestionName.WORD_BANK));
//                } else if (qn.equals(QuizQuestionName.YES_OR_NO)) {
//                } else if (qn.equals(QuizQuestionName.MULTIPLE_CHOICE)) {
//                    sb.append(LadsQuizReportUtil.chooseQuestionDiv(questionEle, QuizQuestionName.MULTIPLE_CHOICE));
//                } else if (qn.equals(QuizQuestionName.MATCHING)) {
//                    sb.append(LadsQuizReportUtil.matchingQuestionDiv(questionEle));
//                } else if (qn.equals(QuizQuestionName.FILL_IN_BLANK_EX)) {
//                    sb.append(LadsQuizReportUtil.fillInBlankExQuestionDiv(questionEle, QuizQuestionName.FILL_IN_BLANK_EX));
//                } else if (qn.equals(QuizQuestionName.HOT_SPOT)) {
//                    sb.append(LadsQuizReportUtil.hotSpotQuestionDiv(questionEle, eqr));
//                } else if (qn.equals(QuizQuestionName.ESSAY)) {
//                    sb.append(LadsQuizReportUtil.essayQuestionDiv(questionEle));
//                } else if (qn.equals(QuizQuestionName.MULTIPLE_RESPONSE)) {
//                    sb.append(LadsQuizReportUtil.multipleResponseQuestionDiv(questionEle));
//                } else if (qn.equals(QuizQuestionName.SEQUENCE)) {
//                    sb.append(LadsQuizReportUtil.sequenceQuestionDiv(questionEle));
//                } else if (qn.equals(QuizQuestionName.MULTIPLE_CHOICE_TEXT)) {
//                    sb.append(LadsQuizReportUtil.fillInBlankExQuestionDiv(questionEle, QuizQuestionName.MULTIPLE_CHOICE_TEXT));
//                } else if (qn.equals(QuizQuestionName.SHORT_ANSWER)) {
//                } else if (qn.equals(QuizQuestionName.MATCHING_SURVEY)) {
//                } else if (qn.equals(QuizQuestionName.MULTIPLE_CHOICE_TEXT_SURVEY)) {
//                } else if (qn.equals(QuizQuestionName.PICK_ONE)) {
//                } else if (qn.equals(QuizQuestionName.NUMERIC_SURVEY)) {
//                } else if (qn.equals(QuizQuestionName.WHICH_WORD)) {
//                } else if (qn.equals(QuizQuestionName.LIKERT_SCALE)) {
//                    sb.append(LadsQuizReportUtil.likertScaleQuestionDiv(questionEle));
//                } else if (qn.equals(QuizQuestionName.PICK_MANY)) {
//                } else if (qn.equals(QuizQuestionName.RANKING)) {
//                } else if (qn.equals(QuizQuestionName.FILL_IN_BLANK_SURVEY)) {
//                }
//            }
//        }
//        return sb;
//
//    }
//
//    @Override
//    public List<LadsQuizQuestionTool> getQuestionVoList(LadsQuizTool pq) throws DocumentException {
//        List<LadsQuizQuestionTool> qqList = new ArrayList();
//        SAXReader saxReader = new SAXReader();
//        Document document = saxReader.read(new File(pq.getAbsolutePath()));
//        Element root = document.getRootElement();
//        List<Element> nodes = root.element("questions").elements();
//        for (Element qe : nodes) {
//            String qid = qe.attributeValue("id");
//            int rightAnswerCount = 0;
//            int wrongAnswerCount = 0;
//            String type = "";
//            String rightAnswer = "";
//            List<LadsQuizResultTool> qrList = this.hibernateBaseDao.find("from LadsQuizResultTool qr where qr.ladsQuizTool.id = '" + pq.getId() + "' and qr.userId is not null and qr.userId <> '' "
//                    + " and qr.id in (select max(qt.id) from LadsQuizResultTool qt where qt.ladsQuizTool.id = '" + pq.getId() + "' group by qt.userId having count(qt.userId)>=1) order by qr.createTime desc");
//            for (LadsQuizResultTool qr : qrList) {
//                SAXReader rsaxReader = new SAXReader();
//                Document rdocument = rsaxReader.read(new File(qr.getAbsolutePath()));
//                Element qre = (Element) rdocument.selectSingleNode("//quizReport/*[3]/*[@id='" + qid + "']");
//                String status = LadsQuizReportUtil.getStatus(qre);
//                if ("incorrect".equals(status)) {
//                    wrongAnswerCount++;
//                } else if ("correct".equals(status)) {
//                    rightAnswerCount++;
//                }
//            }
//            LadsQuizQuestionTool qq;
//            List<LadsQuizQuestionTool> sqqList = this.hibernateBaseDao.find("from LadsQuizQuestionTool eqq where eqq.questionId=" + qid + " and eqq.ladsQuizTool.id='" + pq.getId() + "'");
//            if (sqqList.size() > 0) {
//                qq = sqqList.get(0);
//            } else {
//                qq = new LadsQuizQuestionTool();
//                if (QuizQuestionName.TRUE_OR_FALSE.equals(qe.getName())) {
//                    type = QuizQuestionName.TRUE_OR_FALSE_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.trueOrFalseCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.FILL_IN_BLANK.equals(qe.getName())) {
//                    type = QuizQuestionName.FILL_IN_BLANK_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.fillInBlankCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.NUMERIC.equals(qe.getName())) {
//                    type = QuizQuestionName.NUMERIC_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.numericCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.WORD_BANK.equals(qe.getName())) {
//                    type = QuizQuestionName.WORD_BANK_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.wordBlankCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.MULTIPLE_CHOICE.equals(qe.getName())) {
//                    type = QuizQuestionName.MULTIPLE_CHOICE_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.trueOrFalseCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.MATCHING.equals(qe.getName())) {
//                    type = QuizQuestionName.MATCHING_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.matchingCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.FILL_IN_BLANK_EX.equals(qe.getName())) {
//                    type = QuizQuestionName.FILL_IN_BLANK_EX_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.fillInBlankExCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.HOT_SPOT.equals(qe.getName())) {
//                    type = QuizQuestionName.HOT_SPOT_STRING;
//                } else if (QuizQuestionName.ESSAY.equals(qe.getName())) {
//                    type = QuizQuestionName.ESSAY_STRING;
//                } else if (QuizQuestionName.MULTIPLE_RESPONSE.equals(qe.getName())) {
//                    type = QuizQuestionName.MULTIPLE_RESPONSE_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.multipleResponseCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.SEQUENCE.equals(qe.getName())) {
//                    type = QuizQuestionName.SEQUENCE_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.sequenceCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.MULTIPLE_CHOICE_TEXT.equals(qe.getName())) {
//                    type = QuizQuestionName.MULTIPLE_CHOICE_TEXT_STRING;
//                    rightAnswer = LadsQuizQuestionUtil.multipleChoiceTextCorrectAnswer(qe).toString();
//                } else if (QuizQuestionName.LIKERT_SCALE.equals(qe.getName())) {
//                    type = QuizQuestionName.LIKERT_SCALE_STRING;
//                }
//                qq.setType(type);
//                qq.setQuestionId(Integer.parseInt(qid));
//                qq.setDirection(LadsQuizReportUtil.getDirection(qe));
//                qq.setLadsQuizTool(pq);
//                qq.setXmlContent(qe.asXML());
//                qq.setRightAnswer(rightAnswer);
//            }
//            qq.setRightAnswerCount(rightAnswerCount);
//            qq.setWrongAnswerCount(wrongAnswerCount);
//            this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(qq);
//            qqList.add(qq);
//        }
//        return qqList;
//    }
//
//    public StringBuilder questionReportCreater(LadsQuizQuestionTool qq, List<LadsQuizResultVo> rList) throws DocumentException, UnsupportedEncodingException {
//        StringBuilder sb = new StringBuilder();
//        SAXReader saxReader = new SAXReader();
//        List<LadsQuizResultVo> correctEqpuList = new ArrayList();
//        List<LadsQuizResultVo> incorrectEqpuList = new ArrayList();
//        List<LadsQuizResultVo> answeredEqpuList = new ArrayList();
//        Map<Integer, LadsQuizResultVo> mutilpleChoiceMap = new HashMap();
//        Map<Integer[], LadsQuizResultVo> likertScaleMap = new HashMap();
//        Document document = saxReader.read(new ByteArrayInputStream(qq.getXmlContent().getBytes("UTF-8")));
//        Element root = document.getRootElement();
//        for (LadsQuizResultVo eqpu : rList) {
//            SAXReader rsaxReader = new SAXReader();
//            Document rdocument = rsaxReader.read(new File(eqpu.getResult().getAbsolutePath()));
//            Element qre = (Element) rdocument.selectSingleNode("//quizReport/*[3]/*[@id='" + qq.getQuestionId() + "']");
//            if (QuizQuestionName.MULTIPLE_CHOICE_STRING.equals(qq.getType())) {
//                Element aes = (Element) qre.element(LadsQuizReportUtil.XML_ANSWERS);
//                mutilpleChoiceMap.put(Integer.parseInt(aes.attribute("userAnswerIndex").getValue()), eqpu);
//            } else if (QuizQuestionName.ESSAY_STRING.equals(qq.getType()) || QuizQuestionName.FILL_IN_BLANK_STRING.equals(qq.getType())) {
//                String status = LadsQuizReportUtil.getStatus(qre);
//                if ("answered".equals(status) || "incorrect".equals(status) || "correct".equals(status)) {
//                    answeredEqpuList.add(eqpu);
//                }
//            } else if (QuizQuestionName.LIKERT_SCALE_STRING.equals(qq.getType())) {
//                List<Element> aes = qre.element("userAnswer").elements();
//                for (Element e : aes) {
//                    Integer[] ints = {Integer.parseInt(e.attributeValue("statementIndex")), Integer.parseInt(e.attributeValue("labelIndex"))};
//                    likertScaleMap.put(ints, eqpu);
//                }
//            } else {
//                String status = LadsQuizReportUtil.getStatus(qre);
//                if ("incorrect".equals(status)) {
//                    incorrectEqpuList.add(eqpu);
//                } else if ("correct".equals(status)) {
//                    correctEqpuList.add(eqpu);
//                }
//            }
//        }
//        if (QuizQuestionName.TRUE_OR_FALSE_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.trueOrFalseQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.FILL_IN_BLANK_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.answeredQuestionDiv(qq, root, answeredEqpuList));
//        } else if (QuizQuestionName.NUMERIC_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.matchingQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.WORD_BANK_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.matchingQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.MULTIPLE_CHOICE_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.multipleChoiceQuestionDiv(qq, root, mutilpleChoiceMap));
//        } else if (QuizQuestionName.MATCHING_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.matchingQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.FILL_IN_BLANK_EX_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.matchingQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.HOT_SPOT_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.hotSpotQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.ESSAY_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.answeredQuestionDiv(qq, root, answeredEqpuList));
//        } else if (QuizQuestionName.MULTIPLE_RESPONSE_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.matchingQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.SEQUENCE_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.matchingQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.MULTIPLE_CHOICE_TEXT_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.matchingQuestionDiv(qq, root, correctEqpuList, incorrectEqpuList));
//        } else if (QuizQuestionName.LIKERT_SCALE_STRING.equals(qq.getType())) {
//            sb.append(LadsQuizQuestionUtil.likertScaleQuestionDiv(qq, root, likertScaleMap));
//        }
//        return sb;
//    }
//
//    public LadsQuizResultTool saveQuizResult(LadsQuizResultTool eqr) {
//        try {
//            eqr = uploadResultXml(eqr);
//        } catch (IOException ex) {
//            Logger.getLogger(QuizToolServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.hibernateBaseDao.save(eqr);
//        return eqr;
//    }
//
//    public LadsQuizResultTool uploadResultXml(LadsQuizResultTool eqr) throws IOException {
//        FtpUtils ftp = new FtpUtils();
//        List allowExt = new ArrayList();
//        allowExt.add(".xml");
//        ftp.setAllowExt(allowExt);
//        String fileName = FtpUtils.getRandomNameByTime();
//        String uploadPath = eqr.getLadsQuizTool().getHttpUrl();
//        uploadPath = uploadPath.substring(uploadPath.lastIndexOf("/exam"), uploadPath.lastIndexOf("/"));
//        uploadPath = uploadPath + "/result/" + eqr.getUserId() + "/" + fileName + "/";
//        InputStream in = new ByteArrayInputStream(eqr.getXmlContent().getBytes("UTF-8"));
//        if (ftp.uploadFile(uploadPath, fileName + ".xml", in)) {
//            eqr.setHttpUrl(ftp.getFtpCode() + "|" + ftp.getUploadPath());
//            eqr.setAbsolutePath(ftp.getAbsolutePath());
//        }
//        return eqr;
//    }
//
//    @Override
//    public LadsQuizResultTool saveScore(LadsQuizResultTool eqr, String[] score) throws DocumentException {
//        SAXReader saxReader = new SAXReader();
//        Document document = saxReader.read(new File(eqr.getAbsolutePath()));
//        Element root = document.getRootElement();
//        double total = 0;
//        List nodes = root.element("questions").elements();
//        for (int i = 0; i < nodes.size(); i++) {
//            Element elm = (Element) nodes.get(i);
//            score[i] = score[i].equals("") ? "0" : score[i];
//            elm.addAttribute(LadsQuizReportUtil.XML_ATTR_AWARD_POINTS, score[i]);
//            total = total + Double.parseDouble(score[i]);
//        }
//        Element summary = root.element("summary");
//        Attribute attribute = summary.attribute("score");
//        DecimalFormat df = new DecimalFormat("###.#");
//        String value = df.format(total);
//        attribute.setValue(value);
//        eqr.setScore(value);
//        eqr.setXmlContent(document.asXML());
//        saveXmlByDocument(eqr.getAbsolutePath(), document);
//        this.hibernateBaseDao.update(eqr);
//        return eqr;
//    }
//
//    public LadsQuizResultTool getLastQuizResult(String userId, String quizId) {
//        DetachedCriteria rcriteria = DetachedCriteria.forClass(LadsQuizResultTool.class);
//        rcriteria.createAlias("ladsQuizTool", "quiz");
//        rcriteria.add(Restrictions.eq("quiz.id", quizId));
//        rcriteria.add(Restrictions.eq("userId", userId));
//        rcriteria.addOrder(Order.desc("createTime"));
//        List<LadsQuizResultTool> qrl = this.hibernateBaseDao.getHibernateTemplate().findByCriteria(rcriteria, 0, 1);
//        if (qrl.size() > 0) {
//            return qrl.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    public void saveXmlByDocument(String savePath, Document document) {
//        XMLWriter writer;
//        try {
//            FileOutputStream fos = new FileOutputStream(savePath);
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
//            writer = new XMLWriter(bw);
//            writer.write(document);
//            writer.close();
//        } catch (IOException ex) {
//            Logger.getLogger(QuizToolServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public int getFinishedStatus(String toolId, String userId) {
//        LadsQuizTool quiz = getQuizByToolId(toolId);
//        LadsQuizResultTool result = getLastQuizResult(userId, quiz.getId());
//        if (result != null) {
//            return FinishedStatus.FINISHED;
//        } else {
//            return FinishedStatus.NOT_FINISHED;
//        }
//    }
//
//    @Override
//    public int countFinishedStatus(List<String> toolIds, String userId) {
//        StringBuilder sb = new StringBuilder();
//        int i = 0;
//        if (toolIds.size() > 0) {
//            for (String toolId : toolIds) {
//                sb.append("'" + toolId + "'");
//                i++;
//                if (i < toolIds.size()) {
//                    sb.append(",");
//                }
//            }
//        } else {
//            sb.append("''");
//        }
//        List list = this.hibernateBaseDao.find(
//                "select count(*) from LadsQuizResultTool et where et.ladsQuizTool.toolId in (" + sb.toString() + ") and et.userId = '" + userId + "'");
//        int count = ((Long) list.get(0)).intValue();
//        return count;
//    }
//
//    @Override
//    public String getUserScore(String toolId, String userId) {
//        LadsQuizTool quiz = getQuizByToolId(toolId);
//        LadsQuizResultTool result = getLastQuizResult(userId, quiz.getId());
//        if (result != null) {
//            return result.getScore();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public double getUserScore(List<String> toolIds, String userId) {
//        StringBuilder sb = new StringBuilder();
//        int i = 0;
//        if (toolIds.size() > 0) {
//            for (String toolId : toolIds) {
//                sb.append("'" + toolId + "'");
//                i++;
//                if (i < toolIds.size()) {
//                    sb.append(",");
//                }
//            }
//        } else {
//            sb.append("''");
//        }
//        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT p.SCORE ");
//        sql.append(" FROM ");
//        sql.append(" (");
//        sql.append(" SELECT et.SCORE,qt.TOOL_ID,et.CREATE_TIME");
//        sql.append(" FROM lads_quiz_result_tool AS et JOIN lads_quiz_tool AS qt ON et.QUIZ = qt.ID");
//        sql.append(" WHERE et.USER_ID = '" + userId + "'");
//        sql.append(" AND");
//        sql.append(" qt.TOOL_ID IN (" + sb.toString() + ")");
//        sql.append(" ORDER BY");
//        sql.append(" et.CREATE_TIME DESC");
//        sql.append(" )");
//        sql.append(" AS p");
//        sql.append(" GROUP BY");
//        sql.append(" p.TOOL_ID");
//        sql.append(" ORDER BY");
//        sql.append(" p.CREATE_TIME DESC");
//        Session session = this.hibernateBaseDao.getSessionFactory().openSession();
//        Query query = session.createSQLQuery(sql.toString());
//        List<String> list = query.list();
//        session.close();
//        double score = 0;
//        for (String obj : list) {
//            if (obj != null && !("".equals(obj)) && StrUtils.isNumeric(obj)) {
//                double realScore = Double.parseDouble(obj);
//                score = score + realScore;
//            }
//        }
//        return score;
//    }
//}
