package platform.szxyzxx.bobao.dao;

import platform.szxyzxx.bobao.vo.BoBaoTime;

public interface BoBaoTimeDao {
    Integer createBoBaoTime(BoBaoTime boBaoTime);
    Integer updateBoBaoTime(BoBaoTime boBaoTime);
    Integer deleteBoBaoTime(Integer id);

}
