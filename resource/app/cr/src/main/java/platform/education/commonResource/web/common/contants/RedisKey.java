package platform.education.commonResource.web.common.contants;

import platform.education.commonResource.web.common.util.RedisKeyAccessor;


public class RedisKey {
	private final static String CR_SCORE_ORDER_KEY = "cr.score.order";
	public  static String CR_SCORE_ORDER ;

	static{
		try {
			CR_SCORE_ORDER=RedisKeyAccessor.getStringProp(CR_SCORE_ORDER_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
