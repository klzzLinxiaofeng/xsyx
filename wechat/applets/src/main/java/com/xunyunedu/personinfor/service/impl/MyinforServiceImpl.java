package com.xunyunedu.personinfor.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.personinfor.dao.MyInforDao;
import com.xunyunedu.personinfor.pojo.PublicClassPojo;
import com.xunyunedu.personinfor.pojo.PublicTimePojo;
import com.xunyunedu.personinfor.pojo.QuestionnairePojo;
import com.xunyunedu.personinfor.pojo.StudentPojo;
import com.xunyunedu.personinfor.service.MyinforService;
import com.xunyunedu.student.dao.ParentDao;
import com.xunyunedu.student.pojo.ParentPojo;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.PublicTeacherPojo;
import com.xunyunedu.util.DateUtil;
import com.xunyunedu.util.ftp.FtpUtils;
import com.xunyunedu.util.redis.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author: yhc
 * @Date: 2020/9/19 14:07
 * @Description:
 */
@Service("myinforService")
public class MyinforServiceImpl implements MyinforService {
    Logger logger = LoggerFactory.getLogger(MyinforServiceImpl.class);
    Jedis jedis = null;

    private static final String TOKEN_PRE = "wxtoken:";

    @Autowired
    private MyInforDao myInforDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    private TeacherHomeDao teacherHomeDao;

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 返回学生和学生对应的选课信息--我的界面
     *
     * @param id 登录的家长账号name
     * @return Map<学生信息, List < 学生对应的课程信息>>
     */
    @Override
    public Map<String, List<StudentPojo>> getInformation(String id) {
        // Map<学生信息, List<学生对应的课程信息>>
        List<StudentPojo> list = new ArrayList<>();
        List<Map<String,Object>> mapList=basicSQLService.find("select * from pj_school_term_current  where 1=1  and school_id= 215");
        // 查询家长下的孩子信息
        List<StudentPojo> stuList = myInforDao.getUserInfoByName(id,mapList.get(0).get("school_year").toString());
        Map<String, List<StudentPojo>> map = new HashMap<>(1);
        // 根据学生信息查询选课信息
        if (stuList != null && stuList.size() > 0) {
            for (StudentPojo studentPojo : stuList) {
                if (studentPojo.getSchoolId() != null && studentPojo.getId() != null) {
                    String coverUuid = studentPojo.getPhotoUuid();
                    if (coverUuid != null && !("").equals(coverUuid)) {
                        // 根据uuid查询封面的url
                        EntityFile file = uploaderDao.findFileByUUID(coverUuid);
                        if (file != null) {
                            studentPojo.setPhotoUrl(ftpUtils.relativePath2HttpUrl(file));
                        }
                    }
                    // 学生id，学校id
//                    List<PublicClassPojo> classInfoList = myInforDao.getPublicClass(studentPojo.getSchoolId(), studentPojo.getId());
                    Integer grade = lowNumber(studentPojo.getTeamName());
                    studentPojo.setGrade(grade);

                    studentPojo.setYear(mapList.get(0).get("school_year").toString());
//                    studentPojo.setPublicClassList(classInfoList);
                    list.add(studentPojo);
                }
            }
        }
        map.put("student", list);
        return map;
    }

    @Override
    public Boolean updateXuanke(Integer cid, Integer stuId, Integer schooldId) {
        // 根据课程id修改已报名人数
       Integer num=  myInforDao.updateXuanke(cid, schooldId);

        Integer flg = myInforDao.removeClassStuRelation(cid,stuId,schooldId);
        if(flg>0){
            return true;
        }
        return false;

    }


