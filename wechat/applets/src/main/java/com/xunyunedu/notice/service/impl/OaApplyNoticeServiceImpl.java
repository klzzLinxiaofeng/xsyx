package com.xunyunedu.notice.service.impl;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.notice.pojo.OaApplyNotice;
import com.xunyunedu.notice.service.OaApplyNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OaApplyNoticeServiceImpl implements OaApplyNoticeService {

    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public void add(OaApplyNotice oaApplyNotice) {
        basicSQLService.update("insert into oa_apply_notice(title,receiver_id,applicant_name,apply_explain,data_id_type,data_id,create_date) values('"+oaApplyNotice.getTitle()+"',"+oaApplyNotice.getReceiverId()+",'"+oaApplyNotice.getApplicantName()+"','"+oaApplyNotice.getApplyExplain()+"','"+oaApplyNotice.getDataIdType().getTypeName()+"',"+oaApplyNotice.getDataId()+",now())");
    }

    @Override
    public void updateApplyResult(Integer receiverId, Integer dataId, Integer applyResult) {
        basicSQLService.update("update oa_apply_notice set handle_date=now(),apply_result="+applyResult+" where receiver_id="+receiverId+" and data_id="+dataId);
    }
}
