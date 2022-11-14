package platform.szxyzxx.web.message.util;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import platform.education.message.vo.MessageVo;
import platform.szxyzxx.web.message.util.push.DefaultWriterAppender;
/**
 * 
 *<p>
 * Title: PushMessageRecord.java 
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
 * @Fuction 方法描述 ：消息记录推送
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年9月30日
 */
public class PushMessageRecord {
	private static DefaultWriterAppender wa = null;
	 
    /**
     * push message
     * 
     * @param message
     * @param userId
     */
    public static void push(List<MessageVo> message,Integer userId,Integer posterId) {
        if (wa == null) {
            wa = new DefaultWriterAppender();
        }
        ObjectMapper mapper = new ObjectMapper();
        String msg = null;
        try {
        	msg = mapper.writeValueAsString(message);
            wa.writer.write(messageEscape(msg, userId,posterId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 执行推送JS
     * 
     * @param script
     */
    public static void pushScript(String script) {
        if (wa == null) {
            wa = new DefaultWriterAppender();
        }
        try {
            wa.writer.write(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * @param message
     * @param userId
     * @return
     */
    private static String messageEscape(String message,Integer userId,Integer posterId) {
        return "<script type='text/javascript'>window.parent.record_comet.showMsg(" + message + "," + userId + "," + posterId + ");</script>";
    }
}
