package platform.szxyzxx.dy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dy.dao.DyExcellentTeamYrcgMapper;
import platform.szxyzxx.dy.pojo.DyExcellentTeamYrcg;
import platform.szxyzxx.dy.service.DyExcellentTeamYrcgService;

@Service
public class DyExcellentTeamYrcgServiceImpl implements DyExcellentTeamYrcgService {

    @Autowired
    private DyExcellentTeamYrcgMapper mapper;

    @Override
    public boolean add(DyExcellentTeamYrcg d) {
        return mapper.create(d)>0;
    }

}
