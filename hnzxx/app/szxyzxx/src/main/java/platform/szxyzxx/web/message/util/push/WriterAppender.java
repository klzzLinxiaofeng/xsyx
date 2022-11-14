package platform.szxyzxx.web.message.util.push;

import java.io.Writer;
import java.util.Queue;

import javax.servlet.AsyncContext;
/**
 * 
 *<p>
 * Title: WriterAppender.java 
 * </p>
 *<p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>  
 * @Fuction 方法描述 ：输出源初始化
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年9月30日
 */
public abstract class WriterAppender {
	/**
     * 异步Servlet上下文队列
     */
    public static final Queue<AsyncContext> ASYNC_CONTEXT_QUEUE = new CustomQueue<AsyncContext>();
 
    /**
     * AsyncContextQueue Writer
     */
    public Writer writer = new AsyncContextQueueWriter(ASYNC_CONTEXT_QUEUE);
}
