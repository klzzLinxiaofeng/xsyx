package platform.education.rest.common.constants;

/**
 * 常用错误代码,若需添加，请自行添加
 * 
 * 命名规则：
 * 	模块首字母+$+错误类型
 *     
 * @see 迅云 OpenAPI 开发规范
 * @author hmzhang 2016/07/23
 * @version 数字校园V3.0
 *
 */
public class CommonCode {
	
	/////////////////////////////////////////////系统级别//////////////////////////////////////////
	//																							//
	//											01												//
	//																							//
	/////////////////////////////////////////////系统级别//////////////////////////////////////////
	/**
	 * 未知错误
	 */
	public static final String S$UNKNOWN_ERROR = "000001";
	
	/**
	 * 服务器忙
	 */
	public static final String S$SERVER_BUSY = "000002";
	
	/**
	 * 服务器无响应
	 */
	public static final String S$NO_RESPONSE = "000003";
	
	/**
	 * 操作超时
	 */
	public static final String S$TIME_OUT = "000004";
	
	/**
	 * 网络异常
	 */
	public static final String S$NETWORK_ANOMALY = "000005";
	
	/**
	 * IP 地址无效或不匹配
	 */
	public static final String S$INVALID_IP = "000006";
	
	/**
	 * 数据无效
	 */
	public static final String S$INVALID_DATA = "000007";
	
	/**
	 * 操作异常
	 */
	public static final String S$ABNORMAL_OPERATION = "000008";
	
	
	
	
	/////////////////////////////////////////////数据层级//////////////////////////////////////////
	//																							//
	//											   02											//
	//																							//
	/////////////////////////////////////////////数据层级//////////////////////////////////////////
	/**
	 * 数据库操作异常
	 */
	public static final String D$DB_OPERATION_EXCEPTION = "020001";
	
	/**
	 * 数据库连接失败
	 */
	public static final String D$DB_CONNECTION_FAILED = "020002";
	
	/**
	 * 找不到数据 (指查询没有匹配的数据)
	 */
	public static final String D$DATA_NOT_FOUND = "020101";
	
	/**
	 * 数据不存在或已删除 (通常是修改或删除业务)
	 */
	public static final String D$DATA_NOT_EXIST  = "020102";
	
	
	/////////////////////////////////////////////用户和权限////////////////////////////////////////
	//																							//
	//											   03											//
	//																							//
	/////////////////////////////////////////////用户和权限////////////////////////////////////////
	
	
	
	/**
	 * 用户名不存在
	 */
	public static final String U$USERNAME_NOT_EXIST= "031001";
	
	/**
	 * 用户名不存在
	 */
	public static final String SUCCESS= "0000";
	
	/**
	 * 密码错误
	 */
	public static final String U$PASSWORD_ERROR = "031002";
	
	/**
	 * 用户账号已经过期
	 */
	public static final String U$USERACCOUNT_HAS_EXPIRED = "031005";
	
	/**
	 * 用户账号已失效
	 */
	public static final String U$USERACCOUNT_NOT_IN_USE  = "031006";
	
	/**
	 * 用户账号已锁定
	 */
	public static final String U$USERACCOUNT_LOCKED  = "031004";
	
	/**
	 * 用户不存在
	 */
	public static final String U$USER_NOT_EXIST= "031007";
	
	
	
	/////////////////////////////////////////////接口方面异常////////////////////////////////////////////
	
	/**
	 * 接口不存在
	 */
	public static final String $INTERFACE_DOES_NOT_EXIST = "060101";
	
	/**
	 * 接口已过期或已禁用
	 */
	public static final String $INTERFACE_IS_EXPIRED = "060102";
	
	/**
	 * 接口调用发生异常
	 */
	public static final String $ABNORMAL_INTERFACE_CALL = "060103";
	
	/**
	 * 参数解释失败(可能是非法字符、非法编码或数据格式不完整)
	 */
	public static final String $FAILED_TO_INTERPRET_PARAMETERS = "060110";
	
	/**
	 * 必填参数为空
	 */
	public static final String $REQUIRED_PARAMETER_IS_NULL = "060111";
	
	/**
	 * 参数格式错误
	 */
	public static final String $PARAMETER_FORMAT_ERROR = "060112";
	
	/**
	 * 参数值内容错误
	 */
	public static final String $PARAMETER_VALUE_CONTENT_ERROR = "060113";
	
	/**
	 * 参数长度超出范围
	 */
	public static final String $PARAMETER_LENGTH_OUT_OF_RANGE = "060114";
	
	/**
	 * 批量导入时发生错误
	 */
	public static final String $ERROR_IN_BULK_IMPORT = "060115";

	/**
	 * 手机号码已注册
	 */
	public static final String $MOBILE_NUMBER_HAS_REGISTERED = "060116";

	
	/////////////////////////////////////////////文件、网盘类////////////////////////////////////////////
	/**
	 * 文件或目录不存在
	 */
	public static final String $FILE_IS_NOT_EXIST = "511001";
	
	/**
	 * 文件或目录操作异常
	 */
	public static final String $FILE_OPERATION_EXCEPTION = "511002";
	
	/**
	 * 文件或目录拒绝访问
	 */
	public static final String $FILE_DENIED_ACCESS = "511003";
	
	/**
	 * 文件或目录无法打开或已损坏
	 */
	public static final String $FILE_CANNOT_BE_OPENED = "511004";

	/**
	 * 文件上传失败
	 */
	public static final String $FILE_UPLOAD_FAILED = "511005";
	
	/**
	 * 网盘空间不存在
	 */
	public static final String $SPACE_DOES_NOT_EXIST = "512001";
	
	/**
	 * 网盘登录密码错误
	 */
	public static final String $SKYDRIVE_PASSWORD_ERROR = "512002";
	
	/**
	 * 网盘已关闭
	 */
	public static final String $SKYDRIVE_HAS_BEEN_SHUT_DOWN = "512003";
	
	/**
	 * 网盘已禁用
	 */
	public static final String $SKYDRIVE_HAS_BEEN_DISABLED = "512004";
	
	/**
	 * 网盘已过期或失效
	 */
	public static final String $SKYDRIVE_HAS_EXPIRED = "512005";
	
	/**
	 * 网盘空间不足
	 */
	public static final String $SKYDRIVE_SPACE_NOT_ENOUGH = "512006";
	
	/**
	 * 已超过接口允许调用有效期
	 */
	public static final String $EXPIRED_DATE = "512007";
	/**
	 * 数字签名不对
	 */
	public static final String $SIGNATURE_IS_NOT_CORRECT = "512008";
	/**
	 * 参数不完整
	 */
	public static final String $INCOMPLETE_PARAMETER = "512009";
	
	
	/**
	 * 该用户下没有班级，请联系管理员!
	 */
	public static final String $INCOMPLETE_NOT_TEAM = "512010";
	
	
	
	
}
