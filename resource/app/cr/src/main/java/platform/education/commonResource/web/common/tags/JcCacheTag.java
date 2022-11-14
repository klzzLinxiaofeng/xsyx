package platform.education.commonResource.web.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import platform.education.commonResource.web.common.util.holder.JcCacheServiceHolder;
import platform.education.generalcode.service.JcCacheService;

/**
 * 根据表名称，主键获取某个字段的值
 * 
 * @author 黄江南
 * @version 1.0 2014-08-12
 */
public class JcCacheTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private final static JcCacheService jcCacheService = JcCacheServiceHolder
			.getInstance().getJcCacheService();

	public String tableName;
	public String paramName;
	public String value;
	public String echoField;

	private String outPutHtml = "";

	@SuppressWarnings("static-access")
	@Override
	protected int doStartTagInternal() throws Exception {
		Object result = jcCacheService.findUniqueByParam(tableName, paramName,
				value, echoField);
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEchoField() {
		return echoField;
	}

	public void setEchoField(String echoField) {
		this.echoField = echoField;
	}

}
