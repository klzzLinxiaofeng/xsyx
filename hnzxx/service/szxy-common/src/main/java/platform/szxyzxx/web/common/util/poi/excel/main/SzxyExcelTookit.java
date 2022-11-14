package platform.szxyzxx.web.common.util.poi.excel.main;

import framework.generic.facility.poi.excel.config.ParseConfig;
import framework.generic.facility.poi.excel.main.ExcelTookit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

public class SzxyExcelTookit {
	private final static Logger log = LoggerFactory.getLogger(SzxyExcelTookit.class);
	/**
	 * @Method getConfig
	 * @Function 功能描述：获取对excel进行解析的配置信息对象
	 * @param modelName
	 * @return
	 * @Date 2014年11月21日
	 */
	public static ParseConfig getConfig(String modelName) {
		return ExcelTookit.getConfig(modelName);
	}

	public static ParseConfig getConfig() {
		return ExcelTookit.getConfig();
	}

	/**
	 * @Method excelDataToModels
	 * @Function 功能描述：采用默认形式处理excel文档 适合解析普通简单的excel表格
	 * @param modelName
	 *            excelToModel.xml 中 实体对象对应的model标签id
	 * @param excelPath
	 *            要进行解析的excel文件绝对路径
	 * @return
	 * @Date 2014年11月21日
	 */
	public static List<Object> excelDataToModels(String modelName, String excelPath) {
		return ExcelTookit.excelDataToModels(modelName, excelPath);
	}
	
	/**
	 * @Method excelDataToModels
	 * @Function 功能描述：可根据ParseConfig的配置对excel进行较灵活的解析
	 * @param config
	 *            配置对象 可配置标题栏的位置、开始与结束、数据的范围
	 * @param excelPath
	 * @return
	 * @Date 2014年11月21日
	 */
	public static List<Object> excelDataToModels(ParseConfig config, String excelPath) {
		return ExcelTookit.excelDataToModels(config, excelPath);
	}
	
	/**
	 * @Method excelDataToModels
	 * @Function 功能描述：采用默认形式处理excel文档 适合解析普通简单的excel表格
	 * @param modelName excelToModel.xml 中 实体对象对应的model标签id
	 * @param is
	 * @param suffix
	 * @return
	 * @Date 2015年4月10日
	 */
	public static List<Object> excelDataToModels(String modelName, InputStream is, String suffix) {
		return ExcelTookit.excelDataToModels(modelName, is, suffix);
	}
	
	/**
	 * @Method excelDataToModels
	 * @Function 功能描述：可根据ParseConfig的配置对excel进行较灵活的解析
	 * @param config 配置对象 可配置标题栏的位置、开始与结束、数据的范围
	 * @param is
	 * @param suffix
	 * @return
	 * @Date 2015年4月10日
	 */
	public static List<Object> excelDataToModels(ParseConfig config, InputStream is, String suffix) {
		return ExcelTookit.excelDataToModels(config, is, suffix);
	}
	
	
	/**
	 * @Method exportExcel
	 * @Function 功能描述：excel导出功能
	 * @param
	 *
	 * @param
	 *
	 * @param dataset
	 *            需要导出的数据
	 * @param config
	 * @param excelPath
	 * @return
	 * @Date 2014年11月24日
	 */
	public static void exportExcel(List<Object> dataset, ParseConfig config, String excelPath) {
		ExcelTookit.exportExcel(dataset, config, excelPath);
	}

	public static void exportExcelToWEB(List<Object> dataset,
			ParseConfig config, HttpServletRequest request,
			HttpServletResponse response, String fileName)
			throws UnsupportedEncodingException {
		//response.setContentType("octets/stream");
		response.setContentType("application/msexcel");
		response.setCharacterEncoding("UTF-8");
		// codes..
		String name = fileName;
		String userAgent = request.getHeader("User-Agent");
		//byte[] bytes = userAgent.contains("MSIE") ? name.getBytes() : name.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
		//name = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
		//response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", name)); // 文件名外的双引号处理firefox的空格截断问题
		response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(name, "UTF-8"));
		if (fileName == null) {
			fileName = "数字校园数据导出.xls";
		}
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			ExcelTookit.exportExcel(dataset, config, out);
			if (log.isInfoEnabled()) {
				log.info("excel导出成功！");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
