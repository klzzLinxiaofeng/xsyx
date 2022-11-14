package com.xunyunedu.bobao.service.impl;

import com.xunyunedu.bobao.dao.BoBaoDaPingDao;
import com.xunyunedu.bobao.service.BoBaoDaPingService;
import com.xunyunedu.bobao.vo.BoBaoDaPing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoBaoDaPingServiceImpl implements BoBaoDaPingService {
    @Autowired
    private BoBaoDaPingDao boBaoDaPingDao;
    @Override
    public BoBaoDaPing findById(Integer id) {
        BoBaoDaPing boBaoDaPings=boBaoDaPingDao.findById(id);
    /*    if(boBaoDaPings.getGradeIds()!=null){
            int [] array = Arrays.stream(boBaoDaPings.getGradeIds().split(",")).mapToInt(Integer::parseInt).toArray();
            BoBaoDaPing boBaoDaPing=boBaoDaPingDao.findByGradeId(array);
            boBaoDaPings.setGradeNames(boBaoDaPing.getGradeNames());
        }*/
        return boBaoDaPings;
    }
}
