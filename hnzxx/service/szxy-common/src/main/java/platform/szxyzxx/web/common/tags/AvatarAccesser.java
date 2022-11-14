package platform.szxyzxx.web.common.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import platform.education.user.holder.UserModuleServiceHolder;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

public class AvatarAccesser extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	public String outPutHtml = "";

	private String size;

	private String def = "/res/images/no_pic.jpg";

	private Integer userId;

	private final ProfileService profileService = UserModuleServiceHolder.getInstance().getProfileService();

	@Override
	protected int doStartTagInternal() throws Exception {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		if (!StringUtils.isEmpty(this.userId)) {
			Profile profile = this.profileService.findByUserId(userId);
			if (profile != null) {
				String icon = profile.getIcon();
				outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
			}
		}
		if ("".equals(outPutHtml)) {
			this.outPutHtml = request.getContextPath() + this.def;
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(this.outPutHtml);
			this.outPutHtml = "";
		} catch (IOException e) {
			throw new JspException("标签输出错误!", e);
		}
		return EVAL_PAGE;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
