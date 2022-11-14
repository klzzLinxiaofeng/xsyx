package platform.education.rest.util;

import java.util.ArrayList;
import java.util.List;

import platform.education.im.holder.IMServiceHolder;
import platform.education.im.model.PushData;
import platform.education.im.service.IMService;

/**
 * 环信推送服务
 * 
 * @author hmzhang 2016-08-17
 *
 */
public class PushUtil
{
	
	/**
	 * 获取imService
	 */
	private static IMService imService = IMServiceHolder.getInstance().getImService();
	
	
	/**
	 * 通知推送
	 * 
	 * @param appKey 推送者的appKey
	 * @param userIds 推送的userId
	 * @param reciverType {@link ReciverType} 通知类型
	 * @param dataId 数据库操作id
	 * @param dataCode 模块标识
	 * @param data 推送内容
	 * @author hmzhang
	 * @date 2016-08-17
	 */
	public static void push(List<Integer> userIds, String reciverType, Integer dataId, String dataCode, String data){
		PushData pushData = new PushData();
		pushData.setReceiver_type(reciverType); //接收群体
		pushData.setData_id(String.valueOf(dataId));//数据库操作的id
		pushData.setData_action("add");//数据库操作
		pushData.setRole("teacher");//通知对像
		pushData.setData_code(dataCode);//模块code
		pushData.setOwner_id("");
		pushData.setData(data == null ? "" : data);
		imService.push(userIds, dataCode, pushData);
	}
	
	
}
