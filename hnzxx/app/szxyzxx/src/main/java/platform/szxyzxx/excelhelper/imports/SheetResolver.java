
package platform.szxyzxx.excelhelper.imports;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
/**
 * Excel文件Sheet解析器，用于将一个Sheet解析为相应的对象集合
 * @author chenjiaxin
 *
 * @param <T>解析为什么类型对象
 */
public interface SheetResolver<T> {
	/**
	 * 解析Sheet为List对象集合
	 * @return
	 */
	List<T> resovle(Sheet sheet);
}
