package platform.szxyzxx.excelhelper.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Excel公式工具类
 * @author chenjiaxin
 *
 */
public abstract class FormulaUtil {
	/**
	 * A-Z的字符和对应的索引的Map
	 */
	public static final Map<Integer,Character> indexCharMap=new HashMap<>(); 
	
	static {
		init();
	}
	
	private static void init() {
		//生成A到Z的字符
		for (int i = 0; i < 26; i++) {
			indexCharMap.put(i, (char) (65 + i));
		}
	}
	
	/**
	 * 根据列的索引(从0开始)，生成列的公式名。 如27等于AB
	 * @param colIndex 列的索引
	 * @return
	 */
	public static String genColumnFormulaName(int colIndex) {
		StringBuffer formulaName=new StringBuffer();
		if(colIndex<=25)
			return indexCharMap.get(colIndex).toString();
		//除以25的余数
		int yuShu=colIndex%25;
		//除以25的商,忽略小数点
		//使用int进行除法运算将会省略小数点，所以得将colIndex改为double类型
		int shang=(int) (new Double(colIndex)/25);
		
		String multiA=genA(shang);
		
		//如果是25的倍数
		if(yuShu==0)
			return multiA;
		
		formulaName.append(multiA).append(indexCharMap.get(yuShu-1));
		
		return formulaName.toString();
	}
	
	/**
	 * 生成i个A组成的字符串
	 * @param i A的个数
	 * @return
	 */
	private static String genA(int i) {
		StringBuffer sb=new StringBuffer();
		for(int j=0;j<i;j++) {
			sb.append("A");
		}
		return sb.toString();
	}
}
