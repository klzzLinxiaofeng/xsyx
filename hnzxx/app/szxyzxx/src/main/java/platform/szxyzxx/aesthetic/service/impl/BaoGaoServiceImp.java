package platform.szxyzxx.aesthetic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.PublicClass;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.aesthetic.pojo.AestheticPojo;
import platform.szxyzxx.aesthetic.pojo.BaoGaoQuery;
import platform.szxyzxx.aesthetic.service.AestheticService;
import platform.szxyzxx.aesthetic.service.BaoGaoService;
import platform.szxyzxx.character.dao.EvaluationDao;
import platform.szxyzxx.character.dao.RecordsDao;
import platform.szxyzxx.character.pojo.Evaluation;
import platform.szxyzxx.character.pojo.Records;
import platform.szxyzxx.indicators.pojo.IndicatorsStudent;
import platform.szxyzxx.indicators.service.IndicatorsStudentService;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;
import platform.szxyzxx.innovation.service.PracticeInnovationService;
import platform.szxyzxx.ishangkelilu.dao.StudyHabitsDao;
import platform.szxyzxx.ishangkelilu.pojo.StudyHabits;
import platform.szxyzxx.literacy.dao.LiteracyDao;
import platform.szxyzxx.literacy.dao.LiteracyStudentDao;
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaoGaoServiceImp implements BaoGaoService {
    @Autowired
    private RecordsDao recordsDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private EvaluationDao evaluationDao;
    @Autowired
    private StudyHabitsDao studyHabitsDao;
    @Autowired
    private IndicatorsStudentService indicatorsStudentService;
    @Autowired
    private PracticeInnovationService prainnService;
    @Autowired
    private AestheticService aestheticService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LiteracyDao literacyDao;
    @Autowired
    private LiteracyStudentDao literacyStudentDao;
    public List<Student> getAllId(UserInfo userInfo, BaoGaoQuery baoGaoQuery){
        //获取该学生的id
        List<Student> studentIdList=studentDao.findByall(userInfo.getSchoolId(),baoGaoQuery.getYear(),baoGaoQuery.getTeamId(),baoGaoQuery.getGradeId(),baoGaoQuery.getStudentName(),null);
        if(studentIdList.size()<=0){
            return null;
        }
        return studentIdList;
    }

    public List<StudentVo> getByAllTeamAll(UserInfo userInfo, BaoGaoQuery baoGaoQuery){
        //获取该班级的所有学生
        List<StudentVo> studentIdTeamList=studentDao.findStudentVoByTeam(userInfo.getSchoolId(),baoGaoQuery.getYear(),null,baoGaoQuery.getTeamId());

        return studentIdTeamList;
    }

    public List<StudentVo> getByAllGradeAll(UserInfo userInfo, BaoGaoQuery baoGaoQuery){
        //获取该学生的id
        //获取该年级的所有学生
        List<StudentVo> studentIdGradeList=studentDao.findStudentVoByTeam(userInfo.getSchoolId(),baoGaoQuery.getYear(),baoGaoQuery.getGradeId(),null);

        return studentIdGradeList;
    }

    @Override
    public Map<String, Object> findByCharacterAll(UserInfo userInfo, BaoGaoQuery baoGaoQuery) {
        //获取该学生的id
        List<Student> studentIdList=getAllId(userInfo,baoGaoQuery);
        if(studentIdList==null){
            return null;
        }
        //获取该班级的所有学生
        List<StudentVo> studentIdTeamList=getByAllTeamAll(userInfo,baoGaoQuery);
        //获取该年级的所有学生
        List<StudentVo> studentIdGradeList=getByAllGradeAll(userInfo,baoGaoQuery);

        Integer studentId=studentIdList.get(0).getId();
        //评价纪录
        List<Records> recordsList=recordsDao.findByAlls(userInfo.getSchoolId(),studentId,null);
        //各指标班级，年级，学生班级
        //所有指标
        List<Map<String,Object>> list=new ArrayList<>();
        List<Evaluation> evaluationList =evaluationDao.findByAlls();
        for(Evaluation evaluationlist:evaluationList){
            Map<String,Object> map=new HashMap<>();
            //获取学生的总分

           Records records=recordsDao.findByZongScore(studentId,evaluationlist.getId());
            map.put("zhibiao",evaluationlist.getName());
            map.put("zhibiaoId",evaluationlist.getId());
            if(records==null){
                map.put("zongfen",evaluationlist.getInitScore());
            }else {
                map.put("zongfen", records.getScore() + evaluationlist.getInitScore());
            }
            //------获取班级平均分------
            int bjFenshu=0;
            for(StudentVo studentVo:studentIdTeamList){
                //获取学生的总分
                Records recordsTeam=recordsDao.findByZongScore(studentId,evaluationlist.getId());
                if(recordsTeam==null){
                    bjFenshu+=evaluationlist.getInitScore();
                }  else{
                    bjFenshu+=recordsTeam.getScore()+evaluationlist.getInitScore();
                }
            }
            int bjSvg=bjFenshu/studentIdTeamList.size();
            map.put("bjSvg",bjSvg);

            //------获取年级平均分------
            int njFenshu=0;
            for(StudentVo studentVo:studentIdGradeList){
                //获取学生的总分
                Records recordsGrade=recordsDao.findByZongScore(studentId,evaluationlist.getId());
                if(recordsGrade==null){
                    njFenshu +=evaluationlist.getInitScore();
                }else {
                    njFenshu += recordsGrade.getScore() + evaluationlist.getInitScore();
                }
            }
            int njSvg=njFenshu/studentIdTeamList.size();
            map.put("njSvg",njSvg);
            list.add(map);
        }
        Map<String,Object> map2=new HashMap<>();
        map2.put("pingjia",recordsList);
        map2.put("tupiao",list);
        return map2;
    }

    @Override
    public List<Map<String, Object>> findByAllXueXi(UserInfo userInfo, BaoGaoQuery baoGaoQuery) {
        //获取该学生的id
        List<Student> studentIdList=getAllId(userInfo,baoGaoQuery);
        if(studentIdList==null){
            return null;
        }
        Integer id=studentIdList.get(0).getId();
        //获取该班级的所有学生
        List<StudentVo> studentIdTeamList=getByAllTeamAll(userInfo,baoGaoQuery);
        //获取该年级的所有学生
        List<StudentVo> studentIdGradeList=getByAllGradeAll(userInfo,baoGaoQuery);
        List<Map<String,Object>> list=new ArrayList<>();
        Integer [] zhibiao={1,2,3,4,5,6};
        for(int i=0;i<zhibiao.length;i++){
            Map<String,Object> map=new HashMap<>();
            StudyHabits studyHabits=studyHabitsDao.findByIdAndLeixing(id,zhibiao[i]);
            if(studyHabits==null){
                return null;
            }
            map.put("studentScore",studyHabits.getScore());
            int teamScore=0;
            for(StudentVo aa:studentIdTeamList){
                StudyHabits studyHabitsteam=studyHabitsDao.findByIdAndLeixing(aa.getId(),zhibiao[i]);
                if(studyHabitsteam==null){
                    teamScore+=0;
                }else{
                    teamScore+=studyHabitsteam.getScore();
                }
            }
            map.put("teamSvgScore",teamScore/studentIdTeamList.size());

            int gradeScore=0;
            for(StudentVo aa:studentIdGradeList){
                StudyHabits studyHabitsteam=studyHabitsDao.findByIdAndLeixing(aa.getId(),zhibiao[i]);
                if(studyHabitsteam==null){
                    gradeScore+=0;
                }else{
                    gradeScore+=studyHabitsteam.getScore();
                }
            }
            map.put("gradeSvgScore",gradeScore/studentIdGradeList.size());
            list.add(map);
        }
        return list;
    }

    @Override
    public IndicatorsStudent findByAllTiZhi(UserInfo userInfo, BaoGaoQuery baoGaoQuery) {
        //获取该学生的id
        List<Student> studentIdList=getAllId(userInfo,baoGaoQuery);
        if(studentIdList==null){
            return null;
        }
        Integer id=studentIdList.get(0).getId();
        IndicatorsStudent indicatorsStudent=indicatorsStudentService.findByIndicator(userInfo,id);
        return indicatorsStudent;
    }

    @Override
    public PracticeInnovation findByAllShijian(UserInfo userInfo, BaoGaoQuery baoGaoQuery) {
        //获取该学生的id
        List<Student> studentIdList=getAllId(userInfo,baoGaoQuery);
        if(studentIdList==null){
            return null;
        }
        Integer id=studentIdList.get(0).getId();
        PracticeInnovation practiceInnovations=
                prainnService.findByPraInner(userInfo,id);
        if (practiceInnovations.getPctreId() != null && !practiceInnovations.getPctreId().equals("")) {
            String [] uuid=practiceInnovations.getPctreId().split(",");
            String [] url=new String [uuid.length];
            // 根据id查询封面的url
            for(int a=0;a<uuid.length;a++){
                FileResult file = fileService.findFileByUUID(uuid[a]);
                if (file != null) {
                    url[a]=file.getHttpUrl();
                }
            }
            practiceInnovations.setPctreUrl(url);
        }
        if (practiceInnovations.getJiangzhuanId() != null && !practiceInnovations.getJiangzhuanId().equals("")) {

            String[] uuid = practiceInnovations.getJiangzhuanId().split(",");
            String [] url=new String [uuid.length];
            // 根据id查询奖状的url
            for (int a = 0; a < uuid.length; a++) {
                FileResult file = fileService.findFileByUUID(uuid[a]);
                if (file != null) {
                   url[a]=file.getHttpUrl();
                }
            }
            practiceInnovations.setJiangzhuanUrl(url);
        }
        for(PublicClass dd:practiceInnovations.getPublicClassList()){
            setUrl(dd);
        }
        return practiceInnovations;
    }
    /**
     * 设置当前课程对应的封面
     *
     * @param publicClassPojo
     */
    public void setUrl(PublicClass publicClassPojo) {
        String coverUuid = publicClassPojo.getCoverUuid();
        if (coverUuid != null && !("").equals(coverUuid)) {
            // 根据uuid查询封面的url
            FileResult file = fileService.findFileByUUID(coverUuid);
            if (file != null) {
                publicClassPojo.setCoverUrl(file.getHttpUrl());
            }
        }
    }

    @Override
    public AestheticPojo findByAllAestheticPojo(UserInfo userInfo, BaoGaoQuery baoGaoQuery) {
        //获取该学生的id
        List<Student> studentIdList=getAllId(userInfo,baoGaoQuery);
        if(studentIdList==null){
            return null;
        }
        Integer id=studentIdList.get(0).getId();
      /*  AestheticPojo aestheticPojo=aestheticService.findByAestheticPojo(userInfo,id);
        if(aestheticPojo!=null) {
            if (aestheticPojo.getFineArtId() != null && !aestheticPojo.getFineArtId() .equals("")) {
                String [] uuid=aestheticPojo.getFineArtId().split(",");
                String [] url=new String [uuid.length];
                // 根据id查询封面的url
                for(int a=0;a<uuid.length;a++){
                    FileResult file = fileService.findFileByUUID(uuid[a]);
                    if (file != null) {
                        url[a]=file.getHttpUrl();
                    }
                }
                aestheticPojo.setFineArtUrlarr(url);
            }
            if (aestheticPojo.getJiangzhuanId() != null && !aestheticPojo.getJiangzhuanId().equals("")) {
                String [] uuid=aestheticPojo.getJiangzhuanId().split(",");
                String [] url=new String [uuid.length];
                // 根据id查询封面的url
                for(int a=0;a<uuid.length;a++){
                    FileResult file = fileService.findFileByUUID(uuid[a]);
                    if (file != null) {
                        url[a]=file.getHttpUrl();
                    }
                }
                aestheticPojo.setJiangzhuanUrlarr(url);

            }
            if (aestheticPojo.getGameWorksId() != null && !aestheticPojo.getGameWorksId() .equals("")) {
                String[] uuid = aestheticPojo.getGameWorksId().split(",");
                String [] url=new String [uuid.length];
                // 根据id查询封面的url
                for (int a = 0; a < uuid.length; a++) {
                    FileResult file = fileService.findFileByUUID(uuid[a]);
                    if (file != null) {
                        url[a]=file.getHttpUrl();
                    }
                }
                aestheticPojo.setGameWorksUrlarr(url);
            }}
               */ return null;
    }

    @Override
    public List<Map<String, Object>> findByAllsubject(UserInfo userInfo, BaoGaoQuery baoGaoQuery,Integer subjectId) {
        List<Map<String,Object>> mapList=new ArrayList<>();

        //获取该学生的id
        List<Student> studentIdList=getAllId(userInfo,baoGaoQuery);
        if(studentIdList==null){
            return null;
        }
        Integer id=studentIdList.get(0).getId();
        //获取该班级的所有学生
        List<StudentVo> studentIdTeamList=getByAllTeamAll(userInfo,baoGaoQuery);
        //获取该年级的所有学生
        List<StudentVo> studentIdGradeList=getByAllGradeAll(userInfo,baoGaoQuery);


            //根据科目查询指标
            List<LiteracyVo> literacyVoList= literacyDao.findByAll(null,Integer.parseInt(baoGaoQuery.getYear()),baoGaoQuery.getGradeId(),subjectId,null);

            for(LiteracyVo bb:literacyVoList){
                Map<String,Object> map=new HashMap<>();
                    LiteracyStudent literacyStudent= literacyStudentDao.findByStudentIdAndLiteracyId(id,bb.getId());
                    System.out.println(literacyStudent.getLiteracyName());
                    map.put("studentlist",literacyStudent);
                    int teamScore=0;
                    for(StudentVo cc:studentIdTeamList){
                        LiteracyStudent literacyTeam=  literacyStudentDao.findByStudentIdAndLiteracyId(cc.getId(),bb.getId());
                        if(literacyTeam.getFenshu()==null){
                            teamScore+=0;
                        }else {
                            teamScore += literacyTeam.getFenshu();
                        }
                    }
                    LiteracyStudent literacyStudent2=new LiteracyStudent();
                    literacyStudent2.setFenshu(teamScore/studentIdTeamList.size());
                    literacyStudent2.setLiteracyName(bb.getLiteracyName());
                    map.put("teamlist",literacyStudent2);
                    //---------------年级平均份------------------------
                    int gradeScore=0;
                    for(StudentVo dd:studentIdGradeList){
                        LiteracyStudent literacyTeam=  literacyStudentDao.findByStudentIdAndLiteracyId(dd.getId(),bb.getId());
                        if(literacyTeam.getFenshu()==null) {
                            gradeScore+=0;
                        }else {
                            gradeScore += literacyTeam.getFenshu();
                        }
                    }
                    LiteracyStudent literacyStudent22=new LiteracyStudent();
                    literacyStudent22.setFenshu(teamScore/studentIdGradeList.size());
                    literacyStudent22.setLiteracyName(bb.getLiteracyName());
                map.put("gradelist",literacyStudent22);
                mapList.add(map);
                }
        return mapList;
    }

    @Override
    public List<Map<String, Object>> findByAllsubjectId(UserInfo userInfo, BaoGaoQuery baoGaoQuery) {
        List<Map<String,Object>> mapList=new ArrayList<>();
        //科目
        List<Map<String,Object>> list=findExamSubject(baoGaoQuery.getGradeId());
        //获取该学生的id
        List<Student> studentIdList=getAllId(userInfo,baoGaoQuery);
        if(studentIdList==null){
            return null;
        }
        Integer id=studentIdList.get(0).getId();
        for(Map<String,Object> aa:list) {
            Map<String, Object> map = new HashMap<>();

            //根据科目查询指标
            List<LiteracyVo> literacyVoList= literacyDao.findByAll(null,Integer.parseInt(baoGaoQuery.getYear()),baoGaoQuery.getGradeId(),Integer.parseInt(aa.get("subjectId").toString()),null);
            //
            if(literacyVoList.size()<=0){
                map.put("subjectId", null);
                map.put("name", null);
            }else{
                map.put("subjectId", aa.get("subjectId"));
                map.put("name", aa.get("subjectName"));
            }
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public List<Map<String, Object>> findByAllstudentIdChengJi(UserInfo userInfo, BaoGaoQuery baoGaoQuery,Integer studentId) {
        List<Map<String,Object>> list=new ArrayList<>();
        String sql="select " +
                "(select VAR_POP(score)  from pj_student_score  where is_delete=0 and school_year='"+baoGaoQuery.getYear()+"' and term_code='"+baoGaoQuery.getXn()+"' and team_id="+baoGaoQuery.getTeamId()+" and grade_id="+baoGaoQuery.getGradeId()+" and exam_type=1 and student_id="+studentId+") as qizhong," +
                "(select VAR_POP(score)  from pj_student_score  where is_delete=0 and school_year='"+baoGaoQuery.getYear()+"' and term_code='"+baoGaoQuery.getXn()+"' and team_id="+baoGaoQuery.getTeamId()+" and grade_id="+baoGaoQuery.getGradeId()+" and exam_type=2 and student_id="+studentId+") as qimo";
        list=basicSQLService.find(sql);
        return list;
    }

    //查询出班级
    public List findExamSubject(Integer nj){
        return basicSQLService.find("select ps.id as subjectId, psg.subject_code as code,psg.subject_name as subjectName from pj_subject_grade  psg inner join pj_grade pg on pg.uni_grade_code=psg.grade_code" +
                " inner join pj_subject ps on ps.code=psg.subject_code where  pg.id="+nj+" and psg.is_deleted=0 group by ps.id");
    }
}
