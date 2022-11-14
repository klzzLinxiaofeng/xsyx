package platform.szxyzxx.web.message.util.push;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;


public class CustomQueue<E> extends ConcurrentLinkedQueue<E>{
	
	
	/** 
	 * @Fields serialVersionUID :
	 */ 
	private static final long serialVersionUID = 1L;

	@Resource(name="redisTemplate")
	private RedisTemplate<String,E> redisTemplate;
	
	/**
	 * 向Redis添加Hash
	 * @param e
	 * @param key
	 * @param hashKey
	 * @return
	 * @Author 陈业强
	 */
	public boolean pushForRedisHash(E e,String key,String hashKey){
		try {
			redisTemplate.opsForHash().put(key, hashKey, e);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据key以及hashKey删除Redis中Hash中的值
	 * @param key
	 * @param hashKey
	 * @return
	 * @Author 陈业强
	 */
	public boolean removeForRedisHash(String key,String hashKey){
		try {
			redisTemplate.opsForHash().delete(key, hashKey);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 根据key以及hashKey获取value值
	 * @param key
	 * @param hashKey
	 * @return
	 * @Author 陈业强
	 */
	public Object getObjectForRedisHash(String key, String hashKey){
		return redisTemplate.opsForHash().get(key, hashKey);
	}
	
	/**
	 * 根据key获取Redis中Hash的长度
	 * @param key
	 * @return
	 * @Author 陈业强
	 */
	public Long lengthForRedisHash(String key){
		return redisTemplate.opsForHash().size(key);
	}
	
	/**
	 * 判断是否存在指定的key
	 * @param key
	 * @return
	 * @Author 陈业强
	 */
	public boolean hasKey(String key){
		return redisTemplate.hasKey(key);
	}
	
	/** 
     * 压栈 
     *  
     * @param key 
     * @param value
     * @Author 陈业强 
     * @return 
     */  
    public Long pushForRedisList(String key, E value) {  
        return redisTemplate.opsForList().leftPush(key, value);  
    }  
  
    /** 
     * 出栈 
     *  
     * @param key
     * @Author 陈业强 
     * @return 
     */  
    public E popForRedisList(String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  
  
    /** 
     * 入队 
     *  
     * @param key 
     * @param value 
     * @Author 陈业强
     * @return 
     */  
    public Long inForRedisList(String key, E value) {  
        return redisTemplate.opsForList().rightPush(key, value);  
    }  
  
    /** 
     * 出队 
     *  
     * @param key 
     * @Author 陈业强
     * @return 
     */  
    public E outForRedisList(String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  
  
    /** 
     * 栈/队列长 
     *  
     * @param key 
     * @Author 陈业强
     * @return 
     */  
    public Long lengthForRedisList(String key) {  
        return redisTemplate.opsForList().size(key);  
    }  
  
    /** 
     * 范围检索 
     *  
     * @param key 
     * @param start 
     * @param end 
     * @Author 陈业强
     * @return 
     */  
    public List<E> rangeForRedisList(String key, int start, int end) {  
        return redisTemplate.opsForList().range(key, start, end);  
    }  
  
    /** 
     * 移除 
     *  
     * @param key 
     * @param i 
     * @param value 
     * @Author 陈业强
     * @return
     */  
    public void removeForRedisList(String key, long i, String value) {  
        redisTemplate.opsForList().remove(key, i, value);  
    }  
  
    /** 
     * 检索 
     *  
     * @param key 
     * @param index 
     * @Author 陈业强
     * @return 
     */  
    public E indexForRedisList(String key, long index) {  
        return redisTemplate.opsForList().index(key, index);  
    }  
  
    /** 
     * 置值 
     *  
     * @param key 
     * @param index 
     * @param value 
     * @Author 陈业强
     * @return
     */  
    public void setForRedisList(String key, long index, E value) {  
        redisTemplate.opsForList().set(key, index, value);  
    }  
  
    /** 
     * 裁剪 
     *  
     * @param key 
     * @param start 
     * @param end
     * @Author 陈业强 
     */  
    public void trimForRedisList(String key, long start, int end) {  
        redisTemplate.opsForList().trim(key, start, end);  
    }
}
