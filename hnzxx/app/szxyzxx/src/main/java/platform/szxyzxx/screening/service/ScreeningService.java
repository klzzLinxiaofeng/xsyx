package platform.szxyzxx.screening.service;

import framework.generic.dao.Page;
import platform.szxyzxx.screening.vo.Screening;

import java.util.List;

public interface ScreeningService {
    List<Screening> findByAll(String paichaName, String screeningArea, String startTime, String endTime, Page page);

    List<Screening> findByDaoAll(String paichaName, String screeningArea,String startTime, String endTime);
    Integer create(Screening screening);
    Integer update(Screening screening);
    Screening findById(Integer id);
}
