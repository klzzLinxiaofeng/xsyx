package platform.education.generalTeachingAffair.model;

/**
 * @author: yhc
 * @Date: 2021/3/15 15:44
 * @Description: 海康用户
 */
public class HikvisionUserPo {

    private String personId;
    private String clientId;
    private String personName;
    private String teamId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 所属组织提前已经在海康平台创建好
     * 正式：
     * [
     *     {
     *         "orgIndexCode": "code1310000",
     *         "orgName": "新建老师",
     *         "parentIndexCode": "e5c87fd0-051f-46f6-904e-3414a5d27a71"
     *     },
     *        {
     *
     *         "orgIndexCode": "code1320000",
     *         "orgName": "新建学生",
     *         "parentIndexCode": "e5c87fd0-051f-46f6-904e-3414a5d27a71"
     *     }
     * ]
     * 测试：
     * [
     *     {
     *         "clientId": 123456789,
     *         "orgIndexCode": "test1320000",
     *         "orgName": "测试新建学生",
     *         "parentIndexCode": "root000000"
     *     },
     *    {
     * 		"clientId": 123456789,
     * 		"orgIndexCode": "test1310000",
     * 		"orgName": "测试新建老师",
     * 		"parentIndexCode": "root000000"
     *    }
     * ]
     */
    private String orgIndexCode;
    private String certificateType = "111";
    private String certificateNo;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getOrgIndexCode() {
        return orgIndexCode;
    }

    public void setOrgIndexCode(String orgIndexCode) {
        this.orgIndexCode = orgIndexCode;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }
}
