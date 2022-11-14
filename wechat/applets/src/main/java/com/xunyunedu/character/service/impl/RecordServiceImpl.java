package com.xunyunedu.character.service.impl;

import com.xunyunedu.character.dao.EvaluationDao;
import com.xunyunedu.character.dao.RecordsDao;
import com.xunyunedu.character.pojo.Evaluation;
import com.xunyunedu.character.pojo.Picture;
import com.xunyunedu.character.pojo.Records;
import com.xunyunedu.character.pojo.StudentContion;
import com.xunyunedu.character.service.RecordService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RecordServiceImpl implements   RecordService {
    @Autowired
    private RecordsDao recordsDao;
    @Autowired
    private EvaluationDao evaluationDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;
    @Override
    public Boolean create(Records records) {
        Date date=new Date();
        records.setCreateTime(date);
       int a= recordsDao.create(records);
        recordsDao.createee(records);
        System.out.println("records"+records.getId());
        return a>0;
    }

    @Override
    public List<Map<String, Object>> pinGePaiHang(Integer schoolId) {
        List<StudentContion> list=evaluationDao.evaluationDaofindByquanxiao(schoolId,null,null);
        List<Evaluation> evaluationList=evaluationDao.findByAll();
        List<Map<String, Object>> asasdawx=new ArrayList<>();
            for(Evaluation aa: evaluationList){
                Map<String,Object> aaaaa=new HashMap<>();
                List<Map<String, Object>> lists=new ArrayList<>();
                for(StudentContion bb:list) {
                    Map<String, Object> map = new HashMap<>();
                    if (bb.getUuid() != null) {
                        // 根据uuid查询封面的url
                        System.out.println("uuid"+bb.getUuid());
                        EntityFile file = uploaderDao.findFileByUUID(bb.getUuid());
                        System.out.println("file"+file);
                        if (file != null) {
                            System.out.println("fafa"+ ftpUtils.relativePath2HttpUrl(file));
                            map.put("uuidUrl", ftpUtils.relativePath2HttpUrl(file));
                        }else{
                            map.put("uuidUrl",null);
                        }
                    }else{
                        map.put("uuidUrl",null);
                    }

                        StudentContion asd = evaluationDao.findByStu(aa.getInitScore(), schoolId, aa.getId(), bb.getStidentId(), null);
                        if (asd == null) {
                            map.put("initScore", aa.getInitScore());
                            map.put("studentName", bb.getStudnetName());
                            map.put("studentId", bb.getStidentId());
                        } else {
                            map.put("initScore", asd.getScore());
                            map.put("studentName", asd.getStudnetName());
                            map.put("studentId", asd.getStidentId());
                        }
                        lists.add(map);
                    }
                System.out.println("daxiao"+lists.size());
                Collections.sort(lists, new Comparator<Map<String, Object>>() {

                    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                        Integer name1 = (Integer) o1.get("initScore");//name1是从你list里面拿出来的一个
                        Integer name2 = (Integer)o2.get("initScore") ; //name1是从你list里面拿出来的第二个name
                        return name2.compareTo(name1);
                    }
                });

                List<Map<String, Object>> listss=new ArrayList<>();
                listss.add(lists.get(0));
                listss.add(lists.get(1));
                listss.add(lists.get(2));
                aaaaa.put("name",aa.getName());
                aaaaa.put("id",aa.getId());
                aaaaa.put("records",listss);
                asasdawx.add(aaaaa);
            }


        System.out.println("daxiao"+asasdawx.size());
            return asasdawx;
    }

    @Override
    public List<Map<String, Object>> pinGePaiHangBanJi(Integer schoolId, Integer teamId, Integer evaluationId) {
        List<StudentContion> list=evaluationDao.evaluationDaofindByquanxiao(schoolId,null,teamId);
        Evaluation evaluationList=evaluationDao.findById(evaluationId);
        List<Map<String, Object>> lists=new ArrayList<>();
            for(StudentContion bb:list){
                Map<String,Object> map=new HashMap<>();
                if (bb.getUuid() != null) {
                    // 根据uuid查询封面的url
                    EntityFile file = uploaderDao.findFileByUUID(bb.getUuid());
                    if (file != null) {
                        map.put("uuidUrl", ftpUtils.relativePath2HttpUrl(file)) ;
                    }else{
                        map.put("uuidUrl",null);
                    }
                }else{
                    map.put("uuidUrl",null);
                }
                StudentContion asd=evaluationDao.findByStu(evaluationList.getInitScore(),schoolId,evaluationId,bb.getStidentId(),null);
                if(asd==null){
                    map.put("initScore",evaluationList.getInitScore());
                    map.put("studentName",bb.getStudnetName());
                    map.put("studentId",bb.getStidentId());
                    map.put("id",evaluationId);

                }else{
                    map.put("initScore",asd.getScore());
                    map.put("studentName",asd.getStudnetName());
                    map.put("studentId",asd.getStidentId());
                    map.put("id",evaluationId);
                }
                lists.add(map);
            }
        System.out.println("daxiao"+lists.size());
        //按时间升序排列
        Collections.sort(lists, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer date1 = new Integer((Integer) o1.get("initScore")) ;
                Integer date2 = new Integer((Integer) o2.get("initScore")) ;
                return date2.compareTo(date1);
            }
        });
        System.out.println("daxiao"+lists.size());

        List<Map<String, Object>> listss=new ArrayList<>();
        if(lists.size()<10){
            for(int a=0;a<lists.size();a++){
                listss.add(lists.get(a));
            }
        }else{
            listss.add(lists.get(0));
            listss.add(lists.get(1));
            listss.add(lists.get(2));
            listss.add(lists.get(3));
            listss.add(lists.get(4));
            listss.add(lists.get(5));
            listss.add(lists.get(6));
            listss.add(lists.get(7));
            listss.add(lists.get(8));
            listss.add(lists.get(9));
        }

        return listss;
    }


    /*
    * 年级排行榜
    */
    @Override
    public List<Map<String, Object>> pinGePaiHangNianJi(Integer schoolId, Integer gradeId, Integer evaluId) {
        List<StudentContion> list=evaluationDao.evaluationDaofindByquanxiao(schoolId,gradeId,null);
        Evaluation evaluationList=evaluationDao.findById(evaluId);
        List<Map<String, Object>> lists=new ArrayList<>();
            for(StudentContion bb:list){
                Map<String,Object> map=new HashMap<>();
                if (bb.getUuid() != null) {
                    // 根据uuid查询封面的url
                    EntityFile file = uploaderDao.findFileByUUID(bb.getUuid());
                    if (file != null) {
                        map.put("uuidUrl",ftpUtils.relativePath2HttpUrl(file));
                    }else{
                        map.put("uuidUrl",null);
                    }
                }else{
                    map.put("uuidUrl",null);
                }
                StudentContion asd=evaluationDao.findByStu(evaluationList.getInitScore(),schoolId,evaluId,bb.getStidentId(),null);
                if(asd==null){
                    StudentContion studentContion =new StudentContion();
                    map.put("gradeId",bb.getGradeId());
                    map.put("gradeName",bb.getGradeName());
                    map.put("initScore",evaluationList.getInitScore());
                    map.put("studentName",bb.getStudnetName());
                    map.put("studentId",bb.getStidentId());
                    map.put("id",evaluId);
                }else{
                    map.put("gradeId",bb.getGradeId());
                    map.put("gradeName",bb.getGradeName());
                    map.put("initScore",asd.getScore());
                    map.put("studentName",asd.getStudnetName());
                    map.put("studentId",asd.getStidentId());
                    map.put("id",evaluId);

                }
                lists.add(map);
            }
        //按时间升序排列
        Collections.sort(lists, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer date1 = new Integer((Integer) o1.get("initScore")) ;
                Integer date2 = new Integer((Integer) o2.get("initScore")) ;
                return date2.compareTo(date1);
            }
        });
        System.out.println("daxiao"+lists.size());
        List<Map<String, Object>> listss=new ArrayList<>();
        if(lists.size()<10){
            for(int a=0;a<lists.size();a++){
                listss.add(lists.get(a));
            }
        }else{
            listss.add(lists.get(0));
            listss.add(lists.get(1));
            listss.add(lists.get(2));
            listss.add(lists.get(3));
            listss.add(lists.get(4));
            listss.add(lists.get(5));
            listss.add(lists.get(6));
            listss.add(lists.get(7));
            listss.add(lists.get(8));
            listss.add(lists.get(9));
        }
        return listss;
    }

    @Override
    public List<Records> pinGeStudentJiLu(Integer schoolId, Integer studentId,Integer evaluationId) {
        if(evaluationId!=null){
            List<Records> records=evaluationDao.findByJiLu(schoolId,studentId,evaluationId);
            for(int a=0;a<records.size();a++){
                Records record=records.get(a);
                if(record.getTeacherId()==null){
                    record.setTeaName("家长点评");
                }
                if(record.getPictureId()!=null){
                    String [] str =record.getPictureId().split(",");
                    List<Picture> pictures=new ArrayList<>();
                    if(str.length>0){
                        for(int i=0;i<str.length;i++){
                            if(str[i]!=null){
                                // 根据PctreId查询优秀作品的url
                                EntityFile file = uploaderDao.findFileByUUID(str[i]);
                                Picture picture =new Picture();
                                if (file != null) {
                                    picture.setPictureId(str[i]);
                                    picture.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                                }
                                pictures.add(picture);
                            }
                        }
                        record.setPictureList(pictures);
                    }
                }

            }
            return records;
        }
        List<Records> records=evaluationDao.findByJiLu(schoolId,studentId,null);
        for(int a=0;a<records.size();a++) {
            Records record = records.get(a);
            if (record.getPictureId() != null) {
                String[] str = record.getPictureId().split(",");
                List<Picture> pictures = new ArrayList<>();
                if (str.length > 0) {
                    for (int i = 0; i < str.length; i++) {
                        if (str[i] != null) {
                            // 根据PctreId查询优秀作品的url
                            EntityFile file = uploaderDao.findFileByUUID(str[i]);
                            Picture picture = new Picture();
                            if (file != null) {
                                picture.setPictureId(str[i]);
                                picture.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                            }
                            pictures.add(picture);
                        }
                    }
                    record.setPictureList(pictures);
                }
            }
        }
        return records;
    }

    @Override
    public List<Map<String, Object>> findByTupian(Integer schoolId, Integer studentId,String schoolYear) {
        List<Evaluation> evaluationList=evaluationDao.findByAll();
        Integer gradeId=evaluationDao.findByGrsa(schoolId,studentId,schoolYear);
        Integer teamNumer =evaluationDao.findByAllStudent(schoolId,studentId);
        Integer gradeNumer =evaluationDao.findByAllStudent2(schoolId,studentId,schoolYear);
        List<Map<String, Object>> list =new ArrayList<>();
        for(Evaluation aa:evaluationList){
            Map map=new HashMap();
            Integer gradeZvg=evaluationDao.findByasd(schoolId,gradeId,aa.getId());
            if(gradeZvg==null){
                gradeZvg=0;
            }
            System.out.println(gradeZvg);
            System.out.println(gradeId);
            System.out.println(teamNumer);
            System.out.println(gradeNumer);
            Integer num=gradeNumer*aa.getInitScore()+gradeZvg;
            Integer teamZvg=evaluationDao.findByeeee(schoolId,studentId,aa.getId());
            if(teamZvg==null){
                teamZvg=0;
            }
            Integer nnaa=teamNumer*aa.getInitScore()+teamZvg;
            Integer studentAcc=evaluationDao.getByStudentIda(schoolId,studentId,aa.getId());
            if(studentAcc==null){
                studentAcc=0;
            }
            map.put("id",aa.getId());
            map.put("name",aa.getName());
            map.put("gradeAvg",num/gradeNumer);
            map.put("teamAvg",nnaa/teamNumer);
            map.put("studentAvg",studentAcc+aa.getInitScore());
            list.add(map);
        }
        return list;
    }

    @Override
    public StudentPojo findBySaoMa(Integer studentId, Integer schoolId) {
        return evaluationDao.findBySaoMa(schoolId,studentId);
    }

}
