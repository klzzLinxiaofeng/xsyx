package platform.education.startuppage.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.startuppage.model.Startup;
import platform.education.startuppage.vo.StartupCondition;


import java.util.List;

public interface StartupDao extends GenericDao<Startup, Integer> {
    List<Startup> findStartupByCondition(StartupCondition var1, Page var2, Order var3);

    Startup findById(Integer var1);

    Long count(StartupCondition var1);
}
