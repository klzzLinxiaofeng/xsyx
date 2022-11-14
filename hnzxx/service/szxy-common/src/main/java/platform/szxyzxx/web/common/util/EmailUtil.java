package platform.szxyzxx.web.common.util;

import platform.szxyzxx.web.common.contants.SysEmailTemplate;
/**
 * <p>Title:EmailSender.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：用于发送邮件工具类
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class EmailUtil {
	
	public static boolean sendActivationCode(String activationCode, String to, String serverName, int port) {
		if(activationCode != null) {
			if(SysEmailTemplate.EMAIL_TEMPLATE_ENABLE) {
				String url = SysEmailTemplate.EMAIL_TEMPLATE_ACTIVATE_URL;
				url = url.replace("${host}", serverName).replace("${port}", String.valueOf(port)).replace("${token}", activationCode);
				String content = SysEmailTemplate.EMAIL_TEMPLATE_ACTIVATE_CONTENT;
				content = content.replace("${url}", url);
				new EmailSender(true, to, null, SysEmailTemplate.EMAIL_TEMPLATE_ACTIVATE_SUBJECT, content, null).start();
			}
			return true;
		}
		return false;
	}
	
	
	public static boolean sendFindPwdCode(String findPwdCode, String to) {
		if(findPwdCode != null) {
			if (SysEmailTemplate.EMAIL_TEMPLATE_ENABLE) {
				String content = SysEmailTemplate.EMAIL_TEMPLATE_FINDPWD_CONTENT;
				content = content.replace("${code}", findPwdCode);
				new EmailSender(true, to, null, SysEmailTemplate.EMAIL_TEMPLATE_FINDPWD_SUBJECT, content, null).start();
				return true;
			}
		} 
		return false;
	}
}
