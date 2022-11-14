package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.ExcuteSqlDao;
import com.xunyunedu.mergin.service.JcCacheService;
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
public class JcCacheServiceImpl implements JcCacheService {

    @Autowired
   private ExcuteSqlDao excuteSqlDao;

    private URL url;
    private String cacheName = "jc_cache";
    private String cacheConfigPath = "/generalCode_ehcache.xml";
    private String databaseName = "edu_general_code";
    private String defPrimaryKey = null;
    @Override
    public List<Map<String, Object>> findByExpr(String tableName, String expr) {
       CacheFilter cacheFilter = new CacheFilter();
        cacheFilter.setCacheKey(tableName);
        this.parseParam(cacheFilter, expr);
        Collection<? extends Map<String, Object>> values = this.getCacheValueByTableName(tableName).values();
        List<Map<String, Object>> mapList = new ArrayList(values);
        return cacheFilter.getEache(mapList);
    }

    @Override
    public List<Map<String, Object>> findByExpr2(String tableName, String expr, Page page, List<Order> orders) {
        CacheFilter cacheFilter = new CacheFilter();
        cacheFilter.setCacheKey(tableName);
        this.parseParam(cacheFilter, expr);
        Collection<? extends Map<String, Object>> values = this.getCacheValueByTableName(tableName).values();
        List<Map<String, Object>> mapList = new ArrayList(values);
        cacheFilter.setPage(page);
        cacheFilter.setOrders(orders);
        return cacheFilter.getEache(mapList);
    }

    private void parseParam(CacheFilter cacaheFilter, String param) {
        String[] relations = cacaheFilter.getRelations();
        if (param != null && !"".equals(param)) {
            String[] p = param.split(";");

            for(int i = 0; i < p.length; ++i) {
                String pa = p[i];
                String[] arr$ = relations;
                int len$ = relations.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String re = arr$[i$];
                    if (pa.indexOf(re) != -1) {
                        String[] t = p[i].split(re);
                        cacaheFilter.add(re, t[0], t[1]);
                        break;
                    }
                }
            }
        }

    }

    private Map<String, Map<String, Object>> getCacheValueByTableName(String tableName) {
        System.out.println("tableName"+tableName);
        url = this.getClass().getResource(cacheConfigPath);
        CacheManager cacheManager = CacheManager.create(url);
        Cache cache = cacheManager.getCache(cacheName);
        System.out.println("1111"+cache);
        System.out.println("222"+cache.get(tableName));
        Element e = cache.get(tableName);
        System.out.println(cache.isElementInMemory(tableName));
        if (cache.isElementInMemory(tableName) && e != null && e.getObjectValue() != null) {
            System.out.println("hahhahh 1");
            Map<String, Map<String, Object>> map = (Map)e.getObjectValue();
            System.out.println(map);
            return map;
        } else {
            System.out.println("hahhahh 2");
            List<Map<String, Object>> mapList = excuteSqlDao.findBySql("select * from " + tableName);
            if (defPrimaryKey == null || "".equals(defPrimaryKey)) {
                StringBuffer sql = new StringBuffer("SELECT k.column_name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING (constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY' AND t.table_schema='" + databaseName + "'  AND t.table_name='");
                sql.append(tableName).append("'");
                Map<String, Object> pk = excuteSqlDao.findUniqueBySql(sql.toString());
                Map.Entry entryPk=null;
                if(pk!=null){
                    for(Map.Entry  entry: pk.entrySet()){
                        entryPk = entry;
                    }
                }
            }

            Map<String, Map<String, Object>> tableMap = new HashMap();
            Iterator i$ = mapList.iterator();
            while(i$.hasNext()) {
                Map<String, Object> map = (Map)i$.next();
                Map<String, Object> map2 = new HashMap();
                Iterator ASD = map.entrySet().iterator();

                while(ASD.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry)ASD.next();
                    map2.put(StringToParam((String)entry.getKey()), entry.getValue());
                }
                tableMap.put(String.valueOf(map.get(this.defPrimaryKey)), map2);
            }
            Element e1 = new Element(tableName, tableMap);
            cache.put(e1);
            System.out.println("1213"+tableMap);
            System.out.println(tableMap.get("null"));
            System.out.println(tableMap.get("parent"));
            return tableMap;
        }
    }

    public static String StringToParam(String paramName) {
        paramName = paramName.toLowerCase();
        String[] daoName = paramName.split("_");
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

    public Object findUniqueByParam(String tableName, String paramName, String value, String echoField) {
        CacheFilter cacheFilter = new CacheFilter();
        cacheFilter.add("=", paramName, value);
        cacheFilter.setCacheKey(tableName);
        Map<String, Map<String, Object>> map = this.getCacheValueByTableName(tableName);
        List<Map<String, Object>> mapList = new ArrayList(map.values());
        List<Map<String, Object>> mapList2 = cacheFilter.getEache(mapList);
        if (mapList2 != null && mapList2.size() > 0) {
            Map result = (Map)mapList2.get(0);
            return result.get(echoField);
        } else {
            return null;
        }
    }

}
