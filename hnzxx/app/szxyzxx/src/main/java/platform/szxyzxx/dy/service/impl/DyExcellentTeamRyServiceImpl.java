package platform.szxyzxx.dy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dy.dao.DyExcellentTeamRyMapper;
import platform.szxyzxx.dy.pojo.DyExcellentTeamRy;
import platform.szxyzxx.dy.service.DyExcellentTeamRyService;

@Service
public class DyExcellentTeamRyServiceImpl implements DyExcellentTeamRyService {

    @Autowired
    private DyExcellentTeamRyMapper mapper;

    @Override
    public boolean add(DyExcellentTeamRy d) {
        return mapper.create(d)>0;
    }

}
