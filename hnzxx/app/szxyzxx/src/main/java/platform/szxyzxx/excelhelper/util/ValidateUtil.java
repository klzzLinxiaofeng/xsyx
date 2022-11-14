package platform.szxyzxx.excelhelper.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
/**
 * java验证api工具类，因为项目默认导入的是hibernate validator的实现
 * @author chenjiaxin
 *
 */
public abstract class ValidateUtil {
	
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	/**
	 * 使用validator进行验证，如果验证不通过将会抛出ValidationException
	 * @param obj
	 */
	public static void validate(Object obj) {
		Set<ConstraintViolation<Object>> valResult=validator.validate(obj);
		if(valResult.size()==0)
			return;
		String errorMsg=buildErrorMsg(valResult);
		throw new ValidationException(errorMsg);
	}

	/**
	 * 根据验证结果，生成错误信息，如  用户名不能为空
	 * @param constraints
	 * @return
	 */
	private static String buildErrorMsg(Set<ConstraintViolation<Object>> constraints) {
		StringBuffer msg=new StringBuffer();
		for (ConstraintViolation<?> constraintViolation : constraints) {
			msg.append(constraintViolation.getMessage());
			msg.append("、");
		}
		//删除最后一个多余的"、"
		msg.deleteCharAt(msg.length()-1);
		return msg.toString();
	}
	

}
