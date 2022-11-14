package com.xunyunedu.mergin.controller;

import com.xunyunedu.mergin.service.JcCacheService;
import com.xunyunedu.mergin.service.JcGcCacheService;
import com.xunyunedu.mergin.util.Order;
import com.xunyunedu.mergin.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cache")
@Api(value = "/cache", description = "测试")
public class JcCacheController {
    @Autowired
    private JcCacheService jcCacheService;
    //根据json字符串获取缓存
    @Autowired
    private JcGcCacheService jcGcCacheService;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/findByExpr")
    @ApiOperation(value = "测试" , httpMethod = "POST")
    public @ResponseBody
    List<Map> findByCondition(
            @RequestParam("tn") String tn,
            @RequestParam("expr") String expr,
            @RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
            @RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
            Page page,
            @RequestParam(value = "order", required = false) String orderStr) {
        List result = null;
        if ("1".equals(useOrder) || "1".equals(usePage)) {
            List orders = null;
            if ("1".equals(useOrder)) {
                orders = new ArrayList<Order>();
                if (orderStr != null && !"".equals(orderStr)) {
                    JSONArray ja = JSONArray.fromObject(orderStr);
                    Iterator<Order> orderIts = ja.iterator();
                    while (orderIts.hasNext()) {
                        JSONObject jo = JSONObject.fromObject(orderIts.next());
                        orders.add((Order) JSONObject.toBean(jo, Order.class));
                    }
                }
                System.out.println("12122221"+tn);
                result = jcCacheService.findByExpr2(tn, expr,"1".equals(usePage) ? page : null, orders);
            }
        } else {
            System.out.println("12131111"+tn);
            result = this.jcCacheService.findByExpr(tn, expr);
        }
        return result;

    }


    /**
     * @Method findByTableCodeWithLevel
     * @Function 功能描述：根据tableCode以及层次级别leve获取分类下的数据项，可分页且排序
     * @param tableCode 分类代码 系统中基础代码的分类都有唯一的一个code 如 性别 对应的tableCode为 GB-XB
     * @param usePage 是否使用分页 1：使用分页 0：不使用分页 默认不使用分页
     * @param level 数据代码项所属的层次级别 默认为0
     * @param asc 是否根据listOrder进行升序 默认是
     * @param page 分页组件
     * @return
     * @Date 2015年3月31日
     */
    @RequestMapping(value = "/jcgc/items")
    @ResponseBody
    public List<Map<String, Object>> findByTableCodeWithLevel(
            @RequestParam("tc") String tableCode,
            @RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
            @RequestParam(value = "level", required = false, defaultValue = "0") String level,
            @RequestParam(value = "asc", required = false, defaultValue = "true") String asc,
            Page page) {
        List<Map<String, Object>> result = null;
        try {
            result = this.jcGcCacheService.findByTableCodeWithLevel(tableCode, level, "1".equals(usePage) ? page : null, "true".equals(asc));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
