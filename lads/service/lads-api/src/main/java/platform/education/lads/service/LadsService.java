/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service;

import platform.education.lads.model.LadsActivity;
import platform.education.lads.model.LadsLearningdesign;
import net.sf.json.JSONArray;

/**
 *
 * @author Administrator
 */
public interface LadsService {

    /*
     * 完整删除lads课件
     * 参数是 ids 要删除的课件id数组
     */
    public void deleteLearningDesigns(String ids[]);


    /*
     * 复制一份课件，新复制的课件为已发布课件，不会因为修改原课件而改变发布后的状态
     * 参数是 ldId
     */
    public LadsLearningdesign copyToPublishedLearningDesign(String ldId);

    /*
     * 工具随机toolId生成器
     */
    public String toolIdCreater();

    /*
     * 通过toolId获取活动
     */
    public LadsActivity getActivityByToolId(String toolId);

    /*
     * 复制课件json
     */
    public void copyJson(JSONArray jsonArray);
    
     /*
     * 获取某用户对某份课件的学习状态
     * ldId
     * userId
     */
    public int getFinishedByUser(String ldId,Integer userId);
}
