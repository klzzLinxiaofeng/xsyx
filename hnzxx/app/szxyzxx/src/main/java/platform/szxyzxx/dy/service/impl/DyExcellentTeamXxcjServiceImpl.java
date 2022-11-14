package platform.szxyzxx.dy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dy.dao.DyExcellentTeamXxcjMapper;
import platform.szxyzxx.dy.pojo.DyExcellentTeamXxcj;
import platform.szxyzxx.dy.service.DyExcellentTeamXxcjService;

@Service
public class DyExcellentTeamXxcjServiceImpl implements DyExcellentTeamXxcjService {

    @Autowired
    private DyExcellentTeamXxcjMapper mapper;

    @Override
    public boolean add(DyExcellentTeamXxcj yscg) {
        return mapper.create(yscg)>0;
    }

}
