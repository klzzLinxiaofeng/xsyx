package platform.szxyzxx.accommodation.service;

import framework.generic.dao.Page;
import platform.szxyzxx.accommodation.pojo.Accommodation;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> findByAll(String teacherName, String fangshihao, String startDate, String endDate, Page page);
    Accommodation findById(Integer id);
    Integer  create(Accommodation accommodation);
    Integer update(Accommodation accommodation);
    Integer updateId(Integer id);
}
