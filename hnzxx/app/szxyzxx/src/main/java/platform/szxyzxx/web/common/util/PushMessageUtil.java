package platform.szxyzxx.web.common.util;

import java.util.List;

import org.springframework.core.task.TaskExecutor;

import platform.education.im.holder.IMServiceHolder;
import platform.education.im.service.IMService;
import platform.education.message.holder.PushMessageHolder;
import platform.education.message.model.Message;
import platform.education.message.service.MessageReceiverService;
import platform.education.message.service.MessageService;
import platform.education.message.vo.MessageReceiverCondition;
import platform.education.message.vo.MessageResult;
import platform.education.user.holder.UserModuleServiceHolder;
import platform.education.user.service.UserService;
import platform.szxyzxx.web.message.contans.StatusTypeContans;

/**
 * 
 *<p>
 * Title: PushMessageUtil.java 
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
 * @Fuction 方法描述 :推送消息工具类
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年9月29日
 */
public class PushMessageUtil {
	
	private final static MessageReceiverService messageReceiverService = PushMessageHolder.getInstance().getMessageReceiverService();
	
	private final static MessageService messageService = PushMessageHolder.getInstance().getMessageService();
	
	private final static TaskExecutor messageExecutor = PushMessageHolder.getInstance().getTaskExecutor();
	
	private final static IMService imService = IMServiceHolder.getInstance().getImService();
	
	private final static UserService userService = UserModuleServiceHolder.getInstance().getUserService(); 
	/**
	 * 推送未读消息数到消息中心
	 * @param userId
	 * @Author 陈业强
	 */
	public static void pushMessage(Integer userId){
		MessageReceiverCondition condition = new MessageReceiverCondition();
		condition.setReceiverId(userId);
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		Long count = messageReceiverService.count(condition);
		//userId为消息推送对象userId
		//imService.push(user.getUserName(), count + "");
		imService.push(userId, "xunyun#educloud#web", count + "");
	}
	/**
	 * 推送消息（）
	 * @param userIds  List<Integer>集合  使用线程池
	 * @Author 陈业强
	 */
	public static void pushMessageList(final List<Integer> userIds){
		final MessageReceiverCondition condition = new MessageReceiverCondition();
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		messageExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				for(Integer userId : userIds){
					condition.setReceiverId(userId);
					Long count = messageReceiverService.count(condition);
					//userId为消息推送对象userId
					imService.push(userId, "xunyun#educloud#web", count + "");
				}
			}
		});
		
	}
	
	/**
	 * 根据传入的信息创建消息，并发送到接收者
	 * @param message 
	 * @param receiverId 接收者单个userId
	 * @return MessageResult
	 * @Author 陈业强
	 */
	public static MessageResult sendMessage(Message message,Integer receiverId) {
		MessageResult result = messageService.sendMessage(message, receiverId);
		Long number = getMessgeCount(receiverId);
		imService.push(receiverId, "xunyun#educloud#web", number + "");
		return result;
	}
	/**
	 * 根据传入的信息创建消息，并发送到接收者
	 * @param message 
	 * @param receiverIds 接收者（单个或多个）id拼成的字符串（如：12,13），若接收者为多个，拼接字符必须是","
	 * @Author 陈业强
	 */
	public static MessageResult sendMessage(Message message,String receiverIds) {
		MessageResult result = messageService.sendMessage(message, receiverIds);
		if(receiverIds.contains(",")){
			String[] receiver =  receiverIds.split(",");
			for(String receiverId : receiver){
				Integer userId = Integer.parseInt(receiverId);
				Long number = getMessgeCount(userId);
				imService.push(userId, "xunyun#educloud#web", number + "");
			}
		}
		return result;
	}
	/**
	 * 根据传入的信息创建消息，并发送到接收者
	 * @param message
	 * @param receiverIdList 接收者userId List
	 * @return
	 * @Author 陈业强
	 */
	public static MessageResult sendMessage(Message message,final List<Integer> receiverIdList) {
		MessageResult result = messageService.sendMessage(message, receiverIdList);
           messageExecutor.execute(new Runnable() {
			@Override
			public void run() {
		final List<Integer> receiverIds = receiverIdList;
		if(receiverIdList.size() > 0 && receiverIdList != null){
			for(Integer receiverId : receiverIds){
				Long number = getMessgeCount(receiverId);
				imService.push(receiverId, "xunyun#educloud#web", String.valueOf(number));
			}
		}
	}
  });
		return result;
	}
	/**
	 * 根据传入的信息创建消息，并发送到接收者
	 * @param message 
	 * @param receiverIds 接收者（单个或多个）id拼成的字符串（如：12,13），若接收者为多个，拼接字符必须是","
	 * @param openPool 线程池开关，默认为true--表示开启，false--关闭
	 * @return Boolean
	 * @Author 陈业强
	 */
	public static MessageResult sendMessage(Message message,String receiverIds,Boolean openPool) {
		MessageResult result = messageService.sendMessage(message, receiverIds);
		if(receiverIds != null && !"".equals(receiverIds)){
			if(receiverIds.contains(",")){
				final String[] receiver =  receiverIds.split(",");//解析数据形成数组
				if(receiver.length > 0 ){
					if(openPool == null || openPool == true){
						messageExecutor.execute(new Runnable() {
							
							@Override
							public void run() {
								for(int i = 0; i<receiver.length; i++ ){
									Integer userId = Integer.parseInt(receiver[i].trim());
									Long number = getMessgeCount(userId);
									imService.push(userId, "xunyun#educloud#web", number + "");
								}
							}
						});
					}else{
						for(int i = 0; i<receiver.length; i++ ){
							Integer userId = Integer.parseInt(receiver[i].trim());
							Long number = getMessgeCount(userId);
							imService.push(userId, "xunyun#educloud#web", number + "");
						}
					}
				}
			}else{
				Integer userId = Integer.parseInt(receiverIds);
				Long number = getMessgeCount(userId);
				imService.push(userId, "xunyun#educloud#web", number + "");
			}
		}
		return result;
	}
	/**
	 * 根据传入的信息创建消息，并发送到接收者
	 * @param message
	 * @param receiverIdList 接收者userId List
	 * @param openPool 线程池开关，默认为true--表示开启，false--关闭
	 * @return
	 * @Author 陈业强
	 */
	public static MessageResult sendMessage(Message message,final List<Integer> receiverIdList,Boolean openPool) {
		MessageResult result = messageService.sendMessage(message, receiverIdList);
		
		if(receiverIdList.size() > 0 && receiverIdList != null){
			if(openPool == null || openPool == true){
				messageExecutor.execute(new Runnable() {
					@Override
					public void run() {
						for(Integer it : receiverIdList){
							Long number = getMessgeCount(it);
							imService.push(it, "xunyun#educloud#web", String.valueOf(number));
						}
					}
				});
			}else{
				for(Integer it : receiverIdList){
					Long number = getMessgeCount(it);
					imService.push(it, "xunyun#educloud#web", String.valueOf(number));
				}
			}
		}
		return result;
	}
	
	private static Long getMessgeCount(Integer userId){
		MessageReceiverCondition condition = new MessageReceiverCondition();
		condition.setReceiverId(userId);
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		return messageReceiverService.count(condition);
	}
}
