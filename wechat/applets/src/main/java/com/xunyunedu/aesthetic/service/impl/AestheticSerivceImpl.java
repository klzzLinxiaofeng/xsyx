package com.xunyunedu.aesthetic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.aesthetic.dao.AestheticDao;
import com.xunyunedu.aesthetic.pojo.AestheticPojo;
import com.xunyunedu.aesthetic.pojo.EmployeeList;
import com.xunyunedu.aesthetic.service.AestheticService;
import com.xunyunedu.character.pojo.Picture;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.util.ftp.FtpUtils;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AestheticSerivceImpl implements AestheticService {
    private static final Logger log = LoggerFactory.getLogger(AestheticSerivceImpl.class);

    @Autowired
    private AestheticDao aestheticDao;
    @Autowired
    private UploaderDao uploaderDao;
    @Autowired
    BasicSQLService basicSQLService;

    @Autowired
    private FtpUtils ftpUtils;
    @Override
    public AestheticPojo findByAlls(Integer studentId, Integer schoolId) {
        List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        AestheticPojo aestheticPojo =new AestheticPojo();
        AestheticPojo aestheticPojo2=aestheticDao.findByAesthetic(studentId,schoolId,termInfo.get(0).get("school_year").toString(),termInfo.get(0).get("school_term_code").toString());
        if(aestheticPojo2!=null){
            aestheticPojo.setComments(aestheticPojo2.getComments());
            aestheticPojo.setReview(aestheticPojo2.getReview());
            aestheticPojo.setReviewSore(aestheticPojo2.getReviewSore());
            aestheticPojo.setFineArtId(aestheticPojo2.getFineArtId());
            aestheticPojo.setStudentId(aestheticPojo2.getStudentId());
            aestheticPojo.setJiangzhuanId(aestheticPojo2.getJiangzhuanId());
            aestheticPojo.setGameWorksId(aestheticPojo2.getGameWorksId());
            aestheticPojo.setStudentName(aestheticPojo2.getStudentName());
                if (aestheticPojo2.getFineArtId() != null) {
                    String [] str=aestheticPojo2.getFineArtId().split(",");
                    List<Picture> pictureList=new ArrayList<>();
                    if(str.length>0) {
                        System.out.println("hahah1");
                        for (int a = 0; a < str.length; a++) {
                            Picture picture = new Picture();
                            // ??????PctreId?????????????????????url
                            EntityFile file = uploaderDao.findFileByUUID(str[a]);
                            if (file != null) {
                                picture.setPictureId(str[a]);
                                picture.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                            }
                            pictureList.add(picture);
                        }
                        aestheticPojo.setPictureList(pictureList);
                    }
            }
            if(aestheticPojo2.getJiangzhuanId()!=null){
                String [] str=aestheticPojo2.getJiangzhuanId().split(",");
                List<Picture> pictureList=new ArrayList<>();
                if(str.length>0) {
                    for (int a = 0; a < str.length; a++) {
                        Picture picture = new Picture();
                        // ??????uuid???????????????url
                        EntityFile file = uploaderDao.findFileByUUID(str[a]);
                        if (file != null) {
                            picture.setPictureId(str[a]);
                            picture.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                        }
                        pictureList.add(picture);
                    }
                    aestheticPojo.setPictureListTwo(pictureList);
                }
            }
            if(aestheticPojo2.getGameWorksId()!=null) {
                String[] str = aestheticPojo2.getGameWorksId().split(",");
                if(str.length>0) {
                    List<Picture> pictureList = new ArrayList<>();
                    for (int a = 0; a < str.length; a++) {
                        Picture picture = new Picture();
                        // ??????uuid???????????????url
                        EntityFile file = uploaderDao.findFileByUUID(str[a]);
                        if (file != null) {
                            picture.setPictureId(str[a]);
                            picture.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                        }
                        pictureList.add(picture);
                    }
                    aestheticPojo.setPictureListStree(pictureList);
                }
            }
        }else {
            Student student = aestheticDao.findByStudentId(studentId, schoolId);
            aestheticPojo.setStudentName(student.getName());
        }
        return aestheticPojo;
    }

    @Override
    public String findByAllStudent(Integer studentId, Integer schoolId,Integer type) {
        //type 0,?????? ???1 ??????
        if(type==0) {
            List<Student> lisst = aestheticDao.findByAllStudent(studentId, schoolId);
            log.info("??????????????????" + lisst.size());
            int a = 0;
            int b = 0;
            String str = null;
            for (Student students : lisst) {
                //???????????????????????????????????????
                EmployeeList employeeList=new EmployeeList();
                employeeList.setEmp_code(students.getEmpCode());
                employeeList.setEmp_pycode(String.valueOf(students.getUserId()));
                Object object = JSONObject.toJSON(employeeList);
                JSONObject param = new JSONObject();
                param.put("sign_name", "kksss");
                //param.put("tran_code","emp_add");
                param.put("tran_code", "temporary_code");
                param.put("employeeList", object);
                //canteenThreadPoolExecutor.submit
                HttpRequestResult httpRequestResult = null;
                //????????????
                HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/UserEmployeeUpdateTemp")
                        .addHeader("content-type", "application/json")
                        .httpEntityType(HttpEntityType.ENTITY_STRING);
                config.json(param.toString());
                try {
                    httpRequestResult = HttpClientUtils.post(config);
                    //????????????????????????????????????
                    if (httpRequestResult == null) {
                        log.info("?????????????????????");
                    }
                    if (200 == httpRequestResult.getCode()) {
                        String responseText = httpRequestResult.getResponseText();
                        if (("").equals(responseText) || responseText == null) {
                            log.info("????????????????????????");
                        }
                        JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                        if (jsonObject2.get("data").equals("success")) {
                            aestheticDao.updateStudent(students.getId(), students.getSchoolId());
                            a++;
                        } else {
                            b++;
                            str += "????????????,??????" + students.getName() + "?????????" + jsonObject2.get("error");
                        }
                    } else {
                        log.error("????????????--???????????????????????????????????????, ???????????? {}", httpRequestResult);
                    }
                    log.info("????????????--??????????????????????????????{}", " ????????????: {}----" + httpRequestResult);
                } catch (IOException e) {
                    log.info("????????????--??????????????????????????????{}??? ???????????????????????????{}" + e);
                }
            }
            return "??????????????????"+a+"??????????????????"+b+"?????????,????????????"+str;
        }else{
            //??????studentid?????????id
            List<TeacherPojo> lisst = aestheticDao.findByAllTeacher(studentId, schoolId);
            log.info("??????????????????" + lisst.size());
            int a = 0;
            int b = 0;
            String str = null;
            for (TeacherPojo teacher : lisst) {
                //???????????????????????????????????????
                EmployeeList employeeList=new EmployeeList();
                employeeList.setEmp_code(teacher.getEmpCode());
                employeeList.setEmp_pycode(String.valueOf(teacher.getUserId()));
                Object object = JSONObject.toJSON(employeeList);
                JSONObject param = new JSONObject();
                param.put("sign_name", "kksss");
                //param.put("tran_code","emp_add");
                param.put("tran_code", "temporary_code");
                param.put("employeeList", object);
                //canteenThreadPoolExecutor.submit
                HttpRequestResult httpRequestResult = null;
                //????????????
                HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/UserEmployeeUpdateTemp")
                        .addHeader("content-type", "application/json")
                        .httpEntityType(HttpEntityType.ENTITY_STRING);
                config.json(param.toString());
                try {
                    httpRequestResult = HttpClientUtils.post(config);
                    //????????????????????????????????????
                    if (httpRequestResult == null) {
                        log.info("?????????????????????");
                        b++;
                    }else {
                        if (200 == httpRequestResult.getCode()) {
                            String responseText = httpRequestResult.getResponseText();
                            if (("").equals(responseText) || responseText == null) {
                                log.info("????????????????????????");
                            }
                            JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                            if (jsonObject2.get("data").equals("success")) {
                                aestheticDao.updateTeacher(teacher.getId(), teacher.getSchoolId());
                                a++;
                            } else {
                                b++;
                                str += "????????????,??????" + teacher.getName() + "?????????" + jsonObject2.get("error")+",";
                            }
                        } else {
                            b++;
                            log.error("????????????--???????????????????????????????????????, ???????????? {}", httpRequestResult);
                        }
                        log.info("????????????--??????????????????????????????{}", " ????????????: {}----" + httpRequestResult);
                    }
                    } catch (IOException e) {
                    log.info("????????????--??????????????????????????????{}??? ???????????????????????????{}" + e);
                }
            }
            return "??????????????????"+a+"??????????????????"+b+"?????????,????????????"+str;
        }
    }
}
