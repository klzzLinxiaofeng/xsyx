package platform.szxyzxx.web.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.IterationTag;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import platform.education.user.holder.UserModuleServiceHolder;
import platform.education.user.model.App;
import platform.education.user.model.Group;
import platform.education.user.model.User;
import platform.education.user.service.AppService;
import platform.education.user.service.GroupService;
import platform.education.user.service.UserService;

public class DataOfUserTage extends RequestContextAwareTag {

	private static final long serialVersionUID = 579623690963072552L;

	private final static AppService appService = UserModuleServiceHolder.getInstance().getAppService();
	
	private final static GroupService groupService = UserModuleServiceHolder.getInstance().getGroupService();
	
//	private final static ProfileService profileService = UserModuleServiceHolder.getInstance().getProfileService();
	
	private final static UserService userService = UserModuleServiceHolder.getInstance().getUserService();
	
	public String type;
	public String code;

	private String outPutHtml = "";
	
	private final static String NOT_DATA = "无";

	@Override
	protected int doStartTagInternal() throws Exception {
		String result = null;
		if ("app".equals(type)) {
			try {
				if (!"".equals(code)) {
					Integer id = Integer.parseInt(code);
					App app = appService.findAppById(id);
					if (app != null) {
						result = app.getName();
					} else {
						result = NOT_DATA;
					}
				} else {
					result = NOT_DATA;
				}
			} catch (Exception e) {
				result = "请传入年级的主键ID";
			}
		} else if ("user".equals(type)) {
			try {
				if (!"".equals(code)) {
					Integer id = Integer.parseInt(code);
					User user = userService.findUserById(id);
					if (user != null) {
						result = user.getUserName();
					} else {
						result = NOT_DATA;
					}
				} else {
					result = NOT_DATA;
				}
			} catch (Exception e) {
				result = "请传入用户的主键ID";
			}
		} else if ("group".equals(type)) {
			try {
				if (!"".equals(code)) {
					Integer id = Integer.parseInt(code);
					Group group = groupService.findGroupById(id);
					if (group != null) {
						result = group.getName();
					} else {
						result = NOT_DATA;
					}
				} else {
					result = NOT_DATA;
				}
			} catch (Exception e) {
				result = "请传入组的主键ID";
			}
		}
		if (result != null) {
			outPutHtml = result.toString();
		}
		return IterationTag.EVAL_BODY_AGAIN;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(outPutHtml);
			outPutHtml = "";
		} catch (IOException e) {
			throw new JspException("标签输出错误!", e);
		}
		return EVAL_PAGE;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
