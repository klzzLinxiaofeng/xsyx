package platform.szxyzxx.wokeBiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.wokeBiao.dao.ZhouQiDao;
import platform.szxyzxx.wokeBiao.pojo.DaoChuPojo;
import platform.szxyzxx.wokeBiao.pojo.WokeXingQing;
import platform.szxyzxx.wokeBiao.pojo.ZhouQi;
import platform.szxyzxx.wokeBiao.service.ZhouQiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZhouQiServiceImpl implements ZhouQiService {
    @Autowired
    private ZhouQiDao zhouQiDao;

    @Override
    public List<Map<String ,Object>>  findByAll(Integer zhouShu, String  schoolYear, String schoolTerm) {
        List<Map<String ,Object>> listm=new ArrayList<>();
        ZhouQi zhouQi=zhouQiDao.findById(zhouShu,schoolYear,schoolTerm);
            for(int a=1;a<18;a++){
                Map<String,Object> map=new HashMap<>();
                List<WokeXingQing> list=new ArrayList<>();
                for(int b=1;b<8;b++){
                    if(a==17){
                        WokeXingQing wokeXingQing=zhouQiDao.findByAll(zhouQi.getId(),a,b);
                        list.add(wokeXingQing);
                        break;
                    }
                    WokeXingQing wokeXingQing=zhouQiDao.findByAll(zhouQi.getId(),a,b);
                    list.add(wokeXingQing);
                }
                map.put("wokeXiangQing",list);
                listm.add(map);
            }
        return listm;
    }

    @Override
    public Integer craete(ZhouQi zhouQi) {
        Integer num =zhouQiDao.createZhouQi(zhouQi);
        if(num>0){
            for(int i=1;i<8;i++){
                for(int j=1;j<18;j++){
                    if(j==17){
                        WokeXingQing wokeXingQing=getAllTime(j);
                        wokeXingQing.setZhouqiId(zhouQi.getId());
                        wokeXingQing.setWeekNum(i);
                        wokeXingQing.setJieNum(j);
                        zhouQiDao.createZhouQiXiangQing(wokeXingQing);
                        break;
                    }
                    WokeXingQing wokeXingQing=getAllTime(j);
                    wokeXingQing.setZhouqiId(zhouQi.getId());
                    wokeXingQing.setWeekNum(i);
                    wokeXingQing.setJieNum(j);
                    zhouQiDao.createZhouQiXiangQing(wokeXingQing);
                }
            }
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public ZhouQi findById(Integer zhoushu,String schoolYaer,String schoolTerm) {
        return zhouQiDao.findById(zhoushu,schoolYaer,schoolTerm);
    }

    @Override
    public Integer updateZhouQiXianQing(WokeXingQing wokeXingQing) {
        return zhouQiDao.updateZhouQiXianQing(wokeXingQing);
    }

    @Override
    public ZhouQi findByAllZhouQi(Integer id) {
        return zhouQiDao.findByAllZhouQi(id);
    }


    @Override
    public WokeXingQing findByObject(Integer zhouQiId, Integer jieshu, Integer zhoushu) {
        return zhouQiDao.findByAll(zhouQiId,jieshu,zhoushu);
    }

    @Override
    public Integer updateZhouQi(ZhouQi zhouQi) {
        return zhouQiDao.updateZhouQi(zhouQi);
    }

    @Override
    public List<DaoChuPojo> findByDaoChu(Integer id) {
        List<DaoChuPojo> list=new ArrayList<>();
        for (int j = 1; j < 18; j++) {
            DaoChuPojo daoChuPojo=new DaoChuPojo();
            for (int i = 1; i < 8; i++) {
                WokeXingQing wokeXingQing=zhouQiDao.findByAll(id,j,i);
                if(i==1){
                    if(j==17){
                        daoChuPojo.setShijian(wokeXingQing.getClassTime());
                        if(wokeXingQing.getContont()!=null){
                            daoChuPojo.setZhouyi(wokeXingQing.getContont());
                        }
                        break;
                    }else{
                        daoChuPojo.setShijian(wokeXingQing.getClassTime());
                        if(wokeXingQing.getContont()!=null){
                            daoChuPojo.setZhouyi(wokeXingQing.getContont());
                        }
                    }

                }
                if(i==2){
                    if(wokeXingQing.getContont()!=null && wokeXingQing.getContont()!=""){
                        daoChuPojo.setZhouer(wokeXingQing.getContont());
                    }
                }
                if(i==3){
                    if(wokeXingQing.getContont()!=null){
                        daoChuPojo.setZhousan(wokeXingQing.getContont());
                    }
                }
                if(i==4){
                    if(wokeXingQing.getContont()!=null){
                        daoChuPojo.setZhousi(wokeXingQing.getContont());
                    }

                }
                if(i==5){
                    if(wokeXingQing.getContont()!=null){
                        daoChuPojo.setZhouwu(wokeXingQing.getContont());
                    }

                }
                if(i==6){
                    if(wokeXingQing.getContont()!=null){
                        daoChuPojo.setZhouliu(wokeXingQing.getContont());
                    }

                }
                if(i==7){
                    if(wokeXingQing.getContont()!=null){
                        daoChuPojo.setZhouqi(wokeXingQing.getContont());
                    }

                }
            }
            list.add(daoChuPojo);
        }

        return list;
    }

    public WokeXingQing  getAllTime(Integer num){
        WokeXingQing wokeXingQing=new WokeXingQing();
        if(num==1){
            wokeXingQing.setClassTime("到校时间");
            wokeXingQing.setClassDate("7:20~8:00");
        }if(num==2){
            wokeXingQing.setClassTime("早读");
            wokeXingQing.setClassDate("8:00~8:15");
        }if(num==3){
            wokeXingQing.setClassTime("第一节");
            wokeXingQing.setClassDate("8:20~9:00");
        }if(num==4){
            wokeXingQing.setClassTime("第二节");
            wokeXingQing.setClassDate("9:10~9:50");
        }if(num==5){
            wokeXingQing.setClassTime("眼保健操");
            wokeXingQing.setClassDate("9:50~9:55");
        }if(num==6){
            wokeXingQing.setClassTime("大课间活动");
            wokeXingQing.setClassDate("9:55~10:30");
        }if(num==7){
            wokeXingQing.setClassTime("第三节");
            wokeXingQing.setClassDate("10:30~11:10");
        }if(num==8){
            wokeXingQing.setClassTime("第四节");
            wokeXingQing.setClassDate("11:20~12:00");
        }if(num==9){
            wokeXingQing.setClassTime("中午");
            wokeXingQing.setClassDate("12:00~14:00");
        }if(num==10){
            wokeXingQing.setClassTime("到校时间");
            wokeXingQing.setClassDate("14:00~14:15");
        }if(num==11){
            wokeXingQing.setClassTime("午读");
            wokeXingQing.setClassDate("14:15~14:30");
        }if(num==12){
            wokeXingQing.setClassTime("第五节");
            wokeXingQing.setClassDate("14:40~15:20");
        }if(num==13){
            wokeXingQing.setClassTime("眼保健操");
            wokeXingQing.setClassDate("15:30~15:35");
        }if(num==14){
            wokeXingQing.setClassTime("第六节");
            wokeXingQing.setClassDate("15:35~16:15");
        }if(num==15){
            wokeXingQing.setClassTime("放学时间");
            wokeXingQing.setClassDate("16:30~16:40");
        }if(num==16){
            wokeXingQing.setClassTime("放学后");
            wokeXingQing.setClassDate("16:40");
        }
        if(num==17){
            wokeXingQing.setClassTime("全周工作");
            wokeXingQing.setClassDate("周一到周五");
        }
        return  wokeXingQing;
    }

}


