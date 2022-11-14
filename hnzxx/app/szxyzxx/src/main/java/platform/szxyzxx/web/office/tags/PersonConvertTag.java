package platform.szxyzxx.web.office.tags;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import platform.education.generalTeachingAffair.holder.GeneralTeachingAffairServiceHolder;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.TeacherService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class PersonConvertTag extends RequestContextAwareTag {

    private static final long serialVersionUID = 1L;
    private final TeacherService teacherService = GeneralTeachingAffairServiceHolder.getInstance().getTeacherService();

    public String outPutHtml = "";
    private Integer schoolId;
    private Integer userId;

    @Override
    protected int doStartTagInternal() {
        if (!StringUtils.isEmpty(this.schoolId) && !StringUtils.isEmpty(this.userId)) {
            Teacher teacher = this.teacherService.findOfUser(this.schoolId, this.userId);
            if (teacher != null) {
                String name = teacher.getAlias();
                if (name == null || name.isEmpty()) {
                    name = teacher.getName();
                }
                if (name == null || name.isEmpty()) {
                    name = teacher.getUserName();
                }
                outPutHtml = name;
            }
        }
        if (outPutHtml == null || outPutHtml.isEmpty()) {
            outPutHtml = "未知";
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
