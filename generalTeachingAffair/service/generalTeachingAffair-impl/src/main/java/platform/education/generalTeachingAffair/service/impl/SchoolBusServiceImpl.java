package platform.education.generalTeachingAffair.service.impl;

import org.springframework.util.StringUtils;
import platform.education.generalTeachingAffair.dao.SchoolBusDao;
import platform.education.generalTeachingAffair.model.ParentStudentBus;
import platform.education.generalTeachingAffair.model.SchoolBusMangerVo;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.education.generalTeachingAffair.service.SchoolBusService;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/3/8 14:57
 * @Description:
 */
public class SchoolBusServiceImpl implements SchoolBusService {

    private SchoolBusDao schoolBusDao;

    private HttpService httpService;

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public void setSchoolBusDao(SchoolBusDao schoolBusDao) {
        this.schoolBusDao = schoolBusDao;
    }

    /**
     * 新增校车的学生
     *
     * @param ids
     * @param schoolBusMangerVo
     * @param isSend            是否开启推送学生信息到校车1:是 0： 否
     * @param url
     * @param url2
     * @return
     */
    @Override
    public SchoolBusMangerVo creates(String ids, SchoolBusMangerVo schoolBusMangerVo, Integer isSend, String url, String url2) {
        String[] split = ids.split(",");
        if (split == null || split.length == 0) {
            return null;
        }
        // 新增之前没有添加过的学生
        schoolBusDao.createStuBusManger(split, schoolBusMangerVo);
        // 发送用户信息到校车
        if (isSend == 1 && !StringUtils.isEmpty(url) && !StringUtils.isEmpty(url2)) {
            httpService.syncSendShoolBusStu(url, url2);
        }
        return schoolBusMangerVo;
    }


    @Override
    public String abandonTeacher(String ids) {
        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for(int a=0;a<split.length;a++){
                schoolBusDao.deleteBatch(split[a]);
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }


    @Override
    public void update(List<ParentStudentBus> list, Integer systemId) {
        if (list != null && list.size() > 0) {
            Integer[] stuIds = new Integer[list.size()];
            for (int i = 0; i < list.size(); i++) {
                stuIds[i] = list.get(i).getId();
            }
            schoolBusDao.updateSendState(systemId, stuIds);
        }
    }
}
