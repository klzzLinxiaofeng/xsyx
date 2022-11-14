package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.HomeToSchoolFeedbackDao;
import platform.education.generalTeachingAffair.model.HomeToSchoolFeedback;
import platform.education.generalTeachingAffair.service.HomeToSchoolFeedbackService;

import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-03 18:32
 */
public class HomeToSchoolFeedbackServiceImpl implements HomeToSchoolFeedbackService {

    private Logger log = LoggerFactory.getLogger(getClass());



    private HomeToSchoolFeedbackDao homeToSchoolFeedbackDao;


    public void setHomeToSchoolFeedbackDao(HomeToSchoolFeedbackDao homeToSchoolFeedbackDao) {
        this.homeToSchoolFeedbackDao = homeToSchoolFeedbackDao;
    }


    @Override
    public List<HomeToSchoolFeedback> findHomeToSchoolFeedbackByCondition(HomeToSchoolFeedback homeToSchoolFeedback, Page page, Order order) {
        List<HomeToSchoolFeedback> entity = null;
        try {
            entity = homeToSchoolFeedbackDao.findHomeToSchoolFeedbackByCondition(homeToSchoolFeedback, page, order);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void abandon(Integer id) {
//        if (entity != null) {
//            //entity.setIsDelete(1);
//            try {
//                //entity.setIsDelete(1);
//                //entity = this.homeToSchoolFeedbackDao.update(entity);
//                //return HomeToSchoolFeedbackService.OPERATE_SUCCESS;
//                this.homeToSchoolFeedbackDao.updateCondition();
//            } catch (Exception e) {
//                log.error("废弃 -> {} 失败，异常信息为 {}", entity.getId(), e);
//                return HomeToSchoolFeedbackService.OPERATE_ERROR;
//            }
//        }
//        return HomeToSchoolFeedbackService.OPERATE_FAIL;
        this.homeToSchoolFeedbackDao.updateDelCondition(id);


    }

    @Override
    public HomeToSchoolFeedback findContentById(Integer id) {
        try {
            return this.homeToSchoolFeedbackDao.findById(id);
        } catch (Exception var3) {
            this.log.info("数据库无存在ID为 {} ", id);
            return null;
        }
    }

    @Override
    public HomeToSchoolFeedback findHomeToSchoolFeedbackByName(String name) {
        try {
            return this.homeToSchoolFeedbackDao.findByName(name);
        } catch (Exception var3) {
            var3.printStackTrace();
            this.log.info("数据库无存在姓名为 {} ", name);
            return null;
        }
    }

    @Override
    public void updateCondition(Integer id) {
        this.homeToSchoolFeedbackDao.updateCondition(id);
    }

    @Override
    public String updateTeacher(Integer id, Integer teacherId) {
         Integer num=homeToSchoolFeedbackDao.updateTeacher(id,teacherId);
         if(num>0){
             return "success";
         }
        return "error";
    }
}
