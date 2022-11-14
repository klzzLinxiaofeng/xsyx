package platform.szxyzxx.excelhelper.imports.impl;

import platform.szxyzxx.excelhelper.imports.RowPostProcessor;
import platform.szxyzxx.excelhelper.util.ValidateUtil;

import javax.validation.ValidationException;
/**
 * 使用hibernate的validator实现进行验证
 * @author chenjiaxin
 *
 */
public class ValidationRowPostProcessor implements RowPostProcessor<Object> {

	@Override
	public void doPost(Object pojo) throws ValidationException {
		try {
			ValidateUtil.validate(pojo);
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}

}
