/**
 * @Title: TeamQuestionOptions.java
 * @Package platform.education.paper.model
 * @Description: 统计某个班某道题的选项实体类
 * @author pantq
 * @date 2017年3月24日 下午1:48:00
 * @version V1.0
 */
package platform.education.paper.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author pantq
 * @ClassName: TeamQuestionOptions
 * @Description: 统计某个班某道题的选项实体类
 * @date 2017年3月24日 下午1:48:00
 */
public class TeamQuestionOptions implements Serializable {

    private static final long serialVersionUID = 1L;

    //选项
    private String questionOption;

    //选项所对应的人数
    private Long questionOptionCount;

    public List<String> getStudentList() {
        return studentList;
    }

    private List<Integer> userIds;

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }

    //选项内容
    private String questionOptionContent;

    //学生姓名名单
    private List<String> studentList;

    public Long getQuestionOptionCount() {
        return questionOptionCount;
    }

    public void setQuestionOptionCount(Long questionOptionCount) {
        this.questionOptionCount = questionOptionCount;
    }

    public String getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(String questionOption) {
        this.questionOption = questionOption;
    }

    public String getQuestionOptionContent() {
        return questionOptionContent;
    }

    public void setQuestionOptionContent(String questionOptionContent) {
        this.questionOptionContent = questionOptionContent;
    }

    @Override
    public String toString() {
        return "{questionOption:'" + questionOption
                + "', questionOptionCount:'" + questionOptionCount
                + "', questionOptionContent:'" + questionOptionContent + "'}";
    }


}
