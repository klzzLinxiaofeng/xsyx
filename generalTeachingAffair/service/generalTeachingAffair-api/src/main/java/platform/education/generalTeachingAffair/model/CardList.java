package platform.education.generalTeachingAffair.model;

public class CardList {
    /**
     * cardNo : 343454652356
     * personId : sadasdasdf1232334
     * orgIndexCode : code1320000
     * cardType : 1
     */

    private String cardNo;
    private String personId;
//    private String orgIndexCode = "d216add0-c940-482e-b8fb-a7207a75d7e7"; 使用组织，学校修改对应的组织后就无法修改卡号

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

//    public String getOrgIndexCode() {
//        return orgIndexCode;
//    }
//
//    public void setOrgIndexCode(String orgIndexCode) {
//        this.orgIndexCode = orgIndexCode;
//    }
}
