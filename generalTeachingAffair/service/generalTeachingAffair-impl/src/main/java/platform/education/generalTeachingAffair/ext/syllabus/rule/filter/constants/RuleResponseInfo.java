package platform.education.generalTeachingAffair.ext.syllabus.rule.filter.constants;

public class RuleResponseInfo {
	
	/**
	 * 在同个学期下，一个教师不能在同一天同一节课给不同班上课（同个时段)
	 */
	public final static String RULE_ONE = "1";
	
	/**
	 * 在同个学期下，多个教师不能给同个班在同个星期同节课上课，除非是不同星期
	 */
	public final static String RULE_TWO = "2";
	
}
