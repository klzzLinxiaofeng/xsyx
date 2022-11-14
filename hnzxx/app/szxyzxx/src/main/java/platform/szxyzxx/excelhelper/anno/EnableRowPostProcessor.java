package platform.szxyzxx.excelhelper.anno;

import platform.szxyzxx.excelhelper.imports.RowPostProcessor;
import platform.szxyzxx.excelhelper.imports.impl.ValidationRowPostProcessor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * 指定后置处理器
 * @author chenjiaxin
 *
 */

@Retention(RUNTIME)
@Target(TYPE)
public @interface EnableRowPostProcessor {
	/**
	 * 后置处理器的Class
	 * @return
	 */
	Class<? extends RowPostProcessor<?>> value() default ValidationRowPostProcessor.class;

	
}
