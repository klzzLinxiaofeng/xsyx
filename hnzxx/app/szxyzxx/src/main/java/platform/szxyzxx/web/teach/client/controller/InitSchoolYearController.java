package platform.szxyzxx.web.teach.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.*;


@Controller
@RequestMapping("/school")
public class InitSchoolYearController extends BaseController {

    @RequestMapping(value = "/schoolYear/index")
    public ModelAndView toSchoolYearTermIndex(@CurrentUser UserInfo user, Model model){

        Integer schoolId = user.getSchoolId();
        School school = schoolService.findSchoolById(schoolId);
        List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearOfSchool(schoolId);
        SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
        List<SchoolTerm> schoolTermList = null;

        List<Object> yearList = new ArrayList<>();
        List<Object> termList = null;
        Map<String, Object> yearMap = null;
        Map<String, Object> termMap = null;
        if (schoolYearList != null && schoolYearList.size() > 0) {
            for (SchoolYear schoolYear : schoolYearList) {

                termList = new ArrayList<>();
                schoolTermList = schoolTermService.getSchoolTermOfYear(schoolId, schoolYear.getId());
                if (schoolTermList != null && schoolTermList.size() > 0) {
                    Collections.reverse(schoolTermList);
                    for (SchoolTerm schoolTerm : schoolTermList) {
                        termMap = new HashMap<>();
                        termMap.put("term", schoolTerm);
                        if (current != null && current.getSchoolTermCode().equals(schoolTerm.getCode())) {
                            termMap.put("isCurrent", true);
                        } else {
                            termMap.put("isCurrent", false);
                        }
                        termList.add(termMap);
                    }
                }

                yearMap = new HashMap<>();
                yearMap.put("year", schoolYear);
                yearMap.put("termList", termList);
                yearList.add(yearMap);
            }
        }

        model.addAttribute("schoolName", school.getName());
        model.addAttribute("yearList", yearList);
        return new ModelAndView("/schoolInit/schoolYear/index", model.asMap());
    }

}
