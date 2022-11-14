package platform.szxyzxx.knowledge.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.knowledge.dao.KnowEvaluationDao;
import platform.szxyzxx.knowledge.service.KnowEvaluationSercice;
import platform.szxyzxx.knowledge.vo.KnowEvaluation;
import platform.szxyzxx.knowledge.vo.PdfVo;
import platform.szxyzxx.knowledge.vo.StudentVo;

import java.util.List;


@Service
public class KnowEvaluationSerciceImpl implements KnowEvaluationSercice {

    @Autowired
    private KnowEvaluationDao knowEvaluationDao;
    @Override
    public List<StudentVo> findByAll(String schoolYear, Integer gradeId, Integer teamId, Page page) {
        return knowEvaluationDao.findByAll(schoolYear,gradeId,teamId,page);
    }

    @Override
    public List<KnowEvaluation> findByAllMenu(Integer knowId,Integer studentId, Page page) {
        //一级菜单
        List<KnowEvaluation> menus= knowEvaluationDao.findByPinjai(knowId,0,null,null,page);
        if(menus.size()>0){
            for(KnowEvaluation aa:menus){
               //二级菜单
                List<KnowEvaluation> chilenMenus=knowEvaluationDao.findByPinjai(knowId,1,aa.getKnowMenuId(),null,null);
                for(KnowEvaluation bb:chilenMenus){
                    KnowEvaluation knowEvaluation=knowEvaluationDao.findByKnowId(bb.getKnowMenuId(),studentId);
                    if(knowEvaluation!=null){
                        bb.setPingfen(knowEvaluation.getPingfen());
                        bb.setPingyu(knowEvaluation.getPingyu());
                        bb.setId(knowEvaluation.getId());
                    }
                }
                aa.setKnowEvaluationList(chilenMenus);
            }
        }
        return menus;
    }

    @Override
    public Integer createOrUpdate(KnowEvaluation knowEvaluation) {
        if(knowEvaluation.getId()!=null){
            return knowEvaluationDao.update(knowEvaluation);
        }
        return knowEvaluationDao.create(knowEvaluation);
    }

    @Override
    public KnowEvaluation findById(Integer id) {
        return knowEvaluationDao.findById(id);
    }

    @Override
    public List<PdfVo> findByPdf(Integer gradeId, Integer studentId, Integer subject) {
        List<PdfVo> list=knowEvaluationDao.findByPdf(gradeId,subject);
        for(PdfVo aa:list){
            Integer num=0;
            //一级菜单
            List<KnowEvaluation> menus= knowEvaluationDao.findByPinjai(aa.getId(),0,null,null,null);
            if(menus.size()>0){
                for(KnowEvaluation cc:menus){
                    //二级菜单
                    List<KnowEvaluation> chilenMenus=knowEvaluationDao.findByPinjai(aa.getId(),1,cc.getKnowMenuId(),null,null);
                    for(KnowEvaluation bb:chilenMenus) {
                        KnowEvaluation knowEvaluation=knowEvaluationDao.findByKnowId(bb.getKnowMenuId(),studentId);
                        if(knowEvaluation!=null){
                            bb.setPingfen(knowEvaluation.getPingfen());
                            bb.setPingyu(knowEvaluation.getPingyu());
                            bb.setId(knowEvaluation.getId());
                        }

                    }
                    cc.setKnowEvaluationList(chilenMenus);
                    num+=chilenMenus.size();
                    }
                }
            aa.setHangNumber(num);
            aa.setList(menus);
            }
        System.out.println(list.size());
        return list;
    }
}
