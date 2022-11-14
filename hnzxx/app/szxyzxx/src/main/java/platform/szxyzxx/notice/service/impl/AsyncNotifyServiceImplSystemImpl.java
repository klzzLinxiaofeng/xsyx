package platform.szxyzxx.notice.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import platform.szxyzxx.notice.pojo.SystemWechatNotice;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

import java.util.List;
import java.util.concurrent.*;

/**
 * 异步的通知实现
 * @author jiaxin
 */
@Service("asyncWechatNoticeService")
public class AsyncNotifyServiceImplSystemImpl implements SystemWechatNotifyService {

    private static final Logger logger= LoggerFactory.getLogger(AsyncNotifyServiceImplSystemImpl.class);

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("发送通知线程池-%d").build();

    ExecutorService executor = new ThreadPoolExecutor(2, 5,120L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(10240), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


    @Autowired
    @Qualifier("generalWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Override
    public void sendSystemAndWechatNotice(final SystemWechatNotice notice, final List receiverList, final String userIdPropertyName, final String openIdPropertyName) {
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    notifyService.sendSystemAndWechatNotice(notice, receiverList, userIdPropertyName, openIdPropertyName);
                }
            });
        } catch (Exception e) {
            logger.error("添加异步通知任务失败",e);
        }
    }

    @Override
    public void sendSystemAndWechatNotice(final SystemWechatNotice notice, final Object userId, final Object openId) {
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    notifyService.sendSystemAndWechatNotice(notice, userId, openId);
                }
            });
        } catch (Exception e) {
            logger.error("添加异步通知任务失败",e);
        }
    }

    @Override
    public void sendSystemAndWechatNotice(final SystemWechatNotice notice, final Integer userId) {
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    notifyService.sendSystemAndWechatNotice(notice, userId);
                }
            });
        } catch (Exception e) {
            logger.error("添加异步通知任务失败",e);
        }
    }

    @Override
    public void sendWechatNotice(final WechatMessageTemplate template, final List receiverList, final String openIdPropertyName, final String page) {
        try {
            try {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        notifyService.sendWechatNotice(template, receiverList, openIdPropertyName, page);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("添加异步通知任务失败",e);
        }
    }

    @Override
    public void sendWechatNotice(final WechatMessageTemplate template, final String openId, final String page) {
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    notifyService.sendWechatNotice(template, openId, page);
                }
            });
        } catch (Exception e) {
            logger.error("添加异步通知任务失败",e);
        }
    }
}
