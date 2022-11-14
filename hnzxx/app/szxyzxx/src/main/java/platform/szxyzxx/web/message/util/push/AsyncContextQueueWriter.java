package platform.szxyzxx.web.message.util.push;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
/**
 * 
 *<p>
 * Title: AsyncContextQueueWriter.java 
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
 * @Fuction 方法描述 ：上下文队列读写操作
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年9月30日
 */
public class AsyncContextQueueWriter extends Writer{
	 /**
     * AsyncContext队列
     */
    private Queue<AsyncContext> queue;
 
    /**
     * 消息队列
     */
    private static final BlockingQueue<String> MESSAGE_QUEUE = new LinkedBlockingQueue<String>();
 
    /**
     * 发送消息到异步线程，最终输出到http response流
     * 
     * @param cbuf
     * @param off
     * @param len
     * @throws IOException
     */
    private void sendMessage(char[] cbuf, int off, int len) throws IOException {
        sendMessage(new String(cbuf, off, len));
    }
 
    /**
     * 发送消息到异步线程，最终输出到http response流
     * 
     * @param str
     * @throws IOException
     */
    private void sendMessage(String str) throws IOException {
        try {
            MESSAGE_QUEUE.put(str);
        } catch (Exception ex) {
            IOException t = new IOException();
            t.initCause(ex);
            throw t;
        }
    }
 
    /**
     * 异步线程，当消息队列中被放入数据，将释放take方法的阻塞，将数据发送到http response流上
     */
    private Runnable notifierRunnable = new Runnable() {
        public void run() {
            boolean done = false;
            while (!done) {
                String script = null;
                try {
                    script = MESSAGE_QUEUE.take();
                    for (AsyncContext ac : queue) {
                        try {
                            PrintWriter acWriter = ac.getResponse().getWriter();
                            acWriter.println(script);
                            acWriter.flush();
                        } catch (Exception ex) {
                            queue.remove(ac);
                        }
                    }
                } catch (Exception iex) {
                	iex.printStackTrace();
                    done = true;
                }
            }
        }
    };
 
    /**
     * 保持一个默认的writer，输出至控制台，这个writer是同步输出，其他输出到response流的writer是异步输出
     */
    private static final Writer DEFAULT_WRITER = new OutputStreamWriter(System.out);
 
    /**
     * 构造AsyncContextQueueWriter
     * 
     * @param queue
     */
    AsyncContextQueueWriter(Queue<AsyncContext> queue) {
        this.queue = queue;
        Thread notifierThread = new Thread(notifierRunnable);
        notifierThread.start();
    }
 
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        DEFAULT_WRITER.write(cbuf, off, len);
        sendMessage(cbuf, off, len);
    }
 
    @Override
    public void write(String str) throws IOException {
        DEFAULT_WRITER.write(str);
        sendMessage(str);
    }
 
    @Override
    public void flush() throws IOException {
        DEFAULT_WRITER.flush();
    }
 
    @Override
    public void close() throws IOException {
        DEFAULT_WRITER.close();
        for (AsyncContext ac : queue) {
            ac.getResponse().getWriter().close();
        }
    }
}
