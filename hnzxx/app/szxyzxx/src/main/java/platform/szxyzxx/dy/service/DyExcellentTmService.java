package platform.szxyzxx.dy.service;

import platform.szxyzxx.dy.pojo.DyExcellentTm;

import java.util.List;
import java.util.Map;

public interface DyExcellentTmService {

    boolean add(DyExcellentTm d);

    boolean update(DyExcellentTm d);

    boolean delete(Integer id);

    DyExcellentTm findById(Integer id);

    List<Map<String,Object>> getAll(DyExcellentTm d);

}
