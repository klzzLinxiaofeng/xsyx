package platform.szxyzxx.schoolbus.service;

import platform.szxyzxx.web.schoolbus.vo.CarIdentifyVo;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/19 16:44
 * @Description: 车辆识别
 */
public interface CarIdentifyService {

    /**
     * 查询车辆信息
     *
     * @param type      type 0:进 ，1：出
     * @param beginDate 进入时间
     * @param endDate   出场时间
     * @param cards     车牌
     * @return
     */
    List<CarIdentifyVo> getCardsMsg(Integer type, String beginDate, String endDate, List<String> cards);
}
