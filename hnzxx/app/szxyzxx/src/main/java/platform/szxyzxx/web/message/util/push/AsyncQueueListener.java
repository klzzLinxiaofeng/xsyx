package platform.szxyzxx.web.message.util.push;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.message.contants.MessageContants;

/**
 * 
 *<p>
 * Title: AsyncQueueListener.java 
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
 * @Fuction 方法描述 ：启动servlet3.0异步，并设置监听器
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年9月30日
 */
public class AsyncQueueListener {
	private Logger log = LoggerFactory.getLogger(AsyncQueueListener.class);
	
	public void asyncStart(HttpServletRequest request, HttpServletResponse response){
		final PrintWriter writer;
		try {
			writer = response.getWriter();
			if (!request.isAsyncSupported()) {
				log.info("the servlet is not supported Async");
				return;
			}
			request.startAsync(request, response);
			if (request.isAsyncStarted()) {
				final AsyncContext asyncContext = request.getAsyncContext();
				asyncContext.setTimeout(MessageContants.MESSAGE_PUSH_RECORD_TIMEOUT);
				asyncContext.addListener(new AsyncListener() {
					public void onComplete(AsyncEvent event) throws IOException {
						DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.remove(asyncContext);
					}
					
					public void onTimeout(AsyncEvent event) throws IOException {
//						this.onComplete(event);
						log.info("the event : " + event.toString()
								+ " is timeout now !");
						// 尝试向客户端发送超时方法调用，客户端会再次请求/blogpush，周而复始
						log.info("try to notify the client the connection is timeout now ...");
						String timeoutString = "<script type=\"text/javascript\">window.parent.record_comet.timeout();</script>";
						writer.println(timeoutString);
						writer.flush();
						writer.close();
						DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.remove(asyncContext);
					}
					
					public void onError(AsyncEvent event) throws IOException {
						DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.remove(asyncContext);
					}
					
					public void onStartAsync(AsyncEvent event) throws IOException {
					}
				});
				DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.add(asyncContext);
			} else {
				log.error("the ruquest is not AsyncStarted !");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
