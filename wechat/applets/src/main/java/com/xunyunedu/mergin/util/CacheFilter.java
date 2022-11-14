package com.xunyunedu.mergin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CacheFilter {
    private List<QueryParam> queryParams = new ArrayList();
    private String[] relations = new String[]{"!=", "<>", ">=", "<=", "=", "<", ">", "like"};
    private String cacheKey;
    private Page page;
    private List<Order> orders;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public CacheFilter() {
    }

    public void add(String relation, String field, Object value) {
        this.queryParams.add(new QueryParam(relation, field, value));
    }

    public List<Map<String, Object>> getEache(List<Map<String, Object>> mapList) {
        List<Map<String, Object>> eList = new ArrayList();
        int i = 0;
        int start = 0;
        int end = 0;
        int currentPage;
        int pageSize = 0;
        if (this.page != null) {
             currentPage = this.page.getCurrentPage();
            pageSize = this.page.getPageSize();
            start = (currentPage - 1) * pageSize;
            end = currentPage * pageSize - 1;
        }

        Iterator i$ = mapList.iterator();

        while(true) {
            label336:
            while(i$.hasNext()) {
                Map map = (Map)i$.next();
                Iterator as = this.queryParams.iterator();

                while(true) {
                    while(as.hasNext()) {
                        QueryParam parm = (QueryParam)as.next();
                        String field = parm.getField();
                        Object value = parm.getValue();
                        Object mValue = map.get(field);
                        String relation = parm.getRelation();
                        Date valueDate;
                        String newS;
                        //String mValue = null;
                        Integer valueInt;
                        Long valueLong;
                        Short valueShort;
                        Byte valueByte;
                        if ("=".equals(relation)) {
                            if (value != null) {
                                try {
                                    if (mValue instanceof String) {
                                        mValue = ((String)mValue).trim();
                                        if (!value.equals(mValue)) {
                                            continue label336;
                                        }
                                    } else if (mValue instanceof Date) {
                                        valueDate = (Date)mValue;
                                        newS = this.sdf.format(valueDate);
                                        if (!value.equals(newS)) {
                                            continue label336;
                                        }
                                    } else if (mValue instanceof Integer) {
                                        valueInt = Integer.parseInt((String)value);
                                        if (valueInt != (Integer)mValue) {
                                            continue label336;
                                        }
                                    } else if (mValue instanceof Long) {
                                        valueLong = Long.parseLong((String)value);
                                        if (valueLong != (Long)mValue) {
                                            continue label336;
                                        }
                                    } else if (mValue instanceof Short) {
                                        valueShort = Short.parseShort((String)value);
                                        if (valueShort != (Short)mValue) {
                                            continue label336;
                                        }
                                    } else if (mValue instanceof Byte) {
                                        valueByte = Byte.parseByte((String)value);
                                        if (valueByte != (Byte)mValue) {
                                            continue label336;
                                        }
                                    }
                                } catch (Exception var39) {
                                    continue label336;
                                }
                            } else if (map.containsKey(field)) {
                                continue label336;
                            }
                        } else if (!"!=".equals(relation) && !"<>".equals(relation)) {
                            Date mValueDate;
                            if (">".equals(relation)) {
                                if (mValue instanceof String) {
                                    try {
                                        if (Integer.parseInt((String)value) >= Integer.parseInt((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var37) {
                                        this.log.info("{} 的值 {} ：无法装换为Integer类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Integer类型", field, value);
                                    }

                                    try {
                                        if (Long.parseLong((String)value) >= Long.parseLong((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var36) {
                                        this.log.info("{} 的值 {} ：无法装换为Long类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Long类型", field, value);
                                    }

                                    try {
                                        if (Double.parseDouble((String)value) >= Double.parseDouble((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var35) {
                                        this.log.info("{} 的值 {} ：无法装换为Double类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Double类型", field, value);
                                    }

                                    try {
                                        if (Float.parseFloat((String)value) >= Float.parseFloat((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var34) {
                                        this.log.info("{} 的值 {} ：无法装换为Float类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Float类型", field, value);
                                        continue label336;
                                    }
                                } else if (mValue instanceof Date) {
                                    try {
                                        valueDate = this.sdf.parse((String)value);
                                        mValueDate = (Date)mValue;
                                        mValueDate = this.sdf.parse(this.sdf.format(mValueDate));
                                        if (valueDate.compareTo(mValueDate) != -1) {
                                            continue label336;
                                        }
                                    } catch (ParseException var33) {
                                        continue label336;
                                    }
                                }
                            } else if ("<".equals(relation)) {
                                if (mValue instanceof String) {
                                    try {
                                        if (Integer.parseInt((String)value) <= Integer.parseInt((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var32) {
                                        this.log.info("{} 的值 {} ：无法装换为Integer类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Integer类型", field, value);
                                    }

                                    try {
                                        if (Long.parseLong((String)value) <= Long.parseLong((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var31) {
                                        this.log.info("{} 的值 {} ：无法装换为Long类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Long类型", field, value);
                                    }

                                    try {
                                        if (Double.parseDouble((String)value) <= Double.parseDouble((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var30) {
                                        this.log.info("{} 的值 {} ：无法装换为Double类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Double类型", field, value);
                                    }

                                    try {
                                        if (Float.parseFloat((String)value) <= Float.parseFloat((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var29) {
                                        this.log.info("{} 的值 {} ：无法装换为Float类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Float类型", field, value);
                                        continue label336;
                                    }
                                } else if (mValue instanceof Date) {
                                    try {
                                        valueDate = this.sdf.parse((String)value);
                                        mValueDate = (Date)mValue;
                                        mValueDate = this.sdf.parse(this.sdf.format(mValueDate));
                                        if (valueDate.compareTo(mValueDate) != 1) {
                                            continue label336;
                                        }
                                    } catch (ParseException var28) {
                                        continue label336;
                                    }
                                }
                            } else if ("<=".equals(relation)) {
                                if (mValue instanceof String) {
                                    try {
                                        if (Integer.parseInt((String)value) < Integer.parseInt((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var27) {
                                        this.log.info("{} 的值 {} ：无法装换为Integer类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Integer类型", field, value);
                                    }

                                    try {
                                        if (Long.parseLong((String)value) < Long.parseLong((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var26) {
                                        this.log.info("{} 的值 {} ：无法装换为Long类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Long类型", field, value);
                                    }

                                    try {
                                        if (Double.parseDouble((String)value) < Double.parseDouble((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var25) {
                                        this.log.info("{} 的值 {} ：无法装换为Double类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Double类型", field, value);
                                    }

                                    try {
                                        if (Float.parseFloat((String)value) < Float.parseFloat((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var24) {
                                        this.log.info("{} 的值 {} ：无法装换为Float类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Float类型", field, value);
                                        continue label336;
                                    }
                                } else if (mValue instanceof Date) {
                                    try {
                                        valueDate = this.sdf.parse((String)value);
                                        mValueDate = (Date)mValue;
                                        mValueDate = this.sdf.parse(this.sdf.format(mValueDate));
                                        if (valueDate.compareTo(mValueDate) == -1) {
                                            continue label336;
                                        }
                                    } catch (ParseException var23) {
                                        continue label336;
                                    }
                                }
                            } else if (">=".equals(relation)) {
                                if (mValue instanceof String) {
                                    try {
                                        if (Integer.parseInt((String)value) > Integer.parseInt((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var22) {
                                        this.log.info("{} 的值 {} ：无法装换为Integer类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Integer类型", field, value);
                                    }

                                    try {
                                        if (Long.parseLong((String)value) > Long.parseLong((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var21) {
                                        this.log.info("{} 的值 {} ：无法装换为Long类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Long类型", field, value);
                                    }

                                    try {
                                        if (Double.parseDouble((String)value) > Double.parseDouble((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var20) {
                                        this.log.info("{} 的值 {} ：无法装换为Double类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Double类型", field, value);
                                    }

                                    try {
                                        if (Float.parseFloat((String)value) > Float.parseFloat((String)mValue)) {
                                            continue label336;
                                        }
                                    } catch (Exception var19) {
                                        this.log.info("{} 的值 {} ：无法装换为Float类型", field, value);
                                        this.log.info("用户输入的 {} 的值 {} ：无法装换为Float类型", field, value);
                                        continue label336;
                                    }
                                } else if (mValue instanceof Date) {
                                    try {
                                        valueDate = this.sdf.parse((String)value);
                                        mValueDate = (Date)mValue;
                                        mValueDate = this.sdf.parse(this.sdf.format(mValueDate));
                                        if (valueDate.compareTo(mValueDate) == 1) {
                                            continue label336;
                                        }
                                    } catch (ParseException var18) {
                                        continue label336;
                                    }
                                }
                            } else if ("like".equals(relation) && mValue instanceof String) {
                                String oldS = (String)mValue;
                                newS = (String)mValue;
                                if (!oldS.contains(newS)) {
                                    continue label336;
                                }
                            }
                        } else if (value != null) {
                            try {
                                if (mValue instanceof String) {
                                    mValue = ((String)mValue).trim();
                                    if (value.equals(mValue)) {
                                        continue label336;
                                    }
                                } else if (mValue instanceof Date) {
                                    valueDate = (Date)mValue;
                                    newS = this.sdf.format(valueDate);
                                    if (value.equals(newS)) {
                                        continue label336;
                                    }
                                } else if (mValue instanceof Integer) {
                                    valueInt = Integer.parseInt((String)value);
                                    if (valueInt == (Integer)mValue) {
                                        continue label336;
                                    }
                                } else if (mValue instanceof Long) {
                                    valueLong = Long.parseLong((String)value);
                                    if (valueLong == (Long)mValue) {
                                        continue label336;
                                    }
                                } else if (mValue instanceof Short) {
                                    valueShort = Short.parseShort((String)value);
                                    if (valueShort == (Short)mValue) {
                                        continue label336;
                                    }
                                } else if (mValue instanceof Byte) {
                                    valueByte = Byte.parseByte((String)value);
                                    if (valueByte == (Byte)mValue) {
                                        continue label336;
                                    }
                                }
                            } catch (Exception var38) {
                                continue label336;
                            }
                        } else if (!map.containsKey(field)) {
                            continue label336;
                        }
                    }

                    if (this.page != null) {
                        if (i >= start && i <= end) {
                            eList.add(map);
                        }

                        ++i;
                    } else {
                        eList.add(map);
                    }
                    break;
                }
            }

            if (this.orders != null && this.orders.size() > 0) {
                MapBeanComparator comparator = new MapBeanComparator();
                comparator.setOrders(this.orders);
                Collections.sort(eList, comparator);
            }

            if (this.page != null) {
                this.page.setTotalRows(i);
                if (i % pageSize == 0) {
                    this.page.setTotalPages(i / pageSize);
                } else {
                    this.page.setTotalPages(i / pageSize + 1);
                }
            }

            return eList;
        }
    }

    public List<QueryParam> getQueryParams() {
        return this.queryParams;
    }

    public void setQueryParams(List<QueryParam> queryParams) {
        this.queryParams = queryParams;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String[] getRelations() {
        return this.relations;
    }
}
