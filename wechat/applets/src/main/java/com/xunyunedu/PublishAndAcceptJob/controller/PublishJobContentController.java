package com.xunyunedu.PublishAndAcceptJob.controller;

import cn.hutool.core.util.StrUtil;
import com.xunyunedu.PublishAndAcceptJob.param.PublishAndAcceptJobContentInsertParam;
import com.xunyunedu.PublishAndAcceptJob.pojo.PublishAndAcceptJobContentPojo;
import com.xunyunedu.PublishAndAcceptJob.service.PublishJobContentService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.logger.vo.Loggers;
import com.xunyunedu.util.ftp.FtpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 老师发布作业
 * 内容新增、查询、修改、删除
 *
 * @author lee
 * @Date 2020/12/07
 */
@RestController
@RequestMapping("/TeacherPublishContent")
@Api(value = "/ZyLogin", description = "作业发布")
public class PublishJobContentController {

    @Autowired
    private PublishJobContentService publishJobContentService;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;
    @Autowired
    private LoggerService loggerService;
    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 判断当前教师是否分配课程
     *
     * @param teacherId
     * @return
     */
    @GetMapping("/getTeacherClassStatus")
    @Authorization
    public ApiResult getTeacherClassStatus(Integer teacherId, Integer schoolId) {
        if (teacherId == null || schoolId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        Integer status = publishJobContentService.getTeacherStatus(teacherId, schoolId);
        if (status == 0){
            return new ApiResult(ResultCode.NO_CLASS_FOUND);
        }
        return new ApiResult(ResultCode.SUCCESS);
    }


    /**
     * 新增内容
     *
     * @param param 内容信息
     * @return
     */
    @PostMapping("/addContent")
    /*@Authorization*/
    @ApiOperation(value = "作业发布", httpMethod = "POST")
    public ApiResult addContent(@RequestBody PublishAndAcceptJobContentInsertParam param) {
        if (param == null || param.getTeacherId() == null || param.getTeamIds() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String uuids = null;
        String pictureUUIDs = param.getPictureUUIDs();

        List<Integer> listTeamids = param.getTeamIds();

        for(Integer aa:listTeamids){
            //发布到多个班级
            PublishAndAcceptJobContentPojo pojo = hetAll(param);
            pojo.setPictureUUID(pictureUUIDs);
            pojo.setTeamId(aa.toString());
            publishJobContentService.addPublishJobContent(pojo);
        }

        Loggers logger = new Loggers();
        logger.setCaozuoId(param.getTeacherId());

        List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile,pt.name as teacherName from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+param.getTeacherId());
        logger.setName(teacher.get(0).get("teacherName").toString());
        logger.setUsername(teacher.get(0).get("user_name").toString());
        logger.setMobile(teacher.get(0).get("mobile").toString());
        logger.setMokuaiName("作业管理");
        logger.setType(1);
        List<Map<String,Object>> team=basicSQLService.find("select name from pj_team where id="+listTeamids.get(0));

        List<Map<String,Object>> subject=basicSQLService.find("select name from pj_subject  where id="+param.getSubjectId());

        logger.setMessage("新增"+team.get(0).get("name")+"的"+subject.get(0).get("name")+"的作业");
        List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        logger.setSchoolYear(termInfo.get(0).get("school_year").toString());
        logger.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
        loggerService.create(logger);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 修改内容
     *
     * @param publishJobContentPojo 内容信息
     * @return
     */
    @PostMapping("/updateContent")
    /*@Authorization*/
    @ApiOperation(value = "作业修改", httpMethod = "POST")
    public ApiResult updateContent(@RequestBody PublishAndAcceptJobContentPojo publishJobContentPojo) {
        if (publishJobContentPojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        System.out.println(publishJobContentPojo.toString());
           /* PublishAndAcceptJobContentPojo pojo = new PublishAndAcceptJobContentPojo();
            pojo.setPictureUUID(publishJobContentPojo.getPictureUUID());
            pojo.setTeamId(publishJobContentPojo.getTeamIds().get(0).toString());
            pojo.setSubjectId(publishJobContentPojo.getSubjectId());
            pojo.setContent(publishJobContentPojo.getContent());*/
            publishJobContentService.updatePublishJobContent(publishJobContentPojo);
        Loggers logger = new Loggers();
        logger.setCaozuoId(publishJobContentPojo.getTeacherId());

        List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile,pt.name as teacherName from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+publishJobContentPojo.getTeacherId());
        logger.setName(teacher.get(0).get("teacherName").toString());
        logger.setUsername(teacher.get(0).get("user_name").toString());
        logger.setMobile(teacher.get(0).get("mobile").toString());
        logger.setMokuaiName("作业管理");
        logger.setType(2);
        List<Map<String,Object>> team=basicSQLService.find("select name from pj_team where id="+publishJobContentPojo.getTeamIds().get(0).toString());

        List<Map<String,Object>> subject=basicSQLService.find("select name from pj_subject  where id="+publishJobContentPojo.getSubjectId());

        logger.setMessage("修改"+team.get(0).get("name")+"的"+subject.get(0).get("name")+"的作业");
        List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        logger.setSchoolYear(termInfo.get(0).get("school_year").toString());
        logger.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
        loggerService.create(logger);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 删除内容
     *
     * @param id 内容id
     * @return
     */
    @PostMapping("/deleteContent")
    @ApiOperation(value = "删除", httpMethod = "GET")
    public ApiResult deleteContent(@RequestParam Integer id) {
        if (id == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        publishJobContentService.deleteByPrimaryKey(id);
        PublishAndAcceptJobContentPojo publishJobContentPojo=publishJobContentService.getContentDetails(id);
        Loggers logger = new Loggers();
        logger.setCaozuoId(publishJobContentPojo.getTeacherId());
        String [] teams = publishJobContentPojo.getTeamId().split(",");
        List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile,pt.name as teacherName from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+publishJobContentPojo.getTeacherId());
        logger.setName(teacher.get(0).get("teacherName").toString());
        logger.setUsername(teacher.get(0).get("user_name").toString());
        logger.setMobile(teacher.get(0).get("mobile").toString());
        logger.setMokuaiName("作业管理");
        logger.setType(2);
        List<Map<String,Object>> team=basicSQLService.find("select name from pj_team where id="+teams[0]);

        List<Map<String,Object>> subject=basicSQLService.find("select name from pj_subject  where id="+publishJobContentPojo.getSubjectId());

        logger.setMessage("删除"+team.get(0).get("name")+"的"+subject.get(0).get("name")+"的作业");
        List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        logger.setSchoolYear(termInfo.get(0).get("school_year").toString());
        logger.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
        loggerService.create(logger);
        return new ApiResult(ResultCode.SUCCESS);
    }


    //重写
    /**
     * 2022-01-10弃用
     *
     * @param id 内容id
     * @return
     */
    @GetMapping("/getDetailsContent")
    @Authorization
    public ApiResult getDetailsContent(@RequestParam Integer id) {
        if (id == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PublishAndAcceptJobContentPojo contentDetails = publishJobContentService.getContentDetails(id);
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
     * 历史
     *
     * @Param teacherId
     * @Param subjectId
     * @Return
     */
    @GetMapping("/getContent")
    @ApiOperation(value = "历史", httpMethod = "GET")
    public ApiResult getContent(@RequestParam Integer teacherId) {
        if (teacherId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<PublishAndAcceptJobContentPojo> contentByTeacherIdAndSubjectId = publishJobContentService.getContentByTeacherIdAndSubjectId(teacherId);
        if (!contentByTeacherIdAndSubjectId.isEmpty()) {
            for (int i = 0; i < contentByTeacherIdAndSubjectId.size(); i++) {
                // 获取文件的UUID,根据uuid查询文件的详细地址
                if (!StrUtil.hasEmpty(contentByTeacherIdAndSubjectId.get(i).getPictureUUID())) {
                    EntityFile entityFile = uploaderDao.findFileByUUID(contentByTeacherIdAndSubjectId.get(i).getPictureUUID());
                    if (entityFile != null) {
                        contentByTeacherIdAndSubjectId.get(i).setHttpUrl(ftpUtils.relativePath2HttpUrl(entityFile));
                        contentByTeacherIdAndSubjectId.get(i).setFileName(entityFile.getFileName());
                    }
                }
            }
        }
        ApiResult apiResult = new ApiResult(ResultCode.SUCCESS);
        apiResult.setData(contentByTeacherIdAndSubjectId);
        return apiResult;
    }


    public PublishAndAcceptJobContentPojo hetAll(PublishAndAcceptJobContentInsertParam param){
        PublishAndAcceptJobContentPojo publishAndAcceptJobContentPojo=new PublishAndAcceptJobContentPojo();
        publishAndAcceptJobContentPojo.setTeacherId(param.getTeacherId());
        publishAndAcceptJobContentPojo.setPictureUUID(param.getPictureUUIDs());
        publishAndAcceptJobContentPojo.setSubjectId(param.getSubjectId());
        publishAndAcceptJobContentPojo.setContent(param.getContent());
        publishAndAcceptJobContentPojo.setSchoolId(param.getSchoolId());
        publishAndAcceptJobContentPojo.setGradeId(param.getGradeId());
        publishAndAcceptJobContentPojo.setSchoolTrem(param.getSchoolTrem());
        publishAndAcceptJobContentPojo.setSchoolYear(param.getSchoolYear());
        publishAndAcceptJobContentPojo.setIsHome(param.getIsHome());
        return publishAndAcceptJobContentPojo;
    }
}
