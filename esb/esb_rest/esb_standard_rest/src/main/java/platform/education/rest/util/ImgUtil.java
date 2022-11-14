package platform.education.rest.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.rest.common.constants.SysContants;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

public class ImgUtil {
	
	/**
	 * 公共方法利用用户的id获取图片的src
	 * @param userId
	 * @param request
	 * @return
	 */
	public static String getImgSrc(Integer userId, ProfileService profileService ){
		String outPutHtml = "";
		String def = SysContants.APP_DEFAULT_USERICON;
		Profile profile = profileService.findByUserId(userId);
		if (profile != null) {
			String icon = profile.getIcon();
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
		} 
		if("".equals(outPutHtml)){
			outPutHtml = def;
		}
		return outPutHtml;
	}
	
	public static Map<Integer, String> getImgSrcByIds(Integer[] userIds, ProfileService profileService ){
		Map<Integer, String> imgMap = new HashMap<Integer, String>(userIds.length);
		
		String def = SysContants.APP_DEFAULT_USERICON;
		List<Profile> profileList = profileService.findByUserIds(userIds);
		
		for (Profile profile : profileList) {
			String icon = profile.getIcon();
			String outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
			if(outPutHtml==null || "".equals(outPutHtml)){
				outPutHtml = def;
			}
			imgMap.put(profile.getUserId(), outPutHtml);
		}
		
		return imgMap;
	}
	
	public static String getImgUrl(String uuid){
		String outPutHtml = "";
		String def = SysContants.APP_DEFAULT_USERICON;
		if (uuid != null && !"".equals(uuid)) {
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(uuid);
		} 
		if("".equals(outPutHtml)){
			outPutHtml = def;
		}
		return outPutHtml;
	}

	public static String getStudentIconUrl(Integer personId,PersonService personService){
		String outPutHtml = "";
		String def = SysContants.APP_DEFAULT_USERICON;
		Person p = personService.findPersonById(personId);
		if (p != null) {
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(p.getPhotoUuid());
		}
		if("".equals(outPutHtml)){
			outPutHtml = def;
		}
		return outPutHtml;
	}
	
}

