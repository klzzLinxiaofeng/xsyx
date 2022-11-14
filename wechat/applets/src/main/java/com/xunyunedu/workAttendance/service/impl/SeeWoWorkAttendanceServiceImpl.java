//package com.xunyunedu.workAttendance.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.xunyunedu.schoolResource.vo.PageInfo;
//import com.xunyunedu.util.hikvision.HikvisionUtil;
//import com.xunyunedu.util.hikvision.constant.HikvisionConstant;
//import com.xunyunedu.util.seewo.SeewoUtil;
//import com.xunyunedu.util.seewo.pojo.AttendanceServiceListEventClassRecordsResult;
//import com.xunyunedu.util.seewo.pojo.AttendanceServiceListLessonRecordsConditionalResult;
//import com.xunyunedu.workAttendance.dao.PjTeamDao;
//import com.xunyunedu.workAttendance.dao.PjTeamStudentDao;
//import com.xunyunedu.workAttendance.dao.PjTeamTeacherDao;
//import com.xunyunedu.workAttendance.model.*;
//import com.xunyunedu.workAttendance.service.SeeWoWorkAttendanceControllerService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @description: 希沃考勤业务层
// * @author: cmb
// * @create: 2020-11-05 18:19
// **/
//@Service("SeeWoWorkAttendanceControllerService")
//public class SeeWoWorkAttendanceServiceImpl implements SeeWoWorkAttendanceControllerService {
//    Logger log = LoggerFactory.getLogger(SeeWoWorkAttendanceServiceImpl.class);
//    @Autowired
//    private PjTeamTeacherDao pjTeamTeacherDao;
//    @Autowired
//    private PjTeamDao pjTeamDao;
//    @Autowired
//    private PjTeamStudentDao pjTeamStudentDao;
//
//    @Override
//    public Map<String,Object> findDetails(SeewoLessonWorkAttendance sw) {
//        AttendanceServiceListLessonRecordsConditionalResult result = SeewoUtil.LessonWorkAttendance(sw);
//        List<AttendanceServiceListLessonRecordsConditionalResult.RecordsItem> recordsItem = result.getResponseBody().getData().getRecords();
//        List<AttendanceServiceListLessonRecordsConditionalResult.LessonInfosItem> lessonInfosItem = result.getResponseBody().getData().getLessonInfos();
//        Map map=new HashMap();
//        PageInfo pageInfo=new PageInfo();
//        List<AttendanceServiceListLessonRecordsConditionalResult.RecordsItem> records=new ArrayList();
//        List<AttendanceServiceListLessonRecordsConditionalResult.LessonInfosItem> lessonInfos=new ArrayList<>();
//        //学生
//        PjTeamStudent pjTeamStudent = pjTeamStudentDao.findPjTeamStudentByStudentId(sw.getId());
//        PjTeam team = pjTeamDao.selectByPrimaryKey(pjTeamStudent.getTeamId());
//        if (pjTeamStudent != null) {
//            //获取当前学生的考勤信息
//            for (AttendanceServiceListLessonRecordsConditionalResult.RecordsItem item : recordsItem) {
//                if (item.getUserUid().equals(pjTeamStudent.getId())) {
//                    records.add(item);
//                }
//            }
//            //获取对应课程授课教师的信息
//            for (AttendanceServiceListLessonRecordsConditionalResult.RecordsItem record : records) {
//                for (AttendanceServiceListLessonRecordsConditionalResult.LessonInfosItem infosItem : lessonInfosItem) {
//                    if (record.getLessonId().equals(infosItem.getLessonId()) && infosItem.getRoomName().equals(team.getName())){
//                        lessonInfos.add(infosItem);
//                    }
//                }
//            }
//            map.put("recordsItem",records);
//            map.put("lessonInfosItem",lessonInfos);
//            pageInfo.setCurrentPage(result.getResponseBody().getData().getPage());
//            pageInfo.setTotalRows(lessonInfos.size());
//            map.put("pageInfo",pageInfo);
//            return map;
//
//        }
//        //授课教师
//        List<PjTeamTeacher> teamTeacher = pjTeamTeacherDao.findTeamTeacherByUserId(sw.getId());
//        List<Integer> teams=new ArrayList<>();
//        Integer teacherId=null;
////        获取班级id
//        if (teamTeacher.size()>0) {
//            for (PjTeamTeacher pjTeamTeacher : teamTeacher) {
//                PjTeam team1 = pjTeamDao.selectByPrimaryKey(pjTeamStudent.getTeamId());
//                teacherId = pjTeamTeacher.getTeacherId();
//                teams.add(team1.getId());
//            }
//            if (teams != null && !teamTeacher.stream().filter(t -> t.getPosition().equals("班主任")).findAny().isPresent()) {
//                for (Integer integer : teams) {
//                    List<PjTeamStudent> pjTeamStudents = pjTeamStudentDao.findPjTeamStudentByTeamId(integer);
//                    for (PjTeamStudent teamStudent : pjTeamStudents) {
//                        if (pjTeamStudent != null) {
//                            //获取当前学生的考勤信息
//                            for (AttendanceServiceListLessonRecordsConditionalResult.RecordsItem item : recordsItem) {
//                                if (item.getUserUid().equals(pjTeamStudent.getId())) {
//                                    records.add(item);
//                                }
//                            }
//                            //获取对应课程授课教师的信息
//                            for (AttendanceServiceListLessonRecordsConditionalResult.RecordsItem record : records) {
//                                for (AttendanceServiceListLessonRecordsConditionalResult.LessonInfosItem infosItem : lessonInfosItem) {
//                                    if (record.getLessonId().equals(infosItem.getLessonId()) && infosItem.getRoomName().equals(team.getName()) && infosItem.getTeacherUid().equals(teacherId)) {
//                                        lessonInfos.add(infosItem);
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//
//            }
//            //班主任
//            else if (teams != null && teamTeacher.stream().filter(t -> t.getPosition().equals("班主任")).findAny().isPresent()) {
//                for (Integer integer : teams) {
//                    List<PjTeamStudent> pjTeamStudents = pjTeamStudentDao.findPjTeamStudentByTeamId(integer);
//                    for (PjTeamStudent teamStudent : pjTeamStudents) {
//                        if (pjTeamStudent != null) {
//                            //获取当前学生的考勤信息
//                            for (AttendanceServiceListLessonRecordsConditionalResult.RecordsItem item : recordsItem) {
//                                if (item.getUserUid().equals(pjTeamStudent.getId())) {
//                                    records.add(item);
//                                }
//                            }
//                            //获取对应课程授课教师的信息
//                            for (AttendanceServiceListLessonRecordsConditionalResult.RecordsItem record : records) {
//                                for (AttendanceServiceListLessonRecordsConditionalResult.LessonInfosItem infosItem : lessonInfosItem) {
//                                    if (record.getLessonId().equals(infosItem.getLessonId()) && infosItem.getRoomName().equals(team.getName())) {
//                                        lessonInfos.add(infosItem);
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//            }
//            map.put("recordsItem", records);
//            map.put("lessonInfosItem", lessonInfos);
//            pageInfo.setCurrentPage(result.getResponseBody().getData().getPage());
//            pageInfo.setTotalRows(records.size());
//            map.put("pageInfo", pageInfo);
//            return map;
//        }
//        return null;
//
//
//
//    }
//
//    @Override
//    public AttendanceServiceListEventClassRecordsResult getEventWorkAttendance(SeewoEventWorkAttendance se) {
//        return SeewoUtil.EventWorkAttendance(se);
//    }
//
//    @Override
//    public Map<String, Object> getDoorWorkAttendance(HikvisionDoorReq req) {
//        String queryDoor = HikvisionConstant.queryDoor;
////        HikvisionDoorReq hik = new HikvisionDoorReq();
////        hik.setPageNo(1);
////        hik.setPageSize(20);
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//        String format = simpleDateFormat.format(date);
//        req.setStartTime(format);
//        req.setEndTime(format);
//        req.setReceiveStartTime(format);
//        req.setReceiveEndTime(format);
//        //启动客户端发送数据
//        String result = HikvisionUtil.GetCameraPreviewURL(queryDoor, req);
//        //将stringjson转对象
//        HikvisionDoorRes hikvisionDoorRes = JSON.parseObject(result, HikvisionDoorRes.class);
//        List<HikvisionDoorResList> hikvisionDoorResLists = new ArrayList<>();
//        HikvisionDoorResList[] list = hikvisionDoorRes.getList();
//        PageInfo pageInfo = new PageInfo();
////班主任则获取所有本班门禁信息
//        List<PjTeamTeacher> teamTeacher = pjTeamTeacherDao.findTeamTeacherByUserId(req.getUserId());
//        List<String> teamName=new ArrayList<>();
//        for (PjTeamTeacher pjTeamTeacher : teamTeacher) {
//            PjTeam team = pjTeamDao.selectByPrimaryKey(pjTeamTeacher.getTeamId());
//            teamName.add(team.getName());
//        }
//        if (teamTeacher.stream().filter(t->t.getPosition().equals("班主任")).findAny().isPresent()){
//            for (HikvisionDoorResList hikvisionDoorResList : list) {
//                for (String s : teamName) {
//                    hikvisionDoorResLists.add(hikvisionDoorResList);
//                }
//            }
//            pageInfo.setCurrentPage(hikvisionDoorRes.getPageNo().intValue());
//            pageInfo.setTotalPages(hikvisionDoorRes.getTotalPage().intValue());
//            pageInfo.setPageSize(hikvisionDoorRes.getPageSize().intValue());
//            pageInfo.setTotalRows(hikvisionDoorRes.getTotal().intValue());
//        }
////学生只获取自己的门禁信息
//        else {
//            PjTeamStudent pjTeamStudent = pjTeamStudentDao.findPjTeamStudentByStudentId(req.getUserId());
//            PjTeam team = pjTeamDao.selectByPrimaryKey(pjTeamStudent.getTeamId());
//            if (pjTeamStudent != null) {
//                for (HikvisionDoorResList hikvisionDoorResList : list) {
//                    if (hikvisionDoorResList.getOrgName().equals(team.getName()) && hikvisionDoorResList.getPersonName().equals(pjTeamStudent.getName())) {
//                        hikvisionDoorResLists.add(hikvisionDoorResList);
//                    }
//                }
//            } else {
//                log.info("查询学生失败");
//            }
//            pageInfo.setCurrentPage(hikvisionDoorRes.getPageNo().intValue());
//            pageInfo.setTotalRows(hikvisionDoorResLists.size());
//        }
//        Map map = new HashMap<>();
//        //封装page数据
//
//
//        map.put("pageInfo", pageInfo);
//        map.put("hikvisionDoorResLists", hikvisionDoorResLists);
//        return map;
//    }
//}