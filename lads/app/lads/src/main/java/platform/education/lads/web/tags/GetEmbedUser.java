package platform.education.lads.web.tags;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import platform.education.lads.service.LadsUserService;

public class GetEmbedUser extends RequestContextAwareTag {

    public String outPutHtml = "";

    @Override
    protected int doStartTagInternal() {
        LadsUserService ladsUserService = (LadsUserService) this.getRequestContext().getWebApplicationContext().getBean("ladsUserService");
        Integer userId = ladsUserService.getEmbedUserId((HttpServletRequest)this.pageContext.getRequest());
        if(userId!=null){
            outPutHtml = userId.toString();
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