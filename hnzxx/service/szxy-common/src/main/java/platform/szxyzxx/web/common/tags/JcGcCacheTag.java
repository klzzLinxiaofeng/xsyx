package platform.szxyzxx.web.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import platform.education.generalcode.holder.JcGcCacheServiceHolder;
import platform.education.generalcode.service.JcGcCacheService;

public class JcGcCacheTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private final static JcGcCacheService jcGcCacheService = JcGcCacheServiceHolder
			.getInstance().getJcGcCacheService();

	public String tableCode;
	public String value;
	public String colName="name";

	private String outPutHtml = "";

	@SuppressWarnings("static-access")
	@Override
	protected int doStartTagInternal() throws Exception {
		Object result = jcGcCacheService.getColumnValue(tableCode, value,
				colName);
		if (result != null) {
			outPutHtml = result.toString();
		}
		return this.EVAL_BODY_AGAIN;
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

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

}
