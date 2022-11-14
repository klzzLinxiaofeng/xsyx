package platform.education.generalTeachingAffair.model;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/3/15 17:19
 *  @Description: 海康绑卡接口
 */
public class HikvisionBindCarPojo {


    /**
     * startDate : 2018-10-30
     * endDate : 2037-12-30
     * cardList : [{"cardNo":"343454652356","personId":"sadasdasdf1232334","orgIndexCode":"code1320000","cardType":1}]
     */

    private String startDate;
    private String endDate;
    private List<CardList> cardList;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<CardList> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardList> cardList) {
        this.cardList = cardList;
    }
}