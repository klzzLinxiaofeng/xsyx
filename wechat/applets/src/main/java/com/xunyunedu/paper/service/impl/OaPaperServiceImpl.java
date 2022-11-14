package com.xunyunedu.paper.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.department.condition.PjDepartmentTeacherCondition;

import com.xunyunedu.department.service.PjDepartmentTeacherService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;

import com.xunyunedu.paper.condition.OaPaperCondition;
import com.xunyunedu.paper.dao.OaPaperDao;
import com.xunyunedu.paper.param.OaPaperInsertParam;
import com.xunyunedu.paper.pojo.OaPaper;
import com.xunyunedu.paper.pojo.OaPaperUser;
import com.xunyunedu.paper.service.OaPaperDepartmentCountService;
import com.xunyunedu.paper.service.OaPaperService;
import com.xunyunedu.paper.service.OaPaperUserReadService;
import com.xunyunedu.paper.service.OaPaperUserService;
import com.xunyunedu.teacher.condition.TeacherSearchCondition;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.teacher.service.TeacherHomeService;
import com.xunyunedu.util.CommonUtil;
import com.xunyunedu.util.UUIDUtil;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OaPaperServiceImpl implements OaPaperService {

    @Autowired
    OaPaperDao dao;

    @Autowired
    OaPaperUserService oaPaperUserService;

    @Autowired
    OaPaperDepartmentCountService paperDepartmentCountService;

    @Autowired
    OaPaperUserReadService paperUserReadService;

    @Autowired
    TeacherHomeService teacherHomeService;

    @Autowired
    PjDepartmentTeacherService departmentTeacherService;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    OaPaperUserReadService readService;

    @Autowired
    private BasicSQLService basicSQLService;



    // 发布状态为全员
    private final static Integer quanyuan = 0;
    // 发布状态为部门
    private final static Integer bumen = 1;
    // 发布状态为个人
    private final static Integer geren = 2;

    @Override
    public PageInfo page(PageCondition<OaPaperCondition> condition){

        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = dao.selectByCondition(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }



    @Override
    @Transactional
    public OaPaper add(OaPaperInsertParam param){
        Date now=new Date();
        OaPaper paper = new OaPaper();
        BeanUtils.copyProperties(param,paper);
        paper.setUuid(UUIDUtil.getUUID());
        paper.setOwnerId(param.getSchoolId());
        paper.setOwnerType(param.getGroupType());
        paper.setReadCount(0);
        paper.setCreateDate(now);
        paper.setModifyDate(now);
        paper.setAppId(1);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        paper.setPublishDate(sdf.format(now));

        //如果范围是部门和全员，获取部对应的所有教师userId
        List<Integer> dtLists = null;
        if (param.getReceiverType().equals(bumen)) {
            dtLists=getDepartTeacherUserIdList(param);
        }else if(param.getReceiverType().equals(quanyuan)){
            dtLists=getSchoolTeacherUserIdList(param);
        }else{
            dtLists=param.getTeacherUserIds();
        }
        //根据发布的类型进行添加接收人数的数量
        addByType(param,paper,dtLists);

        dao.insert(paper);

        // 保存发布对象的相关信息
        savePaperUser(param,paper,dtLists);
        return paper;

    }




    private  List<Integer> getDepartTeacherUserIdList(OaPaperInsertParam param){
        PjDepartmentTeacherCondition condition=new PjDepartmentTeacherCondition();
        condition.setDepIdArr(param.getDepartIds());
        condition.setSchoolId(param.getSchoolId());
        condition.setIsDelete(false);
        return departmentTeacherService.getUserIdListByCondition(condition);
    }

    private  List<Integer> getSchoolTeacherUserIdList(OaPaperInsertParam param){
        PjDepartmentTeacherCondition condition=new PjDepartmentTeacherCondition();
        condition.setDepIdArr(param.getDepartIds());
        condition.setSchoolId(param.getSchoolId());
        condition.setIsDelete(false);
        return departmentTeacherService.getUserIdListByCondition(condition);
    }

    void addByType(OaPaperInsertParam param,OaPaper paper, List<Integer> dtList){
        if (paper.getReceiverType().equals(bumen) || paper.getReceiverType().equals(quanyuan)) {
            // 发布给部门的时候接收的人数
            paper.setReceiverCount(dtList.size());
        } else if (paper.getReceiverType().equals(geren)) {
            // 发布给个人的时候接收的人数
            paper.setReceiverCount(param.getTeacherUserIds().size());
        }
    }


    // 保存发布对象的相关信息
    public void savePaperUser(OaPaperInsertParam param,OaPaper paper, List<Integer> userIds) {
        //将接受者保存到公文阅读表中，并标志位未阅读
        if (userIds.size() > 0) {
            paperUserReadService.add(param,userIds,paper.getId());
        }

    }


    @Override
    public OaPaper getDetailAndUpdateRedById(Integer id,Integer currUserId) {
        OaPaper paper= dao.selectById(id);
        if(paper==null){
            return null;
        }
        Map<String,Object> params=new HashMap<>();
        if(paper.getAttachmentUuid()!=null) {
            EntityFile entityFile = uploaderDao.findFileByUUID(paper.getAttachmentUuid());
            if (entityFile != null) {
                params.put("fileName",entityFile.getFileName());
                params.put("fileUrl",ftpUtils.relativePath2HttpUrl(entityFile));
                paper.setParams(params);
            }
        }

        params.put("teacherName",(String)basicSQLService.findUnique("select u.name from oa_paper_user_read r left join pj_teacher u on u.user_id=r.user_id where r.paper_id="+id));

        if(paper.getReceiverType().equals(2) && currUserId!=null) {
            if (!readService.isRead(currUserId, id)) {
                readService.updateToRead(currUserId, id);
            }
        }

        return paper;
    }
}
