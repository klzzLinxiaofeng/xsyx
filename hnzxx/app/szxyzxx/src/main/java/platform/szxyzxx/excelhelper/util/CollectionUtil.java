package platform.szxyzxx.excelhelper.util;

import java.util.Collection;
import java.util.Map;

public abstract class CollectionUtil {
	public static boolean isNotEmpty(Collection<?> coll) {
		if(coll==null || coll.isEmpty())
			return false;
		return true;
	}
	
	public static boolean isNotEmpty(Map<?, ?> map) {
		if(map==null || map.isEmpty())
			return false;
		return true;
	}
}
