package platform.education.service.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import platform.education.service.dao.OutSchoolActivityApprovalDao;
import platform.education.service.model.OutSchoolActivity;
import platform.education.service.model.OutSchoolActivityApproval;
import platform.education.service.vo.*;
import platform.education.service.service.OutSchoolActivityService;
import platform.education.service.dao.OutSchoolActivityDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.user.contants.GroupContants;
import platform.education.user.contants.SysDefRole;
import platform.education.user.dao.GroupDao;
import platform.education.user.dao.UserRoleDao;
import platform.education.user.model.Group;
import platform.education.user.model.UserRole;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

import javax.annotation.Resource;

public class OutSchoolActivityServiceImpl implements OutSchoolActivityService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private OutSchoolActivityDao outSchoolActivityDao;
    private OutSchoolActivityApprovalDao outSchoolActivityApprovalDao;
    private UserRoleDao userRoleDao;
    private GroupDao groupDao;

    @Resource
    private FileService fileService;

    public void setOutSchoolActivityDao(OutSchoolActivityDao outSchoolActivityDao) {
        this.outSchoolActivityDao = outSchoolActivityDao;
    }

    public void setOutSchoolActivityApprovalDao(OutSchoolActivityApprovalDao outSchoolActivityApprovalDao) {
        this.outSchoolActivityApprovalDao = outSchoolActivityApprovalDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public OutSchoolActivity findOutSchoolActivityById(Integer id) {
        try {
            return outSchoolActivityDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("??????????????????ID??? {} ", id);
        }
        return null;
    }

    @Override
    public OutSchoolActivity add(OutSchoolActivity outSchoolActivity) {
        if (outSchoolActivity == null) {
            return null;
        }
        Date createDate = outSchoolActivity.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        outSchoolActivity.setCreateDate(createDate);
        outSchoolActivity.setModifyDate(createDate);
        return outSchoolActivityDao.create(outSchoolActivity);
    }

    @Override
    public OutSchoolActivity modify(OutSchoolActivity outSchoolActivity) {
        if (outSchoolActivity == null) {
            return null;
        }
        Date modify = outSchoolActivity.getModifyDate();
        outSchoolActivity.setModifyDate(modify != null ? modify : new Date());
        return outSchoolActivityDao.update(outSchoolActivity);
    }

    @Override
    public void remove(OutSchoolActivity outSchoolActivity) {
        try {
            outSchoolActivityDao.delete(outSchoolActivity);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("????????????????????????ID??? {} ,????????????{}", outSchoolActivity.getId(), e);
            }
        }
    }

    @Override
    public List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page, Order order) {
        return outSchoolActivityDao.findOutSchoolActivityByCondition(outSchoolActivityCondition, page, order);
    }

    @Override
    public List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition) {
        return outSchoolActivityDao.findOutSchoolActivityByCondition(outSchoolActivityCondition, null, null);
    }

    @Override
    public List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page) {
        return outSchoolActivityDao.findOutSchoolActivityByCondition(outSchoolActivityCondition, page, null);
    }

    @Override
    public List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Order order) {
        return outSchoolActivityDao.findOutSchoolActivityByCondition(outSchoolActivityCondition, null, order);
    }

    @Override
    public Long count() {
        return this.outSchoolActivityDao.count(null);
    }

    @Override
    public Long count(OutSchoolActivityCondition outSchoolActivityCondition) {
        return this.outSchoolActivityDao.count(outSchoolActivityCondition);
    }

    @Override
    public List<OutSchoolActivityVo> findOutSchoolActivityVoByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page, Order order) {
        return this.outSchoolActivityDao.findOutSchoolActivityVoByCondition(outSchoolActivityCondition, page, order);
    }

    @Override
    public OutSchoolActivityPictureVo findOutSchoolActivityVoById(Integer id) {
        if (id == null) {
            return new OutSchoolActivityPictureVo();
        }
        OutSchoolActivityPictureVo outSchoolActivityPictureVo = this.outSchoolActivityDao.findOutSchoolActivityVoById(id);
        // ??????????????????
        String accessory = outSchoolActivityPictureVo.getAccessory();
        if (accessory != null && !accessory.isEmpty()) {
            outSchoolActivityPictureVo.setAccessoryUrl(this.fileService.relativePath2HttpUrl(outSchoolActivityPictureVo.getAccessoryUrl()));
        }
        // ????????????????????????
        List<PictureVo> pictureVoList = outSchoolActivityPictureVo.getPictureVoList();
        if (pictureVoList == null || pictureVoList.isEmpty()) {
            return outSchoolActivityPictureVo;
        }
        for (PictureVo pictureVo : pictureVoList) {
            pictureVo.setUrl(this.fileService.relativePath2HttpUrl(pictureVo.getUrl()));
        }
        outSchoolActivityPictureVo.setPictureVoList(pictureVoList);
        return outSchoolActivityPictureVo;
    }

    @Override
    public OutSchoolActivity addActivity(Integer userId, Integer schoolId, String name, String location, Long startTime, Long endTime, MultipartFile file, String description, String appId) {
        if (userId == null || schoolId == null || name == null || name.isEmpty() || location == null || location.isEmpty() || startTime == null || endTime == null || startTime > endTime || appId == null || appId.isEmpty()) {
            throw new RuntimeException("??????????????????");
        }
        OutSchoolActivity outSchoolActivity = new OutSchoolActivity();

        try {
            // ????????????
            if (file != null) {
                String uploadFileName = file.getOriginalFilename();

                FileResult fileResult = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, appId);
                if (fileResult != null && fileResult.getEntityFile() != null && FileStatusCode.UPLOAD_SUCCESS.equals(fileResult.getStatusCode())) {
                    outSchoolActivity.setAccessory(fileResult.getEntityFile().getUuid());
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("????????????????????????");
        }
        try {
            // ???????????????????????????
            outSchoolActivity.setStartTime(new Date(startTime));
            outSchoolActivity.setEndTime(new Date(endTime));
        } catch (Exception e) {
            throw new RuntimeException("??????????????????");
        }
        Date now = new Date();
        outSchoolActivity.setUserId(userId);
        outSchoolActivity.setSchoolId(schoolId);
        outSchoolActivity.setName(name);
        outSchoolActivity.setLocation(location);
        outSchoolActivity.setDescription(description);
        outSchoolActivity.setCreateDate(now);
        outSchoolActivity.setModifyDate(now);
        outSchoolActivity = this.add(outSchoolActivity);

        if (outSchoolActivity == null) {
            this.log.error("OutSchoolActivity is null");
            throw new RuntimeException("??????????????????");
        }

        // ?????????   0=System,1=School,2=Unit
        Group group = this.groupDao.findUnique(schoolId, GroupContants.TYPE_SCHOOL);
        // ?????????????????????
        List<UserRole> userRoleList = this.userRoleDao.findByUserIdAndGroupId(null, group.getId(), SysDefRole.SCHOOL_MANAGER);

        if (userRoleList == null || userRoleList.isEmpty()) {
            this.log.error("List<UserRole> is null");
            throw new RuntimeException("????????????????????????");
        }

        for (UserRole ur : userRoleList) {
            if (ur.getUser() == null) {
                continue;
            }
            OutSchoolActivityApproval outSchoolActivityApproval = new OutSchoolActivityApproval();
            outSchoolActivityApproval.setUserId(ur.getUser().getId());
            outSchoolActivityApproval.setActivityId(outSchoolActivity.getId());
            outSchoolActivityApproval.setStatus(OutSchoolActivityApproval.STAT100);
            outSchoolActivityApproval.setCreateDate(now);
            outSchoolActivityApproval.setModifyDate(now);
            outSchoolActivityApproval = this.outSchoolActivityApprovalDao.create(outSchoolActivityApproval);
            if (outSchoolActivityApproval == null) {
                this.log.error("OutSchoolActivityApproval is null");
                throw new RuntimeException("?????????????????????");
            }
        }
        return outSchoolActivity;
    }

    @Override
    public OutSchoolActivity updateActivity(Integer id, String name, String location, Long startTime, Long endTime, MultipartFile file, String description, String appId) {
        if (id == null) {
            throw new RuntimeException("??????????????????");
        }
        // ????????????
        OutSchoolActivity outSchoolActivity = this.outSchoolActivityDao.findById(id);
        if (outSchoolActivity == null) {
            throw new RuntimeException("??????????????????");
        }
        // ??????????????????
        OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition = new OutSchoolActivityApprovalCondition();
        outSchoolActivityApprovalCondition.setActivityId(id);
        List<OutSchoolActivityApproval> outSchoolActivityApprovalList = this.outSchoolActivityApprovalDao.findOutSchoolActivityApprovalByCondition(outSchoolActivityApprovalCondition, null, null);
        if (outSchoolActivityApprovalList == null || outSchoolActivityApprovalList.isEmpty()) {
            throw new RuntimeException("??????????????????????????????");
        }
        if (outSchoolActivityApprovalList.get(0).getStatus() > OutSchoolActivityApproval.STAT200) {
            throw new RuntimeException("????????????????????????????????????");
        }
        // ??????????????????
        try {
            // ????????????
            if (file != null && appId != null && !appId.isEmpty()) {
                String uploadFileName = file.getOriginalFilename();

                FileResult fileResult = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, appId);
                if (fileResult != null && fileResult.getEntityFile() != null && FileStatusCode.UPLOAD_SUCCESS.equals(fileResult.getStatusCode())) {
                    outSchoolActivity.setAccessory(fileResult.getEntityFile().getUuid());
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("????????????????????????");
        }

        if (name != null && !name.isEmpty()) {
            outSchoolActivity.setName(name);
        }
        if (location != null && !location.isEmpty()) {
            outSchoolActivity.setLocation(location);
        }
        if (startTime != null && endTime != null) {
            if (startTime > endTime) {
                throw new RuntimeException("??????????????????????????????");
            }
            try {
                outSchoolActivity.setStartTime(new Date(startTime));
                outSchoolActivity.setEndTime(new Date(endTime));
            } catch (Exception e) {
                throw new RuntimeException("??????????????????");
            }
        }
        if (startTime != null && endTime == null) {
            if (startTime > outSchoolActivity.getEndTime().getTime()) {
                throw new RuntimeException("??????????????????????????????");
            }
            try {
                outSchoolActivity.setStartTime(new Date(startTime));
            } catch (Exception e) {
                throw new RuntimeException("??????????????????");
            }
        }
        if (startTime == null && endTime != null) {
            if (outSchoolActivity.getStartTime().getTime() > endTime) {
                throw new RuntimeException("??????????????????????????????");
            }
            try {
                outSchoolActivity.setEndTime(new Date(endTime));
            } catch (Exception e) {
                throw new RuntimeException("??????????????????");
            }
        }
        outSchoolActivity.setDescription(description);
        return this.outSchoolActivityDao.update(outSchoolActivity);
    }

    @Override
    public List<OutSchoolActivity> findOutSchoolActivityAndApprovalVoByCondition(OutSchoolActivity act, Page page, Order order) {
        return this.outSchoolActivityDao.findOutSchoolActivityAndApprovalVoByCondition(act, page, order);
    }
}
