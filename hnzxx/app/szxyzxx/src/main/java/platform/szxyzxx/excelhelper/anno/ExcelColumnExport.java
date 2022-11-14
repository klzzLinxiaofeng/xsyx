package platform.szxyzxx.excelhelper.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 列级别的的导出配置，将pojo的某个属性映射为Excel文件的一列
 * 
 * @author chenjiaxin
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelColumnExport{
	/**
	 * 标题行的列名
	 * 
	 * @return
	 */
	String value();
	
	/**
	 * 列的宽度
	 * 
	 */
	int width() default -1;

	/**
	 * 列排序时的数字，从小到大进行排序
	 * 
	 * @return
	 */
	int orderNum() default 0;

	/**
	 * 导出时对pojo的值进行替换，如将true替换为是，false替换为否，则可以这样写{"true=是,"false=否"}
	 * 
	 * @return
	 */
	String[] replace() default "";

	/**
	 * 将java.util.Date对象按照格式字符串转换成相应格式的字符串
	 * 
	 * @return
	 */
	String dateFormat() default "";

	/**
	 * 追加后缀，只限String类型
	 * 
	 * @return
	 */
	String suffix() default "";

	/**
	 * 是否是公式值，默认否，需要确保属性是String类型。 例如此列公式是 age*height,格式就是SUM(age*height)。age和height对应属性名</br>
	 * 这里其实可以是任何公式,导出的时候会将属性替换为相应的列名
	 * @return
	 */
	boolean isFormula() default false;

	
}
