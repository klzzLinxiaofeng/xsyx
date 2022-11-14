package platform.education.generalTeachingAffair.event;

import org.springframework.context.ApplicationEvent;

/**
 *  @author: yhc
 *  @Date: 2021/4/2 17:22
 *  @Description: 修改卡号事件
 */
public class UpdateCardEvent extends ApplicationEvent {
    /**
     * 用户id
     */
    private Integer uId;


    /**
     * 用户类型 0:学生 1老师
     */
    private Integer uType;


    public UpdateCardEvent(Object source, Integer uId, Integer uType) {
        super(source);
        this.uId = uId;
        this.uType = uType;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getuType() {
        return uType;
    }

    public void setuType(Integer uType) {
        this.uType = uType;
    }

    @Override
    public String toString() {
        return "UpdateCardEvent{" +
                "uId=" + uId +
                ", uType=" + uType +
                '}';
    }
}
