package platform.szxyzxx.notice.service;


import platform.szxyzxx.notice.pojo.OaApplyNotice;

public interface OaApplyNoticeService {

    void add(OaApplyNotice oaApplyNotice);

    void updateApplyResult(Integer receiverId, Integer dataId, Integer applyResult);

}
