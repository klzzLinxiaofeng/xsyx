/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service;

import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.model.LadsMediaUserStatusTool;


/**
 *
 * @author Administrator
 */
public interface MediaToolService extends ToolService {

    public LadsMediaTool save(String toolId, String title, String content,String uploadList);

    public LadsMediaTool getMediaByToolId(String toolId);

    public LadsMediaUserStatusTool saveUserStatus(String toolId, Integer userId,Double lastPlayTime,String finishedFlag, String editorScore);

    public LadsMediaUserStatusTool getMediaUserStatusByToolIdAndUserId(String toolId, Integer userId);

    public Object[] getUserStatusList(String ldId, String toolId);
}
