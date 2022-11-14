/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.paper.service;

import framework.generic.dao.Page;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperQuestion;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaCollect;
import platform.education.paper.model.PaUserAnswer;

/**
 *
 * @author Administrator
 */
public interface PaperService {

    public PaPaperQuestion copyQuestion(PaPaperQuestion ppq, PaQuestion pq,PaPaper paper);

    public PaQuestion setQuestionValue(PaQuestion question, JSONObject ques,String quesSource,String userId);

    public PaPaperQuestion setPaperQuestionValue(PaPaperQuestion question, JSONObject ques,PaPaper paper, String userId);
    
    public List saveQuestion(JSONObject ques, PaPaper paper, String source, String userId);
    
    public Map<PaPaper,JSONArray> savePaper(String jsonString);
    
    public List saveComplex(JSONObject ques, PaPaper paper, String source, String userId);

    public void deleteNotInUpdateQuestions(String paId, List<String> ids);

    public List<PaUserAnswer> saveUserAnswer(JSONArray qArray, String userId);

    public Object confirmStudyAnswerType(String answer,String client);
    
    public List<PaCollect> confirmCollect(String userId,Collection relateId,int collectType);
    
    public List<PaPaper> paperSearchImpl(Integer paperType, String title, String userId, String score, String publish, Page page, String subjectCode, String publishCode, String gradeCode,String phaseCode, String volumeCode, String unitCode, String sectionCode, String bookId, String bookUnitId, String bookSectionId,String knowledge);
    
    //public String getBookName(String bookId);
    
    //public String getBookUnit(String bookUnitId);
    
    //public String getBookSection(String bookSectionId);

    //public CenterUser getCenterUser(String userId);

    public JSONObject putDataToPaperJson(JSONObject jAct, PaPaper p, boolean relateQuestions, String currentUserId,String client);

    public JSONObject putDataToQuesJson(JSONObject jAct, PaQuestion q,String client);

    public JSONObject putDataToQuesJson(JSONObject jAct, PaPaperQuestion q,String client);
    
    public JSONObject getUserAnswerStatistics(String userId);
}
