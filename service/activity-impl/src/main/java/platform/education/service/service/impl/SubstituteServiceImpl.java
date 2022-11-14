package platform.education.service.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import platform.education.service.model.Substitute;
import platform.education.service.vo.*;
import platform.education.service.service.SubstituteService;
import platform.education.service.dao.SubstituteDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

import javax.annotation.Resource;

public class SubstituteServiceImpl implements SubstituteService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private SubstituteDao substituteDao;

    @Resource
    private FileService fileService;

    public void setSubstituteDao(SubstituteDao substituteDao) {
        this.substituteDao = substituteDao;
    }

    @Override
    public Substitute findSubstituteById(Integer id) {
        try {
            return substituteDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public Substitute add(Substitute substitute) {
        if (substitute == null) {
            return null;
        }
        Date createDate = substitute.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        substitute.setCreateDate(createDate);
        substitute.setModifyDate(createDate);
        return substituteDao.create(substitute);
    }

    @Override
    public Substitute modify(Substitute substitute) {
        if (substitute == null) {
            return null;
        }
        Date modify = substitute.getModifyDate();
        substitute.setModifyDate(modify != null ? modify : new Date());
        return substituteDao.update(substitute);
    }

    @Override
    public void remove(Substitute substitute) {
        try {
            substituteDao.delete(substitute);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", substitute.getId(), e);
            }
        }
    }

    @Override
    public List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition, Page page, Order order) {
        return substituteDao.findSubstituteByCondition(substituteCondition, page, order);
    }

    @Override
    public List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition) {
        return substituteDao.findSubstituteByCondition(substituteCondition, null, null);
    }

    @Override
    public List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition, Page page) {
        return substituteDao.findSubstituteByCondition(substituteCondition, page, null);
    }

    @Override
    public List<Substitute> findSubstituteByCondition(SubstituteCondition substituteCondition, Order order) {
        return substituteDao.findSubstituteByCondition(substituteCondition, null, order);
    }

    @Override
    public Long count() {
        return this.substituteDao.count(null);
    }

    @Override
    public Long count(SubstituteCondition substituteCondition) {
        return this.substituteDao.count(substituteCondition);
    }

    @Override
    public Substitute addSubstitute(Integer userId, Integer schoolId, Integer receiver, Long startTime, Long endTime, String description, String account, String password, MultipartFile file, String appId) {
        if (userId == null || schoolId == null || receiver == null || startTime == null || endTime == null || startTime > endTime || description == null || description.isEmpty() || account == null || account.isEmpty() || password == null || password.isEmpty() || appId == null || appId.isEmpty()) {
            throw new RuntimeException("必选参数错误");
        }

        if (userId.equals(receiver)) {
            throw new RuntimeException("代课人错误");
        }

        Substitute substitute = new Substitute();
        try {
            // 活动附件
            if (file != null) {
                String uploadFileName = file.getOriginalFilename();

                FileResult fileResult = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, appId);
                if (fileResult != null && fileResult.getEntityFile() != null && FileStatusCode.UPLOAD_SUCCESS.equals(fileResult.getStatusCode())) {
                    substitute.setAccessory(fileResult.getEntityFile().getUuid());
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("代课附件上传异常");
        }
        try {
            // 活动开始与结束时间
            substitute.setStartTime(new Date(startTime));
            substitute.setEndTime(new Date(endTime));
        } catch (Exception e) {
            throw new RuntimeException("时间转换异常");
        }
        Date now = new Date();
        substitute.setUserId(userId);
        substitute.setSchoolId(schoolId);
        substitute.setReceiver(receiver);
        substitute.setStatus(Substitute.STAT100);
        substitute.setDescription(description);
        substitute.setAccount(account);
        substitute.setPassword(password);
        substitute.setCreateDate(now);
        substitute.setModifyDate(now);
        substitute = this.add(substitute);

        if (substitute == null) {
            this.log.error("Substitute is null");
            throw new RuntimeException("申请代课失败");
        }
        return substitute;
    }

    @Override
    public List<ExaminerVo> findExaminerVoByCondition(SubstituteCondition substituteCondition, Page page, Order order) {
        return this.substituteDao.findExaminerVoByCondition(substituteCondition, page, order);
    }

    @Override
    public List<ApprovalVo> findSubstituteGetStatusBySchoolId(Integer schoolId) {
        if (schoolId == null) {
            return new ArrayList<>();
        }
        return this.substituteDao.findSubstituteGetStatusBySchoolId(schoolId);
    }

    @Override
    public Object updateSubstitute(Integer id, Integer userId, Integer status, String feedback) {
        if (id == null || userId == null || status == null) {
            throw new RuntimeException("必选参数错误");
        }
        // 查找是否存在
        SubstituteCondition substituteCondition = new SubstituteCondition();
        substituteCondition.setId(id);
        substituteCondition.setReceiver(userId);
        substituteCondition.setStatus(Substitute.STAT100);
        if (this.substituteDao.count(substituteCondition) <= 0) {
            throw new RuntimeException("找不到相关数据");
        }
        // 驳回
        if (status == Substitute.STAT200) {
            substituteCondition.setStatus(status);
            substituteCondition.setFeedback(feedback);
            Substitute substitute = this.substituteDao.update(substituteCondition);
            SenderVo senderVo = new SenderVo();
            BeanUtils.copyProperties(substitute, senderVo);
            return senderVo;
        }
        // 同意
        if (status == Substitute.STAT201) {
            substituteCondition.setStatus(status);
            substituteCondition.setFeedback(feedback);
            Substitute substitute = this.substituteDao.update(substituteCondition);
            ReceiverVo receiverVo = new ReceiverVo();
            BeanUtils.copyProperties(substitute, receiverVo);
            return receiverVo;
        }
        throw new RuntimeException("状态参数错误");
    }

    @Override
    public List<ExaminerVo> findSenderByUserIdAndSchoolId(Integer userId, Integer schoolId, Integer pageSize, Integer currentPage) {
        if (userId == null || schoolId == null) {
            throw new RuntimeException("必选参数错误");
        }
        SubstituteCondition substituteCondition = new SubstituteCondition();
        substituteCondition.setUserId(userId);
        substituteCondition.setSchoolId(schoolId);

        Page page = new Page();
        if (pageSize != null && pageSize > 0) {
            page.setPageSize(pageSize);
        }
        if (currentPage != null && currentPage > 0) {
            page.setCurrentPage(currentPage);
        }

        return this.substituteDao.findExaminerVoByCondition(substituteCondition, page, Order.desc("create_date"));
    }

    @Override
    public ReceiverVo findSenderByIdAndUserIdAndSchoolId(Integer id, Integer userId, Integer schoolId) {
        if (id == null || userId == null || schoolId == null) {
            throw new RuntimeException("必选参数错误");
        }
        SubstituteVo substituteVo = this.substituteDao.findSubstituteVoById(id);
        if (substituteVo == null || !substituteVo.getUserId().equals(userId) || !substituteVo.getSchoolId().equals(schoolId)) {
            throw new RuntimeException("找不到相关数据");
        }
        substituteVo.setAccessoryUrl(this.fileService.relativePath2HttpUrl(substituteVo.getAccessoryUrl()));
        ReceiverVo receiverVo = new ReceiverVo();
        BeanUtils.copyProperties(substituteVo, receiverVo);
        return receiverVo;
    }

    @Override
    public List<ExaminerVo> findReceiverByUserIdAndSchoolId(Integer userId, Integer schoolId, Integer pageSize, Integer currentPage) {
        if (userId == null || schoolId == null) {
            throw new RuntimeException("必选参数错误");
        }
        SubstituteCondition substituteCondition = new SubstituteCondition();
        substituteCondition.setReceiver(userId);
        substituteCondition.setSchoolId(schoolId);

        Page page = new Page();
        if (pageSize != null && pageSize > 0) {
            page.setPageSize(pageSize);
        }
        if (currentPage != null && currentPage > 0) {
            page.setCurrentPage(currentPage);
        }

        return this.substituteDao.findExaminerVoByCondition(substituteCondition, page, Order.desc("create_date"));
    }

    @Override
    public Object findReceiverByIdAndUserIdAndSchoolId(Integer id, Integer userId, Integer schoolId) {
        if (id == null || userId == null || schoolId == null) {
            throw new RuntimeException("必选参数错误");
        }
        SubstituteVo substituteVo = this.substituteDao.findSubstituteVoById(id);
        if (substituteVo == null || !substituteVo.getReceiver().equals(userId) || !substituteVo.getSchoolId().equals(schoolId)) {
            throw new RuntimeException("找不到相关数据");
        }
        substituteVo.setAccessoryUrl(this.fileService.relativePath2HttpUrl(substituteVo.getAccessoryUrl()));
        // 驳回
        if (substituteVo.getStatus() == Substitute.STAT200) {
            SenderVo senderVo = new SenderVo();
            BeanUtils.copyProperties(substituteVo, senderVo);
            return senderVo;
        }
        // 同意
        if (substituteVo.getStatus() == Substitute.STAT201) {
            ReceiverVo receiverVo = new ReceiverVo();
            BeanUtils.copyProperties(substituteVo, receiverVo);
            return receiverVo;
        }
        return null;
    }

}
