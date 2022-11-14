package platform.szxyzxx.web.yihaostudy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.szxyzxx.ishangkelilu.pojo.StudyHabits;
import platform.szxyzxx.ishangkelilu.service.StudyHabitsService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/zykjStudy")
public class YiHaoStudyController {

    @Autowired
    private StudyHabitsService studyHabitsService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private GradeService gradeService;
    /*
     * 获取所有学生
     */
    @RequestMapping(value ="/studentAll")
    public List<StudentVo> findByStudent(@CurrentUser UserInfo userInfo,
                                      @RequestParam Integer gradeId,
                                      @RequestParam  Integer teamId) {
        List<StudentVo> list = studentService.findStudentVoByTeam(userInfo.getSchoolId(),userInfo.getSchoolYear(), gradeId,teamId);
        return list;
    }
    /*
     * 根据年级id获取班级
    */
    @RequestMapping(value ="/teamAll")
    public  Map<String,Object> findByTeam(@CurrentUser UserInfo userInfo,
                                       @RequestParam Integer gradeId) {
        Map<String,Object> map=new HashMap<>();
            if(gradeId==null){
                List list=null;
                map.put("stats",false);
                map.put("message","参数为空");
                map.put("data",list);
                return map;
            }else{
                List<Team> list = teamService.findTeamOfGradeAndSchool(gradeId,userInfo.getSchoolId());
                if(list.size()<1){
                    map.put("stats",false);
                    map.put("message","该年级无班级信息");
                    map.put("data",list);
                    return map;
                }
                map.put("stats",true);
                map.put("message","success");
                map.put("data",list);
                return map;
            }

    }

    /*
     * 根据老师id获取年级
     */
    @RequestMapping(value ="/gradeAll")
    public Map<String,Object> findByGade(@CurrentUser UserInfo userInfo) {
        Map<String,Object> map=new HashMap<>();

        if(userInfo.getTeacherId()==null){
            List list=null;
            map.put("stats",false);
            map.put("message","该账号非教师账号");
            map.put("data",list);
            return map;
        }else{
            List<Grade> list = gradeService.findGradeOfSchoolYearAndTeacherId(userInfo.getSchoolId(),userInfo.getSchoolYear(),userInfo.getTeacherId());
            if(list.size()<1){
                map.put("stats",false);
                map.put("message","该账号无对应年级信息");
                map.put("data",list);
                return map;
            }
            map.put("stats",true);
            map.put("message","success");
            map.put("data",list);
            return map;
        }
    }

    /*
     * 获取课堂评分记录
     */
    @RequestMapping(value ="/ScoreAll")
    public Map<String,Object> findByScoreAll(@CurrentUser UserInfo userInfo, @RequestParam Integer teamId) {
        Map<String,Object> map=new HashMap<>();
        if(teamId==null) {
            List list = null;
            map.put("stats", false);
            map.put("message", "参数为空");
            map.put("data", list);
            return map;
        }else{
            List<StudyHabits> list = studyHabitsService.findByShangke(teamId, userInfo.getTeacherId());
            if(list.size()<1){
                map.put("stats",false);
                map.put("message","该账号无对应年级信息");
                map.put("data",list);
                return map;
            }
            map.put("stats",true);
            map.put("message","success");
            map.put("data",list);
            return map;
        }

    }
    /*
     * 课堂评分
     */
    @RequestMapping(value ="/classScore")
    public Map<String,Object> findByScoreClassPingfen(@CurrentUser UserInfo userInfo, StudyHabits studyHabits) {
        if(studyHabits!=null){}
        String str=studyHabitsService.createOrupdate(studyHabits);
        Map<String,Object> map=new HashMap<>();
        if(str=="success"){
            map.put("stats",true);
            map.put("message","评分成功");
        }else{
            map.put("stats",false);
            map.put("message","评分失败");
        }
           return map;
    }

    /*
     * 获取课堂评分撤回
     */
    @RequestMapping(value ="/ScoreWithdraw")
    public Map<String,Object> findByScoreWithdraw(@RequestParam Integer id) {
        Boolean falg = studyHabitsService.findBychehui(id);
        Map<String,Object> map=new HashMap<>();
        if(falg){
            map.put("message","撤回成功");
            map.put("status",true);
        }else{
            map.put("message","撤回失败");
            map.put("status",false);
        }
        return map;
    }
    /*
     * 下课后提交所有上课记录
     */
    @RequestMapping(value ="/submitScore")
    public Map<String,Object> findBySubmitScore(@CurrentUser UserInfo userInfo,@RequestParam Integer teamId) {
        Map<String,Object> map=new HashMap<>();
        String str=studyHabitsService.findByXiake(userInfo, teamId);
        if(str=="success"){
            map.put("stats",true);
            map.put("message","提交成功");

        }else{
            map.put("stats",false);
            map.put("message","提交失败");
        }
        return map;
    }

}
