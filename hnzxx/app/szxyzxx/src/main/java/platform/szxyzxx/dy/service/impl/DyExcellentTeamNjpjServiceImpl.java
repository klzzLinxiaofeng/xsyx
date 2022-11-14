package platform.szxyzxx.dy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dy.dao.DyExcellentTeamNjpjMapper;
import platform.szxyzxx.dy.pojo.DyExcellentTeamNjpj;
import platform.szxyzxx.dy.service.DyExcellentTeamNjpjService;
@Service
public class DyExcellentTeamNjpjServiceImpl implements DyExcellentTeamNjpjService {

    @Autowired
    private DyExcellentTeamNjpjMapper mapper;

    @Override
    public boolean add(DyExcellentTeamNjpj yscg) {
        return mapper.create(yscg)>0;
    }

}
