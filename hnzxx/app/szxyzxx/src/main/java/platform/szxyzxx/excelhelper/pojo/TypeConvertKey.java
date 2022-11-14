package platform.szxyzxx.excelhelper.pojo;
/**
 * 其实就是进行类型转换时 源类型 和 要转换类型 这两个的Class实例封装
 * @author chenjiaxin
 *
 */
public class TypeConvertKey {
	private Class<?> originClass;
	private Class<?> convertClass;
	
	public TypeConvertKey() {
		
	}
	
	public TypeConvertKey(Class<?> originClass,Class<?> convertToClass) {
		this.originClass=originClass;
		this.convertClass=convertToClass;
	}
	
	public Class<?> getOriginClass() {
		return originClass;
	}
	public void setOriginClass(Class<?> originClass) {
		this.originClass = originClass;
	}
	public Class<?> getConvertClass() {
		return convertClass;
	}
	public void setConvertClass(Class<?> convertClass) {
		this.convertClass = convertClass;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((convertClass == null) ? 0 : convertClass.hashCode());
		result = prime * result + ((originClass == null) ? 0 : originClass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeConvertKey other = (TypeConvertKey) obj;
		if (convertClass == null) {
			if (other.convertClass != null)
				return false;
		} else if (!convertClass.equals(other.convertClass))
			return false;
		if (originClass == null) {
			if (other.originClass != null)
				return false;
		} else if (!originClass.equals(other.originClass))
			return false;
		return true;
	}
	
}
