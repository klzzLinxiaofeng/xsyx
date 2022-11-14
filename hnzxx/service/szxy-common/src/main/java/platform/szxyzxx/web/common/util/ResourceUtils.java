package platform.szxyzxx.web.common.util;

import javax.servlet.http.HttpServletRequest;
import platform.education.resource.vo.ImportCatalogVo;

/**
 *
 * @author 罗志明
 */
public class ResourceUtils {

    public static ImportCatalogVo setDirectory(HttpServletRequest request) {
                ImportCatalogVo vo = new ImportCatalogVo();
		String subject = request.getParameter("subject");
		String publish = request.getParameter("publish");
		String volume = request.getParameter("volume");
                String grade = request.getParameter("grade");
		String stage = request.getParameter("stage");
		String catalog = request.getParameter("catalog");
                vo.setSubjectCode(subject);
                vo.setGradeCode(grade);
                vo.setStageCode(stage);
                vo.setVolumnCode(volume);
                vo.setVersionCode(publish);
                if(catalog!=null&&!"".equals(catalog)){
                    vo.setCatalogCode(catalog);                }
		return vo;
	}
}
