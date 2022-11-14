package platform.education.generalTeachingAffair.model;

import java.io.Serializable;

/**
 *  @author: yhc
 *  @Date: 2021/4/14 16:56
 *  @Description: 海康组织添加
 */
public class HikTeamRequestVo implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * clientId : 123456789
     * orgIndexCode : 1239017947124
     * orgName : 组织test1
     * parentIndexCode : root000000
     */

    private Integer clientId;
    private String orgIndexCode;
    private String orgName;
    private String parentIndexCode;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getOrgIndexCode() {
        return orgIndexCode;
    }

    public void setOrgIndexCode(String orgIndexCode) {
        this.orgIndexCode = orgIndexCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentIndexCode() {
        return parentIndexCode;
    }

    public void setParentIndexCode(String parentIndexCode) {
        this.parentIndexCode = parentIndexCode;
    }
}
