package platform.szxyzxx.web.message.util.push;

import java.io.IOException;
/**
 * 
 *<p>
 * Title: DefaultWriterAppender.java 
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
 * @Fuction 方法描述 ：默认的消息推送方式输出源
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年9月30日
 */
public class DefaultWriterAppender extends WriterAppender{
	public void write(String message) {
        try {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
