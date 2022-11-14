package platform.szxyzxx.web.common.util;

import java.util.List;

import platform.szxyzxx.web.common.util.holder.JavaEmailServiceHolder;
import framework.generic.facility.email.JavaEmailService;
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
public class EmailSender extends Thread {

	private boolean useHtml;

	private String to;
	private String cc;
	private String subject;
	private String content;
	private List<String> attachFileList;

	public EmailSender() {

	}

	public EmailSender(boolean useHtml, String to, String cc, String subject,
			String content, List<String> attachFileList) {
		this.useHtml = useHtml;
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.content = content;
		this.attachFileList = attachFileList;
	}

	@Override
	public void run() {
		JavaEmailService javaEmailService = JavaEmailServiceHolder
				.getInstance().getJavaEmailService();
		if (useHtml) {
			javaEmailService.sendMailHtml(to, cc, subject, content,
					attachFileList);
		} else {
			javaEmailService.sendMailText(to, cc, subject, content,
					attachFileList);
		}
	}

	public boolean isUseHtml() {
		return useHtml;
	}

	public void setUseHtml(boolean useHtml) {
		this.useHtml = useHtml;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getAttachFileList() {
		return attachFileList;
	}

	public void setAttachFileList(List<String> attachFileList) {
		this.attachFileList = attachFileList;
	}

}
