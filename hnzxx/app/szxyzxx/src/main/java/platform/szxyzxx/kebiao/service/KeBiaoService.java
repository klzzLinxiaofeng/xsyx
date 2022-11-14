package platform.szxyzxx.kebiao.service;

import platform.szxyzxx.web.common.vo.UserInfo;

public interface KeBiaoService {
    /*
    * 推送课表到seenwo
    */
    public String findBySeeoween(UserInfo userInfo,Integer teamId,Integer gradeId,String schoolYear,String teamCode);
}