    /**
     * 获取对应年级下的课程
     * 并且对课程是否选满、是否选择（排序）、是否冲突判断
     *
     * @param stuId    学生账号id
     * @param grade    年级 只有小学 1-6
     * @param schoolId 学校id
     * @return
     */
    @Override
    public List<PublicClassPojo> getChildren(Integer grade, Integer schoolId, Integer stuId,Integer coursetype) {
        // 根据学生的年级查询课程
        List<PublicClassPojo> classInfoList = myInforDao.getPublicClassByGrade(grade, schoolId,coursetype);
        List<PublicClassPojo> classInfoList2=new ArrayList<PublicClassPojo>();
        Date date=new Date();
        //获取学生所有已选课程的列表
      //  List<PublicClassPojo> YiXuanclassInfoList = myInforDao.findByYiXuan(stuId, schoolId);
        if (CollUtil.isNotEmpty(classInfoList)) {
            // 获取当前学生选择的所有课程对应的课程时间信息，再判断课程对应的时间和这个学生课程的时间是否有相同的
            Map<Integer, Map<String, Integer>> listTimeId = myInforDao.findTimeIdByStuIdAndSchoolId(stuId, schoolId);
            boolean flg = false;
            for (int i = 0; i < classInfoList.size(); i++) {
                PublicClassPojo publicClassPojo = classInfoList.get(i);
                Integer cid = publicClassPojo.getCid();
                List<PublicTeacherPojo> publicTeacherList = teacherHomeDao.findByPublicClassIdAndSchoolId(cid, schoolId);
                flg = judgePublic(publicClassPojo, schoolId, stuId, listTimeId, publicTeacherList, flg, 2);
            }
            if (flg) {
                // 进行排序，对已经选择的课程优先展示
                Collections.sort(classInfoList, new Comparator<PublicClassPojo>() {
                    @Override
                    public int compare(PublicClassPojo pp, PublicClassPojo pp2) {
                        Integer chooseState = pp.getChooseState();
                        Integer chooseState2 = pp2.getChooseState();
                        if (chooseState.equals(chooseState2)) {
                            return 0;
                        } else {
                            return chooseState > chooseState2 ? -1 : 1;
                        }
                    }
                });
            }
        }
        for(PublicClassPojo aa:classInfoList){
            int a=0;
            if(classInfoList2.size()>0) {
                for (int i = 0; i < classInfoList2.size(); i++) {
                    if (aa.getCid() == classInfoList2.get(i).getCid()) {
                        a++;
                    }
                }
                if(a==0) {
                    if (aa.getExpiryDate().getTime() > date.getTime() || aa.getChooseState() == 1) {
                                classInfoList2.add(aa);
                    }
                }
            }else{
                if(aa.getExpiryDate().getTime()>date.getTime() || aa.getChooseState()==1){
                    classInfoList2.add(aa);
                }
            }

        }
      return  classInfoList2;
    }

    /**
     * 选课功能
     *
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     * @return 1:选课成功 0 选课失败 2：人数已满
     * 修改，避免synchronized执行完成事务还没有提交
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addChooseClass(Integer cid, Integer stuId, Integer schooldId, Integer isJiao) {
        int flg = 0;
//        // 判断当前报名截止时间
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        Date endTime = null;
//        try {
//            endTime = ft.parse("2019-06-09");
//        } catch (ParseException e) {
//            logger.error("日期转换失败！{}", e.getMessage());
//        }
//        Date nowTime = new Date();
//        boolean effectiveDate = DateUtil.isEffectiveDate(nowTime, endTime);
//        if (effectiveDate) {
//            System.out.println("当前时间在范围内");
//        } else {
//            System.out.println("当前时间在不在范围内");
//        }

        // 判断当前课程的已报名人数
        // 根据不同的课程id 提高并发
//        synchronized (LockUtil.getLock("" + cid)) {
        // 查询当前课程是否删除和剩余的人数
        PublicClassPojo count = myInforDao.getEnrollNumber(cid, schooldId);
        if (count != null && count.getIsDelete() != null && count.getIsDelete() == 0) {
            if (count.getMaxMember() != null && count.getEnrollNumber() != null) {
                // 人数上限  已报名人数
                if (count.getMaxMember() - count.getEnrollNumber() > 0) {
                    /**
                     * 判断学生的其他课程是否和这个课程时间冲突
                     */
                    // 获取当前学生选择的所有课程对应的课程时间信息，再判断课程对应的时间和这个学生课程的时间是否有相同的
                    Map<Integer, Map<String, Integer>> mapIds = myInforDao.findTimeIdByStuIdAndSchoolId(stuId, schooldId);
                    // 获取当前课程对应的时间id
                    List<PublicTimePojo> publicTimePojoList = myInforDao.findTimeInfoBySchoolIdAndClassId(schooldId, cid);
                    if (CollUtil.isNotEmpty(mapIds) && CollUtil.isNotEmpty(publicTimePojoList)) {
                        for (PublicTimePojo publicTimePojo : publicTimePojoList) {
                            Integer id = publicTimePojo.getId();
                            boolean contains = mapIds.containsKey(id);
                            // 根据课程对应的时间id和学生获取课程信息
                            if (contains) {
                                // 冲突课程删除原来的,课程获取的是之前的课程信息
                                Map<String, Integer> map = mapIds.get(id);
                                Integer class_id = map.get("public_class_id");
                                cancelPublicClass(class_id, stuId, schooldId);
                                break;
                            }
                        }
                    }
                    // 根据课程id修改已报名人数
                    myInforDao.modifyByEnrollNumber(cid, schooldId);
                    // 选课成功 保存信息
                    myInforDao.addChoose(cid, stuId, schooldId,isJiao);
                    flg = 1;
                } else {
                    flg = 2;
                }
            }
        }
