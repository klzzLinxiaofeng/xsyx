package platform.szxyzxx.web.bbx.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.facility.poi.excel.config.ParseConfig;
import framework.generic.facility.poi.excel.config.XmlPropertyParam;
import framework.generic.facility.poi.excel.converter.DataCarrier;
import framework.generic.facility.poi.excel.converter.DmConverter;
import framework.generic.facility.poi.excel.converter.factory.ConverterFactory;
import framework.generic.facility.poi.excel.utils.ExcelReaderTookit;
import framework.generic.facility.poi.excel.utils.MyBeanUtils;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;

public class SzxyExcelTookitSub {
	
	@Autowired
	@Qualifier("converterFactory")
	private static ConverterFactory converterFactory;
	
	private final static Logger log = LoggerFactory.getLogger(SzxyExcelTookit.class);
	
	public static void exportExcelToWEB(Workbook workBook, HttpServletRequest request,
			HttpServletResponse response, String fileName)
			throws UnsupportedEncodingException {
		response.setContentType("octets/stream");
		response.setCharacterEncoding("UTF-8");
		// codes..
		String name = fileName;
		String userAgent = request.getHeader("User-Agent");
		
		boolean conditon1 = userAgent.toLowerCase().contains("msie");
		boolean conditon2 = userAgent.toLowerCase().contains("windows") && userAgent.toLowerCase().contains("like gecko");
		if ( conditon1 || conditon2 ) {
			name = URLEncoder.encode(fileName, "UTF-8");
		} else {
			name = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		}
		response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", name)); // 文件名外的双引号处理firefox的空格截断问题
		if (fileName == null) {
			fileName = "数字校园数据导出.xls";
		}
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			exportExcel(workBook, out);
			if (log.isInfoEnabled()) {
				log.info("excel导出成功！");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void exportExcel(Workbook workBook, OutputStream out) {
		try {
			workBook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes"})
	public static Workbook generateWorkbook(List<Object> dataset, ParseConfig config, Workbook workbook) {

		HSSFSheet sheet = (HSSFSheet) workbook.createSheet(config.getSheetTitle());

		CellStyle titleStyle = config.getTitleStyle();
		titleStyle = (titleStyle != null) ? titleStyle : ExcelReaderTookit.getTitleStyle(workbook);

		CellStyle dataStyle = config.getDataStyle();
		dataStyle = (dataStyle != null) ? dataStyle : ExcelReaderTookit.getDataStyle(workbook);

		Integer titleRow = config.getTitleRow();
		HSSFRow row = sheet.createRow(titleRow.intValue());
		String[] titles = config.getTitles();
		String[] fieldNames = config.getFieldNames();
		boolean isNotUseConfig = false;
		if (fieldNames == null) {
			fieldNames = new String[titles.length];
			isNotUseConfig = true;
		} else {
			isNotUseConfig = false;
		}
		XmlPropertyParam[] xmlProperties = new XmlPropertyParam[titles.length];
		Map map = config.getPropertyMap();
		for (int i = config.getTitleStartCol().intValue(); i < titles.length; ++i) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(titleStyle);
			HSSFRichTextString text = new HSSFRichTextString(titles[i]);
			cell.setCellValue(text);
			if (map != null) {
				XmlPropertyParam propertyBean = (XmlPropertyParam) map.get(titles[i]);
				if (propertyBean != null) {
					if (isNotUseConfig) {
						String fieldName = propertyBean.getName();
						fieldNames[i] = fieldName;
					}
					xmlProperties[i] = propertyBean;
				}
			}
		}

		Iterator it = dataset.iterator();
		int index = titleRow.intValue();
		while (it.hasNext()) {
			++index;
			row = sheet.createRow(index);
			Object t = it.next();

			if (fieldNames != null) {
				for (int i = config.getTitleStartCol().intValue(); i < fieldNames.length; ++i) {
					setCellValue(t, config, fieldNames[i], xmlProperties[i], row, sheet, i, index, workbook, dataStyle);
				}
			}

		}

		return workbook;
	}
	
	@SuppressWarnings("rawtypes")
	private static void setCellValue(Object t, ParseConfig config, String fieldName, XmlPropertyParam propertyBean,
			HSSFRow row, HSSFSheet sheet, int i, int index, Workbook workbook, CellStyle dataStyle) {
		HSSFCell cell = row.createCell(i);
		cell.setCellStyle(dataStyle);
		Object value = null;
		if (value == null) {
			if (t instanceof Map)
				value = ((Map) t).get(fieldName);
			else {
				value = MyBeanUtils.getFieldValue(t, fieldName);
			}
		}

		String textValue = null;
		String dataFormat = null;
		if (propertyBean != null) {
			dataFormat = propertyBean.getDataFormat();
			DataCarrier carrier = new DataCarrier();
			carrier.setCodeWithValueMaps(config.getCodeWithValueMaps());
			carrier.setXmlPropertyParam(propertyBean);
			if ((value != null) && (propertyBean.isUseConvert())) {
				String convertType = propertyBean.getConvertType();
				DmConverter converter = converterFactory.getConverter(convertType);

				carrier.setXmlPropertyParam(propertyBean);
				value = converter.convert(value, "export", carrier);
			}

		}

		if (value instanceof Date) {
			Date date = (Date) value;
			dataFormat = (dataFormat != null) ? dataFormat : "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
			textValue = sdf.format(date);
		} else if (value instanceof byte[]) {
			row.setHeightInPoints(60.0F);

			sheet.setColumnWidth(i, 2856);

			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

			byte[] bsValue = (byte[]) (byte[]) value;
			//   int dx1, int dy1, int dx2, int dy2, short col1, int row1, short col2, int row2 
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short)6, index, (short)6, index);
			anchor.setAnchorType(2);
			patriarch.createPicture(anchor, workbook.addPicture(bsValue, 5));
		} else {
			textValue = (value != null) ? value.toString() : "";
		}

		if (textValue != null) {
			Pattern p = Pattern.compile("^//d+(//.//d+)?$");
			Matcher matcher = p.matcher(textValue);
			if (matcher.matches()) {
				cell.setCellValue(Double.parseDouble(textValue));
			} else {
				HSSFRichTextString richString = new HSSFRichTextString(textValue);
				HSSFFont font3 = (HSSFFont) workbook.createFont();
				font3.setColor((short)12);
				richString.applyFont(font3);
				cell.setCellValue(richString);
				cell.setCellType(1);
			}
		}
	}
	
}
