package com.xunyunedu.notice.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.BaseController;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.notice.condition.PubNoticeCondition;
import com.xunyunedu.notice.constant.NoticeType;
import com.xunyunedu.notice.param.PubNoticeInsertParam;
import com.xunyunedu.notice.pojo.PubNotice;
import com.xunyunedu.notice.pojo.UpdateReadParam;
import com.xunyunedu.notice.pojo.detail.PubNoticeDetail;
import com.xunyunedu.notice.service.PubNoticeService;
import com.xunyunedu.notice.service.SystemWechatNotifyService;
import com.xunyunedu.wechat.template.NoticeWechatMessageTemplate;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知模块
 * @author edison
 */
@RestController
@RequestMapping("/oa/pub/notice")
public class PubNoticeController extends BaseController {

    @Autowired
    private PubNoticeService service;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    /**
     * 分页查找消息通知
     * @param condition
     * @return
     */
    @PostMapping("/page")
    ApiResult<PageInfo<List<PubNotice>>> page(@RequestBody PageCondition<PubNoticeCondition> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.page(condition));
        return apiResult;
    }

    /**
     * 分页查询通知阅读情况
     * @param condition
     * @return
     */
    @PostMapping("/readPage")
    ApiResult<PageInfo<List>> readPage(@RequestBody PageCondition<PubNoticeCondition> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        String sql="select receiver_name as receiverName,modify_date readDate,`read` from pub_notice_receiver where notice_id="+condition.getCondition().getId();

        if(condition.getCondition().getRead()!=null){
            int read=0;
            if(condition.getCondition().getRead()){
                read=1;
            }
            sql+=" and `read`="+read;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        PageInfo<Map<String,Object>> pageInfo=basicSQLService.findByPaging(sql,condition.getPageNum(),condition.getPageSize());
        List<Map<String,Object>> list=pageInfo.getList();
        for (Map<String, Object> map : list) {
            Date data=(Date) map.get("readDate");
            map.put("readDate",sdf.format(data));
        }

        apiResult.setData(pageInfo);
        return apiResult;
    }

    /**
     * 添加通知
     * @param param
     * @return
     */
    @PostMapping
    ApiResult add(@RequestBody PubNoticeInsertParam param){
        List<String> targetTypeList=param.getTargetTypeList();
        for (String targetType : targetTypeList) {
            if(NoticeType.NoticeType_dept.equals(targetType) && CollectionUtil.isEmpty(param.getDeptIdList())){
                return paramError("当前条件deptIdList必填");
            }else if(NoticeType.NoticeType_person.equals(targetType) && (param.getTeacherIdList()==null || param.getTeacherIdList().isEmpty())){
                return paramError("当前条件teacherIdList必填");
            }
            else if(NoticeType.NoticeType_team.equals(targetType)){
                Integer userId=param.getPosterId();
                String sql="select tt.team_id from pj_teacher t left join pj_team_teacher tt on tt.teacher_id=t.id  where t.user_id="+userId+" and tt.type=1 and t.is_delete=0";
                Integer teamId=Integer.valueOf(basicSQLService.findUnique(sql).toString());
                if(teamId==null){
                    return paramError("当前用户不是班主任");
                }
                param.setTeamId(teamId);
            }
        }
        if("dept".equals(param.getReceiverType())){
            if(StringUtil.isEmpty(param.getAppKey())) {

                return paramError("当前条件，appKey必填");
            }
            //部门通知发布者为部门名称
            String posterName=param.getPosterName();
            param.setPosterName(param.getAppKey());
            param.setAppKey(posterName);
        }
        PubNotice pubNotice= service.add(param);
        if(pubNotice!=null) {
            //发送微信通知
            sendWechatNotice(pubNotice);
            return ApiResult.of(ResultCode.SUCCESS);
        }
        return ApiResult.of(ResultCode.SAVE_FAIL);
    }

    /**
     * 更新通知阅读状态为已读
     * @return
     */
    @PostMapping("/updateToRead")
    ApiResult<PubNoticeDetail> updateRead(@RequestBody UpdateReadParam param){
        List<Map<String,Object>> map=basicSQLService.find("select * from pub_notice_receiver where `read`=0 and notice_id="+param.getNoticeId()+ " and receiver_id="+param.getUserId());
        if(map.size()>0){
            basicSQLService.update("update pub_notice set read_count=read_count+1 where id="+param.getNoticeId()+" and read_count<user_count");
        }
        basicSQLService.update("update pub_notice_receiver set `read`=1 where notice_id="+param.getNoticeId()+" and receiver_id="+param.getUserId()+" and `read`=0");
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        return apiResult;
    }

    /**
     * 获取通知详情
     * @param id 通知id
     * @param updateRead 是否更新已读/未读状态，默认true
     * @param userId 当前用户id
     * @return
     */
    @GetMapping("/detail")
    ApiResult<PubNoticeDetail> getDetailById(Integer id,Integer userId,@RequestParam(name = "updateRead",required = false,defaultValue = "true") Boolean updateRead){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.updateStatusAndGetDetail(id,userId,updateRead));
        return apiResult;
    }


    private void sendWechatNotice(PubNotice sysNotice){
        //用户openId以及家长openId
        List<Map<String,Object>> notifyUserList=basicSQLService.find("SELECT pu.open_id AS parent_open_id, u.open_id FROM pub_notice_receiver r LEFT JOIN yh_user u ON u.id = r.receiver_id LEFT JOIN pj_parent_student ps ON ps.student_user_id = r.receiver_id AND ps.is_delete = 0 AND ps.rank = 1 LEFT JOIN yh_user pu ON pu.id = ps.parent_user_id WHERE r.notice_id = "+sysNotice.getId()+" AND (u.open_id IS NOT NULL OR pu.open_id IS NOT NULL)");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            NoticeWechatMessageTemplate messageTemplate = new NoticeWechatMessageTemplate(sysNotice.getReceiverType(), sysNotice.getTitle());
            for (Map<String, Object> map : notifyUserList) {
                //此处要区分学生和老师，老师直接发送给本人，学生发送给家长
                String openId=(String) map.get("open_id");
                String parentOpenId=(String) map.get("parent_open_id");
                if(StringUtils.isNotEmpty(parentOpenId)){
                    openId=parentOpenId;
                }
                if(StringUtils.isEmpty(openId)){
                    continue;
                }
                notifyService.sendWechatNotice(messageTemplate,openId,null);
            }
        }
    }


}
