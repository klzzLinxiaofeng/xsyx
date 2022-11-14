package platform.szxyzxx.web.shenxue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.literacy.service.LiteracyService;
import platform.szxyzxx.pingyumoban.service.PingYuMoBanService;
import platform.szxyzxx.pingyumoban.vo.PingYuMoBan;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/9 9:54
 * @Version 1.0
 * 德育方面的指标进行升，学年，学期操作
 */
@RestController
@RequestMapping("/shenxue")
public class SheXueController {

    @Autowired
    private PingYuMoBanService pingYuMoBanService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private LiteracyService literacyService;
    /**
     * 评语模板升级
     * @return
     */
    @RequestMapping("/deyuShenXue")
    public String upDateShenXueQi(@CurrentUser UserInfo userInfo){
        List<PingYuMoBan> pingYuMoBanList=pingYuMoBanService.findBySchoolYear(Integer.parseInt(userInfo.getSchoolYear()),userInfo.getSchoolTermCode());
        if(pingYuMoBanList.size()>0){
            return "当前学期已经存在评语模板";
        }else {
            /*获取老的学年和学期*/
            String oldTrem = "";
            Integer oldYear = null;
            if (Integer.parseInt(userInfo.getSchoolTermCode().substring(9, 10)) == 2) {
                oldTrem = "215-" + userInfo.getSchoolYear() + "-1";
                oldYear = Integer.parseInt(userInfo.getSchoolYear());
            } else {
                oldTrem = "215-" + (Integer.parseInt(userInfo.getSchoolYear()) - 1) + "-2";
                oldYear = Integer.parseInt(userInfo.getSchoolYear()) - 1;
            }
            List<PingYuMoBan> pingYuMoBans = pingYuMoBanService.findBySchoolYear(oldYear, oldTrem);
            for (PingYuMoBan pingYuMoBan : pingYuMoBans) {
                Grade grade = gradeService.findGradeBySchoolIdYearAndName(userInfo.getSchoolId(), oldYear.toString(), pingYuMoBan.getGradeName());
                pingYuMoBan.setSchoolTrem(userInfo.getSchoolTermCode());
                pingYuMoBan.setSchoolYear(userInfo.getSchoolYear());
                //获取当前学年学期的对应的指
                if (!pingYuMoBan.getGradeName().equals("七年级")) {
                    //当前学年的年级
                    Grade newgrade = gradeService.findGradeBySchoolIdYearAndName(userInfo.getSchoolId(), userInfo.getSchoolYear(), pingYuMoBan.getGradeName());
                    LiteracyVo literacyVo = literacyService.findBySchoolYearAndSchoolTrem(userInfo.getSchoolYear(), userInfo.getSchoolTermCode(), newgrade.getId(), pingYuMoBan.getSubjectId(), pingYuMoBan.getSubjectZhiBiaoName());
                    pingYuMoBan.setSubjctZhiBiaoId(literacyVo.getId());
                    pingYuMoBan.setGradeId(newgrade.getId());
                    pingYuMoBanService.create(pingYuMoBan);
                } else {

                }
            }
        }
        return "success";
    }
    /**
     * 学科素养指标升级
     * @return
     */
    @RequestMapping("/xuekesuyang")
    public String upDatexuekesuyang(@CurrentUser UserInfo userInfo){
        List<LiteracyVo> literacyVoList=literacyService.findByAll(userInfo.getSchoolTermCode(),Integer.parseInt(userInfo.getSchoolYear()),null,null,null);

        if(literacyVoList.size()>0){
            return "当前学年已经存在学科素养指标";

        }else{
            /*获取老的学年和学期*/
            String oldTrem="";
            Integer oldYear=null;
            if(Integer.parseInt(userInfo.getSchoolTermCode().substring(9,10))==2){
                oldTrem="215-"+userInfo.getSchoolYear()+"-1";
                oldYear=Integer.parseInt(userInfo.getSchoolYear());
            }else{
                oldTrem="215-"+(Integer.parseInt(userInfo.getSchoolYear())-1)+"-2";
                oldYear=Integer.parseInt(userInfo.getSchoolYear())-1;
            }
            List<LiteracyVo> pingYuMoBans=literacyService.findByAll(oldTrem,oldYear,null,null,null);
            for(LiteracyVo literacyVo:pingYuMoBans){
                Grade grade =gradeService.findGradeBySchoolIdYearAndName(userInfo.getSchoolId(),userInfo.getSchoolYear(),literacyVo.getGradeName());
                if(grade!=null){
                    if(grade.getId()!=null) {
                        literacyVo.setXq(userInfo.getSchoolTermCode());
                        literacyVo.setXn(userInfo.getSchoolYear());
                        literacyVo.setGradeId(grade.getId());
                        literacyService.create(literacyVo, userInfo);
                    }
                }else{}
                //获取对应的指标
            }
        }
        return "success";
    }
}
