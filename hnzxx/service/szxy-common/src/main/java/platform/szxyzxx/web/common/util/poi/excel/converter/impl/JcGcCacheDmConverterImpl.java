package platform.szxyzxx.web.common.util.poi.excel.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalcode.service.JcGcCacheService;
import framework.generic.facility.poi.excel.config.XmlPropertyParam;
import framework.generic.facility.poi.excel.contants.ExcelOperationType;
import framework.generic.facility.poi.excel.converter.DataCarrier;
import framework.generic.facility.poi.excel.converter.DmConverter;

public class JcGcCacheDmConverterImpl implements DmConverter {
	
	@Autowired
	@Qualifier("jcGcCacheService")
	private JcGcCacheService jcGcCacheService;

	@Override
	public Object convert(Object codeValue, String currentOperationType, DataCarrier dataCarrier) {
		XmlPropertyParam property = dataCarrier.getXmlPropertyParam();
		String tableCode = property.getDmTableName();
		String val = null;
		if(codeValue instanceof String) {
			val = (String) codeValue;
		} else if (codeValue instanceof Integer){
			val = String.valueOf((Integer) codeValue);
		} else if (codeValue instanceof Long) {
			val = String.valueOf((Long) codeValue);
		} else if (codeValue instanceof Short) {
			val = String.valueOf((Short) codeValue);
		} else if (codeValue instanceof Byte) {
			val = String.valueOf((Byte) codeValue);
		}
		if(ExcelOperationType.OPERATION_TYPE_IMPORT.equals(currentOperationType)) {
			return this.jcGcCacheService.getValueByName(tableCode, val);
			
		}
		return this.jcGcCacheService.getNameByValue(tableCode, val);
	}

}
