package platform.szxyzxx.web.moral.service;

import framework.generic.dao.Page;
import org.springframework.web.multipart.MultipartFile;
import platform.education.generalTeachingAffair.vo.ResposeLyauiVO;
import platform.education.generalTeachingAffair.vo.WeekStarParamVo;
import platform.education.generalTeachingAffair.vo.WeekStarResonseVo;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/5/8 15:30
 * @Description: 周明星学生
 */
public interface StudentWeekStarService {

    ResposeLyauiVO readExcelFile(MultipartFile file, HttpServletRequest request, UserInfo user);

    List<WeekStarResonseVo> findWeekStarStu(WeekStarParamVo weekStarParamVo, Page page, Integer type);

    List<Map<String, Object>> findWeekStarStuById(Integer id, String ids, Integer type);

    void deleteWeekStarStu(Integer id);

    /**
     * 查询任课教师绩效
     *
     * @param type
     * @param year
     * @param page
     * @return
     */
    List<Map<String, Object>> findTheTeacherScoreList(Integer type, String year, Integer schoolId, Page page,String xq,String name);

    ResposeLyauiVO uploadTeacherExcel(MultipartFile file, HttpServletRequest request, UserInfo user);
}
