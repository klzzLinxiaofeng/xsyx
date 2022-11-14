package platform.szxyzxx.bobao.service;

import platform.szxyzxx.bobao.vo.BoBaoTime;

public interface BoBaoTimeService {
   Integer createBoBaoTime(BoBaoTime boBaoTime);
   Integer deleteBoBaoTime(Integer id);
   Integer updateBoBaoTime(BoBaoTime boBaoTime);
}
