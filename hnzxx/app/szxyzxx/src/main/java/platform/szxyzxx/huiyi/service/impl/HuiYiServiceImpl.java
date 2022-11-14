package platform.szxyzxx.huiyi.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.huiyi.dao.HuiYiDao;
import platform.szxyzxx.huiyi.service.HuiYiService;
import platform.szxyzxx.huiyi.vo.HuiYi;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HuiYiServiceImpl implements HuiYiService {
    @Autowired
    private HuiYiDao huiYiDao;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public List<HuiYi> findByAll(String theme, Integer depentId, String shenqingName,
                                 String fuzeren, String changdi, String shijian,String shijian2,
                                 Integer teacherId, String teacherName,Page page) {
            return  huiYiDao.findByAll(theme,depentId,shenqingName,fuzeren,changdi,shijian,shijian2,teacherId,teacherName,page);
    }

    @Override
    public List<HuiYi> findByAllDaoChu(String theme, Integer depentId, String shenqingName,
                                       String fuzeren, String changdi, String shijian, String shijian2,Integer teacherid,String name ) {
        return huiYiDao.findByAllDaoChu(theme,depentId,shenqingName,fuzeren,changdi,shijian,shijian2,teacherid,name);
    }

    @Override
    public HuiYi findById(Integer id) {
        return huiYiDao.findById(id);
    }

    @Override
    public String create(HuiYi huiYi) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //时间比较
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date sd1 = df2.parse(huiYi.getKaishiTime());
            //年月日
            Date sd=df.parse(huiYi.getKaishiTime());
            String date=df.format(sd)+" 12:00";
            Date sd2=df2.parse(date);
            List<HuiYi> asd=huiYiDao.findByTime(df.format(sd));
            if(asd.size()>0){
                for(HuiYi aa: asd){
                    Date sd3=df2.parse(aa.getKaishiTime());
                    if(sd1.before(sd2)==sd3.before(sd2)){
                        if(aa.getChangdiId().equals(huiYi.getChangdiId())){
                            return "场地冲突，该时间段内该地点已有会议";
                        }
                        String [] canhui=aa.getStaff().split(",");
                        String [] canhui2=huiYi.getStaff().split(",");
                        for(int t=0;t<canhui.length;t++){
                            for(int y=0;y<canhui2.length;y++){
                                if(canhui[t].equals(canhui2[y])){
                                    return "人员冲突，该时间段内"+canhui2[y]+"已有会议";
                                }
                            }
                        }
                    }
                }
                int num= huiYiDao.create(huiYi);
                if(num>0){
                    sendWechatNotice(huiYi.getReviewer());
                    return "success";
                }else{
                    return "shibai";
                }
            }else{
                int num= huiYiDao.create(huiYi);
                if(num>0){
                    sendWechatNotice(huiYi.getReviewer());
                    return "success";
                }else{
                    return "shibai";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "null";
    }
    private void sendWechatNotice(Integer userId){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+userId+" and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("会议通知","您有新的会议待审核");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }
    @Override
    public String update(HuiYi huiYi) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //时间比较
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date sd1 = df2.parse(huiYi.getKaishiTime());
            //年月日
            Date sd=df.parse(huiYi.getKaishiTime());
            String date=df.format(sd)+" 12:00";
            Date sd2=df2.parse(date);
            List<HuiYi> asd=huiYiDao.findByTime(df.format(sd));
            if(asd.size()>0){
                for(HuiYi aa: asd){
                    Date sd3=df2.parse(aa.getKaishiTime());
                    if(sd1.before(sd2)==sd3.before(sd2)){

                        if(aa.getChangdiId().equals(huiYi.getChangdiId()) && aa.getId()!= huiYi.getId()){
                            return "场地冲突，该时间段内该地点已有会议";
                        }
                        String [] canhui=aa.getStaff().split(",");
                        String [] canhui2=huiYi.getStaff().split(",");
                        for(int t=0;t<canhui.length;t++){
                            for(int y=0;y<canhui2.length;y++){
                                if(canhui[t].equals(canhui2[y]) && aa.getId()!= huiYi.getId()){
                                    return "人员冲突，该时间段内"+canhui2[y]+"已有会议";
                                }
                            }
                        }
                    }
                }
                Integer num= huiYiDao.update(huiYi);
                if(num>0){
                    return "success";
                }else{
                    return "shibai";
                }
            }else {
                Integer num = huiYiDao.update(huiYi);
                if (num > 0) {
                    return "success";
                } else {
                    return "shibai";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @Override
    public String updateshanchu(Integer id) {
        Integer num= huiYiDao.updateshanchu(id);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    @Override
    public HuiYi fingByIdXingQing(Integer id) {
        return huiYiDao.findById(id);

    }

    @Override
    public String updateShenHe(Integer id, Integer zhuangtai, String text) {
        Integer num= huiYiDao.updateShenHe(id,zhuangtai,text);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public void createshenhe(HuiYi huiYi) {
        huiYiDao.createshenhe(huiYi);
    }


}
