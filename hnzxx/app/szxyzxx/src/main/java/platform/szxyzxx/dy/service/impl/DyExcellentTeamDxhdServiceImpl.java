package platform.szxyzxx.dy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dy.dao.DyExcellentTeamDxhdMapper;
import platform.szxyzxx.dy.pojo.DyExcellentTeamDxhd;
import platform.szxyzxx.dy.service.DyExcellentTeamDxhdService;

@Service
public class DyExcellentTeamDxhdServiceImpl implements DyExcellentTeamDxhdService {

    @Autowired
    private DyExcellentTeamDxhdMapper mapper;

    @Override
    public boolean add(DyExcellentTeamDxhd d) {
        return mapper.create(d)>0;
    }

}
