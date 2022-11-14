package platform.szxyzxx.dto.seewo;

/**
 * 主键比较器,比较目标对象的主键和自己的主键是否相等
 * @param <T>
 */
public interface PrimaryKeyComparator<T> {
    /**
     * 比较目标对象的主键和自己的主键是否相等
     * @param obj
     * @return
     */
    boolean primaryKeyEquals(T obj);

}
