package platform.education.commonResource.web.common.util;

import javax.servlet.http.HttpServletRequest;

import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

public class UserIconUtil {
	/**
	 * 公共方法利用用户的id获取图片的src
	 * @param userId
	 * @param request
	 * @return
	 */
	public static String getImgSrc(Integer userId,HttpServletRequest request,ProfileService profileService){
		String outPutHtml = "";
		String def = "res/images/no_pic.jpg";
		Profile profile = profileService.findByUserId(userId);
		if (profile != null) {
			String icon = profile.getIcon();
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
		}
		if ("".equals(outPutHtml)) {
			StringBuffer url = request.getRequestURL();  
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();  
			outPutHtml = tempContextUrl+def;
		}
		return outPutHtml;
	}
}
