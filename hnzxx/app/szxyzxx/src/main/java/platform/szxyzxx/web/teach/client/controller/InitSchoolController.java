package platform.szxyzxx.web.teach.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.service.SchoolInitService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;


@Controller
@RequestMapping("/school/init")
public class InitSchoolController extends BaseController {
	@Autowired
	@Qualifier("schoolInitService")
	private SchoolInitService schoolInitService;
	
	@RequestMapping(value = "/page")
    public ModelAndView toImportPage(@CurrentUser UserInfo user, Model model,
    		@RequestParam(value="type", defaultValue="0") Integer type){
		
		SchoolTermCurrent term = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("type", type);
		model.addAttribute("term", term);
		model.addAttribute("schoolName", user.getSchoolName());
		
        return new ModelAndView("/schoolInit/page", model.asMap());
    }

    @RequestMapping(value = "/index")
    public ModelAndView toInitSchoolPage(@CurrentUser UserInfo user, Model model){
        Integer schoolId = user.getSchoolId();
        String schoolYear = user.getSchoolYear();
        if (schoolYear == null) {
            schoolYear = "";
        }

        model.addAttribute("initTerm", isInitTerm(schoolId));
        model.addAttribute("initTeacher", isInitTeacher(schoolId, schoolYear));
        model.addAttribute("initStudent", isInitStudent(schoolId, schoolYear));
        model.addAttribute("initTeamTeacher", isInitTeamTeacher(schoolId, schoolYear));
        model.addAttribute("schoolName", user.getSchoolName());
        
        return new ModelAndView("/schoolInit/index", model.asMap());
    }

    private boolean isInitTerm(Integer schoolId){
        boolean initTerm = false;
        SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
        if (current != null) {
            initTerm = true;
        }
        return initTerm;
    }
    
    private boolean isInitStudent(Integer schoolId, String schoolYear) {
    	if(schoolYear==null || "".equals(schoolYear)) {
			return false;
		}
    	Integer status = Integer.parseInt((schoolId+schoolYear));
    	Long size = schoolInitService.countStudentTmpByCodeAndStatus(null, status);
    	
        if(size > 0) {
        	return true;
        } else {
        	return false;
        }
	}

	private boolean isInitTeacher(Integer schoolId, String schoolYear) {
		if(schoolYear==null || "".equals(schoolYear)) {
			return false;
		}
		Integer status = Integer.parseInt((schoolId+schoolYear));
		Long size = schoolInitService.countTeacherTmpByCodeAndStatus(null, status);
		
        if(size > 0) {
        	return true;
        } else {
        	return false;
        }
    }

    private boolean isInitTeamTeacher(Integer schoolId, String schoolYear){
        boolean isInit = false;
        Integer status = Integer.parseInt((schoolId+schoolYear));
        Long size = schoolInitService.countSubjectTeacherTmpByCodeAndStatus(null, status);
        if (size > 0) {
            isInit = true;
        }
        return isInit;
    }

}
