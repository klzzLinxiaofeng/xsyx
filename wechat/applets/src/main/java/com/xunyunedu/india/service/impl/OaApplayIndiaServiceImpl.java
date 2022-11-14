package com.xunyunedu.india.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.department.service.DepartmentService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.india.condition.OaApplayIndiaCondition;
import com.xunyunedu.india.condition.OaApplayIndiaDepartmentCountCondition;
import com.xunyunedu.india.dao.OaApplayIndiaDao;
import com.xunyunedu.india.param.OaApplayIndiaInsertParam;
import com.xunyunedu.india.pojo.OaApplayIndia;
import com.xunyunedu.india.pojo.OaApplayIndiaDepartmentCount;
import com.xunyunedu.india.service.OaApplayIndiaDepartmentCountService;
import com.xunyunedu.india.service.OaApplayIndiaService;

import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OaApplayIndiaServiceImpl implements OaApplayIndiaService {

    @Autowired
    OaApplayIndiaDao dao;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    OaApplayIndiaDepartmentCountService applayIndiaDepartmentCountService;
    @Autowired
    private UploaderDao uploaderDao;
    @Autowired
    private FtpUtils ftpUtils;
    @Autowired
    private BasicSQLService basicSQLService;
    @Override
    public PageInfo page(PageCondition<OaApplayIndiaCondition> condition){

        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = dao.selectByCondition(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public OaApplayIndia getDetailById(Integer id) {
        OaApplayIndia oaApplayIndia=dao.selectById(id);
        Map<String, String> param = new HashMap<>(4, 1);
        if(oaApplayIndia!=null) {
            String sql="select `name` from pj_teacher where user_id="+oaApplayIndia.getApproverId();
            param.put("approverName",(String) basicSQLService.findUnique(sql));
            if(oaApplayIndia.getUploadId()!=null) {
                EntityFile file = uploaderDao.findFileByUUID(oaApplayIndia.getUploadId());
                if (file != null) {
                    param.put("fileName", file.getFileName());
                    param.put("fileUrl", ftpUtils.relativePath2HttpUrl(file));
                }
            }
            oaApplayIndia.setParam(param);
        }


        return oaApplayIndia;
    }

    @Override
    public OaApplayIndia add(OaApplayIndiaInsertParam param){



        OaApplayIndia applayIndia = new OaApplayIndia();
        applayIndia.setApproverId(param.getApproverId());
        applayIndia.setTitle(param.getProposerName()+"的用章");
        applayIndia.setOwnerId(param.getSchoolId());
        applayIndia.setOwnerType(param.getGroupType());
        //处理状态  （0：待处理 1：未处理 2：处理中 3：已处理）
        applayIndia.setIndiaStatus("0");
        applayIndia.setProposerId(param.getProposerId());
        applayIndia.setProposerName(param.getProposerName());
        applayIndia.setIsDeleted(false);
        applayIndia.setPublishDate(new Date());
        applayIndia.setMobile(param.getProposerTelephone());
        applayIndia.setRemark(param.getRemark());
        applayIndia.setUploadId(param.getUploadId());
        applayIndia.setStartDate(param.getStartDate());
        applayIndia.setEndDate(param.getEndDate());
        applayIndia.setIsDeleted(false);
        applayIndia.setModifyDate(new Date());
//        Integer depId=departmentService.getDepartIdByUserId(param.getProposerId());
//        applayIndia.setDepartmentId(depId);
        dao.insert(applayIndia);
        //param.setDepartmentId(depId);
        /*如果申请人有所属部门，则在oa_applayIndia_department_count查找，
         * 如果根据该部门id和处理状态在该表中找到相应的数据，则在该条数据的基础上在count字段中+1
         * 否则重新根据条件进行新增一条数据
         */
//        if (depId != null) {
//            this.applayIndiaDepartmentCountService.saveOrUpdate(param,applayIndia);
//        }

        return applayIndia;
    }



}
