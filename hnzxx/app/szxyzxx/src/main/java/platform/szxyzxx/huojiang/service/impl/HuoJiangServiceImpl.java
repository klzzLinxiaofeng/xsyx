package platform.szxyzxx.huojiang.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.huojiang.dao.ClassLunwenDao;
import platform.szxyzxx.huojiang.dao.HuoJiangDao;
import platform.szxyzxx.huojiang.service.HuoJiangService;
import platform.szxyzxx.huojiang.vo.ClassLunwen;
import platform.szxyzxx.huojiang.vo.HuoJiang;
import platform.szxyzxx.innovation.dao.PracticeInnovationDao;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;

import java.util.List;


@Service
public class HuoJiangServiceImpl implements HuoJiangService {
    @Autowired
    private HuoJiangDao huoJiangDao;
    @Autowired
    private PracticeInnovationDao practiceInnovationDao;
    @Autowired
    private ClassLunwenDao classLunwenDao;

    @Override
    public Integer create(HuoJiang huoJiang) {
        return huoJiangDao.create(huoJiang);
    }

    public List<HuoJiang> findByAll(Integer shenhe, String name,String teacherName, Page page){
      return   huoJiangDao.findByAll(shenhe,name,teacherName,page);
    }

    @Override
    public HuoJiang findById(Integer id) {
        return huoJiangDao.findById(id);
    }

    @Override
    public Integer updateHuoJiang(Integer id) {
        return huoJiangDao.updateHuoJiang(id);
    }

    @Override
    public Integer updateShenHe(Integer id, Integer zhuangtai) {
        Integer num=huoJiangDao.updateShenHe(id , zhuangtai);
        if(zhuangtai==1) {
            if (num > 0) {
                HuoJiang huoJiang = huoJiangDao.findById(id);
                ClassLunwen classLunwen = classLunwenDao.findById(id);
                if (huoJiang != null) {
                    if (huoJiang.getStudentIds() != null) {
                        String[] studentIds = huoJiang.getStudentIds().split(",");
                        for (int a = 0; a < studentIds.length; a++) {
                            PracticeInnovation practiceInnovation = practiceInnovationDao.findByPracticeInnovation(Integer.parseInt(studentIds[a]));
                            PracticeInnovation pp = new PracticeInnovation();
                            if (practiceInnovation != null) {
                                pp.setId(practiceInnovation.getId());
                                //判断图片是否需要修改
                                if (huoJiang.getPictureId() != null) {
                                    if (practiceInnovation.getPctreId() != null) {
                                        pp.setPctreId(practiceInnovation.getPctreId() + huoJiang.getPictureId());
                                    } else {
                                        pp.setPctreId(huoJiang.getPictureId());
                                    }
                                }
                                //判断分数是否需要修改
                                if (classLunwen != null) {
                                    pp.setScore(practiceInnovation.getScore() +classLunwen.getScore());
                                }
                                //修改实践
                                practiceInnovationDao.update(pp);
                            } else {
                                pp.setStudentId(Integer.parseInt(studentIds[a]));
                                //添加图片
                                if (huoJiang.getPictureId() != null) {
                                    pp.setPctreId(huoJiang.getPictureId());
                                }
                                //添加分数
                                if (classLunwen != null) {
                                    pp.setScore(classLunwen.getScore());
                                } else {
                                    pp.setScore(0);
                                }
                                //添加实践
                                practiceInnovationDao.create(pp);
                            }
                        }
                    }
                }
            }
        }
        return  num ;
    }
}
