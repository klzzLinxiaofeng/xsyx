package com.xunyunedu.act.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.dao.InSchoolActivityDao;
import com.xunyunedu.act.pojo.InSchoolActivity;
import com.xunyunedu.act.service.InSchoolActivityService;
import com.xunyunedu.exception.PageCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InSchoolActivityServiceImpl implements InSchoolActivityService {

    @Autowired
    private InSchoolActivityDao dao;

    @Override
    public InSchoolActivity selectById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public void create(InSchoolActivity act) {
        dao.create(act);
    }

    @Override
    public PageInfo selectList(PageCondition<InSchoolActivity> condition) {
        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = dao.selectList(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }
}
