package platform.szxyzxx.web.common.contants;

import platform.szxyzxx.web.common.util.SystemCacheHttpConfigAccessor;
/**
 * <p>Title:SysCacheHttpConfig.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统 缓存 http请求访问接口
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年8月12日
 */
public class SysCacheHttpUrlAccessor {
	
	private final static String CACHE_JC_FINDBYPARAM_URL_KEY = "cache.jc.findbyparam.url";
	/**
	 * 获取指定数据库表中指定参数作为筛选条件获取 数据列表
	 * http://ip:port/ehcahe/findByParam?th=xxxx&pn=xxx&value=xxx
	 * 参数为tn 表名
	 * pn 参数名
	 * value 参数值
	 */
	public final static String CACHE_JC_FINDBYPARAM_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JC_FINDBYPARAM_URL_KEY);
	
	
	
	
	private final static String CACHE_JC_FINDALL_URL_KEY = "cache.jc.findall.url";
	
	/**
	 * 获取指定数据库表中所有数据
	 * http://ip:port/ehcahe/findAll?th=xxxx
	 * 参数为tn 表名
	 */
	public final static String CACHE_JC_FINDALL_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JC_FINDALL_URL_KEY);
	
	
	
	
	private final static String CACHE_JC_FINDBYJSON_URL_KEY = "cahce.jc.findByJson.url";
	/**
	 * http://ip:port/echache/findByJson
	 */
	public final static String CACHE_JC_FINDBYJSON_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JC_FINDBYJSON_URL_KEY);
	
	
	
	
	
	private final static String CACHE_JC_FINDBYCONDITION_URL_KEY = "cache.jc.findByCondition.url";
	/**
	 * 根据多条件获取缓存中的数据
	 * http://ip:port/ehcache/findByCondition
	 * post请求
	 * tn 表名
	 * pm 参数表达式
	 * param1=value;param2=value 
	 */
	public final static String CACHE_JC_FINDBYCONDITION_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JC_FINDBYCONDITION_URL_KEY);
	
	
	
	
	private final static String CACHE_JC_REFRESH_URL_KEY = "cache.jc.refresh.url";
	/**
	 * 根据ID 刷新某张代码表在缓冲中的记录！
	 * tn 表名
	 * id 记录主键值
	 */
	public final static String CACHE_JC_REFRESH_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JC_REFRESH_URL_KEY);
	
	
	
	
	private final static String CACHE_JCGC_ITEMS_URL_KEY = "cache.jcgc.items.url";
	/**
	 * http://ip:port/cache/jcgc/items
	 * tc : tableCode ====> table_code
	 * level
	 * asc
	 * usePage
	 * page
	 */
	public final static String CACHE_JCGC_ITEMS_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JCGC_ITEMS_URL_KEY);
	
	private final static String CACHE_JCGC_CONDITION_URL_KEY = "cache.jcgc.condition.url";
	/**
	 * http://ip:port/cache/jcgc/condition
	 * tc : tableCode ===> table_code
	 * asc : 是否升序 true/false 
	 * usePage 是否使用分页 1/0 === > true/false
	 * page.pageSize  每页个数
	 * page.currentPage 当前页数
	 */
	public final static String CACHE_JCGC_CONDITION_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JCGC_CONDITION_URL_KEY);
	
	
	private final static String CACHE_JCGC_REFRESH_URL_KEY = "cahce.jcgc.refresh.url";
	/**
	 * http://ip:port/cache/jcgc/refresh
	 * tc
	 * value
	 */
	public final static String CACHE_JCGC_REFRESH_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JCGC_REFRESH_URL_KEY);
	
	
	private final static String CACHE_JCGC_ALL_URL_KEY = "cache.jcgc.all.url";
	/**
	 * http://ip:port/cache/jcgc/all
	 */
	public final static String CACHE_JCGC_ALL_URL = SystemCacheHttpConfigAccessor.getStringProp(CACHE_JCGC_ALL_URL_KEY);
	
	
	
}
