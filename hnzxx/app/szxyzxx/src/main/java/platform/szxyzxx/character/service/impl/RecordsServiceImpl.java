package platform.szxyzxx.character.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.character.dao.RecordsDao;
import platform.szxyzxx.character.pojo.Records;
import platform.szxyzxx.character.service.RecordsService;

import java.util.List;

@Service
public class RecordsServiceImpl implements RecordsService {
    @Autowired
    private RecordsDao recordsDao;
    @Override
    public List<Records> findByAll(Integer schoolId, Integer studentId, Page page) {
        List<Records> list=recordsDao.findByAlls(schoolId, studentId, page);
        return list;
    }
}
