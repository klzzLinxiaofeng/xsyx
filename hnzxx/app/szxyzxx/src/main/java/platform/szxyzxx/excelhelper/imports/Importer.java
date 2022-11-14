package platform.szxyzxx.excelhelper.imports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;


/**
 * Excel导入器，根据文件名、文件、输入流创建
 * @author chenjiaxin
 *
 */
public interface Importer<T> {
	/**
	 * 将Excel文件输入流的第一个Sheet解析为对象集合
	 * @param is
	 * @return
	 */
	List<T> importBy(InputStream is);
	/**
	 * 将Sheet解析为对象集合
	 * @param sheet
	 * @return
	 */
	List<T> importBy(Sheet sheet);
	/**
	 * 将指定路径的Excel文件的第一个Sheet解析为对象集合
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 */
	List<T> importBy(String filePath) throws FileNotFoundException;
	/**
	 * 将指定的Excel文件对象的第一个Sheet解析为对象集合
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	List<T> importBy(File file) throws FileNotFoundException;
	/**
	 * 将Excel文件输入流指定索引的Sheet解析为对象集合
	 * @param is
	 * @param sheetIndex
	 * @return
	 */
	List<T> importBy(InputStream is, int sheetIndex);
	/**
	 * 将Excel文件输入流指定名字的Sheet解析为对象集合
	 * @param is
	 * @param sheetName
	 * @return
	 */
	List<T> importBy(InputStream is, String sheetName);
	
	
}
