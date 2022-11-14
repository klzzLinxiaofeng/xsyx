package com.xunyunedu.notice.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.notice.pojo.PubNotice;
import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.notice.service.PubNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.wechat.service.WechatApiService;
import com.xunyunedu.wechat.template.WechatMessageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一般的通知实现，所有操作都是非异步的
 */
@Service("generalWechatNoticeService")
public class GeneralSystemWechatNotifyServiceImpl implements SystemWechatNotifyService {

    private static final Logger logger= LoggerFactory.getLogger(GeneralSystemWechatNotifyServiceImpl.class);

    @Autowired
    private WechatApiService wechatApiService;

    @Autowired
    private PubNoticeService noticeService;
    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public void sendSystemAndWechatNotice(SystemWechatNotice notice, List receiverList, String userIdPropertyName, String openIdPropertyName) {
        checkSysNotice(notice);
        PubNotice sysNotice=new PubNotice();
        sysNotice.setPosterId(-1);
        sysNotice.setPosterName("system");
        sysNotice.setTitle(notice.getTitle());
        sysNotice.setContent(notice.getContent());
        sysNotice.setUuid(notice.getDataId());
        sysNotice.setReceiverName(notice.getDataIdType().getTypeName());
        sysNotice.setReceiverType(notice.getSystemNoticeType().getType());
        sysNotice.setUserCount(0);
        logger.info("开始发送系统通知");
        try {
            noticeService.addSystemNoticeAndReceiver(sysNotice,receiverList,userIdPropertyName);
            logger.info("系统通知发送成功，发送人数："+receiverList.size());
        } catch (Exception e) {
            logger.error("发送系统通知失败",e);
        }
        sendWechatNotice(notice.getWechatMessageTemplate(),receiverList,openIdPropertyName,notice.getWechatPage());
    }

    @Override
    public void sendSystemAndWechatNotice(SystemWechatNotice notice, Integer userId) {
        List notifyUserList=basicSQLService.find("select id user_id,open_id from yh_user where id="+userId);
        if(notifyUserList!=null && notifyUserList.size()>0){
            sendSystemAndWechatNotice(notice,notifyUserList,"user_id","open_id");
        }
    }

    @Override
    public void sendSystemAndWechatNotice(SystemWechatNotice notice, Object userId, Object openId) {
        List<Map<String,Object>> receiverList=new ArrayList<>(1);
        Map<String,Object> map=new HashMap<>(3,1);
        map.put("userId",userId);
        map.put("openId",openId);
        receiverList.add(map);
        sendSystemAndWechatNotice(notice,receiverList,"userId","openId");
    }


    @Override
    public void sendWechatNotice(WechatMessageTemplate template, List receiverList, String openIdPropertyName, String page) {
        logger.info("开始发送微信通知");
        int count=0;
        int errorCount=0;
        try {
            for (Object receiver : receiverList) {
                Object openId=null;
                if(receiver instanceof Map){
                    openId=((Map)receiver).get(openIdPropertyName);
                }else{
                    openId=ReflectUtil.getFieldValue(receiver,openIdPropertyName);
                }
                if(openId==null || openId.equals("")){
                    continue;
                }
                String errorMsg=wechatApiService.sendNotice(template.getTemplateId(),template.getTemplateData(),openId.toString(),page);
                if(errorMsg!=null){
                    errorCount++;
                }
                count++;
            }
            logger.info("微信通知发送结束，发送总人数：{},失败：{}",count,errorCount);
        } catch (Exception e) {
            logger.error("发送微信通知异常，终止其他微信通知发送，已发送："+count+"人",e);
        }
    }

    @Override
    public void sendWechatNotice(WechatMessageTemplate template, String openId, String page) {
        wechatApiService.sendNotice(template.getTemplateId(),template.getTemplateData(),openId,page);
    }

    private void checkSysNotice(SystemWechatNotice notice){
        if(StringUtils.isEmpty(notice.getTitle())|| StringUtils.isEmpty(notice.getContent()) || StringUtils.isEmpty(notice.getDataId()) || notice.getWechatMessageTemplate()==null || notice.getDataIdType()==null){
            throw new IllegalArgumentException("SystemWechatNotice对象包含不可为空的属性，无法发送通知");
        }
    }

}
