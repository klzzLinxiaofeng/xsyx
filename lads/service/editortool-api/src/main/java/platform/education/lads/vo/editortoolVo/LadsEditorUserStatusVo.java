/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.vo.editortoolVo;

import platform.education.lads.model.LadsEditorUserStatusTool;

/**
 *
 * @author Administrator
 */
public class LadsEditorUserStatusVo {

    private String realName;
    private Integer userId;
    private LadsEditorUserStatusTool status;

    public LadsEditorUserStatusTool getStatus() {
        return status;
    }

    public void setStatus(LadsEditorUserStatusTool status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
