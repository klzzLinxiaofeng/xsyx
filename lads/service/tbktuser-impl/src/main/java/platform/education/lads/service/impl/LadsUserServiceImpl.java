package platform.education.lads.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import platform.education.lads.model.LadsLastStudyRecord;
import platform.education.lads.service.LadsLastStudyRecordService;
import platform.education.lads.service.LadsLearningdesignService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.vo.LadsLastStudyRecordCondition;
import platform.education.lads.vo.LadsLearningdesignVo;
import platform.education.lads.vo.LadsUserVo;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;

/**
 *
 * @author Administrator
 */
public class LadsUserServiceImpl implements LadsUserService {

    private LadsLearningdesignService ladsLearningdesignService;
    private LadsLastStudyRecordService ladsLastStudyRecordService;
    private ProfileService profileService;

    public void setLadsLearningdesignService(LadsLearningdesignService ladsLearningdesignService) {
        this.ladsLearningdesignService = ladsLearningdesignService;
    }

    public void setLadsLastStudyRecordService(LadsLastStudyRecordService ladsLastStudyRecordService) {
        this.ladsLastStudyRecordService = ladsLastStudyRecordService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    /*
     * 用于接入用户数据的方法
     */
    @Override
    public LadsUserVo getUser(Integer userId) {
        LadsUserVo vo = new LadsUserVo();
        vo.setUserId(userId);
        //获取当前用户的基本信息，假如
        Profile profile = this.profileService.findByUserId(userId);
        vo.setPhoto(profile.getIcon());
        vo.setRealName("暂时不能获取真实姓名");
        return vo;
    }


    /*
     * 用于接入外部课程系统的方法
     * embedSystem 嵌入的系统
     * embedId 嵌入id
     */
    @Override
    public LadsLearningdesignVo embedLesson(String embedId) {
        LadsLearningdesignVo vo = new LadsLearningdesignVo();

        //同步课堂项目业务
//            Learningdesign ld = this.tbktLearningdesignService.findLearningdesignByUuid(embedId);
//            vo.setUserId(ld.getUserId());
//            vo.setTitle(ld.getTitle());
//            vo.setLdId(ld.getLadsId());
        return vo;
    }

    /*
     * 用于接入外部课程发布学习用户的方法
     * 参数是 ldId
     */
    @Override
    public List<LadsUserVo> getStudyUserList(String ldId) {
        List<LadsUserVo> voList = new ArrayList();

        //同步课堂项目业务
        List embedIdList = this.ladsLearningdesignService.findUserIdByLdId(ldId);
//            if (embedIdList.size() > 0) {
//                MoocLesson ml = this.hibernateBaseDao.findUniqueBy(MoocLesson.class, "id", embedIdList.get(0));
//                if (ml != null) {
//                    List<MoocLessonStudent> mlsList = this.hibernateBaseDao.find("from MoocLessonStudent mls where mls.moocLesson.id =?", ml.getId());
//                    for (MoocLessonStudent mls : mlsList) {
//                        LadsUserVo vo = new LadsUserVo();
//                        vo.setRealName(mls.getCenterUser().getRealname());
//                        vo.setPhoto(mls.getCenterUser().getPhotopath());
//                        vo.setUserId(mls.getCenterUser().getUserId());
//                        voList.add(vo);
//                    }
//                }
//            }
        return voList;
    }

    /*
     * 获取当前嵌入系统的学习用户id
     * 参数是 sysType,如果不满足需求,可在sysType后加上参数,用|隔开
     */
    @Override
    public Integer getEmbedUserId(HttpServletRequest request) {

        return null;
    }

    /*
     * 获取某用户最后一次学习的记录
     * 参数是 ldId userId
     */
    @Override
    public LadsLastStudyRecord getLastStudyRecord(String ldId, Integer userId) {
        LadsLastStudyRecordCondition lsrc = new LadsLastStudyRecordCondition();
        lsrc.setUserId(userId);
        lsrc.setLdid(ldId);
        List<LadsLastStudyRecord> list = this.ladsLastStudyRecordService.findLadsLastStudyRecordByCondition(lsrc);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /*
     * 获取当前嵌入系统的appId
     */
    public Integer getRelateAppId(HttpServletRequest request) {
        //暂时先直接写出同步课堂项目的appId
        return 7;
    }
}
