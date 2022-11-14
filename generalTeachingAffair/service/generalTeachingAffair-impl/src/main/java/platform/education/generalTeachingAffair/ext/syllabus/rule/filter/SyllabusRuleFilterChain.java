package platform.education.generalTeachingAffair.ext.syllabus.rule.filter;

import java.util.ArrayList;
import java.util.List;

import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.DataCarrier;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.ResponseDataCarrier;

public class SyllabusRuleFilterChain<T> implements SyllabusRuleFilter<T> {
	
	private List<SyllabusRuleFilter<T>> rules = new ArrayList<SyllabusRuleFilter<T>>();

	public SyllabusRuleFilterChain<T> addFilter(SyllabusRuleFilter<T> filter) {
		rules.add(filter);
		return this;
	}
	
	public void setRules(List<SyllabusRuleFilter<T>> rules) {
		this.rules = rules;
	}
	
	/**
	 * 迭代规则过滤器 如果规则过滤器返回EXECUTE_NEXT_FILTER 
	 * 表示当前规则通过，继续验证下一个规则
	 * 当返回的值不为EXECUTE_NEXT_FILTER
	 * 则表明规则验证未通过，并且返回错误信息 
	 */
	@Override
	public ResponseDataCarrier doFilter(DataCarrier<T> dc) {
		ResponseDataCarrier responseData = null;
		for(SyllabusRuleFilter<T> rule : rules) {
			responseData = rule.doFilter(dc);
			String result = responseData.getInfo();
			if(!EXECUTE_NEXT_FILTER.equals(result)) {
				return responseData;
			}
		}
		return new ResponseDataCarrier(EXECUTE_SUC);
	}
}
