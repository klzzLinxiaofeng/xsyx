package com.xunyunedu.mergin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapBeanComparator implements Comparator {
    private List<Order> orders;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public MapBeanComparator() {
    }

    public int compare(Object o1, Object o2) {
        Iterator i$ = this.orders.iterator();

        Order order;
        int result;
        do {
            if (!i$.hasNext()) {
                return 0;
            }

            order = (Order)i$.next();
            result = this.compareField(o1, o2, order.getField());
        } while(result == 0);

        if (order.getAsc()) {
            return result;
        } else {
            return -result;
        }
    }

    private int compareField(Object o1, Object o2, String key) {
        try {
            Object value1 = this.getValueByKey(key, o1);
            Object value2 = this.getValueByKey(key, o2);
            if (value1 instanceof String) {
                String v1 = this.getValueByKey(key, o1).toString();
                String v2 = this.getValueByKey(key, o2).toString();
                Collator myCollator = Collator.getInstance();
                CollationKey[] keys = new CollationKey[]{myCollator.getCollationKey(v1), myCollator.getCollationKey(v2), null, null, null};
                return keys[0].compareTo(keys[1]);
            } else if (!"java.lang.Boolean".equals(value1.getClass().getName()) && !"java.lang.Byte".equals(value1.getClass().getName())) {
                BigDecimal b1 = new BigDecimal(value1.toString());
                BigDecimal b2 = new BigDecimal(value2.toString());
                return b1.compareTo(b2);
            } else {
                return 0;
            }
        } catch (Exception var10) {
            if (this.log.isInfoEnabled()) {
                this.log.info("-----------------------------------------------------------------------------");
                this.log.info("---------对象的该属性不存在或者不允许在此安全级别上反射该属性，详情请查阅JAVA DOC--------");
                this.log.info("-----------------------------------------------------------------------------");
            }

            if (this.log.isDebugEnabled()) {
                this.log.debug("异常信息为：", var10);
            }

            return -1;
        }
    }

    private Object getValueByKey(String key, Object map) {
        Map tempMap = null;

        try {
            if (map != null && map instanceof Map) {
                tempMap = (Map)map;
                return tempMap.get(key);
            } else {
                return null;
            }
        } catch (Exception var5) {
            this.log.info("------------------------------------------------------");
            this.log.info("---------该" + key + "属性不存在----------------------");
            this.log.info("------------------------------------------------------");
            return null;
        }
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
