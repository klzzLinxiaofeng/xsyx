package platform.szxyzxx.excelhelper.util;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 * @author chenjiaxin
 *
 */
public abstract class AssertUtil {
	public static void assertNull(Object obj,String msg) {
		assertTrue(obj==null,msg);
	}
	
	public static void assertNull(String msg,Object... objs) {
		assertTrue(objs==null || objs.length<1,"断言参数不能为空");
		for (Object object : objs) {
			assertNull(object,"msg");
		}
	}
	
	public static void assertColloctionEmpty(Collection<?> coll,String msg) {
		assertTrue(coll==null || coll.isEmpty(),msg);
	}
	
	public static void assertMapEmpty(Map<?, ?> map,String msg) {
		assertTrue(map==null || map.isEmpty(),msg);
	}
	
	public static void assertEqNegativeOne(int val,String msg) {
		assertTrue(val==-1,msg);
	}
	
	public static void assertNotNegativeOne(int val,String msg) {
		assertTrue(val!=-1,msg);
	}
	
	public static void assertTrue(boolean real,String msg) {
		if(real)
			throw new IllegalArgumentException(msg);
	}
}
