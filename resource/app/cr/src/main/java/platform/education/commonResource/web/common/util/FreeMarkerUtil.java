package platform.education.commonResource.web.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {
	/**
	 * 生成静态页面主方法
	 * 
	 * @param context
	 *            ServletContext
	 * @param data
	 *            一个Map的数据结果集
	 * @param templatePath
	 *            ftl模版路径
	 * @param targetHtmlPath
	 *            生成静态页面的路径
	 */
	public static void crateHTML(ServletContext context, Map<String, Object> data, String templatePath,
			String targetHtmlPath) {
		Configuration freemarkerCfg = new Configuration();
		// 加载模版
		freemarkerCfg.setServletContextForTemplateLoading(context, File.separator + "ftl");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		Writer out = null;
		try {
			// 指定模版路径
			Template template = freemarkerCfg.getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");
			// 静态页面路径
			String htmlPath = context.getRealPath(File.separator + "html") + File.separator + targetHtmlPath;
			File htmlFile = new File(htmlPath);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版
			template.process(data, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.flush();
					out.close();
					out = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
