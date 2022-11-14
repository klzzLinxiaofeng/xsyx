package platform.szxyzxx.excelhelper.imports.impl;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.anno.EnableRowPostProcessor;
import platform.szxyzxx.excelhelper.constants.CellValueContants;
import platform.szxyzxx.excelhelper.convert.TypeConvertManager;
import platform.szxyzxx.excelhelper.exception.BreakRowException;
import platform.szxyzxx.excelhelper.exception.CellResolveException;
import platform.szxyzxx.excelhelper.exception.IgnoreRowException;
import platform.szxyzxx.excelhelper.exception.RowResolveException;
import platform.szxyzxx.excelhelper.imports.CellProcessController;
import platform.szxyzxx.excelhelper.imports.RowPostProcessor;
import platform.szxyzxx.excelhelper.imports.RowResolver;
import platform.szxyzxx.excelhelper.imports.SheetResolver;
import platform.szxyzxx.excelhelper.util.AssertUtil;
import platform.szxyzxx.excelhelper.util.ExcelUtil;
import platform.szxyzxx.excelhelper.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 单行表格解析器，对Sheet中的行进行单行解析。通过标题行或者开始行，使用行解析器，一行一行的将Row解析为对象
 * 
 * @author chenjiaxin
 *
 * @param <T>
 */
public class SingleRowSheetResolver<T> implements SheetResolver<T> {

	/**
	 * 指定读取的开始行索引，从0开始
	 */
	private Integer startRowIndex;
	/**
	 * 指定读取的结束行索引，从0开始
	 */
	private Integer endRowIndex;

	/**
	 * 解析的类的Class信息
	 */
	private Class<T> resolveClass;
	/**
	 * 标题行所在索引
	 */
	private int titleRowIndex;
	/**
	 * 行解析器
	 */
	private volatile RowResolver<T> rowResolver;
	/**
	 * 类型转换器管理者
	 */
	TypeConvertManager typeConvertManager;

	public Integer getStartRowIndex() {
		return startRowIndex;
	}

	public void setStartRowIndex(int startRowIndex) {
		this.startRowIndex = startRowIndex;
	}

	public Integer getEndRowIndex() {
		return endRowIndex;
	}

	public void setEndRowIndex(int endRowIndex) {
		this.endRowIndex = endRowIndex;
	}

	SingleRowSheetResolver(TypeConvertManager typeConvertManager, Class<T> resolveClass, int titleRowIndex) {
		this.typeConvertManager = typeConvertManager;
		this.resolveClass = resolveClass;
		this.titleRowIndex = titleRowIndex;
	}

	@Override
	public List<T> resovle(Sheet sheet) {
		List<T> resolveResult = new ArrayList<>();
		int startIndex = this.getStartRowIndex();
		int endIndex = getEndIndexBySheet(sheet);
		FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
		// 是否需要初始化rowResolver
		if (rowResolver == null) {
			synchronized (this) {
				if (rowResolver == null)
					this.rowResolver = createRowResolver(sheet.getRow(titleRowIndex));
			}
		}
		for (int i = startIndex; i <= endIndex; i++) {
			Row row = sheet.getRow(i);
			try {
				T reolveObj = rowResolver.resolve(evaluator, row);
				resolveResult.add(reolveObj);
			} catch (CellResolveException e) {
				throw e;
			} catch (IgnoreRowException e) {
				continue;
			} catch(BreakRowException e) {
				break;
			}catch (Exception e) {
				throw new RowResolveException("解析表格行异常，异常行：" + (i + 1), e, i);
			}
		}
		return resolveResult;
	}

	/**
	 * 根据有ExcelColumnImport注解的Field集合。生成Excel文件列的字段名和索引一一对应的Map
	 * 
	 * @param fields
	 * @return
	 */
	public Map<Field, Integer> genRelation(Row titleRow, List<Field> fields) {
		Map<Field, Integer> fieldIndexMap = new LinkedHashMap();
		for (Field field : fields) {
			ExcelColumnImport colImp = field.getAnnotation(ExcelColumnImport.class);
			// 优先级控制，指定了属性对应的列索引就不会根据名字查找列索引
			int index = colImp.index();
			if (index == -1)
				index = getTitleCellIndexByNames(titleRow, colImp.value());
			// 检查index是否重复
			AssertUtil.assertTrue(fieldIndexMap.containsValue(index), "index:" + index + "已存在");
			fieldIndexMap.put(field, index);
		}

		if (fieldIndexMap.isEmpty())
			throw new IllegalArgumentException(resolveClass + "类的字段上没有一个@ExcelColumImport注解，解析个毛啊");

		return fieldIndexMap;
	}

	/**
	 * 得到标题行指定名字列的索引
	 * 
	 * @param titleNames 指定要查找的列的名字，多个名字之间是或者的关系
	 * @return
	 */
	private int getTitleCellIndexByNames(Row titleRow, String[] titleNames) {
		for (String colName : titleNames) {
			int index = ExcelUtil.findCellIndexByValue(titleRow, colName);
			if (index != -1)
				return index;
		}
		throw new IllegalArgumentException("列数组：" + Arrays.asList(titleNames) + "在标题行中都不存在！");
	}

	private RowResolver<T> createRowResolver(Row titleRow) {
		List<Field> hasAnnoFields = ReflectUtil.getFieldsByAnnoClass(resolveClass, ExcelColumnImport.class);
		List<RowPostProcessor<T>> postProcessors= createPostProcessors(hasAnnoFields);
		List<CellProcessController> cellProcessControllers=createProcessControllers(hasAnnoFields);
		return new SingleRowResolver<>(genRelation(titleRow, hasAnnoFields), resolveClass, typeConvertManager,postProcessors,cellProcessControllers);
	}
	
	private List<RowPostProcessor<T>> createPostProcessors(List<Field> hasAnnoFields){
		List<RowPostProcessor<T>> filters=new ArrayList<>(0);
		EnableRowPostProcessor annlFilterClass = this.resolveClass.getAnnotation(EnableRowPostProcessor.class);
		if(annlFilterClass!=null) {
			@SuppressWarnings("unchecked")
			RowPostProcessor<T> annFilter=(RowPostProcessor<T>) ReflectUtil.createObject(annlFilterClass.value());
			filters.add(annFilter);
		}
		return filters;
	}


	private List<CellProcessController> createProcessControllers(List<Field> hasAnnoFields){
		List<CellProcessController> list=new ArrayList<>(0);
		for (Field field : hasAnnoFields) {
			ExcelColumnImport importAnno=field.getAnnotation(ExcelColumnImport.class);
			String[] ignoreValues=importAnno.ignoreValue();
			if(!ignoreValues[0].equals(CellValueContants.DEFAULT)) {
				list.add(new IgnoreRowCellProcessController(ignoreValues,importAnno.index()));
			}
			String[] endValues=importAnno.endValue();
			if(!endValues[0].equals(CellValueContants.DEFAULT)) {
				list.add(new EndReadCellProcessController(endValues,importAnno.index()));
			}
		}
		return list;
	}

	
	private int getEndIndexBySheet(Sheet sheet) {
		int endIndex=sheet.getLastRowNum();
		if(this.endRowIndex!=null) {
			if(this.endRowIndex<=endIndex)
				endIndex= this.endRowIndex;
		}
		return endIndex;
	}
	
}
