package platform.szxyzxx.web.common.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 
 * @author 潘维良
 * 
 *         格式化时间 以文本形式输出
 */
public class ShowTime extends RequestContextAwareTag {

	private static final long serialVersionUID = -7700057900507441887L;

	public Date createTime;
	public String outPutHtml = "";

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected int doStartTagInternal() {
		if (createTime != null) {
			Date currentDate = new Date(); // 结束时间
			Long valueDate = currentDate.getTime() - createTime.getTime(); // 时间差的毫秒数
			// 计算出相差天数
			double number = valueDate / (24.0 * 3600.0 * 1000.0);
			Integer days = 0;
			if (number >= 1) {
				days = (int) Math.ceil(number);
			}
			// 计算出小时数
			double number1 = valueDate / (3600.0 * 1000.0);
			Integer hours = 0;
			if (number1 >= 1) {
				hours = (int) Math.ceil(number1);
			}
			// 计算相差分钟数
			double number2 = valueDate / (60.0 * 1000.0);
			Integer minutes = 0;
			if (number2 >= 1) {
				minutes = (int) Math.ceil(number2);
			}
			// 计算相差秒数
			Integer seconds = (int) Math.ceil(valueDate / 1000.0);

			String beforeTime = "";
			if (days > 7) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				beforeTime = format.format(createTime);

			} else if (days > 0) {
				beforeTime += days + "天前";

			} else if (hours > 0) {
				beforeTime += hours + "小时前";

			} else if (minutes > 0) {
				beforeTime += minutes + "分钟前";

			} else if (seconds > 0) {
				beforeTime += seconds + "秒前";
			}
			outPutHtml = beforeTime;
		}
		return EVAL_BODY_INCLUDE;
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
}
