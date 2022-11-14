package com.xunyunedu.mergin.service;


import com.xunyunedu.mergin.util.Order;
import com.xunyunedu.mergin.util.Page;

import java.util.List;
import java.util.Map;

public interface JcCacheService {
    List<Map<String, Object>> findByExpr(String var1, String var2);

    Object findUniqueByParam(String var1, String var2, String var3, String var4);

    List<Map<String, Object>> findByExpr2(String tableName, String expr, Page page, List<Order> orders);
}
