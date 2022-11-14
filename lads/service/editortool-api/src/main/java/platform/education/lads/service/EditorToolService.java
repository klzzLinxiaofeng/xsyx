/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service;

import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.model.LadsEditorUserStatusTool;

/**
 *
 * @author Administrator
 */
public interface EditorToolService extends ToolService {

    public LadsEditorTool save(String toolId, String title, String content,String uploadList);

    public LadsEditorTool getEditorByToolId(String toolId);

    public LadsEditorUserStatusTool saveUserStatus(String toolId, Integer userId, String editorScore);

    public LadsEditorUserStatusTool getEditorUserStatusByToolIdAndUserId(String toolId, Integer userId);

    public Object[] getUserStatusList(String ldId, String toolId);
}
