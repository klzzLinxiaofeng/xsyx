package platform.szxyzxx.web.bbx.util;

import java.util.List;

import org.springframework.core.task.TaskExecutor;

import net.sf.json.JSONObject;
import platform.education.clazz.model.TeamAccount;
import platform.education.generalTeachingAffair.model.Teacher;

/**
 * 推送类
 * @author huangyanchun
 *
 */
public class PushUtils {/*

	*//**
	 * 根据Teacher集合推送
	 * 使用线程的方式
	 *//*
	public static void pushOfTaskExecutor(final List<Teacher> teachers,final String object_type,  final Integer object_id, final String object_data, final String operation_type, final PushService pushService,TaskExecutor taskExecutor){
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
	        	for(Teacher t:teachers){
	        		JSONObject messageMap = new JSONObject();
	        		messageMap.put("object_id", object_id==null?"":object_id);  //对象实体表的id 
	        		messageMap.put("receive_id", t.getUserId()==null?"":t.getUserId()); // 推送接收者id  老师ID
	        		messageMap.put("object_type", object_type);  //对象类型 
	        		messageMap.put("object_data", object_data==null? "":object_data); //推送内容
	        		messageMap.put("operation_type", operation_type);  //说明引发本推送的object数据操作类型
					pushService.pushMsg(t.getUserName(),messageMap.toString());
					
					System.out.println("+++====="+messageMap.toString());
					
					}
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
}
	
	*//**
	 * 根据班班星账号集合推送
	 * 使用线程的方式
	 *//*
	public static void pushOfTeamAccountTaskExecutor(final List<TeamAccount> teamAccount,final String object_type,final Integer object_id,final String object_data, final String operation_type,final PushService pushService,TaskExecutor taskExecutor){
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
	        	for(TeamAccount ta:teamAccount){
	        		JSONObject messageMap = new JSONObject();
	        		messageMap.put("object_id", object_id);   //对象实体表的id
	        		messageMap.put("receive_id", ta.getTeamId()==null?"":ta.getTeamId()); //推送接收者id  班班星账号对应的班级Id
	        		messageMap.put("object_type", object_type);  //对象类型
	        		messageMap.put("object_data", object_data==null? "":object_data);  //推送内容
	        		messageMap.put("operation_type", operation_type);  //说明引发本推送的object数据操作类型
					pushService.pushMsg(ta.getAccount(),messageMap.toString());
					
					System.out.println("======"+messageMap.toString());
					
					}
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
}
	
*/	
}
