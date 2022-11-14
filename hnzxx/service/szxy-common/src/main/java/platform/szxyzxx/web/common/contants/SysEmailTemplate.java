package platform.szxyzxx.web.common.contants;

import platform.szxyzxx.web.common.util.SystemEmailTemplateAccesser;
/**
 * <p>Title:SysEmailTemplate.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：邮箱发送组件模板常量
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class SysEmailTemplate {
	
	/*模板是否启动*/
	private static final String EMAIL_TEMPLATE_ENABLE_KEY = "mail.template.enable";
	public static final boolean EMAIL_TEMPLATE_ENABLE = SystemEmailTemplateAccesser.getBooleanProp(EMAIL_TEMPLATE_ENABLE_KEY);
	
	/*邮件主题*/
	private static final String EMAIL_TEMPLATE_ACTIVATE_SUBJECT_KEY = "mail.template.activate.subject";
	public static final String EMAIL_TEMPLATE_ACTIVATE_SUBJECT = SystemEmailTemplateAccesser.getStringProp(EMAIL_TEMPLATE_ACTIVATE_SUBJECT_KEY);
	
	/*激活邮件内容模板*/
	private static final String EMAIL_TEMPLATE_ACTIVATE_CONTENT_KEY = "mail.template.activate.content";
	public static final String EMAIL_TEMPLATE_ACTIVATE_CONTENT = SystemEmailTemplateAccesser.getStringProp(EMAIL_TEMPLATE_ACTIVATE_CONTENT_KEY);
	
	/*邮件激活url*/
	private static final String EMAIL_TEMPLATE_ACTIVATE_URL_KEY = "mail.template.activate.url";
	public static final String EMAIL_TEMPLATE_ACTIVATE_URL = SystemEmailTemplateAccesser.getStringProp(EMAIL_TEMPLATE_ACTIVATE_URL_KEY);
	
	/****************************************************** 找回密码邮箱文本模板 **********************************************************************/
	
	private static final String EMAIL_TEMPLATE_FINDPWD_SUBJECT_KEY = "mail.template.findpwd.subject";
	public static final String EMAIL_TEMPLATE_FINDPWD_SUBJECT = SystemEmailTemplateAccesser.getStringProp(EMAIL_TEMPLATE_FINDPWD_SUBJECT_KEY);
	
	private static final String EMAIL_TEMPLATE_FINDPWD_CONTENT_KEY = "mail.template.findpwd.content";
	public static final String EMAIL_TEMPLATE_FINDPWD_CONTENT = SystemEmailTemplateAccesser.getStringProp(EMAIL_TEMPLATE_FINDPWD_CONTENT_KEY);
	
}
