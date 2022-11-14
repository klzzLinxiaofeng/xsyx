package com.xunyunedu.team.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.team.pojo.ParamPojo;
import com.xunyunedu.team.service.ExamTeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 班级成绩&成绩分析
 *
 * @author: yhc
 * @Date: 2020/12/20 15:08
 * @Description:
 */
@RestController
@RequestMapping("/team/score")
public class ExamTeamController {

    @Autowired
    private ExamTeamService examTeamService;

    @Autowired
    private BasicSQLService basicSQLService;



    /**
     * 教师端（班主任）--获取当前老师教的班级
     *
     * @param teacherId
     * @param schoolId
     * @return
     */
    @GetMapping("/getTeacherClass")
    public ApiResult getTeacherClass(@RequestParam("teacherId") Integer teacherId, @RequestParam("schoolId") Integer schoolId) {
        if (teacherId == null || schoolId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, examTeamService.getTeacherClass(teacherId, schoolId));
    }


    /**
     * 教师端--获取当前班级的科目
     *
     * @param teamId   班级id
     * @param schoolId 学校id
     * @param type     1：统考 0：平时
     * @return
     */
    @GetMapping("/getTeacherSubject")
    public ApiResult getTeacherSubject(@RequestParam("teamId") Integer teamId, @RequestParam("schoolId") Integer schoolId, @RequestParam("type") Integer type) {
        if (teamId == null || schoolId == null || type == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, examTeamService.getTeacherSubject(teamId, schoolId, type));
    }

