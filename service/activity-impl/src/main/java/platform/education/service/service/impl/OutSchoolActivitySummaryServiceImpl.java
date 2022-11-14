package platform.education.service.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import platform.education.service.dao.OutSchoolActivityApprovalDao;
import platform.education.service.dao.OutSchoolActivitySummaryPicDao;
import platform.education.service.model.OutSchoolActivityApproval;
import platform.education.service.model.OutSchoolActivitySummary;
import platform.education.service.model.OutSchoolActivitySummaryPic;
import platform.education.service.vo.OutSchoolActivityApprovalCondition;
import platform.education.service.vo.OutSchoolActivitySummaryCondition;
import platform.education.service.service.OutSchoolActivitySummaryService;
import platform.education.service.dao.OutSchoolActivitySummaryDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.vo.OutSchoolActivitySummaryVo;
import platform.education.service.vo.PictureVo;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

import javax.annotation.Resource;

public class OutSchoolActivitySummaryServiceImpl implements OutSchoolActivitySummaryService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private OutSchoolActivitySummaryDao outSchoolActivitySummaryDao;
    private OutSchoolActivitySummaryPicDao outSchoolActivitySummaryPicDao;
    private OutSchoolActivityApprovalDao outSchoolActivityApprovalDao;

    @Resource
    private FileService fileService;

    public void setOutSchoolActivitySummaryDao(OutSchoolActivitySummaryDao outSchoolActivitySummaryDao) {
        this.outSchoolActivitySummaryDao = outSchoolActivitySummaryDao;
    }

    public void setOutSchoolActivitySummaryPicDao(OutSchoolActivitySummaryPicDao outSchoolActivitySummaryPicDao) {
        this.outSchoolActivitySummaryPicDao = outSchoolActivitySummaryPicDao;
    }

    public void setOutSchoolActivityApprovalDao(OutSchoolActivityApprovalDao outSchoolActivityApprovalDao) {
        this.outSchoolActivityApprovalDao = outSchoolActivityApprovalDao;
    }

    @Override
    public OutSchoolActivitySummary findOutSchoolActivitySummaryById(Integer id) {
        try {
            return outSchoolActivitySummaryDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public OutSchoolActivitySummary add(OutSchoolActivitySummary outSchoolActivitySummary) {
        if (outSchoolActivitySummary == null) {
            return null;
        }
        Date createDate = outSchoolActivitySummary.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        outSchoolActivitySummary.setCreateDate(createDate);
        outSchoolActivitySummary.setModifyDate(createDate);
        return outSchoolActivitySummaryDao.create(outSchoolActivitySummary);
    }

    @Override
    public OutSchoolActivitySummary modify(OutSchoolActivitySummary outSchoolActivitySummary) {
        if (outSchoolActivitySummary == null) {
            return null;
        }
        Date modify = outSchoolActivitySummary.getModifyDate();
        outSchoolActivitySummary.setModifyDate(modify != null ? modify : new Date());
        return outSchoolActivitySummaryDao.update(outSchoolActivitySummary);
    }

    @Override
    public void remove(OutSchoolActivitySummary outSchoolActivitySummary) {
        try {
            outSchoolActivitySummaryDao.delete(outSchoolActivitySummary);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", outSchoolActivitySummary.getId(), e);
            }
        }
    }

    @Override
    public List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition, Page page, Order order) {
        return outSchoolActivitySummaryDao.findOutSchoolActivitySummaryByCondition(outSchoolActivitySummaryCondition, page, order);
    }

    @Override
    public List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition) {
        return outSchoolActivitySummaryDao.findOutSchoolActivitySummaryByCondition(outSchoolActivitySummaryCondition, null, null);
    }

    @Override
    public List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition, Page page) {
        return outSchoolActivitySummaryDao.findOutSchoolActivitySummaryByCondition(outSchoolActivitySummaryCondition, page, null);
    }

    @Override
    public List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition, Order order) {
        return outSchoolActivitySummaryDao.findOutSchoolActivitySummaryByCondition(outSchoolActivitySummaryCondition, null, order);
    }

    @Override
    public Long count() {
        return this.outSchoolActivitySummaryDao.count(null);
    }

    @Override
    public Long count(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition) {
        return this.outSchoolActivitySummaryDao.count(outSchoolActivitySummaryCondition);
    }

    @Override
    public OutSchoolActivitySummary addActivitySummary(Integer activityId, String summary, List<MultipartFile> files, String appId) {
        if (activityId == null || summary == null || summary.isEmpty() || files == null || files.isEmpty() || appId == null || appId.isEmpty()) {
            throw new RuntimeException("必选参数错误");
        }
        // 是否在总结状态
        OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition = new OutSchoolActivityApprovalCondition();
        outSchoolActivityApprovalCondition.setActivityId(activityId);
        outSchoolActivityApprovalCondition.setStatus(OutSchoolActivityApproval.STAT211);
        List<OutSchoolActivityApproval> outSchoolActivityApprovalList = this.outSchoolActivityApprovalDao.findOutSchoolActivityApprovalByCondition(outSchoolActivityApprovalCondition, null, null);
        if (outSchoolActivityApprovalList == null || outSchoolActivityApprovalList.isEmpty()) {
            this.log.error("OutSchoolActivityApproval is null or Wrong status");
            throw new RuntimeException("找不到该活动");
        }
        // 查找是否存在活动总结
        OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition = new OutSchoolActivitySummaryCondition();
        outSchoolActivitySummaryCondition.setActivityId(activityId);
        List<OutSchoolActivitySummary> outSchoolActivitySummaryList = this.outSchoolActivitySummaryDao.findOutSchoolActivitySummaryByCondition(outSchoolActivitySummaryCondition, null, null);
        OutSchoolActivitySummary outSchoolActivitySummary;
        if (outSchoolActivitySummaryList != null && outSchoolActivitySummaryList.size() > 0) {
            outSchoolActivitySummary = outSchoolActivitySummaryList.get(0);
            BeanUtils.copyProperties(outSchoolActivitySummary, outSchoolActivitySummaryCondition);
        }

        Date now = new Date();
        outSchoolActivitySummaryCondition.setContent(summary);
        outSchoolActivitySummaryCondition.setCreateDate(now);
        outSchoolActivitySummaryCondition.setModifyDate(now);
        outSchoolActivitySummary = this.add(outSchoolActivitySummaryCondition);
        if (outSchoolActivitySummary == null) {
            this.log.error("OutSchoolActivitySummary is null");
            throw new RuntimeException("提交活动总结失败");
        }
        // 查找是否存在活动总结图片
        List<OutSchoolActivitySummaryPic> outSchoolActivitySummaryPicList = this.outSchoolActivitySummaryPicDao.findOutSchoolActivitySummaryPicByActivityId(activityId);
        for (OutSchoolActivitySummaryPic outSchoolActivitySummaryPic : outSchoolActivitySummaryPicList) {
            this.outSchoolActivitySummaryPicDao.delete(outSchoolActivitySummaryPic);
        }

        OutSchoolActivitySummaryPic outSchoolActivitySummaryPic;
        for (MultipartFile file : files) {
            String uploadFileName = file.getOriginalFilename();
            FileResult fileResult;
            try {
                fileResult = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, appId);
            } catch (IOException ioe) {
                throw new RuntimeException("活动总结图片上传异常");
            }
            if (fileResult == null || fileResult.getEntityFile() == null || !FileStatusCode.UPLOAD_SUCCESS.equals(fileResult.getStatusCode())) {
                throw new RuntimeException("活动总结图片上传失败");
            }
            // 校外活动总结图片
            outSchoolActivitySummaryPic = new OutSchoolActivitySummaryPic();
            outSchoolActivitySummaryPic.setSummaryId(outSchoolActivitySummary.getId());
            outSchoolActivitySummaryPic.setPicture(fileResult.getEntityFile().getUuid());
            outSchoolActivitySummaryPic.setCreateDate(now);
            outSchoolActivitySummaryPic.setModifyDate(now);
            outSchoolActivitySummaryPic = this.outSchoolActivitySummaryPicDao.create(outSchoolActivitySummaryPic);
            if (outSchoolActivitySummaryPic == null) {
                this.log.error("OutSchoolActivitySummaryPic is null");
                throw new RuntimeException("活动总结图片保存失败");
            }
        }
        // 更新状态
        for (OutSchoolActivityApproval outSchoolActivityApproval : outSchoolActivityApprovalList) {
            outSchoolActivityApproval.setStatus(OutSchoolActivityApproval.STAT221);
            this.outSchoolActivityApprovalDao.update(outSchoolActivityApproval);
        }
        return outSchoolActivitySummary;
    }

    @Override
    public OutSchoolActivitySummaryVo findOutSchoolActivitySummaryVoByActivityId(Integer activityId) {
        if (activityId == null) {
            return new OutSchoolActivitySummaryVo();
        }
        OutSchoolActivitySummaryVo outSchoolActivitySummaryVo = this.outSchoolActivitySummaryDao.findOutSchoolActivitySummaryVoByActivityId(activityId);
        if (outSchoolActivitySummaryVo == null) {
            return new OutSchoolActivitySummaryVo();
        }
        List<PictureVo> pictureVoList = outSchoolActivitySummaryVo.getPictureVoList();
        if (pictureVoList == null || pictureVoList.isEmpty()) {
            return outSchoolActivitySummaryVo;
        }
        for (PictureVo pictureVo : pictureVoList) {
            pictureVo.setUrl(this.fileService.relativePath2HttpUrl(pictureVo.getUrl()));
        }
        outSchoolActivitySummaryVo.setPictureVoList(pictureVoList);
        return outSchoolActivitySummaryVo;
    }

}
