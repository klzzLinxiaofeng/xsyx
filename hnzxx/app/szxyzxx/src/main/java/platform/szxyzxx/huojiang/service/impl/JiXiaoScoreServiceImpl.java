package platform.szxyzxx.huojiang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.huojiang.dao.JiXiaoScoreDao;
import platform.szxyzxx.huojiang.service.JiXiaoScoreService;
import platform.szxyzxx.huojiang.vo.JiXiaoScore;

import java.util.List;


@Service
public class JiXiaoScoreServiceImpl implements JiXiaoScoreService {
    @Autowired
    private JiXiaoScoreDao jiXiaoScoreDao;

    @Override
    public List<JiXiaoScore> findByAll() {
        return jiXiaoScoreDao.findByAll();
    }

    @Override
    public Integer create(JiXiaoScore jiXiaoScore) {
        return jiXiaoScoreDao.create(jiXiaoScore);
    }

    @Override
    public Integer update(JiXiaoScore jiXiaoScore) {
        return jiXiaoScoreDao.update(jiXiaoScore);
    }
}
