package platform.szxyzxx.web.schoolbus.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: yhc
 * @Date: 2021/4/19 16:30
 * @Description: 出场记录
 */
public class CarIdentifyVo implements Serializable,Comparable<CarIdentifyVo> {
    private static final long serialVersionUID = 1L;

    /**
     * 车牌
     */
    private String carno;
    /**
     * 产生时间
     */
    private String time;

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int compareTo(CarIdentifyVo o) {
        return time.compareTo(o.getTime());
    }

    @Override
    public String toString() {
        return "CarIdentifyVo{" +
                "carno='" + carno + '\'' +
                ", time=" + time +
                '}';
    }


}
