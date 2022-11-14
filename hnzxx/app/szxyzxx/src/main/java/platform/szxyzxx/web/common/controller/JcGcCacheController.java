package platform.szxyzxx.web.common.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalcode.cache.Order;
import platform.education.generalcode.cache.Page;
import platform.szxyzxx.web.common.controller.base.BaseController;
/**
 * <p>Title:JcGcCacheController.java</p>
 * <p>Description:数字校园</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：jc_gc_item 对应的基础代码数据 restful 接口
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年3月31日
 */
@Controller
public class JcGcCacheController extends BaseController {
	
	/**
	 * @Method findByTableCode
	 * @Function 功能描述：根据tableCode获取分类下的所有数据项，可分页且排序
	 * @param tableCode  分类代码 系统中基础代码的分类都有唯一的一个code 如 性别 对应的tableCode为 GB-XB
	 * @param usePage 是否使用分页 1：使用分页 0：不使用分页
	 * @param useOrder 是否使用排序 1：使用排序 0：不使用排序
	 * @param page 排序组件
	 * @param orderStr 为json字符串格式如：[{"field" : "listOrder", "asc" : "true"}, {"field" : "listOrder", "asc" : "false"} ...]
	 * @return
	 * @Date 2015年3月31日
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cache/jcgc/all")
	@ResponseBody
	public List<Map<String, Object>> findByTableCode(
			@RequestParam("tc") String tableCode,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
			Page page,
			@RequestParam(value = "order", required = false) String orderStr) {
		List<Map<String, Object>> mapList = null;
		List<Order> orders = null;
		try {
			if ("1".equals(useOrder)) {
				orders = new ArrayList<Order>();
				JSONArray ja = JSONArray.fromObject(orderStr);
				Iterator<Order> orderIts = ja.iterator();
				while (orderIts.hasNext()) {
					JSONObject jo = JSONObject.fromObject(orderIts.next());
					orders.add((Order) JSONObject.toBean(jo, Order.class));
				}
			}
			mapList = this.jcGcCacheService.findByTableCode(tableCode, "1".equals(usePage) ? page : null, orders);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapList;
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
	@RequestMapping(value = "/cache/jcgc/items")
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
	
	/**
	 * @Method findByCondition
	 * @Function 功能描述：根据分类代码code以及指定用于做筛选条件的代码项字段且对应的值进行数据筛选
	 * @param tableCode 分类代码 系统中基础代码的分类都有唯一的一个code 如 性别 对应的tableCode为 GB-XB
	 * @param field 对应的是jc_gc_item 数据表中的column name 的驼峰标识 如：create_date == createDate
	 * @param value 作为筛选条件的字段对应的 筛选值 如:name:男
	 * @param usePage 是否使用分页 1：使用分页 0：不使用分页 默认不使用分页
	 * @param asc 是否根据listOrder进行升序 默认是
	 * @param page 排序组件
	 * @return
	 * @Date 2015年3月31日
	 */
	@RequestMapping(value = "/cache/jcgc/condition")
	@ResponseBody
	public List<Map<String, Object>> findByCondition(
			@RequestParam("tc") String tableCode,
			@RequestParam("field") String field,
			@RequestParam("value") String value,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "asc", required = false, defaultValue = "true") String asc, 
			Page page) {
		List<Map<String, Object>> result = null;
		try {
			result = this.jcGcCacheService.findByCondition(tableCode, field, value, "1".equals(usePage) ? page : null, "true".equals(asc));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Method refresh
	 * @Function 功能描述：根据指定的tableCode以及分类中唯一的code 进行缓存刷新
	 * @param tableCode 分类代码 系统中基础代码的分类都有唯一的一个code 如 性别 对应的tableCode为 GB-XB
	 * @param value 每个分类下唯一的数据项code
	 * @return
	 * @Date 2015年3月31日
	 */
	@RequestMapping(value = "/cache/jcgc/refresh")
	public @ResponseBody boolean refresh(@RequestParam("tc") String tableCode,
			@RequestParam("value") String value) {
		return this.jcGcCacheService.refresh(tableCode, value);
	}
	
	
}
