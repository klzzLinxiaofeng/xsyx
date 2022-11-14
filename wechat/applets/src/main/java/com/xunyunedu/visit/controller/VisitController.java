package com.xunyunedu.visit.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.util.QRCodeUtil;
import com.xunyunedu.visit.pojo.VisitApplyCondition;
import com.xunyunedu.visit.pojo.VisitApplyHandleParam;
import com.xunyunedu.visit.pojo.VisitVisitApply;
import com.xunyunedu.visit.pojo.VisitVisitApplyUser;
import com.xunyunedu.visit.service.VisitVisitApplyService;
import com.xunyunedu.wechat.template.ApprovalWechatMessageTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 访客管理
 */
@RequestMapping("/visit")
@RestController
public class VisitController {

    @Autowired
    private VisitVisitApplyService service;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 申请访校
     * @param apply
     * @return
     */
    @PostMapping("/apply")
    public ApiResult apply(@RequestBody VisitVisitApply apply){
        apply.setCreateDate(new Date());
        apply.setVisitStatus(0);
        apply.setUsedQrcode(false);
        VisitVisitApplyUser leader=apply.getVisitUserList().get(0);
        apply.setApplicantName(leader.getVisitorName());
        apply.setApplicantPhone(leader.getVisitorPhone());
        apply.setVisitUserCount(apply.getVisitUserList().size());
        service.add(apply);
        try {
            List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+apply.getVisitUserId()+" and open_id is not null");
            if(notifyUserList.size()>0) {
                notifyService.sendWechatNotice(new ApprovalWechatMessageTemplate("来访通知", "您有一条" + apply.getVisitUserName() + "的来访申请待审批"), notifyUserList, "open_id",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 分页查询访校
     * @param page
     * @return
     */
    @PostMapping("/page")
    public ApiResult pagingQuery(@RequestBody PageCondition<VisitApplyCondition> page){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.pagingQuery(page));
        return apiResult;
    }

    /**
     * 分页查询我的待审批
     * @param page
     * @return
     */
    @PostMapping("/pageMyNotApproval")
    public ApiResult pageMyNotApproval(@RequestBody PageCondition<VisitApplyCondition> page){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.pagingQueryMyNotApproval(page));
        return apiResult;
    }

    /**
     * 查询访校申请详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public ApiResult detail(Integer id){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.queryDetailById(id));
        return apiResult;
    }

    /**
     * 审批访校申请
     * @param param
     * @return
     */
    @PostMapping("/handle")
    public ApiResult handle(@RequestBody VisitApplyHandleParam param){

        if(param.getUserId()==null || StringUtils.isEmpty(param.getUserName()) || param.getAdmin()==null || param.getApplyId()==null || param.getResult()==null){
            return errorResult("必填参数不可为空");
        }

        VisitVisitApply apply=service.queryById(param.getApplyId());
        if(apply==null){
            return errorResult("applyId有误");
        }


        if(!apply.getVisitStatus().equals(0) && !apply.getVisitStatus().equals(1)){
            return errorResult("当前状态不可审批");
        }


        if((apply.getVisitStatus().equals(0) && !apply.getVisitUserId().equals(param.getUserId())) || (apply.getVisitStatus().equals(1) && !param.getAdmin())){
            return errorResult("您没有权限审批");
        }

        VisitVisitApply updateApply=new VisitVisitApply();
        updateApply.setId(apply.getId());

        Date now=new Date();

        String visitTimeStr=new SimpleDateFormat("yyyy-MM-dd").format(apply.getVisitDate());

        //审批通过
        if(param.getResult().equals(0)){
            //是管理员
            if(param.getAdmin()){
                //直接待入校
                updateApply.setVisitStatus(3);
                updateApply.setQrcodeContent(genQrCodeContent(apply));
                if(updateApply.getVisitStatus().equals(0)) {
                    updateApply.setVisisUserApprovalTime(now);
                }
                updateApply.setAdminApprovalTime(now);
                updateApply.setApprovalAdminUserId(param.getUserId());
                updateApply.setApprovalAdminUserName(param.getUserName());
                notifyService.sendWechatNotice(new ApprovalWechatMessageTemplate("访校审批结果","您"+visitTimeStr+"日的访校申请审批【通过】，请在当天入校"),apply.getApplicantOpenId(),null);
            }else{
                //待管理员审批
                updateApply.setVisitStatus(1);
                updateApply.setVisisUserApprovalTime(now);
            }
        }else{
//            if(StringUtils.isEmpty(param.getRefuseCause())){
//                return errorResult("驳回理由必填");
//            }
            updateApply.setRefuseCause(param.getRefuseCause());
            //管理员拒绝
            if(param.getAdmin() && apply.getVisitStatus().equals(1)){
                updateApply.setVisitStatus(4);
                updateApply.setAdminApprovalTime(now);
                updateApply.setApprovalAdminUserId(param.getUserId());
                updateApply.setApprovalAdminUserName(param.getUserName());
            }else {
                //被访者拒绝
                updateApply.setVisitStatus(2);
                updateApply.setVisisUserApprovalTime(now);
            }

            notifyService.sendWechatNotice(new ApprovalWechatMessageTemplate("访校审批结果","您"+visitTimeStr+"日的访校申请审批【不通过】"),apply.getApplicantOpenId(),null);
        }

        service.updateSelective(updateApply);

        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 二维码开门
     * @param qrcodeContent
     * @return
     */
    @PostMapping("/openDoor")
    public ApiResult openDoor(@RequestBody String qrcodeContent){
        if(StringUtils.isEmpty(qrcodeContent)){
            return errorResult("参数不可为空");
        }
        String[] arr=qrcodeContent.split("-");
        if(arr.length!=2){
            return errorResult("无效二维码");
        }
        Integer id=null;
        try {
            id=Integer.valueOf(arr[1]);
        } catch (NumberFormatException e) {
           return errorResult("无效二维码");
        }
       VisitVisitApply apply= service.queryById(id);
       if(apply==null || StringUtils.isEmpty(apply.getQrcodeContent()) || !apply.getQrcodeContent().equals(qrcodeContent)){
           return errorResult("无效二维码");
       }

       if(apply.getUsedQrcode()){
           return errorResult("二维码已使用");
       }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       Date now=new Date();
       String nowDateStr=sdf.format(now);
       String visitDate=sdf.format(apply.getVisitDate());
       int r=nowDateStr.compareTo(visitDate);
       if(r==1){
           return errorResult("二维码已过期");
       }
       if(r==-1){
           return errorResult("未到来访日期");
       }
       VisitVisitApply updateApply= new VisitVisitApply();
       updateApply.setId(apply.getId());
       updateApply.setUsedQrcode(true);
       updateApply.setVisitStatus(5);
       service.updateSelective(updateApply);
       String nowTime=new SimpleDateFormat("HH:mm").format(now);
        //通知所有保安
        try {
            List notifyUserList=basicSQLService.find("select u.open_id from yh_user_role ur left join yh_role r on r.id=ur.role_id left join yh_user u on u.id=ur.user_id where r.`name`='保安' and u.open_id is not null and ur.is_deleted=0");
            if(notifyUserList.size()>0) {
                apply.setApplicantName("jack");
                String con="【"+apply.getApplicantName() + "】在" + nowTime + "进校，来访" + apply.getVisitUserCount() + "人";
                notifyService.sendWechatNotice(new ApprovalWechatMessageTemplate("进校通知", con), notifyUserList, "open_id", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 生成二维码
     * @param content 二维码内容
     * @param response
     */
    @GetMapping(value = "/genQrCode")
    public void generateQR(String content, HttpServletResponse response) {

        // 禁止图像缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        BufferedImage image = QRCodeUtil.createImage(content);
        // 创建二进制的输出流
        try(ServletOutputStream sos = response.getOutputStream()){
            // 将图像输出到Servlet输出流中。
            ImageIO.write(image, "jpeg", sos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String genQrCodeContent(VisitVisitApply apply){
        return getRandom(6)+"-"+apply.getId();
    }

    public static String getRandom(int length){
        StringBuilder val = new StringBuilder("");
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val.append( String.valueOf(random.nextInt(10)));
        }
        return val.toString();
    }

    ApiResult errorResult(String msg){
        ApiResult apiResult=new ApiResult();
        apiResult.setCode(400);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
