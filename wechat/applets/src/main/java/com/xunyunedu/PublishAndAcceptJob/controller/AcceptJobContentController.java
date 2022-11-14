package com.xunyunedu.PublishAndAcceptJob.controller;

import cn.hutool.core.util.StrUtil;
import com.xunyunedu.PublishAndAcceptJob.pojo.PublishAndAcceptJobContentPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import com.xunyunedu.PublishAndAcceptJob.service.AcceptJobContentService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Set;

/**
 * 学生接收作业
 *
 * @author lee
 * @Date 2020/12/07
 */
@RestController
@RequestMapping("/teamStudent")
public class AcceptJobContentController {

    @Autowired
    private AcceptJobContentService acceptJobContent;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    /**
     * 查询所有作业
     *
     * @param teamId
     * @return
     */
    @GetMapping("/getStudentContentAll")
    @Authorization
    public ApiResult getStudentContentAll(@RequestParam Integer teamId) {
        if (teamId == null || teamId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        ApiResult apiResult = new ApiResult(ResultCode.SUCCESS);
        apiResult.setData(acceptJobContent.getAcceptJobContentAll(teamId));
        return apiResult;
    }

    /**
     * 学生接收作业 指定科目
     *
     * @Param teamId
     * @Param subjectId
     * @Return
     */
    @GetMapping("/getStudentContent")
    @Authorization
    public ApiResult getStudentContent(@RequestParam Integer teamId, @RequestParam String subjectId) {
        if (teamId == null || subjectId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        ApiResult apiResult = new ApiResult(ResultCode.SUCCESS);
        apiResult.setData(acceptJobContent.getContentByStudentIdAndSubjectId(teamId, subjectId));
        return apiResult;
    }

    /**
     * 作业详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getContentDetails")
    @Authorization
    public ApiResult getContentDetails(@RequestParam Integer id) {
        if (id == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PublishAndAcceptJobContentPojo contentDetails = acceptJobContent.getContentDetails(id);
        if (contentDetails == null) {
            throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
        }
        String uuid = contentDetails.getPictureUUID();
        // 获取文件的UUID,根据uuid查询文件的详细地址
        if (!StrUtil.hasEmpty(uuid)) {
            String[] split = uuid.split(",");
            if (split != null && split.length > 0) {
                ArrayList<String> list = new ArrayList<>();

                for (int i = 0; i < split.length; i++) {
                    EntityFile entityFile = uploaderDao.findFileByUUID(split[i]);
                    if (entityFile != null) {
                        list.add(ftpUtils.relativePath2HttpUrl(entityFile));
                    }
                }
                contentDetails.setPictureUUIDs(list);
            }
        }
        return new ApiResult(ResultCode.SUCCESS, contentDetails);
    }

    /**
     * 获得学科信息
     *
     * @param subjectPojo
     * @return
     */
    @GetMapping("/getSubject")
    @Authorization
    public ApiResult getSubject(SubjectPojo subjectPojo) {
        if (subjectPojo == null || subjectPojo.getSchoolId() == null || subjectPojo.getTeamId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        Set<SubjectPojo> set = acceptJobContent.getSubject(subjectPojo);
        return new ApiResult(ResultCode.SUCCESS, set);
    }
}
