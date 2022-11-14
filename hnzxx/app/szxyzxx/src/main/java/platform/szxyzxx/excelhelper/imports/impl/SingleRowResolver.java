package platform.szxyzxx.excelhelper.imports.impl;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.convert.ITypeConverter;
import platform.szxyzxx.excelhelper.convert.TypeConvertManager;
import platform.szxyzxx.excelhelper.exception.*;
import platform.szxyzxx.excelhelper.imports.CellProcessController;
import platform.szxyzxx.excelhelper.imports.RowPostProcessor;
import platform.szxyzxx.excelhelper.imports.RowResolver;
import platform.szxyzxx.excelhelper.util.AssertUtil;
import platform.szxyzxx.excelhelper.util.BasicDataTypeUtil;
import platform.szxyzxx.excelhelper.util.ExcelUtil;
import platform.szxyzxx.excelhelper.util.ReflectUtil;

import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 单行解析器，将一行数据，解析为一个pojo。根据字段名和行的Cell的索引的对应关系，使用反射调用set方法来将Cell中的值设置到字段中</br>
 * 该类不能解析多行Row,即一行，对应一个对象，不能将多行（例如一个学生有多个老师，Excel文件可能就是学生信息将会跨行,即需要多行才能解析为一个完整的学生对象）映射为一个对象
 *
 * @param <T>将行解析为什么样的Class
 * @author chenjiaxin
 */
public class SingleRowResolver<T> implements RowResolver<T> {

    /**
     * 字段和Cell索引的一一对应关系
     */
    private Map<Field, Integer> fieldIndexRelation;
    /**
     * 解析对象所需的该对象的Class对象
     */
    private Class<T> resolveObjClass;
    /**
     * 进行类型转换时所需的类型转换器管理器
     */
    private TypeConvertManager typeConvertManager;

    private List<RowPostProcessor<T>> rowPostProcessors;

    private List<CellProcessController> cellProcessControllers;

    private NumberFormat nf = null;

    SingleRowResolver(Map<Field, Integer> fieldIndexRelation, Class<T> resolveObjClass, TypeConvertManager typeConvertManager,List<RowPostProcessor<T>> rowPostProcessors,List<CellProcessController> cellProcessControllers) {
        this.fieldIndexRelation = fieldIndexRelation;
        this.resolveObjClass = resolveObjClass;
        this.typeConvertManager = typeConvertManager;
        this.rowPostProcessors=rowPostProcessors;
        this.cellProcessControllers=cellProcessControllers;
        nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        //最多保留6位小鼠
        nf.setMaximumFractionDigits(6);
        nf.setRoundingMode(RoundingMode.DOWN);

    }

    @Override
    public T resolve(FormulaEvaluator evaluator, Row... rows) {
        AssertUtil.assertTrue(rows.length != 1, "~我是一个单行解析器，所以只能对一个Row对象进行解析，多个Row对象不能解析");
        //因为是单行解析，所以取出第一行就好了
        Row row = rows[0];

        T pojo = null;

        //空的Row将返回null（如果不抛IgnoreRowException或者BreakRowException）
        if (row != null) {
            //调用无参构造方法创建对象
            pojo = ReflectUtil.createObject(resolveObjClass);
        }
        Set<Field> fields = fieldIndexRelation.keySet();
        for (Field field : fields) {
            int cellIndex = fieldIndexRelation.get(field);
            try {

                setPojoProperty(evaluator, row, cellIndex, pojo, field);
            } catch (IgnoreRowException | BreakRowException e) {
                throw e;
            } catch (Exception e) {
                int rowNum = row.getRowNum();
                throw new CellResolveException("解析单元格异常，异常行：" + (rowNum + 1) + ",列：" + (cellIndex + 1),
                        e, rowNum, cellIndex);
            }

            if(!rowPostProcessors.isEmpty()){
                for (RowPostProcessor rowPostProcessor : rowPostProcessors) {
                    rowPostProcessor.doPost(pojo);
                }
            }

        }
        return pojo;
    }

    /**
     * 将row第cellIndex列的值，设置到pojo的field字段中
     *
     * @param evaluator
     * @param row
     * @param cellIndex
     * @param pojo
     * @param field
     */
    private void setPojoProperty(FormulaEvaluator evaluator, Row row, int cellIndex, T pojo, Field field) {
        Object cellValue = null;
        if (row != null)
            cellValue = ExcelUtil.getCellValue(row.getCell(cellIndex), evaluator);

        if(!cellProcessControllers.isEmpty()){
            for (CellProcessController cellProcessController : cellProcessControllers) {
                cellProcessController.handle(cellIndex,cellValue);
            }
        }

        // null值、空字符串，不进行赋值操作
        if (cellValue == null || cellValue.toString().isEmpty())
            return;
        Object convertedValue = cellValue;
        // 如果单元格的值的类型和属性的类型不一致
        if (!BasicDataTypeUtil.equal(cellValue.getClass(), field.getType())) {
            // 类型转换
            convertedValue = convertType(cellValue, field);
        }
        // 调用set方法赋值
        ReflectUtil.invokeSetter(pojo, field.getName(), convertedValue);
    }


    /**
     * 将cellValue转换成field实例对应的类型
     *
     * @param cellValue
     * @param field
     * @return
     */
    private Object convertType(Object cellValue, Field field) {
        Class<?> fieldType = field.getType();

        //支持任意类型转字符串
        if (fieldType == String.class) {
            if (cellValue instanceof Double) {
                return nf.format(cellValue);
            }
            return cellValue.toString();
        }

        //字符串转日期就直接用SimpleDateFormat转换就行了
        if (cellValue instanceof String && fieldType == Date.class) {
            String formatStr = field.getAnnotation(ExcelColumnImport.class).dateFormatStr();
            if (!formatStr.isEmpty()) {
                try {
                    return new SimpleDateFormat(formatStr).parse(cellValue.toString());
                } catch (ParseException e) {
                    throw new DateFormatException("日期格式：[" + formatStr + "] 不能够解析：[" + cellValue + "]");
                }
            }
        }
        @SuppressWarnings("unchecked")
        ITypeConverter<Object, ?> converter = (ITypeConverter<Object, ?>) typeConvertManager
                .getTypeConvert(cellValue.getClass(), fieldType);
        if (converter == null) {
            throw new TypeConverterNotFountException(
                    "不能够将单元格值[" + cellValue + "(" + cellValue.getClass() + "]转换成[" + fieldType + "]类型");
        }
        return converter.convert(cellValue);
    }
}
