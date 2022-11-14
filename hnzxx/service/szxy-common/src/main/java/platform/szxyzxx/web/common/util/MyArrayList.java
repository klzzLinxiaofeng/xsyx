package platform.szxyzxx.web.common.util;

import java.util.ArrayList;
/**
 * <p>Title:MyArrayList.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：自定义ArrayList,帮助查询数据时传递参数，使用方法：new MyArrayList(参数1，参数2，参数3……);
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年8月7日
 */
public class MyArrayList<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;

	/**
	 * @Method MyArrayList
	 * @Function 功能描述：构造函数
	 * @param param
	 * @Date Aug 27, 2012
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList(T... param){
		for(T t:param){
			this.add(t);
		}
	}
}
