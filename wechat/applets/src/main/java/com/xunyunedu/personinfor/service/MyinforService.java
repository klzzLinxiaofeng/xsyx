package com.xunyunedu.personinfor.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.personinfor.pojo.PublicClassPojo;
import com.xunyunedu.personinfor.pojo.QuestionnairePojo;
import com.xunyunedu.personinfor.pojo.StudentPojo;
import com.xunyunedu.student.pojo.ParentPojo;

import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2020/9/19 14:07
 * @Description: 用户授权登录
 */
public interface MyinforService {


    /**
     * @param name
     * @return
     */
    Map<String, List<StudentPojo>> getInformation(String name);

    Boolean updateXuanke(Integer cid, Integer stuId, Integer schooldId);
    //课程列表
    List<PublicClassPojo> getChildren(Integer integer, Integer grade, Integer stuId,Integer coursetype);
    //免费课程
    Integer addChooseClass(Integer cid, Integer stuId, Integer schooldId,Integer isJiao);
    //收费课程
    Integer addChooseClassShouFei(Integer cid, Integer stuId, Integer schooldId);
    //支付成功修改用户状态
    Integer paySuccess(Integer cid, Integer stuId, Integer schooldId,Integer isJiao);

    void modifyChooseNum(Integer cid, Integer stuId, Integer schooldId);

    void removeLoginStaus(String token);

    Map<String, List<PublicClassPojo>> getAllCourse(Integer schooldId);

    PageInfo<QuestionnairePojo> findBySchooldIdAndObject(QuestionnairePojo questionnairePojo, Integer pageNum, Integer pageSize);

    void addLocation(Integer schooldId, List<Integer> stuId, Integer location);

    void updateLocation(Integer schooldId, List<Integer> integers, Integer location);

    List<PublicClassPojo> getAllCourseHistory(Integer schooldId, Integer stuId,Integer type);

    PublicClassPojo courseDetails(Integer cid, Integer schooldId, Integer stuId, Integer type);

    PublicClassPojo counservt(Integer cid, Integer schooldId);

    List<Integer> findPublicUserByIdAndSchoolId(Integer id, Integer schoolId);
    Integer getxuankes( Integer stuId,Integer cid, Integer schooldId);

    ParentPojo getParentInfo(String name);

    String modifyParentInfo(ParentPojo parentPojo);

}
