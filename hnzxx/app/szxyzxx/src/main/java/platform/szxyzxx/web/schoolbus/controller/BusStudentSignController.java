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
            return new BusResult(500,"服务器内部错误");
        }
    }




    @RequestMapping(value = "/batchPushBusSignInfo",method = RequestMethod.POST)
    public BusResult addBatch(@RequestBody List<BusStudentSign> signList, HttpServletRequest request){
        try {
            if(signList==null){
                return new BusResult(666,"参数有误");
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

            //分批插入，每500条数据一批
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
            return new BusResult(500,"服务器内部错误");
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
                notifyService.sendWechatNotice(new ApprovalWechatMessageTemplate("校车打卡通知", "您的小孩"+notifyList.get(0).get("name")+"在" + sign.getSignTime() + "，校车刷卡"), notifyList, "open_id", null);
            }
            } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private BusResult checkAndSet(BusStudentSign sign){
        if(sign==null || StringUtils.isEmpty(sign.getStuCard()) || StringUtils.isEmpty( sign.getSchoolBusCard()) || StringUtils.isEmpty(sign.getSignTime())){
            return new BusResult(666,"非空参数必填");
        }

        if(sign.getSignTime().length()!=19 || sign.getSignTime().indexOf(' ')==-1){
            return new BusResult(666,"signTime参数格式有误");
        }
        String[] dateTime=sign.getSignTime().split(" ");
        sign.setSignDate(dateTime[0]);
        //默认上学时间段
        Integer direction=0;
        //打卡时间是否放学时间段
        if(dateTime[1].compareTo("15:00:00")>-1 && dateTime[1].compareTo("18:00:00")<1){
            direction=1;
        }
        sign.setDirection(direction);

        return null;

    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * 用户真实IP为： 192.168.1.110
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
