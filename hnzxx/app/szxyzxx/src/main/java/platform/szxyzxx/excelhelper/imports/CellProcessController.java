package platform.szxyzxx.excelhelper.imports;

/**
 * sheet行解析过程中的流程控制器，流程控制通过抛出对应的异常实现，目前支持的有IgnoreRowException、BreakRowException
 */
public interface CellProcessController {

    void handle(int colIndex,Object cellVal);

}
