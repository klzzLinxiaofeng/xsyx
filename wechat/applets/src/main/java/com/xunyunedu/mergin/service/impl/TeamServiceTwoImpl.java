package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.TeamTwoDao;
import com.xunyunedu.mergin.service.TeamTwoService;
import com.xunyunedu.mergin.vo.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeamServiceTwoImpl implements TeamTwoService {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private TeamTwoDao teamDao;
    @Override
    public Team findTeamById(Integer id) {
        try {
            return teamDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

}
