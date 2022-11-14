package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.ExcuteSqlDao;
import com.xunyunedu.mergin.service.JcGcCacheService;
import com.xunyunedu.mergin.util.CacheFilter;
import com.xunyunedu.mergin.util.Order;
import com.xunyunedu.mergin.util.Page;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.*;

@Service
public class JcGcCacheServiceImpl implements JcGcCacheService {

    private String cacheConfigPath = "/generalCode_ehcache.xml";
    private String defPrimaryKey = null;

    @Autowired
    private ExcuteSqlDao excuteSqlDao;

    private String cacheName = "jc_gc_cache";

    @Override
    public List<Map<String, Object>> findByTableCodeWithLevel(String tableCode, String level, Page page, boolean isAsc) {
        List<Order> orders = new ArrayList();
        orders.add(new Order("sortOrder", isAsc));
        return this.findByTableCodeWithLevel2(tableCode, level, page, orders);
    }

    public List<Map<String, Object>> findByTableCodeWithLevel2(String tableCode, String level, Page page, List<Order> orders) {
        CacheFilter cacheFilter = new CacheFilter();
        cacheFilter.add("=", "level", level);
        cacheFilter.setPage(page);
        cacheFilter.setOrders(orders);
        Map<String, Map<String, Object>> map = this.getCacheValueByTableCode(tableCode);
        return cacheFilter.getEache(new ArrayList(map.values()));
    }

    private Map<String, Map<String, Object>> getCacheValueByTableCode(String tableCode) {
        URL url = this.getClass().getResource(this.cacheConfigPath);
        CacheManager cacheManager = CacheManager.create(url);
        Cache cache = cacheManager.getCache(this.cacheName);
        Element e = cache.get(tableCode);
        if (cache.isElementInMemory(tableCode) && e != null && e.getObjectValue() != null) {
            Map<String, Map<String, Object>> map = (Map) e.getObjectValue();
            return map;
        } else {
            List<Map<String, Object>> mapList = excuteSqlDao.findBySql("SELECT * FROM jc_gc_item WHERE table_code = '" + tableCode + "' AND disable = 0");
            Map<String, Map<String, Object>> tableMap = new HashMap();
            Iterator i$ = mapList.iterator();

            while (i$.hasNext()) {
                Map<String, Object> map = (Map) i$.next();
                Map<String, Object> map2 = new HashMap();
                Iterator asd = map.entrySet().iterator();

                while (asd.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry) asd.next();
                    map2.put(StringToParam((String) entry.getKey()), entry.getValue());
                }

                tableMap.put(String.valueOf(map.get(this.defPrimaryKey)), map2);
            }
            e = new Element(tableCode, tableMap);
            cache.put(e);
            return tableMap;
        }
    }

        private static String StringToParam(String paramName) {
           String paramNames =paramName.toLowerCase();
            String[] daoName = paramNames.split("_");
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < daoName.length; ++i) {
                if (i > 0) {
                    String str = daoName[i];
                    char[] ch = str.toCharArray();
                    if (ch[0] >= 'a' && ch[0] <= 'z') {
                        ch[0] = (char)(ch[0] - 32);
                    }

                    char[] arr$ = ch;
                    int len$ = ch.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        char c = arr$[i$];
                        sb.append(c);
                    }
                } else {
                    sb.append(daoName[i]);
                }
            }

            return sb.toString();
        }

}
