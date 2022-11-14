package platform.education.generalTeachingAffair.ext.syllabus.rule.filter;

import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.DataCarrier;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.ResponseDataCarrier;
/**
 * <p>Title:KbRuleFilter.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：排课规则过滤器接口
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年10月15日
 */
public interface SyllabusRuleFilter<T> {
	
	/**
	 * 允许执行下一个规则过滤器
	 */
	public final static String EXECUTE_NEXT_FILTER = "enf";
	
	/**
	 * 执行结果正确
	 */
	public final static String EXECUTE_SUC = "success";
	
	public ResponseDataCarrier doFilter(DataCarrier<T> dc);
	
}
