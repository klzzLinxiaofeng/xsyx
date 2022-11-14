package platform.szxyzxx.web.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 *
 * @author 罗志明
 *
 */
public class SubjectIcon extends RequestContextAwareTag {

	private static final long serialVersionUID = -7700057900507441887L;
	public String subjectName;
	public String outPutHtml = "";

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	protected int doStartTagInternal() {
		if (subjectName != null && !"".equals(subjectName)) {
			if ("数学".equals(subjectName)) {
				this.outPutHtml = "sx";
			} else if ("语文".equals(subjectName)) {
				this.outPutHtml = "yw";
			} else if ("英语".equals(subjectName)) {
				this.outPutHtml = "yy";
			} else if ("化学".equals(subjectName)) {
				this.outPutHtml = "hx";
			} else if ("物理".equals(subjectName)) {
				this.outPutHtml = "wl";
			} else if ("政治".equals(subjectName)) {
				this.outPutHtml = "zz";
			} else if ("生物".equals(subjectName)) {
				this.outPutHtml = "sw";
			} else if ("地理".equals(subjectName)) {
				this.outPutHtml = "dl";
			} else if ("历史".equals(subjectName)) {
				this.outPutHtml = "ls";
			}
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
}
