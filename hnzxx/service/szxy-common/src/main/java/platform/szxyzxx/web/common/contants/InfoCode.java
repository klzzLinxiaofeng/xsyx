package platform.szxyzxx.web.common.contants;

/**
 * 用户打操作状态码
 * @author onsoul
 *
 */
public  final class InfoCode {
	public static final String CURRENT_USER = "SZXY_CURRENT_USER";
	public static final String L$ERROR  	= "1000";	//登陆出现错误
	public static final String L$EXE 		= "1001";	//登陆出现异常
	public static final String L$ACCEPT 	= "1002";	//登陆成功
	public static final String L$PWD 		= "1003";   //密码错误
	public static final String L$ACCOUNT 	= "1004";	//账号错识
	public static final String L$DEFER 		= "1005";	//网络延迟
	public static final String L$IDENTIFY 	= "1006";   //验证码错误
	public static final String R$LAYOUT     = "2001";   //注册信息格式错误

}
