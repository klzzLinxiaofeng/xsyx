package com.xunyunedu.act.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.dao.AtSubstituteDao;
import com.xunyunedu.act.pojo.AtSubstitute;
import com.xunyunedu.act.service.AtSubstituteService;
import com.xunyunedu.exception.PageCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtSubstituteServiceImpl implements AtSubstituteService {

    @Autowired(required = false)
    private AtSubstituteDao dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int create(AtSubstitute record) {
        return dao.insert(record);
    }

    @Override
    public int createSelective(AtSubstitute record) {
        return dao.insertSelective(record);
    }

    @Override
    public AtSubstitute selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AtSubstitute record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AtSubstitute record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo selectList(PageCondition<AtSubstitute> condition) {
            Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
            List list = dao.selectBy(condition.getCondition());
            PageInfo pageInfo = page.toPageInfo();
            pageInfo.setList(list);
            return pageInfo;

    }
}