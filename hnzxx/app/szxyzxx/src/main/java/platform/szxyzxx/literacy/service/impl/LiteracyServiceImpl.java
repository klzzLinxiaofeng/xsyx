package platform.szxyzxx.literacy.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.literacy.dao.LiteracyDao;
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.literacy.service.LiteracyService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class LiteracyServiceImpl implements LiteracyService {
    @Autowired
    private LiteracyDao literacyDao;
    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public List<LiteracyVo> findByAll(String xq, Integer xn, Integer nj, Integer km, Page page) {
        return literacyDao.findByAll(xq, xn, nj, km, page);
    }

    @Override
    public Boolean create(LiteracyVo literacyVo, UserInfo userInfo) {
        LiteracyVo literacyVo1 =new LiteracyVo();
        literacyVo1.setIsDelete(0);
        literacyVo1.setLiteracyName(literacyVo.getLiteracyName());
        literacyVo1.setXn(literacyVo.getXn());
        literacyVo1.setXq(literacyVo.getXq());
        literacyVo1.setGradeId(literacyVo.getGradeId());
        literacyVo1.setSubjectId(literacyVo.getSubjectId());
        literacyVo1.setScore(literacyVo.getScore());
        Date date =new Date();
        literacyVo1.setCreateTime(date);
        int a=literacyDao.create(literacyVo1);
        int b=literacyVo1.getId();
        String sql="  select ps.id as stuid,ps.name as stuname from pj_student ps " +
                "inner join pj_team_student pts on pts.student_id=ps.id where pts.grade_id="+literacyVo1.getGradeId() +" and ps.is_delete=0  and ps.study_state='01'  and ps.school_id="+userInfo.getSchoolId()+" group by ps.id";
     List<Map<String,Object>> list  =basicSQLService.find(sql);

        if (list!=null) {
            if (list.size() > 0) {
                for (Map<String,Object> aa : list) {
                    LiteracyStudent literacyStudent = new LiteracyStudent();
                    literacyStudent.setCreateTime(date);
                    literacyStudent.setLiteracyId(b);
                    Long asss= (Long) aa.get("stuid");
                    literacyDao.createsss(literacyStudent,asss);
                }
            }
        }else{
            System.out.println("null");
            return false;
        }
        return a>0;
    }

    @Override
    public LiteracyVo findById(Integer id) {
        return literacyDao.findById(id);
    }

    @Override
    public Boolean update(LiteracyVo literacyVo) {
        Date date =new Date();
        literacyVo.setMoideTime(date);
        return literacyDao.update(literacyVo)>0;
    }

    @Override
    public Boolean updatefenshu(Integer id, Integer score,String pingyu) {
        return literacyDao.updatefenshu(id,score,pingyu)>0;
    }

    @Override
    public Boolean updatedaoruXuigai(Integer studentId, Integer literId, Integer fenshu) {
        return literacyDao.updateDaorufenshu(studentId,literId,fenshu)>0;
    }

    @Override
    public Integer updatestudent(Integer literacyId) {
        int a= literacyDao.updatestudent(literacyId);
        System.out.println(literacyId);
        System.out.println("a"+a);
        return a;
    }

    @Override
    public List<LiteracyVo> findByName(LiteracyVo literacyVo) {
        return literacyDao.findByName( literacyVo);
    }

    @Override
    public LiteracyVo findBySchoolYearAndSchoolTrem(String year, String schoolTrem, Integer gardeId,Integer subjectId, String name) {
        return literacyDao.findBySchoolYearAndSchoolTrem( year,  schoolTrem,  gardeId,subjectId,  name);
    }
}
