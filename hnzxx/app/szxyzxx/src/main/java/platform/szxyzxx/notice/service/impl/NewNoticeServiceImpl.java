package platform.szxyzxx.notice.service.impl;

import cn.hutool.core.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.notice.dao.NoticeDao;
import platform.education.notice.dao.NoticeFileDao;
import platform.education.notice.model.NoticeFile;
import platform.szxyzxx.notice.service.NewNoticeService;
import platform.szxyzxx.oa.dao.NoticesDao;
import platform.szxyzxx.oa.vo.Notices;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NewNoticeServiceImpl implements NewNoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeFileDao noticeFileDao;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private NoticesDao noticesDao;

    @Override
    @Transactional
    public Notices addNoticeAndReceiver(Notices notice, String sfhz,String textContent, List<String> fileUuidList, List<String> insertReceiverSqlList) {

        notice = addNotice(notice, fileUuidList);
        System.out.println(notice.getId());
        int affectedCount = 0;
        for (String insertReceiverSql : insertReceiverSqlList) {
            affectedCount += basicSQLService.update(insertReceiverSql.replace("#noticeId", notice.getId().toString()));
        }
        String updateSql = "update pub_notice set user_count=" + affectedCount+",text_content='"+textContent+"'";
        //没有源码，简单一点就这样更新is_reply字段了
        if ("true".equals(sfhz)) {
            updateSql += ",is_reply=1";
        }

        updateSql += " where id=" + notice.getId();
        basicSQLService.update(updateSql);
        return notice;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSystemNoticeAndReceiver(Notices notice, List receiverList,String userIdPropertyName) {
        notice=addNotice(notice,null);
        basicSQLService.update(buildBatchAddReceiverSql(notice.getId(),receiverList,userIdPropertyName));
    }

    @Override
    public NoticeFile create(NoticeFile noticeFile) {
        return noticeFileDao.create(noticeFile);
    }

    @Override
    public Integer updateShenpi(Integer id, Integer zhuangti) {
        return noticesDao.updateShenpi( id,  zhuangti);
    }

    @Override
    public Notices findById(Integer id) {
        return noticesDao.findById(id);
    }

    @Override
    public Integer updateNotice(Notices notices) {
        return noticesDao.updateNotice(notices);
    }

    private String buildBatchAddReceiverSql(Integer noticeId,List receiverList,String userIdPropertyName){
        StringBuilder sql=new StringBuilder("insert into pub_notice_receiver (notice_id,receiver_id,create_date,modify_date,is_deleted) values");
        for (int i=0;i<receiverList.size();i++) {
            Object receiver=receiverList.get(i);
            Object userId=null;
            if(receiver instanceof Map){
                userId=((Map)receiver).get(userIdPropertyName);
            }else {
                userId=ReflectUtil.getFieldValue(receiverList.get(i), userIdPropertyName);
            }
            sql.append("(").append(noticeId).append(",").append(userId).append(",now(),now(),0)");
            if(i!=receiverList.size()-1){
                sql.append(",");
            }
        }
        return sql.toString();
    }

    private Notices addNotice(Notices notice, List<String> fileUuidList) {
        NoticeFile noticeFile = null;
        try {
            if (notice == null) {
                throw new IllegalArgumentException("notice is not null");
            } else if (notice.getTitle() != null && !"".equals(notice.getTitle())) {
                if (notice.getContent() != null && !"".equals(notice.getContent())) {
                    if (notice.getPosterId() == null) {
                        throw new IllegalArgumentException("param 'posterId' is not null");
                    } else if (notice.getReceiverType() != null && !"".equals(notice.getReceiverType())) {
                        if (notice.getUserCount() == null) {
                            throw new IllegalArgumentException("param 'userCount' is not null");
                        } else {
                            Date date = new Date();
                            if (notice != null) {
                                if (notice.getCreateDate() == null) {
                                    notice.setCreateDate(date);
                                }

                                if (notice.getModifyDate() == null) {
                                    notice.setModifyDate(date);
                                }

                                if (notice.getIsDeleted() == null) {
                                    notice.setIsDeleted(false);
                                }

                                if (notice.getPostTime() == null) {
                                    notice.setPostTime(date);
                                }

                                if (notice.getReadCount() == null) {
                                    notice.setReadCount(0);
                                }

                                //新的添加公告
                                noticesDao.create(notice);
                            }

                            Iterator i$;
                            if (fileUuidList != null && fileUuidList.size() > 0) {
                                i$ = fileUuidList.iterator();

                                while (i$.hasNext()) {
                                    String fileUuid = (String) i$.next();
                                    if (fileUuid != null && !"".equals(fileUuid)) {
                                        noticeFile = new NoticeFile();
                                        noticeFile.setNoticeId(notice.getId());
                                        noticeFile.setFileUuid(fileUuid);
                                        noticeFile.setCreateDate(date);
                                        noticeFile.setModifyDate(date);
                                        noticeFile.setIsDeleted(false);
                                        this.noticeFileDao.create(noticeFile);
                                    }
                                }
                            }
                            return notice;
                        }
                    } else {
                        throw new IllegalArgumentException("param 'receiverType' is not null");
                    }
                } else {
                    throw new IllegalArgumentException("param 'content' is not null");
                }

            } else {
                throw new IllegalArgumentException("param 'title' is not null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
