package platform.szxyzxx.huojiang.dao;

import platform.szxyzxx.huojiang.vo.JiXiaoScore;

import java.util.List;

public interface JiXiaoScoreDao {

    List<JiXiaoScore> findByAll();
    Integer create(JiXiaoScore jiXiaoScore);
    Integer update(JiXiaoScore jiXiaoScore);
}
