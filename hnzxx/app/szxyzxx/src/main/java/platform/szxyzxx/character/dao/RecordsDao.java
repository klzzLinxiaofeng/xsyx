package platform.szxyzxx.character.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.character.pojo.Records;

import java.util.List;

public interface RecordsDao {
   List<Records> findByAll(Integer studentId, Integer evaluationId);
    List<Records> findByAllScore(Integer studentId, Integer evaluationId);
    List<Records>  findByAlls(Integer schoolId, Integer studentId,Page page);
    //获取某一学生某一指标的总分
    Records findByZongScore(Integer studentId,Integer evaluationId);
}
