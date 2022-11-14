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
                            // 根据PctreId查询优秀作品的url
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
                        // 根据uuid查询奖状的url
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
                        // 根据uuid查寻比赛的url
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
        //type 0,学生 。1 老师
        if(type==0) {
            List<Student> lisst = aestheticDao.findByAllStudent(studentId, schoolId);
            log.info("学生数据多少" + lisst.size());
            int a = 0;
            int b = 0;
            String str = null;
            for (Student students : lisst) {
                //组装数据发送到食堂进行修改
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
                //修改路径
                HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/UserEmployeeUpdateTemp")
                        .addHeader("content-type", "application/json")
                        .httpEntityType(HttpEntityType.ENTITY_STRING);
                config.json(param.toString());
                try {
                    httpRequestResult = HttpClientUtils.post(config);
                    //判断食堂修改接口返回信息
                    if (httpRequestResult == null) {
                        log.info("食堂无返回信息");
                    }
                    if (200 == httpRequestResult.getCode()) {
                        String responseText = httpRequestResult.getResponseText();
                        if (("").equals(responseText) || responseText == null) {
                            log.info("食堂返回信息为空");
                        }
                        JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                        if (jsonObject2.get("data").equals("success")) {
                            aestheticDao.updateStudent(students.getId(), students.getSchoolId());
                            a++;
                        } else {
                            b++;
                            str += "修改失败,学生" + students.getName() + "返回：" + jsonObject2.get("error");
                        }
                    } else {
                        log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
                    }
                    log.info("食堂接口--食堂添加接口添加状态{}", " 返回信息: {}----" + httpRequestResult);
                } catch (IOException e) {
                    log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}" + e);
                }
            }
            return "修改学生成功"+a+"条数据，失败"+b+"条数据,错误信息"+str;
        }else{
            //此处studentid传老师id
            List<TeacherPojo> lisst = aestheticDao.findByAllTeacher(studentId, schoolId);
            log.info("老师数据多少" + lisst.size());
            int a = 0;
            int b = 0;
            String str = null;
            for (TeacherPojo teacher : lisst) {
                //组装数据发送到食堂进行修改
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
                //修改路径
                HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/UserEmployeeUpdateTemp")
                        .addHeader("content-type", "application/json")
                        .httpEntityType(HttpEntityType.ENTITY_STRING);
                config.json(param.toString());
                try {
                    httpRequestResult = HttpClientUtils.post(config);
                    //判断食堂修改接口返回信息
                    if (httpRequestResult == null) {
                        log.info("食堂无返回信息");
                        b++;
                    }else {
                        if (200 == httpRequestResult.getCode()) {
                            String responseText = httpRequestResult.getResponseText();
                            if (("").equals(responseText) || responseText == null) {
                                log.info("食堂返回信息为空");
                            }
                            JSONObject jsonObject2 = JSONObject.parseObject(responseText);
                            if (jsonObject2.get("data").equals("success")) {
                                aestheticDao.updateTeacher(teacher.getId(), teacher.getSchoolId());
                                a++;
                            } else {
                                b++;
                                str += "修改失败,学生" + teacher.getName() + "返回：" + jsonObject2.get("error")+",";
                            }
                        } else {
                            b++;
                            log.error("食堂接口--添加用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
                        }
                        log.info("食堂接口--食堂添加接口添加状态{}", " 返回信息: {}----" + httpRequestResult);
                    }
                    } catch (IOException e) {
                    log.info("食堂接口--食堂添加接口添加状态{}， 请求远程接口失败：{}" + e);
                }
            }
            return "修改学生成功"+a+"条数据，失败"+b+"条数据,错误信息"+str;
        }
    }
}
