package platform.education.oa.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.oa.dao.AdjustClassDao;
import platform.education.oa.model.AdjustClass;
import platform.education.oa.service.AdjustClassService;
import platform.education.oa.vo.AdjustClassCondition;
import platform.education.oa.vo.AdjustClassVo;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-26 16:33
 */
public class AdjustClassServiceImpl implements AdjustClassService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private AdjustClassDao adjustClassDao;

    public void setAdjustClassDao(AdjustClassDao adjustClassDao) {
        this.adjustClassDao = adjustClassDao;
    }

    @Override
    public List<AdjustClass> findAdjustClassByCondition(AdjustClassCondition adjustClassCondition, Page page, Order order) {
        return this.adjustClassDao.findAdjustClassByCondition(adjustClassCondition, page, order);
    }

    @Override
    public Long count(AdjustClassCondition adjustClassCondition) {
        return this.adjustClassDao.count(adjustClassCondition);
    }

    @Override
    public List<AdjustClassVo> findAdjustClassVoByCondition(AdjustClassCondition adjustClassCondition, Page page, Order order) {
        return this.adjustClassDao.findAdjustClassVoByCondition(adjustClassCondition, page, order);
    }

    @Override
    public AdjustClass findById(Integer id) {
        return this.adjustClassDao.findById(id);
    }

    @Override
    public List<AdjustClass> findAdjustClassByCondition(AdjustClassCondition adjustClassCondition) {
        return adjustClassDao.findAdjustClassByCondition(adjustClassCondition, null, null);
    }

    @Override
    public List<AdjustClass> modifyReject(AdjustClass adjustClass) {
        adjustClass.setApplyLesson(adjustClass.getApplyLesson().substring(2, 3));
        adjustClass.setSetLesson(adjustClass.getSetLesson().substring(2, 3));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String applyDateStr = df.format(adjustClass.getApplyDate());
        String setDateStr = df.format(adjustClass.getSetDate());
        return adjustClassDao.findAdjustClassByDate(adjustClass, applyDateStr, setDateStr);
    }

}
