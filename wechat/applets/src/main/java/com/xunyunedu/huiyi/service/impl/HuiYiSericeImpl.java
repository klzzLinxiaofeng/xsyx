package com.xunyunedu.huiyi.service.impl;


import com.xunyunedu.huiyi.dao.HuiYiDao;
import com.xunyunedu.huiyi.pojo.HuiYi;
import com.xunyunedu.huiyi.service.HuiYiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HuiYiSericeImpl implements HuiYiService {
    @Autowired
    private HuiYiDao huiwuDao;


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
            List<HuiYi> asd=huiwuDao.findByTime(df.format(sd));
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
                int num= huiwuDao.create(huiYi);
                if(num>0){
                    return "success";
                }else{
                    return "shibai";
                }
            }else{
                int num= huiwuDao.create(huiYi);
                if(num>0){
                    return "success";
                }else{
                    return "shibai";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "shibai";
    }

    @Override
    public void createshenhe(HuiYi huiYi) {
         huiwuDao.createshenhe(huiYi);
    }

    @Override
    public List<HuiYi> findByAll(Integer id, String name) {
        return huiwuDao.findByAll(id,name);
    }

    @Override
    public Integer updateShenHe(Integer id, Integer zhuangtai, String text) {
        return huiwuDao.updateShenHe( id, zhuangtai, text);
    }

    @Override
    public HuiYi findById(Integer id) {
        return huiwuDao.findById(id);
    }
}
