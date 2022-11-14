/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.vo.mediaToolVo;

import platform.education.lads.model.LadsMediaUserStatusTool;


/**
 *
 * @author Administrator
 */
public class LadsMediaUserStatusVo {

    private String realName;
    private Integer userId;
    private LadsMediaUserStatusTool status;
    private String formatLastPlayTime;

    public String getFormatLastPlayTime() {
        return formatLastPlayTime;
    }

    public void setFormatLastPlayTime(String formatLastPlayTime) {
        this.formatLastPlayTime = formatLastPlayTime;
    }
    
    public LadsMediaUserStatusTool getStatus() {
        return status;
    }

    public void setStatus(LadsMediaUserStatusTool status) {
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
