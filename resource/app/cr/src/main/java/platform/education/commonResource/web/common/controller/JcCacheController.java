package platform.education.commonResource.web.common.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.generalcode.cache.Order;
import platform.education.generalcode.cache.Page;

/**
 * 获取缓存控制器
 * 
 * @author 黄江南,潘维良
 * @version 1.0 2014-08-08
 */
@Controller
public class JcCacheController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(JcCacheController.class);

	/**
	 * 根据表名称，字段名称，字段的值获取缓存
	 * 
	 * @param tableName
	 *            表名称
	 * @param paramName
	 *            字段名称
	 * @param value
	 *            字段的值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cache/findByParam")
	public @ResponseBody List<Map<String, Object>> findByParm(
			@RequestParam("tn") String tableName,
			@RequestParam(value = "pn", required = false) String paramName,
			@RequestParam(value = "value", required = false) String value,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
			Page page,
			@RequestParam(value = "order", required = false) String orderStr) {
		List<Map<String, Object>> mapList = null;
		List<Order> orders = null;
		try {
			if ("null".equals(value)) {
				value = null;
			}
			if ("1".equals(useOrder)) {
				orders = new ArrayList<Order>();
				JSONArray ja = JSONArray.fromObject(orderStr);
				Iterator<Order> orderIts = ja.iterator();
				while (orderIts.hasNext()) {
					JSONObject jo = JSONObject.fromObject(orderIts.next());
					orders.add((Order) JSONObject.toBean(jo, Order.class));
				}
			}
			mapList = this.jcCacheService.findByParam(tableName, paramName,
					value, "1".equals(usePage) ? page : null, orders);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("没有这个表'{}'或者没有这个字段'{}'", tableName, paramName);
		}
		return mapList;
	}

	/**
	 * 根据表名称获取缓存
	 * 
	 * @param tableName
	 *            表名称
	 * @return
	 */
	@RequestMapping(value = "/cache/findAll")
	public @ResponseBody List<Map<String, Object>> findAll(
			@RequestParam("tn") String tableName) {
		List<Map<String, Object>> mapList = null;
		try {
			Collection<? extends Map<String, Object>> values = (Collection<? extends Map<String, Object>>) this.jcCacheService
					.findAll(tableName).values();
			mapList = new ArrayList<Map<String, Object>>(values);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("没有这个表{}", tableName);
		}
		return mapList;
	}

	@RequestMapping(value = "/cache/refresh")
	public @ResponseBody boolean refresh(@RequestParam("tn") String tableName,
			@RequestParam("id") String id) {
		return this.jcCacheService.refresh(tableName, id);
	}

	/**
	 * 根据json字符串获取缓存
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cache/findByExpr")
	public @ResponseBody List<Map> findByCondition(
			@RequestParam("tn") String tableName,
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
				result = this.jcCacheService.findByExpr(tableName, expr,
						"1".equals(usePage) ? page : null, orders);
			}
		} else {
			result = this.jcCacheService.findByExpr(tableName, expr);
		}
		return result;
	}
}
