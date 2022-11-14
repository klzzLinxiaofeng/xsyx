package platform.szxyzxx.notice.service;

import platform.education.notice.model.NoticeFile;
import platform.szxyzxx.oa.vo.Notices;

import java.util.List;

public interface NewNoticeService {

    Notices addNoticeAndReceiver(Notices notices,String sfhz, String textContent,List<String> fileUuidList,List<String> insertReceiverSqlList);

    void addSystemNoticeAndReceiver(Notices notice, List receiverList, String userIdPropertyName);
    NoticeFile create(NoticeFile noticeFile);

    /*审批*/
    Integer updateShenpi(Integer id,Integer zhuangti);
    Notices findById(Integer id);
    Integer updateNotice(Notices notices);
}
