package platform.szxyzxx.schoolbus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import platform.szxyzxx.schoolbus.service.CarIdentifyService;
import platform.szxyzxx.web.schoolbus.vo.CarIdentifyVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/4/19 17:23
 * @Description: 车辆识别
 */
@Service
public class CarIdentifyServiceImpl implements CarIdentifyService {

    @Autowired
    @Qualifier("carNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 获取车辆信息
     * @param type      type 0:进 ，1：出
     * @param beginDate 进入时间
     * @param endDate   出场时间
     * @param cars     车牌
     * @return
     */
    @Override
    public List<CarIdentifyVo> getCardsMsg(Integer type, String beginDate, String endDate, List<String> cars) {
        if(cars==null || cars.size()==0){
            return new ArrayList<>(0);
        }
        Map<String, Object> params = new HashMap<>(3);
        StringBuilder sql = new StringBuilder("select carno");
        if (type == 0) {
            sql.append(",intime time from t_carin where intime");
        } else {
            sql.append(",outtime time from t_carout where outtime");
        }
        sql.append(" between :beginDate and :endDate and carno in ( "+join(cars)+" )");
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        RowMapper<CarIdentifyVo> rowMapper = new BeanPropertyRowMapper<>(CarIdentifyVo.class);
        List<CarIdentifyVo> list = null;
        try {
            list = namedParameterJdbcTemplate.query(sql.toString(), params, rowMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
        return list;
    }


    private String join(List<String> list){
        StringBuilder sb=new StringBuilder();
        for (String  s: list) {
            if(sb.length()!=0){
                sb.append(",");
            }
            sb.append("'").append(s.replace(",","','")).append("'");
        }
        return sb.toString();
    }

}
