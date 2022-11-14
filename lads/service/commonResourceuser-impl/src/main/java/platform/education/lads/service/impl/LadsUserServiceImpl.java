package platform.education.lads.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.lads.dao.CRLearningDesignRelateDao;
import platform.education.lads.model.LadsLastStudyRecord;
import platform.education.lads.service.LadsLastStudyRecordService;
import platform.education.lads.service.LadsLearningdesignService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.vo.LadsLastStudyRecordCondition;
import platform.education.lads.vo.LadsLearningdesignVo;
import platform.education.lads.vo.LadsUserVo;
import platform.education.learningDesign.service.LearningDesignRelateService;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserService;
import platform.service.storage.holder.FileServiceHolder;

/**
 *
 * @author Administrator
 */
public class LadsUserServiceImpl implements LadsUserService {

    private LadsLearningdesignService ladsLearningdesignService;
    private LadsLastStudyRecordService ladsLastStudyRecordService;
    private ProfileService profileService;
    private UserService userService;
    private StudentService studentService;
    private LearningDesignRelateService learningDesignRelateService;
    private CRLearningDesignRelateDao crLearningDesignRelateDao;

    public void setCrLearningDesignRelateDao(CRLearningDesignRelateDao crLearningDesignRelateDao) {
        this.crLearningDesignRelateDao = crLearningDesignRelateDao;
    }

    public void setLearningDesignRelateService(LearningDesignRelateService learningDesignRelateService) {
        this.learningDesignRelateService = learningDesignRelateService;
    }

    public void setLadsLearningdesignService(LadsLearningdesignService ladsLearningdesignService) {
        this.ladsLearningdesignService = ladsLearningdesignService;
    }

    public void setLadsLastStudyRecordService(LadsLastStudyRecordService ladsLastStudyRecordService) {
        this.ladsLastStudyRecordService = ladsLastStudyRecordService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 标记公共资源库的当前登录用户session键
     */
    public final static String CURRENT_USER = "resource_user_info";
    /**
     * 标记当前从哪个app交互到公共资源库的session键
     */
    public final static String RELATE_APP_ID_KEY = "relate_app_id";


    /*
     * 用于接入用户数据的方法
     */
    @Override
    public LadsUserVo getUser(Integer userId) {
        LadsUserVo vo = new LadsUserVo();
        vo.setUserId(userId);
        //获取当前用户的基本信息，假如
        Profile profile = this.profileService.findByUserId(userId);
        User user = this.userService.findUserById(userId);
        if (profile != null) {
            vo.setPhoto(FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(profile.getIcon()));
        }
        vo.setRealName(user.getUserName());
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
        Integer relateIdint =  this.crLearningDesignRelateDao.findRelateIdByLdId(ldId);
        List<Student> stList = this.studentService.findStudentOfTeam(relateIdint);
        if (stList != null && stList.size() > 0) {
            for (Student st : stList) {
                LadsUserVo vo = new LadsUserVo();
                vo.setRealName(st.getName());
                Profile profile = this.profileService.findByUserId(st.getUserId());
                if (profile != null) {
                    vo.setPhoto(profile.getIcon());
                }
                vo.setUserId(st.getUserId());
                voList.add(vo);
            }
        }

        return voList;
    }

    /*
     * 获取当前嵌入系统的学习用户id
     */
    @Override
    public Integer getEmbedUserId(HttpServletRequest request) {
        UserInfo user = (UserInfo) request.getSession().getAttribute(CURRENT_USER);
        if (user != null) {
            return user.getUserId();
        } else {
            return null;
        }
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
    @Override
    public Integer getRelateAppId(HttpServletRequest request) {
        String relateAppId = request.getParameter("relateAppId");
        Integer returnValue;
        if (relateAppId != null && !"".equals(relateAppId)) {
            Integer intValue = Integer.parseInt(relateAppId);
            request.getSession().setAttribute(RELATE_APP_ID_KEY, intValue);
            returnValue = intValue;
        } else {
            returnValue = (Integer) request.getSession().getAttribute(RELATE_APP_ID_KEY);
        }
        return returnValue;
    }
}
