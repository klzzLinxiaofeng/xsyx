package com.xunyunedu.notice.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.notice.condition.PubNoticeCondition;
import com.xunyunedu.notice.constant.NoticeType;
import com.xunyunedu.notice.dao.PubNoticeDao;
import com.xunyunedu.notice.param.PubNoticeInsertParam;
import com.xunyunedu.notice.pojo.PubNotice;
import com.xunyunedu.notice.pojo.PubNoticeFile;
import com.xunyunedu.notice.service.PubNoticeFileService;
import com.xunyunedu.notice.service.PubNoticeService;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PubNoticeServiceImpl implements PubNoticeService {


    @Autowired
    private PubNoticeDao dao;

    @Autowired
    PubNoticeFileService noticeFileService;

    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public PageInfo page(PageCondition<PubNoticeCondition> condition){

        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = dao.selectByCondition(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }


    @Override
    @Transactional
    public PubNotice add(PubNoticeInsertParam param){
        Date now=new Date();
        PubNotice notice = new PubNotice();
        BeanUtils.copyProperties(param,notice);
        notice.setCreateDate(now);
        notice.setReadCount(0);
        notice.setUserCount(0);
        notice.setIsDeleted(false);
        //插入通知
        if(dao.insert(notice) <= 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
        String[] insertSqlList=createInsertReceiverSql(param,notice.getId());
        //插入通知的接收人
        int affectedCount=basicSQLService.updateBatch(insertSqlList);
        //更新发送总人数，和是否回复
        String updateSql="update pub_notice set user_count="+affectedCount;
        if(param.getIsReply()!=null && param.getIsReply()){
            updateSql+=",is_reply=1";
        }
        updateSql+=" where id="+notice.getId();
        basicSQLService.update(updateSql);
        //插入通知附件
        if(param.getFileUuidList()!=null && param.getFileUuidList().size()>0){
            List<String> files=param.getFileUuidList();
            for (String fileUuid : files) {
                PubNoticeFile noticeFile = new PubNoticeFile();
                noticeFile.setNoticeId(notice.getId());
                noticeFile.setFileUuid(fileUuid);
                noticeFile.setCreateDate(now);
                noticeFile.setModifyDate(now);
                notice.setIsDeleted(false);
                if(this.noticeFileService.add(noticeFile).getCode() != ResultCode.SUCCESS.getCode()){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return null;
                }
            }
        }


        return notice;

    }


    @Override
    public void addSystemNoticeAndReceiver(PubNotice notice, List receiverList, String userIdPropertyName) {
        dao.insert(notice);
        basicSQLService.update(buildBatchAddReceiverSql(notice.getId(),receiverList,userIdPropertyName));
    }

    private String[] createInsertReceiverSql(PubNoticeInsertParam param, Integer noticeId){
        List<String> targetTypes=param.getTargetTypeList();
        String[] sqlList=new String[targetTypes.size()];
        //部门和个人人员列表需要去重
        if(param.getTargetTypeList().contains(NoticeType.NoticeType_dept) && param.getTargetTypeList().contains(NoticeType.NoticeType_person)){
            sqlList=new String[1];
            String deptIds = joinList(param.getDeptIdList());
            String teacherIds = joinList(param.getTeacherIdList());
            sqlList[0]="INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) SELECT "+noticeId+", '老师' AS receiver_type, t.user_id, t.`name`, now(), now(), 0 FROM pj_teacher t WHERE t.is_delete=0 and t.id IN("+teacherIds+") OR exists(select 1 from pj_department_teacher dt where dt.teacher_id=t.id and dt.is_deleted=0 and dt.department_id in ("+deptIds+") )";
        }else {
            for (int i = 0; i < targetTypes.size(); i++) {
                String targetType = targetTypes.get(i);
                //班级
                if (targetType.equals("pj.team")) {
                    sqlList[i] = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) SELECT " + noticeId + ", '学生' AS receiver_type, s.user_id, s.`name`, now() , now(), 0 FROM pj_student s inner JOIN pj_team_student ts ON ts.student_id = s.id WHERE  s.is_delete = 0 and ts.is_delete=0 AND ts.team_id =" + param.getTeamId();
                } else if (targetType.equals("pj.dept")) {
                    //部门
                    String deptIds = joinList(param.getDeptIdList());
                    sqlList[i] = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) SELECT " + noticeId + ", '老师' AS receiver_type, t.user_id, t.`name`, now(), now(), 0 FROM pj_teacher t WHERE t.is_delete=0 and  exists(select 1 from pj_department_teacher dt where dt.teacher_id=t.id and dt.is_deleted=0 and dt.department_id in (" + deptIds + ") )";
                } else if (targetType.equals("pj.person")) {
                    //个人（指定的老师）
                    String ids = joinList(param.getTeacherIdList());
                    sqlList[i] = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) SELECT " + noticeId + ", '老师' AS receiver_type, t.user_id, t.`name`, now() , now(), 0 FROM pj_teacher t WHERE t.is_delete=0 and t.id IN (" + ids + ")";
                }
            }
        }
        return sqlList;
    }


    private String joinList(List list){
        StringBuilder str=new StringBuilder();
        for(int i=0;i<list.size();i++){
            str.append(list.get(i).toString());
            if(i!=list.size()-1){
                str.append(",");
            }
        }
        return str.toString();
    }


    @Override
    public PubNotice updateStatusAndGetDetail(Integer id, Integer userId, Boolean updateRead){
        PubNotice notice=dao.selectById(id);
        if(notice==null){
            return null;
        }
        List<PubNoticeFile> files=noticeFileService.selectByNoticeId(notice.getId());
        for (PubNoticeFile file : files) {
            if(file!=null && file.getFileUrl()!=null){
                file.setFileUrl(ftpUtils.relativePath2HttpUrl(file.getFileUrl()));
            }
        }
        notice.setNoticeFiles(files);
        if(updateRead){
            Object rid=basicSQLService.findUnique("select id from pub_notice_receiver where notice_id="+id+" and receiver_id="+userId+" and `read`=0");
            if(rid!=null){
                String sql="update pub_notice_receiver set `read`=1,modify_date=now() where `read`=0 and id="+rid+";update pub_notice set read_count=read_count+1 where id="+id+" and read_count<user_count";
                basicSQLService.updateBatchByStr(sql);
            }
        }

        return notice;
    }


    private String buildBatchAddReceiverSql(Integer noticeId,List receiverList,String userIdPropertyName){
        StringBuilder sql=new StringBuilder("insert into pub_notice_receiver (notice_id,receiver_id,create_date,modify_date,is_deleted) values");
        for (int i=0;i<receiverList.size();i++) {
            Object receiver=receiverList.get(i);
            Object userId=null;
            if(receiver instanceof Map){
                userId=((Map)receiver).get(userIdPropertyName);
            }else {
                userId= ReflectUtil.getFieldValue(receiverList.get(i), userIdPropertyName);
            }
            sql.append("(").append(noticeId).append(",").append(userId).append(",now(),now(),0)");
            if(i!=receiverList.size()-1){
                sql.append(",");
            }
        }
        return sql.toString();
    }
}
