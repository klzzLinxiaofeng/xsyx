package platform.szxyzxx.wuyu.pindemoban.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.wuyu.pindemoban.service.PinDeMoBanService;
import platform.szxyzxx.wuyu.pindemoban.vo.PinDeMoBan;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/14 13:11
 * @Version 1.0
 */
@Service
public class PinDeMoBanServiceImpl implements PinDeMoBanService {
    @Autowired
    private PinDeMoBanService pinDeMoBanService;
    @Override
    public List<PinDeMoBan> findByAll(Integer zhibiaoId, String schoolYear, String schoolTrem, Page page) {
        return pinDeMoBanService.findByAll(zhibiaoId,schoolYear,schoolTrem,page);
    }

    @Override
    public PinDeMoBan findById(Integer id) {
        return pinDeMoBanService.findById(id);
    }

    @Override
    public Integer create(PinDeMoBan pinDeMoBan) {
        return pinDeMoBanService.create(pinDeMoBan);
    }

    @Override
    public Integer update(PinDeMoBan pinDeMoBan) {
        return pinDeMoBanService.update(pinDeMoBan);
    }

    @Override
    public Integer updateDelete(Integer id) {
        return pinDeMoBanService.updateDelete(id);
    }
}
