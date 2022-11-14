package platform.szxyzxx.huojiang.service;

import platform.szxyzxx.huojiang.vo.JiXiaoScore;

import java.util.List;

public interface JiXiaoScoreService {
    List<JiXiaoScore> findByAll();
    Integer create(JiXiaoScore jiXiaoScore);
    Integer update(JiXiaoScore jiXiaoScore);
}
