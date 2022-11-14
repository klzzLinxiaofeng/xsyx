package platform.szxyzxx.kaoqin.service;


import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Map;

public interface KaoQinService {
    public Map<String,Object> findByKaoQin(UserInfo userInfo);
}
