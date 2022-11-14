/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.web.tags;

import platform.education.lads.service.LadsService;
import platform.education.lads.vo.LadsLearningdesignVo;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import platform.education.lads.service.LadsUserService;

/**
 *
 * @author 罗志明
 */
public class GetLdIdByEmbed extends RequestContextAwareTag {

    public String lessonId;
    public String outPutHtml = "";

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    protected int doStartTagInternal() {
        LadsUserService ladsUserService = (LadsUserService) this.getRequestContext().getWebApplicationContext().getBean("ladsUserService");
        LadsLearningdesignVo vo = ladsUserService.embedLesson(lessonId);
        outPutHtml = vo.getLdId();
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
