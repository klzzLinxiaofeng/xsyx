//package com.gzxtjy.lads.util.quizToolUtils;
//
//import com.gzxtjy.exam.constants.QuizQuestionName;
//import com.gzxtjy.lads.entities.LadsQuizResultTool;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.CLEAR_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.CORRECT_IMG;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.END_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.END_SPAN;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.END_TABLE;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.END_TD;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.END_TR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.FIRST_TD;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.HEADER_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.ICON_COLUMN_TD;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.LABEL_SPAN;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.NO_CLASS_TR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.QUESTION_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.QUESTION_INFO_SPAN;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.QUESTION_TITLE_SPAN;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.REPORT_LIST_TABLE;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.SPACE_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.TITLE_TR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.USER_RESPONSE_IMG;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.USER_RESPONSE_TD;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.USER_SCORE_DIV;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.VALUE_SPAN;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.WRONG_ANSWER_TR;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.XML_ANSWER;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.XML_ANSWERS;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.XML_ATTR_AWARD_POINTS;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.XML_ATTR_MAX_POINTS;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.XML_ATTR_STATUS;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.XML_DIRECTION;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.addBaseFoot;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.addDirection;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.addQuestionDiv;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.getDirection;
//import static com.gzxtjy.lads.util.quizToolUtils.LadsQuizReportUtil.getStatus;
//import com.gzxtjy.portal.util.Watermark;
//import com.gzxtjy.resources.util.DocPathUtil;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
///**
// *
// * @author 罗志明
// */
//public class LadsQuizReportUtil {
//
//    public static final String SCRIPT = "<script type=\"text/javascript\">";
//    public static final String END_SCRIPT = "</script>";
//    public static final String QUESTION_DIV = "<div class=\"quiz_question\"";
//    public static final String END_DIV = "</div>";
//    public static final String HEADER_DIV = "<div class=\"header\">";
//    public static final String QUESTION_TITLE_SPAN = "<span class=\"question_title\">";
//    public static final String END_SPAN = "</span>";
//    public static final String SPACE_DIV = "<div class=\"space\">";
//    public static final String QUESTION_INFO_SPAN = "<span class=\"question_info\">";
//    public static final String REPORT_LIST_TABLE = "<table class=\"list report_list\">";
//    public static final String TITLE_TR = "<tr class=\"title\"><td width=\"52\" class=\"icon_column\"></td><td class=\"first\">答案</td><td width=\"110\" class=\"center\">学生答案</td>";
//    public static final String END_TR = "</tr>";
//    public static final String NO_CLASS_TR = "<tr class=\"\">";
//    public static final String WRONG_ANSWER_TR = "<tr class=\"wrong_answer\">";
//    public static final String ICON_COLUMN_TD = "<td class=\"icon_column\">";
//    public static final String CORRECT_IMG = "<img title=\"正确答案\" alt=\"正确答案\" src=\"/css/common/quiz/images/correct_icon.gif\"/>";
//    public static final String USER_RESPONSE_TD = "<td class=\"center user_response\">";
//    public static final String USER_RESPONSE_IMG = "<img title=\"学生选择\" alt=\"学生选择\" src=\"/css/common/quiz/images/user_response_icon.gif\"/>";
//    public static final String FIRST_TD = "<td class=\"first\">";
//    public static final String END_TD = "</td>";
//    public static final String CLEAR_DIV = "<div class=\"clear\">";
//    public static final String USER_SCORE_DIV = "<div class=\"user_score\">";
//    public static final String LABEL_SPAN = "<span class=\"label\">学生得分:";
//    public static final String VALUE_SPAN = "<span class=\"value\">";
//    public static final String END_TABLE = "</table>";
//    public static final String XML_DIRECTION = "direction";
//    public static final String XML_ANSWERS = "answers";
//    public static final String XML_ANSWER = "answer";
//    public static final String XML_ATTR_AWARD_POINTS = "awardedPoints";
//    public static final String XML_ATTR_MAX_POINTS = "maxPoints";
//    public static final String XML_ATTR_STATUS = "status";
//
//    public static StringBuilder hotSpotQuestionDiv(Element qe, LadsQuizResultTool eqr) throws DocumentException {
//        final String HOT_SPOT_TD = "<td class=\"hot_spot_question\">";
//        final String HOT_SPOT_TITLE_TR = "<tr class=\"title\"><td width=\"52\" class=\"icon_column\"></td><td class=\"first\">学生回答与答案</td>";
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("热区题 |");
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(HOT_SPOT_TITLE_TR);
//        sb.append(END_TR);
//        String status = getStatus(qe);
//        if (status.equals("incorrect")) {
//            sb.append(WRONG_ANSWER_TR);
//        } else {
//            sb.append(NO_CLASS_TR);
//        }
//        sb.append(ICON_COLUMN_TD);
//        sb.append("&nbsp;");
//        sb.append(END_TD);
//        sb.append(HOT_SPOT_TD);
//        String xmlPath = eqr.getLadsQuizTool().getAbsolutePath();
//        String dir = xmlPath.substring(0, xmlPath.lastIndexOf("/") + 1);
//        SAXReader saxReader = new SAXReader();
//        Document document = saxReader.read(new File(xmlPath));
//        Element root = document.getRootElement();
//        String qid = qe.attributeValue("id");
//        Element imgAtt = (Element) document.selectSingleNode("//quiz/*[3]/*[@id='" + qid + "']/*[@imageId]");
//        Element image = (Element) document.selectSingleNode("//quiz/*[2]/*[@id='" + imgAtt.attributeValue("imageId") + "']");
//        String imagePath = dir + image.attributeValue("src");
//        String outImagePath = eqr.getAbsolutePath().substring(0, eqr.getAbsolutePath().lastIndexOf("/") + 1) + image.attributeValue("src");
//        String outHttpImagePath = DocPathUtil.converHttpUrl(eqr.getHttpUrl().substring(0, eqr.getHttpUrl().lastIndexOf("/") + 1) + image.attributeValue("src"));
//        String spotPath = LadsQuizReportUtil.class.getResource("") + "spot.png";
//        spotPath = spotPath.substring(spotPath.indexOf("file:/") + 6);
//        File outImage = new File(outImagePath);
//        if (!outImage.exists()) {
//            Element ua = (Element) qe.element("userAnswer");
//            List<Element> pointList = ua.elements("point");
//            List<int[]> xy = new ArrayList();
//            for (int i = 0; i < pointList.size(); i++) {
//                Element pe = pointList.get(i);
//                int x = Integer.parseInt(pe.attributeValue("x"));
//                int y = Integer.parseInt(pe.attributeValue("y"));
//                int[] xyInt = {x, y};
//                xy.add(xyInt);
//            }
//            Watermark.createMark(imagePath, spotPath, xy, "", outImagePath);
//        }
//        sb.append("<div class=\"pic\">");
//        sb.append("<img id=\"spotImg_" + qid + "\" src=\"" + outHttpImagePath + "\"/>");
//        sb.append("<canvas id=\"canvas_" + qid + "\"></canvas>");
//        List hotspotEle = qe.element("hotspots").elements();
//        for (int h = 0; h < hotspotEle.size(); h++) {
//            Element he = (Element) hotspotEle.get(h);
//            int x = Integer.parseInt(he.attributeValue("x"));
//            int y = Integer.parseInt(he.attributeValue("y"));
//            int width = Integer.parseInt(he.attributeValue("width"));
//            int height = Integer.parseInt(he.attributeValue("height"));
//            if (he.getName().equals("rectangle")) {
//                sb.append("<input type='hidden' id='shape_" + qid + "_" + h + "' value='rectangle'>");
//                sb.append("<input type='hidden' id='points_" + qid + "_" + h + "' value='" + x + "," + y + "," + width + "," + height + "'>");
//            } else if (he.getName().equals("freeform")) {
//                sb.append("<input type='hidden' id='shape_" + qid + "_" + h + "' value='freeform'>");
//                sb.append("<input type='hidden' id='points_" + qid + "_" + h + "' value='" + x + "," + y + ";" + he.attributeValue("points") + "'>");
//            } else if (he.getName().equals("oval")) {
//                sb.append("<input type='hidden' id='shape_" + qid + "_" + h + "' value='oval'>");
//                sb.append("<input type='hidden' id='points_" + qid + "_" + h + "' value='" + x + "," + y + "," + width + "," + height + "'>");
//            }
//        }
//        sb.append(END_DIV);
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder numericQuestionDiv(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("数值题 |");
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        sb.append(NO_CLASS_TR);
//        sb.append(ICON_COLUMN_TD);
//        sb.append(CORRECT_IMG);
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        String status = getStatus(qe);
//        Element aes = (Element) qe.element(XML_ANSWERS);
//        List<Element> list = aes.elements();
//        for (int i = 0; i < list.size(); i++) {
//            Element ele = list.get(i);
//            if (ele.getName().equals("equal")) {
//                sb.append("数值 =" + ele.getTextTrim() + "&nbsp;&nbsp;&nbsp;&nbsp;");
//            } else if (ele.getName().equals("between")) {
//                sb.append(ele.element("leftOperand").getTextTrim() + "< 数值 <" + ele.element("rightOperand").getTextTrim() + "&nbsp;&nbsp;&nbsp;&nbsp;");
//            } else if (ele.getName().equals("greater")) {
//                sb.append("数值 >" + ele.getTextTrim() + "&nbsp;&nbsp;&nbsp;&nbsp;");
//            } else if (ele.getName().equals("greaterOrEqual")) {
//                sb.append("数值 >=" + ele.getTextTrim() + "&nbsp;&nbsp;&nbsp;&nbsp;");
//            } else if (ele.getName().equals("less")) {
//                sb.append("数值 <" + ele.getTextTrim() + "&nbsp;&nbsp;&nbsp;&nbsp;");
//            } else if (ele.getName().equals("lessOrEqual")) {
//                sb.append("数值 <=" + ele.getTextTrim() + "&nbsp;&nbsp;&nbsp;&nbsp;");
//            } else if (ele.getName().equals("notEqual")) {
//                sb.append("数值 ≠" + ele.getTextTrim() + "&nbsp;&nbsp;&nbsp;&nbsp;");
//            }
//        }
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        sb.append("&nbsp;");
//        sb.append(END_TD);
//        sb.append(END_TR);
//        if (status.equals("incorrect")) {
//            sb.append(WRONG_ANSWER_TR);
//        } else {
//            sb.append(NO_CLASS_TR);
//        }
//        sb.append(ICON_COLUMN_TD);
//        sb.append("&nbsp;");
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        sb.append(qe.attribute("userAnswer").getValue());
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        sb.append(USER_RESPONSE_IMG);
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder likertScaleQuestionDiv(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("简易调查表 |");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        sb.append(NO_CLASS_TR);
//        sb.append(ICON_COLUMN_TD);
//        sb.append("&nbsp;");
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        Element aes = (Element) qe.element("userAnswer");
//        List<Element> list = aes.elements("match");
//        for (int i = 0; i < list.size(); i++) {
//            Element ae = (Element) list.get(i);
//            int statementIndex = Integer.parseInt(ae.attributeValue("statementIndex"));
//            int labelIndex = Integer.parseInt(ae.attributeValue("labelIndex"));
//            Element se = (Element) qe.element("statements").elements().get(statementIndex);
//            Element le = (Element) qe.element("scaleLabels").elements().get(labelIndex);
//            sb.append(se.getTextTrim() + "=" + le.getTextTrim() + ",");
//        }
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        sb.append(USER_RESPONSE_IMG);
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder sequenceQuestionDiv(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("排序题 |");
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        sb.append(NO_CLASS_TR);
//        sb.append(ICON_COLUMN_TD);
//        sb.append(CORRECT_IMG);
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        String status = getStatus(qe);
//        Element aes = (Element) qe.element(XML_ANSWERS);
//        List<Element> list = aes.elements(XML_ANSWER);
//        for (int i = 0; i < list.size(); i++) {
//            Element ae = (Element) list.get(i);
//            sb.append((i + 1) + ". " + ae.getTextTrim() + ",&nbsp;&nbsp;");
//        }
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        if (status.equals("incorrect")) {
//            sb.append("&nbsp;");
//        } else {
//            sb.append(USER_RESPONSE_IMG);
//        }
//        sb.append(END_TD);
//        sb.append(END_TR);
//        if (status.equals("incorrect")) {
//            sb.append(WRONG_ANSWER_TR);
//            sb.append(ICON_COLUMN_TD);
//            sb.append("&nbsp;");
//            sb.append(END_TD);
//            sb.append(FIRST_TD);
//            String[] userDefine = new String[list.size()];
//            for (int i = 0; i < list.size(); i++) {
//                Element ae = (Element) list.get(i);
//                int userDefinedPosition = Integer.parseInt(ae.attributeValue("userDefinedPosition"));
//                userDefine[userDefinedPosition] = ae.getTextTrim();
//            }
//            for (int j = 0; j < userDefine.length; j++) {
//                sb.append((j + 1) + ". " + userDefine[j] + ",&nbsp;&nbsp;");
//            }
//            sb.append(END_TD);
//            sb.append(USER_RESPONSE_TD);
//            sb.append(USER_RESPONSE_IMG);
//            sb.append(END_TD);
//            sb.append(END_TR);
//        }
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder essayQuestionDiv(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("论述题 |");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        sb.append(NO_CLASS_TR);
//        sb.append(ICON_COLUMN_TD);
//        sb.append(CORRECT_IMG);
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        sb.append(qe.element("userAnswer").getTextTrim());
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        sb.append(USER_RESPONSE_IMG);
//        sb.append(END_TD);
//        sb.append(END_TR);
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder fillInBlankQuestionDiv(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("简答题 |");
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        sb.append(NO_CLASS_TR);
//        sb.append(ICON_COLUMN_TD);
//        sb.append(CORRECT_IMG);
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        String status = getStatus(qe);
//        Element de = qe.element("acceptableAnswers");
//        List des = de.elements();
//        for (int i = 0; i < des.size(); i++) {
//            Element e = (Element) des.get(i);
//            sb.append(e.getText() + ",");
//        }
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        if (status.equals("incorrect")) {
//            sb.append("&nbsp;");
//        } else {
//            sb.append(USER_RESPONSE_IMG);
//        }
//        sb.append(END_TD);
//        sb.append(END_TR);
//        if (status.equals("incorrect")) {
//            sb.append(WRONG_ANSWER_TR);
//            sb.append(ICON_COLUMN_TD);
//            sb.append("&nbsp;");
//            sb.append(END_TD);
//            sb.append(FIRST_TD);
//            sb.append(qe.attributeValue("userAnswer"));
//            sb.append(END_TD);
//            sb.append(USER_RESPONSE_TD);
//            sb.append(USER_RESPONSE_IMG);
//            sb.append(END_TD);
//            sb.append(END_TR);
//        }
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder matchingQuestionDiv(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("匹配题 |");
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        String status = getStatus(qe);
//        sb.append(NO_CLASS_TR);
//        sb.append(ICON_COLUMN_TD);
//        sb.append(CORRECT_IMG);
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        Element mess = qe.element("matches");
//        List mes = mess.elements();
//        for (int i = 0; i < mes.size(); i++) {
//            Element me = (Element) mes.get(i);
//            int premiseIndex = Integer.parseInt(me.attributeValue("premiseIndex"));
//            int responseIndex = Integer.parseInt(me.attributeValue("responseIndex"));
//            Element pe = (Element) qe.element("premises").elements().get(premiseIndex);
//            Element re = (Element) qe.element("responses").elements().get(responseIndex);
//            sb.append(pe.getTextTrim() + " = " + re.getTextTrim() + ",");
//        }
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        if (status.equals("incorrect")) {
//            sb.append("&nbsp;");
//        } else {
//            sb.append(USER_RESPONSE_IMG);
//        }
//        sb.append(END_TD);
//        sb.append(END_TR);
//        if (status.equals("incorrect")) {
//            sb.append(WRONG_ANSWER_TR);
//            sb.append(ICON_COLUMN_TD);
//            sb.append("&nbsp;");
//            sb.append(END_TD);
//            sb.append(FIRST_TD);
//            Element umess = qe.element("userAnswer");
//            List umes = umess.elements();
//            for (int i = 0; i < umes.size(); i++) {
//                Element me = (Element) umes.get(i);
//                int premiseIndex = Integer.parseInt(me.attributeValue("premiseIndex"));
//                int responseIndex = Integer.parseInt(me.attributeValue("responseIndex"));
//                Element pe = (Element) qe.element("premises").elements().get(premiseIndex);
//                Element re = (Element) qe.element("responses").elements().get(responseIndex);
//                sb.append(pe.getTextTrim() + " = " + re.getTextTrim() + ",");
//            }
//            sb.append(END_TD);
//            sb.append(USER_RESPONSE_TD);
//            sb.append(USER_RESPONSE_IMG);
//            sb.append(END_TD);
//            sb.append(END_TR);
//        }
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder multipleResponseQuestionDiv(Element qe) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        sb.append("多项选择题 |");
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        Element aes = (Element) qe.element(XML_ANSWERS);
//        List<Element> list = aes.elements(XML_ANSWER);
//        for (int i = 0; i < list.size(); i++) {
//            Element ae = list.get(i);
//            String correct = ae.attributeValue("correct");
//            String selected = ae.attributeValue("selected");
//            if (correct.equals("false") && selected.equals("true")) {
//                sb.append(WRONG_ANSWER_TR);
//            } else {
//                sb.append(NO_CLASS_TR);
//            }
//            sb.append(ICON_COLUMN_TD);
//            if (correct.equals("false")) {
//                sb.append("&nbsp;");
//            } else {
//                sb.append(CORRECT_IMG);
//            }
//            sb.append(END_TD);
//            sb.append(FIRST_TD);
//            sb.append(ae.getText());
//            sb.append(END_TD);
//            sb.append(USER_RESPONSE_TD);
//            if (selected.equals("true")) {
//                sb.append(USER_RESPONSE_IMG);
//            } else {
//                sb.append("&nbsp;");
//            }
//            sb.append(END_TD);
//            sb.append(END_TR);
//        }
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder fillInBlankExQuestionDiv(Element qe, String type) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        if (type.equals(QuizQuestionName.FILL_IN_BLANK_EX)) {
//            sb.append("填空题 |");
//        } else if (type.equals(QuizQuestionName.MULTIPLE_CHOICE_TEXT)) {
//            sb.append("选择填空题 |");
//        } else if (type.equals(QuizQuestionName.WORD_BANK)) {
//            sb.append("拖动题 |");
//        }
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        sb.append(NO_CLASS_TR);
//        sb.append(ICON_COLUMN_TD);
//        sb.append(CORRECT_IMG);
//        sb.append(END_TD);
//        sb.append(FIRST_TD);
//        String status = getStatus(qe);
//        Element de = qe.element("details");
//        List des = de.elements();
//        for (int i = 0; i < des.size(); i++) {
//            Element e = (Element) des.get(i);
//            if (e.getName().equals("text")) {
//                sb.append(e.getText());
//            } else if (e.getName().equals("blank")) {
//                sb.append("<span class=\"blank_answer\">");
//                sb.append(e.element("answer").getText());
//                sb.append(END_SPAN);
//            } else if (e.getName().equals("word")) {
//                sb.append("<span class=\"blank_answer\">");
//                sb.append(e.getTextTrim());
//                sb.append(END_SPAN);
//            }
//        }
//        sb.append(END_TD);
//        sb.append(USER_RESPONSE_TD);
//        if (status.equals("incorrect")) {
//            sb.append("&nbsp;");
//        } else {
//            sb.append(USER_RESPONSE_IMG);
//        }
//        sb.append(END_TD);
//        sb.append(END_TR);
//        if (status.equals("incorrect")) {
//            sb.append(WRONG_ANSWER_TR);
//            sb.append(ICON_COLUMN_TD);
//            sb.append("&nbsp;");
//            sb.append(END_TD);
//            sb.append(FIRST_TD);
//            for (int i = 0; i < des.size(); i++) {
//                Element e = (Element) des.get(i);
//                if (e.getName().equals("text")) {
//                    sb.append(e.getText());
//                } else if (e.getName().equals("blank")) {
//                    sb.append("<span class=\"blank_answer\">");
//                    if (e.elements("answer").size() > 0&&e.attributeValue("userAnswerIndex")!=null) {
//                        Element an = (Element)e.elements("answer").get(Integer.parseInt(e.attributeValue("userAnswerIndex")));
//                        sb.append(an.getTextTrim());
//                    } else {
//                        sb.append(e.attributeValue("userAnswer") == null ? "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" : e.attributeValue("userAnswer"));
//                    }
//                    sb.append(END_SPAN);
//                } else if (e.getName().equals("word")) {
//                    sb.append("<span class=\"blank_answer\">");
//                    sb.append(e.attributeValue("userAnswer") == null ? "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" : e.attributeValue("userAnswer"));
//                    sb.append(END_SPAN);
//                }
//            }
//            sb.append(END_TD);
//            sb.append(USER_RESPONSE_TD);
//            sb.append(USER_RESPONSE_IMG);
//            sb.append(END_TD);
//            sb.append(END_TR);
//        }
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder chooseQuestionDiv(Element qe, String type) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(addQuestionDiv(qe));
//        sb.append(HEADER_DIV);
//        sb = addDirection(sb, qe);
//        sb.append(QUESTION_INFO_SPAN);
//        if (type.equals(QuizQuestionName.TRUE_OR_FALSE)) {
//            sb.append("判断题 |");
//        } else if (type.equals(QuizQuestionName.MULTIPLE_CHOICE)) {
//            sb.append("单项选择题 |");
//        }
//        sb.append("满分: " + qe.attribute(XML_ATTR_MAX_POINTS).getValue() + " 分");
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(REPORT_LIST_TABLE);
//        sb.append(TITLE_TR);
//        sb.append(END_TR);
//        Element aes = (Element) qe.element(XML_ANSWERS);
//        int correctIndex = Integer.parseInt(aes.attribute("correctAnswerIndex").getValue());
//        int userIndex = Integer.parseInt(aes.attribute("userAnswerIndex").getValue());
//        List<Element> list = aes.elements(XML_ANSWER);
//        for (int i = 0; i < list.size(); i++) {
//            Element ae = list.get(i);
//            if (correctIndex != userIndex && i == userIndex) {
//                sb.append(WRONG_ANSWER_TR);
//            } else {
//                sb.append(NO_CLASS_TR);
//            }
//            sb.append(ICON_COLUMN_TD);
//            if (i == correctIndex) {
//                sb.append(CORRECT_IMG);
//            } else {
//                sb.append("&nbsp;");
//            }
//            sb.append(END_TD);
//            sb.append(FIRST_TD);
//            sb.append(ae.getTextTrim());
//            sb.append(END_TD);
//            sb.append(USER_RESPONSE_TD);
//            if (i == userIndex) {
//                sb.append(USER_RESPONSE_IMG);
//            } else {
//                sb.append("&nbsp;");
//            }
//            sb.append(END_TD);
//            sb.append(END_TR);
//        }
//        sb = addBaseFoot(sb, qe);
//        return sb;
//    }
//
//    public static StringBuilder addDirection(StringBuilder sb, Element qe) {
//        sb.append(QUESTION_TITLE_SPAN);
//        sb.append(getDirection(qe));
//        sb.append(END_SPAN);
//        sb.append(SPACE_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//
//    public static StringBuilder addBaseFoot(StringBuilder sb, Element qe) {
//        sb.append(END_TABLE);
//        sb.append(CLEAR_DIV);
//        sb.append(END_DIV);
//        sb.append(USER_SCORE_DIV);
//        sb.append(LABEL_SPAN);
//        sb.append(END_SPAN);
//        sb.append(VALUE_SPAN);
//        if (qe.attributeValue(XML_ATTR_AWARD_POINTS) != null) {
//            sb.append(qe.attributeValue(XML_ATTR_AWARD_POINTS) + " 分");
//        }
//        sb.append(END_SPAN);
//        sb.append(END_DIV);
//        sb.append(END_DIV);
//        return sb;
//    }
//    
//    public static String addQuestionDiv(Element qe) {
//        return QUESTION_DIV+" id=\"question_div_"+qe.attributeValue("id")+"\">"; 
//    }
//
//    public static String getStatus(Element qe) {
//        return qe.attributeValue(XML_ATTR_STATUS);
//    }
//
//    public static String getDirection(Element qe) {
//        Element te = (Element) qe.element(XML_DIRECTION);
//        String title = te.getTextTrim();
//        return title;
//    }
//}
