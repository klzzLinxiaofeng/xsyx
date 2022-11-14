package platform.szxyzxx.web.oa.utils;

import java.util.List;

import org.springframework.core.task.TaskExecutor;

import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.im.service.IMService;

public class PushUtils {

    /**
     * 根据Teacher集合推送
     * 不使用线程的方式
     */
    public static void push(final List<Teacher> teachers, final int message_type, final String receiverType, final JSONObject jsonObjects, final IMService imService) {
        for (Teacher t : teachers) {
            JSONObject messageMap = new JSONObject();
            messageMap.put("objectid", t.getUserId());
            messageMap.put("receiveid", t.getUserId()); // 老师ID
            messageMap.put("message_type", message_type);
            messageMap.put("receiverType", receiverType);
            messageMap.put("return_info", jsonObjects);// 返回的通知实体
            try {
                //imService.push(t.getUserName(),messageMap.toString());
                imService.push(t.getUserId(), "xunyun#educloud#web", messageMap.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据Teacher集合推送
     * 使用线程的方式
     */
    public static void pushOfTaskExecutor(final List<Teacher> teachers, final int message_type, final String receiverType, final JSONObject jsonObjects, final IMService imService, TaskExecutor taskExecutor) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    for (Teacher t : teachers) {
                        JSONObject messageMap = new JSONObject();
                        messageMap.put("objectid", t.getUserId());
                        messageMap.put("receiveid", t.getUserId()); // 老师ID
                        messageMap.put("message_type", message_type);
                        messageMap.put("receiverType", receiverType);
                        messageMap.put("return_info", jsonObjects);// 返回的通知实体
                        //imService.push(t.getUserName(),messageMap.toString());
                        imService.push(t.getUserId(), "xunyun#educloud#web", messageMap.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
