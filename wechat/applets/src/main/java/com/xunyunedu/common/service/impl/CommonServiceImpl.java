package com.xunyunedu.common.service.impl;

import com.xunyunedu.common.dao.CommonDao;
import com.xunyunedu.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/1/21 15:51
 * @Description:
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    /**
     * 查询jc_gc_item表，下拉框使用
     *
     * @param code
     * @return
     */
    @Override
    public List<Map<String, Integer>> getValueByCode(String code) {
        return commonDao.getValueByCode(code);
    }
}
