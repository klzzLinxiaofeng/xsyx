package platform.education.paper.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class GetStudyUser extends TagSupport {
    
    public String outPutHtml = "";
    
    @Override
    public int doStartTag() throws JspException {
        //获取正在学习的用户,还没实现
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