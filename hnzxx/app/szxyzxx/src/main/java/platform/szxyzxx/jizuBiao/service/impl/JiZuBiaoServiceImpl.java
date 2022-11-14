package platform.szxyzxx.jizuBiao.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.jizuBiao.dao.JiZuBiaoDao;
import platform.szxyzxx.jizuBiao.pojo.JiZuBiao;
import platform.szxyzxx.jizuBiao.pojo.JiZuTeacherBiao;
import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;
import platform.szxyzxx.jizuBiao.service.JiZuBiaoService;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiZuBiaoServiceImpl implements JiZuBiaoService {

    @Autowired
    private JiZuBiaoDao jiZuBiaoDao;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public List<Map<String, Object>> getAll(String schoolYear, String schoolTream, Integer zhoushu, Integer jizuId) {
        List<Map<String, Object>> list2=new ArrayList<>();
            List<JiZuBiao> list=jiZuBiaoDao.findByAll(jizuId);
            if(list.size()>0){
                for(JiZuBiao aa:list){
                    Map<String,Object> map=new HashMap<>();
                    map.put("groupId",aa.getId());
                    map.put("groupName",aa.getJizuName());
                    List<JiZuTeacherBiao> listb=jiZuBiaoDao.findByjizuId(aa.getId());
                    for(JiZuTeacherBiao bb:listb){
                        if(aa.getFuzeId()==bb.getTeacherId()){
                            bb.setIsPrincipal("true");
                        }else{
                            bb.setIsPrincipal("false");
                        }
                        Integer num=jiZuBiaoDao.findByCount(bb.getTeacherId(),schoolYear,schoolTream,zhoushu);
                        bb.setZongshu(num);
                    }
                    map.put("teacher",listb);
                    list2.add(map);
                }
            }
            return list2;
    }

    @Override
    public List<Map<String, Object>> AllJiZu(String schoolYear, String schoolTream, Integer zhoushu, UserInfo userInfo) {
        List<Map<String, Object>> list2=new ArrayList<>();
        List<JiZuBiao> list=jiZuBiaoDao.findByAllJZu(0,null);
        for(JiZuBiao aa:list){
            Map<String,Object> map=new HashMap<>();
            map.put("groupId",aa.getId());
            map.put("groupName",aa.getJizuName());
            map.put("teacherId",userInfo.getTeacherId());
            map.put("fuzeId",aa.getFuzeId());
            List<JiZuTeacherBiao> listb=jiZuBiaoDao.findByjizuId(aa.getId());
            for(JiZuTeacherBiao bb:listb){
                //是否负责人
                if(aa.getFuzeId()!=null) {
                    if (aa.getFuzeId().equals(bb.getTeacherId())) {
                        bb.setIsPrincipal("true");
                    } else {
                        bb.setIsPrincipal("false");
                    }
                }else{
                    bb.setIsPrincipal("false");
                }
                Integer num=jiZuBiaoDao.findByCount(bb.getTeacherId(),schoolYear,schoolTream,zhoushu);
                bb.setZongshu(num);
            }

            map.put("teacher",listb);
            List<JiZuBiao> ziJiZulist=jiZuBiaoDao.findByAllJZu(1,aa.getId());
            map.put("subGroup",AllZiJiZu(schoolYear,schoolTream,zhoushu,ziJiZulist,userInfo));
            list2.add(map);
        }
        return list2;
    }
    public List<Map<String, Object>> AllZiJiZu(String schoolYear, String schoolTream, Integer zhoushu,List<JiZuBiao> ziJiZulist,UserInfo userInfo) {
        List<Map<String, Object>> list2=new ArrayList<>();
        for(JiZuBiao aa:ziJiZulist) {
            Map<String, Object> map = new HashMap<>();
            map.put("subGroupId", aa.getId());
            map.put("subGroupName", aa.getJizuName());
            map.put("teacherId",userInfo.getTeacherId());
            map.put("fuzeId",aa.getFuzeId());
            List<JiZuTeacherBiao> listb = jiZuBiaoDao.findByjizuId(aa.getId());
            for (JiZuTeacherBiao bb : listb) {
                //是否负责人
                if(aa.getFuzeId()!=null) {
                    if (aa.getFuzeId().equals(bb.getTeacherId())) {
                        bb.setIsPrincipal("true");
                    } else {
                        bb.setIsPrincipal("false");
                    }
                }else{
                    bb.setIsPrincipal("false");
                }
                Integer num = jiZuBiaoDao.findByCount(bb.getTeacherId(), schoolYear, schoolTream, zhoushu);
                bb.setZongshu(num);
            }
            map.put("teacher",listb);
            list2.add(map);
        }

        return list2;
    }

    @Override
    public List<JiZuBiao> findByAllJiZu(Integer jizuId,Integer jizuceng) {
        return  jiZuBiaoDao.findByAllIs(jizuId,jizuceng);
    }

    @Override
    public String create(JiZuBiao jiZuBiao) {
        Integer num= jiZuBiaoDao.create(jiZuBiao);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public String createZiJiZu(JiZuBiao jiZuBiao) {
        Integer num= jiZuBiaoDao.createZijiZu(jiZuBiao);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    /*
    * 删除级组
    */
    @Override
    public String deleteJiZuBiao(JiZuBiao jiZuBiao) {
        Integer num= jiZuBiaoDao.updateJiZu(jiZuBiao);
        List<JiZuTeacherBiao> list=jiZuBiaoDao.findByjizuId(jiZuBiao.getId());
        for(JiZuTeacherBiao aa:list){
            jiZuBiaoDao.updateTeacherWoke(aa.getTeacherId(),jiZuBiao.getId());
        }
        jiZuBiaoDao.updateTeacherJiZuId(jiZuBiao.getId());
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    /*
    * 级组名称修改
    */
    @Override
    public String updateJiZuBiao(JiZuBiao jiZuBiao) {
        Integer num= jiZuBiaoDao.updateJiZu(jiZuBiao);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    //设置级组长
    @Override
    public String updateTeacherJiZuZhang(Integer teacherId, Integer id) {
        Integer num= jiZuBiaoDao.updateTeacherJiZuZhang(teacherId,id);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public String createTeacherJiZu(JiZuTeacherBiao jiZuTeacherBiao) {
        Integer num= jiZuBiaoDao.createTeacherJiZu(jiZuTeacherBiao);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    //删除教师
    @Override
    public String updateTeacherJiZu(JiZuTeacherBiao jiZuTeacherBiao,Integer jizuId) {
        Integer num= jiZuBiaoDao.updateTeacherJiZu(jiZuTeacherBiao);
            jiZuBiaoDao.updateTeacherWoke(jiZuTeacherBiao.getTeacherId(),jizuId);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public String createTeacherWoke(TeacherWoke teacherWoke) {
        Integer num= jiZuBiaoDao.createTeacherWoke(teacherWoke);
        List<Map<String,Object>> mapList=basicSQLService.find("select user_id from pj_teacher where is_delete=0 and  id="+teacherWoke.getTeacherId());
       // sendWechatNotice(mapList.get(0).get("user_id").toString());
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public List<TeacherWoke> findByAllTeacherId(Integer teacherId,String schoolYear,String schoolTrem,Integer zhoushu) {
        return jiZuBiaoDao.findByAllTeacherId(teacherId,schoolYear,schoolTrem,zhoushu);
    }

    @Override
    public String updateDelete(Integer id) {
        Integer num= jiZuBiaoDao.updateDelete(id);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public String updateTeacherWoke(Integer id, String tontent) {
        Integer num= jiZuBiaoDao.updateTeacherWokes(id,tontent);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    @Override
    public List<Teacher> teacherAll(String teacher, Integer buMenId, String subjectId, Integer jiZuId, Page page) {
        List<Integer> teacherIdList=jiZuBiaoDao.findByIdIntegers(jiZuId);
        String str="";
        for(Integer aa:teacherIdList){
            if(str=="" || str==null){
                str=aa.toString();
            }else{
                str+=","+aa.toString();
            }
        }
        List<Teacher> list=new ArrayList<>();
        if(teacherIdList.size()>0){
            list=jiZuBiaoDao.findByTeacher(teacher,buMenId,subjectId,jiZuId,page);
        }else{
            list=jiZuBiaoDao.findByTeacher(teacher,buMenId,subjectId,null,page);
        }
        return list;
    }

    @Override
    public List<TeacherWoke> findByDaoChu(String schoolYear, String schoolTrem, Integer zhoushu) {
        return jiZuBiaoDao.findByDaoChu(schoolYear,schoolTrem,zhoushu);
    }

    @Override
    public String addTeacherAllWoke(TeacherWoke teacherWoke) {
      List<JiZuTeacherBiao> list=  jiZuBiaoDao.findByjizuId(teacherWoke.getJizuId());
        List<JiZuBiao> jiZuBiao=jiZuBiaoDao.findByAll(teacherWoke.getJizuId());
      for(JiZuTeacherBiao aa:list){
          teacherWoke.setTeacherId(aa.getTeacherId());
          teacherWoke.setNumenjizuName(jiZuBiao.get(0).getJizuName());
          jiZuBiaoDao.createTeacherWoke(teacherWoke);
          List<Map<String,Object>> mapList=basicSQLService.find("select user_id from pj_teacher where is_delete=0 and  id="+aa.getTeacherId());
          //sendWechatNotice(mapList.get(0).get("user_id").toString());
      }
        return "success";
    }

    private void sendWechatNotice(String userId){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+userId+" and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("教职工事项通知","您有新的教职工事项待查看");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }
}
