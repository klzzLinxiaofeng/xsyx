package platform.education.commonResource.web.common.contants;

import platform.education.commonResource.web.common.util.FtlConfigAccessor;
/**
 * 读取配置文件Template.properties里的内容
 * @author 张海明
 *
 */
public class FtlContants {
	/**
	 * 模板文件存放的位置
	 */
	private final static String FTL_TEMPLATEPATH_KEY = "ftl.templateFilePath";
	public final static String FTL_TEMPLATEPATH = FtlConfigAccessor.getStringProp(FTL_TEMPLATEPATH_KEY);
	
	/**
	 * 模板文件资源的文件名
	 */
	private final static String FTL_TEMPLATERESOURCENAME_KEY = "ftl.templateResourceName";
	public final static String FTL_TEMPLATERESOURCENAME = FtlConfigAccessor.getStringProp(FTL_TEMPLATERESOURCENAME_KEY);
	/**
	 * 模板文件微课的文件名
	 */
	private final static String FTL_TEMPLATEMICRONAME_KEY = "ftl.templateMicroName";
	public final static String FTL_TEMPLATEMICRONAME = FtlConfigAccessor.getStringProp(FTL_TEMPLATEMICRONAME_KEY);
	
	/**
	 * 生成的html文件存放的位置
	 */
	private final static String FTL_HTMLPATH_KEY = "ftl.htmlFilePath";
	public final static String FTL_HTMLPATH = FtlConfigAccessor.getStringProp(FTL_HTMLPATH_KEY);
	   
	/**
	 * 访问html文件位置
	 */
	private final static String FTL_HTMLURL_KEY = "ftl.htmlUrl";
	public final static String FTL_HTMLURL = FtlConfigAccessor.getStringProp(FTL_HTMLURL_KEY);
	
	/**
	 * 访问html
	 */
	private final static String HTML_BASE_PATH_KEY = "html.base.path";
	public final static String HTML_BASE_PATH = FtlConfigAccessor.getStringProp(HTML_BASE_PATH_KEY);
}
