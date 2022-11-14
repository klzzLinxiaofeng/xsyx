package platform.szxyzxx.bobao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Grade;
import platform.szxyzxx.bobao.dao.BoBaoDaPingDao;
import platform.szxyzxx.bobao.service.BoBaoDaPingService;
import platform.szxyzxx.bobao.vo.BoBaoDaPing;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Arrays;
import java.util.List;

@Service
public class BoBaoDaPingServiceImpl implements BoBaoDaPingService {
    @Autowired
    private BoBaoDaPingDao boBaoDaPingDao;
    @Override
    public List<BoBaoDaPing> findByAll(UserInfo userInfo) {
        List<BoBaoDaPing> boBaoDaPings=boBaoDaPingDao.findByAll(userInfo);
        for(BoBaoDaPing list:boBaoDaPings){
            if(list.getGradeIds()!=null){
                int [] array = Arrays.stream(list.getGradeIds().split(",")).mapToInt(Integer::parseInt).toArray();
                BoBaoDaPing boBaoDaPing=boBaoDaPingDao.findByGradeId(array);
                list.setGradeNames(boBaoDaPing.getGradeNames());
            }

        }
        return boBaoDaPings;
    }

    @Override
    public BoBaoDaPing findById(Integer id) {
       BoBaoDaPing boBaoDaPings=boBaoDaPingDao.findById(id);
       if(boBaoDaPings.getGradeIds()!=null){
           int [] array = Arrays.stream(boBaoDaPings.getGradeIds().split(",")).mapToInt(Integer::parseInt).toArray();
           BoBaoDaPing boBaoDaPing=boBaoDaPingDao.findByGradeId(array);
           boBaoDaPings.setGradeNames(boBaoDaPing.getGradeNames());
       }
        return boBaoDaPings;
    }

    @Override
    public Integer updateViewDaPing(BoBaoDaPing boBaoDaPing, UserInfo userInfo) {
        return boBaoDaPingDao.updateViewDaPing(boBaoDaPing,userInfo);
    }

    @Override
    public List<Grade> findGradeBySchoolId(Integer schoolId, String schoolYear) {
        return boBaoDaPingDao.findGradeBySchoolId(schoolId,schoolYear);
    }

    @Override
    public String findByGradeName(String [] array, String schoolYear) {
        return boBaoDaPingDao.findByGradeName( array,  schoolYear);
    }
}
