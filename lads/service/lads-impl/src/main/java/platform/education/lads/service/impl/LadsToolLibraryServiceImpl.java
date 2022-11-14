package platform.education.lads.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.lads.model.LadsToolLibrary;
import platform.education.lads.vo.LadsToolLibraryCondition;
import platform.education.lads.service.LadsToolLibraryService;
import platform.education.lads.dao.LadsToolLibraryDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LadsToolLibraryServiceImpl implements LadsToolLibraryService {

    private Logger log = LoggerFactory.getLogger(getClass());
    private LadsToolLibraryDao ladsToolLibraryDao;

    public void setLadsToolLibraryDao(LadsToolLibraryDao ladsToolLibraryDao) {
        this.ladsToolLibraryDao = ladsToolLibraryDao;
    }

    @Override
    public LadsToolLibrary findLadsToolLibraryById(String id) {
        try {
            return ladsToolLibraryDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public LadsToolLibrary add(LadsToolLibrary ladsToolLibrary) {
        return ladsToolLibraryDao.create(ladsToolLibrary);
    }

    @Override
    public LadsToolLibrary modify(LadsToolLibrary ladsToolLibrary) {
        return ladsToolLibraryDao.update(ladsToolLibrary);
    }

    @Override
    public void remove(LadsToolLibrary ladsToolLibrary) {
        ladsToolLibraryDao.delete(ladsToolLibrary);
    }

    @Override
    public List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition, Page page, Order order) {
        return ladsToolLibraryDao.findLadsToolLibraryByCondition(ladsToolLibraryCondition, page, order);
    }

    @Override
    public List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition) {
        return ladsToolLibraryDao.findLadsToolLibraryByCondition(ladsToolLibraryCondition, null, null);
    }

    @Override
    public List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition, Page page) {
        return ladsToolLibraryDao.findLadsToolLibraryByCondition(ladsToolLibraryCondition, page, null);
    }

    @Override
    public List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition, Order order) {
        return ladsToolLibraryDao.findLadsToolLibraryByCondition(ladsToolLibraryCondition, null, order);
    }

    @Override
    public Long count() {
        return this.ladsToolLibraryDao.count(null);
    }

    @Override
    public Long count(LadsToolLibraryCondition ladsToolLibraryCondition) {
        return this.ladsToolLibraryDao.count(ladsToolLibraryCondition);
    }

    @Override
    public void remove(LadsToolLibraryCondition ladsToolLibraryCondition) {
        this.ladsToolLibraryDao.deleteByCondition(ladsToolLibraryCondition);
    }

    //以下是业务方法
    @Override
    public LadsToolLibrary findByToolName(String toolName) {
        return this.ladsToolLibraryDao.findByToolName(toolName);
    }
}
