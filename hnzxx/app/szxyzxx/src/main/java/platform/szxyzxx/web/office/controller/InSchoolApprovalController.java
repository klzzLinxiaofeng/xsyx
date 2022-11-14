package platform.szxyzxx.web.office.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.service.model.InSchoolActivity;
import platform.education.service.service.InSchoolActivityService;
import platform.szxyzxx.notice.constants.SystemNoticeDataIdType;
import platform.szxyzxx.notice.constants.SystemNoticeType;
import platform.szxyzxx.notice.pojo.SystemWechatNotice;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/office/in/school")
public class InSchoolApprovalController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static String viewBasePath = "office/in/school";

    @Autowired
    private InSchoolActivityService inSchoolActivityService;

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;


    //查询所有记录
    @RequestMapping(value = "/approval/index")
    public ModelAndView approval2index(@CurrentUser UserInfo userInfo,
                                       InSchoolActivity act,
                                       @ModelAttribute("page") Page page,
                                       @RequestParam(value = "sub", required = false) String sub,
                                       Model model) {
            log.warn("asd"+userInfo.getId());
            String sqls="select owner_id FROM pj_school_user where user_id='"+userInfo.getId()+"'";
            List<Map<String,Object>> list=basicSQLService.find(sqls);
           act.setHandleUserId((Integer) list.get(0).get("owner_id"));
        log.warn("opeid"+list.get(0).get("owner_id"));
        //}
                                                //findInSchoolActivityById
        List<InSchoolActivity> inSchoolActivityAndApprovalVoList =
                this.inSchoolActivityService.findInSchoolActivityAndApprovalVoByCondition(act, page, Order.desc("create_date"));

        model.addAttribute("items", inSchoolActivityAndApprovalVoList);

        String roomWhereSql=" where room_id=r.id";
        if(StringUtils.isNotEmpty(act.getName())){
            roomWhereSql+=" and `name` like '"+act.getName()+"'";
        }
        if(act.getState()!=null){
            roomWhereSql+=" and `state`="+act.getState();
        }else{
            roomWhereSql+=" and `state` !=0";
        }
       // if(act.getHandleUserId()!=null){
            roomWhereSql+=" and handle_user_id="+act.getHandleUserId();
        //}

        if(StringUtils.isNotEmpty(act.getStartTime()) && StringUtils.isNotEmpty(act.getEndTime())){
            roomWhereSql+=" and ( ('" + act.getStartTime() + "' >= a.start_time and a.end_time >= '" +
                    act.getEndTime() + "') or (  a.start_time >='" + act.getStartTime() + "' and '" + act.getEndTime() + "' >=a.end_time ) or (a.start_time >= '" + act.getStartTime() + "' and '" + act.getEndTime() + "' >=a.start_time and a.end_time >='" + act.getEndTime() + "') or ('" + act.getStartTime() + "' >= a.start_time and a.end_time >='" + act.getStartTime() + "' and '" + act.getEndTime() + "' >=a.end_time) )";
        }

        String sql="select r.id,r.name,(select count(1) from at_in_school_activity a"+roomWhereSql+") roomCount from at_in_school_room r";

        model.addAttribute("roomVoList", basicSQLService.find(sql));
        model.addAttribute("state",act.getState());
        String subPath = "/approval/index";

        if ("list".equals(sub)) {
            subPath = "/approval/list";
        }
        return new ModelAndView(viewBasePath + subPath, model.asMap());
    }

    //驳回申请
    @ResponseBody
    @RequestMapping(value = "/approval/handle", method = RequestMethod.POST)
    public synchronized String approval2id4post(@CurrentUser UserInfo userInfo,Integer activityId,Integer state,String refuseCause) {
        try {

            InSchoolActivity act=inSchoolActivityService.findInSchoolActivityById(activityId);
            if(act.getState()!=0){
                return "活动当前状态不可审批";
            }
            List<Map<String, Object>> conflictList =null;
            if(state==1) {
                //查询出与通过活动时间冲突的其他已审批、待审批活动
                //两个时间段有交汇点就算冲突
                String queryConflict = "select a.id,a.user_id,u.open_id,a.`name`,a.`state` from at_in_school_activity a left join yh_user u on u.id=a.user_id where a.`state`!=2 and a.id!="+act.getId()+" " +
                        "and a.room_id=" + act.getRoomId() + " and ( ('" + act.getStartTime() + "' >= a.start_time and a.end_time >= '" + act.getEndTime() + "') or (  a.start_time >='" + act.getStartTime() + "' and '" + act.getEndTime() +
                        "' >=a.end_time ) or (a.start_time >= '" + act.getStartTime() + "' and '" + act.getEndTime() + "' >=a.start_time and a.end_time >='" + act.getEndTime() + "') or ('" + act.getStartTime() + "' >= a.start_time and a.end_time >='" + act.getStartTime() + "' and '" + act.getEndTime() + "' >=a.end_time) ) ";
                conflictList=basicSQLService.find(queryConflict);
                if (conflictList.size() > 0) {
                    for (Map<String, Object> map : conflictList) {
                        if(map.get("state").toString().equals("true")){
                            return "当前活动和已审批通过活动：【"+map.get("name")+"】时间冲突";
                        }
                    }
                }
            }

            String sql="update at_in_school_activity set `state`="+state;
            if(StringUtils.isNotEmpty(refuseCause)){
                sql+=",refuse_cause='"+refuseCause+"'";
            }
            String sqls="select owner_id FROM pj_school_user where user_id='"+userInfo.getId()+"'";
            List<Map<String,Object>> list=basicSQLService.find(sqls);
            sql+=",handle_user_id="+list.get(0).get("owner_id")+" where id="+activityId;
            basicSQLService.update(sql);

            if(state==1){
                //通知参与者
                notifyParticipant(act.getId(),userInfo.getRealName(),act.getName(),act.getAttendUserIds());
                if(conflictList.size()>0){
                    //其他将冲突的待审批活动驳回
                    basicSQLService.update("update at_in_school_activity set `state`=2,refuse_cause='该场地已被占用',handle_user_id=-1 where id in(" + joinId(conflictList) + ")");
                    //发送通知给冲突活动申请者
                    sendApprovedResultSystemAndWechatNotice(conflictList, "【驳回】",act);
                }
            }

            //发送审批结果通知
            sendApprovedResultSystemAndWechatNotice(basicSQLService.find("select id as user_id,open_id from yh_user where id="+act.getUserId()),state==1 ? "【通过】":"【驳回】",act);
        } catch (Exception e) {
            e.printStackTrace();
            return "审批失败";
        }
        return "success";
    }

    //发送审批结果通知
    private void sendApprovedResultSystemAndWechatNotice(List<Map<String,Object>> actList,String result,InSchoolActivity act){
        try {
            for (Map<String,Object> map : actList) {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("校内活动审批结果");
                swNotice.setContent("活动【"+act.getName()+"】审批"+result);
                swNotice.setDataId(act.getId().toString());
                swNotice.setSystemNoticeType(SystemNoticeType.ACTIVITY);
                swNotice.setDataIdType(SystemNoticeDataIdType.XNHD);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("校内活动审批结果", "校内活动【"+act.getName()+"】审批"+result));
                notifyService.sendSystemAndWechatNotice(swNotice, map.get("user_id"), map.get("open_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送审批结果通知
    private void notifyParticipant(Integer actId,String userName,String actName,String notifyUserIds){
        try {
                SystemWechatNotice swNotice = new SystemWechatNotice();
                swNotice.setTitle("校内活动邀请");
                swNotice.setContent(userName+"邀请您参与活动："+actName);
                swNotice.setDataId(actId.toString());
                swNotice.setSystemNoticeType(SystemNoticeType.ACTIVITY);
                swNotice.setDataIdType(SystemNoticeDataIdType.XNHD);
                swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("校内活动邀请", userName+"邀请您参与活动："+actName));
                notifyService.sendSystemAndWechatNotice(swNotice, basicSQLService.find("select id user_id,open_id from yh_user where id in ("+notifyUserIds+")"),"user_id","open_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String joinId(List<Map<String,Object>> conflictList){
        StringBuilder ids=new StringBuilder();
        for(int i=0;i<conflictList.size();i++){
            ids.append(conflictList.get(i).get("id"));
            if(i!=conflictList.size()-1){
                ids.append(",");
            }
        }
        return ids.toString();

    }


}
