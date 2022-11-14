package platform.szxyzxx.web.moral.dao;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: yhc
 * @Date: 2021/5/11 14:35
 * @Description:
 */
public interface StudentWeekStarDao {
    void deleteWeekStarStu(Integer id);

    void createClassTeacherScore(String year, String filePath, Integer teacherId, BigDecimal sumZP, BigDecimal sumXZP, BigDecimal sumXP, Date createDate,String xq,Integer type,Integer sumScore);
}
