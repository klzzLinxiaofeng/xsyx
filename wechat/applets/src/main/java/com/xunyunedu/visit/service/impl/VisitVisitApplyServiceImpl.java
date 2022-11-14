package com.xunyunedu.visit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.visit.dao.VisitVisitApplyDao;
import com.xunyunedu.visit.dao.VisitVisitApplyUserDao;
import com.xunyunedu.visit.pojo.VisitApplyCondition;
import com.xunyunedu.visit.pojo.VisitVisitApply;
import com.xunyunedu.visit.pojo.VisitVisitApplyUser;
import com.xunyunedu.visit.service.VisitVisitApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VisitVisitApplyServiceImpl implements VisitVisitApplyService {

    @Autowired
    private VisitVisitApplyDao dao;
    @Autowired
    private VisitVisitApplyUserDao applyUserDao;

    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public boolean add(VisitVisitApply apply) {

        if(dao.insertSelective(apply)>0){
           List<VisitVisitApplyUser> userList=apply.getVisitUserList();
            for (VisitVisitApplyUser visitVisitApplyUser : userList) {
                visitVisitApplyUser.setApplyId(apply.getId());
                applyUserDao.insert(visitVisitApplyUser);
            }
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<VisitVisitApply> pagingQuery(PageCondition<VisitApplyCondition> pageCondition) {
        VisitApplyCondition condition=pageCondition.getCondition();
        condition.setNowDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //"已失效"状态其实也是"待入校"
        if(condition.getStatusList()!=null && condition.getStatusList().size()>0){
            List<Integer> statusList= condition.getStatusList();
            if(statusList.contains(3) && statusList.contains(6)){
                statusList.remove((Object)6);
            }else if(statusList.contains(3) && !statusList.contains(6)){
                condition.setDrxFlag(0);
                statusList.remove((Object)3);
            }else if(statusList.contains(6) && !statusList.contains(3)){
                condition.setDrxFlag(1);
                statusList.remove((Object)6);
            }
        }
        Page page = PageHelper.startPage(pageCondition.getPageNum(),pageCondition.getPageSize());
        PageInfo<VisitVisitApply> pageInfo = page.toPageInfo();
        List<VisitVisitApply> list=dao.selectByCondition(condition);
        setInvalidStatus(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public PageInfo<VisitVisitApply> pagingQueryMyNotApproval(PageCondition<VisitApplyCondition> pageCondition) {
        Page page = PageHelper.startPage(pageCondition.getPageNum(),pageCondition.getPageSize());
        PageInfo<VisitVisitApply> pageInfo = page.toPageInfo();
        List<VisitVisitApply> list=dao.selectMyNotApproval(pageCondition.getCondition());
        setInvalidStatus(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public boolean updateSelective(VisitVisitApply apply) {
        return dao.updateByPrimaryKeySelective(apply)>0;
    }

    @Override
    public VisitVisitApply queryById(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public VisitVisitApply queryDetailById(Integer id) {
        VisitVisitApply apply= dao.selectDetailByPrimaryKey(id);
        if(apply.getVisitStatus().equals(3) && compareDate(new Date(),apply.getVisitDate())==1){
            apply.setVisitStatus(6);
        }
        return apply;
    }

    //设置失效状态
    private void setInvalidStatus(List<VisitVisitApply> list){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String nowString=sdf.format(new Date());
        for (VisitVisitApply apply : list) {
            if(apply.getVisitStatus().equals(3) && nowString.compareTo(sdf.format(apply.getVisitDate()))==1){
                apply.setVisitStatus(6);
            }
        }
    }

    private int compareDate(Date d1,Date d2){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d1).compareTo(sdf.format(d2));
    }

}
