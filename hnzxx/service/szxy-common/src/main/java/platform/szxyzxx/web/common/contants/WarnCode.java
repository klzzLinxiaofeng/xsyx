package platform.szxyzxx.web.common.contants;

/**
 * 
 * <p>Title:WarnCode.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统操作代码
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月25日
 */
public  final class WarnCode {
	
	public static final String L$ERROR  	= "1000";	//登陆出现错误
	public static final String L$EXE 		= "1001";	//登陆出现异常
	public static final String L$ACCEPT 	= "1002";	//登陆成功
	public static final String L$PWD 		= "1003";   //密码错误
	public static final String L$ACCOUNT 	= "1004";	//账号无存在
	public static final String L$403 	    = "403";	//账号无存在
	public static final String L$DEFER 		= "1005";	//网络延迟
	public static final String L$IDENTIFY 	= "1006";   //验证码错误
	public static final String R$LAYOUT     = "2001";   //注册信息格式错误
	
	public static final String ACCOUNT_DISABLE ="2002"; //账号被禁用
	public static final String ACCOUNT_INACTIVE  = "2003"; //账号未激活
	public static final String ACCOUNT_OVERDUE = "2004"; //账号过期
	
	public static final String INFO$ERROR   = "4004";

}
