package platform.education.rest.bp.service.contants;

public class BpSignageConstants {
	
	/**
	 * 班牌默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";
	
	/*** 班牌类型 班牌 */
	public static final String SIGNAGE_TYPE_BP = "BP";
	/*** 班牌类型 广告机 */
	public static final String SIGNAGE_TYPE_AD = "AD";
	
	/**
	 * 开启
	 */
	public static final Integer WORKING_STATE_OPEN = 0;
	/**
	 * 关闭
	 */
	public static final Integer WORKING_STATE_CLOSE = 1;
	/**
	 * 故障
	 */
	public static final Integer WORKING_STATE_FAULT = 2;
	
		
	/**
	 * 正常
	 */
	public static final Integer USING_STATE_OPEN = 0;
	/**
	 * 报废
	 */
	public static final Integer USING_STATE_CLOSE = 1;
	/**
	 * 故障
	 */
	public static final Integer USING_STATE_FAULT = 2;
}
