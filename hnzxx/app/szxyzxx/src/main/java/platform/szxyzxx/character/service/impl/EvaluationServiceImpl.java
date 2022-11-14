package platform.szxyzxx.character.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.character.dao.EvaluationDao;
import platform.szxyzxx.character.dao.RecordsDao;
import platform.szxyzxx.character.pojo.Evaluation;
import platform.szxyzxx.character.pojo.GongShiDaoChu;
import platform.szxyzxx.character.pojo.Records;
import platform.szxyzxx.character.service.EvaluationService;

import java.util.*;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationDao evaluationDao;

    @Autowired
    private  StudentDao studentDao;
    @Autowired
    private RecordsDao recordsDao;
    /*
    * 添加评价指标
    */
    @Override
    public boolean create(Evaluation evaluation) {
        Date date =new Date();
        evaluation.setCreateTime(date);
        return evaluationDao.createBys(evaluation)>0;
    }

    @Override
    public List<Evaluation> findByAll() {
        return evaluationDao.findByAll();
    }

    @Override
    public String abandonTime(String ids) {
        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                evaluationDao.update(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }

    @Override
    public Evaluation findById(int id) {
        return evaluationDao.findById(id);
    }

    /*
    * 修该指标
    */
    @Override
    public boolean update(Evaluation evaluation) {
        return evaluationDao.updateEvalua(evaluation)>0;
    }

    @Override
    public List<Records> findByAllRecords(Integer schoolId,String xq,Integer bg,Integer nj,String stuName,Page page) {
        List<Evaluation> evaluationList =evaluationDao.findByAlls();
        List<Student> student=studentDao.findByall(schoolId,xq,bg,nj,stuName,page);
       List<Records> list=new ArrayList<>();
        for(Student aa:student){
            List<Evaluation> list2=new ArrayList<>();
            Records records =new Records();
            records.setStudentId(aa.getId());
            records.setStuName(aa.getName());
            for(int a=0;a<evaluationList.size();a++){
                Evaluation evaluation=new Evaluation();
                evaluation.setId(evaluationList.get(a).getId());
                evaluation.setName(evaluationList.get(a).getName());
                List<Records> recordslist=recordsDao.findByAllScore(aa.getId(),evaluationList.get(a).getId());
                int b=0;
                for(int j=0;j<recordslist.size();j++){
                    b+=recordslist.get(j).getScore();
                }
                evaluation.setInitScore(evaluationList.get(a).getInitScore()+b);
                list2.add(evaluation);
            }
            records.setEvaluationList(list2);
            list.add(records);
        }
        return list;
    }

    @Override
    public List<Student> findByStudentAll(Integer schoolId, Integer bg, Integer nj, String xn) {
        return studentDao.findByall(schoolId,xn,bg,nj,null,null);
    }

    @Override
    public List<GongShiDaoChu> findByAllDaoChu() {
        return null;
    }
}
