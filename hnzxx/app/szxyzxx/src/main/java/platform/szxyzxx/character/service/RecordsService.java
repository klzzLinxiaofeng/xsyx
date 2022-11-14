package platform.szxyzxx.character.service;

import framework.generic.dao.Page;
import platform.szxyzxx.character.pojo.Records;

import java.util.List;

public interface RecordsService {
    List<Records> findByAll(Integer schoolId, Integer studentId, Page page);
}
