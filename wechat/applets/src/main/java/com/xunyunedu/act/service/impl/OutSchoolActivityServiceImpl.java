package com.xunyunedu.act.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.dao.OutSchoolActivityDao;
import com.xunyunedu.act.pojo.InSchoolActivity;
import com.xunyunedu.act.pojo.OutSchoolActivity;
import com.xunyunedu.act.service.OutSchoolActivityService;
import com.xunyunedu.exception.PageCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutSchoolActivityServiceImpl implements OutSchoolActivityService {

    @Autowired
    private OutSchoolActivityDao dao;

    @Override
    public OutSchoolActivity selectById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public void create(OutSchoolActivity act) {
        dao.create(act);
    }

    @Override
    public PageInfo selectList(PageCondition<OutSchoolActivity> condition) {
        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = dao.selectList(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }
}
