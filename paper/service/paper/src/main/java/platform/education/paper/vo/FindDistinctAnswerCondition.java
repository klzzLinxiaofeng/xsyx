/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.paper.vo;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class FindDistinctAnswerCondition {

    public String userId;
    public List<String> qIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getqIds() {
        return qIds;
    }

    public void setqIds(List<String> qIds) {
        this.qIds = qIds;
    }

    
}
