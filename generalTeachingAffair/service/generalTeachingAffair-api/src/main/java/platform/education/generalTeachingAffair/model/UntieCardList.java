package platform.education.generalTeachingAffair.model;

/**
 * @author: yhc
 * @Date: 2021/4/25 11:56
 * @Description: 解绑卡号列表
 */
public class UntieCardList {

    /**
     * cardNumber : 100000002
     * personId : 370d303b-3294-428b-993b-07e6b3f09295
     */

    private String cardNumber;
    private String personId;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "UntieCardList{" +
                "cardNumber='" + cardNumber + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }
}
