package platform.szxyzxx.excelhelper.imports;

/**
 * 表格行解析为对象后的后置处理器，可以修改属性，校验数据等，可抛异常
 * @param <T>
 */
public interface RowPostProcessor<T> {

	void doPost(T pojo);
}