    /**
     * 教师端-班级成绩管理 -- 班主任可以查看
     * <p>
     * 当前学年的考试成绩记录
     *
     * @param pojo
     * @return
     */
    @GetMapping("/examTeam")
    public ApiResult examTeam(ParamPojo pojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (pojo == null || pojo.getSchoolId() == null || pojo.getTeamId() == null || pojo.getType() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 获取当前学年下的所有考试成绩
        return new ApiResult(ResultCode.SUCCESS, examTeamService.getExamTeamList(pojo, pageNum, pageSize));
    }

    /**
     * 教师端-学生成绩管理
     *
     * @param id
     * @return
     */
    @GetMapping("/stuScore")
    public ApiResult studentScore(@RequestParam("id") Integer id, @RequestParam("schoolId") Integer schoolId) {
        if (id == null || schoolId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        ApiResult result = new ApiResult(ResultCode.SUCCESS);
        result.setData(examTeamService.getStudentScoreByTeamId(id, schoolId));
        return result;
    }

    /**
     * 学生端--获取学生参与过的考试科目
     * <p>
     * 包含所有历史考试科目记录
     *
     * @param stuId    学生id
     * @param schoolId 学校id
     * @param type     1：统考 0：平时
     * @return
     */
    @GetMapping("/getStuSubject")
    public ApiResult getStuSubject(@RequestParam("stuId") Integer stuId, @RequestParam("schoolId") Integer schoolId, @RequestParam("type") Integer type) {
        if (stuId == null || schoolId == null || type == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, examTeamService.getStuSubject(stuId, schoolId, type));
    }


    public ApiResult getSumScore(Integer stuId, Boolean showRank){

        try {
            List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
            if(termInfo.size()!=1){
                return ApiResult.error("当前学期配置有误");
            }
            //学生当前学年所在班级和年级id
            List<Map<String,Object>> teamInfoList=basicSQLService.find("select pts.team_id,pts.grade_id from pj_team_student pts inner join pj_team t on t.id=pts.team_id where pts.is_delete=0 and pts.student_id="+stuId+" and t.school_year='"+termInfo.get(0).get("school_year")+"'");
            if(teamInfoList.size()!=1){
                return ApiResult.error("找不到当前学年学生班级信息");
            }



            Object teamId=teamInfoList.get(0).get("team_id");
            Object gradeId=teamInfoList.get(0).get("grade_id");
            Object termCode=termInfo.get(0).get("school_term_code");
            //期中班级排名
            List<Map<String,Object>> qzTeamList=querySumScoreList(teamId,null,"1",termCode);
            //期中年级排名
            List<Map<String,Object>> qzGradeList=querySumScoreList(null,gradeId,"1",termCode);

            //期末班级排名
            List<Map<String,Object>> qmTeamList=querySumScoreList(teamId,null,"2",termCode);
            //期末年级排名
            List<Map<String,Object>> qmGradeList=querySumScoreList(null,gradeId,"2",termCode);
            Map<String,Object> data=new LinkedHashMap<>();


            putSumScoreInfo(data,"qzTeam","qzScore",qzTeamList,stuId,showRank);
            putSumScoreInfo(data,"qzGrade",null,qzGradeList,stuId,showRank);
            putSumScoreInfo(data,"qmTeam","qmScore",qmTeamList,stuId,showRank);
            putSumScoreInfo(data,"qmGrade",null,qmGradeList,stuId,showRank);
            return ApiResult.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.error("服务器错误");
        }
    }


    private void putSumScoreInfo(Map<String,Object> data,String keyPrefix,String scoreKey,List<Map<String,Object>> rankList,Integer stuId,boolean showRank){
        String rankKey=keyPrefix+"Rank";
        String sizeKey=keyPrefix+"Size";
        data.put(sizeKey,rankList.size()+"");
        if(rankList.size()==0){
            if(scoreKey!=null) {
                data.put(scoreKey, "暂无考试");
            }
            if(!showRank){
                data.put(rankKey,"暂不排名");
            }else {
                data.put(rankKey, "0");
            }

            return;

        }
            Map<String,Object> infoMap=findSumScoreList(rankList,stuId);
            if(infoMap==null){
                if(scoreKey!=null) {
                    data.put(scoreKey, "暂无考试");
                }
                if(!showRank){
                    data.put(rankKey,"暂不排名");
                }else {
                    data.put(rankKey, "0");
                }
                return;
            }
            String score=new BigDecimal(infoMap.get("score").toString()).stripTrailingZeros().toPlainString();
            if(StringUtils.isEmpty(score) || score.equals("0")){
                data.put(scoreKey,"暂无成绩");
            }else {
                data.put(scoreKey, score);
            }
            if(!showRank){
                data.put(rankKey,"暂不排名");
            }else {
                if(StringUtils.isEmpty(score) || score.equals("0")){
                    data.put(rankKey,"0");
                }else {
                    data.put(rankKey, infoMap.get("rank"));
                }
            }

    }


    private List<Map<String,Object>> querySumScoreList(Object teamId,Object gradeId,String examType,Object termCode){
        String sql="SELECT ss.student_id, sum(ss.score) score FROM pj_student_score ss INNER JOIN pj_exam_team_subject pets ON pets.id = ss.exam_team_subject_id WHERE pets.term_code='"+termCode+"' and pets.exam_type='"+examType+"'";
       if(teamId!=null) {
           sql += " and ss.team_id=" + teamId;
       }

       if(gradeId!=null){
           sql+=" and ss.grade_id="+gradeId;
       }

       sql+=" AND ss.school_id = 215 group by ss.student_id";
        return basicSQLService.find(sql);
    }


    private Map<String,Object> findSumScoreList(List<Map<String,Object>> list,Integer stuId){
        for (Map<String, Object> map : list) {
            if(map.get("student_id").toString().equals(stuId.toString())){
                String rank=findSumScoreRank(list,(BigDecimal)map.get("score"));
                map.put("rank",rank);
                return map;
            }
        }
        return null;
    }


    private String findSumScoreRank(List<Map<String,Object>> list,BigDecimal stuScore){
        if(stuScore==null || stuScore.compareTo(new BigDecimal("0"))==0){
            return "0";
        }
        int rank=1;
        for (Map<String, Object> map : list) {
            BigDecimal score=(BigDecimal)map.get("score");
            if(score!=null && score.compareTo(stuScore)>0){
                rank++;
            }
        }
        return rank+"";
    }



    /**
     * 学生端--成绩列表
     *
     * @param pojo
     * @return
     */
    @PostMapping("/stuScoreAnalysis")
    public ApiResult stuScoreAnalysis(@RequestBody ParamPojo pojo) {

//        if(pojo.getCode()!=null && pojo.getCode().equals("-1")){
//            Boolean showRank=(Boolean)basicSQLService.findUnique("SELECT Interrupteur FROM pj_job_control WHERE is_deleted = 0 AND name = 'STU_SCORE_ORDER'");
//
//            /**
//             * 推荐按下面的sql查询总分和排名，但是不想改了
//             * SELECT
//             * 	e.exam_name,s.student_id,sum(s.score) sum,s.team_id
//             * FROM
//             * 	pj_student_score s
//             * 	INNER JOIN pj_exam_team_subject e ON e.id = s.exam_team_subject_id
//             * 	group by e.exam_name,s.student_id,s.team_id
//             * 	order by e.precise_start_date,e.create_date desc
//             *
//             */
//
//            ApiResult apiResult=getSumScore(pojo.getStuId(),showRank);
//            if(apiResult.getCode()!=200){
//                return apiResult;
//            }
//            Map<String,Object> data=(Map<String,Object>)apiResult.getData();
//            List<ExamTeamPojo> list = new ArrayList<>(2);
//            ExamTeamPojo qz=new ExamTeamPojo();
//            qz.setName("");
//            qz.setExamName("期中考试总成绩");
//            qz.setScore((String) data.get("qzScore"));
//            qz.setOrder((String) data.get("qzTeamRank"));
//            qz.setOrderSize((String) data.get("qzTeamSize"));
//            qz.setStuSchoolOrder((String) data.get("qzGradeRank"));
//            qz.setStuSchoolOrderSize((String) data.get("qzGradeSize"));
//
//            list.add(qz);
//
//            ExamTeamPojo qm=new ExamTeamPojo();
//            qm.setName("");
//            qm.setExamName("期末考试总成绩");
//            qm.setScore((String) data.get("qmScore"));
//            qm.setOrder((String) data.get("qmTeamRank"));
//            qm.setOrderSize((String) data.get("qmTeamSize"));
//            qm.setStuSchoolOrder((String) data.get("qmGradeRank"));
//            qm.setStuSchoolOrderSize((String) data.get("qmGradeSize"));
//            list.add(qm);
//            HashMap<String, Object> map = new HashMap<>(2);
//            map.put("isStuScore", showRank);
//            map.put("list", new PageInfo(list));
//            return ApiResult.success(map);
//        }

        if (pojo == null || pojo.getSchoolId() == null || pojo.getStuId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        ApiResult result = new ApiResult(ResultCode.SUCCESS);
        result.setData(examTeamService.getStudentScoreAnalysis(pojo, null, null));
        return result;
    }


    @RequestMapping("/getTermList")
    public ApiResult getTermList(){
        try {
            List list=basicSQLService.find("select t.`code` termCode,concat(y.`name`,t.`name`) termFullName from pj_school_term t inner join  pj_school_year y on y.id=t.school_year_id and y.school_id=215 and y.is_delete=0 left join pj_school_term_current c on c.school_term_code=t.`code` where t.school_id=215 and t.is_delete=0 order by c.id desc,t.school_year desc,t.`code` desc");
            return ApiResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.error("服务器内部错误");
        }
    }


}
