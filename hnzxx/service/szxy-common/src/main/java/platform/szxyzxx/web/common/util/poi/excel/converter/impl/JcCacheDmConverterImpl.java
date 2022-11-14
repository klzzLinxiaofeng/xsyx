package platform.szxyzxx.web.common.util.poi.excel.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalcode.service.JcCacheService;
import framework.generic.facility.poi.excel.config.XmlPropertyParam;
import framework.generic.facility.poi.excel.contants.ExcelOperationType;
import framework.generic.facility.poi.excel.converter.DataCarrier;
import framework.generic.facility.poi.excel.converter.DmConverter;

public class JcCacheDmConverterImpl implements DmConverter {
	
	@Autowired
	@Qualifier("jcCacheService")
	private JcCacheService jcCacheService;
	
	@Override
	public Object convert(Object codeValue, String currentOperationType, DataCarrier dataCarrier) {
		XmlPropertyParam property = dataCarrier.getXmlPropertyParam();
		String tableName = property.getDmTableName();
		String paramName = property.getName(); 
		String echoField = property.getEchoField();
		String expr = dataCarrier.getExpr();
		String value = "";
		if(codeValue instanceof String) {
			value = (String) codeValue;
		} else if (codeValue instanceof Integer){
			value = String.valueOf((Integer) codeValue);
		} else if (codeValue instanceof Long) {
			value = String.valueOf((Long) codeValue);
		} else if (codeValue instanceof Short) {
			value = String.valueOf((Short) codeValue);
		} else if (codeValue instanceof Byte) {
			value = String.valueOf((Byte) codeValue);
		}
		if(ExcelOperationType.OPERATION_TYPE_IMPORT.equals(currentOperationType)) {
			String tmp = null;
			tmp = paramName;
			paramName = echoField;
			echoField = tmp;
		}
		if(expr != null) {
			expr = expr + ";" + paramName+"="+value;
		} else {
			if("".equals(value)) {
				return value;
			}
			expr = paramName+"="+value;
		}
		Object obj = this.jcCacheService.findUniqueByParam(tableName, expr, echoField);
		if(obj != null) {
			return obj;
		}
		return null;
	}
}