//        }
        return flg;
    }
    /*
    * 收费课程
    */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addChooseClassShouFei(Integer cid, Integer stuId, Integer schooldId) {
        int flg = 0;
        PublicClassPojo count = myInforDao.getEnrollNumber(cid, schooldId);
        if (count != null && count.getIsDelete() != null && count.getIsDelete() == 0) {
            if (count.getMaxMember() != null && count.getEnrollNumber() != null) {
                // 人数上限  已报名人数
                if (count.getMaxMember() - count.getEnrollNumber() > 0) {
                    /**
                     * 判断学生的其他课程是否和这个课程时间冲突
                     */
                    // 获取当前学生选择的所有课程对应的课程时间信息，再判断课程对应的时间和这个学生课程的时间是否有相同的
                    Map<Integer, Map<String, Integer>> mapIds = myInforDao.findTimeIdByStuIdAndSchoolId(stuId, schooldId);
                    // 获取当前课程对应的时间id
                    List<PublicTimePojo> publicTimePojoList = myInforDao.findTimeInfoBySchoolIdAndClassId(schooldId, cid);
                    if (CollUtil.isNotEmpty(mapIds) && CollUtil.isNotEmpty(publicTimePojoList)) {
                        for (PublicTimePojo publicTimePojo : publicTimePojoList) {
                            Integer id = publicTimePojo.getId();
                            boolean contains = mapIds.containsKey(id);
                            // 根据课程对应的时间id和学生获取课程信息
                            if (contains) {
                                // 冲突课程删除原来的,课程获取的是之前的课程信息
                                Map<String, Integer> map = mapIds.get(id);
                                Integer class_id = map.get("public_class_id");
                                cancelPublicClass(class_id, stuId, schooldId);
                                break;
                            }
                        }
                    }
                    /*// 根据课程id修改已报名人数
                    myInforDao.modifyByEnrollNumber(cid, schooldId);
                    // 选课成功 保存信息
                    myInforDao.addChoose(cid, stuId, schooldId,isJiao);*/
                    flg = 1;
                } else {
                    flg = 2;
                }
            }
        }
//        }
        return flg;
    }
    /*
    * 支付成功修改用户绑定
    */

    @Override
    public Integer paySuccess(Integer cid, Integer stuId, Integer schooldId, Integer isJiao) {
                    // 根据课程id修改已报名人数
            int num2=myInforDao.modifyByEnrollNumber(cid, schooldId);
                    // 选课成功 保存信息
            int num=myInforDao.addChoose(cid, stuId, schooldId,isJiao);
            if(num>0 && num2>0){
                return  1;
            }
        return  1;
    }

    /**
     * 取消选课功能
     *
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     * @return 1:选课成功 0 选课失败 2：人数已满
     * 修改事务的隔离级别，避免synchronized执行完成事务还没有提交
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyChooseNum(Integer cid, Integer stuId, Integer schooldId) {
        cancelPublicClass(cid, stuId, schooldId);
    }

    /**
     * 取消选课
     *
     * @param cid
     * @param stuId
     * @param schooldId
     */
    public void cancelPublicClass(Integer cid, Integer stuId, Integer schooldId) {
        //        logger.debug("选课id：{} 学生：{}，学校id：{}", cid, stuId, schooldId);
        // 修改选择人数
        myInforDao.lessEnrollNumber(cid, schooldId);
        // 删除关联的关系
        myInforDao.removeClassStuRelation(cid, stuId, schooldId);
    }

    /**
     * 切换绑定
     * 清除redis中的token
     *
     * @param token
     */
    @Override
    public void removeLoginStaus(String token) {
        if (StrUtil.isNotEmpty(token)) {
            try {
                jedis = RedisPoolUtil.getConnect();
                jedis.del(TOKEN_PRE + token);
            } finally {
                if (jedis != null) {
                    RedisPoolUtil.closeConnect(jedis);
                }
            }
        }
    }

    /**
     * 首页展示所有选课
     * 暂时不需要
     *
     * @param schooldId
     * @return
     */
    @Override
    public Map<String, List<PublicClassPojo>> getAllCourse(Integer schooldId) {

        List<PublicClassPojo> classInfoList = myInforDao.getPublicClassByGrade(null, schooldId,null);
        Map<String, List<PublicClassPojo>> map = new HashMap<>(1);
        map.put("publicClass", classInfoList);
        return map;
    }

    /**
     * 根据学校id获取问卷
     *
     * @return
     */
    @Override
    public PageInfo<QuestionnairePojo> findBySchooldIdAndObject(QuestionnairePojo questionnairePojo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<QuestionnairePojo> questionnairePojoList = myInforDao.findBySchoolId(questionnairePojo);

        if (!questionnairePojoList.isEmpty()) {
            for (int i = 0; i < questionnairePojoList.size(); i++) {
                // 获取文件的UUID,根据uuid查询文件的详细地址
                if (!StrUtil.hasEmpty(questionnairePojoList.get(i).getUuid())) {
                    EntityFile entityFile = uploaderDao.findFileByUUID(questionnairePojoList.get(i).getUuid());
                    if (entityFile != null) {
                        questionnairePojoList.get(i).setHttpUrl(ftpUtils.relativePath2HttpUrl(entityFile));
                        questionnairePojoList.get(i).setFileName(entityFile.getFileName());
                    }
                }
            }
        }
        PageInfo<QuestionnairePojo> objectPageInfo = new PageInfo<>(questionnairePojoList);
        return objectPageInfo;
    }

    /**
     * 家长接送学生添加
     *
     * @param schooldId
     * @param stuId
     * @param location
     */
    @Override
    public void addLocation(Integer schooldId, List<Integer> stuId, Integer location) {
        String date = DateUtil.getNowDateYYYY_MM_DD();
        myInforDao.addLocation(schooldId, stuId, location, date, new Date());
    }

    /**
     * 家长接送学生修改
     *
     * @param schooldId
     * @param stuId
     * @param location
     */
    @Override
    public void updateLocation(Integer schooldId, List<Integer> stuId, Integer location) {
        String date = DateUtil.getNowDateYYYY_MM_DD();
        myInforDao.updateLocation(schooldId, stuId, location, date, new Date());
    }

    /**
     * 获取选课历史记录
     *
     * @param schooldId
     * @param stuId
     * @return
     */
    @Override
    public List<PublicClassPojo> getAllCourseHistory(Integer schooldId, Integer stuId, Integer type) {
        List<PublicClassPojo> historyList = myInforDao.getAllCourseHistory(schooldId, stuId,type);
        // 设置课程对应的教师姓名
        if (CollUtil.isNotEmpty(historyList)) {
            for (PublicClassPojo pojo : historyList) {
                Integer cid = pojo.getCid();
                // 获取教师信息
                List<PublicTeacherPojo> publicTeacherList = teacherHomeDao.findByPublicClassIdAndSchoolId(cid, schooldId);
                pojo.setTname(getPublicTeachers(publicTeacherList));
                setUrl(pojo);
            }
        }
        return historyList;
    }

    /**
     * 获取指定课程
     * @param cid
     * @param schooldId
     * @return
     */
    @Override
    public PublicClassPojo counservt(Integer cid, Integer schooldId) {
        PublicClassPojo pojo = myInforDao.courseDetails(cid, schooldId);
        return pojo;
    }


    @Override
    public PublicClassPojo courseDetails(Integer cid, Integer schooldId, Integer stuId, Integer type) {
        PublicClassPojo pojo = myInforDao.courseDetails(cid, schooldId);
        if (pojo != null) {
            // 根据课程查询当前课程的教师
            List<PublicTeacherPojo> publicTeacherList = teacherHomeDao.findByPublicClassIdAndSchoolId(cid, schooldId);
            if (CollUtil.isNotEmpty(publicTeacherList)) {
                // 获取教师封面图片路径
                for (PublicTeacherPojo teacherPojo : publicTeacherList) {
                    String coverUuid = teacherPojo.getCoverUuid();
                    if (coverUuid != null && !("").equals(coverUuid)) {
                        // 根据uuid查询封面的url
                        EntityFile file = uploaderDao.findFileByUUID(coverUuid);
                        if (file != null) {
                            teacherPojo.setCoverUrl(ftpUtils.relativePath2HttpUrl(file));
                        }
                    }
                }
                pojo.setListTeachers(publicTeacherList);
            }
            // 获取当前学生选择的所有课程对应的课程时间信息，再判断课程对应的时间和这个学生课程的时间是否有相同的
            Map<Integer, Map<String, Integer>> listTimeId = myInforDao.findTimeIdByStuIdAndSchoolId(stuId, schooldId);
            judgePublic(pojo, schooldId, stuId, listTimeId, publicTeacherList, false, type);
        }
        return pojo;
    }


    /**
     * 获取课程对应的学生id
     *
     * @param id
     * @param schoolId
     * @return
     */
    @Override
    public List<Integer> findPublicUserByIdAndSchoolId(Integer id, Integer schoolId) {
        return myInforDao.findPublicUserByIdAndSchoolId(id, schoolId);
    }

    @Override
    public Integer getxuankes(Integer stuId,Integer cid, Integer schooldId) {
        return myInforDao.getXuankes(stuId,cid,schooldId);
    }

    @Override
    public ParentPojo getParentInfo(String userName) {
        ParentPojo parentPojo = new ParentPojo();
        parentPojo.setUserName(userName);
        List<ParentPojo> parentPojoList = parentDao.read(parentPojo);
        if (CollUtil.isNotEmpty(parentPojoList)) {
            return parentPojoList.get(0);
        }
        return null;
    }

    /**
     * 修改家长信息
     * @param parentPojo
     */
    @Override
    public String modifyParentInfo(ParentPojo parentPojo) {
        //这里因为前端参数userId传的有误（传的是学生userId），但userName传的是家长的，所以这里根据userName先查询一下
        Integer userId=Integer.valueOf(basicSQLService.findUnique("select u.id from pj_parent p inner join yh_user u on u.id=p.user_id where u.user_name='"+parentPojo.getUserName()+"'").toString());
        parentPojo.setUserId(userId);
        Object rank=basicSQLService.findUnique("select rank from pj_parent_student where is_delete=0 and parent_user_id="+parentPojo.getUserId());
        if(rank==null || rank.toString().equals("0")){
            return "您不是主监护人无法绑定车牌";
        }

        if(StringUtils.isNotEmpty(parentPojo.getLicensePlate())) {
            parentPojo.setLicensePlate(parentPojo.getLicensePlate().trim());
            String[] carNums=parentPojo.getLicensePlate().split(",");
            for (String carNum : carNums) {
                long count =basicSQLService.findUniqueLong("select exists(select 1 from pj_parent where is_delete=0 and license_plate like '%"+carNum+"%' and user_id!='"+parentPojo.getUserId()+"') e");
                if (count > 0) {
                    return "车牌【"+carNum+"】已被其他人绑定";
                }
            }
        }
        parentDao.update(parentPojo);
        return null;
    }

    /**
     * 判断课程：时间冲突、是否选满、是否已经加入、获取课程封面、设置课程时间、设置教师
     *
     * @param publicClassPojo
     */
    public Boolean judgePublic(PublicClassPojo publicClassPojo, Integer schoolId, Integer stuId, Map<Integer, Map<String, Integer>> listTimeId, List<PublicTeacherPojo> publicTeacherList, boolean flg, Integer type) {
        // 用于正常查看详情，不用判断课程是否冲突
        boolean choseFlg = false;

        Integer cid = publicClassPojo.getCid();
        // 判断当前学生和当前课程是否已经选择
        publicClassPojo.setChooseState(0);
        Integer count = myInforDao.getSelectState(cid, stuId, schoolId);
        if (count != null && count > 0) {
            // 修改当前课程的选择状态
            flg = true;
            publicClassPojo.setChooseState(1);
            choseFlg = true;
        }
        // 历史记录和已经选择的课程，不需要判断冲突、选满
        if (type == 2) {
            // 判断课程选择人数是否已经满
            publicClassPojo.setIsFull(0);
            Integer enrollNumber = publicClassPojo.getEnrollNumber();
            Integer maxMember = publicClassPojo.getMaxMember();
            if (enrollNumber >= maxMember) {
                publicClassPojo.setIsFull(1);
            }
            publicClassPojo.setIsConflict(0);
        }
        List<PublicTimePojo> publicTimePojoList = myInforDao.findTimeInfoBySchoolIdAndClassId(schoolId, cid);
        if (CollUtil.isNotEmpty(publicTimePojoList)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < publicTimePojoList.size(); i++) {
                String name = publicTimePojoList.get(i).getClassTime();
                stringBuilder.append(name);
                if (i != publicTimePojoList.size() - 1) {
                    stringBuilder.append(",");
                }
                if (type != 1) {
                    // 判断当前课程的时间是否和当前学生的其他课程时间冲突
                    //     1.获取当前课程对应的时间
                    //     2.当前课程是否和学生的其他课程时间包含
                    if (CollUtil.isNotEmpty(listTimeId) && !choseFlg) {
                        Integer id = publicTimePojoList.get(i).getId();
                       // Integer ccid=listTimeId.get(id).get("public_class_id");
                       // System.out.println(ccid);
                      // PublicClassPojo publicClassPojo1= myInforDao.courseDetails(ccid,schoolId);
                       // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        //String date1=simpleDateFormat.format(publicClassPojo1.getBeginDate());
                       // String date2=simpleDateFormat.format(publicClassPojo.getExpiryDate());
                       // if(Integer.parseInt(date1.substring(0,4))==Integer.parseInt(date2.substring(0,4))){
                            boolean contains = listTimeId.containsKey(id);
                            if (contains) {
                                publicClassPojo.setIsConflict(1);
                            }
                        //}

                    }
                }
            }
            publicClassPojo.setClassTime(stringBuilder.toString());
        }
        setUrl(publicClassPojo);
        // 获取当前课程对应的教师名称
        publicClassPojo.setTname(getPublicTeachers(publicTeacherList));
        return flg;
    }

    /**
     * 设置当前课程对应的封面
     *
     * @param publicClassPojo
     */
    public void setUrl(PublicClassPojo publicClassPojo) {
        String coverUuid = publicClassPojo.getCoverUuid();
        if (coverUuid != null && !("").equals(coverUuid)) {
            // 根据uuid查询封面的url
            EntityFile file = uploaderDao.findFileByUUID(coverUuid);
            if (file != null) {
                publicClassPojo.setCoverUrl(ftpUtils.relativePath2HttpUrl(file));
            }
        }
    }


    /**
     * 获取当前课程对应的教师
     *
     * @param publicTeacherList
     * @return
     */
    public String getPublicTeachers(List<PublicTeacherPojo> publicTeacherList) {
        // 获取当前课程对应的教师名称
        if (CollUtil.isNotEmpty(publicTeacherList)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < publicTeacherList.size(); j++) {
                String name = publicTeacherList.get(j).getName();
                stringBuilder.append(name);
                if (j != publicTeacherList.size() - 1) {
                    stringBuilder.append("、");
                }
            }
            return stringBuilder.toString();
        }
        return "";
    }


    private Integer lowNumber(String numStr) {
        String sub = StrUtil.sub(numStr, 0, 1);
        switch (sub) {
            case "一":
                return 1;
            case "二":
                return 2;
            case "三":
                return 3;
            case "四":
                return 4;
            case "五":
                return 5;
            case "六":
                return 6;
            case "七":
                return 7;
            default:
                return null;
        }
    }


}
