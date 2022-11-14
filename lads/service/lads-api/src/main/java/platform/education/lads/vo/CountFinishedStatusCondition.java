package platform.education.lads.vo;

import java.util.List;

/**
 *
 * @author 罗志明
 */
public class CountFinishedStatusCondition {

    private List<String> toolIds;
    private Integer userId;

    public List<String> getToolIds() {
        return toolIds;
    }

    public void setToolIds(List<String> toolIds) {
        this.toolIds = toolIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
