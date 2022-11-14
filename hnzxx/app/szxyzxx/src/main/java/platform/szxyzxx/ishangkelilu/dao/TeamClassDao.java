package platform.szxyzxx.ishangkelilu.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.ishangkelilu.pojo.TeamClassPassWord;

import java.util.List;

public interface TeamClassDao {
    List<TeamClassPassWord> findByAll(Integer gradeId, Integer teamId,Integer schoolId,String schoolYear, Page page);
    TeamClassPassWord findById(Integer teamId);
    Integer create(TeamClassPassWord teamClassPassWord);
    Integer update(Integer teamId,String passWord);
}
