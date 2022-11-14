package platform.education.rest.gc.service.vo;

import java.io.Serializable;

public class DesktopAppInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * App名称
	 */
	private String appName;
	/**
	 * app图标
	 */
	private String appIco;
	/**
	 * app下载地址
	 */
	private String downloAddr;
	/**
	 * app发布者名称
	 */
	private String publisher;
	/**
	 * app显示名称
	 */
	private String displayName;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppIco() {
		return appIco;
	}
	public void setAppIco(String appIco) {
		this.appIco = appIco;
	}
	public String getDownloAddr() {
		return downloAddr;
	}
	public void setDownloAddr(String downloAddr) {
		this.downloAddr = downloAddr;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
