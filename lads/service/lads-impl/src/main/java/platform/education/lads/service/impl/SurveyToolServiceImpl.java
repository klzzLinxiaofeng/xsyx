package platform.education.lads.service.impl;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.service.impl;
//
//import com.gzxtjy.common.dao.hibernate.HibernateBaseDao;
//import com.gzxtjy.common.util.StrUtils;
//import com.gzxtjy.lads.constants.FinishedStatus;
//import com.gzxtjy.lads.constants.ToolName;
//import com.gzxtjy.lads.constants.surveyToolCons.AnswerConstants;
//import com.gzxtjy.lads.entities.LadsActivity;
//import com.gzxtjy.lads.entities.LadsSurveyAnswerTool;
//import com.gzxtjy.lads.entities.LadsSurveyQuestionTool;
//import com.gzxtjy.lads.entities.LadsSurveyTool;
//import com.gzxtjy.lads.entities.LadsToolLibrary;
//import com.gzxtjy.lads.service.SurveyToolService;
//import com.gzxtjy.lads.vo.LadsUserVo;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.annotation.Resource;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Administrator
// */
//@Service("surveyToolServiceImpl")
////这里不能使用事务,因为在编辑题目的时候需要删除原有题目这个功能出现了锁等待,参考本类的 save 方法
////@Transactional(rollbackFor = {Exception.class})
//public class SurveyToolServiceImpl implements SurveyToolService {
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
//    public int getFinishedStatus(String toolId, String userId) {
//        String sql = "select count(an.id) from LadsSurveyAnswerTool an where an.userId = '" + userId + "' and an.questionId in (select st.id from LadsSurveyQuestionTool st where st.ladsSurveyTool.toolId = '" + toolId + "'))";
//        List list = this.hibernateBaseDao.find(sql);
//        Long no = (Long) list.get(0);
//        if (no > 0) {
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
//                "select count(an.id) from LadsSurveyAnswerTool an where an.userId = '" + userId + "' and an.questionId in (select st.id from LadsSurveyQuestionTool st where st.ladsSurveyTool.toolId in (" + sb.toString() + "))");
//        int count = ((Long) list.get(0)).intValue();
//        return count;
//    }
//
//    @Override
//    public String getUserScore(String toolId, String userId) {
//        if (getFinishedStatus(toolId, userId) == FinishedStatus.FINISHED) {
//            List list = this.hibernateBaseDao.find("select act.score from LadsActivity act where act.toolId = '" + toolId + "'");
//            return (String) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public double getUserScore(List<String> toolIds, String userId) {
//        double score = 0;
//        for (String toolId : toolIds) {
//            String sscore = getUserScore(toolId, userId);
//            if (sscore != null && !"".equals(sscore)) {
//                double realScore = Double.parseDouble(sscore);
//                score = score + realScore;
//            }
//        }
//        return score;
//    }
//
//    @Override
//    public LadsSurveyTool getSurveyByToolId(String toolId) {
//        List list = this.hibernateBaseDao.find("from LadsSurveyTool st where st.toolId = '" + toolId + "' order by st.createTime desc");
//        if (list.size() > 0) {
//            return (LadsSurveyTool) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<LadsSurveyQuestionTool> getSurveyQuestionList(String toolId) {
//        List list = this.hibernateBaseDao.find("from LadsSurveyQuestionTool st where st.ladsSurveyTool.toolId = '" + toolId + "' order by st.pos");
//        return list;
//    }
//
//    @Override
//    public int[] getAnswerPercentByQuestion(String questionId, List<LadsUserVo> voList, String standardAnswer) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < voList.size(); i++) {
//            if (i == voList.size() - 1) {
//                sb.append("'" + voList.get(i).getUserId() + "'");
//            } else {
//                sb.append("'" + voList.get(i).getUserId() + "'" + ",");
//            }
//        }
//        List<String> list = this.hibernateBaseDao.find("select an.userAnswer from LadsSurveyAnswerTool an where an.userId in(" + sb.toString() + ") and an.questionId='" + questionId + "'");
//        String[] split = standardAnswer.split(AnswerConstants.ANSWER_SPLIT_FLAG);
//        int[] percents = new int[split.length];
//        for (int v = 0; v < split.length; v++) {
//            int choose = 0;
//            for (String answer : list) {
//                if (answer != null && !"".equals(answer)) {
//                    String[] answerSplit = answer.split(AnswerConstants.ANSWER_SPLIT_FLAG);
//                    for (String ua : answerSplit) {
//                        if (ua.equals(AnswerConstants.ANSWER_LETTERS[v])) {
//                            choose++;
//                            break;
//                        }
//                    }
//                }
//            }
//            percents[v] = (int) Math.round(((double) choose / voList.size()) * 100);
//        }
//        return percents;
//    }
//
//    public JSONArray getWordAnswerByQuestion(String questionId, List<LadsUserVo> voList) {
//        StringBuilder sb = new StringBuilder();
//        JSONArray array = new JSONArray();
//        for (int i = 0; i < voList.size(); i++) {
//            if (i == voList.size() - 1) {
//                sb.append("'" + voList.get(i).getUserId() + "'");
//            } else {
//                sb.append("'" + voList.get(i).getUserId() + "'" + ",");
//            }
//        }
//        List<Object[]> list = this.hibernateBaseDao.find("select an.userId,an.userAnswer from LadsSurveyAnswerTool an where an.userId in(" + sb.toString() + ") and an.questionId='" + questionId + "'"
//                + " and an.userAnswer <> null and an.userAnswer <> ''"
//                + " order by an.createTime desc");
//        JSONObject obj = new JSONObject();
//        for (Object[] data : list) {
//            String userId = (String) data[0];
//            String userAnswer = (String) data[1];
//            obj.put("userId", userId);
//            for (int h = 0; h < voList.size(); h++) {
//                if (voList.get(h).getUserId().equals(userId)) {
//                    obj.put("realName", voList.get(h).getRealName());
//                    break;
//                }
//            }
//            obj.put("userAnswer", userAnswer);
//            array.add(obj);
//        }
//        return array;
//    }
//
//    @Override
//    public LadsSurveyQuestionTool getSurveyQuestionByToolIdAnsPos(String toolId, int pos) {
//        List list = this.hibernateBaseDao.find("from LadsSurveyQuestionTool st where st.ladsSurveyTool.toolId = '" + toolId + "' and st.pos=" + pos + "");
//        if (list.size() > 0) {
//            return (LadsSurveyQuestionTool) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public LadsSurveyAnswerTool getSurveyAnswerByUserIdAndQuestion(String userId, String questionId) {
//        List<LadsSurveyAnswerTool> answer = this.hibernateBaseDao.find(
//                "from LadsSurveyAnswerTool an where an.userId='" + userId + "' and an.questionId='" + questionId + "'");
//        if (answer.size() > 0) {
//            return answer.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    //这里要用事务才能批量插入数据
//    @Transactional(rollbackFor = {Exception.class})
//    public LadsSurveyAnswerTool saveAnswer(String questionId, String userId, String userAnswer) {
//        LadsSurveyAnswerTool answer = getSurveyAnswerByUserIdAndQuestion(userId, questionId);
//        if (answer == null) {
//            answer = new LadsSurveyAnswerTool();
//            answer.setCreateTime(new Date());
//        }
//        answer.setQuestionId(questionId);
//        answer.setUserAnswer(userAnswer);
//        answer.setUserId(userId);
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(answer);
//        return answer;
//    }
//
//    public void deleteSurveyQuestionBySurveyId(String surveyId) {
//        String sql = "delete LadsSurveyQuestionTool st where st.ladsSurveyTool.id = ?";
//        Session session = this.hibernateBaseDao.getHibernateTemplate().getSessionFactory().openSession();
//        Query query = session.createQuery(sql);
//        query.setString(0, surveyId);
//        query.executeUpdate();
//        session.close();
//    }
//
//    public void deleteSurveyQuestionByExistId(String surveyId, List<String> ids) {
//        //删除不在题目列表的题目
//        StringBuilder sb = new StringBuilder();
//        String sql = "";
//        if (ids.size() > 0) {
//            for (int i = 0; i < ids.size(); i++) {
//                if (i == ids.size() - 1) {
//                    sb.append("'" + ids.get(i) + "'");
//                } else {
//                    sb.append("'" + ids.get(i) + "'" + ",");
//                }
//            }
//            System.out.println("sb==" + sb.toString());
//            if (sb.toString() == null || sb.toString().equals("")) {
//                sql = "DELETE FROM lads_survey_question_tool WHERE SURVEY_TOOL = '" + surveyId + "'";
//            } else {
//                sql = "DELETE FROM lads_survey_question_tool WHERE ID NOT IN (" + sb.toString() + ") AND SURVEY_TOOL = '" + surveyId + "'";
//            }
//        } else {
//            sql = "DELETE FROM lads_survey_question_tool WHERE SURVEY_TOOL = '" + surveyId + "'";
//        }
//        Session session = this.hibernateBaseDao.getHibernateTemplate().getSessionFactory().openSession();
//        Query query = session.createSQLQuery(sql);
//        query.executeUpdate();
//        session.close();
//    }
//
//    @Override
//    public LadsSurveyTool save(String toolId, String title, String surveyDescription, JSONArray surveyArray) {
//        LadsSurveyTool st = getSurveyByToolId(toolId);
//        if (st == null) {
//            st = new LadsSurveyTool();
//            st.setCreateTime(new Date());
//            st.setLadsToolLibrary(this.hibernateBaseDao.findUniqueBy(LadsToolLibrary.class, "toolName", ToolName.SURVEY_TOOL));
//        }
//        st.setToolId(toolId);
//        st.setTitle(title);
//        st.setDescription(surveyDescription);
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(st);
//        List<String> questionIdList = new ArrayList();
//        if (surveyArray != null) {
//            for (int x = 0; x < surveyArray.size(); x++) {
//                JSONObject question = surveyArray.getJSONObject(x);
//                String questionId = (String) question.get("questionId");
//                String content = (String) question.get("content");
//                String answer = (String) question.get("answer");
//                String type = (String) question.get("type");
//                int pos = (Integer) question.get("pos");
//                LadsSurveyQuestionTool sq;
//                if (questionId != null && !"".equals(questionId)) {
//                    sq = this.hibernateBaseDao.findUniqueBy(LadsSurveyQuestionTool.class, "id", questionId);
//                } else {
//                    sq = new LadsSurveyQuestionTool();
//                    sq.setCreateTime(new Date());
//                }
//                sq.setContent(content);
//                sq.setAnswer(answer);
//                sq.setLadsSurveyTool(st);
//                sq.setQuestionType(type);
//                sq.setPos(pos);
//                this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(sq);
//                questionIdList.add(sq.getId());
//            }
//        }
//        //删除不在题目列表的题目
//        this.deleteSurveyQuestionByExistId(st.getId(), questionIdList);
//        return st;
//    }
//}
