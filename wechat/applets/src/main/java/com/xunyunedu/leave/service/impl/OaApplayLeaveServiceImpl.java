package com.xunyunedu.leave.service.impl;

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
import com.xunyunedu.leave.condition.OaApplayLeaveCondition;
import com.xunyunedu.leave.condition.OaApplayLeaveDepartmentCountCondition;
import com.xunyunedu.leave.dao.OaApplayLeaveDao;
import com.xunyunedu.leave.param.OaApplayLeaveInsertParam;
import com.xunyunedu.leave.pojo.OaApplayLeave;
import com.xunyunedu.leave.pojo.OaApplayLeaveApproveUser;
import com.xunyunedu.leave.pojo.OaApplayLeaveDepartmentCount;
import com.xunyunedu.leave.service.OaApplayLeaveApproveUserService;
import com.xunyunedu.leave.service.OaApplayLeaveDepartmentCountService;
import com.xunyunedu.leave.service.OaApplayLeaveService;
import com.xunyunedu.notice.condition.PubNoticeCondition;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.util.UUIDUtil;
import com.xunyunedu.util.ftp.FtpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author edison
 */
@Service
public class OaApplayLeaveServiceImpl implements OaApplayLeaveService {

    @Autowired
    OaApplayLeaveDao dao;

    @Autowired
    OaApplayLeaveDepartmentCountService applayLeaveDepartmentCountService;

    @Autowired
    OaApplayLeaveApproveUserService oaApplayLeaveApproveUserService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private BasicSQLService basicSQLService;


    @Autowired
    private FtpUtils ftpUtils;
    @Override
    public PageInfo page(PageCondition<OaApplayLeaveCondition> condition){

        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = dao.selectByCondition(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 添加请假申请
     * TODO 事务啊
     * @param param
     * @return
     */
    @Override
    @Transactional
    public OaApplayLeave add(OaApplayLeaveInsertParam param){

            OaApplayLeave applayLeave = new OaApplayLeave();
            BeanUtils.copyProperties(param,applayLeave);
            applayLeave.setTitle(param.getPropserName()+"的请假申请");
            applayLeave.setUuid(UUIDUtil.getUUID());
            applayLeave.setAppId(1);
            applayLeave.setOwnerId(param.getSchoolId());
            applayLeave.setApproveId(Integer.valueOf(param.getApproveIds()));
            applayLeave.setOwnerType("1");
            applayLeave.setAuditStatus("0");
            if(dao.insert(applayLeave)<0){
                return null;
            }

//            // 当前发布请假条的人员有部门，则在oa_applay_leave_department_count表中要添加相应的数据
//            Integer propserDepartmentId = departmentService.getDepartIdByUserId(applayLeave.getPropserId());
//            if (propserDepartmentId != null) {
//                param.setPropserDepartmentId(propserDepartmentId);
//                this.applayLeaveDepartmentCountService.addOrUpdate(param,applayLeave);
//            }

            if(StringUtils.isNotEmpty(param.getApproveIds())){
                String[] approveIds=param.getApproveIds().split(",");
                for (String approveId : approveIds) {
                    OaApplayLeaveApproveUser oaApplayLeaveApproveUser = new OaApplayLeaveApproveUser();
                    oaApplayLeaveApproveUser.setLeaveId(applayLeave.getId());
                    oaApplayLeaveApproveUser.setApproveId(new Integer(approveId));
                    oaApplayLeaveApproveUser.setApproveState(0);
                    this.oaApplayLeaveApproveUserService.add(oaApplayLeaveApproveUser);
                }

            }



        return applayLeave;
    }

    @Override
    public OaApplayLeave getDetailById(Integer id) {
        OaApplayLeave applayLeave=dao.selectById(id);
        if(applayLeave!=null && applayLeave.getAttachmentUuid()!=null) {
            EntityFile file=uploaderDao.findFileByUUID(applayLeave.getAttachmentUuid());
            if(file!=null){
                Map<String,String> fileInfo=new HashMap<>(3,1);
                fileInfo.put("fileName",file.getFileName());
                fileInfo.put("fileUrl",ftpUtils.relativePath2HttpUrl(file));
                applayLeave.setAttachmentFile(fileInfo);
            }
        }

        if(applayLeave!=null){
            String sql="select u.name from oa_applay_leave_approve_user a left join pj_teacher u on u.user_id=a.approve_id where a.leave_id="+applayLeave.getId();
            applayLeave.setApproveName((String) basicSQLService.findUnique(sql));
        }

        return applayLeave;
    }

    @Override
    public List<Map<String, Object>> getLeaveTypes() {
        return dao.selectLeaveTypes();
    }
}
