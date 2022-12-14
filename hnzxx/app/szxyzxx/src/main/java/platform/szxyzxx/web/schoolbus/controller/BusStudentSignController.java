package platform.szxyzxx.web.schoolbus.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.schoolbus.pojo.BusResult;
import platform.szxyzxx.schoolbus.pojo.BusStudentSign;
import platform.szxyzxx.schoolbus.service.BusStudentSignService;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/schoolBus/studentSign")
@RestController
public class BusStudentSignController {

    @Autowired
    private BusStudentSignService service;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;

    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping(value = "/pushBusSignInfo",method = RequestMethod.POST)
    public BusResult add(@RequestBody BusStudentSign sign,HttpServletRequest request){

        try {
            BusResult checkResult=checkAndSet(sign);
            if(checkResult!=null){
                return checkResult;
            }

            sign.setRequestIp(getIpAddress(request));

            sign.setCreateTime(new Date());

            checkAndSetCard(sign);

            service.create(sign);
            sendWechatNotice(sign);
            updateLastCar(sign.getStuCard(),sign.getSchoolBusCard());
            return new BusResult(200);
        } catch (Exception e) {
            e.printStackTrace();
            return new BusResult(500,"?????????????????????");
        }
    }




    @RequestMapping(value = "/batchPushBusSignInfo",method = RequestMethod.POST)
    public BusResult addBatch(@RequestBody List<BusStudentSign> signList, HttpServletRequest request){
        try {
            if(signList==null){
                return new BusResult(666,"????????????");
            }
            String requestIp=getIpAddress(request);
            Date now=new Date();
            for (BusStudentSign sign : signList) {
                BusResult checkResult=checkAndSet(sign);
                if(checkResult!=null){
                    return checkResult;
                }
                checkAndSetCard(sign);
                sign.setRequestIp(requestIp);
                sign.setCreateTime(now);
            }

            //??????????????????500???????????????
            if(signList.size()>500) {
                List<BusStudentSign> oneBatch=new ArrayList<>(500);
                for (int i = 1; i <= signList.size(); i++) {
                    oneBatch.add(signList.get(i-1));
                    if(i%500==0){
                        service.createBatch(oneBatch);
                        oneBatch=new ArrayList<>(500);
                    }
                }
                if(oneBatch.size()>0){
                    service.createBatch(oneBatch);
                }
            }else{
                service.createBatch(signList);
            }

            for (BusStudentSign busStudentSign : signList) {
                sendWechatNotice(busStudentSign);
                updateLastCar(busStudentSign.getStuCard(),busStudentSign.getSchoolBusCard());
            }

            return new BusResult(200);
        } catch (Exception e) {
            e.printStackTrace();
            return new BusResult(500,"?????????????????????");
        }

    }
    private void checkAndSetCard(BusStudentSign sign){
        if(sign==null || sign.getSchoolBusCard()==null){
            return;
        }
        int len=sign.getStuCard().length();
        if(len<10){
            int d=10-len;
            StringBuilder zeroStr=new StringBuilder();
            for(int i=0;i<d;i++){
                zeroStr.append("0");
            }
            sign.setStuCard(zeroStr +sign.getStuCard());
        }
    }

    private void updateLastCar(String empCard,String carNo){
        basicSQLService.update("update pj_student set last_by_car_no='"+carNo+"' where emp_card='"+empCard+"' and last_by_car_no!='"+carNo+"'");
    }


    private void sendWechatNotice(BusStudentSign sign){
        try {
            List<Map<String,Object>> notifyList=basicSQLService.find("select u.open_id,s.name from pj_student s  left join yh_user u on u.id=s.user_id where s.emp_card='"+sign.getStuCard()+"' and u.open_id is not null");
            if(notifyList.size()>0) {
                notifyService.sendWechatNotice(new ApprovalWechatMessageTemplate("??????????????????", "????????????"+notifyList.get(0).get("name")+"???" + sign.getSignTime() + "???????????????"), notifyList, "open_id", null);
            }
            } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private BusResult checkAndSet(BusStudentSign sign){
        if(sign==null || StringUtils.isEmpty(sign.getStuCard()) || StringUtils.isEmpty( sign.getSchoolBusCard()) || StringUtils.isEmpty(sign.getSignTime())){
            return new BusResult(666,"??????????????????");
        }

        if(sign.getSignTime().length()!=19 || sign.getSignTime().indexOf(' ')==-1){
            return new BusResult(666,"signTime??????????????????");
        }
        String[] dateTime=sign.getSignTime().split(" ");
        sign.setSignDate(dateTime[0]);
        //?????????????????????
        Integer direction=0;
        //?????????????????????????????????
        if(dateTime[1].compareTo("15:00:00")>-1 && dateTime[1].compareTo("18:00:00")<1){
            direction=1;
        }
        sign.setDirection(direction);

        return null;

    }

    /**
     * ??????????????????IP??????????????????request.getRemoteAddr();??????????????????????????????????????????????????????????????????IP??????,
     * ???????????????????????????????????????????????????X-Forwarded-For????????????????????????????????????IP???????????????????????????????????????????????????IP??????
     * ????????????X-Forwarded-For???????????????unknown?????????IP????????????
     * ??????X-Forwarded-For???192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * ????????????IP?????? 192.168.1.110
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }




}
