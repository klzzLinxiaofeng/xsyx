package platform.szxyzxx.literacy.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Subject;
import platform.szxyzxx.literacy.dao.LiteracyStudentDao;
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.literacy.service.LiteracyStudentService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiteracyStudentServiceImpl implements LiteracyStudentService {
    @Autowired
    private LiteracyStudentDao listuDao;

    @Override
    public List<LiteracyStudent> findByAll(String xn, Integer nj, Integer bj, Integer km, Page page, UserInfo user) {
        if (bj != null && km != null) {
            List<LiteracyStudent> list = listuDao.findByAll(user.getSchoolId(), xn, bj, page);
            List<LiteracyStudent> lisiss = new ArrayList<>();
            for (LiteracyStudent lists : list) {
                List<Subject> subject = listuDao.findBySubId(user.getSchoolId(), km, lists.getCode(),xn);
                for (Subject ss : subject) {
                    LiteracyStudent literacyStudent = new LiteracyStudent();
                    literacyStudent.setStudentId(lists.getStudentId());
                    literacyStudent.setStuName(lists.getStuName());
                    literacyStudent.setSubjectId(ss.getId());
                    literacyStudent.setSubName(ss.getName());
                    literacyStudent.setTeamId(lists.getTeamId());
                    literacyStudent.setTeamName(lists.getTeamName());
                    lisiss.add(literacyStudent);
                }
            }
            return lisiss;

        }
        return null;
    }

    @Override
    public List<LiteracyStudent> findByStudent(Integer schoolId, Integer studentId, Integer subjectId,String xn,String xq, Page page) {
        return listuDao.findByStudent(schoolId, studentId, subjectId,xn,xq, page);
    }

    @Override
    public List<LiteracyStudent> findByliteracy(Integer id,Integer teamId) {
        return listuDao.findByliteracy(id,teamId);
    }

    @Override
    public List<LiteracyVo> getAllSubjectZhibiao(String xn, String xq, Integer gradeId, Integer subjectId) {

        return listuDao.getAllSubjectZhibiao(xn,xq,gradeId,subjectId);

    }

    @Override
    public Integer updatePingyu( Integer id, String pingyu) {
        return listuDao.updatePingyu(id,pingyu);
    }

    @Override
    public LiteracyStudent findByLiteracyId(Integer schoolId, Integer id) {
        return listuDao.findByLiteracyId(schoolId,id);
    }

    @Override
    public Integer updatePingFen(Integer stuId, Integer zhibiaoId, Integer fenshu) {
        return listuDao.updatePingFen( stuId,  zhibiaoId,  fenshu);
    }

}
