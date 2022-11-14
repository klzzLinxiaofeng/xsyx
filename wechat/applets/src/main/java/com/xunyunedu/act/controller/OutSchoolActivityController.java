package com.xunyunedu.act.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.pojo.OutSchoolActivity;
import com.xunyunedu.act.service.OutSchoolActivityService;
import com.xunyunedu.act.service.OutSchoolActivityService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.file.service.ResEntityFileService;
import com.xunyunedu.util.ftp.FtpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 校外活动
 */
@RestController
@RequestMapping("/act/schoolOut")
public class OutSchoolActivityController {

    @Autowired
    private OutSchoolActivityService service;
    @Autowired
    private FtpUtils ftpUtils;
    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    private UploaderDao uploaderDao;

    /**
     * 创建校外活动
     * @param act 活动信息
     * @return
     */
    @PostMapping("/add")
    public ApiResult add(@RequestBody OutSchoolActivity act){
        act.setCreateDate(new Date());
        act.setState(0);
        service.create(act);
        return ApiResult.of(ResultCode.SUCCESS);
    }


    /**
     * 填写总结
     * @param act 总结信息
     * @return
     */
    @PostMapping("/summary")
    public ApiResult summary(@RequestBody OutSchoolActivity act){
       basicSQLService.update("update at_out_school_activity set state=3,summary='"+act.getSummary()+"',summary_imgs='"+act.getSummaryImgs()+"' where id="+act.getId());
        return ApiResult.of(ResultCode.SUCCESS);
    }


    /**
     * 分页查询校外活动
     * @param condition 查询参数
     * @return 活动列表
     */
    @PostMapping("/page")
    public PageInfo<OutSchoolActivity> pageSelect(@RequestBody PageCondition<OutSchoolActivity> condition){
        return service.selectList(condition);
    }

    /**
     * 查询校外活动详情
     * @param id 校内活动id
     * @return 活动信息
     */
    @GetMapping("/detail")
    public ApiResult detail(Integer id){
        OutSchoolActivity act= service.selectById(id);
        if(act!=null & StringUtils.isNotEmpty(act.getSummaryImgs())){
            String[] imgArr=act.getSummaryImgs().split(",");
            List<String> imgList=new ArrayList(imgArr.length);
            for (String s : imgArr) {
                imgList.add(ftpUtils.getPrefix()+s);
            }
            act.setImgs(imgList);
        }

        if(act.getAccessory()!=null && StringUtils.isNotEmpty(act.getAccessory())){

            EntityFile file = uploaderDao.findFileByUUID(act.getAccessory());
            if (file != null) {
                Map<String,Object> param=new HashMap<>();
                param.put("fileName", file.getFileName());
                param.put("fileUrl", ftpUtils.relativePath2HttpUrl(file));
                act.setParam(param);
            }


        }

        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(act);
        return apiResult;
    }






}
