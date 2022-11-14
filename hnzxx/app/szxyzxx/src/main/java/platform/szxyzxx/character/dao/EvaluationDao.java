package platform.szxyzxx.character.dao;

import org.springframework.stereotype.Component;
import platform.szxyzxx.character.pojo.Evaluation;

import java.util.List;

@Component
public interface EvaluationDao {
     int createBys(Evaluation evaluation);

     List<Evaluation> findByAll();

    List<Evaluation> findByAlls();

    void update(int ids);
    Evaluation findById(int id);
    int updateEvalua(Evaluation evaluation);
}
