package platform.szxyzxx.excelhelper.anno;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import platform.szxyzxx.excelhelper.constants.CellValueContants;
/**
 * 代表此属性对应Excel文件的一列
 * @author chenjiaxin
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelColumnImport {
	/**
	 * 属性对应的Excel文件列名，多个名字之间是或者的关系
	 * @return
	 */
	String[] value() default "";
	/**
	 * 属性对应的Excel文件列的索引，设置后将忽略value数组
	 * @return
	 */
	int index() default -1;
	/**
	 * 将字符串解析为Date对象时使用的格式化字符串
	 * @return
	 */
	String dateFormatStr() default "";
	/**
	 * 读取时，如果单元格的值在这个值数组中存在，将会跳过该行数据，比较其实就是和单元格个值的toString()方法的值进行比较</br>
	 * 对于整数，因为读取出来是Double,所以要比较的话需要加上小数".0"。空单元格CellValueContants.BLANK表示。
	 * @return
	 */
	String[] ignoreValue() default CellValueContants.DEFAULT;
	/**
	 * 读取时，如果单元格的值在这个值数组中存在，将会立即停止读取后面行的数据，包括当前行数据也会被忽略。如果导出时有指定endRowIndex,那么将会忽略该注解。比较其实就是和单元格个值的toString()方法的值进行比较</br>
	 * 对于整数，因为读取出来是Double,所以要比较的话需要加上小数".0"。空单元格(null 或者value是blank类型，或者等于"")CellValueContants.BLANK表示。
	 * @return
	 */
	String[] endValue() default CellValueContants.DEFAULT;
}
