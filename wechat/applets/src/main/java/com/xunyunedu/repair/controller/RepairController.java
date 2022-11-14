
package com.xunyunedu.repair.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.repair.pojo.*;
import com.xunyunedu.repair.pojo.model.ShenHe;
import com.xunyunedu.repair.service.RepairService;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.teacher.service.TeacherHomeService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import com.xunyunedu.wechat.template.WechatMessageTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 报修管理
 * @author chenjiaxin
 * @Date 2020/12/08
 */

@RestController
@Api(value = "维修接口", description = "维修接口")
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private TeacherHomeService teacherHomeService;

    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    //维修工账号列表
    //private String maintenanceWorkerAccountList="'7164219523','5383751330','7036398827','0835690738','6440679550'";
    /**
     * 新增报修
     */
    @PostMapping("/add")
    @Authorization
    public ApiResult addRepairContent(@RequestBody ApplyrepairInsertParam insertParam) {

        TeacherPojo teacher=teacherHomeService.getByUserId(insertParam.getProposerId());
        if(teacher==null){
            ApiResult r=new ApiResult();
            r.setCode(400);
            r.setMsg("当前用户不是老师");
            return r;
        }
        ApplyrepairPojo applyrepairPojo=new ApplyrepairPojo();
        BeanUtils.copyProperties(insertParam,applyrepairPojo);
        Date now=new Date();
        applyrepairPojo.setPhone(teacher.getMobile());
        applyrepairPojo.setProposerName(teacher.getName());
        applyrepairPojo.setShcoolId(insertParam.getSchoolId());
        applyrepairPojo.setCreateDate(now);
        applyrepairPojo.setAppointmentDate(now);
        applyrepairPojo.setModifyDate(now);
        applyrepairPojo.setStatus("01");
        applyrepairPojo.setIsDelete(false);
        applyrepairPojo.setShenherenId(insertParam.getShenherenId());
        applyrepairPojo.setShenherenName(insertParam.getShenherenName());
        applyrepairPojo.setShenqingPictureId(insertParam.getShenqingPictureId());

        //applyrepairPojo.setDepartmentId(departmentService.getDepartIdByUserId(insertParam.getProposerId()));
        repairService.add(applyrepairPojo);
        applyrepairPojo.setTitle(applyrepairPojo.getProposerName()+"的维修申请");
        repairService.addShiJian(applyrepairPojo);
        //发送微信通知
        sendWechatNotice(insertParam.getShenherenId());

        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 获取所有保修类型
     * @return
     */
    @GetMapping("/getAllType")
    public List getAllType(){
        return basicSQLService.find("select `name`,`id` from jc_gc_item where table_code='GB-BXLX' and `disable`=0");
    }


    /**
     * 分页查询维修单列表
     * @param condition
     * @return
     */
    @PostMapping("/page")
    @Authorization
    public ApiResult pagingList(@RequestBody PageCondition<ApplyrepairCondition> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(repairService.pagingList(condition));
        return apiResult;
    }

    /**
     * 获取维修单详情
     * @return
     */
    @GetMapping("/detail/{id}")
    @Authorization
    @ApiOperation(value = "维修详情", httpMethod = "GET")
    public ApiResult detail(@PathVariable("id") Integer id){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(repairService.getDetailById(id));
        return apiResult;
    }

    /**
     * 维修维修单
     * @param param
     * @return
     */
    @PostMapping("/repair")
    @Authorization
    @ApiOperation(value = "维修", httpMethod = "POST")
    public ApiResult repair(@RequestBody RepairRepairParam param){
        TeacherPojo t=teacherHomeService.getByUserId(param.getUserId());
        if(t==null){
            ApiResult r=new ApiResult();
            r.setCode(400);
            r.setMsg("当前用户不是老师");
            return r;
        }
        AcceptRepari acceptRepari=new AcceptRepari();
        acceptRepari.setAcceptDate(new Date());
        acceptRepari.setAccepterId(param.getUserId());
        acceptRepari.setAccepterName(t.getName());
        acceptRepari.setPhone(t.getMobile());
        acceptRepari.setRemark(param.getRemark());
        acceptRepari.setRepariId(param.getRepariId());
        acceptRepari.setCreateDate(new Date());
        acceptRepari.setModifyDate(new Date());
        acceptRepari.setIsDelete(false);
        acceptRepari.setSchoolId(param.getSchoolId());
        acceptRepari.setIsHaoCai(param.getIsHaoCai());
        acceptRepari.setPicture(param.getPicture());
        repairService.addAcceptRepari(acceptRepari,param.getState());
        ApplyrepairPojo applyrepairPojo=repairService.findById(param.getRepariId());
        if(applyrepairPojo!=null){
            List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+applyrepairPojo.getProposerId()+"  and open_id is not null");
            if(notifyUserList!=null && notifyUserList.size()>0) {
                WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("维修结果通知","您有一个维修已完成");
                notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
            }else{
                System.out.println("没有这个人12");
            }
            List notifyUserList2=basicSQLService.find("select open_id from yh_user where id="+applyrepairPojo.getShenherenId()+"  and open_id is not null");
            if(notifyUserList!=null && notifyUserList.size()>0) {
                WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("维修结果通知","您有一个审核通过的维修已完成");
                notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList2,"open_id",null);
            }else{
                System.out.println("没有这个人11");
            }
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 评价维修单
     * @param param
     * @return
     */
    @PostMapping("/evaluate")
    @Authorization
    public ApiResult evaluate(@RequestBody ApplyrepairCommentParam param){
        repairService.comment(param);
        return ApiResult.of(ResultCode.SUCCESS);
    }



    private void sendWechatNotice(Integer id){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+id+"  and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核通知","您有新的维修申请待审核");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }else{
            System.out.println("没有这个人");
        }
    }
    //------------------2022，02，24 新增接口------------------------
    /*
     * 审核人下拉框
     */
    @RequestMapping("/findshenheren")
    public List<Map<String,Object>> findshenheren(){
        List<Map<String,Object>> list=basicSQLService.find(" select pt.user_id,pt.name from yh_user_role yur" +
                "        inner join yh_role yr on yr.id=yur.role_id" +
                "        inner join pj_teacher pt on pt.user_id=yur.user_id" +
                "        where 1=1 and yr.code='SCHOOL_SUPPORT_STAFF'" +
                "        and yr.group_id=264");
        return list;
    }
    /*
     * 维修人下拉框
     */
    @RequestMapping("/findWeixiuren")
    public List<Map<String,Object>> findWeixiuren(@RequestParam Integer typeId){
        List<Map<String,Object>> list=basicSQLService.find("select zw.*,pt.name as teacherName from zy_weixiugong zw inner join pj_teacher pt on pt.user_id=zw.teacher_id  where zw.at_id="+typeId+" and zw.is_delete=0");
        //116712
        return list;
    }

    /*
     *审核过程
     */
    @RequestMapping("/shenhePass")
    public ApiResult shenhePass(@RequestBody ShenHe shenHe){
        repairService.updateShenhe(shenHe);
        ApplyrepairPojo applyrepairPojo=repairService.findById(shenHe.getId());
        repairService.updateShenHeLie(shenHe.getIsShenhe(),shenHe.getId());
        if(shenHe.getIsShenhe()==1){
            List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+applyrepairPojo.getProposerId()+"  and open_id is not null");
            if(notifyUserList!=null && notifyUserList.size()>0) {
                WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核结果通知","您有一个申请的维修已通过");
                notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
            }else{
                System.out.println("没有这个人1");
            }

            List notifyUserList2=basicSQLService.find("select open_id from yh_user where id="+shenHe.getWeixiugong()+"  and open_id is not null");
            if(notifyUserList2!=null && notifyUserList2.size()>0) {
                WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("维修通知","您有一个新的维修待处理");
                notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList2,"open_id",null);
            }else{
                System.out.println("没有这个人1");
            }
        }else{
            List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+applyrepairPojo.getProposerId()+"  and open_id is not null");
            if(notifyUserList!=null && notifyUserList.size()>0) {
                WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核结果通知","您有一个申请的维修未通过");
                notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
            }else{
                System.out.println("没有这个人2");
            }
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    @RequestMapping("/weixiuType")
    public List<Map<String,Object>> daochuzongfen() {
        List<Map<String,Object>> map=basicSQLService.find("select `name`,`id` from jc_gc_item where table_code='GB-BXLX' and `disable`=0");
        return map;
    }


    @RequestMapping("/asc")
    public Map<String,String> asc() {
      Map<String,String> ob=new HashMap<>();
      ob.put("appSecret","4b33f1d9620a1ef8f62a35f6b937700e");
        return ob;
    }

}

