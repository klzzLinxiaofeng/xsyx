package platform.szxyzxx.dy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dy.dao.DyExcellentTeamDkjMapper;
import platform.szxyzxx.dy.pojo.DyExcellentTeamDkj;
import platform.szxyzxx.dy.service.DyExcellentTeamDkjService;

@Service
public class DyExcellentTeamDkjServiceImpl implements DyExcellentTeamDkjService {

    @Autowired
    private DyExcellentTeamDkjMapper mapper;

    @Override
    public boolean add(DyExcellentTeamDkj d) {
        return mapper.create(d)>0;
    }

}
