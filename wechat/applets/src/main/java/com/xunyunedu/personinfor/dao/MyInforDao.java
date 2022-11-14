package com.xunyunedu.personinfor.dao;

import com.xunyunedu.personinfor.pojo.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2020/9/19 14:19
 * @Description: 获取用户状态
 */
public interface MyInforDao {


    /**
     * 根据家长查询孩子
     *
     * @param name
     * @return
     */
    List<StudentPojo> getUserInfoByName(@Param("name") String name,@Param("schoolYear") String schoolYear);

    /**
     * 根据学生查询选课信息
     *
     * @param schoolId 学校id
     * @param childId  学生账号
     * @return
     */
    List<PublicClassPojo> getPublicClass(@Param("schoolId") Integer schoolId, @Param("childId") Integer childId);

    /**
     * 根据学生年级查询课程
     *
     * @param grade    年级
     * @param schoolId 学校id
     * @return
     */
    List<PublicClassPojo> getPublicClassByGrade(@Param("grade") Integer grade, @Param("schoolId") Integer schoolId
                                                ,@Param("type") Integer type);

    /**
     * @param cId      课程id
     * @param stuId    学生id
     * @param schoolId
     * @return
     */
    Integer getSelectState(@Param("cId") Integer cId, @Param("stuId") Integer stuId, @Param("schoolId") Integer schoolId);

    /**
     * 选课成功
     * 添加学生和选课信息
     *
     * @param cid
     * @param stuId
     * @param schooldId
     * @return
     */
    Integer addChoose(@Param("cId") Integer cid, @Param("stuId") Integer stuId, @Param("schooldId") Integer schooldId,@Param("isJiao") Integer isJiao);

    /**
     * 查询当前课程剩余报名人数
     *
     * @param cId       课程id
     * @param schooldId 学校id
     * @return
     */
    PublicClassPojo getEnrollNumber(@Param("cId") Integer cId, @Param("schooldId") Integer schooldId);
    Integer getXuankes(@Param("studentid") Integer studentid,@Param("cId") Integer cId, @Param("schooldId") Integer schooldId);



    /**
     * 查询当前课程剩余报名人数
     *
     * @param cid       课程id
     * @param schooldId 学校id
     * @Param("stuId")  学生id
     * @return
     */
    Integer updateXuanke(@Param("cid") Integer cid, @Param("schooldId") Integer schooldId);

    /**
     * 根据课程id修改已报名人数
     *
     * @param cId
     * @param schooldId
     * @return
     */
    Integer modifyByEnrollNumber(@Param("cId") Integer cId, @Param("schooldId") Integer schooldId);

    /**
     * 根据课程id修改已报名人数
     *
     * @param cid
     * @param schooldId
     * @return
     */
    void lessEnrollNumber(@Param("cid") Integer cid, @Param("schooldId") Integer schooldId);

    /**
     * 删除选课和学生关联的关系
     *  @param cid
     * @param stuId
     * @return
     */
    Integer removeClassStuRelation(@Param("cId") Integer cid, @Param("stuId") Integer stuId, @Param("schooldId") Integer schooldId);


    /**
     * @return
     */
    List<QuestionnairePojo> findBySchoolId(QuestionnairePojo questionnairePojo);

    void addLocation(@Param("schooldId") Integer schooldId, @Param("stuId") List<Integer> stuId, @Param("location") Integer location, @Param("date") String date, @Param("createDate") Date createDate);

    void updateLocation(@Param("schooldId") Integer schooldId, @Param("stuId") List<Integer> stuId, @Param("location") Integer location, @Param("date") String date, @Param("modify") Date createDate);

    List<PublicClassPojo> getAllCourseHistory(@Param("schoolId") Integer schooldId, @Param("stuId") Integer stuId
            ,@Param("type") Integer type);

    /**
     * 获取当前课程对应的课程时间
     *
     * @param schoolId
     * @param cid
     * @return
     */
    List<PublicTimePojo> findTimeInfoBySchoolIdAndClassId(@Param("schoolId") Integer schoolId, @Param("cid") Integer cid);

    @MapKey("id")
    Map<Integer, Map<String, Integer>> findTimeIdByStuIdAndSchoolId(@Param("stuId") Integer stuId,
                                                                    @Param("schoolId") Integer schoolId);

    PublicClassPojo courseDetails(@Param("cid") Integer cid, @Param("schooldId") Integer schooldId
                                 );

    List<PublicClassPojo> findExpireDateClass(PublicClassPojo publicClassPojo);

    List<Integer> findPublicUserByIdAndSchoolId(@Param("id") Integer id, @Param("schoolId") Integer schoolId);

    void addChoseHistory(PublicElectiveHistoryPojo historyPojo);

    void updatePublicClass(PublicClassPojo publicPojo);

    /*
    * 查询但钱学生已选课程
    */
    List<PublicClassPojo> findByYiXuan(@Param("id") Integer id, @Param("schoolId") Integer schoolId);
}
