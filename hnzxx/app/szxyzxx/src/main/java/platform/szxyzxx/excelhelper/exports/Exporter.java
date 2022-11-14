package platform.szxyzxx.excelhelper.exports;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Excel导出器
 * @author chenjiaxin
 *
 * @param <T>
 */
public interface Exporter<T> {
	/**
	 * 创建Workbook实例，并将对象集合导出到一个新的Sheet中
	 * 
	 * @param exportDatas
	 * @return
	 */
	Workbook exportToNew(List<T> exportDatas);

	/**
	 * 将对象集合导出到Excel文件输入流的一个新Sheet中
	 * @param is Excel文件的输入流
	 * @param exportDatas 导出的对象集合
	 * @return 添加了一个新Sheet的Workbook实例
	 */
	Workbook exportToStreamSheet(InputStream is, List<T> exportDatas) throws EncryptedDocumentException, IOException, InvalidFormatException;
	/**
	 * 将对象集合导出到指定的Workbook的一个新Sheet中
	 * @param wb
	 * @param exportDatas
	 */
	void exportToWorkbook(Workbook wb, List<T> exportDatas);
	/**
	 * 将对象集合导出到Excel文件输入流指定的Sheet中，改existSheetIndex必须存在
	 * @param is
	 * @param existSheetIndex
	 * @param exportDatas
	 * @return
	 */
	Workbook exportToExistSheet(InputStream is, int existSheetIndex, int titleRowIndex, List<T> exportDatas) throws EncryptedDocumentException, IOException, InvalidFormatException;
	
	/**
	 * 将对象集合导出到Excel文件输入流指定的Sheet中，改existSheetIndex必须存在,默认第行是标题行
	 * @param is
	 * @param existSheetIndex
	 * @param exportDatas
	 * @return
	 */
	Workbook exportToExistSheet(InputStream is, int existSheetIndex, List<T> exportDatas) throws EncryptedDocumentException, IOException, InvalidFormatException;
	
	
	/**
	 * 将对象集合追加到已经存在的Sheet中，并指定标题行
	 *
	 */
	void exportToExistSheet(Sheet existSheet, int titleRowIndex, List<T> exportDatas);
	
	/**
	 * 将对象集合追加到已经存在的Sheet中，默认第0行为标题行
	 * @param existSheet
	 * @param exportDatas
	 */
	void exportToExistSheet(Sheet existSheet, List<T> exportDatas);
}
