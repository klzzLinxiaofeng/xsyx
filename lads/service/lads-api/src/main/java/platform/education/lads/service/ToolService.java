/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service;

import java.util.List;

/**
 *
 * @author 罗志明
 */
public interface ToolService {

    public String toHtml();

    public String toXml();

    public int getFinishedStatus(String toolId, Integer userId);
    
    public int countFinishedStatus(List<String> toolIds, Integer userId);

    public String getUserScore(String toolId, Integer userId);

    public double getUserScore(List<String> toolIds, Integer userId);
}
