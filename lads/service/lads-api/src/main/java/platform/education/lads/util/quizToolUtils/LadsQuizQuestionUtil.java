///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.util.quizToolUtils;
//
//import com.gzxtjy.lads.entities.LadsQuizQuestionTool;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.A;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.BR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.CORRECT_IMG_TD;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.END_A;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.END_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.END_TABLE;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.END_TD;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.END_TR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.FRAME1_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.FRAME2_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.FRAME2_FRAMEBG_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.HR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.TABLE;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.TD;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.TR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.WORD_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.XML_ANSWERS;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.addBaseA;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.addCorrectContent;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.addQuestionContent;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizQuestionUtil.trueOrFalseIncorrectAnswer;
//import com.gzxtjy.lads.vo.quizToolVo.LadsQuizResultVo;
//import com.gzxtjy.resources.util.DocPathUtil;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
///**
// *
// * @author 罗志明
// */
//public class LadsQuizQuestionUtil {
//
//    public static final String FRAME1_DIV = "<div class=\"frame1\">";
//    public static final String FRAME2_DIV = "<div class=\"frame2\">";
//    public static final String WORD_DIV = "<div class=\"word\">";
//    public static final String TABLE = "<table>";
//    public static final String END_TABLE = "</table>";
//    public static final String TR = "</tr>";
//    public static final String END_TR = "</tr>";
//    public static final String TD = "<td>";
//    public static final String CORRECT_IMG_TD = "<td width=\"30\"><img src=\"/css/common/quiz/images/correct_icon.gif\"/>";
//    public static final String END_TD = "</td>";
//    public static final String FRAME2_FRAMEBG_DIV = "<div class=\"frame2 frameBg\">";
//    public static final String END_DIV = "</div>";
//    public static final String HR = "<hr/>";
//    public static final String BR = "<br/>";
//    public static final String A = "<a target=\"_blank\" href=\"/common/lads/ladsQuizAction_personalQuestionDetail.action?resultId=";
//    public static final String END_A = "<a/>";
//    public static final String XML_ANSWERS = "answers";
//    public static final String XML_ANSWER = "answer";
//
//    public static StringBuilder hotSpotQuestionDiv(LadsQuizQuestionTool qq, Element qe, List<LadsQuizResultVo> correctEqpuList, List<LadsQuizResultVo> incorrectEqpuList) throws DocumentException {
//        StringBuilder sb = new StringBuilder();
//        //题目区域
//        sb.append(addQuestionContent(qq));
//        //正确的区域
//        sb.append(FRAME2_DIV);
//        sb.append(WORD_DIV);
//        sb.append(TABLE);
//        sb.append(TR);
//        sb.append(CORRECT_IMG_TD);
//        sb.append(END_TD);
//        sb.append(TD);
//        String xmlPath = qq.getLadsQuizTool().getAbsolutePath();
//        String dir = qq.getLadsQuizTool().getHttpUrl().substring(0, qq.getLadsQuizTool().getHttpUrl().lastIndexOf("/") + 1);
//        SAXReader saxReader = new SAXReader();
//        Document document = saxReader.read(new File(xmlPath));
//        Element imgAtt = (Element) document.selectSingleNode("//quiz/*[3]/*[@id='" + qq.getQuestionId() + "']/*[@imageId]");
//        Element image = (Element) document.selectSingleNode("//quiz/*[2]/*[@id='" + imgAtt.attributeValue("imageId") + "']");
//        String imagePath = DocPathUtil.converHttpUrl(dir + image.attributeValue("src"));
//        sb.append("<img src=\"" + imagePath + "\" width=\"600\" height=\"340\"/> (" + correctEqpuList.size() + "人)");
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb.append(END_TABLE);
//        sb.append(END_DIV);
//        sb.append(HR);
//        sb.append(WORD_DIV);
//        for (LadsQuizResultVo eqpu : correctEqpuList) {
//            sb.append(addBaseA(qq, eqpu));
//            sb.append(eqpu.getRealName());
//            sb.append(END_A);
//        }
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        //错误的区域
//        sb.append(FRAME2_FRAMEBG_DIV);
//        sb.append(WORD_DIV);
//        sb.append(TABLE);
//        sb.append(TR);
//        sb.append(TD);
//        sb.append("&nbsp;&nbsp;");
//        sb.append(END_TD);
//        sb.append(TD);
//        sb.append("错误" + " (" + incorrectEqpuList.size() + "人)");
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb.append(END_TABLE);
//        sb.append(END_DIV);
//        sb.append(HR);
//        sb.append(WORD_DIV);
//        for (LadsQuizResultVo eqpu : incorrectEqpuList) {
//            sb.append(addBaseA(qq, eqpu));
//            sb.append(eqpu.getRealName());
//            sb.append(END_A);
//        }
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//
//    public static StringBuilder likertScaleQuestionDiv(LadsQuizQuestionTool qq, Element qe, Map<Integer[], LadsQuizResultVo> likertScaleMap) {
//        StringBuilder sb = new StringBuilder();
//        //题目区域
//        sb.append(addQuestionContent(qq));
//        Element ane = qe.element("statements");
//        List<Element> ase = ane.elements();
//        for (int h = 0; h < ase.size(); h++) {
//            Element ae = ase.get(h);
//            List<Element> ses = qe.element("scaleLabels").elements();
//            for (int v = 0; v < ses.size(); v++) {
//                Element se = ses.get(v);
//                sb.append(FRAME2_DIV);
//                sb.append(WORD_DIV);
//                sb.append(TABLE);
//                sb.append(TR);
//                sb.append(TD);
//                sb.append("&nbsp;&nbsp;");
//                sb.append(END_TD);
//                sb.append(TD);
//                List<LadsQuizResultVo> userList = new ArrayList();
//                Iterator iter = likertScaleMap.entrySet().iterator();
//                while (iter.hasNext()) {
//                    Map.Entry entry = (Map.Entry) iter.next();
//                    Integer[] index = (Integer[]) entry.getKey();
//                    LadsQuizResultVo eqpu = (LadsQuizResultVo) entry.getValue();
//                    if (index[0] == h && index[1] == v) {
//                        userList.add(eqpu);
//                    }
//                }
//                sb.append(ae.getTextTrim() + " 选择 " + se.getTextTrim() + " (" + userList.size() + "人)");
//                sb.append(END_TD);
//                sb.append(END_TR);
//                sb.append(END_TABLE);
//                sb.append(END_DIV);
//                sb.append(HR);
//                sb.append(WORD_DIV);
//                for (LadsQuizResultVo eqpu : userList) {
//                    sb.append(addBaseA(qq, eqpu));
//                    sb.append(eqpu.getRealName());
//                    sb.append(END_A);
//                }
//                sb.append(END_DIV);
//                sb.append(END_DIV);
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder multipleChoiceQuestionDiv(LadsQuizQuestionTool qq, Element qe, Map<Integer, LadsQuizResultVo> mutilpleChoiceMap) {
//        StringBuilder sb = new StringBuilder();
//        //题目区域
//        sb.append(addQuestionContent(qq));
//        Element ane = qe.element(XML_ANSWERS);
//        int correctIndex = Integer.parseInt(ane.attributeValue("correctAnswerIndex"));
//        List<Element> ase = ane.elements();
//        for (int h = 0; h < ase.size(); h++) {
//            Element ae = ase.get(h);
//            if (h == correctIndex) {
//                //正确的区域
//                sb.append(FRAME2_DIV);
//                sb.append(WORD_DIV);
//                sb.append(TABLE);
//                sb.append(TR);
//                sb.append(CORRECT_IMG_TD);
//                sb.append(END_TD);
//                sb.append(TD);
//                List<LadsQuizResultVo> userList = new ArrayList();
//                Iterator iter = mutilpleChoiceMap.entrySet().iterator();
//                while (iter.hasNext()) {
//                    Map.Entry entry = (Map.Entry) iter.next();
//                    int userIndex = (Integer) entry.getKey();
//                    LadsQuizResultVo eqpu = (LadsQuizResultVo) entry.getValue();
//                    if (userIndex == h) {
//                        userList.add(eqpu);
//                    }
//                }
//                sb.append(qq.getRightAnswer() + " (" + userList.size() + "人)");
//                sb.append(END_TD);
//                sb.append(END_TR);
//                sb.append(END_TABLE);
//                sb.append(END_DIV);
//                sb.append(HR);
//                sb.append(WORD_DIV);
//                for (LadsQuizResultVo eqpu : userList) {
//                    sb.append(addBaseA(qq, eqpu));
//                    sb.append(eqpu.getRealName());
//                    sb.append(END_A);
//                }
//                sb.append(END_DIV);
//                sb.append(END_DIV);
//            } else {
//                //错误的区域
//                sb.append(FRAME2_FRAMEBG_DIV);
//                sb.append(WORD_DIV);
//                sb.append(TABLE);
//                sb.append(TR);
//                sb.append(TD);
//                sb.append("&nbsp;&nbsp;");
//                sb.append(END_TD);
//                sb.append(TD);
//                List<LadsQuizResultVo> userList = new ArrayList();
//                Iterator iter = mutilpleChoiceMap.entrySet().iterator();
//                while (iter.hasNext()) {
//                    Map.Entry entry = (Map.Entry) iter.next();
//                    int userIndex = (Integer) entry.getKey();
//                    LadsQuizResultVo eqpu = (LadsQuizResultVo) entry.getValue();
//                    if (userIndex == h) {
//                        userList.add(eqpu);
//                    }
//                }
//                sb.append(ae.getTextTrim() + " (" + userList.size() + "人)");
//                sb.append(END_TD);
//                sb.append(END_TR);
//                sb.append(END_TABLE);
//                sb.append(END_DIV);
//                sb.append(HR);
//                sb.append(WORD_DIV);
//                for (LadsQuizResultVo eqpu : userList) {
//                    sb.append(addBaseA(qq, eqpu));
//                    sb.append(eqpu.getRealName());
//                    sb.append(END_A);
//                }
//                sb.append(END_DIV);
//                sb.append(END_DIV);
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder answeredQuestionDiv(LadsQuizQuestionTool qq, Element qe, List<LadsQuizResultVo> answeredEqpuList) {
//        StringBuilder sb = new StringBuilder();
//        //题目区域
//        sb.append(addQuestionContent(qq));
//        sb.append(FRAME2_DIV);
//        sb.append(WORD_DIV);
//        for (LadsQuizResultVo eqpu : answeredEqpuList) {
//            sb.append(addBaseA(qq, eqpu));
//            sb.append(eqpu.getRealName());
//            sb.append(END_A);
//        }
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//
//    public static StringBuilder trueOrFalseQuestionDiv(LadsQuizQuestionTool qq, Element qe, List<LadsQuizResultVo> correctEqpuList, List<LadsQuizResultVo> incorrectEqpuList) {
//        StringBuilder sb = new StringBuilder();
//        //题目区域
//        sb.append(addQuestionContent(qq));
//        //正确的区域
//        sb.append(addCorrectContent(qq, correctEqpuList));
//        //错误的区域
//        sb.append(FRAME2_FRAMEBG_DIV);
//        sb.append(WORD_DIV);
//        sb.append(TABLE);
//        sb.append(TR);
//        sb.append(TD);
//        sb.append("&nbsp;&nbsp;");
//        sb.append(END_TD);
//        sb.append(TD);
//        sb.append(trueOrFalseIncorrectAnswer(qe) + " (" + incorrectEqpuList.size() + "人)");
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb.append(END_TABLE);
//        sb.append(END_DIV);
//        sb.append(HR);
//        sb.append(WORD_DIV);
//        for (LadsQuizResultVo eqpu : incorrectEqpuList) {
//            sb.append(addBaseA(qq, eqpu));
//            sb.append(eqpu.getRealName());
//            sb.append(END_A);
//        }
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//
//    public static StringBuilder matchingQuestionDiv(LadsQuizQuestionTool qq, Element qe, List<LadsQuizResultVo> correctEqpuList, List<LadsQuizResultVo> incorrectEqpuList) {
//        StringBuilder sb = new StringBuilder();
//        //题目区域
//        sb.append(addQuestionContent(qq));
//        //正确的区域
//        sb.append(addCorrectContent(qq, correctEqpuList));
//        //错误的区域
//        sb.append(FRAME2_FRAMEBG_DIV);
//        sb.append(WORD_DIV);
//        sb.append(TABLE);
//        sb.append(TR);
//        sb.append(TD);
//        sb.append("&nbsp;&nbsp;");
//        sb.append(END_TD);
//        sb.append(TD);
//        sb.append("错误" + " (" + incorrectEqpuList.size() + "人)");
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb.append(END_TABLE);
//        sb.append(END_DIV);
//        sb.append(HR);
//        sb.append(WORD_DIV);
//        for (LadsQuizResultVo eqpu : incorrectEqpuList) {
//            sb.append(addBaseA(qq, eqpu));
//            sb.append(eqpu.getRealName());
//            sb.append(END_A);
//        }
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//
//    public static StringBuilder addQuestionContent(LadsQuizQuestionTool qq) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(FRAME1_DIV);
//        sb.append(WORD_DIV);
//        sb.append("<b>" + qq.getType() + "</b>");
//        sb.append(BR);
//        sb.append(qq.getDirection());
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//
//    public static StringBuilder addCorrectContent(LadsQuizQuestionTool qq, List<LadsQuizResultVo> correctEqpuList) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(FRAME2_DIV);
//        sb.append(WORD_DIV);
//        sb.append(TABLE);
//        sb.append(TR);
//        sb.append(CORRECT_IMG_TD);
//        sb.append(END_TD);
//        sb.append(TD);
//        sb.append(qq.getRightAnswer() + " (" + correctEqpuList.size() + "人)");
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb.append(END_TABLE);
//        sb.append(END_DIV);
//        sb.append(HR);
//        sb.append(WORD_DIV);
//        for (LadsQuizResultVo eqpu : correctEqpuList) {
//            sb.append(addBaseA(qq, eqpu));
//            sb.append(eqpu.getRealName());
//            sb.append(END_A);
//        }
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//
//    public static String addBaseA(LadsQuizQuestionTool qq, LadsQuizResultVo eqpu) {
//        String baseA = A + eqpu.getResult().getId() + "&qid=" + qq.getQuestionId() + "\">";
//        return baseA;
//    }
//
//    public static StringBuilder trueOrFalseCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        Element ans = qe.element(XML_ANSWERS);
//        int correctIndex = Integer.parseInt(ans.attributeValue("correctAnswerIndex"));
//        List<Element> aes = ans.elements();
//        sb.append(aes.get(correctIndex).getTextTrim());
//        return sb;
//    }
//
//    public static StringBuilder trueOrFalseIncorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        Element ans = qe.element(XML_ANSWERS);
//        int correctIndex = Integer.parseInt(ans.attributeValue("correctAnswerIndex"));
//        List<Element> aes = ans.elements();
//        aes.remove(correctIndex);
//        for (Element e : aes) {
//            sb.append(e.getTextTrim());
//        }
//        return sb;
//    }
//
//    public static StringBuilder fillInBlankCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        List<Element> ans = qe.element(XML_ANSWERS).elements();
//        for (int i = 0; i < ans.size(); i++) {
//            if (i == (ans.size() - 1)) {
//                sb.append(ans.get(i).getTextTrim());
//            } else {
//                sb.append(ans.get(i).getTextTrim() + ",");
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder numericCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        Element aes = (Element) qe.element(XML_ANSWERS);
//        List<Element> list = aes.elements();
//        for (int i = 0; i < list.size(); i++) {
//            Element ele = list.get(i);
//            if (ele.getName().equals("equal")) {
//                sb.append("数值 =" + ele.attributeValue("value"));
//            } else if (ele.getName().equals("between")) {
//                sb.append(ele.attributeValue("leftHandOperand") + "< 数值 <" + ele.attributeValue("rightHandOperand"));
//            } else if (ele.getName().equals("greaterThan")) {
//                sb.append("数值 >" + ele.attributeValue("value"));
//            } else if (ele.getName().equals("greaterThanOrEqual")) {
//                sb.append("数值 >=" + ele.attributeValue("value"));
//            } else if (ele.getName().equals("lessThan")) {
//                sb.append("数值 <" + ele.attributeValue("value"));
//            } else if (ele.getName().equals("lessThanOrEqual")) {
//                sb.append("数值 <=" + ele.attributeValue("value"));
//            } else if (ele.getName().equals("notEqual")) {
//                sb.append("数值 ≠" + ele.attributeValue("value"));
//            }
//            if (i != (list.size() - 1)) {
//                sb.append(",");
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder wordBlankCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        Element de = qe.element("details");
//        List des = de.elements();
//        for (int i = 0; i < des.size(); i++) {
//            Element e = (Element) des.get(i);
//            sb.append(e.attributeValue("value"));
//            if (i != (des.size() - 1)) {
//                sb.append(",");
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder matchingCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        Element mess = qe.element("matches");
//        List mes = mess.elements();
//        for (int i = 0; i < mes.size(); i++) {
//            Element me = (Element) mes.get(i);
//            int premiseIndex = Integer.parseInt(me.attributeValue("premiseId"));
//            int responseIndex = Integer.parseInt(me.attributeValue("responseId"));
//            Element pe = ((Element) qe.element("premises").elements().get(premiseIndex)).element("text");
//            Element re = ((Element) qe.element("responses").elements().get(responseIndex)).element("text");
//            sb.append(pe.getTextTrim() + "=" + re.getTextTrim());
//            if (i != (mes.size() - 1)) {
//                sb.append(",");
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder fillInBlankExCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        Element de = qe.element("details");
//        List des = de.elements();
//        for (int i = 0; i < des.size(); i++) {
//            Element e = (Element) des.get(i);
//            if (e.getName().equals("text")) {
//                sb.append(e.attributeValue("value"));
//            } else if (e.getName().equals("blank")) {
//                sb.append(e.element("answer").getTextTrim());
//            }
//            if (i != (des.size() - 1)) {
//                sb.append(",");
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder multipleResponseCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        List<Element> ans = qe.element(XML_ANSWERS).elements();
//        for (int i = 0; i < ans.size(); i++) {
//            if ("true".equals(ans.get(i).attributeValue("correct"))) {
//                sb.append(ans.get(i).getTextTrim());
//                if (i != (ans.size() - 1)) {
//                    sb.append(",");
//                }
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder sequenceCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        List<Element> ans = qe.element(XML_ANSWERS).elements();
//        for (int i = 0; i < ans.size(); i++) {
//            sb.append(ans.get(i).getTextTrim());
//            if (i != (ans.size() - 1)) {
//                sb.append(",");
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuilder multipleChoiceTextCorrectAnswer(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        Element de = qe.element("details");
//        List des = de.elements();
//        for (int i = 0; i < des.size(); i++) {
//            Element e = (Element) des.get(i);
//            if (e.getName().equals("text2")) {
//                sb.append(e.attributeValue("value"));
//            } else if (e.getName().equals("blank2")) {
//                sb.append(((Element) e.elements().get(Integer.parseInt(e.attributeValue("correctAnswerIndex")))).getTextTrim());
//            }
//            if (i != (des.size() - 1)) {
//                sb.append(",");
//            }
//        }
//        return sb;
//    }
//}
