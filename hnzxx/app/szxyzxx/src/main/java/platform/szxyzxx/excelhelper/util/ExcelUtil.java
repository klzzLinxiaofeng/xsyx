package platform.szxyzxx.excelhelper.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import platform.szxyzxx.excelhelper.constants.CellValueContants;
import platform.szxyzxx.excelhelper.exception.ErrorCellTypeException;
import platform.szxyzxx.excelhelper.exception.UnknownCellTypeException;
import platform.szxyzxx.excelhelper.exception.UnsupportedFormulaTypeException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Excel工具类
 *
 * @author chenjiaxin
 */
public abstract class ExcelUtil {
    /**
     * 根据cell对象的类型，返回相应的单元格值。如果cell为空或者cell类型为BLANK，返回null</br>
     * 方法可能的返回值类型：String、Double、java.util.Date、Boolean
     *
     * @param cell
     * @param evaluator 计算公式类型时所需要的FormulaEvaluator
     * @return
     */
    public static Object getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null)
            return null;

        Object cellValue = null;
        int type = cell.getCellType();
        switch (type) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                // 判断是否是日期类型。Excel日期类型读取后就是数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell))
                    cellValue = cell.getDateCellValue();
                else
                    cellValue = cell.getNumericCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                if (evaluator == null)
                    throw new NullPointerException("计算公式单元格所必需的FormulaEvaluator实例为空");
                CellValue formulaValue = evaluator.evaluate(cell);
                cellValue = getValueByCellValueObj(formulaValue);
                break;
            case Cell.CELL_TYPE_BLANK:
                cellValue = null;
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;

            case Cell.CELL_TYPE_ERROR:
                throw new ErrorCellTypeException("单元格值错误");
            default:
                throw new UnknownCellTypeException("单元格类型未知");
        }
        return cellValue;
    }


    public static Object getValueByCellValueObj(CellValue cellValue) {
        Object value = null;
        int type = cellValue.getCellType();
        switch (type) {
            // 如果公式计算后是数字
            case Cell.CELL_TYPE_NUMERIC:
                value = cellValue.getNumberValue();
                break;
            case Cell.CELL_TYPE_STRING:
                value = cellValue.getStringValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cellValue.getBooleanValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = null;
                break;
            case Cell.CELL_TYPE_ERROR:
                throw new ErrorCellTypeException("公式单元格的值错误");
            default:
                throw new UnsupportedFormulaTypeException("单元格类型不支持");
        }

        return value;
    }

    /**
     * 根据value的类型，调用相应的类型的cell.setCellValue重载方法
     */
    public static void setCellValue(Cell cell, Object value, boolean isFormula) {
        if (value == null)
            return;
        Class<?> vc = value.getClass();
        // double类型
        if (vc == Integer.class || vc == Double.class || vc == Float.class || vc == BigDecimal.class)
            cell.setCellValue(new Double(value.toString()));
            // 日期类型
        else if (vc == Date.class)
            cell.setCellValue((Date) value);
        else if (vc == Calendar.class)
            cell.setCellValue((Calendar) value);
            // 其他类型使用toString方法的值
        else {
            if (isFormula)
                cell.setCellFormula(value.toString());
            else
                cell.setCellValue(value.toString());
        }

    }

    public static void setCellValue(Cell cell, Object value) {
        setCellValue(cell, value, false);
    }


    /**
     * 查找findRow中，Cell的值等于value的单元格的索引
     *
     * @param findRow   要查找的Row对象
     * @param value     单元格要比较的值
     * @param evaluator 计算公式类型单元格时使用的公式计算器，如果确定该行没有公式单元格则可为null
     * @return
     */
    public static int findCellIndexByValue(Row findRow, Object value, FormulaEvaluator evaluator) {
        short startIndex = findRow.getFirstCellNum();
        short endIndex = findRow.getLastCellNum();
        for (int i = startIndex; i < endIndex; i++) {
            Cell cell = findRow.getCell(i);
            if (cell == null)
                continue;
            Object cellVal = getCellValue(cell, evaluator);
            //如果是字符串，则去掉空格再比较
            if (cellVal instanceof String) {
                cellVal = ((String) cellVal).trim();
            }
            if (cellVal != null && cellVal.equals(value))
                return i;
        }
        return -1;
    }

    public static int findCellIndexByValue(Row findRow, Object value) {
        return findCellIndexByValue(findRow, value, null);
    }

    /**
     * 如果单元格的值在values数组中将返回true,判断时将会支持CellValueContants的值
     *
     * @param values
     * @param cellValue
     * @return
     */
    public static boolean ifIncludeCellValue(String[] values, Object cellValue) {
        if (!values[0].equals(CellValueContants.DEFAULT)) {
            for (String eqValue : values) {
                if (cellValue == null) {
                    if (eqValue.equals(CellValueContants.BLANK))
                        return true;
                } else {
                    if(cellValue instanceof String){
                        if (cellValue.toString().trim().equals(eqValue))
                            return true;
                    }else if (cellValue.equals(eqValue))
                        return true;
                }
            }
        }
        return false;
    }

}
