/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import platform.education.lads.model.LadsLastStudyRecord;
import platform.education.lads.vo.LadsLearningdesignVo;
import platform.education.lads.vo.LadsUserVo;

/**
 *
 * @author Administrator
 */
public interface LadsUserService {

     /*
     * 用于接入用户数据的方法,userId是接入的用户id
     */
    public LadsUserVo getUser(Integer userId);

    /*
     * 用于接入外部课程系统的方法
     * embedId 嵌入id
     */
    public LadsLearningdesignVo embedLesson(String embedId);

    /*
     * 用于接入外部课程发布学习用户的方法
     * 参数是 ldId
     */
    public List<LadsUserVo> getStudyUserList(String ldId);

    /*
     * 获取当前嵌入系统的学习用户id
     */
    public Integer getEmbedUserId(HttpServletRequest request);
    
    /*
     * 获取某用户最后一次学习的记录
     * 参数是 ldId userId
     */
    public LadsLastStudyRecord getLastStudyRecord(String ldId, Integer userId);
    
    
    /*
     * 获取当前嵌入系统的appId
     */
    public Integer getRelateAppId(HttpServletRequest request);
}
