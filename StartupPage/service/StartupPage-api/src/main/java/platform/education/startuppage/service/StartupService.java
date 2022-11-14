package platform.education.startuppage.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.startuppage.model.Startup;
import platform.education.startuppage.vo.StartupCondition;

import java.util.List;

public interface StartupService {
    Startup findStartupById(Integer var1);

    Startup add(Startup var1);

    Startup modify(Startup var1);

    void remove(Startup var1);

    List<Startup> findStartupCondition(StartupCondition var1, Page var2, Order var3);

    List<Startup> findStartupCondition(StartupCondition var1);

    List<Startup> findStartupCondition(StartupCondition var1, Page var2);

    List<Startup> findStartupCondition(StartupCondition var1, Order var2);

    Long count();

    Long count(StartupCondition var1);
}
