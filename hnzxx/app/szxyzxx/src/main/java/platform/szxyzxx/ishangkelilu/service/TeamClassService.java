package platform.szxyzxx.ishangkelilu.service;

import framework.generic.dao.Page;
import platform.szxyzxx.ishangkelilu.pojo.TeamClassPassWord;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface TeamClassService {
    List<TeamClassPassWord> findByAll(Integer gradeId, Integer teamId,UserInfo userInfo, Page page);
    String UpdateOrCreate(Integer teamId,String teamName, String password, UserInfo userInfo);
    String updateOrcreateTwo(String password, UserInfo userInfo);
}
