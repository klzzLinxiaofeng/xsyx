/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.paper.vo;

/**
 *
 * @author Administrator
 */
public class FindDistinctAnswerResult {

    public String paperQuestion;
    public String answer;
    public Integer countPqId;

    public String getPaperQuestion() {
        return paperQuestion;
    }

    public void setPaperQuestion(String paperQuestion) {
        this.paperQuestion = paperQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCountPqId() {
        return countPqId;
    }

    public void setCountPqId(Integer countPqId) {
        this.countPqId = countPqId;
    }
}
